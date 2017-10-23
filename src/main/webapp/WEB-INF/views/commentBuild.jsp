<%@ page import="only.utils.WebConstants"%>
<%@ page import="only.model.Post"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="sessionChk.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty clist }">
	<c:forEach var="comment" items="${clist}">
		<div class="commentSpace" id="commentView-comment-${comment.cid }">
			<table width="90%" style="margin-left: ${comment.re_level * 50}px">
				<tr>
					<td class="commentTh">
						<a href="blog/${comment.userid }">
							<div class="commentUserImg">
								<img src="img_timeline/<%=member.getProfile_image()%>">
							</div>
						</a>
					</td>
					<td>
						<a href="blog/${comment.userid }">
							<span class="commentViewMid">${comment.userid }</span>
						</a>
						<span class="commentViewCre">${comment.created }</span>
						<br>
						<span class="commentViewText">${comment.text }</span>
						<div class="commentReact">
							<a onclick="fa_heart_click(event)">
								<c:if test="${comment.isLiked==true }">
									<div class='heart on comment' id="comment-${member.userid	 }-${comment.cid }"></div>
								</c:if>
								<c:if test="${comment.isLiked==false }">
									<div class='heart comment' id="comment-${member.userid	 }-${comment.cid }"></div>
								</c:if>
							</a>
							<div class='heartCount comment' id="heartCount-${comment.cid }">
								<div id="comment-likes-${comment.cid}"
									style="display: table-cell; vertical-align: middle;">
									<span>${comment.likes }</span>
								</div>
							</div>
							<div onclick="fa_reply_click(event)" class="reply"
								id="reply-${comment.cid }"></div>
						</div></td>
				</tr>
				<tr>
					<td></td>
					<td><div class='replyForm'
							id="comment-comment-${comment.cid }" style="display: none">
							<div class="comment_text">
								<input type="text" id="commentText-comment-${comment.cid }"
									placeholder=" Reply...">
								<div class="sendBtn" id="commentBtn-comment-${comment.cid }"></div>
							</div>
						</div></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<c:if test="${comment.commentCount == 1}">
							<div onclick="viewComment(event)"
								id="commentView-${comment.cid }-1-1"
								style="cursor: pointer; curtext-align: left; font-size: 0.8em; color: grey; padding-bottom: 0.8em;">${comment.commentCount }
								Reply</div>
						</c:if> <c:if test="${comment.commentCount > 1}">
							<div onclick="viewComment(event)"
								id="commentView-${comment.cid }-1-1"
								style="cursor: pointer; text-align: left; font-size: 0.8em; color: grey; padding-bottom: 0.8em;">${comment.commentCount }
								Replies</div>
						</c:if></td>
				</tr>
			</table>
		</div>
	</c:forEach>
</c:if>
<c:if test="${ref_c_num > COMMENTS_PER_PAGE * pageNum}">
	<div onclick="viewComment(event)" id="commentView-${ref_id }-${ref_type }-${pageNum+1}" style="cursor: pointer; text-align: center; font-size: 0.8em;">
		<c:if test="${ref_c_num - COMMENTS_PER_PAGE * pageNum == 1}">
			${ref_c_num - COMMENTS_PER_PAGE * pageNum } More Comment..
		</c:if>
		<c:if test="${ref_c_num - COMMENTS_PER_PAGE * pageNum > 1}">
			${ref_c_num - COMMENTS_PER_PAGE * pageNum } More Comments..
		</c:if>
		
	</div>
</c:if>






