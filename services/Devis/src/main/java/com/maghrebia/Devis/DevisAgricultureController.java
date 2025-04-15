package com.maghrebia.Devis;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200") // Angular port
@RestController
@RequestMapping("/api/devis/agriculture")
public class DevisAgricultureController {

    @Autowired
    private DevisAgricultureService devisAgricultureService;

    // Ajouter un devis
    @PostMapping
    public ResponseEntity<?> createDevis(@RequestBody DevisAgriculture devis) {
        try {
            DevisAgriculture createdDevis = devisAgricultureService.addDevis(devis);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDevis);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    Map.of("error !!! plz try again", "Creation failed !!",
                            "details ...", e.getMessage())
            );
        }
    }

    // Obtenir tous les devis
    @GetMapping
    public ResponseEntity<List<DevisAgriculture>> getAllDevis() {
        return ResponseEntity.ok(devisAgricultureService.getAllDevis());
    }

    // Obtenir un devis par ID
    @GetMapping("/{id}")
    public ResponseEntity<DevisAgriculture> getDevisById(@PathVariable String id) {
        Optional<DevisAgriculture> devis = devisAgricultureService.getDevisById(id);
        return devis.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Mettre à jour un devis
    @PutMapping("/{id}")
    public ResponseEntity<DevisAgriculture> updateDevis(@PathVariable String id, @RequestBody DevisAgriculture newDevis) {
        DevisAgriculture updatedDevis = devisAgricultureService.updateDevis(id, newDevis);
        return updatedDevis != null ? ResponseEntity.ok(updatedDevis) : ResponseEntity.notFound().build();
    }

    // Supprimer un devis
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevis(@PathVariable String id) {
        devisAgricultureService.deleteDevis(id);
        return ResponseEntity.noContent().build();
    }
}
