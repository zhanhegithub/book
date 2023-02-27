package com.zh.dao.impl;

import com.zh.bean.Tbook;
import com.zh.dao.TbookDao;
import com.zh.utils.BaseDao;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TbookDaoImpl extends BaseDao implements TbookDao {
    @Override
    public List<Tbook> findAll() throws SQLException {

        BeanListHandler<Tbook> handler = new BeanListHandler<>(Tbook.class);
        List<Tbook> tbookList = queryRunner.query("select * from t_book order by id desc", handler);
        return tbookList;
    }

    @Override
    public void save(Tbook tbook) throws SQLException {
        queryRunner.update("insert into t_book values(NULL ,?,?,?,?,?,?)",
        tbook.getName(),tbook.getPrice(),tbook.getAuthor(),tbook.getSales(),tbook.getStock(),tbook.getImg_path());
    }

    @Override
    public void updateById(Tbook tbook) throws SQLException {
        queryRunner.update("update t_book set name=?,price=?,author=?,sales=?,stock=?,img_path=? where id = ?",tbook.getName(),tbook.getPrice(),tbook.getAuthor(),tbook.getSales(),tbook.getStock(),tbook.getImg_path(),tbook.getId());
    }

    @Override
    public void deleteById(Integer id) throws SQLException {
        queryRunner.update("delete from t_book where id = ? " , id);
    }

    @Override
    public Tbook findById(Integer id) throws SQLException {
        BeanProcessor bean = new GenerousBeanProcessor();
        RowProcessor processor = new BasicRowProcessor(bean);
        BeanHandler<Tbook> handler = new BeanHandler<>(Tbook.class);
        Tbook tbook = queryRunner.query("select * from t_book where id = ?", handler,id);
        return tbook;
    }

    @Override
    public List<Tbook> page(Integer pageNumber) throws SQLException {
        String sql = "select * from t_book limit ? , ?";
        BeanListHandler<Tbook> handler = new BeanListHandler<>(Tbook.class);
        List<Tbook> tbookList = queryRunner.query(sql, handler, (pageNumber - 1) * pageSize, pageSize);
        return tbookList;
    }

    @Override
    public Integer pageRecord() throws SQLException {
        String sql = "select count(*) from t_book";
        ScalarHandler<Long> handler = new ScalarHandler<>();
        Long i = queryRunner.query(sql, handler);
        return i.intValue();
    }

    public List<Tbook> Fuzzyquery(String str) throws SQLException {
        RowProcessor processor = new BasicRowProcessor(new GenerousBeanProcessor());
        BeanListHandler<Tbook> handler = new BeanListHandler<>(Tbook.class);
        List<Tbook> tbookList = queryRunner.query("select * from t_book where name like ?", handler,"%"+str+"%");
        return tbookList;
    }

    @Override
    public Integer queryForPageTotalCount()  throws SQLException {
        String sql = "select count(*) from t_book";
        ScalarHandler<Long> handler = new ScalarHandler<>();
        Long i = queryRunner.query(sql, handler);
        return i.intValue();
    }

    @Override
    public List<Tbook> queryForPageItems(int begin, int pageSize)  throws SQLException {
        String sql = "select * from t_book order by id desc limit ?,?";
        return queryRunner.query(sql,new BeanListHandler<Tbook>(Tbook.class),begin,pageSize);
    }

    @Override
    public List<Tbook> findByPrice(int min, int max) throws SQLException {
        BeanListHandler<Tbook> handler = new BeanListHandler<>(Tbook.class);
        List<Tbook> tbookList = queryRunner.query("select * from t_book where price >=? and price <=? order  by price asc", handler,min,max);
        return tbookList;
    }

    @Override
    public Integer queryForPriceTotalCount(int min, int max)  throws SQLException {
        String sql = "select count(*) from t_book where price >=? and price <=? order  by price asc";
        ScalarHandler<Long> handler = new ScalarHandler<>();
        Long i = queryRunner.query(sql, handler,min,max);
        return i.intValue();
    }

    @Override
    public Integer queryForPageTotalCount(String name, String author, BigDecimal min, BigDecimal max) throws SQLException {
        StringBuilder sql = new StringBuilder("select count(*) from t_book where 1 = 1 ");
        ArrayList list = new ArrayList();
        if (name!= null && !"".equals(name)) {
            sql.append(" and name like ? ");
            list.add("%"+name+"%");
        }
        if (author != null && !"".equals(author)){
            sql.append(" and author like ? ");
            list.add("%"+author+"%");
        }
        if ((min != null && min.signum() == 1 ) && (max != null && max.signum() == 1)){
            //min值 大于 max值
            if (min.compareTo(max)==1){  //进行两值交换
                BigDecimal temp = min;
                min = max;
                max = temp;
            }
            sql.append(" and price between ? and ? ");
            list.add(min);
            list.add(max);
        }else if ( min != null && min.signum() == 1 ){
            sql.append(" and price > ? ");
            list.add(min);
        }else if ( max != null && max.signum() == 1 ){
            sql.append(" and price < ? ");
            list.add(max);
        }

        ScalarHandler<Long> handler = new ScalarHandler<>();
        Long i = queryRunner.query(sql.toString(), handler,list.toArray());
        return i.intValue();
    }

    @Override
    public List<Tbook> queryForPageItems(String name, String author, BigDecimal min, BigDecimal max, int begin, int pageSize) throws SQLException {
        StringBuilder sql = new StringBuilder("select * from t_book where 1 = 1 ");
        ArrayList list = new ArrayList();
        if (name!= null && !"".equals(name)) {
            sql.append(" and name like ? ");
            list.add("%"+name+"%");
        }
        if (author != null && !"".equals(author)){
            sql.append(" and author like ? ");
            list.add("%"+author+"%");
        }
        if ((min != null && min.signum() == 1 ) && (max != null && max.signum() == 1)){
            //min值 大于 max值
            if (min.compareTo(max)==1){  //进行两值交换
                BigDecimal temp = min;
                min = max;
                max = temp;
            }
            sql.append(" and price between ? and ? ");
            list.add(min);
            list.add(max);
        }else if ( min != null && min.signum() == 1 ){
            sql.append(" and price > ? ");
            list.add(min);
        }else if ( max != null && max.signum() == 1 ){
            sql.append(" and price < ? ");
            list.add(max);
        }

        String end = " order by id desc limit ?,?";
        sql.append(end);
        list.add(begin);
        list.add(pageSize);
        return queryRunner.query(sql.toString(),new BeanListHandler<Tbook>(Tbook.class),list.toArray());
    }
}
