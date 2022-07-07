package com.github.dmitrKuznetsov.orderms.repositories.mappers;

import com.github.dmitrKuznetsov.orderms.repositories.entities.Order;
import org.apache.ibatis.annotations.*;

@Mapper
public interface OrderMapper {

    Order findById(int id);

    void insert(Order order);

    void deleteById(int id);

    void update(Order order);
}
