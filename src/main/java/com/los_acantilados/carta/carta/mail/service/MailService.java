package com.los_acantilados.carta.carta.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.UUID;


@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;


    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
/*
    public void sendSimpleMail (String from, String to, String subject, String message){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        javaMailSender.send(mailMessage);
    }
*/
    public void fileTextMessage(String htmlContext) throws MessagingException{
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true,"UTF-8");

        messageHelper.setTo(new String[]{"losacantiladosproductos@gmail.com","claudiopetti@hotmail.com","senderlosacantilados@hotmail.com"});
        //messageHelper.setTo("senderlosacantilados@hotmail.com");

        messageHelper.setFrom("senderlosacantilados@hotmail.com");
        messageHelper.setSubject("Comentarios desde tu Menú Web");
        messageHelper.setText(htmlContext, true);
        this.javaMailSender.send(mimeMessage);
    }

}
