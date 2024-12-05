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


function fn_view(reqDealSeq){
	window.location.href = "${pageContext.request.contextPath}/front/fixNAuc/reqSellView.do?reqDealSeq="+reqDealSeq;
}

function fn_reg(){
	window.location.href = "${pageContext.request.contextPath}/front/fixNAuc/reqSellReg.do";
}


function fn_search(orderString){
	
	$("#orderString").val(orderString);
	
	$("#frm").submit();
	
}

function fn_more(){
	pageIndex++;
	
	var searchStartDt = $("#searchStartDt").val();
	var searchEndDt = $("#searchEndDt").val();
	var searchFixState = $("#searchFixState").prop("value");
	var searchChulCode = $("#searchChulCode").val();
	var searchMokCode = $("#searchMokCode").val();
	var searchPumCode = $("#searchPumCode").val();
	
	$(".td_add").removeClass("td_add");
	
	$.ajax({
		data:{
			pageIndex: pageIndex,
			searchStartDt:searchStartDt,
			searchEndDt:searchEndDt,
			searchChulCode:searchChulCode,
			searchPumCode:searchPumCode,
			searchFixState:searchFixState
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixNAuc/reqSellList.json",
        success : function(data){
           console.log("더보가페이징:",data);
           var html = "";
           var mhtml = "";
           var resultList = data.resultList;
           var rowIndex = ((pageIndex-1)*pageUnit);
           if(resultList.length > 0){
        	   for(var i=0; i<resultList.length; i++ ){
        		    //resultWebList
           	    	html += "<tr id=\"fixTr"+resultList[i].reqDealSeq+"\">                                                        ";
           	    	html += "<input type=\"hidden\" id=\"fixState"+resultList[i].reqDealSeq+"\" value=\""+resultList[i].fixState+"\"/>";
           	    	html += "	<td class=\"tc td_add\" style=\"cursor:pointer;\" onclick=\"fn_view('"+resultList[i].reqDealSeq+"');\"><p class=\"txt_01\">"+resultList[i].insertDate+"</p></td>                                                             ";
           	    	html += "	<td class=\"tc td_add\" style=\"cursor:pointer;\" onclick=\"fn_view('"+resultList[i].reqDealSeq+"');\"><p class=\"txt_01\">"+resultList[i].chulDate+"</p></td>                                                             ";
					html += "	<td class=\"tc td_add\" style=\"cursor:pointer;\" onclick=\"fn_view('"+resultList[i].reqDealSeq+"');\"><p class=\"txt_01\">"+resultList[i].mokName+"</p></td>                                                                ";
					html += "	<td class=\"tc td_add\" style=\"cursor:pointer;\" onclick=\"fn_view('"+resultList[i].reqDealSeq+"');\"><p class=\"txt_01\">"+resultList[i].pumName+"</p></td>                                                                ";
					html += "	<td class=\"tc td_add\" style=\"cursor:pointer;\" onclick=\"fn_view('"+resultList[i].reqDealSeq+"');\">                                                                                                              ";
					html += "		<p class=\"txt_01\">"+resultList[i].chulName+"</p> ";
					html += "		<p class=\"txt_01\">( "+resultList[i].chulCode.substring(0,4)+" - "+resultList[i].chulCode.substring(4,8)+" )</p> ";
					html += "	</td>                                                                                                                          ";
					html += "	<td class=\"tc td_add\" style=\"cursor:pointer;\" onclick=\"fn_view('"+resultList[i].reqDealSeq+"');\"><p class=\"txt_01\">"+resultList[i].chulArea+"</p></td>                                                          ";
					html += "	<td class=\"tc td_add\" style=\"cursor:pointer;\" onclick=\"fn_view('"+resultList[i].reqDealSeq+"');\">                                                                                                              ";
					html += "		<p class=\"txt_01\">"+resultList[i].jName+"</p> ";
					html += "		<p class=\"txt_01\">( "+resultList[i].jCode+" )</p> ";
					html += "	</td>                                                                                                                          ";
					html += "	<td class=\"tc td_add\" style=\"cursor:pointer;\" onclick=\"fn_view('"+resultList[i].reqDealSeq+"');\"><p class=\"txt_01\">"+resultList[i].chulLevelName+"</p></td>                                                          ";
					html += "	<td class=\"tc td_add\" style=\"cursor:pointer;\" onclick=\"fn_view('"+resultList[i].reqDealSeq+"');\"><p class=\"txt_01\">"+comma(resultList[i].boxCnt)+"</p></td>                ";
					html += "	<td class=\"tc td_add\" style=\"cursor:pointer;\" onclick=\"fn_view('"+resultList[i].reqDealSeq+"');\"><p class=\"txt_01\">"+comma(resultList[i].sokCnt)+"</p></td>                ";
					html += "	<td class=\"tc td_add\" style=\"cursor:pointer;\" onclick=\"fn_view('"+resultList[i].reqDealSeq+"');\">                                                                                                              ";
					html += "		<p class=\"txt_01\" style=\"cursor:pointer;\" onclick=\"fn_view('"+resultList[i].reqDealSeq+"');\">";
					html += ""+comma(resultList[i].unitPrice)+"";
					html += "</p>                                                                                                                       ";
					html += "	</td>                                                                                                                          ";
					html += "	<td class=\"tc td_add\" style=\"cursor:pointer;\" onclick=\"fn_view('"+resultList[i].reqDealSeq+"');\">                                                                                                              ";
					html += "		<p class=\"txt_01\">";
					if(resultList[i].fixState == "1"){
						html += "신청 ";	
					}else if (resultList[i].fixState == "2"){
						html += "반려";	
					}else if (resultList[i].fixState == "3"){
						html += "승인";	
					}else if (resultList[i].fixState == "4"){
						html += "완료";	
					}
					html += "</p>                                                                                                                       ";
					html += "	</td>                                                                                                                          ";
					html += "<td class=\"tc td_add\" style=\"display: none;\">"+resultList[i].unitPrice+"</td>";
					html += "</tr>                                                                                                                             ";
           	    		
           	   		//resultMobList
           	   		
					mhtml += "<table>                                                                                                                                                 ";
					mhtml += "	<caption>info</caption>                                                                                                                               ";
					mhtml += "	<colgroup>                                                                                                                                            ";
					mhtml += "		<col style=\"width:100%\">                                                                                                                        ";
					mhtml += "	</colgroup>                                                                                                                                           ";
					mhtml += "	<thead>                                                                                                                                               ";
					mhtml += "		<tr>                                                                                                                                              ";
					mhtml += "			<td>                                                                                                                                          ";
					mhtml += "				<div class=\"title_box_m\">                                                                                                               ";
					mhtml += "					<div class=\"fl\">                                                                                                                    ";
					mhtml += "						<h4 class=\"sub_tit_05\">["+resultList[i].insertDate+"] "+resultList[i].mokName+"("+resultList[i].pumName+")</h4>                                         ";
					mhtml += "					</div>                                                                                                                                ";
					mhtml += "					<div class=\"fr\">                                                                                                                    ";
					if(resultList[i].fixState == "1"){
						mhtml += "							<p class=\"txt_apply\">신청</p>";	
					}else if (resultList[i].fixState == "2"){
						mhtml += "							<p class=\"txt_re\">반려</p>";	
					}else if (resultList[i].fixState == "3"){
						mhtml += "							<p class=\"txt_apply\">승인</p>";	
					}else if (resultList[i].fixState == "4"){
						mhtml += "							<p class=\"txt_apply\">완료</p>";	
					}
					mhtml += "						                                                                                                                                  ";
					mhtml += "					</div>                                                                                                                                ";
					mhtml += "				</div>                                                                                                                                    ";
					mhtml += "			</td>                                                                                                                                         ";
					mhtml += "		</tr>                                                                                                                                             ";
					mhtml += "	</thead>                                                                                                                                              ";
					mhtml += "	<tbody>                                                                       ";
					mhtml += "		<tr>                                                                                                                                              ";
					mhtml += "			<td>                                                                                                                                          ";
					mhtml += "				<div class=\"table_info\" onclick=\"fn_view('"+resultList[i].reqDealSeq+"');\" style=\"cursor: pointer;\">                                                                                                                ";
					mhtml += "					<p class=\"txt_01\">출하자(코드)명 : "+resultList[i].chulName+" ( "+resultList[i].chulCode.substring(0,4)+" - "+resultList[i].chulCode.substring(4,8)+" )</p> ";
					mhtml += "					<p class=\"txt_01\">중도매인(코드)명 : "+resultList[i].jName+" ( "+resultList[i].jCode+" )</p> ";
					mhtml += "					<ul class=\"profile_box\">                                                                                                            ";
					mhtml += "						<li>                                                                                                                              ";
					mhtml += "							<p class=\"txt_01\">등급 : "+resultList[i].chulLevelName+"</p>                                                                          ";
					mhtml += "							<p class=\"txt_01\">상자수량 : "+comma(resultList[i].boxCnt)+"</p>                              ";
					mhtml += "							<p class=\"txt_01\">입고희망일 :                                                                                                    ";
					mhtml += "								"+resultList[i].chulDate+"                                                                                                         ";
					mhtml += "							</p>                                                                                                                          ";
					mhtml += "						</li>                                                                                                                             ";
					mhtml += "						<li>                                                                                                                              ";
					mhtml += "							<p class=\"txt_01\">지역 : "+resultList[i].chulArea+"</p>                                                                               ";
					mhtml += "							<p class=\"txt_01\">속/분수량 : "+comma(resultList[i].sokCnt)+"</p>                             ";
					mhtml += "							<p class=\"txt_01\">구매단가 : "+comma(resultList[i].unitPrice)+"</p>                           ";
					mhtml += "						</li>                                                                                                                             ";
					mhtml += "					</ul>                                                                                                                                 ";
					mhtml += "				</div>                                                                                                                                    ";
					mhtml += "			</td>                                                                                                                                         ";
					mhtml += "		</tr>                                                                                                                                             ";
					mhtml += "	</tbody>                                                                                                                                              ";
					mhtml += "</table>                                                                                                                                                ";
           	   		
           	   		
              }
        	   
        	   if(resultList.length < 10){
        		   $("#moreBtn").css("display","none");
        	   }else{
        		   $("#moreBtn").css("display","");$("#currentPageCnt").text(pageUnit*pageIndex);
        	   }
        	   
        	   
        	   
        	   
           }else{
        	   html += "<tr><td class=\"tc\" colspan=\"10\">데이터가 없습니다.</td></tr>";
        	   mhtml += "데이터가 없습니다.";
           }
           
           $("#resultWebList").append(html);
           $("#resultMobList").append(mhtml);
           
	       	$( "input.datepicker" ).datepicker({
	      	  showOn: "both",
	      	  buttonImage: "${pageContext.request.contextPath}/img/ico_datepicker.png",
	      	  buttonImageOnly: true,
	         	  buttonText: "Select date",
	          
	      	});
           
           
        }
    });
	
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


function getTradeAmt(){
	var code = $("#jCode").prop("value");
	
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

$(document).ready(function(){
	
	getTradeAmt();
	
});

</script>




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
								<ul class="devide_3">
									<c:if test="${nFloLoginVO ne null }">
									<li><a href="${pageContext.request.contextPath}/front/fixNAuc/reqSellList.do" class="active">절화</a></li>
									</c:if>
									<c:if test="${yFloLoginVO ne null }">
									<li><a href="${pageContext.request.contextPath}/front/fixYAuc/reqSellList.do">&nbsp;&nbsp;&nbsp;난&nbsp;&nbsp;&nbsp;</a></li>
									</c:if>
									<c:if test="${cFloLoginVO ne null }">
									<li><a href="${pageContext.request.contextPath}/front/fixCAuc/reqSellList.do">관엽</a></li>
									</c:if>
								</ul>
							</div>
							<!-- 서브탭(E) -->

							<!-- 검색조건창(S) -->
							<form id="frm" name="frm" method="post" action="${pageContext.request.contextPath}/front/fixNAuc/reqSellList.do" >
							
							<div class="condition_box">
								<div class="search_box">
									<ul class="sb_line">
										<li>
											<p class="sb_title">신청일자</p>
											<div class="sb_data">
												<div class="date_box">
													<input type="text" class="datepicker" name="searchStartDt" id="searchStartDt" value="<c:out value="${paramMap.searchStartDt}"/>">
												</div>
												<span class="hyphen">~</span>
												<div class="date_box">
													<input type="text" class="datepicker"  name="searchEndDt" id="searchEndDt" value="<c:out value="${paramMap.searchEndDt}"/>">
												</div>
											</div>
										</li>
										<li>
											<p class="sb_title">상태</p>
											<div class="sb_data">
												<div class="sel_type_01 w150">
													<select id="searchFixState" name="searchFixState"> 
														<option value="" <c:if test="${paramMap.searchFixState eq null}">selected="selected"</c:if>>전체</option>
														<option value="3" <c:if test="${paramMap.searchFixState eq '3'}">selected="selected"</c:if>>승인</option>
														<option value="4" <c:if test="${paramMap.searchFixState eq '4'}">selected="selected"</c:if>>완료</option>
													</select>
													<label for="searchFixState"></label>
												</div>
											</div>
										</li>
										<li>
											<p class="sb_title">중도매인코드</p>
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
							<div class="title_box mt5">
								<div class="fl">
									<h4 class="sub_tit_04">요청리스트 (${resultCnt}건)</h4>
								</div>
								<!-- 
								<div class="fr">
									<div class="sel_type_01 w150">
										<select id="jCode" name="jCode" onchange="getTradeAmt();">
											<c:forEach items="${floList}" var="floVO" varStatus="status">
												<c:if test="${floVO.bunChk eq 'N'}">
													<option value="${floVO.code}" <c:if test="${nFloLoginVO.chulCd eq  floVO.code}">selected="selected"</c:if>>${floVO.name} ( ${floVO.code} )</option>
												</c:if>
											</c:forEach>	
										</select>
									</div>
								</div> 
								-->
								
							</div>
							<!-- 타이틀(E) -->
							
							<!-- WEB테이블(S) -->
							<div class="table_type_01 web mt10">
								<table id="fixTable">
									<caption>info</caption>
									<colgroup>
										<col width="8.2%">
										<col width="8.2%">
										<col width="8.2%">
										<col width="8.2%">
										<col width="10.2%">
										<col width="8.2%">
										<col width="6.2%">
										<col width="6.2%">
										<col width="6.2%">
										<col width="6.2%">
										<col width="6.2%">
										<col width="4.2%">
									</colgroup>
									<thead>
										<tr>
											<th style="cursor: pointer;" onclick="fn_order(0);">신청일자</th>
											<th style="cursor: pointer;" onclick="fn_order(1);">입고희망일</th>
											<th style="cursor: pointer;" onclick="fn_order(2);">품목명</th>
											<th style="cursor: pointer;" onclick="fn_order(3);">품종명</th>
											<th style="cursor: pointer;" onclick="fn_order(4);">출하자<br/>(코드)</th>
											<th style="cursor: pointer;" onclick="fn_order(5);">지역</th>
											<th style="cursor: pointer;" onclick="fn_order(6);">중도매인<br/>(코드)</th>
											<th style="cursor: pointer;" onclick="fn_order(7);">등급</th>
											<th style="cursor: pointer;" onclick="fn_order(8);">상자수</th>
											<th style="cursor: pointer;" onclick="fn_order(9);">속수량</th>
											<th style="cursor: pointer;" onclick="fn_order(10);">구매단가</th>
											<th style="cursor: pointer;" onclick="fn_order(11);">상태</th>
										</tr>
									</thead>
									<tbody id="resultWebList">
										<c:forEach items="${resultList}" var="result" varStatus="status">
											<input type="hidden" id="fixState${result.reqDealSeq}" value="${result.fixState}"/>
											<tr id="fixTr${result.reqDealSeq}">
												<td class="tc" style="cursor:pointer" onclick="fn_view('${result.reqDealSeq}');"><p class="txt_01">${result.insertDate}</p></td>
												<td class="tc" style="cursor:pointer" onclick="fn_view('${result.reqDealSeq}');"><p class="txt_01">${result.chulDate}</p></td>
												<td class="tc" style="cursor:pointer" onclick="fn_view('${result.reqDealSeq}');"><p class="txt_01">${result.mokName}</p></td>
												<td class="tc" style="cursor:pointer" onclick="fn_view('${result.reqDealSeq}');"><p class="txt_01">${result.pumName}</p></td>
												<td class="tc" style="cursor:pointer" onclick="fn_view('${result.reqDealSeq}');">
													<p class="txt_01">${result.chulName}</p>
													<p class="txt_01">( ${fn:substring(result.chulCode,0,4)} - ${fn:substring(result.chulCode,4,8)} )</p>
												</td>
												<td class="tc" style="cursor:pointer" onclick="fn_view('${result.reqDealSeq}');"><p class="txt_01">${result.chulArea}</p></td>
												<td class="tc" style="cursor:pointer" onclick="fn_view('${result.reqDealSeq}');">
													<p class="txt_01">${result.jName}</p>
													<p class="txt_01">( ${result.jCode} )</p>
												</td>
												<td class="tc" style="cursor:pointer" onclick="fn_view('${result.reqDealSeq}');"><p class="txt_01">${result.chulLevelName}</p></td>
												<td class="tc" style="cursor:pointer" onclick="fn_view('${result.reqDealSeq}');"><p class="txt_01"><fmt:formatNumber value="${result.boxCnt}" pattern="#,###" /></p></td>
												<td class="tc" style="cursor:pointer" onclick="fn_view('${result.reqDealSeq}');"><p class="txt_01"><fmt:formatNumber value="${result.sokCnt}" pattern="#,###" /></p></td>
												<td class="tc" style="cursor:pointer" onclick="fn_view('${result.reqDealSeq}');">
													<p class="txt_01"><fmt:formatNumber value="${result.unitPrice}" pattern="#,###" /></p>
												</td>
												<td class="tc" style="cursor:pointer" onclick="fn_view('${result.reqDealSeq}');">
													<p class="txt_01">
														<c:if test="${result.fixState eq '1'}">
															신청
													    </c:if>
													    <c:if test="${result.fixState eq '2'}">
															반려
													   </c:if>
													   <c:if test="${result.fixState eq '3'}">
															승인
													   </c:if>
													   <c:if test="${result.fixState eq '4'}">
															완료
													   </c:if>
													</p>
												</td>
												<td class="tc" style="display: none;">${result.unitPrice}</td>
											</tr>
										</c:forEach>
										<c:if test="${fn:length(resultList) eq 0 }">
											<tr>
												<td class="tc" colspan="11">
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
															<p class="txt_re">반려</p>
													   </c:if>
													   <c:if test="${result.fixState eq '3'}">
															<p class="txt_apply">승인</p>
													   </c:if>
													   <c:if test="${result.fixState eq '4'}">
															<p class="txt_apply">완료</p>
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
													<p class="txt_01">출하자(코드)명 : ${result.chulName} ( ${fn:substring(result.chulCode,0,4)} - ${fn:substring(result.chulCode,4,8)} )</p>
													<p class="txt_01">중도매인(코드)명 : ${result.jName} ( ${result.jCode} )</p>
													<ul class="profile_box" onclick="fn_view('${result.reqDealSeq}');" style="cursor: pointer;">
														<li>
															<p class="txt_01">등급 : ${result.chulLevelName}</p>
															<p class="txt_01">상자수량 : <fmt:formatNumber value="${result.boxCnt}" pattern="#,###" /></p>
															<p class="txt_01">입고희망일 : 
																${result.chulDate}
															</p>
														</li>
														<li>
															<p class="txt_01">지역 : ${result.chulArea}</p>
															<p class="txt_01">속/분수량 : <fmt:formatNumber value="${result.sokCnt}" pattern="#,###" /></p>
															<p class="txt_01">구매단가 : <fmt:formatNumber value="${result.unitPrice}" pattern="#,###" /></p>
														</li>
													</ul>
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


							
							<!-- 페이지박스(S) -->
							<div class="paging_box mt30">
								<c:if test="${paginationInfo.lastPageNo ne paramMap.pageIndex}">
								<a href="javascript:fn_more();" class="btn_search_02 ml10" id="moreBtn">더보기 <b id="currentPageCnt"><c:out value="${paramMap.pageUnit}"/></b> / ${resultCnt}</a>
								</c:if>
								
							</div>
							<!-- 페이지박스(E) -->

							<!-- 버튼박스(S) -->
							<div class="btn_box mt30">
								<c:if test="${regTimeVO.timeCheck eq 'Y'}">
								<a href="javascript:fn_reg();" class="btn_type_01 gray">등록</a>
								</c:if>
							</div>
							<!-- 버튼박스(S) -->
						</div>

						</div>
					</div>
					<!-- tab 내용01(E) --><!-- 판매(E)-->
				</div>
				<!-- sub탭(E) -->

			</div>
			<!-- sub내용(E) -->
