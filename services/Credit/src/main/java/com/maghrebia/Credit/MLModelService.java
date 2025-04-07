package com.maghrebia.Credit;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MLModelService {

    private final String FLASK_API_URL = "http://127.0.0.1:5001/api/credits/evaluer-risque"; // URL de votre API Flask

    // Méthode pour envoyer des données à Flask et récupérer la prédiction
    public Double getRisquePrediction(Credit credit) {
        // Préparer les données à envoyer
        String requestJson = String.format("{\"montant\": %d, \"tauxInteret\": \"%s\", \"duree\": %d, \"typeCredit\": \"%s\", \"historiquePaiements\": \"%s\"}",
                credit.getMontant(), credit.getTauxInteret(), credit.getDuree(), credit.getTypeCredit(), credit.getHistoriquePaiements());

        // Créer une instance de RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Créer des en-têtes
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // Créer une entité avec les en-têtes et les données
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

        // Faire la requête POST à l'API Flask
        ResponseEntity<String> response = restTemplate.exchange(FLASK_API_URL, HttpMethod.POST, entity, String.class);

        // Parser la réponse et extraire le score de risque
        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            // Extraire le scoreRisque (Supposons que la réponse est sous forme de {"scoreRisque": 0.35})
            String scoreRisque = responseBody.split(":")[1].replace("}", "").trim();
            return Double.parseDouble(scoreRisque);
        } else {
            // Si l'appel à Flask échoue, renvoyer un risque par défaut
            throw new RuntimeException("Erreur lors de l'appel au modèle de machine learning.");
        }
    }

    public Double getRisquePrediction(Demande demande) {
        // Préparer les données à envoyer
        String requestJson = String.format("{\"montant\": %d, \"tauxInteret\": \"%s\", \"duree\": %d, \"typeCredit\": \"%s\", \"historiquePaiements\": \"%s\"}",
                demande.getMontant(), demande.getTauxInteret(), demande.getDuree(), demande.getTypeCredit(), demande.getHistoriquePaiements());

        // Créer une instance de RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Créer des en-têtes
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // Créer une entité avec les en-têtes et les données
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

        // Faire la requête POST à l'API Flask
        ResponseEntity<String> response = restTemplate.exchange(FLASK_API_URL, HttpMethod.POST, entity, String.class);

        // Parser la réponse et extraire le score de risque
        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            // Extraire le scoreRisque (Supposons que la réponse est sous forme de {"scoreRisque": 0.35})
            String scoreRisque = responseBody.split(":")[1].replace("}", "").trim();
            return Double.parseDouble(scoreRisque);
        } else {
            // Si l'appel à Flask échoue, renvoyer un risque par défaut
            throw new RuntimeException("Erreur lors de l'appel au modèle de machine learning.");
        }
    }
}
