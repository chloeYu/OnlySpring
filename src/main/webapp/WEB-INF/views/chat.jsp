<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>채팅</title>
<style type="text/css">
#chatArea {
	width: 200px;
	height: 100px;
	overflow-y: auto;
	border: 1px solid black;
}
</style>
<script type="text/javascript">
	// 웹 소켓 변수
	var websock;
	var url = window.location.host;//웹브라우저의 주소창의 포트까지 가져옴
	var pathname = window.location.pathname; /* '/'부터 오른쪽에 있는 모든 경로*/
	var appCtx = pathname.substring(0, pathname.indexOf("/", 2));
	var root = url + appCtx;
	
	//문서 시작 호출메소드
	$(document).ready(function() {
		$('#message').keypress(function(event) {
			var keycode = event.keyCode ? event.keyCode : event.which;
			if (keycode == 13)
				send();
			event.stopPropagation();
		});
		$('#sendBtn').click(function() {
			send();
		});
		$('#enterBtn').click(function() {
			connect();
		});
		$('#exitBtn').click(function() {
			disconnet();
		});
	});
	// 메시지를 출력하는 메소드
	function appendMessage(msg) {
		// msg를 chatMessageArea에 추가
		$('#chatMessageArea').append(msg + '<br/>');
	}
	// 입장 버튼을 눌렀을 때 호출될 메소드
	function connect() {
		websock = new WebSocket("ws://" + root + "/chat-ws");
		websock.onopen = onOpen; // 연결이 될 때 호출될 메소드 설정
		websock.onmessage = onMessage; // 메시지가 왔을 때 호출될 메소드 설정
		websock.onclose = onClose; // 연결이 해제될 때 호출될 메소드 설정
	}
	// 퇴장 버튼을 눌렀을 때 호출될 메소드
	function disconnect() {
		// 소켓 닫기
		websock.close();
	}
	function onOpen(evt) { // 연결이 되었을 때 호출될 메소드
		appendMessage("연결되었습니다.")
	}
	function onMessage(evt) {//메시지가 왔을 때 호출될 메소드
		var data = evt.data;
		appendMessage(data);
	}
	function onClose(evt) { // 연결이 해제될 때 호출되는 메소드
		appendMessage("연결을 끊었습니다.");
	}
	// 전송 버튼을 눌렀을 때 호출되는 메소드
	function send() {
		// jQuery로 쓸 때 코드
		var nickname = $('#nickname').val();
		var msg = $('#message').val();
		websock.send("msg:" + nickname + ":" + msg);
		$('#message').val("");
	}
</script>
</head>
<body>
	별명 :
	<input type="text" id="nickname" />
	<input type="button" id="enterBtn" value="입장" />
	<input type="button" id="exitBtn" value="퇴장" />
	<br>
	<input type="text" id="message" />
	<input type="button" id="sendBtn" value="전송" />
	<h1>대화영역</h1>
	<div id="chatArea">
		<div id="chatMessageArea"></div>
	</div>
</body>
</html>