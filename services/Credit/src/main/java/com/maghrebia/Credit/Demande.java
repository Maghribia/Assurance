package com.maghrebia.Credit;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "demande")
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Demande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Utilisation d'un ID auto-incrémenté pour MySQL
    private Long id;

    @Column(nullable = false)
    private String userEmail;  // Email de l'utilisateur

    @Column(nullable = false)
    private int montant;

    @Column(nullable = false)
    private double tauxInteret;

    @Column(nullable = false)
    private int duree;

    @Enumerated(EnumType.STRING) // Stocker l'enum sous forme de chaîne de caractères
    @Column(nullable = false)
    private TypeCredit typeCredit;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EtatCredit etatCredit;

    private String historiquePaiements;

    private double scoreRisque;

    private String gainPrediction;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE) // Indique que cette colonne stocke une date
    private Date dateDemande = new Date(); // Initialisation automatique avec la date du jour
}
