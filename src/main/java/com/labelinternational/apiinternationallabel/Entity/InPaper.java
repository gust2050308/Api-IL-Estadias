package com.labelinternational.apiinternationallabel.Entity;


import com.labelinternational.apiinternationallabel.Entity.Enums.QualityCertificate;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class InPaper {

    @Id
    @GeneratedValue
    private Long id_inPaper;

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
    private String typeOfMeasures;

    @Column(nullable = false)
    private Long weightPaper;

    @Column(nullable = false)
    private Long largePaper;

    @Column(nullable = false)
    private String batchProvider;

    @Column(nullable = false)
    private String internalBatch;

    @Column(nullable = false)
    private QualityCertificate qualityCertificate;

    public void setId_inPaper(Long idInPaper) {
        this.id_inPaper = idInPaper;
    }

    public Long getId_inPaper() {
        return id_inPaper;
    }

    public Date getDate() {
        return dateEntry;
    }

    public String getInvoiceRemission() {
        return invoiceRemission;
    }

    public Provider getProvider() {
        return provider;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public String getType() {
        return type;
    }

    public String getCode() {
        return code;
    }

    public Long getUnits() {
        return units;
    }

    public String getTypeOfMeasures() {
        return typeOfMeasures;
    }

    public Long getWeightPaper() {
        return weightPaper;
    }

    public Long getLargePaper() {
        return largePaper;
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
