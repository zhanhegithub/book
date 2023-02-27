package com.zh.dao;

import com.zh.bean.User;
import com.zh.utils.BaseDao;
import com.zh.utils.BaseInterface;

import java.sql.SQLException;

//userdao接口继承BaseInterFace
public interface UserDao extends BaseInterface<User> {
    /**
     *	根据用户名查询用户信息
     *	@param username 用户名
     *	@return 如果返回null,说明没有这个用户。反之亦然
     */
    public User queryUserByUsername(String username) throws SQLException;

    /**
     *	根据 用户名和密码查询用户信息
     *	@param username
     *	@param password
     *	@return 如果返回null,说明用户名或密码错误,反之亦然
     */
    public User queryUserByUsernameAndPassword(String username,String password) throws SQLException ;

}
