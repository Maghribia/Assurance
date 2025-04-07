package com.example.user.user;


import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import org.springframework.stereotype.Service;

@Service
public class GoogleAuthService {

    private final GoogleAuthenticator googleAuthenticator;

    public GoogleAuthService() {
        this.googleAuthenticator = new GoogleAuthenticator();
    }

    public String generateSecretKey() {
        GoogleAuthenticatorKey key = googleAuthenticator.createCredentials();
        return key.getKey();
    }

    public boolean verifyCode(String secret, int code) {
        return googleAuthenticator.authorize(secret, code);
    }
}
