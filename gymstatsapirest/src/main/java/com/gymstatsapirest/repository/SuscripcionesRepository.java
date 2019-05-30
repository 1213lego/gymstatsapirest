package com.gymstatsapirest.repository;

import com.gymstatsapirest.model.Cliente;
import com.gymstatsapirest.model.EstadoSuscripcion;
import com.gymstatsapirest.model.Suscripcione;
import com.gymstatsapirest.model.Tarifa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface SuscripcionesRepository extends JpaRepository<Suscripcione,Long>
{
    @Query("SELECT s FROM Suscripcione s WHERE s.cliente = :cliente and s.estadoSuscripcion = :estadoSuscripcion")
    Suscripcione existeSuscripcionActiva(@Param("cliente") Cliente cliente, @Param("estadoSuscripcion") EstadoSuscripcion estadoSuscripcion);

    Page<Suscripcione> findAllByCliente(Pageable pageable,Cliente cliente);

    //Cambia las suscripciones que esten expiradas segun la fecha actual dada por parametro al estado dado por parametro
    @Transactional
    @Modifying
    @Query("UPDATE Suscripcione s SET s.estadoSuscripcion = :estadoExpirada  WHERE s.fechaFin <= :currentDate AND s.estadoSuscripcion != :estadoExpirada")
    int actualizarSuscripcionesExpiradas(@Param("currentDate") Date currentDate,@Param("estadoExpirada") EstadoSuscripcion estadoExpirada);

    @Query("SELECT s.cliente.usuario.email FROM Suscripcione s where s.estadoSuscripcion = :suscripcionVigente AND  s.tarifa != :tarifaDiaria AND s.fechaFin> :marginDate")
    List<String> darSuscripcionesPorExpirar(@Param("tarifaDiaria")Tarifa tarifaDiaria,
                                                  @Param("suscripcionVigente")EstadoSuscripcion suscripcionVigente,
                                                  @Param("marginDate") Date marginDate
    );

}
