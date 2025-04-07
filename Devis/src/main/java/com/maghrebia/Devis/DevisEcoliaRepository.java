package com.maghrebia.Devis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevisEcoliaRepository extends JpaRepository<DevisEcolia, String> {
    // Add custom query methods if needed
}
