package com.zh.service.impl;

import com.zh.bean.CartItem;
import com.zh.bean.Order;
import com.zh.bean.OrderItem;
import com.zh.bean.Tbook;
import com.zh.dao.OrderDao;
import com.zh.dao.OrderItemDao;
import com.zh.dao.TbookDao;
import com.zh.dao.impl.OrderDaoImpl;
import com.zh.dao.impl.OrderItemDaoImpl;
import com.zh.dao.impl.TbookDaoImpl;
import com.zh.service.Cart;
import com.zh.service.OrderService;
import com.zh.utils.Page;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    //订单dao
    private OrderDao orderDao = new OrderDaoImpl();
    //订单项dao
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();

    private TbookDao bookDao = new TbookDaoImpl();

    /**
    * 生成一个订单
    * 1.添加一个订单到数据库中的order表中
    * 2.此订单中至少有一个单项..所以要将订单项目都添加到orderItem表中
    * 3.清空掉购物车中的数据
    * @param cart
    * @param userId
     * @return
    */
    @Override
    public String createOrder(Cart cart, Integer userId) throws SQLException {
        //1.添加一个订单到数据库中的order表中
        String orderId = ""+System.currentTimeMillis() + userId;
        Order order = new Order();
        order.setOrderId(orderId);//生成的订单编号
        order.setCreateTime(new Timestamp(System.currentTimeMillis()));//当前的系统时间
        order.setPrice(cart.getTotalPrice());//订单的总价格
        order.setStatus(0);//设置0表示未发货
        order.setUserId(userId);//设置用户编号,因为这个订单要知道是谁下的单
        orderDao.save(order);
        //2.此订单中至少有一个单项..所以要将订单项目都添加到orderItem表中
        Map<Integer, CartItem>items = cart.getItems();
        for (Map.Entry<Integer,CartItem> entry:items.entrySet()) {
            OrderItem item = new OrderItem();
            item.setName(entry.getValue().getName());//设置订单项的名字
            item.setCount(entry.getValue().getCount());//设置订单项的数量
            item.setPrice(entry.getValue().getPrice());//设置订单项的单价
            item.setTotalPrice(entry.getValue().getTotalPrice());//设置订单项的总价
            item.setOrderId(orderId);//设置订单项的外键id订单编号
            orderItemDao.save(item);
            //更新库存销量
            Tbook book = bookDao.findById(entry.getValue().getId());//通过图书的主键id返回一个图书对象:book
            book.setSales(book.getSales()+entry.getValue().getCount());
            book.setStock(book.getStock()-entry.getValue().getCount());
            bookDao.updateById(book);//修改book中的销量与库存

        }
        //3.清空掉购物车中的数据
        cart.clear();
        return orderId;
    }

    @Override
    public Page<Order> page(Integer pageNo, Integer pageSize) throws SQLException {
        Page<Order> page = new Page<>();
        Integer totalCount = orderDao.pageRecord();//获取总记录数
        page.setPageTotalCount(totalCount);//设置总记录数
        page.setPageTotal(( totalCount+ pageSize-1) / pageSize );//设置总页数
        page.setPageNo(pageNo);
        page.setItems(orderDao.page(page.getPageNo()));
        return page;
    }

    @Override
    public Page<OrderItem> pageItem(Integer pageNo, Integer pageSize,Long orderId) throws SQLException {
        Page<OrderItem> page = new Page<>();
        Integer totalCount = orderDao.pageItemsRecord(orderId);//获取总记录数
        page.setPageTotalCount(totalCount);//设置总记录数
        page.setPageTotal(( totalCount+ pageSize-1) / pageSize );//设置总页数
        page.setPageNo(pageNo);
        page.setItems(orderDao.pageItems(page.getPageNo(),orderId));
        return page;
    }
}
