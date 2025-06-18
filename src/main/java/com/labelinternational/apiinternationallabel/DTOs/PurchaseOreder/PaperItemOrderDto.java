package com.labelinternational.apiinternationallabel.DTOs.PurchaseOreder;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaperItemOrderDto {
    private Long idPaperItemOrder;
    private Long unitsQuantity;
    private String codeItem;
    private Long paperWidth;
    private Long paperlength;
}
