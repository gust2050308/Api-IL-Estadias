package com.labelinternational.apiinternationallabel.Controller.Mappers;

import org.mapstruct.Mapping;
import com.labelinternational.apiinternationallabel.Controller.DTOs.*;
import com.labelinternational.apiinternationallabel.Entity.*;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PurchaseOrderMapper {

    PurchaseOrderDto toDto(PurchaseOrder purchaseOrder);

    @Mapping(target = "purchaseOrder", ignore = true) // Para evitar ciclo
    InkItemOrderDto toInkItemOrderDto(InkItemOrder item);

    @Mapping(target = "purchaseOrder", ignore = true)
    PaperItemOrderDto toPaperItemOrderDto(PaperItemOrder item);

    ProviderDto toProviderDto(Provider provider);

    InInkDto toInInkDto(InInk inInk);
}
