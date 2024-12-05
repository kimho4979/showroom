<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

			<!-- sub내용(S) -->
			<div class="sub_conts_in">
					
				<!-- 타이틀(S) -->
				<div class="title_box">
					<div class="fl">
						<h4 class="sub_tit_04">전시실 예약</h4>
					</div>
				</div>
				<!-- 타이틀(E) -->
				
				<!-- 전시실 예약(S) -->
				<div class="about_market about_market2" style="border: 0px; margin: 0;">
					<div class="am_left">
						<div class="Calendar_W mt30" id='reservationCalendar' ></div>
					</div>
					
					<div class="am_right" style="padding: 0; border-left: 0px; ${noimgestyle}">
						<div style="text-align: right; margin-top: 35px;">
							<label style="display: inline-block; height: 20px; width: 20px; margin-right: 3px; border: 1px solid #ddd; background-color: #FFFFFF;"></label>
							<p style="display: inline-block; vertical-align: top;">예약가능</p>
							<label style="display: inline-block; height: 20px; width: 20px; margin: 0 3px 0 20px; background-color: #085853;"></label>
							<p style="display: inline-block; vertical-align: top;">선택</p>
							<label style="display: inline-block; height: 20px; width: 20px; margin: 0 3px 0 20px; background-color: #ccc;"></label>
							<p style="display: inline-block; vertical-align: top;">선택불가</p>
						</div>
						<div class="table_type_01 mt20 overflow_a">
							<table>
								<caption>전시실 예약 시 옵션을 선택하는 테이블입니다.</caption>
								<colgroup>
									<col style="width: 16%;">
									<col style="width: 15%;">
									<col style="width: 15%;">
									<col style="width: 18%;">
									<col style="width: 18%;">
									<col style="width: 18%;">
								</colgroup>
								<thead>
									<tr>
										<th style="height: 35px; padding: 5px;">구 분</th>
										<th style="height: 35px; padding: 5px;">전용면적</th>
										<th style="height: 35px; padding: 5px;">수용인원</th>
										<th style="height: 35px; padding: 5px;">09시 ~ 12시</th>
										<th style="height: 35px; padding: 5px;">13시 ~ 17시</th>
										<th style="height: 35px; padding: 5px;">09시 ~ 17시</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td class="tc" style="height: 35px;"><p class="txt_01"></p>무궁화홀</td>
										<td class="tc" style="height: 35px;"><p class="txt_01"></p>394㎡</td>
										<td class="tc" style="height: 35px;"><p class="txt_01"></p>120명</td>
										<td class="tc" style="height: 35px;">
											<label style="display: inline-block; height: 20px; width: 20px; border: 1px solid #ddd; background-color: #FFFFFF; cursor: pointer;" onclick="checkEvntDt(this.id);" id="AM1"></label>
										</td>
										<td class="tc" style="height: 35px;">
											<label style="display: inline-block; height: 20px; width: 20px; border: 1px solid #ddd; background-color: #FFFFFF; cursor: pointer;" onclick="checkEvntDt(this.id);" id="PM1"></label>
										</td>
										<td class="tc" style="height: 35px;">
											<label style="display: inline-block; height: 20px; width: 20px; border: 1px solid #ddd; background-color: #FFFFFF; cursor: pointer;" onclick="checkEvntDt(this.id);" id="ALL1"></label>
										</td>
									</tr>
									<tr>
										<td class="tc" style="height: 35px;"><p class="txt_01"></p>국화홀</td>
										<td class="tc" style="height: 35px;"><p class="txt_01"></p>225㎡</td>
										<td class="tc" style="height: 35px;"><p class="txt_01"></p>60명</td>
										<td class="tc" style="height: 35px;">
											<label style="display: inline-block; height: 20px; width: 20px; border: 1px solid #ddd; background-color: #FFFFFF; cursor: pointer;" onclick="checkEvntDt(this.id);" id="AM2"></label>
										</td>
										<td class="tc" style="height: 35px;">
											<label style="display: inline-block; height: 20px; width: 20px; border: 1px solid #ddd; background-color: #FFFFFF; cursor: pointer;" onclick="checkEvntDt(this.id);" id="PM2"></label>
										</td>
										<td class="tc" style="height: 35px;">
											<label style="display: inline-block; height: 20px; width: 20px; border: 1px solid #ddd; background-color: #FFFFFF; cursor: pointer;" onclick="checkEvntDt(this.id);" id="ALL2"></label>
										</td>
									</tr>
									<tr>
										<td class="tc" style="height: 35px;"><p class="txt_01"></p>장미홀</td>
										<td class="tc" style="height: 35px;"><p class="txt_01"></p>169㎡</td>
										<td class="tc" style="height: 35px;"><p class="txt_01"></p>40명</td>
										<td class="tc" style="height: 35px;">
											<label style="display: inline-block; height: 20px; width: 20px; border: 1px solid #ddd; background-color: #FFFFFF; cursor: pointer;" onclick="checkEvntDt(this.id);" id="AM3"></label>
										</td>
										<td class="tc" style="height: 35px;">
											<label style="display: inline-block; height: 20px; width: 20px; border: 1px solid #ddd; background-color: #FFFFFF; cursor: pointer;" onclick="checkEvntDt(this.id);" id="PM3"></label>
										</td>
										<td class="tc" style="height: 35px;">
											<label style="display: inline-block; height: 20px; width: 20px; border: 1px solid #ddd; background-color: #FFFFFF; cursor: pointer;" onclick="checkEvntDt(this.id);" id="ALL3"></label>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="table_type_01 mt20 overflow_a">
							<table>
								<caption>선택한 전시실 예약정보를 표시하는 테이블입니다.</caption>
								<colgroup>
									<col style="width: 32%;">
									<col style="width: 28%;">
									<col style="width: 40%;">
								</colgroup>
								<thead>
									<tr>
										<th style="height: 35px; padding: 5px;">예약일자</th>
										<th style="height: 35px; padding: 5px;">구 분</th>
										<th style="height: 35px; padding: 5px;">사용일정</th>
									</tr>
								</thead>
								<tbody id="checkedDtList">
									<tr>
										<td class="tc" style="height: 35px;"><p class="txt_01"></p></td>
										<td class="tc" style="height: 35px;"><p class="txt_01"></p></td>
										<td class="tc" style="height: 35px;"><p class="txt_01"></p></td>
									</tr>
									<tr>
										<td class="tc" style="height: 35px;"><p class="txt_01"></p></td>
										<td class="tc" style="height: 35px;"><p class="txt_01"></p></td>
										<td class="tc" style="height: 35px;"><p class="txt_01"></p></td>
									</tr>
									<tr>
										<td class="tc" style="height: 35px;"><p class="txt_01"></p></td>
										<td class="tc" style="height: 35px;"><p class="txt_01"></p></td>
										<td class="tc" style="height: 35px;"><p class="txt_01"></p></td>
									</tr>
									<tr>
										<td class="tc" style="height: 35px;"><p class="txt_01"></p></td>
										<td class="tc" style="height: 35px;"><p class="txt_01"></p></td>
										<td class="tc" style="height: 35px;"><p class="txt_01"></p></td>
									</tr>
									<tr>
										<td class="tc" style="height: 35px;"><p class="txt_01"></p></td>
										<td class="tc" style="height: 35px;"><p class="txt_01"></p></td>
										<td class="tc" style="height: 35px;"><p class="txt_01"></p></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="info_btn_box">
					<div style="display: inline-block; width: 92%; float: left;">
						<p style="color: #232323; font-weight: bold; font-size: 20px;">전시실 예약방법</p>
						<p class="s_txt mt10" style="color: #232323;">
							1.&ensp;날짜를 선택 후 예약 가능한 회의실을 확인해주세요.<br>
							2.&ensp;원하시는 날짜, 전시실, 시간을 선택하여 예약을 진행해주세요.<br>
							3.&ensp;“날짜 → 전시실 → 날짜 → 전시실” 순으로 선택하시면, 각각 다른 날짜의 전시실을 한 번에 최대 5일까지 예약하실 수 있습니다.<br>
