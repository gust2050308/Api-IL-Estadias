package com.labelinternational.apiinternationallabel.Entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
public class InPaper {

    @Id
    @GeneratedValue
    private Long id_inPaper;

    @Column(nullable = false)
    private Date date;

    @OneToOne
    @JoinColumn(nullable = false)
    private Provider provider;

    @Column(nullable = false)
    private String invoiceRemission;// INVOICE/REMISSION - FACTURA/REMISIÓN

    @OneToOne
    @JoinColumn(nullable = false)
    private PurchaseOrder purchaseOrder;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private Long units;



    public void setId_inPaper(Long idInPaper) {
        this.id_inPaper = idInPaper;
    }

    public Long getId_inPaper() {
        return id_inPaper;
    }


}
