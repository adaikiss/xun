<form action="${base}/user/${user.id}" method="POST">
Nick Name:<input name="nickName" value="${user.nickName}"></br>
Display Name:<input name="displayName" value="${user.displayName}"></br>
Login Name:<input name="loginName" value="${user.loginName}"></br>
Password:<input name="password" type="password"></br>
Repeat Password:<input name="password2" type="password"></br>
Email:<input name="email" value="${user.email}"></br>
Url:<input name="url" value="${user.url}"></br>
<input type="button" value="save">
</form>