<!-- 							&emsp;&ensp;<br> -->
							
						</p>
					</div>
					<div style="clear: both; display: inline-block;"></div>
					<button class="link default wid8" style="display: inline-block; height: 40px;"  onclick="moveToReserv();">다음</button>
				</div>
				<!-- 전시실 예약(E) -->
						
				<!-- Data Form (S) -->
				<form id="submitForm" method="POST" style="display: none;" action="${pageContext.request.contextPath}/front/content/showroomestimate.do">
					<div>
						<input type="text" name="date" value="">
						<input type="text" name="evntDt" value="">
						<input type="text" name="hallType" value="">
						<input type="text" name="typr" value="">
						<input type="text" name="knotDate" value="">
					</div>
					<div>
						<input type="text" name="date" value="">
						<input type="text" name="evntDt" value="">
						<input type="text" name="hallType" value="">
						<input type="text" name="typr" value="">
						<input type="text" name="knotDate" value="">
					</div>
					<div>
						<input type="text" name="date" value="">
						<input type="text" name="evntDt" value="">
						<input type="text" name="hallType" value="">
						<input type="text" name="typr" value="">
						<input type="text" name="knotDate" value="">
					</div>
					<div>
						<input type="text" name="date" value="">
						<input type="text" name="evntDt" value="">
						<input type="text" name="hallType" value="">
						<input type="text" name="typr" value="">
						<input type="text" name="knotDate" value="">
					</div>
					<div>
						<input type="text" name="date" value="">
						<input type="text" name="evntDt" value="">
						<input type="text" name="hallType" value="">
						<input type="text" name="typr" value="">
						<input type="text" name="knotDate" value="">
					</div>
					
				</form>
				<!-- Data Form (E) -->
				
			</div>
			<!-- sub내용(E) -->

