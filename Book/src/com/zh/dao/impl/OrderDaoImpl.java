package com.zh.dao.impl;

import com.zh.bean.Order;
import com.zh.bean.OrderItem;
import com.zh.dao.OrderDao;
import com.zh.utils.BaseDao;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl  extends BaseDao implements OrderDao{
    @Override
    public List<Order> findAll() throws SQLException {
        return null;
    }

    @Override
    public void save(Order order) throws SQLException {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
        queryRunner.update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public void updateById(Order order) throws SQLException {

    }

    @Override
    public void deleteById(Integer id) throws SQLException {

    }

    @Override
    public Order findById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<Order> page(Integer pageNumber) throws SQLException {
        String sql = "select * from t_order order by create_time desc limit ?,?";
        BeanProcessor bean = new GenerousBeanProcessor();
        RowProcessor processor = new BasicRowProcessor(bean);
        return queryRunner.query(sql,new BeanListHandler<>(Order.class,processor),(pageNumber-1)*pageSize,pageSize);
    }

    @Override
    public Integer pageRecord() throws SQLException {
        String sql = "select count(*) from t_order";
        ScalarHandler<Long> handler = new ScalarHandler<>();
        Long i = queryRunner.query(sql, handler);
        return i.intValue();
    }

    @Override
    public List<OrderItem> pageItems(Integer pageNumber, Long orderId) throws SQLException {
        String sql = "select * from t_order_item where order_id = ? order by id desc limit ?,?";
        BeanProcessor bean = new GenerousBeanProcessor();
        RowProcessor processor = new BasicRowProcessor(bean);
        return queryRunner.query(sql,new BeanListHandler<>(OrderItem.class,processor),orderId,(pageNumber-1)*pageSize,pageSize);
    }

    @Override
    public Integer pageItemsRecord(Long orderId) throws SQLException {
        String sql = "select count(*) from t_order_item where order_id = ?";
        ScalarHandler<Long> handler = new ScalarHandler<>();
        Long i = queryRunner.query(sql, handler,orderId);
        return i.intValue();
    }
}
