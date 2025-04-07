package com.maghrebia.Devis;

import jakarta.mail.MessagingException;
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
@RequestMapping("/api/devis/auto")
public class DevisAutoController {

    @Autowired
    private DevisAutoService devisAutoService;

    // Create a new DevisAuto
    @PostMapping("/create")
    public ResponseEntity<DevisAuto> createDevisAuto(@RequestBody DevisAuto devisAuto) {
        DevisAuto createdDevisAuto = devisAutoService.createDevisAuto(devisAuto);
        return new ResponseEntity<>(createdDevisAuto, HttpStatus.CREATED);
    }


    // Get all DevisAuto
    @GetMapping
    public ResponseEntity<List<DevisAuto>> getAllDevisAuto() {
        List<DevisAuto> devisAutoList = devisAutoService.getAllDevisAuto();
        return ResponseEntity.ok(devisAutoList);
    }

    // Get DevisAuto by ID
    @GetMapping("/{id}")
    public ResponseEntity<DevisAuto> getDevisAutoById(@PathVariable String id) {
        Optional<DevisAuto> devisAuto = devisAutoService.getDevisAutoById(id);
        return devisAuto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update DevisAuto
    @PutMapping("/{id}")
    public DevisAuto updateDevisAuto(@PathVariable String id, @RequestBody DevisAuto updateDevisAuto) {
        return devisAutoService.updateDevisAuto(id, updateDevisAuto);
    }

    // Delete DevisAuto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevisAuto(@PathVariable String id) {
        devisAutoService.deleteDevisAuto(id);
        return ResponseEntity.noContent().build();
    }

    // Generate PDF
    @GetMapping("/pdf")
    public void generatePdf(HttpServletResponse response) throws IOException {
        devisAutoService.generatePdf(response);
    }
}
