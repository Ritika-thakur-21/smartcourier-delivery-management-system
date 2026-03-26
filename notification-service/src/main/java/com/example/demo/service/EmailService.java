package com.example.demo.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String subject, String message) {

        String formattedMessage =
                "🚚 Delivery Update\n\n" +
                message +
                "\n\nThank you for using SmartCourier 💙";

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(formattedMessage);

        mailSender.send(mail);

        System.out.println("✅ EMAIL SENT TO: " + to);
    }
}