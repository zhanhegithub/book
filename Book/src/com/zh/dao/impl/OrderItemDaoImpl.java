package com.zh.dao.impl;

import com.zh.bean.OrderItem;
import com.zh.dao.OrderItemDao;
import com.zh.utils.BaseDao;

import java.sql.SQLException;
import java.util.List;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public List<OrderItem> findAll() throws SQLException {
        return null;
    }

    @Override
    public void save(OrderItem orderItem) throws SQLException {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        queryRunner.update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    @Override
    public void updateById(OrderItem orderItem) throws SQLException {

    }

    @Override
    public void deleteById(Integer id) throws SQLException {

    }

    @Override
    public OrderItem findById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<OrderItem> page(Integer pageNumber) throws SQLException {
        return null;
    }

    @Override
    public Integer pageRecord() throws SQLException {
        return null;
    }
}
