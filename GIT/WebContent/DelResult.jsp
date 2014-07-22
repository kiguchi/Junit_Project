<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ import="user.bean.RegistrantInfo" pageEncoding="Utf-8 %>

<!DOCTYPE html>

<jsp:useBean id="userInfo" scope="session" class="user.bean.UserInfo" />
<jsp:useBean id="delInfo" scope="request" class="user.bean.RegistrantInfo" />
<html>
<head>
<link rel="stylesheet" href="Task.css" type="text/css" />
<title>登録者削除</title>	
</head>
<body>
	<%@ include file="Header.jsp" %>

	<div id="main">
		登録者変更<hr color=white width=30%>
		削除しました<br />
		<table class="regList">
		<tr>
			<th width="150">ID</th>
			<td width="200"><jsp:getProperty name="delInfo" property="rId" /></td>
		</tr>
		<tr>
			<th>名前</th>
			<td><jsp:getProperty name="delInfo" property="rName" /></td>
		</tr>
		<tr>
			<th>年齢</th>
			<td><jsp:getProperty name="delInfo" property="rAge" /></td>
		</tr>
		</table>
		<br />
		<div id="menu"><a href="MenuList.jsp" style="text-decoration: none">一覧へ</a></div>
 	</div>

	<%@ include file="Footer.jsp" %>

</body>
</html>