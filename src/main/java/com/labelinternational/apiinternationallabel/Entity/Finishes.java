package com.labelinternational.apiinternationallabel.Entity;

import com.labelinternational.apiinternationallabel.Entity.Enums.CoilOutput;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Finishes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFinishes;

    @Column(nullable = false)
    private CoilOutput coilOutput;

    @Column(nullable = false)
    private String coilType;

    @Column(nullable = false)
    private Long labelsPacked;

    @Column(nullable = false)
    private Boolean emissionVerification = false;

    @Column(nullable = false)
    private Boolean textReview =  false;

    @Column(nullable = false)
    private Long labelsPerCoil;

    @Column(nullable = false)
    private String spare;

    @Column
    private Boolean designReview = false;

    @Column
    private Boolean useOfSeparators = false;

    @Column
    private String comments;
}
