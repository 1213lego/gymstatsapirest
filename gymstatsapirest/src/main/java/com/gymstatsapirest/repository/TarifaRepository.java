package com.gymstatsapirest.repository;

import com.gymstatsapirest.model.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarifaRepository extends JpaRepository<Tarifa,Short> {
}
