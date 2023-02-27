package com.zh.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

//单例模式
public class MyDataSource {

    public static DataSource dataSource = null ;

    private MyDataSource(){
    }

    public static DataSource getDataSource(){
        if(dataSource == null) {
            try {
                Properties pro = new Properties();
                pro.load(MyDataSource.class.getClassLoader().getResourceAsStream("jdbc.properties.properties"));
                //创建一个数据源对象
                dataSource = DruidDataSourceFactory.createDataSource(pro);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dataSource;
    }
    public static Date StringToDate(String sdate) {
        String str = sdate;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = null;
        try {
            d = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date = new Date(d.getTime());
        return date;
    }
}
