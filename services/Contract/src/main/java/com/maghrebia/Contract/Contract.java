package com.maghrebia.Contract;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrément pour H2
    private Long id;
    private String ClientId;
    private int ProduitAssuranceId;
    private String statut;
    private double montantMensuel;
    private String typeAssurance;
    private LocalDate dateExpiration;
}
