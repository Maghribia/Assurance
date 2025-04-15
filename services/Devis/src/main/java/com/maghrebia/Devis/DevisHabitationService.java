package com.maghrebia.Devis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DevisHabitationService {

    private final DevisHabitationRepository devisHabitationRepo;

    // Ajouter un devis
    public DevisHabitation addDevis(DevisHabitation devis) {
        return devisHabitationRepo.save(devis);
    }

    // Récupérer tous les devis
    public List<DevisHabitation> getAllDevis() {
        return devisHabitationRepo.findAll();
    }

    // Récupérer un devis par ID
    public Optional<DevisHabitation> getDevisById(String id) {
        return devisHabitationRepo.findById(id);
    }

    // Mettre à jour un devis
    public DevisHabitation updateDevis(String id, DevisHabitation newDevis) {
        return devisHabitationRepo.findById(id).map(existingDevis -> {
            existingDevis.setSurface(newDevis.getSurface());
            existingDevis.setTypeLogement(newDevis.getTypeLogement());
            existingDevis.setValeurBien(newDevis.getValeurBien());
            existingDevis.setNiveauRisque(newDevis.getNiveauRisque());
            existingDevis.setAlarmeSecurite(newDevis.isAlarmeSecurite());
            return devisHabitationRepo.save(existingDevis);
        }).orElse(null);
    }

    // Supprimer un devis
    public void deleteDevis(String id) {
        devisHabitationRepo.deleteById(id);
    }
}