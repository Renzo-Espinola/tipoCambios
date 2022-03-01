package com.tiposCambio.cambio.controller;

import com.tiposCambio.cambio.entity.CambioEntity;
import com.tiposCambio.cambio.exceptions.UserNotFoundException;
import com.tiposCambio.cambio.model.Cambio;
import com.tiposCambio.cambio.service.ICambioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cambio")
public class CambioRestController {
    private static final Logger logger = LoggerFactory.getLogger(CambioRestController.class);

    @Autowired
    private ICambioService iCambioService;

    @PostMapping()
    public ResponseEntity<Cambio> ejecutarCambio(@RequestBody Cambio cambio){
        Cambio cambioEntityDb=iCambioService.save(cambio);
        logger.info("Nuevo Cambio registrado");
        return ResponseEntity.status(HttpStatus.CREATED).body(cambioEntityDb);
    }

    @GetMapping("/historial")
    public Iterable<CambioEntity> findAll(){
        List<CambioEntity> listCambio= new ArrayList<>();
        iCambioService.findAll().forEach(listCambio::add);
        return listCambio;
    }
    @GetMapping("/buscarCambio/{id}")
    public ResponseEntity<CambioEntity> findbyId(@PathVariable Long id){
        Optional<CambioEntity> cambioEntity = iCambioService.findBy(id);
        if(!cambioEntity.isPresent()) {
            logger.info("id No Encontrado");
            throw new UserNotFoundException("id: " + id);
        }
        return  ResponseEntity.ok(cambioEntity.get());
    }


    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<CambioEntity> deleteById(@PathVariable Long id){
        Optional<CambioEntity> cambioEntity = iCambioService.findBy(id);
        if(!cambioEntity.isPresent()) {
            logger.info("id No Encontrado");
            throw new UserNotFoundException("id: " + id);
        }else{
            iCambioService.deleteBy(id);
            return  ResponseEntity.ok(cambioEntity.get());
        }
    }


}
