package com.zh.dao;

import com.zh.bean.User;
import com.zh.bean.Admin;
import com.zh.utils.BaseInterface;

import java.sql.SQLException;

public interface AdminDao extends BaseInterface<Admin> {
    public Admin queryAdminByUsernameAndPassword(String username, String password) throws SQLException;
}
