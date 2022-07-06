package com.github.dmitrKuznetsov.orderms.repositories.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 2L;

    private int id;
    private int orderId;
    private String itemName;

    public OrderItem(int id, int orderId, String itemName) {
        this.id = id;
        this.orderId = orderId;
        this.itemName = itemName;
    }
}
