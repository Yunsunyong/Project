<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="popup.model.vo.Popup" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팝업등록</title>

<!-- css -->
	<link href="/math/resources/assets/css/material-dashboard.css" rel="stylesheet" />
	<link href="/math/resources/assets/css/reset.css" rel="stylesheet" />
	<link href="/math/resources/assets/css/popup/popupWriteView.css" rel="stylesheet" />
	
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <script type="text/javascript">

window.onload = function(){
	$(document).bind("contextmenu",function(){   return false;}); //우클릭방지
	$(document).bind('selectstart',function() {return false;}); //선택방지
	$(document).bind('dragstart',function(){return false;}); //드래그방지	
}
</script>
<script type="text/javascript" src="/math/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
<%@ include file="../common/Adminheader.jsp"  %>
<div class="content">
        <div class="container-fluid">
          <div class="card">
            <div class="card-header card-header-primary">
              <h4 class="card-title">팝업</h4>
              <p class="card-category">팝업등록</p>
            </div>
            <div class="card-body">
              <div id="typography">
                <div class="card-title">
<form action="/math/pinsert" method="post" accept-charset="utf-8" enctype="multipart/form-data">
<table class="table">
<tr>
</tr>
	<tr>
		<th>제목</th>
		<td><input type="text" name="ptitle" id="ptitle" required class="form-control"></td>
	</tr>
	<tr>
		<input type="hidden" name="plink" id="plink" required class="form-control" value="popup.jsp" readonly>
	</tr>
	<tr>
		<th>x좌표</th>
		<td><input type="number" name="pX" id="pX" required class="form-control"></td>
	</tr>
	<tr>
		<th>y좌표</th>
		<td><input type="number" name="pY" id="pY" required class="form-control"></td>
	</tr>
	<tr>
		<th>width</th>
		<td><input type="number" name="pWidth" id="pWidth" required class="form-control"></td>
	</tr>
	<tr>
		<th>height</th>
		<td><input type="number" name="pHeight" id="pHeight" required class="form-control"></td>
	</tr>
	<tr>
		<td colspan="2" class="link-date">
		<div class="sd-wrap">
		<label for="startDate">시작일</label>
		<input type="date"  class="form-control" name="startDate" id="startDate" required>
		</div>
		<span class="wave-dashh">~</span>
		<div class="ed-wrap">
		<label for="endDate">종료일</label>
		<input type="date"  class="form-control" name="endDate" id="endDate" required>
		</div>
		</td>
	</tr>
	<tr>
		<th>이미지첨부</th>
		<td><input type="file" name="imagelink" id="imagelink" required class="form-control"></td>
	</tr>
	<tr>
		<th>링크주소</th>
		<td><input type="text" name="imgl" id="imgl" required class="form-control"></td>
	</tr>
	<tr>
		<th>설명</th>
		<td><textarea rows="2" cols="60" name="discrip" id="discrip" required class="form-control"></textarea></td>
	</tr>
</table>
<div style="text-align:center;" id="d1">
			<input type="submit" value="팝업등록" class="btn btn-default btn-sm">
			<input type="reset" value="등록취소" class="btn btn-default btn-sm">
			<button onclick="javascript:location.href='/math/plist'; return false;" class="btn btn-default btn-sm">목록가기</button>
</div>
</form>
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