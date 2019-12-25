package com.edu.sale.mapper;

import com.edu.wzh.entity.Order;

import java.util.List;

public interface OrderMapper {

    boolean createOrder(Order order);

    boolean updateOrder(String oid);

    boolean updateOrderPay(String oid);

    List<Order> getOrders(int aid);

    Order getOrder(String oid);
}
