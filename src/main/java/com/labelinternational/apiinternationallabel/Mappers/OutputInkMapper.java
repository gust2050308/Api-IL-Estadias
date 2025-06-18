package com.labelinternational.apiinternationallabel.Mappers;

import com.labelinternational.apiinternationallabel.DTOs.OutputInkDto;
import com.labelinternational.apiinternationallabel.Entity.OutputInk;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OutputInkMapper {

    @Mapping(source = "ink.idInk", target = "idInk")
    @Mapping(source = "ink.inInk.typeMaterial", target = "typeMaterial")
    @Mapping(source = "ink.inInk.internalBatch", target = "internalBatch")
    OutputInkDto toDto(OutputInk outputInk);

    List<OutputInkDto> toResponseInks(List<OutputInk> outputInks);
}

