<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<hr class="instant">
	<h2 class="flh2 instant">친구요청</h2>
	<hr class="instant">
<c:if test="${not empty requestList }">
	<c:forEach var="friend" items="${requestList }">
		<div class="fld instant">
			<p class="instant">${friend.username }
				<button class="instant"
					onclick="friendupdate(1, '${friend.userid}', '${member.userid}')">수락</button>
				<button class="instant"
					onclick="friendupdate(0, '${friend.userid}', '${member.userid}')">거절</button>
			</p>
		</div>
	</c:forEach>
</c:if>
	<hr class="instant">
	<h2 class="flh2 instant">대기중 요청</h2>
	<hr class="instant">
<c:if test="${not empty pendingList }">
	<c:forEach var="friend" items="${pendingList }">
		<div class="fld instant">
			<p class="instant">${friend.username }</p>
		</div>
	</c:forEach>
</c:if>