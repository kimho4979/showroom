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

$(document).ready(function(){
	
	//getChulList();
});

function fn_view(fixSubDealSeq){
	window.location.href = "${pageContext.request.contextPath}/front/fixCAuc/fixAdmSubView.do?fixSubDealSeq="+fixSubDealSeq;
}

function fn_reg(){
	window.location.href = "${pageContext.request.contextPath}/front/fixCAuc/fixAdmSubReg.do";
}


function fn_search(orderString){
	$("#frm").submit();
}


function fn_chulReg(chulCode){
	if(confirm("선택한 출하자를 등록하시겠습니까?")){
		$.ajax({
			data:{
				 chulCode: chulCode
		        },
	        type : "POST",
	        url : "${pageContext.request.contextPath}/front/fixCAuc/fixAdmSubChulRegProc.json",
	        success : function(data){
	            if(data.result==1){
	            	alert("정상적으로 저장되었습니다.");
	            	 location.reload(true);
	            }else if(data.result==2){
	            	alert("등록된 분갈이 취급 출하자입니다.");
	            }
	        }
	    });
	}
}

function fn_chulDel(chulCode){
	if(confirm("선택한 출하자를 삭제하시겠습니까?")){
		$.ajax({
			data:{
				 chulCode: chulCode
		        },
	        type : "POST",
	        url : "${pageContext.request.contextPath}/front/fixCAuc/fixAdmSubChulDelProc.json",
	        success : function(data){
	            if(data.result==1){
	            	alert("정상적으로 삭제되었습니다.");
	            	 location.reload(true);
	            }
	        }
	    });
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
   	   			html += "<tr onclick=\"fn_chulReg('"+resultList[i].code+"')\" style=\"cursor: pointer;\">           ";
   	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+resultList[i].name+"</a></td>       ";
   	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+resultList[i].code.substring(0,4)+"-"+resultList[i].code.substring(4,8)+"</a></td>          ";
   	   			html += "	<td class=\"tc\"><a href=\"#!\" class=\"txt_01\">"+resultList[i].chulArea+"</a></td>          ";
   	   			html += "</tr>                                                                               ";
           	   
              }
        	   
           }else{
        	   html += "<tr><td class=\"tc\" colspan=\"3\">출하자정보가 없습니다</td></tr>";
           }
           
           $("#chulTbody").html(html);
        }
    });
}


</script>




<!-- sub내용(S) -->
			<div class="sub_conts_in">

				<div class="tab_content pdt0">
					<!-- tab 내용01(S) --><!-- 판매(S)-->
					<div class="ti_01">


						<div class="info_box">

							<!-- 서브탭(S) -->
							<div class="sub_tab">
								<ul class="devide_3">
									<c:if test="${nFloLoginVO ne null }">
									<li><a href="${pageContext.request.contextPath}/front/fixNAuc/fixAdmList.do" >절화</a></li>
									</c:if>
									<c:if test="${yFloLoginVO ne null }">
									<li><a href="${pageContext.request.contextPath}/front/fixYAuc/fixAdmList.do">&nbsp;&nbsp;&nbsp;난&nbsp;&nbsp;&nbsp;</a></li>
									</c:if>
									<c:if test="${cFloLoginVO ne null }">
									<li><a href="${pageContext.request.contextPath}/front/fixCAuc/fixAdmList.do">관엽</a></li>
									<li><a href="${pageContext.request.contextPath}/front/fixCAuc/fixAdmSubList.do" class="active">관엽-분갈이</a></li>
									</c:if>
								</ul>
							</div>
							<!-- 서브탭(E) -->
							
							<div class="title_box mt30">
								<div class="fl">
									<h2 class="sub_tit_02">출하자 검색</h2>
								</div>
							</div>
							
							<ul class="col_wrap col4_6 mt10 search_box_03">
								<li class="cola">
									<div class="popup_search">
										<div class="ip_type_02 dib">
											<input type="text" id="searchChulCode" placeholder="출하자명 or 코드">
											<label for="searchChulCode"></label>
										</div>
										<a href="javascript:getChulList();" class="btn_search_03 btn-popup ml10 vb">검색</a>
									</div>
			
									<div class="table_type_04 mt10 overflow_a">
										<table>
											<caption>출하자리스트</caption>
											<colgroup>
												<col style="width:25%">
												<col style="width:25%">
												<col style="width:50%">
											</colgroup>
											<thead>
												<tr>
													<th class="tc">출하자명</th>
													<th class="tc">출하자코드</th>
													<th class="tc">지역</th>
												</tr>
											</thead>
											<tbody id="chulTbody">
												
											</tbody>
										</table>
									</div>
								</li>
								<li class="colb">
									<div class="name_list">
										<c:forEach items="${chulList}" var="chulVO" varStatus="status">
										<a href="javascript:fn_chulDel('${chulVO.chulCode}');" class="btn_name">${chulVO.chulName}</br> ( ${fn:substring(chulVO.chulCode,0,4)} - ${fn:substring(chulVO.chulCode,4,8)} )</a>
										</c:forEach>
									</div>
								</li>
							</ul>


						</div>
						
						<!-- 버튼박스(S) 
						<div class="btn_box mt30">
							<a href="javascript:fn_chulReg('15000673');" class="btn_type_01 gray">출하자 등록</a>
							<a href="javascript:fn_chulDel('15000673');" class="btn_type_01 gray">출하자 삭제</a>
						</div>-->
						<!-- 버튼박스(S) -->
						
						<!-- 검색조건창(S) -->
						<form id="frm" name="searchFrm" method="post" action="${pageContext.request.contextPath}/front/fixCAuc/fixAdmSubList.do" >
						
						<div class="condition_box">
							<div class="search_box">
								<ul class="sb_line">
									<li>
										<p class="sb_title">출하자</p>
										<div class="sb_data">
											<div class="sel_type_01 w150">
												<select id="searchChulCode" name="searchChulCode">
													<option value="" <c:if test="${paramMap.searchChulCode eq null}">selected="selected"</c:if>>전체</option>
													<c:forEach items="${chulList}" var="chulVO" varStatus="status">
														<option value="${chulVO.chulCode}" <c:if test="${paramMap.searchChulCode eq chulVO.chulCode}">selected="selected"</c:if>>${fn:substring(chulVO.chulCode,0,4)} - ${fn:substring(chulVO.chulCode,4,8)} ( ${chulVO.chulName} )</option>
													</c:forEach>
												</select>
												<label for="searchChulCode"></label>
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
						
						<div class="card_box mt30">
							<ul>
								<c:forEach items="${resultList}" var="result" varStatus="status">
								<li onclick="fn_view('${result.fixSubDealSeq}');" style="cursor: pointer;">
									<div class="card">
										<span class="c_img">
											<img src="${pageContext.request.contextPath}${result.thumbPath}" alt="이미지_${result.pumName}">
										</span>
										<div class="c_info">
											<h4 class="c_tit">${result.pumName}</h4>
											<p class="c_cost">판매단가 : <fmt:formatNumber value="${result.unitPrice}" pattern="#,###" /></p>
											<p class="c_name">${result.chulName}<br/>(${fn:substring(result.chulCode,0,4)} - ${fn:substring(result.chulCode,4,8)})</p></p>
										</div>
									</div>
								</li>
								</c:forEach>
							</ul>
						</div>
						<c:if test="${fn:length(resultList) eq 0 }">
								<p class="tc">등록된 데이터가 없습니다.</p>
						</c:if>

					</div>
					<!-- tab 내용01(E) --><!-- 판매(E)-->
				</div>
				<!-- sub탭(E) -->

			</div>
			<!-- sub내용(E) -->
