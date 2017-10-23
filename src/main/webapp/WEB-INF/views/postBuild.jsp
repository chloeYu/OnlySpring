<%@ page import="only.utils.WebConstants"%>
<%@ page import="only.model.Post"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="sessionChk.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:if test="${not empty plist }">
	<c:forEach var="post" items="${plist }">
		<li class='infinite_scroll'>
			<div class='postUid'>
				<c:if test="${not empty post.username }">	
					<div class="post_header">
						<a href="blog/${post.userid }"> 
						<div class="commentUserImg">
							<img style="vertical-align: middle" src="${path }/only/img_timeline/<%=member.getProfile_image()%>">
						</div>
						<span>[${post.pid }]${post.username }</span>
							<c:if test="${not empty post.taggedFriend or not empty post.place }"> is 
							</c:if>
							<c:if test="${not empty post.taggedFriend }">
								<c:forEach var="friend" items="${post.taggedFriend }" varStatus="status">
									<c:if test="${status.first == true }"> with </c:if>
									<c:if test="${status.first == false }"> & </c:if>
									<a href="blog/${friend }">${friend}</a>
								</c:forEach>
							</c:if>
							<c:if test="${not empty post.place }">
							@ <a class="searchByLocation" id="${post.lat }-${post.lng}"style="color:#0000FF;text-align:left">${post.place}</a>
							</c:if>
						</a>
						<div class="post_header_more">
							<span class="headerMoreBtn"></span>
						</div>
						<div class="postMore">
							<ul>
								<li>Delete</li>
							</ul>
						</div>
					</div>
				</c:if>
			</div>
			<hr> 
			<c:if test="${not empty post.files }">
				<c:forEach var="file" items="${post.files }" varStatus="i">
					<c:if test="${post.files.size() < 4 }">
						<div class="postImg1">
							<img class="postInner1" src='${path }/only/img_timeline/${file.url }'>
						</div>
					</c:if>
					<c:if test="${post.files.size() == 4 }">
						<div class="postImg2">
							<img class="postInner2" src='${path }/only/img_timeline/${file.url }'>
						</div>
					</c:if>
					<c:if test="${post.files.size() > 4 }">
						<div class="postImg3">
							<c:choose>
								<c:when test="${i.index == 3}">
									<img id='post${post.pid }-${i.index}' data-total="${fn:length(post.files)}" data-index = "${i.index }" class="postInner3"
										src='${path }/only/img_timeline/${file.url }'>
									<!-- <span>+More</span> -->
								</c:when>
								<c:otherwise>
									<img id='post${post.pid }-${i.index}' data-total="${fn:length(post.files)}" data-index = "${i.index }" class="postInner3"
										src='${path }/only/img_timeline/${file.url }'>
								</c:otherwise>
							</c:choose>
						</div>
					</c:if>
				</c:forEach>
			</c:if> 
			<c:if test="${not empty post.place }">
				<c:if test="${empty post.files }">
					<iframe width="100%" height="200px" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://maps.google.com/maps?q=${post.lat},${post.lng }&hl=es;z=14&amp;output=embed"></iframe><br />
				</c:if>
				
			</c:if> 
			<c:if test="${not empty post.text }">
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
			<%-- <div class="det hide" id='det${post.pid }'>
				<div class="imgDetail" style="position:fixed; z-index:100; top:0; left:0; width:100%; height:100%;">
					<div class="dimBackground" style="position:absolute; background-color:#000; opacity:0.5; width:100%; height:100%; top:0; left:0;">
					</div>
					<div class="detailDim">
						<div class="dimClose" style="float:right; cursor:pointer;">X</div>
						<div class="back_btn no_select"><</div>
       				    <div class="next_btn no_select">></div>
						<c:if test="${not empty post.files }">
							<c:forEach var="file" items="${post.files }" varStatus="i">
							<div class="postImg4">
							<img id='post${post.pid }-${i.index}' class="postInner4" src='${path }/only/img_timeline/${file.url }'>
								<img class="postInner4" src='${path }/only/img_timeline/${file.url }'>
							</div>
							</c:forEach>
						</c:if> 
					</div>
				</div>
			</div> --%>
		</li>
	</c:forEach>

</c:if>
