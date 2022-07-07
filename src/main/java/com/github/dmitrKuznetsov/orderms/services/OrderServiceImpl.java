package com.github.dmitrKuznetsov.orderms.services;

import com.github.dmitrKuznetsov.orderms.repositories.OrderRepository;
import com.github.dmitrKuznetsov.orderms.repositories.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    public OrderRepository orderRepository;

    @Override
    public Order findById(int id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        return orderRepository.update(order);
    }

    @Override
    public void deleteById(int id) {
        orderRepository.deleteById(id);
    }
}
