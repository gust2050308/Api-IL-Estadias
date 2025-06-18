package com.labelinternational.apiinternationallabel.DTOs.PurchaseOreder;

import lombok.Data;
import lombok.*;

@Data
@Setter
@Getter
public class ProviderDto {
    private Long id_Provider;
    private String provider_Name;
    private String provider_Address;
    private String provider_Email;
    private String provider_Phone;
    private String provider_Person;
}
