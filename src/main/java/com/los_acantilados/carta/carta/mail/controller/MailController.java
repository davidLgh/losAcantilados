package com.los_acantilados.carta.carta.mail.controller;

import com.los_acantilados.carta.carta.mail.dto.MailDTO;
import com.los_acantilados.carta.carta.mail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/email")
public class MailController {

    @Autowired
    private MailService mailService;

 /*   @GetMapping("/")
    public String index(){
        return "index.html";
    }
*/
 @PostMapping("/sendMail")
 public ResponseEntity<MailDTO> sendMail(@RequestBody MailDTO mailDTO) {
     String subject = "Comentarios desde tu Menú Web";
     String message = mailDTO.getMessage();
     String name = mailDTO.getName();
     String email = mailDTO.getEmail();

     //TODO: poner como TO el correo de Los Acantilados
     String newMessage = "Mensaje: \n   " + message + "\n\nDatos de contacto: " + "\nNombre: " + name + "\nEmail: " + email;
     mailService.sendMail("senderlosacantilados@gmail.com", "senderlosacantilados@gmail.com", subject, newMessage);
     return ResponseEntity.status(HttpStatus.OK).body(mailDTO);
 }
}
