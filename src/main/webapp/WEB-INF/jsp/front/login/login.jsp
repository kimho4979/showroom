<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <title>로그인 - 양재꽃시장</title>

  <div class="sub_conts" id="contents" tabindex="0">
		<div class="sub_conts_in">
			<div class="log_box">
				<h2 class="log_tit">회원 로그인</h2>
				<span class="log_line"></span>
				<p class="log_tit_sub">aT홈페이지 가입자는 통합 아이디로 로그인이 가능합니다.</p>
				<p class="log_tit_sub">중도매인, 출하자, 경매사는 통합회원 회원가입 후 이용가능합니다.</p>
				
				<input type="hidden" id="message" value="${message}">
				
				<h4 class="log_tit_02 mt40">아이디</h4>
				<div class="log_ip mt10">
					<input type="text" id="logId" placeholder="아이디를 입력해주세요." onkeydown="fn_enter();" title="아이디 입력"><label for="logId"></label>
				</div>
				<h4 class="log_tit_02 mt30">비밀번호</h4>
				<div class="log_ip mt10">
					<input type="password" id="logPw" placeholder="비밀번호를 입력해주세요." onkeydown="fn_enter();" title="비밀번호 입력"><label for="logPw"></label>
				</div>
				<a href="javascript:atLogin2();" class="btn_login mt25">로그인</a>

				<div class="btn_log_box mt20">
					<a href="https://member.at.or.kr/customer/m100002/memberForm.action" target="_blank" title="회원가입 (새 창으로 이동)">회원가입</a>
					<a href="https://member.at.or.kr/namecheck/confirmIdSerach.action?retUrl=https://member.at.or.kr/customer/m100002/idSearch.action&menuNm=%EB%A1%9C%EA%B7%B8%EC%9D%B8&confirmType=2" target="_blank" title="아이디 찾기 (새 창으로 이동)">아이디 찾기</a>
					<a href="https://member.at.or.kr/namecheck/confirmIdSerach.action?retUrl=https://member.at.or.kr/customer/m100002/password.action&menuNm=%EB%A1%9C%EA%B7%B8%EC%9D%B8&confirmType=2" target="_blank" title="비밀번호 찾기 (새 창으로 이동)">비밀번호 찾기</a>
				</div>

				<h2 class="log_tit mt60">SNS 간편인증</h2>
				<span class="log_line"></span>

				<div class="btn_sns_box" style="height:73px;">
					<div style="left:45px;" href="#" class="btn_sns naver" id="naver_id_login" title="네이버 로그인 (새 창으로 이동)"><img src="${pageContext.request.contextPath}/img/icon_logo_naver.png" alt=""></div>
					<a style="position:absolute; right:45px; top:0;" href="javascript:loginWithKakao();" class="btn_sns kakao" title="카카오 로그인 (새 창으로 이동)"><img src="${pageContext.request.contextPath}/img/icon_logo_kakao.png" alt=""></a>
					<!-- <a href="javascript:faceLogin();" class="btn_sns fb"><img src="${pageContext.request.contextPath}/img/icon_logo_fb.png" alt="페이스북로그인"></a> -->
				</div>
	<!-- 25.03 2.4 -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.1.1/core.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.1.1/sha256.js"></script>				
	
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/sns.js"></script>
				 <script type="text/javascript">







				 $( document ).ready(function() {
		        	   var message = $("#message").val();
		        	   if(message != null && message != ""){
		        		   alert(message);
		        	   }
		        	   
		        	   naverLoginInit();
		        	   
		          });
				 
				 function fn_enter(){
					 if (window.event.keyCode == 13) {
						 atLogin2();
			        }
				 }
				 </script> 
				
			</div>
		</div>
	</div>