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

<style>

.date_box.flex-01{width:100%!important; vertical-align:8%;}
.table_type_03 table tbody tr td .btn_search_02{vertical-align:middle;}

@media screen and (max-width: 600px) {
	.td-type-01 .sel_type_03{display:block; margin-bottom:5px;}
	.td-type-01 .btn-popup{margin-left:0!important;}
}

</style>

<!-- sub내용(S) -->
			<div class="sub_conts_in">

				<div class="info_box">

					<div class="m_col_wrap">
						<div class="m_col_left">
							<!-- 테이블03(S) -->
							<form id="frm" name="frm" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/front/fixNAuc/reqAdmRegProc.do" >
								<input type="hidden" name="bunCheck" value="N">
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
												<td class="tl"><p class="txt_01">절화</p></td>
											</tr>
											<tr>
												<th class="tc">중도매인</th>
												<td class="tl">
													<div class="sel_type_03 w50p">
													<input type="hidden" id="jCode" name="jCode" value=""/>
													<input type="text" id="jCodeName" name="jCodeName" value="" onkeydown="fn_enterJCode();"/>
													</div>
													<a href="javascript:getEnterJList();" class="btn_search_02 ml10">검색</a>
												</td>
											</tr>
											<tr>
												<th class="tc">출하자</th>
												<td class="tl td-type-01">
													<div class="sel_type_03 w50p">
													<input type="hidden" id="chulCode" name="chulCode" value=""/>
													<input type="text" id="chulCodeName" name="chulCodeName" value="" onkeydown="fn_enterChulCode();"/>
													</div>
													<a href="javascript:getEnterChulList();" class="btn_search_02 ml10">검색</a>
													<a href="#layerSampleC" class="btn_search_02 btn-popup" id="btnLayerSampleC" onclick="getRecentCList();" style="display: none;">출하품</a>
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
														<select id="chulLevel" name="chulLevel" onkeydown="fn_enterFocus('boxCnt')">
															<c:forEach items="${levelList}" var="level">
																<option value="${level.code}" <c:if test="${level.code eq '12'}">selected="selected"</c:if>>${level.name}</option>
															</c:forEach>
														</select>
														<label for="chulLevel"></label>
													</div>
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
												<th class="tc">총 속/분 수량</th>
												<td class="tl">
													<div class="ip_type_01 w100">
														<input type="number" class="tr" id="sokCnt" name="sokCnt" maxlength="4" oninput="maxLengthCheck(this)" onkeydown="fn_enterFocus('unitPrice')">
														<label for="sokCnt"></label>
													</div>
												</td>
											</tr>
											<tr id="twinTr" style="display: none;">
												<th class="tc">쌍대/송이 수</th>
												<td class="tl">
													<input type="hidden" class="tr" id="twinCnt" name="twinCnt" maxlength="5" value="0">
													<div class="ip_type_01 w90">
														<input type="number" class="tr" id="stwinCnt" name="stwinCnt" maxlength="2" oninput="maxLengthCheck(this)">
														<label for="stwinCnt"></label>
													</div>
													-
													<div class="ip_type_01 w90">
														<input type="number" class="tr" id="etwinCnt" name="etwinCnt" maxlength="2" oninput="maxLengthCheck(this)">
														<label for="etwinCnt"></label>
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
												<th class="tc">거래잔액</th>
												<td class="tl">
													<div class="fl"> 
														<p class="txt_01" id="fixAmt"> 요청금액 : 0원 / </p>
													</div>	
													<div class="fl"> 
														<p class="txt_01" id="reqAmt"> 입찰금액 : 0원 / </p>
													</div>
													<div class="fl"> 
														<p class="txt_01" id="limitAmt">거래잔액 : 0원 </p>
													</div>
												</td>
											</tr>
											<tr>
												<th class="tc">구매단가</th>
												<td class="tl">
													<div class="ip_type_01 w100">
														<input type="number" class="tr" id="unitPrice" name="unitPrice" maxlength="10" oninput="maxLengthCheck(this)" onkeydown="fn_enterFocus('chulDate')">
														<label for="unitPrice"></label>
													</div>
												</td>
											</tr>
											<tr>
												<th class="tc">입고희망일자</th>
												<td class="tl">
													<div class="date_box">
														<input type="text" class="datepicker" id="chulDate" name="chulDate" onkeydown="fn_enterFocus('reqText')">
													</div>
												</td>
											</tr>
											<tr>
												<th class="tc">상자형</th>
												<td class="tl">
												
													<div class="sel_type_03" style="width: 60%!important;">
														<select id="boxCode" name="boxCode">
															<c:forEach items="${boxList}" var="boxRow">
																<c:if test="${boxRow.boxCode eq '200'}"><option value="${boxRow.boxCode}" selected="selected">[ ${boxRow.boxCode} ] ${boxRow.boxName}</option></c:if>
																<c:if test="${boxRow.boxCode ne '200'}"><option value="${boxRow.boxCode}">[ ${boxRow.boxCode} ] ${boxRow.boxName}</option></c:if>
															</c:forEach>
														</select>
													</div>
													<!-- <a href="#layerSample2" class="btn_search_02 btn-popup ml10">검색</a> -->
												</td>
											</tr>
										</tbody>
									</table>
								</div>
								
								<div class="title_box mt30">
									<div class="fl">
										<h4 class="sub_tit_02">요청 및 개선사항</h4>
									</div>
								</div>
			
								<div class="t_area mt10 h200">
									<textarea wrap="physical" id="reqText" name="reqText"></textarea>
									<label for="reqText"></label>
								</div>
								
							</form>
							<!-- 테이블03(E) -->
							<div class="btn_box mt30">
								<a href="javascript:fn_addSave();" class="btn_type_01 save">계속등록</a>
								<a href="javascript:fn_save();" class="btn_type_01 save">저장</a>
								<a href="${pageContext.request.contextPath}/front/fixNAuc/reqAdmList.do" class="btn_type_01 list" id="listBtn">목록</a>
							</div>
						</div>
						<div class="m_col_right">
							<div class="info_box_02">
								<div class="title_box">
									<div class="fl">
										<h4 class="sub_tit_03">최근 구매정보</h4>
									</div>
									<div class="date_box fr">
										<input type="text" class="datepicker" id="updayRecent" name="updayRecent">
									</div>
								</div>
								<div class="table_type_04 mt5 overflow_a mh620">
									<table>
										<caption>구매정보</caption>
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
												<th>출하자정보</th>
											</tr>
										</thead>
										<tbody id="recentTbody">
											<tr><td class="tc" colspan="4">최근 정보가 없습니다</td></tr>
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
	<div class="dim-layer" id="layerSample1">
		<div class="dimBg"></div>
		<div class="pop-layer popbox pop_type_02">
			<div class="pop-container">
				<a href="#!" class="btn-layerClose pop_btn_close_02 close_B" alt="닫기 버튼"></a>
				<div class="pop-conts_00">
					<!-- 팝업 타이틀 박스(S) -->
					<div class="pop_title_box">
						<div class="fl">
							<p class="pop_title">중도매인 검색</p>
						</div>
					</div>
					<!-- 팝업 타이틀 박스(E) -->

					<!-- 팝업 컨텐츠(S) -->
					<div class="choice_box">
						<div class="popup_search">
							<div class="ip_type_02 dib">
								<input type="text" class="tr" id="searchJCode">
								<label for="searchJCode"></label>
							</div>
							<a href="javascript:getJList();" class="btn_search_03 ml10 vb">검색</a>
						</div>

						<div class="table_type_04 mt10 overflow_a" style="max-height: 250px;">
							<table>
								<caption>중도매인정보</caption>
								<colgroup>
									<col style="width:25%">
									<col style="width:25%">
									<col style="width:50%">
								</colgroup>
								<thead>
									<tr>
										<th>중도매인명</th>
										<th>중도매인코드</th>
										<th>지역</th>
									</tr>
								</thead>
								<tbody id="jTbody">
									
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
			
			
			
			<!-- 팝업(S) -->
	<!-- 상자형 검색 -->
	<div class="dim-layer" id="layerSample2">
		<div class="dimBg"></div>
		<div class="pop-layer popbox pop_type_02">
			<div class="pop-container">
				<a href="#!" class="btn-layerClose pop_btn_close_02 close_B" alt="닫기 버튼"></a>
				<div class="pop-conts_00">
					<!-- 팝업 타이틀 박스(S) -->
					<div class="pop_title_box">
						<div class="fl">
							<p class="pop_title">상자형 검색</p>
						</div>
					</div>
					<!-- 팝업 타이틀 박스(E) -->

					<!-- 팝업 컨텐츠(S) -->
					<div class="choice_box">
						<div class="popup_search">
							<div class="ip_type_02 dib">
								<input type="text" class="tr" id="searchBoxCode">
								<label for="searchBoxCode"></label>
							</div>
							<a href="javascript:getBoxList();" class="btn_search_03 ml10 vb">검색</a>
						</div>

						<div class="table_type_04 mt10 overflow_a" style="max-height: 250px;">
							<table>
								<caption>상자형정보</caption>
								<colgroup>
									<col style="width:50%">
									<col style="width:50%">
								</colgroup>
								<thead>
									<tr>
										<th>상자형코드</th>
										<th>상자형 내역</th>
									</tr>
								</thead>
								<tbody id="boxTbody">
									
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
	<div class="dim-layer" id="layerSampleC">
		<div class="dimBg"></div>
		<div class="pop-layer popbox pop_type_02">
			<div class="pop-container">
				<a href="#!" class="btn-layerClose pop_btn_close_02 close_B" alt="닫기 버튼"></a>
				<div class="pop-conts_00">
					<!-- 팝업 타이틀 박스(S) -->
					<div class="pop_title_box">
						<div class="fl">
							<p class="pop_title">출하품목 검색</p>
						</div>
					</div>
					<!-- 팝업 타이틀 박스(E) -->

					<!-- 팝업 컨텐츠(S) -->
					<div class="choice_box">
						<div class="popup_search">
							<div class="ip_type_02 dib">
								<div class="date_box flex-01" id="updayRecentCBox">
									<input type="text" class="datepicker" id="updayRecentC" name="updayRecentC" onkeydown="fn_enterUpdayRecentC();">
									<label for="updayRecentC"></label>
								</div>
							</div>
							<a href="javascript:getRecentCList();" class="btn_search_03 ml10 vb">검색</a>
						</div>

						<div class="table_type_04 mt10 overflow_a" style="max-height: 250px;">
							<table>
								<caption>출하품목</caption>
								<colgroup>
									<col style="width:50%">
									<col style="width:50%">
								</colgroup>
								<thead>
									<tr>
										<th>품목명</th>
										<th>품종명</th>
									</tr>
								</thead>
								<tbody id="recentCbody">
									
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

