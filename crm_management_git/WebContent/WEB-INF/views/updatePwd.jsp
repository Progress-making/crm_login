<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>密码修改</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/common.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.cookie.js"></script>
<script type="text/javascript">
	$(function(){
		
		$("input[type='button']").click(function(){
			$.ajax({
				url:"${pageContext.request.contextPath }/executeUpdatePwd.do",
				data:$("#form_update_pwd").serialize(),
					/* oldPwd:$("input[type='password']:eq(0)").val(),
					newPwd:$("input[type='password']:eq(1)").val(),
					confirmPwd:$("input[type='password']:eq(2)").val() */
					
				type:"post",
				async:true,
				dataType:"json",
				success:function(data){
					alert(data.msg);
					if (data.code == 200) {
						window.location.href = "${pageContext.request.contextPath }/index.jsp";
					} else {
						window.location.href = "${pageContext.request.contextPath }/toErrorPage.do?msg=" + data.msg + "&code=" + data.code;
					}
					
				}
			});
		});
	});
</script>
</head>
<body>
	<form id="form_update_pwd">
		<table>
			<thead><tr><td>UpdatePassword</td></tr></thead>
			<tbody>
				<tr><td>原始密码：</td><td><input type="password" name="oldPwd"/></td></tr>
				<tr><td>新密码：</td><td><input type="password" name="newPwd"/></td></tr>
				<tr><td>确认密码：</td><td><input type="password" name="confirmPwd"/></td></tr>
				<tr><td colspan="2" align="center"><input type="button" value="确定"/></td></tr>
			</tbody>
		</table>
	</form>
</body>
</html>