package com.maghrebia.User;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthentificationServiceImpl implements AuthentificationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;  // ✅ Injection correcte
    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    public User signup(SignUpRequest signUpRequest) {
        User user = new User();
        user.setCin(signUpRequest.getCin());
        user.setNom(signUpRequest.getNom());
        user.setPrenom(signUpRequest.getPrenom());
        user.setAdresse(signUpRequest.getAdresse());
        user.setEmail(signUpRequest.getEmail());
        Set<ERole> roles = new HashSet<>();

        if (signUpRequest.getRole() != null) {
            roles.add(signUpRequest.getRole()); // Si un seul rôle est envoyé
        } else {
            roles.add(ERole.CLIENT); // Par défaut, tout nouvel utilisateur est un CLIENT
        }

        user.setRoles(roles);

        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        return userRepository.save(user);
    }


    public jwtAuthentifactionResponse signin(SignInRequest signInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword())) ;
        var user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        var refreshTocken = jwtService.generateRefreshToken(new HashMap<>(),user);

        jwtAuthentifactionResponse jwtAuthentifactionResponse = new jwtAuthentifactionResponse();
        jwtAuthentifactionResponse.setToken(jwt);
        jwtAuthentifactionResponse.setRefreshToken(refreshTocken);
        jwtAuthentifactionResponse.setUser(user);  // Ajouter l'utilisateur dans la réponse

        return jwtAuthentifactionResponse;
    }


    public jwtAuthentifactionResponse  refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {

            var jwt = jwtService.generateToken(user);
            jwtAuthentifactionResponse jwtAuthentifactionResponse = new jwtAuthentifactionResponse();
            jwtAuthentifactionResponse.setToken(jwt);
            jwtAuthentifactionResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthentifactionResponse;



        }
        return null;


        }

}
