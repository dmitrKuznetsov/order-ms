package com.github.dmitrKuznetsov.orderms.services;

import com.github.dmitrKuznetsov.orderms.mappers.OrderItemMapper;
import com.github.dmitrKuznetsov.orderms.mappers.OrderMapper;
import com.github.dmitrKuznetsov.orderms.mappers.entities.Order;
import com.github.dmitrKuznetsov.orderms.mappers.entities.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderService {

    @Autowired
    public OrderMapper orderMapper;
    @Autowired
    public OrderItemMapper orderItemMapper;

    public Order findById(int id) {
        Order order = orderMapper.findById(id);
        if (order != null) {
            ArrayList<OrderItem> items = orderItemMapper.findByOrderId(id);
            if (items != null) {
                order.setOrderItems(items);
            }
        }
        return order;
    }

    public void save(Order order) {
        orderMapper.insert(order);
    }

    public void deleteById(int id) {
        orderMapper.deleteById(id);
    }

    public void update(int id, Order order) {
        orderMapper.update(order);
    }
}
