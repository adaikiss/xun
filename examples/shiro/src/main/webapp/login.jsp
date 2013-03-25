<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
admin:can visit /admin.jsp, /anyrole.jsp
manager:can visit /manager.jsp, /anyrole.jsp
system:can visit /admin.jsp, /manager.jsp, /system.jsp, /anyrole.jsp
<form action="/login.svl" method="post">
	username:<input name="username">
	password:<input name="password" type="password">
	<input type="submit" value="submit">
</form>
</body>
</html>