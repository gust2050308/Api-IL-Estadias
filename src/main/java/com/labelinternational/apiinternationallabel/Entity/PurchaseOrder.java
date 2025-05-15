package com.labelinternational.apiinternationallabel.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = false)
    private Long id_PurchaseOrder;

    @Column(unique=true)
    private Long purchaseOrderNumber;

    @ManyToOne  // Cambiada de @OneToOne a @ManyToOne
    @JoinColumn(nullable = false)
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

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("purchaseOrder")
    private List<ItemOrder> items = new ArrayList<>();

}
