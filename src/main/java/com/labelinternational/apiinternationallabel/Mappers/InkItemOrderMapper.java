package com.labelinternational.apiinternationallabel.Mappers;

import com.labelinternational.apiinternationallabel.DTOs.PurchaseOreder.InkItemOrderDto;
import com.labelinternational.apiinternationallabel.DTOs.InkItemOrderWithEntriesDto;
import com.labelinternational.apiinternationallabel.Entity.InkItemOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InkItemOrderMapper {

    @Mapping(target = "idItemOrder", ignore = true)
    InkItemOrderDto toItemResponseDto(InkItemOrder inkItemOrder);

    @Mapping(target = "purchaseOrder", ignore = true) // Evita setearla al crear desde DTO
    InkItemOrder toEntity(InkItemOrderDto dto);

    @Mapping(target = "idItemOrder")
    InkItemOrderWithEntriesDto toWithEntriesDto(InkItemOrder entity);
}
