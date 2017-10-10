<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../sessionChk.jsp"%>
<%@ include file="../include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${owner.username }</title>
</head>
<body class="blogLayout">
	<c:if test="${owner.userid eq member.userid }">
		<%@ include file="updatePic.jsp" %>
	</c:if>
	<div id="header"><jsp:include page="../header.jsp"></jsp:include></div>
	<div class="header_hidden"></div>
	
	<div id="globalContainer">
		<div id="mainContainer">
			<div id="leftCol"></div>
			<div id="contentCol">
				<div id="contentArea">
					<div class="blogTopSection">
						<div id="profileCover">
							<div class="coverImage">
								<a class="coverWrap"> <img class="photo"
									src="https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/c0.78.960.355/561844_4028233778472_1070318011_n.jpg?oh=1dfb165db2d646245354101f9be80a00&oe=5A872339">
								</a>
								<c:if test="${owner.userid eq member.userid }">
									<div class="coverImageUpdate">
										<div class="editCoverBtn"></div>
									</div>
								</c:if>
								</div>
							</div>
							<div class="blogProfileName">
								<h1 class="h1ProfileName">Bokyung Yu</h1>
							</div>
						</div>
						<div class="profileHeadline">
							<div class="profileNav">
								<ul stype="border-left: 1px solid #e9eaed;">
									<li><a>Timeline</a></li>
									<li><a>About</a></li>
									<li><a>Friends</a></li>
									<li><a>Photo</a></li>
								</ul>
							</div>
							<div class="profileThumb">
								<div class="photoContainer">
									<a class="profilePicThumb"> 
										<img src="https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-1/p240x240/12189038_10208306516209197_5877572499878530668_n.jpg?oh=575b2b6e27c8a227d743bfe997d697b6&oe=5A415E36">
										<c:if test="${owner.userid eq member.userid }">
											<div class="thumbImageUpdate">
												<div class="editCoverBtnHover">
													<table>
													<tr><td><div class="editCoverBtn"></div></td>
													<td><span>Update Profile Picture</span></td>
													</table>
												</div>
											</div>
										</c:if>
									</a>
									
								</div>
							</div>
						</div>

					</div>

					<div class="blogTimelineCapsule">
						<div class="blogTimelineContent" id="recent_capsule_container">
							<ol class="post_view_box">
								<li class="type_choice_box" id="infinite_container">
									<form action="postWrite" method="post"
										enctype="multipart/form-data">
										<input type="hidden" value="<%=userid%>" name="userid">
										<input type="hidden"
											value="<%=application.getRealPath("/fileSave")%>" name="path">
										<c:if test="${owner.userid eq member.userid }">
											<textarea rows="1" cols="1" class="type_choice_textarea"
												name="text"
												placeholder="What's on your mind , ${member.username }?"></textarea>
										</c:if>
										<c:if test="${owner.userid ne member.userid }">
											<textarea rows="1" cols="1" class="type_choice_textarea"
												name="text"
												placeholder="Write Someting to , ${owner.username }?"></textarea>

										</c:if>
										<!-- 지도 -->
										<div id="google_place_view"></div>
										<input type="hidden" name="place" id="place" value=''>
										<input type="hidden" name="lat" id="lat" value=0> <input
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
													<td><div id="selectedfriends"
															class="friendTagListSpan">
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
											<a class="choice_type" id="preview"></a> <a
												class="choice_type"> <br>
											<img alt="" src="img_timeline/picture.svg"
												class="img_hide img_hidden type_photo" width="30px"
												border='0' onclick='document.all.files.click();'> <input
												type="file" name="files" style='display: none;'
												id="input_img" accept="image/png, image/gif, image/jpg"
												onchange="previewFiles()" multiple> <!-- </a> <a class="choice_type"> <img
									src='img_timeline/video-camera.svg'
									class="img_hide img_hidden img_video" width="30px" border='0'
									onclick='document.all.videoUpload.click();'> <input
									type="file" name="videoUpload" style='display: none;'
									accept="video/mp4"> -->
											</a> <a class="choice_type"><img alt=""
												src="img_timeline/location.svg"
												class="img_hide img_hidden type_photo" width="30px"
												border='0' id="google"> <input type="button"
												name="google" style='display: none;' id="google"
												onclick="google()"> </a> <a class="choice_type"
												id="friendTagBtn"><img alt=""
												src="img_timeline/friendTag.png	"
												class="img_hide img_hidden type_photo" width="25px"
												border='0'> <input type="button" name="google"
												style='display: none;' id="google" onclick="google()">
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
						</div>
						<div class="blogTimelineMenu">
							<div style="bottom: 0px; left: 54px; top: auto; z-index: 0; width: 323px;">
							<span display="inline">
								<ol class="blogMenuList">
									<div id="photos_tile">
										<li class="photos_tile_col">
											<div style="border-radius: 3px; background-color: #ffffff">
												<div class="tile_title_wraper">
												<a href="#"><span style="font-size: 16px; line-height: 20px;">Photo</span></a>
												</div>
											</div>
										</li>
									</div>
									<div id="friends_tile"></div>
								</ol>
							</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>