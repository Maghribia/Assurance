package com.example.user.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profil")
@CrossOrigin(origins = "http://localhost:4200")
public class ProfilClientController {

    @Autowired
    private ProfilClientService profilClientService;

    @Autowired
    private ProfilClientRepository profilClientRepository;

    @PostMapping
    public ResponseEntity<ProfilClient> enregistrerProfil(@RequestBody ProfilClient client) {
        ProfilClient savedClient = profilClientRepository.save(client);
        return ResponseEntity.ok(savedClient);
    }

    @GetMapping
    public ResponseEntity<List<ProfilClient>> getAllProfils() {
        return ResponseEntity.ok(profilClientRepository.findAll());
    }
/*
    @GetMapping("/{id}/credit")
    public ResponseEntity<String> evaluerCredit(@PathVariable String id) {
        ProfilClient client = profilClientRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(profilClientService.evaluerCapaciteEmprunt(client));
    }

    @GetMapping("/{id}/assurance")
    public ResponseEntity<String> evaluerAssurance(@PathVariable String id) {
        ProfilClient client = profilClientRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(profilClientService.evaluerRisqueAssurance(client));
    }*/
}

