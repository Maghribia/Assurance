package com.maghrebia.Credit;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USERSERVICE", url = "http://localhost:8023") // Remplacez l'URL par celle de votre microservice user
public interface UserClient {

    @GetMapping("/api/v1/auth/email/{email}")
    UserDTO getUserByEmail(@PathVariable("email") String email);
}