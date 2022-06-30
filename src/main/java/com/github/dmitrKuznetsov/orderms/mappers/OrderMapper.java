package com.github.dmitrKuznetsov.orderms.mappers;

import com.github.dmitrKuznetsov.orderms.mappers.entities.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface OrderMapper {
    Order findById(@Param("id") int id);

//    @Select("SELECT * FROM \"order\" WHERE ID = #{id}")
//    Order save(@Param("id") int id);
}
