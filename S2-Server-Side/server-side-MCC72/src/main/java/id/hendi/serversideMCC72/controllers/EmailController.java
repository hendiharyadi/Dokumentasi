/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.hendi.serversideMCC72.controllers;

import id.hendi.serversideMCC72.models.dto.request.EmailRequest;
import id.hendi.serversideMCC72.services.EmailService;
import id.hendi.serversideMCC72.services.EmailThService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Hendi
 */
@AllArgsConstructor
@RestController
@RequestMapping
public class EmailController {
    
    private EmailService emailService;
    private EmailThService emailThService;
    
    //Simple Mail Message
    @PostMapping("/email")
    public EmailRequest sendSimpleMessage(@RequestBody EmailRequest emailRequest){
        return emailService.sendSimpleMessage(emailRequest);
    }
    
    //Mime Message
    @PostMapping("/attach")
    public EmailRequest sendMessageWithAttachment(@RequestBody EmailRequest emailRequest) {
        return emailService.sendMessageWithAttachment(emailRequest);
  }
    //Mime Message
    @PostMapping("/attachth")
    public EmailRequest sendMessageWithAttachmentTh(@RequestBody EmailRequest emailRequest) {
        return emailThService.sendMessageWithAttachment(emailRequest);
  }
}
