<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>   
    
<script type="text/javascript">
$( document ).ready(function() {
	var opener = null
	 if (window.opener){
        opener = window.opener;
     }else {   //IE11
     	opener = window.open('','login');
     }
	 
    opener.location.href = "${pageContext.request.contextPath}/front/mypage.do";
    window.open('', '_self', '');
    window.close();
    //self.close();
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
    
 
  