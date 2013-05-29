<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
.info{color:green;}
.error{color:red;}
</style>
<title>后台登录</title>
</head>
<body>
	<div id="login">
		<div id="msg"></div>
		<form action="${base }/admin/login.do" method="POST">
			<input name="username">
			<input name="password" type="password">
			<label><input name="rememberMe" type="checkbox" value="true">记住登录状态</label>
			<input id="loginBtn" value="登录" type="submit">
		</form>
	</div>
	<script type="text/javascript" src="${base }/rs/jquery-1.9.1.min.js"></script>
	<script type="text/javascript">
		$('form').submit(function(){
			var form = $(this);
			if(form.data('submitting')){
				return;
			}
			form.data('submitting', true);
			$.ajax({
				url : form.attr('action'),
				type : form.attr('method'),
				data : form.serialize(),
				dataType : 'JSON',
				success : function(result){
					if(!result){
						$('#msg').addClass('error').text('服务器错误!');
					}
					if(result.success){
						$('#msg').addClass('info').text('登录成功!');
						window.location.href = '${base}/app.html';
					}else{
						$('#msg').addClass('error').text(result.msg);
					}
				},
				complete : function(){
					form.removeData('submitting');
				}
			});
			return false;
		})
	</script>
</body>
</html>