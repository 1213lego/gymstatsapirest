package com.gymstatsapirest.repository;

import com.gymstatsapirest.model.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento,Short>
{
    TipoDocumento findByNombreDocumento(String nombreDocumento);
}
