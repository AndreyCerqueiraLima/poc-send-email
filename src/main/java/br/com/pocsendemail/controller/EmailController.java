package br.com.pocsendemail.controller;

import br.com.pocsendemail.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
@RequestMapping(value="api/v1/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @GetMapping
    public void enviarEmail(){
        HashMap<String, Object> params = new HashMap<>();
        params.put("text","Socorro deus!!!!");
        this.emailService.sendCustomEmail("andreydarochacerqueiralima@gmail.com","Email Teste","template-email", params);
    }
}
