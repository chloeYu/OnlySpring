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
	var websocket;
	var url = window.location.host;// 웹브라우저의 주소창의 포트까지 가져옴
	var pathname = window.location.pathname;
	var appCtx = pathname.substring(0, pathname.indexOf("/", 2));
	var root = url + appCtx;
	
	var $messages = $('.messages-content'), d, h, m, i = 0; // 스크롤
	
	// 웹소켓 커넥션
	function connect() { 
		websocket = new WebSocket("ws://" + root + "/chat-ws");
		websocket.onmessage = onMessage;
	}
	
	// 웹소켓 디커넥션
	function disconnect() { 
		websocket.close();
	}
	
	function onMessage(event) {
		appendMessage(event.data);
	}
	// 웹소켓에 저장된 메세지 출력
	function appendMessage(jsonString) {
		var fromID = null;
		var toID = null;
		var chatContent = null;
		
		$.each(JSON.parse(jsonString), function(key, value) {
			if (key === 'fromID')
				fromID = value;
			if (key === 'toID')
				toID = value;
			if (key === 'chatContent')
				chatContent = value;
		});
		
		if(fromID === '<%=userid%>') {
			$('<div class="message message-personal">' + chatContent + '</div>')
					.appendTo($('.mCSB_container')).addClass('new');
			setDate();
			$('<div class="checkmark-sent-delivered">&check;</div>')
			.appendTo($('.message:last'));
		} else {
			$('<div class="message new"><figure class="avatar"><img src="/only/img_all/user.png" /></figure>'
					+ chatContent + '</div>')
					.appendTo($('.mCSB_container'))
					.addClass('new');
			setDate();
			$('<div class="checkmark-read">&check;</div>')
					.appendTo($('.message:last'));
		}
		updateScrollbar();
	}
	
	$(window).load(function() {
		connect();
		$messages.mCustomScrollbar();
	});
	
	$(window).on('unload', (function() {
		disconnect();
	}));

	// 전송 버튼을 눌렀을 때 호출되는 메소드
	$('.message-submit').click(function() {
		insertMessage();
	});
	
	//엔터키 조작
	$(window).on('keydown', function(e) {
		if (e.which == 13) {
			insertMessage();
			return false;
		}
	})
	
	//채팅창 미니메뉴 
	$('.button').click(function() {
		$('.menu .items span').toggleClass('active');
		$('.menu .button').toggleClass('active');
	});
					
	$('.endChat').click(function () {
		disconnect();
		$('.menu .items span').removeClass('active');
		$('.menu .button').removeClass('active');
	});
	
	//친구목록창 열기
	$('#message_notification').click(function() {
		$('.left').toggleClass('active');
	});
	
	//스크롤바 펑션
	function updateScrollbar() {
		$messages.mCustomScrollbar("update").mCustomScrollbar(
				'scrollTo', 'bottom', {
					scrollInertia : 10,
					timeout : 0
		});
	}
	
	//시간 
	function setDate() {
		d = new Date()
		if (m != d.getMinutes()) {
			m = d.getMinutes();
			$('<div class="timestamp">' + d.getHours()
							+ ':' + m + '</div>').appendTo(
				$('.message:last'));
			$('<div class="checkmark-sent-delivered">&check;</div>')
							.appendTo($('.message:last'));
		}
	}
	
	//메세지 저장
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
					chatContent: encodeURIComponent(chatContent)
			},
			success: function () {
				websocket.send(JSON.stringify({
					fromID : "<%=userid%>",
					toID : "<%=toID%>",
					chatContent : $('.message-input').val()
				}));
				$('.message-input').val("");
			}
		});
					/*setTimeout(function() {
						receiveMessage();
					}, 1000 + (Math.random() * 20) * 100);*/
	}
	
	//메세지 리스트 갯수
	chatListFunction('ten');
	
	//메세지 리스트 불러오기
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
	
	//메세지 리스트 CSS
	function addChat(chatName, chatContent, chatTime) {
		if(chatName == '<%=userid%>') {
			$('<div class="message message-personal">' + chatContent + '</div>')
					.appendTo($('.mCSB_container')).addClass('new');
			$('<div class="timestamp">' + chatTime +'</div>')
					.appendTo($('.message:last'));
			$('<div class="checkmark-sent-delivered">&check;</div>')
			.appendTo($('.message:last'));
		} else {
			$('<div class="message new"><figure class="avatar"><img src="/only/img_all/user.png" /></figure>'
					+ chatContent + '</div>')
					.appendTo($('.mCSB_container'))
					.addClass('new');
			$('<div class="timestamp">' + chatTime +'</div>')
					.appendTo($('.message:last'));
			$('<div class="checkmark-read">&check;</div>')
					.appendTo($('.message:last'));
		}
		updateScrollbar();
	}
	
	// 메시지 목록 불러오기
	function MessageList() {
		$.ajax({
			type : "POST",
			url : "/messageList",
			data : ${messageList},
			success : function() {
				$('.people').append('<li class="person" data-chat="person1"><img src="/only/img_all/user.png" alt=""/>'+ 
						'<span class="name">'+ ${toID} +'</span><span class="time">' + ${chatTime}
						+ '</span><span class="preview">'+ ${chatContent} +'</span></li>')
			}
		});
	}
});
</script>
</head>
<body>
<!-- 채팅창 -->
	<div class="alert alert-success" id="successMessage"
		style="display: none;">
		<strong>메세지 전송에 성공했습니다.</strong>
	</div>
	<section class="avenue-messenger">
		<div class="menu">
			<div class="items">
				<span> <a href="#" title="Minimize">&mdash;</a><br> <!--     
     <a href="">enter email</a><br>
     <a href="">email transcript</a><br>--> <a href="#" title="End Chat" class="endChat">&#10005;</a>

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
<!-- 채팅창 끝 -->
<!-- 친구목록 -->
        <div class="left">
            <div class="top">
                <input type="text" />
                <a href="javascript:;" class="searchF"></a>
            </div>
            <ul class="people">
                <li class="person" data-chat="person1">
                    <img src="https://s13.postimg.org/ih41k9tqr/img1.jpg" alt="" />
                    <span class="name">Thomas Bangalter</span>
                    <span class="time">2:09 PM</span>
                    <span class="preview">I was wondering...</span>
                </li>
            </ul>
            ${messageList}
        </div>
<!-- 친구목록 끝 -->
</body>
</html>