package com.labelinternational.apiinternationallabel.DTOs.PurchaseOrder;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Data
@NoArgsConstructor
public class InkItemOrderDto {
    private Long idItemOrder;
    private BigDecimal unitsQuantity;
    private BigDecimal amountKilograms;
    private String codeItem;
    private BigDecimal totalUnitsQuantityArrived;
    private Boolean isSatisfied;

    public InkItemOrderDto(Long idItemOrder, BigDecimal unitsQuantity, BigDecimal amountKilograms, String codeItem, BigDecimal totalUnitsQuantityArrived, Boolean isSatisfied) {
        this.idItemOrder = idItemOrder;
        this.unitsQuantity = unitsQuantity;
        this.amountKilograms = amountKilograms;
        this.codeItem = codeItem;
        this.totalUnitsQuantityArrived = totalUnitsQuantityArrived;
        this.isSatisfied = isSatisfied;
    }
}