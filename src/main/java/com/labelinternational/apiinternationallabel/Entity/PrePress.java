package com.labelinternational.apiinternationallabel.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrePress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrePress;

    @Column
    private Boolean technicalDate = false;

    @Column
    private Boolean emissionVerification = false;

    @Column
    private Boolean DimensionsVerification = false;

    @Column
    private Boolean OutputsVerification = false;

    @Column
    private Boolean goodEngravings = false;

    @Column
    private Boolean rollersVerification = false;

    @Column
    private Boolean fileColorVerification = false;

}
