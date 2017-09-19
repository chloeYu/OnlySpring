<%@ page import="only.utils.WebConstants"%>
<%
String userid = (String) session.getAttribute(WebConstants.USER_ID);
if(userid==null || userid.equals("")){
	response.sendRedirect("joinus/signUpForm");
	return;
}
%>