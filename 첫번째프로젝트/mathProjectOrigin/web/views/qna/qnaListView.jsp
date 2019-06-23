<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, qna.model.vo.Qna" %>    
<%
	ArrayList<Qna> qList = (ArrayList<Qna>)request.getAttribute("qList");
	int allSearchListCount = ((Integer)request.getAttribute("allSearchListCount")).intValue();
	int currentPage = ((Integer)request.getAttribute("currentPage")).intValue();
	int startPage = ((Integer)request.getAttribute("startPage")).intValue();
	int endPage = ((Integer)request.getAttribute("endPage")).intValue();
	int maxPage = ((Integer)request.getAttribute("maxPage")).intValue();	
	String searchTitle = (String)request.getAttribute("searchTitle");
	String qOption = (String)request.getAttribute("qOption");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>감성수학</title>

<!-- css -->
	<link href="/math/resources/assets/css/material-dashboard.css" rel="stylesheet" />
  <link href="/math/resources/assets/css/reset.css" rel="stylesheet" />
  <link href="/math/resources/assets/css/qna/qnaListView.css" rel="stylesheet" />
  
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <script type="text/javascript">

window.onload = function(){
	$(document).bind("contextmenu",function(){   return false;}); //우클릭방지
	$(document).bind('selectstart',function() {return false;}); //선택방지
	$(document).bind('dragstart',function(){return false;}); //드래그방지	
}
</script>
</head>
<body>
<%@ include file="../common/Adminheader.jsp" %>
<div class="content">
        <div class="container-fluid">
          <div class="row">
            <div class="col-md-12">
              <div class="card">
                <div class="card-header card-header-primary">
                  <h4 class="card-title ">QnA</h4>
                  <%if(admin != null){ %>
                  <p class="card-category"> 현재 게시글 수 : <%=allSearchListCount %></p>
                  <%}%>
                </div>
                <div class="card-body">
                  <div class="table-responsive">
                  <%if(admin != null){ %>
                    <table class="table table-striped table-hover">
                      <thead class=" text-primary">
	<tr class="table-primary">
		<th>글번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>등록일자</th>
	</tr>
	</thead>
	<% for(Qna qna : qList){ %>
	<tr>
		<td><%=qna.getQnaNo() %></td>
		<%if(qna.getQnaLevel() == 0){ %>
			<td><a href="/math/qdetail?no=<%=qna.getQnaNo()%>&page=<%=currentPage%>"><%=qna.getQnaTitle() %></a></td>
		<%} else { %>
			<td><a href="/math/qdetail?no=<%=qna.getQnaNo()%>&page=<%=currentPage%>">[re]<%=qna.getQnaTitle() %></a></td>
		<%} %>
		<td><%=qna.getQnaWriter() %></td>
		<td><%=qna.getQnaDate() %></td>
	</tr>
	<% } %>
</table>
<ul class="pagination pagination-primary" style="justify-content: center;">
	<% if(currentPage <= 1){ %>
	<li class="page-item">
		<a class="page-link"> [맨처음] </a>
		</li>
	<% }else if(searchTitle != null){ %>
	<li class="page-item">
		<a href="/math/qslist?page=1&title=<%=searchTitle%>&qOption=<%=qOption%>" class="page-link">[맨처음]</a>
		</li>
	<% }else{ %>
	<li class="page-item">
		<a href="/math/qslist?page=1" class="page-link">[맨처음]</a>
		</li>
	<%} if((currentPage - 5) <= startPage && (endPage - 5) >= 1){ %>
	<li class="page-item">
		<a href="/math/qslist?page=<%= (startPage - 5) + 4   %>&title=<%=searchTitle%>&qOption=<%=qOption%>" class="page-link">[이전]</a>
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
			<%}else if(searchTitle != null && qOption != null){ %>
			<li class="page-item">
				<a href="/math/qslist?page=<%=p%>&title=<%=searchTitle%>&qOption=<%=qOption%>" class="page-link"><%=p %></a>
				</li>
				<%}else{ %>
				<li class="page-item">
				<a href="/math/qslist?page=<%=p%>" class="page-link"><%=p %></a>
				</li>
	<% }} %>
	<% if((startPage + 5) <= maxPage && (currentPage + 5) >= startPage){ %>
	<li class="page-item">
		<a href="/math/qslist?page=<%=startPage+5%>&title=<%=searchTitle %>&qOption=<%=qOption%>" class="page-link">[다음]</a>
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
		<a href="/math/qslist?page=<%=maxPage%>&title=<%=searchTitle%>&qOption=<%=qOption%>" class="page-link">[마지막]</a>
		</li>
	<% }else{ %>
	<li class="page-item">
		<a href="/math/qslist?page=<%=maxPage%>" class="page-link">[마지막]</a>
		</li>
	<% } %>
</ul>
<div align="center" id="d1">
<form action="/math/qslist" method="post" class="form-inline ml-auto">
<div class="container">
<select name="qOption" class="form-control">
	<option value="">선택하세요</option>
	<option name="qTitle" value="qTitle">제목</option>
	<option name="qTContent" value="qTContent">제목+내용</option>
	<option name="qnaUser" value="qUserId">작성자</option>
</select>
&nbsp;
	<input type="text" name="title" class="form-control" placeholder="Search">
	<button type="submit" class="btn btn-white btn-raised btn-fab btn-round">
                    <i class="material-icons">search</i>
                  </button>
</div>
</form>
<%}else { %>
<p class="log-in">로그인 후 이용할 수 있습니다.</p>
<%} %>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
<%@ include file="../common/footer.jsp" %>
<script src="/math/resources/assets/js/core/jquery.min.js"></script>
  <script src="/math/resources/assets/js/core/popper.min.js"></script>
  <script src="/math/resources/assets/js/core/bootstrap-material-design.min.js"></script>
  <script src="/math/resources/assets/js/plugins/perfect-scrollbar.jquery.min.js"></script>
  <!-- Include a polyfill for ES6 Promises (optional) for IE11, UC Browser and Android browser support SweetAlert -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/core-js/2.4.1/core.js"></script>
  <!-- Library for adding dinamically elements -->
  <script src="/math/resources/assets/js/plugins/arrive.min.js"></script>
  <!-- Chartist JS -->
  <script src="/math/resources/assets/js/plugins/chartist.min.js"></script>
  <!--  Notifications Plugin    -->
  <script src="/math/resources/assets/js/plugins/bootstrap-notify.js"></script>
  <!-- Control Center for Material Dashboard: parallax effects, scripts for the example pages etc -->
  <script src="/math/resources/assets/js/material-dashboard.js?v=2.1.1" type="text/javascript"></script>
  <!-- Material Dashboard DEMO methods, don't include it in your project! -->
  <script src="/math/resources/assets/demo/demo.js"></script>
</body>
</html>