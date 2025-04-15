package com.maghrebia.Sinistre;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@RestController
@RequestMapping("/sinistre")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200") // Angular port
public class SinistreController {
    private SinistreService sinistreService;

    @PostMapping("/add")
    public Sinistre addSinistre(@RequestParam("description") String description,
                                @RequestParam("date") String dateString,
                                @RequestParam("montantRembourssement") double montantRembourssement,
                                @RequestParam("status") String status,
                                @RequestParam(value = "file", required = false) MultipartFile file) {
        Sinistre sinistre = new Sinistre();
        sinistre.setDescription(description);

        // Parse the date string into Date object
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  // Adjust format as needed
            Date date = dateFormat.parse(dateString);
            sinistre.setDateSinistre(date);
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle date parse exception
        }

        // Set additional fields
        sinistre.setMontantRembourssement(montantRembourssement);
        sinistre.setStatus(status);

        // Handle file upload (image) if present
        if (file != null && !file.isEmpty()) {
            try {
                // Convert the file to byte array and set it to the sinistre
                byte[] imageBytes = file.getBytes();
                sinistre.setImageData(imageBytes);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle error during file processing
            }
        }



        // Save the sinistre to the database
        return sinistreService.addSinsitre(sinistre);
    }
    /*public Sinistre addSinistre(@RequestBody Sinistre sinistre) {
        // Si une image en base64 est envoyée
        if (sinistre.getImageBase64() != null) {
            byte[] decodedImage = Base64.getDecoder().decode(sinistre.getImageBase64());
            sinistre.setImageData(decodedImage);  // Stockage de l'image en tant que données binaires
        }
        return sinistreService.addSinsitre(sinistre);
    }*/

    @GetMapping("/all")
    public List<Sinistre> getAllCredits() {
        return sinistreService.getAllCredits();
    }

    @Value("${welcome.message}")
    private String welcomeMessage;
    @GetMapping("/welcome")
    public String welcome() {
        return welcomeMessage;
    }



    @PutMapping("/{id}")
    public ResponseEntity<Sinistre> updateSinistre(
            @PathVariable String id,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "dateSinistre", required = false) Date dateSinistre,
            @RequestParam(value = "montantRembourssement", required = false) Double montantRembourssement,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam(value = "type_prop", required = false) String typeProp
    ) throws IOException {

        Optional<Sinistre> existingSinistreOpt = sinistreService.findById(id);
        if (!existingSinistreOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Sinistre existingSinistre = existingSinistreOpt.get();

        // Mise à jour uniquement des champs non nuls
        if (description != null) existingSinistre.setDescription(description);
        if (dateSinistre != null) existingSinistre.setDateSinistre(dateSinistre);
        if (montantRembourssement != null) existingSinistre.setMontantRembourssement(montantRembourssement);
        if (status != null) existingSinistre.setStatus(status);
        if (typeProp != null) existingSinistre.setType_prop(typeProp);

        if (image != null && !image.isEmpty()) {
            existingSinistre.setImage(image.getOriginalFilename());
            existingSinistre.setImageData(image.getBytes());
        }

        Sinistre updatedSinistre = sinistreService.updateSinistre(id, existingSinistre);
        return ResponseEntity.ok(updatedSinistre);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSinistre(@PathVariable String id) {
        sinistreService.deleteSinistre(id);
        return ResponseEntity.noContent().build();
    }
}

