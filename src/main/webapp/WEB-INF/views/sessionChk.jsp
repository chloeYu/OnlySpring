<%@page import="only.model.Member"%>
<%@ page import="only.utils.WebConstants"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String userid = (String) session.getAttribute(WebConstants.USER_ID);
String username = (String) session.getAttribute("username");
Member member = (Member) session.getAttribute("member");
if(userid==null || userid.equals("")){
	response.sendRedirect("joinus/signUpForm");
	return;
}
if(member==null){
	response.sendRedirect("joinus/signUpForm");
	return;
}
%>
<c:set var="member" value="<%=member %>"></c:set>
<script type="text/javascript">
	var userid = '<%=(String) session.getAttribute(WebConstants.USER_ID)%>'
	var username = '<%=(String) session.getAttribute("username") %>'
</script>