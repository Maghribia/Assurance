package com.maghrebia.User;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Document(collection = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    private String id;
    private Integer cin;
    private String nom;
    private String prenom;
    private String adresse;
    private ERole role;
    private String email;
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));  // ✅ Utilisation correcte du rôle
    }

    @Override
    public String getUsername() {
        return email;  // ✅ Retourner l'email comme username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
