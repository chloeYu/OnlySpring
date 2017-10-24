<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../sessionChk.jsp"%>
<%@ include file="../include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${pp.pname } Page</title>
<script type="text/javascript">
$(function() {
    if($("#puserid").val() != $("#muserid").val()){
    	$("#pageset").hide();
    }
});
</script>
</head>
<body class="pageLayout">
	<div id="header"><jsp:include page="../header.jsp"></jsp:include></div>
	<div class="header_hidden"></div>
	<div id="pageContainer">
		<div id="left_menu">
			<img alt=""
				src="<%-- ../img_timeline/${pp.logo_image } --%>
			https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQISsJjhBMxd4wE6TvpP5A9Cu9Mz54luurjgTGLNQV6W9jQi3n4jw">
			<br> <a href="${path}/pagemain/${pp.pid}" id="text_pagehome">${pp.pname }</a>
			<br>
			<ul id="left_menu_ul">
				<li><a>홈</a></li>
				<li><a>게시물</a></li>
				<li><a>리뷰</a></li>
				<li><a>동영상</a></li>
				<li><a>사진</a></li>
				<li><a>정보</a></li>
				<li><a>커뮤니티</a></li>
			</ul>
		</div>
		<div id="pageTimeline_view">
			<div id="main_img">
				<img alt=""
					src="https://i1.daumcdn.net/thumb/C720x360/?fname=http://t1.daumcdn.net/brunch/service/user/1ekq/image/kYsgk2G31uDmg6I1lf-9_ViXOhQ.jpg
				<%-- ../img_timeline/${pp.cover_image } --%>">
			</div>
			<div id="pageTimeline_centermenu">
				<%-- <input type="hidden" name="username" value="${member.username}" id="username">  --%>
				<input type="button" value="좋아요" id="like_button"
					onclick="page_like()"> <input type="button" value="설정"
					id="pageset">
			</div>
			<div id="pageTimeline_left">
				<div id="main_container">
					<ol class="post_view_box">
						<li class="type_choice_box" id="infinite_container">
							<form action="postWrite" method="post"
								enctype="multipart/form-data">
								<%-- onsubmit='return sendChat(JSON.stringify({type:"post",from:"<%=id%>"}));' --%>
								<input type="hidden" value="" name="userid"> <input
									type="hidden" value="<%=application.getRealPath("/fileSave")%>"
									name="path">
								<%
									System.out.println("path=" + application.getRealPath("/fileSave"));
								%>
								<textarea rows="1" cols="1" class="type_choice_textarea"
									name="text"
									placeholder="What's on your mind, ${member.username }?"></textarea>
								<!-- 지도 -->
								<div id="google_place_view"></div>
								<input type="hidden" name="place" id="place" value=''> <input
									type="hidden" name="lat" id="lat" value=0> <input
									type="hidden" name="lng" id="lng" value=0>
								<div id='google_view'>
									<input id='pac-input' style='display: none;' type='text'
										placeholder='Search Box'> <input type="button"
										id="google_button" style='display: none;' value="확인">
									<div id='map'
										style='width: 100%; height: 200px; display: none;'></div>
								</div>
								<div class="tagInput" id="friendTag">
									<table id='addFriend' style='display: none;'>
										<tr style="border-color: #adbed8;">
											<td class="withWho">With</td>
											<td><div id="selectedfriends" class="friendTagListSpan">
													<span><input onkeyup="runSearch(event)"
														id="friendTagInput" placeholder='Who are you with?'>
													</span>
												</div></td>
										</tr>
									</table>
								</div>
								<div class="bloc" style="display: none;">
									<select id="friendTagList"></select>
								</div>
								<div class="write_type_choice">
									<a class="choice_type" id="preview"></a> <a class="choice_type">
										<br> <img alt="" src="img_timeline/picture.svg"
										class="img_hide img_hidden type_photo" width="30px" border='0'
										onclick='document.all.files.click();'> <input
										type="file" name="files" style='display: none;' id="input_img"
										accept="image/png, image/gif, image/jpg"
										onchange="previewFiles()" multiple> <!-- </a> <a class="choice_type"> <img
									src='img_timeline/video-camera.svg'
									class="img_hide img_hidden img_video" width="30px" border='0'
									onclick='document.all.videoUpload.click();'> <input
									type="file" name="videoUpload" style='display: none;'
									accept="video/mp4"> -->
									</a> <a class="choice_type"><img alt=""
										src="img_timeline/location.svg"
										class="img_hide img_hidden type_photo" width="30px" border='0'
										id="google"> <input type="button" name="google"
										style='display: none;' id="google" onclick="google()">
									</a> <a class="choice_type" id="friendTagBtn"><img alt=""
										src="img_timeline/friendTag.png	"
										class="img_hide img_hidden type_photo" width="25px" border='0'>
										<input type="button" name="google" style='display: none;'
										id="google" onclick="google()"> </a>
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
			<div id="pageTimeline_right">
			<input type="hidden" value="${pp.userid }" id="puserid">
			<input type="hidden" value="${member.userid }" id="muserid">	
				<div id="pageTimeline_right_veiw1">
					<input id="category" type="hidden" value="${pp.category }">
					<b id="category_view"></b><br> <b id="page_text">좋아요 수 : </b><b
						id="page_like_view"></b>
				</div>
				<div id="pageTimeline_right_veiw2">
					<b id="page_text">정보 : </b> <b id="ptext">${pp.ptext}</b>
				</div>
			</div>
		</div>
	</div>
</body>
</html>