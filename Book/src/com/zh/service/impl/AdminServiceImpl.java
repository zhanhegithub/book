package com.zh.service.impl;

import com.zh.bean.User;
import com.zh.bean.Admin;
import com.zh.dao.AdminDao;
import com.zh.dao.impl.AdminDaoImpl;
import com.zh.service.AdminService;

import java.sql.SQLException;

public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao = new AdminDaoImpl();
    @Override
    public Admin adminLogin(Admin admin) throws SQLException {
        return adminDao.queryAdminByUsernameAndPassword(admin.getUsername(),admin.getPassword());
    }
}
