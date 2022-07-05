package com.github.dmitrKuznetsov.orderms.services;

import com.github.dmitrKuznetsov.orderms.mappers.OrderItemMapper;
import com.github.dmitrKuznetsov.orderms.mappers.OrderMapper;
import com.github.dmitrKuznetsov.orderms.mappers.entities.Order;
import com.github.dmitrKuznetsov.orderms.mappers.entities.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;

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

    public Order save(Order order) {
        orderMapper.insert(order);

        ArrayList<OrderItem> orderItems = order.getOrderItems();
        if (orderItems != null && orderItems.size() != 0) {
            orderItems.forEach(orderItem -> orderItem.setOrderId(order.getId()));
            orderItemMapper.insertByOrderId(orderItems);
        }

        return order;
    }

    public Order update(int id, Order order) {
        orderMapper.update(order);

        // update order items
        ArrayList<OrderItem> existedItems = orderItemMapper.findByOrderId(id);
        ArrayList<OrderItem> updatedItems = order.getOrderItems();

        ArrayList<OrderItem> intersection = new ArrayList<>(existedItems);
        intersection.retainAll(updatedItems);
        existedItems.remove(intersection);
        updatedItems.remove(intersection);

        Iterator<OrderItem> exIterator = existedItems.iterator();
        while (exIterator.hasNext()) {
            OrderItem existingItem = exIterator.next();
            Iterator<OrderItem> upIterator = updatedItems.iterator();
            while (upIterator.hasNext()) {
                OrderItem updatedItem = upIterator.next();
                if (existingItem.getId() == updatedItem.getId() && existingItem.getOrderId() == updatedItem.getOrderId()) {
                    orderItemMapper.update(updatedItem);
                    exIterator.remove();
                    upIterator.remove();
                    break;
                }
            }
        }

        // todo case with creating and deleting items in order
//        if (!existingItems.isEmpty()) {
//            orderItemMapper.delete(existingItems);
//        }
//        if (!updatedItems.isEmpty()) {
//            orderItemMapper.insert(updatedItems);
//        }

        return order;
    }

    public void deleteById(int id) {
        orderMapper.deleteById(id);
    }
}
