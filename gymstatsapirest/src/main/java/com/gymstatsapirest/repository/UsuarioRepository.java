package com.gymstatsapirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gymstatsapirest.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>
{

}
