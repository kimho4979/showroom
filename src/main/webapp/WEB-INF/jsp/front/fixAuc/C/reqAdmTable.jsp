<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
.td_add{
	background: #EAEAEA !important;
}


</style>

<script src="${pageContext.request.contextPath}/js/fix.js"></script>

<script type="text/javascript">


var pageIndex = 1;
var pageUnit = 10;
var orderArray = new Array();
var replace = null;

orderArray = [false,false,false,false,false,false,false,false,false,false,false]




function fn_search(orderString){
	
	$("#orderString").val(orderString);
	var chulDate = $("#chulDate").val();
	if(chulDate == null || chulDate == ""){
		alert("입고희망일을 입력해주세요");
		$("#chulDate").focus();
	}else{
		$("#frm").submit();
	}
}




function fn_order(index){
	$(".td_add").removeClass("td_add");
	var myTable = document.getElementById("fixTable"); 
	replace = replacement(myTable); 
	if(!orderArray[index]){
		sortTD(index);	
		orderArray[index] = true;
	}else{
		reverseTD(index);
		orderArray[index] = false;
	}
	
}

function sortTD(index){
	replace.ascending(index);
}

function reverseTD(index){
	replace.descending(index);
}



function fn_comp(){
	var compChulDate = $("#compChulDate").val();
	
	if(confirm(compChulDate + " 요청 완료하시겠습니까?")){
		$.ajax({
			data:{
				chulDate: compChulDate
		        },
	        type : "POST",
	        url : "${pageContext.request.contextPath}/front/fixCAuc/reqAdmTableComp.json",
	        success : function(data){
	             alert("요청 " + data.compresult + "건 체결 / " + data.disresult + "건 미체결 처리되었습니다.");
	             location.reload(true);
	        }
	    });
	}
	
}




