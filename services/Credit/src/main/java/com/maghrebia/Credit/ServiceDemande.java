package com.maghrebia.Credit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceDemande {
    @Autowired
    private DemandeRepository demandeRepository;

    @Autowired
    private UserClient userClient;

    // Ajouter une demande
    public Demande addDemande(Demande demande , String userEmail) {
        UserDTO userDTO = userClient.getUserByEmail(userEmail);

        // Vérifier si l'utilisateur existe
        if (userDTO == null) {
            throw new RuntimeException("User not found with email: " + userEmail);
        }
        demande.setUserEmail(userEmail);

        return demandeRepository.save(demande);

    }

    // Récupérer toutes les demandes
    public List<Demande> getAllDemandes() {
        return demandeRepository.findAll();
    }

    // Récupérer une demande par ID
    public Optional<Demande> getDemandeById(long id) {
        return demandeRepository.findById(id);
    }

    // Mettre à jour une demande
    public Demande updateDemande(long id, Demande updatedDemande) {
        Optional<Demande> existingDemande = demandeRepository.findById(id);

        if (existingDemande.isPresent()) {
            Demande demande = existingDemande.get();
            demande.setUserEmail(updatedDemande.getUserEmail());
            demande.setMontant(updatedDemande.getMontant());
            demande.setTauxInteret(updatedDemande.getTauxInteret());
            demande.setDuree(updatedDemande.getDuree());
            demande.setTypeCredit(updatedDemande.getTypeCredit());
            demande.setEtatCredit(updatedDemande.getEtatCredit());
            demande.setHistoriquePaiements(updatedDemande.getHistoriquePaiements());
            demande.setScoreRisque(updatedDemande.getScoreRisque());
            return demandeRepository.save(demande);
        } else {
            throw new RuntimeException("Demande non trouvée avec l'ID : " + id);
        }
    }

    // Supprimer une demande
    public void deleteDemande(long id) {
        demandeRepository.deleteById(id);
    }

    public long getTotalDemandeCount() {
        return demandeRepository.count();
    }
}
