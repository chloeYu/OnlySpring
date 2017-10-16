<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="sessionChk.jsp"%>
<%@ include file="include.jsp"%>
<html>
<body>
	<!-- 상단고정바 시작 -->
	<div id="header"><jsp:include page="header.jsp"></jsp:include></div>
	<!-- 상단고정바 끝 -->
	<div class="header_hidden"></div>
	<!-- 내용 들어갈 부분 -->
	<div
		style="border-left: 0; margin-left: 172px; padding-left: 11px; padding-top: 11px;">
		<div class="search_main">
			<div class="search_post">
				<div class="search_view_box">
					<c:if test="${empty searchResult }">
						<div>
							<h3>검색 결과가 없습니다</h3>
						</div>
					</c:if>
					<c:if test="${not empty searchResult }">
						<c:forEach items="${searchResult }" var="result">
							<div class="search_view_box">
								<div>
									<div class="clearfix" style="overflow: hidden;">
										<a class="rightMargin leftFloat"> <img
											src='${path}/img_timeline/${result.profile_image }'>
										</a>
										<div style="overflow: hidden;">
											<div class="search_detail">
												<div class="clearfix">
													<c:if test="${result.userid ne member.userid}">
													<div class="memberListAction">
														<!-- 친구 액션 버튼 -->
														<div style="display: inline-block; vertical-align: middle; white-space: nowrap;">
															<div class="friendBtn">
																<c:if test="${result.relation == 1 }">
																	<a class="hidden_elem"><div class="checkFriend"></div>Friend</a>
																	<div class="friend-dropdown">
																			<a class="unfriend" data-user="${result.userid }" data-uid1="${result.uid1 }" data-uid2="${result.uid2 }">Unfriend</a>
																		</div>
																</c:if>
																<c:if test="${result.relation == 2 }">
																	<c:if test="${result.uid1 eq result.userid }"> <!-- 검색회원이 친구요청을 한 경우 -->
																		<a class="friendRequestAdd"><div class="addFriend"></div>Respond to Friend Request</a>
																		<div class="friend-dropdown">
																			<a class="accept" data-user="${result.userid }">Accept</a>
																			<a class="reject" data-user="${result.userid }">Reject</a>
																		</div>
																	</c:if>
																	<c:if test="${result.uid2 eq result.userid }"> <!-- 검색회원에게 친구요청을 한 경우 -->
																		<a class="friendRequestCancel"><div class="addFriend"></div>Friend Request Sent</a>
																		<div class="friend-dropdown">
																			<a class="cancel" data-user="${result.userid }">Cancel Request</a>
																		</div>
																	</c:if>
																</c:if>
																<c:if test="${result.relation == 0 }">
																	<a class="friendRequestBtn"  data-user='${result.userid }'><div class="addFriend"></div>Add Friend</a>
																</c:if>
															</div>
														</div>
														<!-- 친구 추가액션 버튼 onMouseOver -->
														<div class="uiPopover" style="display: inline-block; vertical-align: middle; white-space: nowrap;">
															<a class="">
																<span style="margin-right: 4px;">
																	<div class="expandBtn"></div>
																</span>
															</a>
														</div>
													</div>
													</c:if>
													<a>
														<span style=" font-size: 16px; font-weight: bold;     color: #365899;" onclick="location.href='${path}/blog/${result.userid}'">
														${result.username}	</span>
													</a>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:if>
				</div>
				<!-- 포스트 뷰 끝 -->
			</div>
		</div>
		<div>
			
		</div>
	</div>
</body>
</html>