package com.maghrebia.Devis;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/devis/sante")
@RequiredArgsConstructor
public class DevisSanteController {
    private final DevisSanteService devisSanteService;

    @PostMapping
    public ResponseEntity<DevisSante> createDevisSante(@RequestBody DevisSante devisSante) {
        DevisSante createdDevisSante = devisSanteService.createDevisSante(devisSante);
        return new ResponseEntity<>(createdDevisSante, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DevisSante>> getAllDevisSante() {
        List<DevisSante> devisSanteList = devisSanteService.getAllDevisSante();
        return ResponseEntity.ok(devisSanteList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DevisSante> getDevisSanteById(@PathVariable String id) {
        Optional<DevisSante> devisSante = devisSanteService.getDevisSanteById(id);
        return devisSante.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DevisSante> updateDevisSante(@PathVariable String id, @RequestBody DevisSante updateDevisSante) {
        DevisSante updatedDevisSante = devisSanteService.updateDevisSante(id, updateDevisSante);
        if (updatedDevisSante != null) {
            return ResponseEntity.ok(updatedDevisSante);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevisSante(@PathVariable String id) {
        devisSanteService.deleteDevisSante(id);
        return ResponseEntity.noContent().build();
    }
}
