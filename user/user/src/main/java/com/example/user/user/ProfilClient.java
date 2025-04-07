package com.example.user.user;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class ProfilClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    // Informations personnelles
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private LocalDate dateNaissance;
    private String statutEmploi;
    private double revenuMensuel;
    private double chargesMensuelles;
    private int scoreCredit;

    // Assurance
    private String typeAssurance;
    private String niveauCouverture;
    private boolean proprietaire;
    private double valeurBienAssure;

    // Historique des accidents & sinistres
    private int nombreAccidents;
    private int nombreSinistres;
    private double totalIndemnisations;
    private String responsabiliteSinistres;
    private double coefficientBonusMalus;

    // Getters & Setters
}

