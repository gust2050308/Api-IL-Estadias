package com.labelinternational.apiinternationallabel.DTOs.Entry;

import com.labelinternational.apiinternationallabel.DTOs.InInkDto;
import com.labelinternational.apiinternationallabel.Entity.InInk;
import lombok.Data;

@Data
public class InkDto {
    private Long idInk;
    private InInkDto inInk;
    private Long totalKilograms;
    private Long volumeUsed = 0L;
    private Long remainingVolume ;
}
