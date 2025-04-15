package com.maghrebia.Devis;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class DevisAuto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private int id;
    // Informations sur l'assurance auto (si applicable)
    private String immatriculation; // Immatriculation du véhicule
    private String marque; // Marque du véhicule
    private String modele; // Modèle du véhicule
    private int annee; // Année du véhicule
    private int kilometrage; // Kilométrage du véhicule
    private String typeContrat; // Type de contrat d'assurance auto

    public String getMontantEstime() {
        return null;
    }

    public Object getDateDemande() {
        return null;
    }
}
