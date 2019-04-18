package com.gymstatsapirest.controller;

import com.gymstatsapirest.model.Tarifa;
import com.gymstatsapirest.service.ServicioMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/")
@CrossOrigin
public class RestMain
{
    @Autowired
    private ServicioMain servicioMain;

    @GetMapping(path = "/tarifas", produces = "application/json")
    public List<Tarifa> darTarifas()
    {
        return servicioMain.darTarifas();
    }
}
