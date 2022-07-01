package com.github.dmitrKuznetsov.orderms.mappers.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 2L;

    private int id;
    private int orderId;
    private String itemName;
}
