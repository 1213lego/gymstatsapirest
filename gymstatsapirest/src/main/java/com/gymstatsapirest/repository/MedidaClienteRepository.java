package com.gymstatsapirest.repository;

import com.gymstatsapirest.model.MedidaCliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedidaClienteRepository extends JpaRepository<MedidaCliente,Integer> {
    List<MedidaCliente> findAllByClienteDocumento(Integer documento);
}
