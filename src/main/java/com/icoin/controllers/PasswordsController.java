package com.icoin.controllers;

import com.icoin.Objects.PasswordsEntity;
import com.icoin.services.PasswordsService;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/passwords")
public class PasswordsController {

    @Autowired
    private PasswordsService passwordsService;

    @PostMapping
    public String save(@Valid @RequestBody PasswordsEntity vO) {
        return passwordsService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        passwordsService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody PasswordsEntity vO) {
        passwordsService.update(id, vO);
    }

    @GetMapping("/{id}")
    public PasswordsEntity getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return passwordsService.getById(id);
    }

    @GetMapping
    public Page<PasswordsEntity> query(@Valid PasswordsEntity vO) {
        return passwordsService.query(vO);
    }
}
