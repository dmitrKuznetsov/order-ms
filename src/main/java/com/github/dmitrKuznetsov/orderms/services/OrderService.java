package com.github.dmitrKuznetsov.orderms.services;

import com.github.dmitrKuznetsov.orderms.mappers.OrderMapper;
import com.github.dmitrKuznetsov.orderms.mappers.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    public OrderMapper orderMapper;

    public Order findById(int id) {
        Order order = orderMapper.findById(id);
        System.out.println(order);
        return orderMapper.findById(id);
    }
}
