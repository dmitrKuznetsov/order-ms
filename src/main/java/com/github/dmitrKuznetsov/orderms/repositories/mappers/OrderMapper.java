package com.github.dmitrKuznetsov.orderms.repositories.mappers;

import com.github.dmitrKuznetsov.orderms.repositories.entities.Order;
import org.apache.ibatis.annotations.*;

@Mapper
public interface OrderMapper {

    String findById = "SELECT * FROM \"order\" WHERE ID = #{id}";

    String insert = "INSERT INTO \"order\" (ID, ORDER_STATUS_ID, CUSTOMER_NAME, CUSTOMER_PHONE, CUSTOMER_COMMENT)" +
                    "VALUES (NEXTVAL('order_seq'), #{orderStatusId}, #{customerName}, #{customerPhone}, #{customerComment})";

    String deleteById = "DELETE FROM \"order\" WHERE ID = #{id}";

    String update = "UPDATE \"order\"" +
                    "SET ORDER_STATUS_ID=#{orderStatusId}," +
                    "CUSTOMER_NAME=#{customerName}," +
                    "CUSTOMER_PHONE=#{customerPhone}," +
                    "CUSTOMER_COMMENT=#{customerComment}" +
                    "WHERE ID = #{id}";


    @Select(findById)
    Order findById(int id);

    @Insert(insert)
    @Options(useGeneratedKeys=true, keyProperty="id")
    void insert(Order order);

    @Delete(deleteById)
    void deleteById(int id);

    @Update(update)
    void update(Order order);
}
