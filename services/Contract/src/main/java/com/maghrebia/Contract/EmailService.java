package com.maghrebia.Contract;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender ;
    public void sendContractStatusEmail(String newStatus, String recipientEmail) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(recipientEmail);
            helper.setSubject("Mise à jour du statut de votre contrat");
            helper.setText("Bonjour, \n\nLe statut de votre contrat a été mis à jour en : " + newStatus, false);
            helper.setFrom("kassem.benhenda@esprit.tn");

            mailSender.send(message);
            System.out.println("Email envoyé avec succès !");
        } catch (MessagingException e) {
            System.err.println("Erreur lors de l'envoi de l'email : " + e.getMessage());
        }
    }

    public void sendExpirationReminder(String to, Contract contract) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject("Votre contrat d'assurance expire bientôt !");
            helper.setFrom("kassem.benhenda@esprit.tn");

            // Construire le message avec HTML
            String emailContent = "<html><body>"
                    + "<h3>Bonjour,</h3>"
                    + "<p>Votre contrat d'assurance <strong>" + contract.getTypeAssurance() + "</strong> "
                    + "arrive à expiration le <strong>" + contract.getDateExpiration() + "</strong>.</p>"
                    + "<p>Veuillez le renouveler pour éviter toute interruption.</p>"
                    + "<br><p>Cordialement,</p>"
                    + "<p><strong>Votre compagnie d'assurance</strong></p>"
                    + "</body></html>";

            helper.setText(emailContent, true); // true = HTML activé

            mailSender.send(message);
            System.out.println("Email de rappel envoyé avec succès à " + to);
        } catch (MessagingException e) {
            System.err.println("Erreur lors de l'envoi de l'email de rappel : " + e.getMessage());
        }
    }





}
