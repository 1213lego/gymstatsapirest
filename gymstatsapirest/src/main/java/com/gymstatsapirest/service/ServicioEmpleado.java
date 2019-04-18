package com.gymstatsapirest.service;
import com.gymstatsapirest.model.Usuario;
import com.gymstatsapirest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ServicioEmpleado
{
    @Autowired
    private UsuarioRepository usuarioRepository;


    public Optional<Usuario> darUsuario(Integer documento)
    {
        return usuarioRepository.findById(documento);
    }
}
