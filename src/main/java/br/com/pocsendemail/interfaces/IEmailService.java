package br.com.pocsendemail.interfaces;

import javax.mail.MessagingException;
import java.io.File;

public interface IEmailService {
    void sendEmail(String subject,String from, String to, File... atatchment) ;
}
