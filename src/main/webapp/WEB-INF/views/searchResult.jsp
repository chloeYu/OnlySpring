<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="sessionChk.jsp"%>
<%@ include file="include.jsp" %>
<body>
	<!-- 상단고정바 시작 -->
	<div id="header"><jsp:include page="header.jsp"></jsp:include></div>
	<!-- 상단고정바 끝 -->
	<div class="header_hidden"></div>
	<!-- 내용 들어갈 부분 -->

	<div class="search_main">
		<!-- 포스트작성 / 뷰 부분 -->
		<div class="search_post">
			<div class="page_margin"></div>
			<!-- 테스트 -->
			<!--  -->
			<ol class="search_view_box">
				<!-- 타입 선택 후 끝 -->
				<li><span style="color: #ffffff; font-weight: bold;">Search Result</span>
					<hr></li>
				<c:if test="${empty searchResult }">
					<li class="infinite_scroll"><h3>검색 결과가 없습니다</h3></li>
				</c:if>
				<c:if test="${not empty searchResult }">
				<li class="search_result">
					<div class="search_profile">
						<table width=100%>
							<c:forEach items="${searchResult }" var="member">
								<tr>
								<td valign="middle" width="10%"><img src='${path}/${member.profile_image }'></td>
								<td valign="middle" align="left">
								<a href="#" class="test" id="test-${member.userid}">
								<h3 onclick="location.href='${path}/blog/${member.userid}'">${member.userid} - ${member.relation }</h3>
										<%-- <div class="hide" id="rmenu-${member.userid}" name="rmenu">
											<ul>
												<li><a href="${path}/blog/${member.userid}">Visit</a></li>
												<%if(sendT.equals("") || sendT==null || sendT.equals(getT)){
													System.out.println("본인 생략");
												} else{%>
												<li><a href="#" id="follow"
													data-followid="<%=member.getUserid()%>"><span
														id="followText-<%=member.getUserid()%>">Follow</span></a></li>
												<li><a href="#" class="chatStart <%=chatroom%>"
													id="<%=chatroom%>" data-sendT="<%=sendT%>"
													data-getT="<%=getT%>">Send Message</a></li>
												<%} %>

											</ul>
										</div> --%>
										</a>
										</td>
								</tr>
								<%-- <tr>
								<td colspan="2">
									<hr><%=member.getEmail()%><br> <%=member.getProfile_image()%><br>
									<%=member.getBirth()%><br>
									<hr>
								</td>
								</tr> --%>
						</c:forEach>
						</table>
						</div>
						</li>
				</c:if>
			</ol>
			<!-- 포스트 뷰 끝 -->
		</div>
		<!-- 포스트작성 / 뷰 끝 -->
		<!-- aside 부분 / *팔로우 추천, 광고등 -->
		<jsp:include page="rightmenu.jsp"></jsp:include>
	</div>
	<!-- 내용 들어갈 부분 끝 -->
</body>
</html>