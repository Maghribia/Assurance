package com.maghrebia.Devis;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Devis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDevis;
}
