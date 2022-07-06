package com.github.dmitrKuznetsov.orderms.services;


import com.github.dmitrKuznetsov.orderms.repositories.OrderRepository;
import com.github.dmitrKuznetsov.orderms.repositories.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderService {

    @Autowired
    public OrderRepository orderRepository;

    public Order findById(int id) {
        return orderRepository.findById(id);
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public Order update(Order order) {
        return orderRepository.update(order);
    }

    public void deleteById(int id) {
        orderRepository.deleteById(id);
    }
}
