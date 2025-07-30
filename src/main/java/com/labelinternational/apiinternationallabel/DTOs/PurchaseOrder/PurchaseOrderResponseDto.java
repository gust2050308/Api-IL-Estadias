package com.labelinternational.apiinternationallabel.DTOs.PurchaseOrder;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Data
@Setter
@Getter
public class PurchaseOrderResponseDto {
    private Long id_PurchaseOrder;
    private Long purchaseOrderNumber;
    private String providerName;
    private Instant requestDate;
    private Instant deliveryDateExpected;
    private Instant dateDelivered;
    private String requiredBy;
    private String paymentMethod;
    private String shipment;
    private String deliveryPlace;
    private Boolean isComplete;
    private Boolean typeMaterial;
    private Long totalItems;
    private Long itemsArrived;
}
