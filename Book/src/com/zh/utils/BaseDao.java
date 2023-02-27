package com.zh.utils;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.RowProcessor;

/**
 * BaseDao的目地就是去优化dao实现类
 */
public abstract class BaseDao {
    public QueryRunner queryRunner ;
    public int pageSize = 4 ;
    public BaseDao(){
        queryRunner = new QueryRunner(com.zh.utils.MyDataSource.getDataSource());
    }
    public RowProcessor hum(){
        //开启下划线驼峰转换用
        BeanProcessor bean = new BeanProcessor();
        return new BasicRowProcessor(bean);
    }
}
