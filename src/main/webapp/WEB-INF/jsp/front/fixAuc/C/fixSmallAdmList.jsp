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

.date_box.flex-01{width:100%!important; vertical-align:8%;}

.table_type_01_m table tbody tr td .table_info .profile_box.devide_3_7 .de2 {
    width: 70%;
    box-sizing: border-box;
    padding: 0 10px 10px 10px;
}

</style>

<script src="${pageContext.request.contextPath}/js/fix.js"></script>

<script type="text/javascript">


var pageIndex = 1;
var pageUnit = 1000000;
var orderArray = new Array();
var replace = null;

orderArray = [false,false,false,false,false,false,false,false,false,false,false]

var arrBid = [];

/* 정렬 유지 추가 1 (S) - 채성주 [ 2021.11.17 ] */
$(document).ready(function(){
	if($("#tableOrder").val() != null){
		var tableOrder = $("#tableOrder").val().split("TD");

		if(tableOrder[0] == "sort"){
			fn_order(tableOrder[1]);	
		}else if(tableOrder[0] == "reverse"){
			orderArray[tableOrder[1]] = true;
			fn_order(tableOrder[1]);
		}
	}
	console.log("arrBid",arrBid)
	for(var i=0; i<arrBid.length; i++){
		$('select:eq('+(i+1)+')').val(arrBid[i] == null || arrBid[i].length == 0 ? 'N' : arrBid[i]);
	}
	$('#smallUpday').datepicker('setDate', 'today');
	$('#smallUpdayDel').datepicker('setDate', 'today');
	$('#smallUpdayExcel').datepicker('setDate', 'today');
})
/* 정렬 유지 추가 1 (E) */

function fn_view(fixDealSeq){
	window.location.href = "${pageContext.request.contextPath}/front/fixCAuc/fixSmallAdmView.do?fixDealSeq="+fixDealSeq;
}

function fn_reg(){
	window.location.href = "${pageContext.request.contextPath}/front/fixCAuc/fixAdmReg.do";
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
        url : "${pageContext.request.contextPath}/front/fixCAuc/fixAdmList.json",
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
        		    
           	    	html += "<tr id=\"fixTr"+resultList[i].fixDealSeq+"\">                                                        ";
           	    	html += "<input type=\"hidden\" id=\"fixState"+resultList[i].fixDealSeq+"\" value=\""+resultList[i].fixState+"\"/>";
           	    	html += "<td class=\"tc\"><input type=\"checkbox\" name=\"fixCheck\" value=\""+resultList[i].fixDealSeq+"\" id=\"t"+resultList[i].fixDealSeq+"\" onchange=\"fn_check(this, '"+resultList[i].fixDealSeq+"');\"><label for=\"t"+resultList[i].fixDealSeq+"\"><span class=\"mr0\"></span></label></td>";
           	    	html += "	<td class=\"tc td_add\" style=\"cursor:pointer;\"><p class=\"txt_01\">"+resultList[i].upNo+"</p></td>                                                             ";
           	    	html += "	<td class=\"tc td_add\" style=\"cursor:pointer;\" onclick=\"fn_view('"+resultList[i].fixDealSeq+"');\"><p class=\"txt_01\">"+resultList[i].insertDate+"</p></td>                                                             ";
           	    	// html += "	<td class=\"tc td_add\" style=\"cursor:pointer;\" onclick=\"fn_view('"+resultList[i].fixDealSeq+"');\"><p class=\"txt_01\">"+resultList[i].chulDate+"</p></td>                                                             ";
					html += "	<td class=\"tc td_add\" style=\"cursor:pointer;\" onclick=\"fn_view('"+resultList[i].fixDealSeq+"');\"><p class=\"txt_01\">"+resultList[i].mokName+"</p></td>                                                                ";
					html += "	<td class=\"tc td_add\" style=\"cursor:pointer;\" onclick=\"fn_view('"+resultList[i].fixDealSeq+"');\"><p class=\"txt_01\">"+pumName+"</p></td>                                                                ";
					html += "	<td class=\"tc td_add\" style=\"cursor:pointer;\" onclick=\"fn_view('"+resultList[i].fixDealSeq+"');\">                                                                                                              ";
					html += "		<p class=\"txt_01\">"+resultList[i].chulName+"</p> ";
					html += "		<p class=\"txt_01\">( "+resultList[i].chulCode.substring(0,4)+" - "+resultList[i].chulCode.substring(4,8)+" )</p> ";
					html += "	</td>                                                                                                                          ";
					html += "	<td class=\"tc td_add\" style=\"cursor:pointer;\" onclick=\"fn_view('"+resultList[i].fixDealSeq+"');\"><p class=\"txt_01\">"+resultList[i].chulLevelName+"</p></td>                                                          ";
					html += "	<td class=\"tc td_add\" style=\"cursor:pointer;\" onclick=\"fn_view('"+resultList[i].fixDealSeq+"');\"><p class=\"txt_01\">"+comma(resultList[i].boxCnt)+"</p></td>                ";
					html += "	<td class=\"tc td_add\" style=\"cursor:pointer;\" onclick=\"fn_view('"+resultList[i].fixDealSeq+"');\"><p class=\"txt_01\">"+comma(resultList[i].sokCnt)+"</p></td>                ";
					html += "	<td class=\"tc td_add\" style=\"cursor:pointer;\" onclick=\"fn_view('"+resultList[i].fixDealSeq+"');\">                                                                                                              ";
					html += "		<p class=\"txt_01\">";
					if(resultList[i].dealType == "L"){
						html += "최저가 ";	
					}else if (resultList[i].dealType == "W"){
						html += "희망가";	
					}else if (resultList[i].dealType == "F"){
						html += "정가";	
					}
					html += "</p>                                                                                                                       ";
					html += "		<p class=\"txt_01\" style=\"cursor:pointer;\" onclick=\"fn_view('"+resultList[i].fixDealSeq+"');\">";
					html += "("+comma(resultList[i].unitPrice)+")";
					html += "</p>                                                                                                                       ";
					html += "	</td>                                                                                                                          ";
					html += "	<td class=\"tc td_add\">                                                                                                              ";
					html += "		<div class=\"date_box\">";
					if(resultList[i].aucDate == "-"){
						html += "<input type=\"text\" class=\"datepicker\" value=\""+resultList[i].chulDate+"\" id=\"aucDate"+resultList[i].fixDealSeq+"\" onchange=\"fn_aucDate('"+resultList[i].fixDealSeq+"')\"> ";	
					}else{
						html += "<input type=\"text\" class=\"datepicker\" value=\""+resultList[i].aucDate+"\" id=\"aucDate"+resultList[i].fixDealSeq+"\" onchange=\"fn_aucDate('"+resultList[i].fixDealSeq+"')\"> ";
					}
					html += "</div>                                                                                                                       ";
					html += "	</td>                                                                                                                          ";
					html += "	<td class=\"tc td_add\" style=\"cursor:pointer;\" onclick=\"fn_view('"+resultList[i].fixDealSeq+"');\">                                                                                                              ";
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
					mhtml += "<div class=\"fr\"><input type=\"checkbox\" id=\"tm"+resultList[i].fixDealSeq+"\" name=\"mfixCheck\" onchange=\"fn_mcheck(this, '"+resultList[i].fixDealSeq+"');\"><label for=\"tm"+resultList[i].fixDealSeq+"\"><span class=\"mr10\"></span></label></div>";
					mhtml += "				</div>                                                                                                                                    ";
					mhtml += "			</td>                                                                                                                                         ";
					mhtml += "		</tr>                                                                                                                                             ";
					mhtml += "	</thead>                                                                                                                                              ";
					mhtml += "	<tbody>                                                                       ";
					mhtml += "		<tr>                                                                                                                                              ";
					mhtml += "			<td>                                                                                                                                          ";
					mhtml += "				<div class=\"table_info\">                                                                                                                ";
					mhtml += "					<p class=\"txt_01\">출하자(코드)명 : "+resultList[i].chulName+" ( "+resultList[i].chulCode.substring(0,4)+" - "+resultList[i].chulCode.substring(4,8)+" )</p> ";
					mhtml += "					<ul class=\"profile_box\" onclick=\"fn_view('"+resultList[i].fixDealSeq+"');\" style=\"cursor: pointer;\">                                                                                                            ";
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
					mhtml += "							<p class=\"txt_01\">출하일자 :                                                                                                    ";
					mhtml += "								"+resultList[i].chulDate+"                                                                                                         ";
					mhtml += "							</p>                                                                                                                          ";
					mhtml += "						</li>                                                                                                                             ";
					mhtml += "						<li>                                                                                                                              ";
					mhtml += "							<p class=\"txt_01\">지역 : "+resultList[i].chulArea+"</p>                                                                               ";
					mhtml += "							<p class=\"txt_01\">속/분수량 : "+comma(resultList[i].sokCnt)+"</p>                             ";
					mhtml += "							<p class=\"txt_01\">판매단가 : "+comma(resultList[i].unitPrice)+"</p>                           ";
					mhtml += "						</li>                                                                                                                             ";
					mhtml += "					</ul>                                                                                                                                 ";
					mhtml += "					<ul class=\"figure_box\">                                                                                                            ";
					mhtml += "						<li><p>경매일자</p>                                                                                                                              ";
					mhtml += "		<div class=\"date_box\">";
					if(resultList[i].aucDate == "-"){
						mhtml += "<input type=\"text\" class=\"datepicker\" value=\""+resultList[i].chulDate+"\" id=\"maucDate"+resultList[i].fixDealSeq+"\" onchange=\"fn_maucDate('"+resultList[i].fixDealSeq+"')\"> ";	
					}else{
						mhtml += "<input type=\"text\" class=\"datepicker\" value=\""+resultList[i].aucDate+"\" id=\"maucDate"+resultList[i].fixDealSeq+"\" onchange=\"fn_maucDate('"+resultList[i].fixDealSeq+"')\"> ";
					}
					mhtml += "</div>                                                                                                                       ";
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
		/* 정렬 유지 추가 2 (S) - 채성주 [ 2021.11.17 ] */
		$("#tableOrder").val("sortTD" + index);
		/* 정렬 유지 추가 2 (E) */
	}else{
		reverseTD(index);
		orderArray[index] = false;
		/* 정렬 유지 추가 3 (S) - 채성주 [ 2021.11.17 ] */
		$("#tableOrder").val("reverseTD" + index);
		/* 정렬 유지 추가 3 (E) */
	}
	
	/* 정렬 유지 추가 4 (S) - 채성주 [ 2021.11.17 ] */
	$.ajax({
		data: {
				searchName: "fixAdmListSearch",
				tableOrder: $("#tableOrder").val()
			},
		type: "POST",
		url: "${pageContext.request.contextPath}/front/fixCAuc/tableOrder.json"
	})
	/* 정렬 유지 추가 4 (E) */
}

