<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<link href="${path}/css/modifyProfile.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ChangeProfile</title>
<script type="text/javascript">
	$(document).ready(
			function() {
				//마지막수정날짜값이 없을시 계정생성날짜로 대체
				if (!("${member.modified}")) {
					$("#modified").val("${member.created }");
				}
				//프로필파일 미리보기 및 선택.
				$("#fileload").on("change", function(e) {
					imgchk(e);
				});

				// open popup
				$('.profileSetting').on('click', function(event) {
					event.preventDefault();
					$('.profileModify').addClass('is-visible');
				});

				// close popup
				$('.profileModify').on(
						'click',
						function(event) {
							if ($(event.target).is('.cd-popup-close')
									|| $(event.target).is('.profileModify')) {
								event.preventDefault();
								$(this).removeClass('is-visible');
							}
						});
				// close popup when clicking the esc keyboard button
				$(document).keyup(function(event) {
					if (event.which == '27') {
						$('.profileModify').removeClass('is-visible');
					}
				});
			});
	function imgchk(e) {
		var thumbext1 = document.getElementById('fileload').value; //파일을 추가한 input 박스의 값
		var thumbext = thumbext1.slice(thumbext1.indexOf(".") + 1)
				.toLowerCase(); //파일 확장자를 잘라내고, 비교를 위해 소문자로 만듭니다.
		if (thumbext != "jpg" && thumbext != "png" && thumbext != "gif"
				&& thumbext != "bmp") { //확장자를 확인합니다.
			alert('프로필은 이미지 파일(jpg, png, gif, bmp)만 등록 가능합니다.');
			$("#fileload").val(null);
			$("#imageplace").html(null);
			$("#copyimagenameplace").val(null);
		} else {//이미지 미리보기 기능.
			$("#copyimagenameplace").val($("#fileload").val());
			var file = e.target.files[0], reader = new FileReader();
			var img = new Image();
			reader.onload = function(e) {
				img.src = e.target.result;
				img.width = 280; // 미리보기 크기 조절.
				img.height = 280;
				$("#imageplace").empty();
				$("#imageplace").append(img);
			}
			reader.readAsDataURL(file);
		}
	}
	//퇴장
	function exit() {
		location.href="timeline";
	}
	//폼체크
	function chk() {
		if (!(changeProfile.password.value.trim())) {
			alert("비밀번호를 입력하여주십시오!!");
			$("#password").focus();
			return false;
		}
		if (changeProfile.password.value.match(/\s/g)) {
			alert("비밀번호에는 공백이 들어갈 수 없습니다!!");
			$("#password").focus();
			return false;
		}
		if (changeProfile.password.value != changeProfile.password2.value) {
			alert("비밀번호랑 비밀번호확인이 일치하지 않습니다!!")
			$("#password").focus();
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<section>
		<div class="profileModify">
			<div class="profileModify-inner">
				<form class="changeProfile" name="changeProfile" action="profileDone" method="post"
					enctype="multipart/form-data" onsubmit="return chk()">
					<div class="Modify-Title">
						<h1 style="font-size: 30px; margin-bottom: 30px; margin-top: 0">Profile Setting</h1>
					</div>
					<div class="useridFieldset">
						<label class="profileLabel" for="userId"> 아이디 </label>
						<div class="profileCell modifyUserid">
							<input type="text" name="userid" id="userId"
								value="${member.userid }" readonly>
						</div>
					</div>
					<div class="passwordFieldset">
						<label class="profileLabel" for="password"> 비밀번호 </label>
						<div class="profileCell modifyPassword">
							<input type="password" name="password" id="password"
								value="${member.password }">
						</div>
					</div>
					<div class="passwordChkFieldset">
						<label class="profileLabel" for="password2"> 비밀번호확인 </label>
						<div class="profileCell modifyPasswordChk">
							<input type="password" id="password2" value="${member.password }">
						</div>
					</div>
					<div class="usernameFieldset">
						<label class="profileLabel" for="username"> 닉네임 </label>
						<div class="profileCell modifyUsername">
							<input type="text" name="username" id="username"
								value="${member.username }">
						</div>
					</div>
					<div class="emailFieldset">
						<label class="profileLabel" for="email"> 이메일 </label>
						<div class="profileCell modifyEmail">
							<input type="email" name="email" id="email"
								value="${member.email }">
						</div>
					</div>
					<div class="createdFieldset">
						<label class="profileLabel" for="created"> 생성날짜 </label>
						<div class="profileCell modifyCreatedDate">
							<input type="text" id="created" name="created"
								value="${member.created }" readonly>
						</div>
					</div>
					<div class="modifiedFieldset">
						<label class="profileLabel" for="modified"> 수정날짜 </label>
						<div class="profileCell modifyModifiedDate">
							<input type="text" id="modified" name="modified"
								value="${member.modified }" readonly>
						</div>
					</div>
					<div class="profileImageFieldset">
						<label class="profileLabel" for="fileload"> 프로필이미지 </label>
						<div class="profileCell modifyProfileImage">
							<input type="file" id="fileload" name="profile_image1" size="40"
								accept="image/png, image/gif, image/jpg, image/bmp">
							<%-- <input type="text" id="copyimagenameplace" value="${member.profile_image }"> --%>
						</div>
						<div id="imageplace"></div>
					</div>
					<div class="telFieldset">
						<label class="profileLabel" for="profileTel"> 전화번호 </label>
						<div class="profileCell modifyTel">
							<input type="tel" name="tel" id="profileTel"
								value="${member.tel }" onkeypress="return telValidate(event);"
								maxlength="12">
						</div>
					</div>
					<div class="birthFieldset">
						<label class="profileLabel" for="profileBirth"> 생년월일 </label>
						<div class="profileCell modifyBirth">
							<input type="date" name="birth1" id="profileBirth"
								value="${member.birth }">
						</div>
					</div>
					<div class="submitFieldset">
						<div class="profileCell modifySubmit">
							<input class="submitTop" type="submit" value="개인정보수정"> 
							<input class="cd-popup-close submitBottom" type="button" value="취소" onclick="exit()">
						</div>
					</div>
					<input type="hidden" name="existingImage"
						value="${member.profile_image }">
				</form>
			</div>
		</div>
	</section>
</body>
</html>