package com.tiposCambio.cambio.repository;

import com.tiposCambio.cambio.entity.CambioEntity;
import com.tiposCambio.cambio.model.Cambio;
import org.springframework.data.repository.CrudRepository;

public interface CambioRepository extends CrudRepository<CambioEntity,Long> {}

