package com.gymstatsapirest.service;
import com.gymstatsapirest.exception.RecursoNoEncontradoException;
import com.gymstatsapirest.model.*;
import com.gymstatsapirest.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    @Autowired
    private SuscripcionesRepository suscripcionesRepository;
    public Usuario crearCliente(Usuario usuario)
    {
        usuario.setTipoUsuario(utils.getTipoUsuarioCliente());
        usuario.getAutenticacionUsuarios().setUsuario(usuario);
        usuario.setEstadosUsuario(utils.getEstadoUsuarioActivo());
        Usuario newUsuario=usuarioRepository.save(usuario);
        Cliente cliente=new Cliente(usuario.getDocumento());
        cliente=clienteRepository.save(cliente);
        usuario.getAutenticacionUsuarios().setPassword(utils.encriptarPassword(usuario.getAutenticacionUsuarios().getPassword()));
        autenticacionUsuarioRepository.save(usuario.getAutenticacionUsuarios());
        return newUsuario;
    }
    public ResponseEntity<?> badRequestErrorFields(List<FieldError> fieldErrors) {
        return utils.badRequestErrorFields(fieldErrors);
    }

    public Map<String, String> clienteValidoParaCrear(Usuario usuario) {
        return utils.usuarioValidoParaCrear(usuario);
    }

    //Deprueba
    public List darCliente()
    {
        return clienteRepository.findAll();
    }

    public Page<Suscripcione> darMisSuscripciones(int page, int size, Integer cedula) {
        Cliente cliente=clienteRepository.findById(cedula).orElseThrow(()-> new RecursoNoEncontradoException("Cliente","cedula",cedula));
        return suscripcionesRepository.findAllByCliente(PageRequest.of(page,size, Sort.by("fechaInicio").descending()),cliente);
    }
}
