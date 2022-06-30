package com.github.dmitrKuznetsov.orderms.mappers;

import com.github.dmitrKuznetsov.orderms.mappers.entities.Order;
import com.github.dmitrKuznetsov.orderms.mappers.entities.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface OrderItemMapper {
    @Select("SELECT * FROM ORDER_ITEM WHERE ORDER_ID = #{id}")
    List<OrderItem> findByOrderId(@Param("id") int id);
}
