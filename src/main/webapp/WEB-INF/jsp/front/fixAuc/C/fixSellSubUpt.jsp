<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<link href="${pageContext.request.contextPath}/assets/css/plugins/select2/select2.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/plugins/select2/select2-bootstrap4.min.css" rel="stylesheet">
<!-- Select2 -->
<script src="${pageContext.request.contextPath}/assets/js/plugins/select2/select2.full.min.js"></script>
<script src="${pageContext.request.contextPath}/js/fix.js"></script>

<!-- sub내용(S) -->
			<div class="sub_conts_in">

				<div class="info_box">

					<div class="m_col_wrap">
						<form id="frm" name="frm" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/front/fixCAuc/fixSellSubUptProc.do" >
						<input type="hidden" name="fixSubDealSeq" value="${result.fixSubDealSeq}">
						<input type="hidden" name="bunCheck" value="C">
						<div class="m_col_left">
							<!-- 테이블03(S) -->
								<input type="hidden" name="bunCheck" value="C">
								<div class="table_type_03">
									<table>
										<caption>info</caption>
										<colgroup>
											<col style="width:30%">
											<col style="width:70%">
										</colgroup>
										<tbody>
											<tr>
												<th class="tc">화훼구분</th>
												<td class="tl"><p class="txt_01">관엽</p></td>
											</tr>
											<tr>
												<th class="tc">출하자</th>
												<td class="tl">
													<div class="sel_type_03 w50p">
													<select id="chulCode" name="chulCode">
														<c:forEach items="${floList}" var="floVO" varStatus="status">
															<c:if test="${floVO.bunChk eq 'C'}">
																<option value="${floVO.code}" <c:if test="${result.chulCode eq  floVO.code}">selected="selected"</c:if>>${fn:substring(floVO.code,0,4)} - ${fn:substring(floVO.code,4,8)} ( ${floVO.name} )</option>
															</c:if>
														</c:forEach>	
													</select>
													</div>
												</td>
											</tr>
											<tr>
												<th class="tc">품목</th>
												<td class="tl">
													<div class="sel_type_03 w50p">
														<input type="hidden" name="mokName" id="mokName" value="분갈이완제품">
														<select id="mokCode" name="mokCode" onchange="getPumList();">
															<option value="029" selected="selected">분갈이완제품</option>
														</select>
														<label for="mokCode"></label>
													</div>
												</td>
											</tr>
											<tr style="display:none;">
												<th class="tc">품종</th>
												<td class="tl">
													<div class="sel_type_03 w50p">
														<input type="hidden" name="pumName" id="pumName" value="${result.pumName}">
														<select id="pumCode" name="pumCode" onchange="getPreList();">
															<option value="80290000" selected="selected">관엽류종합</option>
														</select>
														<label for="pumCode"></label>
													</div>
												</td>
											</tr>
											<tr>
												<th class="tc">품종명</th>
												<td class="tl">
													<div class="ip_type_01 w50p">
														<input type="text" class="tl" id="pumNameCustom" name="pumNameCustom" value="${result.pumName}" maxlength="500" >
														<label for="pumNameCustom"></label>
													</div><br>
													미입력 시 "관엽류종합"으로 표시됩니다.
												</td>
											</tr>
											<tr>
												<th class="tc">이미지</th>
												<td class="tl">
													<div class="file_box">
														<input type="text" class="upload_text" readonly="readonly">
														<!--button-->
														<div class="upload-btn_wrap">
														<button type="button" title="파일찾기">
														<span>파일찾기</span>  
														</button>
														<input type="file" class="input_file" name="attachFile" title="파일찾기" accept="image/*">
														</div>
													</div>
													<c:if test="${!empty file0}">
														<div class="file_box">
														<c:out value='${file0.orignlFileNm}'/> <input type="checkbox" name="deleteAtchFile" style="display:inline;" value="<c:out value='${file0.fileSeq}'/>" /> 삭제
														</div>
													</c:if>
													<div class="file_box">
														<input type="text" class="upload_text mt5" readonly="readonly">
														<!--button-->
														<div class="upload-btn_wrap mt5">
														<button type="button" title="파일찾기">
														<span>파일찾기</span>  
														</button>
														<input type="file" class="input_file" name="attachFile" title="파일찾기" accept="image/*">
														</div>
													</div>
													<c:if test="${!empty file1}">
														<div class="file_box">
														<c:out value='${file1.orignlFileNm}'/> <input type="checkbox" name="deleteAtchFile" style="display:inline;" value="<c:out value='${file1.fileSeq}'/>" /> 삭제
														</div>
													</c:if>
													<div class="file_box">
														<input type="text" class="upload_text mt5" readonly="readonly">
														<!--button-->
														<div class="upload-btn_wrap mt5">
														<button type="button" title="파일찾기">
														<span>파일찾기</span>  
														</button>
														<input type="file" class="input_file" name="attachFile" title="파일찾기" accept="image/*">
														</div>
													</div>
													<c:if test="${!empty file2}">
														<div class="file_box">
														<c:out value='${file2.orignlFileNm}'/> <input type="checkbox" name="deleteAtchFile" style="display:inline;" value="<c:out value='${file2.fileSeq}'/>" /> 삭제
														</div>
													</c:if>
													<div class="file_box">
														<input type="text" class="upload_text mt5" readonly="readonly">
														<!--button-->
														<div class="upload-btn_wrap mt5">
														<button type="button" title="파일찾기">
														<span>파일찾기</span>  
														</button>
														<input type="file" class="input_file" name="attachFile" title="파일찾기" accept="image/*">
														</div>
													</div>
													<c:if test="${!empty file3}">
														<div class="file_box">
														<c:out value='${file3.orignlFileNm}'/> <input type="checkbox" name="deleteAtchFile" style="display:inline;" value="<c:out value='${file3.fileSeq}'/>" /> 삭제
														</div>
													</c:if>
													<div class="file_box">
														<input type="text" class="upload_text mt5" readonly="readonly">
														<!--button-->
														<div class="upload-btn_wrap mt5">
														<button type="button" title="파일찾기">
														<span>파일찾기</span>  
														</button>
														<input type="file" class="input_file" name="attachFile" title="파일찾기" accept="image/*">
														</div>
													</div>
													<c:if test="${!empty file4}">
														<div class="file_box">
														<c:out value='${file4.orignlFileNm}'/> <input type="checkbox" name="deleteAtchFile" style="display:inline;" value="<c:out value='${file4.fileSeq}'/>" /> 삭제
														</div>
													</c:if>
													<div class="file_box">
														<input type="text" class="upload_text mt5" readonly="readonly">
														<!--button-->
														<div class="upload-btn_wrap mt5">
														<button type="button" title="파일찾기">
														<span>파일찾기</span>  
														</button>
														<input type="file" class="input_file" name="attachFile" title="파일찾기" accept="image/*">
														</div>
													</div>
													<c:if test="${!empty file5}">
														<div class="file_box">
														<c:out value='${file5.orignlFileNm}'/> <input type="checkbox" name="deleteAtchFile" style="display:inline;" value="<c:out value='${file5.fileSeq}'/>" /> 삭제
														</div>
													</c:if>
													<div class="file_box">
														<input type="text" class="upload_text mt5" readonly="readonly">
														<!--button-->
														<div class="upload-btn_wrap mt5">
														<button type="button" title="파일찾기">
														<span>파일찾기</span>  
														</button>
														<input type="file" class="input_file" name="attachFile" title="파일찾기" accept="image/*">
														</div>
													</div>
													<c:if test="${!empty file6}">
														<div class="file_box">
														<c:out value='${file6.orignlFileNm}'/> <input type="checkbox" name="deleteAtchFile" style="display:inline;" value="<c:out value='${file6.fileSeq}'/>" /> 삭제
														</div>
													</c:if>
													<div class="file_box">
														<input type="text" class="upload_text mt5" readonly="readonly">
														<!--button-->
														<div class="upload-btn_wrap mt5">
														<button type="button" title="파일찾기">
														<span>파일찾기</span>  
														</button>
														<input type="file" class="input_file" name="attachFile" title="파일찾기" accept="image/*">
														</div>
													</div>
													<c:if test="${!empty file7}">
														<div class="file_box">
														<c:out value='${file7.orignlFileNm}'/> <input type="checkbox" name="deleteAtchFile" style="display:inline;" value="<c:out value='${file7.fileSeq}'/>" /> 삭제
														</div>
													</c:if>
													<div class="file_box">
														<input type="text" class="upload_text mt5" readonly="readonly">
														<!--button-->
														<div class="upload-btn_wrap mt5">
														<button type="button" title="파일찾기">
														<span>파일찾기</span>  
														</button>
														<input type="file" class="input_file" name="attachFile" title="파일찾기" accept="image/*">
														</div>
													</div>
													<c:if test="${!empty file8}">
														<div class="file_box">
														<c:out value='${file8.orignlFileNm}'/> <input type="checkbox" name="deleteAtchFile" style="display:inline;" value="<c:out value='${file8.fileSeq}'/>" /> 삭제
														</div>
													</c:if>
													<div class="file_box">
														<input type="text" class="upload_text mt5" readonly="readonly">
														<!--button-->
														<div class="upload-btn_wrap mt5">
														<button type="button" title="파일찾기">
														<span>파일찾기</span>  
														</button>
														<input type="file" class="input_file" name="attachFile" title="파일찾기" accept="image/*">
														</div>
													</div>
													<c:if test="${!empty file9}">
														<div class="file_box">
														<c:out value='${file9.orignlFileNm}'/> <input type="checkbox" name="deleteAtchFile" style="display:inline;" value="<c:out value='${file9.fileSeq}'/>" /> 삭제
														</div>
													</c:if>
													<br><p style="font-size: 15px;">- 모바일 사진촬영 -</p> <p style="font-size: 13px;">※ 세로 촬영 시 사진이 잘릴 수 있습니다.</p>  
												</td>
											</tr>
											<tr>
												<th class="tc">1상자 당 분수량</th>
												<td class="tl">
													<div class="ip_type_01 w100">
														<input type="number" class="tr" id="unitSok" name="unitSok" value="${result.unitSok}" maxlength="4" oninput="maxLengthCheck(this)">
														<label for="unitSok"></label>
													</div>
												</td>
											</tr>
											<tr style="display:none;">
												<th class="tc">전장 가격</th>
												<td class="tl"><p class="txt_01" id="prePrice1">품종선택 후 가격이 출력됩니다.</p></td>
											</tr>
											<tr style="display:none;">
												<th class="tc">전전장 가격</th>
												<td class="tl"><p class="txt_01" id="prePrice2">품종선택 후 가격이 출력됩니다.</p></td>
											</tr>
											<tr>
												<th class="tc">판매단가</th>
												<td class="tl">
													<div class="ip_type_01 w100">
														<input type="number" class="tr" id="unitPrice" name="unitPrice" value="${result.unitPrice}" maxlength="10" oninput="maxLengthCheck(this)">
														<label for="unitPrice"></label>
													</div>
												</td>
											</tr>
											<tr>
												<th class="tc">사용여부</th>
												<td class="tl">
													<label class="radio mr10">
														<input type="radio" name="useYn" value="Y" <c:if test="${result.useYn eq 'Y'}">checked="checked"</c:if>>
														<i class="rdo"></i>
														<em class="label-title">사용</em>
													</label>
													<label class="radio mr10">
														<input type="radio" name="useYn" value="N" <c:if test="${result.useYn eq 'N'}">checked="checked"</c:if>>
														<i class="rdo"></i>
														<em class="label-title">미사용</em>
													</label>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							
							<!-- 테이블03(E) -->
						</div>
						<div class="m_col_right">
							<div class="info_box_02">
								<div class="title_box">
									<div class="fl">
										<h4 class="sub_tit_03">상품설명</h4>
									</div>
								</div>
								<div class="t_area mt10" style="height:calc(100% - 40px);">
									<textarea name="itemText" wrap="physical">${result.itemText}</textarea>
								</div>
							</div>
						</div>
						</form>
					</div>
					
					

					<div class="btn_box mt30">
						<a href="javascript:fn_save();" class="btn_type_01 save">저장</a>
						<a href="${pageContext.request.contextPath}/front/fixCAuc/fixSellSubList.do" class="btn_type_01 list">목록</a>
					</div>
				</div>
			</div>
			<!-- sub내용(E) -->
