package com.example.user.profiling.recommendation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InsuranceProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String category; // "Auto", "Santé", "Crédit"
    private String description;
    private double basePrice;
    private String eligibilityCriteria; // Ex: ["hasCar", "age>25"]


    // Constructeurs, Getters, Setters
}

