<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt" %>

<script src="${pageContext.request.contextPath}/assets/js/plugins/slick/slick.min.js"></script>


<!-- sub내용(S) -->
			<div class="sub_conts_in">

				<div class="info_box">


					<!-- 테이블03(S) -->
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
									<th class="tc">상호</th>
									<td class="tc"><p class="txt_01">${result.copName}</p></td>
									<th class="tc">중도매인명<br/>(코드)</th>
									<td class="tc"><p class="txt_01 dib">${result.jName}(${result.jCode})</p></td>
								</tr>
								<tr>
									<th class="tc">품목명</th>
									<td class="tc"><p class="txt_01">${result.mokName}</p></td>
									<th class="tc">품종명</th>
									<td class="tc"><p class="txt_01">${result.pumName}<c:if test="${result.twinCnt ne '0'}">/${result.twinCnt}</c:if></p></td>
								</tr>
								<tr>
									<th class="tc">출하자명<br/>(코드)</th>
									<td class="tc"><p class="txt_01 dib">${result.chulName}(${fn:substring(result.chulCode,0,4)} - ${fn:substring(result.chulCode,4,8)})</p></td>
									<th class="tc">구매요청단가</th>
									<td class="tc"><p class="txt_01"><fmt:formatNumber value="${result.unitPrice}" pattern="#,###" /></p></td>
								</tr>
								<tr>
									<th class="tc">등급</th>
									<td class="tc"><p class="txt_01">${result.chulLevelName}</p></td>
									<th class="tc">요청상자수</th>
									<td class="tc"><p class="txt_01"><fmt:formatNumber value="${result.boxCnt}" pattern="#,###" /></p></td>
								</tr>
								<tr>
									<th class="tc">요청 총 속/분 수량</th>
									<td class="tc"><p class="txt_01"><fmt:formatNumber value="${result.sokCnt}" pattern="#,###" /></p></td>
									<th class="tc">경매사 조정단가</th>
									<td class="tc">
										<p class="txt_01"><fmt:formatNumber value="${result.aucPrice}" pattern="#,###" /></p></td>
									</td>
								</tr>
								<tr>
									<th class="tc">신청일자</th>
									<td class="tc"><p class="txt_01">${result.reqDate}</p></td>
									<th class="tc">입고희망일</th>
									<td class="tc"><p class="txt_01">${result.chulDate}</p></td>
								</tr>
								<tr>
									<th class="tc">상태</th>
									<td class="tc" <c:if test="${result.mokCode ne '1005'}">colspan="3"</c:if>><p class="txt_01">
									<c:if test="${result.fixState eq '1'}">
										신청
								    </c:if>
								    <c:if test="${result.fixState eq '2'}">
										미체결
								   </c:if>
								   <c:if test="${result.fixState eq '3'}">
										진행
								   </c:if>
								   <c:if test="${result.fixState eq '4'}">
										체결
								   </c:if>
								  </p></td>
									<c:if test="${result.mokCode eq '1005'}">
										<th class="tc">쌍대/송이</th>
										<td class="tc"><p class="txt_01">${result.twinCnt}</p></td>
									</c:if>
								</tr>
								<tr>
									<th class="tc">요청 및 개선사항</th>
									<td class="tc" colspan="3"><p class="txt_01"><pre>${result.reqText}</pre></p></td>
								</tr>
								
							</tbody>
						</table>
					</div>
					<!-- 테이블03(E) -->

					<!-- 버튼박스(S) -->
					<div class="btn_box mt30">
						<c:if test="${result.fixState eq '3'}">
							<a href="javascript:fn_comp('${result.reqDealSeq}');" class="btn_type_01 btn-popup fix">체결</a>
							<a href="#layerSample2" class="btn_type_01 btn-popup re">미체결</a>
						</c:if>
						<a href="${pageContext.request.contextPath}/front/fixNAuc/reqSellList.do" class="btn_type_01 list" id="listBtn">목록</a>
						
					</div>
					<!-- 버튼박스(E) -->

				</div>

			</div>
			<!-- sub내용(E) -->
			
			
			<!-- 팝업(S) -->
	
	<div class="dim-layer" id="layerSample2">
		<div class="dimBg"></div>
		<div class="pop-layer popbox pop_type_02">
			<div class="pop-container">
				<a href="#!" class="btn-layerClose pop_btn_close_02 close_B" alt="닫기 버튼"></a>
				<div class="pop-conts_00">
					<!-- 팝업 타이틀 박스(S) -->
					<div class="pop_title_box">
						<div class="fl">
							<p class="pop_title">미체결사유</p>
						</div>
					</div>
					<!-- 팝업 타이틀 박스(E) -->

					<!-- 팝업 컨텐츠(S) -->
					<div class="choice_box">
						<!-- 
						<div class="popup_search">
							<div class="ip_type_02 dib">
								<input type="text" class="tr" id="grade">
								<label for="grade"></label>
							</div>
							<a href="#layerSample" class="btn_search_03 btn-popup ml10 vb">검색</a>
						</div>
 						-->
						
							<textarea wrap="physical" id="banText" style="width:100%;height:100px;border: 1px solid;"></textarea>
							<a href="javascript:fn_ban('${result.reqDealSeq}');" class="btn_search_03">미체결</a>
						
					</div>
					<!-- 팝업 컨텐츠(E) -->
				</div>
			</div>
		</div>
	</div>
	

<script type="text/javascript">


function fn_ban(reqDealSeq){
	var checkedArray = new Array();
	var banTextArray = new Array();
	
	checkedArray.push(reqDealSeq);
	banTextArray.push($("#banText").val());
	
	
	$.ajax({
		data:{
			checkedArray: checkedArray,
			banTextArray: banTextArray
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixNAuc/reqSellBan.json",
        success : function(data){
             alert("정삭적으로 미체결처리되었습니다.");
             window.location.href = "${pageContext.request.contextPath}/front/fixNAuc/reqSellList.do";
        }
    });
	
}


function fn_comp(reqDealSeq){
	var checkedArray = new Array();
	var aucPriceArray = new Array();
	
	checkedArray.push(reqDealSeq);
	aucPriceArray.push($("#aucPrice").val());
	
	
	$.ajax({
		data:{
			checkedArray: checkedArray,
			aucPriceArray: aucPriceArray
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixNAuc/reqSellComp.json",
        success : function(data){
             alert("정삭적으로 체결처리되었습니다.");
             location.reload(true);
        }
    });
	
}




$( document ).ready(function() {
	
});


</script>
			
			