package com.github.dmitrKuznetsov.orderms.mappers;

import com.github.dmitrKuznetsov.orderms.mappers.entities.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface OrderMapper {
    @Select("SELECT * FROM order") //SQL
    List<Order> findAll( );
}
