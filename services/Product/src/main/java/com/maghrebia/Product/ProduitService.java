    package com.maghrebia.Product;
    import com.google.zxing.WriterException;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.stereotype.Service;
    import org.springframework.web.client.HttpClientErrorException;
    import org.springframework.web.client.ResourceAccessException;
    import org.springframework.web.client.RestTemplate;
    import org.springframework.web.multipart.MultipartFile;

    import java.io.IOException;
    import java.util.*;

    @Service
    @Slf4j
    public class
    ProduitService implements IServiceProduit {
        @Autowired
        private ProduitRepository produitRepository;

        @Autowired
        private QRCodeService qrCodeService;

        public List<Produit> getAllProduits() {
            List<Produit> produits = produitRepository.findAll();
            for (Produit produit : produits) {
                if (produit.getImageData() != null) {
                    String imageBase64 = Base64.getEncoder().encodeToString(produit.getImageData());
                    produit.setImageBase64(imageBase64);  // Ajoute l'image base64 pour chaque produit
                }
            }
            return produits;
        }
        public Optional<Produit> getProduitById(Long id) {
            return produitRepository.findById(id);
        }



        public Produit updateProduit(Long id, Produit produitDetails) {
            Optional<Produit> produitOptional = produitRepository.findById(id);

            if (produitOptional.isPresent()) {
                Produit produit = produitOptional.get();
                produit.setDescription(produitDetails.getDescription());
                produit.setTarifs(produitDetails.getTarifs());
                produit.setImage(produitDetails.getImage());
                produit.setImageData(produitDetails.getImageData());

                return produitRepository.save(produit);
            } else {
                throw new RuntimeException("Produit introuvable");
            }
        }



        public void deleteProduit(Long id) {
            produitRepository.deleteById(id);
        }

        public Produit addProduit(Produit produit) throws WriterException, IOException {
            // Sauvegarde initiale du produit pour obtenir son ID
            Produit savedProduit = produitRepository.save(produit);

            // Génération du lien en fonction du type avec conversion de l'ID en String
            String typeUrl = qrCodeService.generateTypeUrl(produit.getType(), String.valueOf(savedProduit.getId()));
            savedProduit.setTypeUrl(typeUrl);

            // Génération du QR Code à partir du lien
            byte[] qrCodeImage = qrCodeService.generateQRCodeImage(typeUrl);

            // Enregistrement de l'URL du QR Code avec l'ID correctement formaté
            savedProduit.setQrCodeUrl("http://localhost:8080/api/produits/"
                    + produit.getType().toString().toLowerCase()
                    + "/qrcode/" + savedProduit.getId());

            // Sauvegarde finale du produit mis à jour
            return produitRepository.save(savedProduit);
        }


        public Produit save(Produit produit) {
            return produitRepository.save(produit);

        }
        public List<Produit> rechercherParType(String type) {
            return produitRepository.findByType(type);
        }
        public List<Produit> searchProduits(String keyword) {
            return produitRepository.searchProduits(keyword);
        }

        public Produit getFirstProduitByType(String type) {
            return produitRepository.findByType(type).stream().findFirst().orElse(null);

        }
        public double predictTarif(String type, int nombreSinistres, int ageClient, int dureeContrat, double primeBase, String localisation) {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:5000/predict";

            // Construire le JSON de la requête
            Map<String, Object> request = new HashMap<>();
            request.put("Type", type);  // Convertit l'énumération en String
            request.put("NombreSinistres", nombreSinistres);
            request.put("AgeClient", ageClient);
            request.put("DureeContrat", dureeContrat);
            request.put("PrimeBase", primeBase);
            request.put("Localisation", localisation);

            try {
                ResponseEntity<Map> responseEntity = restTemplate.postForEntity(url, request, Map.class);
                Map<String, Double> response = responseEntity.getBody();

                if (response != null && response.containsKey("TarifPrévu")) {
                    return response.get("TarifPrévu");
                } else {
                    throw new RuntimeException("Réponse invalide du serveur.");
                }
            } catch (HttpClientErrorException e) {
                throw new RuntimeException("Erreur HTTP : " + e.getStatusCode(), e);
            } catch (ResourceAccessException e) {
                throw new RuntimeException("Impossible de contacter le serveur : " + e.getMessage(), e);
            }
        }
    }

