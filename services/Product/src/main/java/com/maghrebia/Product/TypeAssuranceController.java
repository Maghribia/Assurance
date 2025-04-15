package com.maghrebia.Product;

import com.google.zxing.WriterException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/typeassurance")
public class TypeAssuranceController {
    @Autowired
    private TypeAssuranceService service;
    private QRCodeService qrCodeService;


    // Ajouter un type d'assurance
    @PostMapping
    public ResponseEntity<TypeAssurance> addTypeAssurance(@RequestBody TypeAssurance typeAssurance) {
        return ResponseEntity.ok(service.addTypeAssurance(typeAssurance));
    }

    // Obtenir tous les types d'assurances
    @GetMapping
    public ResponseEntity<List<TypeAssurance>> getAllTypeAssurances() {
        return ResponseEntity.ok(service.getAllTypeAssurances());
    }

    // Obtenir un type d'assurance par ID
    @GetMapping("/{id}")
    public ResponseEntity<TypeAssurance> getTypeAssuranceById(@PathVariable Long id) {
        Optional<TypeAssurance> typeAssurance = service.getTypeAssuranceById(id);
        return typeAssurance.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Mettre à jour un type d'assurance
    @PutMapping("/{id}")
    public ResponseEntity<TypeAssurance> updateTypeAssurance(@PathVariable Long id, @RequestBody TypeAssurance typeAssuranceDetails) {
        try {
            return ResponseEntity.ok(service.updateTypeAssurance(id, typeAssuranceDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer un type d'assurance
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypeAssurance(@PathVariable Long id) {
        service.deleteTypeAssurance(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{type}/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getQRCodeByType(@PathVariable String type) throws WriterException, IOException {
        // 🔹 Récupérer un produit correspondant au type
        List<TypeAssurance> typeAssurances = service.getAllTypeAssurances();

        TypeAssurance matchingType = typeAssurances.stream()
                .filter(t -> t.getType().equalsIgnoreCase(type))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Aucun produit trouvé pour le type : " + type));

        // 🔹 Générer l'URL dynamique pour ce produit
        String typeUrl = matchingType.getUrl();
        if (typeUrl == null || typeUrl.isEmpty()) {
            throw new RuntimeException("URL non définie pour le type : " + type);
        }

        // 🔹 Générer et retourner l'image du QR Code
        return qrCodeService.generateQRCodeImage(typeUrl);
    }
}
