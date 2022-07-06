package com.github.dmitrKuznetsov.orderms.repositories;

import com.github.dmitrKuznetsov.orderms.repositories.entities.Order;
import com.github.dmitrKuznetsov.orderms.repositories.entities.OrderItem;
import com.github.dmitrKuznetsov.orderms.repositories.mappers.OrderItemMapper;
import com.github.dmitrKuznetsov.orderms.repositories.mappers.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;

@Repository
public class OrderRepository {
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

    public Order update(Order order) {
        orderMapper.update(order);

        // update order items
        ArrayList<OrderItem> existedItems = new ArrayList<>(orderItemMapper.findByOrderId(order.getId()));
        ArrayList<OrderItem> updatedItems = new ArrayList<>(order.getOrderItems());

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
//        if (!existedItems.isEmpty()) {
//            orderItemMapper.delete(existedItems);
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
