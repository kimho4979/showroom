<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    
  <!-- sub-conts(S) -->
		<div class="sub_conts bgw">

			<!-- sub상단(S) -->
			<div class="sub_top">
				<h2 class="sub_title">·상자형별 하역비내역·</h2>

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
							<h4 class="sub_tit_02">상자형별 하역비내역</h4>
						</div>
					</div>

					<!-- 타이틀(S) -->
					<div class="title_box mt30">
						<div class="fl">
							<h4 class="sub_tit_04">상자형별 하역비내역 검색</h4>
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
												<input type="radio" name="bunChk" value="N" checked="checked">
												<i class="rdo"></i>
												<em class="label-title">절화</em>
											</label>
											<label class="radio">
												<input type="radio" name="bunChk" value="Y">
												<i class="rdo"></i>
												<em class="label-title">난</em>
											</label>
											<label class="radio">
												<input type="radio" name="bunChk" value="C">
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
							<ul class="sb_line">
								<li>
									<p class="sb_title bold">경매구분</p>
									<div class="sb_data">
										<div class="table_devide">
											<label class="radio">
												<input type="radio" name="kmList" value="1" checked="checked">
												<i class="rdo"></i>
												<em class="label-title">경매</em>
											</label>
											<label class="radio">
												<input type="radio" name="kmList" value="2">
												<i class="rdo"></i>
												<em class="label-title">폐기</em>
											</label>
											<label class="radio">
												<input type="radio" name="kmList" value="3">
												<i class="rdo"></i>
												<em class="label-title">전체</em>
											</label>
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
							<h4 class="sub_tit_04">상자형별 하역비내역</h4>
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
							<caption>상자형별 하역비내역</caption>
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
								<col style="width:199px;">
								<col style="width:100px;">
								<col style="width:100px;">
								<col style="width:100px;">
								<col style="width:100px;">
								<col style="width:100px;">
								<col style="width:100px;">
							</colgroup>
							<thead>
								<tr>
									<th class="tc">하역일자</th>
									<th class="tc">상자코드</th>
									<th class="tc">상자형</th>
									<th class="tc">상자수량</th>
									<th class="tc">하역단가</th>
									<th class="tc">하역비(농가)</th>
									<th class="tc">하역비(공사)</th>
									<th class="tc">재포장비</th>
									<th class="tc">합계금액</th>
								</tr>
							</thead>
							<tbody id="resultTbody">
								
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
			setSearchDateInit(7);
			currentDate = $("#endDate").val();
			$("#startDate").unbind('change');
			$("#endDate").unbind('change');
		});
		
		
		function fn_search(){
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			var bunChk = $("input:radio[name=bunChk]:checked").val();
			var kmList = $("input:radio[name=kmList]:checked").val();
			$.ajax({
				data:{
					startDate: startDate,
					endDate: endDate,
					bunChk: bunChk,
					kmList:kmList
			        },
		        type : "POST",
		        url : "${pageContext.request.contextPath}/front/stat/boxLoading.json",
		        success : function(data){
		        	console.log(data);
		           var html = "";
		           var fhtml = "";
		           var resultList = data.resultList;
		           
		           var bunName = "";
		           
		           if(bunChk == 'N'){
		        	   bunName = "절화";
		           }else if(bunChk == 'Y'){
		        	   bunName = "난";
		           }else if(bunChk == 'C'){
		        	   bunName = "관엽";
		           }
		           
		           $("#bunName").html(bunName);
		           $("#searchDate").html(startDate + " ~ " + endDate);
		           $("#currentDate").html(currentDate);
		           
		           if(resultList.length > 0){
		        	   $("#btnReport").css("display", "");
		        	   
		        	   for(var i=0; i<resultList.length; i++ ){
		        		   var boxCode = "";
		        		   var boxName = "";
		        		   
		        		   
		        		   if(resultList[i].boxCode != null){
		        			   boxCode = resultList[i].boxCode;
		        		   }
		        		   if(resultList[i].boxName != null){
		        			   boxName = resultList[i].boxName;
		        		   }
		        		   
		        		   
		        		   
		        		    html+="<tr>                                                                   ";
							html+="	<td class=\"tc\"><p class=\"txt_01\">"+resultList[i].fpanDay+"</p></td>  ";
							html+="	<td class=\"tc\"><p class=\"txt_01\">"+boxCode+"</p></td>  ";
							html+="	<td class=\"tl\"><p class=\"txt_01\">"+boxName+"</p></td>  ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].boxCnt)+"</p></td>  ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].downP)+"</p></td>  ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].downReP)+"</p></td>  ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].downRePg)+"</p></td>  ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].reBoxP)+"</p></td>  ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].sumP)+"</p></td>  ";
							html+="</tr>                                                                  ";
		        	   }
		           }else{
		        	   $("#btnReport").css("display", "none");
		        	   html += "<tr><td class=\"tc\" colspan=\"9\">데이터가 없습니다.</td></tr>";
		           }
		           
		           $("#resultTbody").html(html);
		           
		        }
		    });
			
		}
		

		function fn_excell(){
			
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			var bunChk = $("input:radio[name=bunChk]:checked").val();
			var kmList = $("input:radio[name=kmList]:checked").val();
			
			var excellUrl = "${pageContext.request.contextPath}/front/stat/boxLoadingExcell.do?startDate="+startDate+"&endDate="+endDate+"&bunChk="+bunChk+"&kmList="+kmList;
			
			window.location.href = excellUrl;
			
		}
		
		
		function fn_report(){
			var reportname = "boxLoading.ozr";
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			var bunChk = $("input:radio[name=bunChk]:checked").val();
			var kmList = $("input:radio[name=kmList]:checked").val();
			
			$('input[name=reportname]').val(reportname);
			$('input[name=setUrl]').val("${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/front/stat/boxLoading.json?startDate="+startDate+"&endDate="+endDate+"&bunChk="+bunChk+"&kmList="+kmList);
			
			$('#reportForm').submit();
		}
		
		
		</script>