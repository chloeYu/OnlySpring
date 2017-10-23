<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath }"></c:set>
<c:if test="${not empty alerts }">
	<ul>
	<c:forEach var="alert" items="${alerts }">
		<c:if test="${alert.type=='0' or alert.type==0 }">
			<c:if test="${alert.status eq 'n' }">
				<li class="postPopup" data-aid ='${alert.aid }' data-pid="${alert.url }">
			</c:if>
			<c:if test="${alert.status eq 'y' }">
				<li class="postPopup read" data-aid ='${alert.aid }' data-pid="${alert.url }"> 
			</c:if>
			<div class="post_header">
					<div style="display:inline-block;"><img style="vertical-align: middle;" src="${path }/img_timeline/${alert.profile_image }"></div>
					<div style="display:inline-block; vertical-align: middle;"><span>${alert.username}님이 새 사진을 게시하였습니다 <br>${alert.created }</span></div>
				</div>		
			</li>
		</c:if>
	</c:forEach>
	</ul>
</c:if>