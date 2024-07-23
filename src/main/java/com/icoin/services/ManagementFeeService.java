package com.icoin.services;

import com.icoin.Objects.ManagementFeeEntity;
import com.icoin.repositories.ManagementFeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ManagementFeeService {

    @Autowired
    private ManagementFeeRepository managementFeeRepository;

    public Integer save(ManagementFeeEntity vO) {
        ManagementFeeEntity bean = new ManagementFeeEntity();
        BeanUtils.copyProperties(vO, bean);
        bean = managementFeeRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        managementFeeRepository.deleteById(id);
    }

    public void update(Integer id, ManagementFeeEntity vO) {
        ManagementFeeEntity bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        managementFeeRepository.save(bean);
    }

    public ManagementFeeEntity getById(Integer id) {
        ManagementFeeEntity original = requireOne(id);
        return toDTO(original);
    }

    public Page<ManagementFeeEntity> query(ManagementFeeEntity vO) {
        throw new UnsupportedOperationException();
    }

    private ManagementFeeEntity toDTO(ManagementFeeEntity original) {
        ManagementFeeEntity bean = new ManagementFeeEntity();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private ManagementFeeEntity requireOne(Integer id) {
        return managementFeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public List<Object[]> GetManagmentFee(String token){
        return managementFeeRepository.GetManagmentFee(token);
    }


    public void AddManagmentFee ( String token, double amount, Date date){
        managementFeeRepository.AddManagmentFee(token,amount,date);
    }
}
