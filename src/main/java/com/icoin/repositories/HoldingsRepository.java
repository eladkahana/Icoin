package com.icoin.repositories;

import com.icoin.Objects.HoldingsEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface HoldingsRepository extends JpaRepository<HoldingsEntity, Integer>, JpaSpecificationExecutor<HoldingsEntity> {


    @Procedure(name = "GetGroupedHoldings")
    List<Object[]> GetGroupedHoldings (@Param("token") String token);

    @Procedure(name = "GetHistory")
    List<Object[]> GetHistory (@Param("token") String token);

    @Procedure(name = "addHolding")
    void addHolding (@Param("ticker") String ticker, @Param("amountOfShares") double amountOfShares,
                     @Param("investAmount") double investAmount, @Param("date") Date date, @Param("token") String token);


    @Procedure(name = "TickerOverview")
    List<Object[]> TickerOverview ( @Param("token") String token, @Param("ticker") String ticker);


    @Procedure(name = "SplitUnsplit")
    public void SplitUnsplit(@Param("token")  String token, @Param("ticker") String ticker, @Param("before") int before, @Param("after") int after);
}