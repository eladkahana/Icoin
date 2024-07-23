package com.icoin.services;

import com.icoin.Objects.TryLoginEntity;
import com.icoin.repositories.TryLoginRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TryLoginService {

    @Autowired
    private TryLoginRepository tryLoginRepository;

    public Integer save(TryLoginEntity vO) {
        TryLoginEntity bean = new TryLoginEntity();
        BeanUtils.copyProperties(vO, bean);
        bean = tryLoginRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        tryLoginRepository.deleteById(id);
    }

    public void update(Integer id, TryLoginEntity vO) {
        TryLoginEntity bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        tryLoginRepository.save(bean);
    }

    public TryLoginEntity getById(Integer id) {
        TryLoginEntity original = requireOne(id);
        return toDTO(original);
    }

    public Page<TryLoginEntity> query(TryLoginEntity vO) {
        throw new UnsupportedOperationException();
    }

    private TryLoginEntity toDTO(TryLoginEntity original) {
        TryLoginEntity bean = new TryLoginEntity();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private TryLoginEntity requireOne(Integer id) {
        return tryLoginRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }


    public List<Object[]> Login(String hash, String IP, String Email){
        return tryLoginRepository.Login(hash,IP,Email);
    }
}
