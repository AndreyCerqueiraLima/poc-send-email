package br.com.pocsendemail.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@RequiredArgsConstructor
public class EmailConfig {

    @Value(value = "${spring.mail.username}")
    private final String username;

    @Value(value="${spring.mail.password}")
    private final String password;

    @Value(value="${spring.mail.host}")
    private final String host;

    @Value(value="${spring.mail.port}")
    private final int port;

    @Value(value="${spring.mail.protocol}")
    private final String protocol;

    @Value(value="${spring.mail.auth}")
    private final String auth;

    @Value(value="${spring.mail.starttls.enable}")
    private final String enable;

    @Value(value="${spring.mail.debug}")
    private final String debug;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.host);
        mailSender.setPort(this.port);
        mailSender.setUsername(this.username);
        mailSender.setPassword(this.password);
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", this.protocol);
        props.put("mail.smtp.auth", this.auth);
        props.put("mail.smtp.starttls.enable",this.enable);
        props.put("mail.debug", this.debug);
        return mailSender;
    }

}
