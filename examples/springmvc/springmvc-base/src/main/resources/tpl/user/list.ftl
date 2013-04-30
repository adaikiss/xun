<table>
 <thead>
  <tr>
   <th>No.</th>
   <th>Nick Name</th>
   <th>Display Name</th>
   <th>Login Name</th>
   <th>email</th>
   <th>url</th>
   <th>operation</th>
  </tr>
 </thead>
 <tbody>
<#if list?size gt 0>
 <#list list as user>
  <tr>
    <td>${user.id}</td>
    <td><span class="load" data-url="${base}/user/${user.id}">${user.nickName}</span></td>
    <td>${user.displayName}</td>
    <td>${user.loginName}</td>
    <td>${user.email}</td>
    <td>${user.url}</td>
    <td><span class="load" data-url="${base}/user/${user.id}/edit">edit</span><span class="load" data-url="${base}/user/${user.id}" data-type="DELETE">delete</span></td>
  </tr>
  </#list>
<#else>
  <tr>
   <td colspan="6">
	No data
   </td>
  </tr>
</#if>
 </tbody>
</table>
