<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="sessionChk.jsp"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<%
	String toID = null;
	if (request.getParameter("toID") != null) {
		toID = (String) request.getParameter("toID");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>채팅</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css"
	rel="stylesheet"
	integrity="sha384-T8Gy5hrqNKT+hzMclPo118YTQO6cYprQmhrYwIiQ/3axmI1hQomh7Ud2hPOy8SP1"
	crossorigin="anonymous">
<link rel='stylesheet prefetch'
	href='https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.3/jquery.mCustomScrollbar.min.css'>
<link rel="stylesheet" href="${path }/css/chat.css">

<script
	src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script
	src='https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.3/jquery.mCustomScrollbar.concat.min.js'></script>
<script src="${path }/js/chat.js"></script>
<script type="text/javascript">
	function submitFunction() {
		var fromID = '<%=userid%>';
		var toID = '<%=toID%>';
		var chatContent = $('.messages-content').val();
		$.ajax({
				type:"POST",
				url:"./chatSubmitServlet",
				data: {
					fromID: encodeURIComponent(fromID),
					toID: encodeURIComponent(toID),
					chatContent: encodeURIComponent(chatContent),
				}
		});
		$('.messages-content').val('');
	}
</script>
</head>
<body>
	<!--
Inspired by https://dribbble.com/supahfunk
-->
	<div class="alert alert-success" id="successMessage" style="display: none;">
		<strong>메세지 전송에 성공했습니다.</strong>
	</div>
	<section class="avenue-messenger">
		<div class="menu">
			<div class="items">
				<span> <a href="#" title="Minimize">&mdash;</a><br> <!--     
     <a href="">enter email</a><br>
     <a href="">email transcript</a><br>--> <a href="#" title="End Chat">&#10005;</a>

				</span>
			</div>
			<div class="button">...</div>
		</div>
		<div class="agent-face">
			<div class="half">
				<img class="agent circle" src="${path }/img_all/user.png" alt="">
			</div>
		</div>
		<div class="chat">
			<div class="chat-title">
				<h1><%=userid %></h1>
				<h2><%=username %></h2>
				<!--  <figure class="avatar">
      <img src="http://askavenue.com/img/17.jpg" /></figure>-->
			</div>
			<div class="messages">
				<div class="messages-content"></div>
			</div>
			<div class="message-box">
				<textarea type="text" class="message-input"
					placeholder="Type message..."></textarea>
				<button type="submit" class="message-submit"
					onclick="submitFunction();">Send</button>
			</div>
		</div>
	</section>
</body>
</html>