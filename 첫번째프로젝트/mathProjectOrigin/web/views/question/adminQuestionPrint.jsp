<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />  
<title>감성수학</title>

 <!--css-->
  <link href="/math/resources/assets/css/material-dashboard.css" rel="stylesheet" />
  <link href="/math/resources/assets/css/reset.css" rel="stylesheet" />
  <link href="/math/resources/assets/css/question/adminQuestionPrint.css" rel="stylesheet" />

<script type="text/javascript" src="/math/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$.ajax({
			url: "/math/asemester",
			type: "post",
			dataType: "json",
			success: function(data){
				var jsonStr = JSON.stringify(data);
				var json = JSON.parse(jsonStr);
				
				console.log("갓하늘");
				
				var print = "<option value=''>학기</option>";
				for(var i in json.list){
					print += "<option value='" + decodeURIComponent(json.list[i].semester).replace(/\+/gi, " ")  + "'>" + decodeURIComponent(json.list[i].semester).replace(/\+/gi, " ")  + "</option>";
				}
				$("#f1sel").html(print);
			},
			error: function(jqXHR, textStatus, errorThrown){
				console.log("error: " + jqXHR + ", " + textStatus + ", " + errorThrown);
			}
		});

			$(document).bind("contextmenu",function(){   return false;}); //우클릭방지
			$(document).bind('selectstart',function() {return false;}); //선택방지
			$(document).bind('dragstart',function(){return false;}); //드래그방지	
	});
	function f1change(){
		var semester = $("#f1sel option:selected").val();
		$.ajax({
			url: "/math/book",
			type: "post",
			data: {semester : semester},
			dataType: "json",
			success: function(data){
				var jsonStr = JSON.stringify(data);
				var json = JSON.parse(jsonStr);
				
				var print = "<option value=''>교재</option>";
				for(var i in json.list){
					print += "<option value='" + decodeURIComponent(json.list[i].book).replace(/\+/gi, " ")  + "'>" + decodeURIComponent(json.list[i].book).replace(/\+/gi, " ")  + "</option>";
				}
				$("#f2sel").html(print);
			},
			error: function(jqXHR, textStatus, errorThrown){
				console.log("error: " + jqXHR + ", " + textStatus + ", " + errorThrown);
			}
		});
	}
	
	function f2change(){
		var semester = $("#f1sel option:selected").val();
		var book = $("#f2sel option:selected").val();
		$.ajax({
			url: "/math/chapter",
			type: "post",
			data: {semester : semester, book : book},
			dataType: "json",
			success: function(data){
				var jsonStr = JSON.stringify(data);
				var json = JSON.parse(jsonStr);
				
				var print = "<option value=''>챕터</option>";
				for(var i in json.list){
					print += "<option value='" + decodeURIComponent(json.list[i].chapter).replace(/\+/gi, " ")  + "'>" + decodeURIComponent(json.list[i].chapter).replace(/\+/gi, " ")  + "</option>";
				}
				$("#f3sel").html(print);
			},
			error: function(jqXHR, textStatus, errorThrown){
				console.log("error: " + jqXHR + ", " + textStatus + ", " + errorThrown);
			}
		});
	}
	var num = new Array();
	
	function f3change(){
		var semester = $("#f1sel option:selected").val();
		var book = $("#f2sel option:selected").val();
		var chapter = $("#f3sel option:selected").val();
		var imgPath = "/math/workbook/" + semester + "/" + book + "/" + chapter + "/";
		$.ajax({
			url: "/math/question",
			type: "post",
			data: {semester : semester, book : book, chapter : chapter},
			dataType: "json",
			success: function(data){
				var jsonStr = JSON.stringify(data);
				var json = JSON.parse(jsonStr);
				
				var print="";
				for(var i in json.list){
					var qPath = imgPath + decodeURIComponent(json.list[i].question).replace(/\+/gi, " ");
					print += "<div class='q'><input type='checkbox' class='que' id='"+decodeURIComponent(json.list[i].question)+"' name='img' value='" + qPath + "'><label for='" + decodeURIComponent(json.list[i].question) + "'>" +
		               decodeURIComponent(json.list[i].question).substring(0, decodeURIComponent(json.list[i].question).length-4) + "</label></div>";
				}
				$("#left").html(print);
				/* $("#qform").html(print); */
			},
			error: function(jqXHR, textStatus, errorThrown){
				console.log("error: " + jqXHR + ", " + textStatus + ", " + errorThrown);
			}
		});
	}
	
	function allCheck() {
	      if($(":checkbox").prop("checked")==false){
	         $(":checkbox").prop("checked",true);
	      }else{
	         $(":checkbox").prop("checked",false);
	      }
 	}
	
	function preview(){
		var semester = $("#f1sel option:selected").val();
		var book = $("#f2sel option:selected").val();
		var chapter = $("#f3sel option:selected").val();
		
		if(semester == ""){
			alert("학기를 선택해주세요");
			return;
		}
		if(book == ""){
			alert("교재를 선택해주세요");
			return;
		}
		if(chapter == ""){
			alert("챕터를 선택해주세요");
			return;
		}
		
		var items = [];
		$("input:checkbox[type=checkbox]:checked").each(function () {
		    items.push($(this).val());
		});
		
		if(items == ""){
			alert("문제를 하나 이상 선택해주세요");
			return;
		}
		var title = $("#title").val();
		var waterMark = $("#waterMark").val();

		/* window.open("/math/views/question/preview.jsp?items="+items+"&title="+title+"&waterMark="+waterMark, "a", "width=1000, height=1000, left=100, top=50"); */
		window.open("", "popup_window", "width=1000, height=1000, left=100, top=50");
		$("#qform").attr("action", "/math/views/question/preview.jsp");
		$("#qform").attr("target", "popup_window");
		$("#qform").submit();
	}
	
	function previewTitle(){
		var semester = $("#f1sel option:selected").val();
		var book = $("#f2sel option:selected").val();
		var chapter = $("#f3sel option:selected").val();
		
		if(semester == ""){
			alert("학기를 선택해주세요");
			return;
		}
		if(book == ""){
			alert("교재를 선택해주세요");
			return;
		}
		if(chapter == ""){
			alert("챕터를 선택해주세요");
			return;
		}
		
		var items = [];
		$("input:checkbox[type=checkbox]:checked").each(function () {
		    items.push($(this).val());
		});
		
		if(items == ""){
			alert("문제를 하나 이상 선택해주세요");
			return;
		}
		var title = $("#title").val();
		var waterMark = $("#waterMark").val();

		/* window.open("/math/views/question/preview.jsp?items="+items+"&title="+title+"&waterMark="+waterMark, "a", "width=1000, height=1000, left=100, top=50"); */
		window.open("", "popup_window", "width=1000, height=1000, left=100, top=50");
		$("#qform").attr("action", "/math/views/question/previewTitle.jsp");
		$("#qform").attr("target", "popup_window");
		$("#qform").submit();
	}
	
	function sixPreview(){
		var semester = $("#f1sel option:selected").val();
		var book = $("#f2sel option:selected").val();
		var chapter = $("#f3sel option:selected").val();
		
		if(semester == ""){
			alert("학기를 선택해주세요");
			return;
		}
		if(book == ""){
			alert("교재를 선택해주세요");
			return;
		}
		if(chapter == ""){
			alert("챕터를 선택해주세요");
			return;
		}
		
		var items = [];
		$("input:checkbox[type=checkbox]:checked").each(function () {
		    items.push($(this).val());
		});
		
		if(items == ""){
			alert("문제를 하나 이상 선택해주세요");
			return;
		}
		var title = $("#title").val();
		var waterMark = $("#waterMark").val();

		/* window.open("/math/views/question/preview.jsp?items="+items+"&title="+title+"&waterMark="+waterMark, "a", "width=1000, height=1000, left=100, top=50"); */
		window.open("", "popup_window", "width=1000, height=1000, left=100, top=50");
		$("#qform").attr("action", "/math/views/question/sixPreview.jsp");
		$("#qform").attr("target", "popup_window");
		$("#qform").submit();
	}
	
	function sixPreviewTitle(){
		var semester = $("#f1sel option:selected").val();
		var book = $("#f2sel option:selected").val();
		var chapter = $("#f3sel option:selected").val();
		
		if(semester == ""){
			alert("학기를 선택해주세요");
			return;
		}
		if(book == ""){
			alert("교재를 선택해주세요");
			return;
		}
		if(chapter == ""){
			alert("챕터를 선택해주세요");
			return;
		}
		
		var items = [];
		$("input:checkbox[type=checkbox]:checked").each(function () {
		    items.push($(this).val());
		});
		
		if(items == ""){
			alert("문제를 하나 이상 선택해주세요");
			return;
		}
		var title = $("#title").val();
		var waterMark = $("#waterMark").val();

		/* window.open("/math/views/question/preview.jsp?items="+items+"&title="+title+"&waterMark="+waterMark, "a", "width=1000, height=1000, left=100, top=50"); */
		window.open("", "popup_window", "width=1000, height=1000, left=100, top=50");
		$("#qform").attr("action", "/math/views/question/sixPreviewTitle.jsp");
		$("#qform").attr("target", "popup_window");
		$("#qform").submit();
	}
	
	function makeSemester(){
		var newSemester = prompt("생성할 학기 이름");
		if(newSemester == null){
			return;
		}
		
		if(confirm("정말로 생성하시겠습니까?")){	
			location.href = "/math/mSemester?newSemester="+newSemester;
		}else{
			return;
		}
	}
	
	function makeBook(){
		var semester = $("#f1sel option:selected").val();
		if(semester == ""){
			alert("학기를 선택해주세요");
			return;
		}
		var newBook = prompt("생성할 교재 이름");
		
		if(newBook == null){
			return;
		}
		
		if(confirm("정말로 생성하시겠습니까?")){	
			location.href = "/math/mBook?newBook="+newBook+"&semester="+semester;
		}else{
			return;
		}
	}
	
	function makeChapter(){
		var semester = $("#f1sel option:selected").val();
		var book = $("#f2sel option:selected").val();
		if(semester == ""){
			alert("학기를 선택해주세요");
			return;
		}
		if(book == ""){
			alert("교재를 선택해주세요");
			return;
		}
		var newChapter = prompt("생성할 챕터 이름");
		if(newChapter == null){
			return;
		}
		if(confirm("정말로 생성하시겠습니까?")){	
			location.href = "/math/mChapter?newChapter="+newChapter+"&semester="+semester+"&book="+book;
		}else{
			return;
		}
	}
	
	function uploadImg(){
		var semester = $("#f1sel option:selected").val();
		var book = $("#f2sel option:selected").val();
		var chapter = $("#f3sel option:selected").val();
		
		if(semester == ""){
			alert("학기를 선택해주세요");
			return;
		}
		if(book == ""){
			alert("교재를 선택해주세요");
			return;
		}
		if(chapter == ""){
			alert("챕터를 선택해주세요");
			return;
		}
		$("#semester").val(semester);
		$("#book").val(book);
		$("#chapter").val(chapter);
		
		$("#uform").attr("action", "/math/upImg");
		$("#uform").submit();
	}
	
	function delSemester(){
		var semester = $("#f1sel option:selected").val();
		if(semester == ""){
			alert("학기를 선택해주세요");
			return;
		}
		location.href = "/math/dSemester?semester="+semester;
	}
	
	function delBook(){
		var semester = $("#f1sel option:selected").val();
		var book = $("#f2sel option:selected").val();
		if(semester == ""){
			alert("학기를 선택해주세요");
			return;
		}
		if(book == ""){
			alert("교재를 선택해주세요");
			return;
		}
		location.href = "/math/dBook?semester="+semester+"&book="+book;
	}
	
	function delChapter(){
		var semester = $("#f1sel option:selected").val();
		var book = $("#f2sel option:selected").val();
		var chapter = $("#f3sel option:selected").val();
		
		if(semester == ""){
			alert("학기를 선택해주세요");
			return;
		}
		if(book == ""){
			alert("교재를 선택해주세요");
			return;
		}
		if(chapter == ""){
			alert("챕터를 선택해주세요");
			return;
		}
		
		location.href = "/math/dChapter?semester="+semester+"&book="+book+"&chapter="+chapter;
	}
	
	function delImg(){
		var semester = $("#f1sel option:selected").val();
		var book = $("#f2sel option:selected").val();
		var chapter = $("#f3sel option:selected").val();
		
		if(semester == ""){
			alert("학기를 선택해주세요");
			return;
		}
		if(book == ""){
			alert("교재를 선택해주세요");
			return;
		}
		if(chapter == ""){
			alert("챕터를 선택해주세요");
			return;
		}
		var items = [];
		$("input:checkbox[type=checkbox]:checked").each(function () {
		    items.push($(this).val());
		});
		
		if(items == ""){
			alert("문제를 하나 이상 선택해주세요");
			return;
		}
		
		$("#semesterDel").val(semester);
		$("#bookDel").val(book);
		$("#chapterDel").val(chapter);
		
		/* var items = [];
		$('input:checkbox[type=checkbox]:checked').each(function () {
		    items.push($(this).val());
		}); */
		
	 	$("#qform").attr("action", "/math/dImg");
		$("#qform").submit();
		
/* 		location.href = "/math/dImg?semester="+semester+"&book="+book+"&chapter="+chapter+"&items="+items; */
	}
	
	function superUpload(){
		location.href = "/math/sUpload";
	}
	
	function delText(){
		location.href = "/math/dText";
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
                  <h4 class="card-title ">문제관리</h4>
                <%if(admin != null){ %>
                  <p class="card-category">문제지 출력 페이지</p>
                 <%}%>
                </div>
                <div class="card-body">
                  <div class="table-responsive">
<%if(admin != null){ %>
<table class="table">
<tr></tr>
<tr>
</tr>
<tr>
<td>
	<select id="f1sel" onChange="f1change();" class="form-control">
	</select><br><br>
	<div align="center">
	<button onclick="makeSemester();" class="btn btn-default btn-sm">추가</button>&nbsp;&nbsp;&nbsp;&nbsp;
	<button onclick="delSemester();" class="btn btn-default btn-sm">삭제</button>
	</div>
	</td>
	<td>
		<select id="f2sel" onChange="f2change();" class="form-control">
	</select><br><br>
	<div align="center">
	<button onclick="makeBook();" class="btn btn-default btn-sm">추가</button>&nbsp;&nbsp;&nbsp;&nbsp;
	<button onclick="delBook();" class="btn btn-default btn-sm">삭제</button>
	</div>
	</td>
	<td>
	<select id="f3sel" onChange="f3change();" class="form-control">
	</select><br><br>
	<div align="center">
	<button onclick="makeChapter();" class="btn btn-default btn-sm">추가</button>&nbsp;&nbsp;&nbsp;&nbsp;
	<button onclick="delChapter();" class="btn btn-default btn-sm">삭제</button>
	</div>
	</td>
</tr>
</table>
<div id="upload" align="center">
	<form id="uform" method="post" enctype="multipart/form-data">
	<input type="hidden" name="semester" id="semester">
	<input type="hidden" name="book" id="book">
	<input type="hidden" name="chapter" id="chapter">
	<input type="file" id="upImg" name="upImg" class="form-control" accept=".gif, .jpg, .png, .bmp, .jpeg">
</form><br>
<button onclick="uploadImg();" class="btn btn-default btn-sm">문제 등록</button>
<button onclick="superUpload();" class="btn btn-default btn-sm">Super Upload</button>
</div>

<table class="table">
<tr>
</tr>
<tr>
<td>
<!-- <div class="form-group">
<label for="exampleFormControlTextarea1">문제지 Title</label> <input type="text" id="title" class="form-control">
</div> -->
</td>
<td>
<!-- <div class="form-group">
<label for="exampleFormControlTextarea1">워터마크</label> <input type="text" id="waterMark" class="form-control" >
</div> -->
</td>
</tr>
</table>
<form id="qform" name="qform" method="post">
	<input type="hidden" name="semesterDel" id="semesterDel">
	<input type="hidden" name="bookDel" id="bookDel">
	<input type="hidden" name="chapterDel" id="chapterDel">
<div class="form-group">
<div class="form-group">
<label for="exampleFormControlTextarea1">문제지 Title</label> <input type="text" id="title" name="title" class="form-control">
</div>
<label for="exampleFormControlTextarea1">워터마크</label> <input type="text" id="waterMark" name="waterMark" class="form-control" >
</div>
<div align="center">
<button type="button" onclick="previewTitle();" class="btn btn-default btn-sm">4문제 미리보기 (Title)</button>
<button type="button" onclick="preview();" class="btn btn-default btn-sm">4문제 미리보기</button>
<button type="button" onclick="sixPreviewTitle();" class="btn btn-default btn-sm">6문제 미리보기 (Title)</button>
<button type="button" onclick="sixPreview();" class="btn btn-default btn-sm">6문제 미리보기</button>
	<button type="button" onclick="allCheck();" class="btn btn-default btn-sm">전체 선택</button>
	<button type="button" onclick="delImg();" class="btn btn-default btn-sm">문제 삭제</button>
	<button type="button" onclick="delText();" class="btn btn-default btn-sm">오답노트 비우기</button>
	</div>
<div id="qImg" class="border" style=" overflow:auto;">
<div id="left" ></div>
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