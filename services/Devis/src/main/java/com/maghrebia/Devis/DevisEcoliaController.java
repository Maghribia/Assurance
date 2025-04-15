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
@RequestMapping("/api/devis/ecolia")
public class DevisEcoliaController {

    @Autowired
    private DevisEcoliaService devisEcoliaService;

    // Create a new DevisEcolia
    @PostMapping
    public ResponseEntity<DevisEcolia> createDevisEcolia(@RequestBody DevisEcolia devisEcolia) {
        DevisEcolia createdDevisEcolia = devisEcoliaService.createDevisEcolia(devisEcolia);
        return new ResponseEntity<>(createdDevisEcolia, HttpStatus.CREATED);
    }

    // Get all DevisEcolia
    @GetMapping
    public ResponseEntity<List<DevisEcolia>> getAllDevisEcolia() {
        List<DevisEcolia> devisEcoliaList = devisEcoliaService.getAllDevisEcolia();
        return ResponseEntity.ok(devisEcoliaList);
    }

    // Get DevisEcolia by ID
    @GetMapping("/{id}")
    public ResponseEntity<DevisEcolia> getDevisEcoliaById(@PathVariable String id) {
        Optional<DevisEcolia> devisEcolia = devisEcoliaService.getDevisEcoliaById(id);
        return devisEcolia.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update DevisEcolia
    @PutMapping("/{id}")
    public DevisEcolia updateDevisEcolia(@PathVariable String id, @RequestBody DevisEcolia updateDevisEcolia) {
        return devisEcoliaService.updateDevisEcolia(id, updateDevisEcolia);
    }

    // Delete DevisEcolia
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevisEcolia(@PathVariable String id) {
        devisEcoliaService.deleteDevisEcolia(id);
        return ResponseEntity.noContent().build();
    }

    // Generate PDF
    @GetMapping("/pdf")
    public void generatePdf(HttpServletResponse response) throws IOException {
        devisEcoliaService.generatePdf(response);
    }

}
