package com.zh.servlet;

import com.zh.bean.CartItem;
import com.zh.bean.Tbook;
import com.zh.service.BookService;
import com.zh.service.Cart;
import com.zh.service.impl.BookServiceImpl;
import com.zh.utils.BaseServlet;
import com.zh.utils.CookieUtils;
import com.zh.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CarServlet", value = "/CarServlet")
public class CarServlet extends BaseServlet {
    /**
     *	加入购物车
     *	@param request
     *	@param response
     *	@throws ServletException
     *	@throws IOException
     */
    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookService bookService = new BookServiceImpl();
        HttpSession session = request.getSession();
        // 获取请求的参数 商品编号
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        System.out.println("id = " + id);
        try {
            //通过主键id获取图书对象:book
            Tbook book = bookService.findById(id);
            // 把图书信息，转换成为CartItem 商品项
            CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
            //从session会话作用域中取出cart,如果为null则表示购物车上无商品信息,否则有
            Cart cart = (Cart)session.getAttribute("cart");
            session.setAttribute("bookName",book.getName());
            if (cart == null){
                cart = new Cart();
                session.setAttribute("cart",cart);
            }
            //添加商品项
            cart.addItem(cartItem);
            System.out.println("cart = " + cart);
            System.out.println("请求头Referer的值 " + request.getHeader("Referer"));
            response.sendRedirect(request.getHeader("Referer"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = WebUtils.parseInt(request.getParameter("id"),0);
        HttpSession session = request.getSession();

        Cart cart = (Cart)session.getAttribute("cart");
        if(cart!=null){
            cart.deleteItem(id);
            response.sendRedirect(request.getHeader("Referer"));
        }

    }

    protected void clearCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1 获取购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null) {
            // 清空购物车
            cart.clear();
            // 重定向回原来购物车展示页面
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

    protected void carJump(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        Cookie iWantCookie = CookieUtils.findCookie("password", cookies);
        Cookie iWantCookie2 = CookieUtils.findCookie("username", cookies);
        if(iWantCookie!=null&&iWantCookie2!=null){
            response.sendRedirect("pages/cart/cart.jsp");
        }
        response.sendRedirect("pages/user/login.jsp");
    }

    protected void updateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = WebUtils.parseInt(request.getParameter("id"),0);
        int count = WebUtils.parseInt(request.getParameter("count"),1);
        HttpSession session = request.getSession();
        Cart cart = (Cart)session.getAttribute("cart");
        if (cart != null){
            cart.updateCount(id,count);
        }
        response.sendRedirect(request.getHeader("Referer"));
    }
}
