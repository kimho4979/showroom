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

					<!-- 슬라이드 이미지(S) /uploads/fixAuc/C/-->
					
					<div class="slide_box">
						<c:forEach items="${fixFileList}" var="file" varStatus="status">
							<span class="img_size">
								<img src="${pageContext.request.contextPath}/uploads/fixAuc/C/${file.thumbStreFileNm}" alt="사진_${result.pumName}">
							</span>
						</c:forEach>
					</div>
					<!-- 슬라이드 이미지(E) -->

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
									<td class="tc"><p class="txt_01">${result.pumName}</p></td>
								</tr>
								<tr>
									<th class="tc">출하자명<br/>(코드)</th>
									<td class="tc"><p class="txt_01 dib">${result.chulName}(${fn:substring(result.chulCode,0,4)} - ${fn:substring(result.chulCode,4,8)})</p></td>
									<th class="tc">판매단가</th>
									<td class="tc"><p class="txt_01">
													<c:if test="${result.dealType eq 'L'}">
														최저가
													</c:if>
													<c:if test="${result.dealType eq 'W'}">
														희망가
													</c:if>
													<c:if test="${result.dealType eq 'F'}">
														정가
									</c:if>/<fmt:formatNumber value="${result.unitPrice}" pattern="#,###" /></p></td>
								</tr>
								<tr>
									<th class="tc">등급</th>
									<td class="tc"><p class="txt_01">${result.chulLevelName}</p></td>
									<th class="tc">상자수</th>
									<td class="tc"><p class="txt_01"><fmt:formatNumber value="${result.boxCnt}" pattern="#,###" /></p></td>
								</tr>
								<tr>
									<th class="tc">총 분수량</th>
									<td class="tc"><p class="txt_01"><fmt:formatNumber value="${result.sokCnt}" pattern="#,###" /></p></td>
									<th class="tc">입찰된 수량</th>
									<td class="tc"><p class="txt_01"><fmt:formatNumber value="${result.bidSokCnt}" pattern="#,###" /></p></td>
								</tr>
								<tr>
									<th class="tc">경매일자</th>
									<td class="tc">
										<p class="txt_01">
											${result.aucDate}
											<c:if test="${result.aucDate eq null}">
												-
											</c:if>
										</p>
									</td>
									<th class="tc">출하예정일</th>
									<td class="tc"><p class="txt_01">${result.chulDate}</p></td>
								</tr>
								<tr>
									<th class="tc">상태</th>
									<td class="tc" <c:if test="${result.mokCode ne '005'}">colspan="3"</c:if>><p class="txt_01">
									<c:if test="${result.fixState eq '1'}">
										신청
								    </c:if>
								    <c:if test="${result.fixState eq '2'}">
										반려
								   </c:if>
								   <c:if test="${result.fixState eq '3'}">
										준비
								   </c:if>
								   <c:if test="${result.fixState eq '4'}">
										완료
								   </c:if>
								   <c:if test="${result.fixState eq '5'}">
										유찰
								   </c:if>
								   <c:if test="${result.fixState eq '6'}">
										부분유찰
								   </c:if>
								   <c:if test="${result.fixState eq '7'}">
										<c:choose>
											<c:when test="${result.fixStateTwo eq '9'}">
												입찰대기 
										    </c:when>
									   		<c:otherwise>
												입찰
								            </c:otherwise>
						    	   		</c:choose>
								   </c:if>
								   <c:if test="${result.fixState eq '8'}">
										마감
								   </c:if>
									</p></td>
									<c:if test="${result.mokCode eq '005'}">
										<th class="tc">쌍대/송이</th>
										<td class="tc"><p class="txt_01">${result.twinCnt}</p></td>
									</c:if>
								</tr>
								<tr>
									<th class="tc">상품설명</th>
									<td class="tc" colspan="3"><p class="txt_01"><pre>${result.itemText}</pre></p></td>
								</tr>
								<c:if test="${result.fixState eq '2'}">
								<tr>
									<th class="tc">반려사유</th>
									<td class="tc" colspan="3"><p class="txt_01"><pre>${result.aucBanText}</pre></p></td>
								</tr>
								</c:if>
							</tbody>
						</table>
					</div>
					<!-- 테이블03(E) -->

					<!-- 버튼박스(S) -->
					<div class="btn_box mt30">
						<c:if test="${result.fixState eq '1'}">
							<a href="javascript:fn_update('${result.fixDealSeq}')" class="btn_type_01 fix">수정</a>
							<a href="javascript:fn_delete('${result.fixDealSeq}')" class="btn_type_01 del">삭제</a>
						</c:if>
						<c:if test="${result.fixState eq '2'}">
							<a href="javascript:fn_update('${result.fixDealSeq}')" class="btn_type_01 fix">재신청</a>
							<a href="javascript:fn_delete('${result.fixDealSeq}')" class="btn_type_01 del">삭제</a>
						</c:if>
						<a href="${pageContext.request.contextPath}/front/fixCAuc/fixSellList.do" class="btn_type_01 list">목록</a>
						<c:if test="${result.fixState eq '4' or result.fixState eq '6'}">
						<a href="#layerSample" class="btn_type_01 btn-popup re">입찰자 정보</a>
						</c:if>
					</div>
					<!-- 버튼박스(E) -->

				</div>

			</div>
			<!-- sub내용(E) -->
			
			
			<!-- 팝업(S) -->
	<div class="dim-layer" id="layerSample">
		<div class="dimBg"></div>
		<div class="pop-layer popbox pop_type_02">
			<div class="pop-container">
				<a href="#!" class="btn-layerClose pop_btn_close_02 close_B" alt="닫기 버튼"></a>
				<div class="pop-conts_00">
					<!-- 팝업 타이틀 박스(S) -->
					<div class="pop_title_box">
						<div class="fl">
							<p class="pop_title">입찰자정보</p>
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
						<div class="table_type_04 mt10 overflow_a" style="max-height: 250px;">
							<table>
								<caption>입찰정보</caption>
								<colgroup>
									<col style="width:20%">
									<col style="width:15%">
									<col style="width:15%">
									<col style="width:15%">
									<col style="width:20%">
									<col style="width:15%">
								</colgroup>
								<thead>
									<tr>
										<th>중도매인<br>코드</th>
										<th>입찰<br>상자수</th>
										<th>입찰<br>분수량</th>
										<th>낙찰<br>분수량</th>
										<th>입찰단가</th>
										<th>상태</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${bidList}" var="bidVO" varStatus="status">
										<c:if test="${bidVO.bidState eq '2' or bidVO.bidState eq '4'}">
											<tr>
												<td class="tc"><a href="#!" class="txt_01">${bidVO.jCode}</a></td>
												<td class="tc"><a href="#!" class="txt_01"><fmt:formatNumber value="${bidVO.bidBoxCnt}" pattern="#,###" /></a></td>
												<td class="tc"><a href="#!" class="txt_01"><fmt:formatNumber value="${bidVO.bidSokCnt}" pattern="#,###" /></a></td>
												<td class="tc">
													<fmt:formatNumber value="${bidVO.nakSokCnt}" pattern="#,###" />
												</td>
												<td class="tc"><a href="#!" class="txt_01"><fmt:formatNumber value="${bidVO.bidUnitPrice}" pattern="#,###" /></a></td>
												
												<td class="tc"><a href="#!" class="txt_01">
													<c:if test="${bidVO.bidState eq '1'}">
														입찰
													</c:if>
													<c:if test="${bidVO.bidState eq '2'}">
														낙찰
													</c:if>
													<c:if test="${bidVO.bidState eq '3'}">
														패찰
													</c:if>
													<c:if test="${bidVO.bidState eq '4'}">
														부분낙찰
													</c:if>
													<c:if test="${bidVO.nakState eq '5'}">
														취소
													</c:if>
												</a></td>
											</tr>
										</c:if>
									</c:forEach>
									<c:if test="${fn:length(bidList) eq 0}">
										<td class="tc" colspan="6"><a href="#!" class="txt_01">입찰자가 없습니다.</a></td>
									</c:if>
								</tbody>
							</table>
						</div>
					</div>
					<!-- 팝업 컨텐츠(E) -->
				</div>
			</div>
		</div>
	</div>
	<!-- 팝업(E) -->

