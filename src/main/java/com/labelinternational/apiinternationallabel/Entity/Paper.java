package com.labelinternational.apiinternationallabel.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Paper {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paper_seq")
    @SequenceGenerator(
            name = "paper_seq",
            sequenceName = "paper_sequence",
            initialValue = 1000,
            allocationSize = 6)
    @Column(unique = true, nullable = false)
    private Long id_Paper;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn
    private InPaper inPaper;

    @ManyToOne
    @JoinColumn(name = "idProduction")
    private Production production;

    @Column(nullable = true)
    private Long LargeUsed;

    @Column(nullable = true)
    private Long widthUsed;

    @Column(nullable = false)
    private Long remainingVolume;


}
