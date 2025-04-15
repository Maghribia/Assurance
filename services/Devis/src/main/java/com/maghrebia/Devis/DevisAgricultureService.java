package com.maghrebia.Devis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DevisAgricultureService {
    @Autowired
    private DevisAgricultureRepo devisAgricultureRepo;

    // Ajouter un devis
    public DevisAgriculture addDevis(DevisAgriculture devis) {
        return devisAgricultureRepo.save(devis);
    }

    // Récupérer tous les devis
    public List<DevisAgriculture> getAllDevis() {
        return devisAgricultureRepo.findAll();
    }

    // Récupérer un devis par ID
    public Optional<DevisAgriculture> getDevisById(String id) {
        return devisAgricultureRepo.findById(id);
    }

    // Mettre à jour un devis
    public DevisAgriculture updateDevis(String id, DevisAgriculture newDevis) {
        return devisAgricultureRepo.findById(id).map(existingDevis -> {
            existingDevis.setSurfaceExploitation(newDevis.getSurfaceExploitation());
            existingDevis.setTypeCulture(newDevis.getTypeCulture());
            existingDevis.setNombreAnimaux(newDevis.getNombreAnimaux());
            existingDevis.setValeurMateriel(newDevis.getValeurMateriel());
            existingDevis.setRisquesCouverts(newDevis.getRisquesCouverts());
            return devisAgricultureRepo.save(existingDevis);
        }).orElse(null);
    }

    // Supprimer un devis
    public void deleteDevis(String id) {
        devisAgricultureRepo.deleteById(id);
    }
}
