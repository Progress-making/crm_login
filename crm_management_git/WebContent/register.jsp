<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Crm 后台注册界面</title>
<link type="text/css" href="/js/common.js">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	$(function(){
		
		var usernameFlag = true;
		// 用户名输入触发的事件
		$("input:text:eq(0)").on({
			"blur":function(){
				$.ajax({
					url:"${pageContext.request.contextPath }/user/query.do",
					data:{
						"userName":$(this).val()
					},
					async:true,
					type:"post",
					dataType :"json",
					success:function(data){
						/* alert(data); */
						if (data == null) {
							/* alert("返回值为null"); */
							if($("input:text:eq(0)").val() == null || $("input:text:eq(0)").val().trim() == "") {
								/* alert("当前参数为null"); */
								$("input:text:eq(0)").next().css("color","red").html("×");
								$("tbody tr:eq(1)").find("span").css("color","red").html("用户名为必填项");
								usernameFlag = true;
							} else {
								$("input:text:eq(0)").next().css("color","green").html("√");
								$("tbody tr:eq(1)").find("span").empty();
								usernameFlag = false;
							}
							
						} else {
							$("input:text:eq(0)").next().css("color","red").html("×");
							$("tbody tr:eq(1)").find("span").css("color","red").html("此用户已存在");
							usernameFlag = true;
						}
						
					},
					/* error:function(xmlRequest, errorMsg){
						if (errorMsg == null) {
							$(this).next().css("color","green").html("√");
							$("tbody tr:eq(1)").find("span").empty();
							usernameFlag = false;
						}
					} */
				});
				
				/* if($(this).val == null || $(this).val() == "") {
					$(this).next().css("color","red").html("×");
					$("tbody tr:eq(1)").find("span").css("color","red").html("用户名为必填项");
					usernameFlag = true;
				} else {
					$("tbody tr:eq(1)").find("span").empty();
				} */
			}
		});
		
		var passwordFlag = true;
		// 原始密码触发的事件
		$("input:password:eq(0)").on({
			"blur" : function(){
				// 若密码不匹配正则表达式 给span标签添上“×”标记，并在下方生成密码有效提示信息；反之，则添加“√”标记，并清除下方提示信息。
				if (!$(this).val().match(/^\w{6,12}$/)) {
					$(this).next().css("color","red").html("×");
					$("tbody tr:eq(3)").find("span").css("color","red").html("密码长度必须为6-12位，且由数字、字母或'_'组成");
					passwordFlag = true;
				} else {
					$(this).next().css("color","green").html("√");
					$("tbody tr:eq(3)").find("span").empty();
					passwordFlag = false;
				}
				
				// 若密码为空，在此输入框下方生成“密码为必填项”的提示信息
				if ($(this).val() == null || $(this).val().trim() == "") {
					$("tbody tr:eq(3)").find("span").css("color","red").html("密码为必填项");
				}
			}
		});
		
		var confirmFlag = true;
		// 确认密码触发的事件
		$("input:password:eq(1)").on({
			"blur" : function(){
				if ($(this).val() != $("input:password:eq(0)").val()) {
					$(this).next().css("color","red").html("×");
					$("tbody tr:eq(5)").find("span").css("color","red").html("密码输入不一致");
					confirmFlag = true;
				} else {
					$(this).next().css("color","green").html("√");
					$("tbody tr:eq(5)").find("span").empty();
					confirmFlag = false;
				}
				
				if ($(this).val() == null || $(this).val().trim() == "") {
					$(this).next().css("color","red").html("×");
					confirmFlag = true;
					$("tbody tr:eq(5)").find("span").css("color","red").html("确认密码为必填项");
				}
			}
		});
		
		var truenameFlag = true;
		// 真实姓名触发的‘blur’事件
		$("input:text:eq(1)").blur(function(){
			if ($(this).val() == null || $(this).val().trim() == "") {
				$(this).next().css("color","red").html("×");
				$("tbody tr:eq(7)").find("span").css("color","red").html("真实姓名为必填项");
				truenameFlag = true;
			} else {
				$(this).next().css("color","green").html("√");
				$("tbody tr:eq(5)").find("span").empty();
				truenameFlag = false;
			}
		});
		
		// 提交按钮触发的点击事件
		$("input:submit").click(function(){
			if (usernameFlag || passwordFlag || confirmFlag || truenameFlag) {
				return false;
			}
		});
	});
</script>
</head>
<body>
	<form action="user/add.do" method="post" >
		<table>
			<thead><tr><td><strong>Register</strong></td><td align="right"><span>已有账号？</span><a href="index.jsp">点此登录</a></td></tr></thead>
			<tbody>
				<tr><td>用户名：</td><td><input type="text" name="userName" placeholder="请输入用户名"/><span></span></td></tr>
				<tr><td><span></span></td></tr>
				<tr><td>用户密码：</td><td><input type="password" name="userPwd" placeholder="请输入初始密码"/><span></span></td></tr>
				<tr><td><span></span></td></tr>
				<tr><td>确认密码：</td><td><input type="password" name="confirmPwd" placeholder="请输入确认密码"/><span></span></td></tr>
				<tr><td><span></span></td></tr>
				<tr><td>真实姓名：</td><td><input type="text" name="trueName" placeholder="请输入真实姓名"/><span></span></td></tr>
				<tr><td><span></span></td></tr>
			</tbody>
			<tfoot><tr><td colspan="2" align="center"><input type="submit" value="立即注册"/></td></tr></tfoot>
		</table>
		<p>Copyright ©2021 crm.xiaowen.com 1.0.0 All Rights Reserved</p>
	</form>
	
</body>
</html>