package com.icoin.controllers;

import com.icoin.Objects.TryLoginEntity;
import com.icoin.services.TryLoginService;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Transactional
@CrossOrigin
@Validated
@RestController
@RequestMapping("/tryLogin")
public class TryLoginController {

    @Autowired
    private TryLoginService tryLoginService;

    @PostMapping
    public String save(@Valid @RequestBody TryLoginEntity vO) {
        return tryLoginService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        tryLoginService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody TryLoginEntity vO) {
        tryLoginService.update(id, vO);
    }

    @GetMapping("/{id}")
    public TryLoginEntity getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return tryLoginService.getById(id);
    }

    @GetMapping
    public Page<TryLoginEntity> query(@Valid TryLoginEntity vO) {
        return tryLoginService.query(vO);
    }



    @PostMapping("/Login")
    public List<Object[]> login(@RequestBody Map<String, String> loginData) {
        String hash = loginData.get("hash");
        String IP = loginData.get("IP");
        String Email = loginData.get("Email");
        return tryLoginService.Login(hash, IP, Email);
    }

}
