package com.gymstatsapirest.service;

import com.gymstatsapirest.model.EstadosUsuario;
import com.gymstatsapirest.model.TipoUsuario;
import com.gymstatsapirest.repository.EstadoUsuarioRepository;
import com.gymstatsapirest.repository.TipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class Utils
{
    private static final String TIPO_USUARIO_CLIENTE="Cliente";
    private static final String ESTADO_USUARIO_ACTIVO="Activo";
    private static final String ESTADO_USUARIO_INACTIVO="Inactivo";
    @Autowired
    public static Utils instace;
    private TipoUsuario tipoUsuarioCliente;
    private EstadosUsuario estadoUsuarioActivo;
    private EstadosUsuario estadoUsuarioInactivo;

    public Utils(@Autowired EstadoUsuarioRepository estadoUsuarioRepository, @Autowired TipoUsuarioRepository tipoUsuarioRepository)
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

    public TipoUsuario getTipoUsuarioCliente() {
        return tipoUsuarioCliente;
    }

    public void setTipoUsuarioCliente(TipoUsuario tipoUsuarioCliente) {
        this.tipoUsuarioCliente = tipoUsuarioCliente;
    }

    public EstadosUsuario getEstadoUsuarioActivo() {
        return estadoUsuarioActivo;
    }

    public void setEstadoUsuarioActivo(EstadosUsuario estadoUsuarioActivo) {
        this.estadoUsuarioActivo = estadoUsuarioActivo;
    }

    public EstadosUsuario getEstadoUsuarioInactivo() {
        return estadoUsuarioInactivo;
    }

    public void setEstadoUsuarioInactivo(EstadosUsuario estadoUsuarioInactivo) {
        this.estadoUsuarioInactivo = estadoUsuarioInactivo;
    }

}
