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
	$(document)
			.ready(
					function() {
						$('#pageCreate').on('click', pageover);

						var j = $("#pagecount").val();
						var pid = "${page.pid}";
						
						if (pid == 0) {
							$("#my_page").hide();
						}

						if (j == 0) {
							$(".page_box").hide();
						} else {
							for (var i = 2; i <= j; i++) {
								var html = "<div class='page_box' id='page_box_"+i+"'><img alt='' src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR_OHGUdWw_d6uII-s3E1v3zVTVUwL06Ak6oVySI4EZDbVtrw1KMA'>"
										+ "<br><div id='pagename'><a href='${path}/pagemain/"+i+"'>pagename</a><br><b>pagecategory</b>"
										+ "</div></div>";
								$("#page_box_Container").append(html);
							}
						}

					});

	function pageover() {
		if ('${page.pid }' > 0) {
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
				<a id="allpage"
					style="font-weight: bold; padding: 5px; border-bottom: 2px solid #1d405f;">전체
					페이지</a> <a id="likepage">좋아하는 페이지</a>
			</div>
			<div id="menubar_right">
				<a id="my_page" href="${path}/pagemain/${page.pid}">회원님의 페이지</a> <a
					href="${path}/pageCreate" id="pageCreate">페이지 만들기</a>
			</div>
		</div>
		<div id="page_main">
			<div id="page_box_Container">
				<input type="hidden" value="${page.del }" id="pagecount">
				<div class="page_box" id="page_box_1">
					<img alt=""
						src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR_OHGUdWw_d6uII-s3E1v3zVTVUwL06Ak6oVySI4EZDbVtrw1KMA">
					<br>
					<div id="pagename">
						<a href="${path}/pagemain/1">pagename</a> <br> <b>pagecategory</b>
					</div>
				</div>
			</div>
		</div>

		<input type="text" value="${page.pid }">
	</div>
</body>
</html>