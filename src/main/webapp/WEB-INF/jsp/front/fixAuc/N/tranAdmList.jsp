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


var pageIndex = 1;
var pageUnit = 10;
var orderArray = new Array();
var replace = null;

orderArray = [false,false,false,false,false,false,false,false,false,false,false]


function fn_view(tranSeq){
	//기능없음
}


function fn_search(orderString){
	
	$("#orderString").val(orderString);
	
	$("#frm").submit();
	
}



function fn_order(index){
	$(".td_add").removeClass("td_add");
	var myTable = document.getElementById("fixTable"); 
	replace = replacement(myTable); 
	if(!orderArray[index]){
		sortTD(index);	
		orderArray[index] = true;
	}else{
		reverseTD(index);
		orderArray[index] = false;
	}
	
}

function sortTD(index){
	replace.ascending(index);
}

function reverseTD(index){
	replace.descending(index);
}


function fn_aucPrice(tranSeq){
	
	$("#maucPrice"+tranSeq).val($("#aucPrice"+tranSeq).val());
}

function fn_maucPrice(tranSeq){
	
	$("#aucPrice"+tranSeq).val($("#maucPrice"+tranSeq).val());
}


function fn_check(obj, tranSeq){
	
	if(obj.checked){
		$("#tm"+tranSeq).prop("checked",true);
	}else{
		$("#tm"+tranSeq).prop("checked",false);
	}
}

function fn_mcheck(obj, tranSeq){
	console.log(obj.id);
	if(obj.checked){
		$("#t"+tranSeq).prop("checked",true);
	}else{
		$("#t"+tranSeq).prop("checked",false);
	}
}

function fn_allCheck(){
	if($("#allCheck").prop("checked")){
	    
	    $("input[name=fixCheck]").prop("checked",true);
	    $("input[name=mfixCheck]").prop("checked",true);
	    
	}else{
	    $("input[name=fixCheck]").prop("checked",false);
	    $("input[name=mfixCheck]").prop("checked",false);
	}	
}


function fn_board(){
	var chulDate = $("#chulDate").val();
	var url = contextPath + "/front/fixNAuc/transAdmCompList.do?chulDate="+chulDate; 
	window.open(url,'정산완료게시','width=1088px,height=680px,scrollbars=yes,resizable=no,toolbars=no,menubar=no');
}

function fn_magam(){
	var chulDate = $("#chulDate").val();
	var reqAppCnt = $("#reqDisCompCnt").val();
	if(reqAppCnt>0){
		if(confirm("요청 미완료 "+reqAppCnt+"건이 존재합니다. 그래도 마감하시겠습니까?")){
			$.ajax({
				data:{
					chulDate: chulDate
			        },
		        type : "POST",
		        url : "${pageContext.request.contextPath}/front/fixNAuc/tranMagamInsProc.json",
		        success : function(data){
		        	if(data.errorCode == '1'){
		        		alert("전송된 마감자료 "+ data.tranCnt + "건 존재하여 마감이 불가능합니다. 관리자에게 문의하세요." );
		        	}else{
			        	alert(data.result + "건 마감처리되었습니다.");
			        	fn_search();
		        	}
		        }
		    });
		}
	}else{
		if(confirm(chulDate + " 마감하시겠습니까?")){
			$.ajax({
				data:{
					chulDate: chulDate
			        },
		        type : "POST",
		        url : "${pageContext.request.contextPath}/front/fixNAuc/tranMagamInsProc.json",
		        success : function(data){
		        	if(data.errorCode == '1'){
		        		alert("전송된 마감자료 "+ data.tranCnt + "건 존재하여 마감이 불가능합니다. 관리자에게 문의하세요." );
		        	}else{
			        	alert(data.result + "건 마감처리되었습니다.");
			        	fn_search();
		        	}
		        }
		    });
		}
	}
	
	
}


