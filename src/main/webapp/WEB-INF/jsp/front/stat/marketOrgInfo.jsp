<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

	<div class="sub_conts bgw">
			
			<!-- sub상단(S) -->
			<div class="sub_top">
				<h2 class="sub_title">·출하단체 등록정보·</h2>

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
							<h4 class="sub_tit_02">출하단체등록정보</h4>
						</div>
					</div>
					
					<p class="txt_w mt10"><span class="number">·</span>한국농수산식품유통공사 양재동 화훼공판장의 출하단체 등록정보입니다. <br/> 등록정보 항목 중 변경사항이 있으시면 화훼공판장 유통정보실(Tel:02-570-1882)로 연락주시기 바랍니다.</p>
				
				
				<!-- 검색조건창(S) -->
					<div class="condition_box bdtg2 mt10">
						<div class="search_box">
							<ul class="sb_line">
								<li>
									<p class="sb_title bold">구분</p>
									<div class="sel_type_01 w150">
										<select id="gCode" name="gCode">
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
										<label for="gCode"></label>
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
							<h4 class="sub_tit_04">구분</h4>
						</div>
						<div class="fr">
							<a href="javascript:fn_report();" class="btn_type_01">리포트</a>
						</div>
					</div>
					<!-- 타이틀(E) -->

					<!-- 테이블03(S) -->
					<div class="table_type_03 mt5">
						<table>
							<caption>구분</caption>
							<colgroup>
								<col style="width:17%">
								<col style="width:33%">
								<col style="width:17%">
								<col style="width:33%">
							</colgroup>
							<tbody id ="resulTbody1">
								<tr>
									<th class="tc">단체코드</th>
									<td class="tl"><p class="txt_04" id="gcode"></p></td>
									<th class="tc">화훼구분</th>
									<td class="tl"><p class="txt_04" id="bunchk"></p></td>
								</tr>
							</tbody>
						</table>
					</div>
					<!-- 테이블03(E) -->

					<div class="title_box mt30">
						<div class="fl">
							<h4 class="sub_tit_04">단체정보</h4>
						</div>
					</div>
					<!-- 테이블03(S) -->
					<div class="table_type_03 mt5">
						<table>
							<caption>단체정보</caption>
							<colgroup>
								<col style="width:17%">
								<col style="width:33%">
								<col style="width:17%">
								<col style="width:33%">
							</colgroup>
							<tbody id ="resulTbody2">
								<tr>
									<th class="tc">출하단체명</th>
									<td class="tl"><p class="txt_04" id="gname1"></p></td>
									<th class="tc">담당자</th>
									<td class="tl"><p class="txt_04" id="gname2"></p></td>
								</tr>
								<tr>
									<th class="tc">대표자명</th>
									<td class="tl"><p class="txt_04" id="cname"></p></td>
									<th class="tc">사업자등록번호</th>
									<td class="tl"><p class="txt_04" id="copno"></p></td>
								</tr>
								<tr>
									<th class="tc">업태</th>
									<td class="tl"><p class="txt_04" id="upte"></p></td>
									<th class="tc">종목</th>
									<td class="tl"><p class="txt_04" id="jongmok"></p></td>
								</tr>
								<tr>
									<th class="tc">계약 개시일</th>
									<td class="tl"><p class="txt_04" id="pstartday"></p></td>
									<th class="tc">계약 종료일</th>
									<td class="tl"><p class="txt_04" id="pendday"></p></td>
								</tr>
								<tr>
									<th class="tc">인원수</th>
									<td class="tl"><p class="txt_04" id="mancnt"></p></td>
									<th class="tc">ARS/FAX 비밀번호</th>
									<td class="tl"><p class="txt_04" id="passwd"></p></td>
								</tr>
							</tbody>
						</table>
					</div>
					<!-- 테이블03(E) -->

					<div class="title_box mt30">
						<div class="fl">
							<h4 class="sub_tit_04">주소</h4>
						</div>
					</div>
					<!-- 테이블03(S) -->
					<div class="table_type_03 mt5">
						<table>
							<caption>주소</caption>
							<colgroup>
								<col style="width:17%">
								<col style="width:33%">
								<col style="width:17%">
								<col style="width:33%">
							</colgroup>
							<tbody id ="resulTbody3">
								<tr>
									<th class="tc">우편번호</th>
									<td class="tl"><p class="txt_04" id="zipno"></p></td>
									<th class="tc">우편번호 순번</th>
									<td class="tl"><p class="txt_04" id="zippk"></p></td>
								</tr>
								<tr>
									<th class="tc">주소</th>
									<td class="tl" colspan="3"><p class="txt_04" id="addr1"></p></td>
								</tr>
								<tr>
									<th class="tc">번지</th>
									<td class="tl" colspan="3"><p class="txt_04" id="addr2"></p></td>
								</tr>
								<tr>
									<th class="tc">전화번호</th>
									<td class="tl"><p class="txt_04" id="telno"></p></td>
									<th class="tc">팩스번호</th>
									<td class="tl"><p class="txt_04" id="faxno"></p></td>
								</tr>
							</tbody>
						</table>
					</div>
					<!-- 테이블03(E) -->

					<div class="title_box mt30">
						<div class="fl">
							<h4 class="sub_tit_04">결제정보</h4>
						</div>
					</div>
					<!-- 테이블03(S) -->
					<div class="table_type_03 mt5">
						<table>
							<caption>결제정보</caption>
							<colgroup>
								<col style="width:17%">
								<col style="width:33%">
								<col style="width:17%">
								<col style="width:33%">
							</colgroup>
							<tbody id ="resulTbody4">
								<tr>
									<th class="tc">은행코드</th>
									<td class="tl"><p class="txt_04" id="bankcode"></p></td>
									<th class="tc">입금계좌</th>
									<td class="tl"><p class="txt_04">단체계좌</p></td>
								</tr>
								<tr>
									<th class="tc">계좌번호</th>
									<td class="tl"><p class="txt_04" id="banklineno"></p></td>
									<th class="tc">예금주</th>
									<td class="tl"><p class="txt_04" id="bankman"></p></td>
								</tr>
							</tbody>
						</table>
					</div>
					<!-- 테이블03(E) -->
		

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
				fn_search();
			});
			
			function fn_search(){
			var gCode = $("#gCode").prop("value");
			var bunChk = $("#gCode option:selected").attr("data-bunchk");
			
			$.ajax({
				data:{
					gCode: gCode,
					bunChk: bunChk
			        },
		        type : "POST",
		        url : "${pageContext.request.contextPath}/front/stat/marketOrgInfo.json",
		        success : function(data){
		           console.log(data);
		           
		       	   var bunName = "";
		           var result = data.result;
		        
		           
		           if(result.bunchk == 'N'){
		        	   bunName = "절화";
		           }else if(result.bunchk  == 'Y'){
		        	   bunName = "난";
		           }else if(result.bunchk  == 'C'){
		        	   bunName = "관엽";
		           }
		  
		           $("#addr1").html(result.addr1);
		           $("#addr2").html(result.addr2);
		           $("#bankcode").html(result.bankcode + " " + result.bankname);
		           $("#banklineno").html(result.banklineno);
		           $("#bankman").html(result.bankman);
		           $("#bunchk").html(bunName);
		           $("#cname").html(result.cname);
		           $("#copno").html(result.copno);
		           $("#faxno").html(result.faxno);
		           $("#gcode").html(result.gcode);
		           $("#gname1").html(result.gname);
		           $("#gname2").html(result.gname);
		           $("#jongmok").html(result.jongmok);
		           $("#mancnt").html(result.mancnt);
		           $("#passwd").html(result.passwd);
		           $("#telno").html(result.telno);
		           $("#upte").html(result.upte);
		           $("#zipno").html(result.zipno);
		           $("#zippk").html(result.zippk);
		           $("#pstartday").html(result.pstartday);
		           $("#pendday").html(result.pendday);
		           
		           
			       
		        }
		    });
			
		}
			
			
			function fn_report(){
				var reportname = "marketOrgInfo.ozr";
				var gCode = $("#gCode").prop("value");
				
				$('input[name=reportname]').val(reportname);
				$('input[name=setUrl]').val("${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/front/stat/marketOrgInfo.json?gCode="+gCode);
				
				$('#reportForm').submit();
			}
		</script>