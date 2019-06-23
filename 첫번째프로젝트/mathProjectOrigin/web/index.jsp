<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <!--     Fonts and icons     -->
  <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
  <!-- Material Kit CSS -->
  <link href="/math/resources/assets/css/material-kit.css?v=2.0.5" rel="stylesheet" />
  <link href="/math/resources/assets/css/.min.css?v=2.0.5" rel="stylesheet" />
  
<title>메인</title>
<script type="text/javascript" src="/math/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
$(function(){
	 $.ajax({
		 	url : '/math/popup.do',
	        type : 'post',
	        dataType : 'json',
	        success : function(data){
	                var jsonStr = JSON.stringify(data);
					var json = JSON.parse(jsonStr);
					for( i in json.list){
						var sdate= new Date(json.list[i].startDate);
						var edate= new Date(json.list[i].endDate);
						var images = json.list[i].popupImagePath;
						var imglink = decodeURIComponent(json.list[i].popupImgLink);
						var now = new Date();
						if(now >= sdate && now <= edate){
						OpenPopup(json.list[i].popupLink+"?images="+ images + "&imglink=" + imglink , decodeURIComponent(json.list[i].popupName) , json.list[i].popupWidth, json.list[i].popupHeight, json.list[i].popupX, json.list[i].popupY);
							}
						}
	            }, error : function(jqXHR, textStatus, errorThrown){
	            	console.log("error : " + jqXHR + textStatus + errorThrown);
	            }
	    });
});

function getCookie(name) {
		var cookie = document.cookie;
		if (document.cookie != "") {
			var cookie_array = cookie.split("; ");
			for ( var index in cookie_array) {
				var cookie_name = cookie_array[index].split("=");
				if (cookie_name[0] == "popupYN") {
					return cookie_name[1];
				}
			}
		}
		return;
	}

function OpenPopup(link, name , width, height, left, top){
       var cookieCheck = getCookie("popupYN"); 
		var winPop;
		if (cookieCheck != "N")
       winPop = window.open(link ,'pop'+name, 'toolbar=yes,width='+width+',height='+height+',left='+left+',top='+top+',status=yes,scrollbars=no, resize=yes, toolbar=no, location=no' );
		
   }

window.onload = function(){
	$(document).bind("contextmenu",function(){   return false;}); //우클릭방지
	$(document).bind('selectstart',function() {return false;}); //선택방지
	$(document).bind('dragstart',function(){return false;}); //드래그방지	
}
</script>
</head>
<body>
<%@ include file="views/common/header.jsp" %>
<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
  <ol class="carousel-indicators">
    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
  </ol>
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img class="d-block w-100" src="/math/resources/images/image1.jpg" alt="First slide">
    </div>
    <div class="carousel-item">
      <img class="d-block w-100" src="/math/resources/images/image2.jpg" alt="Second slide">
    </div>
    <div class="carousel-item">
      <img class="d-block w-100" src="/math/resources/images/image3.jpg" alt="Third slide">
    </div>
  </div>
  <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
<%@ include file="views/common/ufooter.jsp" %>
  <script src="/math/resources/assets/js/core/jquery.min.js" type="text/javascript"></script>
  <script src="/math/resources/assets/js/core/popper.min.js" type="text/javascript"></script>
  <script src="/math/resources/assets/js/core/bootstrap-material-design.min.js" type="text/javascript"></script>
  <script src="/math/resources/assets/js/plugins/moment.min.js"></script>
  <!--	Plugin for the Datepicker, full documentation here: https://github.com/Eonasdan/bootstrap-datetimepicker -->
  <script src="/math/resources/assets/js/plugins/bootstrap-datetimepicker.js" type="text/javascript"></script>
  <!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
  <script src="/math/resources/assets/js/plugins/nouislider.min.js" type="text/javascript"></script>
  <!-- Place this tag in your head or just before your close body tag. -->
  <script async defer src="https://buttons.github.io/buttons.js"></script>
  <!--	Plugin for Sharrre btn -->
  <script src="/math/resources/assets/js/plugins/jquery.sharrre.js" type="text/javascript"></script>
</body>
</html>