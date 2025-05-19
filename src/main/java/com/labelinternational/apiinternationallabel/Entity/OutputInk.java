package com.labelinternational.apiinternationallabel.Entity;

import com.labelinternational.apiinternationallabel.Entity.Ink;
import com.labelinternational.apiinternationallabel.Entity.Production;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OutputInk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOutputInk;

    @Column(nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "idProduction", nullable = false)
    private Production production;

    @ManyToOne
    @JoinColumn(name = "idInk", nullable = false)
    private Ink ink;

    @Column(nullable = false)
    private Long kilogramsRequired;

    @Column(nullable = false)
    private Long kilogramsDelivered;

    @Column(nullable = false)
    private String whoDelivers;

    @Column(nullable = false)
    private String whoReceives;
}
