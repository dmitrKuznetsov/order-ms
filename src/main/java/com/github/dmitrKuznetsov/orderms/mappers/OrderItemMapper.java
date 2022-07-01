package com.github.dmitrKuznetsov.orderms.mappers;

import com.github.dmitrKuznetsov.orderms.mappers.entities.OrderItem;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface OrderItemMapper {
    String findByOrderId = "SELECT * FROM ORDER_ITEM WHERE ORDER_ID = #{id}";

    String insertByOrderId =
        "<script>" +
            "insert into ORDER_ITEM (ID, ORDER_ID, ITEM_NAME) values " +
            "<foreach collection='items' item='o' separator = ', '>(NEXTVAL('order_item_seq'), #{orderId}, #{o.itemName})</foreach>" +
        "</script>";

    String update = "UPDATE ORDER_ITEM SET ITEM_NAME=#{itemName} WHERE ID = #{id}";


    @Select(findByOrderId)
    ArrayList<OrderItem> findByOrderId(int id);

    @Insert(insertByOrderId)
    void insertByOrderId(@Param("orderId") int orderId, @Param("items") ArrayList<OrderItem> items);

    @Update(update)
    void update(OrderItem orderItem);
}
