package com.gymstatsapirest.controller;
import com.gymstatsapirest.model.AutenticacionUsuario;
import com.gymstatsapirest.model.Usuario;
import com.gymstatsapirest.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/pruebas")
@CrossOrigin(origins = "*")
public class RestPrueba 
{
    @Autowired
    private GeneroRepository generoRepository;
    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;
    @Autowired
    private EstadoUsuarioRepository estadoUsuarioRepository;
    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;
    @Autowired
    private AutenticacionUsuarioRepository autenticacionUsuarioRepository;
    @Autowired
    private TipoEmpleadoRepository tipoEmpleadoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @GetMapping(path = "/utilidades", produces = "application/json")
	public Map<String,List> utilidadesUsuario()
    {
        Map<String, List> map = new HashMap<>();
        map.put("genero",generoRepository.findAll());
        map.put("tipousuario",tipoUsuarioRepository.findAll());
        map.put("estadousuario",estadoUsuarioRepository.findAll());
        map.put("tipodocumento",tipoDocumentoRepository.findAll());
        map.put("tipoempleado",tipoEmpleadoRepository.findAll());
        return map;
    }
    @GetMapping(path = "/autenticacions", produces = "application/json")
    public List<AutenticacionUsuario>darAuntenticaciones()
    {
        return autenticacionUsuarioRepository.findAll();
    }
    @GetMapping(path = "/usuarios", produces = "application/json")
    public List<Usuario>darUsuarios()
    {
        return usuarioRepository.findAll();
    }


}
