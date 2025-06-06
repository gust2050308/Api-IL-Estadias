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
    @Column(nullable = false, unique = true)
    private Long id_PurchaseOrder;

    @Column(unique=true, nullable = false)
    private Long purchaseOrderNumber;

    @ManyToOne  // Cambiada de @OneToOne a @ManyToOne
    @JoinColumn(nullable = false)
    private Provider provider;

    @Column(nullable=false)
    private Date requestDate;

    @Column
    private Date deliveryDateExpected;

    @Column
    private Date dateDelivered;

    @Column(nullable=false)
    private String requiredBy;

    @Column(nullable=false)
    private String paymentMethod;

    @Column(nullable=false)
    private String shipment;

    @Column(nullable=false)
    private String deliveryPlace;

    @Column(nullable=false)
    private Boolean isComplete = false;

    @Column(nullable=false)
    private Boolean typeMaterial; // "cero = '0'  es papel, uno = '1' tinta"

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("purchaseOrder")
    private List<InkItemOrder> inkItems= new ArrayList<>();

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("purchaseOrder")
    private List<PaperItemOrder> paperItems = new ArrayList<>();
}
