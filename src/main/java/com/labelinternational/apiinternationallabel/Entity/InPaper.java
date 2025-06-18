package com.labelinternational.apiinternationallabel.Entity;
import com.labelinternational.apiinternationallabel.Entity.Enums.QualityCertificate;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class InPaper {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inPaper_seq")
    @SequenceGenerator(
            name = "inPaper_seq",
            sequenceName = "inPaper_sequence",
            initialValue = 1000,
            allocationSize = 6)
    @Column(unique = true, nullable = false)
    private Long id;

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

   @Getter
   @Column(nullable = false)
    private String typeOfMeasures;

    @Column(nullable = false)
    private Long weightPaper;

    @Column(nullable = false)
    private Long largePaper;

    @Column(nullable = false)
    private String batchProvider;

    @Getter
    @Column(nullable = false)
    private String internalBatch;

    @Column(nullable = false)
    private QualityCertificate qualityCertificate;

    @Version
    private int version;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
