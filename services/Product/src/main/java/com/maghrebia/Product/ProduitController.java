package com.maghrebia.Product;
import com.google.zxing.WriterException;
import com.maghrebia.Product.ProduitService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;


@RestController
@RequestMapping("/produits")
@CrossOrigin(origins = "http://localhost:4200") // Angular port
@RequiredArgsConstructor
public class ProduitController {



    @Autowired
    private ProduitService produitService;
    private QRCodeService qrCodeService;
    private static final String ML_API_URL = "http://localhost:5000/predict"; // URL du modèle ML



    @GetMapping
    public List<Produit> getAllProduits() {
        return produitService.getAllProduits();
    }



    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable Long id) {
        Optional<Produit> produit = produitService.getProduitById(id);
        if (produit.isPresent()) {
            // Convertir les bytes de l'image en base64
            String imageBase64 = produit.get().getImageData() != null ?
                    Base64.getEncoder().encodeToString(produit.get().getImageData()) : null;
            produit.get().setImageBase64(imageBase64); // Assurer que tu as un champ pour stocker l'image base64 dans ton modèle
            return ResponseEntity.ok(produit.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public Produit addProduit(@RequestBody Produit produit) throws WriterException, IOException {
        // Obtenir le tarif ajusté depuis le modèle ML
        Double tarifPredicted = appelerModeleML(produit);
        produit.setTarifs(tarifPredicted); // Mettre à jour le tarif avec la prédiction

        // Sauvegarde du produit en base
        return produitService.save(produit);
    }
    private Double appelerModeleML(Produit produit) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("nombre_sinistres", produit.getNombreSinistres());
        requestBody.put("age_client", produit.getAgeClient());
        requestBody.put("duree_contrat", produit.getDureeContrat());
        requestBody.put("prime_base", produit.getPrimeBase());

        try {
            ResponseEntity<Double> response = restTemplate.postForEntity(ML_API_URL, requestBody, Double.class);
            return response.getBody();
        } catch (Exception e) {
            System.err.println("Erreur lors de l'appel au modèle ML: " + e.getMessage());
            return produit.getTarifs(); // Si erreur, garder le tarif initial
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Produit> updateProduit(
            @PathVariable Long id,
            @RequestParam("type") String type,
            @RequestParam("Description") String Description,
            @RequestParam("Tarifs") Double Tarifs,
            @RequestParam(value = "Image", required = false) MultipartFile Image) throws IOException {

        Optional<Produit> produitOptional = produitService.getProduitById(id);
        if (produitOptional.isPresent()) {
            Produit produit = produitOptional.get();
            produit.setType((type));
            produit.setDescription(Description);
            produit.setTarifs(Tarifs);

            if (Image != null && !Image.isEmpty()) {
                String base64Image = Base64.getEncoder().encodeToString(Image.getBytes());
                produit.setImageBase64("data:image/jpeg;base64,"+base64Image);
            }

            Produit updatedProduit = produitService.save(produit);
            return ResponseEntity.ok(updatedProduit);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Value("${welcome.message}")
    private String welcomeMessage;
    @GetMapping("/welcome")
    public String welcome() {
        return welcomeMessage;
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        produitService.deleteProduit(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/recherche")
    public ResponseEntity<?> rechercherParType(
            @RequestParam("type") String type) {

        try {
            List<Produit> produits = produitService.rechercherParType(type);
            return ResponseEntity.ok(produits);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Type invalide: " + e.getMessage());
        }
    }
    @GetMapping("/search")
    public List<Produit> searchProduits(@RequestParam String keyword) {
        return produitService.searchProduits(keyword);
    }

    // Génération du QR Code par type
    @GetMapping(value = "/{type}/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getQRCodeByType(@PathVariable String type) throws WriterException, IOException {
        // 🔹 Récupérer un produit correspondant au type
        Produit produit = produitService.getFirstProduitByType(type);

        if (produit == null) {
            throw new RuntimeException("Aucun produit trouvé pour le type : " + type);
        }

        // 🔹 Générer l'URL dynamique pour ce produit
        //String typeUrl = qrCodeService.generateTypeUrl(typeAssurance, produit.getId());

        // 🔹 Générer et retourner l'image du QR Code
        return qrCodeService.generateQRCodeImage("https://www.facebook.com/");
    }

    @PostMapping("/predict")
    public ResponseEntity<Double> predictTarif(
            @RequestParam String Type,
            @RequestParam int NombreSinistres,
            @RequestParam int AgeClient,
            @RequestParam int DureeContrat,
            @RequestParam double PrimeBase,
            @RequestParam String Localisation) {

        double tarifPrevu = produitService.predictTarif(Type, NombreSinistres, AgeClient, DureeContrat, PrimeBase, Localisation);
        return ResponseEntity.ok(tarifPrevu);
    }


}


