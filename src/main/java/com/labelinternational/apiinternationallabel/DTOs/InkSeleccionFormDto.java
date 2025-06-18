package com.labelinternational.apiinternationallabel.DTOs;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class InkSeleccionFormDto {
    private Long id;
    private String provider;
    private String typeMateria;
    private BigDecimal volumenRemaiming;
}
