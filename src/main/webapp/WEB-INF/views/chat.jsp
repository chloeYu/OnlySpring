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
<script type="text/javascript">
$(document).ready(function() {
	var $messages = $('.messages-content'), d, h, m, i = 0;

	$(window).load(function() {
		$messages.mCustomScrollbar();
	});

	chatListFunction('ten');
	getInfiniteChat();

	// 전송 버튼을 눌렀을 때 호출되는 메소드
	$('.message-submit').click(function() {
		insertMessage();
	});

	$(window).on('keydown', function(e) {
		if (e.which == 13) {
			insertMessage();
			return false;
		}
	})

	$('.button').click(function() {
		$('.menu .items span').toggleClass('active');
		$('.menu .button').toggleClass('active');
	});
					
	function updateScrollbar() {
		$messages.mCustomScrollbar("update").mCustomScrollbar(
				'scrollTo', 'bottom', {
					scrollInertia : 10,
					timeout : 0
		});
	}

	function setDate() {
		d = new Date()
		if (m != d.getMinutes()) {
			m = d.getMinutes();
			$('<div class="timestamp">' + d.getHours()
							+ ':' + m + '</div>').appendTo(
				$('.message:last'));
			$('<div class="checkmark-sent-delivered">&check;</div>')
							.appendTo($('.message:last'));
			$('<div class="checkmark-read">&check;</div>')
							.appendTo($('.message:last'));
		}
	}

	function insertMessage() {
		var fromID = '<%=userid%>';
		var toID = '<%=toID%>';
		var chatContent = $('.message-input').val();
		
		$.ajax({
			type:"POST",
			url:"./chatSubmitServlet",
			data: {
					fromID: encodeURIComponent(fromID),
					toID: encodeURIComponent(toID),
					chatContent: encodeURIComponent(chatContent),
			}
		});
		$('.message-input').val("");
					/*setTimeout(function() {
						receiveMessage();
					}, 1000 + (Math.random() * 20) * 100);*/
	}

	var lastID = 0;
	function chatListFunction(type) {
		var fromID = '<%=userid%>';
		var toID = '<%=toID%>';
		$.ajax({
			type : "POST",
			url : "./chatListServlet",
			data : {
				fromID : encodeURIComponent(fromID),
				toID : encodeURIComponent(toID),
				listType : type,
			},
			success : function(data) {
				if (data == "")
					return;
				var parsed = JSON.parse(data);
				var result = parsed.result;
				for (var i = 0; i < result.length; i++) {
					addChat(result[i][0].value, result[i][2].value,
							result[i][3].value);
				}
				lastID = Number(parsed.last);
			}
		});
	}
	
	function addChat(chatName, chatContent, chatTime) {
		var fromID = '<%=userid%>';
		var toID = '<%=toID%>';
		
		if(fromID = '<%=userid%>') {
			$('<div class="message message-personal">' + chatContent + '</div>')
					.appendTo($('.mCSB_container')).addClass('new');
			$('<div class="timestamp">' + chatTime +'</div>')
					.appendTo($('.message:last'));
			$('<div class="checkmark-sent-delivered">&check;</div>')
			.appendTo($('.message:last'));
		} else {
			$('<div class="message new"><figure class="avatar"><img src="/only/img_all/user.png" /></figure>'
					+ msg + '</div>')
					.appendTo($('.mCSB_container'))
					.addClass('new');
			$('<div class="checkmark-read">&check;</div>')
			.appendTo($('.message:last'));
		}
		updateScrollbar();
	}
	function getInfiniteChat() {
		setInterval(function() {
			chatListFunction(lastID);
		}, 1000);
	}
});
</script>
</head>
<body>
	<!--
Inspired by https://dribbble.com/supahfunk
-->
	<div class="alert alert-success" id="successMessage"
		style="display: none;">
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
				<h1><%=toID%></h1>
				<!--  <figure class="avatar">
      <img src="http://askavenue.com/img/17.jpg" /></figure>-->
			</div>
			<div class="messages">
				<div class="messages-content"></div>
			</div>
			<div class="message-box">
				<textarea type="text" class="message-input"
					placeholder="Type message..."></textarea>
				<button type="submit" class="message-submit">Send</button>
			</div>
		</div>
	</section>

</body>
</html>