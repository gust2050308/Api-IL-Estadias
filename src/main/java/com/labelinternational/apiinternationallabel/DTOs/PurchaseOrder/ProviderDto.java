package com.labelinternational.apiinternationallabel.DTOs.PurchaseOrder;

import com.labelinternational.apiinternationallabel.Entity.Enums.ProviderType;
import lombok.Data;
import lombok.*;

@Data
@Setter
@Getter
public class ProviderDto {
    private Long idProvider;
    private String providerName;
    private String providerAddress;
    private String providerEmail;
    private String providerPhone;
    private String providerPerson;
    private ProviderType providerType;
    private Boolean enabled;

    public ProviderDto(Long idProvider, String providerName, String providerAddress, String providerEmail, String providerPhone, String providerPerson, ProviderType providerType,  Boolean enabled) {
        this.idProvider = idProvider;
        this.providerName = providerName;
        this.providerAddress = providerAddress;
        this.providerEmail = providerEmail;
        this.providerPhone = providerPhone;
        this.providerPerson = providerPerson;
        this.providerType = providerType;
        this.enabled = enabled;
    }
}