function fn_magamCancel(){
	
	var chulDate = $("#chulDate").val();
	
	if(confirm(chulDate + " 마감취소하시겠습니까?")){
		$.ajax({
			data:{
				chulDate: chulDate
		        },
	        type : "POST",
	        url : "${pageContext.request.contextPath}/front/fixNAuc/tranMagamDelProc.json",
	        success : function(data){
	        	if(data.errorCode == '1'){
	        		alert("전송된 마감자료"+ data.tranCnt + "건 존재하여 마감취소가 불가능합니다. 관리자에게 문의하세요." );
	        	}else{
	        	 	alert("마감취소 처리되었습니다.");
	        	 	fn_search();
	        	}
	        }
	    });
	}
	
	
}


function fn_tran(){
	var checkedArray = new Array();
	
	$("input[name=fixCheck]:checked").each(function(index){
		var checkedValue = this.value;
		var tranYn = $("#tranYn"+checkedValue).val();
		if(tranYn == 'N'){
			checkedArray.push(checkedValue);
		}
		
	});
	
	if(checkedArray.length == 0){
		alert("체크리스트중 미전송 상태의 마감자료가 없습니다.");
	}else{
		if(confirm("체크된 마감자료를 전송하시겠습니까? \n 전송된 데이터는 삭제 할 수 없습니다.")){
			$.ajax({
				data:{
					checkedArray: checkedArray
			        },
		        type : "POST",
		        url : "${pageContext.request.contextPath}/front/fixNAuc/tranMagamComp.json",
		        success : function(data){
		        	if(data.errorCode == '1'){
		        		var upDay = data.errorVO.upDay;
		        		var upNo = data.errorVO.upNo;
		        		alert("상장일자 : " + upDay + " 상장번호 : " + upNo + "데이터가 존재합니다. 관리자에게 문의하세요." );
		        	}else{
		        		alert(data.result + "건 전송처리되었습니다.");
			            location.reload(true);
		        	}
		             
		        }
		    });
		}
	}
	
}




</script>




