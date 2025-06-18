package com.labelinternational.apiinternationallabel.Mappers;

import com.labelinternational.apiinternationallabel.DTOs.PurchaseOreder.PaperItemOrderDto;
import com.labelinternational.apiinternationallabel.Entity.PaperItemOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaperItemOrderMapper {

    @Mapping(target = "idPaperItemOrder")
    PaperItemOrderDto toItemDto(PaperItemOrder entity);
}
