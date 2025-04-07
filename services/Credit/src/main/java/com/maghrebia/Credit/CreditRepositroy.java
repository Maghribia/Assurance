package com.maghrebia.Credit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface CreditRepositroy extends JpaRepository<Credit,Long> {
    Optional<Credit> findById(long id);

    void deleteById(long id);
}