<script type="text/javascript">

function fn_update(fixDealSeq){
	window.location.href = "${pageContext.request.contextPath}/front/fixCAuc/fixSellUpt.do?fixDealSeq="+fixDealSeq;
}

function fn_delete(fixDealSeq){
	window.location.href = "${pageContext.request.contextPath}/front/fixCAuc/fixSellDelProc.do?fixDealSeq="+fixDealSeq;
}


function slide(imgCnt){
	if(imgCnt > 1){
		$('.slide_box').slick({
			 slide: 'span',
			 infinite: true,
			 //vertical: true,
	         slidesToShow: 2,
	         slidesToScroll: 1,
	         centerMode: true,
	         arrows: false,
	         responsive: [
	             {
	                 breakpoint: 1024,
	                 settings: {
	                     slidesToShow: 2,
	                     slidesToScroll: 1,
	                     infinite: true
	                 }
	             },
	             {
	                 breakpoint: 600,
	                 settings: {
	                     slidesToShow: 1,
	                     slidesToScroll: 1,
	                     infinite: true
	                 }
	             },
	             {
	                 breakpoint: 480,
	                 settings: {
	                     slidesToShow: 1,
	                     slidesToScroll: 1,
	                     infinite: true
	                 }
	             }
	         ]
		});
	}else {
		/*
		$('.slide_box').slick({
			infinite: true
	    });*/	
		
		$('.slide_box span').css("width","100%");
	    /*$('.slide_box').css("text-align","center");
		*/
	}
}

$( document ).ready(function() {
	slide($(".slide_box span").size());
});


</script>

<link href="${pageContext.request.contextPath}/css/slide.css" rel="stylesheet">
			
			