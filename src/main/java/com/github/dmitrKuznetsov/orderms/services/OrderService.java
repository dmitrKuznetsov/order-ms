package com.github.dmitrKuznetsov.orderms.services;

import com.github.dmitrKuznetsov.orderms.mappers.OrderItemMapper;
import com.github.dmitrKuznetsov.orderms.mappers.OrderMapper;
import com.github.dmitrKuznetsov.orderms.mappers.entities.Order;
import com.github.dmitrKuznetsov.orderms.mappers.entities.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    public OrderMapper orderMapper;
    @Autowired
    public OrderItemMapper orderItemMapper;

    public Order findById(int id) {
        Order order = orderMapper.findById(id);
        ArrayList<OrderItem> orderItems = new ArrayList(orderItemMapper.findByOrderId(id));
        order.setOrders(orderItems);
        return order;
    }
}
