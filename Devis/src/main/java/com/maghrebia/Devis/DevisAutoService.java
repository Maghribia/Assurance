package com.maghrebia.Devis;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class DevisAutoService {

    @Autowired
    private DevisAutoRepository devisAutoRepository;
    @Autowired
    private JavaMailSender mailSender;

    public DevisAuto createDevisAuto(DevisAuto devisAuto) {
        return devisAutoRepository.save(devisAuto);
    }

    public List<DevisAuto> getAllDevisAuto() {
        return devisAutoRepository.findAll();
    }

    public Optional<DevisAuto> getDevisAutoById(String id) {
        return devisAutoRepository.findById(id);
    }

    public DevisAuto updateDevisAuto(String id, DevisAuto updateDevisAuto) {
        Optional<DevisAuto> existingDevisAuto = devisAutoRepository.findById(id);
        if (existingDevisAuto.isPresent()) {
            DevisAuto devisAuto = existingDevisAuto.get();
            // Update the fields of devisAuto with the values from updateDevisAuto
            devisAuto.setImmatriculation(updateDevisAuto.getImmatriculation());
            devisAuto.setMarque(updateDevisAuto.getMarque());
            // Update other fields as needed
            return devisAutoRepository.save(devisAuto);
        } else {
            return null; // or throw an exception
        }
    }

    public void deleteDevisAuto(String id) {
        devisAutoRepository.deleteById(id);
    }

    public void generatePdf(HttpServletResponse response) throws IOException {
        // Implement the logic to generate a PDF for DevisAuto
        // and write it to the HttpServletResponse
    }


    public void sendEmail(String to, String subject, String text) throws MessagingException {
        // Création de l'objet MimeMessage

        MimeMessage message = mailSender.createMimeMessage();

        // Création d'un helper pour gérer l'envoi de l'email
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // Configuration de l'email
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);
        helper.setFrom("yossra.tlili123456789@gmail.com");

        // Envoi de l'email
        mailSender.send(message);
    }


}
