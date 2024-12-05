<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<style>
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
	
	@media screen and (max-width: 470px) {
		.table_devide.type-01{width:100%;}
		.table_devide.type-02{width:100%; text-align:right;}
		.table_devide.type-02{margin-left:0px; margin-bottom:10px;}
		
	}
	
	@media screen and (max-width: 407px) {
	    /* 휴대폰 */
	    .table_devide.type-01 .ip_type_01:nth-child(1){width:100%!important; margin-right:0;}
	    .table_devide.type-01 .ip_type_01{width:100%!important;}
	}
	
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
  <!-- sub-conts(S) -->
		<div class="sub_conts bgw">

			<!-- sub상단(S) -->
			<div class="sub_top">
				<h2 class="sub_title">·판매정산내역[전체]·</h2>

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
							<h4 class="sub_tit_02">판매정산내역[전체]</h4>
						</div>
					</div>

					<!-- 타이틀(S) -->
					<div class="title_box mt30">
						<div class="fl">
							<h4 class="sub_tit_04">판매정산내역 검색</h4>
						</div>
					</div>
					<!-- 타이틀(E) -->
					
					<!-- 검색조건창(S) -->
					<div class="condition_box bdtg2 mt10">
						<div class="search_box">
							<ul class="sb_line">
								<li>
									<p class="sb_title bold">화훼부류</p>
									<div class="sb_data">
										<div class="table_devide">
											<label class="radio">
												<input type="radio" name="rBunChk" value="N" checked="checked" onclick="setBunChkInit('N');">
												<i class="rdo"></i>
												<em class="label-title">절화</em>
											</label>
											<label class="radio">
												<input type="radio" name="rBunChk" value="Y" onclick="setBunChkInit('Y');">
												<i class="rdo"></i>
												<em class="label-title">난</em>
											</label>
											<label class="radio">
												<input type="radio" name="rBunChk" value="C" onclick="setBunChkInit('C');">
												<i class="rdo"></i>
												<em class="label-title">관엽</em>
											</label>
										</div>
									</div>
								</li>		
							</ul>
							
							<ul class="sb_line">
								<li class="sbl-type-01">
									<p class="sb_title bold">출하자</p>
									<div class="sb_data">
										<div class="table_devide type-01">
											<div class="ip_type_01" id="disChulCodeDiv">
												<input type="hidden" id="chulCode" name="chulCode" value=""/>
												<input type="hidden" id="bunChk" name="bunChk" value=""/>
												<input type="text" id="disChulCode" name="disChulCode" disabled />
											</div>
											<div class="ip_type_01">
												<input type="text" id="chulCodeName" name="chulCodeName" value="" onkeydown="fn_enterChulCode();"/>
											</div>
										</div>
										<div class="table_devide type-02">
											<a href="javascript:getEnterChulList();" class="btn_type_01">검색</a>
										</div>
									</div>
								</li>		
							</ul>
							<ul class="sb_line">
								<li>
									<p class="sb_title bold">경매일자</p>
									<div class="sb_data">
										<div class="table_devide">
											<label class="radio">
												<input type="radio" name="rr" checked="checked" onclick="setSearchDateInit(7);">
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

					<div id="print_page">
					<style>
						@page { margin:5mm 5mm 5mm 5mm; }
						
						@media print {
							html, body {
								   width:210mm;
						           border: 1px solid white;
						           height: 99%;
						           page-break-after: avoid;
						           page-break-before: avoid;
						    }
						    table {font-size: 11px; text-align: center;}
							table th {background-color: #666699 !important; color:#ffffff !important; -webkit-print-color-adjust:exact; }
							table td {height: 15px !important; padding-bottom: 2px !important; padding-top: 2px !important; border:1px solid #dcdcdc;}
							#th1{width:200px;}
							#bthDiv{display:none;}
							
						}
					</style>
					
					<div id="mainContentDiv" style="display: none;">
						<!-- 타이틀(S) -->
						<div class="title_box mt30">
							<div class="fl">
								<h4 class="sub_tit_04">판매정산내역</h4>
							</div>
							<div class="fr" id="bthDiv">
								<a href="javascript:fn_report();" id="btnReport" class="btn_type_01" style="display: none;">리포트</a>
								<a onclick="pageprint()" id="btnPrint" class="btn_type_01" style="display: none;">인쇄</a>
								<a href="javascript:fn_excell();" id="btnExcell" class="btn_type_01" style="display: none;">엑셀다운로드</a>
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
										<th class="tc">출하농가</th>
										<td class="tl" colspan="3"><p class="txt_04" id="chulName"></p></td>
									</tr>
									<tr>
										<th class="tc">화훼부류</th>
										<td class="tl" colspan="3"><p class="txt_04" id="bunName"></p></td>
									</tr>
									<tr>
										<th class="tc">경매기간</th>
										<td class="tl"><p class="txt_04" id="searchDate"></p></td>
										<th class="tc">조회일자</th>
										<td class="tl"><p class="txt_04" id="currentDate"></p></td>
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
									<col style="width:108px;">
									<col style="width:99px;">
									<col style="width:99px;">
									<col style="width:99px;">
									<col style="width:99px;">
									<col style="width:99px;">
									<col style="width:99px;">
									<col style="width:99px;">
									<col style="width:99px;">
									<col style="width:99px;">
								</colgroup>
								<thead>
									<tr>
										<th class="tc">출하일자</th>
										<th class="tc">품목명</th>
										<th class="tc">품종명</th>
										<th class="tc">품종코드</th>
										<th class="tc">상자</th>
										<th class="tc">속</th>
										<th class="tc">등급</th>
										<th class="tc">낙찰단가</th>
										<th class="tc">판매금액</th>
										<th class="tc">결과</th>
									</tr>
								</thead>
								<tbody id="resultTbody">
									
								</tbody>
								<tfoot id="resultTfoot">
									<tr>
										<th colspan="4">소계(A)</th>
										<td class="tr"><p class="txt_01">125</p></td>
										<td class="tr"><p class="txt_01">1549</p></td>
										<td class="tr"><p class="txt_01">1549</p></td>
										<td class="tr"><p class="txt_01">1549</p></td>
										<td class="tr"><p class="txt_01">17,308,150</p></td>
										<td class="tr"><p class="txt_01"></p></td>
									</tr>
									<tr>
										<th colspan="4">공제금액(B)</th>
										<td class="tr"><p class="txt_01">125</p></td>
										<td class="tr"><p class="txt_01">1549</p></td>
										<td class="tr"><p class="txt_01">1549</p></td>
										<td class="tr"><p class="txt_01">1549</p></td>
										<td class="tr"><p class="txt_01">17,308,150</p></td>
										<td class="tr"><p class="txt_01"></p></td>
									</tr>
									<tr>
										<th colspan="4">지급금액(A-B)</th>
										<td class="tr"><p class="txt_01">125</p></td>
										<td class="tr"><p class="txt_01">1549</p></td>
										<td class="tr"><p class="txt_01">1549</p></td>
										<td class="tr"><p class="txt_01">1549</p></td>
										<td class="tr"><p class="txt_01">17,308,150</p></td>
										<td class="tr"><p class="txt_01"></p></td>
									</tr>
								</tfoot>
							</table>
						</div>
						<!-- 테이블04(E) -->
						
						<!-- 타이틀(S) -->
						<div class="title_box mt30">
							<div class="fl">
								<h4 class="sub_tit_04">공제 세부내역</h4>
							</div>
						</div>
						<!-- 타이틀(E) -->
	
						<!-- 테이블03(S) -->
						<div class="table_type_03 mt5">
							<table>
								<caption>공제 세부내역</caption>
								<colgroup>
									<col style="width:10%">
									<col style="width:23.33%">
									<col style="width:10%">
									<col style="width:23.33%">
									<col style="width:10%">
									<col style="width:23.33%">
								</colgroup>
								<tbody id="gongDetail">
									
								</tbody>
							</table>
						</div>
						<!-- 테이블03(E) -->	
						</div>
					</div>
				</div>

			</div>
			<!-- sub내용(E) -->
			
			<!-- 팝업(S) -->
			<div class="dim-layer" id="layerSample">
				<div class="dimBg"></div>
				<div class="pop-layer popbox pop_type_02">
					<div class="pop-container">
						<a href="#!" class="btn-layerClose pop_btn_close_02 close_B" alt="닫기 버튼"></a>
						<div class="pop-conts_00">
							<!-- 팝업 타이틀 박스(S) -->
							<div class="pop_title_box">
								<div class="fl">
									<p class="pop_title">출하자 검색</p>
								</div>
							</div>
							<!-- 팝업 타이틀 박스(E) -->
		
							<!-- 팝업 컨텐츠(S) -->
							<div class="choice_box">
								<div class="popup_search">
									<div class="ip_type_02 dib">
										<input type="text" class="tl" id="searchChulCode" onkeydown="fn_enterSearchChulCode();">
										<label for="searchChulCode"></label>
									</div>
									<a href="javascript:getChulList();" class="btn_search_03 ml10 vb">검색</a>
								</div>
		
								<div class="table_type_04 mt10 overflow_a" style="max-height: 250px;">
									<table>
										<caption>구매정보</caption>
										<colgroup>
											<col style="width:25%">
											<col style="width:25%">
											<col style="width:50%">
										</colgroup>
										<thead>
											<tr>
												<th>출하자명</th>
												<th>출하자코드</th>
												<th>지역</th>
											</tr>
										</thead>
										<tbody id="chulTbody">
											
										</tbody>
									</table>
								</div>
							</div>
							<!-- 팝업 컨텐츠(E) -->
						</div>
					</div>
				</div>
			</div>
			<!-- 팝업(E) -->
			
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
			setSearchDateInit(7);
			currentDate = $("#endDate").val();
			setBunChkInit("N");
		});
		
		
		function fn_search(){
			$(".loadingWrap").show();
			
			var chulCode = $("#chulCode").val();
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			var bunChk = $("#bunChk").val();
			
			if($("#chulCode").val() == "" || $("#chulCode").val() == null){
				alert("출하자를 검색해주세요.");
				$("#chulCodeName").focus();
				return false;
			}
			
			$.ajax({
				data:{
					chulCode: chulCode,
					startDate: startDate,
					endDate: endDate,
					bunChk: bunChk
			        },
		        type : "POST",
		        url : "${pageContext.request.contextPath}/front/stat/shprSaleAllCalcDetail.json",
		        success : function(data){
		           var html = "";
		           var fhtml = "";
		           var ghtml = "";
		           
		           var resultList = data.resultList;
		           var chulVO = data.chulVO;
		           var sumboxcnt = 0;
		           var sumsokcnt = 0;
		           var sumpanprice = 0;
		           var sumchain = 0;
		           
		           var sumgongje = 0;
		           var sumsusup = 0;
		           var sumdownrep = 0;
		           var sumreboxp = 0;
		           var sumtransprice = 0;
		           var sumdaegiprice = 0;
		           var sumsanghwan = 0;
		           var sumbaekamt = 0;
		           var sumtmisunuge = 0;
		           var sumselfamt = 0;
		           var sumfeeamt = 0;
		           var sumrentalamt = 0;
		           
		           $("#mainContentDiv").css("display", "");
		           $("#disChulCode").val(chulVO.name + " ( " + chulCode+" )");
		           $("#chulName").html(chulVO.name);
		           $("#bunName").html(chulVO.bunchknm);
		           $("#searchDate").html(startDate + " ~ " + endDate);
		           $("#currentDate").html(chulVO.today);
		           
		           
		           if(resultList.length > 0){
		        	   $("#btnReport, #btnPrint, #btnExcell").css("display", "");
		        	   for(var i=0; i<resultList.length; i++ ){
		        		   if(resultList[i].boxcnt != null){
		        			   sumboxcnt = sumboxcnt + resultList[i].boxcnt;   
		        		   }
		        		   if(resultList[i].sokcnt != null){
		        			   sumsokcnt = sumsokcnt + resultList[i].sokcnt;   
		        		   }
		        		   if(resultList[i].panprice != null){
		        			   sumpanprice = sumpanprice + resultList[i].panprice;   
		        		   }
		        		   if(resultList[i].chain != null){
		        			   sumchain = sumchain + resultList[i].chain;   
		        		   }
		        		   
		        		   if(resultList[i].susup != null){
		        			   sumsusup = sumsusup + resultList[i].susup;   
		        		   }
		        		   if(resultList[i].downrep != null){
		        			   sumdownrep = sumdownrep + resultList[i].downrep;   
		        		   }
		        		   if(resultList[i].reboxp != null){
		        			   sumreboxp = sumreboxp + resultList[i].reboxp;   
		        		   }
		        		   if(resultList[i].transprice != null){
		        			   sumtransprice = sumtransprice + resultList[i].transprice;
		        		   }
		        		   if(resultList[i].transpriceadd != null){
		        			   sumtransprice = sumtransprice + resultList[i].transpriceadd;
		        		   }
		        		   if(resultList[i].daegiprice != null){
		        			   sumdaegiprice = sumdaegiprice + resultList[i].daegiprice;   
		        		   }
		        		   if(resultList[i].sanghwan != null){
		        			   sumsanghwan = sumsanghwan + resultList[i].sanghwan;   
		        		   }
		        		   if(resultList[i].baekamt != null){
		        			   sumbaekamt = sumbaekamt + resultList[i].baekamt;   
		        		   }
		        		   if(resultList[i].misunuge != null){
		        			   sumtmisunuge = sumtmisunuge + resultList[i].misunuge;   
		        		   }
		        		   if(resultList[i].selfamt != null){
		        			   sumselfamt = sumselfamt + resultList[i].selfamt;   
		        		   }
		        		   if(resultList[i].feeamt != null){
		        			   sumfeeamt = sumfeeamt + resultList[i].feeamt;   
		        		   }
		        		   if(resultList[i].rentalamt != null){
		        			   sumrentalamt = sumrentalamt + resultList[i].rentalamt;   
		        		   }
		        		   
		        		   sumgongje = sumsusup+sumdownrep+sumreboxp+sumtransprice+sumdaegiprice+sumsanghwan+sumbaekamt+sumtmisunuge+sumselfamt+sumfeeamt+sumrentalamt;   
		        		  
		        		    
		        		   
		        		   
		        		    var panday = "";
							var pmokname = "";
							var pjongname = "";
							var levelname = "";
							
							
							panday = resultList[i].panday;
							pmokname = resultList[i].pmokname;
							pjongname = resultList[i].pjongname;
							levelname = resultList[i].levelname;
							
							
							html+="<tr>                                                               ";
							html+="	<td class=\"tc\"><p class=\"txt_01\">"+panday+"</p></td>          ";
							html+="	<td class=\"tc\"><p class=\"txt_01\">"+pmokname+"</p></td>                  ";
							html+="	<td class=\"tc\"><p class=\"txt_01\">"+pjongname+"</p></td>          ";
							html+="	<td class=\"tc\"><p class=\"txt_01\">"+resultList[i].pumcode+"</p></td>                  ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].boxcnt)+"</p></td>                   ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].sokcnt)+"</p></td>                  ";
							html+="	<td class=\"tc\"><p class=\"txt_01\">"+levelname+"</p></td>                  ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].kmpnew)+"</p></td>              ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].panprice)+"</p></td>             ";
							html+="	<td class=\"tc\"><p class=\"txt_01\">"+resultList[i].kmlist+"</p></td>                  ";
							html+="</tr>                                                              ";
							
		        	   }
		        	   
		        	   
		        	fhtml+="<tr>                                                        ";
					fhtml+="	<th colspan=\"4\">소계(A)</th>                            ";
					fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(sumboxcnt)+"</p></td>       ";
					fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(sumsokcnt)+"</p></td>      ";
					fhtml+="	<td class=\"tr\"><p class=\"txt_01\"></p></td>      ";
					fhtml+="	<td class=\"tr\"><p class=\"txt_01\"></p></td>      ";
					fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(sumpanprice)+"</p></td>";
					fhtml+="	<td class=\"tr\"><p class=\"txt_01\"></p></td>          ";
					fhtml+="</tr>                                                       ";
					fhtml+="<tr>                                                        ";
					fhtml+="	<th colspan=\"4\">공제금액(B)</th>                          ";
					fhtml+="	<td class=\"tr\"><p class=\"txt_01\"></p></td>       ";
					fhtml+="	<td class=\"tr\"><p class=\"txt_01\"></p></td>      ";
					fhtml+="	<td class=\"tr\"><p class=\"txt_01\"></p></td>      ";
					fhtml+="	<td class=\"tr\"><p class=\"txt_01\"></p></td>      ";
					fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(sumgongje)+"</p></td>";
					fhtml+="	<td class=\"tr\"><p class=\"txt_01\"></p></td>          ";
					fhtml+="</tr>                                                       ";
					fhtml+="<tr>                                                        ";
					fhtml+="	<th colspan=\"4\">지급금액(A-B)</th>                        ";
					fhtml+="	<td class=\"tr\"><p class=\"txt_01\"></p></td>       ";
					fhtml+="	<td class=\"tr\"><p class=\"txt_01\"></p></td>      ";
					fhtml+="	<td class=\"tr\"><p class=\"txt_01\"></p></td>      ";
					fhtml+="	<td class=\"tr\"><p class=\"txt_01\"></p></td>      ";
					fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(sumchain)+"</p></td>";
					fhtml+="	<td class=\"tr\"><p class=\"txt_01\"></p></td>          ";
					fhtml+="</tr>                                                       ";
		        	
					
					ghtml += "<tr>                                                            ";
					ghtml += "	<th class=\"tc\">상장수수료</th>                                   ";
					ghtml += "	<td class=\"tr\"><p class=\"txt_04\">"+comma(sumsusup)+"</p></td>         ";
					ghtml += "	<th class=\"tc\">습식용기<br/>렌탈비</th>                            ";
					ghtml += "	<td class=\"tr\"><p class=\"txt_04\">"+comma(sumrentalamt)+"</p></td>               ";
					ghtml += "	<th class=\"tc\">자조금</th>                                     ";
					ghtml += "	<td class=\"tr\"><p class=\"txt_04\">"+comma(sumselfamt)+"</p></td>          ";
					ghtml += "</tr>                                                           ";
					ghtml += "<tr>                                                            ";
					ghtml += "	<th class=\"tc\">하역비</th>                                     ";
					ghtml += "	<td class=\"tr\"><p class=\"txt_04\">"+comma(sumdownrep)+"</p></td>         ";
					ghtml += "	<th class=\"tc\">선도금상환</th>                                   ";
					ghtml += "	<td class=\"tr\"><p class=\"txt_04\">"+comma(sumsanghwan)+"</p></td>               ";
					ghtml += "	<th class=\"tc\">회비</th>                                      ";
					ghtml += "	<td class=\"tr\"><p class=\"txt_04\">"+comma(sumfeeamt)+"</p></td>          ";
					ghtml += "</tr>                                                           ";
					ghtml += "<tr>                                                            ";
					ghtml += "	<th class=\"tc\">재포장비</th>                                    ";
					ghtml += "	<td class=\"tr\"><p class=\"txt_04\">"+comma(sumreboxp)+"</p></td>         ";
					ghtml += "	<th class=\"tc\">백합자조금</th>                                   ";
					ghtml += "	<td class=\"tr\"><p class=\"txt_04\">"+comma(sumbaekamt)+"</p></td>               ";
					ghtml += "	<th class=\"tc\" rowspan=\"2\">공제액계</th>                                    ";
					ghtml += "	<td class=\"tr\" rowspan=\"2\"><p class=\"txt_04 bold\">"+comma(sumgongje)+"</p></td>     ";
					ghtml += "</tr>                                                           ";
					ghtml += "<tr>                                                            ";
					ghtml += "	<th class=\"tc\">운송비</th>                                     ";
					ghtml += "	<td class=\"tr\"><p class=\"txt_04\">"+comma(sumtransprice)+"</p></td>         ";
					ghtml += "	<th class=\"tc\">미수금상환</th>                                   ";
					ghtml += "	<td class=\"tr\"><p class=\"txt_04\">"+comma(sumtmisunuge)+"</p></td>               ";
					ghtml += "</tr>                                                           ";
					
					
		           }else{
		        	   $("#btnReport, #btnPrint, #btnExcell").css("display", "none");
		        	   html += "<tr><td class=\"tc\" colspan=\"10\">데이터가 없습니다.</td></tr>";
		        	   ghtml += "<tr><td class=\"tc\" colspan=\"6\">데이터가 없습니다.</td></tr>";
		           }
		           
		           $("#resultTbody").html(html);
		           $("#resultTfoot").html(fhtml);
		           $("#gongDetail").html(ghtml);
		           
		           $(".loadingWrap").hide();
		        }
		    });
			
		}
		
		
		function setSearchDateInit(dayCnt){
			
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
		
		
		function setBunChkInit(bunChk){
			$("#bunChk").val(bunChk);
			chulReset();
		}
		
		
		function fn_enterChulCode(){
			if (window.event.keyCode == 13) {
				getEnterChulList();
			}
		}
		
		
		function fn_enterSearchChulCode(){
			if (window.event.keyCode == 13) {
				getChulList();
			}
		}
		
		
		function getBunName(bunChk){
			if(bunChk == "N") return "절화";
			if(bunChk == "Y") return "난";
			if(bunChk == "C") return "관엽";
			
			return "";
		}
		
		
		function chkSize(){
			$("#disChulCode").prop("size", $("#disChulCode").val().length + 5);
		}
		
		
		function chulReset(){
			$("#chulCode").val("");
			$("#disChulCode").val("");
			$("#btnChulReset").css("display", "none");
			$("#mainContentDiv").css("display", "none");
			$("#chulCodeName").focus();
		}
		
		
		function getEnterChulList(){
			var searchChulCode = $("#chulCodeName").val();
			var bunChk = $("#bunChk").val();
			
			if(searchChulCode == null || searchChulCode ==""){
				alert("출하자명을 입력해주세요.");
				return false;
			}
			
			$.ajax({
				data:{
					searchChulCode: searchChulCode,
					bunChk: bunChk
			        },
		        type : "POST",
		        url : "${pageContext.request.contextPath}/front/stat/getChulList.json",
		        success : function(data){
		           var html = "";
		           var resultList = data.chulList;
		           chulReset();
		           
		           if(resultList.length > 1){
		        	   for(var i=0; i<resultList.length; i++ ){		        		
		           	    html += "<input type=\"hidden\" id=\"code"+i+"\" value=\""+resultList[i].code+"\"/>    ";
		   	   			html += "<input type=\"hidden\" id=\"name"+i+"\" value=\""+resultList[i].name+"\"/>      ";
		   	   			html += "<input type=\"hidden\" id=\"bunChk"+i+"\" value=\""+resultList[i].bunChk+"\"/>      ";
		   	   			html += "<input type=\"hidden\" id=\"gwanriZone"+i+"\" value=\""+resultList[i].gwanriZone+"\"/>      ";
		   	   			html += "<input type=\"hidden\" id=\"chulArea"+i+"\" value=\""+resultList[i].chulArea+"\"/>      ";
		   	   			html += "<input type=\"hidden\" id=\"cityGubn"+i+"\" value=\""+resultList[i].cityGubn+"\"/>  ";
		   	   			html += "<tr onclick=\"fn_chulClick('"+i+"')\" style=\"cursor: pointer;\">           ";
		   	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+resultList[i].name+"</a></td>       ";
		   	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+resultList[i].code.substring(0,4)+"-"+resultList[i].code.substring(4,8)+"</a></td>          ";
		   	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+resultList[i].chulArea+"</a></td>          ";
		   	   			html += "</tr>                                                                               ";
		           	   
		              }
		        	   $("#searchChulCode").val(searchChulCode);
		        	   layer_popup_adm("#layerSample");
		        	   $("#searchChulCode").focus();
		           }else if(resultList.length == 1){		        	   
		        	   $("#chulCode").val(resultList[0].code);
		        	   $("#disChulCode").val(resultList[0].name+" ( "+resultList[0].code+" )");
					   chkSize();
					   $("#btnChulReset").css("display", "");
		        	   $("#chulCodeName").val("");
		        	   $('#chulCodeName').blur();
		        	   $("#mainContentDiv").css("display", "none");
		           }else{
		        	   html += "<tr><td class=\"tc\" colspan=\"3\">검색된 정보가 없습니다.</td></tr>";
		        	   alert("검색된 정보가 없습니다.");
		        	   //$("#chulCodeName").val("");
		       		   $("#chulCode").val("");
		           }
		           
		           $("#chulTbody").html(html);
		        }
		    });
		}
		
		
		function getChulList(){
			var searchChulCode = $("#searchChulCode").val();
			var bunChk = $("#bunChk").val();
			
			if(searchChulCode == null || searchChulCode ==""){
				alert("출하자명을 입력해주세요.");
				return false;
			}
			
			$.ajax({
				data:{
					searchChulCode: searchChulCode,
					bunChk: bunChk
			        },
		        type : "POST",
		        url : "${pageContext.request.contextPath}/front/stat/getChulList.json",
		        success : function(data){
		           var html = "";
		           var resultList = data.chulList;
		           if(resultList.length > 0){
		        	   for(var i=0; i<resultList.length; i++ ){
		        	   	var bunName = getBunName(resultList[i].bunChk);

		           	    html += "<input type=\"hidden\" id=\"code"+i+"\" value=\""+resultList[i].code+"\"/>    ";
		   	   			html += "<input type=\"hidden\" id=\"name"+i+"\" value=\""+resultList[i].name+"\"/>      ";
		   	   			html += "<input type=\"hidden\" id=\"bunChk"+i+"\" value=\""+resultList[i].bunChk+"\"/>      ";
		   	   			html += "<input type=\"hidden\" id=\"gwanriZone"+i+"\" value=\""+resultList[i].gwanriZone+"\"/>      ";
		   	   			html += "<input type=\"hidden\" id=\"chulArea"+i+"\" value=\""+resultList[i].chulArea+"\"/>      ";
		   	   			html += "<input type=\"hidden\" id=\"cityGubn"+i+"\" value=\""+resultList[i].cityGubn+"\"/>  ";
		   	   			html += "<tr onclick=\"fn_chulClick('"+i+"')\" style=\"cursor: pointer;\">           ";
		   	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+resultList[i].name+"</a></td>       ";
		   	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+resultList[i].code.substring(0,4)+"-"+resultList[i].code.substring(4,8)+"</a></td>          ";
		   	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+resultList[i].chulArea+"</a></td>          ";
		   	   			html += "</tr>                                                                               ";
		   	   			
		              }
		        	   
		           }else{
		        	   html += "<tr><td class=\"tc\" colspan=\"4\">최근 정보가 없습니다</td></tr>";
		           }
		           
		           $("#chulTbody").html(html);
		        }
		    });
		}
		
		
		function fn_chulClick(index){
			if(confirm("선택한 정보를 가져오시겠습니까?")){
				var chulCode = $("#code"+index).val();
				var chulName = $("#name"+index).val();
				var bunChk = $("#bunChk"+index).val();
				var bunName = getBunName($("#bunChk"+index).val());
				/*
				$("#chulCode").html("<option value=\""+chulCode+"\">"+chulCode.substring(0,4)+" - "+chulCode.substring(4,8)+" ( "+chulName+" ) </option>");
				$("#chulCode").prop("value", chulCode);*/
				$("#chulCode").val(chulCode);
				$("#disChulCode").val(chulName+" ( "+chulCode+" )");
				chkSize();
				$("#btnChulReset").css("display", "");
				$("#chulCodeName").val("");
				$("#mainContentDiv").css("display", "none");
				$('.dim-layer').fadeOut();
				$('body').css('overflow','auto');
				$('body').css('position','relative');
			}
		}
		
		
		function fn_report(){
			var reportname = "shprSaleAllCalcDetail.ozr";
			var chulCode = $("#chulCode").val();
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			var bunChk = $("#bunChk").val();
			
			$('input[name=reportname]').val(reportname);
			$('input[name=setUrl]').val("${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/front/stat/shprSaleAllCalcDetail.json?chulCode="+chulCode+"&startDate="+startDate+"&endDate="+endDate+"&bunChk="+bunChk);
			
			$('#reportForm').submit();
		}
		
		
		function fn_excell(){
			//${pageContext.request.contextPath}/front/fixCAuc/reqAdmTableExcell.do?chulDate=<c:out value="${paramMap.chulDate}"/>
			var chulCode = $("#chulCode").val();
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			var bunChk = $("#bunChk").val();
			
			var excellUrl = "${pageContext.request.contextPath}/front/stat/shprSaleAllCalcDetailExcell.do?chulCode="+chulCode+"&startDate="+startDate+"&endDate="+endDate+"&bunChk="+bunChk;
			
			window.location.href = excellUrl;
			
		}
		
		var windowObject = null;
		
		function pageprint()
		{
			windowObject = window.open('',"printWindow","width=1024,height=500");
			windowObject.document.write(print_page.innerHTML);
			
			windowObject.onafterprint = afterPrint; 
			windowObject.print();
			
		}
		
		function afterPrint()
		{ 
			windowObject.close();
		} 
		
		
		function layer_popup_adm(el){
			  
			var $el = $(el);        //레이어의 id를 $el 변수에 저장
		  	var isDim = $el.prev().hasClass('dimBg');   //dimmed 레이어를 감지하기 위한 boolean 변수

			isDim ? $('.dim-layer').fadeIn() : $el.fadeIn();

		  	var $elWidth = $( window ).width(),
				$elHeight = $( document ).scrollTop(),
				docWidth = $(window).width(),
				docHeight = $(window).height();

		 	var popboxW = $el.find(".popbox").width()/2; 
		  	var popboxH = $el.find(".popbox").height()/2;
			
		  	$el.find(".popbox").css({
		  		marginTop: $elHeight + popboxH/2,
				// marginTop: $elHeight/2 - popboxH,
				marginLeft: $elWidth/2 - popboxW
		  	})
		  	// 화면의 중앙에 레이어를 띄운다.
		  	/*if ($elHeight < docHeight || $elWidth < docWidth) {
				$el.css({
				  marginTop: -$elHeight /2,
				  marginLeft: -$elWidth/2
				})
		  	} else {
				$el.css({top: 0, left: 0});
		  	}*/

		  	$el.find('a.btn-layerClose').click(function(){
				isDim ? $('.dim-layer').fadeOut() : $el.fadeOut(); // 닫기 버튼을 클릭하면 레이어가 닫힌다.
				$('body').css('overflow','auto');
				$('body').css('position','relative');
				$("#chulCodeName").focus();
				return false;
		  	});

		  	$('.layer .dimBg').click(function(){
				$('.dim-layer').fadeOut();
				return false;
		  	});
		  
		}
		
		
		</script>
		
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