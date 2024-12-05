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
	<!-- warp(S) -->
	<div class="wrap">
		<!-- header(S) -->
		<jsp:include page="/front/inc/menuList.do"/>
		<!-- header(E) -->

		<!-- main-conts(S) -->
		<div class="sub_conts bgw">
			<jsp:include page="/WEB-INF/jsp/front/inc/fixLocation.jsp"/>
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
		<a href="#!" class="btn_scroll_top">TOP</a>
		<!-- 탑버튼(E) -->
	<!-- 20200825 추가(E) -->	
</body>
</html>
