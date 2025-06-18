package com.labelinternational.apiinternationallabel.Mappers;

import com.labelinternational.apiinternationallabel.DTOs.Entry.InkDto;
import com.labelinternational.apiinternationallabel.Entity.Ink;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InkMapper {

    Ink toEntity (InkDto inkDto);

    InkDto toDto (Ink ink);

    List<InkDto> toDtoList (List<Ink> inks);
}
