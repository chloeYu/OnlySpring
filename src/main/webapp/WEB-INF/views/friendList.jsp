<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="sessionChk.jsp"%>
<%@ include file="include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FriendList</title>
</head>
<body>
	<c:if test="${not empty listFriend }">
		<c:forEach var="friend" items="${listFriend }">
			<c:if test="${friend.status!=0}">
				<c:if test="${friend.status==1 }">
					<c:if test="${friend.uid1 != member.userid }">${friend.uid1 }<p>
					</c:if>
					<c:if test="${friend.uid2 != member.userid }">${friend.uid2 }<p>
					</c:if>
				</c:if>
			</c:if>
		</c:forEach>
		<h2>친구신청</h2>
		<c:forEach var="friend" items="${listFriend }">
			<c:if test="${friend.status!=0}">
				<c:if test="${friend.status==2 }">
					<c:if test="${friend.uid1 != member.userid }">${friend.uid1 }</c:if>
					<c:if test="${friend.uid2 != member.userid }">${friend.uid2 }</c:if>
					<button onclick="">수락</button>
					<button onclick="">거절</button>
					<p>
				</c:if>
			</c:if>
		</c:forEach>
	</c:if>
</body>
</html>