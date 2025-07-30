package com.labelinternational.apiinternationallabel.DTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
public class SumariseStockInk {

    Long inksTotal;
    BigDecimal totalRemaining;
    List<OrderDesInkDto> orderDesInkDtoList;

}
