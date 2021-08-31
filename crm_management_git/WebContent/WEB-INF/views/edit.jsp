<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit User Information</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/common.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.cookie.js"></script>
<script type="text/javascript">
	$(function(){
		$("input[type='button']").click(function(){
			$.ajax({
				url:"${pageContext.request.contextPath }/edit.do",
				data:$("#form_edit_user").serialize(),
				type:"post",
				async:true,
				dataType:"json",
				success:function(data){
					alert(data.msg);
					if (data.code == 200) {
						window.top.location.href = "${pageContext.request.contextPath }/main.do";
					}
				}
			});
		});
	});
</script>
</head>
<body>
<form id="form_edit_user">
	<table>
		<thead><tr><td>Edit</td></tr></thead>
		<tbody>
			<tr><td>用户名：</td><td><input type="text" name="userName" value="${userInfo.userName }"/></td></tr>
			<tr><td>真实姓名：</td><td><input type="text" name="trueName" value="${userInfo.trueName }"/></td></tr>
			<tr><td colspan="2" align="center"><input type="button" value="提交"/></td></tr>
		</tbody>
	</table>
</form>
</body>
</html>