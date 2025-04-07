package com.maghrebia.Devis;

import jakarta.persistence.*;
import lombok.*;
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class DevisVie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private int  id;
    @Enumerated(EnumType.STRING)
    private typeContrat typeContrat;  // Ex: épargne, retraite, mixte
    private double primeMensuelle;  // Cotisation mensuelle
    private String beneficiaire;  // Nom du bénéficiaire
    private double rendementEspere;  // Taux de rendement annuel attendu
    private int dureeMinimale;  // Durée minimale avant retrait
}