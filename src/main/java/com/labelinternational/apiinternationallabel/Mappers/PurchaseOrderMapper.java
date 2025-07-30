package com.labelinternational.apiinternationallabel.Mappers;

import com.labelinternational.apiinternationallabel.DTOs.PurchaseOrder.PurchaseOrderResponseDto;
import com.labelinternational.apiinternationallabel.Entity.PurchaseOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PurchaseOrderMapper {
    @Mapping(source = "provider.providerName", target = "providerName")
    PurchaseOrderResponseDto toResponseDto(PurchaseOrder order);

    @Mapping(source = "provider.providerName", target = "providerName")
    List<PurchaseOrderResponseDto> toResponseDtoList(List<PurchaseOrder> purchaseOrders);

}