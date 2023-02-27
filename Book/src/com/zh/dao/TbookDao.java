package com.zh.dao;

import com.zh.bean.Tbook;
import com.zh.utils.BaseInterface;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface TbookDao extends BaseInterface<Tbook> {
    public List<Tbook> Fuzzyquery(String str) throws SQLException;
    public Integer queryForPageTotalCount()  throws SQLException ;
    public List<Tbook> queryForPageItems(int begin, int pageSize) throws SQLException;
    public List<Tbook> findByPrice(int min, int max) throws SQLException;
    public Integer queryForPriceTotalCount(int max, int min)  throws SQLException;

    public Integer queryForPageTotalCount(String name, String author, BigDecimal min,BigDecimal max)  throws SQLException ;
    public List<Tbook> queryForPageItems(String name, String author, BigDecimal min,BigDecimal max,int begin, int pageSize) throws SQLException;
}
