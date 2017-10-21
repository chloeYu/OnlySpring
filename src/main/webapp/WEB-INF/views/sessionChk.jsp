<%@page import="only.model.Member"%>
<%@ page import="only.utils.WebConstants"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
Member member = (Member) session.getAttribute("member");
String userid = member.getUserid();
/* if(userid==null || userid.equals("")){
	response.sendRedirect("/only/joinus/signUpForm");
	return;
}
if(member==null){
	response.sendRedirect("/only/joinus/signUpForm");
	return;
} */
%>
<c:set var="member" value="<%=member %>"></c:set>
<script type="text/javascript">
	var userid = '<%=(String) session.getAttribute(WebConstants.USER_ID)%>';
	var member = '<%=(Member) session.getAttribute("member")%>';
</script>