package com.labelinternational.apiinternationallabel.Entity;

import com.labelinternational.apiinternationallabel.Entity.Enums.QualityCertificate;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Setter
@Getter
public class InInk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idInInk;

    @Column(nullable = false)
    private Date dateEntry;

    @Column(nullable = false)
    private String invoiceRemission;// INVOICE/REMISSION - FACTURA/REMISIÓN

    @Column(nullable = false)
    private String typeMaterial;

    @Column(nullable = false)
    private String batchProvider;

    @Column(nullable = false)
    private String internalBatch;

    @Column(nullable = false)
    private Long unitsArrived;

    @Column(nullable = false)
    private QualityCertificate qualityCertificate;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ink_item_order_id", nullable = false)
    private InkItemOrder itemOrder;

    @OneToOne(mappedBy = "inInk", cascade = CascadeType.ALL)
    private Ink ink;

}
