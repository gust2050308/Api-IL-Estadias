package com.labelinternational.apiinternationallabel.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class InkItemOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idItemOrder;

    @Column(nullable = false)
    private Long unitsQuantity;

    @Column(nullable = false)
    private Long amountKilograms;

    @Column(nullable = false)
    private String codeItem;

    @Column()
    private Long totalUnitsQuantityArrived = 0L;

    @Column(nullable = false)
    private Boolean isSatisfied = false;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "purchase_order_id", nullable = false)
    @JsonIgnoreProperties("items")
    private PurchaseOrder purchaseOrder;

    @JsonIgnoreProperties
    @OneToMany(mappedBy = "itemOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InInk> inInks = new ArrayList<>();
}
