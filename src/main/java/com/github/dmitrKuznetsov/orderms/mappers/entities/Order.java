package com.github.dmitrKuznetsov.orderms.mappers.entities;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

@Data
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int orderStatusId;
    private String customerName;
    private String customerPhone;
    private String customerComment;
    private ArrayList<OrderItem> orders;
}
