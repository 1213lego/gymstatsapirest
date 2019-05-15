package com.gymstatsapirest.controller;
import com.gymstatsapirest.model.Suscripcione;
import com.gymstatsapirest.model.Usuario;
import com.gymstatsapirest.service.JwtResponse;
import com.gymstatsapirest.service.ServicioCliente;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
@Api(value="Clientes ", description="Se encarga de todas las operaciones sobre los clientes")
@RestController
@CrossOrigin(origins = "*")
public class RestCliente
{

    @Autowired
    private ServicioCliente servicioCliente;
    @ApiOperation(value = "Registra un nuevo cliente",
            notes = "Retorna el nuevo cliente si este fue creado, de lo contrario genera un json con sus respectivos erores y codigo de respuesta Bad Request 400 ",
            response = Usuario.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Se ha creado satisfatoriamente"),
            @ApiResponse(code = 400, message = "Los datos suministrados no son validos se retornara un body con su respectivos detalles")
    })
    @PostMapping(path = "/signup",consumes = "application/json",produces = "application/json")
    public ResponseEntity<?> crearCliente(@ApiParam(value = "cliente a guardar", required = true) @Valid @RequestBody Usuario usuario, BindingResult bindingResult)
    {
        //Si el usuario a crear tiene errores en alguno de sus campos
        if(bindingResult.hasErrors())
        {
            //generamos una respuesta con map clave (campo del error) valor(mensaje del error en el campo)
            return  servicioCliente.badRequestErrorFields(bindingResult.getFieldErrors());
        }
        Map<String, String> validacion=servicioCliente.clienteValidoParaCrear(usuario);
        if(validacion.size()>0)
        {
            return new ResponseEntity<>(validacion, HttpStatus.BAD_REQUEST);
        }

        Usuario newCliente=servicioCliente.crearCliente(usuario);
        return new ResponseEntity<>(newCliente,HttpStatus.CREATED);
    }

    @PostMapping(path = "clientes/mis-suscripciones/{page}/{size}",consumes = "application/json",produces = "application/json")
    @PreAuthorize("hasRole('ROLE_CLIENTE') or hasRole('ROLE_ADMIN') ")
    public ResponseEntity misSuscripciones(@PathVariable int page, @PathVariable int size, @NotNull @RequestBody Map jwtResponse){
        return servicioCliente.darMisSuscripciones(page,size,jwtResponse);
    }

    @PutMapping(path = "clientes/congelar-suscripcion",consumes = "application/json",produces = "application/json")
    @PreAuthorize("hasRole('ROLE_CLIENTE')")
    public ResponseEntity congelarSuscripcion(@NotNull @RequestBody Map jwtResponse){
        return servicioCliente.congelarSuscripcion(jwtResponse);
    }
}
