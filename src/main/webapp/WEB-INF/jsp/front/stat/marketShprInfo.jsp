<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

	<div class="sub_conts bgw">
			
			<!-- sub상단(S) -->
			<div class="sub_top">
				<h2 class="sub_title">·출하농가 등록정보·</h2>

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
							<h4 class="sub_tit_02">출하농가등록정보</h4>
						</div>
					</div>
					
					<p class="txt_w mt10"><span class="number">·</span>한국농수산식품유통공사 양재동 화훼공판장의 출하농가 등록정보입니다. <br/> 등록정보 항목 중 변경사항이 있으시면 화훼공판장 유통정보실(Tel:02-570-1882)로 연락주시기 바랍니다.</p>
				
				
				<!-- 검색조건창(S) -->
					<div class="condition_box bdtg2 mt10">
						<div class="search_box">
							<ul class="sb_line">
								<li>
									<p class="sb_title bold">구분</p>
									<div class="sel_type_01 w150">
										<select id="chulCode" name="chulCode">
											<c:forEach items="${floLoginList}" var="floLoginVO" varStatus="status" >
												<c:if test="${floLoginVO.aucType eq '01' }">
													<option value="${floLoginVO.chulCd}" data-bunchk="${floLoginVO.floMokCd}">
														<c:if test="${floLoginVO.floMokCd eq 'N'}">절화</c:if>
														<c:if test="${floLoginVO.floMokCd eq 'Y'}">난</c:if>
														<c:if test="${floLoginVO.floMokCd eq 'C'}">관엽</c:if>
														${floLoginVO.chulCd}
													</option>
												</c:if>
											</c:forEach>
										</select>
										<label for="code"></label>
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
							<a href="javascript:fn_report();" id="btnReport" class="btn_type_01">리포트</a>
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
							<tbody>
								<tr>
									<th class="tc">출하자코드</th>
									<td class="tl"><p class="txt_04" id="code"></p></td>
									<th class="tc">화훼구분</th>
									<td class="tl"><p class="txt_04" id="bunchknm"></p></td>
								</tr>
							</tbody>
						</table>
					</div>
					<!-- 테이블03(E) -->

					<div class="title_box mt30">
						<div class="fl">
							<h4 class="sub_tit_04">농가정보</h4>
						</div>
					</div>
					<!-- 테이블03(S) -->
					<div class="table_type_03 mt5">
						<table>
							<caption>농가정보</caption>
							<colgroup>
								<col style="width:17%">
								<col style="width:33%">
								<col style="width:17%">
								<col style="width:33%">
							</colgroup>
							<tbody>
								<tr>
									<th class="tc">출하자명</th>
									<td class="tl" colspan="3"><p class="txt_04" id="name"></p></td>
								</tr>
								<tr>
									<th class="tc">출하자 등급</th>
									<td class="tl"><p class="txt_04" id="grade"></p></td>
									<th class="tc">소속단체</th>
									<td class="tl"><p class="txt_04" id="gcodegname"></p></td>
								</tr>
								<tr>
									<th class="tc">E-mail주소</th>
									<td class="tl"><p class="txt_04" id="emailid"></p></td>
									<th class="tc">우편물발송자</th>
									<td class="tl"><p class="txt_04" id="dmsend"></p></td>
								</tr>
								<tr>
									<th class="tc">등록일자</th>
									<td class="tl"><p class="txt_04" id="yday"></p></td>
									<th class="tc">AR8/FAX 비밀번호</th>
									<td class="tl"><p class="txt_04" id="gpasswd"></p></td>
								</tr>
							</tbody>
						</table>
					</div>
					<!-- 테이블03(E) -->

					<div class="title_box mt30">
						<div class="fl">
							<h4 class="sub_tit_04">자택주소</h4>
						</div>
					</div>
					<!-- 테이블03(S) -->
					<div class="table_type_03 mt5">
						<table>
							<caption>자택주소</caption>
							<colgroup>
								<col style="width:17%">
								<col style="width:33%">
								<col style="width:17%">
								<col style="width:33%">
							</colgroup>
							<tbody>
								<tr>
									<th class="tc">우편번호</th>
									<td class="tl"><p class="txt_04" id="hzipno"></p></td>
									<th class="tc">우편번호 순번</th>
									<td class="tl"><p class="txt_04" id="hzippk"></p></td>
								</tr>
								<tr>
									<th class="tc">주소</th>
									<td class="tl" colspan="3"><p class="txt_04" id="haddr1"></p></td>
								</tr>
								<tr style="display: none;">
									<th class="tc">번지</th>
									<td class="tl" colspan="3"><p class="txt_04" id="haddr2"></p></td>
								</tr>
								<tr>
									<th class="tc">전화번호</th>
									<td class="tl"><p class="txt_04" id="htelno"></p></td>
									<th class="tc">핸드폰</th>
									<td class="tl"><p class="txt_04" id="handyphoneno"></p></td>
								</tr>
							</tbody>
						</table>
					</div>
					<!-- 테이블03(E) -->

					<div class="title_box mt30">
						<div class="fl">
							<h4 class="sub_tit_04">농장주소</h4>
						</div>
					</div>
					<!-- 테이블03(S) -->
					<div class="table_type_03 mt5">
						<table>
							<caption>농장주소</caption>
							<colgroup>
								<col style="width:17%">
								<col style="width:33%">
								<col style="width:17%">
								<col style="width:33%">
							</colgroup>
							<tbody>
								<tr>
									<th class="tc">우편번호</th>
									<td class="tl"><p class="txt_04" id="fzipno"></p></td>
									<th class="tc">우편번호 순번</th>
									<td class="tl"><p class="txt_04" id="fzippk"></p></td>
								</tr>
								<tr>
									<th class="tc">주소</th>
									<td class="tl" colspan="3"><p class="txt_04" id="faddr1"></p></td>
								</tr>
								<tr style="display: none;">
									<th class="tc">번지</th>
									<td class="tl" colspan="3"><p class="txt_04" id="faddr2"></p></td>
								</tr>
								<tr>
									<th class="tc">전화번호</th>
									<td class="tl"><p class="txt_04" id="ftelno"></p></td>
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
								<col style="width:10%">
								<col style="width:23.33%">
								<col style="width:10%">
								<col style="width:23.33%">
								<col style="width:10%">
								<col style="width:23.33%">
							</colgroup>
							<tbody>
								<tr>
									<th class="tc">은행코드</th>
									<td class="tl"><p class="txt_04" id="bankcodebankname"></p></td>
									<th class="tc">계좌번호</th>
									<td class="tl"><p class="txt_04" id="kyeno"></p></td>
									<th class="tc">예금주</th>
									<td class="tl"><p class="txt_04" id="inname"></p></td>
								</tr>
								<tr>
									<th class="tc">공동계좌송금</th>
									<td class="tl"><p class="txt_04" id="gbankcodegbankname"></p></td>
									<th class="tc">계좌번호</th>
									<td class="tl"><p class="txt_04" id="ggyejwa"></p></td>
									<th class="tc">예금주</th>
									<td class="tl"><p class="txt_04" id="gbankman"></p></td>
								</tr>
							</tbody>
						</table>
					</div>
					<!-- 테이블03(E) -->

					<div class="title_box mt30">
						<div class="fl">
							<h4 class="sub_tit_04">사업정보 및 재배품목</h4>
						</div>
					</div>
					<!-- 테이블03(S) -->
					<div class="table_type_03 mt5">
						<table>
							<caption>사업정보 및 재배품목</caption>
							<colgroup>
								<col style="width:10%">
								<col style="width:23.33%">
								<col style="width:10%">
								<col style="width:23.33%">
								<col style="width:10%">
								<col style="width:23.33%">
							</colgroup>
							<tbody>
								<tr>
									<th class="tc">사업자등록번호</th>
									<td class="tl"><p class="txt_04" id="copno"></p></td>
									<th class="tc">업태</th>
									<td class="tl"><p class="txt_04" id="upte"></p></td>
									<th class="tc">업종</th>
									<td class="tl"><p class="txt_04" id="jongmok"></p></td>
								</tr>
								<tr>
									<th class="tc">재배품목1</th>
									<td class="tl"><p class="txt_04" id="procode1"></p></td>
									<td class="tl" colspan="2"><p class="txt_04" id="proname1"></p></td>
									<td class="tl" colspan="2"><p class="txt_04" id="proarea1"></p></td>
								</tr>
								<tr>
									<th class="tc">재배품목2</th>
									<td class="tl"><p class="txt_04" id="procode2"></p></td>
									<td class="tl" colspan="2"><p class="txt_04" id="proname2"></p></td>
									<td class="tl" colspan="2"><p class="txt_04" id="proarea2"></p></td>
								</tr>
								<tr>
									<th class="tc">재배품목3</th>
									<td class="tl"><p class="txt_04" id="procode3"></p></td>
									<td class="tl" colspan="2"><p class="txt_04" id="proname3"></p></td>
									<td class="tl" colspan="2"><p class="txt_04" id="proarea3"></p></td>
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
			var chulCode = $("#chulCode").prop("value");
			var bunChk = $("#code option:selected").attr("data-bunchk");
			
			$.ajax({
				data:{
					code: chulCode,
					bunChk: bunChk
			        },
		        type : "POST",
		        url : "${pageContext.request.contextPath}/front/stat/marketShprInfo.json",
		        success : function(data){
		           		           
		       	   var bunName = "";
		           var result = data.result;
		        
		           
		           if(result.bunchk == 'N'){
		        	   bunName = "절화";
		           }else if(result.bunchk  == 'Y'){
		        	   bunName = "난";
		           }else if(result.bunchk  == 'C'){
		        	   bunName = "관엽";
		           }
		  		   
		           if(result.code != null){
		  			 $("#code").html(result.code);   
		  		   }
		           
		  		   $("#bunchknm").html(bunName);   
		  		   
		           if(result.name != null){
		  			 $("#name").html(result.name);   
		  		   }
		           if(result.grade != null){
		  			 $("#grade").html(result.grade);   
		  		   }
		           if(result.gcode != null){
		  			 $("#gcodegname").html(result.gcode + " " +result.gname);   
		  		   }
		           if(result.emailid != null){
		  			 $("#emailid").html(result.emailid);   
		  		   }
		           if(result.addr1 != null){
		  			 $("#addr1").html(result.addr1);   
		  		   }
		           if(result.dmsend != null){
		        	   if(result.dmsend == 'N'){
		        		   $("#dmsend").html("미발송");	   
		        	   }else{
		        		   $("#dmsend").html("발송");
		        	   }
		  		   }
		           if(result.yday != null){
		  			 $("#yday").html(result.yday);   
		  		   }
		           if(result.gpasswd != null){
		  			 $("#gpasswd").html(result.gpasswd);   
		  		   }
		           if(result.hzipno != null){
		  			 $("#hzipno").html(result.hzipno);   
		  		   }
		           if(result.hzippk != null){
		  			 $("#hzippk").html(result.hzippk);   
		  		   }
		           if(result.addrNew != null){
		  			 $("#haddr1").html(result.addrNew);   
		  		   }
		           if(result.haddr2 != null){
		  			 $("#haddr2").html(result.haddr2);   
		  		   }
		           if(result.htelno != null){
		  			 $("#htelno").html(result.htelno);   
		  		   }
		           if(result.handyphoneno != null){
		  			 $("#handyphoneno").html(result.handyphoneno);   
		  		   }
		           if(result.fzipno != null){
		  			 $("#fzipno").html(result.fzipno);   
		  		   }
		           if(result.fzippk != null){
		  			 $("#fzippk").html(result.fzippk);   
		  		   }
		           if(result.fAddrNew != null){
		  			 $("#faddr1").html(result.fAddrNew);   
		  		   }
		           if(result.faddr2 != null){
		  			 $("#faddr2").html(result.faddr2);   
		  		   }
		           if(result.ftelno != null){
		  			 $("#ftelno").html(result.ftelno);   
		  		   }
		           if(result.faxno != null){
		  			 $("#faxno").html(result.faxno);   
		  		   }
		           if(result.bankcode != null){
		  			 $("#bankcodebankname").html(result.bankcode + " " +result.bankname);   
		  		   }
		           if(result.kyeno != null){
		  			 $("#kyeno").html(result.kyeno);   
		  		   }
		           if(result.inname != null){
		  			 $("#inname").html(result.inname);   
		  		   }
		           if(result.gbankcode != null){
		  			 $("#gbankcodegbankname").html(result.gbankcode+ " " +result.gbankname);   
		  		   }
		           if(result.ggyejwa != null){
		  			 $("#ggyejwa").html(result.ggyejwa);   
		  		   }
		           if(result.gbankman != null){
		  			 $("#gbankman").html(result.gbankman);   
		  		   }
		           if(result.copno != null){
		  			 $("#copno").html(result.copno);   
		  		   }
		           if(result.upte != null){
		  			 $("#upte").html(result.upte);   
		  		   }
		           if(result.jongmok != null){
		  			 $("#jongmok").html(result.jongmok);   
		  		   }
		           if(result.procode1 != null){
		           
						if(result.bunchk == 'Y'){
			        	   if(result.procode1 == "6002"){
			        		   result.proname1 = "팔레높시스(호접란)"; 
			    		   } else if(result.procode1 == '6043'){
			    			   result.proname1 = "호접란(팔레높시스)";
			    		   }
						}
		        	
		  			 $("#procode1").html(result.procode1);   
		  		   }
		           if(result.proname1 != null){
		  			 $("#proname1").html(result.proname1);   
		  		   }
		           if(result.proarea1 != null){
		  			 $("#proarea1").html(result.proarea1);   
		  		   }
		           
		           if(result.procode2 != null){
		        	   
		        	   if(result.bunchk == 'Y'){
			        	   if(result.procode2 == "6002"){
			        		   result.proname2 = "팔레높시스(호접란)"; 
			    		   } else if(result.procode2 == '6043'){
			    			   result.proname2 = "호접란(팔레높시스)";
			    		   }
		        	   }
		        	   
		  			 $("#procode2").html(result.procode2);   
		  		   }
		           if(result.proname2 != null){
		  			 $("#proname2").html(result.proname2);   
		  		   }
		           if(result.proarea2 != null){
		  			 $("#proarea2").html(result.proarea2);   
		  		   }
		           
		           if(result.procode3 != null){
		        	 	
		        	   if(result.bunchk == 'Y'){
			        	   if(result.procode3 == "6002"){
			        		   result.proname3 = "팔레높시스(호접란)"; 
			    		   } else if(result.procode3 == '6043'){
			    			   result.proname3 = "호접란(팔레높시스)";
			    		   }
		        	   }
		        	 
		  			 $("#procode3").html(result.procode3);   
		  		   }
		           if(result.proname3 != null){
		  			 $("#proname3").html(result.proname3);   
		  		   }
		           if(result.proarea3 != null){
		  			 $("#proarea3").html(result.proarea3);   
		  		   }
		           
		           
		           
		           
			       
		        }
		    });
			
		}
			
			
			function fn_report(){
				var reportname = "marketShprInfo.ozr";
				var code = $("#chulCode").prop("value");
				
				$('input[name=reportname]').val(reportname);
				$('input[name=setUrl]').val("${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/front/stat/marketShprInfo.json?code="+code);
				
				$('#reportForm').submit();
			}
		</script>