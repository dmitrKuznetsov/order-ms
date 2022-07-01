package com.github.dmitrKuznetsov.orderms.mappers;

import com.github.dmitrKuznetsov.orderms.mappers.entities.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface OrderItemMapper {
    String findByOrderId = "SELECT * FROM ORDER_ITEM WHERE ORDER_ID = #{id}";

    @Select(findByOrderId)
    ArrayList<OrderItem> findByOrderId(int id);
}
