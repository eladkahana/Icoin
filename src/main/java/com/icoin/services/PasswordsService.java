package com.icoin.services;

import com.icoin.Objects.PasswordsEntity;
import com.icoin.repositories.PasswordsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class PasswordsService {

    @Autowired
    private PasswordsRepository passwordsRepository;

    public Integer save(PasswordsEntity vO) {
        PasswordsEntity bean = new PasswordsEntity();
        BeanUtils.copyProperties(vO, bean);
        bean = passwordsRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        passwordsRepository.deleteById(id);
    }

    public void update(Integer id, PasswordsEntity vO) {
        PasswordsEntity bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        passwordsRepository.save(bean);
    }

    public PasswordsEntity getById(Integer id) {
        PasswordsEntity original = requireOne(id);
        return toDTO(original);
    }

    public Page<PasswordsEntity> query(PasswordsEntity vO) {
        throw new UnsupportedOperationException();
    }

    private PasswordsEntity toDTO(PasswordsEntity original) {
        PasswordsEntity bean = new PasswordsEntity();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private PasswordsEntity requireOne(Integer id) {
        return passwordsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
