package br.com.pocsendemail.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    public final static String EMAIL_CONTATO = "andrey.cerqueira.work@gmail.com";

    private final SpringTemplateEngine thymeleafTemplateEngine;

    public void sendCustomEmail(String to,
                                String subject,
                                String templateEmailName,
                                Map<String, Object> params,
                                File... attatchments) {
        Context thymeleafContext = new Context();
        thymeleafContext.setVariables(params);
        String htmlBody = thymeleafTemplateEngine.process(templateEmailName, thymeleafContext);
        try {
            sendEmail(subject, to, EMAIL_CONTATO, htmlBody, attatchments);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void sendEmail(String subject, String to, String from, String htmlBody, File... attatchments) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);

        if (attatchments != null) {
            for (File attatchment : attatchments) {
                helper.addAttachment("Invoice", attatchment);
            }

        }

        javaMailSender.send(message);
    }

}
