<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
		 
		
		<div class="sub_conts bgw">

			<!-- sub상단(S) -->
			<div class="sub_top">
				<h2 class="sub_title">·거래원장·</h2>

				<!-- 네비게이션(S) -->
				<jsp:include page="/WEB-INF/jsp/front/inc/statNav.jsp"/>
				<!-- 네비게이션(E) -->
			</div>
			<!-- sub상단(E) -->
			
			<!-- sub내용(S) -->
			<div class="sub_conts_in">
			<jsp:include page="/WEB-INF/jsp/front/login/statTab.jsp"/>

				<div class="info_box">
					<div class="title_box">
						<div class="fl">
							<h4 class="sub_tit_02">거래원장</h4>
						</div>
					</div>

					<!-- 타이틀(S) -->
					<div class="title_box mt30">
						<div class="fl">
							<h4 class="sub_tit_04">거래원장조회</h4>
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
						<p class="txt_w mt5">* 조회기간은 시작일자와 종료일자가 3개월 이내에서만 가능합니다.</p>
					</div>
					<!-- 검색조건창(E) -->

					<!-- 타이틀(S) -->
					<div class="title_box mt30">
						<div class="fl">
							<h4 class="sub_tit_04">거래원장 정산내역</h4>
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
							<caption>거래원장정산내역</caption>
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
								<col style="width:82.75px;">
								<col style="width:82.75px;">
								<col style="width:82.75px;">
								<col style="width:82.75px;">
								<col style="width:82.75px;">
								<col style="width:82.75px;">
								<col style="width:82.75px;">
								<col style="width:82.75px;">
								<col style="width:82.75px;">
								<col style="width:82.75px;">
								<col style="width:82.75px;">
								<col style="width:82.75px;">
							</colgroup>
							<thead>
								<tr>
									<th class="tc" rowspan="2">거래일자</th>
									<th class="tc" rowspan="2">적요</th>
									<th class="tc" rowspan="2">매입액</th>
									<th class="tc" colspan="3">입금액</th>
									<th class="tc" colspan="3">미수금</th>
									<th class="tc" colspan="2">이자부과</th>
									<th class="tc" rowspan="2">경매가능<br/>금액</th>
								</tr>
								<tr>
									<th>입금총액</th>
									<th>미수원금</th>
									<th>이자</th>
									<th>미수총액</th>
									<th>미수원금</th>
									<th>이자잔액</th>
									<th>이자부과액</th>
									<th>이자산출<br/>대상금액</th>
								</tr>
							</thead>
							<tbody id="resultTbody">
								
							</tbody>
							<tfoot id="resultTfoot">
	
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
		var currentDate = "";
		
		$(document).ready(function(){
			setSearchDateInit(7);
			currentDate = $("#endDate").val();
		});
		
		
		function fn_search(){
			var domeCode = $("#domeCode").prop("value");
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			var bunChk = $("#domeCode option:selected").attr("data-bunchk");
			
			$.ajax({
				data:{
					domeCode: domeCode,
					startDate: startDate,
					endDate: endDate,
					bunChk: bunChk
			        },
		        type : "POST",
		        url : "${pageContext.request.contextPath}/front/stat/whslDealBlotter.json",
		        success : function(data){
		           var html = "";
		           var fhtml = "";
		           var resultList = data.resultList;
		           var sumVO = data.sumVO;
		           var nameVO = data.nameVO;
		           console.log(resultList);
		           var gName = "";
		           var bunName = "";
		           
		           if(nameVO != null){
		        	   gName = nameVO.name;
		           }
		           
		           if(bunChk == 'N'){
		        	   bunName = "절화";
		           }else if(bunChk == 'Y'){
		        	   bunName = "난";
		           }else if(bunChk == 'C'){
		        	   bunName = "관엽";
		           }
		           
	        	   //var domeName = resultList.name;
			    	 
		    	   //gName = domeName + "("+domeCode+")";
		    	   
		    	   $("#gName").html(gName);
		           //$("#gName").html(gName);
		           $("#bunName").html(bunName);
		           $("#searchDate").html(startDate + " ~ " + endDate);
		           $("#currentDate").html(currentDate);
		           
		        
		           if(resultList.length > 0){

		        	   for(var i=0; i<resultList.length; i++ ){
		        		   html+="<tr>" ;
		        		    html+="	<td class=\"tc\"><p class=\"txt_01\">"+resultList[i].jday+"</p></td>  "; //거래일자
							html+="	<td class=\"tc\"><p class=\"txt_01\">"+resultList[i].summary+"</p></td>  "; //적요
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].meip)+"</p></td>  "; //매입액
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].ipgum)+"</p></td>  "; //입금총액
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].orgmeip)+"</p></td>  "; // 미수원금
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].todayret)+"</p></td>  "; //이자
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].misutotal)+"</p></td>  "; //미수총액
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].unptot)+"</p></td>  "; // 미수원금
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].dlytot)+"</p></td>  "; // 이자잔액
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].todaydly)+"</p></td>  "; // 이자부과액 =당일이자상환액
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].dlyptot)+"</p></td>  "; // 이자산출 대상금액
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].todaybo)+"</p></td>  "; // 경매 가능금액
							html+="</tr>                                                                  ";
		        	   }
		        	   
		        	    fhtml+="<tr>                                                                   ";
						fhtml+="	<td class=\"tc\"><p class=\"txt_01\">합계</p></td>  ";
						fhtml+="	<td class=\"tc\"><p class=\"txt_01\"></p></td>            ";
						fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(sumVO.meipsum)+"</p></td>  ";
						fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(sumVO.ipgumsum)+"</p></td>  ";
						fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(sumVO.orgmeipsum)+"</p></td>  ";
						fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(sumVO.todayretsum)+"</p></td>  ";
						fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(sumVO.misutotalsum)+"</p></td>  ";
						fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(sumVO.unptotsum)+"</p></td>  ";
						fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(sumVO.dlytotsum)+"</p></td>  ";
						fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(sumVO.todaydlysum)+"</p></td>  ";
						fhtml+="	<td class=\"tc\"><p class=\"txt_01\"></p></td>            ";
						fhtml+="	<td class=\"tc\"><p class=\"txt_01\"></p></td>            ";
	
						fhtml+="</tr>                                                                  ";
						
						$("#btnReport").css("display", "");
		           }else{
		        	   html += "<tr><td class=\"tc\" colspan=\"12\">데이터가 없습니다.</td></tr>";
		        	   
		        	   $("#btnReport").css("display", "none");
		           }
		           
		           $("#resultTbody").html(html);
		           $("#resultTfoot").html(fhtml);
			       
		        }
		    });
			
		}
		
		function fn_excell(){
			//${pageContext.request.contextPath}/front/fixCAuc/reqAdmTableExcell.do?chulDate=<c:out value="${paramMap.chulDate}"/>
			var domeCode = $("#domeCode").prop("value");
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			var bunChk = $("#domeCode option:selected").attr("data-bunchk");
			
			var excellUrl = "${pageContext.request.contextPath}/front/stat/whslDealBlotterExcell.do?domeCode="+domeCode+"&startDate="+startDate+"&endDate="+endDate+"&bunChk="+bunChk;
			
			window.location.href = excellUrl;
			
		}
		
		function fn_report(){
			var reportname = "whslDealBlotter.ozr";
			var domeCode = $("#domeCode").prop("value");
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			var bunChk = $("#domeCode option:selected").attr("data-bunchk");
			
			$('input[name=reportname]').val(reportname);
			$('input[name=setUrl]').val("${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/front/stat/whslDealBlotter.json?domeCode="+domeCode+"&startDate="+startDate+"&endDate="+endDate+"&bunChk="+bunChk);
			
			$('#reportForm').submit();
		}
		
		</script>
