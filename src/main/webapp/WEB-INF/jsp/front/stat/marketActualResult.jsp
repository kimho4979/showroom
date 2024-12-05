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
				<h2 class="sub_title">·소속농가실적확인서·</h2>

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
							<h4 class="sub_tit_02">소속농가실적확인서</h4>
						</div>
					</div>

					<!-- 타이틀(S) -->
					<div class="title_box mt30">
						<div class="fl">
							<h4 class="sub_tit_04">소속농가실적확인서 검색</h4>
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
									<p class="sb_title bold">농가선택</p>
									<p class="txt_01 dib vm">출하자명</p>
									<div class="sel_type_01 w150 ml10">
										<select id="chulCode">
											<option>선택</option>
										</select>
										<label for="chulCode"></label>
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

					<!-- 실적확인서가 없을시(S) -->
					<p class="print txt_w mt5 tc" id="emptyp">* 등록된 실적확인서가 없습니다.</p>
					<!-- 실적확인서가 없을시(E) -->

					<!-- 타이틀(S) -->
					<div class="title_box mt30">
						<div class="fr">
							<a href="javascript:fn_report();" id="btnReport" class="btn_type_01">리포트</a>
							<a onclick="pageprint()" class="btn_type_01">인쇄</a>
						</div>
					</div>
					<!-- 타이틀(E) -->

					<!-- 프린트(S) -->
					<script language="JavaScript"> 
						var initBody;
						var printDomeCode;
						var printChulCode;
						var printStartDate;
						var printEndDate;
						
						function beforePrint()
						{ 
							printDomeCode = $("#domeCode").prop("value");
							printChulCode = $("#chulCode").prop("value");
							printStartDate = $("#startDate").val();
							printEndDate = $("#endDate").val();
							initBody = document.body.innerHTML; 
							document.body.innerHTML = print_page.innerHTML;
						} 

						function afterPrint()
						{ 
							document.body.innerHTML = initBody; 
							$("#startDate").val(printStartDate);
							$("#endDate").val(printEndDate);
							$("#chulCode").prop("value",printChulCode);
							$("#domeCode").prop("value",printDomeCode);
						} 

						function pageprint()
						{
							if($("#printDiv").css("display") == "none"){
								alert("등록된 실적확인서가 없습니다.");
								return false;
							}
							window.onbeforeprint = beforePrint; 
							window.onafterprint = afterPrint; 
							window.print(); 
						}
					</script>
					<div class="book" id="printDiv">
						
					</div>
					<!-- 프린트(E) -->

					
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
			//setSearchDateInit(7);
			fn_chulList();
			currentDate = $("#endDate").val();
		});
		
		function fn_chulList(){
			var domeCode = $("#domeCode").prop("value");
			var bunChk = $("#domeCode option:selected").attr("data-bunchk");
			
			$.ajax({
				data:{
					domeCode: domeCode,
					bunChk: bunChk
			        },
		        type : "POST",
		        url : "${pageContext.request.contextPath}/front/stat/markActualResultChulhajaList.json",
		        success : function(data){
		           var html = "";
		           var  resultList = data.chulList;
		           
		           if(resultList.length > 0){
		        	   for(var i=0; i<resultList.length; i++ ){
		        		  html+="<option value=\""+resultList[i].code+"\">"+resultList[i].name+"("+resultList[i].code.substring(0,4)+"-"+resultList[i].code.substring(4,8)+")</option>";
		        	   }
		           }else{
		        	   html += "<option>출하자가 없습니다.</option>";
		           }
		           $("#chulCode").html(html);
		           setSearchDateInit(7);
		        }
		    });
			
		}
		
		function fn_search(){
			var domeCode = $("#domeCode").prop("value");
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			var bunChk = $("#domeCode option:selected").attr("data-bunchk");
			var chulCode = $("#chulCode").prop("value");
			
			$.ajax({
				data:{
					domeCode: domeCode,
					startDate: startDate,
					endDate: endDate,
					bunChk: bunChk,
					chulCode : chulCode
			        },
		        type : "POST",
		        url : "${pageContext.request.contextPath}/front/stat/marketActualResult.json",
		        success : function(data){
		           var html = "";
		           var fhtml = "";
		           var resultList = data.resultList;
		           var sumVO = data.sumVO;
		          
		           if(resultList.length>0){
		        	    html+="<div class=\"page\">";
						html+="	<div class=\"subpage\" id=\"content\">";
						html+="		<div class=\"pb_in\" id=\"print_page\">";
						html+="			<div style=\"padding:70px; box-sizing:border-box;\">";
						html+="				<h2 class=\"print_tit_01\" style=\"font-family:Gulim!important; \">출하자 실적 확인서("+resultList[0].bunchknm+")</h2>";
						html+="				<h4 class=\"sub_print_tit_01 mt40\"><span class=\"spt_01_th\" style=\"font-family:Gulim!important;\">성&emsp;&emsp;명&emsp;:&emsp;</span><span class=\"spt_01_td\" style=\"font-family:Gulim!important; \">"+resultList[0].name+"("+resultList[0].juminno1+" - *******)</span></h4>";
						html+="				<h4 class=\"sub_print_tit_01\"><span class=\"spt_01_th\" style=\"font-family:Gulim!important;\">코드번호&emsp;:&emsp;</span><span class=\"spt_01_td\" style=\"font-family:Gulim!important;\">"+resultList[0].code1+"-"+resultList[0].code2+"</span></h4>";
						html+="				<h4 class=\"sub_print_tit_01\"><span class=\"spt_01_th\" style=\"font-family:Gulim!important;\">주&emsp;&emsp;소&emsp;:&emsp;</span><span class=\"spt_01_td\" style=\"font-family:Gulim!important;\">"+resultList[0].addr+"</span></h4>";
						html+="				<p class=\"sub_print_txt_01 mt30 tc\" style=\"font-family:Gulim!important;\">상기인의 출하실적을 다음과 같이 확인합니다.</p>";
						html+="				<p class=\"sub_print_txt_02 tc mt30\" style=\"font-family:Gulim!important;\">-다음-</p>";
			            html+="				<h4 class=\"sub_print_tit_01 mt30\"><span class=\"spt_01_th\" style=\"font-family:Gulim!important;\">가. 출하기간&emsp;:&emsp;</span><span class=\"spt_01_td\" style=\"font-family:Gulim!important; \">"+resultList[0].startdate+" ~ "+resultList[0].enddate+"</span></h4>";
						html+="				<h4 class=\"sub_print_tit_01\"><span class=\"spt_01_th\" style=\"font-family:Gulim!important;\">나. 출하실적</span></h4>";
				        html+="				<div class=\"title_box mt20\">";
						html+="					<div class=\"fr\">";
						html+="						<p class=\"sub_print_txt_03\" style=\"font-family:Gulim!important;\">(단위:상자, 속·분, 원)</p>";
						html+="					</div>";
						html+="				</div>";
				        html+="				<div class=\"print_table mt5\">";
						html+="					<table>";
						html+="						<caption>프린트테이블</caption>";
						html+="						<colgroup>";
						html+="							<col style=\"width:50%\">";
						html+="							<col style=\"width:15%\">";
						html+="							<col style=\"width:15%\">";
						html+="							<col style=\"width:30%\">";
						html+="						</colgroup>";
						html+="						<thead>";
						html+="							<tr>";
						html+="								<th><p style=\"font-family:Gulim!important;\">품&ensp;목</p></th>";
						html+="								<th class=\"tr\"><p style=\"font-family:Gulim!important;\">상&ensp;자</p></th>";
						html+="								<th class=\"tr\"><p style=\"font-family:Gulim!important;\">물&ensp;량</p></th>";
						html+="								<th class=\"tr\"><p style=\"font-family:Gulim!important;\">금&ensp;액</p></th>";
						html+="							</tr>";
						html+="						</thead>";
						html+="						<tbody>";
						for(var i=0; i<resultList.length; i++ ){
							html+="							<tr>";
							html+="								<td class=\"tl\"><p style=\"font-family:Gulim!important;\">"+resultList[i].pmokname+"</p></td>";
							html+="								<td class=\"tr\"><p style=\"font-family:Gulim!important;\">"+comma(resultList[i].boxcnt)+"</p></td>";
							html+="								<td class=\"tr\"><p style=\"font-family:Gulim!important;\">"+comma(resultList[i].sokcnt)+"</p></td>";
							html+="								<td class=\"tr\"><p style=\"font-family:Gulim!important;\">"+comma(resultList[i].panprice)+"</p></td>";
							html+="							</tr>";
						}
						html+="							<tr>";
						html+="								<td class=\"tl\"><p style=\"font-family:Gulim!important;\">합계</p></td>";
						html+="								<td class=\"tr\"><p style=\"font-family:Gulim!important;\">"+comma(sumVO.sumboxcnt)+"</p></td>";
						html+="								<td class=\"tr\"><p style=\"font-family:Gulim!important;\">"+comma(sumVO.sumsokcnt)+"</p></td>";
						html+="								<td class=\"tr\"><p style=\"font-family:Gulim!important;\">"+comma(sumVO.sumpanprice)+"</p></td>";
						html+="							</tr>";
						html+="						</tbody>";
						html+="					</table>";
						html+="				</div>";
						html+="				<p class=\"sub_print_txt_day mt50 tc\" style=\"font-family:Gulim!important;\">"+resultList[0].syear+". "+resultList[0].smonth+". "+resultList[0].sday+"</p>";
						html+="				<div class=\"print_bottom\">";
						html+="					<h2 class=\"print_center\" style=\"font-family:Gulim!important;\">한국농수산식품유통공사 화훼사업센터 장</h2>";
						html+="					<img class=\"print_img\" src=\"${pageContext.request.contextPath}/img/print_img.jpg\" alt=\"도장\">";
						html+="					<p class=\"print_center_number\" style=\"font-family:Gulim!important;\">＊사업자번호 : 229-82-00650</p>";
						html+="				</div>";
						html+="			</div>";
						html+="		</div>";
						html+="	</div>";
						html+="</div>";
						
						$("#printDiv").html(html);
						$("#printDiv").css("display","");
						$("#emptyp").css("display","none");
						$("#btnReport").css("display", "");
		           }else{
		        	    $("#printDiv").css("display","none");
		        	    $("#emptyp").css("display","");
		        	    $("#btnReport").css("display", "none");
		           }
			        
		        }
		    });
			
		}
		
		
		function fn_report(){
			var reportname = "marketActualResult.ozr";
			var domeCode = $("#domeCode").prop("value");
			var chulCode = $("#chulCode").prop("value");
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			var bunChk = $("#domeCode option:selected").attr("data-bunchk");
			
			$('input[name=reportname]').val(reportname);
			$('input[name=setUrl]').val("${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/front/stat/marketActualResult.json?domeCode="+domeCode+"&chulCode="+chulCode+"&startDate="+startDate+"&endDate="+endDate+"&bunChk="+bunChk);
			
			$('#reportForm').submit();
		}
		
		
		</script>