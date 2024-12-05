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
				<h2 class="sub_title">·공동출하장려금·</h2>

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
							<h4 class="sub_tit_02">공동출하장려금</h4>
						</div>
					</div>

					<!-- 타이틀(S) -->
					<div class="title_box mt30">
						<div class="fl">
							<h4 class="sub_tit_04">공동출하장려금 검색</h4>
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
												<input type="radio" name="searchType" value="detail" checked="checked" onclick="fn_search();">
												<i class="rdo"></i>
												<em class="label-title">기간검색</em>
											</label>
											<label class="radio">
												<input type="radio" name="searchType" value="sum" onclick="fn_search();">
												<i class="rdo"></i>
												<em class="label-title">월별검색</em>
											</label>
										</div>
									</div>
								</li>
							</ul>
							<ul class="sb_line" id="detailUl">
								<li>
									<p class="sb_title bold">경매일자</p>
									<div class="sb_data">
										<div class="table_devide">
											<label class="radio">
												<input type="radio" name="rr" onclick="setSearchMonthInit(3);" checked="checked">
												<i class="rdo"></i>
												<em class="label-title">최근 3개월</em>
											</label>
											<label class="radio">
												<input type="radio" name="rr" onclick="setSearchMonthInit(6);">
												<i class="rdo"></i>
												<em class="label-title">최근 6개월</em>
											</label>
											<label class="radio">
												<input type="radio" name="rr" onclick="setSearchMonthInit(12);">
												<i class="rdo"></i>
												<em class="label-title">최근 1년</em>
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
													<input type="text" id="startMonth" class="monthpicker" onclick="customInputCheck();">
												</div>
												<span class="hyphen">~</span>
												<div class="date_box">
													<input type="text" id="endMonth" class="monthpicker" onclick="customInputCheck();">
												</div>												
											</div>
											<p class="txt_01 dib"></p>
										</div>
									</div>
								</li>
							</ul>
							<!-- 월별검색(S) -->
							<ul class="sb_line" id="sumUl">
								<li>
									<p class="sb_title bold">경매일자</p>
									<!-- 월별검색(S) -->
									<div class="sb_data">
										<div class="table_devide">
											<div class="ip_type_01 w100">
												<input type="number" class="tr" id="stYear">
											</div>
											<p class="txt_01 dib">년 </p>
											<div class="sel_type_01 w50 ml10">
												<select id="selMonth" >
													<option value="01">1</option>
													<option value="02">2</option>
													<option value="03">3</option>
													<option value="04">4</option>
													<option value="05">5</option>
													<option value="06">6</option>
													<option value="07">7</option>
													<option value="08">8</option>
													<option value="09">9</option>
													<option value="10">10</option>
													<option value="11">11</option>
													<option value="12">12</option>
												</select>
												<label for="selMonth"></label>
											</div>
											<p class="txt_01 dib">월</p>
											<p class="txt_01 dib">(년도를 입력하신 후 월을 선택하세요.)</p>
										</div>
									</div>
								</li>
							</ul>
							<!-- 월별검색(E) -->
							<a href="javascript:fn_search();" class="btn_search">조회</a>
						</div>
						
					</div>
					<!-- 검색조건창(E) -->

					<!-- 타이틀(S) -->
					<div class="title_box mt30">
						<div class="fl">
							<h4 class="sub_tit_04">공동출하장려금 내역</h4>
						</div>
						<div class="fr">
							<a href="javascript:fn_report();" id="btnReport" class="btn_type_01">리포트</a>
							<!-- <a href="javascript:fn_excell();" class="btn_type_01">엑셀다운로드</a> -->
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
					<div class="table_type_04 mt5 overflow_a" id="detailDiv">
						<table>
							<caption>리스트</caption>
							<colgroup>
								<col style="width:99px;">
								<col style="width:100px;">
								<col style="width:200px;">
								<col style="width:200px;">
								<col style="width:200px;">
								<col style="width:200px;">
							</colgroup>
							<thead>
								<tr>
									<th class="tc">년월</th>
									<th class="tc">출하농가수</th>
									<th class="tc">출하물량</th>
									<th class="tc">출하실적</th>
									<th class="tc">장려금액</th>
									<th class="tc">비고</th>
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
								<col style="width:99px;">
								<col style="width:100px;">
								<col style="width:100px;">
								<col style="width:100px;">
								<col style="width:200px;">
								<col style="width:200px;">
								<col style="width:200px;">
							</colgroup>
							<thead>
								<tr>
									<th class="tc">년월</th>
									<th class="tc">출하자</th>
									<th class="tc">출하상자</th>
									<th class="tc">출하속/분</th>
									<th class="tc">출하실적</th>
									<th class="tc">장려금액</th>
									<th class="tc">비고</th>
								</tr>
							</thead>
							<tbody id="resultFbody">
								
							</tbody>
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
			
			currentDate = $("#endMonth").val();
			
			$( "input.monthpicker" ).datepicker({
				  showOn: "both",
				  buttonImage: contextPath+"/img/ico_datepicker.png",
				  buttonImageOnly: true,
			   	  buttonText: "Select date"
			});

					// KR language callendar
			$.datepicker.regional['kr'] = {
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
		  };
		  
		  $('.ui-datepicker ').css({ "margin-left" : "-100px", "margin-top": "0px"});  //달력(calendar) 위치
		  //  $('img.ui-datepicker-trigger').attr('align', 'absmiddle');
				// Seeting up default language, Korean
			$.datepicker.setDefaults($.datepicker.regional['kr']);
			
				
			setSearchMonthInit(3);
			
		});
		
		
		function fn_search(){
			var domeCode = $("#domeCode").prop("value");
			var startMonth = $("#startMonth").val();
			var endMonth = $("#endMonth").val();
			var stYear = $("#stYear").val();
			var selMonth = $("#selMonth").prop("value");
			var bunChk = $("#domeCode option:selected").attr("data-bunchk");
			var searchType = $("input:radio[name=searchType]:checked").val();
			
			if(searchType == "sum"){
				$("#detailDiv").css("display","none");
				$("#sumDiv").css("display","");
				$("#detailUl").css("display","none");
				$("#sumUl").css("display","");
			}else{
				$("#detailDiv").css("display","");
				$("#sumDiv").css("display","none");
				$("#detailUl").css("display","");
				$("#sumUl").css("display","none");
			}
			
			$.ajax({
				data:{
					domeCode: domeCode,
					startDate: startMonth,
					endDate: endMonth,
					stYear : stYear,
					selMonth : selMonth,
					bunChk: bunChk
			        },
		        type : "POST",
		        url : "${pageContext.request.contextPath}/front/stat/markShprBounty.json",
		        success : function(data){
		        	console.log(data);
		           var html = "";
		           var fhtml = "";
		           var monthList = data.monthList;
		           var termList = data.termList;
		           var groupVO = data.groupVO;
		           
		           $("#gName").html(groupVO.gname);
		           $("#bunName").html(groupVO.bunchk);
		           if(searchType == "sum"){
		        	   $("#searchDate").html(stYear + "-" +  selMonth);
				   }else{
					   $("#searchDate").html(startMonth + " ~ " +  endMonth);
				   }
		           
		           $("#currentDate").html(groupVO.today);
		           
		           
		           if(termList.length > 0){
		        	   for(var i=0; i<termList.length; i++ ){
		        		   	
							html+="<tr>                                                               ";
							html+="	<td class=\"tc\"><p class=\"txt_01\">"+termList[i].yearmonth+"</p></td>                 ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(termList[i].nongga)+"</p></td>                   ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(termList[i].sok)+"</p></td>                  ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(termList[i].mechul)+"</p></td>              ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(termList[i].jangp)+"</p></td>             ";
							html+="	<td class=\"tc\"><p class=\"txt_01\"></p></td>                  ";
							html+="</tr>                                                              ";
							
		        	   }
		        	   
		        	   if(searchType == "detail") $("#btnReport").css("display", "");
		           }else{
		        	   html += "<tr><td class=\"tc\" colspan=\"6\">데이터가 없습니다.</td></tr>";
		        	   
		        	   if(searchType == "detail") $("#btnReport").css("display", "none");
		           }
		           
		           if(monthList.length > 0){
		        	   for(var i=0; i<monthList.length; i++ ){
		        		   	
							fhtml+="<tr>                                                               ";
							fhtml+="	<td class=\"tc\"><p class=\"txt_01\">"+stYear+"."+selMonth+"</p></td>                 ";
							fhtml+="	<td class=\"tc\"><p class=\"txt_01\">"+monthList[i].chulname+"<br>("+monthList[i].chulcode+")</p></td>                   ";
							fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(monthList[i].pboxcnt)+"</p></td>                  ";
							fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(monthList[i].psokcnt)+"</p></td>                  ";
							fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(monthList[i].panprice)+"</p></td>              ";
							fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(monthList[i].jangp)+"</p></td>             ";
							fhtml+="	<td class=\"tc\"><p class=\"txt_01\"></p></td>                  ";
							fhtml+="</tr>                                                              ";
							
		        	   }
		        	   
		        	   if(searchType == "sum") $("#btnReport").css("display", "");
		           }else{
		        	   fhtml += "<tr><td class=\"tc\" colspan=\"7\">데이터가 없습니다.</td></tr>";
		        	   
		        	   if(searchType == "sum") $("#btnReport").css("display", "none");
		           }
		           
		           
		           
		           $("#resultTbody").html(html);
		           $("#resultFbody").html(fhtml);
			       
		        }
		    });
			
		}
		
		
		function fn_report(){
			var reportname = "";
			var domeCode = $("#domeCode").prop("value");
			var startMonth = $("#startMonth").val();
			var endMonth = $("#endMonth").val();
			var stYear = $("#stYear").val();
			var selMonth = $("#selMonth").prop("value");
			var bunChk = $("#domeCode option:selected").attr("data-bunchk");
			var searchType = $("input:radio[name=searchType]:checked").val();
			
			if(searchType == "sum"){
				reportname = "markShprBounty_Sum.ozr";
			}else{
				reportname = "markShprBounty_Detail.ozr";
			}
			
			$('input[name=reportname]').val(reportname);
			$('input[name=setUrl]').val("${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/front/stat/markShprBounty.json?domeCode="+domeCode+"&startDate="+startMonth+"&endDate="+endMonth+"&stYear="+stYear+"&selMonth="+selMonth+"&bunChk="+bunChk);
			
			$('#reportForm').submit();
		}
		
		
		
		</script>