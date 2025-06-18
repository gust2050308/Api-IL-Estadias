package com.labelinternational.apiinternationallabel.Mappers;

import com.labelinternational.apiinternationallabel.DTOs.InInkDto;
import com.labelinternational.apiinternationallabel.Entity.InInk;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InInkMapper {
    @Mapping(target = "idItemOrder", source = "itemOrder.idItemOrder")
    InInkDto toDto(InInk inInk);
}