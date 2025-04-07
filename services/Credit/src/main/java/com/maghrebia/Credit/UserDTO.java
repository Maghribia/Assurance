package com.maghrebia.Credit;

import lombok.Data;

@Data
public class UserDTO {
    private String id;
    private Integer cin;
    private String nom;
    private String prenom;
    private String adresse;
    private String role;
    private String email;
}