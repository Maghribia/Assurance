package com.example.user.user;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
        } catch (Exception e) {

            throw new RuntimeException("Failed to send email: " + e.getMessage());
        }
    }
    public void sendEmaill(String to, String subject, String text) throws MessagingException {
        // Création de l'objet MimeMessage
        MimeMessage message = mailSender.createMimeMessage();

        // Création d'un helper pour gérer l'envoi de l'email
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // Configuration de l'email
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);
        helper.setFrom("achoury.mayem@gmail.com");

        // Envoi de l'email
        mailSender.send(message);
    }


}