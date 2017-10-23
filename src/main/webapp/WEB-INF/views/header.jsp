<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="sessionChk.jsp"%>
<c:set var="path" value="${pageContext.request.contextPath }"></c:set>
<html>
<head>
<script type="text/javascript">
	function updateAlertNotification(num) {
		console.log(num);
		if (num == 0 || num == "0") {
			/* $("#alarm_notification").html("<span>새 글 없음</span>");
			$("#alarm_notification").removeClass('alert'); */
			$('#alertCountAll').html('');
		} else {
			/* $("#alarm_notification").html(
					"<span>" + num + "개의 읽지 않은 새 글</span>");
			$("#alarm_notification").addClass('alert'); */
			$('#alertCountAll').html('<span style="left:12px;">'+num+'</span>');
		}
	}

	function logout() {
		alert("logout");
		var websocket;
		var url = window.location.host;// 웹브라우저의 주소창의 포트까지 가져옴
		var pathname = window.location.pathname;
		var appCtx = pathname.substring(0, pathname.indexOf("/", 2));
		var root = url + appCtx;
		websocket = new WebSocket("ws://" + root + "/chat-ws");

		websocket.onopen = function(event) {
			websocket.send(JSON.stringify({
				type : "contacts"
			}));
			location.href="${path}/logout";
		};
	}
	$(function() {
		console.log("update notification");
		$.post("/only/updateNotification", {type : "post"}, function(data) {
			console.log("updateNotification for post");
			updateAlertNotification(data);
		});
		
		$("#alarm_notification").click(function(e) {
			$.post("/only/alarmList", "userid="+userid, function(data) {
				$("#displayAlarmList").html(data);
				if($('#displayAlarmList').css('display')=='none'){
					$('#displayAlarmList').show();	
				}else{
					$('#displayAlarmList').hide();
				}
			});
		});		
	});
</script>
</head>
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
		<form class="form-wrapper cf" name="frm" action="/only/searchResult"
			method="post" onsubmit="return searchChk()">
			<input list="searchResult" class="searchTerm" name="searchTerm"
				placeholder="Search" onkeyup="runSearch(event)" autocomplete="off">
			<datalist id="searchResult">
			</datalist>
			<button type="submit">Search</button>
		</form>
		<div class="searchClear"></div>
	</div>
	<!-- 서치 끝 -->
	<div class="header_profile">
		<div class="header_wraper userProfile" data-click="profile_icon">
			<a id="user_profile_url" href="${path }/blog/<%=userid%>">
			<span class="userProfileName" style="float: right;"><%=member.getUsername()%></span>
			<span class="userProfileImg_span" style="float: left;">
				<div class="commentUserImg">
					<img style="vertical-align: middle" src="${path}/img_timeline/<%=member.getProfile_image()%>">
				</div>
			</span>
			</a>
		</div>

		<div class="header_wraper userApplication">
			<div class="nav_icon instant">
				<div class="friend_list instant">
					<div class="instant" id="friendList">
						<div class="instant" id="friend_notification">
							<span class="instant">Friend List</span>
						</div>
					</div>
				</div>
				<div class="friendlistplace disapper instant">
					<div class="instant" id="friendload"></div>
				</div>
			</div>
			<div class="nav_icon">
				<div class="alarm_list" data-click="alarm_list_icon">
					<!-- <a href="./timeline.jsp" id="alarmList" class="info"> -->
						<div id="alarm_notification">
							<span>Notifications</span>
						</div>
						<div id="displayAlarmList"></div>
					<!-- </a> -->
				</div>
				<div id="alertCountAll"></div>
			</div>

			<div class="nav_icon">
				<div class="message_list" data-click="message_list_icon">
					<div id="message_notification">
						<span>Message</span>
					</div>
					<div id="displayChatList" class="chatListToggle"></div>
				</div>
			</div>

			<div id="menu-toggle">
				<div id="hamburger">
					<span id="hamburegerSpan"></span> <span id="hamburegerSpan"></span>
					<span id="hamburegerSpan"></span>
				</div>
				<div id="cross">
					<span id="hamburegerSpan"></span> <span id="hamburegerSpan"></span>
				</div>
			</div>
			<div id="menu-container">
				<ul class="menu-list accordion">
					<li id="nav1" class="toggle accordion-toggle"><span
						class="icon-plus"></span> <a class="menu-link" href="#createPage">Create
							Page</a></li>

					<!-- menu-submenu accordon-content-->
					<li id="nav2" class="toggle accordion-toggle"><span
						class="icon-plus"></span> <a class="menu-link" href="#createGroup">Create
							Group</a></li>
					<!-- menu-submenu accordon-content-->
					<li id="nav3" class="toggle accordion-toggle"><span
						class="icon-plus"></span> <a class="menu-link profileSetting">Profile
							Setting</a></li>

					<li id="nav4" class="toggle accordion-toggle"><span
						class="icon-plus"></span> <a class="menu-link"
						onclick="logout()">Log Out</a></li>
					<!-- menu-submenu accordon-content-->
				</ul>
			</div>
			<!--  <div id="myDropdown" class="dropdown-content">
					<a href="#createPage">Create Page</a> <a href="#createGroup">Create
						Group</a> <a class="profileSetting">Profile Setting</a> <a
						href="/only/logout" onclick="alert('로그아웃 되었습니다');">Log Out</a>
				</div> -->
		</div>
	</div>
</div>
<form id="formfriend" action="friendList">
	<input type="hidden" id="member_id" name="member_id"
		value="<%=userid%>">
</form>
<!-- 상단고정바 끝 -->

</html>