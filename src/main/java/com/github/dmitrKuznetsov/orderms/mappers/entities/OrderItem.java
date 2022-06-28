package com.github.dmitrKuznetsov.orderms.mappers.entities;

import lombok.Data;

@Data
public class OrderItem {
    private int id;
    private int orderId;
    private String itemName;
}
