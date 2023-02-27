package com.zh.service;

import com.zh.bean.Tbook;
import com.zh.utils.Page;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface BookService {

    public List<Tbook> findAll() throws SQLException;
    public void save(Tbook tbook) throws SQLException;
    public void updateById(Tbook tbook) throws SQLException;
    public void deleteById(Integer id) throws SQLException;
    public Tbook findById(Integer id) throws SQLException;
    public Page<Tbook> page(int pageNo,int pageSize) throws SQLException;
    public Page<Tbook> pricePage(int pageNo, int pageSize,int max, int min) throws SQLException;
    public Page<Tbook> page(int pageNo, int pageSize, String name, String author, BigDecimal min,BigDecimal max) throws SQLException;
}
