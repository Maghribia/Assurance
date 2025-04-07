package com.maghrebia.Devis;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DevisVieService {
    @Autowired
    private DevisVieRepository devisVieRepo;

    // Ajouter un devis
    public DevisVie addDevis(DevisVie devis) {
        return devisVieRepo.save(devis);
    }

    // Récupérer tous les devis
    public List<DevisVie> getAllDevis() {
        return devisVieRepo.findAll();
    }

    // Récupérer un devis par ID
    public Optional<DevisVie> getDevisById(String id) {
        return devisVieRepo.findById(id);
    }

    // Mettre à jour un devis
    public DevisVie updateDevis(String id, DevisVie newDevis) {
        return devisVieRepo.findById(id).map(existingDevis -> {
            existingDevis.setTypeContrat(newDevis.getTypeContrat());
            existingDevis.setPrimeMensuelle(newDevis.getPrimeMensuelle());
            existingDevis.setBeneficiaire(newDevis.getBeneficiaire());
            existingDevis.setRendementEspere(newDevis.getRendementEspere());
            existingDevis.setDureeMinimale(newDevis.getDureeMinimale());
            return devisVieRepo.save(existingDevis);
        }).orElse(null);
    }

    // Supprimer un devis
    public void deleteDevis(String id) {
        devisVieRepo.deleteById(id);
    }
}