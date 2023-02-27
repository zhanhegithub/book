package com.zh.service.impl;


import com.zh.bean.Tbook;
import com.zh.dao.TbookDao;
import com.zh.dao.impl.TbookDaoImpl;
import com.zh.service.BookService;
import com.zh.utils.Page;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class BookServiceImpl implements BookService {
    private TbookDao bookDao = new TbookDaoImpl();

    @Override
    public List<Tbook> findAll() throws SQLException {
        List<Tbook> tbookList = bookDao.findAll();
        return tbookList;
    }

    @Override
    public void save(Tbook tbook) throws SQLException {
        bookDao.save(tbook);
    }

    @Override
    public void updateById(Tbook tbook) throws SQLException {
        bookDao.updateById(tbook);
    }

    @Override
    public void deleteById(Integer id) throws SQLException {
        bookDao.deleteById(id);
    }

    @Override
    public Tbook findById(Integer id) throws SQLException {
        Tbook tbook = bookDao.findById(id);
        return tbook;
    }

    @Override
    public Page<Tbook> page(int pageNo, int pageSize) throws SQLException {
        Page<Tbook> page = new Page<>();
        Integer totalCount = bookDao.queryForPageTotalCount();//获取总记录数
        page.setPageTotalCount(totalCount);//设置总记录数
        page.setPageTotal(( totalCount+ pageSize-1) / pageSize );//设置总页数
        page.setPageNo(pageNo);
        page.setItems(bookDao.queryForPageItems((page.getPageNo()-1)*pageSize,pageSize));
        return page;
    }

    @Override
    public Page<Tbook> pricePage(int pageNo, int pageSize,int min, int max) throws SQLException {
        Page<Tbook> page = new Page<>();
        Integer totalCount = bookDao.queryForPriceTotalCount(min,max);//获取总记录数
        page.setPageTotalCount(totalCount);//设置总记录数
        page.setPageTotal(( totalCount+ pageSize-1) / pageSize );//设置总页数
        page.setPageNo(pageNo);
        page.setItems(bookDao.findByPrice(min,max));
        return page;
    }

    @Override
    public Page<Tbook> page(int pageNo, int pageSize, String name, String author, BigDecimal min, BigDecimal max) throws SQLException {
        Page<Tbook> page = new Page<>();
        Integer totalCount = bookDao.queryForPageTotalCount(name,  author,  min,  max);//获取总记录数
        page.setPageTotalCount(totalCount);//设置总记录数
        page.setPageTotal(( totalCount+ pageSize-1) / pageSize );//设置总页数
        page.setPageNo(pageNo);
        page.setItems(bookDao.queryForPageItems(name,  author, min,  max,(page.getPageNo()-1)*pageSize,pageSize));
        return page;
    }
}
