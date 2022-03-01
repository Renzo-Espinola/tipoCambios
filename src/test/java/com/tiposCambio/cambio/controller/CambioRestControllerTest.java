package com.tiposCambio.cambio.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiposCambio.cambio.entity.CambioEntity;
import com.tiposCambio.cambio.model.Cambio;
import com.tiposCambio.cambio.repository.CambioRepository;
import com.tiposCambio.cambio.service.CambioServiceImpl;
import com.tiposCambio.cambio.service.ICambioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CambioRestController.class)
class CambioRestControllerTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CambioServiceImpl cambioServiceImpl;
    @MockBean
    private CambioRepository ICambioRepository;

    CambioEntity cambioEntity, cambioEntity2 = new CambioEntity();
    Cambio cambio= new Cambio();
    private ArrayList<CambioEntity> cambioList;
    @BeforeEach
    void setUp() {
        this.cambioList =  new ArrayList<>();
        Date fechaConsulta=new Date(1987,8,12);
        cambio = new Cambio(1L,"ARS","EUR","100","0.83",fechaConsulta);
        cambioEntity = new CambioEntity(1L,"ARS","EUR","100","0.83",fechaConsulta);
        cambioEntity2 = new CambioEntity(2L,"ARS","EUR","100","0.83",fechaConsulta);
        cambioList.add(cambioEntity);
        cambioList.add(cambioEntity2);
    }
    @Test
    void save_cambio_iscreated_test () throws Exception{
        given(cambioServiceImpl.save(cambio)).willAnswer((invocation)->invocation.getArguments());
        this.mockMvc.perform(post("/cambio")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cambio)))
                .andExpect(status().isCreated());
    }
    @Test
    void save_cambio_not_found_test () throws Exception{
        given(cambioServiceImpl.save(cambio)).willAnswer((invocation)->invocation.getArguments());
        this.mockMvc.perform(post("/cambio/v1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cambioEntity2)))
                .andExpect(status().isNotFound());
    }
   @Test
    void find_all_cambios_test () throws Exception {
        given(cambioServiceImpl.findAll()).willReturn(this.cambioList);
        this.mockMvc.perform(get("/cambio/historial")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(cambioList.size())));
    }
    @Test
    void find_all_not_found_cambios_test () throws Exception {
        given(cambioServiceImpl.findAll()).willReturn(this.cambioList);
        this.mockMvc.perform(get("/cambio/historial/v1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }
    @Test
    void find_by_id_cambio_isok_test() throws Exception {
        Long idCambio = 1L;
        Optional<CambioEntity>oCambioEntity=Optional.of(cambioEntity);
        given(cambioServiceImpl.findBy(idCambio)).willReturn(oCambioEntity);
        this.mockMvc.perform(get("/cambio/buscarCambio/{id}",idCambio))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreMoneda1", is(cambioEntity.getNombreMoneda1())));
    }
    @Test
    void find_by_id_cambio_is_not_found_test() throws Exception {
        Long idCambio = 1L;
        Optional<CambioEntity>oCambioEntity=Optional.of(cambioEntity);
        given(cambioServiceImpl.findBy(idCambio)).willReturn(oCambioEntity);
        this.mockMvc.perform(get("/cambio/buscarCambio/v1/{id}",idCambio))
                .andExpect(status().isNotFound());

    }
    @Test
    void delete_by_id_cambio_isok_test() throws Exception {
        Long idCambio = 1L;
        Optional<CambioEntity>oCambioEntity=Optional.of(cambioEntity);
        given(cambioServiceImpl.findBy(idCambio)).willReturn(oCambioEntity);
        doNothing().when(cambioServiceImpl).deleteBy(cambioEntity.getId());

        this.mockMvc.perform(delete("/cambio/borrar/{id}", cambioEntity.getId()))
                .andExpect(status().isOk());
    }
    @Test
    void delete_by_id_cambio_is_not_found_test() throws Exception {
        Long idCambio = 1L;
        Optional<CambioEntity>oCambioEntity=Optional.of(cambioEntity);
        given(cambioServiceImpl.findBy(idCambio)).willReturn(oCambioEntity);
        doNothing().when(cambioServiceImpl).deleteBy(cambioEntity.getId());

        this.mockMvc.perform(delete("/cambio/borrar/v1/{id}", cambioEntity.getId()))
                .andExpect(status().isNotFound());
    }
}
