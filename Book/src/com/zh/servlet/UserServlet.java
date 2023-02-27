package com.zh.servlet;

import com.zh.bean.User;
import com.zh.service.UserService;
import com.zh.service.impl.UserServiceImpl;
import com.zh.utils.CookieUtils;
import com.zh.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

@WebServlet(name = "UserServlet", value = "/UserServlet")
public class UserServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("action = " + action);
        Class claxx = this.getClass();//得到userservlet的描述对象
        try {
            Method method = claxx.getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,request,response);//this代表UserServlet类对象
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //不用自己手动的一个个获取用户提交上来的参数了
        //        String username = request.getParameter("username");
//        String password = request.getParameter("password");

        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        WebUtils.copyParamToBean(parameterMap,user);

        UserService userService = new UserServiceImpl();
        // 调用 userService.login()登录处理业务

        try {
            User myuser = userService.login(user);
            // 如果等于null,说明登录 失败!
            if (myuser == null) {
                //	跳回登录页面
                request.setAttribute("msg","账号或登录密码不正确!");
                request.setAttribute("username",user.getUsername());
                request.setAttribute("password",user.getPassword());
                request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            } else {
                // 登录 成功
                //跳到成功页面login_success.html
                Cookie nameCookie = new Cookie("username",myuser.getUsername());
                Cookie passCookie = new Cookie("password",myuser.getPassword());
                nameCookie.setMaxAge(60*60*24*7);
                passCookie.setMaxAge(60*60*24*7);
                response.addCookie(nameCookie);
                response.addCookie(passCookie);

                HttpSession session = request.getSession();//会话作用域
                session.setAttribute("user",myuser);//用户登陆成功后的个人信息会保存到session会话作用域
                request.setAttribute("msg","欢迎回来");
                if (request.getParameter("zhurl") !=null && !request.getParameter("zhurl").equals("")){
                    request.getRequestDispatcher(request.getParameter("zhurl")).forward(request, response);
                }else {
                    request.getRequestDispatcher("/pages/user/success.jsp").forward(request, response);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取Session 中的验证码
        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 删除 Session 中的验证码
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        String code = request.getParameter("code");//验证码
        System.out.println("code = " + code);
        System.out.println("token = " + token);

        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        WebUtils.copyParamToBean(parameterMap,user);

        request.setAttribute("user",user);//为了回显
        UserService userService = new UserServiceImpl();

        if (token.equals(code)) {
            try {
                if (userService.existsUsername(user.getUsername())) {
                    request.setAttribute("msg","用户名已经存在请更换");
                    // 跳回注册页面
                    request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
                } else {
                    userService.registUser(user);
                    HttpSession session = request.getSession();
                    session.setAttribute("user",user);
                    request.setAttribute("msg","注册成功");
                    request.getRequestDispatcher("/pages/user/success.jsp").forward(request, response);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            request.setAttribute("msg","验证码不正确");
            request.getRequestDispatcher("pages/user/regist.jsp").forward(request, response);
        }
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //	1、销毁Session 中用户登录的信息（或者销毁Session）
        HttpSession session = request.getSession();
        session.invalidate();
        Cookie nameCookie = CookieUtils.findCookie("username", request.getCookies());
        Cookie passCookie = CookieUtils.findCookie("password", request.getCookies());
        if (nameCookie!=null){
            nameCookie.setMaxAge(0);
            response.addCookie(nameCookie);
        }
        if (passCookie!=null){
            passCookie.setMaxAge(0);
            response.addCookie(passCookie);
        }
        response.sendRedirect("index.jsp");
    }

}
