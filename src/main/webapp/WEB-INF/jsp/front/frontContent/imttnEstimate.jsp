<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

			<!-- sub내용(S) -->
			<div class="sub_conts_in">
				
				<div style="display: none;" id="paramList">
					<%
						String[] date = request.getParameterValues("date");
						String[] evntDt = request.getParameterValues("evntDt");
						String[] hallType = request.getParameterValues("hallType");
						
						if (date != null) {
							for (int i=0; i<date.length; i++) {
								if (date[i].equals("")) {
									break;
								}
								out.println("<div id='param["+i+"]'><p>"+date[i]+"</p><p>"+evntDt[i]+"</p><p>"+hallType[i]+"</p></div>");
							}
						}
					%>
				</div>
				
				<!-- 타이틀(S) -->
				<div class="title_box">
					<div class="fl">
						<h4 class="sub_tit_04">온라인 견적산출</h4>
					</div>
				</div>
				<!-- 타이틀(E) -->
				
				<!-- 견적서 작성(S) -->
				<div id="reservInfo">
					<div class="table_type_01 mt40 overflow_a">
						<table>
							<caption>전시실 예약 시 옵션을 선택하는 테이블입니다.</caption>
							<colgroup>
								<col style="width: 16.665%;">
								<col style="width: 16.665%;">
								<col style="width: 16.665%;">
								<col style="width: 16.665%;">
								<col style="width: 33.33%;">
							</colgroup>
							<thead>
								<tr>
									<th style="padding: 5px; background-color: #EEEEEE;">행사일정</th>
									<th style="padding: 5px; background-color: #FFFFFF; text-align: left;" colspan="4">
										<div style="display: inline-block; padding-left: 15px; width: 38%;">
											<p class="txt_01">예약일자&ensp;:&emsp;</p>
										</div>
										<div style="display: inline-block; width: 18%;">
											<input type="radio" name="evntDt" style="margin-right: 10px; display: inline; position: relative; accent-color: #267FED;" id="AM" onclick="return(false);">
											<label for="AM">09시 ~ 12시</label>
										</div>
										<div style="display: inline-block; width: 18%;">
											<input type="radio" name="evntDt" style="margin-right: 10px; display: inline; position: relative; accent-color: #267FED;" id="PM" onclick="return(false);">
											<label for="PM">13시 ~ 17시</label>
										</div>
										<div style="display: inline-block; width: 18%;">
											<input type="radio" name="evntDt" style="margin-right: 10px; display: inline; position: relative; accent-color: #267FED;" id="ALL" onclick="return(false);">
											<label for="ALL">09시 ~ 17시</label>
										</div>
									</th>
								</tr>
								<tr>
									<th style="padding: 0 0; background-color: #FFFFFF;" colspan="5">
										<div class="tab devide_3 web" style="width: 100%; margin: 0 4px 0 0; display: inline;" name="hallTypeTab">
											<ul>
												<li style="height: 65px; line-height: 30px; border-width: 0 1px 0 0; pointer-events: none !important;">
													<a style="border-width: 0 1px 0;" title="무궁화홀">무궁화홀<br>(전용면적 : 394㎡, 수용인원 : 120명)</a></li>
												<li style="height: 65px; line-height: 30px; border-width: 0 1px 0 0; pointer-events: none !important;">
													<a style="border-width: 0 1px 0;" title="국화홀">국화홀<br>(전용면적 : 225㎡, 수용인원 : 60명)</a></li>
												<li style="height: 65px; line-height: 30px; border-width: 0; pointer-events: none !important;">
													<a style="border-width: 0 1px 0;" title="장미홀">장미홀<br>(전용면적 : 169㎡, 수용인원 : 40명)</a></li>
											</ul>
										</div>
									</th>
								</tr>
								<tr>
									<th>선 택</th>
									<th>장 비 명</th>
									<th>보유수량</th>
									<th>임대수량</th>
									<th>비 고</th>
								</tr>
							</thead>
							<tbody name='sreqpmntpayrfrnc'>
								<tr>
									<td class="tc"><p class="txt_01"></p></td>
									<td class="tc"><p class="txt_01">전시실 복도</p></td>
									<td class="tc" colspan="3" style="text-align: left !important;">
										<input type="radio" name="iscorrdr" style="margin: 0 4px 0 10px; display: inline; position: relative; accent-color: #267FED;" onclick="calculateTotal();" value="true" id="iscorrdr_Y">
										<label for="iscorrdr_Y">예</label>
										<input type="radio" name="iscorrdr" style="margin: 0 4px 0 55px; display: inline; position: relative; accent-color: #267FED;" onclick="calculateTotal();" value="false" id="iscorrdr_N">
										<label for="iscorrdr_N">아니오</label>
									</td>
								</tr>
								<tr>
									<td class="tc"><p class="txt_01"></p></td>
									<td class="tc"><p class="txt_01">냉난방 포함</p></td>
									<td class="tc" colspan="3" style="text-align: left !important;">
										<input type="radio" name="isTemp" style="margin: 0 4px 0 10px; display: inline; position: relative; accent-color: #267FED;" onclick="calculateTotal();" value="true" id="isTemp_Y">
										<label for="isTemp_Y">예</label>
										<input type="radio" name="isTemp" style="margin: 0 4px 0 55px; display: inline; position: relative; accent-color: #267FED;" onclick="calculateTotal();" value="false" id="isTemp_N">
										<label for="isTemp_N">아니오</label>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="table_type_01 mt10 overflow_a">
						<table>
							<caption>전시실 임대료, 관리비, 장비사용료 등의 소계를 계산하는 테이블입니다.</caption>
							<colgroup>
								<col style="width: 25%;">
								<col style="width: 25%;">
								<col style="width: 25%;">
								<col style="width: 25%;">
							</colgroup>
							<thead>
								<tr>
									<th>임 대 료</th>
									<th>관 리 비</th>
									<th>장비 사용료</th>
									<th>소계 (VAT 포함)</th>
								</tr>
							</thead>
							<tbody>
								<tr name="priceSubtotal">
									<td class="tc"><p class="txt_01">-</p></td>
									<td class="tc"><p class="txt_01">-</p></td>
									<td class="tc"><p class="txt_01">-</p></td>
									<td class="tc"><p class="txt_01" style="color: #0000FF;">-</p></td>
								</tr>
							</tbody>
						</table>
					</div>
					<hr class="mt40" width="100%" color="#303030" size="1">
				</div>

				<!-- 타이틀(S) -->
				<div class="title_box mt50">
					<div class="fl" style="display: inline-block; width:20%;">
						<h4 class="sub_tit_04">총 견적 금액 합계</h4>
					</div>
				</div>
				<!-- 타이틀(E) -->
				
				<div class="table_type_01 mt10 overflow_a">
					<table>
						<caption>전시실 임대료, 관리비, 장비사용료 등의 총 합계를 계산하는 테이블입니다.</caption>
						<colgroup>
							<col style="width: 20%;">
							<col style="width: 20%;">
							<col style="width: 20%;">
							<col style="width: 20%;">
							<col style="width: 20%;">
						</colgroup>
						<thead>
							<tr>
								<th>임 대 료</th>
								<th>관 리 비</th>
								<th>장비 사용료</th>
								<th>합계 (VAT 포함)</th>
								<th>견적서</th>
							</tr>
						</thead>
						<tbody>
							<tr id="priceTotal">
								<td class="tc"><p class="txt_01">-</p></td>
								<td class="tc"><p class="txt_01">-</p></td>
								<td class="tc"><p class="txt_01">-</p></td>
								<td class="tc"><p class="txt_01" style="color: #0000FF;">-</p></td>
								<td class="tc"><div class="info_btn_box"><button class="link default" style="width:100%; height: 2em;" onclick="printRsrvCntrct();">출력</button></div></td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- 견적서 작성(E) -->
				
			</div>
			<!-- sub내용(E) -->



