<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:include page="header.jsp">
	<jsp:param name="msg" value="图书管理系统"/>
</jsp:include>

	<div id="main">
		<table  style="margin-top: 8px">
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_add.jsp">添加图书</a></td>
			</tr>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>

			<c:forEach items="${page.items}" var="book">
			<tr>
				<td>${book.name}</td>
				<td>${book.price}</td>
				<td>${book.author}</td>
				<td>${book.sales}</td>
				<td>${book.stock}</td>
				<td ><img src="${book.img_path}" width="50px" height="50px"></td>
				<td><a href="BookServlet?action=queryById&id=${book.id}&pageNo=${page.pageNo}">修改</a></td>
				<td><a href="javascript:void(0)" onclick="mydelete('${book.id}',this,${page.pageNo})">删除</a></td>
<%--				数字或者字符串都加单引号--%>
			</tr>
			</c:forEach>
		</table>
		
		<jsp:include page="/pages/common/page.jsp"></jsp:include>

	</div>

<%--	<%@include file="/pages/common/botton.jsp"%>--%>
<jsp:include page="/pages/common/botton.jsp"></jsp:include>

</body>
</html>
<script>
	function check(){
		var inputobj = document.getElementById("pn_input");
		var s1 = /^[0-9]*$/;
		if(!s1.test(inputobj.value)){
			alert("请您输入正整数页数");
			inputobj.value="";
		}
	}

	function mydelete(id,aobj,pageNo){
		var name = aobj.parentElement.parentElement.cells[0].innerHTML;
		if(window.confirm("您确定删除"+name+"吗?")){
			window.location.href = "BookServlet?action=delete&id="+id+"&pageNo="+pageNo;
		}
	}
</script>