package com.maghrebia.User;

import org.springframework.security.core.userdetails.UserDetails;


public interface JWTService {
     String extractUserName(String token);
    public String generateToken(UserDetails userDetails);
    public boolean isTokenValid(String token, UserDetails userDetails);
}
