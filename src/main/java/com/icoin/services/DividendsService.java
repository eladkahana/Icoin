package com.icoin.services;

import com.icoin.Objects.DividendsEntity;
import com.icoin.repositories.DividendsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DividendsService {

    @Autowired
    private DividendsRepository dividendsRepository;

    public Integer save(DividendsEntity vO) {
        DividendsEntity bean = new DividendsEntity();
        BeanUtils.copyProperties(vO, bean);
        bean = dividendsRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        dividendsRepository.deleteById(id);
    }

    public void update(Integer id, DividendsEntity vO) {
        DividendsEntity bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        dividendsRepository.save(bean);
    }

    public DividendsEntity getById(Integer id) {
        DividendsEntity original = requireOne(id);
        return toDTO(original);
    }

    public Page<DividendsEntity> query(DividendsEntity vO) {
        throw new UnsupportedOperationException();
    }

    private DividendsEntity toDTO(DividendsEntity original) {
        DividendsEntity bean = new DividendsEntity();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private DividendsEntity requireOne(Integer id) {
        return dividendsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public void AddDividend (String ticker, double amount, Date date, String token){
        dividendsRepository.AddDividend(ticker,amount,date,token);
    }

    public List<Object[]> DividendHistory (String token, String ticker){
        return dividendsRepository.DividendHistory(token, ticker);
    }


    public List<Object[]> AllDividends (String token){
        return dividendsRepository.AllDividends(token);
    }



}
