package com.gymstatsapirest.service;
import com.gymstatsapirest.model.*;
import com.gymstatsapirest.repository.*;
import com.gymstatsapirest.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.*;

@Service
public class ServicioMain
{
    @Autowired
    private TarifaRepository tarifaRepository;
    @Autowired
    private MaquinaRepository maquinaRepository;
    @Autowired
    private Utils utils;

    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private GeneroRepository generoRepository;
    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;
    @Autowired
    private AutenticacionUsuarioRepository autenticacionUsuarioRepository;
    @Autowired
    private AsistenciaUsuarioRepository asistenciaUsuarioRepository;
    @Autowired
    private SuscripcionesRepository suscripcionesRepository;
    @Autowired
    private EstadosMaquinaRepository estadosMaquinaRepository;

    public List<Tarifa> darTarifas()
    {
        return tarifaRepository.findAll();
    }
    public List<Maquina> darMaquina()
    {
        return maquinaRepository.findAll();
    }

    public Utils getUtils() {
        return utils;
    }
    public ResponseEntity<?> login(AutenticacionUsuario autenticacionUsuario)
    {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        autenticacionUsuario.getUsername(),
                        autenticacionUsuario.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt=jwtProvider.generateJwtToken(authentication);
        Map<String,Object> result=new HashMap<>();
        Optional<AutenticacionUsuario>autenticacion=autenticacionUsuarioRepository.findByUsername(autenticacionUsuario.getUsername());
        if(autenticacion.isPresent())
        {
            Usuario usuario=autenticacion.get().getUsuario();
            usuario.setAutenticacionUsuarios(null);
            result.put("usuario",usuario);
        }
        result.put("token",new JwtResponse(jwt));
        return ResponseEntity.ok(result);
    }
    public List<Genero> darGeneros()
    {
        return generoRepository.findAll();
    }
    public List<TipoDocumento> darTiposDocumento()
    {
        return tipoDocumentoRepository.findAll();
    }

    public Map<String, String> validar(JwtResponse jwtResponse)
    {
        Map<String,String> result=new HashMap<>();
        Usuario usuario=null;
        if(jwtProvider.validateJwtToken(jwtResponse.getAccessToken()))
        {
            String username=jwtProvider.getUserNameFromJwtToken(jwtResponse.getAccessToken());
            Optional<AutenticacionUsuario> autenticacion=autenticacionUsuarioRepository.findByUsername(username);
            if(autenticacion.isPresent())
            {
                usuario=autenticacion.get().getUsuario();
                if(usuario.getTipoUsuario().getTipo().equals(Utils.TIPO_USUARIO_ADMIN))
                {
                    result.put("usuario",Utils.TIPO_USUARIO_ADMIN);
                }
                else if(usuario.getTipoUsuario().getTipo().equals(Utils.TIPO_USUARIO_CLIENTE))
                {
                    result.put("usuario",Utils.TIPO_USUARIO_CLIENTE);
                }
                else if(usuario.getTipoUsuario().getTipo().equals(Utils.TIPO_USUARIO_EMPLEADO))
                {
                    result.put("usuario",Utils.TIPO_USUARIO_EMPLEADO);
                }
            }
        }
        else
        {
            result.put("usuario","expirado");
        }
        return result;
    }

    public ResponseEntity<?> registrarAsistenciaUsuario(AutenticacionUsuario autenticacionUsuario)
    {
        Optional<AutenticacionUsuario> autenticacion=autenticacionUsuarioRepository.findByUsername(autenticacionUsuario.getUsername());
        if(!autenticacion.isPresent())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else if(!utils.encriptarPassword(autenticacionUsuario.getPassword()).equals(autenticacion.get().getPassword()))
        {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        else if(autenticacion.get().getUsuario().getTipoUsuario().getTipo().equals(utils.getTipoUsuarioEmpleado().getTipo()))
        {
            return registrarAsistenciaEmpleado(autenticacion.get().getUsuario());
        }
        else
        {
            return registrarAsistenciaCliente(autenticacion.get().getUsuario());
        }
    }

    private ResponseEntity registrarAsistenciaEmpleado(Usuario usuario)
    {
        return null;
    }
    private ResponseEntity registrarAsistenciaCliente(Usuario usuario)
    {
        ResponseEntity<?> result=null;
        if(suscripcionesRepository.existeSuscripcionActiva(new Cliente(usuario.getDocumento()),utils.getEstadoSuscripcionVigente())==null)
        {
            result= new ResponseEntity<>(HttpStatus.PAYMENT_REQUIRED);
        }
        else
        {
            Calendar c = Calendar.getInstance();
            Integer dia = c.get(Calendar.DATE);
            Integer mes = c.get(Calendar.MONTH)+1;
            Integer annio = c.get(Calendar.YEAR);
            List<AsistenciasUsuario> asistencias= asistenciaUsuarioRepository.asistenciaPorUsuarioyFecha(dia,mes,annio,usuario);
            if(asistencias.size()==0)
            {
                registraAsistencia(usuario);
                result=new ResponseEntity<>(HttpStatus.OK);
            }
            else
            {
                for (AsistenciasUsuario  asistencia: asistencias)
                {
                    if(asistencia.getFechaSalida()==null)
                    {
                        actualizarAsistenciaUsuario(asistencia,new Date(System.currentTimeMillis()));
                        result=new ResponseEntity<>(HttpStatus.OK);
                        break;
                    }
                }
            }
            if(result==null)
            {
                registraAsistencia(usuario);
                result=new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return result;
    }
    private void registraAsistencia(Usuario usuario)
    {
        AsistenciasUsuario nuevaAsistencia= new AsistenciasUsuario();
        nuevaAsistencia.setFechaIngreso(new Date(System.currentTimeMillis()));
        nuevaAsistencia.setUsuario(usuario);
        asistenciaUsuarioRepository.save(nuevaAsistencia);
    }
    public List<EstadosMaquina> darEstadosMaquinas()
    {
        return estadosMaquinaRepository.findAll();
    }
    private void actualizarAsistenciaUsuario(AsistenciasUsuario asistenciasUsuario, Date fechaSalida)
    {
        asistenciasUsuario.setFechaSalida(fechaSalida);
        asistenciaUsuarioRepository.save(asistenciasUsuario);
    }
}

