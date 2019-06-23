<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Email</title>

<!-- css -->
	<link href="/math/resources/assets/css/reset.css" rel="stylesheet" />
	<link href="/math/resources/assets/css/member/searchemail.css" rel="stylesheet" />
	
<script type="text/javascript" src="/math/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
function searchid() {
	var phone = $("#phone").val();
	
	num = /^[0-9]+$/;
	if(!num.test(phone)){
		alert("숫자만 입력해주세요");
		phone = "";
		phone.focus();
		return;
	}else{
		$.ajax({
			url: "/math/semail",
			type: "get",
			data: {phone : $("#phone").val()},
			success: function(data) {
				console.log(data)
				if(data=="no"){
				$("#p1").html("검색결과 없음");
				}else{
				var jsonStr = JSON.stringify(data);
				var json = JSON.parse(jsonStr);
				var values = "<tr><td>아이디</td></tr>";
				for(var i in json.list){
						values += "<tr><td class='find-id'>" + json.list[i].email+"</td></tr>";
				}
				
				$("#p1").html($("#p1").html() + values);
				}
			},
			error: function( jqXHR, textStatus, errorThrown) {
				console.log("error : "+  jqXHR +", "+textStatus+", "+errorThrown);
			}
		});
	}
}

window.onload = function(){
	$(document).bind("contextmenu",function(){   return false;}); //우클릭방지
	$(document).bind('selectstart',function() {return false;}); //선택방지
	$(document).bind('dragstart',function(){return false;}); //드래그방지	
}
</script>
</head>
<body>
<div class="wrap">
<h1>아이디 찾기</h1>
	<p class="find-num">
		전화번호를 정확하게 입력하세요.
		<input type="text" name="phone" id="phone"></input>
		<button onclick="searchid();">확인</button>
	</p>
<div>
<table id="p1">
</table>
</div>
</div>
</body>
</html>