<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, qna.model.vo.Qna" %>    
<%
	ArrayList<Qna> qList = (ArrayList<Qna>)request.getAttribute("qList");
	int myUserListCount = ((Integer)request.getAttribute("myUserListCount")).intValue();
	int currentPage = ((Integer)request.getAttribute("currentPage")).intValue();
	int startPage = ((Integer)request.getAttribute("startPage")).intValue();
	int endPage = ((Integer)request.getAttribute("endPage")).intValue();
	int maxPage = ((Integer)request.getAttribute("maxPage")).intValue();	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>감성수학</title>

<!-- css -->
	<link href="/math/resources/assets/css/material-kit.css" rel="stylesheet" />
	<link href="/math/resources/assets/css/reset.css" rel="stylesheet" />
	<link href="/math/resources/assets/css/qna/qnaUserMyListView.css" rel="stylesheet" />
<script type="text/javascript">
window.onload = function(){
	$(document).bind("contextmenu",function(){   return false;}); //우클릭방지
	$(document).bind('selectstart',function() {return false;}); //선택방지
	$(document).bind('dragstart',function(){return false;}); //드래그방지	
}
</script>
</head>
<style>

</style>
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
            <h1>로그인 해주세요</h1>
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
      <table class="table table-striped table-hover">
	<tr class="table-success">
		<th>글번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>등록일자</th>
	</tr>
	<% for(Qna qna : qList){ %>
	<tr class="tablebody">
		<td><%=qna.getQnaNo() %></td>
		<%if(qna.getQnaLevel() == 0){ %>
			<td><a href="/math/qudetail?no=<%=qna.getQnaNo()%>"><%=qna.getQnaTitle() %></a></td>
		<%} else { %>
			<td><a href="/math/qudetail?no=<%=qna.getQnaNo()%>">[re]<%=qna.getQnaTitle() %></a></td>
		<%} %>
		<td><%=qna.getQnaWriter() %></td>
		<td><%=qna.getQnaDate() %></td>
	</tr>
	<% } %>
</table>
<nav aria-label="Page navigation example">
<ul class="pagination pagination-info" style="justify-content: center;">	
	<% if(currentPage <= 1){ %>
	<li class="page-item">
		<a class="page-link"> [맨처음] </a>
		</li>
	<% }else{ %>
	<li class="page-item">
		<a href="/math/qmuslist?page=1&userId=<%=userId%>" class="page-link">[맨처음]</a>
		</li>
	<%} if((currentPage - 5) <= startPage && (endPage - 5) >= 1){ %>
	<li class="page-item">
		<a href="/math/qmuslist?page=<%= (startPage - 5) + 4   %>&userId=<%=userId%>" class="page-link">[이전]</a>
		</li>
	<% }else{%>
	<li class="page-item">
		<a class="page-link"> [이전] </a>
		</li>
	<%} for(int p = startPage; p <= endPage; p++){ 
			if(p == currentPage){%>
			<li class="page-item">
				<a class="page-link"> <font>[<%=p %>]</font> </a>
				</li>
				<%}else{ %>
				<li class="page-item">
				<a href="/math/qmuslist?page=<%=p%>&userId=<%=userId%>" class="page-link"><%=p %></a>
				</li>
	<% }} %>
	<% if((startPage + 5) <= maxPage && (currentPage + 5) >= startPage){ %>
	<li class="page-item">
		<a href="/math/qmuslist?page=<%=startPage+5%>&userId=<%=userId%>" class="page-link">[다음]</a>
		</li>
	<%}else{ %>
	<li class="page-item">
		<a class="page-link"> [다음] </a>
		</li>
	<%} %>
	<% if(currentPage >= maxPage){ %>
	<li class="page-item">
		<a class="page-link"> [마지막] </a>
		</li>
	<% }else{ %>
	<li class="page-item">
		<a href="/math/qmuslist?page=<%=maxPage%>&userId=<%=userId%>" class="page-link">[마지막]</a>
		</li>
	<%} %>
</ul>
</nav>
<div id="d1">
<button onclick="questionWrite();" class="btn btn-default btn-sm">질문하기</button>
</div>
<script type="text/javascript">
function questionWrite(){
	location.href="/math/views/qna/questionWriteView.jsp";
}
</script>
<%} %>
</div>
</div>
</div>
<%@ include file="../common/ufooter.jsp" %>
</body>
</html>