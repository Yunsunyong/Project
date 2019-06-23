<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="notice.model.vo.Notice" %>    
<%
	Notice notice = (Notice)request.getAttribute("notice");
	String searchTitle = (String)request.getAttribute("searchTitle");
	String nOption = (String)request.getAttribute("noption");
	int noticeBack = ((Integer)request.getAttribute("noticeBack")).intValue();
	int noticeNext = ((Integer)request.getAttribute("noticeNext")).intValue();
	int noticeMin = ((Integer)request.getAttribute("noticeMin")).intValue();
	int currentPage = ((Integer)request.getAttribute("currentPage")).intValue();
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 상세보기</title>
<!-- css -->
	<link href="/math/resources/assets/css/material-kit.css" rel="stylesheet" />
	<link href="/math/resources/assets/css/reset.css" rel="stylesheet" />
	<link href="/math/resources/assets/css/notice/noticeUserDetailView.css" rel="stylesheet" />
	
<script type="text/javascript">
	function noticeList(){
			location.href="/math/nuslist?page="+<%=currentPage%>;
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
          <h1>공지사항</h1>
            <h3 class="title text-center">상세보기</h3>
          </div>
        </div>
      </div>
    </div>
  </div>
   <div class="main main-raised">
    <div class="container">
      <div class="section text-center">
	<table class="table table-striped table-hover">
		<tr>
			<th>제목</th>
			<td><%=notice.getNoticeTitle() %></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><%=notice.getWriterName()%></td>
		</tr>
		<tr>
			<th>글내용</th>
			<td><%=notice.getNoticeContent()%></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<% if(notice.getOriginalFileName() != null && !notice.getOriginalFileName().equals("null")){ %>
					<a href="/math/nfdown?ofile=<%=notice.getOriginalFileName() %>&rfile=<%=notice.getRenameFileName()%>"><%=notice.getOriginalFileName() %></a>
				<% }else{ %>
					첨부파일없음
				<% } %>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="buttonz">
				<button onclick="noticeList();" class="btn btn-default btn-sm">목록가기</button>
				<%if(notice.getNoticeNo() > noticeMin){ %>
					<button name="btn" onclick="javascript:location.href='/math/nuback?no=<%=notice.getNoticeNo()%>'" class="btn btn-default btn-sm">이전글</button>
				<%} if(notice.getNoticeNo() < noticeNext){ %>
					<button name="btn" onclick="javascript:location.href='/math/nunext?no=<%=notice.getNoticeNo()%>'" class="btn btn-default btn-sm">다음글</button>
				<%} %>
			</td>
		</tr>
	</table>
	</div>
	</div>
	</div>
</body>
<%@ include file="../common/ufooter.jsp" %>
</html>