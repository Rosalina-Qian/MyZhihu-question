<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  	<form action="main" method="POST">
  		userid:<input type="text" name="userId" value=""/><br>
  		userName:<input type="text" name="userName" value=""/><br>
  		userPhoneNum:<input type="text" name="userPhoneNum" value="userPhoneNum"/><br>
  		passWord:<input type="password" name="passWord" value="passWord"/><br>
  		sex:<input type="radio" name="sex" value="1" checked>Male	
			<input type="radio" name="sex" value="0">Female<br>
  		<input type="submit" value="enter"/><input type="submit" value="new your account">
  	</form>
    
  </body>
</html>
