<%@ page import="only.utils.WebConstants"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String userid = (String) session.getAttribute(WebConstants.USER_ID);
if(userid==null || userid.equals("")){
	response.sendRedirect("joinus/signUpForm");
	return;
}
%>
<script type="text/javascript">
var userid = '<%=(String) session.getAttribute(WebConstants.USER_ID)%>';
</script>