function sortTD(index){
	replace.ascending(index);
}

function reverseTD(index){
	replace.descending(index);
}


function fn_aucDate(fixDealSeq){
	
	$("#maucDate"+fixDealSeq).val($("#aucDate"+fixDealSeq).val());
}

function fn_maucDate(fixDealSeq){
	
	$("#aucDate"+fixDealSeq).val($("#maucDate"+fixDealSeq).val());
}


function fn_check(obj, fixDealSeq){
	
	if(obj.checked){
		$("#tm"+fixDealSeq).prop("checked",true);
	}else{
		$("#tm"+fixDealSeq).prop("checked",false);
	}
}

function fn_mcheck(obj, fixDealSeq){
	console.log(obj.id);
	if(obj.checked){
		$("#t"+fixDealSeq).prop("checked",true);
	}else{
		$("#t"+fixDealSeq).prop("checked",false);
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

function fn_apply(){
	var checkedArray = new Array();
	var aucDateArray = new Array();
	
	$("input[name=fixCheck]:checked").each(function(index){
		var checkedValue = this.value;
		var fixState = $("#fixState"+checkedValue).val();
		if(fixState == 1){
			checkedArray.push(checkedValue);
			aucDateArray.push($("#aucDate"+checkedValue).val());
		}
		
	});
	
	if(checkedArray.length == 0){
		alert("체크리스트중 신청상태의 정가수의매매가 없습니다.");
	}else{
		$.ajax({
			data:{
				checkedArray: checkedArray,
				aucDateArray: aucDateArray
		        },
	        type : "POST",
	        url : "${pageContext.request.contextPath}/front/fixCAuc/fixSmallAdmApply.json",
	        success : function(data){
	             alert("신청된 정가수의매매 " + data.result + "건 승인처리되었습니다.");
	             // location.reload(true);
	             /* 정렬 유지 수정 1 (S) - 채성주 [ 2021.11.26 ] */
	             location.href = "${pageContext.request.contextPath}/front/fixCAuc/fixSmallAdmList.do?history=true";
	             /* 정렬 유지 수정 1 (E) */
	        }
	    });
	}
	
}

function fn_compCancel(){
	var checkedArray = new Array();
	var aucDateArray = new Array();
	
	$("input[name=fixCheck]:checked").each(function(index){
		var checkedValue = this.value;
		var fixState = $("#fixState"+checkedValue).val();
		if(fixState == 4 || fixState == 5 || fixState == 6){
			checkedArray.push(checkedValue);
			aucDateArray.push($("#aucDate"+checkedValue).val());
		}
		
	});
	
	if(checkedArray.length == 0){
		alert("체크리스트중 완료상태의 정가수의매매가 없습니다.");
	}else{
		$.ajax({
			data:{
				checkedArray: checkedArray,
				aucDateArray: aucDateArray
		        },
	        type : "POST",
	        url : "${pageContext.request.contextPath}/front/fixCAuc/fixSmallAdmApply.json",
	        success : function(data){
	             alert("완료된 정가수의매매 " + data.result + "건 완료취소 처리되었습니다.");
	             // location.reload(true);
	             /* 정렬 유지 수정 2 (S) - 채성주 [ 2021.11.26 ] */
	             location.href = "${pageContext.request.contextPath}/front/fixCAuc/fixSmallAdmList.do?history=true";
	             /* 정렬 유지 수정 2 (E) */
	        }
	    });
	}
	
}


function fn_comp(){
	var checkedArray = new Array();
	var aucDateArray = new Array();
	
	$("input[name=fixCheck]:checked").each(function(index){
		var checkedValue = this.value;
		var fixState = $("#fixState"+checkedValue).val();
		var vrPanDay = $("#vrPanDay"+checkedValue).val();
		var vrPanNo = $("#vrPanNo"+checkedValue).val();
		if(fixState == 8 && vrPanDay == "" && vrPanNo == ""){		
			checkedArray.push(checkedValue);
			aucDateArray.push($("#aucDate"+checkedValue).val());
		}
		
	});
	
	if(checkedArray.length == 0){
		alert("체크리스트중 마감상태의 정가수의매매가 없습니다.");
	}else{
		$.ajax({
			data:{
				checkedArray: checkedArray,
				aucDateArray: aucDateArray
		        },
	        type : "POST",
	        url : "${pageContext.request.contextPath}/front/fixCAuc/fixSmallAdmComp.json",
	        success : function(data){
	             alert("마감된 정가수의매매 " + data.result + "건 완료처리되었습니다.");
	             // location.reload(true);
	             /* 정렬 유지 수정 3 (S) - 채성주 [ 2021.11.26 ] */
	             location.href = "${pageContext.request.contextPath}/front/fixCAuc/fixSmallAdmList.do?history=true";
	             /* 정렬 유지 수정 3 (E) */
	        }
	    });
	}
	
}

function fn_appCancel(){
	//fixAdmAppCancel
	
	var checkedArray = new Array();
	var aucDateArray = new Array();
	
	$("input[name=fixCheck]:checked").each(function(index){
		var checkedValue = this.value;
		var fixState = $("#fixState"+checkedValue).val();
		var vrPanDay = $("#vrPanDay"+checkedValue).val();
		var vrPanNo = $("#vrPanNo"+checkedValue).val();
		if((fixState == 3 || fixState == 7 || fixState == 8) && vrPanDay == "" && vrPanNo == ""){
			checkedArray.push(checkedValue);
			aucDateArray.push($("#aucDate"+checkedValue).val());
		}
	});
	
	if(checkedArray.length == 0){
		alert("체크리스트중 승인(준비,마감,입찰)상태의 정가수의매매가 없습니다.");
	}else{
		$.ajax({
			data:{
				checkedArray: checkedArray,
				aucDateArray: aucDateArray
		        },
	        type : "POST",
	        url : "${pageContext.request.contextPath}/front/fixCAuc/fixSmallAdmAppCancel.json",
	        success : function(data){
	             alert("승인된 정가수의매매 " + data.result + "건 승인취소 처리되었습니다.");
	             // location.reload(true);
	             /* 정렬 유지 수정 4 (S) - 채성주 [ 2021.11.26 ] */
	             location.href = "${pageContext.request.contextPath}/front/fixCAuc/fixSmallAdmList.do?history=true";
	             /* 정렬 유지 수정 4 (E) */
	        }
	    });
	}
	
}


/*
 * 이현승
 * 2021.11.19
 */
function fn_sms_select(codeType){
	
	var checkedArray = new Array();
	var banArray = new Array();
	var bidStrArray = new Array();
	var dealCompArray = new Array();
	var chekCnt = 0;	
	var sendCnt = 0;
	
	var html = "";
	
	$("input[name=fixCheck]:checked").each(function(index){
		var checkedValue = this.value;
		var fixState = $("#fixState"+checkedValue).val();
		var codeTypeChek = codeType;
		
		/* if(fixState == 2){
			//반려문자 : 출하자 대상
			banArray.push(checkedValue);
		}else if(fixState == 7){
			//입찰시작 문자 : 출하자, 중도매인 단체
			bidStrArray.push(checkedValue);
		}else if(fixState == 4 || fixState == 5 || fixState == 6){
			//거래 완료 문자 (완료, 유찰, 부분유찰) : 츨하자, 중도매인
			dealCompArray.push(checkedValue);
		} */
		
				
		//중도매인 입찰관리 안내문자
		bidStrArray.push(checkedValue);
				
		
		chekCnt++;
		
	});
	
	
	if (codeType == "2") { //중도매인
		
		html += "※ 입찰관리 중도매인 안내 문자※ ";
		html += "\n--------------------------------------------------------------------\n";
	
		sendCnt += bidStrArray.length;
		
		
/* 		//완료 4, 유찰 5, 부분유찰 6
		if(dealCompArray.length > 0){
			sendCnt += dealCompArray.length;
		} */
		
	}
	
	
	
	 if (sendCnt == 0) {
		
		html += "\n 문자발송 건이 없습니다. \n";
		
		alert(html);
		
		return false;
		
	}else{
		
		html += "\n 총 발송 건수 : "+ sendCnt + " 건" ;
		
			
		html += "\n\n 중도매인 문자발송 진행하시겠습니까? \n";
		
	}
	
	
	if(confirm(html)){
		$.ajax({
			data:{
				bidStrArray: bidStrArray,
				codeType : codeType,
				bunChk : "C"
		        },
	        type : "POST",
	        url : "${pageContext.request.contextPath}/front/fixAucMsg/fixSmallSmsProc.json",
	        success : function(data){
	            console.log(data);
	            alert("문자가 발송되었습니다.");
	        }
	    });
	}
	
}


/*function fn_sms(){
	
	var checkedArray = new Array();
	var banArray = new Array();
	var applyArray = new Array();
	var bidPreArray = new Array();
	var bidStrArray = new Array();
	var dealCompArray = new Array();
	var html = "";
	
	$("input[name=fixCheck]:checked").each(function(index){
		var checkedValue = this.value;
		var fixState = $("#fixState"+checkedValue).val();
		
		if(fixState == 2){
			//반려문자 : 출하자 대상
			banArray.push(checkedValue);
		}else if(fixState == 3){
			//입찰시작 문자 : 화훼부류 전체 중도매인
			//applyArray.push(checkedValue);
			//bidPreArray.push(checkedValue);
		}else if(fixState == 7){
			//입찰시작 문자 : 화훼부류 전체 중도매인
			bidStrArray.push(checkedValue);
		}else if(fixState == 4 || fixState == 5 || fixState == 6){
			//거래 완료 문자 : 중도매인 대상
			dealCompArray.push(checkedValue);
		}
		
	});
	
	if(banArray.length > 0){
		//반려문자 : 출하자 대상
		html += "\n * 반려문자 : 출하자 개별 발송 "+banArray.length+"건\n";
		//var text = "ex) 신청한 2020-08-18 호접란(화이트) 정가매매가 반려되었습니다. \n 반려사유 : ---- \n";
		//html += text;
	}
	
	if(applyArray.length > 0){
		//승인문자 : 출하자 대상
		html += "\n * 입찰시작전 문자 : 출하자 대상  개별발송 "+applyArray.length+"건\n";
		//var text = "ex) 2020-08-18 정가매매 입찰이 시작되었습니다. \n 시작시간 : 10:00 ~ 11:00 \n --입찰 시작 매매건수 : "+applyArray.length+"\n ";
		
		//html += text;
	}
	
	if(bidPreArray.length > 0){
		//입찰시작전 문자 : 중도매인전체
		html += "\n * 입찰시작전 문자 : 출하자 개별 , 중도매인 단체 발송 "+bidPreArray.length+"건\n";
		//var text = "ex) 2020-08-18 정가매매 입찰이 시작전 알림문자입니다. \n 시작시간 : 10:00 ~ 11:00 \n --입찰 시작 매매건수 : "+bidPreArray.length+"\n ";
		
		//html += text;
	}
	
	if(bidStrArray.length > 0){
		//입찰시작문자 : 중도매인전체
		html += "\n * 입찰시작문자 : 출하자 개별발송 , 화훼구분별 중도매인 단체 발송 "+bidStrArray.length+"건\n";
		//var text = "ex) 2020-08-18 정가매매 입찰이 시작되었습니다. \n --입찰 시작 매매건수 : "+bidStrArray.length+"\n ";
		
		//html += text;
	}
	
	if(dealCompArray.length > 0){
		//완료문자 : 입찰 중도매인 단체발송
		html += "\n * 거래완료문자 : 출하자 개별, 응찰 중도매인 단체 발송 "+dealCompArray.length+"건\n";
		
		//var text = "ex) 낙찰자 : 2020-08-20 홍길동/장미/레드홀  낙찰되었습니다. \n";
		//text += "ex) 패찰자 : 2020-08-20 홍길동/장미/레드홀  패찰되었습니다. \n";
		//html += text;
	}
	
	if(html == ''){
		alert("문자발송 할 상태가 없습니다. \n 발송대상상태(승인, 반려, 입찰, 완료)");
		return false;
	}
	
	
	if(confirm(html)){
		$.ajax({
			data:{
				banArray: banArray,
				applyArray: applyArray,
				bidPreArray: bidPreArray,
				bidStrArray: bidStrArray,
				dealCompArray: dealCompArray
		        },
	        type : "POST",
	        url : "${pageContext.request.contextPath}/front/fixCAuc/fixSmsProc.json",
	        success : function(data){
	            console.log(data);
	            alert("문자가 발송되었습니다.");
	        }
	    });
	}
	
} */


function fn_fix_small() {
	layer_popup_adm("#layerSampleC");
}

function getFixSmall() {

	if(confirm("재상장 내역을 불러오시겠습니까?")){
		
		var beforeSmallUpDay = $("#smallUpday").val();
		
		$.ajax({
			data:{
				upDay : beforeSmallUpDay
		        },
	        type : "POST",
	        url : "${pageContext.request.contextPath}/front/fixCAuc/fixSmallAdmSangJang.json",
	        success : function(data){
	        	
	        	if(data.errorCode != null) {
	        		
	        		var errorMessage = "";
	        		
	        		//error
	        		if(data.errorCode == '9999') {
						errorMessage = "[재상장 내역 불러오기] 기능에 대한 권한이 없습니다.";	        			
	        		}
	        		if(data.errorCode == '9998') {
						errorMessage = "[재상장 내역 불러오기] 재상장 일자를 입력해주세요.";	        			
	        		}
	        		if(data.errorCode == '9997') {
						errorMessage = "[재상장 내역 불러오기]는 금요일만 가능합니다.";	        			
	        		}
	        		if(data.errorCode == '9996') {
						errorMessage = "[재상장 내역 불러오기] 이미 불러온 재상장 내역입니다.";	        			
	        		}
	        		
	        		alert(errorMessage);
	        		return;
	        		
	        	}else {
	        		
	        		if(data.result == 0) {
		        		alert("불러 올 재상장 내역이 없습니다.");
		        		return;
		        	}
	        		
	        		
	        		$.ajax({
	        			data:{
	        				upDay : beforeSmallUpDay
	        		        },
	        	        type : "POST",
	        	        url : "${pageContext.request.contextPath}/front/fixCAuc/fixSmallAdmSangJangRecord.json",
	        	        success : function(data){}
	        	    });
		        	
		            alert("[재상장 내역 불러오기] "+data.result+" 건을 불러오기에 성공하였습니다.");
		            
		            $("#searchStartDt").val(beforeSmallUpDay);
		            fn_search();
	        	}
	        }
	    });

	}
	
}


function fn_fix_small_del() {
	layer_popup_adm("#layerSampleCDel");
}


function getFixSmall_del() {
	
	var beforeSmallUpDay = $("#smallUpdayDel").val();

	if(confirm( "해당 일자("+beforeSmallUpDay +") 내역을 삭제하시겠습니까?")){
		
		$.ajax({
			data:{
				upDay : beforeSmallUpDay
		        },
	        type : "POST",
	        url : "${pageContext.request.contextPath}/front/fixCAuc/fixSmallAdmSangJangDel.json",
	        success : function(data){
	        	
	        	if(data.errorCode != null) {
	        		
	        		var errorMessage = "";
	        		
	        		//error
	        		if(data.errorCode == '9999') {
						errorMessage = "[재상장 내역 삭제] 기능에 대한 권한이 없습니다.";	        			
	        		}
	        		if(data.errorCode == '9998') {
						errorMessage = "[재상장 내역 삭제] 재상장 일자를 입력해주세요.";	        			
	        		}
	        		if(data.errorCode == '9997') {
						errorMessage = "[재상장 내역 삭제]는 금요일만 가능합니다.";	        			
	        		}
	        		if(data.errorCode == '9996') {
						errorMessage = "[재상장 내역 삭제] 이미 삭제한 재상장 내역입니다.";	        			
	        		}
	        		
	        		alert(errorMessage);
	        		return;
	        		
	        	}else {
	        		
	        		if(data.result == 0) {
		        		alert("불러 올 재상장 내역이 없습니다.");
		        		return;
		        	}
		        	
		            alert("[재상장 내역 삭제] "+data.result+" 삭제를 성공하였습니다.");
		            
		            $("#searchStartDt").val(beforeSmallUpDay);
		            fn_search();
	        	}
	        }
	    });
	}
	
}


function fn_update(fixDealSeq){
	window.location.href = "${pageContext.request.contextPath}/front/fixCAuc/fixSmallAdmUpt.do?fixDealSeq="+fixDealSeq;
}


function loadImage(fixDealSeq,fileCnt){
	if(fileCnt>=10){
		alert("사진 첨부 파일이 10개를 초과하였습니다. 사진 삭제 후 추가 해주세요");
		fn_update(fixDealSeq);
		return false;
	}
	
	$("#frm_"+ fixDealSeq).submit();
	
}

function fn_fix_small_excel() {
	layer_popup_adm("#layerSampleCExcel");
}


function getFixSmall_excel() {
	var smallUpDay = $("#smallUpdayExcel").val();
	window.location.href = "${pageContext.request.contextPath}/front/fixCAuc/fixSmallListExcell.do?upDay="+smallUpDay;
}

function fn_onBidSuc(event,fixDealSeq){
	console.log(event,fixDealSeq)	
	event = event || window.event;
	var keyID = (event.which) ? event.which : event.keyCode;
	//if (keyID == 13) {
		
		if(confirm("온라인낙찰여부를 입력하시겠습니까?")){
			$.ajax({
				data:{
					fixDealSeq : fixDealSeq,
					bidSuccess : event.target.value,
					flag : 'bid'
					},
				type : "POST",
				url : "${pageContext.request.contextPath}/front/fixCAuc/fixSmallAdmStdPrice.json",
				success : function(data){
					if(data.result == 0){
						alert("온라인낙찰여부가 입력되었습니다.");
						location.reload(true);
					}else{
						alert("온라인낙찰여부 입력에 실패하였습니다.");
					}
				}
			});
		}
	//} else {
		//console.log("not enter");
	//}
}


function inptStdPrice2(event, fixDealSeq){
	console.log(event,fixDealSeq)	
	event = event || window.event;
	var stdPrice = $.trim(event.target.value.toLocaleString());
	var keyID = (event.which) ? event.which : event.keyCode;
	//if (keyID == 13) {
		if(stdPrice == ""){
			alert("기준가를 입력해주세요.");
			return false;
		}
		if (isNaN(stdPrice)) {
			alert("기준가는 숫자만 입력 가능합니다.");
			event.target.value = '';
			return false;
		}
		if(confirm("기준가를 입력하시겠습니까?")){
			$.ajax({
				data:{
					fixDealSeq : fixDealSeq,
					stdPrice : stdPrice,
					flag : 'std'
					},
				type : "POST",
				url : "${pageContext.request.contextPath}/front/fixCAuc/fixSmallAdmStdPrice.json",
				success : function(data){
					if(data.result == 0){
						alert("기준가가 입력되었습니다.");
						location.reload(true);
					}else{
						alert("기준가 입력에 실패하였습니다.");
					}
				}
			});
		}
	//} else {
		//console.log("not enter");
	//}
	
}

function inptStdPrice(event, fixDealSeq){
	console.log(event,fixDealSeq)	
	event = event || window.event;
	var stdPrice = $.trim(event.target.value.toLocaleString());
	var keyID = (event.which) ? event.which : event.keyCode;
	if (keyID == 13) {
		if(stdPrice == ""){
			alert("기준가를 입력해주세요.");
			return false;
		}
		if (isNaN(stdPrice)) {
			alert("기준가는 숫자만 입력 가능합니다.");
			event.target.value = '';
			return false;
		}
		if(confirm("기준가를 입력하시겠습니까?")){
			$.ajax({
				data:{
					fixDealSeq : fixDealSeq,
					stdPrice : stdPrice,
					flag : 'std'
					},
				type : "POST",
				url : "${pageContext.request.contextPath}/front/fixCAuc/fixSmallAdmStdPrice.json",
				success : function(data){
					if(data.result == 0){
						alert("기준가가 입력되었습니다.");
						location.reload(true);
					}else{
						alert("기준가 입력에 실패하였습니다.");
					}
				}
			});
		}
	} else {
		console.log("not enter");
		return false;
	}
	
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
									<c:if test="${cFloLoginVO ne null }">
									<li><a href="${pageContext.request.contextPath}/front/fixCAuc/fixSmallAdmList.do" class="active">관엽</a></li>
									</c:if>
									<!--  
									<c:if test="${yFloLoginVO ne null }">
									<li><a href="${pageContext.request.contextPath}/front/fixYAuc/fixAdmList.do">&nbsp;&nbsp;&nbsp;난&nbsp;&nbsp;&nbsp;</a></li>
									</c:if>
									<c:if test="${cFloLoginVO ne null }">
									<li><a href="${pageContext.request.contextPath}/front/fixCAuc/fixAdmList.do">관엽</a></li>
									<li><a href="${pageContext.request.contextPath}/front/fixCAuc/fixAdmSubList.do">관엽-분갈이</a></li>
									</c:if>
									-->
								</ul>
							</div>
							<!-- 서브탭(E) -->

							<!-- 검색조건창(S) -->
							<!-- 정렬 유지 수정 5 (S) - 채성주 [ 2021.11.26 ] -->
							<form id="frm" name="searchFrm" method="get" action="${pageContext.request.contextPath}/front/fixCAuc/fixSmallAdmList.do" >
							<!-- 정렬 유지 수정 5 (E) -->
							
							<!-- 정렬 유지 추가 5 (S) - 채성주 [ 2021.11.17 ] -->
							<input type="hidden" id="tableOrder" name="tableOrder" value="<c:out value="${paramMap.tableOrder}"/>">
							<!-- 정렬 유지 추가 5 (E) -->
							
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
														<%-- <option value="1" <c:if test="${paramMap.searchFixState eq '1'}">selected="selected"</c:if>>신청</option> --%>
														<!-- <option value="3" <c:if test="${paramMap.searchFixState eq '3'}">selected="selected"</c:if>>준비</option> -->
														<option value="7" <c:if test="${paramMap.searchFixState eq '7'}">selected="selected"</c:if>>입찰</option>
														<option value="9" <c:if test="${paramMap.searchFixState eq '9'}">selected="selected"</c:if>>입찰대기</option>
														<option value="8" <c:if test="${paramMap.searchFixState eq '8'}">selected="selected"</c:if>>마감</option>
														<%-- <option value="2" <c:if test="${paramMap.searchFixState eq '2'}">selected="selected"</c:if>>반려</option> --%>
														<option value="4" <c:if test="${paramMap.searchFixState eq '4'}">selected="selected"</c:if>>완료</option>
														<option value="5" <c:if test="${paramMap.searchFixState eq '5'}">selected="selected"</c:if>>유찰</option>
														<%-- <option value="6" <c:if test="${paramMap.searchFixState eq '6'}">selected="selected"</c:if>>부분유찰</option> --%>
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
								<!-- <div class="fr"> 
									<p class="txt_unit">판매등록시간 : ${regTimeVO.strTime} ~ ${regTimeVO.endTime} (${regTimeVO.dayKr})
										
										 
										<a href="javascript:fn_reg();" class="btn_type_01 gray ml10">등록</a>
										
										<a href="javascript:fn_fix_small_excel();" class="btn_type_01 gray ml10">엑셀 다운로드</a>
										<a href="javascript:fn_fix_small_del();" class="btn_type_01 gray ml10">재상장 내역 삭제</a>
										<a href="javascript:fn_fix_small();" class="btn_type_01 gray ml10">재상장 내역 불러오기</a>
										
									</p>
								</div> -->
								
							</div>
							<!-- 타이틀(E) -->
							
							<!-- 요청관리 버튼(7개)(S) -->
							<div class="btn-tab-new mt20 d6">
								<ul>
									<!-- <li class="btn-chk-icon agree"><a href="javascript:fn_apply();"><i><img src="/yfmc/img/btn-new-01.png" alt="승인"></i><p>승인</p></a></li>승인 -->				
									<!-- <li class="btn-chk-icon a-cancel"><a href="javascript:fn_appCancel();"><i><img src="/yfmc/img/btn-new-02.png" alt="승인취소"></i><p>승인취소</p></a></li>승인취소 -->				
									<li class="btn-chk-icon complete"><a href="javascript:fn_comp();"><i><img src="/yfmc/img/btn-new-03.png" alt="완료"></i><p>완료</p></a></li><!--완료-->				
									<li class="btn-chk-icon c-cancel"><a href="javascript:fn_compCancel();"><i><img src="/yfmc/img/btn-new-04.png" alt="완료취소"></i><p>완료취소</p></a></li><!--완료취소-->				
									<li class="btn-chk-icon mid-message"><a href="javascript:fn_sms_select('2');"><span>중도매인</span><i><img src="/yfmc/img/btn-new-05.png" alt="중도매인문자발송"></i><p>문자발송</p></a></li><!-- 문자발송(중도매인) -->
									<li class="btn-chk-icon no-chk"><a href="javascript:fn_fix_small_excel();"><i><img src="/yfmc/img/btn-new-09.png" alt="엑셀 다운로드"></i><p>엑셀<br>다운로드</p></a></li>
									<li class="btn-chk-icon no-chk"><a href="javascript:fn_fix_small_del();"><i><img src="/yfmc/img/btn-new-11.png" alt="재상장 내역 삭제"></i><p>재상장 내역 삭제</p></a></li>
									<li class="btn-chk-icon no-chk txt_btn"><a href="javascript:fn_fix_small();"><i><img src="/yfmc/img/btn-new-10.png" alt="재상장 내역 불러오기"></i><p>재상장 내역 불러오기</p></a></li>			
								</ul>
							</div>
							<!-- 요청관리 버튼(7개)(E) -->
							
							<!-- WEB테이블(S) -->
							<div class="table_type_04 mt20 overflow_a">
								<table id="fixTable">
									<caption>info</caption>
									<colgroup>
										<col style="width:36px;">
										<col style="width:64px;">
										<col style="width:50px;">
										<col style="width:50px;">
										<col style="width:60px;">
										<col style="width:50px;">
										<col style="width:64px;">
										<col style="width:66px;">
										<col style="width:66px;">
										<col style="width:86px;">
										<col style="width:56px;">
										<col style="width:56px;">
										<col style="width:56px;">
										<col style="width:66px;">
										<col style="width:86px;">
										<col style="width:50px;">
									</colgroup>
									<thead>
										<tr>
											<th><input type="checkbox" id="allCheck" name="allCheck" onchange="fn_allCheck(this);"><label for="allCheck"><span class="mr0"></span></label></th>
											<th style="cursor: pointer;" onclick="fn_order(1);">상장번호</th>
											<th style="cursor: pointer;" onclick="fn_order(4);">품목명</th>
											<th style="cursor: pointer;" onclick="fn_order(5);">품종명</th>
											<th style="cursor: pointer;" onclick="fn_order(11);">사진</th>
											<th style="cursor: pointer;" onclick="fn_order(11);">상태</th>
											<th style="cursor: pointer;" onclick="fn_order(1);">신청일자</th>
											<!-- <th style="cursor: pointer;" onclick="fn_order(2);">출하일자</th> -->
											<th style="cursor: pointer;" onclick="fn_order(2);">이미지</th>
											<th style="cursor: pointer;" onclick="fn_order(2);">출하자명</th>
											<th style="cursor: pointer;" onclick="fn_order(3);">출하자코드</th>
											<th style="cursor: pointer;" onclick="fn_order(6);">등급</th>
											<th style="cursor: pointer;" onclick="fn_order(7);">상자수</th>
											<th style="cursor: pointer;" onclick="fn_order(8);">속수량</th>
											<th>기준가</th>
											<th>경매일자</th>
											<th>온라인<br>낙찰</th>
										</tr>
									</thead>
									<tbody id="resultWebList">
										<c:forEach items="${resultList}" var="result" varStatus="status">
											<tr id="fixTr${result.fixDealSeq}">
												<input type="hidden" id="fixState${result.fixDealSeq}" value="${result.fixState}"/>
												<td class="tc"><input type="checkbox" id="t${result.fixDealSeq}" name="fixCheck" value="${result.fixDealSeq}" onchange="fn_check(this, '${result.fixDealSeq}');"><label for="t${result.fixDealSeq}"><span class="mr0"></span></label></td>
												<td class="tc" style="cursor:pointer"><p class="txt_01">${result.upNo}</p></td>
												<td class="tc" style="cursor:pointer" onclick="<c:if test="${empty result.vrPanDay and empty result.vrPanNo}">fn_view('${result.fixDealSeq}');</c:if>"><p class="txt_01">${result.mokName}</p></td>
												<td class="tc" style="cursor:pointer" onclick="<c:if test="${empty result.vrPanDay and empty result.vrPanNo}">fn_view('${result.fixDealSeq}');</c:if>"><p class="txt_01">${result.pumName}</p><c:if test="${result.twinCnt ne '0'}">/${result.twinCnt}</c:if></td>
												<td class="tc">
													<c:if test="${result.fixState eq '1' or result.fixState eq '2' or result.fixState eq '3' or result.fixState eq '7'}">
														<c:if test="${empty result.vrPanDay and empty result.vrPanNo}">
															<c:if test="${result.fileCnt ne 0}">
															 <div style="padding: 5px;">
															 	${result.fileCnt}개 
															 </div>
															</c:if>
															  
															<div class="upload-btn_wrap" style="width: 50px; height:40px;">
															<a href="#" class="btn_type_round btn-popup">
															  +
															</a>
															<form id="frm_${result.fixDealSeq}" name="frm" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/front/fixCAuc/fixSmallAdmUptProc.do" >
																<input type="hidden" name="fixDealSeq" value="${result.fixDealSeq}">
																<input type="hidden" name="bunCheck" value="C">
																<input type="hidden" name="type" value="list">
																<input type="hidden" name="fileCnt" value="${result.fileCnt}">
																<input type="file" class="input_file" name="attachFile" title="파일찾기" accept="image/*" onchange="loadImage('${result.fixDealSeq}',${result.fileCnt});">
															</form>
															</div>
															
														</c:if>
													</c:if>
												</td>
												<c:choose>
													<c:when test="${result.fixState eq '2'}">
														<c:set var="background" value="background-color:#FFD8D8;"/>
												    </c:when>
												    <c:when test="${result.fixState eq '4' or result.fixState eq '5' or result.fixState eq '6'}">
												    	<c:set var="background" value="background-color:#D9E5FF;"/>
												    </c:when>
												    <c:otherwise>
												    	<c:set var="background" value=""/>
												    </c:otherwise>
											    </c:choose>
												<td class="tc" style="cursor:pointer;${background}" onclick="<c:if test="${empty result.vrPanDay and empty result.vrPanNo}">fn_view('${result.fixDealSeq}');</c:if>">
													<p class="txt_01">
														<input type="hidden" id="vrPanDay${result.fixDealSeq}" value="${result.vrPanDay}"/>
														<input type="hidden" id="vrPanNo${result.fixDealSeq}" value="${result.vrPanNo}"/>
														<c:choose>
															<c:when test="${not empty result.vrPanDay and not empty result.vrPanNo}">
																반송
															</c:when>
															<c:otherwise>
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
														   </c:otherwise>
													   </c:choose>
													</p>
												</td>
												<td class="tc" style="cursor:pointer" onclick="<c:if test="${empty result.vrPanDay and empty result.vrPanNo}">fn_view('${result.fixDealSeq}');</c:if>"><p class="txt_01">${result.insertDate}</p></td>
												<td class="tc pd2" style="cursor:pointer" onclick="<c:if test="${empty result.vrPanDay and empty result.vrPanNo}">fn_view('${result.fixDealSeq}');</c:if>">
			                                       <div class="t_img">
			                                          <img src="${pageContext.request.contextPath}${result.thumbPath}" alt="사진_${result.pumName}">
			                                       </div>
			                                    </td>
												<!-- <td class="tc" style="cursor:pointer" onclick="fn_view('${result.fixDealSeq}');"><p class="txt_01">${result.chulDate}</p></td>-->
												<td class="tc" style="cursor:pointer" onclick="<c:if test="${empty result.vrPanDay and empty result.vrPanNo}">fn_view('${result.fixDealSeq}');</c:if>">
													<p class="txt_01">${result.chulName}</p>
												</td>
												<td class="tc" style="cursor:pointer" onclick="<c:if test="${empty result.vrPanDay and empty result.vrPanNo}">fn_view('${result.fixDealSeq}');</c:if>">
													<p class="txt_01">${fn:substring(result.chulCode,0,4)} - ${fn:substring(result.chulCode,4,8)}</p>
												</td>

												<td class="tc" style="cursor:pointer" onclick="<c:if test="${empty result.vrPanDay and empty result.vrPanNo}">fn_view('${result.fixDealSeq}');</c:if>"><p class="txt_01">${result.chulLevelName}</p></td>
												<td class="tc" style="cursor:pointer" onclick="<c:if test="${empty result.vrPanDay and empty result.vrPanNo}">fn_view('${result.fixDealSeq}');</c:if>"><p class="txt_01"><fmt:formatNumber value="${result.boxCnt}" pattern="#,###" /></p></td>
												<td class="tc" style="cursor:pointer" onclick="<c:if test="${empty result.vrPanDay and empty result.vrPanNo}">fn_view('${result.fixDealSeq}');</c:if>"><p class="txt_01"><fmt:formatNumber value="${result.sokCnt}" pattern="#,###" /></p></td>
												<td class="tc" style="width:100%!important;"><p class="txt_01" style="width:100%!important;"><input  style="width:100%!important;"  type="text" value="<c:out value="${result.stdPrice}" />"  class="txt_01"  onblur="inptStdPrice2(event,'${result.fixDealSeq}')"  ></p></td>
												<td class="tc" style="cursor:pointer" onclick="<c:if test="${empty result.vrPanDay and empty result.vrPanNo}">fn_view('${result.fixDealSeq}');</c:if>">
													<p class="txt_01">
														<c:if test="${result.aucDate ne null and result.aucDate ne '-'}">
															<input type="hidden" value="${result.aucDate}" id="aucDate${result.fixDealSeq}" >
															${result.aucDate}
														</c:if>
														<c:if test="${result.aucDate eq null or result.aucDate eq '-'}">
															<input type="hidden"  value="${result.chulDate}" id="aucDate${result.fixDealSeq}">
															${result.chulDate}	
														</c:if>
													</p>
													<%-- <c:if test="${result.aucDate ne null and result.aucDate ne '-'}">
														<div class="date_box">
															<input type="text" class="datepicker" value="${result.aucDate}" id="aucDate${result.fixDealSeq}" onchange="fn_aucDate('${result.fixDealSeq}')">
														</div>
													</c:if>
													<c:if test="${result.aucDate eq null or result.aucDate eq '-'}">
														<div class="date_box">
															<input type="text" class="datepicker" value="${result.chulDate}" id="aucDate${result.fixDealSeq}" onchange="fn_aucDate('${result.fixDealSeq}')">
														</div>	
													</c:if> --%>
												</td>
												<script> 
													
														arrBid.push("${result.bidSuccess}");
													
												</script>

												<td class="tc" style="">
													<p class="txt_01"><select  value="${result.bidSuccess}"  style="width:100%;!important"  id="onBidSuc" onchange="fn_onBidSuc(event,'${result.fixDealSeq}')">
														<%-- <option value="" seleted>선택</option> --%>
														<option value="N">N</option>
														<option value="Y">Y</option>
													</select></p>
												</td>
												<td class="tc" style="display: none;">${result.unitPrice}</td>
											</tr>
										</c:forEach>
										<c:if test="${fn:length(resultList) eq 0 }">
											<tr>
												<td class="tc" colspan="16">
													데이터가 없습니다.
												</td>
											</tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- WEB테이블(E) -->

							<!-- MOBILE테이블(S) -->
							<%-- <div class="table_type_01_m mobile mt10" id="resultMobList">
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
														<h4 class="sub_tit_05">상장번호  [${result.upNo}]</h4>
													</div>
													<div class="fr">
														<c:choose>
															<c:when test="${not empty result.vrPanDay and not empty result.vrPanNo}">
																<p class="txt_re">반송</p>
															</c:when>
															<c:otherwise>
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
														   </c:otherwise>
														</c:choose>  
													</div>
													<div class="fr"><input type="checkbox" id="tm${result.fixDealSeq}" name="mfixCheck" onchange="fn_mcheck(this, '${result.fixDealSeq}');"><label for="tm${result.fixDealSeq}"><span class="mr10"></span></label></div>
												</div>
											</td>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>
												<div class="table_info">
													
													<ul class="profile_box devide_3_7" onclick="<c:if test="${empty result.vrPanDay and empty result.vrPanNo}">fn_view('${result.fixDealSeq}');</c:if>" style="cursor: pointer;">
														<li class="de1" style="height: 200px">
			                                             	<img src="${pageContext.request.contextPath}${result.thumbPath}" alt="사진_${result.pumName}">
			                                          	</li>
														 <li class="de2" style="height: 200px">
														 	<p class="txt_01">신청일자 : ${result.insertDate}</p>
															<p class="txt_01">품목(품목) : ${result.mokName}(${result.pumName})</p>
															<p class="txt_01">출하자(코드)명 : ${result.chulName} ( ${fn:substring(result.chulCode,0,4)} - ${fn:substring(result.chulCode,4,8)} )</p>
															<p class="txt_01">등급 : ${result.chulLevelName}</p>
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
															<p class="txt_01">출하일자 : 
																${result.chulDate}
															</p>
															<p class="txt_01">지역 : ${result.chulArea}</p>
															<p class="txt_01">판매단가 : <fmt:formatNumber value="${result.unitPrice}" pattern="#,###" /></p>
														</li>
													</ul>
													
													<ul class="figure_box">
														<li>
															<p>경매일자</p>
															<c:if test="${result.aucDate ne null and result.aucDate ne '-'}">
																<div class="date_box">
																	<input type="text" class="datepicker" value="${result.aucDate}" id="maucDate${result.fixDealSeq}" onchange="fn_maucDate('${result.fixDealSeq}')">
																</div>
															</c:if>
															<c:if test="${result.aucDate eq null or result.aucDate eq '-'}">
																<div class="date_box">
																	<input type="text" class="datepicker" value="${result.chulDate}" id="maucDate${result.fixDealSeq}" onchange="fn_maucDate('${result.fixDealSeq}')">
																</div>	
															</c:if>
														</li>
													</ul>
													<c:if test="${result.fixState eq '1' or result.fixState eq '2' or result.fixState eq '3' or result.fixState eq '7'}">
														<c:if test="${empty result.vrPanDay and empty result.vrPanNo}">
														<a href="javascript:fn_update('${result.fixDealSeq}');" class="btn_table_mobile"><c:if test="${result.fileCnt ne 0}">${result.fileCnt}개  +</c:if> 사진 첨부</a>
														</c:if>
													</c:if>
													
												</div>
											</td>
										</tr>
									</tbody>
								</table>
								</c:forEach>
								<c:if test="${fn:length(resultList) eq 0 }">
									데이터가 없습니다.
								</c:if>
							</div> --%>
							<!-- MOBILE테이블(E) -->


							
							<!-- 페이지박스(S)
							<div class="paging_box mt30">
								<c:if test="${paginationInfo.lastPageNo ne paramMap.pageIndex}">
								<a href="javascript:fn_more();" class="btn_search_02 ml10" id="moreBtn">더보기 <b id="currentPageCnt"><c:out value="${paramMap.pageUnit}"/></b> / ${resultCnt}</a>
								</c:if>
							</div> -->
							<!-- 페이지박스(E) -->

							<!-- 버튼박스(S) -->
							<!-- <div class="btn_box mt30">
								<a href="javascript:fn_apply();" class="btn_type_01 gray">체크된행 승인<a>
								<a href="javascript:fn_appCancel();" class="btn_type_01 gray">체크된행 승인취소<a>
								<a href="javascript:fn_comp();" class="btn_type_01 gray">체크된행 완료<a>
								<a href="javascript:fn_compCancel();" class="btn_type_01 gray">체크된행 완료취소<a>
								<a href="javascript:fn_sms();" class="btn_type_01 gray">체크된행 문자발송<a>
								2021.11.19 이현승
								<a href="javascript:fn_sms_select('1');" class="btn_type_01 gray">체크된행 문자발송(출하자)<a>
								<a href="javascript:fn_sms_select('2');" class="btn_type_01 gray">체크된행 문자발송(중도매인)<a>
								<a href="javascript:fn_reg();" class="btn_type_01 gray">등록</a>
							</div> -->
							<!-- 버튼박스(S) -->
						
						</div>

						</div>
					</div>
					<!-- tab 내용01(E) --><!-- 판매(E)-->
				</div>
				<!-- sub탭(E) -->

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
						<p class="pop_title">재상장 내역 불러오기</p>
					</div>
				</div>
				<!-- 팝업 타이틀 박스(E) -->

				<!-- 팝업 컨텐츠(S) -->
				<div class="choice_box">
					<div class="popup_search">
						<div class="ip_type_02 dib">
							<div class="date_box flex-01" id="smallUpdayBox">
								<input type="text" class="datepicker" id="smallUpday" name="smallUpday">
								<label for="smallUpday"></label>
							</div>
						</div>
						<a href="javascript:getFixSmall();" class="btn_search_03 ml10 vb">확인</a>
					</div>

					<div class="table_type_04 mt10 overflow_a" style="max-height: 250px;">
						
						<table>
							<caption>출하품목</caption>
							<colgroup>
								<col style="width:50%">
								<col style="width:50%">
							</colgroup>
							<thead>
								<!--  
								<tr>
									<th>품목명</th>
									<th>품종명</th>
								</tr>
								-->
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



<div class="dim-layer" id="layerSampleCDel">
	<div class="dimBg"></div>
	<div class="pop-layer popbox pop_type_02">
		<div class="pop-container">
			<a href="#!" class="btn-layerClose pop_btn_close_02 close_B" alt="닫기 버튼"></a>
			<div class="pop-conts_00">
				<!-- 팝업 타이틀 박스(S) -->
				<div class="pop_title_box">
					<div class="fl">
						<p class="pop_title">재상장 내역 삭제</p>
					</div>
				</div>
				<!-- 팝업 타이틀 박스(E) -->

				<!-- 팝업 컨텐츠(S) -->
				<div class="choice_box">
					<div class="popup_search">
						<div class="ip_type_02 dib">
							<div class="date_box flex-01" id="smallUpdayBox">
								<input type="text" class="datepicker" id="smallUpdayDel" name="smallUpdayDel">
								<label for="smallUpdayDel"></label>
							</div>
						</div>
						<a href="javascript:getFixSmall_del();" class="btn_search_03 ml10 vb">확인</a>
					</div>

					<div class="table_type_04 mt10 overflow_a" style="max-height: 250px;">
						
						<table>
							<caption>출하품목</caption>
							<colgroup>
								<col style="width:50%">
								<col style="width:50%">
							</colgroup>
							<thead>
								<!--  
								<tr>
									<th>품목명</th>
									<th>품종명</th>
								</tr>
								-->
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



<div class="dim-layer" id="layerSampleCExcel">
	<div class="dimBg"></div>
	<div class="pop-layer popbox pop_type_02">
		<div class="pop-container">
			<a href="#!" class="btn-layerClose pop_btn_close_02 close_B" alt="닫기 버튼"></a>
			<div class="pop-conts_00">
				<!-- 팝업 타이틀 박스(S) -->
				<div class="pop_title_box">
					<div class="fl">
						<p class="pop_title">엑셀 다운로드</p>
					</div>
				</div>
				<!-- 팝업 타이틀 박스(E) -->

				<!-- 팝업 컨텐츠(S) -->
				<div class="choice_box">
					<div class="popup_search">
						<div class="ip_type_02 dib">
							<div class="date_box flex-01" id="smallUpdayBox">
								<input type="text" class="datepicker" id="smallUpdayExcel" name="smallUpdayExcel">
								<label for="smallUpdayExcel"></label>
							</div>
						</div>
						<a href="javascript:getFixSmall_excel();" class="btn_search_03 ml10 vb">확인</a>
					</div>

					<div class="table_type_04 mt10 overflow_a" style="max-height: 250px;">
						
						<table>
							<caption>출하품목</caption>
							<colgroup>
								<col style="width:50%">
								<col style="width:50%">
							</colgroup>
							<thead>
								<!--  
								<tr>
									<th>품목명</th>
									<th>품종명</th>
								</tr>
								-->
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



<!-- 요청관리 버튼(7개)(S) -->
<div class="btn-tab-new right d6">
	<ul>
		<!-- <li class="btn-chk-icon agree"><a href="javascript:fn_apply();"><i><img src="/yfmc/img/btn-new-01.png" alt="승인"></i><p>승인</p></a></li>승인 -->				
		<!-- <li class="btn-chk-icon a-cancel"><a href="javascript:fn_appCancel();"><i><img src="/yfmc/img/btn-new-02.png" alt="승인취소"></i><p>승인취소</p></a></li>승인취소 -->				
		<li class="btn-chk-icon complete"><a href="javascript:fn_comp();"><i><img src="/yfmc/img/btn-new-03.png" alt="완료"></i><p>완료</p></a></li><!--완료-->				
		<li class="btn-chk-icon c-cancel"><a href="javascript:fn_compCancel();"><i><img src="/yfmc/img/btn-new-04.png" alt="완료취소"></i><p>완료취소</p></a></li><!--완료취소-->				
		<li class="btn-chk-icon mid-message"><a href="javascript:fn_sms_select('2');"><span>중도매인</span><i><img src="/yfmc/img/btn-new-05.png" alt="중도매인문자발송"></i><p>문자발송</p></a></li><!-- 문자발송(중도매인) -->
		<li class="btn-chk-icon no-chk txt-top"><a href="javascript:fn_fix_small_excel();"><i><img src="/yfmc/img/btn-new-09.png" alt="엑셀 다운 로드"></i><p>엑셀<br>다운로드</p></a></li>
		<li class="btn-chk-icon no-chk txt-top"><a href="javascript:fn_fix_small_del();"><i><img src="/yfmc/img/btn-new-11.png" alt="재상장 내역 삭제"></i><p>재상장 내역 삭제</p></a></li>
		<li class="btn-chk-icon no-chk txt-top"><a href="javascript:fn_fix_small();"><i><img src="/yfmc/img/btn-new-10.png" alt="재상장 내역 불러오기"></i><p>재상장 내역 불러오기</p></a></li> 
	</ul>
</div>
<!-- 요청관리 버튼(7개)(E) -->