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
							<form id="frm" name="frm" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/front/fixYAuc/reqBuyRegProc.do" >
								<input type="hidden" name="bunCheck" value="Y">
								<input type="hidden" id="returnType" name="returnType" value="1">
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
												<td class="tl"><p class="txt_01">난</p></td>
											</tr>
											<tr>
												<th class="tc">중도매인</th>
												<td class="tl">
													<div class="sel_type_03 w50p">
													<select id="jCode" name="jCode" onchange="getRecentList();">
														<c:forEach items="${floList}" var="floVO" varStatus="status">
															<c:if test="${floVO.bunChk eq 'Y'}">
																<option value="${floVO.code}" <c:if test="${yFloLoginVO.chulCd eq  floVO.code}">selected="selected"</c:if>>${floVO.code} ( ${floVO.name} )</option>
															</c:if>
														</c:forEach>	
													</select>
													</div>
												</td>
											</tr>
											<tr>
												<th class="tc">출하자</th>
												<td class="tl td-type-01">
													<div class="sel_type_03 w50p">
													<select id="chulCode" name="chulCode">
														출하자를 선택하세요
													</select>
													</div>
													<a href="#layerSample" class="btn_search_02 btn-popup ml10">검색</a>
													<a href="#layerSampleC" class="btn_search_02 btn-popup" id="btnLayerSampleC" onclick="getRecentCList();" style="display: none;">출하품</a>
												</td>
											</tr>
											<tr>
												<th class="tc">품목</th>
												<td class="tl">
													<div class="sel_type_03 w50p">
														<input type="hidden" name="mokName" id="mokName" value="">
														<select id="mokCode" name="mokCode" onchange="getPumList();">
															<option value=""></option>
														</select>
														<label for="mokCode"></label>
													</div>
												</td>
											</tr>
											<tr>
												<th class="tc">품종</th>
												<td class="tl">
													<div class="sel_type_03 w50p">
														<input type="hidden" name="pumName" id="pumName" value="">
														<select id="pumCode" name="pumCode" onchange="getPreList();">
															<option value=""></option>
														</select>
														<label for="pumCode"></label>
													</div>
												</td>
											</tr>
											<tr id="twinTr">
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
											<tr style="display: none;">
												<th class="tc">등급</th>
												<td class="tl">
													<div class="sel_type_03">
														<input type="hidden" name="chulLevelName" id="chulLevelName" value="">
														<select id="chulLevel" name="chulLevel">
															<c:forEach items="${levelList}" var="level">
																<option value="${level.code}" <c:if test="${level.code eq '11'}">selected="selected"</c:if>>${level.name}</option>
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
														<input type="number" class="tr" id="boxCnt" name="boxCnt" maxlength="2" oninput="maxLengthCheck(this)">
														<label for="boxCnt"></label>
													</div>
												</td>
											</tr>
											<tr>
												<th class="tc">총 분수량</th>
												<td class="tl">
													<div class="ip_type_01 w100">
														<input type="number" class="tr" id="sokCnt" name="sokCnt" maxlength="4" oninput="maxLengthCheck(this)">
														<label for="sokCnt"></label>
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
														<input type="number" class="tr" id="unitPrice" name="unitPrice" maxlength="10" oninput="maxLengthCheck(this)">
														<label for="unitPrice"></label>
													</div>
												</td>
											</tr>
											<tr>
												<th class="tc">입고희망일자</th>
												<td class="tl">
													<div class="date_box">
														<input type="text" class="datepicker" id="chulDate" name="chulDate">
														<input type="hidden" id="saveDate">
													</div>
												</td>
											</tr>
											<tr>
												<th class="tc">판매유형</th>
												<td class="tl">
													<div class="sb_data ml10">
														<label class="radio mr10">
															<input type="radio" name="panType" value="03">
															<i class="rdo"></i>
															<em class="label-title">선취</em>
														</label>
														<label class="radio mr10">
															<input type="radio" name="panType" value="04" checked="checked">
															<i class="rdo"></i>
															<em class="label-title">사전</em>
														</label>
													</div>
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
								<a href="javascript:fn_save('2');" class="btn_type_01 save">계속저장</a>
								<a href="javascript:fn_save('1');" class="btn_type_01 save">저장</a>
								<a href="${pageContext.request.contextPath}/front/fixYAuc/reqBuyList.do" class="btn_type_01 list" id="listBtn">목록</a>
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
								<div class="table_type_04 mt5 overflow_a">
									<table>
										<caption>구매정보</caption>
										<colgroup>
											<col style="width:25%">
											<col style="width:25%">
											<col style="width:25%">
											<col style="width:25%">
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

