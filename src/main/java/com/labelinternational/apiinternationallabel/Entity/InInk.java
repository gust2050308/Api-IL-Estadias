package com.labelinternational.apiinternationallabel.Entity;

import com.labelinternational.apiinternationallabel.Entity.Enums.QualityCertificate;
import jakarta.persistence.*;

import java.util.Date;


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
        return quantityKilograms;
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

    public void setId_InInk(Long id_InInk) {
        this.id_InInk = id_InInk;
    }

    public void setDateEntry(Date dateEntry) {
        this.dateEntry = dateEntry;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public void setInvoiceRemission(String invoiceRemission) {
        this.invoiceRemission = invoiceRemission;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setUnits(Long units) {
        this.units = units;
    }

    public void setQuantity(Long quantityKilograms) {
        this.quantityKilograms = quantityKilograms;
    }

    public void setBatchProvider(String batchProvider) {
        this.batchProvider = batchProvider;
    }

    public void setQualityCertificate(QualityCertificate qualityCertificate) {
        this.qualityCertificate = qualityCertificate;
    }

    public void setInternalBatch(String internalBatch) {
        this.internalBatch = internalBatch;
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
