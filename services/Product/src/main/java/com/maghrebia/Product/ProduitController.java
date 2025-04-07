package com.maghrebia.Product;
import com.maghrebia.Product.ProduitService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@RestController
@RequestMapping("/produits")
public class ProduitController {


    @Autowired
    private ProduitService produitService;

    @GetMapping
    public List<Produit> getAllProduits() {
        return produitService.getAllProduits();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable int id) {
        Optional<Produit> produit = produitService.getProduitById(id);
        return produit.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Produit addProduit(@RequestBody Produit produit) {
        return produitService.addProduit(produit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable int id, @RequestBody Produit produitDetails) {
        try {
            return ResponseEntity.ok(produitService.updateProduit(id, produitDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable int id) {
        produitService.deleteProduit(id);
        return ResponseEntity.noContent().build();
    }
}


