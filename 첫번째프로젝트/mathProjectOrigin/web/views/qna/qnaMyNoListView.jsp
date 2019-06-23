<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String message = (String)request.getAttribute("message");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>감성수학</title>

<!-- css -->
	<link href="/math/resources/assets/css/material-kit.css" rel="stylesheet" />
	<link href="/math/resources/assets/css/reset.css" rel="stylesheet" />
	<link href="/math/resources/assets/css/qna/qnaMyNoListView.css" rel="stylesheet" />

<script type="text/javascript">
function questionWrite() {
	location.href="/math/views/qna/questionWriteView.jsp";
}
window.onload = function(){
	$(document).bind("contextmenu",function(){   return false;}); //우클릭방지
	$(document).bind('selectstart',function() {return false;}); //선택방지
	$(document).bind('dragstart',function(){return false;}); //드래그방지	
}
</script>
</head>
<body>
<%@ include file="../common/header.jsp" %>
<div class="page-header header-filter" data-parallax="true" style="background-image: url('/math/resources/images/math-3986758_1920.jpg')">
    <div class="container">
      <div class="row">
        <div class="col-md-8 ml-auto mr-auto">
          <div class="brand text-center">
           <%if(userId != null){ %>
          <h1>QnA</h1>
            <h3 class="title text-center">목록보기</h3>
           <%}else{ %>
						 <p class="log-in">로그인 후 이용할 수 있습니다.</p>
            <%} %>
          </div>
        </div>
      </div>
    </div>
  </div>
   <div class="main main-raised">
    <div class="container">
      <div class="section text-center">
       <%if(userId != null){ %>
<h3 align="center" class="no-question">등록한 질문이 없습니다.</h3>
<div id="d1">
<button onclick="questionWrite();" class="btn btn-default btn-sm">질문하기</button>
<%}%>
</div>
</div>
</div>
</div>
<%@ include file="../common/ufooter.jsp" %>
</body>
</html>