package com.maghrebia.Devis;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class DevisVoyageService {

    @Autowired
    private DevisVoyageRepository devisVoyageRepository;

    public DevisVoyage createDevisVoyage(DevisVoyage devisVoyage) {
        return devisVoyageRepository.save(devisVoyage);
    }

    public List<DevisVoyage> getAllDevisVoyage() {
        return devisVoyageRepository.findAll();
    }

    public Optional<DevisVoyage> getDevisVoyageById(String id) {
        return devisVoyageRepository.findById(id);
    }

    public DevisVoyage updateDevisVoyage(String id, DevisVoyage updatedDevis) {
        return devisVoyageRepository.findById(id).map(devis -> {
            devis.setDureeContrat(updatedDevis.getDureeContrat());
            devis.setDateDepart(updatedDevis.getDateDepart());
            devis.setDateRetour(updatedDevis.getDateRetour());
            devis.setDestination(updatedDevis.getDestination());
            devis.setTrancheAge(updatedDevis.getTrancheAge());
            devis.setZoneGeographique(updatedDevis.getZoneGeographique());
            return devisVoyageRepository.save(devis);
        }).orElseThrow(() -> new RuntimeException("DevisVoyage not found"));
    }

    public void deleteDevisVoyage(String id) {
        devisVoyageRepository.deleteById(id);
    }
}