function getChulList(reqDealSeq){
	var searchChulCode = $("#searchChulCode1").prop("value");
	$.ajax({
		data:{
			searchChulCode: searchChulCode
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixCAuc/getChulList.json",
        success : function(data){
           
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
   	   			html += "<tr onclick=\"fn_chulClick('"+i+"', '"+reqDealSeq+"')\" style=\"cursor: pointer;\">           ";
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


function fn_chulClick(index, reqDealSeq){
	
	if(confirm("선택한 정보를 가져오시겠습니까?")){
		var chulCode = $("#code"+index).val();
		var chulName = $("#name"+index).val();
		var chulArea = $("#chulArea"+index).val();
		
		
		var chulNameTxt = chulName; 
		var chulCodeTxt = " ( "+chulCode.substring(0,4)+" - "+chulCode.substring(4,8)+" ) ";
		var chulAreaTxt = chulArea;
		var mchulInfo = "출하자(코드)명 : "+chulName+" ( "+chulCode.substring(0,4)+" - "+chulCode.substring(4,8)+" ) ";
		var mchulArea = "지역: "+chulArea;
		
		$("#chulCode"+reqDealSeq).val(chulCode);
		$("#chulNameTxt"+reqDealSeq).html(chulNameTxt);
		$("#chulCodeTxt"+reqDealSeq).html(chulCodeTxt);
		$("#chulAreaTxt"+reqDealSeq).html(chulAreaTxt);
		$("#mchulInfo"+reqDealSeq).html(mchulInfo);
		$("#mchulArea"+reqDealSeq).html(mchulArea);
	
		$('.dim-layer').fadeOut();
		$('body').css('overflow','auto');
		$('body').css('position','relative');
		
	}
}

function fn_wupdate(){
	
}


function fn_wupdate(reqDealSeq){
	var chulCode = $("#chulCode"+reqDealSeq).val();
	var boxCnt = $("#boxCnt"+reqDealSeq).val();
	var sokCnt = $("#sokCnt"+reqDealSeq).val();
	var aucPrice = $("#aucPrice"+reqDealSeq).val();
	var gtext = $("#gtext"+reqDealSeq).val();
	
	fn_update(reqDealSeq, chulCode, boxCnt, sokCnt, aucPrice, gtext);
}

function fn_mupdate(reqDealSeq){
	var chulCode = $("#chulCode"+reqDealSeq).val();
	var boxCnt = $("#mboxCnt"+reqDealSeq).val();
	var sokCnt = $("#msokCnt"+reqDealSeq).val();
	var aucPrice = $("#maucPrice"+reqDealSeq).val();
	var gtext = $("#mgtext"+reqDealSeq).val();
	
	fn_update(reqDealSeq, chulCode, boxCnt, sokCnt, aucPrice, gtext);
}

function fn_update(reqDealSeq, chulCode, boxCnt, sokCnt, aucPrice, gtext){
	
	if(chulCode == "" || chulCode == null){
		alert("출하자 코드를 선택해주세요");
		return false;
	}
	
	if(boxCnt == ""  || boxCnt == null){
		alert("상자수량을 입력해주세요");
		return false;
	}
	
	if(sokCnt == ""  || sokCnt == null){
		alert("분수량을 입력해주세요");
		return false;
	}
	
	if(aucPrice == ""  || aucPrice == null){
		alert("조정단가를 입력해주세요");
		return false;
	}
	
	$.ajax({
		data:{
			reqDealSeq: reqDealSeq,
			chulCode: chulCode,
			boxCnt: boxCnt,
			sokCnt: sokCnt,
			aucPrice: aucPrice,
			gtext: gtext
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixCAuc/fixAdmTableUptProc.json",
        success : function(data){
           if(data.result == 1){
        	   alert("정상적으로 수정되었습니다");
           }else {
        	   alert(data.message);
           }
        }
    });
}

function fn_excellUpload(){
	var form = new FormData(document.getElementById('uploadForm'));
    
	if($("#uploadText").val() == null || $("#uploadText").val() == ""){
		alert("엑셀파일울 첨부해주세요.");
		return false;
	}
	
	$("#uploadingText").html("업로드중..");
	
	$.ajax({
      url: "${pageContext.request.contextPath}/front/fixCAuc/reqExcellUpload.json",
      data: form,
      dataType: 'json',
      processData: false,
      contentType: false,
      type: 'POST',
      success: function (data) {
        
        if(data.result == "1"){
        	//업로드 성공
        	alert("중도매인 요청 "+data.uploadCnt+"건 저장되었습니다.");
        	location.reload(true);
        }else if(data.result == "2"){
        	//파일내용 오류
        	var text = data.rowIndex+"행에 오류가있습니다. \n -오류내역- \n";
        	if(data.chulCodeErr != null){
        		text += data.chulCodeErr + "\n" ;
        	}
        	
        	if(data.jCodeErr != null){
        		text += data.jCodeErr + "\n" ;
        	}
        	
        	if(data.mokCodeErr != null){
        		text += data.mokCodeErr + "\n" ;
        	}
        	
        	if(data.pumCodeErr != null){
        		text += data.pumCodeErr + "\n" ;
        	}
        	
        	if(data.boxCntErr != null){
        		text += data.boxCntErr + "\n" ;
        	}
        	
        	if(data.sokCntErr != null){
        		text += data.sokCntErr + "\n" ;
        	}
        	
        	if(data.unitPriceErr != null){
        		text += data.unitPriceErr + "\n" ;
        	}
        	
        	alert(text);
        	location.reload(true);
        }else if(data.result == "3"){
        	//예외발생
        	alert("오류가 발생했습니다. 관리자에게 문의하세요.");
        	location.reload(true);
        }else if(data.result == "4"){
        	//파일없음
        	alert("파일이 없습니다.");
        	location.reload(true);
        }
        
      },error: function (jqXHR) {
		alert("오류가 발생했습니다. 관리자에게 문의하세요.");
		location.reload(true);
	  }
	});	
}

$(document).ready(function(){
	
});

</script>


<input type="hidden" id="compChulDate" value="<c:out value="${paramMap.chulDate}"/>"/>

<!-- sub내용(S) -->
			<div class="sub_conts_in">

				<!-- sub탭(S) 
				<div class="tab_url">
					<ul>
						<li class="active"><a href="#">판매</a></li>
						<li><a href="#">요청</a></li>
					</ul>
				</div>-->
				<div class="tab_content pdt0">
					<!-- tab 내용01(S) --><!-- 판매(S)-->
					<div class="ti_01">
						<div class="info_box">
							<!-- 서브탭(S) -->
							<div class="sub_tab">
								
							</div>
							<!-- 서브탭(E) -->

							<!-- 검색조건창(S) -->
							<form id="frm" name="searchFrm" method="post" action="${pageContext.request.contextPath}/front/fixCAuc/reqAdmTable.do" >
							
							<div class="condition_box">
								<div class="search_box">
									<ul class="sb_line">
										<li>
											<p class="sb_title">입고희망일자</p>
											
											<div class="date_box vm">
												<input type="text" class="datepicker" name="chulDate" id="chulDate" value="<c:out value="${paramMap.chulDate}"/>">
											</div>
											
										</li>
										
										<li>
											<p class="sb_title">출하자코드</p>
											<div class="sb_data">
												<div class="ip_type_01 w100p">
													<input type="text" id="searchChulCode" name="searchChulCode" placeholder="검색어를 입력하세요" value="<c:out value="${paramMap.searchChulCode}"/>"><label for="searchChulCode"></label>
												</div>
											</div>
										</li>
										
										<li>
											<p class="sb_title">중도매인 코드</p>
											<div class="sb_data">
												<div class="ip_type_01 w100p">
													<input type="text" id="searchJCode" name="searchJCode" placeholder="검색어를 입력하세요" value="<c:out value="${paramMap.searchJCode}"/>"><label for="searchJCode"></label>
												</div>
											</div>
										</li>
									</ul>
									<ul class="sb_line">
										<li>
											<p class="sb_title">품목명</p>
											<div class="sb_data">
												<div class="ip_type_01 w100p">
													<input type="text" id="searchMokCode" name="searchMokCode" value="<c:out value="${paramMap.searchMokCode}"/>" placeholder="검색어를 입력하세요"><label for="searchMokCode"></label>
												</div>
											</div>
										</li>
										<li>
											<p class="sb_title">품종명</p>
											<div class="sb_data">
												<div class="ip_type_01 w100p">
													<input type="text" id="searchPumCode" name="searchPumCode" value="<c:out value="${paramMap.searchPumCode}"/>" placeholder="검색어를 입력하세요"><label for="searchPumCode"></label>
												</div>
											</div>
										</li>
									</ul>
									<a href="javascript:fn_search();" class="btn_search">검색</a>
								</div>
							</div>
							</form>
							<!-- 검색조건창(E) -->

							<!-- 검색조건버튼(S) -->
							<div class="btn_box tc">
								<a href="#!" class="btn_condition close">검색조건 닫기<span class="img_close"><img src="${pageContext.request.contextPath}/img/ico_close.png" alt="닫기아이콘"></span></a>
								<a href="#!" class="btn_condition open">검색조건 열기<span class="img_close"><img src="${pageContext.request.contextPath}/img/ico_close.png" alt="닫기아이콘"></span></a>
							</div>
							<!-- 검색조건버튼(E) -->

						</div>


						<div class="info_box mt30">
							<!-- 타이틀(S) -->
							<div class="title_box">
								<div class="fl">
									<h4 class="sub_tit_04">중도매인 요청리스트 (${resultCnt}건)</h4>
								</div>
								<div class="fr"> 
									<a href="#layerSample1" class="btn_type_01 btn-popup gray ml10">중도매인요청 엑셀업로드</a>
									<a href="${pageContext.request.contextPath}/front/fixCAuc/reqAdmTableExcell.do?chulDate=<c:out value="${paramMap.chulDate}"/>" class="btn_type_01 gray ml10">중도매인요청표 엑셀다운</a>
									<a href="${pageContext.request.contextPath}/front/fixCAuc/getReqAdmTableExcellComp.do?chulDate=<c:out value="${paramMap.chulDate}"/>" class="btn_type_01 gray ml10">정가수의매매 기록부내역 엑셀다운</a>
									<a href="javascript:fn_comp();" class="btn_type_01 gray ml10">완료</a>
								</div>
							</div>
							<!-- 타이틀(E) -->
							
							<!-- WEB테이블(S) -->
							<div class="table_type_01 ver2 web mt10">
								<c:forEach items="${resultList}" var="result" varStatus="status">
								<input type="hidden" id="fixState${result.reqDealSeq}" value="${result.fixState}"/>
								<input type="hidden" id="chulCode${result.reqDealSeq}" value="${result.chulCode}"/>
								</c:forEach>
								<table id="fixTable">
									<caption>info</caption>
									<colgroup>
										<col width="9.6%">
										<col width="7.6%">
										<col width="8.6%">
										<col width="7.6%">
										<col width="7.6%">
										<col width="5.6%">
										<col width="6.6%">
										<col width="7.6%">
										<col width="7.6%">
										<col width="7.6%">
										<col width="5.6%">
										<col width="9.6%">
										<col width="7.6%">
									</colgroup>
									<thead>
										<tr>
											<th style="cursor: pointer;" onclick="fn_order(0);">출하자<br>(코드)</th>
											<th style="cursor: pointer;" onclick="fn_order(1);">지역</th>
											<th style="cursor: pointer;" onclick="fn_order(2);">중도매인<br>(코드)</th>
											<th style="cursor: pointer;" onclick="fn_order(3);">품목명</th>
											<th style="cursor: pointer;" onclick="fn_order(4);">품종명</th>
											<th style="cursor: pointer;" onclick="fn_order(5);">등급</th>
											<th style="cursor: pointer;" onclick="fn_order(6);">상자수</th>
											<th style="cursor: pointer;" onclick="fn_order(7);">총 분수량</th>
											<th style="cursor: pointer;" onclick="fn_order(8);">요청단가</th>
											<th style="cursor: pointer;" onclick="fn_order(9);">조정단가</th>
											<th style="cursor: pointer;" onclick="fn_order(10);">상태</th>
											<th style="cursor: pointer;" onclick="fn_order(11);">미체결<br>사유</th>
											<th style="cursor: pointer;">관리</th>
										</tr>
									</thead>
									<tbody id="resultWebList">
										<c:forEach items="${resultList}" var="result" varStatus="status">
											<tr id="fixTr${result.reqDealSeq}">
												<td class="tc">
													<p class="txt_01 dib" id="chulNameTxt${result.reqDealSeq}">
														${result.chulName}
													</p>
													<a href="#layerSample" class="btn_refresh btn-popup" onclick="getChulList('${result.reqDealSeq}');"></a>
													<p class="txt_01" id="chulCodeTxt${result.reqDealSeq}">( ${fn:substring(result.chulCode,0,4)} - ${fn:substring(result.chulCode,4,8)} )</p>
													
												</td>
												<td class="tc"><p class="txt_01" id="chulAreaTxt${result.reqDealSeq}">${result.chulArea}</p></td>
												<td class="tc">
													<p class="txt_01">${result.jName}</p>
													<p class="txt_01">( ${result.jCode} )</p>
												</td>
												<td class="tc"><p class="txt_01">${result.mokName}</p></td>
												<td class="tc"><p class="txt_01">${result.pumName}</p></td>
												<td class="tc"><p class="txt_01">${result.chulLevelName}</p></td>
												<td class="tc">
													<div class="ip_type_01 w100p">
														<input type="number" class="tr" id="boxCnt${result.reqDealSeq}" value="${result.boxCnt}" maxlength="2" oninput="maxLengthCheck(this)">
													</div>
												</td>
												<td class="tc">
													<div class="ip_type_01 w100p">
														<input type="number" class="tr" id="sokCnt${result.reqDealSeq}" value="${result.sokCnt}" maxlength="4" oninput="maxLengthCheck(this)">
													</div>
												</td>
												<td class="tc">
													<p class="txt_01"><fmt:formatNumber value="${result.unitPrice}" pattern="#,###" /></p>
												</td>
												<td class="tc">
													<div class="ip_type_01 w100p">
														<input type="number" class="tr" id="aucPrice${result.reqDealSeq}" value="${result.aucPrice}" maxlength="10" oninput="maxLengthCheck(this)">
													</div>
												</td>
												<td class="tc">
													<p class="txt_01">
														<c:if test="${result.fixState eq '1'}">
															신청
													    </c:if>
													    <c:if test="${result.fixState eq '2'}">
															미체결
													   </c:if>
													   <c:if test="${result.fixState eq '3'}">
															승인
													   </c:if>
													   <c:if test="${result.fixState eq '4'}">
															체결
													   </c:if>
													   <c:if test="${result.fixState eq '5'}">
															미체결
													   </c:if>
													</p>
												</td>
												<td class="tc">
													<div class="ip_type_01 w100p mt10">
														<input type="text" class="tr" id="gtext${result.reqDealSeq}" value="${result.gtext}" maxlength="100">
													</div>
												</td>
												<td class="tc">
													<a href="javascript:fn_wupdate('${result.reqDealSeq}');" class="btn_type_round">수정</a>
												</td>
												
											</tr>
											
											
										</c:forEach>
										<c:if test="${fn:length(resultList) eq 0 }">
											<tr>
												<td class="tc" colspan="13">
													데이터가 없습니다.
												</td>
											</tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- WEB테이블(E) -->

							<!-- MOBILE테이블(S) -->
							<div class="table_type_01_m mobile mt10" id="resultMobList">
								<c:forEach items="${resultList}" var="result" varStatus="status">
								<table>
									<caption>info</caption>
									<colgroup>
										<col style="width:100%">
									</colgroup>
									<thead>
										<tr>
											<td>
												<div class="title_box_m">
													<div class="fl">
														<h4 class="sub_tit_05">[${result.insertDate}] ${result.mokName}(${result.pumName})</h4>
													</div>
													<div class="fr">
														<c:if test="${result.fixState eq '1'}">
															<p class="txt_apply">신청</p>
													    </c:if>
													    <c:if test="${result.fixState eq '2'}">
															<p class="txt_re">미체결</p>
													   </c:if>
													   <c:if test="${result.fixState eq '3'}">
															<p class="txt_apply">승인</p>
													   </c:if>
													   <c:if test="${result.fixState eq '4'}">
															<p class="txt_apply">체결</p>
													   </c:if>
													   <c:if test="${result.fixState eq '5'}">
															<p class="txt_apply">미체결</p>
													   </c:if>
													</div>
												</div>
											</td>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>
												<div class="table_info">
													<p class="txt_01 dib" id="mchulInfo${result.reqDealSeq}">출하자(코드)명 : ${result.chulName} ( ${fn:substring(result.chulCode,0,4)} - ${fn:substring(result.chulCode,4,8)} )</p>
													<a href="#layerSample" class="btn_refresh btn-popup " onclick="getChulList('${result.reqDealSeq}');"></a>
													<p class="txt_01">중도매인(코드)명 : ${result.jName} ( ${result.jCode} )</p>
													<ul class="profile_box" style="cursor: pointer;">
														<li>
															<p class="txt_01">등급 : ${result.chulLevelName}</p>
															<p class="txt_01">입고희망일 : 
																${result.chulDate}
															</p>
														</li>
														<li>
															<p class="txt_01" id="mchulArea${result.reqDealSeq}">지역 : ${result.chulArea}</p>
															<p class="txt_01">요청단가 : <fmt:formatNumber value="${result.unitPrice}" pattern="#,###" /></p>
														</li>
													</ul>
													<div class="table_type_06 devide_4 mt30">
														<ul>
															<li>
																<div class="t_th">
																	<span>상자수량</span>
																</div>
																<div class="t_td">
																	<input type="number" id="mboxCnt${result.reqDealSeq}" value="${result.boxCnt}" class="tr w100p" maxlength="2" oninput="maxLengthCheck(this)"><label for="mboxCnt${result.reqDealSeq}"></label>
																</div>
															</li>
															<li>
																<div class="t_th">
																	<span>분수량</span> 
																</div>
																<div class="t_td">
																	<input type="number" id="msokCnt${result.reqDealSeq}" value="${result.sokCnt}" class="tr w100p" maxlength="4" oninput="maxLengthCheck(this)"><label for="msokCnt${result.reqDealSeq}"></label>
																</div>
															</li>
															<li>
																<div class="t_th">
																	<span>조정단가</span>
																</div>
																<div class="t_td">
																	<input type="number" id="maucPrice${result.reqDealSeq}" value="${result.aucPrice}" class="tr w100p" maxlength="10" oninput="maxLengthCheck(this)"><label for="maucPrice${result.reqDealSeq}"></label>
																</div>
															</li>
															<li>
																<div class="t_th">
																	<span>미체결 사유</span>
																</div>
																<div class="t_td">
																	<input type="text" class="tl w100p" id="mgtext${result.reqDealSeq}" value="${result.gtext}" maxlength="100">
																</div>
															</li>
														</ul>
													</div>
													<a href="javascript:fn_mupdate('${result.reqDealSeq}');" class="btn_table_mobile">수정</a>
													
												</div>
											</td>
										</tr>
									</tbody>
								</table>
								</c:forEach>
								<c:if test="${fn:length(resultList) eq 0 }">
									데이터가 없습니다.
								</c:if>
							</div>
							<!-- MOBILE테이블(E) -->

							
						</div>

						</div>
					</div>
					<!-- tab 내용01(E) --><!-- 판매(E)-->
				</div>
				<!-- sub탭(E) -->

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
										<input type="text" class="tr" id="searchChulCode1">
										<label for="searchChulCode1"></label>
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
			<div class="dim-layer" id="layerSample1">
				<div class="dimBg"></div>
				<div class="pop-layer popbox pop_type_02">
					<div class="pop-container">
						<a href="#!" class="btn-layerClose pop_btn_close_02 close_B" alt="닫기 버튼"></a>
						<div class="pop-conts_00">
							<!-- 팝업 타이틀 박스(S) -->
							<div class="pop_title_box">
								<div class="fl">
									<p class="pop_title">중도매인 요청표 엑셀 업로드</p>
								</div>
							</div>
							<!-- 팝업 타이틀 박스(E) -->
		
							<!-- 팝업 컨텐츠(S) -->
							<div class="choice_box">
								<div class="popup_search">
									
									<form id="uploadForm" enctype="multipart/form-data" method="POST" action="${pageContext.request.contextPath}/front/fixCAuc/reqExcellUpload.do">
									<input type="hidden" name="excellChulDate" value="<c:out value="${paramMap.chulDate}"/>"/>
									<div class="file_box m0a">
										<input type="text" id="uploadText" class="upload_text" readonly="readonly" tabindex="-1">
										<!--button-->
										<div class="upload-btn_wrap">
										<button type="button" title="파일찾기" tabindex="-1">
										<span>파일찾기</span>
										</button>
										<input type="file" class="input_file" name="excellFile" title="파일찾기" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" tabindex="-1">
										</div>
									</div>
									</form>
									<br/>
									<p id="uploadingText" class="tc"></p>
									
									<div class="btn_box mt30">
										<a href="javascript:fn_excellUpload();;" class="btn_search_03 ml10 vb">엑셀업로드</a>
										<a href="${pageContext.request.contextPath}/front/fixCAuc/excellTempDown.do" class="btn_search_03 ml10 vb">템플릿다운로드</a>
									</div>
								</div>
								<!-- 
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
								</div> -->
							</div>
							<!-- 팝업 컨텐츠(E) -->
						</div>
					</div>
				</div>
			</div>
			<!-- 팝업(E) -->
