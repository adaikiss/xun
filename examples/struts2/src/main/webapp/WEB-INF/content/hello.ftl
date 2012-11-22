<@list type='User' ; u>
<#assign list = u>
</@list>
<html>
<head>
<title>freemarker sample</title>
<link rel="stylesheet" href="http://docs.sencha.com/ext-js/4-0/resources/css/app.css" type="text/css">
<script src="http://docs.sencha.com/ext-js/4-0/extjs/ext-all.js"></script>
<script type="text/javascript">
Ext.onReady(function(){
	Ext.define('User', {
		extend: 'Ext.data.Model',
		fields: [
			{name: 'id',    type: 'int'},
			{name: 'displayName',  type: 'string'},
			{name: 'loginName',  type: 'string'},
			{name: 'email',  type: 'string'},
			{name: 'credit', type: 'int'}
		]
	});
	var data = {
		users: [
<#list list as user>
			{
				id : ${user.id},
				displayName : '${user.displayName}',
				loginName : '${user.loginName}',
				email : '${user.email}',
				credit : <#if user.credit != null>${user.credit.credit1}<#else>0</#if>
			}<#if user_has_next>,</#if>
</#list>
		]
	};
	var store = new Ext.data.Store({
		autoLoad: true,
		model: 'User',
		data : data,
		proxy: {
			type: 'memory',
			reader: {
				type: 'json',
				root: 'users'
			}
		}
	});
	Ext.create('Ext.grid.Panel', {
		closable : false,
		width: 700,
		height: 500,
		stripeRows: true,
		loadMask: true,
		renderTo : 'main',
		store : store,
		columns:[{
			text: "Id",
			dataIndex: 'id',
			width : 40,
			align: 'right',
			sortable: true
		},{
			text: "名字",
			dataIndex: 'displayName',
			width: 100,
			sortable: true
		},{
			text: "账号",
			dataIndex: 'loginName',
			width: 150,
			sortable: true
		},{
			text: "email",
			dataIndex: 'email',
			width: 100,
			sortable: true
		},{
			text: "积分",
			dataIndex: 'credit',
			flex: 1,
			sortable: true
		}]
	});
});
</script>
</head>
<body>
<div id="main">
</div>
<div>
<form action="ajax/user!testList.action">
<input name="users[0].jb.length" value="1">
<input name="users[1].jb.length" value="2">
<input name="users[2].jb.length" value="3">
<input name="users[3].jb.length" value="4">
<input name="users[4].jb.length" value="5">
<input type="submit" value="submit">
</form>
</div>
</body>
</html>