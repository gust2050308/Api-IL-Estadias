package com.labelinternational.apiinternationallabel.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class PurchaseOrder {


    @Id
    private Long id_PurchaseOrder;

    @Column(unique=true)
    private String purchaseOrderNumber;

    @OneToOne
    @JoinColumn(nullable=false)
    private Provider provider;

    @Column(nullable=false)
    private Date purchaseDate;

    @Column(nullable=false)
    private String requiredBy;

    @Column(nullable=false)
    private String paymentMethod;

    @Column(nullable=false)
    private Date deliveryDate;

    @Column(nullable=false)
    private String shipment;

    @Column(nullable=false)
    private String deliveryPlace;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<ItemOrder> items;

    public void setId_PurchaseOrder(Long idPurchaseOrder) {
        this.id_PurchaseOrder = idPurchaseOrder;
    }

    public Long getId_PurchaseOrder() {
        return id_PurchaseOrder;
    }

    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public Provider getProvider() {
        return provider;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public String getRequiredBy() {
        return requiredBy;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public String getShipment() {
        return shipment;
    }

    public String getDeliveryPlace() {
        return deliveryPlace;
    }

    public List<ItemOrder> getItems() {
        return items;
    }
}
