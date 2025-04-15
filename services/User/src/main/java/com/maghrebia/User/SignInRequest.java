package com.maghrebia.User;


import lombok.Data;

@Data
public class SignInRequest {
    private String email;
    private String password;
}
