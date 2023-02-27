package com.zh.test;

import com.zh.bean.OrderItem;
import com.zh.dao.OrderItemDao;
import com.zh.dao.impl.OrderItemDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;

public class OrderItemDaoImplTrst {
    @Test
    public void saceTest () throws SQLException {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();

        orderItemDao.save(new OrderItem(null,"java 从入门到精通", 1,new BigDecimal(100),new BigDecimal(100),"1659934761692"));
        orderItemDao.save(new OrderItem(null,"javaScript 从入门到精通", 2,new BigDecimal(100),new BigDecimal(200),"1659934761692"));
        orderItemDao.save(new OrderItem(null,"Netty 入门", 1,new BigDecimal(100),new BigDecimal(100),"1659934761692"));

    }
}
