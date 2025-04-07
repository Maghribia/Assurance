package com.example.user.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.annotation.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private Set<ERole> roles;
    // Ajout des attributs pour le scoring
    private double salaire;
    private boolean hasCredit;
    private boolean hasInsurance;

    public User(String username, String email, String password, Set<ERole> roles, double salaire, boolean hasCredit, boolean hasInsurance, boolean mfaEnabled, String otpSecret, String mfaSecret, String resetToken) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.salaire = salaire;
        this.hasCredit = hasCredit;
        this.hasInsurance = hasInsurance;
        this.mfaEnabled = mfaEnabled;
        this.otpSecret = otpSecret;
        this.mfaSecret = mfaSecret;
        this.resetToken = resetToken;
    }

    public User() {

    }

    public Set<ERole> getRoles() {
        return roles;
    }

    public void setRoles(Set<ERole> roles) {
        this.roles = roles;
    }

    // Ajout de MFA
    private boolean mfaEnabled;
    private String otpSecret;
    private String mfaSecret;  // Store the MFA secret here

    private String resetToken;
    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMfaSecret() {
        return mfaSecret;
    }

    public void setMfaSecret(String mfaSecret) {
        this.mfaSecret = mfaSecret;
    }
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}