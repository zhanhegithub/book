package com.zh.dao.impl;

import com.zh.bean.Tbook;
import com.zh.dao.TbookDao;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class TbookDaoImplTest {

    @Test
    public void updateById() {
        Tbook tbook = new Tbook();
        tbook.setName("haha");
        tbook.setPrice(new BigDecimal(48.2));
        tbook.setAuthor("haha");
        tbook.setSales(56);
        tbook.setStock(10);
        tbook.setImg_path("haha");
        tbook.setId(31);
        TbookDao tbookDao = new TbookDaoImpl();
        try {
            tbookDao.updateById(tbook);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Test
    public void testQueryForPageTotalCount() throws SQLException {
        TbookDao tbookDao = new TbookDaoImpl();
        Integer integer = tbookDao.queryForPageTotalCount();
        System.out.println("integer = " + integer);
    }
    @Test
    public void queryForPageItems() {
        TbookDao tbookDao = new TbookDaoImpl();
        try {
            List<Tbook> tbookList = tbookDao.queryForPageItems(null,null,null,null,0,4);
            for (Tbook tbook:tbookList) {
                System.out.println("tbook = " + tbook);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}