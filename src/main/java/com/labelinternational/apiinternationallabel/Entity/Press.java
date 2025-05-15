package com.labelinternational.apiinternationallabel.Entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Press {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPress;

    @Column
    private Boolean suaje = false;

    @Column
    private Boolean designVerification = false;

    @Column
    private Boolean tonesColor = false;

    @Column
    private Boolean registration = false;

    @Column
    private Boolean finishing = false;

    @Column
    private Boolean stainsInLebel = false;

    @Column
    private String observations;

}
