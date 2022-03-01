package com.tiposCambio.cambio.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Cambio")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CambioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(hidden = true)
    private Long id;
    @Column
    private String nombreMoneda1;
    @Column
    private String nombreMoneda2;
    @Column
    private String montoMoneda1;
    @Column
    @ApiModelProperty(hidden = true)
    private String montoMoneda2;
    @Column
    private Date fechaConsulta;
}