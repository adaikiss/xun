<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<shiro:hasPermission name="admin">
admin
</shiro:hasPermission>
<shiro:hasPermission name="article:read">
permit to read articles!
</shiro:hasPermission>
<shiro:guest>
<jsp:forward page="login.jsp"></jsp:forward>
</shiro:guest>
</body>
</html>