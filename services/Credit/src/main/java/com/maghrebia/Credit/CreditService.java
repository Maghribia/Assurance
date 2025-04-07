package com.maghrebia.Credit;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CreditService {
    @Autowired
    private CreditRepositroy creditRepository;



    @Autowired
    private MLModelService mlModelService;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserClient userClient;




    public Credit addCredit(Credit credit) {

        return creditRepository.save(credit);
    }

    // ✅ Récupérer tous les crédits
    public List<Credit> getAllCredits() {
        return creditRepository.findAll();
    }
    public Credit updateCredit(long id, Credit updatedCredit) {
        Optional<Credit> existingCredit = creditRepository.findById(id);

        if (existingCredit.isPresent()) {
            Credit credit = existingCredit.get();
            credit.setMontant(updatedCredit.getMontant());
            credit.setTauxInteret(updatedCredit.getTauxInteret());
            credit.setDuree(updatedCredit.getDuree());
            credit.setTypeCredit(updatedCredit.getTypeCredit());
            credit.setEtatCredit(updatedCredit.getEtatCredit());
            credit.setHistoriquePaiements(updatedCredit.getHistoriquePaiements());

            return creditRepository.save(credit);
        } else {
            throw new RuntimeException("Crédit non trouvé avec l'ID : " + id);
        }
    }
    public void deleteCredit(long id) {
        creditRepository.deleteById(id);
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
        helper.setFrom("selmimaher12@gmail.com");

        // Envoi de l'email
        mailSender.send(message);
    }


}

