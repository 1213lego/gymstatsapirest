package com.gymstatsapirest.controller;

import com.gymstatsapirest.repository.AutenticacionUsuarioRepository;
import com.gymstatsapirest.repository.UsuarioRepository;
import com.gymstatsapirest.service.Utils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value="Empleados ", description="Se encarga de todas las operaciones sobre los empleado")
@RestController
@RequestMapping(path = "/empleados")
public class RestEmpleado
{
    @Autowired
    private Utils utils;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AutenticacionUsuarioRepository autenticacionUsuarioRepository;
}
