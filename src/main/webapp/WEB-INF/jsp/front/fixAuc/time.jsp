<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<style>
.tc{overflow: hidden;}
</style>
<script type="text/javascript">

	$(document).ready(function(){
		
		for (var i = 1; i <= 7; i++){
			
			$("#timeFrom1_" + i).timepicker({
				step: 1,            //시간간격 : 1분
				timeFormat: "H:i"    //시간:분 으로표시
			});
			$("#timeTo1_" + i).timepicker({
				step: 1,            //시간간격 : 1분
				timeFormat: "H:i"    //시간:분 으로표시
			});
			
			$("#timeFrom2_" + i).timepicker({
				step: 1,            //시간간격 : 1분
				timeFormat: "H:i"    //시간:분 으로표시
			});
			$("#timeTo2_" + i).timepicker({
				step: 1,            //시간간격 : 1분
				timeFormat: "H:i"    //시간:분 으로표시
			});
			
			$("#timeFrom3_" + i).timepicker({
				step: 1,            //시간간격 : 1분
				timeFormat: "H:i"    //시간:분 으로표시
			});
			$("#timeTo3_" + i).timepicker({
				step: 1,            //시간간격 : 1분
				timeFormat: "H:i"    //시간:분 으로표시
			});
			
			
			$("#timeFrom4_" + i).timepicker({
				step: 1,            //시간간격 : 1분
				timeFormat: "H:i"    //시간:분 으로표시
			});
			$("#timeTo4_" + i).timepicker({
				step: 1,            //시간간격 : 1분
				timeFormat: "H:i"    //시간:분 으로표시
			});
			
		}
		
	});
	
	
	
	$(function() {

		timer = setInterval( function () {

			getCurrentTime();

		    }, 60000);

		});
	
	
	$(document).ready(function(){
		
		/* 현재시간 */
		getCurrentTime();
		
		
		$('[id^="timeFrom"]').change(function() {
			
			var startTime = "";
			var endTime = "";
			
			
			var thisVal = $(this).val();
			var thisType = $(this).data('type');
			var startTime = $("#timeFrom" + thisType).val();
			var endTime = $("#timeTo" + thisType).val();
			var startDay = $("#dayFrom" + thisType).val();
			var endDay = $("#dayTo" + thisType).val();
			if(thisType != '4_6' && thisType != '4_5'){
				if((!thisType.startsWith('3') || !thisType.startsWith('4')) && (startTime > endTime) ){
					alert("시작시간이 종료시간 이후입니다.");
					$("#timeFrom" + thisType).val(endTime);
					$("#timeFrom" + thisType).focus();
					return false;
				}else if((thisType.startsWith('3')) && (startDay = endDay) && (startTime > endTime) ){
				alert("시작시간이 종료시간 이후입니다.");
					$("#timeFrom" + thisType).val(endTime);
					$("#timeFrom" + thisType).focus();
					return false;   
				}
			}
			
		 });
		
		
		
		$('[id^="timeTo"]').change(function() {
			
			var startTime = "";
			var endTime = "";
			
			
			var thisVal = $(this).val();
			var thisType = $(this).data('type');
			var startTime = $("#timeFrom" + thisType).val();
			var endTime = $("#timeTo" + thisType).val();
			var startDay = $("#dayFrom" + thisType).val();
			var endDay = $("#dayTo" + thisType).val();
			if(thisType != '4_6' && thisType != '4_5'){
				if((!thisType.startsWith('3') || !thisType.startsWith('4')) && (startTime > endTime) ){
					alert("종료일자가 시작일자 이전입니다.");
					$("#timeTo" + thisType).val(startTime);
					$("#timeTo" + thisType).focus();
					return false;
				}else if((thisType.startsWith('3')) && (startDay = endDay) && (startTime > endTime) ){
					alert("종료일자가 시작일자 이전입니다.");
					$("#timeTo" + thisType).val(startTime);
					$("#timeTo" + thisType).focus();
					return false;   
				}
			}
			
		 });
		
		
		
		$('[id^="dayFrom"]').change(function() {
			
			var startTime = "";
			var endTime = "";
			
			
			var thisVal = $(this).val();
			var thisType = $(this).data('type');
			var startTime = $("#timeFrom" + thisType).val();
			var endTime = $("#timeTo" + thisType).val();
			var startDay = parseInt($("#dayFrom" + thisType).val());
			var endDay = parseInt($("#dayTo" + thisType).val());
			if(thisType != '4_6' && thisType != '4_5'){
				if( startDay < endDay ){
			   		alert("시작일자가 종료일자 이후입니다.");
			   		$("#dayFrom" + thisType).val(endDay);
			   		$("#dayFrom" + thisType).focus();
			   		return false;
			 	}else if( (startDay = endDay) && (startTime > endTime) ){
					alert("시작시간이 종료시간 이후입니다.");
					$("#timeFrom" + thisType).val(endTime);
				   	$("#timeFrom" + thisType).focus();
				   	return false;   
			   	}
			}
		 });
		
		
		
		$('[id^="dayTo"]').change(function() {
			
			var startTime = "";
			var endTime = "";
			
			
			var thisVal = $(this).val();
			var thisType = $(this).data('type');
			var startTime = $("#timeFrom" + thisType).val();
			var endTime = $("#timeTo" + thisType).val();
			var startDay = parseInt($("#dayFrom" + thisType).val());
			var endDay = parseInt($("#dayTo" + thisType).val());
			if(thisType != '4_6' && thisType != '4_5'){
				if( startDay < endDay ){
					alert("종료일자가 시작일자 이후입니다.");
					$("#dayTo" + thisType).val(startDay);
					$("#dayTo" + thisType).focus();
					return false;
				}else if((startDay = endDay) && (startTime > endTime) ){
					alert("종료일자가 시작일자 이전입니다.");
					$("#timeTo" + thisType).val(startTime);
					$("#timeTo" + thisType).focus();
					return false;   
				}
			}
			
		 });
		
		
 		$('[id^="dayFrom"]').on("propertychange change keyup paste input", function() {
 			
			var thisType = $(this).data('type');
			var thisVal = $(this).val();
			var dayType = thisType.slice(-1)
			var startDay = $("#dayFrom" + thisType).val();
			
			var dayKo = getTodayLabel(dayType,startDay);
			
			$("#dayKoFrom" + thisType).text(" ("+dayKo+")");
			
		    
		});
 		
 		$('[id^="dayTo"]').on("propertychange change keyup paste input", function() {
 			
			var thisType = $(this).data('type');
			var thisVal = $(this).val();
			var dayType = thisType.slice(-1)
			var endDay = $("#dayTo" + thisType).val();
			
			var dayKo = getTodayLabel(dayType,endDay);
			
			$("#dayKoTo" + thisType).text(" ("+dayKo+")");
			
		    
		});
 		
 		
 		$("#timeTable").find('[id^="dayFrom"]').each(function(index, item){
 			
			var thisType = $(this).data('type');
			var thisVal = $(this).val();
			var dayType = thisType.slice(-1)
			var startDay = $("#dayFrom" + thisType).val();
			
			var dayKo = getTodayLabel(dayType,startDay);
			
			$("#dayKoFrom" + thisType).text(" ("+dayKo+")");
 			
 		});
 		
 		$("#timeTable").find('[id^="dayTo"]').each(function(index, item){
 			
			var thisType = $(this).data('type');
			var thisVal = $(this).val();
			var dayType = thisType.slice(-1)
			var endDay = $("#dayTo" + thisType).val();
			
			var dayKo = getTodayLabel(dayType,endDay);
			
			$("#dayKoTo" + thisType).text(" ("+dayKo+")");
 			
 		});
		
	});
	
	
	function getCurrentTime() {
		let bunChk = '${bunChk}';
	    $.ajax ({
	        "url" : "${pageContext.request.contextPath}/front/fixAucTime/currentTime.json?bunChk="+bunChk,
	        cache : false,
	        success : function (data) {
	        	$("#currentTime").text(data.currentTime[0].currentTime);
	        	$("#currentTime_mobile").text(data.currentTime[0].currentTime);
	        }
	    });
	}
	
	
	function getTodayLabel(dateNum,day) {
	    
	    var date = new Array('2022-01-16', '2022-01-17', '2022-01-18', '2022-01-19', '2022-01-20', '2022-01-21', '2022-01-22');
	    var week = new Array('일', '월', '화', '수', '목', '금', '토');
	    var today = new Date(date[dateNum-1]);
	    
	    today.setDate(today.getDate() - day);	    
	    
	    var weekNum = new Date(today).getDay();
	    var dayKo = week[weekNum];
	    
	    return dayKo;
	}

	
	
	function fn_timeSave(bunChk){
		
		var paramList = new Array();
		var isRight = true;
      
         $("#timeTable").find("input[type=text]").each(function(index, item){
        	 
        	 var pattern = /^([1-9]|[01][0-9]|2[0-3]):([0-5][0-9])$/;
         
             // 아무값없이 띄어쓰기만 있을 때도 빈 값으로 체크되도록 trim() 함수 호출
             if ($(this).val().trim() == '') {
                 alert("빈 값을 입력 할 수 없습니다.");
                 isRight = false;
                 return false;
             }
             
             if (!pattern.test($(this).val().trim())) {
                 alert("시간 형식으로 입력해 주세요 (예시 : 23:59)");
                 isRight = false;
                 return false;
             }
         });
         
         $("#timeTable").find("input[type=number]").each(function(index, item){
             // 아무값없이 띄어쓰기만 있을 때도 빈 값으로 체크되도록 trim() 함수 호출
             if ($(this).val().trim() == '') {
                 alert("빈 값을 입력 할 수 없습니다.");
                 isRight = false;
                 return false;
             }
         });

         if (!isRight) {
             return;
         }

        
		
		
		for (var i = 1; i <= 7; i++){
			paramList.push({bunChk:bunChk,timeType:"1",dayType:i,strTime:$(".strTime1_" + i).val(),endTime:$(".endTime1_" + i).val()});
			paramList.push({bunChk:bunChk,timeType:"2",dayType:i,strTime:$(".strTime2_" + i).val(),endTime:$(".endTime2_" + i).val()});
			paramList.push({bunChk:bunChk,timeType:"3",dayType:i,strTime:$(".strTime3_" + i).val(),endTime:$(".endTime3_" + i).val(),strFewDay:$(".strDay3_" + i).val(),endFewDay:$(".endDay3_" + i).val()});
			//if(bunChk == "N"){
				paramList.push({bunChk:bunChk,timeType:"4",dayType:i,strTime:$(".strTime4_" + i).val(),endTime:$(".endTime4_" + i).val(),strFewDay:$(".strDay4_" + i).val(),endFewDay:$(".endDay4_" + i).val()});	
			//}
		}
		console.log(paramList);
		$.ajax({
			data: {paramList : JSON.stringify(paramList)
				   , bunChk : bunChk
			},
	        type : "POST",
	        url : "${pageContext.request.contextPath}/front/fixAucTime/updateTime.json",
	        success : function(data){
	            if(data.status == "sucess"){
	            	alert("저장되었습니다.");
	            	location.href = "${pageContext.request.contextPath}/front/fixAucTime/time.do?bunChk="+bunChk;
	            }else{
	            	alert("시스템 오류로 인하여 실패하였습니다.");
	            }
	        }
	    });
		
	}
	
