<%@ page import="only.utils.WebConstants"%>
<%@ page import="only.model.Post"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="sessionChk.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty clist }">
	<hr>
	<c:forEach var="comment" items="${clist }">
	<div class="commentSpace">
		<span class="commentViewMid">${comment.userid }</span>
		<span class="commentViewCre">${comment.created }</span>
		<br>
		<span class="commentViewText">${comment.text }</span>
	</div>
	<br>
	</c:forEach>
</c:if>
