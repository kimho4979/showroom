<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<input type="hidden" id="menuNm" value="${menuVO.menuNm}" />

<!-- sub상단(S) -->
			<div class="sub_top">
				<h2 class="sub_title">
					·${menuVO.menuNm}·
				</h2>
				
				<jsp:include page="/WEB-INF/jsp/front/inc/nav.jsp"></jsp:include>
			</div>
			<!-- sub상단(E) -->
	
    
    

