<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ include file="sessionChk.jsp"%>
<%@ include file="include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ChangeProfile</title>
</head>
<body>
   <form name="changeProfile" action="profileDone" method="post" enctype="multipart/form-data" onsubmit="return chk()">
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
            id="fileload" name="profile_image1" size="40"
            accept="image/png, image/gif, image/jpg, image/bmp" > <input type="text" id="copyimagenameplace" name="copyimagenameplace" value="${member.profile_image }">
      <p>
      <div id="imageplace"></div>
         전화번호<input type="text" name="tel" value="${member.tel }">
      <p>
         생년월일<input type="date" name="birth1" value="${member.birth }">
      <p>
         <input type="submit" value="개인정보수정"> <input type="button"
            value="취소" onclick="exit()">
      <p>
   </form>
</body>
</html>