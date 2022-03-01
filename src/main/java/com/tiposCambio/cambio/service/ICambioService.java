package com.tiposCambio.cambio.service;

import com.tiposCambio.cambio.entity.CambioEntity;
import com.tiposCambio.cambio.model.Cambio;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface ICambioService {
    Iterable<CambioEntity>findAll();
    Cambio save (Cambio clientes);
    Optional<CambioEntity> findBy(Long id);
    void deleteBy (Long id);
}
