<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../sessionChk.jsp"%>
<%@ include file="../include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Page</title>
</head>
<body class="pageLayout">
	<div id="header"><jsp:include page="../header.jsp"></jsp:include></div>
	<div class="header_hidden"></div>
	<div id="pageContainer">
		<div id="mainContainer">
			<form action="page_action" name="frm">
				분류 : <select id="page_select">
					<option value="">---분 류---</option>
					<option value="매장 또는 장소">매장 또는 장소</option>
					<option value="브랜드 또는 제품">브랜드 또는 제품</option>
					<option value="예술가, 밴드, 공인">예술가, 밴드, 공인</option>
					<option value="기타">기타</option>
				</select> <br> 커뮤니티 이름 : <input type="text" id="page_name"> <br>
				로고 : <input type="file" id="page_log"> <br> 간단 설명 :
				<textarea id="page_text"></textarea>
				<br> <input type="button" value="시작하기" onclick="pagestart()">
				<div id="page_test"></div>
			</form>
		</div>
	</div>
</body>
</html>