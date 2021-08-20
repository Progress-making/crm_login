<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Crm 后台登录界面</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	$(function() {
		var $textInput = $("input:text:eq(0)");
		var $passwordInput = $("input:password:eq(0)");
		var $msgDiv = $("#null_form_tip");
		
		$textInput.on({
			"change" : function() {
				if ($(this).val() == null || $(this).val().trim() == "") {
					$msgDiv.css({
						"display" : "block"
					}).html("请输入用户名");
				} else {
					if ($passwordInput.val() == null || $passwordInput.val().trim() == "") {
						$msgDiv.css({
							"display" : "block"
						}).html("请输入密码");
					}
				    $passwordInput.on({
						"change" : function() {
							if ($(this).val() == null || $(this).val().trim() == "") {
								$msgDiv.css({
									"display" : "block"
								}).html("请输入密码");
							} else {
								$msgDiv.css({
									"display" : "none"
								});
							}
							
						}
					});
					
				}
			}
		});


		$("input:submit").click(function() {
			if ($textInput.val() == null || $textInput.val().trim() == ""){
				$msgDiv.css({
					"display" : "block"
				}).html("请输入用户名");
				return false;
			} else if ($passwordInput.val() == null || $passwordInput.val().trim() == "") {
				$msgDiv.css({
					"display" : "block"
				}).html("请输入密码");
				return false;
			} else {
				return true;
			}
		});
		
	});
</script>
<style type="text/css">
#null_form_tip {
	width: 200px;
	height: 20px;
	border: 1px solid #ffffff;
	margin: center;
	color: red;
	display: none;
}
</style>
</head>
<body>
	<div id="null_form_tip"></div>
	<div>
		<form action="user/login.do" method="post">
			<table>
				<thead>
					<tr>
						<td>Login</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Username:</td>
						<td><input type="text" name="userName" /></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><input type="password" name="userPwd" /></td>
					</tr>
					<tr>
						<td><input type="checkbox" name="lockMe" />记住我</td>
						<td><span>没有账号？请</span><a href="register.jsp">注册</a></td>
					</tr>
					<tr>
						<td><input type="submit" value="提交" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>