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
						String[] knotDate = request.getParameterValues("knotDate");
						
						if (date != null) {
							for (int i=0; i<date.length; i++) {
								if (date[i].equals("")) {
									break;
								}
								out.println("<div id='param["+i+"]'><p>"+date[i]+"</p><p>"+evntDt[i]+"</p><p>"+hallType[i]+"</p><p>"+knotDate[i]+"</p></div>");
							}
						}
					%>
				</div>
				
				<!-- 타이틀(S) -->
				<div class="title_box">
					<div class="fl">
						<h4 class="sub_tit_04">견적서 작성</h4>
					</div>
				</div>
				<!-- 타이틀(E) -->
			
				<!-- 견적서 작성(S) -->
				<c:if test="${(atLoginVO ne null or snsLoginVO ne null)}">
				<div class="pd_lr_15">
					<div class="r_story" style="float: right;">
						<p class="s_txt"><span style="color: #e53c48; font-size: 15px; font-weight: bold">*</span> 표시가 있는 항목은 필수입력 항목입니다.</p>
					</div>
					<div style="clear: both;"></div>
					<div class="table_type_01 mt10 overflow_a">
						<table>
							<caption>전시실 예약 시 행사정보를 테이블입니다.</caption>
							<colgroup>
								<col style="width: 16.665%;">
								<col style="width: 16.665%;">
								<col style="width: 16.665%;">
								<col style="width: 16.665%;">
								<col style="width: 33.33%;">
							</colgroup>
							<thead style="border-bottom: 1px solid #dcdcdc;">
								<tr>
									<th style="padding: 5px; background-color: #EEEEEE;"><span style="color: #e53c48; font-size: 15px; font-weight: bold">*</span>행 사 명</th>
									<th style="padding: 5px; background-color: #FFFFFF;" colspan="4">
										<input type="text" style="width: 100%;" oninput="oninptRsrvInfo(this, 100);" id="evntNm">
									</th>
								</tr>
								<tr>
									<th style="padding: 5px; background-color: #EEEEEE;"><span style="color: #e53c48; font-size: 15px; font-weight: bold">*</span>행사내용</th>
									<th style="padding: 5px; background-color: #FFFFFF;" colspan="4">
										<input type="text" style="width: 100%;" oninput="oninptRsrvInfo(this, 100);" id="evntDesc">
									</th>
								</tr>
								<tr>
									<th style="padding: 5px; background-color: #EEEEEE;"><span style="color: #e53c48; font-size: 15px; font-weight: bold">*</span>상호/단체명</th>
									<th style="padding: 5px; background-color: #FFFFFF;" colspan="4">
										<input type="text" style="width: 100%;" oninput="oninptRsrvInfo(this, 100);" id="orgNm">
									</th>
								</tr>
								<tr>
									<th style="padding: 5px; background-color: #EEEEEE;"><span style="color: #e53c48; font-size: 15px; font-weight: bold">*</span>사업자등록번호</th>
									<th style="padding: 5px; background-color: #FFFFFF;" colspan="2">
										<input type="text" style="width: 100%; border: 1px solid #dcdcdc !important; background-color: field;" oninput="oninputComRgstNo(this);" onchange="onchangeComRgstNo(this);" id="comRgstNo">
									</th>
									<th style="padding: 5px; background-color: #EEEEEE;"><span style="color: #e53c48; font-size: 15px; font-weight: bold">*</span>사업자등록증</th>
									<th style="padding: 5px; background-color: #FFFFFF; text-align: left !important">
										<input type="text" style="width:calc(100% - 48px); margin: 0 4px 0 0; display: inline; border: 1px solid #dcdcdc !important; background-color: field;" id="comRgstNoFileNm" disabled>
	    								<input type="file" id="comRgstNoFile" style="display: none;" onchange="onchangeFile(event);" accept=".jpg, .jpeg, .png, .gif, .bmp, .svg, .pdf">
										<label
											for="comRgstNoFile"
											style="	display: inline-block;
													min-width: 40px;
													height: 30px;
													color: #FFFFFF;
													background: #7d7d7d;
													font: 14px Arial;
													font-weight: 500;
													letter-spacing: normal;
													position: relative;
													vertical-align: top;
													text-align: center;
													line-height: 2em;">첨부
										</label>
									</th>
								</tr>
								<tr>
									<th style="padding: 5px; background-color: #EEEEEE;"><span style="color: #e53c48; font-size: 15px; font-weight: bold">*</span>대 표 자</th>
									<th style="padding: 5px; background-color: #FFFFFF;" colspan="2">
										<input type="text" style="width: 100%;" oninput="oninptRsrvInfo(this, 50);" id="rprsntrNm">
									</th>
									<th style="padding: 5px; background-color: #EEEEEE;"><span style="color: #e53c48; font-size: 15px; font-weight: bold">*</span>담 당 자</th>
									<th style="padding: 5px; background-color: #FFFFFF;">
										<input type="text" style="width: 100%; border: 1px solid #dcdcdc !important;" oninput="oninptRsrvInfo(this, 50);" id="mngrNm" disabled>
									</th>
								</tr>
								<tr>
									<th style="padding: 5px; background-color: #EEEEEE;">업 태</th>
									<th style="padding: 5px; background-color: #FFFFFF;" colspan="2">
										<input type="text" style="width: 100%;" oninput="oninptRsrvInfo(this, 50);" id="bizType">
									</th>
									<th style="padding: 5px; background-color: #EEEEEE;">종 목</th>
									<th style="padding: 5px; background-color: #FFFFFF;">
										<input type="text" style="width: 100%;" oninput="oninptRsrvInfo(this, 50);" id="bizType2">
									</th>
								</tr>
								<tr>
									<th style="padding: 5px; background-color: #EEEEEE;"><span style="color: #e53c48; font-size: 15px; font-weight: bold">*</span>휴대전화번호<br>(SMS 수신용)</th>
									<th style="padding: 5px; background-color: #FFFFFF;" colspan="2">
										<input type="text" style="width: 100%; border: 1px solid #dcdcdc !important;" oninput="oninputTelNoFaxNo(this);" onchange="onchangeTelNo(this);" id="telNo" disabled>
									</th>
									<th style="padding: 5px; background-color: #EEEEEE;">유선전화번호</th>
									<th style="padding: 5px; background-color: #FFFFFF;">
										<input type="text" style="width: 100%;" oninput="oninputTelNoFaxNo(this);" onchange="onchangeFaxNo(this);" id="faxNo">
									</th>
								</tr>
								<tr>
									<th style="padding: 5px; background-color: #EEEEEE;"><span style="color: #e53c48; font-size: 15px; font-weight: bold">*</span>주 소</th>
									<th style="padding: 5px; background-color: #FFFFFF;" colspan="2">
										<input type="text" style="width: 100%; border: 1px solid #dcdcdc !important; background-color: field;" oninput="oninptRsrvInfo(this, 100);" onclick="openAddress();" id="address" readonly>
									</th>
									<th style="padding: 5px; background-color: #EEEEEE;"><span style="color: #e53c48; font-size: 15px; font-weight: bold">*</span>상세주소</th>
									<th style="padding: 5px; background-color: #FFFFFF;">
										<input type="text" style="width: 100%;" oninput="oninptRsrvInfo(this, 100);" id="addressDtl">
									</th>
								</tr>
								<tr>
									<th style="padding: 5px; background-color: #EEEEEE;"><span style="color: #e53c48; font-size: 15px; font-weight: bold">*</span>이 메 일</th>
									<th style="padding: 5px; background-color: #FFFFFF;" colspan="4">
										<input type="text" style="width: 100%; border: 1px solid #dcdcdc !important;" oninput="oninptRsrvInfo(this, 100);" onchange="onchangeEmail(this);" id="email" disabled>
									</th>
								</tr>
								<tr>
									<th style="padding: 5px; background-color: #EEEEEE;">요청사항</th>
									<th style="padding: 5px; background-color: #FFFFFF;" colspan="4">
										<input type="text" style="width: 100%;" oninput="oninptRsrvInfo(this, 200);" id="etc">
									</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				</c:if>	
				
				<hr class="mt40" width="100%" color="#303030" size="1">
				<div id="reservInfo" class="pd_lr_15">
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
											<p class="txt_01">예약일자 : </p>
										</div>
										<div style="display: inline-block; margin-right: 10px;">
											<input type="radio" name="evntDt" style="display: inline; position: relative; accent-color: #267FED;" id="AM" onclick="return(false);">
											<label for="AM">09시 ~ 12시</label>
										</div>
										<div style="display: inline-block; margin-right:10px;">
											<input type="radio" name="evntDt" style="display: inline; position: relative; accent-color: #267FED;" id="PM" onclick="return(false);">
											<label for="PM">13시 ~ 17시</label>
										</div>
										<div style="display: inline-block; margin-right:10px;">
											<input type="radio" name="evntDt" style="display: inline; position: relative; accent-color: #267FED;" id="ALL" onclick="return(false);">
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
									<td class="tc" colspan="3" style="text-align: left !important;padding-left:10px">
										<input type="radio" name="iscorrdr" style="display: inline; position: relative; accent-color: #267FED;" onclick="onclickRadio(this);" value="true" id="iscorrdr_Y">
										<label for="iscorrdr_Y" style="margin-right:50px;">예</label>
										<input type="radio" name="iscorrdr" style="display: inline; position: relative; accent-color: #267FED;" onclick="onclickRadio(this);" value="false" id="iscorrdr_N">
										<label for="iscorrdr_N">아니오</label>
										<span   class="s_txt" style="color: #ff0000;display:flex;">※ 전시실 복도는 주말에만 이용 가능합니다.</span>
									</td>
								</tr>
								<tr>
									<td class="tc"><p class="txt_01"></p></td>
									<td class="tc"><p class="txt_01">냉난방 포함</p></td>
									<td class="tc" colspan="3" style="text-align: left !important;padding-left:10px;">
										<input type="radio" name="isTemp" style="display: inline; position: relative; accent-color: #267FED;" onclick="onclickRadio(this);" value="true" id="isTemp_Y">
										<label for="isTemp_Y" style="margin-right:50px;">예</label>
										<input type="radio" name="isTemp" style="display: inline; position: relative; accent-color: #267FED;" onclick="onclickRadio(this);" value="false" id="isTemp_N">
										<label for="isTemp_N">아니오</label>
									</td>
								</tr>
								<tr>
									<td class="tc"><p class="txt_01"></p></td>
									<td class="tc"><p class="txt_01">배치 형태</p></td>
									<td class="tc" colspan="3" style="text-align: left !important; padding-left:10px;">
										<input type="radio" name="btchStyl" style="display: inline; position: relative; accent-color: #267FED;" onclick="onclickBtchStyl(this);" id="A" disabled>
										<label for="A" style="margin-right: 25px;">교실형</label>
										<input type="radio" name="btchStyl" style="display: inline; position: relative; accent-color: #267FED;" onclick="onclickBtchStyl(this);" id="B" disabled>
										<label for="B" style="margin-right: 25px;">사각회의형</label>
										<input type="radio" name="btchStyl" style="display: inline; position: relative; accent-color: #267FED;" onclick="onclickBtchStyl(this);" id="C" disabled>
										<label for="C" style="margin-right: 3px;">고객선택형</label>
										<input type="text" style="display: inline; border: 1px solid #dcdcdc !important;" id="btchStylFileNm" disabled>
	    								<input type="file" id="btchStylFile" style="display: none;" onchange="onchangeFile(event);" accept=".jpg, .jpeg, .png, .gif, .bmp, .svg, .pdf">
										<label
											id="attachBtchStyl"
											style="	display: inline-block;
													min-width: 40px;
													height: 30px;
													color: #FFFFFF;
													background: #7d7d7d;
													font: 14px Arial;
													font-weight: 500;
													letter-spacing: normal;
													position: relative;
													vertical-align: top;
													text-align: center;
													line-height: 2em;">첨부
										</label>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="r_story" style="float: right;">
						<p class="s_txt" style="color: #ff0000;">※ 배치 형태를 고객선택형으로 선택하실 경우, 배치도를 첨부하셔야 합니다.(jpg,png,gif,bmp,svg,pdf)</p>
					</div>
					<div style="clear: both;"></div>
					<div class="table_type_01 mt10 overflow_a">
						<table>
							<caption>전시실 임대료, 관리비, 장비사용료 등의 소계를 계산하는 테이블입니다.</caption>
							<colgroup>
								<col style="width: 25%;">
								<col style="">
								<col style="">
								<col style="">
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


				<!-- 동의서 (S) -->
				<jsp:include page="/WEB-INF/jsp/front/frontContent/srAgree.jsp"/>
				<!-- 동의서 (E) -->


				<!-- 타이틀(S) -->
				<div class="title_box mt50">
					<div class="fl" style="display: inline-block; pd_lr_15">
						<h4 class="sub_tit_04">총 견적 금액 합계</h4>
					</div>
				</div>
				<!-- 타이틀(E) -->
				
				<div class="table_type_01 mt10 overflow_a">
					<table>
						<caption>전시실 임대료, 관리비, 장비사용료 등의 총 합계를 계산하는 테이블입니다.</caption>
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
								<th>합계 (VAT 포함)</th>
							</tr>
						</thead>
						<tbody>
							<tr id="priceTotal">
								<td class="tc"><p class="txt_01">-</p></td>
								<td class="tc"><p class="txt_01">-</p></td>
								<td class="tc"><p class="txt_01">-</p></td>
								<td class="tc"><p class="txt_01" style="color: #0000FF;">-</p></td>
							</tr>
						</tbody>
					</table>
				</div>
				<%-- <c:if test="${(atLoginVO ne null or snsLoginVO ne null)}"> --%>
					<div class="info_btn_box mt70">
						<button class="link reservation" style="position: relative; left: 33.3%;" onclick="applReserv();">예약 신청</button>
					</div>
				<%-- </c:if> --%>
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
		var queryString = window.location.search;
		var params = new URLSearchParams(queryString);
		var locationParam = params.get('type');
		var urlType = locationParam;
		$('#mngrNm').val('${atLoginVO.name}');
		$('#email').val('${atLoginVO.email}');
		$('#telNo').val('${atLoginVO.phone}');
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
							html += '<td class="tc"><p class="txt_01"><input type="text" style="width: 40%; border: 1px solid #dcdcdc !important;" oninput="inptNum(this);" onchange="inptOnchange(this, ' + empty(v.MAX_AMOUNT) + ');" id="' + eqpmntNm + 'Inpt"> 개</p></td>';
						} else {
							html += '<td class="tc"><p class="txt_01"><input type="text" style="width: 40%;" oninput="inptNum(this);" onchange="inptOnchange(this, ' + empty(v.MAX_AMOUNT) + ');" id="' + eqpmntNm + 'Inpt"> 개</p></td>';
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
		
		// 예약 정보 세션 가져오기
		if (userId != null && userId != "") {
			$.ajax({
				type: "post",
				url: "${pageContext.request.contextPath}/front/getRsrvSessionInfo.json",
				data: ({userId : userId}),
				success: function(data){
					if (data.rsrvsessioninfo == null) return;
					
					if (data.rsrvsessioninfo.evntNm != null) {
						document.querySelector('#evntNm').value = data.rsrvsessioninfo.evntNm;
					}
					if (data.rsrvsessioninfo.evntDesc != null) {
						document.querySelector('#evntDesc').value = data.rsrvsessioninfo.evntDesc;
					}
					if (data.rsrvsessioninfo.orgNm != null) {
						document.querySelector('#orgNm').value = data.rsrvsessioninfo.orgNm;
					}
					if (data.rsrvsessioninfo.comRgstNo != null) {
						document.querySelector('#comRgstNo').value = data.rsrvsessioninfo.comRgstNo;
						oninputComRgstNo(document.querySelector('#comRgstNo'));
						document.querySelector('#comRgstNoFileNm').value = data.rsrvsessioninfo.comRgstNoFileNm;
					}
					if (data.rsrvsessioninfo.rprsntrNm != null) {
						document.querySelector('#rprsntrNm').value = data.rsrvsessioninfo.rprsntrNm;
					}
					if (data.rsrvsessioninfo.mngrNm != null) {
						document.querySelector('#mngrNm').value = data.rsrvsessioninfo.mngrNm;
					} else {
						document.querySelector('#mngrNm').value = '${atLoginVO.name}';
					}
					if (data.rsrvsessioninfo.bizType != null) {
						document.querySelector('#bizType').value = data.rsrvsessioninfo.bizType;
					}
					if (data.rsrvsessioninfo.bizType2 != null) {
						document.querySelector('#bizType2').value = data.rsrvsessioninfo.bizType2;
					}
					if (data.rsrvsessioninfo.telNo != null) {
						document.querySelector('#telNo').value = data.rsrvsessioninfo.telNo;
						oninputTelNoFaxNo(document.querySelector('#telNo'));
					} else {
						document.querySelector('#telNo').value = '${atLoginVO.phone}';
					}
					if (data.rsrvsessioninfo.faxNo != null) {
						document.querySelector('#faxNo').value = data.rsrvsessioninfo.faxNo;
						oninputTelNoFaxNo(document.querySelector('#faxNo'));
					}
					if (data.rsrvsessioninfo.address != null) {
						document.querySelector('#address').value = data.rsrvsessioninfo.address;
					}
					if (data.rsrvsessioninfo.addressDtl != null) {
						document.querySelector('#addressDtl').value = data.rsrvsessioninfo.addressDtl;
					}
					if (data.rsrvsessioninfo.email != null) {
						document.querySelector('#email').value = data.rsrvsessioninfo.email;
					} else {
						document.querySelector('#email').value = '${atLoginVO.email}';
					}
				},
				error: function(a,b,c){
					console.log(a,b,c);
				}
			});
		}
		
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
	    	$('tbody').eq(i*2).attr('data-date', moment(param[0].innerText).format("YYYYMMDD"));
	    	if (param[3].innerText == '') {
		    	$('tbody').eq(i*2).attr('data-knot-date', 'noKnotDate');
	    	} else {
		    	$('tbody').eq(i*2).attr('data-knot-date', moment(param[3].innerText).format("YYYYMMDD"));
	    	}
			reservTable.querySelector('p').innerText = reservTable.querySelector('p').innerText + param[0].innerText;
			reservTable.querySelector('#' + param[1].innerText).checked = true;
			
			// id 및  name 변경
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

			reservTable.querySelector('label[for="A"]').setAttribute('for', 'A' + (i+1));	// 배치 형태
			reservTable.querySelector('label[for="B"]').setAttribute('for', 'B' + (i+1));
			reservTable.querySelector('label[for="C"]').setAttribute('for', 'C' + (i+1));
			reservTable.querySelector('#A').setAttribute('name', 'btchStyl' + (i+1));
			reservTable.querySelector('#B').setAttribute('name', 'btchStyl' + (i+1));
			reservTable.querySelector('#C').setAttribute('name', 'btchStyl' + (i+1));
			reservTable.querySelector('label[id="attachBtchStyl"]').id = 'attachBtchStyl' + (i+1);
			
			for (var setId of reservTable.querySelectorAll('input')) {
				setId.id = setId.id + (i+1);
			}
		}
		
		calculateTotal();
	}
	
	// reservation information input limit
	function oninptRsrvInfo(target, limitByte) {
		for(b = i = 0; c = target.value.charCodeAt(i);) {
			b += c >> 11 ? 3 : c >> 7 ? 2 : 1;
			if (b > limitByte) {
				alert("최대 입력가능 용량입니다.");
				break;
			}
			i++;
		}
	    
		target.value = target.value.substring(0,i);
	}

	// reservation information input format
	function oninputComRgstNo(target) {
		target.value = target.value
			.replace(/[^0-9]/g, '')
			.replace(/(^[0-9]{3})([0-9]{2})([0-9]{5})/g, "$1-$2-$3")
			.slice(0,12);
	}
	function onchangeComRgstNo(target) {
		if (target.value != null && target.value != ''
			&& !(/(^[0-9]{3})\-([0-9]{2})\-([0-9]{5})/g).test(target.value)) {
			alert("올바른 사업자등록번호 형식이 아닙니다.");
			return;
		}
	}
	function oninputTelNoFaxNo(target) {
		target.value = target.value
			.replace(/[^0-9]/g, '')
			.replace(/(^02.{0}|^01.{1}|^0.{2})([0-9]{3,4})([0-9]{4})/g, "$1-$2-$3");
		if ((/^02.{0}/).test(target.value)) {
			target.value = target.value.slice(0,12);
		} else {
			target.value = target.value.slice(0,13);
		}
	}
	function onchangeTelNo(target) {
		if (target.value != null && target.value != ''
			&& !(/(^01.{1})\-([0-9]{3,4})\-([0-9]{4})/g).test(target.value)) {
			if (target.id == 'telNo') {
				alert("올바른 휴대전화번호 형식이 아닙니다.");
			}
			return;
		}
	}
	function onchangeFaxNo(target) {
		if (target.value != null && target.value != ''
			&& !(/(^02.{0}|^0.{2})\-([0-9]{3,4})\-([0-9]{4})/g).test(target.value)) {
			if (target.id == 'faxNo') {
				alert("올바른 유선전화번호 형식이 아닙니다.");
			}
			return;
		}
	}
	function openAddress() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
                // 예제를 참고하여 다양한 활용법을 확인해 보세요.
                let address = "("+data.zonecode+") ";
                if(data.userSelectedType == 'R'){
                    address += data.roadAddress;
                } else {
                    address += data.jibunAddress;
                }
                if(data.apartment == 'Y')
                    address += ' ' + data.buildingName + ' ';
                $('#address').val(address);
            }
        }).open();
    }
	/* function onchangeEmail(target) {
		if (target.value != null && target.value != ''
			&& !(/^[0-9a-zA-Z]*@[0-9a-zA-Z]([\-\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/).test(target.value)) {
			alert("올바른 이메일 형식이 아닙니다.");
			return;
		}
	} */
	
	// equipment input event
	function inptNum(target) {
		// only number
		target.value = target.value.replace(/[^0-9]/gi, "");
	}
	function inptOnchange(target, max) {
		var id = target.id;
		var inptIdValue = document.querySelector('#'+id).value;
		var index = id.slice(-1);
		var chkId = '#'+id.slice(0, -5)+'Chk'+index;
		var knotDate = document.querySelector('#'+id).parentNode.parentNode.parentNode.parentNode.dataset.knotDate;
		
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
				} else if (id.slice(0, -1) == 'tblInpt') {
					document.querySelector('#A'+index).disabled = false;
					document.querySelector('#B'+index).disabled = false;
					document.querySelector('#C'+index).disabled = false;
					if (document.querySelector('input[name="btchStyl'+index+'"]:checked') == null) {
						document.querySelector('#A'+index).checked = true;
					}
				} else if (id.slice(0, -1) == 'chrInpt') {
					document.querySelector('#A'+index).disabled = false;
					document.querySelector('#B'+index).disabled = false;
					document.querySelector('#C'+index).disabled = false;
					if (document.querySelector('input[name="btchStyl'+index+'"]:checked') == null) {
						document.querySelector('#A'+index).checked = true;
					}
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
				} else if (id.slice(0, -1) == 'tblInpt' && !document.querySelector('#chrChk'+index).checked) {
					if (document.querySelector('input[name="btchStyl'+index+'"]:checked') != null) {
						document.querySelector('input[name="btchStyl'+index+'"]:checked').checked = false;
					}
					document.querySelector('#btchStylFileNm'+index).style.backgroundColor = '';
		        	$('#attachBtchStyl'+index).attr('for', '');
					document.querySelector('#A'+index).disabled = true;
					document.querySelector('#B'+index).disabled = true;
					document.querySelector('#C'+index).disabled = true;
				} else if (id.slice(0, -1) == 'chrInpt' && !document.querySelector('#tblChk'+index).checked) {
					if (document.querySelector('input[name="btchStyl'+index+'"]:checked') != null) {
						document.querySelector('input[name="btchStyl'+index+'"]:checked').checked = false;
					}
					document.querySelector('#btchStylFileNm'+index).style.backgroundColor = '';
		        	$('#attachBtchStyl'+index).attr('for', '');
					document.querySelector('#A'+index).disabled = true;
					document.querySelector('#B'+index).disabled = true;
					document.querySelector('#C'+index).disabled = true;
				}
				if (inptIdValue > max) {
		 			alert('임대 가능수량을 초과했습니다');
		 		}
		}

		if (knotDate != 'noKnotDate') {
			synchKnotDate(knotDate, index);
		}
		calculateTotal();
	}

	// checkbox event
	function selectEquip(event) {
		var index = event.target.id.slice(-1);
		var knotDate = document.querySelector('#'+event.target.id).parentNode.parentNode.parentNode.dataset.knotDate;
		
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
		
		} else if (event.target.id.slice(0, -1) == 'tblChk') {			// 탁자
			
			if (event.target.checked) {
				if (!document.querySelector('#chrChk'+index).checked) {
					document.querySelector('#A'+index).disabled = false;
					document.querySelector('#B'+index).disabled = false;
					document.querySelector('#C'+index).disabled = false;
					document.querySelector('#A'+index).checked = true;
				}
			} else {
				if (!document.querySelector('#chrChk'+index).checked) {
					document.querySelector('input[name="btchStyl'+index+'"]:checked').checked = false;
					document.querySelector('#btchStylFileNm'+index).style.backgroundColor = '';
		        	$('#attachBtchStyl'+index).attr('for', '');
					document.querySelector('#A'+index).disabled = true;
					document.querySelector('#B'+index).disabled = true;
					document.querySelector('#C'+index).disabled = true;
				}
				
				document.querySelector('#tblInpt'+index).value = "";		// 탁자 		input
			}
		
		} else if (event.target.id.slice(0, -1) == 'chrChk') {			// 의자
			
			if (event.target.checked) {
				if (!document.querySelector('#tblChk'+index).checked) {
					document.querySelector('#A'+index).disabled = false;
					document.querySelector('#B'+index).disabled = false;
					document.querySelector('#C'+index).disabled = false;
					document.querySelector('#A'+index).checked = true;
				}
			} else {
				if (!document.querySelector('#tblChk'+index).checked) {
					document.querySelector('input[name="btchStyl'+index+'"]:checked').checked = false;
					document.querySelector('#btchStylFileNm'+index).style.backgroundColor = '';
		        	$('#attachBtchStyl'+index).attr('for', '');
					document.querySelector('#A'+index).disabled = true;
					document.querySelector('#B'+index).disabled = true;
					document.querySelector('#C'+index).disabled = true;
				}
				
				document.querySelector('#chrInpt'+index).value = "";		// 의자 		input
			}
		
		} else {

			if (!event.target.checked) {
				document.querySelector('#' + event.target.id.slice(0, -4)+'Inpt'+index).value = "";
			}
			
		}

		if (knotDate != 'noKnotDate') {
			synchKnotDate(knotDate, index);
		}
		calculateTotal();
	}
	
	function synchKnotDate(knotDate, index) {
		var synchTable = document.querySelector('tbody[data-date="'+knotDate+'"]');
		var synchTableChk = synchTable.querySelectorAll('input[type="checkbox"]');
		var synchTableInpt = synchTable.querySelectorAll('input[type="text"]');
		var synchTableRadio = synchTable.querySelectorAll('input[type="radio"]');
		
		synchTableChk[0].checked = document.querySelector('#wlsMicChk'+index).checked;		// 무선마이크
		synchTableInpt[0].value = document.querySelector('#wlsMicInpt'+index).value;		// 무선마이크
		synchTableChk[1].checked = document.querySelector('#wMicChk'+index).checked;		// 유선마이크
		synchTableInpt[1].value = document.querySelector('#wMicInpt'+index).value;			// 유선마이크
		synchTableChk[2].checked = document.querySelector('#bAmpChk'+index).checked;		// 방송앰프
		synchTableChk[2].disabled = document.querySelector('#bAmpChk'+index).disabled;		// 방송앰프
		synchTableInpt[2].value = document.querySelector('#bAmpInpt'+index).value;			// 방송앰프
		synchTableInpt[2].disabled = document.querySelector('#bAmpInpt'+index).disabled;	// 방송앰프
		synchTableChk[3].checked = document.querySelector('#prjctrChk'+index).checked;		// 프로젝터
		synchTableInpt[3].value = document.querySelector('#prjctrInpt'+index).value;		// 프로젝터
		synchTableChk[4].checked = document.querySelector('#tblChk'+index).checked;			// 탁자
		synchTableInpt[4].value = document.querySelector('#tblInpt'+index).value;			// 탁자
		synchTableChk[5].checked = document.querySelector('#chrChk'+index).checked;			// 의자
		synchTableInpt[5].value = document.querySelector('#chrInpt'+index).value;			// 의자
		synchTableChk[6].checked = document.querySelector('#grbgPckChk'+index).checked;		// 쓰레기봉투
		synchTableInpt[6].value = document.querySelector('#grbgPckInpt'+index).value;		// 쓰레기봉투
		
		synchTableRadio[4].checked = document.querySelector('#A'+index).checked;			// 배치형태
		synchTableRadio[4].disabled = document.querySelector('#A'+index).disabled;			// 배치형태
		synchTableRadio[5].checked = document.querySelector('#B'+index).checked;			// 배치형태
		synchTableRadio[5].disabled = document.querySelector('#B'+index).disabled;			// 배치형태
		synchTableRadio[6].checked = document.querySelector('#C'+index).checked;			// 배치형태
		synchTableRadio[6].disabled = document.querySelector('#C'+index).disabled;			// 배치형태
		synchTableInpt[7].value = document.querySelector('#btchStylFileNm'+index).value;									// 배치형태 첨부파일 input
		synchTableInpt[7].style.backgroundColor = document.querySelector('#btchStylFileNm'+index).style.backgroundColor;	// 배치형태 첨부파일 input
		synchTable.querySelector('input[type="file"]').files = document.querySelector('#btchStylFile'+index).files;			// 배치형태 첨부파일
		$('tbody[data-date="'+knotDate+'"] label').eq(7).attr('for', $('#attachBtchStyl'+index).attr('for'));				// 배치형태 첨부파일 label
	}
	
	function onclickRadio(target) {
		var knotDate = document.querySelector('#'+target.id).parentNode.parentNode.parentNode.dataset.knotDate;
		
		if (knotDate == 'noKnotDate') {
			calculateTotal();
			return;
		}
		
		var synchTable = document.querySelector('tbody[data-date="'+knotDate+'"]');
		var synchTableRadio = synchTable.querySelectorAll('input[type="radio"]');
		var index = target.id.slice(-1);
		
		synchTableRadio[0].checked = document.querySelector('#iscorrdr_Y'+index).checked;	// 전시실 복도
		synchTableRadio[1].checked = document.querySelector('#iscorrdr_N'+index).checked;	// 전시실 복도
		synchTableRadio[2].checked = document.querySelector('#isTemp_Y'+index).checked;		// 냉난방 포함
		synchTableRadio[3].checked = document.querySelector('#isTemp_N'+index).checked;		// 냉난방 포함
		
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
	
	
	
	// 첨부 버튼 활성화 & 비활성화
	function onclickBtchStyl(target) {
		var knotDate = document.querySelector('#'+target.id).parentNode.parentNode.parentNode.dataset.knotDate;
		var id = target.id;
		var index = id.slice(-1);
		
		if (id.slice(0, -1) == "C") {
        	$('#attachBtchStyl'+id.slice(-1)).attr('for', 'btchStylFile'+id.slice(-1));
			document.querySelector('#btchStylFileNm'+id.slice(-1)).style.backgroundColor = 'field';
			if (knotDate != 'noKnotDate') {
				synchKnotDate(knotDate, index);
			}
        } else {
        	$('#attachBtchStyl'+id.slice(-1)).attr('for', '');
			document.querySelector('#btchStylFileNm'+id.slice(-1)).style.backgroundColor = '';
			if (knotDate != 'noKnotDate') {
				synchKnotDate(knotDate, index);
			}
        }
	}
	
	// 첨부 onchange event
	function onchangeFile(e) {
		var knotDate = document.querySelector('#'+e.target.id).parentNode.parentNode.parentNode.dataset.knotDate;
		var id = e.target.id;
		var index = id.slice(-1);
		
		if (!(e.target.files.length > 0)) {
			if (id == "comRgstNoFile") {
				$('#'+id+'Nm').val('');
			} else {
				$('#'+id.slice(0, -1)+'Nm'+id.slice(-1)).val('');
				if (knotDate != 'noKnotDate') {
					synchKnotDate(knotDate, index);
				}
			}
			return;
		}

		var file = e.target.files[0];
		var maxSize = 100 * 1024 * 1024;
		var fileExt = '.' + file.type.split('/')[1];
		
		if (file.size > maxSize) {
			e.target.value = '';
			if (knotDate != 'noKnotDate') {
				synchKnotDate(knotDate, index);
			}
			alert("첨부 가능 최대용량은 100MB입니다.");
			return; 
		}
		
		if (id == "comRgstNoFile") {
			$('#'+id+'Nm').val(file.name);
		} else {
			$('#'+id.slice(0, -1)+'Nm'+id.slice(-1)).val(file.name);
			if (knotDate != 'noKnotDate') {
				synchKnotDate(knotDate, index);
			}
		}
	}
	
	// 예약 신청
	function applReserv() {
		
		if((!document.querySelector("#agreeY_1").checked && !document.querySelector("#agreeN_1").checked)
        	|| (!document.querySelector("#agreeY_2").checked && !document.querySelector("#agreeN_2").checked)){
            alert("동의 여부를 선택하여 주십시오.");
            return;
        } else if(document.querySelector("#agreeN_1").checked || document.querySelector('#agreeN_2').checked){
            alert('서비스를 이용할 수 없습니다.')
            return;
        }
		
		var paramList = document.querySelectorAll('#paramList div');
		var priceSubtotal = document.querySelectorAll('tr[name="priceSubtotal"]');
		var priceTotal = document.querySelector('#priceTotal').getElementsByTagName('p');

		if (priceTotal[0].innerText == '-') {
			alert("견적을 먼저 계산한 후, 예약해주십시오.");
			return;
		}
		for (var i=0; i<paramList.length; i++) {
			if (priceSubtotal[i].getElementsByTagName('p')[0].innerText == '-') {
				alert("견적을 먼저 계산한 후, 예약해주십시오.");
				return;
			}
		}
		
		// 첨부파일
		var rsrvtnDataList = [];
		var rsrvtnInfo = {};
		var formData = new FormData();
		var comRgstNoFile = document.querySelector('#comRgstNoFile').files;

		if(comRgstNoFile != null && comRgstNoFile.length>0){
			formData.append("comRgstNoFile", comRgstNoFile[0]);
		} else if (document.querySelector('#comRgstNoFileNm').value == "") {
			alert("사업자등록번호 이미지 파일을 첨부하십시오.");
			return;
		}

		/* 행사정보 */
		if (document.querySelector('#evntNm').value == null
			|| document.querySelector('#evntNm').value == '') {
			alert("행사명을 입력하십시오.");
			return;
		} else {
			rsrvtnInfo.evntNm = document.querySelector('#evntNm').value;
		}
		if (document.querySelector('#evntDesc').value == null
			|| document.querySelector('#evntDesc').value == '') {
			alert("행사내용을 입력하십시오.");
			return;
		} else {
			rsrvtnInfo.evntDesc = document.querySelector('#evntDesc').value;
		}
		if (document.querySelector('#orgNm').value == null
			|| document.querySelector('#orgNm').value == '') {
			alert("상호/단체명을 입력하십시오.");
			return;
		} else {
			rsrvtnInfo.orgNm = document.querySelector('#orgNm').value;
		}
		if (document.querySelector('#comRgstNo').value == null
			|| document.querySelector('#comRgstNo').value == '') {
			alert("사업자등록번호를 입력하십시오.");
			return;
		} else if (!(/(^[0-9]{3})\-([0-9]{2})\-([0-9]{5})/g).test(document.querySelector('#comRgstNo').value)) {
			alert("올바른 사업자등록번호 형식이 아닙니다.");
			return;
		} else {
			rsrvtnInfo.comRgstNo = document.querySelector('#comRgstNo').value.replaceAll('-', '');
		}
		if (document.querySelector('#rprsntrNm').value == null
			|| document.querySelector('#rprsntrNm').value == '') {
			alert("대표자를 입력하십시오.");
			return;
		} else {
			rsrvtnInfo.rprsntrNm = document.querySelector('#rprsntrNm').value;
		}
		if (document.querySelector('#mngrNm').value == null
			|| document.querySelector('#mngrNm').value == '') {
			alert("담당자를 입력하십시오.");
			return;
		} else {
			rsrvtnInfo.mngrNm = document.querySelector('#mngrNm').value;
		}
		if (document.querySelector('#bizType').value != null
			&& document.querySelector('#bizType').value != '') {
			rsrvtnInfo.bizType = document.querySelector('#bizType').value;
		}
		if (document.querySelector('#bizType2').value != null
			&& document.querySelector('#bizType2').value != '') {
			rsrvtnInfo.bizType2 = document.querySelector('#bizType2').value;
		}
		if (document.querySelector('#telNo').value == null
			|| document.querySelector('#telNo').value == '') {
			alert("휴대전화번호를 입력하십시오.");
			return;
		} else if (!(/(^01.{1})(-)([0-9]{3,4})(-)([0-9]{4})/g).test(document.querySelector('#telNo').value)) {
			alert("올바른 휴대전화번호 형식이 아닙니다.");
			return;
		} else {
			rsrvtnInfo.telNo = document.querySelector('#telNo').value.replaceAll('-', '');
		}
		if (document.querySelector('#faxNo').value != null
			&& document.querySelector('#faxNo').value != '') {
			if (!(/(^02.{0}|^0.{2})(-)([0-9]{3,4})(-)([0-9]{4})/g).test(document.querySelector('#faxNo').value)) {
				alert("올바른 유선전화번호 형식이 아닙니다.");
				return;
			} else {
				rsrvtnInfo.faxNo = document.querySelector('#faxNo').value.replaceAll('-', '');
			}
		}
		if (document.querySelector('#address').value == null
			|| document.querySelector('#address').value == '') {
			alert("주소를 입력하십시오.");
			return;
		} else {
			rsrvtnInfo.address = document.querySelector('#address').value;
		}
		if (document.querySelector('#addressDtl').value == null
			|| document.querySelector('#addressDtl').value == '') {
			alert("상세주소를 입력하십시오.");
			return;
		} else {
			rsrvtnInfo.addressDtl = document.querySelector('#addressDtl').value;
		}
		if (document.querySelector('#email').value == null
			|| document.querySelector('#email').value == '') {
			//alert("이메일을 입력하십시오.");
			//return;
		} else if (!(/^[0-9a-zA-Z]*@[0-9a-zA-Z]([\-\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/).test(document.querySelector('#email').value)) {
			//alert("올바른 이메일 형식이 아닙니다.");
			//return;
		} else {
			rsrvtnInfo.email = document.querySelector('#email').value;
		}
		if (document.querySelector('#etc').value != null
			&& document.querySelector('#etc').value != '') {
			rsrvtnInfo.etc = document.querySelector('#etc').value;
		}
		
		for (var i=0; i<paramList.length; i++) {
			var btchStylFile = document.querySelector('#btchStylFile'+(i+1)).files;
			var infoKeys = Object.keys(rsrvtnInfo);
			
			// SrrsrvtnVO
			/* 전시실 & 행사일정 & 장비 */
			var rsrvtnData = {
				rsrvDtStart : paramList[i].getElementsByTagName('p')[0].innerText.replaceAll("-", ""),
				rsrvDtEnd 	: paramList[i].getElementsByTagName('p')[0].innerText.replaceAll("-", ""),
				evntDt		: paramList[i].getElementsByTagName('p')[1].innerText,
				hallType 	: paramList[i].getElementsByTagName('p')[2].innerText,
				status		: 'H',
				rentPay		: priceSubtotal[i].getElementsByTagName('p')[0].innerText.replaceAll(",", "").replace(" 원", ""),
				mngPay		: priceSubtotal[i].getElementsByTagName('p')[1].innerText.replaceAll(",", "").replace(" 원", ""),
				eqpmntPay	: priceSubtotal[i].getElementsByTagName('p')[2].innerText.replaceAll(",", "").replace(" 원", ""),
				totalPay	: priceSubtotal[i].getElementsByTagName('p')[3].innerText.replaceAll(",", "").replace(" 원", ""),
				userId		: userId,
				inDt		: moment(new Date()).format("YYYYMMDD")
			};
			
			for (var infoKey of infoKeys) {
				rsrvtnData[infoKey] = rsrvtnInfo[infoKey];
			}
			
			if (document.querySelector('#wlsMicChk'+(i+1)).checked) {
				if (document.querySelector('#wlsMicInpt'+(i+1)).value == null
					|| document.querySelector('#wlsMicInpt'+(i+1)).value == '') {
					alert("무선마이크 임대수량을 입력하십시오.");
					return;
				} else {
					rsrvtnData.wlsMic = document.querySelector('#wlsMicInpt'+(i+1)).value;
				}
			}
			if (document.querySelector('#wMicChk'+(i+1)).checked) {
				if (document.querySelector('#wMicInpt'+(i+1)).value == null
					|| document.querySelector('#wMicInpt'+(i+1)).value == '') {
					alert("유선마이크 임대수량을 입력하십시오.");
					return;
				} else {
					rsrvtnData.wMic = document.querySelector('#wMicInpt'+(i+1)).value;
				}
			}
			if (document.querySelector('#bAmpChk'+(i+1)).checked
					&& document.querySelector('#bAmpInpt'+(i+1)).value != null
					&& document.querySelector('#bAmpInpt'+(i+1)).value != '') {
				rsrvtnData.bAmp = document.querySelector('#bAmpInpt'+(i+1)).value;
			}
			if (document.querySelector('#prjctrChk'+(i+1)).checked
					&& document.querySelector('#prjctrInpt'+(i+1)).value != null
					&& document.querySelector('#prjctrInpt'+(i+1)).value != '') {
				rsrvtnData.prjctr = document.querySelector('#prjctrInpt'+(i+1)).value;
			}
			if (document.querySelector('#tblChk'+(i+1)).checked) {
				if (document.querySelector('#tblInpt'+(i+1)).value == null
					|| document.querySelector('#tblInpt'+(i+1)).value == '') {
					alert("탁자 임대수량을 입력하십시오.");
					return;
				} else {
					rsrvtnData.tbl = document.querySelector('#tblInpt'+(i+1)).value;
				}
			}
			if (document.querySelector('#chrChk'+(i+1)).checked) {
				if (document.querySelector('#chrInpt'+(i+1)).value == null
					|| document.querySelector('#chrInpt'+(i+1)).value == '') {
					alert("의자 임대수량을 입력하십시오.");
					return;
				} else {
					rsrvtnData.chr = document.querySelector('#chrInpt'+(i+1)).value;
				}
			}
			if (document.querySelector('#grbgPckChk'+(i+1)).checked) {
				if (document.querySelector('#grbgPckInpt'+(i+1)).value == null
					|| document.querySelector('#grbgPckInpt'+(i+1)).value == '') {
					alert("쓰레기봉투 임대수량을 입력하십시오.");
					return;
				} else {
					rsrvtnData.grbgPck = document.querySelector('#grbgPckInpt'+(i+1)).value;
				}
			}
			if (document.querySelector('input[name="btchStyl'+(i+1)+'"]:checked') != null) {
				if (document.querySelector('input[name="btchStyl'+(i+1)+'"]:checked').id.slice(0, -1) == 'C') {
					if (!(btchStylFile != null && btchStylFile.length>0)) {
						alert("배치 형태를 고객선택형으로 선택하실 경우, 배치도를 첨부하셔야 합니다.(jpg,png,gif,bmp,svg,pdf)");
						return;
					}
					formData.append("btchStylFile"+(i+1), btchStylFile[0]);
				}
				rsrvtnData.btchStyl = document.querySelector('input[name="btchStyl'+(i+1)+'"]:checked').id.slice(0, -1);
			}
			if (document.querySelector('input[name="iscorrdr'+(i+1)+'"]:checked') == null) {
				alert("전시실 복도 사용 여부를 선택해야 합니다.");
				return;
			}
			if (document.querySelector('input[name="isTemp'+(i+1)+'"]:checked') == null) {
				alert("냉난방 포함 여부를 선택해야 합니다.");
				return;
			}
	
			rsrvtnData.iscorrdr = document.querySelector('#iscorrdr_Y'+(i+1)).checked ? 'Y' : 'N';
			rsrvtnData.isTemp = document.querySelector('#isTemp_Y'+(i+1)).checked ? 'Y' : 'N';
			
			rsrvtnDataList.push(rsrvtnData);
		}
		
		formData.append("rsrvtnDataList", new Blob([JSON.stringify(rsrvtnDataList)], { type: "application/json" }));
		formData.append("act", "move");
		$.ajax({
			url: '${pageContext.request.contextPath}/front/multiRsrvtn.json',
			processData: false,
			contentType: false,
			data: formData,
			type: 'POST',
			success: function(result){
				if (result.result.code == 'S') {
					alert("예약이 완료되었습니다.\n세부내역 및 예약 내역 출력은 마이페이지에서 가능합니다.");
					location.href = "${pageContext.request.contextPath}/front/mypage.do";
				} else if (result.result.code == 'E') {
					alert(result.result.msg);
				}
			},
			error: function(a,b,c){
				console.log(a,b,c);
			}
		});
		
	}
	
	function empty(data){
		return typeof data == 'undefined' ? '' : data === null ? '' : data;
	}
	
</script>
<!-- 스크립트(E) -->