package com.example.microservice_candidats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceCandidatsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceCandidatsApplication.class, args);
    }

    @Autowired
    private CandidatRepository repository;

    @Bean
    CommandLineRunner init() {
        return args -> {
            repository.save(new Candidat("Mariem", "Ch", "ma@esprit.tn"));
            repository.save(new Candidat("Sarra", "Ab", "sa@esprit.tn"));
            repository.save(new Candidat("Mohamed", "Ba", "mo@esprit.tn"));
            repository.save(new Candidat("Maroua", "Dh", "maroua@esprit.tn"));

            repository.findAll().forEach(System.out::println);
        };
    }
}
