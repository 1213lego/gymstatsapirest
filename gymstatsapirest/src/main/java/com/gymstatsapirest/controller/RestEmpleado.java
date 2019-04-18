package com.gymstatsapirest.controller;
import com.gymstatsapirest.exception.RecursoNoEncontradoException;
import com.gymstatsapirest.model.Usuario;
import com.gymstatsapirest.service.ServicioEmpleado;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Supplier;

@Api(value="Empleados ", description="Se encarga de todas las operaciones sobre los empleado")
@RestController
@RequestMapping(path = "/empleados")
public class RestEmpleado
{
    @Autowired
    private ServicioEmpleado servicioEmpleado;

    @GetMapping(value = "/clientes/{documento}", produces = "application/json")
    public Usuario darCliente(@PathVariable Integer documento)
    {
        return servicioEmpleado.darUsuario(documento).orElseThrow(() -> new RecursoNoEncontradoException("cliente", "documento", documento));
    }
}
