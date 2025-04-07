package com.maghrebia.Devis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevisVieRepository extends JpaRepository<DevisVie, String> {
}
