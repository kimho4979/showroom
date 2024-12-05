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


var pageIndex = 0;
var changeIndex = 0;
var pageUnit = 5;
var orderArray = new Array();
var replace = null;

orderArray = [false,false,false,false,false,false,false,false,false,false,false]


function fn_view(fixDealSeq){
	var jCode = $("#jCode").prop("value");
	window.location.href = "${pageContext.request.contextPath}/front/fixNAuc/fixBuyView.do?fixDealSeq="+fixDealSeq+"&jCode="+jCode;
}

function fn_reg(){
	window.location.href = "${pageContext.request.contextPath}/front/fixNAuc/fixBuyReg.do";
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
	var code = $("#jCode").prop("value");
	
	$(".td_add").removeClass("td_add");
	
	$.ajax({
		data:{
			pageIndex: pageIndex,
			searchStartDt:searchStartDt,
			searchEndDt:searchEndDt,
			searchChulCode:searchChulCode,
			searchPumCode:searchPumCode,
			searchFixState:searchFixState,
			code:code
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixNAuc/fixBuyList.json",
        success : function(data){
           console.log("더보가페이징:",data);
           var html = "";
           var mhtml = "";
           var resultList = data.resultList;
           var rowIndex = ((pageIndex-1)*pageUnit);
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
	           		
           	    	html += "<tr>                                                        ";
					html += "	<td class=\"tc td_add\"><p class=\"txt_01\">"+resultList[i].aucDate+"</p></td>                                                             ";
					html += "	<td class=\"tc pd2\" onclick=\"fn_view('"+resultList[i].fixDealSeq+"');\" style=\"cursor: pointer;\">";
					html += "	<div class=\"t_img\">";
					html += "		<img src=\""+contextPath+resultList[i].thumbPath+"\" alt=\"사진_"+resultList[i].pumName+"\">";
					html += "	</div>";
					html += "   </td>";
					html += "	<td class=\"tc td_add\"><p class=\"txt_01\">"+resultList[i].mokName+"</p></td>                                                                ";
					html += "	<td class=\"tc td_add\"><p class=\"txt_01\">"+pumName+"</p></td>                                                                ";
					html += "	<td class=\"tc td_add\">                                                                                                              ";
					html += "		<p class=\"txt_01\">"+resultList[i].chulName+"</p> ";
					html += "		<p class=\"txt_01\"> ( "+resultList[i].chulCode.substring(0,4)+" - "+resultList[i].chulCode.substring(4,8)+" )</p> ";
					html += "	</td>                                                                                                                          ";
					html += "	<td class=\"tc td_add\"><p class=\"txt_01\">"+resultList[i].chulArea+"</p></td>                                                          ";
					html += "	<td class=\"tc td_add\"><p class=\"txt_01\">"+resultList[i].chulLevelName+"</p></td>                                                          ";
					html += "	<td class=\"tc td_add\"><p class=\"txt_01\">"+comma(resultList[i].boxCnt)+"</p>                ";
					if(resultList[i].fixState != "4" && resultList[i].fixState != "5" && resultList[i].fixState != "6" && (resultList[i].fixState == "7" || resultList[i].bidAdminYn == "Y")){
						html += "	<div class=\"ip_type_01 w100p mt10\">";
						html += "		<input type=\"number\" class=\"tr\" id=\"bidBoxCnt"+(rowIndex+i)+"\" value=\""+resultList[i].bidBoxCnt+"\" placeholder=\"-\" maxlength=\"2\" oninput=\"maxLengthCheck(this);fn_boxCntKeyUp('"+(rowIndex+i)+"',"+resultList[i].boxCnt+","+resultList[i].unitSok+",'w');\">";
						html += "	</div>";
					}else if(resultList[i].fixState == "4" || resultList[i].fixState == "5" || resultList[i].fixState == "6" || (resultList[i].fixState == "8" && resultList[i].bidBoxCnt !="" && resultList[i].bidAdminYn != "Y")){
						html += "	<p class=\"txt_01\">";
						html += "	입찰: "+comma(resultList[i].bidBoxCnt)+"";
						html += "	</p>";
					}
					html += "</td>";
					html += "	<td class=\"tc td_add\"><p class=\"txt_01\">"+comma(resultList[i].sokCnt)+"</p>                ";
					if(resultList[i].fixState != "4" && resultList[i].fixState != "5" && resultList[i].fixState != "6" && (resultList[i].fixState == "7" || resultList[i].bidAdminYn == "Y")){
						html += "	<div class=\"ip_type_01 w100p mt10\">";
						html += "		<input type=\"number\" readonly=\"readonly\" class=\"tr\" id=\"bidSokCnt"+(rowIndex+i)+"\" value=\""+resultList[i].bidSokCnt+"\" placeholder=\"-\" maxlength=\"4\" oninput=\"maxLengthCheck(this)\">";
						html += "	</div>";
					}else if(resultList[i].fixState == "4" || resultList[i].fixState == "5" || resultList[i].fixState == "6" || (resultList[i].fixState == "8" && resultList[i].bidSokCnt !="" && resultList[i].bidAdminYn != "Y")){
						html += "	<p class=\"txt_01\">";
						html += "	입찰:"+comma(resultList[i].bidSokCnt)+"";
						html += "	</p>";
					}
					html += "</td>";
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
					if(resultList[i].fixState != "4" && resultList[i].fixState != "5" && resultList[i].fixState != "6" && (resultList[i].fixState == "7" || resultList[i].bidAdminYn == "Y")){
						html += "	<div class=\"ip_type_01 w100p mt10\">";
						if(resultList[i].bidUnitPrice != ""){
							html += "		<input type=\"number\" class=\"tr\" id=\"bidUnitPrice"+(rowIndex+i)+"\" value=\""+resultList[i].bidUnitPrice+"\" placeholder=\"-\" maxlength=\"10\" oninput=\"maxLengthCheck(this)\">";	
						}else{
							html += "		<input type=\"number\" class=\"tr\" id=\"bidUnitPrice"+(rowIndex+i)+"\" value=\""+resultList[i].unitPrice+"\" placeholder=\"-\" maxlength=\"10\" oninput=\"maxLengthCheck(this)\">";	
						}
						html += "	</div>";
					}else if(resultList[i].fixState == "4" || resultList[i].fixState == "5" || resultList[i].fixState == "6" || (resultList[i].fixState == "8" && resultList[i].bidUnitPrice !="" && resultList[i].bidAdminYn != "Y")){
						html += "	<p class=\"txt_01\">";
						html += "	입찰:"+comma(resultList[i].bidUnitPrice)+"";
						html += "	</p>";
					}
					html += "	</td>                                                                                                                          ";
					html += "	<td class=\"tc td_add\">                                                                                                              ";
					if (resultList[i].fixState == "3"){
						html += "<p class=\"txt_01\">준비</p>";	
					}else if (resultList[i].fixState == "4"){
						html += "<p class=\"txt_01\">완료</p>";	
					}else if (resultList[i].fixState == "5"){
						html += "<p class=\"txt_01\">유찰</p>";	
					}else if (resultList[i].fixState == "6"){
						html += "<p class=\"txt_01\">부분유찰</p>";	
					}else if (resultList[i].fixState == "7" || resultList[i].bidAdminYn == "Y"){
						if(resultList[i].bidCheck == "Y" && resultList[i].bidAdminYn != "Y"){
							if(resultList[i].bidState == '5'){
								html += "<p class=\"txt_01\">입찰취소</p>";
							}else{
								html += "<p class=\"txt_01\">입찰완료</p>";	
							}		
						}else{
							if (resultList[i].fixStateTwo == "9"){
								html += "<p class=\"txt_01\">입찰대기</p>";
							}else{
								html += "<a href=\"javascript:fn_wbid('"+(rowIndex+i)+"','"+resultList[i].fixDealSeq+"');\" class=\"btn_type_round\">입찰</a>";
							}
						}
							
					}else if (resultList[i].fixState == "8" && resultList[i].bidAdminYn != "Y"){
						html += "<p class=\"txt_01\">마감</p>";	
					}
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
					mhtml += "						<h4 class=\"sub_tit_05\">["+resultList[i].aucDate+"] "+resultList[i].mokName+"("+pumName+")</h4>                                         ";
					mhtml += "					</div>                                                                                                                                ";
					mhtml += "					<div class=\"fr\">                                                                                                                    ";
					if (resultList[i].fixState == "3"){
						mhtml += "							<p class=\"txt_apply\">준비</p>";	
					}else if (resultList[i].fixState == "7"){
						if (resultList[i].fixStateTwo == "9"){
							mhtml += "							<p class=\"txt_bid\">입찰대기</p>";
						}else{
							mhtml += "							<p class=\"txt_bid\">입찰</p>";	
						}
					}else if (resultList[i].fixState == "8"){
						mhtml += "							<p class=\"txt_bid\">마감</p>";	
					}else if (resultList[i].fixState == "4"){
						mhtml += "							<p class=\"txt_bid\">완료</p>";	
					}else if (resultList[i].fixState == "5"){
						mhtml += "							<p class=\"txt_bid\">유찰</p>";	
					}else if (resultList[i].fixState == "6"){
						mhtml += "							<p class=\"txt_bid\">부분유찰</p>";	
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
					mhtml += "				<div class=\"table_info\">                                                                                                                ";
					mhtml += "					<ul class=\"profile_box devide_3_7\" onclick=\"fn_view('"+resultList[i].fixDealSeq+"');\" style=\"cursor: pointer;\">                                                                                                            ";
					mhtml += "<li class=\"de1\">";
					mhtml += "	<img src=\""+contextPath+resultList[i].thumbPath+"\" alt=\"사진_"+resultList[i].pumName+"\">";
					mhtml += "</li>";
					mhtml += "						<li class=\"de2\">                                                                                                                              ";
					mhtml += "					<p class=\"txt_01\">출하자(코드)명 : "+resultList[i].chulName+" ( "+resultList[i].chulCode.substring(0,4)+" - "+resultList[i].chulCode.substring(4,8)+" )</p> ";
					mhtml += "							<p class=\"txt_01\">등급 : "+resultList[i].chulLevelName+"</p>                                                                          ";
					mhtml += "							<p class=\"txt_01\">지역 : "+resultList[i].chulArea+"</p>                                                                               ";
					mhtml += "							<p class=\"txt_01\">수량 : "+comma(resultList[i].sokCnt)+"속/분 "+comma(resultList[i].boxCnt)+"상자</p>                              ";
					mhtml += "							<p class=\"txt_01\">판매구분 :                                                                                                    ";
					if(resultList[i].dealType == "L"){
						mhtml += "최저가 ";	
					}else if (resultList[i].dealType == "W"){
						mhtml += "희망가";	
					}else if (resultList[i].dealType == "F"){
						mhtml += "정가";	
					}
					mhtml += "							</p>                                                                                                                          ";
					mhtml += "							<p class=\"txt_01\">판매단가 : "+comma(resultList[i].unitPrice)+"</p>                           ";
					mhtml += "						</li>                                                                                                                             ";
					mhtml += "					</ul>                                                                                                                                 ";
					if(resultList[i].fixState != "4" && resultList[i].fixState != "5" && resultList[i].fixState != "6" && (resultList[i].fixState == "7" || resultList[i].bidAdminYn == "Y")){
						mhtml += "<ul class=\"figure_box\">";
						mhtml += "	<li>";
						mhtml += "		<p>입찰상자</p>";
						mhtml += "		<div class=\"ip_type_01 w100p\">";
						mhtml += "			<input type=\"number\" id=\"mbidBoxCnt"+(rowIndex+i)+"\" class=\"tr\" value=\""+resultList[i].bidBoxCnt+"\" maxlength=\"2\" oninput=\"maxLengthCheck(this);fn_boxCntKeyUp('"+(rowIndex+i)+"',"+resultList[i].boxCnt+","+resultList[i].unitSok+",'m');\"><label for=\"mbidBoxCnt"+(rowIndex+i)+"\"></label>";
						mhtml += "		</div>";
						mhtml += "	</li>";
						mhtml += "	<li>";
						mhtml += "		<p>입찰수량</p>";
						mhtml += "		<div class=\"ip_type_01 w100p\">";
						mhtml += "			<input type=\"number\" readonly=\"readonly\" id=\"mbidSokCnt"+(rowIndex+i)+"\" class=\"tr\" value=\""+resultList[i].bidSokCnt+"\" maxlength=\"4\" oninput=\"maxLengthCheck(this)\"><label for=\"mbidSokCnt"+(rowIndex+i)+"\"></label>";
						mhtml += "		</div>";
						mhtml += "	</li>";
						mhtml += "	<li>";
						mhtml += "		<p>구매단가</p>";
						mhtml += "		<div class=\"ip_type_01 w100p\">";
						if(resultList[i].bidUnitPrice != ""){
							mhtml += "			<input type=\"number\" id=\"mbidUnitPrice"+(rowIndex+i)+"\" value=\""+resultList[i].bidUnitPrice+"\" class=\"tr\" maxlength=\"10\" oninput=\"maxLengthCheck(this)\"><label for=\"mbidUnitPrice"+(rowIndex+i)+"\"></label>";	
						}else{
							mhtml += "			<input type=\"number\" id=\"mbidUnitPrice"+(rowIndex+i)+"\" value=\""+resultList[i].unitPrice+"\" class=\"tr\" maxlength=\"10\" oninput=\"maxLengthCheck(this)\"><label for=\"mbidUnitPrice"+(rowIndex+i)+"\"></label>";
						}
						mhtml += "		</div>";
						mhtml += "	</li>";
						mhtml += "</ul>";
						if(resultList[i].bidCheck =="Y" && resultList[i].bidAdminYn != "Y"){
							if(resultList[i].bidState == '5'){
								mhtml += "<a href=\"javascript:alert('경매사에 의해 취소된 입찰입니다. 상세화면에서 취소사유를 확인하세요.');\" class=\"btn_table_mobile\">취소된 입찰입니다.</a>";
							}else{
								mhtml += "<a href=\"javascript:alert('완료된 입찰입니다.');\" class=\"btn_table_mobile\">입찰완료</a>";	
							}
						}else{
							if(resultList[i].fixStateTwo != "9"){
								mhtml += "<a href=\"javascript:fn_mbid('"+(rowIndex+i)+"','"+resultList[i].fixDealSeq+"');\" class=\"btn_table_mobile\">입찰하기</a>";	
							}
						}
						
					}else if(resultList[i].fixState == "4" || resultList[i].fixState == "5" || resultList[i].fixState == "6" || (resultList[i].fixState == "8" && resultList[i].bidCheck =="Y" && resultList[i].bidAdminYn != "Y")){
						mhtml += "<ul class=\"figure_box\">";
						mhtml += "	<li>";
						mhtml += "		<p>입찰상자</p>";
						mhtml += "		<p class=\"txt_01\">";
						mhtml += comma(resultList[i].bidBoxCnt);
						mhtml += "		</p>";
						mhtml += "	</li>";
						mhtml += "	<li>";
						mhtml += "		<p>입찰수량</p>";
						mhtml += "		<p class=\"txt_01\">";
						mhtml += comma(resultList[i].bidSokCnt);
						mhtml += "		</p>";
						mhtml += "	</li>";
						mhtml += "	<li>";
						mhtml += "		<p>구매단가</p>";
						mhtml += "		<p class=\"txt_01\">";
						mhtml += comma(resultList[i].bidUnitPrice);
						mhtml += "		</p>";
						mhtml += "	</li>";
						mhtml += "</ul>";
					}
					mhtml += "				</div>                                                                                                                                    ";
					mhtml += "			</td>                                                                                                                                         ";
					mhtml += "		</tr>                                                                                                                                             ";
					mhtml += "	</tbody>                                                                                                                                              ";
					mhtml += "</table>                                                                                                                                                ";
           	   		
              }
        	   
        	  
        	   if(resultList.length < 5){
        		   $("#moreBtn").css("display","none");
        	   }else{
        		   $("#moreBtn").css("display","");$("#currentPageCnt").text(pageUnit*pageIndex);
        	   }
        	   
        	   
        	   
           }else{
        	   html += "<tr><td class=\"tc\" colspan=\"11\">데이터가 없습니다.</td></tr>";
        	   mhtml += "데이터가 없습니다.";
        	   $("#moreBtn").css("display","none");
           }
           
           $(".totalPageCnt").text(data.resultCnt);
           
           $("#resultWebList").append(html);
           $("#resultMobList").append(mhtml);
           
           
    	   if(data.paramMap.pageIndex == "1"){
    		   $(".td_add").removeClass("td_add");
    	   }
    	   
    	   
    	   if(changeIndex > 1){
    		   changeIndex--;
    		   fn_more();
    	   }
    	   
    	   $("input[type=number]").keydown(function(e){
    		   
	   			if(e.keyCode == 107 || e.keyCode == 109 || e.keyCode == 187 || e.keyCode == 189 || e.keyCode == 229 || e.keyCode == 110 || e.keyCode == 190) {
	   		        return false;
	   		    }
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


function fn_wbid(index, fixDealSeq){
	var bidBoxCnt = $("#bidBoxCnt"+index).val();
	var bidSokCnt = $("#bidSokCnt"+index).val();
	var bidUnitPrice = $("#bidUnitPrice"+index).val();
	fn_bid(fixDealSeq, bidBoxCnt, bidSokCnt, bidUnitPrice );
}

function fn_mbid(index, fixDealSeq){
	var bidBoxCnt = $("#mbidBoxCnt"+index).val();
	var bidSokCnt = $("#mbidSokCnt"+index).val();
	var bidUnitPrice = $("#mbidUnitPrice"+index).val();
	fn_bid(fixDealSeq, bidBoxCnt, bidSokCnt, bidUnitPrice );
}

function fn_bid(fixDealSeq, bidBoxCnt, bidSokCnt, bidUnitPrice ){
	
	if(bidBoxCnt == "" || bidBoxCnt == null || bidBoxCnt == 0){
		alert("입찰 상자수량을 입력해주세요");
		return false;
	}
	
	if(bidSokCnt == ""  || bidSokCnt == null || bidSokCnt == 0){
		alert("입찰 속/분 수량을 입력해주세요");
		return false;
	}
	
	if(bidUnitPrice == ""  || bidUnitPrice == null || bidUnitPrice == 0){
		alert("입찰단가를 입력해주세요");
		return false;
	}
	
	var code = $("#jCode").prop("value");
	
	$.ajax({
		data:{
			fixDealSeq: fixDealSeq,
			bidBoxCnt: bidBoxCnt,
			bidSokCnt: bidSokCnt,
			bidUnitPrice: bidUnitPrice,
			code: code
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixNAuc/fixBuyBid.json",
        success : function(data){
           if(data.result == 1){
        	   alert("정상적으로 입찰되었습니다");
           }else if(data.result == 2){
        	   alert("정상적으로 수정되었습니다 \n 수정 전 입찰금액은 거래잔액으로 전환됩니다.");
           }else if(data.result == 3){
        	   alert("거래잔액이 부족합니다.");
           }else if(data.result == 4){
        	   alert("입찰 마감된 거래입니다.");
           }else if(data.result == 5){
        	   alert("완료된 입찰입니다.");
           }else {
        	   alert(data.message);
           }
           
           listChange();
           getTradeAmt();
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
        url : "${pageContext.request.contextPath}/front/fixNAuc/getLimitAmt.json",
        success : function(data){
           console.log(data);
           var limitAmt = data.result.limitAmt;
           var reqAmt = data.result.reqAmt;
           var fixAmt = data.result.fixAmt;
           
           $("#limitAmt").html("거래잔액 : "+comma(limitAmt)+"원");
           $("#reqAmt").html("요청금액 : "+comma(reqAmt)+"원  /");
           $("#fixAmt").html("입찰금액 : "+comma(fixAmt)+"원  /");
           
        }
    });
}


function listChange(){
	 $("#resultWebList").html("");
     $("#resultMobList").html("");
     
     changeIndex = pageIndex;
     pageIndex = 0;
     fn_more();
     
     
     
}

$(document).ready(function(){
	getTradeAmt();
	fn_more();
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
									<li><a href="${pageContext.request.contextPath}/front/fixNAuc/fixBuyList.do" class="active">절화</a></li>
									</c:if>
									<c:if test="${yFloLoginVO ne null }">
									<li><a href="${pageContext.request.contextPath}/front/fixYAuc/fixBuyList.do">&nbsp;&nbsp;&nbsp;난&nbsp;&nbsp;&nbsp;</a></li>
									</c:if>
									<c:if test="${cFloLoginVO ne null }">
									<li><a href="${pageContext.request.contextPath}/front/fixCAuc/fixBuyList.do">관엽</a></li>
									<li><a href="${pageContext.request.contextPath}/front/fixCAuc/fixBuySubList.do">관엽-분갈이</a></li>
									</c:if>
								</ul>
							</div>
							<!-- 서브탭(E) -->

							<!-- 검색조건창(S) -->
							<form id="frm" name="searchFrm" method="post" action="${pageContext.request.contextPath}/front/fixNAuc/fixBuyList.do" >
							
							<div class="condition_box">
								<div class="search_box">
									<ul class="sb_line">
										<li>
											<p class="sb_title">경매일자</p>
											<div class="sb_data">
												<div class="date_box">
													<input type="text" class="datepicker" name="searchStartDt" id="searchStartDt" value="<c:out value="${paramMap.searchStartDt}"/>">
												</div>
												<span class="hyphen">~</span>
												<div class="date_box">
													<input type="text" class="datepicker"  name="searchEndDt" id="searchEndDt" value="<c:out value="${paramMap.searchEndDt}"/>">
												</div>
											</div>
											<div class="sb_data ml10">
												<label class="radio mr10">
													<input type="radio" name="dateRadioChange" value="0" <c:if test="${paramMap.dateRadioChange eq null or paramMap.dateRadioChange eq '0'}">checked="checked"</c:if> onclick="setSearchDateInitBuy(0);">
													<i class="rdo"></i>
													<em class="label-title">당일</em>
												</label>
												<label class="radio mr10">
													<input type="radio" name="dateRadioChange" value="1" <c:if test="${paramMap.dateRadioChange eq '1'}">checked="checked"</c:if> onclick="setSearchDateInitBuy(1);">
													<i class="rdo"></i>
													<em class="label-title">2일</em>
												</label>
												<label class="radio mr10">
													<input type="radio" name="dateRadioChange" value="2" <c:if test="${paramMap.dateRadioChange eq '2'}">checked="checked"</c:if> onclick="setSearchDateInitBuy(2);">
													<i class="rdo"></i>
													<em class="label-title">3일</em>
												</label>
												<label class="radio">
													<input type="radio" name="dateRadioChange" value="6" <c:if test="${paramMap.dateRadioChange eq '6'}">checked="checked"</c:if> onclick="setSearchDateInitBuy(6);">
													<i class="rdo"></i>
													<em class="label-title">7일</em>
												</label>
											</div>
										</li>
										<li>
											<p class="sb_title">상태</p>
											<div class="sb_data">
												<div class="sel_type_01 w150">
													<select id="searchFixState" name="searchFixState"> 
														<option value="" <c:if test="${paramMap.searchFixState eq null}">selected="selected"</c:if>>전체</option>
														<!-- <option value="3" <c:if test="${paramMap.searchFixState eq '3'}">selected="selected"</c:if>>준비</option> -->
														<option value="7" <c:if test="${paramMap.searchFixState eq '7'}">selected="selected"</c:if>>입찰</option>
														<option value="9" <c:if test="${paramMap.searchFixState eq '9'}">selected="selected"</c:if>>입찰대기</option>
														<option value="8" <c:if test="${paramMap.searchFixState eq '8'}">selected="selected"</c:if>>마감</option>
														<option value="4" <c:if test="${paramMap.searchFixState eq '4'}">selected="selected"</c:if>>완료</option>
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
							<div class="title_box mt5">
								<div class="fl">
									<h4 class="sub_tit_04">판매리스트 (<b class="totalPageCnt"></b>)</h4>
								</div>
								<div class="fr">
									<div class="sel_type_01 w150">
										<select id="jCode" name="jCode" onchange="getTradeAmt();listChange();">
											<c:forEach items="${floList}" var="floVO" varStatus="status">
												<c:if test="${floVO.bunChk eq 'N'}">
													<c:if test="${code eq null}">
														<option value="${floVO.code}" <c:if test="${nFloLoginVO.chulCd eq  floVO.code}">selected="selected"</c:if>>${floVO.name} ( ${floVO.code} )</option>
													</c:if>
													<c:if test="${code ne null}">
														<option value="${floVO.code}" <c:if test="${code eq  floVO.code}">selected="selected"</c:if>>${floVO.name} ( ${floVO.code} )</option>
													</c:if>
												</c:if>
											</c:forEach>	
										</select>
									</div>
								</div>
								<div class="title_mid">
									<p class="txt_unit dib " id="fixAmt"> 입찰금액 : 0원  / </p>
									<p class="txt_unit dib ml5" id="reqAmt"> 요청금액 : 0원  /</p> 
									<p class="txt_unit dib ml5" id="limitAmt">거래잔액 : 0원 </p>
									
								</div>
								
									
								
								
								
								
								
							</div>
							<!-- 타이틀(E) -->
							
							<!-- WEB테이블(S) -->
							<div class="table_type_01 ver2 web mt10">
								<table id="fixTable">
									<caption>info</caption>
									<colgroup>
										<col width="9.3%">
										<col width="9.3%">
										<col width="9.3%">
										<col width="9.3%">
										<col width="9.3%">
										<col width="9.3%">
										<col width="9.3%">
										<col width="8.3%">
										<col width="8.3%">
										<col width="11.3%">
										<col width="7%">
									</colgroup>
									<thead>
										<tr>
											<th>경매일자</th>
											<th>이미지</th>
											<th>품목명</th>
											<th>품종명</th>
											<th>출하자(코드)</th>
											<th>지역</th>
											<th>등급</th>
											<th>상자수</th>
											<th>총 속수량</th>
											<th>판매단가</th>
											<th>상태</th>
										</tr>
									</thead>
									<tbody id="resultWebList">
										<!-- 
										<c:forEach items="${resultList}" var="result" varStatus="status">
										<tr>
											<td class="tc">
												<p class="txt_01">${result.aucDate}</p>
											</td>
											<td class="tc pd2" onclick="fn_view('${result.fixDealSeq}');" style="cursor: pointer;">
												<div class="t_img">
													<img src="${result.thumbPath}" alt="사진_${result.pumName}">
												</div>
											</td>
											<td class="tc"><p class="txt_01">${result.mokName}</p></td>
											<td class="tc"><p class="txt_01">${result.pumName}<c:if test="${result.twinCnt ne '0'}">/${result.twinCnt}</c:if></p></td>
											<td class="tc">
												<p class="txt_01">${result.chulName}</p>
												<p class="txt_01">( ${fn:substring(result.chulCode,0,4)} - ${fn:substring(result.chulCode,4,8)} )</p>
											</td>
											<td class="tc"><p class="txt_01">${result.chulArea}</p></td>
											<td class="tc"><p class="txt_01">${result.chulLevelName}</p></td>
											<td class="tc">
												<p class="txt_01"><fmt:formatNumber value="${result.boxCnt}" pattern="#,###" /></p>
												<c:if test="${result.fixState eq '7'}">
												<div class="ip_type_01 w100p mt10">
													<input type="number" class="tr" id="bidBoxCnt${status.index}" placeholder="-" maxlength="2" oninput="maxLengthCheck(this)">
												</div>
												</c:if>
											</td>
											<td class="tc">
												<p class="txt_01"><fmt:formatNumber value="${result.sokCnt}" pattern="#,###" /></p>
												<c:if test="${result.fixState eq '7'}">
												<div class="ip_type_01 w100p mt10">
													<input type="number" class="tr" id="bidSokCnt${status.index}" placeholder="-" maxlength="4" oninput="maxLengthCheck(this)">
												</div>
												</c:if>
											</td>
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
												<c:if test="${result.fixState eq '7'}">
												<div class="ip_type_01 w100p mt10">
													<input type="number" class="tr" id="bidUnitPrice${status.index}" placeholder="-" maxlength="10" oninput="maxLengthCheck(this)">
												</div>
												</c:if>
											</td>
											<td class="tc">
												   <c:if test="${result.fixState eq '3'}">
														<p class="txt_01">준비</p>
												   </c:if>
												   <c:if test="${result.fixState eq '7'}">
														<a href="javascript:fn_wbid('${status.index}','${result.fixDealSeq}');" class="btn_type_round">입찰</a>
												   </c:if>
												   <c:if test="${result.fixState eq '8'}">
														<p class="txt_01">마감</p>
												   </c:if>
											</td>
										</tr>
										</c:forEach>
										<c:if test="${fn:length(resultList) eq 0 }">
											<tr>
												<td class="tc" colspan="11">
													데이터가 없습니다.
												</td>
											</tr>
										</c:if> -->
									</tbody>
								</table>
							</div>
							<!-- WEB테이블(E) -->

							<!-- MOBILE테이블(S) -->
							<div class="table_type_01_m mobile mt10" id="resultMobList">
								<!-- 
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
														<h4 class="sub_tit_05">[${result.aucDate}] ${result.mokName}(${result.pumName})</h4>
													</div>
													<div class="fr">
													   <c:if test="${result.fixState eq '3'}">
															<p class="txt_apply">준비</p>
													   </c:if>
													   <c:if test="${result.fixState eq '7'}">
															<p class="txt_bid">입찰</p>
													   </c:if>
													   <c:if test="${result.fixState eq '8'}">
															<p class="txt_bid">마감</p>
													   </c:if>
													</div>
												</div>
											</td>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>
												<div class="table_info pd0">
													<ul class="profile_box devide_3_7" onclick="fn_view('${result.fixDealSeq}');">
														<li class="de1">
															<img src="${result.thumbPath}" alt="사진_${result.pumName}">
														</li>
														<li class="de2">
															<p class="txt_01">출하자(코드명) : ${result.chulName} ( ${fn:substring(result.chulCode,0,4)} - ${fn:substring(result.chulCode,4,8)} )</p>
															<p class="txt_01">등급 : ${result.chulLevelName}</p>
															<p class="txt_01">지역 : ${result.chulArea}</p>
															<p class="txt_01">상자수량 : <fmt:formatNumber value="${result.boxCnt}" pattern="#,###" /></p>
															<p class="txt_01">속/분수량 : <fmt:formatNumber value="${result.sokCnt}" pattern="#,###" /></p>
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
															<p class="txt_01">판매단가 : <fmt:formatNumber value="${result.unitPrice}" pattern="#,###" /></p>
														</li>
													</ul>
													<c:if test="${result.fixState eq '7'}">
													<ul class="figure_box">
														<li>
															<p>입찰상자</p>
															<div class="ip_type_01 w100p">
																<input type="number" id="mbidBoxCnt${status.index}" class="tr" maxlength="2" oninput="maxLengthCheck(this)"><label for="mbidBoxCnt${status.index}"></label>
															</div>
														</li>
														<li>
															<p>입찰수량</p>
															<div class="ip_type_01 w100p">
																<input type="number" id="mbidSokCnt${status.index}" class="tr" maxlength="4" oninput="maxLengthCheck(this)"><label for="mbidSokCnt${status.index}"></label>
															</div>
														</li>
														<li>
															<p>구매단가</p>
															<div class="ip_type_01 w100p">
																<input type="number" id="mbidUnitPrice${status.index}" class="tr" maxlength="10" oninput="maxLengthCheck(this)"><label for="mbidUnitPrice${status.index}"></label>
															</div>
														</li>
													</ul>
													<a href="javascript:fn_mbid('${status.index}','${result.fixDealSeq}');" class="btn_table_mobile">입찰하기</a>
													</c:if>
												</div>
											</td>
										</tr>
									</tbody>
								</table>
								</c:forEach> -->
							</div>
							<!-- MOBILE테이블(E) -->

							<!-- 페이지박스(S) -->
							<%-- <div class="paging_box mt30">
								<a href="javascript:fn_more();" class="btn_search_02 ml10" id="moreBtn">더보기 <b id="currentPageCnt"><c:out value="${paramMap.pageUnit}"/></b> / <b class="totalPageCnt"></b></a>
							</div> --%>
							<!-- 페이지박스(E) -->

						</div>

						</div>
					</div>
					<!-- tab 내용01(E) --><!-- 판매(E)-->
				</div>
				<!-- sub탭(E) -->

			</div>
			<!-- sub내용(E) -->
