<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="popup.model.vo.Popup" %>
<%
	Popup popup = (Popup)request.getAttribute("popup");
	int currentPage = ((Integer)request.getAttribute("currentPage")).intValue();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>감성수학</title>

<!-- css -->
	<link href="/math/resources/assets/css/material-dashboard.css" rel="stylesheet" />
	<link href="/math/resources/assets/css/reset.css" rel="stylesheet" />
	<link href="/math/resources/assets/css/popup/popupUpdateView.css" rel="stylesheet" />
	
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<script type="text/javascript" src="/math/SE2/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="/math/resources/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
function listView(){
	location.href = '/math/plist?page='+<%=currentPage%>;
}

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
                  <h4 class="card-title ">팝업</h4>
                  <p class="card-category"><%= popup.getPopupNo() %>번 글 수정페이지</p>
                </div>
                <div class="card-body">
                  <div class="table-responsive">
<form id="pform" action="/math/pupdate" method="post" enctype="multipart/form-data">
<input type="hidden" name="no" value="<%= popup.getPopupNo() %>">
<input type="hidden" name="imagelink" value="<%=popup.getPopupImagePath() %>">
	<table class="table">
	<tr>
	</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="ptitle" value="<%= popup.getPopupName() %>" class="form-control"></td>
		</tr>
		<tr>
			<input type="hidden" name="plink" value="<%=popup.getPopupLink() %>"  class="form-control" value="popup.jsp">
		</tr>
			<tr>
			<th>X</th>
			<td><input type="number" name="pX" value="<%= popup.getPopupX() %>" class="form-control"></td>
		</tr>
		<tr>
			<th>Y</th>
			<td><input type="number" name="pY" value="<%= popup.getPopupY() %>" class="form-control"></td>
		</tr>
		<tr>
			<th>width</th>
			<td><input type="number" name="pWidth" value="<%= popup.getPopupWidth() %>" class="form-control"></td>
		</tr>
		<tr>
			<th>height</th>
			<td><input type="number" name="pHeight" value="<%= popup.getPopupHeight() %>" class="form-control"></td>
		</tr>
		<tr>
			<td colspan="2" class="link-date">
			<div class="sd-wrap">
			<label for="startDate">시작일</label>
			<input type="date" name="startDate" id="startDate"  value="<%= popup.getPopupDate() %>" class="form-control">
			</div>
			<span class="wave-dashh">~</span>
			<div class="ed-wrap">
			<label for="endDate">종료일</label>
			<input type="date" name="endDate" id="endDate"  value="<%= popup.getPopupEndDate() %>" class="form-control">
			</div>
		</td>
		</tr>
		<tr>
			<th>링크주소</th>
			<td><input type="text" name="imgl" id="imgl" value="<%= popup.getPopupImgLink() %>" class="form-control"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea row="10" cols="30" id="con1" name="discrip" style="width:100%; height: 350px;" class="form-control"><%= popup.getPopupExplan() %></textarea></td>
		</tr>
		<tr height="50">
			<th>첨부파일</th>
			<td id="filetd">
				<% if(popup.getPopupImagePath() != null && !popup.getPopupImagePath().equals("null")){ %>
					<%= popup.getPopupImagePath() %>
				<% }else{ %>
					첨부파일없음
				<% } %>&nbsp;
				<input type="file" value="첨부파일 변경"  name="imagelinkk" class="form-control">
			</td>
			</tr>
	</table>
	<div align="center" id="d1">
				<input type="submit"  value="수정하기" class="btn btn-defualt btn-sm">
				<button onclick="listView(); return false;" class="btn btn-default btn-sm">목록으로 돌아가기</button>
				</div>
</form>
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