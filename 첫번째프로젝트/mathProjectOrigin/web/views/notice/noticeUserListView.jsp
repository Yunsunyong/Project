<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, notice.model.vo.Notice" %>    
<%
	ArrayList<Notice> nsList = (ArrayList<Notice>)request.getAttribute("nslist");
	int allSearchListCount = ((Integer)request.getAttribute("allSearchListCount")).intValue();
	int currentPage = ((Integer)request.getAttribute("currentPage")).intValue();
	int startPage = ((Integer)request.getAttribute("startPage")).intValue();
	int endPage = ((Integer)request.getAttribute("endPage")).intValue();
	int maxPage = ((Integer)request.getAttribute("maxPage")).intValue();
	String searchTitle = (String)request.getAttribute("searchTitle");
	String nOption = (String)request.getAttribute("noption");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>감성수학</title>
<!-- css -->
	<link href="/math/resources/assets/css/material-kit.css" rel="stylesheet" />
	<link href="/math/resources/assets/css/reset.css" rel="stylesheet" />
	<link href="/math/resources/assets/css/notice/noticeUserListView.css" rel="stylesheet" />

<script type="text/javascript">

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
          	<h1>공지사항</h1>
            <h3 class="title text-center">목록보기</h3>         
          </div>
        </div>
      </div>
    </div>
  </div>
   <div class="main main-raised">
    <div class="container">
      <div class="section text-center">   
<table class="table table-striped table-hover">
	<tr class="table-success">
		<th>글번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>등록일자</th>
		<th>조회수</th>
	</tr>
	<% for(int n = 0; n < nsList.size(); n++){ %>
	<tr class="tablebody">
		<td><%=nsList.get(n).getNoticeNo() %></td>
		<td><a href="/math/nudetail?no=<%=nsList.get(n).getNoticeNo()%>&page=<%=currentPage%>"><%=nsList.get(n).getNoticeTitle() %></a></td>
		<td><%=nsList.get(n).getWriterName() %></td>
		<td><%=nsList.get(n).getNoticeDate() %></td>
		<td><%=nsList.get(n).getNoticeCount() %></td>
	</tr>
	<% } %>
</table>
<nav aria-label="Page navigation example">
<ul class="pagination pagination-info" style="justify-content: center;">	
	<% if(currentPage <= 1){ %>
	<li class="page-item">
		<a class="page-link"> [맨처음] </a>
		</li>
	<% }else if(searchTitle != null){ %>
	<li class="page-item">
		<a href="/math/nuslist?page=1&title=<%=searchTitle%>&noption=<%=nOption%>" class="page-link">[맨처음]</a>
		</li>
	<% }else{ %>
	<li class="page-item">
		<a href="/math/nuslist?page=1" class="page-link">[맨처음]</a>
		</li>
	<%} if((currentPage - 5) <= startPage && (endPage - 5) >= 1){ %>
	<li class="page-item">
		<a href="/math/nuslist?page=<%= (startPage - 5) + 4   %>&title=<%=searchTitle%>&noption=<%=nOption%>" class="page-link">[이전]</a>
		</li>
	<% }else{%>
	<li class="page-item">
		<a class="page-link"> [이전] </a>
		</li>
	<%} for(int p = startPage; p <= endPage; p++){ 
			if(p == currentPage){%>
			<li class="page-item">
				<a class="page-link"> <font><%=p%></font> </a>
				</li>
			<%}else if(searchTitle != null && nOption != null){ %>
			<li class="page-item">
				<a href="/math/nuslist?page=<%=p%>&title=<%=searchTitle%>&noption=<%=nOption%>" class="page-link"><%=p%></a>
				</li>
				<%}else{ %>
				<li class="page-item">
				<a href="/math/nuslist?page=<%=p%>" class="page-link"><%=p%></a>
				</li>
	<% }} %>
	<% if((startPage + 5) <= maxPage && (currentPage + 5) >= startPage){ %>
	<li class="page-item">
		<a href="/math/nuslist?page=<%=startPage+5%>&title=<%=searchTitle %>&noption=<%=nOption%>" class="page-link">[다음]</a>
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
	<% }else if(searchTitle != null){ %>
	<li class="page-item">
		<a href="/math/nuslist?page=<%=maxPage%>&title=<%=searchTitle%>&noption=<%=nOption%>" class="page-link">[마지막]</a>
		</li>
	<% }else{ %>
	<li class="page-item">
		<a href="/math/nuslist?page=<%=maxPage%>" class="page-link">[마지막]</a>
		</li>
	<%} %>
	</ul>
	</nav>
<div align="center">
<form action="/math/nuslist?page=1" method="post" class="form-inline ml-auto">
<div class="container">
	<select name="noption" class="form-control">
		<option value="" >선택</option>
		<option name="ntitle" value="ntitle" >제목</option>
		<option name="ntContent" value="ntContent">제목+내용</option>
	</select>
	&nbsp;
                    <input type="text" name="title" class="form-control" placeholder="Search">
	<button type="submit" class="btn btn-black btn-raised btn-fab btn-round">
                    <i class="material-icons">search</i>
                  </button>
                  </div>
</form>
</div>
</div>
</div>
</div>
<%@ include file="../common/ufooter.jsp" %>
</body>
</html>