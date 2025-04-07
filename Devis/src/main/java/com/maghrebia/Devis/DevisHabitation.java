package com.maghrebia.Devis;

import jakarta.persistence.*;
import lombok.*;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class DevisHabitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private int id;
    private int surface;  // Superficie en m²
    @Enumerated(EnumType.STRING)
    private TypeLogement typeLogement;  // Ex: appartement, maison, villa…
    private double valeurBien;  // Valeur estimée du logement
    private String niveauRisque;  // Ex: faible, moyen, élevé
    private boolean alarmeSecurite;  // Présence d’un système de sécurité
}