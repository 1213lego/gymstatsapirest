package com.gymstatsapirest.controller;
import com.gymstatsapirest.exception.RecursoNoEncontradoException;
import com.gymstatsapirest.model.Cliente;
import com.gymstatsapirest.model.Suscripcione;
import com.gymstatsapirest.model.Usuario;
import com.gymstatsapirest.service.ServicioEmpleado;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value="Empleados ", description="Se encarga de todas las operaciones sobre los empleado")
@RestController
@RequestMapping(path = "/empleados")
@CrossOrigin(origins = "*")
public class RestEmpleado
{
    @Autowired
    private ServicioEmpleado servicioEmpleado;

    @GetMapping(value = "/clientes/{documento}", produces = "application/json")
    @PreAuthorize("hasRole('ROLE_EMPLEADO')")
    public Usuario darCliente(@PathVariable Integer documento)
    {
        return servicioEmpleado.darUsuario(documento).orElseThrow(() -> new RecursoNoEncontradoException("cliente", "documento", documento));
    }

    @PostMapping(value = "/suscripciondiaria", produces = "application/json",consumes = "application/json")
    @PreAuthorize("hasRole('ROLE_EMPLEADO')")
    public ResponseEntity registroSuscripcionDiaria(@Valid @RequestBody Cliente cliente, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return servicioEmpleado.getUtils().badRequestErrorFields(bindingResult.getFieldErrors());
        }
        else
        {
            return servicioEmpleado.registrarSuscripcionDiaria(cliente);
        }
    }


    @PostMapping(value = "/suscripciones", produces = "application/json",consumes = "application/json")
    @PreAuthorize("hasRole('ROLE_EMPLEADO')")
    public ResponseEntity registroSuscripcionD(@RequestBody Suscripcione suscripcion, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return servicioEmpleado.getUtils().badRequestErrorFields(bindingResult.getFieldErrors());
        }
        else
        {
            return servicioEmpleado.registrarSuscripcion(suscripcion);
        }
    }
}
