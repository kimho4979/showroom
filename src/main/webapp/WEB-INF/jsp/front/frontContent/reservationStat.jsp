<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="dim-layer pop_type_01" id="reservationStat">
	<div class="dimBg"></div>
	<div class="pop-layer popbox" tabindex="1">
		<div class="pop-container">
			<div class="pop_info" style="background:#ffffff;">
				<a href="#!" class="btn-layerClose pop_btn_close close_B" title="온라인 견적산출 팝업 닫기 버튼"></a>
				<div style="padding: 75px 0px 20px 0px;">
					<div style="padding: 0 25px; max-height: 800px; overflow: auto;">
					
						<div class="title_box">
							<div class="fl">
								<h4 class="sub_tit_04">예약현황</h4>
							</div>
						</div>
						<div class="table_type_01 mt10 overflow_a">
							<table>
								<caption>전시실 예약 시 옵션을 선택하는 테이블입니다.</caption>
								<colgroup>
									<col style="width: 16%;">
									<col style="width: 16%;">
									<col style="width: 16%;">
									<col style="width: 33%;">
									<col style="width: 16%;">
									<col style="width: 16%;">
									<col style="width: 16%;">
								</colgroup>
								<thead>
									<tr>
										<th>신청일자</th>
										<th>상태</th>
										<th>전시실</th>
										<th>예약일자</th>
										<th>예약시간</th>
										<th>예약금액</th>
										<th>출력</th>
									</tr>
								</thead>
								<tbody id='rsrvtnStatBindList'>
									<!-- <tr> -->
										<!-- <td class="tc"><input type="checkbox" style="display: inline;" id="iscorrdr"></td> -->	
									<!-- </tr> -->
								</tbody>
							</table>
						</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    
</script>