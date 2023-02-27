package com.zh.service;

import com.zh.bean.User;
import com.zh.bean.Admin;

import java.sql.SQLException;

public interface AdminService {
    public Admin adminLogin(Admin admin) throws SQLException;
}
