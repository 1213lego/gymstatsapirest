package com.gymstatsapirest.repository;

import com.gymstatsapirest.model.TipoUsuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gymstatsapirest.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>
{
    boolean existsByDocumento(Integer documento);
    @Query("SELECT u FROM Usuario  u WHERE u.tipoUsuario= :tipoUsuario")
    Page<Usuario> listarUsuarios(Pageable pageable, @Param("tipoUsuario") TipoUsuario tipoUsuario);

    List<Usuario> findAllByTipoUsuario(TipoUsuario tipoUsuario);
}
