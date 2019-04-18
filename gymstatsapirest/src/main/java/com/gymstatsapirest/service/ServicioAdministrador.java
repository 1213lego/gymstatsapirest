package com.gymstatsapirest.service;
import com.gymstatsapirest.model.Empleado;
import com.gymstatsapirest.model.Tarifa;
import com.gymstatsapirest.model.Usuario;
import com.gymstatsapirest.repository.*;
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
    @Autowired
    private TipoEmpleadoRepository tipoEmpleadoRepository;
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private TarifaRepository tarifaRepository;

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
        autenticacionUsuarioRepository.save(usuario.getAutenticacionUsuarios());
        return newUsuario;
    }

    public Tarifa crearTarifa(Tarifa tarifa)
    {
        return tarifaRepository.save(tarifa);
    }
}
