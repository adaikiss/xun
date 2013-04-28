<form action="${base}/user" method="PUT">
<input type="hidden" name="_method" value="PUT">
<#if errors?has_content>
<#list errors?keys as field>
<p>${errors[field]}</p>
</#list>
</#if>
Nick Name:<input name="nickName"></br>
Display Name:<input name="displayName"></br>
Login Name:<input name="loginName"><span class="error">${(errors['loginName'])!}</span></br>
Password:<input name="password" type="password"></br>
Repeat Password:<input name="password2" type="password"></br>
Email:<input name="email"></br>
Url:<input name="url"></br>
<input type="button" value="save">
</form>
