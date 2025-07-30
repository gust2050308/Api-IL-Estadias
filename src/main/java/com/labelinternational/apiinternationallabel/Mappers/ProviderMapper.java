package com.labelinternational.apiinternationallabel.Mappers;

import com.labelinternational.apiinternationallabel.DTOs.PurchaseOrder.ProviderDto;
import com.labelinternational.apiinternationallabel.Entity.Provider;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProviderMapper {
    Provider toEntity(ProviderDto providerDto);

    ProviderDto toDto(Provider provider);


}
