<%@page import="admin.model.vo.Semester"%>
<%@page import="java.util.ArrayList"%>
<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% Member member = (Member)request.getAttribute("member");
	ArrayList<Semester> slist = (ArrayList<Semester>)request.getAttribute("semester");
	ArrayList<Semester> mylist = (ArrayList<Semester>)request.getAttribute("permission");
	int pages = ((Integer)request.getAttribute("page")).intValue();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리 : 감성수학</title>
<script type="text/javascript" src="/math/resources/js/jquery-3.3.1.min.js"></script>
 <script type="text/javascript">
 window.onload = function(){
 	$(document).bind("contextmenu",function(){   return false;}); //우클릭방지
 	$(document).bind('selectstart',function() {return false;}); //선택방지
 	$(document).bind('dragstart',function(){return false;}); //드래그방지	
 }
 
function backPage() {
	location.href="/math/mmanager?page="+<%=pages%>;
}
function changePwd() {
	var pwd1 = $("#password").val();
	var pwd2 = $("#password1").val();
	
	if(pwd1 == null || pwd1 == ""){
		alert("비밀번호를 입력하셔야 수정이 가능합니다.");
		$("#password").focus();
		return false;
	}else if(pwd2 == null || pwd2 == ""){
		alert("비밀번호 확인을 입력하셔야 수정이 가능합니다.");
		$("#password1").focus();
		return false;
	}	
	
	if(pwd1 != pwd2){
		alert("암호와 암호 확인이 일치하지 않습니다.\n"
				+"다시 입력하십시요.");
		$("#password").select();
	}else{
		if(confirm("적용하시겠습니까?")){
		$.ajax({
			url: "/math/mpwdchange",
			type: "post",
			data: {password : $("#password").val(),
				userid: $("#userid").val()},
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
};

function sendmail(){
	if(confirm("적용하시겠습니까?")){
	$.ajax({
		url: "/math/sendemail",
		type: "post",
		data: {userid: $("#userid").val()},
		success: function(data) {
				if(data == "ok"){
					alert("변경되였습니다.");
					window.location.reload();
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
	};
	
	var arr = new Array();
	function addPermission() {
		var per = $("#s1").val();	
		if(per=="모든권한"){
			arr = new Array();
			$("#d1").html(per);
		}else{
		var arr2 = new Array();
		var per2 = "";
		arr.push(per);
		arr2 =arr.slice().sort(function(a,b){return a - b}).reduce(function(a,b){if (a.slice(-1)[0] !== b) a.push(b);return a;},[]);
		for(var i= 0; i<arr2.length;i++){
			per2 += arr2[i]+" ";
			
		}
		$("#d1").html(per2); 
		}
		
	};
	
	
	function sendAddPer() {
		if(confirm("권한을 적용하시겠습니까?")){
			$.ajax({
				url: "/math/addper",
				type: "post",
				data: {userid : $("#userid").val(),
					    per: $("#d1").text()},
				
				success: function(data) {
						if(data == "ok"){
							alert("변경되였습니다.");
							window.location.reload();
						}else{
							alert("권한주기 오류 확인후 다시해주세요");
						}
				
				},
				error: function( jqXHR, textStatus, errorThrown) {
					console.log("error : "+  jqXHR +", "+textStatus+", "+errorThrown);
				}
		});
		}else{
			$("#d1").html("");
			return false;
		}
	};
	function remove1() {
		arr=Array();
		$("#d1").html("");
	}
	
	function allCheck() {
	      if($(":checkbox").prop("checked")==false){
	         $(":checkbox").prop("checked",true);
	      }else{
	         $(":checkbox").prop("checked",false);
	      }
	      
	   }
	function removePermission() {
		var items = "";
		$("input:checkbox[type=checkbox]:checked").each(function() {
			items+= $(this).val()+" ";
		});
		if(confirm("권한을 삭제하시겠습니까?")){

			console.log(items);
				$.ajax({
					url: "/math/removeper",
					type: "post",
					
					data: {userid : $("#userid").val(),
						    per: items },
					success: function(data) {
							if(data == "ok"){
								alert("변경되였습니다.");
								window.location.reload();
							}else{
								alert("권한주기 오류 확인후 다시해주세요");
							}
					
					},
					error: function( jqXHR, textStatus, errorThrown) {
						console.log("error : "+  jqXHR +", "+textStatus+", "+errorThrown);
					}
			});
			} else {
				return false;
			}
		}
	</script>
</head>
<body>
<%@ include file="../common/Adminheader.jsp" %>
<div class="content">
        <div class="container-fluid">
          <div class="card">
            <div class="card-header card-header-primary">
              <h4 class="card-title">회원정보</h4>
              <p class="card-category">회원정보수정</p>
            </div>
            <div class="card-body">
              <div id="typography">
                <div class="card-title">
<table class="table table-striped table-hover">
<tr></tr>
<tr><td>회원 아이디 </td><th><input type="text" id="userid"  value="<%=member.getUserId()%>" readonly="readonly" class="form-control"></th></tr>
<tr><td>회원 이름</td><th><input type="text" value="<%=member.getUserName() %>" class="form-control" readonly></th></tr>
<tr><td>전화번호</td><th><input type="text" value="<%=member.getPhone() %>" class="form-control" readonly></th></tr>
<tr><td>비밀번호</td><th><input type="password" name="password" id="password" id="userid" value="<%=member.getUserPwd() %>" class="form-control"></th></tr>
<tr><td>비밀번호 확인</td><th><input type="password" name="password" id="password1" id="userid" value="<%=member.getUserPwd() %>" class="form-control"></th></tr>
<tr><td colspan="2" align="center"><button onclick="changePwd();" class="btn btn-default">변경</button>&nbsp;&nbsp;<button onclick="sendmail();" class="btn btn-default">비밀번호 초기화</button></td></tr>
<tr><td>가입일</td><th><%=member.getRegistDate() %></th></tr>
<tr><td>최종 수정일</td><th><%=member.getLastModified() %></th></tr>
<tr><td>권한 주기</td>
<th>
<Select id="s1" style="width:200px;height:40px; float:left;" class="form-control">
<div class="container">
	<option selected="selected" >모든권한</option>
	<%for(Semester s : slist){ %>
	<option><%=s.getSemesterName() %></option>
	<% } %>
	</div>
</Select>
<button onclick="addPermission();" class="btn btn-default" style="margin:10px;">권한추가</button>
</th></tr>
<tr><th><div id="d1"></div></th><th><button onclick="sendAddPer();" class="btn btn-default">권한 주기</button><button onclick="remove1();" class="btn btn-default">선택한 권한지우기</button></th></tr>
<tr><td>보유 권한</td><td>
<%for(int i =0 ; i<mylist.size();i++){%>
<input type="checkbox" id="ckd" value="<%=mylist.get(i).getSemesterName()%>" name="chk"><%=mylist.get(i).getSemesterName() %></input>&nbsp;&nbsp;
<%if((i+1)%4==0){
	out.print("<br>");
}}
%>
</td><th>
<tr><td colspan="2">
<button onclick="allCheck();" class="btn btn-default">전체선택</button>&nbsp;&nbsp;
<button onclick="removePermission();" class="btn btn-default">권한삭제</button>
</td></tr>
</table>
<div align="center" id="d1"><button onclick="backPage();" class="btn btn-default btn-sm">목록으로</button></div>
</div>
</div>
</div>
</div>
</div>
</div>
<%@ include file="../common/footer.jsp" %>
</body>
</html>