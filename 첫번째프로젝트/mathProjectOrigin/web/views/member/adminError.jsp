<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<%
    String message = (String)request.getAttribute("message");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 관련 에러!</title>
<script type="text/javascript">
window.onload = function () {
	 
	alert("<%=message %>");
	location.href="/math/admin.jsp";
	}
</script>
</head>
<body>
<%-- <h1>회원관련 에러! </h1>
<% if(exception != null){ %>
JSP 예외발생 : <%= exception.getMessage() %><br>
예외클래스명 확인 : <%=exception.getClass().getName() %>
<%}else{ %>
서블릿이 전달한 메세지 : <%=message %><br>
<%} %>
<br>
<a href="/math/index.jsp">시작페이지로 이동</a><br> --%>
</body>
</html>