package com.labelinternational.apiinternationallabel.Repository.Filters;

import com.labelinternational.apiinternationallabel.DTOs.OutputsDto.OutputInkDto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public interface OutputInkRepositoryCustom {
    List<OutputInkDto> filterOutputInk(Instant minRequestedDate,
                                       Instant maxRequestedDate,
                                       Long idInk,
                                       String type,
                                       String internalBatch,
                                       BigDecimal minKgRequested,
                                       BigDecimal maxKgRequested,
                                       BigDecimal minKgDelivered,
                                       BigDecimal maxKgDelivered,
                                       String whoDelivered,
                                       String whoRecibed);
}