<!-- 스크립트(S) -->
<script type="text/javascript">

	var srOption = document.querySelector('tbody[name="sreqpmntpayrfrnc"]').innerHTML;
	var sreqpmntpayrfrnc1 = '',
		sreqpmntpayrfrnc2 = '',
		sreqpmntpayrfrnc3 = '';
	var eqpmntRfrnc = {};
	var prcRfrnc = { sr1 : {},  sr2 : {},  sr3 : {},  iscorrdr : {} };
	var userId;
	
	if ('${atLoginVO.loginId}' != null && '${atLoginVO.loginId}' != '') {
		userId = '${atLoginVO.loginId}';
	} else if ('${snsLoginVO.loginId}' != null && '${snsLoginVO.loginId}' != '') {
		userId = '${snsLoginVO.loginId}';
	}

	$(document).ready(function(){
		$('h2.sub_title').text('온라인 견적산출')
		var queryString = window.location.search;
		var params = new URLSearchParams(queryString);
		var locationParam = params.get('type');
		var urlType = locationParam;
		if (document.querySelectorAll('#paramList div').length <= 0) {
			alert("페이지가 만료되었습니다. 일자 예약화면으로 돌아갑니다.");
			location.href = "${pageContext.request.contextPath}/front/content/showroomreserv.do?id=1&menuId=34&type="+urlType;
		}
		
		// 디비 가격기준표의 값으로 바인딩
		$.ajax({
			type: "get",
			url: "${pageContext.request.contextPath}/front/getcmmnrfrnc.json",
			data: {},
			success: function(data){
				var sr1 = [],
					sr2 = [],
					sr3 = [];
				var srJson = [];

				// 디비 가격기준표의 값으로 바인딩
				$.each(data.prcRfrnc,function(i,v){
					if (v.GBN == '임대료') {
						if (v.SHW_RM == '무궁화홀') { prcRfrnc.sr1.am = v.AM; prcRfrnc.sr1.pm = v.PM; prcRfrnc.sr1.all = v.ALL_DAY; }
						if (v.SHW_RM == '국화홀') { prcRfrnc.sr2.am = v.AM; prcRfrnc.sr2.pm = v.PM; prcRfrnc.sr2.all = v.ALL_DAY; }
						if (v.SHW_RM == '장미홀') { prcRfrnc.sr3.am = v.AM; prcRfrnc.sr3.pm = v.PM; prcRfrnc.sr3.all = v.ALL_DAY; }
						if (v.SHW_RM == '전시실복도') { prcRfrnc.iscorrdr.rntAm = v.AM; prcRfrnc.iscorrdr.rntPm = v.PM; prcRfrnc.iscorrdr.rntAll = v.ALL_DAY; }
					}
					if (v.GBN == '관리비(냉난방제외)') {
						if (v.SHW_RM == '무궁화홀') { prcRfrnc.sr1.notTempAm = v.AM; prcRfrnc.sr1.notTempPm = v.PM; prcRfrnc.sr1.notTempAll = v.ALL_DAY; }
						if (v.SHW_RM == '국화홀') { prcRfrnc.sr2.notTempAm = v.AM; prcRfrnc.sr2.notTempPm = v.PM; prcRfrnc.sr2.notTempAll = v.ALL_DAY; }
						if (v.SHW_RM == '장미홀') { prcRfrnc.sr3.notTempAm = v.AM; prcRfrnc.sr3.notTempPm = v.PM; prcRfrnc.sr3.notTempAll = v.ALL_DAY; }
						if (v.SHW_RM == '전시실복도') { prcRfrnc.iscorrdr.mntAm = v.AM; prcRfrnc.iscorrdr.mntPm = v.PM; prcRfrnc.iscorrdr.mntAll = v.ALL_DAY; }
					}
					if (v.GBN == '관리비(냉난방포함)') {
						if (v.SHW_RM == '무궁화홀') { prcRfrnc.sr1.isTempAm = v.AM; prcRfrnc.sr1.isTempPm = v.PM; prcRfrnc.sr1.isTempAll = v.ALL_DAY; }
						if (v.SHW_RM == '국화홀') { prcRfrnc.sr2.isTempAm = v.AM; prcRfrnc.sr2.isTempPm = v.PM; prcRfrnc.sr2.isTempAll = v.ALL_DAY; }
						if (v.SHW_RM == '장미홀') { prcRfrnc.sr3.isTempAm = v.AM; prcRfrnc.sr3.isTempPm = v.PM; prcRfrnc.sr3.isTempAll = v.ALL_DAY; }
					} 
				})
				
				$.each(data.eqpmntRfrnc,function(i,v){
					if (v.HALL_TYPE == '1') {
						sr1.push(v);
						if (v.EQPMNT_NM == '무선마이크') eqpmntRfrnc.wlsMic = v.DAY_PAY;
						if (v.EQPMNT_NM == '유선마이크') eqpmntRfrnc.wMic = v.DAY_PAY;
						if (v.EQPMNT_NM == '방송엠프') eqpmntRfrnc.bAmp = v.DAY_PAY;
						if (v.EQPMNT_NM == '프로젝터') { eqpmntRfrnc.prjctrDayPay1 = v.DAY_PAY; eqpmntRfrnc.prjctrHalfPay1 = v.HALF_PAY; }
						if (v.EQPMNT_NM == '탁자') eqpmntRfrnc.tbl = v.DAY_PAY;
						if (v.EQPMNT_NM == '의자') eqpmntRfrnc.chr = v.DAY_PAY;
						if (v.EQPMNT_NM == '쓰레기봉투') eqpmntRfrnc.grbgPck = v.DAY_PAY;
					}
					if (v.HALL_TYPE == '2') {
						sr2.push(v);
						if (v.EQPMNT_NM == '프로젝터') { eqpmntRfrnc.prjctrDayPay2 = v.DAY_PAY; eqpmntRfrnc.prjctrHalfPay2 = v.HALF_PAY; }
					}
					if (v.HALL_TYPE == '3') {
						sr3.push(v);
						if (v.EQPMNT_NM == '프로젝터') { eqpmntRfrnc.prjctrDayPay3 = v.DAY_PAY; eqpmntRfrnc.prjctrHalfPay3 = v.HALF_PAY; }
					}
				})
				
				srJson.push(sr1);
				srJson.push(sr2);
				srJson.push(sr3);

				// 최대 임대수량 바인딩
				$.each(srJson,function(index,value){

					var html = '';
					
					$.each(value,function(i,v){
						
						var eqpmntNm = '';
						
						if (v.EQPMNT_NM == '무선마이크') eqpmntNm = 'wlsMic';
						if (v.EQPMNT_NM == '유선마이크') eqpmntNm = 'wMic';
						if (v.EQPMNT_NM == '방송엠프') { eqpmntNm = 'bAmp'; v.ETC = '프로젝터 임대 시 불필요'; }
						if (v.EQPMNT_NM == '프로젝터') eqpmntNm = 'prjctr';
						if (v.EQPMNT_NM == '탁자') { eqpmntNm = 'tbl'; }
						if (v.EQPMNT_NM == '의자') { eqpmntNm = 'chr'; }
						if (v.EQPMNT_NM == '쓰레기봉투') { eqpmntNm = 'grbgPck'; v.ETC = '장당 4,000원'; }
						
						html += '<tr>';
						html += '<td class="tc"><input type="checkbox" style="display: inline;" id="' + eqpmntNm + 'Chk" onchange="selectEquip(event);"></td>';
						html += '<td class="tc"><p class="txt_01">' + v.EQPMNT_NM + '</p></td>';
						html += '<td class="tc"><p class="txt_01">' + empty(v.AMOUNT) + '</p></td>';
						
						if (v.EQPMNT_NM == '방송엠프') {
							html += '<td class="tc"><p class="txt_01"><input type="text" style="width: 40%; border: 1px solid #dcdcdc !important;" oninput="inptNum(this);" onchange="inptOnchange(this.id, ' + empty(v.MAX_AMOUNT) + ');" id="' + eqpmntNm + 'Inpt"> 개</p></td>';
						} else {
							html += '<td class="tc"><p class="txt_01"><input type="text" style="width: 40%;" oninput="inptNum(this);" onchange="inptOnchange(this.id, ' + empty(v.MAX_AMOUNT) + ');" id="' + eqpmntNm + 'Inpt"> 개</p></td>';
						}
						
						html += '<td class="tc"><p class="txt_01">' + empty(v.ETC) + '</p></td>';
						html += '</tr>';
					});
					
					if (index == 0) sreqpmntpayrfrnc1 = html;
					if (index == 1) sreqpmntpayrfrnc2 = html;
					if (index == 2) sreqpmntpayrfrnc3 = html;
				});
				
				setHtml();
			},
			error: function(a,b,c){
				console.log(a,b,c);
			}
		});
		
	});
	
	// 기본 html 코드 작성
	function setHtml() {
		var paramList = document.querySelectorAll('#paramList div');
		var reservInfoHtml = document.querySelector('#reservInfo').innerHTML.split(srOption);
		var html = ''
		
		for (var i=0; i<paramList.length; i++) {
			var param = paramList[i].querySelectorAll('p');
			
			if (param[2].innerText == '1') html += reservInfoHtml[0] + sreqpmntpayrfrnc1 + srOption + reservInfoHtml[1];
			if (param[2].innerText == '2') html += reservInfoHtml[0] + sreqpmntpayrfrnc2 + srOption + reservInfoHtml[1];
			if (param[2].innerText == '3') html += reservInfoHtml[0] + sreqpmntpayrfrnc3 + srOption + reservInfoHtml[1];
		}
		$('#reservInfo').empty();
		$('#reservInfo').html(html);
		
		for (var i=0; i<paramList.length; i++) {
			var param = paramList[i].querySelectorAll('p');
			var reservTable = document.querySelectorAll('#reservInfo table')[i*2];

			// 각 예약정보 table 초기 세팅
			$('div[name="hallTypeTab"]').eq(i).find('li').eq(param[2].innerText - 1).addClass('active');
			reservTable.querySelector('p').innerText = reservTable.querySelector('p').innerText + param[0].innerText;
			reservTable.querySelector('#' + param[1].innerText).checked = true;
			
			// id 변경 및 기능 연결
			reservTable.querySelector('label[for="AM"]').setAttribute('for', 'AM' + (i+1));	// 행사일정
			reservTable.querySelector('label[for="PM"]').setAttribute('for', 'PM' + (i+1));
			reservTable.querySelector('label[for="ALL"]').setAttribute('for', 'ALL' + (i+1));
			reservTable.querySelector('#AM').setAttribute('name', 'evntDt' + (i+1));
			reservTable.querySelector('#PM').setAttribute('name', 'evntDt' + (i+1));
			reservTable.querySelector('#ALL').setAttribute('name', 'evntDt' + (i+1));
			
			reservTable.querySelector('label[for="iscorrdr_Y"]').setAttribute('for', 'iscorrdr_Y' + (i+1));	// 전시실 복도
			reservTable.querySelector('label[for="iscorrdr_N"]').setAttribute('for', 'iscorrdr_N' + (i+1));
			reservTable.querySelector('#iscorrdr_Y').setAttribute('name', 'iscorrdr' + (i+1));
			reservTable.querySelector('#iscorrdr_N').setAttribute('name', 'iscorrdr' + (i+1));
			
			reservTable.querySelector('label[for="isTemp_Y"]').setAttribute('for', 'isTemp_Y' + (i+1));		// 냉난방 포함
			reservTable.querySelector('label[for="isTemp_N"]').setAttribute('for', 'isTemp_N' + (i+1));
			reservTable.querySelector('#isTemp_Y').setAttribute('name', 'isTemp' + (i+1));
			reservTable.querySelector('#isTemp_N').setAttribute('name', 'isTemp' + (i+1));
			
			for (var setId of reservTable.querySelectorAll('input')) {
				setId.id = setId.id + (i+1);
			}
		}
		
		calculateTotal();
	}
	
	// equipment input event
	function inptNum(target) {
		// only number
		target.value = target.value.replace(/[^0-9]/gi, "");
	}
	function inptOnchange(id, max) {
		var inptIdValue = document.querySelector('#'+id).value;
		var index = id.slice(-1);
		var chkId = '#'+id.slice(0, -5)+'Chk'+index;
		
		if (inptIdValue != '' && inptIdValue != null && inptIdValue != 0 && inptIdValue <= max) {
				document.querySelector(chkId).checked = true;
				if (id.slice(0, -1) == 'wlsMicInpt' && !document.querySelector('#prjctrChk'+index).checked) {
					document.querySelector('#bAmpChk'+index).checked = true;		// 방송앰프 		checkbox
					document.querySelector('#bAmpInpt'+index).value = 1;			// 방송앰프 		input
					document.querySelector('#bAmpChk'+index).disabled = true;		// 방송앰프 		checkbox
					document.querySelector('#bAmpInpt'+index).disabled = true;		// 방송앰프 		input
				} else if (id.slice(0, -1) == 'wMicInpt' && !document.querySelector('#prjctrChk'+index).checked) {
					document.querySelector('#bAmpChk'+index).checked = true;		// 방송앰프 		checkbox
					document.querySelector('#bAmpInpt'+index).value = 1;			// 방송앰프 		input
					document.querySelector('#bAmpChk'+index).disabled = true;		// 방송앰프 		checkbox
					document.querySelector('#bAmpInpt'+index).disabled = true;		// 방송앰프 		input
				} else if (id.slice(0, -1) == 'prjctrInpt') {
					document.querySelector('#bAmpChk'+index).checked = false;		// 방송앰프 		checkbox
					document.querySelector('#bAmpInpt'+index).value = "";			// 방송앰프 		input
					document.querySelector('#bAmpChk'+index).disabled = true;		// 방송앰프 		checkbox
					document.querySelector('#bAmpInpt'+index).disabled = true;		// 방송앰프 		input
				}
		} else {
				document.querySelector('#'+id).value = "";
				document.querySelector(chkId).checked = false;
				if (id.slice(0, -1) == 'wlsMicInpt'
					&& !document.querySelector('#wMicChk'+index).checked
					&& !document.querySelector('#prjctrChk'+index).checked) {
					document.querySelector('#bAmpChk'+index).disabled = false;		// 방송앰프 		checkbox
					document.querySelector('#bAmpInpt'+index).disabled = false;		// 방송앰프 		input
				} else if (id.slice(0, -1) == 'wMicInpt'
					&& !document.querySelector('#wlsMicChk'+index).checked
					&& !document.querySelector('#prjctrChk'+index).checked) {
					document.querySelector('#bAmpChk'+index).disabled = false;		// 방송앰프 		checkbox
					document.querySelector('#bAmpInpt'+index).disabled = false;		// 방송앰프 		input
				} else if (id.slice(0, -1) == 'prjctrInpt') {
					if (!document.querySelector('#wlsMicChk'+index).checked && !document.querySelector('#wMicChk'+index).checked) {
						document.querySelector('#bAmpChk'+index).disabled = false;	// 방송앰프 		checkbox
						document.querySelector('#bAmpInpt'+index).disabled = false;	// 방송앰프 		input
					} else {
						document.querySelector('#bAmpChk'+index).checked = true;	// 방송앰프 		checkbox
						document.querySelector('#bAmpInpt'+index).value = 1;		// 방송앰프 		input
					}
				}
				if (inptIdValue > max) {
		 			alert('임대 가능수량을 초과했습니다');
		 		}
		}
		
		calculateTotal();
	}

	// checkbox event
	function selectEquip(event) {
		var index = event.target.id.slice(-1);
		
		if (event.target.id.slice(0, -1) == 'wlsMicChk') {				// 무선마이크
			
			if (event.target.checked && !document.querySelector('#prjctrChk'+index).checked) {
				document.querySelector('#bAmpChk'+index).checked = true;		// 방송앰프 		checkbox
				document.querySelector('#bAmpInpt'+index).value = 1;			// 방송앰프 		input
				document.querySelector('#bAmpChk'+index).disabled = true;		// 방송앰프 		checkbox
				document.querySelector('#bAmpInpt'+index).disabled = true;		// 방송앰프 		input
			} else {
				if (!document.querySelector('#wMicChk'+index).checked && !document.querySelector('#prjctrChk'+index).checked) {
					document.querySelector('#bAmpChk'+index).disabled = false;	// 방송앰프 		checkbox
					document.querySelector('#bAmpInpt'+index).disabled = false;	// 방송앰프 		input
				}
				document.querySelector('#wlsMicInpt'+index).value = "";			// 무선마이크 	input
			}
			
		} else if (event.target.id.slice(0, -1) == 'wMicChk') {			// 유선마이크
			
			if (event.target.checked && !document.querySelector('#prjctrChk'+index).checked) {
				document.querySelector('#bAmpChk'+index).checked = true;		// 방송앰프 		checkbox
				document.querySelector('#bAmpInpt'+index).value = 1;			// 방송앰프 		input
				document.querySelector('#bAmpChk'+index).disabled = true;		// 방송앰프 		checkbox
				document.querySelector('#bAmpInpt'+index).disabled = true;		// 방송앰프 		input
			} else {
				if (!document.querySelector('#wlsMicChk'+index).checked && !document.querySelector('#prjctrChk'+index).checked) {
					document.querySelector('#bAmpChk'+index).disabled = false;	// 방송앰프 		checkbox
					document.querySelector('#bAmpInpt'+index).disabled = false;	// 방송앰프 		input
				}
				document.querySelector('#wMicInpt'+index).value = "";			// 유선마이크 	input
			}
			
		} else if (event.target.id.slice(0, -1) == 'bAmpChk') {			// 방송앰프 
			
			if (event.target.checked) {
				document.querySelector('#bAmpInpt'+index).value = 1;			// 방송앰프 		input
			} else {
				document.querySelector('#bAmpInpt'+index).value = "";			// 방송앰프 		input
			}
			
		} else if (event.target.id.slice(0, -1) == 'prjctrChk') {		// 프로젝터
			
			if (event.target.checked) {
				document.querySelector('#prjctrInpt'+index).value = 1;			// 프로젝터 		input
				document.querySelector('#bAmpChk'+index).checked = false;		// 방송앰프 		checkbox
				document.querySelector('#bAmpInpt'+index).value = "";			// 방송앰프 		input
				document.querySelector('#bAmpChk'+index).disabled = true;		// 방송앰프 		checkbox
				document.querySelector('#bAmpInpt'+index).disabled = true;		// 방송앰프 		input
			} else {
				if (!document.querySelector('#wlsMicChk'+index).checked && !document.querySelector('#wMicChk'+index).checked) {
					document.querySelector('#bAmpChk'+index).disabled = false;	// 방송앰프 		checkbox
					document.querySelector('#bAmpInpt'+index).disabled = false;	// 방송앰프 		input
				} else {
					document.querySelector('#bAmpChk'+index).checked = true;	// 방송앰프 		checkbox
					document.querySelector('#bAmpInpt'+index).value = 1;		// 방송앰프 		input
				}
				
				document.querySelector('#prjctrInpt'+index).value = "";			// 프로젝터 		input
			}
		
		} else {

			if (!event.target.checked) {
				document.querySelector('#' + event.target.id.slice(0, -4)+'Inpt'+index).value = "";
			}
			
		}
		
		calculateTotal();
	}
	
	// calculate total
	function calculateTotal() {
		var priceElList = document.querySelector('#priceTotal').getElementsByTagName('p');
		var rent = 0;
		var maintenance = 0;
		var equipment = 0;
		
		for (var i=0; i<document.querySelectorAll('#paramList div').length; i++) {
			calculateSubTotal(i);
			
			var subtotalList = document.querySelectorAll('tr[name="priceSubtotal"]')[i].getElementsByTagName('p');
			rent += parseInt(subtotalList[0].innerText.replaceAll(',', '').replace(' 원', ''));
			maintenance += parseInt(subtotalList[1].innerText.replaceAll(',', '').replace(' 원', ''));
			equipment += parseInt(subtotalList[2].innerText.replaceAll(',', '').replace(' 원', ''));
		}
		
		priceElList[0].innerText = rent.toLocaleString() + " 원";
		priceElList[1].innerText = maintenance.toLocaleString() + " 원";
		priceElList[2].innerText = equipment.toLocaleString() + " 원";
		priceElList[3].innerText = (rent + maintenance + equipment).toLocaleString() + " 원";
	}
	
	// calculate subtotal
	function calculateSubTotal(index) {
		var hallType = document.querySelectorAll('#paramList div')[index].querySelectorAll('p')[2].innerText;
		var priceElList = document.querySelectorAll('tr[name="priceSubtotal"]')[index].getElementsByTagName('p');
		var iscorrdr = document.querySelector('#iscorrdr_Y'+(index+1));
		var isTemp = document.querySelector('#isTemp_Y'+(index+1));
		var evntDt = document.querySelector('input[name="evntDt'+(index+1)+'"]:checked');
		var rent = 0;
		var maintenance = 0;
		var equipment = 0;
		
		// 임대료 & 관리비
		if (hallType == '1') {				// 무궁화홀
			
			if (evntDt.id == 'AM'+(index+1)) {
				rent = prcRfrnc.sr1.am;
				if (!isTemp.checked) {
					maintenance = prcRfrnc.sr1.notTempAm;
				} else {
					maintenance = prcRfrnc.sr1.isTempAm;
				}
				if (iscorrdr.checked) {
					rent += prcRfrnc.iscorrdr.rntAm;
					maintenance += prcRfrnc.iscorrdr.mntAm;
				}
			} else if (evntDt.id == 'PM'+(index+1)) {
				rent = prcRfrnc.sr1.pm;
				if (!isTemp.checked) {
					maintenance = prcRfrnc.sr1.notTempPm;
				} else {
					maintenance = prcRfrnc.sr1.isTempPm;
				}
				if (iscorrdr.checked) {
					rent += prcRfrnc.iscorrdr.rntPm;
					maintenance += prcRfrnc.iscorrdr.mntPm;
				}
			} else if (evntDt.id == 'ALL'+(index+1)) {
				rent = prcRfrnc.sr1.all;
				if (!isTemp.checked) {
					maintenance = prcRfrnc.sr1.notTempAll;
				} else {
					maintenance = prcRfrnc.sr1.isTempAll;
				}
				if (iscorrdr.checked) {
					rent += prcRfrnc.iscorrdr.rntAll;
					maintenance += prcRfrnc.iscorrdr.mntAll;
				}
			}
			
		} else if (hallType == '2') {		// 국화홀
			
			if (evntDt.id == 'AM'+(index+1)) {
				rent = prcRfrnc.sr2.am;
				if (!isTemp.checked) {
					maintenance = prcRfrnc.sr2.notTempAm;
				} else {
					maintenance = prcRfrnc.sr2.isTempAm;
				}
				if (iscorrdr.checked) {
					rent += prcRfrnc.iscorrdr.rntAm;
					maintenance += prcRfrnc.iscorrdr.mntAm;
				}
			} else if (evntDt.id == 'PM'+(index+1)) {
				rent = prcRfrnc.sr2.pm;
				if (!isTemp.checked) {
					maintenance = prcRfrnc.sr2.notTempPm;
				} else {
					maintenance = prcRfrnc.sr2.isTempPm;
				}
				if (iscorrdr.checked) {
					rent += prcRfrnc.iscorrdr.rntPm;
					maintenance += prcRfrnc.iscorrdr.mntPm;
				}
			} else if (evntDt.id == 'ALL'+(index+1)) {
				rent = prcRfrnc.sr2.all;
				if (!isTemp.checked) {
					maintenance = prcRfrnc.sr2.notTempAll;
				} else {
					maintenance = prcRfrnc.sr2.isTempAll;
				}
				if (iscorrdr.checked) {
					rent += prcRfrnc.iscorrdr.rntAll;
					maintenance += prcRfrnc.iscorrdr.mntAll;
				}
			}
			
		} else if (hallType == '3') {		// 장미홀
			
			if (evntDt.id == 'AM'+(index+1)) {
				rent = prcRfrnc.sr3.am;
				if (!isTemp.checked) {
					maintenance = prcRfrnc.sr3.notTempAm;
				} else {
					maintenance = prcRfrnc.sr3.isTempAm;
				}
				if (iscorrdr.checked) {
					rent += prcRfrnc.iscorrdr.rntAm;
					maintenance += prcRfrnc.iscorrdr.mntAm;
				}
			} else if (evntDt.id == 'PM'+(index+1)) {
				rent = prcRfrnc.sr3.pm;
				if (!isTemp.checked) {
					maintenance = prcRfrnc.sr3.notTempPm;
				} else {
					maintenance = prcRfrnc.sr3.isTempPm;
				}
				if (iscorrdr.checked) {
					rent += prcRfrnc.iscorrdr.rntPm;
					maintenance += prcRfrnc.iscorrdr.mntPm;
				}
			} else if (evntDt.id == 'ALL'+(index+1)) {
				rent = prcRfrnc.sr3.all;
				if (!isTemp.checked) {
					maintenance = prcRfrnc.sr3.notTempAll;
				} else {
					maintenance = prcRfrnc.sr3.isTempAll;
				}
				if (iscorrdr.checked) {
					rent += prcRfrnc.iscorrdr.rntAll;
					maintenance += prcRfrnc.iscorrdr.mntAll;
				}
			}
			
		}
		
		// 장비 사용료
		if (document.querySelector('#wlsMicChk'+(index+1)).checked) {
			if (document.querySelector('#wlsMicInpt'+(index+1)).value != ''
				&& document.querySelector('#wlsMicInpt'+(index+1)).value != null
				&& document.querySelector('#wlsMicInpt'+(index+1)).value != 0) {
				equipment += document.querySelector('#wlsMicInpt'+(index+1)).value * eqpmntRfrnc.wlsMic;
			}
		}
		if (document.querySelector('#wMicChk'+(index+1)).checked) {
			if (document.querySelector('#wMicInpt'+(index+1)).value != ''
				&& document.querySelector('#wMicInpt'+(index+1)).value != null
				&& document.querySelector('#wMicInpt'+(index+1)).value != 0) {
				equipment += document.querySelector('#wMicInpt'+(index+1)).value * eqpmntRfrnc.wMic;
			}
		}
		if (document.querySelector('#prjctrChk'+(index+1)).checked) {
			if (document.querySelector('#prjctrInpt'+(index+1)).value != ''
				&& document.querySelector('#prjctrInpt'+(index+1)).value != null
				&& document.querySelector('#prjctrInpt'+(index+1)).value != 0) {
				if (document.querySelector('#ALL'+(index+1)).checked) {
					if (hallType == '1') {
						equipment += document.querySelector('#prjctrInpt'+(index+1)).value * eqpmntRfrnc.prjctrDayPay1;
					} else if (hallType == '2') {
						equipment += document.querySelector('#prjctrInpt'+(index+1)).value * eqpmntRfrnc.prjctrDayPay2;
					} else if (hallType == '3') {
						equipment += document.querySelector('#prjctrInpt'+(index+1)).value * eqpmntRfrnc.prjctrDayPay3;
					}
				} else if (document.querySelector('#AM'+(index+1)).checked || document.querySelector('#PM'+(index+1)).checked) {
					if (hallType == '1') {
						equipment += document.querySelector('#prjctrInpt'+(index+1)).value * eqpmntRfrnc.prjctrHalfPay1;
					} else if (hallType == '2') {
						equipment += document.querySelector('#prjctrInpt'+(index+1)).value * eqpmntRfrnc.prjctrHalfPay2;
					} else if (hallType == '3') {
						equipment += document.querySelector('#prjctrInpt'+(index+1)).value * eqpmntRfrnc.prjctrHalfPay3;
					}
				}
			}
		} else {
			if (document.querySelector('#bAmpChk'+(index+1)).checked) {
				if (document.querySelector('#bAmpInpt'+(index+1)).value != ''
					&& document.querySelector('#bAmpInpt'+(index+1)).value != null
					&& document.querySelector('#bAmpInpt'+(index+1)).value != 0) {
					equipment += document.querySelector('#bAmpInpt'+(index+1)).value * eqpmntRfrnc.bAmp;
				}
			}
		}
		if (document.querySelector('#tblChk'+(index+1)).checked) {
			if (document.querySelector('#tblInpt'+(index+1)).value != ''
				&& document.querySelector('#tblInpt'+(index+1)).value != null
				&& document.querySelector('#tblInpt'+(index+1)).value != 0) {
				equipment += document.querySelector('#tblInpt'+(index+1)).value * eqpmntRfrnc.tbl;
			}
		}
		if (document.querySelector('#chrChk'+(index+1)).checked) {
			if (document.querySelector('#chrInpt'+(index+1)).value != ''
				&& document.querySelector('#chrInpt'+(index+1)).value != null
				&& document.querySelector('#chrInpt'+(index+1)).value != 0) {
				equipment += document.querySelector('#chrInpt'+(index+1)).value * eqpmntRfrnc.chr;
			}
		}
		if (document.querySelector('#grbgPckChk'+(index+1)).checked) {
			if (document.querySelector('#grbgPckInpt'+(index+1)).value != ''
				&& document.querySelector('#grbgPckInpt'+(index+1)).value != null
				&& document.querySelector('#grbgPckInpt'+(index+1)).value != 0) {
				equipment += document.querySelector('#grbgPckInpt'+(index+1)).value * eqpmntRfrnc.grbgPck;
			}
		}
		
		priceElList[0].innerText = rent.toLocaleString() + " 원";
		priceElList[1].innerText = maintenance.toLocaleString() + " 원";
		priceElList[2].innerText = equipment.toLocaleString() + " 원";
		priceElList[3].innerText = (rent + maintenance + equipment).toLocaleString() + " 원";
	}
	
	function empty(data){
		return typeof data == 'undefined' ? '' : data === null ? '' : data;
	}
	
	// 계약서 출력
	function printRsrvCntrct(detail){
		var priceSubtotal = document.querySelectorAll('tr[name="priceSubtotal"]');
		var priceTotal = document.querySelector('#priceTotal').getElementsByTagName('p');

		if (priceTotal[0].innerText == '-') {
			alert("견적을 먼저 계산한 후, 예약해주십시오.");
			return;
		}
		for (var i=0; i<priceSubtotal.length; i++) {
			if (priceSubtotal[i].getElementsByTagName('p')[0].innerText == '-') {
				alert("견적을 먼저 계산한 후, 예약해주십시오.");
				return;
			}
		}
		
		var estimateInfoData = [];
		for (var i=0; i<document.querySelectorAll('#paramList div').length; i++) {
			var obj = {};
			
			if (document.querySelector('input[name="iscorrdr'+(i+1)+'"]:checked') == null) {
				alert("전시실 복도 사용 여부를 선택해야 합니다.");
				return;
			}
			if (document.querySelector('input[name="isTemp'+(i+1)+'"]:checked') == null) {
				alert("냉난방 포함 여부를 선택해야 합니다.");
				return;
			}
			
			obj.rsrvDtStart = document.querySelectorAll('#paramList div')[i].children[0].textContent;
			obj.evntDt = document.querySelectorAll('#paramList div')[i].children[1].textContent;
			obj.hallType = document.querySelectorAll('#paramList div')[i].children[2].textContent;
			obj.rentPay = document.querySelectorAll('tr[name="priceSubtotal"]')[i].children[0].textContent;
			obj.mngPay = document.querySelectorAll('tr[name="priceSubtotal"]')[i].children[1].textContent;
			obj.eqpmntPay = document.querySelectorAll('tr[name="priceSubtotal"]')[i].children[2].textContent;
			obj.totalPay = document.querySelectorAll('tr[name="priceSubtotal"]')[i].children[3].textContent;
			obj.wlsMicInpt = document.querySelector('#wlsMicInpt'+(i+1)).value;
			obj.wMicInpt = document.querySelector('#wMicInpt'+(i+1)).value;
			obj.prjctrInpt = document.querySelector('#prjctrInpt'+(i+1)).value;
			obj.tblInpt = document.querySelector('#tblInpt'+(i+1)).value;
			obj.bAmpInpt = document.querySelector('#bAmpInpt'+(i+1)).value;
			obj.chrInpt = document.querySelector('#chrInpt'+(i+1)).value;
			obj.grbgPckInpt = document.querySelector('#grbgPckInpt'+(i+1)).value;
			obj.rentTotal = document.querySelector('#priceTotal').children[0].textContent;
			obj.mngTotal = document.querySelector('#priceTotal').children[1].textContent;
			obj.eqpmntTotal = document.querySelector('#priceTotal').children[2].textContent;
			obj.total = document.querySelector('#priceTotal').children[3].textContent;
			obj.eqpmntRef = eqpmntRfrnc;
			estimateInfoData.push(obj);
		}
		
		var url = '${pageContext.request.contextPath}/front/print/srContractEstimate.do';
					
		const formData = new FormData();

		formData.append('params', JSON.stringify(estimateInfoData));
		for (let pair of formData.entries()) {
			console.log(pair[0] + ': ' + pair[1]);
		}
		const form = document.createElement('form');
		form.method = 'POST';
		form.action = url;
		form.target = '_blank';
	
		// 기존 폼에 FormData 객체의 데이터를 추가
		for (let [name, value] of formData.entries()) {
			let input = document.createElement('input');
			input.type = 'hidden';
			input.name = name;
			input.value = value;
			form.appendChild(input);
		}
		// 폼을 문서에 추가 (필수: form이 submit되기 위해)
		document.body.appendChild(form);

		// 폼 제출
		form.submit();

		// 제출 후 폼 제거 (선택 사항)
		document.body.removeChild(form);

		return false;
	}
	
</script>
<!-- 스크립트(E) -->