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
    $('#pageCreate').on('click', pageover);
});

function pageover() {
	if(${page.pid } > 0){
		alert("페이지는 한개만 설정 가능");
	}
}
</script>
</head>
<body class="pageLayout">
	<div id="header"><jsp:include page="../header.jsp"></jsp:include></div>
	<div class="header_hidden"></div>
	<div id="pageContainer">
		<div id="menubar">
			<div id="menubar_left">
				<a>전체 페이지</a> <a>좋아하는 페이지</a>
			</div>
			<div id="menubar_right">
				<a href="${path}/pagemain/${page.pid}">회원님의 페이지</a> <a
					href="${path}/pageCreate" id="pageCreate">페이지 만들기</a>
			</div>
		</div>
		<div id="mainContainer">
			<div id="page_box_Container">
				<div class="page_box" id="page_box_1">
					<img alt=""
						src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR_OHGUdWw_d6uII-s3E1v3zVTVUwL06Ak6oVySI4EZDbVtrw1KMA">
					<br>
					<div id="pagename">
						<a href="#pageLike">pagename</a> <br> <b>pagecategory</b>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>