<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String value = request.getParameter("images");
	String link = request.getParameter("imglink");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>감성수학</title>
<script type="text/javascript" src="/math/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
function setCookie(name, value, expiredays) {
    var date = new Date();
    date.setDate(date.getDate() + expiredays);
    document.cookie = escape(name) + "=" + escape(value) + "; expires=" + date.toUTCString();
}

function closePopup() {
    if (document.getElementById("check").value) {
        setCookie("popupYN", "N", 1);
        self.close();
    }
}
</script>
</head>
<body>
<div style="width:100%;">
	<a href="http://<%= link %>" ><img src="/math/files/popup/<%= value %>" style="width:100%; height:500px;" /></a>
	<div style="text-align:right; background:black;">
	<label>
		<input type="checkbox" id="check" onclick="closePopup();">
		<a style="cursor:pointer;">
		<font style="color:white;">
		오늘하루 보지않기
		</font>
		</a>
		</label>
	</div>
</div>
</body>
</html>

