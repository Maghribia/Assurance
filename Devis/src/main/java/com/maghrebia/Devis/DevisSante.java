package com.maghrebia.Devis;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class DevisSante {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private int id;


    private String contactNom;
    private String contactPrenom;
    private String contactEmail;
    private String contactSociete;
    private String contactFonction;
    private String contactTelephone;
    private Date contactDateNaissance;


}
