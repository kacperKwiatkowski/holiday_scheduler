package com.github.kacperkwiatkowski.holidayscheduler_backend.controllers;

import com.github.kacperkwiatkowski.holidayscheduler_backend.config.EmailConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping(path = "/send/{id}")
    @PreAuthorize("hasAuthority('self:edit')")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity sendAnEmail(
            @PathVariable("id") int id,
            @RequestParam("address") String address,
            @RequestParam("title") String title,
            @RequestParam("content") String content) {

        sendEmail(address, title, content);
        log.info("Controller 'sendAnEmail' initiated.");
        return null;
    }

    void sendEmail(String to, String subject, String content) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);

        msg.setSubject(subject);
        msg.setText(content);

        javaMailSender.send(msg);

    }
}
