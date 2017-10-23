<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../sessionChk.jsp"%>
<%@ include file="../include.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Page</title>
<script type="text/javascript">
$(document).ready(function() {
    $('#pid').val(${page.pid}+1);
});
</script>
</head>
<body class="pageLayout">
	<div id="header"><jsp:include page="../header.jsp"></jsp:include></div>
	<div class="header_hidden"></div>
	<div id="pCreate_pageContainer">
		<div id="pCreate_mainContainer">
			<form id="page_action" action="pageCreate" name="frm" method="post">
				분류 : <select id="page_select" name="category">
					<option value="">---분 류---</option>
					<option value="1">매장 또는 장소</option>
					<option value="2">브랜드 또는 제품</option>
					<option value="3">예술가, 밴드, 공인</option>
					<option value="4">기타</option>
				</select> <br> 커뮤니티 이름 : <input type="text" id="page_name" name="pname">
				<br> <br> <span id="d">간단 설명 :</span>
				<textarea rows="4" cols="50" id="page_text" name="ptext"></textarea>
				<br> <input type="submit" value="시작하기" id="pagestart"> <input
					type="text" id="pid" name="pid" value="${page.pid }">
			</form>
		</div>
	</div>
</body>
</html>