<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../sessionChk.jsp" %>
<%@ include file="../include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${owner }</title>
</head>
<body class="blogLayout">
	<div id="header"><jsp:include page="../header.jsp"></jsp:include></div>
	<div class="header_hidden"></div>
	<div id="globalContainer">
		<div id="mainContainer">
			<div id="leftCol"></div>
			<div id="contentCol">
				<div id="contentArea">
					<div class="blogTopSection">
						<div id="profileCover">
							<div class="coverImage">
								<a class="coverWrap">
									<img class="photo"src="https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/c0.78.960.355/561844_4028233778472_1070318011_n.jpg?oh=1dfb165db2d646245354101f9be80a00&oe=5A872339">
								</a>
							</div>
							<div class="blogProfileName"><h1 class="h1ProfileName">Bokyung Yu</h1></div>
						</div>
						<div class="profileHeadline">
							<div class="profileNav">
								<ul stype="border-left: 1px solid #e9eaed;">
									<li><a>Timeline</a></li>
									<li><a>About</a></li>
									<li><a>Friends</a></li>
									<li><a>Photo</a></li>
								</ul>
							</div>
							<div class="profileThumb">
								<div class="photoContainer">
									<a class="profilePicThumb" >
										<img src="https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-1/p240x240/12189038_10208306516209197_5877572499878530668_n.jpg?oh=575b2b6e27c8a227d743bfe997d697b6&oe=5A415E36">
									</a>
								</div>
						</div>
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>