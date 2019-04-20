package com.gymstatsapirest.controller;
import com.gymstatsapirest.exception.RecursoNoEncontradoException;
import com.gymstatsapirest.model.Usuario;
import com.gymstatsapirest.service.ServicioEmpleado;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
}
