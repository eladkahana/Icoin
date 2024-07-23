package com.icoin.repositories;

import com.icoin.Objects.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsersRepository extends JpaRepository<UsersEntity, Integer>, JpaSpecificationExecutor<UsersEntity> {


    @Procedure(name = "CreateUser")
    void CreateUser (@Param("Name") String Name , @Param("Email") String Email , @Param("hash") String hash);

}