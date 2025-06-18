package com.labelinternational.apiinternationallabel.DTOs.Entry;

import com.labelinternational.apiinternationallabel.Entity.InInk;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class InkEntryDto {

    private Long idItemOrder;

    private InInk inkEntry;

}
