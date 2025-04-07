package com.maghrebia.Devis;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200") // Angular port
@RestController
@RequestMapping("/api/devis")
public class DevisController {

    @Autowired
    private DevisService devisService;

    // Create a new Devis
    @PostMapping
    public ResponseEntity<Devis> createDevis(@RequestBody Devis devis) {
        devis.setEtatDevis(EtatDevis.EN_ATTENTE); // Set default state
        Devis createdDevis = devisService.createDevis(devis);
        return new ResponseEntity<>(createdDevis, HttpStatus.CREATED);
    }

    // Get all Devis
    @GetMapping
    public ResponseEntity<List<Devis>> getAllDevis() {
        List<Devis> devisList = devisService.getAllDevis();
        return ResponseEntity.ok(devisList);
    }

    // Get Devis by ID
    @GetMapping("/{id}")
    public ResponseEntity<Devis> getDevisById(@PathVariable String id) { // Updated parameter name
        Optional<Devis> devis = devisService.getDevisById(id);
        return devis.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update Devis

    @PutMapping("/{id}")
    public Devis updateDevis(@PathVariable String id, @RequestBody Devis updateDevis) {
        return devisService.updateDevis(id, updateDevis);
    }

    // Delete Devis
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevis(@PathVariable String id) { // Updated parameter name
        devisService.deleteDevis(id);
        return ResponseEntity.noContent().build();
    }
    // Generate PDF
    @GetMapping("/pdf")
    public void generatePdf(HttpServletResponse response) throws IOException {
        devisService.generatePdf(response);
    }

}