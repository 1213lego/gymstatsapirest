package com.gymstatsapirest.service;

import com.gymstatsapirest.model.*;
import com.gymstatsapirest.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Utils
{
    public static final String TIPO_USUARIO_CLIENTE="ROLE_CLIENTE";
    public static final String TIPO_USUARIO_ADMIN="ROLE_ADMIN";
    public static final String TIPO_USUARIO_EMPLEADO="ROLE_EMPLEADO";
    public static final String ESTADO_USUARIO_ACTIVO="Activo";
    public static final String ESTADO_USUARIO_INACTIVO="Inactivo";
    public static final String ESTADO_SUSCRIPCION_EXPIRADA="expirada";
    public static final String ESTADO_SUSCRIPCION_VIGENTE="vigente";
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AutenticacionUsuarioRepository autenticacionUsuarioRepository;
    @Autowired
    private TarifaRepository tarifaRepository;
    private TipoUsuario tipoUsuarioCliente;
    private TipoUsuario tipoUsuarioAdministrador;
    private TipoUsuario tipoUsuarioEmpleado;
    private EstadosUsuario estadoUsuarioActivo;
    private EstadosUsuario estadoUsuarioInactivo;
    private EstadoSuscripcion estadoSuscripcionExpirada;
    private EstadoSuscripcion estadoSuscripcionVigente;
    private Tarifa tarifaDiaria;
    @Autowired
    private PasswordEncoder encoder;

    public Utils(@Autowired EstadoUsuarioRepository estadoUsuarioRepository, @Autowired TipoUsuarioRepository tipoUsuarioRepository
    , @Autowired EstadoSuscripcionRepository estadoSuscripcionRepository )
    {
        TipoUsuario usuarioCliente=tipoUsuarioRepository.findByTipo(TIPO_USUARIO_CLIENTE);
        if(usuarioCliente==null)
        {
            usuarioCliente=tipoUsuarioRepository.save(new TipoUsuario(TIPO_USUARIO_CLIENTE));
        }
        tipoUsuarioCliente=usuarioCliente;

        TipoUsuario usuarioAdmin=tipoUsuarioRepository.findByTipo(TIPO_USUARIO_ADMIN);
        if(usuarioAdmin==null)
        {
            usuarioAdmin=tipoUsuarioRepository.save(new TipoUsuario(TIPO_USUARIO_ADMIN));
        }
        tipoUsuarioAdministrador=usuarioAdmin;

        TipoUsuario usuarioEmpleado=tipoUsuarioRepository.findByTipo(TIPO_USUARIO_EMPLEADO);
        if(usuarioEmpleado==null)
        {
            usuarioEmpleado=tipoUsuarioRepository.save(new TipoUsuario(TIPO_USUARIO_EMPLEADO));
        }
        tipoUsuarioEmpleado=usuarioEmpleado;
        EstadosUsuario usuarioActivo=estadoUsuarioRepository.findByEstadoCliente(ESTADO_USUARIO_ACTIVO);
        if(usuarioActivo==null)
        {
            usuarioActivo= estadoUsuarioRepository.save(new EstadosUsuario(ESTADO_USUARIO_ACTIVO));
        }
        estadoUsuarioActivo=usuarioActivo;
        EstadosUsuario usuarioInactivo=estadoUsuarioRepository.findByEstadoCliente(ESTADO_USUARIO_INACTIVO);
        if(usuarioInactivo==null)
        {
            usuarioInactivo=estadoUsuarioRepository.save(new EstadosUsuario(ESTADO_USUARIO_INACTIVO));
        }
        estadoUsuarioInactivo=usuarioInactivo;

        EstadoSuscripcion expirada= estadoSuscripcionRepository.findByEstadoSuscripcion(ESTADO_SUSCRIPCION_EXPIRADA);
        if(expirada==null)
        {
            expirada=estadoSuscripcionRepository.save(new EstadoSuscripcion(ESTADO_SUSCRIPCION_EXPIRADA));
        }
        estadoSuscripcionExpirada=expirada;
        EstadoSuscripcion vigente= estadoSuscripcionRepository.findByEstadoSuscripcion(ESTADO_SUSCRIPCION_VIGENTE);
        if(vigente==null)
        {
            vigente=estadoSuscripcionRepository.save(new EstadoSuscripcion(ESTADO_SUSCRIPCION_VIGENTE));
        }
        estadoSuscripcionVigente=vigente;
    }

    public TipoUsuario getTipoUsuarioCliente() {
        return tipoUsuarioCliente;
    }

    public EstadosUsuario getEstadoUsuarioActivo() {
        return estadoUsuarioActivo;
    }

    public TipoUsuario getTipoUsuarioEmpleado() {
        return tipoUsuarioEmpleado;
    }

    public EstadosUsuario getEstadoUsuarioInactivo() {
        return estadoUsuarioInactivo;
    }


    public ResponseEntity badRequestErrorFields(List<FieldError> fieldErrors)
    {
        //generamos una respuesta con map clave (campo del error) valor(mensaje del error en el campo)
        Map<String,String> mapErrores=new HashMap<>();
        for (FieldError fieldError: fieldErrors)
        {
            mapErrores.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        return new ResponseEntity(mapErrores, HttpStatus.BAD_REQUEST);
    }

    /**
     * Validad si se puede crear el usuario verificando, que documento no exista y el username tampoco exista
     * @param usuario
     * @return
     */
    public Map<String,String > usuarioValidoParaCrear(Usuario usuario)
    {
        HashMap<String,String> result=new HashMap<>();
        if(usuario.getAutenticacionUsuarios()==null)
        {
            result.put("autenticacionUsuarios","No puede estar estar vacio");
        }
        else
        {
            boolean existeUserName=autenticacionUsuarioRepository.existsByUsername(usuario.getAutenticacionUsuarios().getUsername());
            if(existeUserName)
            {
                result.put("autenticacionUsuarios.username","ya existe");
            }
        }
        boolean existeUsuario=usuarioRepository.existsByDocumento(usuario.getDocumento());
        if(existeUsuario)
        {
            result.put("documento","ya existe");
        }
        return result;
    }
    public String encriptarPassword(String password)
    {
        return encoder.encode(password);
    }
    public boolean verificarPassword(String password, String encodedPassword)
    {
        return encoder.matches(password,encodedPassword);
    }

    public EstadoSuscripcion getEstadoSuscripcionExpirada() {
        return estadoSuscripcionExpirada;
    }

    public EstadoSuscripcion getEstadoSuscripcionVigente() {
        return estadoSuscripcionVigente;
    }

    public Tarifa getTarifaDiaria()
    {
        return tarifaRepository.findById(new Short("4")).get();
    }
    public int getDiaActual()
    {
        return Calendar.getInstance().get(Calendar.DATE);
    }
    public int getMesActual()
    {
        return (Calendar.getInstance().get(Calendar.MONTH)+1);
    }
    public int getAnioActual()
    {
        return Calendar.getInstance().get(Calendar.YEAR);
    }
}
