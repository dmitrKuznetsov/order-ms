package com.github.dmitrKuznetsov.orderms.repositories.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@NoArgsConstructor
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int orderStatusId;
    private String customerName;
    private String customerPhone;
    private String customerComment;
    private ArrayList<OrderItem> orderItems;

    public Order(int id, int orderStatusId, String customerName, String customerPhone, String customerComment, ArrayList<OrderItem> orderItems) {
        this.id = id;
        this.orderStatusId = orderStatusId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerComment = customerComment;
        this.orderItems = orderItems;
    }
}
