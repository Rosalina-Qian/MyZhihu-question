<%@page import="com.mq.zhihu.dao.AnswerDAO"%>
<%@page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<style type="text/css">
		body {background-image:url("./images/lake.jpg");}
	</style>
  </head>
  
  <body>
  <div id="questionlist">
  <!-- 显示所有的问题 -->
  <c:if test="${empty requestScope.questions }">这儿没有任何问题清单~~~</c:if>
	<c:if test="${!empty requestScope.questions }">
	${sessionScope.user.userName }<br>
	<input type="text" style="display:none" value="${sessionScope.user.userId }"/>
	
		<c:forEach items="${requestScope.questions }" var="ques">
			<form:form>
			<!--<c:forEach items="${requestScope.users }" var="user">${user.userName }username</c:forEach>-->
			
				来自TopicID:${ques.topicId } <br>
				<a href="showcomment/${ques.questionId }">QuestionDescription:${ques.questionDescription }</a><br>
				FurtherExplanations:${ques.furtherExplanations }<br>
				QuestionDate:${ques.questionDate }<br>	
				
				<!-- start统计该问题下的回答数 -->
				<div id="countAnswerNum">
					<c:if test="${empty requestScope.answers }"></c:if>
					<c:if test="${!empty requestScope.answers }">
						<%int answerNum = 0; %>
						<c:forEach items="${requestScope.answers }" var="answer">
							<c:if test="${ques.questionId == answer.questionId }">
							<% answerNum++;%>
							</c:if>
						</c:forEach>
						<a href="showcomment/${ques.questionId }"><%=answerNum%>个回答</a>
					</c:if>
				</div>	
				<!-- end统计该问题下的回答数 -->
				
				<!-- start统计该问题的关注数 -->
				<div id="countFocusQuesNum">
				<c:if test="${empty requestScope.focus }"></c:if>
					<c:if test="${!empty requestScope.focus }">
						<%int QuesNum = 0; %>
						<c:forEach items="${requestScope.focus }" var="f">
							<c:if test="${ques.questionId == f.objectId }">
								<c:if test="${f.category == '0' }">
							<% QuesNum++;%></c:if>
							</c:if>
						</c:forEach>
						<a><%=QuesNum%>人关注</a>
					</c:if>
				</div>
				<!-- end统计该问题的关注数 -->
			</form:form>
			<hr/>
		</c:forEach>
	</c:if>
  </div>
  
	
  </body>
</html>
