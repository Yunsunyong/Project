<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="course.model.vo.Member" %>    
<% Member member = (Member)request.getAttribute("member"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>감성수학</title>

<!-- css -->
	<link href="/math/resources/assets/css/material-kit.css" rel="stylesheet" />
	<link href="/math/resources/assets/css/reset.css" rel="stylesheet" />
	<link href="/math/resources/assets/css/question/questionPrint.css" rel="stylesheet" />
	
<script type="text/javascript" src="/math/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$.ajax({
			url: "/math/semester",
			type: "post",
			dataType: "json",
			success: function(data){
				var jsonStr = JSON.stringify(data);
				var json = JSON.parse(jsonStr);
				
				var print = "<option value=''>학기</option>";
				for(var i in json.list){
					print += "<option value='" + decodeURIComponent(json.list[i].semester).replace(/\+/gi, " ") + "'>" + decodeURIComponent(json.list[i].semester).replace(/\+/gi, " ") + "</option>";
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
					print += "<option value='" + decodeURIComponent(json.list[i].book).replace(/\+/gi, " ") + "'>" + decodeURIComponent(json.list[i].book).replace(/\+/gi, " ") + "</option>";
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
					print += "<option value='" + decodeURIComponent(json.list[i].chapter).replace(/\+/gi, " ") + "'>" + decodeURIComponent(json.list[i].chapter).replace(/\+/gi, " ") + "</option>";
				}
				$("#f3sel").html(print);
			},
			error: function(jqXHR, textStatus, errorThrown){
				console.log("error: " + jqXHR + ", " + textStatus + ", " + errorThrown);
			}
		});
	}
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
					print += "<div class='q'><input type='checkbox' name='img' value='" + qPath + "'>" +
					decodeURIComponent(json.list[i].question).substring(0, decodeURIComponent(json.list[i].question).length-4) + "</div>";
					
				}
				$("#left").html(print);
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
		
	function textDown(){
		var semester = $("#f1sel option:selected").val();
		var book = $("#f2sel option:selected").val();
		var chapter = $("#f3sel option:selected").val();
		var title = $("#title").val();
		var name="";
		$('input:checkbox[type=checkbox]:checked').each(function () {
		   name += $(this).val(); 
		});
		$.ajax({
			url: "/math/tdown",
			type: "post",
			data: {semester: semester,
					book: book,
					chapter: chapter,
					name: name,
					title : title},
			success: function(data) {
					if(data == "ok"){
						alert("오답노트 생성 성공 다운로드 받아주세요!");
						$("#ddd").html("<a href='/math/files/text/"+$("#title").val()+".txt' download>오답노트<a>");
					}else{
						alert("오답노트 생성 실패!");
					}
			},
			error: function( jqXHR, textStatus, errorThrown) {
				console.log("error : "+  jqXHR +", "+textStatus+", "+errorThrown);
			}
		});
	};
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
          <h1>문제</h1>
            <h3 class="title text-center">문제지 출력</h3>
            <%}else { %>
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
      <table class="table">
      <tr>
      <td>
<div align="center">
<label for="exampleFormControlTextarea1">학기선택</label>
	</div>
	<select id="f1sel" onChange="f1change();" class="form-control">
	</select>
</td>
<td>
<div align="center">
<label for="exampleFormControlTextarea1">교재선택</label>
	</div>
	<select id="f2sel" onChange="f2change();" class="form-control">
	</select>
</td>
	<td>
	<div align="center">
	<label for="exampleFormControlTextarea1">챕터선택</label>
	</div>
	<select id="f3sel" onChange="f3change();" class="form-control">
	</select>
	</td>
</tr>
</table>
<form id="qform" name="qform" method="post">
<table class="table">
<tr><td>
<div class="form-group">
<label for="exampleFormControlTextarea1">문제지 Title</label><input type="text" id="title" name="title" class="form-control">
</div>
</td><td>
<div class="form-group">
<label for="exampleFormControlTextarea1">워터마크</label><input type="text" id="waterMark" name="waterMark" class="form-control">
</div>
</td>
</tr>
</table>
	<button type="button" onclick="previewTitle();" class="btn btn-default btn-sm">4문제 미리보기 (Title)</button>
	<button type="button" onclick="preview();" class="btn btn-default btn-sm">4문제 미리보기</button>
	<button type="button" onclick="sixPreviewTitle();" class="btn btn-default btn-sm">6문제 미리보기 (Title)</button>
	<button type="button" onclick="sixPreview();" class="btn btn-default btn-sm">6문제 미리보기</button>
	<button type="button" onclick="allCheck();" class="btn btn-default btn-sm">전체 선택</button>
	<button type="button" onclick="textDown();" class="btn btn-default btn-sm">오답 체크</button>
	<div id="ddd"></div>
<div id="qImg" class="border" style=" overflow:auto;">
<div id="left"></div>
</div>
</form>
<% if(member.getMemberLevel().equals("0")){ %>
<script type="text/javascript">
window.onload = function(){
	alert("접근하실수 없습니다.");
	history.go(-1);
}
</script>
<%} %>
<%} %>
</div>
</div>
</div>

<%@ include file="../common/ufooter.jsp" %>
</body>
</html>