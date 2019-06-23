<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    /* String value = request.getParameter("items"); */
    request.setCharacterEncoding("utf-8");
	String strArr[] = request.getParameterValues("img");
	
   /*  String[] strArr = value.split(",");  */
    
    int page2 = (strArr.length - 6) / 6;
    int img=6;
     
    String title = request.getParameter("title");
    String waterMark = request.getParameter("waterMark"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>감성수학</title>

<!-- css -->
	<link href="/math/resources/assets/css/question/sixPreviewTitle.css" rel="stylesheet" />
	
<script type="text/javascript" src="/math/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
   <%-- $(function(){
      $("div.paper").on("click",function(){
         var index = $(this).index() - 1;
         $("div.paper:gt(" + index + ")").remove();
         
         $("div#one").html($("div#one").html() + $("div#one").html("zzzzzzzzzzzz"));
         var $h1 = $('<% for(int i=0; i<2; i++){out.println("<h1>hi</h1><br>"); } %>');
         $("div.paper").eq(index).append($h1);
      });

   }); --%>
   window.onload = function(){
   	$(document).bind("contextmenu",function(){   return false;}); //우클릭방지
   	$(document).bind('selectstart',function() {return false;}); //선택방지
   	$(document).bind('dragstart',function(){return false;}); //드래그방지	
   }
   var initBody;
          function beforePrint()
          {
              initBody = document.body.innerHTML;
              document.body.innerHTML = one.innerHTML;
          }
          function afterPrint()
          {
              document.body.innerHTML = initBody;
          }
          function pageprint()
          {
              window.onbeforeprint = beforePrint;
              window.onafterprint = afterPrint;
              window.print();
          }


</script>
<style type="text/css">
</style>

</head>
<body>
<button id="pr"  onclick="pageprint();" style="width: 90px; height: 70px;">인쇄하기</button>

<div id="one">
<div class="paper">
   <div class="content abs-center">
      <table>
         <tr><td rowspan="2" width="70%" class="td-a"><%= title %></td><td width="10%" height="40%" class="td-b">채점일</td><td width="10%" class="td-c">채점자</td><td width="10%" class="td-d">점수</td></tr>
         <tr><td class="td-b-css"></td><td class="td-c-css"></td><td class="td-d-css"></td></tr>
      </table>
<% 
for(int i=0; i<6; i++){
   out.println("<div class='main'><img src='"+strArr[i]+"' width='100%'></div>");
   if(i == strArr.length - 1 && (1 == strArr.length % 6)){
       out.println("<div class='p'></div>"); 
       out.println("<div class='p'></div>");
       out.println("<div class='p'></div>");
       out.println("<div class='p'></div>");
       out.println("<div class='p'></div>");
       break;
    }else if(i == strArr.length - 1 && (2 == strArr.length % 6)){
       out.println("<div class='p'></div>"); 
       out.println("<div class='p'></div>");
       out.println("<div class='p'></div>");
       out.println("<div class='p'></div>");
       break;
    }else if(i == strArr.length - 1 && (3 == strArr.length % 6)){
       out.println("<div class='p'></div>");
       out.println("<div class='p'></div>");
       out.println("<div class='p'></div>");
       break;
    }else if(i == strArr.length - 1 && (4 == strArr.length % 6)){
   	 out.println("<div class='p'></div>");
   	 out.println("<div class='p'></div>");
   	 break;
    }else if(i == strArr.length - 1 && (5 == strArr.length % 6)){
   	 out.println("<div class='p'></div>");
   	 break;
    }
}
%>
		<span id="absolutecenter" class="waterMark"><%= waterMark %></span>
   </div>
</div>
<%
if(strArr.length > 6){
   for(int i=0; i<=page2; i++){ 
   out.println("<div class='paper' id='" + i + "'>");
   out.println("<div class='content'>");
      for(int j=img; j<img+6; j++){
         out.println("<div class='p'> <img src='" + strArr[j] + "' width='100%'></div>");
         if(j == strArr.length - 1 && (1 == strArr.length % 6)){
            out.println("<div class='p'></div>"); 
            out.println("<div class='p'></div>");
            out.println("<div class='p'></div>");
            out.println("<div class='p'></div>");
            out.println("<div class='p'></div>");
            break;
         }else if(j == strArr.length - 1 && (2 == strArr.length % 6)){
            out.println("<div class='p'></div>"); 
            out.println("<div class='p'></div>");
            out.println("<div class='p'></div>");
            out.println("<div class='p'></div>");
            break;
         }else if(j == strArr.length - 1 && (3 == strArr.length % 6)){
            out.println("<div class='p'></div>");
            out.println("<div class='p'></div>");
            out.println("<div class='p'></div>");
            break;
         }else if(j == strArr.length - 1 && (4 == strArr.length % 6)){
        	 out.println("<div class='p'></div>");
        	 out.println("<div class='p'></div>");
        	 break;
         }else if(j == strArr.length - 1 && (5 == strArr.length % 6)){
        	 out.println("<div class='p'></div>");
        	 break;
         }
      }
   img = img + 6;
   out.println("<span id='absolutecenter' class='waterMark'>" + waterMark + "</span>");
   out.println("</div>");
   out.println("</div>"); 
   if(img == strArr.length)
      break;
   } 
}
%>
    <!-- <div class="paper">
        <div class="content">Page 2</div>    
    </div> -->

</div>

</body>
</html>