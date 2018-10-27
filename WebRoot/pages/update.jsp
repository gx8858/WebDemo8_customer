<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑客户信息</title>
</head>
<body>


<form action="${ pageContext.request.contextPath }/update" method="post">
	
	<!-- 隐藏域 -->
	<input type="hidden" name="id" value="${ c.id }" />

	<table align="center" border="1" width="600" cellpadding="10">
		<tr>
			<td>用户名</td>
			<td>
				<input type="text" name="username" value="${ c.username }"/>
			</td>
		</tr>
		<tr>
			<td>性别</td>
			<td>
				<input type="radio" name="gender" value="男"  <c:if test="${ c.gender eq '男' }">checked</c:if>  />男
				<input type="radio" name="gender" value="女"  <c:if test="${ c.gender eq '女' }">checked</c:if>   />女
			</td>
		</tr>
		<tr>
			<td>生日</td>
			<td>
				<input type="text" name="birthday" value="${ c.birthday }"/>
			</td>
		</tr>
		<tr>
			<td>电话</td>
			<td>
				<input type="text" name="cellphone" value="${ c.cellphone }"/>
			</td>
		</tr>
		<tr>
			<td>邮箱</td>
			<td>
				<input type="text" name="email" value="${ c.email }"/>
			</td>
		</tr>
		<tr>
			<td>爱好</td>
			<td>
				<input type="checkbox" name="love" value="骑马"  <c:if test="${ fn:contains( c.love , '骑马' ) }">checked</c:if>  />骑马
				<input type="checkbox" name="love" value="高尔夫" <c:if test="${ fn:contains( c.love , '高尔夫' ) }">checked</c:if>  />高尔夫
				<input type="checkbox" name="love" value="篮球" <c:if test="${ fn:contains( c.love , '篮球' ) }">checked</c:if>  />篮球
				<input type="checkbox" name="love" value="吹牛X"  <c:if test="${ fn:contains( c.love , '吹牛X' ) }">checked</c:if> />吹牛X
			</td>
		</tr>
		<tr>
			<td>客户类型</td>
			<td>
				<select name="type">
					<option value="青铜会员" <c:if test="${ c.type eq '青铜会员' }">selected</c:if>  >青铜会员</option>
					<option value="白银会员" <c:if test="${ c.type eq '白银会员' }">selected</c:if> >白银会员</option>
					<option value="黄金会员" <c:if test="${ c.type eq '黄金会员' }">selected</c:if> >黄金会员</option>
					<option value="钻石会员" <c:if test="${ c.type eq '钻石会员' }">selected</c:if> >钻石会员</option>
				</select>
			</td>
		</tr>
		<tr align="center">
			<td colspan="2">
				<input type="submit" value="提交" />
			</td>
		</tr>
	</table>
</form>

</body>
</html>