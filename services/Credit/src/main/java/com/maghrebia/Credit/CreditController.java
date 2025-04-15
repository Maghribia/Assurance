package com.maghrebia.Credit;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/credits")
@RequiredArgsConstructor


@CrossOrigin(origins = "http://localhost:4200") // Angular port

public class CreditController {
    private CreditService creditService;
    private final MLModelService mlModelService; // Ajoutez une dépendance pour le service ML


    private final String UPLOAD_DIR = "uploads/";

    @Value("${welcome.message}")
    private String welcomeMessage;
    @GetMapping("/welcome")
    public String welcome() {
        return welcomeMessage;
    }


    @PostMapping("/add")
    public Credit addCredit(@RequestBody Credit credit
                            ) {

        return creditService.addCredit(credit);
    }
    // ✅ Récupérer tous les crédits
    @GetMapping("/all")
    public List<Credit> getAllCredits() {
        return creditService.getAllCredits();
    }
    @PutMapping("/{id}")
    public Credit updateCredit(@PathVariable long id, @RequestBody Credit updatedCredit) {
        return creditService.updateCredit(id, updatedCredit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContract(@PathVariable long id) {
        creditService.deleteCredit(id);
        return ResponseEntity.noContent().build();
    }








}
