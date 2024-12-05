<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>



<!doctype html>
<html lang="ko">
<head>
<jsp:include page="/WEB-INF/jsp/front/inc/header.jsp"/>
</head>

<body> 
	<!-- 본문바로가기(S) -->
	<div class="skip">
        <a href="#contents">본문 바로가기</a>
	</div>
	<!-- 본문바로가기(E) -->
	
	<!-- warp(S) -->
	<div class="wrap">
		<!-- header(S) -->
		<jsp:include page="/front/inc/menuList.do"/>
		<!-- header(E) -->

		<!-- main-conts(S) -->
		<div class="sub_conts bgw" id="contents" tabindex="0">
			<jsp:include page="/front/inc/menuInfo.do?menuId=${paramMap.menuId}"/>
			<jsp:include page="/WEB-INF/jsp/front/${contentPath}"/>
		</div>
		
		<!-- main-conts(E) -->

		<!-- footer(S) -->
		<jsp:include page="/WEB-INF/jsp/front/inc/footer.jsp"/>
		<!-- footer(E) -->

		
		<!-- LNB(S) -->
		<jsp:include page="/front/inc/lnbMenuList.do"/>
		  <!-- LNB(E) -->

	</div>
	<!-- warp(E) -->	
	<!-- 20200825 추가(S) -->	
		<!-- 탑버튼(S) -->
		<a href="#!" class="btn_scroll_top" title="위로가기">TOP</a>
		<!-- 탑버튼(E) -->
	<!-- 20200825 추가(E) -->	
	<script>
		if(document.getElementsByClassName("sel-festa").length > 0){
			document.getElementsByClassName("sel-festa")[0].onchange = function( a ) { 
				location.href = a.target.value;
			}	
		}
		
	</script>	
</body>
</html>
