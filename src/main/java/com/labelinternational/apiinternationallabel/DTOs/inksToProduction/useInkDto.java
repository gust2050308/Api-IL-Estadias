package com.labelinternational.apiinternationallabel.DTOs.inksToProduction;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@Data
@Builder
public class useInkDto {
    private Long production;
    private String whoDelivers;
    private String whoReceives;
    private List<inkUse> inks;
}

