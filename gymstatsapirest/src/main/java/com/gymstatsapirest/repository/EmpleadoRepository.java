package com.gymstatsapirest.repository;

import com.gymstatsapirest.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado,Integer>
{
}
