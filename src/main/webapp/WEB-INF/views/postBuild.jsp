<%@ page import="only.utils.WebConstants"%>
<%@ page import="only.model.Post"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="sessionChk.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty plist }">
	<c:forEach var="post" items="${plist }">
		<li class='infinite_scroll'>
			<div class='postUid'>
				<c:if test="${not empty post.username }">
					<div class="post_header">
						<a href="blog/${post.userid }"> <img
							style="vertical-align: middle"
							src="img_timeline/<%=member.getProfile_image()%>"> <span>[${post.pid }]${post.username }</span>
						</a>
					</div>
				</c:if>
			</div>
			<hr> <c:if test="${not empty post.files }">
				<c:forEach var="file" items="${post.files }" varStatus="i">
					<c:if test="${post.files.size() < 4 }">
						<div class="postImg1">
							<img class="postInner1" src='img_timeline/${file.url }'>
						</div>
					</c:if>
					<c:if test="${post.files.size() == 4 }">
						<div class="postImg2">
							<img class="postInner2" src='img_timeline/${file.url }'>
						</div>
					</c:if>
					<c:if test="${post.files.size() > 4 }">
						<div class="postImg3">
							<c:choose>
								<c:when test="${i.index == 3}">
									<img id='post${post.pid }-${i.index}' class="postInner3"
										src='img_timeline/${file.url }'>
									<span>+More</span>
								</c:when>
								<c:otherwise>
									<img id='post${post.pid }-${i.index}' class="postInner3"
										src='img_timeline/${file.url }'>
								</c:otherwise>
							</c:choose>
						</div>
					</c:if>
				</c:forEach>
			</c:if> <c:if test="${not empty post.text }">
				<h3>${post.text }</h3>
			</c:if>
			<div class='reactBtn'>
				<a onclick="fa_heart_click(event)">
				<c:if test="${post.isLiked==true }">
					<div class='heart on' id='post-${member.userid }-${post.pid }'></div>
				</c:if>
				<c:if test="${post.isLiked==false }">
					<div class='heart' id='post-${member.userid }-${post.pid }'></div>
				</c:if>
				</a>
				<div class='heartCount' id="heartCount-${post.pid }">
					<div id="post-likes-${post.pid}"
						style="display: table-cell; vertical-align: middle;">
						<span>${post.likes }</span>
					</div>
				</div>
				<div class='share_out' onclick="openLayer('layerPop',200,18)"></div>
			</div>
			<hr>
			<div class="commentView" id="commentView-${post.pid }"></div>
			<div class='commentForm'>
				<textarea row='1' cols='1' name='commentText' placeholder='Comments'
					class='comment_textarea' id="commentText-post-${post.pid }"></textarea>
				<div class='sendBtn' id="commentBtn-post-${post.pid }"></div>
			</div>
			<div class='postLayoutClear'></div>
		</li>
	</c:forEach>

</c:if>
