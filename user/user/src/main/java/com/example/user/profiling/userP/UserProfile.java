package com.example.user.profiling.userP;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


import java.time.LocalDate;


@Entity
@Data
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String profession;
    private double monthlyIncome;
    private double monthlyExpenses;
    private boolean hasCreditHistory;
    // Ajoutez d'autres champs selon vos besoins
}