package com.github.dmitrKuznetsov.orderms.services;

import com.github.dmitrKuznetsov.orderms.repositories.entities.Order;

public interface OrderService {
    Order findById(int id);

    Order save(Order order);

    Order update(Order order);

    void deleteById(int id);
}
