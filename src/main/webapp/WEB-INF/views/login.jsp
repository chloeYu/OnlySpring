<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="sessionChk.jsp"%>
<%@ include file="include.jsp" %>
<script type="text/javascript">
	var websocket;
	var url = window.location.host;// 웹브라우저의 주소창의 포트까지 가져옴
	var pathname = window.location.pathname;
	var appCtx = pathname.substring(0, pathname.indexOf("/", 2));
	var root = url + appCtx;
	console.log(root+"asdfs");
	websocket = new WebSocket("ws://" + root + "/chat-ws");
	
	websocket.onopen = function (event) {
		websocket.send(JSON.stringify({
			type : "contacts",
			fromID : "<%=userid%>"
		}));
		location.href="timeline";
		
	};
</script>