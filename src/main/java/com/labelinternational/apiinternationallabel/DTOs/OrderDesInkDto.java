package com.labelinternational.apiinternationallabel.DTOs;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class OrderDesInkDto {
    String typeMaterial;
    BigDecimal remainingVolume;

    public void OrderDesInkDto(String typeMaterial, BigDecimal remainingVolume){
        this.typeMaterial = typeMaterial;
        this.remainingVolume = remainingVolume;
    }
}
