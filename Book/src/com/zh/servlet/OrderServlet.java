package com.zh.servlet;

import com.zh.bean.Order;
import com.zh.bean.OrderItem;
import com.zh.bean.User;
import com.zh.service.Cart;
import com.zh.service.OrderService;
import com.zh.service.impl.OrderServiceImpl;
import com.zh.utils.BaseServlet;
import com.zh.utils.Page;
import com.zh.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "OrderServlet",value = "/OrderServlet")
public class OrderServlet extends BaseServlet {

    protected void listOrderItems(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = new OrderServiceImpl();
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"),4);
        System.out.println("pageNo = " + pageNo);
        System.out.println("pageSize = " + pageSize);
        Long orderId = Long.valueOf(request.getParameter("orderId"));
        try {
            Page<OrderItem> page = orderService.pageItem(pageNo, pageSize,orderId);
            System.out.println("page = " + page);
            request.setAttribute("page",page);
            page.setUrl("OrderServlet?action=listOrderItems&orderId="+orderId+"&");
            request.getRequestDispatcher("/pages/order/orderItem.jsp").forward(request,response);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void listOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = new OrderServiceImpl();
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"),4);
        try {
            Page<Order> page = orderService.page(pageNo, pageSize);
            request.setAttribute("page",page);
            page.setUrl("OrderServlet?action=listOrder&");
            request.getRequestDispatcher("/pages/order/order.jsp").forward(request,response);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //生成订单
    protected void creatOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Cart cart = (Cart)session.getAttribute("cart");
        User user = (User)session.getAttribute("user");

        if (user==null){
            request.setAttribute("msg","登录超时");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }

        OrderService orderService = new OrderServiceImpl();
        try {
            //生成订单号并且返回
            String orderId = orderService.createOrder(cart, user.getId());
            //为什么选择用重定向跳转,而不是服务器转发?
            //为了防止表单重复提交
            request.getSession().setAttribute("orderId",orderId);
            response.sendRedirect(request.getContextPath()+"/pages/cart/checkout.jsp");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
