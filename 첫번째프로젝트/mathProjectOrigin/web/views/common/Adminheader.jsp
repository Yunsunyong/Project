<%@page import="admin.model.vo.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String admin = (String)session.getAttribute("admin");
%>
<!DOCTYPE html>
<html>
<head>
<title>adminheader</title>
 <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  
  <link href="/math/resources/assets/css/material-dashboard.css" rel="stylesheet" />
  <link href="/math/resources/assets/css/reset.css" rel="stylesheet" />
  
  <script type="text/javascript" src="/math/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
function logout() {
	
	if(confirm("로그아웃 하시겠습니다.")){
			
		location.href="/math/alogout";
		
	}else{
		return false;
	}
}
</script>
<style>
	.logo-normal {
		font-family: 'Nanum Myeongjo', 'Noto Sans KR', 'dotum';
		font-size: 1.75rem !important;
	}
	
	.log-in-out a {
		cursor: pointer;
		text-align: center;
		background: #eee;
	}
	
</style>

</head>
<body>
<div class="wrapper ">
    <div class="sidebar" data-color="purple" data-background-color="white">
      <!--
      Tip 1: You can change the color of the sidebar using: data-color="purple | azure | green | orange | danger"
      Tip 2: you can also add an image using data-image tag
  -->
      <div class="logo">
        <a href="/math/nslist" class="simple-text logo-normal">
          감성수학
        </a>
      </div>
      <div class="sidebar-wrapper">
        <ul class="nav">
         
         <div class="log-in-out">
          	<%if(admin!=null){ %>
            		<ul class="navbar-nav">
              		<li class="nav-item">
                		<a class="nav-link" onclick="logout();">
             				logout
                		</a>
             			 </li>
           			 </ul>
            <%}else{ %>
            		<ul class="navbar-nav">
             			<li class="nav-item">
               		 <a class="nav-link" href="/math/admin.jsp">
                 	 login
              		 </a>
              		</li>
            		</ul>
            <%} %>
					</div>

          <li class="nav-item">
            <a class="nav-link" href="/math/nslist">
              <i class="material-icons">dashboard</i>
              <p>공지사항</p>
            </a>
          </li>
           <li class="nav-item">
            <a class="nav-link" href="/math/fslist">
              <i class="material-icons">description</i>
              <p>FAQ</p>
            </a>
          </li>
           <li class="nav-item">
            <a class="nav-link" href="/math/qslist">
              <i class="material-icons">toc</i>
              <p>QnA</p>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/math/plist">
              <i class="material-icons">style</i>
              <p>팝업관리</p>
            </a>
          </li>
           <li class="nav-item">
            <a class="nav-link" href="/math/views/question/adminQuestionPrint.jsp">
              <i class="material-icons">import_contacts</i>
              <p>문제관리</p>
            </a>
          </li>
           <li class="nav-item">
            <a class="nav-link" href="/math/clist">
              <i class="material-icons">ondemand_video</i>
              <p>강의관리</p>
            </a>
          </li>
           <li class="nav-item">
            <a class="nav-link" href="/math/mmanager">
              <i class="material-icons">people</i>
              <p>회원관리</p>
            </a>
            </li>
          <!-- your sidebar here -->
        </ul>
      </div>
    </div>
    <div class="main-panel">
      <!-- Navbar -->
      <nav class="navbar navbar-expand-lg navbar-transparent navbar-absolute fixed-top ">
        <div class="container-fluid">
          <div class="navbar-wrapper">
						<span class="header-lft"><a class="navbar-brand" href="#">Admin</a></span>
          </div>
          <button class="navbar-toggler" type="button" data-toggle="collapse" aria-controls="navigation-index" aria-expanded="false" aria-label="Toggle navigation">
            <span class="sr-only">Toggle navigation</span>
            <span class="navbar-toggler-icon icon-bar"></span>
            <span class="navbar-toggler-icon icon-bar"></span>
            <span class="navbar-toggler-icon icon-bar"></span>
          </button>
        </div>
      </nav>
</body>
</html>