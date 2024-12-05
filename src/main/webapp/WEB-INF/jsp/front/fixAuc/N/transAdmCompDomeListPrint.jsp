<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt" %>



<meta charset="UTF-8">
<meta name="Author" content="">
<meta name="Keywords" content="">
<meta name="Description" content="">
<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=10,chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>화훼온라인매매시스템</title>
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
<!-- CSS(E) -->
<style>
@page { margin:5mm 10mm 5mm 10mm; }

@media print {
	html, body {
           border: 1px solid white;
           height: 99%;
           page-break-after: avoid;
           page-break-before: avoid;
    }
	table th {background-color: #666699 !important; color:#ffffff !important; -webkit-print-color-adjust:exact; }
	table td {height: 20px !important; padding-bottom: 2px !important; padding-top: 2px !important}
}

thead {border-bottom: 2px solid #dcdcdc !important;}

.sub_tit_04 {
	font-size: 25px;
    color: #232323;
    line-height: 26px;
}

.fn {
	text-align: center;
}
</style>
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

<!-- SCRIPT(E) -->

<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="https://developers.kakao.com/sdk/js/kakao.js"></script>

 
<script type="text/javascript">

	var initBody;
	var printChulCode;
	var printStartDate;
	var printEndDate;
	
	function beforePrint()
	{ 
		/*
		initBody = document.body.innerHTML; 
		document.body.innerHTML = print_page.innerHTML;*/
	} 
	
	function afterPrint()
	{ 
		//document.body.innerHTML = initBody; 
		var agent = navigator.userAgent.toLowerCase();
		if (agent.indexOf("chrome") != -1) {
			window.close();	
		}
		
	} 
	
	function pageprint()
	{
		
		window.onbeforeprint = beforePrint; 
		window.onafterprint = afterPrint; 
		window.print(); 
	}
	
	function get_day(chuldate){
		var week = new Array('일', '월', '화', '수', '목', '금', '토');
	    
	    var today = new Date(chuldate).getDay();
	    var todayLabel = week[today];
	    
	    return todayLabel;
	}
	
	$(document).ready(function(){
		var chulDate = $("#chulDate").val();
		
		var html = chulDate.substring(0,4)+"년 "+chulDate.substring(5,7)+"월 "+chulDate.substring(8,10)+"일("+get_day(chulDate)+") 정가수의매매 최종내역";
		$("#title").html(html);
		pageprint();
		
	});

</script>
<body>

<input type="hidden" id="chulDate" value="<c:out value="${paramMap.chulDate}"/>"/>

		<!-- 타이틀(S) -->
		<div class="title_box mt30">
			<div class="fn">
				<h4 class="sub_tit_04" id="title"></h4>
			</div>
			
		</div>
		<!-- 타이틀(E) -->
		
		<!-- WEB테이블(S) -->
		<div class="table_type_01 mt10">
			<table id="fixTable">
				<caption>info</caption>
				<colgroup>
					<col width="97px">
					<col width="106px">
					<col width="117px">
					<col width="132px">
					<col width="52px">
					<col width="52px">
					<col width="52px">
					<col width="68px">
				</colgroup>
				<thead>
					<tr>
						<th>출하자</th>
						<th>코드번호</th>
						<th>품목</th>
						<th>품종</th>
						<th>등급</th>
						<th>상자</th>
						<th>속수</th>
						<th>중매인</th>
					</tr>
				</thead>
				<tbody id="resultWebList">
					<c:forEach items="${resultList}" var="result" varStatus="status">
						<input type="hidden" name="tranSeq" value="${result.tranSeq}"/>
						<tr id="fixTr${result.tranSeq}">
							<td class="tc"><p class="txt_01">${result.chulName}</p></td>
							<td class="tc"><p class="txt_01">${result.chulCode}</p></td>
							<td class="tc"><p class="txt_01">${result.pumMok}</p></td>
							<td class="tc"><p class="txt_01">${result.pumJong}</p></td>
							<td class="tc"><p class="txt_01">${result.grade}</p></td>
							<td class="tc"><p class="txt_01"><fmt:formatNumber value="${result.boxCnt}" pattern="#,###" /></p></td>
							<td class="tc"><p class="txt_01"><fmt:formatNumber value="${result.sokCnt}" pattern="#,###" /></p></td>
							<td class="tc"><p class="txt_01">${result.domeCode}</p></td>
						</tr>
					</c:forEach>
					<c:if test="${fn:length(resultList) eq 0 }">
						<tr>
							<td class="tc" colspan="11">
								데이터가 없습니다.
							</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>
		<!-- WEB테이블(E) -->
</body>
