package com.labelinternational.apiinternationallabel.Mappers;

import com.labelinternational.apiinternationallabel.DTOs.PurchaseOreder.PurchaseOrderResponseDto;
import com.labelinternational.apiinternationallabel.Entity.PurchaseOrder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PurchaseOrderMapper {

    PurchaseOrderResponseDto toResponseDto(PurchaseOrder order);

    List<PurchaseOrderResponseDto> toResponseDtoList(List<PurchaseOrder> purchaseOrders);

}