package com.zh.dao;

import com.zh.bean.Order;
import com.zh.bean.OrderItem;
import com.zh.utils.BaseDao;
import com.zh.utils.BaseInterface;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao extends BaseInterface<Order> {
    public List<OrderItem> pageItems(Integer pageNumber, Long orderId) throws SQLException;
    public Integer pageItemsRecord(Long orderId) throws SQLException;

}
