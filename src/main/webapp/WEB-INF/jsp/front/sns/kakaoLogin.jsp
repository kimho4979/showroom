<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="https://developers.kakao.com/sdk/js/kakao.js"></script>   
<script type="text/javascript">
	
	Kakao.init('4ba5ba209cdc1e5e8bed585bd89319da');
	
	Kakao.Auth.setAccessToken('${access_token}');
	
	 $( document ).ready(function() {
		 console.log(1111);
		Kakao.API.request({
		    url: '/v2/user/me',
		    success: function(res) {
		    	$("#kakaoId").val(res.id);
		        $("#name").val(res.properties.nickname);
		        $("#snsForm").submit();
		    },
		    fail: function(error) {
		      alert(
		        'login success, but failed to request user information: ' +
		          JSON.stringify(error)
		      );
		      console.log(error);
		    },
		  })
	 });
  
</script>

<form id="snsForm" name="snsForm" method="POST" action="${pageContext.request.contextPath}/kakaoLoginProc.do">
	<input type="hidden" id="kakaoId" name="kakaoId" value="">
	<input type="hidden" id="email" name="email" value="">
	<input type="hidden" id="name" name="name" value="">
	<input type="hidden" id="access_token" name="access_token" value="${access_token}">
</form>
 
<script type="text/javascript">
/*
	$(document).ready(function(){
		$.ajax({
			data:{
				//checkedArray: checkedArray,
				//aucDateArray: aucDateArray
		        },
	        type : "GET",
	        dataType: 'json',
	        url : "${tokenVO.tokenReqUrl}",
	        success : function(data){
	             console.log(data);
	        }
	    });
	});*/
</script>
 <!-- 
  String responseBody = "";
		Map<String, String> requestHeaders = new HashMap<String, String>();
		try {
			
			String code = String.valueOf(requestparam.get("code"));
			String state = String.valueOf(requestparam.get("state"));
			
			String tokenReqUrl = "https://nid.naver.com/oauth2.0/token?client_id="+clientId+"&client_secret="+clientSecret+"&grant_type=authorization_code&state="+state+"&code="+code;
			responseBody = NaverMemberProfile.get(tokenReqUrl,requestHeaders);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		 JSONParser parser = new JSONParser();
         JSONObject obj = null;
		
         try {
              obj = (JSONObject)parser.parse(responseBody);
         } catch (Exception e) {
              e.printStackTrace();
         }
         
        
		String token = String.valueOf(obj.get("access_token")); // 네이버 로그인 접근 토큰;
        String header = "Bearer " + token; // Bearer 다음에 공백 추가

        String apiURL = "https://openapi.naver.com/v1/nid/me?client_id="+clientId+"&client_secret="+clientSecret;
        
        requestHeaders.put("Authorization", header);
        responseBody = NaverMemberProfile.get(apiURL,requestHeaders);
 
        try {
            obj = (JSONObject)parser.parse(responseBody);
       } catch (Exception e) {
            e.printStackTrace();
       }
        
        responseBody = String.valueOf(obj.get("response"));
        
        try {
            obj = (JSONObject)parser.parse(responseBody);
       } catch (Exception e) {
            e.printStackTrace();
       }
  -->
  