<!-- sub내용(S) -->
			<div class="sub_conts_in">
				
				<!-- sub탭(S) 
				<div class="tab_url">
					<ul>
						<li class="active"><a href="#">판매</a></li>
						<li><a href="#">요청</a></li>
					</ul>
				</div>-->
				<div class="tab_content pdt0">
					<!-- tab 내용01(S) --><!-- 판매(S)-->
					<div class="ti_01">
						<div class="info_box">
							<!-- 서브탭(S) -->
							<div class="sub_tab">
								<ul class="devide_3">
									<c:if test="${nFloLoginVO ne null }">
									<li><a href="${pageContext.request.contextPath}/front/fixNAuc/tranAdmList.do" class="active">절화</a></li>
									</c:if>
									<c:if test="${yFloLoginVO ne null }">
									<li><a href="${pageContext.request.contextPath}/front/fixYAuc/tranAdmList.do">&nbsp;&nbsp;&nbsp;난&nbsp;&nbsp;&nbsp;</a></li>
									</c:if>
									<c:if test="${cFloLoginVO ne null }">
									<li><a href="${pageContext.request.contextPath}/front/fixCAuc/tranAdmList.do">관엽</a></li>
									</c:if>
								</ul>
							</div>
							<!-- 서브탭(E) -->
							<input type="hidden" id="chulDate" value="${fixInfo.chulDate}"/>
							<!-- 검색조건창(S) -->
							<form id="frm" name="searchFrm" method="post" action="${pageContext.request.contextPath}/front/fixNAuc/tranAdmList.do" >
							
							<div class="condition_box">
								<div class="search_box">
									<ul class="sb_line">
										<li>
											<p class="sb_title">경매일자</p>
											
											<div class="date_box vm">
												<input type="text" class="datepicker" name="searchChulDate" id="searchChulDate" value="<c:out value="${paramMap.searchChulDate}"/>">
											</div>
										</li>
										
										<li>
											<p class="sb_title">품목명</p>
											<div class="sb_data">
												<div class="ip_type_01 w100p">
													<input type="text" id="searchMokCode" name="searchMokCode" value="<c:out value="${paramMap.searchMokCode}"/>" placeholder="검색어를 입력하세요"><label for="searchMokCode"></label>
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
									<ul class="sb_line">
										<li>
											<p class="sb_title">출하자코드</p>
											<div class="sb_data">
												<div class="ip_type_01 w100p">
													<input type="text" id="searchChulCode" name="searchChulCode" placeholder="검색어를 입력하세요" value="<c:out value="${paramMap.searchChulCode}"/>"><label for="searchChulCode"></label>
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


						<div class="info_box mt30">
							<div class="table_type_03">
						<table>
							<caption>info</caption>
							<colgroup>
								<col style="width:17%">
								<col style="width:33%">
								<col style="width:17%">
								<col style="width:33%">
							</colgroup>
							<tbody>
								<tr>
									<th class="tc">경매일자</th>
									<td class="tc"><p class="txt_01">${fixInfo.chulDate}</p></td>
									<th class="tc">총거래량/총금액</th>
									<td class="tc"><p class="txt_01"><fmt:formatNumber value="${fixInfo.totBoxCnt}" pattern="#,###" />상자 (<fmt:formatNumber value="${fixInfo.totSokCnt}" pattern="#,###" />속) / <fmt:formatNumber value="${fixInfo.totTradePrice}" pattern="#,###" />원</p></td>
								</tr>
								<tr>
									<th class="tc">판매 거래량</th>
									<td class="tc"><p class="txt_01"><fmt:formatNumber value="${fixInfo.fixBoxCnt}" pattern="#,###" />상자 (<fmt:formatNumber value="${fixInfo.fixSokCnt}" pattern="#,###" />속)</p></td>
									<th class="tc">판매 거래금액</th>
									<td class="tc"><p class="txt_01"><fmt:formatNumber value="${fixInfo.fixTradePrice}" pattern="#,###" />원</p></td>
								</tr>
								<tr>
									<th class="tc">요청 거래량</th>
									<td class="tc"><p class="txt_01"><fmt:formatNumber value="${fixInfo.reqBoxCnt}" pattern="#,###" />상자 (<fmt:formatNumber value="${fixInfo.reqSokCnt}" pattern="#,###" />속)</p></td>
									<th class="tc">요청 거래금액</th>
									<td class="tc"><p class="txt_01"><fmt:formatNumber value="${fixInfo.reqTradePrice}" pattern="#,###" />원</p></td>
								</tr>
								<tr>
									<th class="tc">판매 완료</th>
									<td class="tc"><p class="txt_01"><fmt:formatNumber value="${fixInfo.fixCompCnt}" pattern="#,###" />건</p></td>
									<th class="tc">판매 미완료</th>
									<td class="tc"><p class="txt_01"><fmt:formatNumber value="${fixInfo.fixDisCompCnt}" pattern="#,###" />건</p></td>
								</tr>
								<tr>
									<th class="tc">요청 완료</th>
									<td class="tc"><p class="txt_01"><fmt:formatNumber value="${fixInfo.reqCompCnt}" pattern="#,###" />건</p></td>
									<th class="tc">요청 미완료</th>
									<td class="tc" <c:if test="${fixInfo.reqDisCompCnt gt 0}">style="background-color: #FFD8D8;"</c:if>>
										<p class="txt_01"><fmt:formatNumber value="${fixInfo.reqDisCompCnt}" pattern="#,###" />건(신청 <fmt:formatNumber value="${fixInfo.reqAppCnt}" pattern="#,###" />건)</p>
										<input type="hidden" id="reqDisCompCnt" value="${fixInfo.reqDisCompCnt}">
									</td>
								</tr>
							</tbody>
						</table>
					</div>
							<!-- 타이틀(S) -->
							<div class="title_box mt30">
								<div class="fl">
									<h4 class="sub_tit_04">마감 리스트 (${resultCnt}건)</h4>
								</div>
								<div class="fr">
									<a href="javascript:fn_board();" class="btn_type_01 gray ml10">게시</a>
									<a href="javascript:fn_magam();" class="btn_type_01 gray ml10">마감</a>
									<a href="javascript:fn_magamCancel();" class="btn_type_01 gray ml10">마감취소</a> 
									<a href="javascript:fn_tran();" class="btn_type_01 gray ml10">체크된행 전송</a>
									<a href="${pageContext.request.contextPath}/front/fixNAuc/tranCompListExcell.do?chulDate=<c:out value="${fixInfo.chulDate}"/>" class="btn_type_01 gray ml10">마감데이터 엑셀다운로드</a>
								</div>
								
							</div>
							<!-- 타이틀(E) -->
							
							<!-- WEB테이블(S) -->
							<div class="table_type_01 web mt10">
								<table id="fixTable">
									<caption>info</caption>
									<colgroup>
										<col width="6%">
										<!--<col width="4%">-->
										<col width="8%">
										<col width="6%">
										<col width="8%">
										<col width="10%">
										<col width="4%">
										<col width="10%">
										<col width="8%">
										<col width="6%">
										<col width="6%">
										<col width="8%">
										<col width="8%">
									</colgroup>
									<thead>
										<tr>
											<th><input type="checkbox" id="allCheck" name="allCheck" onchange="fn_allCheck(this);"><label for="allCheck"><span class="mr0"></span></label>전체</th>
											<!-- <th style="cursor: pointer;" onclick="fn_order(1);">화훼<br>형태</th> -->
											<th style="cursor: pointer;" onclick="fn_order(1);">상장일자</th>
											<th style="cursor: pointer;" onclick="fn_order(2);">상장번호</th>
											<th style="cursor: pointer;" onclick="fn_order(3);">품목명</th>
											<th style="cursor: pointer;" onclick="fn_order(4);">품종명</th>
											<th style="cursor: pointer;" onclick="fn_order(5);">등급</th>
											<th style="cursor: pointer;" onclick="fn_order(6);">출하자(코드)</th>
											<th style="cursor: pointer;" onclick="fn_order(7);">낙찰자(코드)</th>
											<th style="cursor: pointer;" onclick="fn_order(8);">낙찰상자</th>
											<th style="cursor: pointer;" onclick="fn_order(9);">낙찰속수</th>
											<th style="cursor: pointer;" onclick="fn_order(10);">낙찰단가</th>
											<th style="cursor: pointer;" onclick="fn_order(11);">전송상태</th>
										</tr>
									</thead>
									<tbody id="resultWebList">
										<c:forEach items="${resultList}" var="result" varStatus="status">
											<tr id="fixTr${result.tranSeq}">
												<input type="hidden" id="tranYn${result.tranSeq}" value="${result.tranYn}"/>
												<td class="tc"><input type="checkbox" id="t${result.tranSeq}" name="fixCheck" value="${result.tranSeq}" onchange="fn_check(this, '${result.tranSeq}');"><label for="t${result.tranSeq}"><span class="mr0"></span></label></td>
												<!-- <td class="tc" style="cursor:pointer" onclick="fn_view('${result.tranSeq}');"><p class="txt_01">${result.bunChk}</p></td> -->
												<td class="tc" style="cursor:pointer" onclick="fn_view('${result.tranSeq}');"><p class="txt_01">${result.upDay}</p></td>
												<td class="tc" style="cursor:pointer" onclick="fn_view('${result.tranSeq}');"><p class="txt_01">${result.upNo}</p></td>
												<td class="tc" style="cursor:pointer" onclick="fn_view('${result.tranSeq}');"><p class="txt_01">${result.mokName}</p></td>
												<td class="tc" style="cursor:pointer" onclick="fn_view('${result.tranSeq}');"><p class="txt_01">${result.pumName}</p><c:if test="${result.fSongeCnt ne '0'}">/${result.fSongeCnt}</c:if></td>
												<td class="tc" style="cursor:pointer" onclick="fn_view('${result.tranSeq}');"><p class="txt_01">${result.chulLevelName}</p></td>
												<td class="tc" style="cursor:pointer" onclick="fn_view('${result.tranSeq}');">
													<p class="txt_01">${result.chulName}</p>
													<p class="txt_01">( ${fn:substring(result.chulCode,0,4)} - ${fn:substring(result.chulCode,4,8)} )</p>
												</td>
												<td class="tc" style="cursor:pointer" onclick="fn_view('${result.tranSeq}');">
													<p class="txt_01">${result.jName}</p>
													<p class="txt_01">( ${result.jCode} )</p>
												</td>
												<td class="tc" style="cursor:pointer" onclick="fn_view('${result.tranSeq}');"><p class="txt_01"><fmt:formatNumber value="${result.boxCnt}" pattern="#,###" /></p></td>
												<td class="tc" style="cursor:pointer" onclick="fn_view('${result.tranSeq}');"><p class="txt_01"><fmt:formatNumber value="${result.sokCnt}" pattern="#,###" /></p></td>
												<td class="tc" style="cursor:pointer" onclick="fn_view('${result.tranSeq}');">
													<p class="txt_01"><fmt:formatNumber value="${result.kmPOrg}" pattern="#,###" /></p>
												</td>
												<td class="tc" style="cursor:pointer" onclick="fn_view('${result.tranSeq}');">
													<p class="txt_01">
														<c:if test="${result.tranYn eq 'Y'}">
															전송
													    </c:if>
													    <c:if test="${result.tranYn eq 'N'}">
															미전송
													    </c:if>
													</p>
												</td>
											</tr>
										</c:forEach>
										<c:if test="${fn:length(resultList) eq 0 }">
											<tr>
												<td class="tc" colspan="12">
													데이터가 없습니다.
												</td>
											</tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- WEB테이블(E) -->

							<!-- MOBILE테이블(S) -->
							<div class="table_type_01_m mobile mt10" id="resultMobList">
								<c:forEach items="${resultList}" var="result" varStatus="status">
								<table>
									<caption>info</caption>
									<colgroup>
										<col style="width:100%">
									</colgroup>
									<thead>
										<tr>
											<td>
												<div class="title_box_m">
													<div class="fl">
														<h4 class="sub_tit_05">[${result.upDay} ${result.upNo}] ${result.mokName}(${result.pumName})</h4>
													</div>
													<div class="fr">
														<c:if test="${result.tranYn eq 'Y'}">
															<p class="txt_apply">전송</p>
													    </c:if>
													    <c:if test="${result.tranYn eq 'N'}">
															<p class="txt_re">미전송</p>
													    </c:if>
													</div>
													<div class="fr"><input type="checkbox" id="tm${result.tranSeq}" name="mfixCheck" onchange="fn_mcheck(this, '${result.tranSeq}');"><label for="tm${result.tranSeq}"><span class="mr10"></span></label></div>
												</div>
											</td>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>
												<div class="table_info">
													<p class="txt_01">출하자(코드)명 : ${result.chulName} ( ${fn:substring(result.chulCode,0,4)} - ${fn:substring(result.chulCode,4,8)} )</p>
													<p class="txt_01">낙찰자(코드)명 : ${result.jName} ( ${result.jCode} )</p>
													<ul class="profile_box" onclick="fn_view('${result.tranSeq}');" style="cursor: pointer;">
														<li>
															<p class="txt_01">등급 : ${result.chulLevelName}</p>
															<p class="txt_01">낙찰상자 : <fmt:formatNumber value="${result.boxCnt}" pattern="#,###" /></p>
														</li>
														<li>
															<p class="txt_01">낙찰속수 : <fmt:formatNumber value="${result.sokCnt}" pattern="#,###" /></p>
															<p class="txt_01">낙찰단가 : <fmt:formatNumber value="${result.kmPOrg}" pattern="#,###" /></p>
														</li>
													</ul>
												</div>
											</td>
										</tr>
									</tbody>
								</table>
								</c:forEach>
								<c:if test="${fn:length(resultList) eq 0 }">
									데이터가 없습니다.
								</c:if>
							</div>
							<!-- MOBILE테이블(E) -->
						</div>

						</div>
					</div>
					<!-- tab 내용01(E) --><!-- 판매(E)-->
				</div>
				<!-- sub탭(E) -->

			</div>
			<!-- sub내용(E) -->
