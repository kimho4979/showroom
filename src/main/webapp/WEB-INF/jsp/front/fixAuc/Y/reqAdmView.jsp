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
									<td class="tc"><p class="txt_01 dib">${result.jName}(${result.jCode})</p><a href="#layerSample4" class="btn_type_01 btn-popup fix ml10">문자</a></td>
								</tr>
								<tr>
									<th class="tc">품목명</th>
									<td class="tc"><p class="txt_01">${result.mokName}</p></td>
									<th class="tc">품종명</th>
									<td class="tc"><p class="txt_01">${result.pumName}<c:if test="${result.twinCnt ne '0'}">/${result.twinCnt}</c:if></p></td>
								</tr>
								<tr>
									<th class="tc">출하자명<br/>(코드)</th>
									<td class="tc"><p class="txt_01 dib">${result.chulName}(${fn:substring(result.chulCode,0,4)} - ${fn:substring(result.chulCode,4,8)})</p><a href="#layerSample3" class="btn_type_01 btn-popup fix ml10">문자</a></td>
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
									<th class="tc">요청 총 분수량</th>
									<td class="tc"><p class="txt_01"><fmt:formatNumber value="${result.sokCnt}" pattern="#,###" /></p></td>
									<th class="tc">경매사 조정단가</th>
									<td class="tc">
										<div class="ip_type_01 w100">
											<c:if test="${result.aucPrice ne null }">
												<input type="number" class="tc" id="aucPrice" value="${result.aucPrice}">
											</c:if>
											<c:if test="${result.aucPrice eq null }">
												<input type="number" class="tc" id="aucPrice" value="${result.unitPrice}">
											</c:if>
											<label for="aucPrice"></label>
										</div>
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
									<td class="tc"><p class="txt_01">
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
									<th class="tc">쌍대/송이</th>
									<td class="tc"><p class="txt_01">${result.twinCnt}</p></td>
								</tr>
								<tr>
									<th class="tc">판매유형</th>
									<td class="tc" colspan="3"><p class="txt_01"><c:if test="${result.panType eq '03'}">선취</c:if><c:if test="${result.panType eq '04'}">사전</c:if></p></td>
								</tr>
								<tr>
									<th class="tc">요청 및 개선사항</th>
									<td class="tc" colspan="3"><p class="txt_01"><pre>${result.reqText}</pre></p></td>
								</tr>
								<c:if test="${result.fixState eq '2'}">
								<tr>
									<th class="tc">경매사 미체결사유</th>
									<td class="tc" colspan="3"><p class="txt_01"><pre>${result.aucBanText}</pre></p></td>
								</tr>
								<tr>
									<th class="tc">출하자 미체결사유</th>
									<td class="tc" colspan="3"><p class="txt_01"><pre>${result.chulBanText}</pre></p></td>
								</tr>
								</c:if>
							</tbody>
						</table>
					</div>
					<!-- 테이블03(E) -->

					<!-- 버튼박스(S) -->
					<div class="btn_box mt30">
						<a href="javascript:fn_update('${result.reqDealSeq}');" class="btn_type_01 fix">수정</a>
						<c:if test="${result.fixState eq '1' or result.fixState eq '2'}">
							<a href="javascript:fn_delete('${result.reqDealSeq}');" class="btn_type_01 del">삭제</a>
							<a href="javascript:fn_apply('${result.reqDealSeq}');" class="btn_type_01 ok">진행</a>
						</c:if>
						
						
						<c:if test="${result.fixState eq '3'}">
							<a href="javascript:fn_appCancel('${result.reqDealSeq}');" class="btn_type_01 ok">진행취소</a>
						</c:if>
						<c:if test="${result.fixState eq '1' or result.fixState eq '3'}">
							<a href="#layerSample2" class="btn_type_01 btn-popup re">미체결</a>
						</c:if>
						<c:if test="${result.fixState eq '3'}">
							<a href="javascript:fn_comp('${result.reqDealSeq}');" class="btn_type_01 btn-popup fix">체결</a>
						</c:if>
						<a href="#layerSample5" class="btn_type_01 btn-popup re">상태 변경 이력</a>
						<a href="${pageContext.request.contextPath}/front/fixYAuc/reqAdmList.do" class="btn_type_01 list" id="listBtn">목록</a>
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
	<div class="dim-layer" id="layerSample3">
		<div class="dimBg"></div>
		<div class="pop-layer popbox pop_type_02">
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
							<textarea wrap="physical" id="smsText1" style="width:100%;height:100px;border: 1px solid;font-size: 12px;padding: 2px 2px 2px 2px;">${result.chulName} ${result.mokName}[${result.pumName}] ${result.boxCnt}상자(${result.sokCnt}분) 
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
		<div class="pop-layer popbox pop_type_02">
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
						수신자 번호 : ${result.jPhoneNo}
						</div>
						<div class="table_type_04 mb10">
						발신자 번호 : <input type="text" class="tl" id="sendPhoneNo2" value="025798100">
						</div>
						<textarea wrap="physical" id="smsText2" style="width:100%;height:100px;border: 1px solid;font-size: 12px;padding: 2px 2px 2px 2px;">${result.chulName} ${result.mokName}[${result.pumName}] ${result.boxCnt}상자(${result.sokCnt}속) 
						</textarea>
						<a href="javascript:fn_smsMms('${result.jCode}','2');" class="btn_search_03">발송</a>
					</div>
					<!-- 팝업 컨텐츠(E) -->
				</div>
			</div>
		</div>
	</div>
	<div class="dim-layer" id="layerSample5">
		<div class="dimBg"></div>
		<div class="pop-layer popbox pop_type_01">
			<div class="pop-container">
				<a href="#!" class="btn-layerClose pop_btn_close_02 close_B" alt="닫기 버튼"></a>
				<div class="pop-conts_00">
					<!-- 팝업 타이틀 박스(S) -->
					<div class="pop_title_box">
						<div class="fl">
							<p class="pop_title">요청 상태 변경 이력</p>
						</div>
					</div>
					<!-- 팝업 타이틀 박스(E) -->

					<!-- 팝업 컨텐츠(S) -->
					<div class="choice_box">
						<div class="table_type_01 mt10 overflow_a" <c:if test="${fn:length(recordList) >= 8}"> style="height: 352px; overflow: auto; border: 1px solid #dcdcdc;"</c:if>>
							<table>
								<caption>요청 상태 변경 이력</caption>
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
												미체결
											</c:if>
											<c:if test="${record.preFixState eq '3'}">
												진행
											</c:if>
											<c:if test="${record.preFixState eq '4'}">
												체결
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
												미체결
											</c:if>
											<c:if test="${record.fixState eq '3'}">
												진행
											</c:if>
											<c:if test="${record.fixState eq '4'}">
												체결
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

