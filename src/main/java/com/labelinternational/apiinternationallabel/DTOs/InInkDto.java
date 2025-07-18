package com.labelinternational.apiinternationallabel.DTOs;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InInkDto {
    private Long idInInk;
    private LocalDateTime dateEntry;
    private String invoiceRemission;
    private String typeMaterial;
    private String batchProvider;
    private String internalBatch;
    private Integer unitsArrived;
    private String qualityCertificate;
    private Long idItemOrder;
}
