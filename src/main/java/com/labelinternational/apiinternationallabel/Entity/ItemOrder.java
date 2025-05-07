package com.labelinternational.apiinternationallabel.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class ItemOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Long id_ItemOrder;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private String CodeItem;

    @Column(nullable = false)
    private String Item;

    /*
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "orden_id", nullable = false)
    @JsonIgnoreProperties("items")
    private PurchaseOrder purchaseOrder;*/

    @Column(nullable = false)
    private Long purchaseOrderID;

    public Long getPurchaseOrderID() {
        return purchaseOrderID;
    }

    public void setPurchaseOrderID(Long purchaseOrderID) {
        this.purchaseOrderID = purchaseOrderID;
    }
    /*
    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder ordenCompra) {
        this.purchaseOrder = ordenCompra;
    }*/

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public void setCodeItem(String codeItem) {
        CodeItem = codeItem;
    }

    public void setItem(String item) {
        Item = item;
    }


    public Long getAmount() {
        return amount;
    }

    public String getCodeItem() {
        return CodeItem;
    }

    public String getItem() {
        return Item;
    }

    public void setId_ItemOrder(Long idItemOrder) {
        this.id_ItemOrder = idItemOrder;
    }

    public Long getId_ItemOrder() {
        return id_ItemOrder;
    }
}
