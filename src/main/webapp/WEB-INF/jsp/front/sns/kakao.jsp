<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>   
    
<script type="text/javascript">
$( document ).ready(function() {
	 
    window.location.href = "${pageContext.request.contextPath}/front/mypage.do";
    
});
</script>

<input type="hidden" id="message" value="${message}">

<script type="text/javascript">
	 $( document ).ready(function() {
    	   var message = $("#message").val();
    	   if(message != null && message != ""){
    		   alert(message);
    	   }
    	   
      });
 </script> 
    
 
  