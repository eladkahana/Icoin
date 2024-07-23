package com.icoin.controllers;

import com.icoin.Objects.ManagementFeeEntity;
import com.icoin.services.ManagementFeeService;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.sql.Date;
import java.util.List;

@Transactional
@CrossOrigin
@Validated
@RestController
@RequestMapping("/managementFee")
public class ManagementFeeController {

    @Autowired
    private ManagementFeeService managementFeeService;

    @PostMapping
    public String save(@Valid @RequestBody ManagementFeeEntity vO) {
        return managementFeeService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        managementFeeService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody ManagementFeeEntity vO) {
        managementFeeService.update(id, vO);
    }

    @GetMapping("/{id}")
    public ManagementFeeEntity getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return managementFeeService.getById(id);
    }

    @GetMapping
    public Page<ManagementFeeEntity> query(@Valid ManagementFeeEntity vO) {
        return managementFeeService.query(vO);
    }

    @GetMapping("/GetManagmentFee")
    public List<Object[]> GetManagmentFee (@RequestParam String token)
    {
        return managementFeeService.GetManagmentFee(token);
    }


    @PutMapping("/AddManagmentFee")
    public void AddManagmentFee (@RequestParam  String token, @RequestParam double amount, @RequestParam Date date){
        managementFeeService.AddManagmentFee(token,amount,date);
    }
}
