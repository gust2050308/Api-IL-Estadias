package com.labelinternational.apiinternationallabel.DTOs;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ExistenceDto {
    private Long id;
    private Long idInInk;
    private String provider;
    private String batchProvider;
    private String internalBatch;
    private String typeOfMaterial;
    private String code;
    private BigDecimal totalKilograms;
    private BigDecimal remainingKilograms;
    private BigDecimal usedKilograms;
}
