package com.labelinternational.apiinternationallabel.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inkItemOrder_seq")
    @SequenceGenerator(
            name = "inkItemOrder_seq",
            sequenceName = "inkItemOrder_sequence",
            initialValue = 1000,
            allocationSize = 2)
    @Column(unique = true, nullable = false)
    private Long idItemOrder;

    @Column(nullable = false)
    private BigDecimal unitsQuantity;

    @Column(nullable = false)
    private BigDecimal  amountKilograms;

    @Column(nullable = false)
    private String codeItem;

    @Column(nullable = false)
    private BigDecimal totalUnitsQuantityArrived = BigDecimal.valueOf(0);

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
