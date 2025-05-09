package com.labelinternational.apiinternationallabel.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class ItemOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id_ItemOrder;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private String codeItem;

    @Column(nullable = false)
    private String item;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "purchase_order_id", nullable = false)
    @JsonIgnoreProperties("items")
    private PurchaseOrder purchaseOrder;

    // Getters y Setters
    public Long getId_ItemOrder() {
        return id_ItemOrder;
    }

    public void setId_ItemOrder(Long id_ItemOrder) {
        this.id_ItemOrder = id_ItemOrder;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCodeItem() {
        return codeItem;
    }

    public void setCodeItem(String codeItem) {
        this.codeItem = codeItem;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }
}