function fn_save(returnType){
	
	
	
	var chulCode = $("#chulCode").val();
	var pumCode = $("#pumCode").prop("value");
	var mokCode = $("#mokCode").prop("value");
	var chulLevel = $("#chulLevel").prop("value");
	var boxCnt = $("#boxCnt").val();
	var sokCnt = $("#sokCnt").val();
	var dealType = $("#dealType").prop("value");
	var unitPrice = $("#unitPrice").val();
	var chulDate = $("#chulDate").val();
	
	
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
		alert("구매단가를 입력하세요");
		$("#unitPrice").focus();
		return false;
	}
	
	if(chulDate == ""){
		alert("입고희망일을 입력하세요");
		$("#chulDate").focus();
		return false;
	}
	
	
	var mokName = $("#mokCode option:selected").text();
	var pumName = $("#pumCode option:selected").text();
	var chulLevelName = $("#chulLevel option:selected").text();
	
	
	if(pumCode.substring(0,4) == '6002'){
		mokName = '팔레높시스';
		$("#mokCode option:selected").val('6002');
		mokCode = '6002';
	}else if(pumCode.substring(0,4) == '6043'){
		mokName = '호접란';
		$("#mokCode option:selected").val('6043');
	}
	
	$("#mokName").val(mokName);
	$("#pumName").val(pumName);
	$("#chulLevelName").val(chulLevelName);
	
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
	$("#returnType").val(returnType);
	$("#frm").submit();
}


