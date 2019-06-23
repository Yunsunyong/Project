<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String userId = (String)session.getAttribute("userId"); %>    
<!DOCTYPE html>
<html>
<head>
<title>유저헤더</title>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <link href="/math/resources/assets/css/material-kit.css" rel="stylesheet" />
    <link href="/math/resources/assets/css/reset.css" rel="stylesheet" />
    <script type="text/javascript" src="/math/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
function logout() {
	if(confirm("로그아웃 합니다.")){
		location.href="/math/logout";
		
	}else{
		return false;
	}
}
</script>
<style>
	.navbar-brand {
		font-family: 'Nanum Myeongjo', 'Noto Sans KR', 'dotum';
		font-size: 2.5rem !important;
	}
	
	.nav-item a {
		font-size: 1.15rem !important;
	}
	
	.cursor {
		cursor: pointer;
	}
</style>
</head>
<body>
<nav class="navbar navbar-color-on-scroll navbar-transparent fixed-top navbar-expand-lg" color-on-scroll="100">
    <div class="container">
      <div class="navbar-translate">
        	<a class="navbar-brand" href="/math/index.jsp">
<!--         	<i class="material-icons" >account_balance</i> -->
         	감성수학</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" aria-expanded="false" aria-label="Toggle navigation">
          <span class="sr-only">Toggle navigation</span>
          <span class="navbar-toggler-icon"></span>
          <span class="navbar-toggler-icon"></span>
          <span class="navbar-toggler-icon"></span>
        </button>
      </div>
      <div class="collapse navbar-collapse">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <a href="/math/nuslist" class="nav-link">
             <!-- <i class="material-icons">dashboard</i>--> 공지사항
            </a>
          </li>
          <li class="nav-item">
            <a href="/math/fulist" class="nav-link">
            <!--  <i class="material-icons">description</i>--> FAQ
            </a>
          </li>
          <li class="nav-item">
            <a href="/math/qmuslist?userId=<%=userId %>" class="nav-link">
             <!-- <i class="material-icons">toc</i>--> QnA
            </a>
          </li>
          <li class="nav-item" >
            <a href="/math/qPrint?userId=<%=userId %>" class="nav-link">
             <!-- <i class="material-icons">import_contacts</i>--> 문제
            </a>
          </li>
          <li class="nav-item">
            <a href="/math/culist?userId=<%=userId %>" class="nav-link"> 
            <!--  <i class="material-icons">ondemand_video</i>--> 강의
            </a>
          </li>
          <li class="nav-item">
            <a href="/math/myinfo" class="nav-link" style="font-size: 0.8rem !important;">
              <i class="material-icons">person</i> 마이페이지
            </a>
          </li>
          <%if(userId != null){ %>
          <li class="nav-item">
            <a onclick="logout();" class="nav-link cursor" style="font-size: 0.8rem !important;">
              <i class="material-icons">power_settings_new</i> 로그아웃
            </a>
          </li>
          <%}else{ %>
          <li class="nav-item">
            <a href="/math/views/common/login.jsp" class="nav-link cursor" style="font-size: 0.8rem !important;">
              <i class="material-icons">power_settings_new</i> 로그인
            </a>
          </li>
          <%} %>
        </ul>
      </div>
    </div>
  </nav>
<script src="/math/resources/assets/js/core/jquery.min.js" type="text/javascript"></script>
<script src="/math/resources/assets/js/core/popper.min.js" type="text/javascript"></script>
<script src="/math/resources/assets/js/core/bootstrap-material-design.min.js" type="text/javascript"></script>
<script src="/math/resources/assets/js/plugins/moment.min.js"></script>
<script src="/math/resources/assets/js/material-kit.js?v=2.0.5" type="text/javascript"></script>
<script src="/math/resources/assets/js/plugins/bootstrap-datetimepicker.js" type="text/javascript"></script>
<script src="/math/resources/assets/js/plugins/nouislider.min.js" type="text/javascript"></script>
<script async defer src="https://buttons.github.io/buttons.js"></script>
</body>
</html>






