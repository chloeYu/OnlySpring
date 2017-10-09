<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="sessionChk.jsp"%>
<%@ include file="include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OnLy</title>
</head>
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
		<!-- 포스트작성 / 뷰 부분 -->
		<div class="timeline_post">
			<div class="page_margin"></div>
			<div id="main_container">
				<ol class="post_view_box">
					<li class="type_choice_box" id="infinite_container">
						<form action="postWrite" method="post"
							enctype="multipart/form-data">
							<%-- onsubmit='return sendChat(JSON.stringify({type:"post",from:"<%=id%>"}));' --%>
							<input type="hidden" value="<%=userid%>" name="userid">
							<input type="hidden"
								value="<%=application.getRealPath("/fileSave")%>" name="path">
							<%
								System.out.println("path=" + application.getRealPath("/fileSave"));
							%>
							<textarea rows="1" cols="1" class="type_choice_textarea"
								name="text" placeholder="What's on your mind, <%=userid%>?"></textarea>
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
							<div class="bloc" style="display: none;">
 							<select id="friendTagList" ></select>
 							</div>
 							<div class="write_type_choice">
								<a class="choice_type" id="preview"></a>
								<a class="choice_type"> <br><img alt="" src="img_timeline/picture.svg"
									class="img_hide img_hidden type_photo" width="30px" border='0'
									onclick='document.all.files.click();'> 
									<input
									type="file" name="files" style='display: none;' id="input_img"
									accept="image/png, image/gif, image/jpg" onchange="previewFiles()" multiple>
								<!-- </a> <a class="choice_type"> <img
									src='img_timeline/video-camera.svg'
									class="img_hide img_hidden img_video" width="30px" border='0'
									onclick='document.all.videoUpload.click();'> <input
									type="file" name="videoUpload" style='display: none;'
									accept="video/mp4"> -->
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
				<div id="postList">
					
				</div>
				<!-- 포스트 뷰 끝 -->
			</div>
		</div>
		<jsp:include page="rightmenu.jsp"></jsp:include>
	</div>
	
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC1aRB-FJzz3Vtd6iiZdlCCk1DCfDxWDko&libraries=places&&callback=initAutocomplete1"
         async defer></script>
         
</body>
</html>