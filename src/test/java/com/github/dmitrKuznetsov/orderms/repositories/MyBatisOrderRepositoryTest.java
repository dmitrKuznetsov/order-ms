package com.github.dmitrKuznetsov.orderms.repositories;

import com.github.dmitrKuznetsov.orderms.repositories.entities.Order;
import com.github.dmitrKuznetsov.orderms.repositories.entities.OrderItem;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.github.dmitrKuznetsov.orderms.repositories.OrderRepository.NON_EXISTING_IP;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@MybatisTest
@Import(MyBatisOrderRepository.class)
@AutoConfigureTestDatabase(replace = NONE)
class MyBatisOrderRepositoryTest {
    @Autowired
    public OrderRepository orderRepository;

    @Sql(scripts = { "/sql/ClearDbs.sql", "/sql/Orders.sql" })
    @Test
    void shouldProperlyFindOrderById() {
        //given
        int orderId = 1001;

        //when
        Order order = orderRepository.findById(orderId);

        //then
        assertEquals(orderId, order.getId());
        assertEquals(1, order.getOrderStatusId());
        assertEquals("Ivanov I.I", order.getCustomerName());
        assertEquals("+7(952)-634-55-23", order.getCustomerPhone());
        assertEquals("Pls. call before delivery", order.getCustomerComment());
        assertEquals(3, order.getOrderItems().size());

        for (int ii = 0; ii < order.getOrderItems().size(); ii++) {
            OrderItem item = order.getOrderItems().get(ii);
            assertEquals(1001 + ii, item.getId());
            assertEquals(orderId, item.getOrderId());
            assertEquals("Order Item #" + (ii+1), item.getItemName());
        }
    }

    @Sql(scripts = { "/sql/ClearDbs.sql", "/sql/Orders.sql" })
    @Test
    void shouldProperlySaveOrder() {
        //given
        int orderId = 1002;
        int orderItemId = 1004;

        List<OrderItem> items = new ArrayList<>();
        for (int ii = 0; ii < 3; ii++) {
            OrderItem item = new OrderItem();
            item.setItemName("New item #" + (ii + 1));
            items.add(item);
        }

        Order order = new Order(
                orderId,
                2,
                "Terminator",
                "+7-666-999-66-99",
                "I'll be back",
                items
        );

        //when
        Order savedOrder = orderRepository.save(order);

        //then
        assertEquals(orderId, savedOrder.getId());
        assertEquals(order.getOrderStatusId(), savedOrder.getOrderStatusId());
        assertEquals(order.getCustomerName(), savedOrder.getCustomerName());
        assertEquals(order.getCustomerPhone(), savedOrder.getCustomerPhone());
        assertEquals(order.getCustomerComment(), savedOrder.getCustomerComment());
        assertEquals(order.getOrderItems().size(), savedOrder.getOrderItems().size());

        for (int ii = 0; ii < savedOrder.getOrderItems().size(); ii++) {
            OrderItem item = order.getOrderItems().get(ii);
            OrderItem savedItem = savedOrder.getOrderItems().get(ii);
            assertEquals(orderItemId + ii, savedItem.getId());
            assertEquals(orderId, savedItem.getOrderId());
            assertEquals(item.getItemName(), savedItem.getItemName());
        }
    }

    @Sql(scripts = { "/sql/ClearDbs.sql", "/sql/Orders.sql" })
    @Test
    void shouldProperlyDeleteOrder() {
        //given
        int orderId = 1001;

        //when
        orderRepository.deleteById(orderId);
        Order orderById = orderRepository.findById(orderId);
        List<OrderItem> orderItems = orderRepository.findOrderItemByOrderId(orderId);
        //then
        assertNull(orderById);
        assertEquals(0, orderItems.size());
    }

    @Sql(scripts = { "/sql/ClearDbs.sql", "/sql/Orders.sql" })
    @Test
    void shouldProperlyUpdateOrder() {
        //given
        int orderId = 1001;
        int orderItemId = 1001;

        Order order = new Order(
            orderId,
            2,
            "Terminator",
            "+7-666-999-66-99",
            "I'll be back",
            new ArrayList<>(Arrays.asList(
                    new OrderItem(orderItemId, orderId, "Order Item #1"),
                    new OrderItem(orderItemId+1, orderId, "Updated order Item"),
                    new OrderItem(NON_EXISTING_IP, orderId, "Created order Item")
            ))
        );

        //when
        Order updatedOrder = orderRepository.update(order);
        Order orderById = orderRepository.findById(order.getId());

        //then
        assertEquals(updatedOrder, order);
        assertEquals(orderById, order);
    }
}