<!-- 스크립트(S) -->
<script type="text/javascript">
	var urlType = '';
	var userId;
	
	if ('${atLoginVO.loginId}' != null && '${atLoginVO.loginId}' != '') {
		userId = '${atLoginVO.loginId}';
	} else if ('${snsLoginVO.loginId}' != null && '${snsLoginVO.loginId}' != '') {
		userId = '${snsLoginVO.loginId}';
	}
	
	$(window).load(function(){
		var queryString = window.location.search;
		var params = new URLSearchParams(queryString);
		var locationParam = params.get('type');
		urlType = locationParam;
		if(urlType == '1'){
			$('h2.sub_title').text('전시실 예약');
		}else if(urlType == '2'){
			$('h2.sub_title').text('온라인 견적산출');
			$('.sub_tit_04').text('온라인 견적산출');
		}
		renderCalendar('#reservationLayer');
	});
	
	var calendar;
	
	var initDate = new Date();
	initDate.setDate(initDate.getDate()+7);
	
	var selectedDt = {
			get date() {
				return this._dt || initDate;
			},
			set date(dt) {
				this._dt = dt;
				calendar.select(this._dt);
			}
	};
	
	var checkedDtList = [];
	var labelStyle = {};
	
	function renderCalendar(el) {
		
		// calendar rendering
		var calendarEl = document.getElementById(el.slice(1, -5) + 'Calendar');
// 		var calendarEl = document.getElementById('#reservationCalendar');
		calendar = new FullCalendar.Calendar(calendarEl, {
			initialView : 'dayGridMonth', // 초기 로드 될때 보이는 캘린더 화면(기본 설정: 달)
			headerToolbar : { // 헤더에 표시할 툴 바
				start : 'title',
				center : '',
				end : 'prev,next'
			},
			titleFormat : function(date) {
				return date.date.year + '년 ' + (parseInt(date.date.month) + 1) + '월';
			},
			contentHeight : 371,
			locale: 'ko', // 한국어 설정
			datesSet : function(dateInfo) { renderDaygrid(calendarEl); },
			displayEventTime : false,
			unselectAuto : false,
			initialDate: initDate,
			validRange: function() {
				var validDtStart = new Date;
				var validDtEnd = new Date;

				validDtStart.setDate(validDtStart.getDate()+7);
				validDtEnd.setMonth(validDtEnd.getMonth()+2)
				validDtEnd.setDate(1);
				validDtEnd.setDate(validDtEnd.getDate()-1);
				
				return {
					start: validDtStart,
					end: validDtEnd
				};
			},
			dateClick: function(info) {
				selectedDt.date = info.date;
				getRsrvYn(moment(info.date).format("YYYYMMDD"));
			},
			select : function(selectionInfo) {
				if (document.querySelector(".selected") != null) document.querySelector(".selected").classList.remove("selected");
				if (document.querySelector(".fc-highlight") != null) {
					document.querySelector(".fc-highlight").parentNode.parentNode.parentNode.classList.add("selected");
				}
			},
		});
		calendar.render();
		calendarEl.querySelector('.fc-prev-button').setAttribute('style', 'height: 28px; width: 40px; padding: 0;');
		calendarEl.querySelector('.fc-next-button').setAttribute('style', 'height: 28px; width: 40px; padding: 0; margin: 0;');
		calendarEl.querySelector('.fc-prev-button').setAttribute('onclick', 'onclickDateNav();');
		calendarEl.querySelector('.fc-next-button').setAttribute('onclick', 'onclickDateNav();');
		calendarEl.querySelector('.fc-toolbar-title').setAttribute('style', 'font-size: 1.55em;');
		calendarEl.querySelector('.fc-header-toolbar').setAttribute('style', 'margin: 0 0 18px 0;');
		
		// initial select
		calendar.select(initDate);
		getRsrvYn(moment(initDate).format("YYYYMMDD"));
		
	}
	
	// calendar daygrid rendering
	function renderDaygrid(calendarEl) {
	
		// resize daygrid & remove today background-color
		var calBodyRow = calendarEl.querySelector('.fc-daygrid-body').getElementsByTagName('tr');
		var calBodyCol = calendarEl.querySelector('.fc-daygrid-body').getElementsByTagName('td');
		var today = calendarEl.querySelector('.fc-day-today');
		var todayOtherMonth = calendarEl.querySelector('[style="background-color: transparent;"]');
		
		for (var i=0; i<calBodyRow.length; i++) {
			calBodyRow[i].setAttribute('style', 'height: 58px;');
		}
		for (var i=0; i<calBodyCol.length; i++) {
			if (calBodyCol[i] == today) {
				calBodyCol[i].setAttribute('style', 'background-color: transparent;');
			} else {
				calBodyCol[i].setAttribute('style', '');
			}
		}
		
	}
	
	function onclickDateNav() {
		calendar.select(selectedDt.date);
		document.querySelector(".fc-highlight").parentNode.parentNode.parentNode.classList.add("selected");
	}
	
	// 예약 가능 여부 확인
	function getRsrvYn(dateStr) {
		var avblStyle = "display: inline-block; height: 20px; width: 20px; border: 1px solid #ddd; background-color: #FFFFFF; cursor: pointer;";
		var unavblStyle = "display: inline-block; height: 20px; width: 20px; background-color: #ccc;";
		var chosenStyle = "display: inline-block; height: 20px; width: 20px; background-color: #085853; cursor: pointer;";
		if (urlType == '1' && (userId == null || userId == "")) {
			return;
		}
		
		$.ajax({
			type: "POST",
			url: "${pageContext.request.contextPath}/front/getRsrvYn.json",
			data: JSON.stringify({ userId : urlType == '1' ? userId : '', rsrvDtStart : dateStr, rsrvDtEnd : dateStr }),
			contentType: 'application/json; charset=utf-8',
			success: function(data){
		
	 			const result = data.result;
	 			var unselectAlert = false;
	 			var chosenSelector = '';
	 			labelStyle = {};
				
	 			$('.table_type_01').find('label').attr('value', '');
	 			
	 			if (result[0].hallType1Yn == 'Y') {
	 				document.querySelector('#AM1').style.cssText = avblStyle;
	 				document.querySelector('#PM1').style.cssText = avblStyle;
	 				document.querySelector('#ALL1').style.cssText = avblStyle;
	 			} else {
	 				document.querySelector('#AM1').style.cssText = unavblStyle;
	 				document.querySelector('#PM1').style.cssText = unavblStyle;
	 				document.querySelector('#ALL1').style.cssText = unavblStyle;
	 			}
	 			if (result[0].hallType2Yn == 'Y') {
	 				document.querySelector('#AM2').style.cssText = avblStyle;
	 				document.querySelector('#PM2').style.cssText = avblStyle;
	 				document.querySelector('#ALL2').style.cssText = avblStyle;
	 			} else {
	 				document.querySelector('#AM2').style.cssText = unavblStyle;
	 				document.querySelector('#PM2').style.cssText = unavblStyle;
	 				document.querySelector('#ALL2').style.cssText = unavblStyle;
	 			}
	 			if (result[0].hallType3Yn == 'Y') {
	 				document.querySelector('#AM3').style.cssText = avblStyle;
	 				document.querySelector('#PM3').style.cssText = avblStyle;
	 				document.querySelector('#ALL3').style.cssText = avblStyle;
	 			} else {
	 				document.querySelector('#AM3').style.cssText = unavblStyle;
	 				document.querySelector('#PM3').style.cssText = unavblStyle;
	 				document.querySelector('#ALL3').style.cssText = unavblStyle;
	 			}
						
	 			
				if(result.length > 1 && result[0].etc1 == '-1'){
					if(result[1].hallTypeCd == '1'){
						document.querySelector('#AM1').style.cssText = unavblStyle;
						document.querySelector('#PM1').style.cssText = unavblStyle;
						document.querySelector('#ALL1').style.cssText = unavblStyle;
					}else if(result[1].hallTypeCd == '2'){
						document.querySelector('#AM2').style.cssText = unavblStyle;
						document.querySelector('#PM2').style.cssText = unavblStyle;
						document.querySelector('#ALL2').style.cssText = unavblStyle;
					}else if(result[1].hallTypeCd == '3'){
						document.querySelector('#AM3').style.cssText = unavblStyle;
						document.querySelector('#PM3').style.cssText = unavblStyle;
						document.querySelector('#ALL3').style.cssText = unavblStyle;
					}
				}


	 			for (var checkedDt of checkedDtList) {
	 				// 주말 예약 제한
	 				var checkedDtDateType = new Date(checkedDt.date);
	 				var checkedDtNextDay = new Date(checkedDt.date);
	 				var checkedDtPreDay = new Date(checkedDt.date);
	 				checkedDtNextDay.setDate(checkedDtNextDay.getDate()+1);
	 				checkedDtPreDay.setDate(checkedDtPreDay.getDate()-1);
	 				
	 				if (result[0]['hallType'+checkedDt.hallType+'Yn'] == 'Y'
	 					&& moment(checkedDt.date).format("e") == '0'
	 					&& moment(selectedDt.date).format("YYYY-MM-DD") == moment(checkedDtPreDay).format("YYYY-MM-DD")) {
		 				if (checkedDt.hallType == '1') {
			 				document.querySelector('#AM2').style.cssText = unavblStyle;
			 				document.querySelector('#PM2').style.cssText = unavblStyle;
			 				document.querySelector('#ALL2').style.cssText = unavblStyle;
			 				document.querySelector('#AM3').style.cssText = unavblStyle;
			 				document.querySelector('#PM3').style.cssText = unavblStyle;
			 				document.querySelector('#ALL3').style.cssText = unavblStyle;
		 				} else if (checkedDt.hallType == '2') {
			 				document.querySelector('#AM1').style.cssText = unavblStyle;
			 				document.querySelector('#PM1').style.cssText = unavblStyle;
			 				document.querySelector('#ALL1').style.cssText = unavblStyle;
			 				document.querySelector('#AM3').style.cssText = unavblStyle;
			 				document.querySelector('#PM3').style.cssText = unavblStyle;
			 				document.querySelector('#ALL3').style.cssText = unavblStyle;
		 				} else if (checkedDt.hallType == '3') {
			 				document.querySelector('#AM1').style.cssText = unavblStyle;
			 				document.querySelector('#PM1').style.cssText = unavblStyle;
			 				document.querySelector('#ALL1').style.cssText = unavblStyle;
			 				document.querySelector('#AM2').style.cssText = unavblStyle;
			 				document.querySelector('#PM2').style.cssText = unavblStyle;
			 				document.querySelector('#ALL2').style.cssText = unavblStyle;
		 				}
	 					
	 				} else if (result[0]['hallType'+checkedDt.hallType+'Yn'] == 'N'
	 					&& moment(checkedDt.date).format("e") == '0'
		 				&& moment(selectedDt.date).format("YYYY-MM-DD") == moment(checkedDtPreDay).format("YYYY-MM-DD")) {
	 					unselectAlert = true;
	 					checkedDtList = checkedDtList.filter((element) => element.date != moment(checkedDt.date).format("YYYY-MM-DD"));
	 					
	 				} else if (result[0]['hallType'+checkedDt.hallType+'Yn'] == 'Y'
	 					&& moment(checkedDt.date).format("e") == '6'
	 					&& moment(selectedDt.date).format("YYYY-MM-DD") == moment(checkedDtNextDay).format("YYYY-MM-DD")) {
		 				if (checkedDt.hallType == '1') {
			 				document.querySelector('#AM2').style.cssText = unavblStyle;
			 				document.querySelector('#PM2').style.cssText = unavblStyle;
			 				document.querySelector('#ALL2').style.cssText = unavblStyle;
			 				document.querySelector('#AM3').style.cssText = unavblStyle;
			 				document.querySelector('#PM3').style.cssText = unavblStyle;
			 				document.querySelector('#ALL3').style.cssText = unavblStyle;
		 				} else if (checkedDt.hallType == '2') {
			 				document.querySelector('#AM1').style.cssText = unavblStyle;
			 				document.querySelector('#PM1').style.cssText = unavblStyle;
			 				document.querySelector('#ALL1').style.cssText = unavblStyle;
			 				document.querySelector('#AM3').style.cssText = unavblStyle;
			 				document.querySelector('#PM3').style.cssText = unavblStyle;
			 				document.querySelector('#ALL3').style.cssText = unavblStyle;
		 				} else if (checkedDt.hallType == '3') {
			 				document.querySelector('#AM1').style.cssText = unavblStyle;
			 				document.querySelector('#PM1').style.cssText = unavblStyle;
			 				document.querySelector('#ALL1').style.cssText = unavblStyle;
			 				document.querySelector('#AM2').style.cssText = unavblStyle;
			 				document.querySelector('#PM2').style.cssText = unavblStyle;
			 				document.querySelector('#ALL2').style.cssText = unavblStyle;
		 				}
	 					
	 				} else if (result[0]['hallType'+checkedDt.hallType+'Yn'] == 'N'
	 					&& moment(checkedDt.date).format("e") == '6'
		 				&& moment(selectedDt.date).format("YYYY-MM-DD") == moment(checkedDtNextDay).format("YYYY-MM-DD")) {
	 					unselectAlert = true;
	 					checkedDtList = checkedDtList.filter((element) => element.date != moment(checkedDt.date).format("YYYY-MM-DD"));
	 					
	 				} else if (moment(selectedDt.date).format("YYYY-MM-DD") == checkedDt.date) {
						if (document.querySelector('#' + checkedDt.evntDt + checkedDt.hallType).style.backgroundColor != 'rgb(204, 204, 204)') {
							chosenSelector = '#' + checkedDt.evntDt + checkedDt.hallType;
						} else {
		 					unselectAlert = true;
							checkedDtList = checkedDtList.filter((element) => element.date != moment(checkedDt.date).format("YYYY-MM-DD"));
						}
						
					}
				}
	 			
	 			for (var label of document.querySelectorAll('.tc label')) {
	 				if (label.style.backgroundColor == 'rgb(8, 88, 83)') {
						labelStyle[label.id] = 'display: inline-block; height: 20px; width: 20px; border: 1px solid rgb(221, 221, 221); background-color: rgb(255, 255, 255); cursor: pointer;';
	 				} else {
		 				labelStyle[label.id] = label.style.cssText;
	 				}
	 			}

	 			if (chosenSelector != '') {
					setChosenStyle(chosenSelector, false /* isNewDate */);
	 			}
	 			
	 			if (unselectAlert) {
					alert("선택한 전시실이 예약되어 해당 예약 정보가 삭제되었습니다.");
					setGrid();
	 			}
			
			},
			error: function(a,b,c){
		
				alert("예약 가능 일자를 불러오는데 실패했습니다.");
				console.log(a,b,c);
	
			}
		});
	}
	
	// 예약 테이블 표시
	function checkEvntDt(id) {
		if (getComputedStyle(document.querySelector('#'+id)).backgroundColor == 'rgb(255, 255, 255)') {
			if (document.querySelector('label[value="chosen"]') != null) {
				setAvblStyle('label[value="chosen"]', false /* isCanceled */);
				setChosenStyle('#'+id, false /* isNewDate */);
			} else {
				if (checkedDtList.length >= 5) {
					alert("예약은 한 번에 최대 5일까지 가능합니다.");
					return;
				}
				setChosenStyle('#'+id, true /* isNewDate */);
			}
		} else if (getComputedStyle(document.querySelector('#'+id)).backgroundColor == 'rgb(8, 88, 83)') {
			setAvblStyle('#'+id, true /* isCanceled */);
		}
	}
	function setChosenStyle(selector, isNewDate) {
		var chosenStyle = "display: inline-block; height: 20px; width: 20px; background-color: #085853; cursor: pointer;";
		var unavblStyle = "display: inline-block; height: 20px; width: 20px; background-color: #ccc;";
		
		for (var label of document.querySelectorAll('.tc label')) {
			if (label.id.includes(selector.slice(-1))) {
				continue;
			}
			label.style.cssText = unavblStyle;
		}
		
		document.querySelector(selector).style.cssText = chosenStyle;
		$(selector).attr('value', 'chosen');
		
		if (isNewDate) {
			checkedDtList.push({
				date		: moment(selectedDt.date).format("YYYY-MM-DD"),
				evntDt		: document.querySelector('label[value="chosen"]').id.slice(0, -1),
				hallType	: document.querySelector('label[value="chosen"]').id.slice(-1)
			});
		} else {
			for (var checkedDt of checkedDtList) {
				if (moment(selectedDt.date).format("YYYY-MM-DD") == checkedDt.date) {
					checkedDt.evntDt = document.querySelector('label[value="chosen"]').id.slice(0, -1);
					checkedDt.hallType = document.querySelector('label[value="chosen"]').id.slice(-1);
					break;
				}
			}
		}

		setGrid();
	}
	function setAvblStyle(selector, isCanceled) {
		var avblStyle = "display: inline-block; height: 20px; width: 20px; border: 1px solid #ddd; background-color: #FFFFFF; cursor: pointer;";
		
		for (var label of document.querySelectorAll('.tc label')) {
			label.style.cssText = labelStyle[label.id];
		}
		
		document.querySelector(selector).style.cssText = avblStyle;
		$(selector).attr('value', '');
		
		if (isCanceled) {
			checkedDtList = checkedDtList.filter((element) => element.date != moment(selectedDt.date).format("YYYY-MM-DD"));
		}
		
		setGrid();
	}
	
	// set grid
	function setGrid() {
		var gridRow = document.querySelectorAll('#checkedDtList tr');
		var i = 0;
		
		for (var checkedDt of checkedDtList) {
			var gridCol = gridRow[i].querySelectorAll('p');
			
			gridCol[0].innerText = moment(checkedDt.date).format("YYYY년 M월 D일");
			gridCol[1].innerText = checkedDt.hallType == '1' ? '무궁화홀' : checkedDt.hallType == '2' ? '국화홀' : '장미홀';
			gridCol[2].innerText = checkedDt.evntDt == 'AM' ? '09시 ~ 12시' : checkedDt.evntDt == 'PM' ? '13시 ~ 17시' : '09시 ~ 17시';
			i++;
		}
		for (; i<gridRow.length; i++) {
			var gridCol = gridRow[i].querySelectorAll('p');
			
			gridCol[0].innerText = '';
			gridCol[1].innerText = '';
			gridCol[2].innerText = '';
		}
	}
	
	// 견적 입력 데이터와 함께 예약 페이지로 이동
	function moveToReserv() {
		if (checkedDtList.length <= 0) {
			alert("전시실과 사용일정을 선택해주십시오.");
			return;
		}
		
		checkedDtList.sort((a, b) => a.date.localeCompare(b.date));
		
		var data = document.querySelectorAll('#submitForm div');
		var i = 0;
		
		for (var checkedDt of checkedDtList) {
			var inputData = data[i].querySelectorAll('input');
			var knotDate = '';
			var checkedDtNextDay = new Date(checkedDt.date);
			var checkedDtPreDay = new Date(checkedDt.date);
			checkedDtNextDay.setDate(checkedDtNextDay.getDate()+1);
			checkedDtPreDay.setDate(checkedDtPreDay.getDate()-1);
			
			for (var isKnotDate of checkedDtList) {
				if (moment(checkedDt.date).format("e") == '0' && isKnotDate.date == moment(checkedDtPreDay).format("YYYY-MM-DD")) {
					knotDate = isKnotDate.date;
					break;
				}
				if (moment(checkedDt.date).format("e") == '6' && isKnotDate.date == moment(checkedDtNextDay).format("YYYY-MM-DD")) {
					knotDate = isKnotDate.date;
					break;
				}
			}
			
			inputData[0].value = checkedDt.date;
			inputData[1].value = checkedDt.evntDt;
			inputData[2].value = checkedDt.hallType;
			inputData[3].value = urlType;
			inputData[4].value = knotDate;
			i++;
		}
		for (; i<data.length; i++) {
			var inputData = data[i].querySelectorAll('input');
			
			inputData[0].value = '';
			inputData[1].value = '';
			inputData[2].value = '';
			inputData[3].value = '';
			inputData[4].value = '';
		}
		
		document.querySelector("#submitForm").action = "${pageContext.request.contextPath}/front/content/showroomestimate.do?type="+urlType;
		document.querySelector("#submitForm").requestSubmit();
	}

</script>
<!-- 스크립트(E) -->