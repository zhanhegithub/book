package com.zh.service;

import com.zh.bean.Order;
import com.zh.bean.OrderItem;
import com.zh.utils.Page;

import java.sql.SQLException;

public interface OrderService {
    public String createOrder(Cart cart, Integer userId) throws SQLException;

    public Page<Order> page(Integer pageNo,Integer pageSize) throws SQLException;

    public Page<OrderItem> pageItem(Integer pageNo, Integer pageSize,Long orderId) throws SQLException;

}
