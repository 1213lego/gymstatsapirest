package com.gymstatsapirest.service;
import com.gymstatsapirest.exception.RecursoNoEncontradoException;
import com.gymstatsapirest.model.*;
import com.gymstatsapirest.repository.*;
import com.gymstatsapirest.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import java.util.Date;
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
    @Autowired
    private JwtProvider jwtProvider;
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

    public ResponseEntity darMisSuscripciones(int page, int size, Map<String,String> jwtResponse) {
        if(!jwtProvider.validateJwtToken(jwtResponse.get("token"))){
            return  new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        String username=jwtProvider.getUserNameFromJwtToken(jwtResponse.get("token"));
        Usuario usuario=autenticacionUsuarioRepository.findByUsername(username).get().getUsuario();
        if(!usuario.getTipoUsuario().getTipo().equals(utils.getTipoUsuarioCliente().getTipo())){
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(suscripcionesRepository.findAllByCliente(PageRequest.of(page,size, Sort.by("fechaInicio").descending()),usuario.getCliente())
        , HttpStatus.OK);
    }

    public ResponseEntity congelarSuscripcion(Map<String,String> jwtResponse) {

        if(!jwtProvider.validateJwtToken(jwtResponse.get("token"))){
            return  new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        String username=jwtProvider.getUserNameFromJwtToken(jwtResponse.get("token"));
        Usuario usuario=autenticacionUsuarioRepository.findByUsername(username).get().getUsuario();
        if(!usuario.getTipoUsuario().getTipo().equals(utils.getTipoUsuarioCliente().getTipo())){
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        Cliente cliente=usuario.getCliente();
        Page<Suscripcione> suscripcioneList=suscripcionesRepository.findAllByCliente(PageRequest.of(0,1, Sort.by("fechaInicio").descending()),cliente);
        Suscripcione suscripcione=suscripcioneList.getContent().get(0);
        suscripcione.setFechaFin(new Date(System.currentTimeMillis()));
        if(suscripcione.getEstadoSuscripcion().getEstadoSuscripcion().equals(utils.getEstadoSuscripcionCongelada().getEstadoSuscripcion())){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        suscripcione.setEstadoSuscripcion(utils.getEstadoSuscripcionCongelada());
        return new ResponseEntity(suscripcionesRepository.save(suscripcione),HttpStatus.OK);
    }
}
