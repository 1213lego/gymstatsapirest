package com.gymstatsapirest.controller;

import com.gymstatsapirest.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class AutomaticController {
    @Autowired private EmailService emailService;

    @GetMapping(path = "/alerta-suscripciones")
    public int generarAlertasSuscripciones(){
        return emailService.enviarEmailSuscripcionesPorExpirar();
    }
}
