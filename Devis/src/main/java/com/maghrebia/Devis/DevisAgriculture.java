package com.maghrebia.Devis;

import jakarta.persistence.*;
import lombok.*;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class DevisAgriculture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private int id;

    private int surfaceExploitation;  // En hectares
    @Enumerated(EnumType.STRING)
    private typeCulture typeCulture;  // Ex: céréales, fruits, légumes
    private int nombreAnimaux;  // Si élevage
    private double valeurMateriel;  // Valeur des équipements agricoles
    private String risquesCouverts;  // Ex: incendie, sécheresse
}
