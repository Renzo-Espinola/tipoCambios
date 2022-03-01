package com.tiposCambio.cambio.service;

import com.tiposCambio.cambio.entity.CambioEntity;
import com.tiposCambio.cambio.exceptions.IllegalArgEx;
import com.tiposCambio.cambio.exceptions.UserNotFoundException;
import com.tiposCambio.cambio.getHttpRequest.GetHttpDataRequest;
import com.tiposCambio.cambio.model.Cambio;
import com.tiposCambio.cambio.repository.CambioRepository;
import com.tiposCambio.cambio.utilityConversion.CambioConversion;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class CambioServiceImpl implements ICambioService {

    private static  final Logger logger = LoggerFactory.getLogger(CambioServiceImpl.class);
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    CambioConversion cambioConversion;

    @Autowired
    CambioRepository cambioRepository;
    @Autowired
    GetHttpDataRequest getHttpDataRequest;


    @Override
    public Iterable<CambioEntity> findAll() {
        return cambioRepository.findAll();
    }

    @Override
    public Cambio save(Cambio cambio) {
        cambio.setMontoMoneda2(cambioConversion.responseCambio(cambio));
        CambioEntity cambioEntity= cambioRepository.save(modelMapper.map(cambio,CambioEntity.class));
        return modelMapper.map(cambioEntity,Cambio.class);
    }

    @Override
    public Optional<CambioEntity> findBy(Long id) {
        return cambioRepository.findById(id);
    }

    @Override
    public void deleteBy(Long id) {
        String message = null;
        try{
            if(id!=null){
                Optional<CambioEntity>oCambio=cambioRepository.findById(id);
                if(oCambio.isPresent()) {
                    cambioRepository.deleteById(id);
                    message = "ID ENCONTRADO";
                }else {
                    message= "ID NO ENCONTRADO";
                    throw new UserNotFoundException("id: " + id);
                }
            } else {
                message= "ID NO ENCONTRADO";
                throw new UserNotFoundException("id: " + id);
            }
        }catch (UserNotFoundException ex){
            ex.getMessage();
        }
            logger.warn(message);
    }

}
