package com.labelinternational.apiinternationallabel.DTOs;

import com.labelinternational.apiinternationallabel.Entity.Ink;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OutputInkDto {
    private Long idOutputInk;

    private Date date;

    private Long production;

    private Long idInk;

    private String typeMaterial;

    private String internalBatch;

    private BigDecimal kilogramsRequired;

    private BigDecimal kilogramsDelivered;

    private String whoDelivers;

    private String whoReceives;

    private BigDecimal ReturnedKilogramsRequired;
}
