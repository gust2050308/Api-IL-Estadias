package com.labelinternational.apiinternationallabel.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class PaperItemOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paperItemOrder_seq")
    @SequenceGenerator(
            name = "paperItemOrder_seq",
            sequenceName = "paperItemOrder_sequence",
            initialValue = 1000,
            allocationSize = 7)
    @Column(unique = true, nullable = false)
    private Long idPaperItemOrder;

    @Column(nullable = false)
    private Long unitsQuantity;

    @Column(nullable = false)
    private String codeItem;

    @Column(nullable = false)
    private Long paperWidth;

    @Column(nullable = false)
    private Long paperlength;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "purchase_order_id", nullable = false)
    @JsonIgnoreProperties("items")
    private PurchaseOrder purchaseOrder;
}
