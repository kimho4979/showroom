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
									<th class="tc">경매사조정단가</th>
									<td class="tc"><p class="txt_01"><fmt:formatNumber value="${result.aucPrice}" pattern="#,###" /></p></td>
								</tr>
								<tr>
									<th class="tc">요청 상자수</th>
									<td class="tc"><p class="txt_01"><fmt:formatNumber value="${result.boxCnt}" pattern="#,###" /></p></td>
									<th class="tc">요청 총 속/분 수량</th>
									<td class="tc"><p class="txt_01"><fmt:formatNumber value="${result.sokCnt}" pattern="#,###" /></p></td>
								</tr>
								<tr>
									<th class="tc">신청일자</th>
									<td class="tc">
										<p class="txt_01">${result.reqDate}</p>
									</td>
									<th class="tc">입고희망일</th>
									<td class="tc"><p class="txt_01">${result.chulDate}</p></td>
								</tr>
								<!--  
								<tr>
									<th class="tc">상자형</th>
									<td class="tc" colspan="3">
										<p class="txt_01"><c:if test="${result.boxCode != null}">[ ${result.boxCode} ] </c:if>${result.boxName}</p>
									</td>
								</tr>
								-->
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
								<c:if test="${result.fixState eq '2'}">
								<tr>
									<th class="tc">경매사미체결사유</th>
									<td class="tc" colspan="3"><p class="txt_01"><pre>${result.aucBanText}</pre></p></td>
								</tr>
								<tr>
									<th class="tc">출하자미체결사유</th>
									<td class="tc" colspan="3"><p class="txt_01"><pre>${result.chulBanText}</pre></p></td>
								</tr>
								</c:if>
							</tbody>
						</table>
					</div>
					<!-- 테이블03(E) -->

					<!-- 버튼박스(S) -->
					<div class="btn_box mt30">
						<c:if test="${result.fixState eq '1'}">
							<a href="javascript:fn_update('${result.reqDealSeq}')" class="btn_type_01 fix">수정</a>
							<a href="javascript:fn_delete('${result.reqDealSeq}')" class="btn_type_01 del">삭제</a>
						</c:if>
						<c:if test="${result.fixState eq '2'}">
							<a href="javascript:fn_update('${result.reqDealSeq}')" class="btn_type_01 fix">재신청</a>
							<a href="javascript:fn_delete('${result.reqDealSeq}')" class="btn_type_01 del">삭제</a>
						</c:if>
						<a href="${pageContext.request.contextPath}/front/fixNAuc/reqBuyList.do" class="btn_type_01 list" id="listBtn">목록</a>
						
					</div>
					<!-- 버튼박스(E) -->

				</div>

			</div>
			<!-- sub내용(E) -->
			
			
			

<script type="text/javascript">

function fn_update(reqDealSeq){
	window.location.href = "${pageContext.request.contextPath}/front/fixNAuc/reqBuyUpt.do?reqDealSeq="+reqDealSeq;
}

function fn_delete(reqDealSeq){
	window.location.href = "${pageContext.request.contextPath}/front/fixNAuc/reqBuyDelProc.do?reqDealSeq="+reqDealSeq;
}


$( document ).ready(function() {
	
});


</script>
			
			