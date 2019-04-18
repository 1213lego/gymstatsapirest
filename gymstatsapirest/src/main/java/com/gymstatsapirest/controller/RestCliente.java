package com.gymstatsapirest.controller;

import com.gymstatsapirest.model.Genero;
import com.gymstatsapirest.model.Profesione;
import com.gymstatsapirest.model.TipoDocumento;
import com.gymstatsapirest.model.Usuario;
import com.gymstatsapirest.service.ServicioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/clientes")
@CrossOrigin
public class RestCliente
{
    @Autowired
    private ServicioCliente servicioCliente;

    @PostMapping(consumes = "application/json",produces = "application/json")
    public ResponseEntity<?> crearCliente(@Valid @RequestBody Usuario usuario, BindingResult bindingResult)
    {
        //Si el usuario a crear tiene errores en alguno de sus campos
        if(bindingResult.hasErrors())
        {
            //generamos una respuesta con map clave (campo del error) valor(mensaje del error en el campo)
            Map<String,String> mapErrores=new HashMap<>();
            for (FieldError fieldError: bindingResult.getFieldErrors())
            {
                mapErrores.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return new ResponseEntity<Map<String,String>>(mapErrores, HttpStatus.BAD_REQUEST);
        }
        Usuario newCliente=servicioCliente.crearCliente(usuario);
        return new ResponseEntity<Usuario>(newCliente,HttpStatus.CREATED);
    }

    @GetMapping(path = "/tiposdocumento", produces = "application/json")
    public List<TipoDocumento> darTiposDocumento()
    {
        return servicioCliente.darTiposDocumento();
    }
    @GetMapping(path = "/generos", produces = "application/json")
    public List<Genero> darGeneros()
    {
        return servicioCliente.darGeneros();
    }
    @GetMapping(path = "/profesiones", produces = "application/json")
    public List<Profesione> darPofesiones()
    {
        return servicioCliente.darProfesiones();
    }


    @GetMapping(path = "/prueba/usuarios", produces = "application/json")
    public List<Usuario> darUsuarios()
    {
        return servicioCliente.darUsuarios();
    }
}
