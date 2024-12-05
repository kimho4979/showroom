<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>



<meta charset="UTF-8">
<meta name="Author" content="">
<meta name="Keywords" content="">
<meta name="Description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<script type="text/javascript">
var contextPath = '${pageContext.request.contextPath}';
</script>
<!-- CSS(S) -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/reset.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sub.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery-ui.css"> <!-- datepicker style -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/timepicker.css"> <!-- timepicker style -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/swiper.css"> <!-- swiper style -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/fullcalendar-5.10.1-main.css"> <!-- fullcalendar css -->
<!-- CSS(E) -->

<!--[if IE 9]>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/matchMedia.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/matchMedia.addListener.js"></script>
<![endif]-->


<!-- SCRIPT(S) -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.js"></script> <!-- datepicker js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/timepicker.min.js"></script> <!-- timepicker js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cycle.all.js"></script> <!-- slider -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.mask.js"></script> <!-- mask -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/swiper.js"></script><!-- swiper -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/stat.js"></script><!-- swiper -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fullcalendar-5.10.1-main.js"></script> <!-- fullcalendar js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fullcalendar-5.10.1-locales-all.js"></script> <!-- fullcalendar 언어 설정관련 script -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/moment.min.js"></script> <!-- daterangepicker js -->
<script type="text/javascript" src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
    if ( window.history.replaceState ) {
        window.history.replaceState( null, null, window.location.href );
    }
</script>
<!-- SCRIPT(E) -->


<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="https://developers.kakao.com/sdk/js/kakao.js"></script>

<!-- Global site tag (gtag.js) - Google Analytics -->	
<script async src="https://www.googletagmanager.com/gtag/js?id=G-PETK6C5D1G"></script>
<script>
	  window.dataLayer = window.dataLayer || [];
	  function gtag(){dataLayer.push(arguments);}
	  gtag('js', new Date());
	
	  gtag('config', 'G-PETK6C5D1G');
	  
	  
</script>	

