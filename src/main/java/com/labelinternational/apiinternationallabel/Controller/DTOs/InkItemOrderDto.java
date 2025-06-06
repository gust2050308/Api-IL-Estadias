package com.labelinternational.apiinternationallabel.Controller.DTOs;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Data
public class InkItemOrderDto {
    private Long idItemOrder;
    private Integer unitsQuantity;
    private Integer amountKilograms;
    private String codeItem;
    private Long totalUnitsQuantityArrived;
    private Boolean isSatisfied;
    private List<InInkDto> inInks;
}
