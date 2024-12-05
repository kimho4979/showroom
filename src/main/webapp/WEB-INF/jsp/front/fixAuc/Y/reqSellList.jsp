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

.reason{overflow:visible; }
.txt_01.reason-hover{ height:auto; line-height:30px; box-sizing:border-box;}
.txt_01.reason-hover::after{display:inline-block; content:''; background:url(${pageContext.request.contextPath}/img/ico-reason.png); width:18px; height:17px; background-size:cover; margin-left:5px; vertical-align:-10%; }
/*.reason-page{position:absolute; width:100%; left:0; border:1px solid #dcdcdc; box-sizing:border-box; padding:15px 15px; background:#eeeeee; height:auto; display:none; transform:translateY(0px); }*/
.reason-page{position:absolute; right:-100%; width:100%;  height:auto; border:1px solid #dcdcdc; box-sizing:border-box; padding:15px 15px; background:rgba(176, 188, 205,0.7); transition-duration:0.3s;}

.reason-page .txt-r{color:#ff0000; font-size:15px; }
.reason-page.open{ right:0;}	 



</style>

<script src="${pageContext.request.contextPath}/js/fix.js"></script>

<script type="text/javascript">


var pageIndex = 1;
var pageUnit = 10;
var orderArray = new Array();
var replace = null;

orderArray = [false,false,false,false,false,false,false,false,false,false,false]


function fn_view(reqDealSeq){
	window.location.href = "${pageContext.request.contextPath}/front/fixYAuc/reqSellView.do?reqDealSeq="+reqDealSeq;
}

function fn_reg(){
	window.location.href = "${pageContext.request.contextPath}/front/fixYAuc/reqSellReg.do";
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
        url : "${pageContext.request.contextPath}/front/fixYAuc/reqSellList.json",
        success : function(data){
           console.log("더보가페이징:",data);
           var html = "";
           var mhtml = "";
           var resultList = data.resultList;
           var rowIndex = ((pageIndex-1)*pageUnit);
           if(resultList.length > 0){
        	   for(var i=0; i<resultList.length; i++ ){
        		   
        		   var twinCnt = resultList[i].twinCnt;  
	           		var pumName = resultList[i].pumName;
	           		if(twinCnt == "0" || twinCnt == null){
	           			pumName = pumName;
	           		}else{ 
	           			pumName = pumName + "/" + twinCnt;
	           		} 
	           		
	           		
	           		//FLAG( 요청 및 개선사항 ) 
	           		var reqDealSeqFlag = false;
	           		if(resultList[i].reqText != null && resultList[i].reqText.toString().trim().length > 0){
	           			reqDealSeqFlag = true;
	           		}
	           		
        		    //resultWebList
           	    	html += "<tr id=\"fixTr"+resultList[i].reqDealSeq+"\">                                                        ";
           	    	html += "<input type=\"hidden\" id=\"fixState"+resultList[i].reqDealSeq+"\" value=\""+resultList[i].fixState+"\"/>";
           	    	html += "<td class=\"tc\"><input type=\"checkbox\" name=\"fixCheck\" value=\""+resultList[i].reqDealSeq+"\" id=\"t"+resultList[i].reqDealSeq+"\" onchange=\"fn_check(this, '"+resultList[i].reqDealSeq+"');\"><label for=\"t"+resultList[i].reqDealSeq+"\"><span class=\"mr0\"></span></label></td>";
           	    	html += "	<td class=\"tc td_add\" style=\"cursor:pointer;\" onclick=\"fn_view('"+resultList[i].reqDealSeq+"');\"><p class=\"txt_01\">"+resultList[i].insertDate+"</p></td>                                                             ";
           	    	html += "	<td class=\"tc td_add\" style=\"cursor:pointer;\" onclick=\"fn_view('"+resultList[i].reqDealSeq+"');\"><p class=\"txt_01\">"+resultList[i].chulDate+"</p></td>                                                             ";
					html += "	<td class=\"tc td_add\" style=\"cursor:pointer;\" onclick=\"fn_view('"+resultList[i].reqDealSeq+"');\"><p class=\"txt_01\">"+resultList[i].mokName+"</p></td>                                                                ";
					html += "	<td class=\"tc td_add\" style=\"cursor:pointer;\" onclick=\"fn_view('"+resultList[i].reqDealSeq+"');\"><p class=\"txt_01\">"+pumName+"</p></td>                                                                ";
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
					if(reqDealSeqFlag == true){
						
						html += "	<td class=\"tc td_add reason\" style=\"cursor:pointer;\" onclick=\"fn_view('"+resultList[i].reqDealSeq+"');\">                                                                                                              ";
						html += "		<p class=\"txt_01 reason-hover\">";
						if(resultList[i].fixState == "1"){
							html += "신청 ";	
						}else if (resultList[i].fixState == "2"){
							html += "미체결";	
						}else if (resultList[i].fixState == "3"){
							html += "진행";	
						}else if (resultList[i].fixState == "4"){
							html += "체결";	
						}
						html += "</p>                                                                                                                       ";
    					html += "	<div class=\"reason-page\">                                                                                                       ";
    					html += "		<p class=\"txt-r\">요청 및 개선사항 :"+resultList[i].reqText+"</p>                                                                        ";
    					html += "	</div>                                                                                                                         ";
						html += "	</td>                                                                                                                          ";
						
					}else{
						html += "	<td class=\"tc td_add\" style=\"cursor:pointer;\" onclick=\"fn_view('"+resultList[i].reqDealSeq+"');\">                                                                                                              ";
						html += "		<p class=\"txt_01\">";
						if(resultList[i].fixState == "1"){
							html += "신청 ";	
						}else if (resultList[i].fixState == "2"){
							html += "미체결";	
						}else if (resultList[i].fixState == "3"){
							html += "진행";	
						}else if (resultList[i].fixState == "4"){
							html += "체결";	
						}
						html += "</p>                                                                                                                       ";
						html += "	</td>                                                                                                                          ";
					}
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
						mhtml += "							<p class=\"txt_re\">미체결</p>";	
					}else if (resultList[i].fixState == "3"){
						mhtml += "							<p class=\"txt_apply\">진행</p>";	
					}else if (resultList[i].fixState == "4"){
						mhtml += "							<p class=\"txt_apply\">체결</p>";	
					}
					mhtml += "						                                                                                                                                  ";
					mhtml += "					</div>                                                                                                                                ";
					mhtml += "<div class=\"fr\"><input type=\"checkbox\" id=\"tm"+resultList[i].reqDealSeq+"\" name=\"mfixCheck\" onchange=\"fn_mcheck(this, '"+resultList[i].reqDealSeq+"');\"><label for=\"tm"+resultList[i].reqDealSeq+"\"><span class=\"mr10\"></span></label></div>";
					mhtml += "				</div>                                                                                                                                    ";
					mhtml += "			</td>                                                                                                                                         ";
					mhtml += "		</tr>                                                                                                                                             ";
					mhtml += "	</thead>                                                                                                                                              ";
					mhtml += "	<tbody>                                                                       ";
					mhtml += "		<tr>                                                                                                                                              ";
					mhtml += "			<td>                                                                                                                                          ";
					mhtml += "				<div class=\"table_info\">                                                                                                                ";
					mhtml += "					<p class=\"txt_01\">출하자(코드)명 : "+resultList[i].chulName+" ( "+resultList[i].chulCode.substring(0,4)+" - "+resultList[i].chulCode.substring(4,8)+" )</p> ";
					mhtml += "					<ul class=\"profile_box\" onclick=\"fn_view('"+resultList[i].reqDealSeq+"');\" style=\"cursor: pointer;\">                                                                                                            ";
					mhtml += "						<li>                                                                                                                              ";
					mhtml += "							<p class=\"txt_01\">등급 : "+resultList[i].chulLevelName+"</p>                                                                          ";
					mhtml += "							<p class=\"txt_01\">상자수량 : "+comma(resultList[i].boxCnt)+"</p>                              ";
					mhtml += "							<p class=\"txt_01\">입고희망일 :                                                                                                    ";
					mhtml += "								"+resultList[i].chulDate+"                                                                                                         ";
					mhtml += "							</p>                                                                                                                          ";
					mhtml += "						</li>                                                                                                                             ";
					mhtml += "						<li>                                                                                                                              ";
					mhtml += "							<p class=\"txt_01\">지역 : "+resultList[i].chulArea+"</p>                                                                               ";
					mhtml += "							<p class=\"txt_01\">분수량 : "+comma(resultList[i].sokCnt)+"</p>                             ";
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
        	   html += "<tr><td class=\"tc\" colspan=\"13\">데이터가 없습니다.</td></tr>";
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


function fn_aucPrice(reqDealSeq){
	
	$("#maucPrice"+reqDealSeq).val($("#aucPrice"+reqDealSeq).val());
}

function fn_maucPrice(reqDealSeq){
	
	$("#aucPrice"+reqDealSeq).val($("#maucPrice"+reqDealSeq).val());
}


function fn_check(obj, reqDealSeq){
	
	if(obj.checked){
		$("#tm"+reqDealSeq).prop("checked",true);
	}else{
		$("#tm"+reqDealSeq).prop("checked",false);
	}
}

function fn_mcheck(obj, reqDealSeq){
	console.log(obj.id);
	if(obj.checked){
		$("#t"+reqDealSeq).prop("checked",true);
	}else{
		$("#t"+reqDealSeq).prop("checked",false);
	}
}

function fn_allCheck(){
	if($("#allCheck").prop("checked")){
	    
	    $("input[name=fixCheck]").prop("checked",true);
	    $("input[name=mfixCheck]").prop("checked",true);
	    
	}else{
	    $("input[name=fixCheck]").prop("checked",false);
	    $("input[name=mfixCheck]").prop("checked",false);
	}	
}



function fn_comp(){
	var checkedArray = new Array();
	var aucPriceArray = new Array();
	
	$("input[name=fixCheck]:checked").each(function(index){
		var checkedValue = this.value;
		var fixState = $("#fixState"+checkedValue).val();
		if(fixState == 3){
			checkedArray.push(checkedValue);
			aucPriceArray.push("");
		}
		
	});
	
	if(checkedArray.length == 0){
		alert("체크리스트중 진행상태의 요청이 없습니다.");
	}else{
		$.ajax({
			data:{
				checkedArray: checkedArray,
				aucPriceArray: aucPriceArray
		        },
	        type : "POST",
	        url : "${pageContext.request.contextPath}/front/fixYAuc/reqSellComp.json",
	        success : function(data){
	             alert("진행된 요청 " + data.result + "건 체결처리되었습니다.");
	             location.reload(true);
	        }
	    });
	}
	
}


$(document).ready(function(){
	
	$(document).on({
		 mouseenter: function () {
			 $(this).parent().find($('.reason-page')).addClass('open');
	        },
		 mouseleave: function () {
			$(this).parent().find($('.reason-page')).removeClass('open');
	        }
	}, '.reason-hover');
	
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
									<li><a href="${pageContext.request.contextPath}/front/fixNAuc/reqSellList.do">절화</a></li>
									</c:if>
									<c:if test="${yFloLoginVO ne null }">
									<li><a href="${pageContext.request.contextPath}/front/fixYAuc/reqSellList.do" class="active">&nbsp;&nbsp;&nbsp;난&nbsp;&nbsp;&nbsp;</a></li>
									</c:if>
									<c:if test="${cFloLoginVO ne null }">
									<li><a href="${pageContext.request.contextPath}/front/fixCAuc/reqSellList.do">관엽</a></li>
									</c:if>
								</ul>
							</div>
							<!-- 서브탭(E) -->

							<!-- 검색조건창(S) -->
							<form id="frm" name="searchFrm" method="post" action="${pageContext.request.contextPath}/front/fixYAuc/reqSellList.do" >
							
							<div class="condition_box">
								<div class="search_box">
									<ul class="sb_line">
										<li>
											<p class="sb_title">입고희망일자</p>
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
														<option value="3" <c:if test="${paramMap.searchFixState eq '3'}">selected="selected"</c:if>>진행</option>
														<option value="4" <c:if test="${paramMap.searchFixState eq '4'}">selected="selected"</c:if>>체결</option>
													</select>
													<label for="searchFixState"></label>
												</div>
											</div>
										</li>
										
									</ul>
									<ul class="sb_line">
										<li>
											<p class="sb_title">출하자</p>
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
									<h4 class="sub_tit_04">중도매인 요청리스트 (${resultCnt}건)</h4>
								</div>
								
							</div>
							<!-- 타이틀(E) -->
							
							<!-- WEB테이블(S) -->
							<div class="table_type_01 web mt10">
								<table id="fixTable">
									<caption>info</caption>
									<colgroup>
										<col width="6.2%">
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
										<col width="5.9%">
									</colgroup>
									<thead>
										<tr>
											<th><input type="checkbox" id="allCheck" name="allCheck" onchange="fn_allCheck(this);"><label for="allCheck"><span class="mr0"></span></label>전체</th>
											<th style="cursor: pointer;" onclick="fn_order(1);">신청일자</th>
											<th style="cursor: pointer;" onclick="fn_order(2);">입고희망일</th>
											<th style="cursor: pointer;" onclick="fn_order(3);">품목명</th>
											<th style="cursor: pointer;" onclick="fn_order(4);">품종명</th>
											<th style="cursor: pointer;" onclick="fn_order(5);">출하자(코드)</th>
											<th style="cursor: pointer;" onclick="fn_order(6);">지역</th>
											<th style="cursor: pointer;" onclick="fn_order(7);">중도매인(코드)</th>
											<th style="cursor: pointer;" onclick="fn_order(8);">등급</th>
											<th style="cursor: pointer;" onclick="fn_order(9);">상자수</th>
											<th style="cursor: pointer;" onclick="fn_order(10);">분수량</th>
											<th style="cursor: pointer;" onclick="fn_order(11);">구매단가</th>
											<!-- <th>조정단가</th> -->
											<th style="cursor: pointer;" onclick="fn_order(12);">상태</th>
										</tr>
									</thead>
									<tbody id="resultWebList">
										<c:forEach items="${resultList}" var="result" varStatus="status">
											<tr id="fixTr${result.reqDealSeq}">
												<input type="hidden" id="fixState${result.reqDealSeq}" value="${result.fixState}"/>
												<td class="tc"><input type="checkbox" id="t${result.reqDealSeq}" name="fixCheck" value="${result.reqDealSeq}" onchange="fn_check(this, '${result.reqDealSeq}');"><label for="t${result.reqDealSeq}"><span class="mr0"></span></label></td>
												<td class="tc" style="cursor:pointer" onclick="fn_view('${result.reqDealSeq}');"><p class="txt_01">${result.insertDate}</p></td>
												<td class="tc" style="cursor:pointer" onclick="fn_view('${result.reqDealSeq}');"><p class="txt_01">${result.chulDate}</p></td>
												<td class="tc" style="cursor:pointer" onclick="fn_view('${result.reqDealSeq}');"><p class="txt_01">${result.mokName}</p></td>
												<td class="tc" style="cursor:pointer" onclick="fn_view('${result.reqDealSeq}');"><p class="txt_01">${result.pumName}<c:if test="${result.twinCnt ne '0'}">/${result.twinCnt}</c:if></p></td>
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
																																				
												<!-- FLAG ( 요청 및 개선사항 ) -->
											    <c:set var="reqDealSeqFlag" value="false" />
											    <c:if test="${result.reqText != null && result.reqText.toString().trim().length() > 0}">
											  		<c:set var="reqDealSeqFlag" value="true" />
											    </c:if>
												
											    <c:if test="${reqDealSeqFlag == true}">
												<td class="tc  reason" style="cursor:pointer" onclick="fn_view('${result.reqDealSeq}');">
													<p class="txt_01 reason-hover">
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
													</p>
													<div class="reason-page">
														<p class="txt-r">요청 및 개선사항 : ${result.reqText}</p>
													</div>
												</td>
												</c:if>
												<c:if test="${reqDealSeqFlag == false}">
												<td class="tc" style="cursor:pointer" onclick="fn_view('${result.reqDealSeq}');">
													<p class="txt_01">
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
													</p>
												</td>
												</c:if>
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
															<p class="txt_apply">진행</p>
													   </c:if>
													   <c:if test="${result.fixState eq '4'}">
															<p class="txt_apply">체결</p>
													   </c:if>
													</div>
													<div class="fr"><input type="checkbox" id="tm${result.reqDealSeq}" name="mfixCheck" onchange="fn_mcheck(this, '${result.reqDealSeq}');"><label for="tm${result.reqDealSeq}"><span class="mr10"></span></label></div>
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
															<p class="txt_01">분수량 : <fmt:formatNumber value="${result.sokCnt}" pattern="#,###" /></p>
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
								<a href="javascript:fn_comp();" class="btn_type_01 gray">체크된행 체결<a>
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
