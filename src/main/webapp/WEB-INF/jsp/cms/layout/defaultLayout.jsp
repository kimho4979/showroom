<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>

<head>
	<jsp:include page="/WEB-INF/jsp/cms/inc/header.jsp"/>
</head>

<body>
<style>
<!-- 로딩 이미지 -->
.overlay{	
  position: fixed;
  top: 0;
  z-index: 100;
  width: 100%;
  height:100%;
  display: none;
  background: rgba(0,0,0,0.6);
}
.cv-spinner {
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;  
}
.spinner {
  width: 40px;
  height: 40px;
  border: 4px #ddd solid;
  border-top: 4px #2e93e6 solid;
  border-radius: 50%;
  animation: sp-anime 0.8s infinite linear;
}
@keyframes sp-anime {
  100% { 
    transform: rotate(360deg); 
  }
}
.is-hide{
  display:none;
}
</style>

    <div id="wrapper">
        <!-- 메뉴 영역 -->
        <jsp:include page="/WEB-INF/jsp/cms/inc/menu.jsp"/>
        <!-- 메뉴 영역 종료 -->
        <!-- 컨텐츠 영역 -->
        
        <jsp:include page="/WEB-INF/jsp/cms/${contentPath}"/>
		<!-- 컨텐츠 영역 종료-->
    </div>
    <div class="overlay" style="display:none;position: fixed;top: 0;z-index: 100;width: 100%;height:100%;background: rgba(0,0,0,0.6);">
        <div class="cv-spinner">
            <span class="spinner"></span>
        </div>
    </div>
</body>
</html>

