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


function fn_view(fixDealSeq){
	window.location.href = "${pageContext.request.contextPath}/front/fixNAuc/fixSellView.do?fixDealSeq="+fixDealSeq;
}

function fn_reg(){
	window.location.href = "${pageContext.request.contextPath}/front/fixNAuc/fixSellReg.do";
}


function fn_search(orderString){
	
	$("#orderString").val(orderString);
	
	$("#frm").submit();
	
}

function fn_more(){
	pageIndex++;
	
	var searchStartDt = $("#searchStartDt").val();
	// var searchEndDt = $("#searchEndDt").val();
	var searchFixState = $("#searchFixState").prop("value");
	var searchChulCode = $("#searchChulCode").val();
	var searchMokCode = $("#searchMokCode").val();
	var searchPumCode = $("#searchPumCode").val();
	
	$(".td_add").removeClass("td_add");
	
	$.ajax({
		data:{
			pageIndex: pageIndex,
			searchStartDt:searchStartDt,
			// searchEndDt:searchEndDt,
			searchChulCode:searchChulCode,
			searchPumCode:searchPumCode,
			searchFixState:searchFixState
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixNAuc/fixSellList.json",
        success : function(data){
           console.log("더보가페이징:",data);
           var html = "";
           var mhtml = "";
           var resultList = data.resultList;
           if(resultList.length > 0){
        	   for(var i=0; i<resultList.length; i++ ){
        		    //resultWebList
           	    	
        		   var twinCnt = resultList[i].twinCnt;  
	           		var pumName = resultList[i].pumName;
	           		if(twinCnt == "0" || twinCnt == null){
	           			pumName = pumName;
	           		}else{ 
	           			pumName = pumName + "/" + twinCnt;
	           		}  
        		    
           	    	html += "<tr style=\"cursor:pointer;\" onclick=\"fn_view('"+resultList[i].fixDealSeq+"');\">                                                        ";
					html += "	<td class=\"tc td_add\"><p class=\"txt_01\">"+resultList[i].insertDate+"</p></td>                                                             ";
					html += "	<td class=\"tc td_add\"><p class=\"txt_01\">"+resultList[i].mokName+"</p></td>                                                                ";
					html += "	<td class=\"tc td_add\"><p class=\"txt_01\">"+pumName+"</p></td>                                                                ";
					html += "	<td class=\"tc td_add\">                                                                                                              ";
					html += "		<p class=\"txt_01\">"+resultList[i].chulName+" ( "+resultList[i].chulCode.substring(0,4)+" - "+resultList[i].chulCode.substring(4,8)+" )</p> ";
					html += "	</td>                                                                                                                          ";
					html += "	<td class=\"tc td_add\"><p class=\"txt_01\">"+resultList[i].chulLevelName+"</p></td>                                                          ";
					html += "	<td class=\"tc td_add\"><p class=\"txt_01\">"+comma(resultList[i].boxCnt)+"</p></td>                ";
					html += "	<td class=\"tc td_add\"><p class=\"txt_01\">"+comma(resultList[i].sokCnt)+"</p></td>                ";
					html += "	<td class=\"tc td_add\">                                                                                                              ";
					html += "		<p class=\"txt_01\">";
					if(resultList[i].dealType == "L"){
						html += "최저가 ";	
					}else if (resultList[i].dealType == "W"){
						html += "희망가";	
					}else if (resultList[i].dealType == "F"){
						html += "정가";	
					}
					html += "("+comma(resultList[i].unitPrice)+")";
					html += "</p>                                                                                                                       ";
					html += "	</td>                                                                                                                          ";
					html += "	<td class=\"tc td_add\">                                                                                                              ";
					html += "		<p class=\"txt_01\">";
					html += ""+resultList[i].aucDate+"";
					html += "</p>                                                                                                                       ";
					html += "	</td>                                                                                                                          ";
					html += "	<td class=\"tc td_add\">                                                                                                              ";
					html += "		<p class=\"txt_01\">";
					if(resultList[i].fixState == "1"){
						html += "신청 ";	
					}else if (resultList[i].fixState == "2"){
						html += "반려";	
					}else if (resultList[i].fixState == "3"){
						html += "준비";	
					}else if (resultList[i].fixState == "4"){
						html += "완료";	
					}else if (resultList[i].fixState == "5"){
						html += "유찰";	
					}else if (resultList[i].fixState == "6"){
						html += "부분유찰";	
					}else if (resultList[i].fixState == "7"){
						if(resultList[i].fixStateTwo == "9"){
							html += "입찰대기";
						}else{
							html += "입찰";
						}	
					}else if (resultList[i].fixState == "8"){
						html += "마감";	
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
					mhtml += "						<h4 class=\"sub_tit_05\">["+resultList[i].insertDate+"] "+resultList[i].mokName+"("+pumName+")</h4>                                         ";
					mhtml += "					</div>                                                                                                                                ";
					mhtml += "					<div class=\"fr\">                                                                                                                    ";
					if(resultList[i].fixState == "1"){
						mhtml += "							<p class=\"txt_apply\">신청</p>";	
					}else if (resultList[i].fixState == "2"){
						mhtml += "							<p class=\"txt_re\">반려</p>";	
					}else if (resultList[i].fixState == "3"){
						mhtml += "							<p class=\"txt_apply\">준비</p>";	
					}else if (resultList[i].fixState == "4"){
						mhtml += "							<p class=\"txt_apply\">완료</p>";	
					}else if (resultList[i].fixState == "5"){
						mhtml += "							<p class=\"txt_bid\">유찰</p>";	
					}else if (resultList[i].fixState == "6"){
						mhtml += "							<p class=\"txt_apply\">부분유찰</p>";	
					}else if (resultList[i].fixState == "7"){
						if(resultList[i].fixStateTwo == "9"){
							mhtml += "							<p class=\"txt_bid\">입찰대기</p>";
						}else{
							mhtml += "							<p class=\"txt_bid\">입찰</p>";
						}	
					}else if (resultList[i].fixState == "8"){
						mhtml += "							<p class=\"txt_bid\">마감</p>";	
					}
					mhtml += "						                                                                                                                                  ";
					mhtml += "					</div>                                                                                                                                ";
					mhtml += "				</div>                                                                                                                                    ";
					mhtml += "			</td>                                                                                                                                         ";
					mhtml += "		</tr>                                                                                                                                             ";
					mhtml += "	</thead>                                                                                                                                              ";
					mhtml += "	<tbody onclick=\"fn_view('"+resultList[i].fixDealSeq+"');\" style=\"cursor: pointer;\">                                                                       ";
					mhtml += "		<tr>                                                                                                                                              ";
					mhtml += "			<td>                                                                                                                                          ";
					mhtml += "				<div class=\"table_info\">                                                                                                                ";
					mhtml += "					<p class=\"txt_01\">출하자(코드)명 : "+resultList[i].chulName+" ( "+resultList[i].chulCode.substring(0,4)+" - "+resultList[i].chulCode.substring(4,8)+" )</p> ";
					mhtml += "					<ul class=\"profile_box\">                                                                                                            ";
					mhtml += "						<li>                                                                                                                              ";
					mhtml += "							<p class=\"txt_01\">등급 : "+resultList[i].chulLevelName+"</p>                                                                          ";
					mhtml += "							<p class=\"txt_01\">상자수량 : "+comma(resultList[i].boxCnt)+"</p>                              ";
					mhtml += "							<p class=\"txt_01\">판매구분 :                                                                                                    ";
					if(resultList[i].dealType == "L"){
						mhtml += "최저가 ";	
					}else if (resultList[i].dealType == "W"){
						mhtml += "희망가";	
					}else if (resultList[i].dealType == "F"){
						mhtml += "정가";	
					}
					mhtml += "							</p>                                                                                                                          ";
					mhtml += "							<p class=\"txt_01\">경매일자 :                                                                                                    ";
					mhtml += "								"+resultList[i].aucDate+"                                                                                                         ";
					mhtml += "							</p>                                                                                                                          ";
					mhtml += "						</li>                                                                                                                             ";
					mhtml += "						<li>                                                                                                                              ";
					mhtml += "							<p class=\"txt_01\">지역 : "+resultList[i].chulArea+"</p>                                                                               ";
					mhtml += "							<p class=\"txt_01\">속/분수량 : "+comma(resultList[i].sokCnt)+"</p>                             ";
					mhtml += "							<p class=\"txt_01\">판매단가 : "+comma(resultList[i].unitPrice)+"</p>                           ";
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
									<li><a href="${pageContext.request.contextPath}/front/fixNAuc/fixSellList.do" class="active">절화</a></li>
									</c:if>
									<c:if test="${yFloLoginVO ne null }">
									<li><a href="${pageContext.request.contextPath}/front/fixYAuc/fixSellList.do">&nbsp;&nbsp;&nbsp;난&nbsp;&nbsp;&nbsp;</a></li>
									</c:if>
									<c:if test="${cFloLoginVO ne null }">
									<li><a href="${pageContext.request.contextPath}/front/fixCAuc/fixSellList.do">관엽</a></li>
									<c:if test="${fixCSubChulCheck eq true }">
									<li><a href="${pageContext.request.contextPath}/front/fixCAuc/fixSellSubList.do">관엽-분갈이</a></li>
									</c:if>
									</c:if>
								</ul>
							</div>
							<!-- 서브탭(E) -->

							<!-- 검색조건창(S) -->
							<form id="frm" name="searchFrm" method="post" action="${pageContext.request.contextPath}/front/fixNAuc/fixSellList.do" >
							
							<div class="condition_box">
								<div class="search_box">
									<ul class="sb_line">
										<li>
											<p class="sb_title">출하예정일자</p>
											<div class="sb_data">
												<div class="date_box mt10">
													<input type="text" class="datepicker" name="searchStartDt" id="searchStartDt" value="<c:out value="${paramMap.searchStartDt}"/>">
												</div>
												<!--  
												<span class="hyphen">~</span>
												<div class="date_box">
													<input type="text" class="datepicker"  name="searchEndDt" id="searchEndDt" value="<c:out value="${paramMap.searchEndDt}"/>">
												</div>
												-->
											</div>
											<div class="sb_data ml10">
												<!--  
												<label class="radio mr10">
													<input type="radio" name="dateRadioChange" value="0" <c:if test="${paramMap.dateRadioChange eq null or paramMap.dateRadioChange eq '0'}">checked="checked"</c:if> onclick="setSearchDateInit(0);">
													<i class="rdo"></i>
													<em class="label-title">당일</em>
												</label>
												<label class="radio mr10">
													<input type="radio" name="dateRadioChange" value="1" <c:if test="${paramMap.dateRadioChange eq '1'}">checked="checked"</c:if> onclick="setSearchDateInit(1);">
													<i class="rdo"></i>
													<em class="label-title">2일</em>
												</label>
												<label class="radio mr10">
													<input type="radio" name="dateRadioChange" value="2" <c:if test="${paramMap.dateRadioChange eq '2'}">checked="checked"</c:if> onclick="setSearchDateInit(2);">
													<i class="rdo"></i>
													<em class="label-title">3일</em>
												</label>
												<label class="radio">
													<input type="radio" name="dateRadioChange" value="6" <c:if test="${paramMap.dateRadioChange eq '6'}">checked="checked"</c:if> onclick="setSearchDateInit(6);">
													<i class="rdo"></i>
													<em class="label-title">7일</em>
												</label>
												-->
											</div>
										</li>
										<li>
											<p class="sb_title">상태</p>
											<div class="sb_data">
												<div class="sel_type_01 w150">
													<select id="searchFixState" name="searchFixState"> 
														<option value="" <c:if test="${paramMap.searchFixState eq null}">selected="selected"</c:if>>전체</option>
														<option value="1" <c:if test="${paramMap.searchFixState eq '1'}">selected="selected"</c:if>>신청</option>
														<!-- <option value="3" <c:if test="${paramMap.searchFixState eq '3'}">selected="selected"</c:if>>준비</option> -->
														<option value="7" <c:if test="${paramMap.searchFixState eq '7'}">selected="selected"</c:if>>입찰</option>
														<option value="9" <c:if test="${paramMap.searchFixState eq '9'}">selected="selected"</c:if>>입찰대기</option>
														<option value="8" <c:if test="${paramMap.searchFixState eq '8'}">selected="selected"</c:if>>마감</option>
														<option value="2" <c:if test="${paramMap.searchFixState eq '2'}">selected="selected"</c:if>>반려</option>
														<option value="4" <c:if test="${paramMap.searchFixState eq '4'}">selected="selected"</c:if>>완료</option>
														<option value="5" <c:if test="${paramMap.searchFixState eq '5'}">selected="selected"</c:if>>유찰</option>
														<option value="6" <c:if test="${paramMap.searchFixState eq '6'}">selected="selected"</c:if>>부분유찰</option>
													</select>
													<label for="searchFixState"></label>
												</div>
											</div>
										</li>
										
									</ul>
									<ul class="sb_line">
										<li>
											<p class="sb_title">출하자코드</p>
											<div class="sb_data">
												<div class="ip_type_01 w100p">
													<input type="text" id="searchChulCode" name="searchChulCode" placeholder="검색어를 입력하세요" value="<c:out value="${paramMap.searchChulCode}"/>"><label for="searchChulCode"></label>
												</div>
											</div>
										</li>
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
									<h4 class="sub_tit_04">판매리스트 (${resultCnt}건)</h4>
								</div>
								<div class="fr"> 
									<p class="txt_unit">판매등록시간 : ${regTimeVO.strTime} ~ ${regTimeVO.endTime} (${regTimeVO.dayKr})
									<c:if test="${regTimeVO.timeCheck eq 'Y'}">
										<a href="javascript:fn_reg();" class="btn_type_01 gray ml10">등록</a>
									</c:if>
									</p>
								</div>
								
								
								
							</div>
							<!-- 타이틀(E) -->
							
							<!-- WEB테이블(S) -->
							<div class="table_type_01 web mt10">
								<table id="fixTable">
									<caption>info</caption>
									<colgroup>
										<col width="8.9%">
										<col width="8.9%">
										<col width="8.9%">
										<col width="17.8%">
										<col width="6.9%">
										<col width="8.9%">
										<col width="8.9%">
										<col width="10.9%">
										<col width="8.9%">
										<col width="8.9%">
									</colgroup>
									<thead>
										<tr>
											<th style="cursor: pointer;" onclick="fn_order(0);">신청일자</th>
											<th style="cursor: pointer;" onclick="fn_order(1);">품목명</th>
											<th style="cursor: pointer;" onclick="fn_order(2);">품종명</th>
											<th style="cursor: pointer;" onclick="fn_order(3);">출하자(코드)</th>
											<th style="cursor: pointer;" onclick="fn_order(4);">등급</th>
											<th style="cursor: pointer;" onclick="fn_order(5);">상자수</th>
											<th style="cursor: pointer;" onclick="fn_order(6);">속수량</th>
											<th style="cursor: pointer;" onclick="fn_order(10);">판매단가</th>
											<th style="cursor: pointer;" onclick="fn_order(8);">경매일자</th>
											<th style="cursor: pointer;" onclick="fn_order(9);">상태</th>
										</tr>
									</thead>
									<tbody id="resultWebList">
										<c:forEach items="${resultList}" var="result" varStatus="status">
											<tr style="cursor:pointer" onclick="fn_view('${result.fixDealSeq}');">
												<td class="tc"><p class="txt_01">${result.insertDate}</p></td>
												<td class="tc"><p class="txt_01">${result.mokName}</p></td>
												<td class="tc"><p class="txt_01">${result.pumName}<c:if test="${result.twinCnt ne '0'}">/${result.twinCnt}</c:if></p></td>
												<td class="tc">
													<p class="txt_01">${result.chulName} ( ${fn:substring(result.chulCode,0,4)} - ${fn:substring(result.chulCode,4,8)} )</p>
												</td>
												<td class="tc"><p class="txt_01">${result.chulLevelName}</p></td>
												<td class="tc"><p class="txt_01"><fmt:formatNumber value="${result.boxCnt}" pattern="#,###" /></p></td>
												<td class="tc"><p class="txt_01"><fmt:formatNumber value="${result.sokCnt}" pattern="#,###" /></p></td>
												<td class="tc">
													<p class="txt_01">
													<c:if test="${result.dealType eq 'L'}">
														최저가
													</c:if>
													<c:if test="${result.dealType eq 'W'}">
														희망가
													</c:if>
													<c:if test="${result.dealType eq 'F'}">
														정가
													</c:if>
													(<fmt:formatNumber value="${result.unitPrice}" pattern="#,###" />)
													</p>
												</td>
												<td class="tc">
													<p class="txt_01">
													${result.aucDate}
													<c:if test="${result.aucDate eq null}">
														-
													</c:if>
													</p>
												</td>
												<td class="tc">
													<p class="txt_01">
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
													</p>
												</td>
												<td class="tc" style="display: none;">${result.unitPrice}</td>
												
											</tr>
										</c:forEach>
										<c:if test="${fn:length(resultList) eq 0 }">
											<tr>
												<td class="tc" colspan="10">
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
															<p class="txt_apply">준비</p>
													   </c:if>
													   <c:if test="${result.fixState eq '4'}">
															<p class="txt_apply">완료</p>
													   </c:if>
													   <c:if test="${result.fixState eq '5'}">
															<p class="txt_bid">유찰</p>
													   </c:if>
													   <c:if test="${result.fixState eq '6'}">
															<p class="txt_apply">부분유찰</p>
													   </c:if>
													   <c:if test="${result.fixState eq '7'}">
															<c:choose>
																<c:when test="${result.fixStateTwo eq '9'}">
																	<p class="txt_bid">입찰대기</p>
															    </c:when>
														   		<c:otherwise>
																	<p class="txt_bid">입찰</p>
													            </c:otherwise>
											    	   		</c:choose>
															
													   </c:if>
													   <c:if test="${result.fixState eq '8'}">
															<p class="txt_bid">마감</p>
													   </c:if>
														
													</div>
												</div>
											</td>
										</tr>
									</thead>
									<tbody onclick="fn_view('${result.fixDealSeq}');" style="cursor: pointer;">
										<tr>
											<td>
												<div class="table_info">
													<p class="txt_01">출하자(코드)명 : ${result.chulName} ( ${fn:substring(result.chulCode,0,4)} - ${fn:substring(result.chulCode,4,8)} )</p>
													<ul class="profile_box">
														<li>
															<p class="txt_01">등급 : ${result.chulLevelName}</p>
															<p class="txt_01">수량 : <fmt:formatNumber value="${result.sokCnt}" pattern="#,###" />속/분 <fmt:formatNumber value="${result.boxCnt}" pattern="#,###" />상자</p>
															<p class="txt_01">판매구분 : 
																<c:if test="${result.dealType eq 'L'}">
																	최저가
																</c:if>
																<c:if test="${result.dealType eq 'W'}">
																	희망가
																</c:if>
																<c:if test="${result.dealType eq 'F'}">
																	정가
																</c:if>
															</p>
														</li>
														<li>
															<p class="txt_01">지역 : ${result.chulArea}</p>
															<p class="txt_01">판매단가 : <fmt:formatNumber value="${result.unitPrice}" pattern="#,###" /></p>
															<p class="txt_01">경매일자 : 
																${result.aucDate}
																<c:if test="${result.aucDate eq null}">
																	-
																</c:if>
															</p>
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
							<%-- <div class="paging_box mt30">
								<c:if test="${paginationInfo.lastPageNo ne paramMap.pageIndex}">
								<a href="javascript:fn_more();" class="btn_search_02 ml10" id="moreBtn">더보기 <b id="currentPageCnt"><c:out value="${paramMap.pageUnit}"/></b> / ${resultCnt}</a>
								</c:if>
								
							</div> --%>
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
