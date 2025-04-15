package com.maghrebia.Devis;

import com.maghrebia.Devis.Devis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DevisRepository extends JpaRepository<Devis, String> {
    Optional<Devis> findById(String id);

    void deleteById(String id);
    // Custom query to find all Devis by Client ID
//         List<Devis> findByClient_NumClient(int clientId);
}