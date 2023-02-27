package com.zh.test;

import com.zh.bean.User;
import com.zh.service.UserService;
import com.zh.service.impl.UserServiceImpl;
import org.junit.Test;

import java.sql.SQLException;

public class UserServiceTest {
    UserService userService = new UserServiceImpl();

    @Test
    public void registUser() throws SQLException {
        userService.registUser(new User(null, "bbj168", "666666", "bbj168@qq.com"));
        userService.registUser(new User(null, "abc168", "666666", "abc168@qq.com"));
    }

    @Test
    public void login() throws SQLException {
        System.out.println(userService.login(new User(null, "wzg168", "123456", null)));
    }

    @Test
    public void existsUsername() throws SQLException {
        if (userService.existsUsername("wzg16888")) {
            System.out.println("用户名已存在！");
        } else {
            System.out.println("用户名可用！");
        }
    }
}
