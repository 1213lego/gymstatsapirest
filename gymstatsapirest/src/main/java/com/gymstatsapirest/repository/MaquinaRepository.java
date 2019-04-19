package com.gymstatsapirest.repository;

import com.gymstatsapirest.model.Maquina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaquinaRepository extends JpaRepository<Maquina,Integer> {
}
