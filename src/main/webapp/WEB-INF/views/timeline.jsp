<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="sessionChk.jsp"%>
<%@ include file="include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OnLy</title>
</head>
<body>
	<div id="wrapper">
		<div id="layerPop">
			<h3>공유하기</h3>
			<hr>
			테스트
			<hr>
			테스트 <a href="#" onclick="closeLayer('layerPop')" class="close">close</a>
		</div>
	</div>
	<!-- 상단고정바 시작 -->
	<div id="header"><jsp:include page="header.jsp"></jsp:include></div>
	<div class="header_hidden"></div>
	<!-- 내용 들어갈 부분 -->
	<div class="timeline_main">
		<!-- 포스트작성 / 뷰 부분 -->
		<div class="timeline_post">
			<div class="page_margin"></div>
			<!-- 테스트 -->
			<!--  -->
			<div id="main_container">
			</div>
			<!-- 포스트 뷰 끝 -->
		</div>
		<jsp:include page="menu.jsp"></jsp:include>
		<!-- <input id="FKKK" type="submit" value="나가기" style="width: 112px; float: left; height: 26px;"> -->
	</div>
	<!-- 내용 들어갈 부분 끝 -->

</body>
</html>