<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="${ pageContext.request.contextPath }/addCustomer" method="post">
	<table align="center" border="1" width="600" cellpadding="10">
		<tr>
			<td>用户名</td>
			<td>
				<input type="text" name="username" />
			</td>
		</tr>
		<tr>
			<td>性别</td>
			<td>
				<input type="radio" name="gender" value="男" checked="checked"/>男
				<input type="radio" name="gender" value="女"/>女
			</td>
		</tr>
		<tr>
			<td>生日</td>
			<td>
				<input type="text" name="birthday" />
			</td>
		</tr>
		<tr>
			<td>电话</td>
			<td>
				<input type="text" name="cellphone" />
			</td>
		</tr>
		<tr>
			<td>邮箱</td>
			<td>
				<input type="text" name="email" />
			</td>
		</tr>
		<tr>
			<td>爱好</td>
			<td>
				<input type="checkbox" name="love" value="骑马" checked="checked"/>骑马
				<input type="checkbox" name="love" value="高尔夫"/>高尔夫
				<input type="checkbox" name="love" value="篮球"/>篮球
				<input type="checkbox" name="love" value="吹牛X"/>吹牛X
			</td>
		</tr>
		<tr>
			<td>客户类型</td>
			<td>
				<select name="type">
					<option value="青铜会员">青铜会员</option>
					<option value="白银会员">白银会员</option>
					<option value="黄金会员">黄金会员</option>
					<option value="钻石会员">钻石会员</option>
				</select>
			</td>
		</tr>
		<tr align="center">
			<td colspan="2">
				<input type="submit" value="添加" />
			</td>
		</tr>
	</table>
</form>

</body>
</html>