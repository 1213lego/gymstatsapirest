package com.gymstatsapirest.service;

import com.gymstatsapirest.model.Tarifa;
import com.gymstatsapirest.repository.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ServicioMain
{
    @Autowired
    private TarifaRepository tarifaRepository;

    public List<Tarifa> darTarifas()
    {
        return tarifaRepository.findAll();
    }
}
