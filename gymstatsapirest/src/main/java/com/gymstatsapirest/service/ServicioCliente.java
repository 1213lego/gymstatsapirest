package com.gymstatsapirest.service;
import com.gymstatsapirest.model.*;
import com.gymstatsapirest.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioCliente
{
    private static final String TIPO_USUARIO_CLIENTE="Cliente";
    private static final String ESTADO_USUARIO_ACTIVO="Activo";
    private static final String ESTADO_USUARIO_INACTIVO="Inactivo";
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProfesionesRepository profesionesRepository;
    @Autowired
    private GeneroRepository generoRepository;
    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    private TipoUsuario tipoUsuarioCliente;
    private EstadosUsuario estadoUsuarioActivo;
    private EstadosUsuario estadoUsuarioInactivo;

    public ServicioCliente(@Autowired EstadoUsuarioRepository estadoUsuarioRepository, @Autowired TipoUsuarioRepository tipoUsuarioRepository)
    {
        TipoUsuario usuarioCliente=tipoUsuarioRepository.findByTipo(TIPO_USUARIO_CLIENTE);
        if(usuarioCliente==null)
        {
            usuarioCliente=tipoUsuarioRepository.save(new TipoUsuario(TIPO_USUARIO_CLIENTE));
        }
        tipoUsuarioCliente=usuarioCliente;

        EstadosUsuario usuarioActivo=estadoUsuarioRepository.findByEstadoCliente(ESTADO_USUARIO_ACTIVO);
        if(usuarioActivo==null)
        {
            usuarioActivo= estadoUsuarioRepository.save(new EstadosUsuario(ESTADO_USUARIO_ACTIVO));
        }
        estadoUsuarioActivo=usuarioActivo;
        EstadosUsuario usuarioInactivo=estadoUsuarioRepository.findByEstadoCliente(ESTADO_USUARIO_INACTIVO);
        if(usuarioInactivo==null)
        {
            usuarioInactivo=estadoUsuarioRepository.findByEstadoCliente(ESTADO_USUARIO_INACTIVO);
        }
        estadoUsuarioInactivo=usuarioInactivo;
    }
    public Usuario crearCliente(Usuario usuario)
    {
        usuario.setTipoUsuario(tipoUsuarioCliente);
        usuario.setEstadosUsuario(estadoUsuarioActivo);
        Usuario newUsuario=usuarioRepository.save(usuario);
        Cliente cliente=new Cliente(usuario.getDocumento());
        cliente=clienteRepository.save(cliente);
        return newUsuario;
    }
    public List<TipoDocumento> darTiposDocumento()
    {
        return tipoDocumentoRepository.findAll();
    }

    public List<Genero> darGeneros()
    {
        return generoRepository.findAll();
    }

    public List<Profesione> darProfesiones()
    {
        return profesionesRepository.findAll();
    }
    public List<Usuario> darUsuarios()
    {
        return usuarioRepository.findAll();
    }
}
