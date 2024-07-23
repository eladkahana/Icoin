package com.icoin.repositories;

import com.icoin.Objects.ManagementFeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface ManagementFeeRepository extends JpaRepository<ManagementFeeEntity, Integer>, JpaSpecificationExecutor<ManagementFeeEntity> {


    @Procedure(name = "GetManagmentFee")
    List<Object[]> GetManagmentFee (@Param("token") String token);


    @Procedure(name = "AddManagmentFee")
    void AddManagmentFee (@Param("token")  String token, @Param("amount") double amount, @Param("date") Date date);
}