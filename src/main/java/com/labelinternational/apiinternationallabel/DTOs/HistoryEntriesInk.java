package com.labelinternational.apiinternationallabel.DTOs;

import com.labelinternational.apiinternationallabel.Entity.Enums.QualityCertificate;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class HistoryEntriesInk {
    private Long id;
    private Date date;
    private String provider;
    private String invoiceRemission;
    private Long orderNumber;
    private String typeMaterial;
    private String code;
    private BigDecimal units;
    private BigDecimal quantityKilograms;
    private String batchProvider;
    private String internalBatch;
    private QualityCertificate qualityCertificate;
}
