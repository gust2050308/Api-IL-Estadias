package com.labelinternational.apiinternationallabel.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    public ExistenceDto(Long idInInk, String code, String internalBatch, String provider,
                        BigDecimal remainingKilograms, BigDecimal totalKilograms, String typeOfMaterial,
                        BigDecimal usedKilograms, String batchProvider, Long id) {
        this.idInInk = idInInk;
        this.code = code;
        this.internalBatch = internalBatch;
        this.provider = provider;
        this.remainingKilograms = remainingKilograms;
        this.totalKilograms = totalKilograms;
        this.typeOfMaterial = typeOfMaterial;
        this.usedKilograms = usedKilograms;
        this.batchProvider = batchProvider;
        this.id = id;
    }

}
