package com.maghrebia.Devis;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/devis/vie")
@RequiredArgsConstructor
public class DevisVieController {
    @Autowired
    private DevisVieService devisVieService;

    // Ajouter un devis
    @PostMapping
    public ResponseEntity<DevisVie> addDevis(@RequestBody DevisVie devis) {
        return ResponseEntity.ok(devisVieService.addDevis(devis));
    }

    // Obtenir tous les devis
    @GetMapping
    public ResponseEntity<List<DevisVie>> getAllDevis() {
        return ResponseEntity.ok(devisVieService.getAllDevis());
    }

    // Obtenir un devis par ID
    @GetMapping("/{id}")
    public ResponseEntity<DevisVie> getDevisById(@PathVariable String id) {
        Optional<DevisVie> devis = devisVieService.getDevisById(id);
        return devis.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Mettre à jour un devis
    @PutMapping("/{id}")
    public ResponseEntity<DevisVie> updateDevis(@PathVariable String id, @RequestBody DevisVie newDevis) {
        DevisVie updatedDevis = devisVieService.updateDevis(id, newDevis);
        return updatedDevis != null ? ResponseEntity.ok(updatedDevis) : ResponseEntity.notFound().build();
    }

    // Supprimer un devis
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevis(@PathVariable String id) {
        devisVieService.deleteDevis(id);
        return ResponseEntity.noContent().build();
    }
}
