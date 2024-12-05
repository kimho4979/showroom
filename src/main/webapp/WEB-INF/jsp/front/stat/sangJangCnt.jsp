<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    
<link href="${pageContext.request.contextPath}/assets/css/plugins/select2/select2.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/plugins/select2/select2-bootstrap4.min.css" rel="stylesheet">
<!-- Select2 -->
<script src="${pageContext.request.contextPath}/assets/js/plugins/select2/select2.full.min.js"></script>
<style type="text/css">

.loadingWrap { display:none; }
.loadArea { position:fixed; display:table; left:0; top:0; width:100%; height:100%; background:#000; background-color:rgba(0,0,0,0.5); z-index:100; }
.loadArea .loading { display:table-cell; width:100%; height:100%; text-align:center; vertical-align:middle; }
.lds-dual-ring { display: inline-block; width: 80px; height: 80px; }
.lds-dual-ring:after { content: " "; display: block; width: 64px; height: 64px; margin: 8px; border-radius: 50%; border: 6px solid #fff; border-color: #fff transparent #fff transparent; animation: lds-dual-ring 1.2s linear infinite; }

@keyframes lds-dual-ring {
    0% { transform: rotate(0deg); }
    100% { transform: rotate(360deg); }
}

</style>

<script type="text/javascript">
		
	var currentDate = "";
		
	$(document).ready(function(){
		setSearchDate(0);
		currentDate = $("#endDate").val();
		getMokCode();
		$("#pumCode").select2({
			theme : 'bootstrap4',
			placeholder : "품종을 선택하세요.",
			allowClear : true,
			language : {
				noResults : function() {
					return "품목을 먼저 선택하세요.";
				},
				searching : function() {
					return '검색중…';
				},
				inputTooShort : function() {
					return "검색어를 입력하세요";
				}
			},
			width : '100%'
		});
		
		$("#btnReport").css("display", "none");
	});
	
	
	function setSearchDate(dayCnt){
		
		var endDate = new Date(); 
		var strDate = new Date();
		
		strDate.setDate(endDate.getDate()-dayCnt);
		
		var strYear = strDate.getFullYear(); 
		var strMonth = new String(strDate.getMonth()+1); 
		var strDay = new String(strDate.getDate()); 
		
		if(strMonth.length == 1){ 
			strMonth = "0" + strMonth; 
		} 
		
		if(strDay.length == 1){ 
			strDay = "0" + strDay; 
		} 
			
		$("#startDate").datepicker("setDate",strYear+"-"+strMonth+"-"+strDay);
		
		
		var endYear = endDate.getFullYear(); 
		var endMonth = new String(endDate.getMonth()+1); 
		var endDay = new String(endDate.getDate()); 
		
		if(endMonth.length == 1){ 
			endMonth = "0" + endMonth; 
		} 
		
		if(endDay.length == 1){ 
			endDay = "0" + endDay; 
		} 
			
		$("#endDate").datepicker("setDate",endYear+"-"+endMonth+"-"+endDay);
		
	}
	

	//조회
	function fn_search() {
		$(".loadingWrap").show();
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		var bunChk = $("input:radio[name=bunChk]:checked").val();
		var mokCode = $("#mokCode").prop("value");
		var pumCode = $("#pumCode").prop("value");
		var searchChul = $("#searchChul").val();

		$.ajax({
			data : {
				startDate : startDate,
				endDate : endDate,
				bunChk : bunChk,
				mokCode : mokCode,
				pumCode : pumCode,
				searchChul : searchChul
			},
			type : "POST",
			url : "${pageContext.request.contextPath}/front/stat/sangJangCntList.json",
			success : function(data) {

				var html = "";
				var fhtml = "";
				var resultList = data.resultList;
				var bunName = "";

				if (bunChk == 'N') {
					bunName = "절화";
				} else if (bunChk == 'Y') {
					bunName = "난";
				} else if (bunChk == 'C') {
					bunName = "관엽";
				}

				$("#bunName").html(bunName);
				$("#searchDate").html(startDate + " ~ " + endDate);

				if (resultList.length > 0) {

					for (var i = 0; i < resultList.length; i++) {

						var upDay = "";
						var upNo = "";
						var chulName = "";
						var pMokName = "";
						var pJongName = "";
						var boxCnt = "";
						var sokCnt = "";

						
						if (resultList[i].upNo != null) {
							upNo = resultList[i].upNo;
						}
						if (resultList[i].pMokName != null) {
							pMokName = resultList[i].pMokName;
						}
						if (resultList[i].pJongName != null) {
							pJongName = resultList[i].pJongName;
						}
						if (resultList[i].sokCnt != null) {
							sokCnt = resultList[i].sokCnt;
						}
						if (resultList[i].boxCnt != null) {
							boxCnt = resultList[i].boxCnt;
						}
						if (resultList[i].chulName != null) {
							chulName = resultList[i].chulName;
						}
						if (resultList[i].upDay != null) {
							upDay = resultList[i].upDay;
						}
						
						
						
						
						

						html += "<tr>                                                                   ";
						html += "	<td class=\"tc\"><p class=\"txt_01\">"
								+ upNo + "</p></td>  ";
						html += "	<td class=\"tc\"><p class=\"txt_01\">"
								+ pMokName + "</p></td>  ";
						html += "	<td class=\"tc\"><p class=\"txt_01\">"
								+ pJongName + "</p></td>  ";
						html += "	<td class=\"tr\"><p class=\"txt_01\">"
								+ comma(resultList[i].sokCnt)
								+ "</p></td>  ";
						html += "	<td class=\"tr\"><p class=\"txt_01\">"
								+ comma(resultList[i].boxCnt)
								+ "</p></td>  ";
						html += "	<td class=\"tc\"><p class=\"txt_01\">"
								+ chulName + "</p></td>  ";
						html += "	<td class=\"tc\"><p class=\"txt_01\">"
								+ upDay + "</p></td>  ";
						html += "</tr>                                                                  ";
					}

					$("#btnReport").css("display", "");
					
				} else {

					html += "<tr><td class=\"tc\" colspan=\"7\">검색하신 상품은 상장입력 전이거나 상장되지 않은 물품입니다.</td></tr>";
					
					$("#btnReport").css("display", "none");

				}

				$("#resultTbody2").html(html);
			

				$(".loadingWrap").hide();
				
			}
		});

	}

	//품목 select box
	function getMokCode() {

		var fixType = "fix" + $("input:radio[name=bunChk]:checked").val()
				+ "Auc";

		$("#mokCode").select2({
			ajax : {
				url : contextPath + '/front/' + fixType + '/moklist.json',
				dataType : 'json',
				delay : 250,
				cache : true,
				// 검색 쿼리를 만든다.
				data : function(params) {
					return {
						searchKeyWord : params.term
					};
				},
				// 결과를 표현한다.
				processResults : function(data) {
					return {
						results : data.results
					};
				}
			},
			theme : 'bootstrap4',
			placeholder : "품목을 선택하세요.",
			allowClear : true,
			language : {
				noResults : function() {
					return "검색결과가없습니다.";
				},
				searching : function() {
					return '검색중…';
				},
				inputTooShort : function() {
					return "검색어를 입력하세요";
				}
			}
		});

	}

	//품종 select box 
	function getPumList(recentCheck, recentPumCode) {

		var pMokCode = $("#mokCode").prop("value");
		var fixType = "fix" + $("input:radio[name=bunChk]:checked").val()
				+ "Auc";

		$("#pumCode").html("");

		if (pMokCode == "") {

			$("#pumCode").select2({
				theme : 'bootstrap4',
				placeholder : "품종을 선택하세요.",
				allowClear : true,
				language : {
					noResults : function() {
						return "품목을 먼저 선택하세요.";
					},
					searching : function() {
						return '검색중…';
					},
					inputTooShort : function() {
						return "검색어를 입력하세요";
					}
				},
				width : '100%'
			});

		} else {
			$("#pumCode").select2({
				ajax : {
					url : contextPath + '/front/' + fixType + '/pumlist.json',
					dataType : 'json',
					delay : 250,
					cache : true,
					// 검색 쿼리를 만든다.
					data : function(params) {
						return {
							searchKeyWord : params.term,
							pMokCode : pMokCode
						};
					},
					// 결과를 표현한다.
					processResults : function(data) {
						return {
							results : data.results
						};
					}
				},
				theme : 'bootstrap4',
				placeholder : "품종을 선택하세요.",
				allowClear : true,
				language : {
					noResults : function() {
						return "검색결과가없습니다.";
					},
					searching : function() {
						return '검색중…';
					},
					inputTooShort : function() {
						return "검색어를 입력하세요";
					}
				},
				width : '100%'
			});
		}
	}
	
	
	function fn_report(){
		var reportname = "sangJangCntList.ozr";
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		var bunChk = $("input:radio[name=bunChk]:checked").val();
		var mokCode = $("#mokCode").prop("value");
		var pumCode = $("#pumCode").prop("value");
		var searchChul = $("#searchChul").val();
		
		$('input[name=reportname]').val(reportname);
		$('input[name=setUrl]').val("${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/front/stat/sangJangCntList.json?startDate="+startDate+"&endDate="+endDate+"&bunChk="+bunChk+"&mokCode="+mokCode+"&pumCode="+pumCode+"&searchChul="+encodeURIComponent(searchChul));
		
		$('#reportForm').submit();
	}

</script>

<!-- sub-conts(S) -->
<div class="sub_conts bgw">

	<!-- sub상단(S) -->
	<div class="sub_top">
		<h2 class="sub_title">·상장물량조회(절화)·</h2>

		<!-- 네비게이션(S) -->
		<jsp:include page="/WEB-INF/jsp/front/inc/statNav.jsp"/>
		<!-- 네비게이션(E) -->
	</div>
	<!-- sub상단(E) -->
	
	<!-- sub내용(S) -->
	<div class="sub_conts_in">
		<jsp:include page="/WEB-INF/jsp/front/login/statTab.jsp"/>	
		<div class="info_box mt30">
			<div class="title_box">
				<div class="fl">
					<h4 class="sub_tit_02">조건검색</h4>
				</div>
			</div>
			<!-- 검색조건창(S) -->
			<div class="condition_box bdtg2 mt10">
				<div class="search_box">
					<ul class="sb_line">
						<li>
							<p class="sb_title bold">화훼부류</p>
							<div class="sb_data">
								<div class="table_devide">
									<label class="radio">
										<input type="radio" name="bunChk" value="N" checked="checked" onchange="getMokCode();">
										<i class="rdo"></i>
										<em class="label-title">절화</em>
									</label>
								</div>
							</div>
						</li>
					</ul>
					<ul class="sb_line" style="font-size: 100% !important;">
						<li>
							<p class="sb_title bold">품목</p>
							<div class="sb_data fr">
								<div class="sel_type_01 w150 mt5">
									<input type="hidden" name="mokName" id="mokName" value="">
									<select id="mokCode" name="mokCode" onchange="getPumList();">
										<option value=""></option>
									</select>
									<label for="mokCode"></label>
								</div>
							</div>
						</li>
					</ul>
					<ul class="sb_line" style="font-size: 100% !important;">
						<li id="pumCodeArea">
							<p class="sb_title bold">품종</p>
							<div class="sb_data fr">
								<div class="sel_type_01 w150 mt5">
									<input type="hidden" name="jongName" id="jongName" value="">
									<select id="pumCode" name="pumCode">
										<option value=""></option>
									</select>
									<label for="pumCode"></label>
								</div>
							</div>
						</li>
					</ul>			
					<ul class="sb_line">
						<li>
							<p class="sb_title bold">출하자</p>
							<div class="sb_data">
								<div class="ip_type_01 w100p">
									<input type="text" id="searchChul" name="searchChul" placeholder="검색어를 입력하세요" value=""><label for="searchChul"></label>
								</div>
							</div>
						</li>
					</ul>
					<ul class="sb_line">
						<li>
							<p class="sb_title bold">상장일자</p>
							<div class="sb_data">
								<div class="table_devide">
									<label class="radio">
										<input type="radio" name="rr" checked="checked" onclick="setSearchDateInit(0);">
										<i class="rdo"></i>
										<em class="label-title">당일</em>
									</label>
									<label class="radio">
										<input type="radio" name="rr" onclick="setSearchDateInit(7);">
										<i class="rdo"></i>
										<em class="label-title">최근 7일</em>
									</label>
									<label class="radio">
										<input type="radio" name="rr" onclick="setSearchDateInit(10);">
										<i class="rdo"></i>
										<em class="label-title">최근 10일</em>
									</label>
									<label class="radio">
										<input type="radio" name="rr" onclick="setSearchDateInit(15);">
										<i class="rdo"></i>
										<em class="label-title">최근 15일</em>
									</label>
									<label class="radio">
										<input type="radio" name="rr" onclick="setSearchDateInit(30);">
										<i class="rdo"></i>
										<em class="label-title">최근 1개월</em>
									</label>
									<label class="radio">
										<input type="radio" name="rr" id="customInput">
										<i class="rdo"></i>
										<em class="label-title">직접입력</em>
									</label>
								</div>
								<div class="table_devide">
									<div class="date_from_to">
										<div class="date_box">
											<input type="text" id="startDate" class="datepicker" onclick="customInputCheck();">
										</div>
										<span class="hyphen">~</span>
										<div class="date_box">
											<input type="text" id="endDate" class="datepicker" onclick="customInputCheck();">
										</div>												
									</div>
									<p class="txt_01 dib"></p>
								</div>
							</div>
						</li>
					</ul>
					<a href="javascript:fn_search();" class="btn_search">조회</a>
				</div>
			</div>
			<!-- 검색조건창(E) -->
		</div>

		<!-- 조회결과(S) -->
		<form>
			<!-- 타이틀(S) -->
			<div class="title_box mt30">
				<div class="fl">
					<h4 class="sub_tit_04 mt10">부류 : <b id="bunName"></b></h4>
				</div>
				<div class="fr">
					<a href="javascript:fn_report();" id="btnReport" class="btn_type_01">리포트</a>
				</div>
			</div>
			<div class="title_box">
				<div class="fl">
					<h4 class="sub_tit_04 mt10">조회일자 : <b id="searchDate"></b></h4>
				</div>
				<div class="fr">
					<div class="icon-box tr">
						<span class="guide-icon width-scroll"></span>
					</div>
				</div>
			</div>
			<!-- 타이틀(E) -->			
			<div class="table_type_01 mt10 overflow_a" id="tableArea2">
				<table>
					<caption>조회결과</caption>
					<colgroup>
						<col width="60px">
						<col width="100px;">
						<col width="100px;">
						<col width="50px;">
						<col width="50px;">
						<col width="100px;">
						<col width="100px;">
					</colgroup>
					<thead>
						<tr>
							<th scope="col">상장번호</th>
							<th scope="col">품목</th>
							<th scope="col">품종</th>
							<th scope="col">속수</th>
							<th scope="col">상자수</th>
							<th scope="col">출하자명</th>
							<th scope="col">상장일자</th>
						</tr>
					</thead>
					<tbody id="resultTbody2">
						<tr><td class="tc" colspan="7">조회버튼을 누르세요.</td></tr>
					</tbody>
				</table>
			</div>
		</form>
		<!-- 조회결과(E) -->
	</div>
	<!-- sub내용(E) -->
	
	<form id="reportForm" action="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/oz80/report.jsp" method="post" target="_blank">
		<input type="hidden" name="reportname" value="">
		<input type="hidden" name="setUrl" value="">
		<input type="hidden" name="hostUrl" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/oz80/server">
	</form>
	
</div>
<!-- sub-conts(E) --> 

<!-- wrap(E) -->		
<div class="loadingWrap">
    <div class="loadArea">
        <div class="loading">
        	<div class="lds-dual-ring"></div>
        	<div class="text" id="procText" style="color: white;">
	        	처리중입니다.
	        </div>
        </div>
    </div>
</div>