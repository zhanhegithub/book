package com.zh.test;

import com.zh.bean.CartItem;
import com.zh.service.Cart;
import org.junit.Test;

import java.math.BigDecimal;

public class cartTest {
    @Test
    public void addItem() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1, "java 从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java 从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(3, "Oracle宝典", 1, new BigDecimal(200),new BigDecimal(200)));

        System.out.println(cart);

    }
    @Test
    public void deleteItem() {

        Cart cart = new Cart();

        cart.addItem(new CartItem(1, "java 从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java 从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));

        cart.deleteItem(1);

        System.out.println(cart);

    }
    @Test
    public void clear() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1, "java 从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java 从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));

        cart.clear();//清空购物车[删除map中的所有数据]

        System.out.println(cart);
    }
    @Test
    public void updateCount() {

        Cart cart = new Cart();

        cart.addItem(new CartItem(1, "java 从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java 从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));

        cart.updateCount(2, 4);//修改商品编号1的商品数量


        System.out.println(cart);

    }

    @Test
    public void getTotalCount() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1, "java 从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java 从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));

        Integer totalCount = cart.getTotalCount();

        System.out.println("totalCount = " + totalCount);

    }
    @Test
    public void getTotalPrice() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1, "java 从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java 从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));

        BigDecimal getTotalPrice = cart.getTotalPrice();

        System.out.println("getTotalPrice = " + getTotalPrice);
    }
}
