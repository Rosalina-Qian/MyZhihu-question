<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'question.jsp' starting page</title>
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
  	<c:if test="${empty requestScope.favorites }">there is nothing</c:if>
  	<c:if test="${!empty requestScope.favorites }">
  		<c:forEach items="${requestScope.favorites }" var="favorite">
  			userId:${favorite.userId }<br>
  			favoriteName:${favorite.favoriteName }<br>
  			favoriteDescription:${favorite.favoriteDescription }<br>
  			FavoriteDate:${favorite.favoriteDate }<br><br>
  		</c:forEach>
  	</c:if>

  </body>
</html>
