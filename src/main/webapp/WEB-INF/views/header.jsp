<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="sessionChk.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath }"></c:set>
<div class="header">
	<!-- 상단고정바 로고 -->
	<div class="head_logo">
		<h1 class="head_logo">
			<a id="logo_href" href="${path}/timeline"> <img id="logo_top"
				alt="" src="${path}/img_all/logo4.png">
			</a>
		</h1>
	</div>
	<!-- 로고 끝 -->
	<!-- 상단고정바 서치 -->
	<div class="search">
		<form class="form-wrapper cf" name="frm" action="searchResult" method="post"
			onsubmit="return searchChk()">
			<input list="searchResult" class="searchTerm" name="searchTerm"
				placeholder="Search" onkeyup="runSearch()" autocomplete="off">
			<datalist id="searchResult">
			</datalist>
			<button type="submit">Search</button>
		</form>
		<div class="searchClear"></div>
	</div>
	<!-- 서치 끝 -->
	<div class="header_profile">
		<div class="header_wraper userProfile" data-click="profile_icon">
			<a id="user_profile_url" href="blog/<%=userid %>"> <img
				style="vertical-align: middle" src="img_timeline/<%=member.getProfile_image()%>"> <span>${name }</span>
			</a>
		</div>

		<div class="header_wraper">
			<div class="nav_icon">
				<div class="friend_list">
					<a id="friendList" href="friendList.do?userid=${sessionId }"
						data-userid="${sessionId }">
						<div id="friend_notification">
							<span>Friend List</span>
						</div>
					</a>
					</form>
				</div>
			</div>
			<div class="nav_icon">
				<div class="alarm_list" data-click="alarm_list_icon">
					<a href="./timeline.jsp" id="alarmList" class="info">
						<div id="alarm_notification">
							<span>Notifications</span>
						</div>
					</a>
				</div>
			</div>

			<div class="nav_icon">
				<div class="message_list" data-click="message_list_icon">
					<a class="info" id="chatList" data-id="${sessionId }">
						<div id="message_notification">
							<span>Message</span>
						</div>
					</a>
					<div id="displayChatList" class="chatListToggle"></div>
				</div>
			</div>

		</div>
	</div>
</div>
<!-- 상단고정바 끝 -->