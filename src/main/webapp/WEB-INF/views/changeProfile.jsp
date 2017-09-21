<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>ChangeProfile</title>
<script type="text/javascript">
	$(document).ready(function() {
		if (!("${member.modified}")) {
			$("#modified").val("${member.created }");
		}
	});
	function chk() {
		if (!(changeProfile.password.value.trim())) {
			alert("비밀번호를 입력하여주십시오!!");
			return false;
		}
		if (changeProfile.password.value.match(/\s/g)) {
			alert("비밀번호에는 공백이 들어갈 수 없습니다!!");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<form name="changeProfile" action="profileDone" method="post"
		onsubmit="return chk()">
		아이디 <input type="text" name="userid" value="${member.userid }" readonly>
		<p>
			비밀번호<input type="password" name="password" id="password" value="${member.password }">
		<p>
			비밀번호확인<input type="password" id="password2"
				value="${member.password }">
		<p>닉네임<input type="text" name="username" value="${member.username }">
		<p>
			이메일<input type="text" name="email" value="${member.email }">
		<p>
			생성날짜<input type="text" value="${member.created }" readonly>
		<p>
			수정날짜<input type="text" id="modified" value="${member.modified }"
				readonly>
		<p>
			프로필이미지<input type="text" name="profile_image" value="${member.profile_image }"><input
				type="button" value="불러오기">
		<p>
			전화번호<input type="text" name="tel" value="${member.tel }">
		<p>
			생년월일<input type="text" name="birth1" value="${member.birth }">
		<p>
			<input type="submit" value="개인정보수정"> <input type="button"
				value="취소">
		<p>
			<input type="hidden" value="${member.userid }" name="member_id">
	</form>
</body>
</html>