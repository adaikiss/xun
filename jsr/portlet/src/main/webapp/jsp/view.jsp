<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Xun Portlet View</title>
</head>
<body>

<portlet:renderURL var="myRenderURL">
    <portlet:param name="yourname" value='John Doe'/>
</portlet:renderURL>
<br/>
<portlet:actionURL var="myActionURL"/>

<div class="portlet-section-header">Action URL: <%= myActionURL %></div>
<form action="<%= myActionURL %>" method="POST">
         <span class="portlet-form-field-label">Name:</span>
         <input class="portlet-form-input-field" type="text" name="yourname"/>
         <input class="portlet-form-button" type="Submit"/>
</form>
</body>
</html>