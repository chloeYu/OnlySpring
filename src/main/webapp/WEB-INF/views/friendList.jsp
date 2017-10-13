<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FriendList</title>
</head>
<body class="instant">
	<c:if test="${not empty friendList }">
		<hr class="instant"><h2 class="flh2 instant">친구</h2><hr class="instant">
		<c:forEach var="friend" items="${friendList }">
			
			<p class="flp instant">${friend.username }</p>
		</c:forEach>
	</c:if>
	<c:if test="${not empty requestList }">
	<hr class="instant"><h2 class="flh2 instant">친구요청</h2><hr class="instant">
		<c:forEach var="friend" items="${requestList }">
			${friend.username }
			<a class="instant" href="/only/friendupdate/1/${friend.userid}/${member.userid}">수락</a>
			<a class="instant" href="/only/friendupdate/0/${friend.userid}/${member.userid}">거절</a>
			<p class="flp instant">		
		</c:forEach>
	</c:if>
	<c:if test="${not empty pendingList }">
		<hr class="instant"><h2 class="flh2 instant">대기중 요청</h2><hr class="instant">
		<c:forEach var="friend" items="${pendingList }">
			<p class="flp instant">${friend.username }</p>
		</c:forEach>
	</c:if>
</body>
</html>