package com.gymstatsapirest.repository;

import com.gymstatsapirest.model.Cliente;
import com.gymstatsapirest.model.EstadoSuscripcion;
import com.gymstatsapirest.model.Suscripcione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface SuscripcionesRepository extends JpaRepository<Suscripcione,Long>
{
    @Query("SELECT s FROM Suscripcione s WHERE s.cliente = :cliente and s.estadoSuscripcion = :estadoSuscripcion")
    Suscripcione existeSuscripcionActiva(@Param("cliente") Cliente cliente, @Param("estadoSuscripcion") EstadoSuscripcion estadoSuscripcion);
}
