<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty friendList }">
	<c:forEach items="${friendList }" var="friend">
		<li class="friendListView">
			<div style="float: right; display: inline-block;">
				<div
					style="height: 100px; display: inline-block; vertical-align: middle;"></div>
				<div class="friendBtn">
					<c:if test="${friend.relation == 1 }">
						<a class="hidden_elem"><div class="checkFriend"></div>Friend</a>
						<div class="friend-dropdown">
							<a class="unfriend" data-user="${friend.userid }"
								data-uid1="${friend.uid1 }" data-uid2="${friend.uid2 }">Unfriend</a>
						</div>
					</c:if>
					<c:if test="${friend.relation == 2 }">
						<c:if test="${friend.uid1 eq friend.userid }">
							<!-- 검색회원이 친구요청을 한 경우 -->
							<a class="friendRequestAdd"><div class="addFriend"></div>Respond
								to Friend Request</a>
							<div class="friend-dropdown">
								<a class="accept" data-user="${friend.userid }">Accept</a> <a
									class="reject" data-user="${friend.userid }">Reject</a>
							</div>
						</c:if>
						<c:if test="${friend.uid2 eq friend.userid }">
							<!-- 검색회원에게 친구요청을 한 경우 -->
							<a class="friendRequestCancel"><div class="addFriend"></div>Friend
								Request Sent</a>
							<div class="friend-dropdown">
								<a class="cancel" data-user="${friend.userid }">Cancel
									Request</a>
							</div>
						</c:if>
					</c:if>
					<c:if test="${friend.relation == 0 }">
						<a class="friendRequestBtn" data-user='${friend.userid }'><div
								class="addFriend"></div>Add Friend</a>
					</c:if>
				</div>
			</div>
			<div class="search_profile">
				<a style="margin-right: 10px; float: left; display: block;"><img
					src='${path}/img_timeline/${friend.profile_image }'
					style="dlsplay: block; width: 100; height: 100;"></a>
			</div>
			<div>
				<div style="display: inline-block; max-width: 40%;">
					<div
						style="height: 100px; display: inline-block; vertical-align: middle"></div>
					<a href="/only/blog/${friend.userid }" class="test"
						id="test-${friend.userid }" style="color: #365899;">${friend.username }</a>
				</div>
			</div>
		</li>
	</c:forEach>
</c:if>