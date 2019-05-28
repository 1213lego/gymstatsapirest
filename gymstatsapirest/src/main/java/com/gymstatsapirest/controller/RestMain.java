package com.gymstatsapirest.controller;
import com.gymstatsapirest.model.*;
import com.gymstatsapirest.service.JwtResponse;
import com.gymstatsapirest.service.ServicioMain;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Api(value="main ", description="Se encarga de las operaciones en general que no requieren de autenticacion")
@RestController
@CrossOrigin(origins = "*")
public class RestMain
{
    @Autowired
    private ServicioMain servicioMain;

    @GetMapping(path = "/tarifas", produces = "application/json")
    public List<Tarifa> darTarifas()
    {
        return servicioMain.darTarifas();
    }

    @GetMapping(path = "/maquinas", produces = "application/json")
    public List<Maquina> darMaquinas()
    {
        return servicioMain.darMaquina();
    }

    @PostMapping(path = "/login",produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> login(@Valid @RequestBody AutenticacionUsuario autenticacionUsuario, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return servicioMain.getUtils().badRequestErrorFields(bindingResult.getFieldErrors());
        }
        return servicioMain.login(autenticacionUsuario);
    }

    @GetMapping(path = "/generos")
    public List<Genero> darGeneros()
    {
        return servicioMain.darGeneros();
    }
    @GetMapping(path = "/tiposdocumento")
    public List<TipoDocumento> darTiposDocumento()
    {
        return servicioMain.darTiposDocumento();
    }

    @PostMapping(path = "/validate",produces = "application/json", consumes = "application/json")
    public Map<String,String> validar(@NotNull @RequestBody JwtResponse jwtResponse)
    {
        return servicioMain.validar(jwtResponse);
    }
    @PostMapping(path = "/asistencias",produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> registrarAsistencia(@Valid @RequestBody AutenticacionUsuario autenticacionUsuario,BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return servicioMain.getUtils().badRequestErrorFields(bindingResult.getFieldErrors());
        }
        return servicioMain.registrarAsistenciaUsuario(autenticacionUsuario);
    }

    @GetMapping(path = "/estadosmaquina",produces = "application/json")
    public List<EstadosMaquina> darEstadosMaquina()
    {
        return servicioMain.darEstadosMaquinas();
    }

    @GetMapping(path = "/listar-clientes/{page}/{size}")
    @PreAuthorize("hasRole('ROLE_EMPLEADO') or hasRole('ROLE_ADMIN')")
    public Page<Usuario> listarClientes(@PathVariable int page, @PathVariable int size){
        return servicioMain.listaClientes(page,size);
    }
    //de prueba
    @GetMapping(path = "/documentos-clientes")
    public List<Integer> darDocumentoCliente(){
        return  servicioMain.darDocumentoCliente();
    }

    @GetMapping(path = "/tipos-medida",produces =   "application/json")
    public List<TiposMedida> darTiposMedida(){
        return servicioMain.darTiposMedida();
    }
}
