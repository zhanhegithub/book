package com.zh.test;

import com.zh.bean.Order;
import com.zh.dao.OrderDao;
import com.zh.dao.impl.OrderDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;

public class OrderTest {
    @Test
    public void saveTest() throws SQLException {
        Order order = new Order(""+System.currentTimeMillis(),new Timestamp(System.currentTimeMillis()),
                new BigDecimal(10),0,1);
        OrderDao orderDao = new OrderDaoImpl();
        orderDao.save(order);
    }
}
