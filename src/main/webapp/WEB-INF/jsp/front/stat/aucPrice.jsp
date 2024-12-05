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
<style>

.search_box .sb_line li{padding: 0 0 0 14px;}
.search_box .sb_line li .sb_title{padding: 0 14px 0 0;}
.table_devide:nth-child(1){margin-right: 5px;}
.table_devide label.radio{margin-left: 6px;}

@media screen and (max-width: 865px) {
.table_devide label.radio {width: 49%; display: inline-block; margin: 0; box-sizing: border-box;}
input[type="radio"] {top:4px !important;}
}

.loadingWrap { display:none; }
.loadArea { position:fixed; display:table; left:0; top:0; width:100%; height:100%; background:#000; background-color:rgba(0,0,0,0.5); z-index:100; }
.loadArea .loading { display:table-cell; width:100%; height:100%; text-align:center; vertical-align:middle; }
.lds-dual-ring { display: inline-block; width: 80px; height: 80px; }
.lds-dual-ring:after { content: " "; display: block; width: 64px; height: 64px; margin: 8px; border-radius: 50%; border: 6px solid #fff; border-color: #fff transparent #fff transparent; animation: lds-dual-ring 1.2s linear infinite; }

@keyframes lds-dual-ring {
    0% { transform: rotate(0deg); }
    100% { transform: rotate(360deg); }
}

input[type="checkbox"], 
input[type="radio"]{
	 display:inline;
}

input[type="radio"] {position:absolute; width:15px; height:15px; top:17px;}
input[type="radio"] + .rdo {position:absolute; width:0px; height:0px; overflow:hidden; /* background:url("../img/btn_radio_off.png") no-repeat center center; */ cursor:pointer; vertical-align:middle;}
input[type="radio"]:checked + .rdo {/* background:url("../img/btn_radio_on.png") no-repeat center center; */}

#mokCode {margin-bottom: 13px;}

</style>
			
			<!-- sub내용(S) -->
			<div class="sub_conts_in">

					
				<div class="info_box">
					<div class="title_box">
						<div class="fl">
							<h4 class="sub_tit_02">조건검색</h4>
							<p class="txt_w mt5"><span class="number">·</span>최근경매일자 : 
								<c:forEach items="${maxList}" var="result" varStatus="status">
									${result.bunName} ${result.maxPanDay} (${result.maxPanDy})<c:if test="${status.last eq false}">,</c:if>
								</c:forEach>
							</p>
						</div>
					</div>
					<!-- 검색조건창(S) -->
					<div class="condition_box bdtg2 mt10">
						<div class="search_box">
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
											<select id="mokCode" name="mokCode" title="품목선택">
												<option value=""></option>
											</select>
											<label for="mokCode"></label>
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
												<input type="radio" name="rr" checked="checked" onclick="setSearchDateInit2(0);">
												<i class="rdo"></i>
												<em class="label-title">당일</em>
											</label>
											<label class="radio">
												<input type="radio" name="rr" onclick="setSearchDateInit2(7);">
												<i class="rdo"></i>
												<em class="label-title">최근 7일</em>
											</label>
											<label class="radio">
												<input type="radio" name="rr" onclick="setSearchDateInit2(15);">
												<i class="rdo"></i>
												<em class="label-title">최근 15일</em>
											</label>
											<label class="radio">
												<input type="radio" name="rr" onclick="setSearchMonthInit2(1);">
												<i class="rdo"></i>
												<em class="label-title">최근 1개월</em>
											</label>
											<label class="radio">
												<input type="radio" name="rr" onclick="setSearchMonthInit2(3);">
												<i class="rdo"></i>
												<em class="label-title">최근 3개월</em>
											</label>
											<label class="radio">
												<input type="radio" name="rr" onclick="setSearchMonthInit2(6);">
												<i class="rdo"></i>
												<em class="label-title">최근 6개월</em>
											</label>
											<label class="radio">
												<input type="radio" name="rr" onclick="setSearchMonthInit2(12);">
												<i class="rdo"></i>
												<em class="label-title">최근 1년</em>
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
													<input type="text" id="startDate2" class="datepicker" onclick="customInputCheck();" title="시작날짜">
												</div>
												<span class="hyphen">~</span>
												<div class="date_box">
													<input type="text" id="endDate2" class="datepicker" onclick="customInputCheck();" title="종료날짜">
												</div>												
											</div>
											<p class="txt_01 dib"></p>
										</div>
									</div>
								</li>
							</ul>
							<a href="javascript:fn_search1();" class="btn_search">조회</a>
						</div>
						<p class="txt_w mt10">* 해당 기간내의 품목별 거래량과 시세(최고가, 최저가, 평균가)를 나타냅니다.</p>
						<p class="txt_w">화훼유통정보시스템(https://flower.at.or.kr/)에서 더 자세한 정보를 조회하실 수 있습니다.(등급별 시세, 유찰물량 등)</p>
					</div>
					<!-- 검색조건창(E) -->

				</div>

				
				


				<!-- 조회결과(S) -->
				<form>
					<!-- 타이틀(S) -->
					<div class="title_box mt30">
						<div class="fl">
							<h4 class="sub_tit_04 mt10">부류 : <b id="bunName"></b></h4>
						</div>
						<div class="fr">
							<div class="icon-box tr">
								<span class="guide-icon width-scroll"></span>
							</div>
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
					<div class="table_type_01 mt10 overflow_a">
						<table>
							<caption>품종코드, 품목명, 품종명, 거래량, 최고가, 최저가, 평균가 정보를 제공하는 경매시세 조회 결과 테이블입니다.</caption>
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
									<th scope="col">품종코드</th>
									<th scope="col">품목명</th>
									<th scope="col">품종명</th>
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
				</form>
				<!-- 조회결과(E) -->
				
				<!-- 타이틀(S) -->
				<div class="title_box mt30">
					<div class="fl">
						<h4 class="sub_tit_02">자료갱신 일시</h4>
					</div>
				</div>
				<!-- 타이틀(E) -->
					
				<!-- 테이블(S) -->
				<div class="table_type_01 mt10">
					<table>
						<caption>구분, 경매일, 자료제공일시, 평균제공시간 정보를 제공하는 자료갱신 일지 테이블입니다.</caption>
						<colgroup>
							<col width="25%">
							<col width="25%">
							<col width="25%">
							<col width="25%">
						</colgroup>
						<thead>
							<tr>
								<th scope="col">구분</th>
								<th scope="col">경매일</th>
								<th scope="col">자료제공일시</th>
								<th scope="col">평균제공시간</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><p class="txt_03">절화</p></td>
								<td><p class="txt_03">매주 월~토요일 (주6일)</p></td>
								<td><p class="txt_03">경매 일마감과 동시</p></td>
								<td><p class="txt_03">당일 9시이후</p></td>
							</tr>
							<tr>
								<td><p class="txt_03">난</p></td>
								<td><p class="txt_03">매주 월, 목요일 (주2일)</p></td>
								<td><p class="txt_03">경매 일마감과 동시</p></td>
								<td><p class="txt_03">익일 오전 10~11시</p></td>
							</tr>
							<tr>
								<td><p class="txt_03">관엽</p></td>
								<td><p class="txt_03">매주 화, 금요일 (주2일)</p></td>
								<td><p class="txt_03">경매 일마감과 동시</p></td>
								<td><p class="txt_03">익일 오전 10~11시</p></td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- 테이블(E) -->

				<p class="txt_w mt10">* 휴일 안내 : 매주 일요일, 신정(1월 1일), 설날 및 추석연휴</p>
				<p class="txt_w">* 월간, 연간 자료는 회원 가입후 검색 가능 합니다.</p>

				<!-- 타이틀(S) -->
				<div class="title_box mt30">
					<div class="fl">
						<h4 class="sub_tit_02">보는 방법</h4>
					</div>
				</div>
				<!-- 타이틀(E) -->
				
				<!-- 내용(S) -->
				<div class="warn_box mt10">
					<p class="txt_w"><span class="number">·</span>위 조건식의 화훼구분, 품목코드, 경매일자를 선택하시고 "조회" 버튼을 누르세요.</p>
					<p class="txt_w mt20"><span class="number">·</span> 경매 단위 및 시세</p>
					<p class="txt_w pdl20">(1) 절화(생화)는 1속당 경매를 하며, 시세는 1속당 가격임.</p>
					<p class="txt_w pdl20">- 품목별 1속당 송이수는 국립농산물품질관리원 사이트의 농산물표준규격을 참고하세요.</p>
					<p class="txt_w mt10 pdl20">(2) 분화는(난,관엽) 1분당 경매를 하며, 시세는 1분당 가격임.</p>
					<p class="txt_w pdl20">- 난의 품종명 뒤의 숫자는 꽃대의 수임</p>
					<p class="txt_w pdl20">- 분화(관엽) 품종명 뒤의 숫자는 분의 지름 크기를 말합니다.</p>
				</div>
				<!-- 내용(E) -->

				<!-- 타이틀(S) -->
				<div class="title_box mt30">
					<div class="fl">
						<h4 class="sub_tit_02">주의사항</h4>
					</div>
				</div>
				<!-- 타이틀(E) -->

				<!-- 내용(S) -->
				<div class="warn_box mt10">
					<p class="txt_w"><span class="number">·</span>화훼류경매시세는 양재동 화훼공판장 중도매인들이 전자식 경매를 통하여 낙찰받은 물량의 가격이며, 중도매인들의 제비용 등이 전혀 포함되어 있지 않으므로 도소매 구입금액과 차이가 있을 수 있습니다.</p>
					<p class="txt_w mt10"><span class="number">·</span><span class="color_o">절화류 화목토 요일은 작은 장인</span> 관계로 거래량이 저조하기 때문에 적정 가격형성이 되지 않습니다.
						따라서 화,목,토요일 경매시세는 제공되지 않으며 시세자료 비교시는 거래량이 많은 <span class="color_o">큰 장인 월,수,금요일</span> 자료를 참조하시기 바랍니다.</p>
				</div>
				<!-- 내용(E) -->
				
			</div>
			<!-- sub내용(E) -->
 
		
		<script type="text/javascript">
		var currentDate = "";
		
		$(document).ready(function(){
			setSearchDateInit2(0);
			currentDate = $("#endDate2").val();
			getMokCode();
		});
		
		
		function getMokCode(){			
			var fixType = "fix"+$("input:radio[name=bunChk]:checked").val()+"Auc";
			
			//fixCAuc
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
		        placeholder: "전체",
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
		    
			// 웹 접근성
		    /* $.ajax ({
				url: contextPath+'/front/'+fixType+'/moklist.json',
				type: 'POST',
				dataType: 'json',
				data: {
				},
				complete: function (data) {
					$("#mokCode").empty();
					
					var option = "<option value='' title='전체'>전체</option>";
					
					$("#mokCode").append(option);
					
					for(idx in data.responseJSON.results) {						
						var result = data.responseJSON.results[idx];
						
						if(fixType == 'fixYAuc'){
							if(result.id == '6002'){
								result.text = '팔레높시스(호접란)';
							}else if(result.id == '6043'){
								result.text = '호접란(팔레높시스)';
							}
						}
						
						var option = "<option value=" + result.id + " title=" + result.text + ">" + result.text + "</option>";
						
						$("#mokCode").append(option);
					}
				}
			}); */
			
			
		}

		function fn_search(){
			
		}
		
		
		
		function fn_search1(){
			$(".loadingWrap").show();
			
			var startDate = $("#startDate2").val();
			var endDate = $("#endDate2").val();
			var bunChk = $("input:radio[name=bunChk]:checked").val();
			var mokCode = $("#mokCode").prop("value");
			/*
			if(mokCode == null || mokCode == ''){
				return false;
			}*/
			
			$.ajax({
				data:{
					startDate: startDate,
					endDate: endDate,
					bunChk: bunChk,
					mokCode: mokCode
			        },
		        type : "POST",
		        url : "${pageContext.request.contextPath}/front/stat/aucPrice.json",
		        success : function(data){
		        	console.log(data);
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
		           
		           if(resultList.length > 0){
		        	   
		        	   var sumpSokCnt = 0;
			           var summaxKmPNew = 0;
			           var summinKmPNew = 9999999999;
			           var sumavgKmPNew = 0;
		        	   
		        	   for(var i=0; i<resultList.length; i++ ){
		        		   var pumCode = "";
		        		   var pMokName = "";
		        		   var pJongName = "";
		        		   
		        		   
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
			        		   if(pumCode.substring(0,4) == '6002'){
			        			   pMokName = '팔레높시스(호접란)';
					           }else if(pumCode.substring(0,4) == '6043'){
					        	   pMokName = '호접란(팔레높시스)';
							   }
		        		   }
		        		   
		        		    html+="<tr>                                                                   ";
							html+="	<td class=\"tc\"><p class=\"txt_01\">"+pumCode+"</p></td>  ";
							html+="	<td class=\"tc\"><p class=\"txt_01\">"+pMokName+"</p></td>  ";
							html+="	<td class=\"tl\"><p class=\"txt_01\">"+pJongName+"</p></td>  ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].pSokCnt)+"</p></td>  ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].maxKmPNew)+"</p></td>  ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].minKmPNew)+"</p></td>  ";
							html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(resultList[i].avgKmPNew)+"</p></td>  ";
							html+="</tr>                                                                  ";
							
							sumpSokCnt = sumpSokCnt + resultList[i].pSokCnt;
							
							if(summaxKmPNew < resultList[i].maxKmPNew){
								summaxKmPNew = resultList[i].maxKmPNew;
							}
							if(summinKmPNew > resultList[i].minKmPNew){
								summinKmPNew = resultList[i].minKmPNew;
							}
							
							sumavgKmPNew = sumavgKmPNew + (resultList[i].pSokCnt*resultList[i].avgKmPNew);
							
		        	   }
		        	   
		        	   sumavgKmPNew = Math.floor(sumavgKmPNew/sumpSokCnt);
		        	   
		        	    html+="<tr>                                                                   ";
						html+="	<td class=\"tc\" colspan=\"3\"><p class=\"txt_01\">합계</p></td>  ";
						html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(sumpSokCnt)+"</p></td>  ";
						html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(summaxKmPNew)+"</p></td>  ";
						html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(summinKmPNew)+"</p></td>  ";
						html+="	<td class=\"tr\"><p class=\"txt_01\">"+comma(sumavgKmPNew)+"</p></td>  ";
						html+="</tr>                                                                  ";
		        	   
		           }else{
		        	   html += "<tr><td class=\"tc\" colspan=\"7\">데이터가 없습니다.</td></tr>";
		           }
		           
		           $("#resultTbody").html(html);
		           
		           $(".loadingWrap").hide();
		        }
		    });
			
		}
		
		function setSearchDateInit2(dayCnt){
			
			var endDate = new Date(); 
			var strDate = new Date();
			
			strDate.setDate(endDate.getDate()-dayCnt);
			
			var strYear = strDate.getFullYear(); 
			var strMonth = new String(strDate.getMonth()+1); 
			var strDay = new String(strDate.getDate()); 
			
			if(strMonth.length == 1){ 
				strMonth = "0" + strMonth; 
			} 
			
			if(strDay.length == 1){ 
				strDay = "0" + strDay; 
			} 
				
			$("#startDate2").datepicker("setDate",strYear+"-"+strMonth+"-"+strDay);
			
			
			var endYear = endDate.getFullYear(); 
			var endMonth = new String(endDate.getMonth()+1); 
			var endDay = new String(endDate.getDate()); 
			
			if(endMonth.length == 1){ 
				endMonth = "0" + endMonth; 
			} 
			
			if(endDay.length == 1){ 
				endDay = "0" + endDay; 
			} 
				
			$("#endDate2").datepicker("setDate",endYear+"-"+endMonth+"-"+endDay);
			
			fn_search();
			
		}
		
		function setSearchMonthInit2(monthCnt){
			
			var endDate = new Date(); 
			var strDate = new Date();
			
			strDate.setMonth(endDate.getMonth()-monthCnt);
			
			var strYear = strDate.getFullYear(); 
			var strMonth = new String(strDate.getMonth()+1); 
			var strDay = new String(strDate.getDate()); 
			
			if(strMonth.length == 1){ 
				strMonth = "0" + strMonth; 
			} 
			
			if(strDay.length == 1){ 
				strDay = "0" + strDay; 
			} 
			
			$("#startDate2").datepicker("setDate",strYear+"-"+strMonth+"-"+strDay);
			
			var endYear = endDate.getFullYear(); 
			var endMonth = new String(endDate.getMonth()+1); 
			var endDay = new String(endDate.getDate());
			
			if(endMonth.length == 1){ 
				endMonth = "0" + endMonth; 
			} 
			
			if(endDay.length == 1){ 
				endDay = "0" + endDay; 
			} 
				
			$("#endDate2").datepicker("setDate",endYear+"-"+endMonth+"-"+endDay);
			
			fn_search();
			
		}
		
		$(document).ready(function(){
			$("#startDate2").change(function() {
			    var startDate = $("#startDate2").val();
			    var endDate = $("#endDate2").val();
			    
			    var sDate = new Date(startDate);
			    var eDate = new Date(endDate);
			    
			    if(sDate > eDate ){
			    	alert("시작일자가 종료일자 이후입니다.");
			    	$("#startDate2").datepicker("setDate",endDate);
			    	return false;
			    }
			    
			    eDate.setFullYear(eDate.getFullYear()-1);
			    
			    var strYear = eDate.getFullYear(); 
				var strMonth = new String(eDate.getMonth()+1); 
				var strDay = new String(eDate.getDate()); 
				
				if(strMonth.length == 1){ 
					strMonth = "0" + strMonth; 
				} 
				
				if(strDay.length == 1){ 
					strDay = "0" + strDay; 
				} 
			    
			    if(sDate < eDate){
			    	alert("조회기간은 시작일자와 종료일자가 1년 이내에서만 가능합니다. \n 종료일자는 선택한 시작일자로 변경됩니다.");
			    	//$("#startDate2").datepicker("setDate",strYear+"-"+strMonth+"-"+strDay);
			    	$("#endDate2").datepicker("setDate",startDate);
			    	$("#customInput").focus();
			    }
			 })
			 
			 
			 $("#endDate2").change(function() {
			    var startDate = $("#startDate2").val();
			    var endDate = $("#endDate2").val();
			    
			    var sDate = new Date(startDate);
			    var eDate = new Date(endDate);
			    
			    if(sDate > eDate ){
			    	alert("종료일자가 시작일자 이전입니다.");
			    	$("#endDate2").datepicker("setDate",startDate);
			    	return false;
			    }
			    
			    
			    sDate.setFullYear(sDate.getFullYear()+1);
			    
			    var endYear = sDate.getFullYear(); 
				var endMonth = new String(sDate.getMonth()+1); 
				var endDay = new String(sDate.getDate()); 
				
				if(endMonth.length == 1){ 
					endMonth = "0" + endMonth; 
				} 
				
				if(endDay.length == 1){ 
					endDay = "0" + endDay; 
				} 
			    
			    if(sDate < eDate){
			    	alert("조회기간은 시작일자와 종료일자가 1년 이내에서만 가능합니다.");
			    	$("#endDate2").datepicker("setDate",endYear+"-"+endMonth+"-"+endDay);
			    	$("#customInput").focus();
			    }
			 })
		})
		
		
		
		</script>
		
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