</script>

<!-- sub내용(S) -->
			<div class="sub_conts_in">
				<div class="tab_content pdt0">
					<!-- tab 내용01(S) --><!-- 판매관리(S)-->
					<div class="ti_01">
						<div class="info_box">
							<!-- 서브탭02(S) -->
							<div class="sub_tab">
								<ul class="devide_3">
									<c:if test="${nFloLoginVO ne null }">
									<li><a href="${pageContext.request.contextPath}/front/fixAucTime/time.do" <c:if test="${bunChk eq 'N'}"> class="active" </c:if>>절화</a></li>
									</c:if>
									<c:if test="${yFloLoginVO ne null }">
									<li><a href="${pageContext.request.contextPath}/front/fixAucTime/time.do?bunChk=Y" <c:if test="${bunChk eq 'Y'}"> class="active" </c:if>>&nbsp;&nbsp;&nbsp;난&nbsp;&nbsp;&nbsp;</a></li>
									</c:if>
									<c:if test="${cFloLoginVO ne null }">
									<li><a href="${pageContext.request.contextPath}/front/fixAucTime/time.do?bunChk=C" <c:if test="${bunChk eq 'C'}"> class="active" </c:if>>관엽</a></li>
									</c:if>
									<li>
										<p class="tit-time web">현재 서버 시간 : <span id="currentTime"></span></p>
									</li>
								</ul>
								
							</div>
							<!-- 서브탭02(E) -->
							
							
							<!-- 테이블03(S) -->
							<p class="dib tit-time mobile">현재 서버 시간 : <span id="currentTime_mobile"></span></p>
					<div class="table_type_03 bdtn">
						<table id="timeTable">
							<caption>info</caption>
							<colgroup>
								<col style="width:17%">
							</colgroup>
							<thead>
							 <tr>
							 	<th></th>
							 	<th>월</th>
							 	<th>화</th>
							 	<th>수</th>
							 	<th>목</th>
							 	<th>금</th>
							 	<th>토</th>
							 	<th>일</th>
							 </tr>
							</thead>
							<tbody>
								<tr>
									<th class="tc">출하자 판매 접수시간</th>
									<c:forEach var="i" begin="2" end="7">
									<td class="tc">
										<div class="time_box">
											<input type="text" placeholder="시간선택"  id="timeFrom1_${i}" class="strTime1_${i}" data-type="1_${i}" value="${fixTimeList[i-1].strTime}">
											<label for="timeFrom"></label>
										</div>
										<span class="hyphen">~</span>
										<div class="time_box">
											<input type="text" placeholder="시간선택"  id="timeTo1_${i}" class="endTime1_${i}" data-type="1_${i}" value="${fixTimeList[i-1].endTime}">
											<label for="timeTo"></label>
										</div>
									</td>
									</c:forEach>
									<td class="tc">
										<div class="time_box">
											<input type="text" placeholder="시간선택"  id="timeFrom1_1" class="strTime1_1" data-type="1_1" value="${fixTimeList[0].strTime}">
											<label for="timeFrom"></label>
										</div>
										<span class="hyphen">~</span>
										<div class="time_box">
											<input type="text" placeholder="시간선택"  id="timeTo1_1" class="endTime1_1" data-type="1_1" value="${fixTimeList[0].endTime}">
											<label for="timeTo"></label>
										</div>
									</td>
								</tr> 
								<tr>
									<th class="tc">중도매인 요청 접수시간</th>
									<c:forEach var="i" begin="9" end="14">
									<td class="tc">
										<div class="time_box">
											<input type="text" placeholder="시간선택"  id="timeFrom2_${i-7}" class="strTime2_${i-7}" data-type="2_${i-7}" value="${fixTimeList[i-1].strTime}">
											<label for="timeFrom02"></label>
										</div>
										<span class="hyphen">~</span>
										<div class="time_box">
											<input type="text" placeholder="시간선택"  id="timeTo2_${i-7}" class="endTime2_${i-7}" data-type="2_${i-7}" value="${fixTimeList[i-1].endTime}">
											<label for="timeTo02"></label>
										</div>
									</td>
									</c:forEach>
									<td class="tc">
										<div class="time_box">
											<input type="text" placeholder="시간선택"  id="timeFrom2_1" class="strTime2_1" data-type="2_1" value="${fixTimeList[7].strTime}">
											<label for="timeFrom02"></label>
										</div>
										<span class="hyphen">~</span>
										<div class="time_box">
											<input type="text" placeholder="시간선택"  id="timeTo2_1" class="endTime2_1" data-type="2_1" value="${fixTimeList[7].endTime}">
											<label for="timeTo02"></label>
										</div>
									</td>
								</tr>
								<tr>
									<th class="tc">중도매인 입찰 가능시간</th>
									<c:forEach var="i" begin="1" end="6">
										<c:choose>
											<c:when test="${empty fixTimeBidList[i].strFewDay or fixTimeBidList[i].strFewDay eq ''}">
												<td class="tc" style="background-color:#f5f5f5"></td>
    										</c:when>
    										<c:otherwise>
    											<td class="tc">
													<div class="time_box" style="width: 35px; margin-bottom: 6px;"> 
														<input type='number' id="dayFrom3_${i+1}" class="strDay3_${i+1}" value="${fixTimeBidList[i].strFewDay}" data-type="3_${i+1}" style="text-align: center;"/>
													</div>
													<span style="font-size:13px">일 전</span><span id="dayKoFrom3_${i+1}" style="font-size:13px"></span>
													<div class="time_box"> 
														<input type="text" placeholder="시간선택"  id="timeFrom3_${i+1}" class="strTime3_${i+1}" data-type="3_${i+1}" value="${fixTimeBidList[i].strTime}">
														<label for="timeFrom3_${i+1}"></label>
													</div>
													<p style="margin: 5px 0">~</p>
													<div class="time_box" style="width: 35px; margin-bottom: 6px;"> 
														<input type='number' id="dayTo3_${i+1}" class="endDay3_${i+1}" value="${fixTimeBidList[i].endFewDay}" data-type="3_${i+1}" style="text-align: center;"/>
													</div>
													<span style="font-size:13px">일 전</span><span id="dayKoTo3_${i+1}" style="font-size:13px"></span>
													<div class="time_box">
														<input type="text" placeholder="시간선택"  id="timeTo3_${i+1}" class="endTime3_${i+1}" data-type="3_${i+1}" value="${fixTimeBidList[i].endTime}">
														<label for="timeTo3_${i+1}"></label>
													</div>
												</td>
    										</c:otherwise>
										</c:choose>
									</c:forEach>
									<c:choose>
										<c:when test="${empty fixTimeBidList[0].strFewDay or fixTimeBidList[0].strFewDay eq ''}">
											<td class="tc" style="background-color:#f5f5f5"></td>
   										</c:when>
   										<c:otherwise>
	   										<td class="tc">
												<div class="time_box" style="width: 35px; margin-bottom: 6px;"> 
													<input type='number' id="dayFrom3_1" class="strDay3_1" value="${fixTimeBidList[0].strFewDay}" data-type="3_1" style="text-align: center;"/>
												</div>
												<span style="font-size:13px">일 전</span><span id="dayKoFrom3_1" style="font-size:13px"></span>
												<div class="time_box"> 
													<input type="text" placeholder="시간선택"  id="timeFrom3_1" class="strTime3_1" data-type="3_1" value="${fixTimeBidList[0].strTime}">
													<label for="timeFrom3_1"></label>
												</div>
												<p style="margin: 5px 0">~</p>
												<div class="time_box" style="width: 35px; margin-bottom: 6px;"> 
													<input type='number' id="dayTo3_1" class="endDay3_1" value="${fixTimeBidList[0].endFewDay}" data-type="3_1" style="text-align: center;"/>
												</div>
												<span style="font-size:13px">일 전</span><span id="dayKoTo3_1" style="font-size:13px"></span>
												<div class="time_box">
													<input type="text" placeholder="시간선택"  id="timeTo3_1" class="endTime3_1" data-type="3_1" value="${fixTimeBidList[0].endTime}">
													<label for="timeTo3_1"></label>
												</div>
											</td>
										</c:otherwise>
									</c:choose>
								</tr>
								<%-- <c:if test="${bunChk eq 'N'}"> --%>
								<tr>
									<th class="tc">중도매인 서면 입찰<br>가능시간</th>
									<c:forEach var="i" begin="1" end="6">
										<c:choose>
											<c:when test="${empty fixAucTimeWriteBid[i].strFewDay or fixAucTimeWriteBid[i].strFewDay eq ''}">
												<td class="tc" style="background-color:#f5f5f5"></td>
    										</c:when>
    										<c:otherwise>
    											<td class="tc">
													<div class="time_box" style="width: 35px; margin-bottom: 6px;"> 
														<input type='number' id="dayFrom4_${i+1}" class="strDay4_${i+1}" value="${fixAucTimeWriteBid[i].strFewDay}" data-type="4_${i+1}" style="text-align: center;"/>
													</div>
													<span style="font-size:13px">일 전</span><span id="dayKoFrom4_${i+1}" style="font-size:13px"></span>
													<div class="time_box"> 
														<input type="text" placeholder="시간선택"  id="timeFrom4_${i+1}" class="strTime4_${i+1}" data-type="4_${i+1}" value="${fixAucTimeWriteBid[i].strTime}">
														<label for="timeFrom4_${i+1}"></label>
													</div>
													<p style="margin: 5px 0">~</p>
													<div class="time_box" style="width: 35px; margin-bottom: 6px;"> 
														<input type='number' id="dayTo4_${i+1}" class="endDay4_${i+1}" value="${fixAucTimeWriteBid[i].endFewDay}" data-type="4_${i+1}" style="text-align: center;"/>
													</div>
													<span style="font-size:13px">일 전</span><span id="dayKoTo4_${i+1}" style="font-size:13px"></span>
													<div class="time_box">
														<input type="text" placeholder="시간선택"  id="timeTo4_${i+1}" class="endTime4_${i+1}" data-type="4_${i+1}" value="${fixAucTimeWriteBid[i].endTime}">
														<label for="timeTo4_${i+1}"></label>
													</div>
												</td>
    										</c:otherwise>
										</c:choose>
									</c:forEach>
									<c:choose>
										<c:when test="${empty fixAucTimeWriteBid[0].strFewDay or fixAucTimeWriteBid[0].strFewDay eq ''}">
											<td class="tc" style="background-color:#f5f5f5"></td>
   										</c:when>
   										<c:otherwise>
	   										<td class="tc">
												<div class="time_box" style="width: 35px; margin-bottom: 6px;"> 
													<input type='number' id="dayFrom4_1" class="strDay4_1" value="${fixAucTimeWriteBid[0].strFewDay}" data-type="4_1" style="text-align: center;"/>
												</div>
												<span style="font-size:13px">일 전</span><span id="dayKoFrom4_1" style="font-size:13px"></span>
												<div class="time_box"> 
													<input type="text" placeholder="시간선택"  id="timeFrom4_1" class="strTime4_1" data-type="4_1" value="${fixAucTimeWriteBid[0].strTime}">
													<label for="timeFrom4_1"></label>
												</div>
												<p style="margin: 5px 0">~</p>
												<div class="time_box" style="width: 35px; margin-bottom: 6px;"> 
													<input type='number' id="dayTo4_1" class="endDay4_1" value="${fixAucTimeWriteBid[0].endFewDay}" data-type="4_1" style="text-align: center;"/>
												</div>
												<span style="font-size:13px">일 전</span><span id="dayKoTo4_1" style="font-size:13px"></span>
												<div class="time_box">
													<input type="text" placeholder="시간선택"  id="timeTo4_1" class="endTime4_1" data-type="4_1" value="${fixAucTimeWriteBid[0].endTime}">
													<label for="timeTo4_1"></label>
												</div>
											</td>
										</c:otherwise>
									</c:choose>
								</tr>
								<%-- </c:if> --%>
							</tbody>
						</table>
					</div>
					<!-- 테이블03(E) -->

							<!-- 버튼박스(S) -->
							<div class="btn_box mt30">
								<a href="#!" class="btn_type_01 save" onclick="fn_timeSave('${bunChk}');">저장</a>
							</div>
							<!-- 버튼박스(S) -->
						</div>
						
					</div>
					<!-- tab 내용01(E) --><!-- 판매관리(E)-->
				</div>
				<!-- sub탭(E) -->

			</div>
			<!-- sub내용(E) -->