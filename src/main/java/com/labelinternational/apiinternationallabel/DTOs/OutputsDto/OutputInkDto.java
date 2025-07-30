package com.labelinternational.apiinternationallabel.DTOs.OutputsDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

@Data
@NoArgsConstructor
public class OutputInkDto {
    private Long idOutputInk;

    private Instant date;

    private Long production;

    private Long idInk;

    private String typeMaterial;

    private String internalBatch;

    private BigDecimal kilogramsRequired;

    private BigDecimal kilogramsDelivered;

    private String whoDelivers;

    private String whoReceives;

    private BigDecimal ReturnedKilogramsRequired;

    public OutputInkDto(Long idOutputInk, Instant  date, Long production, Long idInk, String typeMaterial, String internalBatch, BigDecimal kilogramsRequired, BigDecimal kilogramsDelivered, String whoDelivers, String whoReceives, BigDecimal returnedKilogramsRequired) {
        this.idOutputInk = idOutputInk;
        this.date = date;
        this.production = production;
        this.idInk = idInk;
        this.typeMaterial = typeMaterial;
        this.internalBatch = internalBatch;
        this.kilogramsRequired = kilogramsRequired;
        this.kilogramsDelivered = kilogramsDelivered;
        this.whoDelivers = whoDelivers;
        this.whoReceives = whoReceives;
        this.ReturnedKilogramsRequired = returnedKilogramsRequired;
    }
}
