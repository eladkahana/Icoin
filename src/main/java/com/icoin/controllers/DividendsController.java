package com.icoin.controllers;

import com.icoin.Objects.DividendsEntity;
import com.icoin.services.DividendsService;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

@Transactional
@CrossOrigin
@Validated
@RestController
@RequestMapping("/dividends")
public class DividendsController {

    @Autowired
    private DividendsService dividendsService;

    @PostMapping
    public String save(@Valid @RequestBody DividendsEntity vO) {
        return dividendsService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        dividendsService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody DividendsEntity vO) {
        dividendsService.update(id, vO);
    }

    @GetMapping("/{id}")
    public DividendsEntity getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return dividendsService.getById(id);
    }

    @GetMapping
    public Page<DividendsEntity> query(@Valid DividendsEntity vO) {
        return dividendsService.query(vO);
    }

    @PutMapping("/AddDividend")
    public void AddDividend (@RequestParam String ticker,@RequestParam double amount,@RequestParam Date date,@RequestParam  String token){
        dividendsService.AddDividend(ticker,amount,date,token);
    }

    @GetMapping("/DividendHistory")
    public List<Object[]> DividendHistory(@RequestParam String token, @RequestParam String ticker){
        return dividendsService.DividendHistory(token,ticker);
    }

    @GetMapping("/AllDividends")
    public List<Object[]> AllDividends(@RequestParam String token){
        return dividendsService.AllDividends(token);
    }
}
