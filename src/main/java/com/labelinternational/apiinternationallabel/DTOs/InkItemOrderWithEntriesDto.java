package com.labelinternational.apiinternationallabel.DTOs;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Data
public class InkItemOrderWithEntriesDto {
    private Long idItemOrder;
    private Long unitsQuantity;
    private Long amountKilograms;
    private String codeItem;
    private Long totalUnitsQuantityArrived;
    private Boolean isSatisfied;
    private List<InInkDto> inInks;
}

