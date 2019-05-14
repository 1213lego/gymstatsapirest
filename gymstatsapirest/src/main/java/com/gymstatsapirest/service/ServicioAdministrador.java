package com.gymstatsapirest.service;
import com.gymstatsapirest.exception.RecursoNoEncontradoException;
import com.gymstatsapirest.model.*;
import com.gymstatsapirest.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ServicioAdministrador
{
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AutenticacionUsuarioRepository autenticacionUsuarioRepository;
    @Autowired
    private Utils utils;
    @Autowired
    private TipoEmpleadoRepository tipoEmpleadoRepository;
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private TarifaRepository tarifaRepository;
    @Autowired
    private MaquinaRepository maquinaRepository;
    @Autowired
    private EstadosMaquinaRepository estadosMaquinaRepository;
    public Map<String, String> empleadoValidoParaCrear(Usuario usuario)
    {
        Map<String,String> result=utils.usuarioValidoParaCrear(usuario);
        if(usuario.getEmpleado()==null)
        {
            result.put("empleado","No puede ser vacio");
        }
        else
        {
            if(usuario.getEmpleado().getTipoEmpleado()==null)
            {
                result.put("empleado.tipoEmpleado","No puede ser vacio");
            }
            else
            {
                if(!tipoEmpleadoRepository.existsByIdTipo(usuario.getEmpleado().getTipoEmpleado().getIdTipo()))
                {
                    result.put("empleado.tipoEmpleado.idTipo","No existe");
                }
            }
        }
        return result;
    }
    public ResponseEntity<?> badRequestErrorFields(List<FieldError> fieldErrors)
    {
        return utils.badRequestErrorFields(fieldErrors);
    }

    public Usuario crearEmpleado(Usuario usuario)
    {
        usuario.setTipoUsuario(utils.getTipoUsuarioEmpleado());
        usuario.getAutenticacionUsuarios().setUsuario(usuario);
        usuario.setEstadosUsuario(utils.getEstadoUsuarioActivo());
        usuario.getEmpleado().setDocumento(usuario.getDocumento());
        Empleado empleado=usuario.getEmpleado();
        usuario.setEmpleado(null);
        Usuario newUsuario=usuarioRepository.save(usuario);
        empleado=empleadoRepository.save(empleado);
        newUsuario.setEmpleado(empleado);
        usuario.getAutenticacionUsuarios().setPassword(utils.encriptarPassword(usuario.getAutenticacionUsuarios().getPassword()));
        autenticacionUsuarioRepository.save(usuario.getAutenticacionUsuarios());
        return newUsuario;
    }
    public Maquina crearMaquina(Maquina maquina)
    {
        return maquinaRepository.save(maquina);
    }
    public Tarifa crearTarifa(Tarifa tarifa)
    {
        tarifa.setIdTarifa(null);
        return tarifaRepository.save(tarifa);
    }

    public ResponseEntity<?> actualizarTarifa(Tarifa tarifa, Short idTarifa)
    {
        Optional<Tarifa> tarifaOptional = tarifaRepository.findById(idTarifa);

        if (!tarifaOptional.isPresent())
        {
            tarifa.setIdTarifa(idTarifa);
            return new ResponseEntity(tarifa, HttpStatus.NOT_FOUND);
        }
        tarifa.setIdTarifa(idTarifa);
        return new ResponseEntity<>(tarifaRepository.save(tarifa),HttpStatus.OK);
    }
    public List<TipoEmpleado> darTiposEmpleado()
    {
        return tipoEmpleadoRepository.findAll();
    }

    public ResponseEntity cambiarEstadoMaquina(Integer idMaquina, Short idEstadoMaquina) {
        Maquina maquina=maquinaRepository.findById(idMaquina).orElseThrow(()-> new RecursoNoEncontradoException("Maquina","idMaquina",idMaquina));
        EstadosMaquina estadoMaquina= estadosMaquinaRepository.findById(idEstadoMaquina).orElseThrow(()-> new RecursoNoEncontradoException("EstadosMaquina","idEstadoMaquina",idEstadoMaquina));
        maquina.setEstadosMaquina(estadoMaquina);
        return new ResponseEntity(maquinaRepository.save(maquina),HttpStatus.OK);
    }
}
