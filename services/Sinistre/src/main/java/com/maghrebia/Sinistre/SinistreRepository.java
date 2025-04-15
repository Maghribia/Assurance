package com.maghrebia.Sinistre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SinistreRepository extends MongoRepository<Sinistre,String> {
    Optional<Sinistre> findById(String id);
}
