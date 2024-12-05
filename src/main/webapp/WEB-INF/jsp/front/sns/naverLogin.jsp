<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>   
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript">
  var naver_id_login = new naver_id_login("8_piJLvcWuBQtaB4D9ms", "http://localhost/yfmc/naverLogin.do");
  // 접근 토큰 값 출력
  //alert(naver_id_login.oauthParams.access_token);
  // 네이버 사용자 프로필 조회
  naver_id_login.get_naver_userprofile("naverSignInCallback()");
  // 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
  function naverSignInCallback() {
	console.log(naver_id_login);
	$("#naverId").val(naver_id_login.getProfileData('id'));
    $("#email").val(naver_id_login.getProfileData('email'));
    $("#name").val(naver_id_login.getProfileData('name'));
    $("#snsForm").submit();
  }
</script>

<form id="snsForm" name="snsForm" method="POST" action="${pageContext.request.contextPath}/naverLoginProc.do">
	<input type="hidden" id="naverId" name="naverId" value="">
	<input type="hidden" id="email" name="email" value="">
	<input type="hidden" id="name" name="name" value="">
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
  