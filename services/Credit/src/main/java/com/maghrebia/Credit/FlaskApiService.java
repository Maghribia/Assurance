package com.maghrebia.Credit;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class FlaskApiService {

    private static final String FLASK_API_BASE_URL = "http://127.0.0.1:5000";  // L'URL de votre API Flask

    private final RestTemplate restTemplate;

    public FlaskApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Méthode pour entraîner le modèle
    public String trainModel() {
        String trainEndpoint = FLASK_API_BASE_URL + "/train";
        ResponseEntity<String> response = restTemplate.exchange(trainEndpoint, HttpMethod.GET, null, String.class);
        return response.getBody();  // Retourne le message de réponse
    }

    // Méthode pour prédire à partir du modèle
    public String predictCreditDemand(String jsonInput) {
        String predictEndpoint = FLASK_API_BASE_URL + "/predict";

        // Créer les en-têtes pour indiquer le type de contenu
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Créer une entité HTTP avec les données et les en-têtes
        HttpEntity<String> entity = new HttpEntity<>(jsonInput, headers);

        // Effectuer un appel POST avec les données d'entrée (jsonInput)
        ResponseEntity<String> response = restTemplate.exchange(predictEndpoint, HttpMethod.POST, entity, String.class);
        return response.getBody();  // Retourne la prédiction
    }
}

