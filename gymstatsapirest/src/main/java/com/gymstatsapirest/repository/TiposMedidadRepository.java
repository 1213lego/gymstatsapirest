package com.gymstatsapirest.repository;

import com.gymstatsapirest.model.TiposMedida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TiposMedidadRepository extends JpaRepository<TiposMedida,Short> {
}
