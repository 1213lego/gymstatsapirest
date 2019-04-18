package com.gymstatsapirest.repository;

import com.gymstatsapirest.model.AutenticacionUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutenticacionUsuarioRepository extends JpaRepository<AutenticacionUsuario,String> {
    boolean existsByUsername(String username);
}
