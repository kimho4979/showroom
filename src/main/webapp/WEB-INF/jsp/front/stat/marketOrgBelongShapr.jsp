<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    
  <!-- sub-conts(S) -->
		<div class="sub_conts bgw">

			<!-- sub상단(S) -->
			<div class="sub_top">
				<h2 class="sub_title">·소속농가현황·</h2>

				<!-- 네비게이션(S) -->
				<jsp:include page="/WEB-INF/jsp/front/inc/statNav.jsp"/>
				<!-- 네비게이션(E) -->
			</div>
			<!-- sub상단(E) -->
			
			<!-- sub내용(S) -->
			<div class="sub_conts_in">
				<jsp:include page="/WEB-INF/jsp/front/login/statTab.jsp"/>
				<div class="info_box mt30">
					<div class="title_box">
						<div class="fl">
							<h4 class="sub_tit_02">소속농가현황</h4>
						</div>
					</div>

					<!-- 타이틀(S) -->
					<div class="title_box mt30">
						<div class="fl">
							<h4 class="sub_tit_04">소속농가현황 검색</h4>
						</div>
					</div>
					<!-- 타이틀(E) -->
					<p class="txt_w mt10"><span class="number">·</span>한국농수산식품유통공사 양재동 화훼공판장의 출하농가 등록정보입니다. <br/> 등록정보 항목 중 변경사항이 있으시면 화훼공판장 유통정보실(Tel:02-570-1881)로 연락주시기 바랍니다.</p>
					<!-- 검색조건창(S) -->
					<div class="condition_box bdtg2 mt10">
						<div class="search_box">
							<ul class="sb_line">
								<li>
									<p class="sb_title bold">구분</p>
									<div class="sel_type_01 w150">
										<select id="domeCode" name="domeCode">
											<c:forEach items="${floLoginList}" var="floLoginVO" varStatus="status" >
												<c:if test="${floLoginVO.aucType eq '02' }">
													<option value="${floLoginVO.chulCd}" data-bunchk="${floLoginVO.floMokCd}">
														<c:if test="${floLoginVO.floMokCd eq 'N'}">절화</c:if>
														<c:if test="${floLoginVO.floMokCd eq 'Y'}">난</c:if>
														<c:if test="${floLoginVO.floMokCd eq 'C'}">관엽</c:if>
														${floLoginVO.chulCd}
													</option>
												</c:if>
											</c:forEach>
										</select>
										<label for="domeCode"></label>
									</div>
								</li>
							</ul>
							<a href="javascript:fn_search();" class="btn_search">조회</a>
						</div>
					</div>
					<!-- 검색조건창(E) -->
					
					<!-- 타이틀(S) -->
					<div class="title_box mt30">
						<div class="fl">
							<h4 class="sub_tit_04">소속농가현황</h4>
						</div>
						<div class="fr">
							<a href="javascript:fn_report();" id="btnReport" class="btn_type_01">리포트</a>
							<!-- <a href="javascript:fn_excell();" class="btn_type_01">엑셀다운로드</a> -->
						</div>
					</div>
					<!-- 타이틀(E) -->

					<!-- 테이블03(S) -->
					<div class="table_type_03 mt5" id="infoDiv">
						<table>
							<caption>소속농가현황</caption>
							<colgroup>
								<col style="width:17%">
								<col style="width:33%">
								<col style="width:17%">
								<col style="width:33%">
							</colgroup>
							<tbody>
								<tr>
									<th class="tc">출하단체</th>
									<td class="tl" colspan="3"><p class="txt_04" id="gName"></p></td>
								</tr>
								<tr>
									<th class="tc">화훼부류</th>
									<td class="tl"><p class="txt_04" id="bunName"></p></td>
									<th class="tc">조회일자</th>
									<td class="tl"><p class="txt_04" id="currentDate"></p></td>
								</tr>
							</tbody>
						</table>
					</div>
					<!-- 테이블03(E) -->
					
					<!-- 타이틀(S) -->
					<div class="title_box mt30">
						<div class="fr">
							<div class="icon-box tr">
								<span class="guide-icon width-scroll"></span>
							</div>
						</div>
					</div>
					<!-- 타이틀(E) -->
					
					<!-- 테이블04(S) -->
					<div class="table_type_04 mt5 overflow_a">
						<table>
							<caption>리스트</caption>
							<colgroup>
								<col style="width:100px;">
								<col style="width:100px;">
								<col style="width:100px;">
								<col style="width:500px;">
							</colgroup>
							<thead>
								<tr>
									<th class="tc">출하자코드</th>
									<th class="tc">성명</th>
									<th class="tc">전화번호</th>
									<th class="tc">주소</th>
								</tr>
							</thead>
							<tbody id="resultTbody">
								
							</tbody>
							<tfoot id="resultTfoot">
								
							</tfoot>
						</table>
					</div>
					<!-- 테이블04(E) -->
		

				</div>

			</div>
			<!-- sub내용(E) -->
			
			<form id="reportForm" action="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/oz80/report.jsp" method="post" target="_blank">
				<input type="hidden" name="reportname" value="">
				<input type="hidden" name="setUrl" value="">
				<input type="hidden" name="hostUrl" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/oz80/server">
			</form>
			
		</div>
		<!-- sub-conts(E) -->  
		
		<script type="text/javascript">
		var currentDate = "";
		
		$(document).ready(function(){
			setSearchDateInit(7);
			currentDate = $("#endDate").val();
		});
		
		
		function fn_search(){
			var domeCode = $("#domeCode").prop("value");
			var bunChk = $("#domeCode option:selected").attr("data-bunchk");
			
			$.ajax({
				data:{
					domeCode: domeCode,
					bunChk: bunChk
			        },
		        type : "POST",
		        url : "${pageContext.request.contextPath}/front/stat/marketOrgBelongShapr.json",
		        success : function(data){
		        	console.log(data);
		           var html = "";
		           var fhtml = "";
		           var resultList = data.resultList;
		           var sumVO = data.sumVO;
		           var groupVO = data.groupVO;
		           var gName = "";
		           var bunName = "";
		           var today = "";
		           
		           if(groupVO != null){
		        	   gName = groupVO.gname;
		        	   today = groupVO.today;
		        	   bunName = groupVO.bunchk;
		           }
		           
		           
		           $("#gName").html(gName);
		           $("#bunName").html(bunName);
		           $("#currentDate").html(today);
		           
		          
		           
		           if(resultList.length > 0){
		        	   for(var i=0; i<resultList.length; i++ ){
		        		   
		        		   var code = "";
				           var name = "";
				           var htelno = "";
				           var haddr1 = "";
				           var haddr2 = "";
				           if(resultList[i].code != null){
				        	   code = resultList[i].code;
				           }
				           if(resultList[i].name != null){
				        	   name = resultList[i].name;
				           }
				           if(resultList[i].htelno != null){
				        	   htelno = resultList[i].htelno;
				           }
				           if(resultList[i].haddr1 != null){
				        	   haddr1 = resultList[i].haddr1;
				           }
				           if(resultList[i].haddr2 != null){
				        	   haddr2 = resultList[i].haddr2;
				           }
		        		   
		        		    html+="<tr>                                                                   ";
							html+="	<td class=\"tc\"><p class=\"txt_01\">"+code+"</p></td>  ";
							html+="	<td class=\"tc\"><p class=\"txt_01\">"+name+"</p></td>  ";
							html+="	<td class=\"tc\"><p class=\"txt_01\">"+htelno+"</p></td>  ";
							html+="	<td class=\"tl\"><p class=\"txt_01\">"+haddr1+" "+haddr2+"</p></td>  ";
							html+="</tr>                                                                  ";
		        	   }
		        	   
		        	    fhtml+="<tr>                                                                   ";
						fhtml+="	<td class=\"tc\"><p class=\"txt_01\">농가수</p></td>  ";
						fhtml+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(sumVO.count)+"명</p></td>  ";
						fhtml+="	<td class=\"tr\"><p class=\"txt_01\"></p></td>  ";
						fhtml+="	<td class=\"tr\"><p class=\"txt_01\"></p></td>  ";
						fhtml+="</tr>                                                                  ";
		           }else{
		        	   html += "<tr><td class=\"tc\" colspan=\"4\">데이터가 없습니다.</td></tr>";
		           }
		           
		           $("#resultTbody").html(html);
		           $("#resultTfoot").html(fhtml);
			       
		        }
		    });
			
		}
		
		function fn_report(){
			var reportname = "marketOrgBelongShapr.ozr";
			var domeCode = $("#domeCode").prop("value");
			var bunChk = $("#domeCode option:selected").attr("data-bunchk");
						
			$('input[name=reportname]').val(reportname);
			$('input[name=setUrl]').val("${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/front/stat/marketOrgBelongShapr.json?domeCode="+domeCode+"&bunChk="+bunChk);
						
			$('#reportForm').submit();
		}

		</script>