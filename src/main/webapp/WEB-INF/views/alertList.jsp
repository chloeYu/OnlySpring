<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty alerts }">
	<ul>
	<c:forEach var="alert" items="${alerts }">
		<c:if test="${alert.type=='0' or alert.type==0 }">
			<c:if test="${alert.status eq 'n' }">
				<li class="postPopup" data-aid ='${alert.aid }' data-pid="${alert.url }" style="padding: 5px 0px;">${alert.uid1}님이 새 사진을 게시하였습니다</li>
			</c:if>
			<c:if test="${alert.status eq 'y' }">
				<li class="postPopup" data-aid ='${alert.aid }' data-pid="${alert.url }" style="padding: 5px 0px; background-color: #c9d0db; cursor: pointer;">${alert.uid1}님이 새 사진을 게시하였습니다</li>
			</c:if>
		</c:if>
	</c:forEach>
	</ul>
</c:if>