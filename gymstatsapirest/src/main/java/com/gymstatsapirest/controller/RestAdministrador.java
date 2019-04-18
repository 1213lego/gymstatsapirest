package com.gymstatsapirest.controller;
import com.gymstatsapirest.model.Tarifa;
import com.gymstatsapirest.model.Usuario;
import com.gymstatsapirest.service.ServicioAdministrador;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.Map;
@Api(value="Administrador ", description="Se encarga de todas las operaciones del administrador")
@RestController
@RequestMapping(path = "/admin")
public class RestAdministrador {

    @Autowired
    private ServicioAdministrador servicioAdministrador;

    @ApiOperation(value = "Registra un nuevo empleado" ,
            notes = "Retorna el nuevo empleado si este fue creado, de lo contrario genera un json con sus respectivos erores y codigo de respuesta Bad Request 400 ",
            response = Usuario.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Se ha creado satisfatoriamente"),
            @ApiResponse(code = 400, message = "Los datos suministrados no son validos se retornara un body con su respectivos detalles")
    })
    @PostMapping(path = "/empleados",consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> crearCliente(@ApiParam(value = "cliente a guardar", required = true) @Valid @RequestBody Usuario usuario, BindingResult bindingResult)
    {
        //Si el usuario a crear tiene errores en alguno de sus campos
        if (bindingResult.hasErrors()) {
            //generamos una respuesta con map clave (campo del error) valor(mensaje del error en el campo)
            return servicioAdministrador.badRequestErrorFields(bindingResult.getFieldErrors());
        }
        Map<String, String> validacion = servicioAdministrador.empleadoValidoParaCrear(usuario);
        if (validacion.size() > 0) {
            return new ResponseEntity<>(validacion, HttpStatus.BAD_REQUEST);
        }
        Usuario newEpleado = servicioAdministrador.crearEmpleado(usuario);
        return new ResponseEntity<>(newEpleado, HttpStatus.CREATED);
    }


    @ApiOperation(value = "Agrega una nueva tarifa" ,
            notes = "Retorna la nueva tarifa si esta fue creada, de lo contrario genera un json con sus respectivos erores y codigo de respuesta Bad Request 400 ",
            response = Usuario.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Se ha creado satisfatoriamente"),
            @ApiResponse(code = 400, message = "Los datos suministrados no son validos se retornara un body con su respectivos detalles")
    })
    @PostMapping(path = "/tarifas",consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> crearTarifa(@ApiParam(value = "cliente a guardar", required = true) @Valid @RequestBody Tarifa tarifa, BindingResult bindingResult)
    {
        //Si el usuario a crear tiene errores en alguno de sus campos
        if (bindingResult.hasErrors()) {
            //generamos una respuesta con map clave (campo del error) valor(mensaje del error en el campo)
            return servicioAdministrador.badRequestErrorFields(bindingResult.getFieldErrors());
        }
        Tarifa newTarifa = servicioAdministrador.crearTarifa(tarifa);
        return new ResponseEntity<>(newTarifa, HttpStatus.CREATED);
    }

}
