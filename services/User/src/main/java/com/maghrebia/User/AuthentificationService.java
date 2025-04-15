package com.maghrebia.User;

public interface AuthentificationService {
    public User signup(SignUpRequest signUpRequest);
    public jwtAuthentifactionResponse signin(SignInRequest signInRequest);
    jwtAuthentifactionResponse  refreshToken(RefreshTokenRequest refreshTokenRequest) ;
}
