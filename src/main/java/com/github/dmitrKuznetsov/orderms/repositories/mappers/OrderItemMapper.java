package com.github.dmitrKuznetsov.orderms.repositories.mappers;

import com.github.dmitrKuznetsov.orderms.repositories.entities.OrderItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderItemMapper {

    List<OrderItem> findByOrderId(int id);

    void insertOrderItems(List<OrderItem> items);

    void update(List<OrderItem>  orderItem);

    void delete(List<Integer> ids);
}