function fn_update(reqDealSeq){
	window.location.href = "${pageContext.request.contextPath}/front/fixYAuc/reqAdmUpt.do?reqDealSeq="+reqDealSeq;
}

function fn_delete(reqDealSeq){
	window.location.href = "${pageContext.request.contextPath}/front/fixYAuc/reqAdmDelProc.do?reqDealSeq="+reqDealSeq;
}

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
        url : "${pageContext.request.contextPath}/front/fixYAuc/reqAdmBan.json",
        success : function(data){
             alert("정삭적으로 미체결처리되었습니다.");
             location.reload(true);
        }
    });
	
}


function fn_apply(reqDealSeq){
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
        url : "${pageContext.request.contextPath}/front/fixYAuc/reqAdmApply.json",
        success : function(data){
             alert("정삭적으로 진행처리되었습니다.");
             location.reload(true);
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
        url : "${pageContext.request.contextPath}/front/fixYAuc/reqAdmComp.json",
        success : function(data){
             alert("정삭적으로 체결처리되었습니다.");
             location.reload(true);
        }
    });
	
}

function fn_appCancel(reqDealSeq){
	//reqAdmAppCancel
	
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
        url : "${pageContext.request.contextPath}/front/fixYAuc/reqAdmAppCancel.json",
        success : function(data){
             alert("정상적으로 진행취소 처리되었습니다.");
             location.reload(true);
        }
    });
	
	
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
			sendPhoneNo: sendPhoneNo,
			message: message
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixYAuc/sendSmsMms.json",
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
	
});


</script>
			
			