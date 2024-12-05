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

function fn_save(){
	
	var tranSeqArray = new Array();
	var bigoArray = new Array();
	
	$("input[name=tranSeq]").each(function(index){
		var tranSeqValue = this.value;
		tranSeqArray.push(tranSeqValue);
	});
	
	$("input[name=bigo]").each(function(index){
		var bigoValue = this.value;
		bigoArray.push(bigoValue);
	});
	
	
	$.ajax({
		data:{
			tranSeqArray: tranSeqArray,
			bigoArray: bigoArray
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixYAuc/transAdmCompListUpdate.json",
        success : function(data){
           	var result = data.result;
           	alert("성공적으로 저장하였습니다.");
        	window.location.reload(true);
        }
    });
}

function fn_ha(){
	var agent = navigator.userAgent.toLowerCase();
	
	if (agent.indexOf("chrome") == -1) {
		alert('Chrom 외 브라우져에서 가로인쇄가 지원되지않습니다. Chrom브라우져를 이용하세요.');
		return;
	}
	
	
	var chulDate = $("#chulDate").val();
	var url = contextPath + "/front/fixYAuc/transAdmCompChulListPrint.do?chulDate="+chulDate; 
	window.open(url,'하역반','width=1088px,height=680px,scrollbars=yes,resizable=no,toolbars=no,menubar=no');
}


function fn_board(){
	var chulDate = $("#chulDate").val();
	var url = contextPath + "/front/fixYAuc/transAdmCompDomeListPrint.do?chulDate="+chulDate; 
	window.open(url,'게시판','width=1088px,height=680px,scrollbars=yes,resizable=no,toolbars=no,menubar=no');
}


</script>

<input type="hidden" id="chulDate" value="<c:out value="${paramMap.chulDate}"/>"/>



		<!-- 타이틀(S) -->
		<div class="title_box mt30">
			<div class="fl">
				<h4 class="sub_tit_04"><c:out value="${paramMap.chulDate}"/> 정가수의매매 최종내역</h4>
			</div>
			<div class="fr">
				<a href="javascript:fn_save();" class="btn_type_01 gray ml10">저장</a>
				<a href="javascript:fn_ha();" class="btn_type_01 gray ml10">하역반</a>
				<a href="javascript:fn_board();" class="btn_type_01 gray ml10">게시판</a>
			</div>
			
		</div>
		<!-- 타이틀(E) -->
		
		<!-- WEB테이블(S) -->
		<div class="table_type_01 mt10">
			<table id="fixTable">
				<caption>info</caption>
				<colgroup>
					<col width="83px">
					<col width="97px">
					<col width="106px">
					<col width="137px">
					<col width="162px">
					<col width="52px">
					<col width="52px">
					<col width="52px">
					<col width="90px">
					<col width="68px">
					<col width="169px">
				</colgroup>
				<thead>
					<tr>
						<th>상장번호</th>
						<th>출하자</th>
						<th>코드번호</th>
						<th>품목</th>
						<th>품종</th>
						<th>등급</th>
						<th>상자</th>
						<th>속수</th>
						<th>운송</th>
						<th>중매인</th>
						<th>비고</th>
					</tr>
				</thead>
				<tbody id="resultWebList">
					<c:forEach items="${resultList}" var="result" varStatus="status">
						<input type="hidden" name="tranSeq" value="${result.tranSeq}"/>
						<tr id="fixTr${result.tranSeq}">
							<td class="tc"><p class="txt_01">${result.upNo}</p></td>
							<td class="tc"><p class="txt_01">${result.chulName}</p></td>
							<td class="tc"><p class="txt_01">${result.chulCode}</p></td>
							<td class="tc"><p class="txt_01">${result.pumMok}</p></td>
							<td class="tc"><p class="txt_01">${result.pumJong}</p></td>
							<td class="tc"><p class="txt_01">${result.grade}</p></td>
							<td class="tc"><p class="txt_01"><fmt:formatNumber value="${result.boxCnt}" pattern="#,###" /></p></td>
							<td class="tc"><p class="txt_01"><fmt:formatNumber value="${result.sokCnt}" pattern="#,###" /></p></td>
							<td class="tc"><p class="txt_01">${result.transName}</p></td>
							<td class="tc"><p class="txt_01">${result.domeCode}</p></td>
							<td class="tc">
								<div class="ip_type_01 w100p mt5">
									<input type="text" class="tl" name="bigo" value="${result.bigo}" maxlength="100">
								</div>
							</td>
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

