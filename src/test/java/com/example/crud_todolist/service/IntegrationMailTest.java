package com.example.crud_todolist.service;

import com.example.crud_todolist.domain.Mail;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
public class IntegrationMailTest {

    @Autowired
    private SimpleEmailService simpleEmailService;


    @Test
    void shouldSendMail(){
        //Given
        Mail mail = Mail.builder()
                .mailTo("krzysztofkulpacb@gmail.com")
                .subject("test mail")
                .message("test")
                .build();

        //when
        simpleEmailService.send(mail);


    }



}
