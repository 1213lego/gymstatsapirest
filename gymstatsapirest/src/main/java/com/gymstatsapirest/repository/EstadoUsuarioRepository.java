package com.gymstatsapirest.repository;

import com.gymstatsapirest.model.EstadosUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoUsuarioRepository extends JpaRepository<EstadosUsuario,Short>
{
    EstadosUsuario findByEstadoCliente(String estadoCliente);
}
