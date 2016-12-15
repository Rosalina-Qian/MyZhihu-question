<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<script type="text/javascript" src="./js/comment.js"></script>
	<script src="./jquery/jquery.min.js"></script>
	
	<script>
	$(document).ready(function(){
  		$(".cancel").on('click',function(){
  			$(this).parent().parent().css('display','none');
  		});
	});
	function thanks(objectId, userId, questionId){
	$.ajax({ 
		url: "ExpressThanks", 
		type: "GET",
		data: { "objectId": objectId, "userId":userId, "questionId":questionId },
		dataType: "JSON", 
		complete: function(data) { 
			//alert("success");
			var flag = true;
			if(flag){
				document.getElementById('thanks'+objectId).innerHTML = "取消感谢";
			}else{
				window.document.getElementById('thanks'+objectId).innerHTML = "感谢";
				flag = true;
			}
		}, 
		error: function (msg) {//ajax请求失败后触发的方法
                alert("ajax请求失败");//弹出错误信息
            }
		});
	}
	function zan(objectId, userId){
	$.ajax({ 
		url: "zan", 
		type: "GET",
		data: { "objectId": objectId, "userId":userId},
		dataType: "JSON", 
		complete: function(data) { 
			alert("已赞");
		}, 
		error: function (msg) {//ajax请求失败后触发的方法
                alert("ajax请求失败");//弹出错误信息
            }
		});
	}
	function cai(objectId, userId){
	$.ajax({ 
		url: "cai", 
		type: "GET",
		data: { "objectId": objectId, "userId":userId},
		dataType: "JSON", 
		complete: function(data) { 
			alert("已踩");
		}, 
		error: function (msg) {//ajax请求失败后触发的方法
                alert("ajax请求失败");//弹出错误信息
            }
		});
	}
	</script>	
  </head>
  
  <body>
  		<!-- start显示对应问题的详情 -->
  		<div id="questionlist">
  		来自：TopicID:${question.topicId } <br>
  		QuestionDescription:${question.questionDescription }<br>
		FurtherExplanations:${question.furtherExplanations }<br>
		QuestionDate:${question.questionDate }<br><br>	
  		</div>
  		<!-- end显示对应问题的详情 -->
  		<hr/>
		
		<!-- start发布我自己的回答 -->
		<a href="javascript:answer(1);">添加评论</a>
		<div id="myanswer" class="box">
			<form  method="POST" action="myanswer">	
			我的名字：${sessionScope.user.userName }  我的id:${sessionScope.user.userId }<br>
				<input type="hidden" name="userName" value="${sessionScope.user.userName }"/>
				<input type="hidden" name="userId" value="${sessionScope.user.userId }"/>
				<input type="hidden" name="questionId" value="${question.questionId }"/>
				<textarea class="content" name="answerContent"  cols="50" rows="2"></textarea>
				<br>
				<p id="cancelBtn" class='cancel' onclick="answer(0); return false;">关闭</p>
				<input type="submit" value="发布回答"/>
			</form>
		</div>
		<!-- end发布我自己的回答 -->
		<br>
			
			<!-- start显示该问题下所有的回答 -->
			<div id="startAnswer">
			<c:if test="${empty requestScope.answers }">请留下属于您的脚印~</c:if>
			<c:if test="${!empty requestScope.answers }">
			<c:forEach items="${requestScope.answers }" var="reply">
				<c:if test="${question.questionId == reply.questionId }">
				<div onmouseover="MM_over(this)" onmouseout="MM_out(this)" style="width:100px;position:relative;">
					<a class="focus_id" href="personalDetail/${reply.userId }">评论者id:${reply.userId }</a><br>
					
					<!-- start统计赞同数 -->
					<c:if test="${empty requestScope.supportNum }"></c:if>
					<c:if test="${!empty requestScope.supportNum }">
						<c:forEach items="${requestScope.supportNum }" var="support">
							<c:if test="${reply.answerId == support.answerId }">
								${support.supportCount }个赞同
							</c:if>
						</c:forEach>
					</c:if>
					<!-- end统计赞同数 -->
					
					<!-- start关注用户 :5代表用户-->
					<div id="add_focus${reply.userId }" class="add_focus" style="display:none">
						<form action="addFocus" method="POST">
						<div><a class="focus" href="personalDetail/${reply.userId }">评论者id:${reply.userId }</a></div>
						<input type="hidden" name="userId" value="${sessionScope.user.userId }"/>
						<input type="hidden" name="objectId" value="${reply.userId }"/>
						<input type="hidden" name="category" value="5"/>
						<div><input type="submit" value="关注他"/></div>
						</form>
					</div>
					<!-- end关注用户 -->
				</div>
					问题编号Id:${reply.questionId }<br>
					${reply.answerContent }<br>
					发布于${reply.answerDate }
					
					
					<!-- 自己对回答进行评论 -->
					<a id="add_comment" href="javascript:comment(${reply.answerId });">
					<div id="div_comment">
					<!-- start统计评论条数 -->
						<c:if test="${empty requestScope.comments }">添加评论</c:if>
						<c:if test="${!empty requestScope.comments }">
							<%int commentNum = 0; %>
							<c:forEach items="${requestScope.comments }" var="comment">
								<c:if test="${reply.answerId == comment.objectId }">
									<c:if test="${comment.category == '1' }">
										<% commentNum++;%></c:if>
								</c:if>
							</c:forEach>
							<%-- alert(${commentNum });
							<c:if test="${commentNum } == '0'">添加评论</c:if>
							<c:if test="${commentNum } != '0'"> --%><%=commentNum%>条评论
						</c:if>
					<!-- end统计评论条数 -->
					</div>
					</a>
					
					<!-- 显示感谢 -->
					<a id="thanks${reply.answerId }" href="javascript:thanks(${reply.userId },${sessionScope.user.userId },${question.questionId});">
						<div id="div_thanks" onclick="clickThanks(this)">感谢</div></a>
					
					<!-- 创建收藏夹 -->
					<a href="javascript:collect(${reply.answerId });">
					<div id="div_collection">收藏</div></a>&nbsp;&nbsp;
					
					<!-- 显示没有帮助 -->
					<a id="help${reply.answerId }" href="javascript:help(${reply.answerId });">
					<div id="div_help">没有帮助</div></a>
					
					<br>
					<!-- 自己对回答进行评论 -->
					<div id="mycomment${reply.answerId }" class="box">
						<form method="POST" action="replyComment">
						我的名字：${sessionScope.user.userName }&nbsp;&nbsp;
						我的id：${sessionScope.user.userId }&nbsp;&nbsp;评论的回答者id:${reply.userId }
						<input type="hidden" name="userName" value="${sessionScope.user.userName }"/>
						<input type="hidden" name="objectId" value="${reply.answerId }"/>
						<input type="hidden" name="userId" value="${sessionScope.user.userId }"/>
						<input type="hidden" name="questionId" value="${question.questionId }"/>
						<input type="hidden" name="category" value="1"/><!-- 评论回答 -->
						<textarea name="commentContent"></textarea>
						<a class='cancel'>关闭</a>&nbsp;&nbsp;
						<input type="submit" value="评论"/>
						</form>
					</div>
						
					<!-- 创建收藏夹 -->
						<div id="collection${reply.userId }" style="display:none" class="mycollection">
							<div class="collection-title">
								<span>&nbsp;&nbsp;添加到收藏夹</span></div>
							<div><p class="mycollection_diag">你可以创建多个收藏夹，将答案分类收藏</p></div> 
							 <div><a class="cancleCollect" href="javascript:cancelCollect(${reply.userId });">取消</a>
							 	<input type="button" value="创建收藏夹" onclick="newCollect(${reply.userId });"/></div>
						</div>
						
						<div id="favorite${reply.userId }" style="display:none" class="myfavorite">
							<div class="collection-title"><span>&nbsp;&nbsp;添加新收藏夹</span></div>
							<form action="addFavortie" method="POST">
							<input type="hidden" name="objectId" value="${reply.userId }"/>
							<input type="hidden" name="userId" value="${sessionScope.user.userId }"/>
							<input type="hidden" name="questionId" value="${question.questionId }"/>
							<input type="hidden" name="category" value="1"/>
							<div>标题<span style="display: none;">请填写标题</span></div>
							 <div><input name="favoriteName" class="collection_input" type="text" placeholder="最多输入 20 字"/></div>
							 <div>描述（可选）</div>
							 <div><textarea name="favoriteDescription" class="collection_input" style="height: 66px;" placeholder="最多输入 256 字"></textarea></div>
							 <div>
							 	<a class="cancleCollect" href="javascript:cancelFavorite(${reply.userId });">取消</a>
							 	<input type="submit" value="确认创建"/>
							 </div>
							 </form>
						 </div>
					
					<%-- </form> --%>
					
					<!-- 更新所有评论 -->
					<c:if test="${empty requestScope.comments }">写下属于你的评论~~</c:if>
					<c:if test="${!empty requestScope.comments }">
						<form:form>
							<c:forEach items="${requestScope.comments }" var="comment">
								<c:if test="${reply.userId == comment.objectId }">
								<c:if test="${comment.category =='1'}">
								评论者Id:${comment.userId }&nbsp;&nbsp;
								被评论者Id:${comment.objectId }<br>
								${comment.commentContent }<br>
								发布于${comment.commentTime }<br>
								
								<!-- 自己对其他评论者进行回复-->
								<div id="div_comment"><a id="replyOthers" href="javascript:replyOthers(${comment.commentId });">回复</a></div>
								
								<!-- 赞 --><div id="div_thanks" onclick="clickThanks();">
								<a id="zan${comment.commentId }" 
									href="javascript:zan(${comment.userId },${sessionScope.user.userId });">赞</a></div>
								
								<!-- 踩 -->
								<a href="javascript:cai(${comment.userId },${sessionScope.user.userId });">
								<div id="div_collection">踩</div></a>
								
								<!-- 举报 -->
								<a id="help${comment.commentId }" href="javascript:help(${comment.commentId });">
								<div id="div_help">举报</div></a>
								<br>
								
								<!-- 回复他人评论 -->
								<div id="replycomment${comment.commentId }" class="box">
									<form:form method="POST" action="replyComment">
									我的id：${sessionScope.user.userId }&nbsp;&nbsp;回复的评论者id:${comment.userId }
									<input type="hidden" name="userName" value="${sessionScope.user.userName }"/>
									<input type="hidden" name="objectId" value="${comment.userId }"/>
									<input type="hidden" name="userId" value="${sessionScope.user.userId }"/>
									<input type="hidden" name="questionId" value="${question.questionId }"/>
									<input type="hidden" name="category" value="5"/><!-- 回复用户 -->
									<textarea name="commentContent"></textarea>
									<a class='cancel'>关闭</a>&nbsp;&nbsp;
									<input type="submit" value="回复"/>
									</form:form>
								</div>
								</c:if>
								</c:if>
								
								<!-- start显示回复 -->
								<c:if test="${empty requestScope.otherComments }">~</c:if>
								<c:if test="${!empty requestScope.otherComments }">
								<div id="replyOtherComment" style="border:1px dashed grey;">
									<c:forEach items="${requestScope.otherComments }" var="other">
									<c:if test="${reply.userId == comment.objectId }">
									<c:if test="${comment.userId == other.objectId }">
									<c:if test="${other.objectId=='5' }">
									我的Id:${other.userId }&nbsp;&nbsp;
									被回复者Id:${other.objectId }<br>
									${other.commentContent }<br>
									发布于${other.commentTime }<br>
									</c:if></c:if></c:if>
									</c:forEach>
								</div>
								</c:if>
								<!-- end显示回复 -->
							</c:forEach><br>
						</form:form>
					</c:if>
					<!-- end更新所有评论 -->
					
				</c:if>
			</c:forEach>
		</c:if>
	</div>
	<!-- endAnswer-->
  </body>
</html>
