package com.icoin.repositories;

import com.icoin.Objects.PasswordsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PasswordsRepository extends JpaRepository<PasswordsEntity, Integer>, JpaSpecificationExecutor<PasswordsEntity> {

}