package com.maghrebia.Devis.Mailing;


import com.maghrebia.Devis.DevisAutoService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200") // Angular port
@RestController
@RequestMapping("/api/devis/mail")
public class Sendmail {
    @Autowired
    private DevisAutoService devisAutoService;
    @PostMapping("/send")
    public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String message) {
        try {
            devisAutoService.sendEmail(to, subject, message);
            return "Email envoyé avec succès à " + to;
        } catch (MessagingException e) {
            return "Erreur lors de l'envoi de l'email : " + e.getMessage();
        }
    }
}