<script type="text/javascript">

function fn_save(){
	
	var chulCode = $("#chulCode").val();
	var pumCode = $("#pumCode").prop("value");
	var mokCode = $("#mokCode").prop("value");
	//var chulLevel = $("#chulLevel").prop("value");
	var unitPrice = $("#unitPrice").val();
	
	
	
	if(chulCode == ""){
		alert("출하자 코드를 입력하세요");
		$("#chulCode").focus();
		return false;
	}
	
	if(mokCode == ""){
		alert("품목을 선택하세요");
		$("#mokCode").focus();
		return false;
	}
	
	if(pumCode == ""){
		alert("품종을 선택하세요");
		$("#pumCode").focus();
		return false;
	}
	
	/*
	if(chulLevel == ""){
		alert("등급을 선택하세요");
		$("#chulLevel").focus();
		return false;
	}*/
	
	if(unitPrice == ""){
		alert("판매단가를 입력하세요");
		$("#unitPrice").focus();
		return false;
	}
	
	
	
	var mokName = $("#mokCode option:selected").text();
	var pumName = $("#pumCode option:selected").text();
	//var chulLevelName = $("#chulLevel option:selected").text();
	
	$("#mokName").val(mokName);
	$("#pumName").val(pumName);
	//$("#chulLevelName").val(chulLevelName);
	
	$("#frm").submit();
}


