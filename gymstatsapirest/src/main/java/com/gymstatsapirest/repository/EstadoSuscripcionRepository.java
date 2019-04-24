package com.gymstatsapirest.repository;

import com.gymstatsapirest.model.EstadoSuscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoSuscripcionRepository extends JpaRepository<EstadoSuscripcion,Short>
{
    EstadoSuscripcion findByEstadoSuscripcion(String estadoSuscripcion);
}
