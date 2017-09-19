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
				<ol class="post_view_box" id="postList">
					<li class="type_choice_box" id="infinite_container">
						<form action="postWrite" method="post" enctype="multipart/form-data">
							<%-- onsubmit='return sendChat(JSON.stringify({type:"post",from:"<%=id%>"}));' --%>
							<input type="hidden" value="<%=userid %>" name="member_id">
							<input type="hidden" value="<%=application.getRealPath("/fileSave")%>" name="path">
							<%
							System.out.println("path=" + application.getRealPath("/fileSave"));
							%>
							<textarea rows="1" cols="1" class="type_choice_textarea"
								name="text" placeholder="What's on your mind, <%=userid%>?"></textarea>
							<div class="write_type_choice">
								<a class="choice_type"> <img alt="" src="img_timeline/picture.svg"
									class="img_hide img_hidden type_photo" width="30px" border='0'
									onclick='document.all.imageUpload.click();'> <input
									type="file" name="imageUpload" style='display: none;'
									accept="image/png, image/gif, image/jpg">
								</a> <a class="choice_type"> <img
									src='img_timeline/video-camera.svg'
									class="img_hide img_hidden img_video" width="30px" border='0'
									onclick='document.all.videoUpload.click();'> <input
									type="file" name="videoUpload" style='display: none;'
									accept="video/mp4">
								</a> <label class="img_hide img_hidden"><br>해시태그</label><input
									class="img_hide img_hidden" type="text" name="hashtag"><br>
								<label class="img_hide img_hidden">회원태그</label><input
									class="img_hide img_hidden" type="text" name="membertag"><br>
							</div>
							<button type="submit" class="post_submit_btn">작성</button>
						</form>
					</li>
					<!-- 타입 선택 후 끝 -->
				</ol>
				<div id="main_container"></div>
				<!-- 포스트 뷰 끝 -->
			</div>
		</div>
		<jsp:include page="rightmenu.jsp"></jsp:include>
	</div>
</body>
</html>