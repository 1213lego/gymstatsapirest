package com.gymstatsapirest.repository;

import com.gymstatsapirest.model.AutenticacionUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface AutenticacionUsuarioRepository extends JpaRepository<AutenticacionUsuario,String> {
    boolean existsByUsername(String username);
    Optional<AutenticacionUsuario> findByUsername(String username);
}