function getPumList(recentCheck, recentPumCode){
	var pMokCode = $("#mokCode").prop("value");
	var pMokCodeArr = new Array();
	
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
		
		if(pMokCode == "6002" || pMokCode == "6043"){
			pMokCodeArr.push("6002");
			pMokCodeArr.push("6043");
			pMokCode = "";
		}
		
		$("#pumCode").select2({
			ajax: {
				url: contextPath+'/front/fixYAuc/pumlist.json',
				dataType: 'json',
				delay: 250,
				cache: true,
				// 검색 쿼리를 만든다.
				data: function (params) {
					return {
						searchKeyWord : params.term,
						pMokCode : pMokCode,
						pMokCodeArr : pMokCodeArr
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
		
		$("#chulCode").html("<option value=\""+chulCode+"\">"+chulCode.substring(0,4)+" - "+chulCode.substring(4,8)+" ( "+chulName+" ) </option>");
		$("#chulCode").prop("value", chulCode);
		$("#mokCode").html("<option value=\""+mokCode+"\">"+mokName+"</option>");
		$("#mokCode").prop("value", mokCode);
		getPumList(true, pumCode);
		$("#pumCode").html("<option value=\""+pumCode+"\">"+pumName+"</option>");
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
	var pJongName = $("#pumCode option:selected").text();
	
	$.ajax({
		data:{
	        	pJongName: pJongName
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixYAuc/getPrePrice.json",
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
	
	$.ajax({
		data:{
			jCode: jCode,
			upDay: upDay
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixYAuc/getJRecentList.json",
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
   	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+pumName+"</a></td>          ";
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


$(document).ready(function(){
	$("#mokCode").select2({
		ajax: {
			url: contextPath+'/front/fixYAuc/moklist.json',
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
				
				for(var i=0; i < data.results.length; i++ ){
					if(data.results[i].id == '6002'){
						data.results[i].text = '팔레높시스(호접란)';
					}else if(data.results[i].id == '6043'){
						data.results[i].text = '호접란(팔레높시스)';
					}
				}
				
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
    });
	
	getNextDate("Y");
	getRecentList();
	//getChulList();
	
	
	getTradeAmt();
	
});


function fn_chulClick(index){
	
	if(confirm("선택한 정보를 가져오시겠습니까?")){
		var chulCode = $("#code"+index).val();
		var chulName = $("#name"+index).val();
		
		$("#chulCode").html("<option value=\""+chulCode+"\">"+chulCode.substring(0,4)+" - "+chulCode.substring(4,8)+" ( "+chulName+" ) </option>");
		$("#chulCode").prop("value", chulCode);
		$('.dim-layer').fadeOut();
		$('body').css('overflow','auto');
		$('body').css('position','relative');
		
		$("#btnLayerSampleC").css("display", "");
		$("#updayRecentC").val("");
	}
}

function getChulList(){
	var searchChulCode = $("#searchChulCode").prop("value");
	$.ajax({
		data:{
			searchChulCode: searchChulCode
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixYAuc/getChulList.json",
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

function getTradeAmt(){
	var code = $("#jCode").prop("value");
	
	$.ajax({
		data:{
			code: code
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixYAuc/getLimitAmt.json",
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

$(function() {
	$('#pumCode').change(function() {
		var pumCode = $("#pumCode").prop("value");
		if(pumCode.substring(0,4) == '6002'){
			$("#mokCode").html("<option value=\"6002\">팔레높시스(호접란)</option>");
			$("#mokName").val("팔레높시스");
		}else if (pumCode.substring(0,4) == '6043') {
			$("#mokCode").html("<option value=\"6043\">호접란(팔레높시스)</option>");
			$("#mokName").val("호접란");
		}
	});
});

$("#chulDate").datepicker({	
	beforeShow: function() {
		$("#saveDate").val($("#chulDate").val());
	},
	onSelect: function() {
		var curDate = new Date();
		var curYear = curDate.getFullYear(); 
		var curMonth = new String(curDate.getMonth()+1); 
		var curDay = new String(curDate.getDate()); 
		
		if(curMonth.length == 1){ 
			curMonth = "0" + curMonth; 
		} 
		
		if(curDay.length == 1){ 
			curDay = "0" + curDay; 
		} 
		
		curDate = new Date(curYear + "-" + curMonth + "-" + curDay);
		var selDate = new Date($("#chulDate").val());
		
		if(selDate < curDate) {
			alert("오늘 이전의 날짜는 선택하실 수 없습니다.");
			$("#chulDate").val($("#saveDate").val());
		}
	},
	onClose: function() {
		var curDate = new Date();
		var curYear = curDate.getFullYear(); 
		var curMonth = new String(curDate.getMonth()+1); 
		var curDay = new String(curDate.getDate()); 
		
		if(curMonth.length == 1){ 
			curMonth = "0" + curMonth; 
		} 
		
		if(curDay.length == 1){ 
			curDay = "0" + curDay; 
		} 
		
		curDate = new Date(curYear + "-" + curMonth + "-" + curDay);
		var selDate = new Date($("#chulDate").val());
		
		if(selDate < curDate) {
			alert("오늘 이전의 날짜는 선택하실 수 없습니다.");
			$("#chulDate").val($("#saveDate").val());
		}
	},
	showOn: "both",
	buttonImage: contextPath+"/img/ico_datepicker.png",
	buttonImageOnly: true,
 	buttonText: "Select date",
});

function fn_enterUpdayRecentC(){
	if (window.event.keyCode == 13) {
		getRecentCList();
	}
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
        url : "${pageContext.request.contextPath}/front/fixYAuc/getCRecentList.json",
        success : function(data){
           console.log("최근정보:",data);
           var html = "";
           var recentList = data.recentList;
           
           if(recentList == "" || recentList == null) {
        	   html += "<tr><td class=\"tc\" colspan=\"2\">최근 정보가 없습니다</td></tr>";
           }else{
				for(var i=0; i<recentList.length; i++ ){
	           		
	           		if(recentList[i].mokCode == "6002"){
	           			recentList[i].mokName = "팔레높시스(호접란)";
					}else if (recentList[i].mokCode == "6043") {
						recentList[i].mokName = "호접란(팔레높시스)";	 
					}
	           		
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
		
		$("#mokCode").html("<option value=\""+mokCode+"\">"+mokName+"</option>");
		$("#mokCode").prop("value", mokCode);
		getPumList(true, pumCode);
		$("#pumCode").html("<option value=\""+pumCode+"\">"+pumName+"</option>");
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
