package com.maghrebia.Devis;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;


@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class DevisEcolia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    // Informations sur le parent/tuteur
    private String parentTuteurNom; // Nom du parent/tuteur
    private String parentTuteurPieceIdentite; // Type de pièce d'identité
    private String parentTuteurNumPieceIdentite; // Numéro de pièce d'identité
    private String parentTuteurTelephone; // Numéro de téléphone
    private String parentTuteurEmail; // Email
    private int nombreEnfants; // Nombre d'enfants à assurer
    private Date dateEffet; // Date d'effet du contrat


}
