<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ChangeProfile</title>
<script type="text/javascript">
	$(document).ready(function() {
		//마지막수정날짜값이 없을시 계정생성날짜로 대체
		if (!("${member.modified}")) {
			$("#modified").val("${member.created }");
		}
		//프로필파일 미리보기 및 선택.
		$("#fileload").on("change", function(e){
			imgchk(e);
		});
		imgchk($("#profile_image_name").val());
	});
	function imgchk(e){
		var thumbext1 = document.getElementById('fileload').value; //파일을 추가한 input 박스의 값
		var thumbext = thumbext1.slice(thumbext1.indexOf(".") + 1).toLowerCase(); //파일 확장자를 잘라내고, 비교를 위해 소문자로 만듭니다.
		if(thumbext != "jpg" && thumbext != "png" &&  thumbext != "gif" &&  thumbext != "bmp"){ //확장자를 확인합니다.
			alert('프로필은 이미지 파일(jpg, png, gif, bmp)만 등록 가능합니다.');
		$("#fileload").val(null);
		$("#imageplace").html(null);
		} else{//이미지 미리보기 기능.
			  var file = e.target.files[0],
			  reader = new FileReader();
			  var img = new Image();
			  reader.onload = function(e){
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
	function exit(){
		location.href = "timeline";
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
		if(!(changeProfile.fileload.value.trim())){
			alert("프로필사진을 선택해주십시오.");
			return false;
		}
		return true;
	}
</script>
<style type="text/css">
	#imgplace{
		width: "281";
	}
</style>
</head>
<body>
	<form name="changeProfile" action="profileDone" method="post"
		onsubmit="return chk()">
		아이디 <input type="text" name="userid" value="${member.userid }"
			readonly>
		<p>
			비밀번호<input type="password" name="password" id="password"
				value="${member.password }">
		<p>
			비밀번호확인<input type="password" id="password2"
				value="${member.password }">
		<p>
			닉네임<input type="text" name="username" value="${member.username }">
		<p>
			이메일<input type="text" name="email" value="${member.email }">
		<p>
			생성날짜<input type="text" value="${member.created }" readonly>
		<p>
			수정날짜<input type="text" id="modified" value="${member.modified }"
				readonly>
		<p>
			프로필이미지<input type="file"
				id="fileload" name="profile_image" size="40"
				accept="image/png, image/gif, image/jpg, image/bmp" value=${member.profile_image }>
		<p>
		<div id="imageplace"></div>
			전화번호<input type="text" name="tel" value="${member.tel }">
		<p>
			생년월일<input type="date" name="birth1" value="${member.birth }">
		<p>
			<input type="submit" value="개인정보수정"> <input type="button"
				value="취소" onclick="exit()">
		<p>
			<input type="hidden" value="${member.userid }" name="member_id">
			<input type="hidden" id="profile_image_name" value="${member.profile_image }" name="member_id">
	</form>
</body>
</html>