package com.tiposCambio.cambio.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cambio {
    @ApiModelProperty(hidden = true)
    private Long id;
    private String nombreMoneda1;
    private String nombreMoneda2;
    private String montoMoneda1;
    @ApiModelProperty(hidden = true)
    private String montoMoneda2;
    private Date fechaConsulta;
}
