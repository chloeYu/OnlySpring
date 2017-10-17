<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty photoList }">
	<c:forEach items="${photoList }" var="photo">
		<li class="photoDisplayElement">
			<div class="search_profile">
				<a style="border: none; margin: 2px 3px 3px 2px; padding: 0; display: inline-block;
    outline: none; position: relative;
    vertical-align: bottom;"><img class="img"
					src='${path}/only/img_timeline/${photo }'
					style="dlsplay: block; width: 195; height: 195;"></a>
			</div>
		</li>
	</c:forEach>
</c:if>