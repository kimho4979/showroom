<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<script src="${pageContext.request.contextPath}/js/jquery.mtz.monthpicker.js"></script>

<style>
	.totSum{background: #eeeeee!important;}
	input:disabled {border: 1px solid #dcdcdc!important; text-align: center;}
	
	.sb_line .w100p{}
	.sb_line .w100p .sb_data{width:calc(100% - 110px);}
	
	.table_devide.type-01{display:inline-block;}
	.table_devide.type-01 .ip_type_01:first-child{margin-right:10px;}
	.table_devide.type-01 .ip_type_01:nth-child(2){width:100px;}
	.table_devide.type-02{margin-top:7px; width:90px; display:inline-block;} 
	
	.table_devide.type-01{margin-top:0; margin-right:0; width:calc(100% - 100px);}
	.table_devide.type-01 .ip_type_01:nth-child(1){width:calc(100% - 110px)!important;}
	.table_devide.type-02{margin-left:10px; width:80px;}
	
	select.mtz-monthpicker{width:150px!important; }
	td.ui-state-default{cursor:pointer!important;}
	td.ui-state-default:hover{background:#fbf2ff; border-radius:10px; }
	td.ui-state-active{background:#fbf2ff; border-radius:10px; }
	
	@media screen and (max-width: 800px) {
		.table_type_04 colgroup col:nth-child(1){width: 88.11px!important;}
		.table_type_04 colgroup col:nth-child(2){width: 88.11px!important;}
		.table_type_04 colgroup col:nth-child(3){width: 208.25px!important;}
		.table_type_04 colgroup col:nth-child(4){width: 208.25px!important;}
		.table_type_04 colgroup col:nth-child(5){width: 208.25px!important;}
	}
	
	@media screen and (max-width: 470px) {
		.table_devide.type-01{width:100%;}
		.table_devide.type-02{width:100%; text-align:right;}
		.table_devide.type-02{margin-left:0px; margin-bottom:10px;}
		
		div.mtz-monthpicker{width:100%!important;}
		
	}
	
	@media screen and (max-width: 407px) {
	    /* 휴대폰 */
	    .table_devide.type-01 .ip_type_01:nth-child(1){width:100%!important; margin-right:0;}
	    .table_devide.type-01 .ip_type_01{width:100%!important;}
	}
	
	#ui-datepicker-div{display: none!important;}
</style>
  <!-- sub-conts(S) -->
		<div class="sub_conts bgw">

			<!-- sub상단(S) -->
			<div class="sub_top">
				<h2 class="sub_title">·월별 온라인매매 현황·</h2>

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
							<h4 class="sub_tit_02">월별 온라인매매 현황</h4>
						</div>
					</div>

					<!-- 타이틀(S) -->
					<div class="title_box mt30">
						<div class="fl">
							<h4 class="sub_tit_04">월별 온라인매매 현황 검색</h4>
						</div>
					</div>
					<!-- 타이틀(E) -->
					
					<!-- 검색조건창(S) -->
					<div class="condition_box bdtg2 mt10">
						<div class="search_box">
							<ul class="sb_line">
								<li>
									<p class="sb_title bold">화훼부류</p>
									<div class="sel_type_01 w200">
										<select id="selectBunChk" name="selectBunChk">
											<option value="">전체 ( 절화,난,관엽 )</option>
											<option value="N">절화</option>
											<option value="Y">난</option>
											<option value="C">관엽</option>
										</select>
										<label for="selectBunChk"></label>
									</div>
								</li>		
							</ul>
							
							<ul class="sb_line" id="detailUl">
								<li>
									<p class="sb_title bold">기간</p>
									<div class="sb_data">
										<div class="table_devide">
											<div class="date_from_to">
												<div class="date_box">
													<input type="text" id="startMonth" class="monthpicker">
												</div>
												<span class="hyphen">~</span>
												<div class="date_box">
													<input type="text" id="endMonth" class="monthpicker">
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

					<div id="print_page">
					
					<div id="mainContentDiv" style="display: none;">
						<!-- 타이틀(S) -->
						<div class="title_box mt30">
							<div class="fl">
								<h4 class="sub_tit_04">월별 온라인매매 현황</h4>
							</div>
							<div class="fr" id="bthDiv">
								<a href="javascript:fn_report();" id="btnReport" class="btn_type_01" style="display: none;">리포트</a>
							</div>
						</div>
						<!-- 타이틀(E) -->
	
						<!-- 테이블03(S) -->
						<div class="table_type_03 mt5" id="infoDiv">
							<table>
								<caption>판매정산내역</caption>
								<colgroup>
									<col style="width:17%">
									<col style="width:33%">
									<col style="width:17%">
									<col style="width:33%">
								</colgroup>
								<tbody>
									<tr>
										<th class="tc">화훼부류</th>
										<td class="tl" colspan="3"><p class="txt_04" id="bunName"></p></td>
									</tr>
									<tr>
										<th class="tc">조회기간</th>
										<td class="tl" colspan="3"><p class="txt_04" id="searchedDate"></p></td>
									</tr>
								</tbody>
							</table>
						</div>
						<!-- 테이블03(E) -->
	
						<!-- 타이틀(S) -->
						<div class="title_box mt30">
							<div class="fl">
								<div class="icon-box tr">
									<span class="guide-icon width-scroll"></span>
								</div>
							</div>
							<div class="fr">
								<p class="txt_unit mt10">(단위: 속(분),원)</p>
							</div>
						</div>
						<!-- 타이틀(E) -->
						
						<!-- 테이블04(S) -->
						<div class="table_type_04 mt5 overflow_a">
							<table>
								<caption>리스트</caption>
								<colgroup>
									<col style="width:11%;">
									<col style="width:11%;">
									<col style="width:26%;">
									<col style="width:26%;">
									<col style="width:26%;">
								</colgroup>
								<thead>
									<tr>
										<th class="tc" rowspan="2">화훼부류</th>
										<th class="tc" rowspan="2">연월</th>
										<th class="tc" colspan="2">총물량</th>
										<th class="tc" rowspan="2">총금액</th>
									</tr>
									<tr>
										<th class="tc">상자</th>
										<th class="tc">속(분)</th>
									</tr>
								</thead>
								<tbody id="resultTbody">
									
								</tbody>
								<tfoot id="resultTfoot">
									<tr>
										<th colspan="2">총계</th>
										<td class="tr"><p class="txt_01"></p></td>
										<td class="tr"><p class="txt_01"></p></td>
										<td class="tr"><p class="txt_01"></p></td>
									</tr>
								</tfoot>
							</table>
						</div>
						<!-- 테이블04(E) -->
						</div>
					</div>
				</div>

			</div>
			<!-- sub내용(E) -->
			
			<form id="reportForm" action="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/oz80/report.jsp" method="post" target="_blank">
				<input type="hidden" name="reportname" value="">
				<input type="hidden" name="setUrl" value="">
				<input type="hidden" name="hostUrl" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/oz80/server">
			</form>
			
		</div>
		<!-- sub-conts(E) -->  
		
		<script type="text/javascript">
		var currentDate = "";
		
		$(document).ready(function(){
			
			currentDate = $("#endMonth").val();
			
			
			$( "input.monthpicker" ).datepicker({
				  showOn: "both",
				  buttonImage: contextPath+"/img/ico_datepicker.png",
				  buttonImageOnly: true,
			   	  buttonText: "Select date"
			});
			
					

					// KR language callendar
			/*$.datepicker.regional['kr'] = {
				closeText: '닫기', // 닫기 버튼 텍스트 변경
				currentText: '오늘', // 오늘 텍스트 변경
				monthNames: ['1 월','2 월','3 월','4 월','5 월','6 월','7 월','8 월','9 월','10 월','11 월','12 월'], // 개월 텍스트 설정
				monthNamesShort: ['1 월','2 월','3 월','4 월','5 월','6 월','7 월','8 월','9 월','10 월','11 월','12 월'], // 개월 텍스트 설정
				dayNamesMin: ['일','월','화','수','목','금','토'], // 요일 텍스트 설정
				dayNamesShort: ['월','화','수','목','금','토','일'], // 요일 텍스트 축약 설정&nbsp;   dayNamesMin: ['월','화','수','목','금','토','일'], // 요일 최소 축약 텍스트 설정
				dateFormat: 'yy-mm', // 날짜 포맷 설정
				showMonthAfterYear: true,
				changeMonth: true,
	            changeYear: true,
		  };*/
		  
		  // $('.ui-datepicker ').css({"margin-left" : "-100px", "margin-top": "0px"});  //달력(calendar) 위치
		  //  $('img.ui-datepicker-trigger').attr('align', 'absmiddle');
				// Seeting up default language, Korean
			// $.datepicker.setDefaults($.datepicker.regional['kr']);
				
			setSearchMonthInit2(3);
			
		});
		
		function setSearchMonthInit2(monthCnt){
			
			var endDate = new Date(); 
			var strDate = new Date();
			
			strDate.setMonth(endDate.getMonth()-monthCnt);
			
			var strYear = strDate.getFullYear(); 
			var strMonth = new String(strDate.getMonth()); 
			var endYear = endDate.getFullYear(); 
			var endMonth = new String(endDate.getMonth()); 
			
			var options = {
					pattern: 'yyyy-mm',
					selectedYear: strYear,
					selectedMonth: endMonth,
					finalYear: endYear,
					monthNames: ['1 월','2 월','3 월','4 월','5 월','6 월','7 월','8 월','9 월','10 월','11 월','12 월'],
					openOnFocus: true,
					Button: '<img src="images/calendar.gif" title="Select date" />'
			};
			
			var currentMonth = new String(strDate.getMonth()+1); 
			if(currentMonth.length == 1){ 
				currentMonth = "0" + currentMonth; 
			} 
			
			$("#startMonth").val(strYear + "-" + currentMonth);
			$("#startMonth").monthpicker(options);
			
			options = {
					pattern: 'yyyy-mm',
					selectedYear: endYear,
					selectedMonth: endMonth,
					finalYear: endYear,
					monthNames: ['1 월','2 월','3 월','4 월','5 월','6 월','7 월','8 월','9 월','10 월','11 월','12 월'],
					openOnFocus: true
			};
			
			currentMonth = new String(endDate.getMonth()+1); 
			if(currentMonth.length == 1){ 
				currentMonth = "0" + currentMonth; 
			} 
			
			$("#endMonth").val(endYear + "-" + currentMonth);
			$("#endMonth").monthpicker(options);
		}
		
		function fn_search(){
			var startMonth = $("#startMonth").val();
			var endMonth = $("#endMonth").val();
			var selectBunChk = $("#selectBunChk").val();
			
			$.ajax({
				data:{
					startMonth: startMonth,
					endMonth: endMonth,
					selectBunChk: selectBunChk
			        },
		        type : "POST",
		        url : "${pageContext.request.contextPath}/front/stat/mafraMagamMasterListJson.json",
		        success : function(data){
		        	
		        	console.log(data);
		        	
		        	var html = "";
		           	var fhtml = "";
		           
		           	var resultList = data.resultList;

		           	var sumBoxCnt = 0;
		           	var sumSokCnt = 0;
		           	var sumTradePrice = 0;
		           	
		           	var sumNBoxCnt = 0;
		           	var sumYBoxCnt = 0;
		           	var sumCBoxCnt = 0;
		           	var sumNSokCnt = 0;
		           	var sumYSokCnt = 0;
		           	var sumCSokCnt = 0;
		           	var sumNTradePrice = 0;
		           	var sumYTradePrice = 0;
		           	var sumCTradePrice = 0;
		           	
		           	$("#mainContentDiv").css("display", "");
		           	$("#searchedDate").html(startMonth + " ~ " + endMonth);
		           	$("#bunName").html($("#selectBunChk option:selected").text());

		           if(resultList.length > 0){
		        	   $("#btnReport").css("display", "");
		        	   for(var i=0; i<resultList.length; i++ ){
		        		   
		        		   	console.log(resultList[i]);
		        		   	
							var upMonth = resultList[i].upMonth;
							var bunChk = resultList[i].bunChk;
							var totBoxCnt = resultList[i].totBoxCnt;
							var totSokCnt = resultList[i].totSokCnt;
							var totTradePrice = resultList[i].totTradePrice;
							
							var bunChkName = "";
							if(bunChk == 'N') {
								bunChkName = "절화";
							}
							if(bunChk == 'Y') {
								bunChkName = "난";
							}
							if(bunChk == 'C') {
								bunChkName = "관엽";
							}
							
							if(totBoxCnt != null){
								sumBoxCnt = sumBoxCnt + totBoxCnt;   
								if(bunChk == 'N') {
									sumNBoxCnt = sumNBoxCnt + totBoxCnt;
								}
								if(bunChk == 'Y') {
									sumYBoxCnt = sumYBoxCnt + totBoxCnt;
								}
								if(bunChk == 'C') {
									sumCBoxCnt = sumCBoxCnt + totBoxCnt;
								}
		        		   	}
							if(totSokCnt != null){
								sumSokCnt = sumSokCnt + totSokCnt;
								if(bunChk == 'N') {
									sumNSokCnt = sumNSokCnt + totSokCnt;
								}
								if(bunChk == 'Y') {
									sumYSokCnt = sumYSokCnt + totSokCnt;
								}
								if(bunChk == 'C') {
									sumCSokCnt = sumCSokCnt + totSokCnt;
								}
		        		   	}
							if(totTradePrice != null){
								sumTradePrice = sumTradePrice + totTradePrice;   
								if(bunChk == 'N') {
									sumNTradePrice = sumNTradePrice + totTradePrice;
								}
								if(bunChk == 'Y') {
									sumYTradePrice = sumYTradePrice + totTradePrice;
								}
								if(bunChk == 'C') {
									sumCTradePrice = sumCTradePrice + totTradePrice;
								}
		        		   	}
							
							html+="<tr>                                                               ";
							html+="	<td class=\"tc\"><p class=\"txt_01\">"+bunChkName+"</p></td>          ";
							html+="	<td class=\"tc\"><p class=\"txt_01\">"+upMonth+"</p></td>                  ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(totBoxCnt)+"</p></td>          ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(totSokCnt)+"</p></td>                  ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(totTradePrice)+"</p></td>                   ";
							html+="</tr>                                                              ";
		        	   }
		        	   
		        	if(sumNBoxCnt > 0){
		        		fhtml+="<tr>														";
		        		fhtml+="	<th colspan=\"2\">절화 총계</th>                            ";
						fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(sumNBoxCnt)+"</p></td>       ";
						fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(sumNSokCnt)+"</p></td>      ";
						fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(sumNTradePrice)+"</p></td>";
		        		fhtml+="</tr>														";
		        	}
					if(sumYBoxCnt > 0){
						fhtml+="<tr>														";
						fhtml+="	<th colspan=\"2\">난 총계</th>                            ";
						fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(sumYBoxCnt)+"</p></td>       ";
						fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(sumYSokCnt)+"</p></td>      ";
						fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(sumYTradePrice)+"</p></td>";
		        		fhtml+="</tr>														";
		        	}
					if(sumCBoxCnt > 0){
						fhtml+="<tr>														";
						fhtml+="	<th colspan=\"2\">관엽 총계</th>                            ";
						fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(sumCBoxCnt)+"</p></td>       ";
						fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(sumCSokCnt)+"</p></td>      ";
						fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(sumCTradePrice)+"</p></td>";
		        		fhtml+="</tr>														";
		        	}
		        	
		        	fhtml+="<tr>                                                        ";
					fhtml+="	<th colspan=\"2\" class=\"totSum\">총계</th>                            ";
					fhtml+="	<td class=\"tr totSum\"><p class=\"txt_01\">"+comma(sumBoxCnt)+"</p></td>       ";
					fhtml+="	<td class=\"tr totSum\"><p class=\"txt_01\">"+comma(sumSokCnt)+"</p></td>      ";
					fhtml+="	<td class=\"tr totSum\"><p class=\"txt_01\">"+comma(sumTradePrice)+"</p></td>";
					fhtml+="</tr>                                                   ";
										
		           }else{
		        	   $("#btnReport").css("display", "none");
		        	   html += "<tr><td class=\"tc\" colspan=\"5\">데이터가 없습니다.</td></tr>";
		           }
		           
		           $("#resultTbody").html(html);
		           $("#resultTfoot").html(fhtml);
		           
		        }
		    });
			
		}
		
		function fn_report(){
			var reportname = "mafraMagamMaster.ozr";
			var startMonth = $("#startMonth").val();
			var endMonth = $("#endMonth").val();
			var selectBunChk = $("#selectBunChk").val();
			
			$('input[name=reportname]').val(reportname);
			$('input[name=setUrl]').val("${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/front/stat/mafraMagamMasterListJson.json?startMonth="+startMonth+"&endMonth="+endMonth+"&selectBunChk="+selectBunChk);
			
			$('#reportForm').submit();
		}
		
		</script>