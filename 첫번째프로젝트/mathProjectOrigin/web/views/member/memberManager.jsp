<%@page import="member.model.vo.Member"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% ArrayList<Member> list = (ArrayList<Member>)request.getAttribute("list"); 
	int currentPage = ((Integer)request.getAttribute("currentPage")).intValue();
	int startPage = ((Integer)request.getAttribute("startPage")).intValue();
	int endPage = ((Integer)request.getAttribute("endPage")).intValue();
	int maxPage = ((Integer)request.getAttribute("maxPage")).intValue();
	int limit = ((Integer)request.getAttribute("limit")).intValue();
	int allSearchListCount = ((Integer)request.getAttribute("allSearchListCount")).intValue();
	String searchTitle = (String)request.getAttribute("searchTitle");
	String fOption = (String)request.getAttribute("fOption");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>감성수학</title>

<!-- css -->
	<link href="/math/resources/assets/css/material-dashboard.css" rel="stylesheet" />
	<link href="/math/resources/assets/css/reset.css" rel="stylesheet" />
	<link href="/math/resources/assets/css/member/memberManager.css" rel="stylesheet" />
	
<script type="text/javascript" src="/math/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	function levelChange(i) {
		if(confirm("적용하시겠습니까?")){
			var name = $("#" + i).html();
			var level= $("#level"+ i).val();
			$.ajax({
				url: "/math/lvchange",
				type: "post",
				data: {level2: level,
					userid2: name},
				success: function(data) {
						if(data == "ok"){
							alert("변경되였습니다.");
						}else{
							alert("변경실패!");
						}
				},
				error: function( jqXHR, textStatus, errorThrown) {
					console.log("error : "+  jqXHR +", "+textStatus+", "+errorThrown);
				}
			});
	}else{
			return false;	
		}
		}
	function memberDetail(i) {
	var name = $("#" + i).html();
	location.href="/math/mdetail?userid="+name+"&page="+<%=currentPage%>;	
	}
	
	$(function() {
		$.ajax({
			url: "/math/cmember",
			type: "post",
			success: function(data) {
				$("#cmember").html("현재 인원수 :"+data);
			},
			error: function( jqXHR, textStatus, errorThrown) {
				console.log("error : "+  jqXHR +", "+textStatus+", "+errorThrown);
			}
		});

		window.onload = function(){
			$(document).bind("contextmenu",function(){   return false;}); //우클릭방지
			$(document).bind('selectstart',function() {return false;}); //선택방지
			$(document).bind('dragstart',function(){return false;}); //드래그방지	
		}
	});
</script>
</head>
<body>
<%@ include file="../common/Adminheader.jsp" %>

<div class="content">
        <div class="container-fluid">
          <div class="card">
            <div class="card-header card-header-primary">
              <h4 class="card-title">회원관리</h4>
               <%if(admin != null){ %>
              <p class="card-category" id="cmember"></p>
               <%} %>
            </div>
            <div class="card-body">
              <div id="typography">
                <div class="card-title">
 <%if(admin != null){ %>
<table id="t1" class="table table-striped table-hover">
<tr class="table-primary"><td>회원 아이디 </td><td>회원 이름</td><td>가입일</td><td>회원수정일</td><td>권한</td><td></td><td>회원정보</td></tr>
<%for(int i=0 ; i<list.size();i++){
String name = list.get(i).getUserId();
%>
<tr>
<th id="<%= i %>"><%=list.get(i).getUserId()%></th>
<th><%=list.get(i).getUserName() %></th>
<th><%=list.get(i).getRegistDate() %></th>
<th><%=list.get(i).getLastModified() %></th>
<th>
<%if(Integer.parseInt(list.get(i).getMemberLevel())==1){ %>
<select name="level" id="level<%= i%>" class="form-control">
    <option value="1" selected="selected">정회원</option>
    <option value="0">준회원</option>
</select>
<%}else{ %>
<select name="level" id="level<%=i%>" class="form-control">
    <option value="1">정회원</option>
    <option value="0" selected="selected">준회원</option>
</select>
<%} %>
</th>
<th><button onclick="levelChange(<%= i %>);" class="btn btn-default">등급변경</button></th>
<th><button onclick="memberDetail(<%= i %>);" class="btn btn-default">회원정보</button></th>
</tr>
<%} %>
</table>
<ul class="pagination pagination-primary" style="justify-content: center;">
	<% if(currentPage <= 1){ %>
	<li class="page-item">
		<a class="page-link"> [맨처음] </a>
		</li>
	<% }else if(searchTitle != null){ %>
	<li class="page-item">
		<a href="/math/mmanager?page=1&title=<%=searchTitle%>&fOption=<%=fOption%>" class="page-link">[맨처음]</a>
		</li>
	<% }else{ %>
	<li class="page-item">
		<a href="/math/mmanager?page=1" class="page-link">[맨처음]</a>
		</li>
	<%} if((currentPage - 5) <= startPage && (endPage - 5) >= 1){ %>
	<li class="page-item">
		<a href="/math/mmanager?page=<%= (startPage - 5) + 4   %>&title=<%=searchTitle%>&fOption=<%=fOption%>" class="page-link">[이전]</a>
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
			<%}else if(searchTitle != null && fOption != null){ %>
			<li class="page-item">
				<a href="/math/mmanager?page=<%=p%>&title=<%=searchTitle%>&fOption=<%=fOption%>" class="page-link"><%=p %></a>
				</li>
				<%}else{ %>
				<li class="page-item">
				<a href="/math/mmanager?page=<%=p%>" class="page-link"><%=p %></a>
				</li>
	<% }} %>
	<% if((startPage + 5) <= maxPage && (currentPage + 5) >= startPage){ %>
	<li class="page-item">
		<a href="/math/mmanager?page=<%=startPage+5%>&title=<%=searchTitle %>&fOption=<%=fOption%>" class="page-link">[다음]</a>
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
		<a href="/math/mmanager?page=<%=maxPage%>&title=<%=searchTitle%>&fOption=<%=fOption%>" class="page-link">[마지막]</a>
		</li>
	<% }else{ %>
	<li class="page-item">
		<a href="/math/mmanager?page=<%=maxPage%>" class="page-link">[마지막]</a>
		</li>
	<%} %>
</ul>
<div align="center" id="d1">
<form action="/math/mmanager?page=1" method="post" class="form-inline ml-auto">
<div class="container">
	<select name="fOption" class="form-control">
		<option value="">선택하세요</option>
		<option name="userid" value="userid">아이디</option>
		<option name="username" value="username">이름</option>
		<option name="phone" value="phone">핸드폰번호</option>
	</select>
	&nbsp;
	<input type="text" name="title" class="form-control" placeholder="Search">
	<button type="submit" class="btn btn-white btn-raised btn-fab btn-round">
                    <i class="material-icons">search</i>
                  </button>
</div>
<%}else{ %>
<p class="log-in">로그인 후 이용할 수 있습니다.</p>
<%} %>
</form>
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
</body>
</html>