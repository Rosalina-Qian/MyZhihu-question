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
	<link rel="stylesheet" type="text/css" href="./css/style.css">
  </head>
  
  <body>
  <!-- 显示所查用户信息 -->
  	<div id="personalDetail">
  	<div>${personal.userName }</div>
  	<div>${personal.personnalIntroduction }</div>
  	<div>${personal.address }</div>
  	<div>
  		<a>提问</a>
  		<a href="personalDetail/${personal.userId}">回答</a>
  		<a href="">文章</a>
  		<a href="personalDetail/${personal.userId}">收藏</a>
  		<a>公共编辑</a>
  	</div>
  	</div>
  	
  	<!-- 该用户的所有回答 -->
  	<div>
  		<div>${personal.userName }的回答</div>
  		<div>
  			<c:if test="${empty requestScope.myanswers }">主人很懒,什么也没有留下~</c:if>
  			<c:if test="${!empty requestScope.myanswers }">
  				<c:forEach items="${requestScope.myanswers }" var="myanswer">
  					<div id="personnal_answer">
  					${personal.userName }${personal.personnalIntroduction }<br>
  					${myanswer.questionId }<br>
  					${myanswer.answerContent }<br>
  					${myanswer.answerDate }
  					<!-- 关注问题 :0代表问题-->
						<form action="addFocus" method="POST">
						<a href="addFocus"></a>
						<input type="hidden" name="userId" value="${sessionScope.user.userId }"/>
						<input type="hidden" name="objectId" value="${personal.userId }"/>
						<input type="hidden" name="focus" value="0"/>
						<div><input type="submit" value="+关注问题"/></div>
						</form>
					</div>
  				</c:forEach>
  			</c:if>
  		</div>
  	</div>
  	
  	<!-- 该用户的所有收藏 -->
  	<div>
  		<div>${personal.userName }的收藏</div>
  		<div>
  			<c:if test="${empty requestScope.mycollections }">主人很懒,什么也没有收藏~</c:if>
  			<c:if test="${!empty requestScope.mycollections }">
  				<c:forEach items="${requestScope.mycollections }" var="mycollection">
  					<div id="personnal_collection">
  					${mycollection.favoriteName }<br>
  					${mycollection.favoriteDescription }<br>
  					${mycollection.favoriteDate }
  					<!-- 关注收藏-->
						<form action="addFocus" method="POST">
						<a href="addFocus"></a>
						<input type="hidden" name="userId" value="${sessionScope.user.userId }"/>
						<input type="hidden" name="objectId" value="${personal.userId }"/>
						<input type="hidden" name="category" value="4"/>
						<input type="submit" value="关注收藏"/>
						</form>
					</div>
  				</c:forEach>
  			</c:if>
  		</div>
  	</div>
  </body>
</html>
