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
									<th class="tc">품종명</th>
									<td class="tc"><p class="txt_01">${result.pumName}</p></td>
									<th class="tc">사용여부</th>
									<td class="tc">
										<c:if test="${result.useYn eq 'Y'}">
										<p class="txt_01">사용</p>
										</c:if>
										<c:if test="${result.useYn ne 'Y'}">
										<p class="txt_01">미사용</p>
										</c:if>
									</td>
								</tr>
								<tr>
									<th class="tc">출하자명<br/>(코드)</th>
									<td class="tc"><p class="txt_01 dib">${result.chulName}(${fn:substring(result.chulCode,0,4)} - ${fn:substring(result.chulCode,4,8)})</p></td>
									<th class="tc">판매단가</th>
									<td class="tc"><fmt:formatNumber value="${result.unitPrice}" pattern="#,###" /></p></td>
								</tr>
								<tr>
									<th class="tc">1상자당 분수량</th>
									<td class="tc" colspan="3">
										<p class="txt_01">
											<c:if test="${result.unitSok ne null}">${result.unitSok} 분</c:if>
											<c:if test="${result.unitSok eq null}">정보없음</c:if> 
										</p>
									</td>
								</tr>
								<tr>
									<th class="tc">상품설명</th>
									<td class="tc" colspan="3"><p class="txt_01"><pre>${result.itemText}</pre></p></td>
								</tr>
							</tbody>
						</table>
					</div>
					<!-- 테이블03(E) -->

					<!-- 버튼박스(S) -->
					<div class="btn_box mt30">
						<a href="javascript:fn_update('${result.fixSubDealSeq}')" class="btn_type_01 fix">수정</a>
						<a href="javascript:fn_delete('${result.fixSubDealSeq}')" class="btn_type_01 del">삭제</a>
						<a href="${pageContext.request.contextPath}/front/fixCAuc/fixSellSubList.do" class="btn_type_01 list">목록</a>
					</div>
					<!-- 버튼박스(E) -->

				</div>

			</div>
			<!-- sub내용(E) -->
			
			

<script type="text/javascript">

function fn_update(fixSubDealSeq){
	window.location.href = "${pageContext.request.contextPath}/front/fixCAuc/fixSellSubUpt.do?fixSubDealSeq="+fixSubDealSeq;
}

function fn_delete(fixSubDealSeq){
	window.location.href = "${pageContext.request.contextPath}/front/fixCAuc/fixSellSubDelProc.do?fixSubDealSeq="+fixSubDealSeq;
}


function slide(imgCnt){
	if(imgCnt > 1){
		$('.slide_box').slick({
			 slide: 'span',
			 infinite: true,
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
		
		$('.slide_box.mobile span').css("width","100%");
	    
	}
}

$( document ).ready(function() {
	slide($(".slide_box span").size());
});


</script>
			
			