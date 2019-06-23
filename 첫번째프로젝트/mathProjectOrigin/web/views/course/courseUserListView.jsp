<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, course.model.vo.Course, course.model.vo.Semester, course.model.vo.Book, course.model.vo.Member" %>    
<%
    	ArrayList<Course> cList = (ArrayList<Course>)request.getAttribute("cList");
		ArrayList<Semester> semList = (ArrayList<Semester>)request.getAttribute("semList");
		ArrayList<Book> bList = (ArrayList<Book>)request.getAttribute("bList");
		Member member = (Member)request.getAttribute("member");
    	int allListCount = ((Integer) request.getAttribute("allListCount")).intValue();
    	int currentPage = ((Integer) request.getAttribute("currentPage")).intValue();
    	int startPage = ((Integer) request.getAttribute("startPage")).intValue();
    	int endPage = ((Integer) request.getAttribute("endPage")).intValue();
    	int maxPage = ((Integer) request.getAttribute("maxPage")).intValue();
    	String sOption = (String)request.getAttribute("sOption");
    	String bOption = (String)request.getAttribute("bOption");
%>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>감성수학</title>

<!-- css -->
	<link href="/math/resources/assets/css/material-kit.css" rel="stylesheet" />
	<link href="/math/resources/assets/css/reset.css" rel="stylesheet" />
	<link href="/math/resources/assets/css/course/courseUserListView.css" rel="stylesheet" />
	<script type="text/javascript" src="/math/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	function courseWrite(){
		location.href="/math/cwview";
	}
	
	$(function(){
		$.ajax({
			url : "/math/semlist",
			type : "post",
			dataType : "json",
			success : function(data){
				var jsonStr = JSON.stringify(data);
				var json = JSON.parse(jsonStr);
				
				var option = "<option value=''>학기선택</option>";
				for(var s in json.list){
					option += "<option value='"+decodeURIComponent(json.list[s].semester).replace(/\+/gi, " ") +"'>"+decodeURIComponent(json.list[s].semester).replace(/\+/gi, " ")+"</option>"; 
				}
				$("#semlist").html(option);
			},
			error: function(jqXHR, textStatus, errorThrown){
				console.log("error: " + jqXHR + ", " + textStatus + ", " + errorThrown);
			}
			
		})
		
		$(document).bind("contextmenu",function(){   return false;}); //우클릭방지
   		$(document).bind('selectstart',function() {return false;}); //선택방지
   		$(document).bind('dragstart',function(){return false;}); //드래그방지
	});
	
	function  bookListView(){
		var sem = $("#semlist option:selected").val();
		$.ajax({
			url : "/math/booklist",
			data : { semester : sem },
			type : "post",
			dataType : "json",
			success : function(data){
				var jsonStr = JSON.stringify(data);
				var json = JSON.parse(jsonStr);
				
				var option = "<option value=''>교재선택</option>";
				for(var b in json.list){
					option += "<option value='"+decodeURIComponent(json.list[b].book).replace(/\+/gi, " ") +"'>"+decodeURIComponent(json.list[b].book).replace(/\+/gi, " ")+"</option>"; 
				}
				$("#booklist").html(option);
			},
			error : function(jqXHR, textStatus, errorThrown){
				console.log("error: " + jqXHR + ", " + textStatus + ", " + errorThrown);
			}
		})
		
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
          	<h1>강의</h1>
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
<% if(member.getMemberLevel().equals("1")){ %>
<table class="table table-striped table-hover">
	<tr class="table-success">
		<th>글번호</th>
		<th>제목</th>
		<th>등록일자</th>
	</tr>
	<% for(Course course : cList){ %>
	<tr class="tablebody">
		<td><%=course.getCourseNo() %></td>
		<td><a href="/math/cudetail?no=<%=course.getCourseNo()%>&page=<%=currentPage%>"><%=course.getCourseTitle() %></a></td>
		<td><%=course.getCourseDate() %></td>
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
		<a href="/math/culist?page=1&sOption=<%=sOption%>&bOption=<%=bOption%>&userId=<%=userId%>" class="page-link">[맨처음]</a>
		</li>
	<%} if((currentPage - 5) <= startPage && (endPage - 5) >= 1){ %>
	<li class="page-item">
		<a href="/math/culist?page=<%= (startPage - 5) + 4   %>&sOption=<%=sOption%>&bOption=<%=bOption%>&userId=<%=userId%>" class="page-link">[이전]</a>
		</li>
	<% }else{%>
	<li class="page-item">
		<a class="page-link"> [이전] </a>
		</li>
	<%} for(int p = startPage; p <= endPage; p++){ 
			if(p == currentPage){%>
			<li class="page-item">
				<a class="page-link"><font>[<%=p %>]</font></a>
				</li>
				<%}else{ %>
				<li class="page-item">
				<a href="/math/culist?page=<%=p%>&sOption=<%=sOption%>&bOption=<%=bOption%>&userId=<%=userId%>" class="page-link"><%=p %></a>
				</li>
	<% }} %>
	<% if((startPage + 5) <= maxPage && (currentPage + 5) >= startPage){ %>
	<li class="page-item">
		<a href="/math/culist?page=<%=startPage+5%>&sOption=<%=sOption%>&bOption=<%=bOption%>&userId=<%=userId%>" class="page-link">[다음]</a>
		</li>
	<%}else{ %>
	<li class="page-item">
		<a class="page-link"> [다음] </a>
		</li>
	<%} %>
	<% if(currentPage >= maxPage){ %>
	<li class="page-item">
		<a class="page-link">  [마지막] </a>
		</li>
	<% }else{ %>
	<li class="page-item">
		<a href="/math/culist?page=<%=maxPage%>&sOption=<%=sOption%>&bOption=<%=bOption%>&userId=<%=userId%>" class="page-link">[마지막]</a>
		</li>
	<%} %>
</ul>
</nav>
<div align="center">
<form action="/math/culist?userId=<%=userId%>" method="post" class="form-inline ml-auto">
<div class="container">
<select name="sOption" class="form-control" id="semlist" onchange="bookListView();">
</select>
<select name="bOption" class="form-control" id="booklist">
</select>
<button type="submit" class="btn btn-black btn-raised btn-fab btn-round">
                    <i class="material-icons">search</i>
                  </button>
</div>
</form>
</div>
<%} if(member.getMemberLevel().equals("0")){ %>
<script type="text/javascript">
window.onload = function(){
	alert("접근하실수 없습니다.");
	history.go(-1);
}
</script>
<%}} %>
</div>
</div>
</div>
<%@ include file="../common/ufooter.jsp" %>
</body>
</html>