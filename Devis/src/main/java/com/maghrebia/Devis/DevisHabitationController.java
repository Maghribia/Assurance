package com.maghrebia.Devis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200") // Angular port
@RestController
@RequestMapping("/api/devis/habitation")
public class DevisHabitationController {
    @Autowired
    private DevisHabitationService devisHabitationService;

    // Ajouter un devis
    @PostMapping
    public ResponseEntity<DevisHabitation> addDevis(@RequestBody DevisHabitation devis) {
        return ResponseEntity.ok(devisHabitationService.addDevis(devis));
    }

    // Obtenir tous les devis
    @GetMapping
    public ResponseEntity<List<DevisHabitation>> getAllDevis() {
        return ResponseEntity.ok(devisHabitationService.getAllDevis());
    }

    // Obtenir un devis par ID
    @GetMapping("/{id}")
    public ResponseEntity<DevisHabitation> getDevisById(@PathVariable String id) {
        Optional<DevisHabitation> devis = devisHabitationService.getDevisById(id);
        return devis.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Mettre à jour un devis
    @PutMapping("/{id}")
    public ResponseEntity<DevisHabitation> updateDevis(@PathVariable String id, @RequestBody DevisHabitation newDevis) {
        DevisHabitation updatedDevis = devisHabitationService.updateDevis(id, newDevis);
        return updatedDevis != null ? ResponseEntity.ok(updatedDevis) : ResponseEntity.notFound().build();
    }

    // Supprimer un devis
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevis(@PathVariable String id) {
        devisHabitationService.deleteDevis(id);
        return ResponseEntity.noContent().build();
    }
}
