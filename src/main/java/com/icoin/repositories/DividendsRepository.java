package com.icoin.repositories;

import com.icoin.Objects.DividendsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface DividendsRepository extends JpaRepository<DividendsEntity, Integer>, JpaSpecificationExecutor<DividendsEntity> {


    @Procedure(name = "AddDividend")
    void AddDividend (@Param("ticker") String ticker, @Param("amount") double amount, @Param("date") Date date, @Param("token")  String token);

    @Procedure(name = "DividendHistory")
    public List<Object[]> DividendHistory(@Param("token")  String token, @Param("ticker") String ticker);

    @Procedure(name = "AllDividends")
    public List<Object[]> AllDividends(@Param("token")  String token);


}