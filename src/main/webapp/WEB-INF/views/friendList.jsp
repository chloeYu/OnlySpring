<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FriendList</title>
</head>
<body>
	<c:if test="${not empty friendList }">
		<h2>친구</h2>
		<c:forEach var="friend" items="${friendList }">
			
			${friend.username }<p>
		</c:forEach>
	</c:if>
	<c:if test="${not empty requestList }">
		<h2>친구요청</h2>
		<c:forEach var="friend" items="${requestList }">
			${friend.username }
			<a href="/only/friendupdate/1/${friend.uid1}/${friend.uid2}">수락</a>
			<a href="/only/friendupdate/0/${friend.uid1}/${friend.uid2}">거절</a>
			<p>		
		</c:forEach>
	</c:if>
	<c:if test="${not empty pendingList }">
		<h2>대기중 요청</h2>
		<c:forEach var="friend" items="${pendingList }">
			${friend.username }<p>
		</c:forEach>
	</c:if>
</body>
</html>