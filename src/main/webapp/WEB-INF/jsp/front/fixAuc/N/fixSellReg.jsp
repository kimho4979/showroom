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
							<form id="frm" name="frm" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/front/fixNAuc/fixSellRegProc.do" >
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
												<th class="tc">출하자</th>
												<td class="tl">
													<div class="sel_type_03 w50p">
													<select id="chulCode" name="chulCode" onchange="getRecentList();">
														<c:forEach items="${floList}" var="floVO" varStatus="status">
															<c:if test="${floVO.bunChk eq 'N'}">
																<option value="${floVO.code}" <c:if test="${nFloLoginVO.chulCd eq  floVO.code}">selected="selected"</c:if>>${fn:substring(floVO.code,0,4)} - ${fn:substring(floVO.code,4,8)} ( ${floVO.name} )</option>
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
											<tr style="display:none;">
												<th class="tc">등급</th>
												<td class="tl">
													<div class="sel_type_03">
														<input type="hidden" name="chulLevelName" id="chulLevelName" value="">
														<select id="chulLevel" name="chulLevel">
															<c:forEach items="${levelList}" var="level">
																<option value="${level.code}" <c:if test="${level.code eq '12'}">selected="selected"</c:if>>${level.name}</option>
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
														<input type="text" class="tl" id="chulLevelCustom" name="chulLevelCustom" maxlength="50" >
														<label for="chulLevelCustom"></label>
													</div>
												</td>
											</tr>
											<tr>
												<th class="tc" rowspan="2">이미지</th>
												<td class="tl" id="fileTd">
													<div class="file_box">
														<input type="text" class="upload_text" readonly="readonly">
														<div class="upload-btn_wrap">
														<button type="button" title="파일찾기">
														<span>파일찾기</span>
														</button>
														<input type="file" class="input_file" name="attachFile" title="파일찾기" accept="image/*">
														</div>
														<a href="javascript:fn_fileAdd();" class="btn_type_01 ml10" style="height:30px;line-height: 30px;padding: 0 10px;">추가</a>
													</div>
												</td>
											</tr>
											<tr>
												<td class="tl">
													<p style="font-size: 15px;">- 모바일 사진촬영 -</p> <p style="font-size: 13px;">※ 세로 촬영 시 사진이 잘릴 수 있습니다.</p>
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
												<th class="tc">총 속/분 수량</th>
												<td class="tl">
													<input type="hidden" name="unitSok" id="unitSok" value="">
													<div class="ip_type_01 w100">
														<input type="number" class="tr" id="sokCnt" name="sokCnt" maxlength="4" oninput="maxLengthCheck(this)">
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
												<th class="tc">가격구분</th>
												<td class="tl">
													<label class="radio mr10">
														<input type="radio" name="dealType" value="F" checked="checked">
														<i class="rdo"></i>
														<em class="label-title">정가</em>
													</label>
													<label class="radio">
														<input type="radio" name="dealType" value="L">
														<i class="rdo"></i>
														<em class="label-title">최저가</em>
													</label>
												</td>
											</tr>
											<tr>
												<th class="tc">판매단가</th>
												<td class="tl">
													<div class="ip_type_01 w100">
														<input type="number" class="tr" id="unitPrice" name="unitPrice" maxlength="10" oninput="maxLengthCheck(this)">
														<label for="unitPrice"></label>
													</div>
												</td>
											</tr>
											<tr>
												<th class="tc">출하예정일자</th>
												<td class="tl">
													<div class="date_box">
														<input type="text" class="datepicker" id="chulDate" name="chulDate">
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
								<a href="javascript:fn_addSave();" class="btn_type_01 save">계속등록</a>
								<a href="javascript:fn_save();" class="btn_type_01 save">저장</a>
								<a href="${pageContext.request.contextPath}/front/fixNAuc/fixSellList.do" class="btn_type_01 list" id="listBtn">목록</a>
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
<script type="text/javascript">

function fn_addSave(){
	
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
	
	if(boxCnt == "" || boxCnt == 0){
		alert("상자수량을 입력하세요");
		$("#boxCnt").focus();
		return false;
	}
	
	if(sokCnt == "" || sokCnt == 0){
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
		alert(" 한상자당 속수량이 나머지가 발생합니다 \n 다시 입력해 주세요. \n 1상자당 "+unitSok.toFixed(2)+"속");
		$("#boxCnt").focus();
		return false;
	}
	
	$("#unitSok").val(unitSok);
	
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
	
	var mokName = $("#mokCode option:selected").text();
	var pumName = $("#pumCode option:selected").text();
	var chulLevelName = $("#chulLevel option:selected").text();
	
	
	$("#mokName").val(mokName);
	$("#pumName").val(pumName);
	$("#chulLevelName").val(chulLevelName);
	
	//$("#frm").submit();
	var form = $('#frm')[0];
	var formData = new FormData(form);
	
	$.ajax({
		type : "POST",
        enctype: 'multipart/form-data',
        data: formData,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        url : "${pageContext.request.contextPath}/front/fixNAuc/fixSellRegProc.json",
        success : function(data){
           alert(data.message);
           $(".input_file").val(null);
           $(".upload_text").val("");
        }
    });
}


function fn_save(){
	
	
	
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
	
	if(boxCnt == "" || boxCnt == 0){
		alert("상자수량을 입력하세요");
		$("#boxCnt").focus();
		return false;
	}
	
	if(sokCnt == "" || sokCnt == 0){
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
		alert(" 한상자당 속수량이 나머지가 발생합니다 \n 다시 입력해 주세요. \n 1상자당 "+unitSok.toFixed(2)+"속");
		$("#boxCnt").focus();
		return false;
	}
	
	$("#unitSok").val(unitSok);
	
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
	
	var mokName = $("#mokCode option:selected").text();
	var pumName = $("#pumCode option:selected").text();
	var chulLevelName = $("#chulLevel option:selected").text();
	
	
	$("#mokName").val(mokName);
	$("#pumName").val(pumName);
	$("#chulLevelName").val(chulLevelName);
	
	$("#frm").submit();
}


function getPumList(recentCheck, recentPumCode){
	var pMokCode = $("#mokCode").prop("value");
	console.log(pMokCode);
	
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
		$("#mokCode").html("<option value=\""+mokCode+"\">"+mokName+"</option>");
		$("#mokCode").prop("value", mokCode);
		getPumList(true, pumCode);
		$("#pumCode").html("<option value=\""+pumCode+"\">"+pumName+"</option>");
		$("#pumCode").prop("value", pumCode);
		//$("#chulLevel").prop("value", chulLevel);
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
	var chulCode = $("#chulCode").prop("value");
	
	$.ajax({
		data:{
			chulCode: chulCode
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixNAuc/getRecentList.json",
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


$(document).ready(function(){
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
    });
	
	getNextDate("N");
	getRecentList();
	
	
});




</script>
