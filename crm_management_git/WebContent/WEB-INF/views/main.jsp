<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Crm 后台主页面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/common.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.cookie.js"></script>
<script type="text/javascript">
	$(function(){
		$.ajax({
			url:"${pageContext.request.contextPath }/queryByCookie",
			type:"post",
			async:true,
			dataType :"json",
			success:function(data){
				if (data.code == 300) {
					alert(data.msg);
					window.location.href = "index.jsp";
					return;
				}
				if (data.code == 500) {
					window.location.href = "${pageContext.request.contextPath }/toErrorPage?msg=" + data.msg + "&code=" + data.code;
					return;
				}
				$("span").html(data.result.userName);
			}
		});
		
		$("button").on({
			"mouseover":function(){
				$(this).css({"color":"red", "background-color":"lightblue"});
			},
			"mouseout":function(){
				$(this).removeAttr("style");
			}
		});
		
		// 安全退出 按钮被点击后 清除客户端用户的cookie信息，并跳转至首页面
		$("button:eq(3)").click(function(){
			$.cookie("userId", null, {expires : -1, path : "/"});
			// 直接退回首页 默认是/index.jsp
			window.location.href = "${pageContext.request.contextPath}/index.jsp";
		});
		
		$("button:eq(2)").click(function(){
			// 注意：a标签中的href和window.location.href都是重定向，都不能访问WEB-INF下的页面
			// 可通过指定action在Servlet中进行转发的方式解决
			$("#iframe_1").attr("src", "toUpdatePwdPage");
			//window.location.href = "updatePwd.do";
		});
		
		$("button:eq(1)").click(function(){
			$("#iframe_1").attr("src", "toEditPage");
			//$(window.frames["iframe_1"]).attr("src", "toEditPage.do");
			//window.location.href = "toEditPage.do";
		});
		
		$("button:eq(0)").click(function(){
			$("#iframe_1").attr("src", "toUserListPage");
		});
	});
</script>
<style type="text/css">
#div_top{
	width:100%;
	height:10%;
}

#div_mid{
	width:100%;
	height:90%;
	position:relative;
	border:1px solid green;
	
}

#div_mid ul {
	width:20%;
	height:100%;
	border:1px solid red;
	margin:0;
	position:relative;
	top:0;
}

#div_mid iframe {
	width:75%;
	height:100%;
	position:absolute;
	top:0;
	right:0;
}
</style>
</head>
<body>
	<div id="div_top"><strong>欢迎：</strong><span></span></div>
	<div id="div_mid">
		<ul>
			<li><button>查看所有用户</button></li>
			<li><button>编辑资料</button></li>
			<li><button>修改密码</button></li>
			<li><button>安全退出</button></li>
		</ul>
		<iframe id="iframe_1" name="ifram_show" frameborder="1"  title="inner page" scrolling="no" src="toUserListPage"></iframe>
	</div>
	
</body>
</html>