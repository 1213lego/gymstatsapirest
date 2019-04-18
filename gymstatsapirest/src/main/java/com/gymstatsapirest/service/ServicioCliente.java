package com.gymstatsapirest.service;
import com.gymstatsapirest.model.*;
import com.gymstatsapirest.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Map;

@Service
public class ServicioCliente
{
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private AutenticacionUsuarioRepository autenticacionUsuarioRepository;
    @Autowired
    private Utils utils;


    public Usuario crearCliente(Usuario usuario)
    {
        usuario.setTipoUsuario(utils.getTipoUsuarioCliente());
        usuario.getAutenticacionUsuarios().setUsuario(usuario);
        usuario.setEstadosUsuario(utils.getEstadoUsuarioActivo());
        Usuario newUsuario=usuarioRepository.save(usuario);
        Cliente cliente=new Cliente(usuario.getDocumento());
        cliente=clienteRepository.save(cliente);
        autenticacionUsuarioRepository.save(usuario.getAutenticacionUsuarios());
        return newUsuario;
    }

    public ResponseEntity<?> badRequestErrorFields(List<FieldError> fieldErrors) {
        return utils.badRequestErrorFields(fieldErrors);
    }

    public Map<String, String> clienteValidoParaCrear(Usuario usuario) {
        return utils.usuarioValidoParaCrear(usuario);
    }
}
