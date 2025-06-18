package com.labelinternational.apiinternationallabel.DTOs;

import com.labelinternational.apiinternationallabel.DTOs.PurchaseOreder.InkItemOrderDto;
import com.labelinternational.apiinternationallabel.DTOs.PurchaseOreder.ProviderDto;
import lombok.Data;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Setter
@Getter
public class PurchaseOrderDto {
    private Long id_PurchaseOrder;
    private Long purchaseOrderNumber;
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
