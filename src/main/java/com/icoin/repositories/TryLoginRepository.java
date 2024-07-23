package com.icoin.repositories;

import com.icoin.Objects.TryLoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TryLoginRepository extends JpaRepository<TryLoginEntity, Integer>, JpaSpecificationExecutor<TryLoginEntity> {


    @Procedure(name = "Login")
    List<Object[]> Login (@Param("hash") String hash, @Param("IP") String IP, @Param("Email") String Email);

}