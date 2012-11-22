<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Strict//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><sitemesh:write property="title"/></title>
<link href="${base}/css/style.css" rel="stylesheet" type="text/css">
<sitemesh:write property="head"/>
</head>
<body>
<div id="wrapper">
<#include "/WEB-INF/content/decorators/fragments/head.ftl"/>
<sitemesh:write property="body"/>
<#include "/WEB-INF/content/decorators/fragments/foot.ftl"/>
</div>
</body>
</html>