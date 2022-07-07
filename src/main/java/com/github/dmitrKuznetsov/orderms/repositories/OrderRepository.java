package com.github.dmitrKuznetsov.orderms.repositories;

import com.github.dmitrKuznetsov.orderms.repositories.entities.Order;
import com.github.dmitrKuznetsov.orderms.repositories.entities.OrderItem;

import java.util.List;

public interface OrderRepository {

    int NON_EXISTING_IP = -1;

    Order findById(int id);

    List<OrderItem> findOrderItemByOrderId(int orderId);

    Order save(Order order);

    Order update(Order order);

    void deleteById(int id);
}