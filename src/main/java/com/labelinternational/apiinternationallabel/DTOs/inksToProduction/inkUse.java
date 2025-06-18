package com.labelinternational.apiinternationallabel.DTOs.inksToProduction;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Data
@Builder
public class inkUse {
    private Long id;
    private BigDecimal kilogramsRequired;
    private BigDecimal kilogramsDelivered;
}
