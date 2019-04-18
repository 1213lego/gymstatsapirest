package com.gymstatsapirest.service;

import com.gymstatsapirest.model.Usuario;
import com.gymstatsapirest.repository.AutenticacionUsuarioRepository;
import com.gymstatsapirest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Map;

@Component
public class ServicioAdministrador
{
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AutenticacionUsuarioRepository autenticacionUsuarioRepository;
    @Autowired
    private Utils utils;

    public Map<String, String> empleadoValidoParaCrear(Usuario usuario)
    {
        Map<String,String> result=utils.usuarioValidoParaCrear(usuario);

        return result;
    }
    public ResponseEntity<?> badRequestErrorFields(List<FieldError> fieldErrors)
    {
        return utils.badRequestErrorFields(fieldErrors);
    }

    public Usuario crearEmpleado(Usuario usuario)
    {
        return null;
    }
}
