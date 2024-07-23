package com.icoin.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icoin.Objects.HoldingsEntity;
import com.icoin.repositories.HoldingsRepository;
import org.apache.commons.lang.ObjectUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
public class HoldingsService {

    @Autowired
    private HoldingsRepository holdingsRepository;
    private HashMap<String, Double> prices = new HashMap<>();
    private final String APIFIN = "co1hk89r01qgulhr8ft0co1hk89r01qgulhr8ftg";
    private final String APIPOL = "2FH0P2IQ7UEZIYYK";

    private final String APIMOD = "a0ELf4SaxQwrF8N3rK2tg1mMuSluHJbp";

    public Integer save(HoldingsEntity vO) {
        HoldingsEntity bean = new HoldingsEntity();
        BeanUtils.copyProperties(vO, bean);
        bean = holdingsRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        holdingsRepository.deleteById(id);
    }

    public void update(Integer id, HoldingsEntity vO) {
        HoldingsEntity bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        holdingsRepository.save(bean);
    }

    public HoldingsEntity getById(Integer id) {
        HoldingsEntity original = requireOne(id);
        return toDTO(original);
    }

    public Page<HoldingsEntity> query(HoldingsEntity vO) {
        throw new UnsupportedOperationException();
    }

    private HoldingsEntity toDTO(HoldingsEntity original) {
        HoldingsEntity bean = new HoldingsEntity();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private HoldingsEntity requireOne(Integer id) {
        return holdingsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }


    public List<List<String>> GetGroupedHoldings(String token) throws URISyntaxException {
        List<Object[]> dataList = holdingsRepository.GetGroupedHoldings(token);
        List<List<String>> stockMetrics = new ArrayList<>();

        if (dataList.isEmpty()) {
            System.out.println("Error: No data found.");
            return stockMetrics;
        }

        // Replace API_KEY with your Alpha Vantage API key

        for (Object[] data : dataList) {
            List<String> stockMetricsForThisStock = new ArrayList<>();
            String symbol = data[0].toString(); // Get the stock ticker from the data list
            double numberOfShares = Double.parseDouble(data[1].toString()); // Get the initial investment amount
            double initialInvestment = Double.parseDouble(data[2].toString()); // Get the number of shares
            double dividends = data[3] != null ? Double.parseDouble(data[3].toString()) : 0.0; // Get the total enuedividends rev



            // Construct the URL for the API request
            String apiUrl = "https://finnhub.io/api/v1/quote?symbol=" + symbol + "&token=" + this.APIFIN;

            try {
                URL url = new URL(apiUrl);
                // Create an HttpURLConnection
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                // Read response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Check if the request was successful
                if (connection.getResponseCode() == 200) {
                    // Parse JSON response
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode root = mapper.readTree(response.toString());

                    // Extract relevant data from the response
                    double currentPrice = root.path("c").asDouble(); // Current price
                    double dailyChangeValue = root.path("d").asDouble(); // Change from previous close
                    double dailyPerformance = dailyChangeValue / currentPrice * 100;
                    double currentWorth = currentPrice * numberOfShares;
                    prices.put(symbol, currentPrice);


                    // Calculate additional metrics
                    double dailyProfit = dailyChangeValue * numberOfShares;
                    double generalChange = ((currentWorth - initialInvestment) / initialInvestment) * 100;
                    double generalGain = generalChange * initialInvestment / 100;

                    // Add metrics to the list for this stock
                    stockMetricsForThisStock.add(symbol);
                    stockMetricsForThisStock.add(Double.toString(initialInvestment));
                    stockMetricsForThisStock.add(Double.toString(numberOfShares));
                    stockMetricsForThisStock.add(Double.toString(currentWorth));
                    stockMetricsForThisStock.add(Double.toString(dailyPerformance)); // Use daily change instead of percentage change
                    stockMetricsForThisStock.add(Double.toString(dailyProfit));
                    stockMetricsForThisStock.add(Double.toString(generalChange));
                    stockMetricsForThisStock.add(Double.toString(generalGain));
                    stockMetricsForThisStock.add(Double.toString(dividends));
                } else {
                    System.out.println("Error: Unable to fetch data. Status code: " + connection.getResponseCode());
                }
            } catch (IOException e) {
                System.out.println("Error occurred while making API request: " + e.getMessage());
            }
            // Add metrics for this stock to the main list
            stockMetrics.add(stockMetricsForThisStock);
        }
        return stockMetrics;
    }


    public List<List<String>> GetHistory(String token){
        List<Object[]> dataList = holdingsRepository.GetHistory(token);
        List<List<String>> stockMetrics = new ArrayList<>();

        for (Object[] data : dataList) {
            List<String> stockMetricsForThisStock = new ArrayList<>();
            String symbol = data[0].toString(); // Get the stock ticker from the data list
            double numberOfShares = Double.parseDouble(data[1].toString()); // Get the initial investment amount
            double initialInvestment = Double.parseDouble(data[2].toString()); // Get the number of shares
            Date date = (Date) data[3];


            double currentWorth = prices.get(symbol);

            double generalChange = (((currentWorth * numberOfShares)- initialInvestment) / initialInvestment) * 100;


            stockMetricsForThisStock.add(symbol);
            stockMetricsForThisStock.add(Double.toString(numberOfShares));
            stockMetricsForThisStock.add(Double.toString(generalChange));
            stockMetricsForThisStock.add(String.valueOf(date));
            stockMetricsForThisStock.add(String.valueOf(initialInvestment));
            stockMetrics.add(stockMetricsForThisStock);

        }

        return stockMetrics;

    }


/*    public List<List<Double>> compare(String token) {
        List<Object[]> entries = holdingsRepository.GetGroupedHoldings(token);
        List<List<Double>> comparisonResults = new ArrayList<>();

        try {
            // Fetch historical prices for QQQ and S&P
            List<Double> qqqPrices = fetchMonthlyStockPrices("QQQ");
            List<Double> spPrices = fetchMonthlyStockPrices("S&P");
            List<Double> protPrices = new ArrayList<>();

            // Iterate over the portfolio entries
            for (Object[] entryData : entries) {
                String ticker = (String) entryData[0];
                double numberOfShares = (double) entryData[1];

                // Fetch historical prices for the ticker
                List<Double> historicalPrices = fetchMonthlyStockPrices(ticker);

                for (int i = 0; i < historicalPrices.size(); i++) {
                    double result = Math.round(historicalPrices.get(i) * numberOfShares * 100.0) / 100.0;
                    if (protPrices.size() > i) {
                        // If the list already has elements at index i, update the value
                        protPrices.set(i, protPrices.get(i) + result);
                    } else {
                        // If the list does not have an element at index i, add it
                        protPrices.add(i, result);
                    }
                }
            }

            // Normalize the portfolio prices
            double protInitialValue = protPrices.get(0);
            for (int i = 0; i < spPrices.size(); i++) {
                spPrices.set(i, spPrices.get(i) / (spPrices.get(0) / protInitialValue));
            }

            for (int i = 0; i < qqqPrices.size(); i++) {
                qqqPrices.set(i, qqqPrices.get(i) / (qqqPrices.get(0) / protInitialValue));
            }

            // Add the lists to the comparisonResults
            comparisonResults.add(qqqPrices);
            comparisonResults.add(spPrices);
            comparisonResults.add(protPrices);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return comparisonResults;
    }



    // Method to fetch stock price for a given ticker and date using the AlphaVantage API
    public List<Double> fetchMonthlyStockPrices(String ticker) throws Exception {
        List<Double> monthlyPrices = new ArrayList<>();
        // Construct URL for API request
        String apiUrl = "https://www.alphavantage.co/query?"
                + "function=TIME_SERIES_DAILY"
                + "&symbol=" + ticker
                + "&outputsize=compact"
                + "&apikey=" + this.APIPOL;

        // Make HTTP request
        StringBuilder response = new StringBuilder();
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // Parse JSON response to get the stock prices
        JSONObject jsonResponse = new JSONObject(response.toString());

        JSONObject timeSeries = jsonResponse.getJSONObject("Time Series (Daily)");
        List<Double> dailyPrices = new ArrayList<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (Iterator it = timeSeries.keys(); it.hasNext(); ) {
            String dateStr = (String) it.next();
            LocalDate date = LocalDate.parse(dateStr, dateFormatter);
            double price = timeSeries.getJSONObject(dateStr).getDouble("4. close");
            dailyPrices.add(price);
        }

        return  dailyPrices;
    }*/
    public void addHolding(String ticker, double amountOfShares, double investAmount, String date, String token){
        java.sql.Date sqlDate = java.sql.Date.valueOf(date);

        holdingsRepository.addHolding(ticker, amountOfShares, investAmount, sqlDate, token);
    }

    public List<List<String>> TickerOverview(String token, String ticker) {
        List<Object[]> dataList = holdingsRepository.TickerOverview(token, ticker);
        List<List<String>> stockMetrics = new ArrayList<>();

        for (Object[] data : dataList) {
            List<String> stockMetricsForThisStock = new ArrayList<>();
            double numberOfShares = Double.parseDouble(data[0].toString()); // Get the number of shares
            double initialInvestment = Double.parseDouble(data[1].toString()); // Get the initial investment amount
            String date = data[2].toString(); // Get the date

            double currentPrice = prices.get(ticker);
            double currentWorth = currentPrice * numberOfShares;

            // Calculate additional metrics
            double generalChange = ((currentWorth - initialInvestment) / initialInvestment) * 100;
            double generalGain = generalChange * initialInvestment / 100;

            // Add metrics to the list for this stock
            stockMetricsForThisStock.add(date);
            stockMetricsForThisStock.add(Double.toString(initialInvestment));
            stockMetricsForThisStock.add(Double.toString(numberOfShares));
            stockMetricsForThisStock.add(Double.toString(currentWorth));
            stockMetricsForThisStock.add(Double.toString(generalChange));
            stockMetricsForThisStock.add(Double.toString(generalGain));

            // Add stock metrics for this stock to the overall list
            stockMetrics.add(stockMetricsForThisStock);
        }
        return stockMetrics;
    }

    public List<List<Object>> getSMA(String token) {
        List<Object[]> dataList = holdingsRepository.GetGroupedHoldings(token);
        List<List<Object>> result = new ArrayList<>();

        // Calculate start and end dates
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String start = startDate.format(formatter);
        String end = endDate.format(formatter);

        for (Object[] data : dataList) {
            String symbol = data[0].toString(); // Get the stock ticker from the data list

            String apiUrl = String.format("https://financialmodelingprep.com/api/v3/technical_indicator/1day/%s?type=sma&period=200&apikey=%s&from=%s&to=%s", symbol, this.APIMOD, start, end);

            try {
                // Make API request
                URL url = new URL(apiUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-Type", "application/json");
                int responseCode = conn.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    // Parse JSON response
                    JSONArray jsonArray = new JSONArray(response.toString());
                    JSONObject firstItem = jsonArray.getJSONObject(0);
                    double close = firstItem.getDouble("close");
                    double sma = firstItem.getDouble("sma");

                    // Add ticker, close price, and SMA to the result list
                    List<Object> rowData = new ArrayList<>();
                    rowData.add(symbol);
                    rowData.add(close);
                    rowData.add(sma);
                    result.add(rowData);
                } else {
                    // Handle error
                    System.out.println("HTTP error: " + responseCode);
                }
            } catch (IOException e) {
                e.printStackTrace();
                // Handle exception
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }


    public void SplitUnsplit( String token,String ticker, int before, int after){
        holdingsRepository.SplitUnsplit(token, ticker,before,after);
    }

}
