package com.github.kacperkwiatkowski.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
public class EmailConfig {

    @Autowired
    private JavaMailSender javaMailSender;

    void sendEmail(String to, String subject, String content) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);

        msg.setSubject(subject);
        msg.setText(content);

        javaMailSender.send(msg);

    }
}
