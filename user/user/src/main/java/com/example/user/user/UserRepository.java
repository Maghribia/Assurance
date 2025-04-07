package com.example.user.user;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByResetToken(String token);
    User findByUsername(String username);
    boolean existsByUsername(String username); // Ajoutez cette méthode
    boolean existsByEmail(String email); // Ajoutez cette méthode

}