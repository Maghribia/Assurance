package com.maghrebia.User;

import lombok.Data;

@Data
public class SignUpRequest {
    private Integer cin;
    private String nom;
    private String prenom;
    private String adresse;
    private ERole role;
    private String email;
    private String password;
}
