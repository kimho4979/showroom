<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
.td_add{
	background: #EAEAEA !important;
}


</style>

<script src="${pageContext.request.contextPath}/js/fix.js"></script>

<script type="text/javascript">

function fn_view(fixSubDealSeq){
	window.location.href = "${pageContext.request.contextPath}/front/fixCAuc/fixBuySubView.do?fixSubDealSeq="+fixSubDealSeq;
}

function fn_reg(){
	window.location.href = "${pageContext.request.contextPath}/front/fixCAuc/fixBuySubReg.do";
}

function fn_save(fixSubDealSeq){
	
	var boxCnt = $("#boxCnt"+fixSubDealSeq).val();
	var sokCnt = $("#sokCnt"+fixSubDealSeq).val();
	var chulDate = $("#chulDate"+fixSubDealSeq).val();
	
	if(boxCnt == "" || boxCnt == null){
		alert("구매상자수를 입력하세요");
		$("#boxCnt"+fixSubDealSeq).focus();
		return false;
	}
	
	if(sokCnt == "" || sokCnt == null){
		alert("구매총분수량를 입력하세요");
		$("#sokCnt"+fixSubDealSeq).focus();
		return false;
	}
	
	if(chulDate == "" || chulDate == null){
		alert("입고희망일을 입력하세요");
		$("#chulDate"+fixSubDealSeq).focus();
		return false;
	}
	
	if(confirm("분갈이 요청은 경매사에 의해 요청취소 가능합니다. 요청진행 하시겠습니까?")){
		window.location.href = "${pageContext.request.contextPath}/front/fixCAuc/reqBuySubRegProc.do?fixSubDealSeq="+fixSubDealSeq+"&boxCnt="+boxCnt+"&sokCnt="+sokCnt+"&chulDate="+chulDate;	
	}
}


function fn_search(orderString){
	
	$("#frm").submit();
}

function fn_boxCntKeyUp(fixSubDealSeq, unitSok){
	
	var boxCnt = $("#boxCnt"+fixSubDealSeq).val();
	$("#sokCnt"+fixSubDealSeq).val(boxCnt*unitSok);
	
}

function fn_sokCntKeyUp(fixSubDealSeq){
	$("#boxCnt"+fixSubDealSeq).val(1);
}

$(document).ready(function(){
	var date = new Date(); 
	var dayIndex = date.getDay();
	
	//관엽 
	if(dayIndex == 6){
		date.setDate(date.getDate()+6);
	}else if (dayIndex == 4 || dayIndex == 0){
		date.setDate(date.getDate()+5);
	}else if (dayIndex == 5 || dayIndex == 1){
		date.setDate(date.getDate()+4);
	}else if(dayIndex == 2){
		date.setDate(date.getDate()+3);
	}else{
		date.setDate(date.getDate()+2);
	}
	

	var year = date.getFullYear(); 
	var month = new String(date.getMonth()+1); 
	var day = new String(date.getDate()); 
	
	if(month.length == 1){ 
	  month = "0" + month; 
	} 
	
	if(day.length == 1){ 
	   day = "0" + day; 
	} 
		
	$("input[name=chulDate]").datepicker("setDate",year+"-"+month+"-"+day);
});
</script>




