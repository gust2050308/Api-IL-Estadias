package com.labelinternational.apiinternationallabel.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class ItemOrder {
    @Id
    @GeneratedValue
    private Long id_ItemOrder;

    private Long amount;

    private String CodeItem;

    private String Item;


    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public void setCodeItem(String codeItem) {
        CodeItem = codeItem;
    }

    public void setItem(String item) {
        Item = item;
    }


    public Long getAmount() {
        return amount;
    }

    public String getCodeItem() {
        return CodeItem;
    }

    public String getItem() {
        return Item;
    }

    public void setId_ItemOrder(Long idItemOrder) {
        this.id_ItemOrder = idItemOrder;
    }

    public Long getId_ItemOrder() {
        return id_ItemOrder;
    }
}
