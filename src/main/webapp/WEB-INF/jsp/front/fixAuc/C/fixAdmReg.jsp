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
						<div class="m_col_left">
							<!-- 테이블03(S) -->
							<form id="frm" name="frm" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/front/fixCAuc/fixAdmRegProc.do" >
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
													<input type="hidden" id="chulCode" name="chulCode" value=""/>
													<input type="text" id="chulCodeName" name="chulCodeName" value="" onkeydown="fn_enterChulCode();"/>
													</div>
													<a href="javascript:getEnterChulList();" class="btn_search_02 ml10">검색</a>
												</td>
											</tr>
											<tr>
												<th class="tc">품목</th>
												<td class="tl">
													<div class="sel_type_03 w50p">
													<input type="hidden" id="mokCode" name="mokCode" value=""/>
													<input type="hidden" id="mokName" name="mokName" value=""/>
													<input type="text" id="searchMokName" name="searchMokName" value="" onkeydown="fn_enterMokCode();"/>
													</div>
													<a href="javascript:getEnterMokList();" class="btn_search_02 ml10">검색</a>
												</td>
											</tr>
											<tr>
												<th class="tc">품종</th>
												<td class="tl">
													<div class="sel_type_03 w50p">
													<input type="hidden" id="pumCode" name="pumCode" value=""/>
													<input type="hidden" id="pumName" name="pumName" value=""/>
													<input type="text" id="searchJongName" name="searchJongName" value="" onkeydown="fn_enterJongCode();"/>
													</div>
													<a href="javascript:getEnterJongList();" class="btn_search_02 ml10">검색</a>
												</td>
											</tr>
											<tr>
												<th class="tc">등급</th>
												<td class="tl">
													<div class="sel_type_03">
														<input type="hidden" name="chulLevelName" id="chulLevelName" value="">
														<select id="chulLevel" name="chulLevel" onkeydown="fn_enterFocus('chulLevelCustom')">
															<c:forEach items="${levelList}" var="level">
																<option value="${level.code}" <c:if test="${level.code eq '11'}">selected="selected"</c:if>>${level.name}</option>
															</c:forEach>
														</select>
														<label for="chulLevel"></label>
													</div>
												</td>
											</tr>
											<tr>
												<th class="tc">출하등급</th>
												<td class="tl">
													<div class="sel_type_03 w50p">
														<input type="text" class="tl" id="chulLevelCustom" name="chulLevelCustom" maxlength="50" value="" onkeydown="fn_enterFocus('boxCnt')">
														<label for="chulLevelCustom"></label>
													</div>
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
													<br><p style="font-size: 15px;">- 모바일 사진촬영 -</p> <p style="font-size: 13px;">※ 세로 촬영 시 사진이 잘릴 수 있습니다.</p>  
													
												</td>
											</tr>
											<tr>
												<th class="tc">상자수</th>
												<td class="tl">
													<div class="ip_type_01 w100">
														<input type="number" class="tr" id="boxCnt" name="boxCnt" maxlength="2" oninput="maxLengthCheck(this)" onkeydown="fn_enterFocus('sokCnt')">
														<label for="boxCnt"></label>
													</div>
												</td>
											</tr>
											<tr>
												<th class="tc">총 분수량</th>
												<td class="tl">
													<input type="hidden" name="unitSok" id="unitSok" value="">
													<div class="ip_type_01 w100">
														<input type="number" class="tr" id="sokCnt" name="sokCnt" maxlength="4" oninput="maxLengthCheck(this)" onkeydown="fn_enterFocus('unitPrice')">
														<label for="sokCnt"></label>
													</div>
												</td>
											</tr>
											<tr id="twinTr" style="display: none;">
												<th class="tc">쌍대/송이 수</th>
												<td class="tl">
													<div class="ip_type_01 w100">
														<input type="number" class="tr" id="twinCnt" name="twinCnt" maxlength="4" oninput="maxLengthCheck(this)">
														<label for="twinCnt"></label>
													</div>
												</td>
											</tr>
											<tr>
												<th class="tc">전장 가격</th>
												<td class="tl"><p class="txt_01" id="prePrice1">품종선택 후 가격이 출력됩니다.</p></td>
											</tr>
											<tr>
												<th class="tc">전전장 가격</th>
												<td class="tl"><p class="txt_01" id="prePrice2">품종선택 후 가격이 출력됩니다.</p></td>
											</tr>
											<tr>
												<th class="tc">가격구분</th>
												<td class="tl">
													<label class="radio mr10">
														<input type="radio" name="dealType" value="F" checked="checked">
														<i class="rdo"></i>
														<em class="label-title">정가</em>
													</label>
													<label class="radio mr10">
														<input type="radio" name="dealType" value="L">
														<i class="rdo"></i>
														<em class="label-title">최저가</em>
													</label>
													<label class="radio">
														<input type="radio" name="dealType" value="W">
														<i class="rdo"></i>
														<em class="label-title">희망가</em>
													</label>
												</td>
											</tr>
											<tr>
												<th class="tc">판매단가</th>
												<td class="tl">
													<div class="ip_type_01 w100">
														<input type="number" class="tr" id="unitPrice" name="unitPrice" maxlength="10" oninput="maxLengthCheck(this)" onkeydown="fn_enterFocus('chulDate')">
														<label for="unitPrice"></label>
													</div>
												</td>
											</tr>
											<tr>
												<th class="tc">출하예정일자</th>
												<td class="tl">
													<div class="date_box">
														<input type="text" class="datepicker" id="chulDate" name="chulDate" onkeydown="fn_enterFocus('itemText')">
													</div>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
								
								<div class="title_box mt30">
									<div class="fl">
										<h4 class="sub_tit_02">상품설명</h4>
									</div>
								</div>
			
								<div class="t_area mt10 h200">
									<textarea wrap="physical" id="itemText" name="itemText"></textarea>
									<label for="itemText"></label>
								</div>
								
							</form>
							<!-- 테이블03(E) -->
							<div class="btn_box mt30">
								<a href="javascript:fn_save();" class="btn_type_01 save">저장</a>
								<a href="${pageContext.request.contextPath}/front/fixCAuc/fixAdmList.do" class="btn_type_01 list" id="listBtn">목록</a>
							</div>
						</div>
						<div class="m_col_right">
							<div class="info_box_02">
								<div class="title_box">
									<div class="fl">
										<h4 class="sub_tit_03">최근 판매정보</h4>
									</div>
								</div>
								<div class="table_type_04 mt5 overflow_a mh620">
									<table>
										<caption>판매정보</caption>
										<colgroup>
											<col style="width:30%">
											<col style="width:23.33%">
											<col style="width:23.33%">
											<col style="width:23.33%">
										</colgroup>
										<thead>
											<tr>
												<th>경매일자</th>
												<th>품목명</th>
												<th>품종명</th>
												<th>판매단가</th>
											</tr>
										</thead>
										<tbody id="recentTbody">
											
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
					
					

					
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
							<p class="pop_title">출하자 검색</p>
						</div>
					</div>
					<!-- 팝업 타이틀 박스(E) -->

					<!-- 팝업 컨텐츠(S) -->
					<div class="choice_box">
						<div class="popup_search">
							<div class="ip_type_02 dib">
								<input type="text" class="tr" id="searchChulCode">
								<label for="searchChulCode"></label>
							</div>
							<a href="javascript:getChulList();" class="btn_search_03 ml10 vb">검색</a>
						</div>

						<div class="table_type_04 mt10 overflow_a" style="max-height: 250px;">
							<table>
								<caption>구매정보</caption>
								<colgroup>
									<col style="width:25%">
									<col style="width:25%">
									<col style="width:50%">
								</colgroup>
								<thead>
									<tr>
										<th>출하자명</th>
										<th>출하자코드</th>
										<th>지역</th>
									</tr>
								</thead>
								<tbody id="chulTbody">
									
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
	
	<!-- 팝업(S) -->
			<div class="dim-layer" id="layerSampleMok">
				<div class="dimBg"></div>
				<div class="pop-layer popbox pop_type_02">
					<div class="pop-container">
						<a href="#!" class="btn-layerClose pop_btn_close_02 close_B" alt="닫기 버튼"></a>
						<div class="pop-conts_00">
							<!-- 팝업 타이틀 박스(S) -->
							<div class="pop_title_box">
								<div class="fl">
									<p class="pop_title">품목 검색</p>
								</div>
							</div>
							<!-- 팝업 타이틀 박스(E) -->
		
							<!-- 팝업 컨텐츠(S) -->
							<div class="choice_box">
								<div class="popup_search">
									<div class="ip_type_02 dib">
										<input type="text" class="tl" id="searchMokCode" onkeydown="fn_enterSearchMokCode();">
										<label for="searchMokCode"></label>
									</div>
									<a href="javascript:getMokList();" class="btn_search_03 ml10 vb">검색</a>
								</div>
		
								<div class="table_type_04 mt10 overflow_a" style="max-height: 250px;">
									<table>
										<caption>품목정보</caption>
										<colgroup>
											<col style="width:50%">
											<col style="width:50%">
										</colgroup>
										<thead>
											<tr>
												<th>품목코드</th>
												<th>품목명</th>
											</tr>
										</thead>
										<tbody id="mokTbody">
											
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
			
			<!-- 팝업(S) -->
			<div class="dim-layer" id="layerSampleJong">
				<div class="dimBg"></div>
				<div class="pop-layer popbox pop_type_02">
					<div class="pop-container">
						<a href="#!" class="btn-layerClose pop_btn_close_02 close_B" alt="닫기 버튼"></a>
						<div class="pop-conts_00">
							<!-- 팝업 타이틀 박스(S) -->
							<div class="pop_title_box">
								<div class="fl">
									<p class="pop_title">품종 검색</p>
								</div>
							</div>
							<!-- 팝업 타이틀 박스(E) -->
		
							<!-- 팝업 컨텐츠(S) -->
							<div class="choice_box">
								<div class="popup_search">
									<div class="ip_type_02 dib">
										<input type="text" class="tl" id="searchJongCode" onkeydown="fn_enterSearchJongCode();">
										<label for="searchJongCode"></label>
									</div>
									<a href="javascript:getJongList();" class="btn_search_03 ml10 vb">검색</a>
								</div>
		
								<div class="table_type_04 mt10 overflow_a" style="max-height: 250px;">
									<table>
										<caption>품종정보</caption>
										<colgroup>
											<col style="width:33%">
											<col style="width:33%">
											<col style="width:33%">
										</colgroup>
										<thead>
											<tr>
												<th>품종코드</th>
												<th>품목명</th>
												<th>품종명</th>
											</tr>
										</thead>
										<tbody id="jongTbody">
											
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

