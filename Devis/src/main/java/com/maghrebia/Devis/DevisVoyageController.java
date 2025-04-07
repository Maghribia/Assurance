package com.maghrebia.Devis;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/devis/voyage")
public class DevisVoyageController {

    @Autowired
    private DevisVoyageService devisVoyageService;

    @GetMapping
    public ResponseEntity<List<DevisVoyage>> getAllDevisVoyage() {
        List<DevisVoyage> devisVoyages = devisVoyageService.getAllDevisVoyage();
        return ResponseEntity.ok(devisVoyages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DevisVoyage> getDevisVoyageById(@PathVariable String id) {
        Optional<DevisVoyage> devisVoyage = devisVoyageService.getDevisVoyageById(id);
        return devisVoyage.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DevisVoyage> createDevisVoyage(@RequestBody DevisVoyage devisVoyage) {

        DevisVoyage createdDevisVoyage = devisVoyageService.createDevisVoyage(devisVoyage);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDevisVoyage);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DevisVoyage> updateDevisVoyage(@PathVariable String id, @RequestBody DevisVoyage updatedDevisVoyage) {
        DevisVoyage devisVoyage = devisVoyageService.updateDevisVoyage(id, updatedDevisVoyage);
        return ResponseEntity.ok(devisVoyage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevisVoyage(@PathVariable String id) {
        devisVoyageService.deleteDevisVoyage(id);
        return ResponseEntity.noContent().build();
    }
}