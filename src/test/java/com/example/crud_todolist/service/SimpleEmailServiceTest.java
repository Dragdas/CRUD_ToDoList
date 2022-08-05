package com.example.crud_todolist.service;

import com.example.crud_todolist.domain.Mail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void shouldSendEmailWithCC() {
        //Given
        Mail mailWithCC = Mail.builder()
                .mailTo("test@test.com")
                .toCC("testCC@test.com")
                .subject("Test")
                .message("Test Message")
                .build();

        Mail mailNoCC = Mail.builder()
                .mailTo("test@test.com")
                .subject("Test")
                .message("Test Message")
                .build();

        SimpleMailMessage mailMessageWithCC = new SimpleMailMessage();
        mailMessageWithCC.setTo(mailWithCC.getMailTo());
        if(mailWithCC.getToCC() != null)
            mailMessageWithCC.setCc(mailWithCC.getToCC());
        mailMessageWithCC.setSubject(mailWithCC.getSubject());
        mailMessageWithCC.setText(mailWithCC.getMessage());

        SimpleMailMessage mailMessageNoCC = new SimpleMailMessage();
        mailMessageNoCC.setTo(mailNoCC.getMailTo());
        if(mailNoCC.getToCC() != null)
            mailMessageNoCC.setCc(mailNoCC.getToCC());
        mailMessageNoCC.setSubject(mailNoCC.getSubject());
        mailMessageNoCC.setText(mailNoCC.getMessage());

        //When
        simpleEmailService.send(mailWithCC);
        simpleEmailService.send(mailNoCC);

        //Then
        verify(javaMailSender, times(1)).send(mailMessageWithCC);
    }


    @Test
    public void shouldSendEmailWithoutCC() {
        //Given

        Mail mailNoCC = Mail.builder()
                .mailTo("test@test.com")
                .subject("Test")
                .message("Test Message")
                .build();


        SimpleMailMessage mailMessageNoCC = new SimpleMailMessage();
        mailMessageNoCC.setTo(mailNoCC.getMailTo());
        if(mailNoCC.getToCC() != null)
            mailMessageNoCC.setCc(mailNoCC.getToCC());
        mailMessageNoCC.setSubject(mailNoCC.getSubject());
        mailMessageNoCC.setText(mailNoCC.getMessage());

        //When
        simpleEmailService.send(mailNoCC);

        //Then
        verify(javaMailSender, times(1)).send(mailMessageNoCC);
    }

}