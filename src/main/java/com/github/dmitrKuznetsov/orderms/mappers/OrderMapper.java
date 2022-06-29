package com.github.dmitrKuznetsov.orderms.mappers;

import com.github.dmitrKuznetsov.orderms.mappers.entities.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface OrderMapper {
    @Select("SELECT * FROM \"order\" WHERE id = #{id}")
    Order findById(@Param("id") int id);
}
