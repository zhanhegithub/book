<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>会员登录页面</title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <link type="text/css" rel="stylesheet" href="static/css/style.css">
</head>
<body>

<div id="login_header">
    <a href="index.jsp"></a>
    <img class="logo_img" alt="" src="static/img/logo.gif" width="230px" height="80px">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎登录</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>会员</h1>
                    <a href="pages/user/regist.jsp">立即注册</a>
                </div>
                <div class="msg_cont">
                    <b></b>
                    <span class="errorMsg">
                        <c:choose>
                            <c:when test="${not empty param.msg}">
                                ${param.msg}
                            </c:when>
                            <c:otherwise>
                                <c:out value="${msg}" default="请输入用户名与密码"></c:out>
                            </c:otherwise>
                        </c:choose>

                    </span>
                </div>
                <div class="form">
                    <form action="UserServlet" method="post">
                        <input type="hidden" name="action" value="login">
                        <label>用户名称：</label>
                        <input value="${username}" class="itxt" type="text" placeholder="请输入用户名" autocomplete="off"
                               tabindex="1" name="username"/>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input value="${password}" class="itxt" type="password" placeholder="请输入密码" autocomplete="off"
                               tabindex="1" name="password"/>
                        <br/>
                        <br/>
                        <input type="submit" value="登录" id="sub_btn"/>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%@include file="/pages/common/botton.jsp" %>
</body>
</html>