package com.labelinternational.apiinternationallabel.DTOs.ProviderDtos;

import com.labelinternational.apiinternationallabel.Entity.Enums.ProviderType;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ProviderFormDto {
    private Long idProvider;
    private String ProviderName;
}
