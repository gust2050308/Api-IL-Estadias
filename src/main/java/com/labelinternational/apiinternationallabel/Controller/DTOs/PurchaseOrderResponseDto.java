package com.labelinternational.apiinternationallabel.Controller.DTOs;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Setter
@Getter
public class PurchaseOrderResponseDto {
    private Long id_PurchaseOrder;
    private Integer purchaseOrderNumber;
    private ProviderDto provider;
    private LocalDateTime requestDate;
    private LocalDateTime deliveryDateExpected;
    private LocalDateTime dateDelivered;
    private String requiredBy;
    private String paymentMethod;
    private String shipment;
    private String deliveryPlace;
    private Boolean isComplete;
    private Boolean typeMaterial;
    private List<InkItemOrderDto> inkItems;
}
