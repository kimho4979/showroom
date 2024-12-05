<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
.sumtr td{background:#e5e0f3 !important; border: 1px solid #FFFFFF !important;}
.sumtr {border: 1px solid #777777 !important;}

</style>
 
  <!-- sub-conts(S) -->
		<div class="sub_conts bgw">

			<!-- sub상단(S) -->
			<div class="sub_top">
				<h2 class="sub_title">·판매정산내역[상세]·</h2>

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
							<h4 class="sub_tit_02">판매정산내역[상세]</h4>
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
									<p class="sb_title bold">구분</p>
									<div class="sel_type_01 w150">
										<select id="domeCode" name="domeCode">
											<c:forEach items="${floLoginList}" var="floLoginVO" varStatus="status" >
												<c:if test="${floLoginVO.aucType eq '02' }">
													<option value="${floLoginVO.chulCd}" data-bunchk="${floLoginVO.floMokCd}" <c:if test="${paramMap.domeCode eq floLoginVO.chulCd}">selected="selected"</c:if>>
														<c:if test="${floLoginVO.floMokCd eq 'N'}">절화</c:if>
														<c:if test="${floLoginVO.floMokCd eq 'Y'}">난</c:if>
														<c:if test="${floLoginVO.floMokCd eq 'C'}">관엽</c:if>
														${floLoginVO.chulCd}
													</option>
												</c:if>
											</c:forEach>
										</select>
										<label for="domeCode"></label>
									</div>
								</li>
							</ul>
							<ul class="sb_line">
								<li>
									<p class="sb_title bold">조회구분</p>
									<div class="sb_data">
										<div class="table_devide">
											<label class="radio">
												<input type="radio" name="searchType" value="sum" onclick="fn_search();" checked="checked">
												<i class="rdo"></i>
												<em class="label-title">공제합계</em>
											</label>
											<label class="radio">
												<input type="radio" name="searchType" value="detail" onclick="fn_search();">
												<i class="rdo"></i>
												<em class="label-title">공제상세</em>
											</label>
										</div>
									</div>
								</li>
							</ul>
							<ul class="sb_line">
								<li>
									<p class="sb_title bold">경매일자</p>
									<div class="sb_data">
										<div class="table_devide mt10">
											<div class="date_from_to">
												<div class="date_box">
													<fmt:parseDate value="${paramMap.panDate}" var="pandate" pattern="yyyyMMdd"/>
													<input type="text" id="panDate" name="panDate" class="datepicker" value="<fmt:formatDate value="${pandate}" pattern="yyyy-MM-dd"/>">
												</div>
											</div>
											<p class="txt_01 dib"></p>
										</div>
									</div>
								</li>
							</ul>
							<a href="javascript:fn_search();" class="btn_search">조회</a>
						</div>
						<p class="txt_w mt5">* 조회기간은 시작일자와 종료일자가 3개월 이내에서만 가능합니다.</p>
					</div>
					<!-- 검색조건창(E) -->

					<!-- 타이틀(S) -->
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
					<div class="title_box mt30">
						<div class="fl">
							<h4 class="sub_tit_04">판매정산내역</h4>
						</div>
						<div class="fr" id="bthDiv">
							<a href="javascript:fn_report();" id="btnReport" class="btn_type_01">리포트</a>
							<a onclick="pageprint()" class="btn_type_01">인쇄</a>
							<a href="javascript:fn_excell();" class="btn_type_01">엑셀다운로드</a>
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
									<td class="tl" colspan="3"><p class="txt_04" id="gName"></p></td>
								</tr>
								<tr>
									<th class="tc">화훼부류</th>
									<td class="tl" colspan="3"><p class="txt_04" id="bunName"></p></td>
								</tr>
								<tr>
									<th class="tc">경매기간</th>
									<td class="tl"><p class="txt_04" id="searchDate"></p></td>
									<th class="tc" id="th1">조회일자</th>
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
					<div class="table_type_04 mt5 overflow_a" id="detailDiv">
						<table>
							<caption>리스트</caption>
							<colgroup>
								<col id="list1col1" style="width:108px;">
								<col id="list1col2" style="width:99px;">
								<col id="list1col3" style="width:99px;">
								<col id="list1col4" style="width:99px;">
								<col id="list1col5" style="width:99px;">
								<col id="list1col6" style="width:99px;">
								<col id="list1col7" style="width:99px;">
								<col id="list1col8" style="width:99px;">
								<col id="list1col9" style="width:99px;">
								<col id="list1col10" style="width:99px;">
							</colgroup>
							<thead>
								<tr>
									<th class="tc">출하자</th>
									<th class="tc" id="th2">출하일자</th>
									<th class="tc">품목</th>
									<th class="tc">품종</th>
									<th class="tc">등급</th>
									<th class="tc">상자</th>
									<th class="tc">물량</th>
									<th class="tc">단가</th>
									<th class="tc">경매대금</th>
									<th class="tc">결과</th>
								</tr>
								<tr>
									<th class="tc">수수료</th>
									<th class="tc">하역비</th>
									<th class="tc">재포장비</th>
									<th class="tc">운송비</th>
									<th class="tc" colspan="2">습식상자대여료</th>
									<th class="tc" colspan="2">선도금상환액</th>
									<th class="tc">백합자조금</th>
									<th class="tc">미수금</th>
								</tr>
								<tr>
									<th class="tc">자조금</th>
									<th class="tc">회비</th>
									<th class="tc" colspan="6"></th>
									<th class="tc">공제액계</th>
									<th class="tc">지급금액</th>
								</tr>
							</thead>
							<tbody id="resultTbody">
								
							</tbody>
						</table>
					</div>
					<!-- 테이블04(E) -->
					
					<!-- 테이블04(S) -->
					<div class="table_type_04 mt5 overflow_a" id="sumDiv">
						<table>
							<caption>리스트</caption>
							<colgroup>
								<col style="width:80px;">
								<col style="width:80px;">
								<col style="width:100px;">
								<col style="width:100px;">
								<col style="width:80px;">
								<col style="width:60px;">
								<col style="width:60px;">
								<col style="width:80px;">
								<col style="width:80px;">
								<col style="width:80px;">
								<col style="width:80px;">
								<col style="width:80px;">
							</colgroup>
							<thead>
								<tr>
									<th class="tc">출하자</th>
									<th class="tc">출하일자</th>
									<th class="tc">품목</th>
									<th class="tc">품종</th>
									<th class="tc">등급</th>
									<th class="tc">상자</th>
									<th class="tc">물량</th>
									<th class="tc">단가</th>
									<th class="tc">경매대금</th>
									<th class="tc">공제액계</th>
									<th class="tc">지급금액</th>
									<th class="tc">결과</th>
								</tr>
							</thead>
							<tbody id="resultFbody">
								
							</tbody>
						</table>
					</div>
					<!-- 테이블04(E) -->
					</div>
					<p class="txt_w mt5">* 공제액계와 각 공제항목별 합산액이 다른 경우는 다른 상장건의 미공제 금액(지급액이 0(zero)인 상장건중 공제액이 경매대금보다 큰 경우의 차액)이 추가로 공제된 경우입니다.</p>
		

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
			fn_search();
			currentDate = $("#endDate").val();
		});
		
		
		function fn_search(){
			var domeCode = $("#domeCode").prop("value");
			var panDate = $("#panDate").val();
			var bunChk = $("#domeCode option:selected").attr("data-bunchk");
			var searchType = $("input:radio[name=searchType]:checked").val();
			
			if(searchType == "sum"){
				$("#detailDiv").css("display","none");
				$("#sumDiv").css("display","");
			}else{
				$("#detailDiv").css("display","");
				$("#sumDiv").css("display","none");
			}
			
			$.ajax({
				data:{
					domeCode: domeCode,
					panDate: panDate,
					bunChk: bunChk
			        },
		        type : "POST",
		        url : "${pageContext.request.contextPath}/front/stat/marketSaleCalcDetail.json",
		        success : function(data){
		           var html = "";
		           var fhtml = "";
		           var resultList = data.resultList;
		           var groupVO = data.groupVO;
		           
		           $("#gName").html(groupVO.gname);
		           $("#bunName").html(groupVO.bunchk);
		           $("#searchDate").html(panDate);
		           $("#currentDate").html(groupVO.today);
		           
		           
		           if(resultList.length > 0){
		        	   for(var i=0; i<resultList.length; i++ ){
		        		   
							var chulcode = ""+resultList[i].chulcode;
							var chulcodeText = "";
							var panday = "";
							var pmokname = "";
							var pjongname = "";
							var levelname = "";
							var kmlist = "";
							//경매결과 : 1.낙찰, 2.유찰, 3.기록  4.기타, 8.폐기대상, 9.폐기확정
							if(resultList[i].chulcode != null){
								
								chulcodeText = "<br>("+chulcode.substring(0,4)+"-"+chulcode.substring(4,8)+")";
								panday = resultList[i].panday;
								pmokname = resultList[i].pmokname;
								pjongname = resultList[i].pjongname;
								levelname = resultList[i].levelname;
								if(resultList[i].kmlist == "1"){
									kmlist = "낙찰";
								}else if(resultList[i].kmlist == "2"){
									kmlist = "유찰";
								}else if(resultList[i].kmlist == "3"){
									kmlist = "기록";
								}else if(resultList[i].kmlist == "4"){
									kmlist = "기타";
								}else if(resultList[i].kmlist == "8"){
									kmlist = "폐기대상";
								}else if(resultList[i].kmlist == "9"){
									kmlist = "폐기확정";
								}
								
								html+="<tr>                                                               ";

							}else{
								html+="<tr class=\"sumtr bold\">                                                               ";	
							}
							//공제상세
							var kmpNew = "";
							if(panday != "" && panday != null){
								kmpNew = comma(resultList[i].kmpnew);
							}
							
							if(bunChk == "Y"){
							 	if(pmokname == "팔레높시스"){
									 pmokname = "팔레높시스(호접란)"; 
				    		 	} else if(pmokname == '호접란'){
				    				 pmokname = "호접란(팔레높시스)";
				    		 	}
				    		 }
							  
							html+="	<td class=\"tc\"><p class=\"txt_01\">"+resultList[i].chulname+chulcodeText+"</p></td>                 ";
							html+="	<td class=\"tc\"><p class=\"txt_01\">"+panday+"</p></td>          ";
							html+="	<td class=\"tc\"><p class=\"txt_01\">"+pmokname+"</p></td>                  ";
							html+="	<td class=\"tc\"><p class=\"txt_01\">"+pjongname+"</p></td>          ";
							html+="	<td class=\"tc\"><p class=\"txt_01\">"+levelname+"</p></td>                  ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].boxcnt)+"</p></td>                   ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].sokcnt)+"</p></td>                  ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+kmpNew+"</p></td>              ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].panprice)+"</p></td>             ";
							html+="	<td class=\"tc\"><p class=\"txt_01\">"+kmlist+"</p></td>                  ";
							html+="</tr>                                                              ";
							if(resultList[i].chulcode != null){
								html+="<tr>                                                               ";	
							}else{
								html+="<tr class=\"sumtr bold\">                                                               ";
							}
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].susup)+"</p></td>              ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].downrep)+"</p></td>                   ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].reboxp)+"</p></td>                   ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].transprice)+"</p></td>               ";
							html+="	<td class=\"tr\" colspan=\"2\"><p class=\"txt_01\">"+comma(resultList[i].rentalamt)+"</p></td>     ";
							html+="	<td class=\"tr\" colspan=\"2\"><p class=\"txt_01\">"+comma(resultList[i].sanghwan)+"</p></td>     ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].baekamt)+"</p></td>                   ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].misunuge)+"</p></td>                   ";
							if(resultList[i].chulcode != null){
								html+="<tr>                                                               ";	
							}else{
								html+="<tr class=\"sumtr bold\">                                                               ";
							}
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].selfamt)+"</p></td>                   ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].feeamt)+"</p></td>                   ";
							html+="	<td class=\"tr\" colspan=\"6\"><p class=\"txt_01\"></p></td>      ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].gongjeamt)+"</p></td>              ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].chainamt)+"</p></td>             ";
							html+="</tr>                                                              ";
							
							//공제합계
							if(resultList[i].chulcode != null){	
								fhtml+="<tr>                                                               ";
							}else{
								fhtml+="<tr class=\"sumtr bold\">                                                               ";	
							}
							
							fhtml+="	<td class=\"tc\"><p class=\"txt_01\">"+resultList[i].chulname+chulcodeText+"</p></td>                 ";
							fhtml+="	<td class=\"tc\"><p class=\"txt_01\">"+panday+"</p></td>          ";
							fhtml+="	<td class=\"tc\"><p class=\"txt_01\">"+pmokname+"</p></td>                  ";
							fhtml+="	<td class=\"tc\"><p class=\"txt_01\">"+pjongname+"</p></td>          ";
							fhtml+="	<td class=\"tc\"><p class=\"txt_01\">"+levelname+"</p></td>                  ";
							fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].boxcnt)+"</p></td>                   ";
							fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].sokcnt)+"</p></td>                  ";
							fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+kmpNew+"</p></td>              ";
							fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].panprice)+"</p></td>             ";
							fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].gongjeamt)+"</p></td>             ";
							fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].chainamt)+"</p></td>             ";
							fhtml+="	<td class=\"tc\"><p class=\"txt_01\">"+kmlist+"</p></td>                  ";
							fhtml+="</tr>                                                              ";
							
							$("#btnReport").css("display", "");
		        	   }
		        	   
		           }else{
		        	   html += "<tr><td class=\"tc\" colspan=\"10\">데이터가 없습니다.</td></tr>";
		        	   fhtml += "<tr><td class=\"tc\" colspan=\"12\">데이터가 없습니다.</td></tr>";
		        	   
		        	   $("#btnReport").css("display", "none");
		           }
		           
		           $("#resultTbody").html(html);
		           $("#resultFbody").html(fhtml);
			       
		        }
		    });
			
		}
		
		
		function fn_report(){
			var reportname = "";
			var searchType = $("input:radio[name=searchType]:checked").val();
			
			if(searchType == "sum"){
				reportname = "marketSaleCalcDetail_Sum.ozr";
			}else{
				reportname = "marketSaleCalcDetail_Detail.ozr";
			}
			
			var domeCode = $("#domeCode").prop("value");
			var panDate = $("#panDate").val();
			var bunChk = $("#domeCode option:selected").attr("data-bunchk");
						
			$('input[name=reportname]').val(reportname);
			$('input[name=setUrl]').val("${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/front/stat/marketSaleCalcDetail.json?domeCode="+domeCode+"&panDate="+panDate+"&bunChk="+bunChk);
						
			$('#reportForm').submit();
		}
		
		
		
		function fn_excell(){
			//${pageContext.request.contextPath}/front/fixCAuc/reqAdmTableExcell.do?chulDate=<c:out value="${paramMap.chulDate}"/>
			var domeCode = $("#domeCode").prop("value");
			var panDate = $("#panDate").val();
			var bunChk = $("#domeCode option:selected").attr("data-bunchk");
			
			var excellUrl = "${pageContext.request.contextPath}/front/stat/marketSaleCalcDetailExcell.do?domeCode="+domeCode+"&panDate="+panDate+"&bunChk="+bunChk;
			
			window.location.href = excellUrl;
			
		}
		
		/*
		var initBody;
		var printDomeCode;
		var printSearchType;
		var printpanDate;
		
		function beforePrint()
		{ 
			printDomeCode = $("#domeCode").prop("value");
			printSearchType = $("input:radio[name=searchType]:checked").val();;
			printPanDate = $("#panDate").val();
			initBody = document.body.innerHTML;
			document.body.innerHTML = print_page.innerHTML;
		} 

		function afterPrint()
		{ 
			document.body.innerHTML = initBody; 
			$("#panDate").val(printPanDate);
			$("#input:radio[name=searchType]").prop("value",printSearchType);
			$("#domeCode").prop("value",printDomeCode);
		} 

		function pageprint()
		{
			
			window.onbeforeprint = beforePrint; 
			window.onafterprint = afterPrint; 
			window.print(); 
		}*/
		
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
		
		
		</script>