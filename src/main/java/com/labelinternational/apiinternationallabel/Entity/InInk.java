package com.labelinternational.apiinternationallabel.Entity;

import com.labelinternational.apiinternationallabel.Entity.Enums.QualityCertificate;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class InInk {
    @Id
    @GeneratedValue
    private Long id_InInk;

    @Column(nullable = false)
    private Date dateEntry;

    @OneToOne
    @JoinColumn(nullable = false)
    private Provider provider;

    @Column(nullable = false)
    private String invoiceRemission;// INVOICE/REMISSION - FACTURA/REMISIÓN

    @OneToOne
    @JoinColumn(nullable = false)
    private PurchaseOrder purchaseOrder;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private Long units;

    @Column(nullable = false)
    private Long quantity;

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

    public Long getId_InInk() {
        return id_InInk;
    }

    public Date getDateEntry() {
        return dateEntry;
    }

    public Provider getProvider() {
        return provider;
    }

    public String getInvoiceRemission() {
        return invoiceRemission;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public Long getUnits() {
        return units;
    }

    public Long getQuantity() {
        return quantity;
    }

    public String getBatchProvider() {
        return batchProvider;
    }

    public String getInternalBatch() {
        return internalBatch;
    }

    public QualityCertificate getQualityCertificate() {
        return qualityCertificate;
    }
}
