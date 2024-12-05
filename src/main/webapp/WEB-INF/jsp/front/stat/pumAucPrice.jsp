<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    
<link href="${pageContext.request.contextPath}/assets/css/plugins/select2/select2.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/plugins/select2/select2-bootstrap4.min.css" rel="stylesheet">
<!-- Select2 -->
<script src="${pageContext.request.contextPath}/assets/js/plugins/select2/select2.full.min.js"></script>
<style type="text/css">

.loadingWrap { display:none; }
.loadArea { position:fixed; display:table; left:0; top:0; width:100%; height:100%; background:#000; background-color:rgba(0,0,0,0.5); z-index:100; }
.loadArea .loading { display:table-cell; width:100%; height:100%; text-align:center; vertical-align:middle; }
.lds-dual-ring { display: inline-block; width: 80px; height: 80px; }
.lds-dual-ring:after { content: " "; display: block; width: 64px; height: 64px; margin: 8px; border-radius: 50%; border: 6px solid #fff; border-color: #fff transparent #fff transparent; animation: lds-dual-ring 1.2s linear infinite; }

@keyframes lds-dual-ring {
    0% { transform: rotate(0deg); }
    100% { transform: rotate(360deg); }
}

</style>


<!-- sub-conts(S) -->
		<div class="sub_conts bgw">

			<!-- sub상단(S) -->
			<div class="sub_top">
				<h2 class="sub_title">·품목/품종별 경매시세·</h2>

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
							<h4 class="sub_tit_02">조건검색</h4>
						</div>
					</div>
					<!-- 검색조건창(S) -->
					<div class="condition_box bdtg2 mt10">
						<div class="search_box">
							<ul class="sb_line">
								<li>
									<p class="sb_title bold">조회구분</p>
									<div class="sb_data">
										<div class="table_devide">
											<label class="radio">
												<input type="radio" name="searchType" value="A" checked="checked" onchange="searchTypeChange();">
												<i class="rdo"></i>
												<em class="label-title">품목별</em>
											</label>
											<label class="radio">
												<input type="radio" name="searchType" value="B" onchange="searchTypeChange();">
												<i class="rdo"></i>
												<em class="label-title">품종별</em>
											</label>
										</div>
									</div>
								</li>
							</ul>
							<ul class="sb_line">
								<li>
									<p class="sb_title bold">화훼부류</p>
									<div class="sb_data">
										<div class="table_devide">
											<label class="radio">
												<input type="radio" name="bunChk" value="N" checked="checked" onchange="getMokCode();">
												<i class="rdo"></i>
												<em class="label-title">절화</em>
											</label>
											<label class="radio">
												<input type="radio" name="bunChk" value="Y" onchange="getMokCode();">
												<i class="rdo"></i>
												<em class="label-title">난</em>
											</label>
											<label class="radio">
												<input type="radio" name="bunChk" value="C" onchange="getMokCode();">
												<i class="rdo"></i>
												<em class="label-title">관엽</em>
											</label>
										</div>
									</div>
								</li>
							</ul>
							<ul class="sb_line" style="font-size: 100% !important;">
								<li>
									<p class="sb_title bold">품목</p>
									<div class="sb_data fr">
										<div class="sel_type_01 w150 mt5">
											<input type="hidden" name="mokName" id="mokName" value="">
											<select id="mokCode" name="mokCode" onchange="getPumList();">
												<option value=""></option>
											</select>
											<label for="mokCode"></label>
										</div>
									</div>
								</li>
							</ul>
							<ul class="sb_line" style="font-size: 100% !important;" id="pumCodeArea">
								<li>
									<p class="sb_title bold">품종</p>
									<div class="sb_data fr">
										<div class="sel_type_01 w150 mt5">
											<input type="hidden" name="jongName" id="jongName" value="">
											<select id="pumCode" name="pumCode">
												<option value=""></option>
											</select>
											<label for="pumCode"></label>
										</div>
									</div>
								</li>
							</ul>
							<ul class="sb_line">
								<li>
									<p class="sb_title bold">경매일자</p>
									<div class="sb_data">
										<div class="table_devide">
											<label class="radio">
												<input type="radio" name="rr" checked="checked" onclick="setSearchDateInit(0);">
												<i class="rdo"></i>
												<em class="label-title">당일</em>
											</label>
											<label class="radio">
												<input type="radio" name="rr" onclick="setSearchDateInit(7);">
												<i class="rdo"></i>
												<em class="label-title">최근 7일</em>
											</label>
											<label class="radio">
												<input type="radio" name="rr" onclick="setSearchDateInit(10);">
												<i class="rdo"></i>
												<em class="label-title">최근 10일</em>
											</label>
											<label class="radio">
												<input type="radio" name="rr" onclick="setSearchDateInit(15);">
												<i class="rdo"></i>
												<em class="label-title">최근 15일</em>
											</label>
											<label class="radio">
												<input type="radio" name="rr" onclick="setSearchDateInit(30);">
												<i class="rdo"></i>
												<em class="label-title">최근 1개월</em>
											</label>
											<label class="radio">
												<input type="radio" name="rr" id="customInput">
												<i class="rdo"></i>
												<em class="label-title">직접입력</em>
											</label>
										</div>
										<div class="table_devide">
											<div class="date_from_to">
												<div class="date_box">
													<input type="text" id="startDate" class="datepicker" onclick="customInputCheck();">
												</div>
												<span class="hyphen">~</span>
												<div class="date_box">
													<input type="text" id="endDate" class="datepicker" onclick="customInputCheck();">
												</div>												
											</div>
											<p class="txt_01 dib"></p>
										</div>
									</div>
								</li>
							</ul>
							<a href="javascript:fn_search1();" class="btn_search">조회</a>
						</div>
					</div>
					<!-- 검색조건창(E) -->

				</div>

				
				


				<!-- 조회결과(S) -->
				<form>
					<!-- 타이틀(S) -->
					<div class="title_box mt30">
						<div class="fl">
							<h4 class="sub_tit_04 mt20">부류 : <b id="bunName"></b></h4>
						</div>
						<div class="fr">
							<div class="icon-box tr">
								<span class="guide-icon width-scroll"></span>
							</div>
							<a href="javascript:fn_report();" id="btnReport" class="btn_type_01 ml10">리포트</a>
						</div>
					</div>
					<div class="title_box">
						<div class="fl">
							<h4 class="sub_tit_04">조회일자 : <b id="searchDate"></b></h4>
						</div>
						<div class="fr">
							<h4 class="txt_unit">(단위 : 속(분), 원/속(분))</h4>
						</div>
					</div>
					<!-- 타이틀(E) -->

					<!-- 테이블(S) -->
					<div class="table_type_01 mt10 overflow_a" id="tableArea1">
						<table>
							<caption>조회결과</caption>
							<colgroup>
								<col width="80px;">
								<col width="100px;">
								<col width="100px;">
								<col width="100px;">
								<col width="100px;">
								<col width="100px;">
								<col width="100px;">
							</colgroup>
							<thead>
								<tr>
									<th scope="col">일자</th>
									<th scope="col">품목명</th>
									<th scope="col">품목코드</th>
									<th scope="col">거래량</th>
									<th scope="col">최고가</th>
									<th scope="col">최저가</th>
									<th scope="col">평균가</th>
								</tr>
							</thead>
							<tbody id="resultTbody">
								<tr><td class="tc" colspan="7">조회버튼을 누르세요.</td></tr>
							</tbody>
						</table>
					</div>
					
					<div class="table_type_01 mt10 overflow_a" id="tableArea2">
						<table>
							<caption>조회결과</caption>
							<colgroup>
								<col width="80px;">
								<col width="100px;">
								<col width="100px;">
								<col width="100px;">
								<col width="100px;">
								<col width="100px;">
								<col width="100px;">
								<col width="100px;">
							</colgroup>
							<thead>
								<tr>
									<th scope="col">일자</th>
									<th scope="col">품목명</th>
									<th scope="col">품종명</th>
									<th scope="col">품종코드</th>
									<th scope="col">거래량</th>
									<th scope="col">최고가</th>
									<th scope="col">최저가</th>
									<th scope="col">평균가</th>
								</tr>
							</thead>
							<tbody id="resultTbody2">
								<tr><td class="tc" colspan="8">조회버튼을 누르세요.</td></tr>
							</tbody>
						</table>
					</div>
				</form>
				<!-- 조회결과(E) -->
			</div>
			<!-- sub내용(E) -->
			
			<form id="reportForm" action="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/oz80/report.jsp" method="post" target="_blank">
				<input type="hidden" name="reportname" value="">
				<input type="hidden" name="setUrl" value="">
				<input type="hidden" name="hostUrl" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/oz80/server">
			</form>
			
		</div>
		<!-- sub-conts(E) -->  
		
		<!-- wrap(E) -->		
		<div class="loadingWrap">
		    <div class="loadArea">
		        <div class="loading">
		        	<div class="lds-dual-ring"></div>
		        	<div class="text" id="procText" style="color: white;">
			        	처리중입니다.
			        </div>
		        </div>
		    </div>
		</div>
		
		<script type="text/javascript">
		var currentDate = "";
		
		$(document).ready(function(){
			$("#btnReport").css("display", "none");
			
			setSearchDateInit(0);
			currentDate = $("#endDate").val();
			getMokCode();
			searchTypeChange();
			$("#pumCode").select2({
				theme: 'bootstrap4',
		        placeholder: "품종을 선택하세요.",
		        allowClear: true,
		        language: {
		        	noResults: function(){
		                return "품목을 먼저 선택하세요.";
		            },
		            searching: function () {
		                return '검색중…';
		            },
		            inputTooShort: function(){
		                return "검색어를 입력하세요";
		            }
		        },
		        width:'100%'
		    });
			
		});
		
		
		function getMokCode(){
			var fixType = "fix"+$("input:radio[name=bunChk]:checked").val()+"Auc";
			
			$("#mokCode").select2({
				ajax: {
					url: contextPath+'/front/'+fixType+'/moklist.json',
					dataType: 'json',
					delay: 250,
					cache: true,
					// 검색 쿼리를 만든다.
					data: function (params) {
						return {
							searchKeyWord : params.term
						};
					},
					// 결과를 표현한다.
					processResults: function (data) {
					
						if(fixType == 'fixYAuc'){
							for(var i=0; i < data.results.length; i++ ){
								if(data.results[i].id == '6002'){
									data.results[i].text = '팔레높시스(호접란)';
								}else if(data.results[i].id == '6043'){
									data.results[i].text = '호접란(팔레높시스)';
								}
							}
						}
						
						return {
							results: data.results
						};
					}
				},
		        theme: 'bootstrap4',
		        placeholder: "품목을 선택하세요.",
		        allowClear: true,
		        language: {
		        	noResults: function(){
		                return "검색결과가없습니다.";
		            },
		            searching: function () {
		                return '검색중…';
		            },
		            inputTooShort: function(){
		                return "검색어를 입력하세요";
		            }
		        }
		        //minimumInputLength: 1
		    });
			
			
		}
		
		function fn_search(){
			
		}
		
		function fn_search1(){
			$(".loadingWrap").show();
			var searchType = $("input:radio[name=searchType]:checked").val();
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			var bunChk = $("input:radio[name=bunChk]:checked").val();
			var mokCode = $("#mokCode").prop("value");
			var pumCode = $("#pumCode").prop("value");
			var sMokCode = mokCode;
			
			$.ajax({
				data:{
					searchType: searchType,
					startDate: startDate,
					endDate: endDate,
					bunChk: bunChk,
					mokCode: mokCode,
					pumCode: pumCode
			        },
		        type : "POST",
		        url : "${pageContext.request.contextPath}/front/stat/pumAucPriceList.json",
		        success : function(data){
		        	
		           var html = "";
		           var fhtml = "";
		           var resultList = data.resultList;
		           
		           var bunName = "";
		           
		           if(bunChk == 'N'){
		        	   bunName = "절화";
		           }else if(bunChk == 'Y'){
		        	   bunName = "난";
		           }else if(bunChk == 'C'){
		        	   bunName = "관엽";
		           }
		           
		           $("#bunName").html(bunName);
		           $("#searchDate").html(startDate + " ~ " + endDate);
		           
		           if(searchType == "A"){
		        	   if(resultList.length > 0){
		        		   $("#btnReport").css("display", "");
			        	   
			        	   for(var i=0; i<resultList.length; i++ ){
			        		   var pmDay = "소계";
			        		   var pumCode = "";
			        		   var mokCode = "";
			        		   var pMokName = "";
			        		   var pJongName = "";
			        		   
			        		   if(resultList[i].pmDay != null){
			        			   pmDay = resultList[i].pmDay;
			        		   }
			        		   if(resultList[i].mokCode != null){
			        			   mokCode = resultList[i].mokCode;
			        		   }
			        		   if(resultList[i].pumCode != null){
			        			   pumCode = resultList[i].pumCode;
			        		   }
			        		   if(resultList[i].pMokName != null){
			        			   pMokName = resultList[i].pMokName;
			        		   }
			        		   if(resultList[i].pJongName != null){
			        			   pJongName = resultList[i].pJongName;
			        		   }
			        		   
			        		   
			        		   if(bunChk == 'Y'){
				        		   if(sMokCode == '6002' || (sMokCode == '' &&  mokCode == '6002')){
						        	   pMokName = '팔레높시스(호접란)';
						        	   mokCode = '6002(6043)';
						           }else if(sMokCode == '6043' || (sMokCode == '' &&  mokCode == '6043')){
						        	   pMokName = '호접란(팔레높시스)';
						        	   mokCode = '6043(6002)';
								   }
							   }
			        		   
			        		    html+="<tr>                                                                   ";
								html+="	<td class=\"tc\"><p class=\"txt_01\">"+pmDay+"</p></td>  ";
								html+="	<td class=\"tc\"><p class=\"txt_01\">"+pMokName+"</p></td>  ";
								html+="	<td class=\"tc\"><p class=\"txt_01\">"+mokCode+"</p></td>  ";
								html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].sokCnt)+"</p></td>  ";
								html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].topAmount)+"</p></td>  ";
								html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].botAmount)+"</p></td>  ";
								html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].sum)+"</p></td>  ";
								html+="</tr>                                                                  ";
								
								
			        	   }
			        	   
			           }else{
			        	   $("#btnReport").css("display", "none");
			        	   
			        	   html += "<tr><td class=\"tc\" colspan=\"7\">데이터가 없습니다.</td></tr>";
			           }
			           
			           $("#resultTbody").html(html);
		           }else{
						if(resultList.length > 0){
							$("#btnReport").css("display", "");
							
			        	   for(var i=0; i<resultList.length; i++ ){
			        		   var pmDay = "소계";
			        		   var pumCode = "";
			        		   var mokCode = "";
			        		   var pMokName = "";
			        		   var pJongName = "";
			        		   
			        		   if(resultList[i].pmDay != null){
			        			   pmDay = resultList[i].pmDay;
			        		   }
			        		   if(resultList[i].mokCode != null){
			        			   mokCode = resultList[i].mokCode;
			        		   }
			        		   if(resultList[i].pmCode != null){
			        			   pumCode = resultList[i].pmCode;
			        		   }
			        		   if(resultList[i].pMokName != null){
			        			   pMokName = resultList[i].pMokName;
			        		   }
			        		   if(resultList[i].pJongName != null){
			        			   pJongName = resultList[i].pJongName;
			        		   }
			        		   
			        		   if(bunChk == 'Y'){
				        		   if(pumCode.substring(0,4) == '6002'){
						        	   pMokName = '팔레높시스(호접란)';
						           }else if(pumCode.substring(0,4) == '6043'){
						        	   pMokName = '호접란(팔레높시스)';
								   }
			        		   }
			        		   
			        		    html+="<tr>                                                                   ";
								html+="	<td class=\"tc\"><p class=\"txt_01\">"+pmDay+"</p></td>  ";
								html+="	<td class=\"tc\"><p class=\"txt_01\">"+pMokName+"</p></td>  ";
								html+="	<td class=\"tc\"><p class=\"txt_01\">"+pJongName+"</p></td>  ";
								html+="	<td class=\"tc\"><p class=\"txt_01\">"+pumCode+"</p></td>  ";
								html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].sokCnt)+"</p></td>  ";
								html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].topAmount)+"</p></td>  ";
								html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].botAmount)+"</p></td>  ";
								html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].sum)+"</p></td>  ";
								html+="</tr>                                                                  ";
								
								
			        	   }
			        	   
			           }else{
			        	   $("#btnReport").css("display", "none");
			        	   
			        	   html += "<tr><td class=\"tc\" colspan=\"8\">데이터가 없습니다.</td></tr>";
			           }
			           
			           $("#resultTbody2").html(html);
		           }
		           
		           $(".loadingWrap").hide();
		        }
		    });
			
		}
		
		
		function getPumList(recentCheck, recentPumCode){
			var pMokCode = $("#mokCode").prop("value");
			var fixType = "fix"+$("input:radio[name=bunChk]:checked").val()+"Auc";
			var pMokCodeArr = new Array();
			
			$("#pumCode").html("");
			
			if(pMokCode == ""){
				
				$("#pumCode").select2({
					theme: 'bootstrap4',
			        placeholder: "품종을 선택하세요.",
			        allowClear: true,
			        language: {
			        	noResults: function(){
			                return "품목을 먼저 선택하세요.";
			            },
			            searching: function () {
			                return '검색중…';
			            },
			            inputTooShort: function(){
			                return "검색어를 입력하세요";
			            }
			        },
			        width:'100%'
			    });
				
			}else{
				
				if( fixType == 'fixYAuc' && (pMokCode == "6002" || pMokCode == "6043") ){
					pMokCodeArr.push("6002");
					pMokCodeArr.push("6043");
					pMokCode = "";
				}
				
				$("#pumCode").select2({
					ajax: {
						url: contextPath+'/front/'+fixType+'/pumlist.json',
						dataType: 'json',
						delay: 250,
						cache: true,
						// 검색 쿼리를 만든다.
						data: function (params) {
							return {
								searchKeyWord : params.term,
								pMokCode : pMokCode,
								pMokCodeArr : pMokCodeArr
							};
						},
						// 결과를 표현한다.
						processResults: function (data) {
							return {
								results: data.results
							};
						}
					},
			        theme: 'bootstrap4',
			        placeholder: "품종을 선택하세요.",
			        allowClear: true,
			        language: {
			        	noResults: function(){
			                return "검색결과가없습니다.";
			            },
			            searching: function () {
			                return '검색중…';
			            },
			            inputTooShort: function(){
			                return "검색어를 입력하세요";
			            }
			        },
			        width:'100%'
			        //minimumInputLength: 1
			    });	
			}
		}

		function searchTypeChange(){
			var searchType = $("input:radio[name=searchType]:checked").val();
			if(searchType == "A"){
				$("#tableArea1").css("display","");
				$("#tableArea2").css("display","none");
				$("#pumCodeArea").css("display","none");
			}else{
				$("#tableArea1").css("display","none");
				$("#tableArea2").css("display","");
				$("#pumCodeArea").css("display","");
			}
		}
		

		$(function() {
			$('#pumCode').change(function() {
				if($("input[name='bunChk']:checked").val() == "Y" && $("input[name='searchType']:checked").val() == "B"){
					var pumCode = $("#pumCode").prop("value");
					if(pumCode.substring(0,4) == '6002'){
						$("#mokCode").html("<option value=\"6002\">팔레높시스(호접란)</option>");
						$("#mokName").val("팔레높시스");
					}else if (pumCode.substring(0,4) == '6043') {
						$("#mokCode").html("<option value=\"6043\">호접란(팔레높시스)</option>");
						$("#mokName").val("호접란");
					}
				}
			});
		});
		
		
		function fn_report(){
			var reportname = "";
			var searchType = $("input:radio[name=searchType]:checked").val();
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			var bunChk = $("input:radio[name=bunChk]:checked").val();
			var mokCode = $("#mokCode").prop("value");
			var pumCode = $("#pumCode").prop("value");
			
			if(searchType == "A"){
				reportname = "pumAucPriceList_A.ozr";
			}else{
				reportname = "pumAucPriceList_B.ozr";
			}
			
			$('input[name=reportname]').val(reportname);
			$('input[name=setUrl]').val("${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/front/stat/pumAucPriceList.json?startDate="+startDate+"&endDate="+endDate+"&bunChk="+bunChk+"&searchType="+searchType+"&mokCode="+mokCode+"&pumCode="+pumCode);
			
			$('#reportForm').submit();
		}
		
		</script>