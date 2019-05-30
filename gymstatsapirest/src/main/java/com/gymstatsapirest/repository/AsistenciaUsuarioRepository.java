package com.gymstatsapirest.repository;

import com.gymstatsapirest.model.AsistenciasUsuario;
import com.gymstatsapirest.model.TipoUsuario;
import com.gymstatsapirest.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AsistenciaUsuarioRepository extends JpaRepository<AsistenciasUsuario,Long>
{
    @Query("SELECT b FROM AsistenciasUsuario b " +
            "WHERE EXTRACT (day FROM b.fechaIngreso) = :dayOfMonth AND EXTRACT (month FROM b.fechaIngreso) = :month AND  EXTRACT (year FROM b.fechaIngreso) = :year AND  b.usuario= :usuario")
    List<AsistenciasUsuario> asistenciaPorUsuarioyFecha(@Param("dayOfMonth") Integer dayOfMonth, @Param("month") Integer month, @Param("year") Integer year,@Param("usuario") Usuario usuario);

    Page<AsistenciasUsuario> findAllByUsuarioTipoUsuario(TipoUsuario usuario, Pageable pageable);
}