function fn_save(){
	
	var chulCode = $("#chulCode").val();
	var pumCode = $("#pumCode").val();
	var mokCode = $("#mokCode").val();
	var chulLevel = $("#chulLevel").prop("value");
	var boxCnt = $("#boxCnt").val();
	var sokCnt = $("#sokCnt").val();
	var dealType = $("#dealType").prop("value");
	var unitPrice = $("#unitPrice").val();
	var chulDate = $("#chulDate").val();
	
	
	if(chulCode == "" || chulCode == null){
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
	
	if(chulLevel == ""){
		alert("등급을 선택하세요");
		$("#chulLevel").focus();
		return false;
	}
	
	if(boxCnt == "" || boxCnt ==0  ){
		alert("상자수량을 입력하세요");
		$("#boxCnt").focus();
		return false;
	}
	
	if(sokCnt == "" || sokCnt  ==0  ){
		alert("총 분수량을 입력하세요");
		$("#sokCnt").focus();
		return false;
	}
	
	if(dealType == ""){
		alert("가격구분을 선택하세요");
		$("#dealType").focus();
		return false;
	}
	
	if(unitPrice == ""){
		alert("판매단가를 입력하세요");
		$("#unitPrice").focus();
		return false;
	}
	
	if(chulDate == ""){
		alert("출하예정일을 입력하세요");
		$("#chulDate").focus();
		return false;
	}
	
	var unitSok = sokCnt/boxCnt;
	
	if(!fn_rateCal()){
		alert(" 한상자당 분수량이 나머지가 발생합니다 \n 다시 입력해 주세요. \n 1상자당 "+unitSok.toFixed(2)+"분");
		$("#boxCnt").focus();
		return false;
	}
	
	$("#unitSok").val(unitSok);
	
	var chulLevelName = $("#chulLevel option:selected").text();
	
	$("#chulLevelName").val(chulLevelName);
	
	$("#frm").submit();
}


function getPumList(recentCheck, recentPumCode){
	var pMokCode = $("#mokCode").prop("value");
	
	if(pMokCode == "1005"){
		//쌍대송이 표출
		$("#twinTr").css("display","");
		
	}else{
		$("#twinTr").css("display","none");
	}
	
	$("#pumCode").html("");
	
	if(pMokCode == ""){
		
		$("#pumCode").select2({
			theme: 'bootstrap4',
	        placeholder: "품종을 선택하세요.",
	        allowClear: true,
	        language: {
	        	noResults: function(){
	                return "품목을 먼저 선택하세요.";
	            },
	            searching: function () {
	                return '검색중…';
	            },
	            inputTooShort: function(){
	                return "검색어를 입력하세요";
	            }
	        }
	    });
		
	}else{
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
}


function fn_recentClick(index){
	
	if(confirm("선택한 정보를 가져오시겠습니까?")){
		var chulCode = $("#chulCode"+index).val();
		var pumCode = $("#pumCode"+index).val();
		var mokCode = $("#mokCode"+index).val();
		var pumName = $("#pumName"+index).val();
		var mokName = $("#mokName"+index).val();
		var chulLevel = $("#chulLevel"+index).val();
		//var twinCnt = $("#twinCnt"+index).val();
		var boxCnt = $("#boxCnt"+index).val();
		var sokCnt = $("#sokCnt"+index).val();
		//var dealType = $("#dealType"+index).val();
		var unitPrice = $("#unitPrice"+index).val();
		//var itemText = $("#itemText"+index).val();
		
		$("#chulCode").prop("value", chulCode);
		$("#searchMokName").prop("value", mokName);
		$("#mokName").prop("value", mokName);
		$("#mokCode").prop("value", mokCode);
		//getPumList(true, pumCode);
		$("#searchJongName").prop("value", pumName);
		$("#pumName").prop("value", pumName);
		$("#pumCode").prop("value", pumCode);
		//$("#chulLevel").prop("value", chulLevel);
		//$("#twinCnt").val(twinCnt);
		$("#boxCnt").val(boxCnt);
		$("#sokCnt").val(sokCnt);
		//$("#dealType").prop("value", dealType);
		$("#unitPrice").val(unitPrice);
		//$("#itemText").val(itemText);
		getPreList();
		
		if(window.innerWidth <= 1086){
			$('html, body').animate({
				scrollTop : 0
			}, 400);	
		}
		
	}
}

function getPreList(){
	var pJongName = $("#pumName").val();
	
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


function getRecentList(){
	var chulCode = $("#chulCode").prop("value");
	
	if(chulCode == null || chulCode == "" ){
		var html = "<tr><td class=\"tc\" colspan=\"4\">최근 정보가 없습니다</td></tr>";
		 $("#recentTbody").html(html);
	}else{
		$.ajax({
			data:{
				chulCode: chulCode
		        },
	        type : "POST",
	        url : "${pageContext.request.contextPath}/front/fixCAuc/getRecentList.json",
	        success : function(data){
	           
	           var html = "";
	           var recentList = data.recentList;
	           if(recentList.length > 0){
	        	   for(var i=0; i<recentList.length; i++ ){
	           	    html += "<input type=\"hidden\" id=\"chulCode"+i+"\" value=\""+recentList[i].chulCode+"\"/>    ";
	   	   			html += "<input type=\"hidden\" id=\"pumCode"+i+"\" value=\""+recentList[i].pumCode+"\"/>      ";
	   	   			html += "<input type=\"hidden\" id=\"pumName"+i+"\" value=\""+recentList[i].pumName.replace("\"","&quot;")+"\"/>      ";
	   	   			html += "<input type=\"hidden\" id=\"mokCode"+i+"\" value=\""+recentList[i].mokCode+"\"/>      ";
	   	   			html += "<input type=\"hidden\" id=\"mokName"+i+"\" value=\""+recentList[i].mokName.replace("\"","&quot;")+"\"/>      ";
	   	   			html += "<input type=\"hidden\" id=\"chulLevel"+i+"\" value=\""+recentList[i].chulLevel+"\"/>  ";
	   	   			html += "<input type=\"hidden\" id=\"boxCnt"+i+"\" value=\""+recentList[i].boxCnt+"\"/>        ";
	   	   			html += "<input type=\"hidden\" id=\"sokCnt"+i+"\" value=\""+recentList[i].sokCnt+"\"/>        ";
	   	   			html += "<input type=\"hidden\" id=\"unitPrice"+i+"\" value=\""+recentList[i].unitPrice+"\"/>  ";
	   	   			html += "<tr onclick=\"fn_recentClick('"+i+"')\" style=\"cursor: pointer;\">           ";
	   	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+recentList[i].chulDate+"</a></td>       ";
	   	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+recentList[i].mokName+"</a></td>          ";
	   	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+recentList[i].pumName+"</a></td>          ";
	   	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+comma(recentList[i].unitPrice)+"</a></td>         ";
	   	   			html += "</tr>                                                                               ";
	           	   
	              }
	        	   
	           }else{
	        	   html += "<tr><td class=\"tc\" colspan=\"4\">최근 정보가 없습니다</td></tr>";
	           }
	           
	           $("#recentTbody").html(html);
	        }
	    });
	}
	
}


$(document).ready(function(){
	/*
	$("#mokCode").select2({
		ajax: {
			url: contextPath+'/front/fixCAuc/moklist.json',
			dataType: 'json',
			delay: 250,
			cache: true,
			// 검색 쿼리를 만든다.
			data: function (params) {
				return {
					searchKeyWord : params.term
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
        placeholder: "품목을 선택하세요.",
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
	
	
	$("#pumCode").select2({
		theme: 'bootstrap4',
        placeholder: "품종을 선택하세요.",
        allowClear: true,
        language: {
        	noResults: function(){
                return "품목을 먼저 선택하세요.";
            },
            searching: function () {
                return '검색중…';
            },
            inputTooShort: function(){
                return "검색어를 입력하세요";
            }
        }
    });*/
	
	getNextDate("C");
	getRecentList();
	//getChulList();
	
	//firstDivFocus();
	$("#chulCodeName").focus()
});





function fn_chulClick(index){
	
	if(confirm("선택한 정보를 가져오시겠습니까?")){
		var chulCode = $("#code"+index).val();
		var chulName = $("#name"+index).val();
		
		/*$("#chulCode").html("<option value=\""+chulCode+"\">"+chulCode.substring(0,4)+" - "+chulCode.substring(4,8)+" ( "+chulName+" ) </option>");
		$("#chulCode").prop("value", chulCode);*/
		$("#chulCodeName").val(chulName);
		$("#chulCode").val(chulCode);
		$('.dim-layer').fadeOut();
		$('body').css('overflow','auto');
		$('body').css('position','relative');
		getRecentList();
		$("#searchMokName").focus();
	}
}

function getChulList(){
	var searchChulCode = $("#searchChulCode").prop("value");
	$.ajax({
		data:{
			searchChulCode: searchChulCode
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixCAuc/getChulList.json",
        success : function(data){
           console.log("최근정보:",data);
           var html = "";
           var resultList = data.chulList;
           if(resultList.length > 0){
        	   for(var i=0; i<resultList.length; i++ ){
           	    html += "<input type=\"hidden\" id=\"code"+i+"\" value=\""+resultList[i].code+"\"/>    ";
   	   			html += "<input type=\"hidden\" id=\"name"+i+"\" value=\""+resultList[i].name+"\"/>      ";
   	   			html += "<input type=\"hidden\" id=\"bunChk"+i+"\" value=\""+resultList[i].bunChk+"\"/>      ";
   	   			html += "<input type=\"hidden\" id=\"gwanriZone"+i+"\" value=\""+resultList[i].gwanriZone+"\"/>      ";
   	   			html += "<input type=\"hidden\" id=\"chulArea"+i+"\" value=\""+resultList[i].chulArea+"\"/>      ";
   	   			html += "<input type=\"hidden\" id=\"cityGubn"+i+"\" value=\""+resultList[i].cityGubn+"\"/>  ";
   	   			html += "<tr onclick=\"fn_chulClick('"+i+"')\" style=\"cursor: pointer;\">           ";
   	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+resultList[i].name+"</a></td>       ";
   	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+resultList[i].code.substring(0,4)+"-"+resultList[i].code.substring(4,8)+"</a></td>          ";
   	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+resultList[i].chulArea+"</a></td>          ";
   	   			html += "</tr>                                                                               ";
           	   
              }
        	   
           }else{
        	   html += "<tr><td class=\"tc\" colspan=\"3\">출하자정보가 없습니다.</td></tr>";
           }
           
           $("#chulTbody").html(html);
        }
    });
}

function firstDivFocus(){
	$(".btn_search_02").focus();
	/*
	$(".btn_search_02").trigger("click");
	$("#searchChulCode").focus();*/
}

function secondDivFocus(){
	$("#recentTbody tr td").each(function(){
		alert();
	});
}

function fn_searchFocus(){
	console.log($("#searchChulCode"));
	$("#searchChulCode").eq(0).focus();
}


function fn_enterChulCode(){
	if (window.event.keyCode == 13) {
		getEnterChulList();
	}
}

function fn_enterMokCode(){
	if (window.event.keyCode == 13) {
		getEnterMokList();
	}
}

function fn_enterSearchMokCode(){
	if (window.event.keyCode == 13) {
		getMokList();
	}
}

function fn_enterJongCode(){
	if (window.event.keyCode == 13) {
		getEnterJongList();
	}
}

function fn_enterSearchJongCode(){
	if (window.event.keyCode == 13) {
		getJongList();
	}
}


function getEnterChulList(){
	var searchChulCode = $("#chulCodeName").val();
	if(searchChulCode == "" || searchChulCode == null){
		alert("출하자명을 입력해 주세요.");
		return false;
	}
	$.ajax({
		data:{
			searchChulCode: searchChulCode
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixCAuc/getChulList.json",
        success : function(data){
           console.log("최근정보:",data);
           var html = "";
           var resultList = data.chulList;
           if(resultList.length > 1){
        	   for(var i=0; i<resultList.length; i++ ){
           	    html += "<input type=\"hidden\" id=\"code"+i+"\" value=\""+resultList[i].code+"\"/>    ";
   	   			html += "<input type=\"hidden\" id=\"name"+i+"\" value=\""+resultList[i].name+"\"/>      ";
   	   			html += "<input type=\"hidden\" id=\"bunChk"+i+"\" value=\""+resultList[i].bunChk+"\"/>      ";
   	   			html += "<input type=\"hidden\" id=\"gwanriZone"+i+"\" value=\""+resultList[i].gwanriZone+"\"/>      ";
   	   			html += "<input type=\"hidden\" id=\"chulArea"+i+"\" value=\""+resultList[i].chulArea+"\"/>      ";
   	   			html += "<input type=\"hidden\" id=\"cityGubn"+i+"\" value=\""+resultList[i].cityGubn+"\"/>  ";
   	   			html += "<tr onclick=\"fn_chulClick('"+i+"')\" style=\"cursor: pointer;\">           ";
   	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+resultList[i].name+"</a></td>       ";
   	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+resultList[i].code.substring(0,4)+"-"+resultList[i].code.substring(4,8)+"</a></td>          ";
   	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+resultList[i].chulArea+"</a></td>          ";
   	   			html += "</tr>                                                                               ";
           	   
              }
        	   $("#searchChulCode").val(searchChulCode);
        	   layer_popup_adm("#layerSample");
        	   $("#searchChulCode").focus();
           }else if(resultList.length == 1){
        	   $("#chulCodeName").val(resultList[0].name);
       		   $("#chulCode").val(resultList[0].code);
       		   getRecentList();
       		   $("#searchMokName").focus();
           }else{
        	   html += "<tr><td class=\"tc\" colspan=\"3\">검색된 정보가 없습니다.</td></tr>";
        	   alert("검색된 정보가 없습니다.");
        	   //$("#chulCodeName").val("");
       		   $("#chulCode").val("");
           }
           
           $("#chulTbody").html(html);
        }
    });
}

function getEnterMokList(){
	var searchMokName = $("#searchMokName").val();
	
	$.ajax({
		data:{
			searchKeyWord: searchMokName
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixCAuc/moklist.json",
        success : function(data){
           console.log("품목정보:",data);
           var html = "";
           var resultList = data.results;
           if(resultList.length > 1){
        	   for(var i=0; i<resultList.length; i++ ){
           	    html += "<tr onclick=\"fn_mokClick('"+resultList[i].id+"','"+resultList[i].text.replace("\"","&quot;")+"')\" style=\"cursor: pointer;\">           ";
   	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+resultList[i].id+"</a></td>       ";
   	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+resultList[i].text.replace("\"","&quot;")+"</a></td>          ";
   	   			html += "</tr>                                                                               ";
              }
        	   $("#searchMokCode").val(searchMokName);
        	   layer_popup_adm("#layerSampleMok");
        	   $("#searchMokCode").focus();
           }else if(resultList.length == 1){
        	   $("#searchMokName").val(resultList[0].text);
        	   $("#mokName").val(resultList[0].text);
       		   $("#mokCode").val(resultList[0].id);
       		   $("#searchJongName").focus();
       		   $("#searchJongName").val("");
	       	   $("#pumName").val("");
	       	   $("#pumCode").val("");
           }else{
        	   html += "<tr><td class=\"tc\" colspan=\"2\">검색된 정보가 없습니다.</td></tr>";
        	   alert("검색된 정보가 없습니다.");
        	   $("#mokName").val("");
       		   $("#mokCode").val("");
           }
           
           $("#mokTbody").html(html);
        }
    });
}

function getMokList(){
	var searchMokName = $("#searchMokCode").val();
	
	$.ajax({
		data:{
			searchKeyWord: searchMokName
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixCAuc/moklist.json",
        success : function(data){
           var html = "";
           var resultList = data.results;
           if(resultList.length > 0){
        	   for(var i=0; i<resultList.length; i++ ){
           	    html += "<tr onclick=\"fn_mokClick('"+resultList[i].id+"','"+resultList[i].text.replace("\"","&quot;")+"')\" style=\"cursor: pointer;\">           ";
   	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+resultList[i].id+"</a></td>       ";
   	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+resultList[i].text.replace("\"","&quot;")+"</a></td>          ";
   	   			html += "</tr>                                                                               ";
              }
           } else{
        	   html += "<tr><td class=\"tc\" colspan=\"2\">검색된 정보가 없습니다.</td></tr>";
           }
           
           $("#mokTbody").html(html);
        }
    });
}

function fn_mokClick(mokCode, mokName){
	 $("#searchMokName").val(mokName);
	 $("#mokName").val(mokName);
	 $("#mokCode").val(mokCode);
	 $('.dim-layer').fadeOut();
	 $('body').css('overflow','auto');
	 $('body').css('position','relative');
	 $("#searchJongName").focus();
	 $("#searchJongName").val("");
	 $("#pumName").val("");
	 $("#pumCode").val("");
}


function getEnterJongList(){
	
	var searchJongName = $("#searchJongName").val();
	
	//var pMokCode = $("#mokCode").val();
	var pMokCode = "";
	
	/*
	if(pMokCode == "" || pMokCode == null){
		alert("품목을 먼저 선택해 주세요.");
		$("#searchMokName").focus();
		return false;
	}*/
	
	$.ajax({
		data:{
			searchKeyWord: searchJongName,
			pMokCode : pMokCode
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixCAuc/pumlist.json",
        success : function(data){
           console.log("품종정보:",data);
           var html = "";
           var resultList = data.results;
           if(resultList.length > 1){
        	   for(var i=0; i<resultList.length; i++ ){
        		   html += "<tr onclick=\"fn_jongClick('"+resultList[i].pMokCode+"','"+resultList[i].pMokName.replace("\"","&quot;")+"','"+resultList[i].id+"','"+resultList[i].text.replace("\"","&quot;")+"')\" style=\"cursor: pointer;\">           ";
      	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+resultList[i].id+"</a></td>       ";
      	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+resultList[i].pMokName.replace("\"","&quot;")+"</a></td>       ";
      	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+resultList[i].text.replace("\"","&quot;")+"</a></td>          ";
      	   			html += "</tr>                                                                               ";
              }
        	   $("#searchJongCode").val(searchJongName);
        	   layer_popup_adm("#layerSampleJong");
        	   $("#searchJongCode").focus();
           }else if(resultList.length == 1){
        	   $("#searchMokName").val(resultList[0].pMokName);
        	   $("#mokName").val(resultList[0].pMokName);
       		   $("#mokCode").val(resultList[0].pMokCode);
        	   $("#searchJongName").val(resultList[0].text);
        	   $("#pumName").val(resultList[0].text);
       		   $("#pumCode").val(resultList[0].id);
       		   $("#chulLevel").focus();
       		   getPreList();
           }else{
        	   html += "<tr><td class=\"tc\" colspan=\"2\">검색된 정보가 없습니다.</td></tr>";
        	   alert("검색된 정보가 없습니다.");
        	   $("#pumName").val("");
       		   $("#pumCode").val("");
           }
           
           $("#jongTbody").html(html);
        }
    });
}

function getJongList(){
	var searchJongName = $("#searchJongCode").val();
	//var pMokCode = $("#mokCode").val();
	var pMokCode = "";
	
	$.ajax({
		data:{
			searchKeyWord: searchJongName,
			pMokCode : pMokCode
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixCAuc/pumlist.json",
        success : function(data){
           var html = "";
           var resultList = data.results;
           if(resultList.length > 0){
        	   for(var i=0; i<resultList.length; i++ ){
        		   html += "<tr onclick=\"fn_jongClick('"+resultList[i].pMokCode+"','"+resultList[i].pMokName.replace("\"","&quot;")+"','"+resultList[i].id+"','"+resultList[i].text.replace("\"","&quot;")+"')\" style=\"cursor: pointer;\">           ";
      	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+resultList[i].id+"</a></td>       ";
      	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+resultList[i].pMokName.replace("\"","&quot;")+"</a></td>       ";
      	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+resultList[i].text.replace("\"","&quot;")+"</a></td>          ";
      	   			html += "</tr>                                                                               ";
              }
           } else{
        	   html += "<tr><td class=\"tc\" colspan=\"2\">검색된 정보가 없습니다.</td></tr>";
           }
           
           $("#jongTbody").html(html);
        }
    });
}

function fn_jongClick(mokCode, mokName, pumCode, pumName){
	 $("#searchMokName").val(mokName);
	 $("#mokName").val(mokName);
	 $("#mokCode").val(mokCode);
	 $("#searchJongName").val(pumName);
	 $("#pumName").val(pumName);
	 $("#pumCode").val(pumCode);
	 $('.dim-layer').fadeOut();
	 $('body').css('overflow','auto');
	 $('body').css('position','relative');
	 $("#chulLevel").focus();
	 getPreList();
}

</script>
