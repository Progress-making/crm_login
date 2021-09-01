<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/common.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.cookie.js"></script>
</head>
<body>
	<table border="1" align="center" cellpadding="2px" cellspacing="0">
		<thead><tr><td colspan="3" align="center">用户信息列表</td></tr></thead>
		<tbody>
			<tr>
				<th>编号</th>
				<th>用户名</th>
				<th>真实姓名</th>
			</tr>
			<c:if test="${!empty userList}">
			<c:forEach items="${userList }" var="user">
			<tr>
				<td>${user.id }</td>
				<td>${user.userName }</td>
				<td>${user.trueName }</td>
			</tr>
			</c:forEach>
			</c:if>
		</tbody>
	</table>

</body>
</html>