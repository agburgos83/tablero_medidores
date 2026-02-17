package com.tablero_medidores.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.tablero_medidores.backend.service.mail.EmailDetails;
import com.tablero_medidores.backend.service.mail.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public String sendSimpleMail(EmailDetails details) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(details.getFrom());
            mailMessage.setTo(details.getTo());
            mailMessage.setSubject(details.getSubject());
            mailMessage.setText(details.getText());
            
            mailSender.send(mailMessage);
            return "mail enviado con éxito...";
        }

        catch (Exception e) {
            return "Error al enviar email";
        }

    }

    

}

