<html>
<head>
<title>Xun::CMS</title>
<meta name="keywords" content="xun, cms" />
<meta name="description" content="xun cms" />
</head>
<body>
<div id="indexMain">
	<div class="main">
		<div class="bg-alpha" style="width:250px;height:200px;padding:5px;color:#FFF;">
			<ul>
<@article_page page_page=0 page_size=10 filter_EQE_status="PostStatus.Normal" page_sort_date="DESC";page>
	<#if page.size = 0>
	<#else>
		<#list page.content as article>
				<li>${article.title}</li>
		</#list>
	</#if>
</@article_page>
			</ul>
			<div class="button full blue" style="width:100px;">light</div>
		</div>
	</div>
</div>
</body>
</html>