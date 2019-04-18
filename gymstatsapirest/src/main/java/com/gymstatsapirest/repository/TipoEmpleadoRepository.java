package com.gymstatsapirest.repository;

import com.gymstatsapirest.model.TipoEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoEmpleadoRepository extends JpaRepository<TipoEmpleado,Short> {
}
