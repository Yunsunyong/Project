<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% Cookie[] cookies = request.getCookies();
if(cookies != null){
	for(Cookie cookie : cookies){
		if(cookie.getName().equals("userId")){
			session.setAttribute("userId", cookie.getValue());
		}
	}
}
%>
<!doctype html>
<html lang="kr">
	<head>
	<meta charset="UTF-8">
	<title>감성수학</title>
<script type="text/javascript" src="/math/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">

window.onload = function(){
	$(document).bind("contextmenu",function(){   return false;}); //우클릭방지
	$(document).bind('selectstart',function() {return false;}); //선택방지
	$(document).bind('dragstart',function(){return false;}); //드래그방지	
}
<%for(Cookie cookie : cookies){
	if(cookie.getName().equals("userId")){%>
$(function() {
	location.href="/math/mautologin";
});
<%}}%>
function checkId() {
	$.ajax({
		url: "/math/chkid",
		type: "post",
		data: {userid: $("#email2").val()},
		success: function(data) {
				console.log("success : "+ data);
				if(data == "ok"){
					alert("사용 가능한 아이디입니다.");
					$("#password2").focus();
				}else{
					alert("이미 사용중인 아이디입니다.\n"
							+"다시 입력하십시요.");
					$("#email2").select();
				}
		},
		error: function( jqXHR, textStatus, errorThrown) {
			console.log("error : "+  jqXHR +", "+textStatus+", "+errorThrown);
		}
		
		
	});
	return false;
}
$(function() {
	$("#password3").blur(function() {
		console.log("포커스 사라짐");
		var pwd1 = $("#password2").val();
		var pwd2 = $("#password3").val();
		if(pwd1 != pwd2){
			alert("암호와 암호 확인이 일치하지 않습니다.\n"
					+"다시 입력하십시요.");
			$("#password2").select();
		}
	});
});
$(function() {
	$("#searchid").click(function() {
		window.open("/math/views/member/searchemail.jsp","window", "width=500,height=300");
	});
});
$(function() {
	$("#tac").click(function() {
		window.open("/math/views/member/TermsAndConditions.jsp","window", "width=500,height=300");
	});
});
$(function() {
	$("#phone").blur(function() {
		console.log("포커스 사라짐");
		var phone = $("#phone").val();
		
		num = /^[0-9]+$/;
		if(!num.test(phone)){
			alert("숫자만 입력해주세요");
			phone = "";
			phone.focus();
			return;
		}
		
	});
});
</script>
<style rel="stylesheet">
@charset "UTF-8";
@import url(https://fonts.googleapis.com/css?family=Lato:400,700);
* {
  -moz-box-sizing: border-box;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
}

body {
  font-family: 'Lato', sans-serif;
  background-color: #f8f8f8;
}
body .container {
  position: relative;
  overflow: hidden;
  width: 700px;
  height: 700px;
  margin: 80px auto 0;
  background-color: #ffffff;
  -moz-box-shadow: rgba(0, 0, 0, 0.1) 0px 10px 30px;
  -webkit-box-shadow: rgba(0, 0, 0, 0.1) 0px 10px 30px;
  box-shadow: rgba(0, 0, 0, 0.1) 0px 10px 30px;
}
body .container .half {
  float: left;
  width: 50%;
  height: 100%;
  padding: 58px 40px 0;
}
body .container .half.bg {
  background-image: url(/math/resources/images/math-3986758_1920.jpg);
  background-size: cover;
  background-repeat: no-repeat;
}
body .container h1 {
  font-size: 18px;
  font-weight: 700;
  margin-bottom: 23px;
  text-align: center;
  text-indent: 6px;
  letter-spacing: 7px;
  text-transform: uppercase;
  color: #263238;
}
body .container .tabs {
  width: 100%;
  margin-bottom: 29px;
  border-bottom: 1px solid #d9d9d9;
}
body .container .tabs .tab {
  display: inline-block;
  margin-bottom: -1px;
  padding: 20px 15px 10px;
  cursor: pointer;
  letter-spacing: 0;
  border-bottom: 1px solid #d9d9d9;
  -moz-user-select: -moz-none;
  -ms-user-select: none;
  -webkit-user-select: none;
  user-select: none;
  transition: all 0.1s ease-in-out;
}
body .container .tabs .tab a {
  font-size: 11px;
  text-decoration: none;
  text-transform: uppercase;
  color: #d9d9d9;
  transition: all 0.1s ease-in-out;
}
body .container .tabs .tab.active a, body .container .tabs .tab:hover a {
  color: #263238;
}
body .container .tabs .tab.active {
  border-bottom: 1px solid #263238;
}
body .container .content form {
  position: relative;
  height: 287px;
}
body .container .content label:first-of-type, body .container .content input:first-of-type, body .container .content .more:first-of-type {
  -moz-animation: slideIn 0.4s cubic-bezier(0.37, 0.82, 0.2, 1);
  -webkit-animation: slideIn 0.4s cubic-bezier(0.37, 0.82, 0.2, 1);
  animation: slideIn 0.4s cubic-bezier(0.37, 0.82, 0.2, 1);
}
body .container .content label:nth-of-type(2), body .container .content input:nth-of-type(2), body .container .content .more:nth-of-type(2) {
  -moz-animation: slideIn 0.5s cubic-bezier(0.37, 0.82, 0.2, 1);
  -webkit-animation: slideIn 0.5s cubic-bezier(0.37, 0.82, 0.2, 1);
  animation: slideIn 0.5s cubic-bezier(0.37, 0.82, 0.2, 1);
}
body .container .content label:nth-of-type(3), body .container .content input:nth-of-type(3), body .container .content .more:nth-of-type(3) {
  -moz-animation: slideIn 0.6s cubic-bezier(0.37, 0.82, 0.2, 1);
  -webkit-animation: slideIn 0.6s cubic-bezier(0.37, 0.82, 0.2, 1);
  animation: slideIn 0.6s cubic-bezier(0.37, 0.82, 0.2, 1);
}
body .container .content label {
  font-size: 12px;
  color: #263238;
  -moz-user-select: -moz-none;
  -ms-user-select: none;
  -webkit-user-select: none;
  user-select: none;
}
body .container .content label:not([for='remember']) {
  display: none;
}
body .container .content input.inpt {
  font-size: 14px;
  display: block;
  width: 100%;
  height: 42px;
  margin-bottom: 12px;
  padding: 16px 13px;
  color: #999999;
  border: 1px solid #d9d9d9;
  background: transparent;
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
}
body .container .content input.inpt::-webkit-input-placeholder {
  font-size: 14px;
  color: #999999;
  font-family: 'Lato', sans-serif;
}
body .container .content input.inpt:-moz-placeholder {
  font-size: 14px;
  color: #999999;
  font-family: 'Lato', sans-serif;
}
body .container .content input.inpt::-moz-placeholder {
  font-size: 14px;
  color: #999999;
  font-family: 'Lato', sans-serif;
}
body .container .content input.inpt:-ms-input-placeholder {
  font-size: 14px;
  color: #999999;
  font-family: 'Lato', sans-serif;
}
body .container .content input.inpt:focus {
  border-color: #999999;
}
body .container .content input.submit {

  font-size: 12px;
  line-height: 42px;
  display: block;
  width: 100%;
  height: 42px;
  cursor: pointer;
  vertical-align: middle;
  letter-spacing: 2px;
  text-transform: uppercase;
  color: #263238;
  border: 1px solid #263238;
  background: transparent;
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
}
body .container .content input.submit:hover {
  background-color: #263238;
  color: #ffffff;
  -moz-transition: all 0.2s;
  -o-transition: all 0.2s;
  -webkit-transition: all 0.2s;
  transition: all 0.2s;
}
body .container .content input:focus {
  outline: none;
}
body .container .content .checkbox {
  margin-top: 4px;
  overflow: hidden;
  clip: rect(0 0 0 0);
  width: 0;
  height: 0;
  margin: 17px -1px;
  padding: 0;
  border: 0;
}
body .container .content .checkbox + label {
  vertical-align: middle;
  display: inline-block;
  width: 50%;
}
body .container .content .checkbox + label:before {
  content: "\A";
  color: #999999;
  font-family: Verdana;
  font-weight: bold;
  font-size: 8px;
  line-height: 10px;
  text-align: center;
  display: inline-block;
  vertical-align: middle;
  position: relative;
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
  background: transparent;
  border: 1px solid #d9d9d9;
  width: 11px;
  height: 11px;
  margin: -2px 8px 0 0;
}
body .container .content .checkbox:checked + label:before {
  content: "✓";
}
body .container .content .submit-wrap {
  position: none;
  bottom: 0;
  width: 100%;
}
body .container .content .submit-wrap a {
  font-size: 12px;
  display: block;
  margin-top: 20px;
  text-align: center;
  text-decoration: none;
  color: #999999;
}
body .container .content .submit-wrap a:hover {
  text-decoration: underline;
}
body .container .content .signup-cont {
  display: none;
}

@keyframes slideIn {
  0% {
    filter: progid:DXImageTransform.Microsoft.Alpha(Opacity=0);
    opacity: 0;
    margin-left: -320px;
  }
  100% {
    filter: progid:DXImageTransform.Microsoft.Alpha(enabled=false);
    opacity: 1;
    margin-left: 0px;
  }
}
@-webkit-keyframes slideIn {
  0% {
    filter: progid:DXImageTransform.Microsoft.Alpha(Opacity=0);
    opacity: 0;
    margin-left: -320px;
  }
  100% {
    filter: progid:DXImageTransform.Microsoft.Alpha(enabled=false);
    opacity: 1;
    margin-left: 0px;
  }
}
.credits {
  display: block;
  position: absolute;
  right: 0;
  bottom: 0;
  color: #999999;
  font-size: 14px;
  margin: 0 10px 10px 0;
}
.credits a {
  filter: progid:DXImageTransform.Microsoft.Alpha(Opacity=80);
  opacity: 0.8;
  color: inherit;
  font-weight: 700;
  text-decoration: none;
}

</style>
</head>
<body>

<section class="container">
		    <article class="half">
			        <h1>감성수학</h1>
			        <div class="tabs">
				            <span class="tab signin active"><a href="#signin">로그인</a></span>
				            <span class="tab signup"><a href="#signup">회원가입</a></span>
			        </div>
			        <div class="content">
				            <div class="signin-cont cont">
					                <form action="/math/login" method="post">
						                    <input type="email" name="email" id="email" class="inpt" required="required" placeholder="이메일 주소">
						                    <label for="email">Your email</label>
						                    <input type="password" name="password" id="password" class="inpt" required="required" placeholder="비밀번호">
                						    <label for="password">Your password</label>
                						    <input type="checkbox" id="keeplogin" name="keeplogin" value="yes" class="checkbox" checked>
						                    <label for="keeplogin">자동로그인</label>
						                    <div class="submit-wrap">
							                        <input type="submit" value="로그인 " class="submit">
							                        <a id="searchid" class="more">Forgot your Email?</a>
						                    </div>
        					        </form>
    				        </div>
    				        <div class="signup-cont cont">
                <form action="/math/mjoin" method="post">
						                    <input type="email" name="email2" id="email2" class="inpt" required="required" placeholder="이메일 주소">
						                    <div class="submit-wrap">
						                    <input type="button" onclick="return checkId();" class="submit" value="중복확인">
						                    </div><br>
						                    <label for="name">Your name</label>
						                    <input type="password" name="password2" id="password2" class="inpt" required="required" placeholder="비밀번호">
						                    <label for="password">Your password</label>
						                    <input type="password" class="inpt" id="password3"required="required" placeholder="비밀번호확인">
                						    <label for="password">Your password</label>
                    						<input type="text" name="username" id="username" class="inpt" required="required" placeholder="이름">
						                    <label for="email">Your email</label>
						                    <input type="tel" name="phone" id="phone" class="inpt" required="required" placeholder="휴대폰번호">
						                    <label for="phone">Your phone</label>

						                    <div class="submit-wrap">
							                        <input type="submit" value="회원가입" class="submit">
							                        <a id="tac" class="more">Terms and conditions</a>
							                        
						                    </div>
        					        </form>
        					       
            </div>
			        </div>
		    </article>
		    <div class="half bg"></div>
	</section>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> 
<script type="text/javascript">
$('.tabs .tab').click(function(){
    if ($(this).hasClass('signin')) {
        $('.tabs .tab').removeClass('active');
        $(this).addClass('active');
        $('.cont').hide();
        $('.signin-cont').show();
    } 
    if ($(this).hasClass('signup')) {
        $('.tabs .tab').removeClass('active');
        $(this).addClass('active');
        $('.cont').hide();
        $('.signup-cont').show();
    }
});
$('.container .bg').mousemove(function(e){
    var amountMovedX = (e.pageX * -1 / 30);
    var amountMovedY = (e.pageY * -1 / 9);
    $(this).css('background-position', amountMovedX + 'px ' + amountMovedY + 'px');
});
</script>
</body>
</html>