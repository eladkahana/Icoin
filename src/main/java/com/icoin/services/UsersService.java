package com.icoin.services;

import com.icoin.Objects.UsersEntity;
import com.icoin.repositories.UsersRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public Integer save(UsersEntity vO) {
        UsersEntity bean = new UsersEntity();
        BeanUtils.copyProperties(vO, bean);
        bean = usersRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        usersRepository.deleteById(id);
    }

    public void update(Integer id, UsersEntity vO) {
        UsersEntity bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        usersRepository.save(bean);
    }

    public UsersEntity getById(Integer id) {
        UsersEntity original = requireOne(id);
        return toDTO(original);
    }

    public Page<UsersEntity> query(UsersEntity vO) {
        throw new UnsupportedOperationException();
    }

    private UsersEntity toDTO(UsersEntity original) {
        UsersEntity bean = new UsersEntity();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private UsersEntity requireOne(Integer id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }


    public void CreateUSer(String Name, String Email, String hash){
        usersRepository.CreateUser(Name , Email, hash);
    }

}
