package com.example.user.user;


import org.springframework.mail.javamail.JavaMailSenderImpl;

public class CustomEmailConfig {

    public JavaMailSenderImpl javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        // Ne pas utiliser localhost ou port 25 dans une configuration pour Gmail
        mailSender.setHost("smtp.gmail.com");  // Host de Gmail
        mailSender.setPort(587);                // Utilise le port SMTP pour Gmail (587 ou 465)

        mailSender.setUsername("achoury.mayem@gmail.com");  // Ton adresse email Gmail
        mailSender.setPassword("asmrasmarani12%");     // Ton mot de passe ou mot de passe d'application

        // Configuration supplémentaire si nécessaire
        mailSender.setProtocol("smtp");
        mailSender.getJavaMailProperties().put("mail.smtp.auth", "true");
        mailSender.getJavaMailProperties().put("mail.smtp.starttls.enable", "true");

        return mailSender;
    }
}
