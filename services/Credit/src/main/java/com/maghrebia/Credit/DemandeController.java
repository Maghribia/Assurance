package com.maghrebia.Credit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Demande")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200") // Angular port
public class DemandeController {
    private final ServiceDemande serviceDemande;
    private final MLModelService mlModelService; // Ajoutez une d&eacute;pendance pour le service ML    private final FlaskApiService flaskApiService;
    private final FlaskApiService flaskApiService;
    private CreditService creditService;






    @PostMapping( "/add")
    public Demande addDemande(@RequestBody Demande demande,  @RequestParam String userEmail) {
        // Calcul du score de risque
        Double scoreRisque = mlModelService.getRisquePrediction(demande);
        demande.setScoreRisque(scoreRisque);

        // Conversion de la demande en JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonInput = "";
        try {
            jsonInput = objectMapper.writeValueAsString(demande);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erreur lors de la conversion de la demande en JSON", e);
        }

        // Appel à l'API Flask pour prédire le gain
        String gainPredictionResponse = flaskApiService.predictCreditDemand(jsonInput);

        // Extraire la valeur de "gainPredicted" depuis la réponse JSON
        try {
            JsonNode jsonNode = objectMapper.readTree(gainPredictionResponse);
            double gainPredicted = jsonNode.get("gainPredicted").asDouble();
            demande.setGainPrediction(gainPredicted + ""); // Convertir en String pour stocker
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erreur lors du parsing de la prédiction du gain", e);
        }

        // Sauvegarde de la demande
        return serviceDemande.addDemande(demande,userEmail);
    }


    @GetMapping("/all")
    public List<Demande> getAllDemandes() {
        return serviceDemande.getAllDemandes();
    }

    @GetMapping("/{id}")
    public Optional<Demande> getDemandeById(@PathVariable long id) {
        return serviceDemande.getDemandeById(id);
    }

    @PutMapping("/update/{id}")
    public Demande updateDemande(@PathVariable long id, @RequestBody Demande updatedDemande) {
        return serviceDemande.updateDemande(id, updatedDemande);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDemande(@PathVariable long id) {
        serviceDemande.deleteDemande(id);
    }
    @GetMapping("/train")
    public String trainModel() {
        return flaskApiService.trainModel();  // Appel à l'API Flask pour entraîner le modèle
    }

    @PostMapping("/predict")
    public String predictCreditDemand(@RequestBody String jsonInput) {
        return flaskApiService.predictCreditDemand(jsonInput);  // Appel à l'API Flask pour prédire la demande de crédit
    }

    @GetMapping("/totalDemandeCount")
    public long getTotalDemandeCount() {
        return serviceDemande.getTotalDemandeCount();
    }

    @PostMapping("/send")
    public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String message) {
        try {
            creditService.sendEmail(to, subject, message);
            return "Email envoyé avec succès à " + to;
        } catch (MessagingException e) {
            return "Erreur lors de l'envoi de l'email : " + e.getMessage();
        }
    }
}
