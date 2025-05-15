package com.labelinternational.apiinternationallabel.Entity;

import com.labelinternational.apiinternationallabel.Entity.Enums.QualityCertificate;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class InInk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id_InInk;

    @Column(nullable = false)
    private Date dateEntry;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Provider provider;

    @Column(nullable = false)
    private String invoiceRemission;// INVOICE/REMISSION - FACTURA/REMISIÓN

    @ManyToOne
    @JoinColumn(nullable = false)
    private PurchaseOrder purchaseOrder;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private Long units;

    @Column(nullable = false)
    private Long quantityKilograms;

    @Column(nullable = false)
    private String batchProvider;

    @Column(nullable = false)
    private String internalBatch;

    @Column(nullable = false)
    private QualityCertificate qualityCertificate;

    public void setId_Ink(Long id_InInk) {
        this.id_InInk = id_InInk;
    }

    public Long getId_Ink() {
        return id_InInk;
    }

    public Long getQuantity() {
        return quantityKilograms;
    }

    @Version
    private int version;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
