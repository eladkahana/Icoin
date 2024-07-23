package com.icoin.controllers;

import com.icoin.Objects.HoldingsEntity;
import com.icoin.services.HoldingsService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

@Transactional
@CrossOrigin
@Validated
@RestController
@RequestMapping("/holdings")
public class HoldingsController {

    @Autowired
    private HoldingsService holdingsService;

    @PostMapping
    public String save(@Valid @RequestBody HoldingsEntity vO) {
        return holdingsService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        holdingsService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody HoldingsEntity vO) {
        holdingsService.update(id, vO);
    }

    @GetMapping("/{id}")
    public HoldingsEntity getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return holdingsService.getById(id);
    }

    @GetMapping
    public Page<HoldingsEntity> query(@Valid HoldingsEntity vO) {
        return holdingsService.query(vO);
    }


    @GetMapping("/GetGroupedHoldings")
    public List<List<String>>  getGroupedHoldings(@RequestParam String token) throws URISyntaxException, IOException, InterruptedException {
        return holdingsService.GetGroupedHoldings(token);
    }


    @GetMapping("/GetHistory")
    public List<List<String>>  GetHistory(@RequestParam String token) throws URISyntaxException, IOException, InterruptedException {
        return holdingsService.GetHistory(token);
    }

/*    @GetMapping("/Compare")
    public List<List<Double>>  Compare(@RequestParam String token) throws Exception {
        return holdingsService.compare(token);
    }*/

    @PutMapping("/addHolding")
    public void addHolding (@RequestParam String ticker,
                            @RequestParam double amountOfShares,
                            @RequestParam double investAmount,
                            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") String date,
                            @RequestParam String token) {
        holdingsService.addHolding(ticker, amountOfShares, investAmount, date, token);
    }

    @GetMapping("/TickerOverview")
    public List<List<String>>  TickerOverview(@RequestParam String token, @RequestParam String ticker) throws URISyntaxException, IOException, InterruptedException {
        return holdingsService.TickerOverview(token, ticker);
    }


    @GetMapping("/getSMA")
    public List<List<Object>> getSMA(@RequestParam String token){

        return holdingsService.getSMA(token);
    }

    @PutMapping("/SplitUnsplit")
    public void SplitUnsplit (@RequestParam String token,
                            @RequestParam String ticker,
                            @RequestParam int before,
                            @RequestParam int after) {
        holdingsService.SplitUnsplit(token, ticker, before, after);
    }

}
