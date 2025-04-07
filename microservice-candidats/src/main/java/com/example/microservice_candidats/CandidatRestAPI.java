package com.example.microservice_candidats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidats")
public class CandidatRestAPI {

    @Autowired
    private CandidatService candidatService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Candidat> createCandidat(@RequestBody Candidat candidat) {
        return new ResponseEntity<>(candidatService.addCandidat(candidat), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Candidat>> getAllCandidats() {
        return ResponseEntity.ok(candidatService.getAllCandidats());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidat> getCandidat(@PathVariable int id) {
        return candidatService.getCandidatById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidat> updateCandidat(@PathVariable int id, @RequestBody Candidat candidat) {
        Candidat updated = candidatService.updateCandidat(id, candidat);
        return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCandidat(@PathVariable int id) {
        return ResponseEntity.ok(candidatService.deleteCandidat(id));
    }
}
