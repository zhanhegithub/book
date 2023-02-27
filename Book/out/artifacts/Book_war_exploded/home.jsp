<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <style>
    ::-webkit-scrollbar{
      display: none;
    }
  </style>
  <meta charset="UTF-8">
  <title>书城首页</title>
  <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
  <link type="text/css" rel="stylesheet" href="static/css/style.css" >
</head>
<body>

<div id="header">
  <img class="logo_img" alt="" src="static/img/logo.gif" width="230px" height="80px" >
  <span class="wel_word">网上书城</span>
  <div>
    <c:if test="${empty user}">
      <a href="pages/user/login.jsp">登录</a> |
      <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
    </c:if>
    <c:if test="${not empty user}">
      <span>欢迎<span class="um_span">${user.username}</span>光临网上书城</span>
      <a href="OrderServlet?action=listOrder">我的订单</a>
      <a href="UserServlet?action=logout">注销</a>&nbsp;&nbsp;
    </c:if>
    <a href="javascript:void(0)" onclick="toCart()">购物车</a>
    <a href="pages/manager/login.jsp">后台管理</a>
  </div>
</div>


<div id="main" style="height: 480px; width: 1250px">
  <div id="book">
    <div class="book_cond" style="margin-left: 320px">
      <form action="BookServlet" method="get">
        <input type="hidden" name="action" value="searchPage">
        书名：<input type="text" name="name" value="${name}">
        作者：<input type="text" name="author" value="${author}">
        价格：<input id="min" type="text" name="min" value="${min}"> 元 -
        <input id="max" type="text" name="max" value="${max}"> 元
        <input type="submit" value="查询" />
      </form>
    </div>

    <div style="text-align: center">
      <c:if test="${empty sessionScope.cart.items}">
        <%--购物车为空的输出--%>
        <span> </span>
        <div>
          <span style="color: red">当前购物车为空</span>
        </div>
      </c:if>
      <c:if test="${not empty sessionScope.cart.items}">
        <%--购物车非空的输出--%>
        <span>您的购物车中有 ${sessionScope.cart.totalCount} 件商品</span>
        <div>
          您刚刚将<span style="color: red">${sessionScope.bookName}</span>加入到了购物车中
        </div>
      </c:if>
    </div>


    <c:choose>
      <c:when test="${empty page.items}">
        <h1 style="color: red;text-align: center;">没有找到数据</h1>
      </c:when>
      <c:otherwise>


        <c:forEach items="${page.items}" var="book">
          <div class="b_list">
            <div class="img_div">
              <img class="book_img" alt="" src=${book.img_path} />
            </div>
            <div class="book_info" style="width: 150px">
              <div class="book_name">
                <span class="sp1">书名:</span>
                <span class="sp2">${book.name}</span>
              </div>
              <div class="book_author">
                <span class="sp1">作者:</span>
                <span class="sp2">${book.author}</span>
              </div>
              <div class="book_price">
                <span class="sp1">价格:</span>
                <span class="sp2">${book.price}</span>
              </div>
              <div class="book_sales">
                <span class="sp1">销量:</span>
                <span class="sp2">${book.sales}</span>
              </div>
              <div class="book_amount">
                <span class="sp1">库存:</span>
                <span class="sp2">${book.stock}</span>
              </div>
              <div class="book_add">

                <input onclick="addToCart('${book.id}')" type="button" value="加入购物车"/>
              </div>
            </div>
          </div>
        </c:forEach>
      </c:otherwise>
    </c:choose>

  </div>

  <jsp:include page="/pages/common/page.jsp"></jsp:include>


</div>

<%@include file="/pages/common/botton.jsp"%>
</body>
</html>
<script>
  function addToCart(bookId){
    //alert(bookId);
    if ('${sessionScope.user}' !=''){
      window.location.href="CartServlet?action=addItem&id="+bookId;
    } else {
      alert("登录再添加购物车")
    }
  }
  function toCart(){
    if ('${sessionScope.user}' !=''){
      window.location.href="pages/cart/cart.jsp";
    } else {
      var msg="请先登录再访问购物车"
      window.location.href="pages/user/login.jsp?msg="+msg;
    }
  }
</script>