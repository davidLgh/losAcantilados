package com.los_acantilados.carta.carta.mail.controller;

import com.los_acantilados.carta.carta.mail.dto.MailDTO;
import com.los_acantilados.carta.carta.mail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;

@Controller
@RequestMapping("/email")
public class MailController {

    @Autowired
    private MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }
    @Autowired
    private TemplateEngine templateEngine;

/*
    @PostMapping("/sendMail")
    public ResponseEntity<MailDTO> sendMail(@RequestBody MailDTO mailDTO) {
        String subject = "Comentarios desde tu Menú Web";
        String message = mailDTO.getMessage();
        String name = mailDTO.getName();
        String email = mailDTO.getEmail();

        //TODO: poner como TO el correo de Los Acantilados
        String newMessage = "Mensaje: \n   " + message + "\n\nDatos de contacto: " + "\nNombre: " + name + "\nEmail: " + email;
        mailService.sendSimpleMail("senderlosacantilados@hotmail.com", "senderlosacantilados@hotmail.com", subject, newMessage);
        return ResponseEntity.status(HttpStatus.OK).body(mailDTO);
    }
*/

    @PostMapping("/sendMail")
    public ResponseEntity<MailDTO> sendMail(@RequestBody MailDTO mailDTO) throws MessagingException {
        //TODO: convertir los datos del DTO en variables de entorno de thymeleaf
        String subject = "Comentarios desde tu Menú Web";
        String message = mailDTO.getMessage();
        String name = mailDTO.getName();
        String email = mailDTO.getEmail();

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("name", name);
        context.setVariable("email", email);
        String htmlContext = templateEngine.process("contact-email-template", context);

        mailService.fileTextMessage(htmlContext);
        return ResponseEntity.status(HttpStatus.OK).body(mailDTO);

    }
}
