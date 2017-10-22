<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="padding: 8px 0 8px 8px;">
	<c:if test="${not empty contacts }">
		<div
			style="color: #4c4c4c; font-size: 16px; font-weight: bold; height: 16px; overflow: hidden; text-overflow: ellipsis;">
			Contacts</div>
		<table style="width: 150px;">
			<c:forEach var="contact" items="${contacts }">
				<tr>
					<td width="90%" style="padding-top: 10px;"><a
						id="user_profile_url" href="${path }/only/blog/${contact.userid}">
							<span class="usreProfileImg_span"><img
								style="vertical-align: middle; width: 34px; height: 34px;"
								src="${path }/only/img_timeline/${contact.profile_image}"></span>
							<span class="userProfileName">${contact.username }</span>
					</a></td>
					<td width="10%" style="padding-top: 10px;"><div
							style="width: 20%;">
							<span
								style="background: rgb(66, 183, 42); border-radius: 50%; display: inline-block; height: 6px; margin-left: 4px; width: 6px;"></span>
						</div></td>
			</c:forEach>
		</table>
	</c:if>
</div>