<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="sessionChk.jsp"%>
<%@ include file="include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FriendList</title>
</head>
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

			<ol class="search_view_box">
				<!-- 타입 선택 후 끝 -->
				<li><span style="color: #ffffff; font-weight: bold;">친구 목록</span>
					<hr></li>
				<c:if test="${empty listFriend }">
					<li class="infinite_scroll"><h3>친구 목록이 없습니다</h3></li>
				</c:if>
				<c:if test="${not empty listFriend }">
					<li class="search_result">
						<div class="search_profile">
							<table width=100%>
								<c:forEach items="${listFriend }" var="friend">
									<tr>
										<c:if test="${friend.status!=0}">
												<c:if test="${friend.status==1 }">
													<!-- 친구 -->
													<c:if test="${friend.uid1 != member.userid }">
														<td valign="middle" width="10%">
														<img src='${path}/${member.profile_image }'></td>
														<td valign="middle" align="left">
														<a href="#" class="test" id="test-${friend.uid1 }">
															<h3 onclick="location.href='${path}/blog/${friend.uid1 }'">${friend.uid1 }</h3>
														</a>
														</td>
													</c:if>
													<c:if test="${friend.uid2 != member.userid }">
														<td valign="middle" width="10%">
														<img src='${path}/${member.profile_image }'></td>
														<td valign="middle" align="left">
														<a href="#" class="test" id="test-${friend.uid2 }">
															<h3 onclick="location.href='${path}/blog/${friend.uid2 }'">${friend.uid2 }</h3>
														</a>
														</td>
													</c:if>
												</c:if>
											</c:if> <%-- <div class="hide" id="rmenu-${member.userid}" name="rmenu">
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
		
		
			<ol class="search_view_box">
				<!-- 타입 선택 후 끝 -->
				<li><span style="color: #ffffff; font-weight: bold;">친구 신청</span>
					<hr></li>
				<c:if test="${empty listFriend }">
					<li class="infinite_scroll"><h3>친구 신청 목록이 없습니다</h3></li>
				</c:if>
				<c:if test="${not empty listFriend }">
					<li class="search_result">
						<div class="search_profile">
							<table width=100%>
								<c:forEach items="${listFriend }" var="friend">
									<tr>
										<c:if test="${friend.status!=0}">
												<c:if test="${friend.status==2 }">
													<!-- 친구 -->
													<c:if test="${friend.uid1 != member.userid }">
														<td valign="middle" width="10%">
														<img src='${path}/${member.profile_image }'></td>
														<td valign="middle" align="left">
														<a href="#" class="test" id="test-${friend.uid1 }">
															<h3 onclick="location.href='${path}/blog/${friend.uid1 }'">${friend.uid1 }</h3>
														</a>
														</td>
													</c:if>
													<c:if test="${friend.uid2 != member.userid }">
														<td valign="middle" width="10%">
														<img src='${path}/${member.profile_image }'></td>
														<td valign="middle" align="left">
														<a href="#" class="test" id="test-${friend.uid2 }">
															<h3 onclick="location.href='${path}/blog/${friend.uid2 }'">${friend.uid2 }</h3>
														</a>
														</td>
													</c:if>
												</c:if>
											</c:if></td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</li>
				</c:if>
			</ol>
	</div>
	</div>

</body>
</html>