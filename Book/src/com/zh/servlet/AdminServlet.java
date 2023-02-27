package com.zh.servlet;

import com.zh.bean.Admin;
import com.zh.service.AdminService;
import com.zh.service.impl.AdminServiceImpl;
import com.zh.utils.BaseServlet;
import com.zh.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
@WebServlet(name = "AdminServlet",value = "/AdminServlet")
public class AdminServlet extends BaseServlet {

    protected void adminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println("parameterMap = " + parameterMap);
        Admin admin = new Admin();
        WebUtils.copyParamToBean(parameterMap,admin);
        System.out.println("admin = " + admin);

        AdminService adminService = new AdminServiceImpl();
        // 调用 userService.login()登录处理业务

        try {
            Admin myuser = adminService.adminLogin(admin);
            // 如果等于null,说明登录 失败!
            if (myuser == null) {
                //	跳回登录页面
                request.setAttribute("msg","账号或登录密码不正确!");

                request.getRequestDispatcher("/pages/user/root_login.jsp").forward(request, response);
            } else {
                // 登录 成功
                //跳到成功页面login_success.html
                request.setAttribute("msg","欢迎回来");
                request.getSession().setAttribute("admin",myuser);
                request.getRequestDispatcher("/pages/manager/manager.jsp").forward(request, response);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
