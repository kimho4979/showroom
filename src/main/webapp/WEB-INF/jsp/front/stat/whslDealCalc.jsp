<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
				<h2 class="sub_title">·거래내역(낙찰서)·</h2>

				<!-- 네비게이션(S) -->
				<jsp:include page="/WEB-INF/jsp/front/inc/statNav.jsp"/>
				<!-- 네비게이션(E) -->
			</div>
			<!-- sub상단(E) -->
			
			<!-- sub내용(S) -->
			<div class="sub_conts_in">
			<jsp:include page="/WEB-INF/jsp/front/login/statTab.jsp"/>
				<div class="info_box">
					<div class="title_box mt30">
						<div class="fl">
							<h4 class="sub_tit_02">거래내역(낙찰서)</h4>
						</div>
					</div>
					
					<!-- 타이틀(S) -->
					<div class="title_box mt30">
						<div class="fl">
							<h4 class="sub_tit_04">거래내역(낙찰서) 검색</h4>
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
												<c:if test="${floLoginVO.aucType eq '03' }">
													<option value="${floLoginVO.chulCd}" data-bunchk="${floLoginVO.floMokCd}">
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
									<p class="sb_title bold">경매일자</p>
									<div class="sb_data">
										<div class="table_devide mt10">
											<div class="date_from_to">
												<div class="date_box">
													<fmt:parseDate value="${dateVO.mpanday}" var="pandate" pattern="yyyyMMdd"/>
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
						<!-- <p class="txt_w mt5">* 조회기간은 시작일자와 종료일자가 3개월 이내에서만 가능합니다.</p>-->
					</div>
					<!-- 검색조건창(E) -->

					<!-- 타이틀(S) -->
					<div class="title_box mt30">
						<div class="fl">
							<h4 class="sub_tit_04">거래정산내역</h4>
						</div>
						<div class="fr">
							<a href="javascript:fn_report();" id="btnReport" class="btn_type_01">리포트</a>
							<a href="javascript:fn_excell();" class="btn_type_01">엑셀다운로드</a>
						</div>
					</div>
					<!-- 타이틀(E) -->

					<!-- 테이블03(S) -->
					<div class="table_type_03 mt5">
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
									<th class="tc">중도매인</th>
									<td class="tl" colspan="3"><p class="txt_04" id="gName"></p></td>
								</tr>
								<tr>
									<th class="tc">화훼부류</th>
									<td class="tl" colspan="3"><p class="txt_04" id="bunName"></p></td>
								</tr>
								<tr>
									<th class="tc">경매일자</th>
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
								<col style="width:150px;">
								<col style="width:150px;">
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
									<th class="tc">품목명</th>
									<th class="tc">품종명</th>
									<th class="tc">등급</th>
									<th class="tc">상자수</th>
									<th class="tc">속수량</th>
									<th class="tc">단가</th>
									<th class="tc">매입금액</th>
									<th class="tc">상장번호</th>
									<th class="tc">출하자</th>
								</tr>
							</thead>
							<tbody id="resulTbody">
								
							</tbody>
							<tfoot id="resulTfoot">
								
							</tfoot>
						</table>
					</div>
					<!-- 테이블04(E) -->



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
		
		$(document).ready(function(){
			setPanDateInit();
			currentDate = $("#currentDate").val();
		});
		
		var curDate = new Date();
		var toDay = curDate.getFullYear()+ '-'+(curDate.getMonth()+1)+'-'+curDate.getDate();
		function setPanDateInit(){
			var domeCode = $("#domeCode").prop("value");
			var panDate = $("#panDate").val();
			var bunChk = $("#domeCode option:selected").attr("data-bunchk");
			var curDate = new Date();
			var toDay = curDate.getFullYear()+ '-'+(curDate.getMonth()+1)+'-'+curDate.getDate();
			
			$.ajax({
				data:{
					domeCode: domeCode,
					panDate: panDate,
					bunChk: bunChk,
					selectType : 'date'
			        },
			    type : "POST",
			    url : "${pageContext.request.contextPath}/front/stat/whslDealCalc.json",
			    success : function(data){
			       var dateVO = data.dateVO;

			       var setDt = dateVO.mpanday;
			       var setYear = setDt.substr(0,4);
			       var setMon = setDt.substr(4,2);
			       var setDay = setDt.substr(6,2);
			       var complDt = setYear + "-" + setMon + "-" + setDay;
			       
			       $("#panDate").datepicker('setDate',complDt);
			       $("#searchDate").html(complDt);
			       
			       fn_search();
			       
			    }
			});
		} 
		
		function fn_search(){
		var domeCode = $("#domeCode").prop("value");
		var panDate = $("#panDate").val();
		var bunChk = $("#domeCode option:selected").attr("data-bunchk");
		var curDate = new Date();
		var toDay = curDate.getFullYear()+ '-'+(curDate.getMonth()+1)+'-'+curDate.getDate();
		//console.log(toDay);
		
		$.ajax({
			data:{
				domeCode: domeCode,
				panDate: panDate,
				bunChk: bunChk,
				selectType : 'data'
		        },
		    type : "POST",
		    url : "${pageContext.request.contextPath}/front/stat/whslDealCalc.json",
		    success : function(data){
		       var html = "";
		       var fhtml = "";
		       var resultList = data.resultList;
		       var sumVO = data.sumVO;
		       var totVO = data.totVO;
		       var gName = "";
		       var bunName = "";
		       
		       if(bunChk == 'N'){
		    	   bunName = "절화";
		       }else if(bunChk == 'Y'){
		    	   bunName = "난";
		       }else if(bunChk == 'C'){
		    	   bunName = "관엽";
		       }
		
		       $("#bunName").html(bunName);
		       $("#currentDate").html(toDay);
		       $("#searchDate").html(panDate);
		       
		       if(resultList.length > 0){
		    	   var domeName = resultList[0].domename;
		    	 
		    	   gName = domeName + "("+domeCode+")";
		    	   $("#gName").html(gName);
		    	   
		    	   for(var i=0; i<resultList.length; i++ ){
		    	   
		    		   if(bunChk == 'Y'){
		    		   		if(resultList[i].pumcode.substring(0,4) == "6002"){
		    			   		resultList[i].pmokname = "팔레높시스(호접란)"; 
		    		   		} else if(resultList[i].pumcode.substring(0,4) == '6043'){
		    					resultList[i].pmokname = "호접란(팔레높시스)";
		    		   		}
		    		   }
		    		  
		    		    html+="<tr>                                                                   ";
		    		   	html+="	<td class=\"tc\"><p class=\"txt_01\">"+resultList[i].pmokname+"</p></td>  ";
						html+="	<td class=\"tc\"><p class=\"txt_01\">"+resultList[i].pjongname+"</p></td>  ";
						html+="	<td class=\"tc\"><p class=\"txt_01\">"+resultList[i].levelname+"</p></td>  ";
						html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].pboxcnt)+"</p></td>  ";
						html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].psokcnt)+"</p></td>  ";
						html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].kmpnew)+"</p></td>  ";
						html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].panprice)+"</p></td>  ";
						html+="	<td class=\"tc\"><p class=\"txt_01\">"+resultList[i].panno+"</p></td>  ";
						html+="	<td class=\"tc\"><p class=\"txt_01\">"+resultList[i].chulname+"</p></td>  ";
						html+="</tr>                                                                  ";
		    	   }
		    	   
		    	    fhtml+="<tr>                                                                   ";
					fhtml+="	<td class=\"tc\"><p class=\"txt_01\">합계</p></td>  ";
					fhtml+="	<td class=\"tc\"><p class=\"txt_01\"></p></td>  ";
					fhtml+="	<td class=\"tc\"><p class=\"txt_01\"></p></td>  ";
					fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(totVO.pboxcnttot)+"</p></td>  ";
					fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(totVO.psokcnttot)+"</p></td>  ";
					fhtml+="	<td class=\"tc\"><p class=\"txt_01\"></p></td>  ";
					fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(totVO.panpricetot)+"</p></td>  ";
					fhtml+="	<td class=\"tc\"><p class=\"txt_01\"></p></td>            ";
					fhtml+="	<td class=\"tc\"><p class=\"txt_01\"></p></td>  ";
					fhtml+="</tr> ";
					
					$("#btnReport").css("display", "");
		       }else{
		    	   html += "<tr><td class=\"tc\" colspan=\"9\">데이터가 없습니다.</td></tr>";
		    	   
		    	   $("#btnReport").css("display", "none");
		       }
		
		       
		       $("#resulTbody").html(html);
		       $("#resulTfoot").html(fhtml);
		       
		    }
		});
			
	}
		
		function fn_excell(){
			//${pageContext.request.contextPath}/front/fixCAuc/reqAdmTableExcell.do?chulDate=<c:out value="${paramMap.chulDate}"/>
			var domeCode = $("#domeCode").prop("value");
			var panDate = $("#panDate").val();
			var bunChk = $("#domeCode option:selected").attr("data-bunchk");
			
			var excellUrl = "${pageContext.request.contextPath}/front/stat/whslDealCalcExcell.do?domeCode="+domeCode+"&panDate="+panDate+"&bunChk="+bunChk;
			
			window.location.href = excellUrl;
			
		}
		
		
		function fn_report(){
			var reportname = "whslDealCalc.ozr";
			var domeCode = $("#domeCode").prop("value");
			var panDate = $("#panDate").val();
			var bunChk = $("#domeCode option:selected").attr("data-bunchk");
			
			$('input[name=reportname]').val(reportname);
			$('input[name=setUrl]').val("${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/front/stat/whslDealCalc.json?domeCode="+domeCode+"&panDate="+panDate+"&bunChk="+bunChk+"&selectType=data");
			
			$('#reportForm').submit();
		}
				
				
					
		</script>