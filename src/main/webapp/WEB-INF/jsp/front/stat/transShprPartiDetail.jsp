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
				<h2 class="sub_title">·출하자별 운송내역[상세]·</h2>

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
							<h4 class="sub_tit_02">출하자별 운송내역[상세]</h4>
						</div>
					</div>

					<!-- 타이틀(S) -->
					<div class="title_box mt30">
						<div class="fl">
							<h4 class="sub_tit_04">출하자별 운송내역[상세] 검색</h4>
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
										<select id="transCode" name="transCode">
											<c:forEach items="${floLoginList}" var="floLoginVO" varStatus="status" >
												<c:if test="${floLoginVO.aucType eq '04' }">
													<option value="${floLoginVO.chulCd}" data-bunchk="${floLoginVO.floMokCd}">
														${floLoginVO.chulCd}
													</option>
												</c:if>
											</c:forEach>
										</select>
										<label for="transCode"></label>
									</div>
								</li>
							</ul>
							<ul class="sb_line">
								<li>
									<p class="sb_title bold">화훼부류</p>
									<div class="sb_data">
										<div class="table_devide">
											<label class="radio">
												<input type="radio" name="bunChk" value="N" <c:if test="${paramMap.bunChk eq 'N' }">checked="checked"</c:if>>
												<i class="rdo"></i>
												<em class="label-title">절화</em>
											</label>
											<label class="radio">
												<input type="radio" name="bunChk" value="Y" <c:if test="${paramMap.bunChk eq 'Y' }">checked="checked"</c:if>>
												<i class="rdo"></i>
												<em class="label-title">난</em>
											</label>
											<label class="radio">
												<input type="radio" name="bunChk" value="C" <c:if test="${paramMap.bunChk eq 'C' }">checked="checked"</c:if>>
												<i class="rdo"></i>
												<em class="label-title">관엽</em>
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
					</div>
					<!-- 검색조건창(E) -->

					<!-- 타이틀(S) -->
					<div class="title_box mt30">
						<div class="fl">
							<h4 class="sub_tit_04">출하자별 운송내역[상세]</h4>
						</div>
						<div class="fr">
							<a href="javascript:fn_report();" id="btnReport" class="btn_type_01">리포트</a>
							<a href="javascript:fn_excell();" class="btn_type_01">엑셀다운로드</a>
						</div>
					</div>
					<!-- 타이틀(E) -->

					<!-- 테이블03(S) -->
					<div class="table_type_03 mt5" id="infoDiv">
						<table>
							<caption>출하자별 운송내역[상세]</caption>
							<colgroup>
								<col style="width:17%">
								<col style="width:33%">
								<col style="width:17%">
								<col style="width:33%">
							</colgroup>
							<tbody>
								<tr>
									<th class="tc">운송업체</th>
									<td class="tl" colspan="3"><p class="txt_04" id="gName"></p></td>
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
								<col style="width:100px;">
								<col style="width:100px;">
								<col style="width:90px;">
								<col style="width:100px;">
								<col style="width:100px;">
								<col style="width:100px;">
								<col style="width:100px;">
								<col style="width:100px;">
								<col style="width:100px;">
								<col style="width:100px;">
							</colgroup>
							<thead>
								<tr>
									<th class="tc">경매일자</th>
									<th class="tc">코드</th>
									<th class="tc">성명</th>
									<th class="tc">상장번호</th>
									<th class="tc">규격</th>
									<th class="tc">비규격</th>
									<th class="tc">합계</th>
									<th class="tc">운송비</th>
									<th class="tc">KG</th>
									<th class="tc">결과</th>
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
			
			var endDate = new Date(); 
			var endYear = endDate.getFullYear(); 
			var endMonth = new String(endDate.getMonth()+1); 
			var endDay = new String(endDate.getDate()); 
			
			if(endMonth.length == 1){ 
				endMonth = "0" + endMonth; 
			} 
			
			if(endDay.length == 1){ 
				endDay = "0" + endDay; 
			} 
			
			currentDate = endYear+"-"+endMonth+"-"+endDay;
			
			fn_search();
			
		});
		
		
		
		function fn_search(){
			var transCode = $("#transCode").prop("value");
			var panDate = $("#panDate").val();
			var bunChk = $("input:radio[name=bunChk]:checked").val();
			
			$.ajax({
				data:{
					transCode: transCode,
					panDate: panDate,
					bunChk: bunChk
			        },
		        type : "POST",
		        url : "${pageContext.request.contextPath}/front/stat/transShprPartiDetail.json",
		        success : function(data){
		           var html = "";
		           var fhtml = "";
		           var resultList = data.resultList;
		           var sumVO = data.sumVO;
		           var bunName = "";
		           
		           if(bunChk == 'N'){
		        	   bunName = "절화";
		           }else if(bunChk == 'Y'){
		        	   bunName = "난";
		           }else if(bunChk == 'C'){
		        	   bunName = "관엽";
		           }
		           
		           $("#gName").html(transCode);
		           $("#bunName").html(bunName);
		           $("#searchDate").html(panDate);
		           $("#currentDate").html(currentDate);
		           
		           console.log(data);
		           var kmlist = "";
		           
		           if(resultList.length > 0){
		        	   for(var i=0; i<resultList.length; i++ ){
		        		   
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
		        		   
		        		    html+="<tr>                                                                   ";
							html+="	<td class=\"tc\"><p class=\"txt_01\">"+resultList[i].panday+"</p></td>  ";
							html+="	<td class=\"tc\"><p class=\"txt_01\">"+resultList[i].chulcode+"</p></td>  ";
							html+="	<td class=\"tc\"><p class=\"txt_01\">"+resultList[i].chulname+"</p></td>  ";
							html+="	<td class=\"tc\"><p class=\"txt_01\">"+resultList[i].panno+"</p></td>  ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].sboxcnt)+"</p></td>  ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].uboxcnt)+"</p></td>  ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].tboxcnt)+"</p></td>  ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].trpri)+"</p></td>  ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].pumkg)+"</p></td>  ";
							html+="	<td class=\"tc\"><p class=\"txt_01\">"+kmlist+"</p></td>  ";
							html+="</tr>                                                                  ";
		        	   }
		        	   
		        	    fhtml+="<tr>                                                                   ";
						fhtml+="	<td class=\"tc\"><p class=\"txt_01\">합계</p></td>  ";
						fhtml+="	<td class=\"tc\"><p class=\"txt_01\"></p></td>  ";
						fhtml+="	<td class=\"tc\"><p class=\"txt_01\"></p></td>  ";
						fhtml+="	<td class=\"tc\"><p class=\"txt_01\"></p></td>  ";
						fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(sumVO.sboxcntsum)+"</p></td>  ";
						fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(sumVO.uboxcntsum)+"</p></td>  ";
						fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(sumVO.tboxcntsum)+"</p></td>  ";
						fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(sumVO.trprisum)+"</p></td>  ";
						fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(sumVO.pumkgsum)+"</p></td>  ";
						fhtml+="	<td class=\"tc\"><p class=\"txt_01\"></p></td>  ";
						fhtml+="</tr>                                                                  ";
						
						$("#btnReport").css("display", "");
		           }else{
		        	   html += "<tr><td class=\"tc\" colspan=\"10\">데이터가 없습니다.</td></tr>";
		        	   
		        	   $("#btnReport").css("display", "none");
		           }
		           
		           $("#resultTbody").html(html);
		           $("#resultTfoot").html(fhtml);
			       
		        }
		    });
			
		}
		
		function fn_view(panDate){
			var transCode = $("#transCode").prop("value");
			var strPanDate = ""+panDate;
			var bunChk = $("input:radio[name=bunChk]:checked").val();
			window.location.href = "${pageContext.request.contextPath}/front/stat/transShprPartiDetail.do?panDate="+strPanDate+"&transCode="+transCode+"&bunChk="+bunChk;
		}
		

		function fn_excell(){
			//${pageContext.request.contextPath}/front/fixCAuc/reqAdmTableExcell.do?chulDate=<c:out value="${paramMap.chulDate}"/>
			var transCode = $("#transCode").prop("value");
			var panDate = $("#panDate").val();
			var bunChk = $("input:radio[name=bunChk]:checked").val();
			
			var excellUrl = "${pageContext.request.contextPath}/front/stat/transShprPartiDetailExcell.do?transCode="+transCode+"&panDate="+panDate+"&bunChk="+bunChk;
			
			window.location.href = excellUrl;
			
		}
		
		
		function fn_report(){
			var reportname = "transShprPartiDetail.ozr";
			var transCode = $("#transCode").prop("value");
			var panDate = $("#panDate").val();
			var bunChk = $("input:radio[name=bunChk]:checked").val();
			
			$('input[name=reportname]').val(reportname);
			$('input[name=setUrl]').val("${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/front/stat/transShprPartiDetail.json?transCode="+transCode+"&panDate="+panDate+"&bunChk="+bunChk);
			
			$('#reportForm').submit();
		}
		
		
		</script>