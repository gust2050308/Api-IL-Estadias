package com.labelinternational.apiinternationallabel.DTOs;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Setter
@Getter
public class DevolutionInkDto {

    private Long idOutputInk;
    private BigDecimal devolutionQuantity;

}
