package com.example.microservice_candidats;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CandidatRepository extends JpaRepository<Candidat, Integer> {
    @Query("SELECT c FROM Candidat c WHERE c.nom LIKE :name")
    Page<Candidat> candidatByNom(@Param("name") String n, Pageable pageable);
}
