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
						<a href="blog/${post.userid }"> 
						<span class="postProfileImg_span">
							<img style="vertical-align: middle" src="img_timeline/<%=member.getProfile_image()%>"> 
						</span>
						<span class="postProfileName">[${post.pid }]${post.username }</span>
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
				<div class='heart' id='heart-${post.userid }'></div>
				<div class='share_out' onclick="openLayer('layerPop',200,18)"></div>
			</div>
			<div class="commentView" id="commentView-${post.pid }">
			<c:if test="${not empty post.comments }">
				<hr>
				<c:forEach var="comment" items="${post.comments }">
					<div class="commentSpace">
						<span class="commentViewMid">${comment.userid }</span>
						<span class="commentViewText">${comment.text }</span>
						<br>
						<a href="">like</a><a href="">reply</a><span class="commentViewCre">${comment.created }</span>
					</div>
				</c:forEach>
			</c:if>
			</div>
			<div class='commentForm'>
				<textarea rows='1' cols='1' name='commentText' placeholder='Comments' class='comment_textarea' id="commentText-${post.pid }"></textarea>
				<div class='commentBtn btn btn-success' id="commentBtn-${post.pid }" onclick="writePost(event)">Enter</div>
			</div>
		</li>
	</c:forEach>

</c:if>
