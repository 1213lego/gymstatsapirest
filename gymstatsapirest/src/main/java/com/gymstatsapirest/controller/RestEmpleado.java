package com.gymstatsapirest.controller;
import com.gymstatsapirest.exception.RecursoNoEncontradoException;
import com.gymstatsapirest.model.*;
import com.gymstatsapirest.service.ServicioEmpleado;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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


    @PostMapping(value = "/tipos-medida" , consumes = "application/json")
    @PreAuthorize("hasRole('ROLE_EMPLEADO')")
    public ResponseEntity registrarTipoMedida(@Valid @RequestBody TiposMedida tiposMedida,BindingResult bindingResult){
        if(bindingResult.hasErrors())
        {
            return servicioEmpleado.getUtils().badRequestErrorFields(bindingResult.getFieldErrors());
        }
        else{
            return servicioEmpleado.registrarTipoMedida(tiposMedida);
        }
    }

    @GetMapping(path = "/asistencia-clientes/{page}/{size}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_EMPLEADO')")
    public Page<AsistenciasUsuario> darAsistenciasCliente(@PathVariable int page, @PathVariable int size){
        return servicioEmpleado.darAsistenciasClientes(page,size);
    }

    @PostMapping(path = "/añadir-medida-cliente", consumes = "application/json")
    @PreAuthorize("hasRole('ROLE_EMPLEADO')")
    public ResponseEntity agregarMedidaCliente(@RequestBody MedidaCliente miMedidaCliente){
        return servicioEmpleado.agregarMedidaCliente(miMedidaCliente);
    }
    @PostMapping(path = "/rutinas", consumes = "application/json")
    @PreAuthorize(("hasRole('ROLE_EMPLEADO')"))
    public ResponseEntity agregaRutina(@Valid @RequestBody Rutina rutina){
        return servicioEmpleado.agregarRutina(rutina);
    }

    @GetMapping(path = "/rutinas", produces = "application/json")
    public List<Rutina> darRutinas(){
        return servicioEmpleado.darRutinas();
    }
}
