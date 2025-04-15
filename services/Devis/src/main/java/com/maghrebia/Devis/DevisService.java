package com.maghrebia.Devis;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class DevisService {

    @Autowired
    private DevisRepository devisRepository;

    // Create a new Devis
    public Devis createDevis(Devis devis) {
        return devisRepository.save(devis);
    }

    // Get all Devis
    public List<Devis> getAllDevis() {
        return devisRepository.findAll();
    }

    // Get Devis by ID
    public Optional<Devis> getDevisById(String id) { // Updated parameter name
        return devisRepository.findById(id);
    }

    // Update Devis
    public Devis updateDevis(String id, Devis updatedDevis) { // Updated parameter name
        Optional<Devis> existingDevis = devisRepository.findById(id);

        if (existingDevis.isPresent()) {
            Devis devis = existingDevis.get();
            devis.setMontantEstime(updatedDevis.getMontantEstime());
            devis.setDateDemande(updatedDevis.getDateDemande());
            devis.setTypeAssurance(updatedDevis.getTypeAssurance());

            return devisRepository.save(devis);
        } else {
            throw new RuntimeException("Devis non trouvé avec l'ID : " + id);
        }
    }

    // Delete Devis
    public void deleteDevis(String id) { // Updated parameter name
        devisRepository.deleteById(id);
    }

    // Generate PDF



}