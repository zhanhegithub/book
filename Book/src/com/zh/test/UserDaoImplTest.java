package com.zh.test;

import com.zh.bean.User;
import com.zh.dao.UserDao;
import com.zh.dao.impl.UserDaoImpl;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class UserDaoImplTest {

    private UserDao userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername() {
        try {
            User user = userDao.queryUserByUsername("zh");
            System.out.println("user = " + user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        try {
            User user = userDao.queryUserByUsernameAndPassword("admin","123");
            System.out.println("user = " + user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAll() {
    }

    @Test
    public void save() {
        String sql = "insert into t_user values(null,?,?,?)";
    }

    @Test
    public void updateById() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void page() {
    }

    @Test
    public void pageRecord() {
    }
}