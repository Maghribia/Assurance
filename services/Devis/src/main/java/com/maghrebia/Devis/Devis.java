package com.maghrebia.Devis;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Devis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double montantEstime;
    private Date dateDemande;
    private String typeAssurance;

    @Enumerated(EnumType.STRING) // Store enum as a string in the database
    private EtatDevis etatDevis; // Use the enum type here

    // Autres attributs selon besoin
    private String message; // Message additionnel
}