package com.example.user.user;


import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @GetMapping("/send-email")
    public String sendTestEmail() {
        emailService.sendEmail("achoury.mayem@gmail.com", "Test Email", "Ceci est un email de test.");
        return "Email envoyé !";
    }

    @PostMapping("/send")
    public String sendEmail(@RequestBody EmailRequest emailRequest) {
        try {
            emailService.sendEmaill(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getMessage());
            return "Email envoyé avec succès à " + emailRequest.getTo();
        } catch (MessagingException e) {
            return "Erreur lors de l'envoi de l'email : " + e.getMessage();
        }
    }


}

