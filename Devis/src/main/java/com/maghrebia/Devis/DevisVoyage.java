package com.maghrebia.Devis;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class DevisVoyage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int dureeContrat; // Durée du contrat (en jours ou mois)
    private Date dateDepart; // Date de départ
    private Date dateRetour; // Date de retour
    @Enumerated(EnumType.STRING)
    private Destination destination;
    @Enumerated(EnumType.STRING)
    private TrancheAge trancheAge;
    @Enumerated(EnumType.STRING)
    private ZoneGeographique zoneGeographique;
}
