<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- css -->
	<link href="/math/resources/assets/css/material-kit.css" rel="stylesheet" />
	<link href="/math/resources/assets/css/reset.css" rel="stylesheet" />
	<link href="/math/resources/assets/css/qna/questionWriteView.css" rel="stylesheet" />
	
<script type="text/javascript" src="/math/SE2/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="/math/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
var oEditors = [];
$(function(){
      nhn.husky.EZCreator.createInIFrame({
          oAppRef: oEditors,
          elPlaceHolder: "con1", //textarea에서 지정한 id와 일치해야 합니다. 
          //SmartEditor2Skin.html 파일이 존재하는 경로
          sSkinURI: "/math/SE2/SmartEditor2Skin.html",  
          htParams : {
              // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
              bUseToolbar : true,             
              // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
              bUseVerticalResizer : true,     
              // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
              bUseModeChanger : true,      
              bSkipXssFilter : true,
              fOnBeforeUnload : function(){
                   
              }
          }, 
          fOnAppLoad : function(){
              //기존 저장된 내용의 text 내용을 에디터상에 뿌려주고자 할때 사용
              oEditors.getById["con1"].exec("PASTE_HTML",[""]);
          },
          fCreator: "createSEditor2"
      });
      
      //저장버튼 클릭시 form 전송
      $("#save").click(function(){
    	 	 oEditors.getById["con1"].exec("UPDATE_CONTENTS_FIELD", []);
    	 	var title = $("#title").val();
         var con1 = $("#con1").val();
         
         if(title == "" || title == null || title == "&nbsp;" || title == "<p>&nbsp;</p>" || title == "<br>"){
   		  alert("제목을 입력해주세요.");
   		  $("#title").focus();
   		  return false;
   	  }else if(con1 == "" || con1 == null || con1 == "&nbsp;" || con1 == "<p>&nbsp;</p>" || con1 == "<br>"){
   		  alert("내용을 입력해주세요.");
   		  oEditors.getById["con1"].exec("FOCUS");
   		  return false;
   	  }
     	  
          	$("#nform").submit();
       });
       document.addEventListener("keydown", function(event) {
     		if (event.keyCode === 13) {
     			event.preventDefault();
     			}
     		}, true);

       window.onload = function(){
       	$(document).bind("contextmenu",function(){   return false;}); //우클릭방지
       	$(document).bind('selectstart',function() {return false;}); //선택방지
       	$(document).bind('dragstart',function(){return false;}); //드래그방지	
       }
});
</script>
</head>
<body>
<%@ include file="../common/header.jsp" %>
<div class="page-header header-filter" data-parallax="true" style="background-image: url('/math/resources/images/math-3986758_1920.jpg')">
    <div class="container">
      <div class="row">
        <div class="col-md-8 ml-auto mr-auto">
          <div class="brand text-center">
          <h1>QnA</h1>
            <h3 class="title text-center">질문쓰기페이지</h3>
          </div>
        </div>
      </div>
    </div>
  </div>
   <div class="main main-raised">
    <div class="container">
      <div class="section text-center">
<form id="nform" action="/math/qqwrite" method="post" enctype="multipart/form-data">
<input type="hidden" name="userId" value="<%=userId%>">
<table class="table">
	<tr>
		<td>제목</td>
		<td><input type="text"  id="title" name="qQTitle" class="form-control"></td>
	</tr>
	<tr>
		<td>질문내용</td>
		<td><textarea row="10" cols="30" id="con1" name="content" style="width:100%; height: 350px;"></textarea></td>
	</tr>
	<tr>
		<td>첨부파일</td>
		<td><input type="file" name="upfile" class="form-control"></td>
	</tr>
</table>
<div id="d1">
<input type="button" id="save" value="질문등록" class="btn btn-default btn-sm">
</div>
</form>
</div>
</div>
</div>
<%@ include file="../common/ufooter.jsp" %>
</body>
</html>