package com.maghrebia.Credit;

import jakarta.persistence.*;
import lombok.*;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation en MySQL
    private Long id;

    private float montant;
    private float tauxInteret;
    private int duree;

    @Enumerated(EnumType.STRING) // Stocke l'Enum sous forme de texte
    private TypeCredit typeCredit;

    @Enumerated(EnumType.STRING)
    private EtatCredit etatCredit;

    private String historiquePaiements;

    private String imageUrl;  // URL de l'image uploadée
    private double scoreRisque;  // Ajoutez le score de risque
    private String userEmail;  // Ajoutez l'email de l'utilisateur
    private int annee;

}