<!-- sub내용(S) -->
			<div class="sub_conts_in">

				<div class="tab_content pdt0">
					<!-- tab 내용01(S) --><!-- 판매(S)-->
					<div class="ti_01">


						<div class="info_box">

							<!-- 서브탭(S) -->
							<div class="sub_tab">
								<ul class="devide_3">
									<c:if test="${nFloLoginVO ne null }">
									<li><a href="${pageContext.request.contextPath}/front/fixNAuc/fixBuyList.do" >절화</a></li>
									</c:if>
									<c:if test="${yFloLoginVO ne null }">
									<li><a href="${pageContext.request.contextPath}/front/fixYAuc/fixBuyList.do">&nbsp;&nbsp;&nbsp;난&nbsp;&nbsp;&nbsp;</a></li>
									</c:if>
									<c:if test="${cFloLoginVO ne null }">
									<li><a href="${pageContext.request.contextPath}/front/fixCAuc/fixBuyList.do">관엽</a></li>
									<li><a href="${pageContext.request.contextPath}/front/fixCAuc/fixBuySubList.do" class="active">관엽-분갈이</a></li>
									</c:if>
								</ul>
							</div>
							<!-- 서브탭(E) -->

							<!-- 검색조건창(S) -->
							<form id="frm" name="searchFrm" method="post" action="${pageContext.request.contextPath}/front/fixCAuc/fixBuySubList.do" >
							
							<div class="condition_box">
								<div class="search_box">
									<ul class="sb_line">
										<li>
											<p class="sb_title">출하자</p>
											<div class="sb_data">
												<div class="sel_type_01 w150">
													<select id="searchChulCode" name="searchChulCode">
														<option value="" <c:if test="${paramMap.searchChulCode eq null}">selected="selected"</c:if>>전체</option>
														<c:forEach items="${chulList}" var="chulVO" varStatus="status">
															<option value="${chulVO.chulCode}" <c:if test="${paramMap.searchChulCode eq chulVO.chulCode}">selected="selected"</c:if>>${fn:substring(chulVO.chulCode,0,4)} - ${fn:substring(chulVO.chulCode,4,8)} ( ${chulVO.chulName} )</option>
														</c:forEach>
													</select>
													<label for="searchChulCode"></label>
												</div>
											</div>
										</li>
										<li>
											<p class="sb_title">품종명</p>
											<div class="sb_data">
												<div class="ip_type_01 w100p">
													<input type="text" id="searchPumCode" name="searchPumCode" value="<c:out value="${paramMap.searchPumCode}"/>" placeholder="검색어를 입력하세요"><label for="searchPumCode"></label>
												</div>
											</div>
										</li>
									</ul>
									<a href="javascript:fn_search();" class="btn_search">검색</a>
								</div>
							</div>
							</form>
							<!-- 검색조건창(E) -->

							<!-- 검색조건버튼(S) -->
							<div class="btn_box tc">
								<a href="#!" class="btn_condition close">검색조건 닫기<span class="img_close"><img src="${pageContext.request.contextPath}/img/ico_close.png" alt="닫기아이콘"></span></a>
								<a href="#!" class="btn_condition open">검색조건 열기<span class="img_close"><img src="${pageContext.request.contextPath}/img/ico_close.png" alt="닫기아이콘"></span></a>
							</div>
							<!-- 검색조건버튼(E) -->

						</div>
						
						<div class="card_box mt30">
							<ul>
								<c:forEach items="${resultList}" var="result" varStatus="status">
								<li>
									<div class="card" onclick="fn_view('${result.fixSubDealSeq}');" style="cursor: pointer;">
										<span class="c_img">
											<img src="${pageContext.request.contextPath}${result.thumbPath}" alt="이미지_${result.pumName}">
										</span>
										<div class="c_info">
											<h4 class="c_tit">${result.pumName}</h4>
											<p class="c_cost">판매단가 : <fmt:formatNumber value="${result.unitPrice}" pattern="#,###" /></p>
											<p class="c_name">${result.chulName}<br/>(${fn:substring(result.chulCode,0,4)} - ${fn:substring(result.chulCode,4,8)})</p></p>
										</div>
									</div>
									<div class="card">
										<div class="c_info ip_type_01 w50p vm">
											<p class="c_cost">구매상자수</p>
											<input type="number" id="boxCnt${result.fixSubDealSeq}" class="tr" maxlength="2" oninput="maxLengthCheck(this);fn_boxCntKeyUp('${result.fixSubDealSeq}','${result.unitSok}');"><label for="boxCnt${result.fixSubDealSeq}"></label>
										</div>
										<div class="c_info ip_type_01 w50p vm">
											<p class="c_cost">구매 총분수량</p>
											<input type="number" id="sokCnt${result.fixSubDealSeq}" class="tr" maxlength="2" oninput="maxLengthCheck(this);fn_sokCntKeyUp('${result.fixSubDealSeq}');"><label for="sokCnt${result.fixSubDealSeq}"></label>
										</div>
										<div class="c_info w100p vm">
											<p class="c_cost">입고희망일</p>
											<div class="date_box">
												<input type="text" class="datepicker" id="chulDate${result.fixSubDealSeq}" name="chulDate">
											</div>
										</div>
										<div class="c_info ip_type_01 w50p vm">
											<a href="javascript:fn_save('${result.fixSubDealSeq}');" class="btn_table_mobile">요청하기</a>
										</div>
									</div>
								</li>
								</c:forEach>
							</ul>
						</div>
						<c:if test="${fn:length(resultList) eq 0 }">
								<p class="tc">등록된 데이터가 없습니다.</p>
						</c:if>

					</div>
					<!-- tab 내용01(E) --><!-- 판매(E)-->
				</div>
				<!-- sub탭(E) -->

			</div>
			<!-- sub내용(E) -->
