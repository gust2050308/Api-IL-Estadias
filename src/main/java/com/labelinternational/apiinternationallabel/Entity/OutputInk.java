package com.labelinternational.apiinternationallabel.Entity;

import com.labelinternational.apiinternationallabel.Entity.Ink;
import com.labelinternational.apiinternationallabel.Entity.Production;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OutputInk {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "outputInk_seq")
    @SequenceGenerator(
            name = "outputInk_seq",
            sequenceName = "outputInk_sequence",
            initialValue = 1000,
            allocationSize = 3)
    private Long idOutputInk;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Long production;

    @ManyToOne
    @JoinColumn(name = "idInk", nullable = false)
    private Ink ink;

    @Column(nullable = false)
    private BigDecimal kilogramsRequired;

    @Column(nullable = false)
    private BigDecimal kilogramsDelivered;

    @Column(nullable = false)
    private String whoDelivers;

    @Column(nullable = false)
    private String whoReceives;

    @Column(nullable = true)
    private BigDecimal returnedKilogramsRequired = BigDecimal.ZERO;
}
