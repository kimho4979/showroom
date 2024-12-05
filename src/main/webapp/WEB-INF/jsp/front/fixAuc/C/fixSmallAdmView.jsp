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
					
					<div class="slide_box web">
						<c:forEach items="${fixFileList}" var="file" varStatus="status">
							<span class="img_size">
								<img src="${pageContext.request.contextPath}/uploads/fixSmallAuc/C/${file.thumbStreFileNm}" alt="사진_${result.pumName}">
							</span>
						</c:forEach>
					</div>
					
					<div class="slide_box mobile" style="text-align: center;" >
						<c:forEach items="${fixFileList}" var="file" varStatus="status">
							<span class="img_size">
								<img src="${pageContext.request.contextPath}/uploads/fixSmallAuc/C/${file.thumbStreFileNm}" alt="사진_${result.pumName}">
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
									<th class="tc">상장번호</th>
									<td class="tc" colspan="3"><p class="txt_01">${result.upNo}</p></td>
								</tr>
								<tr>
									<th class="tc">품목명</th>
									<td class="tc"><p class="txt_01">${result.mokName}</p></td>
									<th class="tc">품종명</th>
									<td class="tc"><p class="txt_01">${result.pumName}<c:if test="${result.twinCnt ne '0'}">/${result.twinCnt}</c:if></p></td>
								</tr>
								<tr>
									<th class="tc">등급</th>
									<td class="tc"><p class="txt_01">${result.chulLevelName}</p></td>
									<th class="tc">상자수</th>
									<td class="tc"><p class="txt_01"><fmt:formatNumber value="${result.boxCnt}" pattern="#,###" /></p></td>
								</tr>
								<tr>
									<th class="tc">총 속/분 수량</th>
									<td class="tc"><p class="txt_01"><fmt:formatNumber value="${result.sokCnt}" pattern="#,###" /></p></td>
									<th class="tc">입찰된 수량</th>
									<td class="tc"><p class="txt_01"><fmt:formatNumber value="${result.bidSokCnt}" pattern="#,###" /></p></td>
								</tr>
								<tr>
									<th class="tc">경매일자</th>
									<td class="tc">
										<c:if test="${result.aucDate eq '-' or result.aucDate eq null}">
											<input type="hidden" id="aucDate" value="${result.chulDate}">
											${result.chulDate}
										</c:if>
										<c:if test="${result.aucDate ne '-' and result.aucDate ne null}">
											<input type="hidden" id="aucDate" value="${result.aucDate}">
											${result.aucDate}
										</c:if>
									</td>
									<th class="tc">출하예정일</th>
									<td class="tc"><p class="txt_01">${result.chulDate}</p></td>
								</tr>
								<tr>
									<th class="tc">출하자명(코드)</th>
									<td class="tc" colspan="1"><p class="txt_01 dib">${result.chulName}(${fn:substring(result.chulCode,0,4)} - ${fn:substring(result.chulCode,4,8)})</p><!-- <a href="#layerSample3" class="btn_type_01 btn-popup fix ml10" onclick="fn_popfucus();">문자</a> --></td>
									<th class="tc">기준가</th>
									<td class="tc"><p class="txt_01"><fmt:formatNumber value="${result.stdPrice}" pattern="#,###" /></p></td>

								</tr>
								<tr>
									<th class="tc">상태</th>
									<td class="tc" <c:if test="${result.mokCode ne '1005'}">colspan="1"</c:if>><p class="txt_01">
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
									<c:if test="${result.mokCode eq '1005'}">
										<th class="tc">쌍대/송이</th>
										<td class="tc"><p class="txt_01">${result.twinCnt}</p></td>
									</c:if>
									<th class="tc">온라인낙찰</th>
									<td class="tc"><p class="txt_01">${result.bidSuccess}</p></td>
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
						<c:if test="${result.fixState eq '1' or result.fixState eq '2'}">
							<%-- <a href="javascript:fn_update('${result.fixDealSeq}');" class="btn_type_01 fix">수정</a>
							<a href="javascript:fn_delete('${result.fixDealSeq}');" class="btn_type_01 del">삭제</a> --%>
							<%-- <a href="javascript:fn_apply('${result.fixDealSeq}');" class="btn_type_01 ok">승인</a> --%>
						</c:if>
						
						
						<c:if test="${result.fixState eq '3' or result.fixState eq '7' or result.fixState eq '8'}">
							<%-- <a href="javascript:fn_appCancel('${result.fixDealSeq}');" class="btn_type_01 ok">승인취소</a> --%>
						</c:if>
						<c:if test="${result.fixState eq '1' or result.fixState eq '3' or result.fixState eq '7' or result.fixState eq '8'}">
							<!-- <a href="#layerSample2" class="btn_type_01 btn-popup re">반려</a> -->
						</c:if>
						<c:if test="${result.fixState eq '1' or result.fixState eq '2' or result.fixState eq '3' or result.fixState eq '7'}">
							<a href="javascript:fn_update('${result.fixDealSeq}');" class="btn_type_01 fix">사진관리</a>
						</c:if>
						<c:if test="${result.fixState eq '8'}">
							<a href="javascript:fn_comp('${result.fixDealSeq}');" class="btn_type_01 btn-popup fix">완료</a>
						</c:if>
						
						<a href="#layerSample" class="btn_type_01 btn-popup re">입찰자 정보</a>
						<a href="#layerSample4" class="btn_type_01 btn-popup re">상태 변경 이력</a>
						<a href="${pageContext.request.contextPath}/front/fixCAuc/fixSmallAdmList.do" class="btn_type_01 list" id="listBtn">목록</a>
					</div>
					<!-- 버튼박스(E) -->

				</div>

			</div>
			<!-- sub내용(E) -->
			
			
			<!-- 팝업(S) -->
	<div class="dim-layer" id="layerSample">
		<div class="dimBg"></div>
		<div class="pop-layer popbox pop_type_01">
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
						<div class="table_type_01 mt10 overflow_a">
							<table>
								<caption>입찰정보</caption>
								<colgroup>
									<col style="width:80px">
									<col style="width:80px">
									<col style="width:80px">
									<col style="width:80px">
									<col style="width:80px">
									<col style="width:80px">
									<c:if test="${result.fixState ne '4' and result.fixState ne '5' and result.fixState ne '6'}">
									<col style="width:80px">
									<col style="width:80px">
									<col style="width:80px">
									<col style="width:80px">
									<col style="width:80px">
									</c:if>
									<col style="width:80px">
								</colgroup>
								<thead>
									<tr>
										<th>중도매인코드</th>
										<th>입찰상자수</th>
										<th>입찰속/분수량</th>
										<th>낙찰수량</th>
										<th>입찰단가</th>
										<th>상태</th>
										<c:if test="${result.fixState ne '4' and result.fixState ne '5' and result.fixState ne '6'}">
										<th>예정상태</th>
										<th>입찰수정</th>
										<th>취소사유</th>
										<th>입찰취소</th>
										<th>추가입찰</th>
										</c:if>
										<th>입찰시간</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${bidList}" var="bidVO" varStatus="status">
									<tr>
										<td class="tc"><a href="#!" class="txt_01">${bidVO.jCode}</a></td>
										<td class="tc">
											<div class="ip_type_01 w100p">
												<input type="number" class="tr" id="bidBoxCnt${bidVO.bidSeq}" value="${bidVO.bidBoxCnt}" placeholder="-" maxlength="2" oninput="maxLengthCheck(this)">
											</div>
										</td>
										<td class="tc">
											<div class="ip_type_01 w100p">
												<input type="number" class="tr" id="bidSokCnt${bidVO.bidSeq}" value="${bidVO.bidSokCnt}" placeholder="-" maxlength="4" oninput="maxLengthCheck(this)">
											</div>
										</td>
										<td class="tc">
											<fmt:formatNumber value="${bidVO.nakSokCnt}" pattern="#,###" />
										</td>
										<td class="tc">
											<div class="ip_type_01 w100p">
												<input type="number" class="tr" id="bidUnitPrice${bidVO.bidSeq}" value="${bidVO.bidUnitPrice}" placeholder="-" maxlength="10" oninput="maxLengthCheck(this)">
											</div>
										</td>
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
										<c:if test="${result.fixState ne '4' and result.fixState ne '5' and result.fixState ne '6'}">
											<td class="tc"><a href="#!" class="txt_01">
												<c:if test="${bidVO.nakState eq '1'}">
													입찰
												</c:if>
												<c:if test="${bidVO.nakState eq '2'}">
													낙찰
												</c:if>
												<c:if test="${bidVO.nakState eq '3'}">
													패찰
												</c:if>
												<c:if test="${bidVO.nakState eq '4'}">
													부분낙찰
												</c:if>
												<c:if test="${bidVO.nakState eq '5'}">
													취소
												</c:if>
											</a></td>
											<td class="tc">
												<a href="javascript:fn_bidUpdate('${bidVO.bidSeq}','${result.fixDealSeq}')" class="btn_type_01">수정</a>
											</td>
											<td class="tc">
												<div class="ip_type_01 w100p">
													<input type="text" class="tl" id="bidCancelText${bidVO.bidSeq}" value="${bidVO.bidCancelText}" placeholder="-" >
												</div>
											</td>
											<td class="tc">
												<a href="javascript:fn_bidCancel('${bidVO.bidSeq}','${result.fixDealSeq}')" class="btn_type_01">취소</a>
											</td>
											<td class="tc">
												<c:if test="${bidVO.bidAdminYn eq 'Y' }">
													<a href="javascript:fn_bidAdmin('${bidVO.bidSeq}','${result.fixDealSeq}','');" class="btn_type_01">마감</a>
												</c:if>
												<c:if test="${bidVO.bidAdminYn ne 'Y' }">
													<a href="javascript:fn_bidAdmin('${bidVO.bidSeq}','${result.fixDealSeq}','Y');" class="btn_type_01">추가</a>
												</c:if>
											</td>
										</c:if>
										<td class="tc"><a href="#!" class="txt_01">
										</a>${bidVO.bidTime}</td>
									</tr>
									</c:forEach>
									<c:if test="${fn:length(bidList) eq 0}">
										<c:if test="${result.fixState eq '4' or result.fixState eq '5' or result.fixState eq '6'}">
										<td class="tc" colspan="7"><a href="#!" class="txt_01">입찰자가 없습니다.</a></td>
										</c:if>
										<c:if test="${result.fixState ne '4' and result.fixState ne '5' and result.fixState ne '6'}">
										<td class="tc" colspan="12"><a href="#!" class="txt_01">입찰자가 없습니다.</a></td>
										</c:if>
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
	<div class="dim-layer" id="layerSample2">
		<div class="dimBg"></div>
		<div class="pop-layer popbox pop_type_02">
			<div class="pop-container">
				<a href="#!" class="btn-layerClose pop_btn_close_02 close_B" alt="닫기 버튼"></a>
				<div class="pop-conts_00">
					<!-- 팝업 타이틀 박스(S) -->
					<div class="pop_title_box">
						<div class="fl">
							<p class="pop_title">반려사유</p>
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
							<a href="javascript:fn_ban('${result.fixDealSeq}');" class="btn_search_03">반려</a>
						
					</div>
					<!-- 팝업 컨텐츠(E) -->
				</div>
			</div>
		</div>
	</div>
	<div class="dim-layer" id="layerSample3">
		<div class="dimBg"></div>
		<div class="pop-layer popbox pop_type_01">
			<div class="pop-container">
				<a href="#!" class="btn-layerClose pop_btn_close_02 close_B" alt="닫기 버튼"></a>
				<div class="pop-conts_00">
					<!-- 팝업 타이틀 박스(S) -->
					<div class="pop_title_box">
						<div class="fl">
							<p class="pop_title">문자발송</p>
						</div>
					</div>
					<!-- 팝업 타이틀 박스(E) -->

					<!-- 팝업 컨텐츠(S) -->
					<div class="choice_box">
						<div class="table_type_04 mb10">
						수신자 번호 : ${result.phoneNo}
						</div>
						<div class="table_type_04 mb10">
						발신자 번호 : <input type="text" class="tl" id="sendPhoneNo1" value="025798100">
						</div>
						<textarea wrap="physical" id="smsText1" style="width:100%;height:100px;border: 1px solid;font-size: 12px;padding: 2px 2px 2px 2px;">${result.chulName} ${result.mokName}[${result.pumName}] ${result.boxCnt}상자(${result.sokCnt}속) 
						</textarea>
						<a href="javascript:fn_smsMms('${result.chulCode}','1');" class="btn_search_03">발송</a>
						
					</div>
					<!-- 팝업 컨텐츠(E) -->
				</div>
			</div>
		</div>
	</div>
	<div class="dim-layer" id="layerSample4">
		<div class="dimBg"></div>
		<div class="pop-layer popbox pop_type_01">
			<div class="pop-container">
				<a href="#!" class="btn-layerClose pop_btn_close_02 close_B" alt="닫기 버튼"></a>
				<div class="pop-conts_00">
					<!-- 팝업 타이틀 박스(S) -->
					<div class="pop_title_box">
						<div class="fl">
							<p class="pop_title">판매 상태 변경 이력</p>
						</div>
					</div>
					<!-- 팝업 타이틀 박스(E) -->

					<!-- 팝업 컨텐츠(S) -->
					<div class="choice_box">
						<div class="table_type_01 mt10 overflow_a" <c:if test="${fn:length(recordList) >= 8}"> style="height: 352px; overflow: auto; border: 1px solid #dcdcdc;"</c:if>>
							<table>
								<caption>판매 상태 변경 이력</caption>
								<colgroup>
									<col style="width:40px">
									<col style="width:80px">
									<col style="width:80px">
									<col style="width:80px">
									<col style="width:100px">					
								</colgroup>
								<thead>
									<tr>
										<th>순번</th>
										<th>변경 전 상태</th>
										<th>변경 후 상태</th>
										<th>변경 아이디</th>
										<th>변경 시간</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${recordList}" var="record" varStatus="vs">
									<tr <c:if test="${vs.last eq true}">style='font-weight: bold;'</c:if>>
										<td class="tc">
											${vs.count}
										</td>
										<td class="tc">
											<c:if test="${record.preFixState eq '1'}">
												신청
											</c:if>
											<c:if test="${record.preFixState eq '2'}">
												반려
											</c:if>
											<c:if test="${record.preFixState eq '3'}">
												준비
											</c:if>
											<c:if test="${record.preFixState eq '4'}">
												완료
											</c:if>
											<c:if test="${record.preFixState eq '5'}">
												유찰
											</c:if>
											<c:if test="${record.preFixState eq '6'}">
												부분유찰
											</c:if>
											<c:if test="${record.preFixState eq '7'}">
												입찰
											</c:if>
											<c:if test="${record.preFixState eq '8'}">
												마감
											</c:if>
											<c:if test="${not empty record.preFixState or record.preFixState eq ''}">
												( ${record.preFixState} )
											</c:if>
										</td>
										<td class="tc">
											<c:if test="${record.fixState eq '1'}">
												신청
											</c:if>
											<c:if test="${record.fixState eq '2'}">
												반려
											</c:if>
											<c:if test="${record.fixState eq '3'}">
												준비
											</c:if>
											<c:if test="${record.fixState eq '4'}">
												완료
											</c:if>
											<c:if test="${record.fixState eq '5'}">
												유찰
											</c:if>
											<c:if test="${record.fixState eq '6'}">
												부분유찰
											</c:if>
											<c:if test="${record.fixState eq '7'}">
												입찰
											</c:if>
											<c:if test="${record.fixState eq '8'}">
												마감
											</c:if>
											<c:if test="${not empty record.fixState or record.fixState eq ''}">
												( ${record.fixState} )
											</c:if>
										</td>
										<td class="tc">${record.insertId}</td>
										<td class="tc">${record.insertDate}</td>
									</tr>
									</c:forEach>
									<c:if test="${fn:length(recordList) eq 0}">
										<td class="tc" colspan="5">상태 변경 이력이 없습니다.</td>
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
	window.location.href = "${pageContext.request.contextPath}/front/fixCAuc/fixSmallAdmUpt.do?fixDealSeq="+fixDealSeq;
}

