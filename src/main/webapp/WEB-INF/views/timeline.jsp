<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="sessionChk.jsp"%>
<%@ include file="include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OnLy</title>
</head>
<c:if test="${not empty login }">
<script type="text/javascript">
alert("${login}");
</script>
</c:if>
<body>
	<div id="wrapper">
		<div id="layerPop">
			<h3>공유하기</h3>
			<hr>
			테스트
			<hr>
			테스트 <a href="#" onclick="closeLayer('layerPop')" class="close">close</a>
		</div>
	</div>
	<!-- 상단고정바 -->
	<div id="header"><jsp:include page="header.jsp"></jsp:include></div>
	<div class="header_hidden"></div>
	<!-- POST 내용 들어갈 부분 -->
	
	<div class="timeline_main">
	<jsp:include page="contacts.jsp"></jsp:include>
		<!-- 포스트작성 / 뷰 부분 -->
		<div class="timeline_post">
			<div class="page_margin"></div>
			<div id="main_container">
				<ol class="post_view_box">
					<li class="type_choice_box" id="infinite_container">
						<form action="postWrite" method="post"
							enctype="multipart/form-data">
							<input type="hidden" value="${member.userid }" name="userid">
							<input type="hidden"
								value="<%=application.getRealPath("/fileSave")%>" name="path">
							<%
								System.out.println("path=" + application.getRealPath("/fileSave"));
							%>
							<textarea rows="1" cols="1" class="type_choice_textarea"
								name="text" placeholder="What's on your mind, ${member.username }?"></textarea>
							<!-- 지도 -->
							<div id="google_place_view"></div>
							<input type="hidden" name="place" id="place" value =''>
							<input type="hidden" name="lat" id="lat" value = 0>
							<input type="hidden" name="lng" id="lng" value = 0>
							<div id='google_view'><input id='pac-input' style='display: none;' type='text' placeholder='Search Box'>
							<input type="button" id="google_button" style='display: none;' value="확인">
							<div id='map' style='width: 100%; height: 200px; display: none;'></div></div>
							<div class="tagInput" id="friendTag">
							<table id='addFriend' style='display: none;' >
							<tr  style="border-color: #adbed8;"><td class="withWho">With</td>
							<td><div id="selectedfriends" class="friendTagListSpan">
							<span><input onkeyup="runSearch(event)" id="friendTagInput" placeholder='Who are you with?'>
							</span>
							</div></td></tr></table></div>
							<div class="tagInput" id="friendTag">
							<table  ><!-- style='display: none;' -->
							<tr  style="border-color: #adbed8;"><td class="withWho" style="font-weight: bolder; font-size: larger;">#</td>
							<td><div id="selectedhash" class="friendTagListSpan">
							<span><input name="hashtag" id="hashtag" placeholder='#hashtag'>
							</span>
							</div></td></tr></table></div>
							<div class="bloc" style="display: none;">
 							<select id="friendTagList" ></select>
 							</div>
 							<div class="write_type_choice">
								<a class="choice_type" id="preview"></a>
								<a class="choice_type"> <br>
								<img alt="" src="img_timeline/picture.svg"
									class="img_hide img_hidden type_photo" width="30px" border='0'
									onclick='document.all.files.click();'> 
									<input
									type="file" name="files" style='display: none;' id="input_img"
									accept="image/*, video/*" multiple="multiple">
								</a>
								<a class="choice_type"><img alt="" src="img_timeline/location.svg"
									class="img_hide img_hidden type_photo" width="30px" border='0'
									id="google">
									<input type="button" name="google" style='display: none;' 
									id="google" onclick="google()">
									</a>
								<a class="choice_type" id="friendTagBtn"><img alt="" src="img_timeline/friendTag.png	"
									class="img_hide img_hidden type_photo" width="25px" border='0'>
									<input type="button" name="google" style='display: none;' 
									id="google" onclick="google()">
									</a>
								<!-- <label class="img_hide img_hidden"><br>해시태그</label><input
									class="img_hide img_hidden" type="text" name="hashtag"><br>
								<label class="img_hide img_hidden">회원태그</label><input
									class="img_hide img_hidden" type="text" name="membertag"><br> -->
							</div>
							<button type="submit" class="post_submit_btn">작성</button>
						</form>
					</li>
					<!-- 타입 선택 후 끝 -->
				</ol>
				<div id="postList"></div>
				<!-- 포스트 뷰 끝 -->
			</div>
		</div>
		<!-- 이미지 팝업 Modal  -->
			<div id="myModal" class="modal" style="z-index:9999;">
				<!-- The Close Button -->
				<span class="popupClose">&times;</span>
				<!-- Modal Content (The Image) -->
				<div class="display: inline-block; margin: 20px;">
				<div class="stageWrapper">
				<div class="stage" style="position: relative; text-align: center; width: 70%; margin: auto; height: 100%;">
					<img class="modal-content" id="popUpImage" style="width: 100%;">
					<video id="iframeYoutube" width="100%" src="" autoplay="autoplay" controls="controls"></video>
					<a class="prev"><div class="prevIcon" data-id="" data-index="" data-total=""></div></a>
					<a class="next"><div class="nextIcon" data-id="" data-index="" data-total=""></div></a>
				</div>
				</div>
				</div>
				<!-- Modal Caption (Image Text) -->
				<div id="caption" value="image popup"></div>
			</div>
		<jsp:include page="rightmenu.jsp"></jsp:include>
	<!-- 채팅 메신저 -->
		<jsp:include page="chat.jsp"></jsp:include>
	<!-- 프로필 수정 -->
		<jsp:include page="changeProfile.jsp"></jsp:include>
	</div>
	
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC1aRB-FJzz3Vtd6iiZdlCCk1DCfDxWDko&libraries=places&&callback=initAutocomplete1"
         async defer></script>
         
</body>
</html>