function fn_addSave(){
	var jCode = $("#jCode").prop("value");
	var chulCode = $("#chulCode").prop("value");
	var pumCode = $("#pumCode").prop("value");
	var mokCode = $("#mokCode").prop("value");
	var chulLevel = $("#chulLevel").prop("value");
	var boxCnt = $("#boxCnt").val();
	var sokCnt = $("#sokCnt").val();
	var unitPrice = $("#unitPrice").val();
	var chulDate = $("#chulDate").val();
	var boxCode = $("#boxCode").prop("value");
	
	if(jCode == ""){
		alert("중도매인 코드를 입력하세요");
		$("#jCode").focus();
		return false;
	}
	
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
	
	if(chulLevel == ""){
		alert("등급을 선택하세요");
		$("#chulLevel").focus();
		return false;
	}
	
	if(boxCnt == ""){
		alert("상자수량을 입력하세요");
		$("#boxCnt").focus();
		return false;
	}
	
	if(sokCnt == ""){
		alert("총 속/분 수량을 입력하세요");
		$("#sokCnt").focus();
		return false;
	}
	
	if(unitPrice == ""){
		alert("구매단가를 입력하세요");
		$("#unitPrice").focus();
		return false;
	}
	
	if(chulDate == ""){
		alert("입고희망일을 입력하세요");
		$("#chulDate").focus();
		return false;
	}
	
	
	/*쌍대송이*/
	var stwinCnt = $("#stwinCnt").val();
	var etwinCnt = $("#etwinCnt").val();
	var twinCnt = "0";
	
	if((etwinCnt != "" && etwinCnt != null) && (stwinCnt == "0" || stwinCnt == null || stwinCnt == "") ){
		alert("쌍대/송이수는 뒷자리만 입력 할 수 없습니다.");
		$("#etwinCnt").focus();
		return false;
	}
	
	if(stwinCnt != "" && stwinCnt != null){
		if(stwinCnt > 0 && stwinCnt < 10 && stwinCnt.length==1){
			stwinCnt = "0"+stwinCnt;
		}
		
		twinCnt = stwinCnt;
	}
	
	if(etwinCnt != "" && etwinCnt != null){
		if(etwinCnt > 0 && etwinCnt < 10 && etwinCnt.length==1){
			etwinCnt = "0"+etwinCnt;
		}
		
		twinCnt = twinCnt + "-"+etwinCnt;
	}
	
	$("#twinCnt").val(twinCnt);
	
	var mokName = $("#mokName").val();
	var pumName = $("#pumName").val();
	var chulLevelName = $("#chulLevel option:selected").text();
	var twinCnt = $("#twinCnt").val();
	var reqText = $("#reqText").val();
	
	$.ajax({
		data:{
			chulCode: chulCode,
			jCode: jCode,
			pumCode: pumCode,
			mokCode: mokCode,
			chulLevel: chulLevel,
			boxCnt: boxCnt,
			sokCnt: sokCnt,
			unitPrice: unitPrice,
			mokName: mokName,
			pumName: pumName,
			chulLevelName: chulLevelName,
			chulDate:chulDate,
			reqText: reqText,
			twinCnt: twinCnt,
			boxCode: boxCode
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixNAuc/reqAdmRegProc.json",
        success : function(data){
           alert(data.message);
           getTradeAmt();
           /* 계속등록 시 커서 (S) - 채성주 [ 2021.11.18 ] */
           if(data.result == 1) {
        	   $("#chulCodeName").focus();
               $("#chulCodeName").select();
           }
           /* 계속등록 시 커서 (E) */
        }
    });
	
	
	
}


function fn_save(){
	var jCode = $("#jCode").prop("value");
	var chulCode = $("#chulCode").prop("value");
	var pumCode = $("#pumCode").prop("value");
	var mokCode = $("#mokCode").prop("value");
	var chulLevel = $("#chulLevel").prop("value");
	var boxCnt = $("#boxCnt").val();
	var sokCnt = $("#sokCnt").val();
	var dealType = $("#dealType").prop("value");
	var unitPrice = $("#unitPrice").val();
	var chulDate = $("#chulDate").val();
	
	if(jCode == ""){
		alert("중도매인 코드를 입력하세요");
		$("#jCode").focus();
		return false;
	}
	
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
	
	if(chulLevel == ""){
		alert("등급을 선택하세요");
		$("#chulLevel").focus();
		return false;
	}
	
	if(boxCnt == ""){
		alert("상자수량을 입력하세요");
		$("#boxCnt").focus();
		return false;
	}
	
	if(sokCnt == ""){
		alert("총 속/분 수량을 입력하세요");
		$("#sokCnt").focus();
		return false;
	}
	
	if(dealType == ""){
		alert("가격구분을 선택하세요");
		$("#dealType").focus();
		return false;
	}
	
	if(unitPrice == ""){
		alert("구매단가를 입력하세요");
		$("#unitPrice").focus();
		return false;
	}
	
	if(chulDate == ""){
		alert("입고희망일을 입력하세요");
		$("#chulDate").focus();
		return false;
	}
	
	/*쌍대송이*/
	var stwinCnt = $("#stwinCnt").val();
	var etwinCnt = $("#etwinCnt").val();
	var twinCnt = "0";
	
	if((etwinCnt != "" && etwinCnt != null) && (stwinCnt == "0" || stwinCnt == null || stwinCnt == "") ){
		alert("쌍대/송이수는 뒷자리만 입력 할 수 없습니다.");
		$("#etwinCnt").focus();
		return false;
	}
	
	if(stwinCnt != "" && stwinCnt != null){
		if(stwinCnt > 0 && stwinCnt < 10 && stwinCnt.length==1){
			stwinCnt = "0"+stwinCnt;
		}
		
		twinCnt = stwinCnt;
	}
	
	if(etwinCnt != "" && etwinCnt != null){
		if(etwinCnt > 0 && etwinCnt < 10 && etwinCnt.length==1){
			etwinCnt = "0"+etwinCnt;
		}
		
		twinCnt = twinCnt + "-"+etwinCnt;
	}
	
	$("#twinCnt").val(twinCnt);
	
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
		$("#stwinCnt").val("");
		$("#etwinCnt").val("");
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
				url: contextPath+'/front/fixNAuc/pumlist.json',
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
		var chulName = $("#chulName"+index).val();
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
		
		if(chulName == "undefined"){
			chulName = "테스트";
		}
		
		$("#chulCodeName").prop("value", chulName);
		$("#chulName").prop("value", chulName);
		$("#chulCode").prop("value", chulCode);
		$("#searchMokName").prop("value", mokName);
		$("#mokName").prop("value", mokName);
		$("#mokCode").prop("value", mokCode);
		//getPumList(true, pumCode);
		$("#searchJongName").prop("value", pumName);
		$("#pumName").prop("value", pumName);
		$("#pumCode").prop("value", pumCode);
		$("#chulLevel").prop("value", chulLevel);
		//$("#twinCnt").val(twinCnt);
		$("#boxCnt").val(boxCnt);
		$("#sokCnt").val(sokCnt);
		//$("#dealType").prop("value", dealType);
		$("#unitPrice").val(unitPrice);
		//$("#itemText").val(itemText);
		
		var fSongeCnt = $("#fSongeCnt"+index).val();
		$("#twinCnt").val(fSongeCnt);
		if(fSongeCnt.length == 5){
			$("#stwinCnt").val(fSongeCnt.substring(0,2));
			$("#etwinCnt").val(fSongeCnt.substring(3,5));
		}else if(fSongeCnt.length == 2){
			$("#stwinCnt").val(fSongeCnt);
			$("#etwinCnt").val("");
		}else{
			$("#stwinCnt").val(0);
			$("#etwinCnt").val("");
		}
		
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
        url : "${pageContext.request.contextPath}/front/fixNAuc/getPrePrice.json",
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
	var jCode = $("#jCode").prop("value");
	var upDay = $("#updayRecent").val();
	
	if(jCode != null && jCode != ''){
		$.ajax({
			data:{
				jCode: jCode,
				upDay: upDay
		        },
	        type : "POST",
	        url : "${pageContext.request.contextPath}/front/fixNAuc/getJRecentList.json",
	        success : function(data){
	           console.log("최근정보:",data);
	           var html = "";
	           var recentList = data.recentList;
	           if(recentList.length > 0){
	        	   for(var i=0; i<recentList.length; i++ ){
	        		   var fSongeCnt = recentList[i].fSongeCnt;  
		           		var pumName = recentList[i].pumName;
		           		if(fSongeCnt == "0"){
		           			pumName = pumName;
		           		}else{ 
		           			pumName = pumName + " " + fSongeCnt;
		           		}   
		        		   
	           	    html += "<input type=\"hidden\" id=\"chulCode"+i+"\" value=\""+recentList[i].chulCode+"\"/>    ";
	           	    html += "<input type=\"hidden\" id=\"chulName"+i+"\" value=\""+recentList[i].chulName+"\"/>    ";
	   	   			html += "<input type=\"hidden\" id=\"pumCode"+i+"\" value=\""+recentList[i].pumCode+"\"/>      ";
	   	   			html += "<input type=\"hidden\" id=\"pumName"+i+"\" value=\""+recentList[i].pumName.replace("\"","&quot;")+"\"/>      ";
	   	   			html += "<input type=\"hidden\" id=\"mokCode"+i+"\" value=\""+recentList[i].mokCode+"\"/>      ";
	   	   			html += "<input type=\"hidden\" id=\"mokName"+i+"\" value=\""+recentList[i].mokName.replace("\"","&quot;")+"\"/>      ";
	   	   			html += "<input type=\"hidden\" id=\"chulLevel"+i+"\" value=\""+recentList[i].chulLevel+"\"/>  ";
	   	   			html += "<input type=\"hidden\" id=\"boxCnt"+i+"\" value=\""+recentList[i].boxCnt+"\"/>        ";
	   	   			html += "<input type=\"hidden\" id=\"sokCnt"+i+"\" value=\""+recentList[i].sokCnt+"\"/>        ";
	   	   			html += "<input type=\"hidden\" id=\"unitPrice"+i+"\" value=\""+recentList[i].unitPrice+"\"/>  ";
	   	   			html += "<input type=\"hidden\" id=\"fSongeCnt"+i+"\" value=\""+fSongeCnt+"\"/>  ";
	   	   			html += "<tr onclick=\"fn_recentClick('"+i+"')\" style=\"cursor: pointer;\">           ";
	   	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+recentList[i].chulDate+"</a></td>       ";
	   	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+recentList[i].mokName+"</a></td>          ";
	   	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+recentList[i].pumName+"</a></td>          ";
	   	   		html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+recentList[i].chulName+"("+recentList[i].chulCode.substring(0,4)+"-"+recentList[i].chulCode.substring(4,8)+")<br> - 지역 - <br>"+recentList[i].chulArea+" </a></td>          ";
	   	   			html += "</tr>                                                                               ";
	           	   
	              }
	        	   
	           }else{
	        	   html += "<tr><td class=\"tc\" colspan=\"4\">최근 정보가 없습니다</td></tr>";
	           }
	           
	           $("#recentTbody").html(html);
	           
	           getTradeAmt();
	        }
	    });
	}
}


$(document).ready(function(){
	/*
	$("#mokCode").select2({
		ajax: {
			url: contextPath+'/front/fixNAuc/moklist.json',
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
	
	getNextDate("N");
	getRecentList();
	//getChulList();
	//getJList();
	
	getTradeAmt();
	$("#jCodeName").focus();
	
	
	$("#searchBoxCode").keypress(function(e) { 
	    if (e.keyCode == 13){
	    	getBoxList();
	    }    
	});
});





function fn_chulClick(index){
	
	if(confirm("선택한 정보를 가져오시겠습니까?")){
		var chulCode = $("#code"+index).val();
		var chulName = $("#name"+index).val();
		/*
		$("#chulCode").html("<option value=\""+chulCode+"\">"+chulCode.substring(0,4)+" - "+chulCode.substring(4,8)+" ( "+chulName+" ) </option>");
		$("#chulCode").prop("value", chulCode);*/
		$("#chulCodeName").val(chulName);
		$("#chulCode").val(chulCode);
		$('.dim-layer').fadeOut();
		$('body').css('overflow','auto');
		$('body').css('position','relative');
		$("#searchMokName").focus();
		
		$("#btnLayerSampleC").css("display", "");
		$("#updayRecentC").val("");
	}
}

function fn_jClick(index){
	
	if(confirm("선택한 정보를 가져오시겠습니까?")){
		var jCode = $("#jcode"+index).val();
		var jName = $("#jname"+index).val();
		/*
		$("#jCode").html("<option value=\""+jCode+"\">"+jCode+" ( "+jName+" ) </option>");
		$("#jCode").prop("value", jCode);*/
		$("#jCodeName").val(jName);
		$("#jCode").val(jCode);
		$('.dim-layer').fadeOut();
		$('body').css('overflow','auto');
		$('body').css('position','relative');
		$("#chulCodeName").focus();
		getRecentList();
	}
}

function getChulList(){
	var searchChulCode = $("#searchChulCode").prop("value");
	$.ajax({
		data:{
			searchChulCode: searchChulCode
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixNAuc/getChulList.json",
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
        	   html += "<tr><td class=\"tc\" colspan=\"4\">최근 정보가 없습니다</td></tr>";
           }
           
           $("#chulTbody").html(html);
        }
    });
}


