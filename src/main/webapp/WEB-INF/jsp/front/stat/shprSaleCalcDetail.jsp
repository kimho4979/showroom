<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
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
										<select id="chulCode" name="chulCode">
											<c:forEach items="${floLoginList}" var="floLoginVO" varStatus="status" >
												<c:if test="${floLoginVO.aucType eq '01' }">
													<option value="${floLoginVO.chulCd}" data-bunchk="${floLoginVO.floMokCd}" <c:if test="${paramMap.chulCode eq floLoginVO.chulCd}">selected="selected"</c:if>>
														<c:if test="${floLoginVO.floMokCd eq 'N'}">절화</c:if>
														<c:if test="${floLoginVO.floMokCd eq 'Y'}">난</c:if>
														<c:if test="${floLoginVO.floMokCd eq 'C'}">관엽</c:if>
														${floLoginVO.chulCd}
													</option>
												</c:if>
											</c:forEach>
										</select>
										<label for="chulCode"></label>
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
					
					<!-- 타이틀(S) -->
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
					<p class="txt_w mt5">* 검색결과 내용에서 해당일자를 클릭하시면 상세내역을 보실 수 있습니다.</p>

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
			
		});
		
		
		function fn_search(){
			var chulCode = $("#chulCode").prop("value");
			var panDate = $("#panDate").val();
			var bunChk = $("#chulCode option:selected").attr("data-bunchk");
			
			
			$.ajax({
				data:{
					chulCode: chulCode,
					panDate: panDate,
					bunChk: bunChk
			        },
		        type : "POST",
		        url : "${pageContext.request.contextPath}/front/stat/shprSaleCalcDetail.json",
		        success : function(data){
		        	console.log(data);
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
		           
		           $("#chulName").html(chulVO.name);
		           $("#bunName").html(chulVO.bunchknm);
		           $("#searchDate").html(panDate);
		           $("#currentDate").html(chulVO.today);
		           
		           
		           if(resultList.length > 0){
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
							
							if(bunChk == "Y"){						
						    	if(resultList[i].pumcode.substring(0,4) == "6002"){
							  	 	pmokname = "팔레높시스(호접란)"; 
			    		  		} else if(resultList[i].pumcode.substring(0,4) == '6043'){
			    			   		pmokname = "호접란(팔레높시스)";
			    		    	}
			    		    }
			    		    
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
					
					$("#btnReport").css("display", "");
		           }else{
		        	   html += "<tr><td class=\"tc\" colspan=\"10\">데이터가 없습니다.</td></tr>";
		        	   ghtml += "<tr><td class=\"tc\" colspan=\"6\">데이터가 없습니다.</td></tr>";
		        	   
		        	   $("#btnReport").css("display", "none");
		           }
		           
		           $("#resultTbody").html(html);
		           $("#resultTfoot").html(fhtml);
		           $("#gongDetail").html(ghtml);
		           
		        }
		    });
			
		}
		
		
		function fn_report(){
			var reportname = "shprSaleCalcDetail.ozr";
			var chulCode = $("#chulCode").prop("value");
			var panDate = $("#panDate").val();
			var bunChk = $("#chulCode option:selected").attr("data-bunchk");
			
			$('input[name=reportname]').val(reportname);
			$('input[name=setUrl]').val("${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/front/stat/shprSaleCalcDetail.json?chulCode="+chulCode+"&panDate="+panDate+"&bunChk="+bunChk);
			
			$('#reportForm').submit();
		}
		
		
		function fn_excell(){
			//${pageContext.request.contextPath}/front/fixCAuc/reqAdmTableExcell.do?chulDate=<c:out value="${paramMap.chulDate}"/>
			var chulCode = $("#chulCode").prop("value");
			var panDate = $("#panDate").val();
			var bunChk = $("#chulCode option:selected").attr("data-bunchk");
			
			var excellUrl = "${pageContext.request.contextPath}/front/stat/shprSaleCalcDetailExcell.do?chulCode="+chulCode+"&panDate="+panDate+"&bunChk="+bunChk;
			
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
		
		
		</script>