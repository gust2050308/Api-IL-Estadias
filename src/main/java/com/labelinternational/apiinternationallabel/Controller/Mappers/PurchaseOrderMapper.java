package com.labelinternational.apiinternationallabel.Controller.Mappers;

import com.labelinternational.apiinternationallabel.Controller.DTOs.InInkDto;
import com.labelinternational.apiinternationallabel.Controller.DTOs.InkItemOrderDto;
import com.labelinternational.apiinternationallabel.Controller.DTOs.PurchaseOrderResponseDto;
import com.labelinternational.apiinternationallabel.Entity.InInk;
import com.labelinternational.apiinternationallabel.Entity.InkItemOrder;
import com.labelinternational.apiinternationallabel.Entity.PurchaseOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PurchaseOrderMapper {
    @Mapping(target = "inkItems", ignore = true) // Evita la serialización de items
    PurchaseOrderResponseDto toResponseDto(PurchaseOrder order);
}