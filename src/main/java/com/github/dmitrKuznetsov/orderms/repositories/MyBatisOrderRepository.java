package com.github.dmitrKuznetsov.orderms.repositories;

import com.github.dmitrKuznetsov.orderms.repositories.entities.Order;
import com.github.dmitrKuznetsov.orderms.repositories.entities.OrderItem;
import com.github.dmitrKuznetsov.orderms.repositories.mappers.OrderItemMapper;
import com.github.dmitrKuznetsov.orderms.repositories.mappers.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MyBatisOrderRepository implements OrderRepository {

    @Autowired
    public OrderMapper orderMapper;
    @Autowired
    public OrderItemMapper orderItemMapper;

    @Override
    public Order findById(int id) {
        Order order = orderMapper.findById(id);
        if (order != null) {
            List<OrderItem> items = orderItemMapper.findByOrderId(id);
            if (items != null) {
                order.setOrderItems(items);
            }
        }
        return order;
    }

    @Override
    public List<OrderItem> findOrderItemByOrderId(int orderId) {
        return orderItemMapper.findByOrderId(orderId);
    }

    @Override
    public Order save(Order order) {
        orderMapper.insert(order);

        List<OrderItem> orderItems = order.getOrderItems();
        if (orderItems != null && orderItems.size() != 0) {
            orderItems.forEach(orderItem -> orderItem.setOrderId(order.getId()));
            orderItemMapper.insertOrderItems(orderItems);
        }

        return order;
    }

    @Override
    public Order update(Order updatedOrder) {
        // check if order with ID exists
        Order existedOrder = this.findById(updatedOrder.getId());
        if (existedOrder == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        // update order
        orderMapper.update(updatedOrder);


        // save new orderItems
        List<OrderItem> newItems = new ArrayList<>(updatedOrder.getOrderItems())
                .stream().filter(item -> item.getId() == NON_EXISTING_IP).collect(Collectors.toList());
        orderItemMapper.insertOrderItems(newItems);

        // create copy of lists
        List<OrderItem> existedItems = new ArrayList<>(existedOrder.getOrderItems());
        List<OrderItem> updatedItems = new ArrayList<>(updatedOrder.getOrderItems());
        updatedItems.removeAll(newItems); // already processed

        // remove intersection of existed and updated lists of items
        ArrayList<OrderItem> intersection = new ArrayList<>(existedItems);
        intersection.retainAll(updatedItems);
        existedItems.removeAll(intersection);
        updatedItems.removeAll(intersection);

        // update edited items
        orderItemMapper.update(updatedItems);

        // delete remaining items
        List<Integer> existedItemIds = existedItems.stream().mapToInt(OrderItem::getId).boxed().collect(Collectors.toList());
        List<Integer> updatedItemIds = updatedItems.stream().mapToInt(OrderItem::getId).boxed().collect(Collectors.toList());
        existedItemIds.removeIf(updatedItemIds::contains);
        orderItemMapper.delete(existedItemIds);

        return this.findById(updatedOrder.getId());
    }

    @Override
    public void deleteById(int id) {
        orderMapper.deleteById(id);
    }
}
