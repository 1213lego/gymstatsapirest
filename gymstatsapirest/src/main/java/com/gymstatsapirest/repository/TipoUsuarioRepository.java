package com.gymstatsapirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gymstatsapirest.model.TipoUsuario;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario,Short>
{
    TipoUsuario findByTipo(String tipo);
}
