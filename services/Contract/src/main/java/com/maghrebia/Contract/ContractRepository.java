package com.maghrebia.Contract;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ContractRepository extends JpaRepository<Contract,Long> {
    List<Contract> findByDateExpirationBetween(LocalDate startDate, LocalDate endDate);



}
