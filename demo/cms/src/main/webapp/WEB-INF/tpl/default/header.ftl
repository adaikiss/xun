<div id="header" >
	<div class="main bg-alpha" style="">
		<div id="logo" class="l"></div>
		<div id="info" class="r">
			hello!
		</div>
		<div id="nav" class="r" style="">
			<ul>
			
<@channel_list parentId=null eager=true; channels>
	<#list channels as channel>
				<li class="nav-li">
					<a class="nav-menu" href="${base}/${channel.url}" title="${channel.description}">
						${channel.name}<#if channel.children?size gt 0><span class="nav-arrow"></span></#if>
					</a>
				</li>
	</#list>
</@channel_list>
				<li class="float-auto-height"></li>
			</ul>
		</div>
		<div class="float-auto-height"></div>
	</div>
</div>