<%@page import="only.utils.WebConstants"%>

<%
String id = (String) session.getAttribute(WebConstants.USER_ID);
if(id==null || id.equals("")){
	response.sendRedirect("joinus/signUpForm");
	return;
}else{
	String userid = (String) session.getAttribute("sessionId");
}
%>