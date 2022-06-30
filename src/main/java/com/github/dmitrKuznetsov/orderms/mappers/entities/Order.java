package com.github.dmitrKuznetsov.orderms.mappers.entities;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Order {
    private int id;
    private int orderStatusId;
    private String customerName;
    private String customerPhone;
    private String customerComment;
    private ArrayList<OrderItem> orders;
}
