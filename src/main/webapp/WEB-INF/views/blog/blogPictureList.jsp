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
		<%@ include file="updatePic.jsp"%>
	</c:if>
	<div id="header"><jsp:include page="../header.jsp"></jsp:include></div>
	<div class="header_hidden"></div>

	<div id="globalContainer">
		<div id="mainContainer">
			<div id="leftCol"></div>
			<jsp:include page="blogHeader.jsp"></jsp:include>

			<!-- 사진 리스트 -->
			<div class="blogFriendList" style="display: node;">
				<div class="container">
					<div class="title">
						<div class="titleWraper">
							<h3>
								<img
									src="https://www.facebook.com/rsrc.php/v3/yl/r/DjG18OrMbQh.png"
									style="margin: 0 9px 0 0; vertical-align: middle;"> <a
									href="#" style="color: #4b4f56;">Photos</a>
							</h3>
						</div>
					</div>
					<div class="friendListWraper">
						<ul id="photoListDisplay">
						</ul>
					</div>
				</div>
			</div>
			<!-- 이미지 팝업 Modal  -->
			<div id="myModal" class="modal" style="z-index:9999;">
				<!-- The Close Button -->
				<span class="popupClose">&times;</span>
				<!-- Modal Content (The Image) -->
				<img class="modal-content" id="img01">
				<!-- Modal Caption (Image Text) -->
				<div id="caption"></div>
			</div>
		</div>
	</div>
</body>
</html>