<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Crm 后台主页面</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.cookie.js"></script>
<script type="text/javascript">
	$(function(){
		/* $("span").html($.cookie("name")); */
		$.ajax({
			url:"${pageContext.request.contextPath }/user/query.do",
			data:{
				userId:$.cookie("userId")
			},
			type:"post",
			async:true,
			dataType :"json",
			success:function(data){
				alert(data.userName);
				$("span").html(data.userName);
			}
		});
	});
</script>
</head>
<body>
	<strong>欢迎：</strong><span></span>
</body>
</html>