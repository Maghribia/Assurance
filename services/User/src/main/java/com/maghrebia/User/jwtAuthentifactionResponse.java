package com.maghrebia.User;

import lombok.Data;

@Data
public class jwtAuthentifactionResponse {
    private String token;
    private String refreshToken;
    private User user;  // Ajouter l'utilisateur à la réponse



}
