package com.icoin.controllers;

import com.icoin.Objects.UsersEntity;
import com.icoin.services.UsersService;
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
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping
    public String save(@Valid @RequestBody UsersEntity vO) {
        return usersService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        usersService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody UsersEntity vO) {
        usersService.update(id, vO);
    }

    @GetMapping("/{id}")
    public UsersEntity getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return usersService.getById(id);
    }

    @GetMapping
    public Page<UsersEntity> query(@Valid UsersEntity vO) {
        return usersService.query(vO);
    }


    @PutMapping("/CreateUser")
    public void CreateUser(@RequestBody Map<String, String> loginData) {
        String hash = loginData.get("password");
        String Name = loginData.get("Name");
        String Email = loginData.get("email");
        usersService.CreateUSer(Name, Email, hash);
    }

}
