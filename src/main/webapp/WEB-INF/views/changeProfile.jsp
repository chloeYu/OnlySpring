<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ include file="sessionChk.jsp"%>
<%@ include file="include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link href="${path}/css/modifyProfile.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ChangeProfile</title>
</head>
<body>
	<div id="header">
		<jsp:include page="header.jsp"></jsp:include>
	</div>
	<div class="profileModify">
		<form name="changeProfile" action="profileDone" method="post" enctype="multipart/form-data" onsubmit="return chk()">
			<div class="useridFieldset">
				<label class="profileLabel" for="userId">
		       		아이디
				</label>
	         	<div class="profileCell modifyUserid">
		         	<input type="text" name="userid" id="userId" value="${member.userid }" readonly>
	         	</div>
			</div>
			<div class="passwordFieldset">
				<label class="profileLabel" for="password">
		   	     	비밀번호
				</label>
	         	<div class="profileCell modifyPassword">
		         	<input type="password" name="password" id="password" value="${member.password }">
	         	</div>
			</div>
			<div class="passwordChkFieldset">
				<label class="profileLabel" for="password2">
					비밀번호확인
				</label>
	         	<div class="profileCell modifyPasswordChk">
					<input type="password" id="password2" value="${member.password }">
	         	</div>
			</div>
			<div class="usernameFieldset">
				<label class="profileLabel" for="username">
					닉네임
				</label>
	         	<div class="profileCell modifyUsername">
					<input type="text" name="username" id="username" value="${member.username }">
	         	</div>
			</div>
			<div class="emailFieldset">
				<label class="profileLabel" for="email">
		   			이메일
				</label>
	         	<div class="profileCell modifyEmail">
	       			<input type="email" name="email" id="email" value="${member.email }">
	         	</div>
			</div>
			<div class="createdFieldset">
				<label class="profileLabel" for="created">
		   	     	생성날짜
				</label>
	         	<div class="profileCell modifyCreatedDate">
		         	<input type="text" id="created" name="created" value="${member.created }" readonly>
	         	</div>
			</div>
			<div class="modifiedFieldset">
				<label class="profileLabel" for="modified">
		   	     	수정날짜
				</label>
	         	<div class="profileCell modifyModifiedDate">
		         	<input type="text" id="modified" name="modified" value="${member.modified }" readonly>
	         	</div>
			</div>
			<div class="profileImageFieldset">
				<label class="profileLabel" for="fileload">
					프로필이미지
				</label>
	         	<div class="profileCell modifyProfileImage">
					<input type="file" id="fileload" name="profile_image1" size="40" accept="image/png, image/gif, image/jpg, image/bmp" >
					<%-- <input type="text" id="copyimagenameplace" value="${member.profile_image }"> --%>
	         	</div>
	      		<div id="imageplace"></div>
			</div>
      		<div class="telFieldset">
				<label class="profileLabel" for="profileTel">
		         	전화번호
				</label>
	         	<div class="profileCell modifyTel">
		         	<input type="tel" name="tel" id="profileTel" value="${member.tel }" onkeypress="return telValidate(event);" maxlength="12">
	         	</div>
      		</div>
      		<div class="birthFieldset">
				<label class="profileLabel" for="profileBirth">
		       		생년월일
				</label>
	         	<div class="profileCell modifyBirth">
		       		<input type="date" name="birth1" id="profileBirth" value="${member.birth }">
	         	</div>
      		</div>
      		<div class="submitFieldset">
	         	<div class="profileCell modifySubmit">
		         	<input type="submit" value="개인정보수정">
		         	<input type="button" value="취소" onclick="exit()">
	         	</div>
      		</div>
      		<input type="hidden" name="existingImage" value="${member.profile_image }">
		</form>
	</div>
</body>
</html>