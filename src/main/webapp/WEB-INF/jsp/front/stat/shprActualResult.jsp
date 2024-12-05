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
				<h2 class="sub_title">·실적확인서·</h2>

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
							<h4 class="sub_tit_02">출하농가실적확인서</h4>
						</div>
					</div>

					<!-- 타이틀(S) -->
					<div class="title_box mt30">
						<div class="fl">
							<h4 class="sub_tit_04">출하농가실적확인서 검색</h4>
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
													<option value="${floLoginVO.chulCd}" data-bunchk="${floLoginVO.floMokCd}">
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
						<!-- <p class="txt_w mt5">* 조회기간은 시작일자와 종료일자가 3년 이내에서만 가능합니다.</p> -->
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
						var printChulCode;
						var printStartDate;
						var printEndDate;
						
						function beforePrint()
						{ 
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
			setSearchDateInit(7);
			currentDate = $("#endDate").val();
			$("#startDate").unbind('change');
			$("#endDate").unbind('change');
		});
		
		
		function fn_search(){
			var chulCode = $("#chulCode").prop("value");
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			var bunChk = $("#chulCode option:selected").attr("data-bunchk");
			
			$.ajax({
				data:{
					chulCode: chulCode,
					startDate: startDate,
					endDate: endDate,
					bunChk: bunChk
			        },
		        type : "POST",
		        url : "${pageContext.request.contextPath}/front/stat/shprActualResult.json",
		        success : function(data){
		           var html = "";
		           var fhtml = "";
		           var resultList = data.resultList;
		           var sumVO = data.sumVO;
		          
		           if(resultList.length>0){
		        	    html+="<div class=\"page\">";
						html+="	<div class=\"subpage\" id=\"content\">";
						html+="		<div class=\"pb_in\" id=\"print_page\">";
						html+="			<div style=\"padding:30px; box-sizing:border-box;\">";
						html+="				<h2 class=\"print_tit_02\">실적 확인서</h2>";
			            html+="				<h4 class=\"sub_print_tit_02 mt40\"><span class=\"spt_02_th\">성&emsp;&emsp;명&emsp;:&emsp;</span><span class=\"spt_02_td\">"+resultList[0].name+"("+resultList[0].juminno1+" - *******)</span></h4>";
						html+="				<h4 class=\"sub_print_tit_02\"><span class=\"spt_02_th\">코드번호&emsp;:&emsp;</span><span class=\"spt_02_td\">"+resultList[0].code1+"-"+resultList[0].code2+"</span></h4>";
						html+="				<h4 class=\"sub_print_tit_02\"><span class=\"spt_02_th\">주&emsp;&emsp;소&emsp;:&emsp;</span><span class=\"spt_02_td\">"+resultList[0].addr+"</span></h4>";
			            html+="				<p class=\"sub_print_txt_03 mt30 tc\">상기인의 출하실적을 다음과 같이 확인합니다.</p>";
						html+="				<p class=\"sub_print_txt_04 tc mt30\">-다음-</p>";
	                    html+="				<h4 class=\"sub_print_tit_02 mt30\"><span class=\"spt_02_th\">가. 기간&emsp;:&emsp;</span><span class=\"spt_02_td\">"+resultList[0].startdate+" ~ "+resultList[0].enddate+"</span></h4>";
						html+="				<h4 class=\"sub_print_tit_02\"><span class=\"spt_02_th\">나. 실적&emsp;-&emsp;</span><br/><span class=\"spt_02_td\">화훼부류 : "+resultList[0].bunchknm+"</span></h4>";
			            html+="				<div class=\"print_table_02 mt20\">";
						html+="					<table>";
						html+="						<caption>프린트테이블</caption>";
						html+="						<colgroup>";
						html+="							<col style=\"width:15%\">";
						html+="							<col style=\"width:15%\">";
						html+="							<col style=\"width:20%\">";
						html+="							<col style=\"width:30%\">";
						html+="							<col style=\"width:20%\">";
						html+="						</colgroup>";
						html+="						<thead>";
						html+="							<tr>";
						html+="								<th><p>품목</p></th>";
						html+="								<th class=\"tr\"><p>상자수</p></th>";
						html+="								<th class=\"tr\"><p>속(분)수</p></th>";
						html+="								<th class=\"tr\"><p>금액</p></th>";
						html+="								<th class=\"tr\"><p>비고</p></th>";
						html+="							</tr>";
						html+="						</thead>";
						html+="						<tbody>";
						for(var i=0; i<resultList.length; i++ ){
							html+="							<tr>";
							html+="								<td class=\"tl\"><p>"+resultList[i].pmokname+"</p></td>";
							html+="								<td class=\"tr\"><p>"+comma(resultList[i].boxcnt)+"</p></td>";
							html+="								<td class=\"tr\"><p>"+comma(resultList[i].sokcnt)+"</p></td>";
							html+="								<td class=\"tr\"><p>"+comma(resultList[i].panprice)+"</p></td>";
							html+="								<td class=\"tr\"><p></p></td>";
							html+="							</tr>";
						}
						html+="							<tr>";
						html+="								<td class=\"tl\"><p>합계</p></td>";
						html+="								<td class=\"tr\"><p>"+comma(sumVO.sumboxcnt)+"</p></td>";
						html+="								<td class=\"tr\"><p>"+comma(sumVO.sumsokcnt)+"</p></td>";
						html+="								<td class=\"tr\"><p>"+comma(sumVO.sumpanprice)+"</p></td>";
						html+="								<td class=\"tr\"><p></p></td>";
						html+="							</tr>";
						html+="						</tbody>";
						html+="					</table>";
						html+="				</div>";
			            html+="				<p class=\"sub_print_txt_day_02 mt50 tc\">"+resultList[0].syear+"."+resultList[0].smonth+"."+resultList[0].sday+"</p>";
						html+="				<div class=\"print_bottom\">";
						html+="					<h2 class=\"print_center_02\">한국농수산식품유통공사 화훼사업센터 장</h2>";
						html+="					<p class=\"print_center_number_02\">＊사업자번호 : 229-82-00650</p>";
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
			var reportname = "shprActualResult.ozr";
			var chulCode = $("#chulCode").prop("value");
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			var bunChk = $("#chulCode option:selected").attr("data-bunchk");
			
			$('input[name=reportname]').val(reportname);
			$('input[name=setUrl]').val("${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/front/stat/shprActualResult.json?chulCode="+chulCode+"&startDate="+startDate+"&endDate="+endDate+"&bunChk="+bunChk);
			
			$('#reportForm').submit();
		}
		
		
		
		</script>