function getPumList(recentCheck, recentPumCode){
	var pMokCode = "029";
	
	$("#pumCode").select2({
		ajax: {
			url: contextPath+'/front/fixCAuc/pumlist.json',
			dataType: 'json',
			delay: 250,
			cache: true,
			// 검색 쿼리를 만든다.
			data: function (params) {
				return {
					searchKeyWord : params.term,
					pMokCode : pMokCode
				};
			},
			// 결과를 표현한다.
			processResults: function (data) {
				return {
					results: data.results
				};
			}
		},
        theme: 'bootstrap4',
        placeholder: "품종을 선택하세요.",
        allowClear: true,
        language: {
        	noResults: function(){
                return "검색결과가없습니다.";
            },
            searching: function () {
                return '검색중…';
            },
            inputTooShort: function(){
                return "검색어를 입력하세요";
            }
        }
        //minimumInputLength: 1
    });	
	
}



function getPreList(){
	var pJongName = $("#pumCode option:selected").text();
	
	$.ajax({
		data:{
	        	pJongName: pJongName
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixCAuc/getPrePrice.json",
        success : function(data){
            for(var i=0; i<data.result.length; i++){
            	var price = data.result[i].avgPrice;
            	var unit = "";
            	if($.isNumeric(price)){
            		price = comma(price);
            		unit = "원";
            	}
            	
            	if(data.result[i].gubn == "1"){
            		//전장
            		$("#prePrice1").html(data.result[i].saleDate + " / " + price + unit);
            	}else{
            		//전전장
            		$("#prePrice2").html(data.result[i].saleDate + " / " +price + unit);
            	}
            }
        }
    });
}


$(document).ready(function(){
	getPumList(false, "");
	getPreList();
	
	var heightArray = $(".m_col_left > .table_type_03").map(function(){
		return $(this).height();
	}).get();

	var maxHeight = Math.max.apply( Math, heightArray);

	$(".m_col_right > .info_box_02").innerHeight(maxHeight);

	$(window).resize(function() {

		var mobileHeight = $(".m_col_left > .table_type_03").height();

		if($(window).width() < 1086) {
			$(".m_col_right > .info_box_02").css('height','100%');
		}

		if($(window).width() > 1086) {
			$(".m_col_right > .info_box_02").innerHeight(mobileHeight);
		}
		
	});
});







</script>