function getJList(){
	var searchJCode = $("#searchJCode").prop("value");
	$.ajax({
		data:{
			searchJCode: searchJCode
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixNAuc/getJList.json",
        success : function(data){
           
           var html = "";
           var resultList = data.jList;
           if(resultList.length > 0){
        	   for(var i=0; i<resultList.length; i++ ){
           	    html += "<input type=\"hidden\" id=\"jcode"+i+"\" value=\""+resultList[i].code+"\"/>    ";
   	   			html += "<input type=\"hidden\" id=\"jname"+i+"\" value=\""+resultList[i].name+"\"/>      ";
   	   			html += "<input type=\"hidden\" id=\"jbunChk"+i+"\" value=\""+resultList[i].bunChk+"\"/>      ";
   	   			html += "<input type=\"hidden\" id=\"jgwanriZone"+i+"\" value=\""+resultList[i].gwanriZone+"\"/>      ";
   	   			html += "<input type=\"hidden\" id=\"jchulArea"+i+"\" value=\""+resultList[i].chulArea+"\"/>      ";
   	   			html += "<input type=\"hidden\" id=\"jcityGubn"+i+"\" value=\""+resultList[i].cityGubn+"\"/>  ";
   	   			html += "<tr onclick=\"fn_jClick('"+i+"')\" style=\"cursor: pointer;\">           ";
   	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+resultList[i].name+"</a></td>       ";
   	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+resultList[i].code+"</a></td>          ";
   	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+resultList[i].chulArea+"</a></td>          ";
   	   			html += "</tr>                                                                               ";
           	   
              }
        	   
           }else{
        	   html += "<tr><td class=\"tc\" colspan=\"4\">최근 정보가 없습니다</td></tr>";
           }
           
           $("#jTbody").html(html);
        }
    });
}


function getBoxList() {
	
	var searchBoxCode = $("#searchBoxCode").prop("value");
	$.ajax({
		data:{
			searchBoxCode: searchBoxCode
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixNAuc/getBoxList.json",
        success : function(data){
           console.log("최근정보:",data);
           var html = "";
           var resultList = data.boxList;
           if(resultList.length > 0){
        	   for(var i=0; i<resultList.length; i++ ){
        		   
        		   var indexBoxCode = resultList[i].boxCode;
        		   var indexBoxName = resultList[i].boxName;
        		   
        		   html += "<tr onclick=\"fn_boxClick('"+indexBoxCode+"', '"+indexBoxName+"')\" style=\"cursor: pointer;\">           ";
        		   html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+indexBoxCode+"</a></td>       ";
        		   html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+indexBoxName+"</a></td>          ";
        		   html += "</tr>                                                                               ";
              }
           }else{
        	   html += "<tr><td class=\"tc\" colspan=\"2\">정보가 없습니다</td></tr>";
           }
           
           $("#boxTbody").html(html);
        }
    });
}

function fn_boxClick(boxCode, boxName){
	
	if(confirm("선택한 정보를 가져오시겠습니까?")){

		$("#boxCode").html("<option value=\""+boxCode+"\">[ "+boxCode+" ] "+boxName+"</option>");
		$("#boxCode").prop("value", boxCode);
		$('.dim-layer').fadeOut();
		$('body').css('overflow','auto');
		$('body').css('position','relative');
	}
}

function getTradeAmt(){
	var code = $("#jCode").prop("value");
	if(code != null && code != ''){
		$.ajax({
			data:{
				code: code
		        },
	        type : "POST",
	        url : "${pageContext.request.contextPath}/front/fixNAuc/getLimitAmt.json",
	        success : function(data){
	           
	           var limitAmt = data.result.limitAmt;
	           var reqAmt = data.result.reqAmt;
	           var fixAmt = data.result.fixAmt;
	           
	           $("#limitAmt").html("거래잔액 : "+comma(limitAmt)+"원");
	           $("#reqAmt").html("요청금액 : "+comma(reqAmt)+"원/");
	           $("#fixAmt").html("입찰금액 : "+comma(fixAmt)+"원/");
	           
	        }
	    });
	}
}


function fn_enterJCode(){
	if (window.event.keyCode == 13) {
		getEnterJList();
	}
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

function fn_enterUpdayRecentC(){
	if (window.event.keyCode == 13) {
		getRecentCList();
	}
}


function getEnterJList(){
	var searchJCode = $("#jCodeName").val();
	if(searchJCode == "" || searchJCode == null){
		alert("중도매인명을 입력해 주세요.");
		return false;
	}
	$.ajax({
		data:{
			searchJCode: searchJCode
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixNAuc/getJList.json",
        success : function(data){
           
           var html = "";
           var resultList = data.jList;
           if(resultList.length > 1){
        	   for(var i=0; i<resultList.length; i++ ){
        		   html += "<input type=\"hidden\" id=\"jcode"+i+"\" value=\""+resultList[i].code+"\"/>    ";
      	   			html += "<input type=\"hidden\" id=\"jname"+i+"\" value=\""+resultList[i].name+"\"/>      ";
      	   			html += "<input type=\"hidden\" id=\"jbunChk"+i+"\" value=\""+resultList[i].bunChk+"\"/>      ";
      	   			html += "<input type=\"hidden\" id=\"jgwanriZone"+i+"\" value=\""+resultList[i].gwanriZone+"\"/>      ";
      	   			html += "<input type=\"hidden\" id=\"jchulArea"+i+"\" value=\""+resultList[i].chulArea+"\"/>      ";
      	   			html += "<input type=\"hidden\" id=\"jcityGubn"+i+"\" value=\""+resultList[i].cityGubn+"\"/>  ";
      	   			html += "<tr onclick=\"fn_jClick('"+i+"')\" style=\"cursor: pointer;\">           ";
      	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+resultList[i].name+"</a></td>       ";
      	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+resultList[i].code+"</a></td>          ";
      	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+resultList[i].chulArea+"</a></td>          ";
      	   			html += "</tr>                                                                               ";
           	   
              }
        	   $("#searchJCode").val(searchJCode);
        	   layer_popup_adm("#layerSample1");
        	   $("#searchJCode").focus();
           }else if(resultList.length == 1){
        	   $("#jCodeName").val(resultList[0].name);
       		   $("#jCode").val(resultList[0].code);
       		   getRecentList();
       		   $("#chulCodeName").focus();
           }else{
        	   html += "<tr><td class=\"tc\" colspan=\"3\">검색된 정보가 없습니다.</td></tr>";
        	   alert("검색된 정보가 없습니다.");
       		   $("#jCode").val("");
           }
           
           $("#jTbody").html(html);
        }
    });
}



function getEnterChulList(){
	var searchChulCode = $("#chulCodeName").val();
	if(searchChulCode == "" || searchChulCode == null){
		alert("출하자명을 입력해 주세요.");
		
		$("#btnLayerSampleC").css("display", "none");
		$("#updayRecentC").val("");
		
		return false;
	}
	$.ajax({
		data:{
			searchChulCode: searchChulCode
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixNAuc/getChulList.json",
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
       		   $("#searchMokName").focus();
       		   
       		   $("#btnLayerSampleC").css("display", "");
			   $("#updayRecentC").val("");
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
        url : "${pageContext.request.contextPath}/front/fixNAuc/moklist.json",
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
    		   if(resultList[0].id == "1005"){
	   				//쌍대송이 표출
	   				$("#twinTr").css("display","");
	   			}else{
	   				$("#twinTr").css("display","none");
	   				$("#stwinCnt").val("");
	   				$("#etwinCnt").val("");
	   			}
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
        url : "${pageContext.request.contextPath}/front/fixNAuc/moklist.json",
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
	 
	 if(mokCode == "1005"){
		//쌍대송이 표출
		$("#twinTr").css("display","");
	}else{
		$("#twinTr").css("display","none");
		$("#stwinCnt").val("");
		$("#etwinCnt").val("");
	}
}


function getEnterJongList(){
	var searchJongName = $("#searchJongName").val();
	var pMokCode = $("#mokCode").val();
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
        url : "${pageContext.request.contextPath}/front/fixNAuc/pumlist.json",
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
	var pMokCode = $("#mokCode").val();
	$.ajax({
		data:{
			searchKeyWord: searchJongName,
			pMokCode : pMokCode
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixNAuc/pumlist.json",
        success : function(data){
           var html = "";
           var resultList = data.results;
           if(resultList.length > 0){
        	   for(var i=0; i<resultList.length; i++ ){
        		   html += "<tr onclick=\"fn_jongClick('"+resultList[i].pMokCode+"','"+resultList[i].pMokName.replace("\"","&quot;")+"','"+resultList[i].id+"','"+resultList[i].text.replace("\"","&quot;")+"')\" style=\"cursor: pointer;\">           ";
     	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+resultList[i].id+"</a></td>       ";
     	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+resultList[i].pMokName.replace("\"","&quot;")+"</a></td>       ";
     	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+resultList[i].text.replace("\"","&quot;")+"</a></td>          ";
     	   			html += "</tr>   ";
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

function getRecentCList(){
	var chulCode = $("#chulCode").prop("value");
	var upDay = $("#updayRecentC").val();
	
	$.ajax({
		data:{
			chulCode: chulCode,
			upDay: upDay
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixNAuc/getCRecentList.json",
        success : function(data){
           console.log("최근정보:",data);
           var html = "";
           var recentList = data.recentList;
           
           if(recentList == "" || recentList == null) {
        	   html += "<tr><td class=\"tc\" colspan=\"2\">최근 정보가 없습니다</td></tr>";
           }else{
        	   for(var i=0; i<recentList.length; i++ ){
	        		   
           	    html += "<input type=\"hidden\" id=\"chulCodeC"+i+"\" value=\""+recentList[i].chulCode+"\"/>    ";
           	    html += "<input type=\"hidden\" id=\"chulNameC"+i+"\" value=\""+recentList[i].chulName+"\"/>    ";
   	   			html += "<input type=\"hidden\" id=\"pumCodeC"+i+"\" value=\""+recentList[i].pumCode+"\"/>      ";
   	   			html += "<input type=\"hidden\" id=\"pumNameC"+i+"\" value=\""+recentList[i].pumName.replace("\"","&quot;")+"\"/>      ";
   	   			html += "<input type=\"hidden\" id=\"mokCodeC"+i+"\" value=\""+recentList[i].mokCode+"\"/>      ";
   	   			html += "<input type=\"hidden\" id=\"mokNameC"+i+"\" value=\""+recentList[i].mokName.replace("\"","&quot;")+"\"/>      ";
   	   			html += "<tr onclick=\"fn_recentClickC('"+i+"')\" style=\"cursor: pointer;\">           ";
   	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+recentList[i].mokName+"</a></td>          ";
   	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+recentList[i].pumName+"</a></td>          ";
   	   			html += "</tr>                                                                               ";
           	   
              }
           }
           
           $("#recentCbody").html(html);
        }
    });
}

function fn_recentClickC(index){
	
	if(confirm("선택한 정보를 가져오시겠습니까?")){
		var pumCode = $("#pumCodeC"+index).val();
		var mokCode = $("#mokCodeC"+index).val();
		var pumName = $("#pumNameC"+index).val();
		var mokName = $("#mokNameC"+index).val();
		
		$("#searchMokName").prop("value", mokName);
		$("#mokName").prop("value", mokName);
		$("#mokCode").prop("value", mokCode);
		//getPumList(true, pumCode);
		$("#searchJongName").prop("value", pumName);
		$("#pumName").prop("value", pumName);
		$("#pumCode").prop("value", pumCode);
		
		getPreList();
		
		$('#layerSampleC').fadeOut();
		$('body').css('overflow','auto');
		$('body').css('position','relative');
		
		if(window.innerWidth <= 1086){
			$('html, body').animate({
				scrollTop : 0
			}, 400);	
		}
	}
}


</script>
