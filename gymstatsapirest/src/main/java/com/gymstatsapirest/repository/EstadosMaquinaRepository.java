package com.gymstatsapirest.repository;

import com.gymstatsapirest.model.EstadosMaquina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadosMaquinaRepository extends JpaRepository<EstadosMaquina,Short> {
}