/* function fn_delete(fixDealSeq){
	window.location.href = "${pageContext.request.contextPath}/front/fixCAuc/fixAdmDelProc.do?fixDealSeq="+fixDealSeq;
} */

function fn_ban(fixDealSeq){
	var checkedArray = new Array();
	var banTextArray = new Array();
	
	checkedArray.push(fixDealSeq);
	banTextArray.push($("#banText").val());
	
	
	$.ajax({
		data:{
			checkedArray: checkedArray,
			banTextArray: banTextArray
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixCAuc/fixSmallAdmBan.json",
        success : function(data){
             alert("정삭적으로 반려처리되었습니다.");
             location.reload(true);
        }
    });
	
}


function fn_apply(fixDealSeq){
	var checkedArray = new Array();
	var aucDateArray = new Array();
	
	checkedArray.push(fixDealSeq);
	aucDateArray.push($("#aucDate").val());
	
	
	$.ajax({
		data:{
			checkedArray: checkedArray,
			aucDateArray: aucDateArray
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixCAuc/fixSmallAdmApply.json",
        success : function(data){
             alert("정삭적으로 승인처리되었습니다.");
             location.reload(true);
        }
    });
	
	
}



function fn_comp(fixDealSeq){
	var checkedArray = new Array();
	var aucDateArray = new Array();
	
	checkedArray.push(fixDealSeq);
	aucDateArray.push($("#aucDate").val());
	
	
	$.ajax({
		data:{
			checkedArray: checkedArray,
			aucDateArray: aucDateArray
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixCAuc/fixSmallAdmComp.json",
        success : function(data){
             alert("정삭적으로 완료처리되었습니다.");
             location.reload(true);
        }
    });
	
}

function fn_appCancel(fixDealSeq){
	//fixAdmAppCancel
	
	var checkedArray = new Array();
	var aucDateArray = new Array();
	
	checkedArray.push(fixDealSeq);
	aucDateArray.push($("#aucDate").val());
	
	
	$.ajax({
		data:{
			checkedArray: checkedArray,
			aucDateArray: aucDateArray
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixCAuc/fixSmallAdmAppCancel.json",
        success : function(data){
             alert("정상적으로 승인취소 처리되었습니다.");
             location.reload(true);
        }
    });
	
	
}


function slide(imgCnt){
	if(imgCnt > 1){
		$('.slide_box').slick({
			 slide: 'span',
			 infinite: true,
	         slidesToShow: 1,
	         slidesToScroll: 1,
	         centerMode: true,
	         arrows: false,
	         responsive: [
	             {
	                 breakpoint: 1024,
	                 settings: {
	                     slidesToShow: 1,
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
		
		$('.slide_box.mobile span').css("width","100%");
	    /*$('.slide_box').css("text-align","center");
		*/
	}
}


function fn_bidAdmin(bidSeq, fixDealSeq, bidAdminYn){
	$.ajax({
		data:{
			bidSeq: bidSeq,
			fixDealSeq: fixDealSeq,
			bidAdminYn: bidAdminYn
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixCAuc/fixSmallAdmBidAdmin.json",
        success : function(data){
             alert("정삭적으로 처리되었습니다.");
             location.reload(true);
        }
    });
}

function fn_bidCancel(bidSeq, fixDealSeq){
	var bidCancelText = $("#bidCancelText"+bidSeq).val();
	if(bidCancelText != null && bidCancelText != ""){
		if(confirm("입찰 취소하시겠습니까?")){
			$.ajax({
				data:{
					bidSeq: bidSeq,
					fixDealSeq: fixDealSeq,
					bidCancelText: bidCancelText
			        },
		        type : "POST",
		        url : "${pageContext.request.contextPath}/front/fixCAuc/fixSmallAdmBidCancel.json",
		        success : function(data){
		             alert("정삭적으로 처리되었습니다.");
		             location.reload(true);
		        }
		    });
		}
	}else{
		alert("취소사유를 입력해주세요.");
		$("#bidCancelText"+bidSeq).focus();
	}
}


function fn_bidUpdate(bidSeq, fixDealSeq){
	var bidBoxCnt = $("#bidBoxCnt"+bidSeq).val();
	var bidSokCnt = $("#bidSokCnt"+bidSeq).val();
	var bidUnitPrice = $("#bidUnitPrice"+bidSeq).val();
	
	if(bidBoxCnt == null || bidBoxCnt == ""){
		alert("입찰상자수를 입력해주세요");
		$("#bidBoxCnt"+bidSeq).focus();
		return false;
	}
	
	if(bidSokCnt == null || bidSokCnt == ""){
		alert("입찰속수를 입력해주세요");
		$("#bidSokCnt"+bidSeq).focus();
		return false;
	}
	
	if(bidUnitPrice == null || bidUnitPrice == ""){
		alert("입찰 단가를 입력해주세요");
		$("#bidUnitPrice"+bidSeq).focus();
		return false;
	}
	
	
	if(confirm("입찰 수정하시겠습니까?")){
		$.ajax({
			data:{
				bidSeq: bidSeq,
				fixDealSeq: fixDealSeq,
				bidBoxCnt: bidBoxCnt,
				bidSokCnt: bidSokCnt,
				bidUnitPrice: bidUnitPrice
		        },
	        type : "POST",
	        url : "${pageContext.request.contextPath}/front/fixCAuc/fixSmallAdmBidUpdate.json",
	        success : function(data){
	             alert("정삭적으로 수정되었습니다.");
	             location.reload(true);
	        }
	    });
	}
}

function fn_smsMms(code , codeType){
	var message = $("#smsText"+codeType).val();
	var sendPhoneNo = $("#sendPhoneNo"+codeType).val();
	
	if(sendPhoneNo == "" || sendPhoneNo == null){
		alert("발신자번호를 입력해 주세요");
		$("#sendPhoneNo"+codeType).focus();
		return false;
	}
	if(message == "" || message == null){
		alert("메세지를 입력해 주세요");
		$("#smsText"+codeType).focus();
		return false;
	}
	
	$.ajax({
		data:{
			code: code,
			codeType: codeType,
			sendPhoneNo : sendPhoneNo,
			message: message
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixCAuc/sendSmsMms.json",
        success : function(data){
             if(data.result == "1"){
            	alert("문자가 발송되었습니다.");
            	$('.dim-layer').fadeOut();
         		$('body').css('overflow','auto');
         		$('body').css('position','relative');
             }else if(data.result == "0"){
            	 alert("수신자 정보가 없습니다.");
             }else if(data.result == "2"){
            	 alert("4000byte를 초과합니다. \n"+data.msgByte+"byte");
             } 
        	
        }
    });
}


$( document ).ready(function() {
	slide($(".slide_box.mobile span").size());
});


function fn_popfucus(){
	
}



</script>
			
			