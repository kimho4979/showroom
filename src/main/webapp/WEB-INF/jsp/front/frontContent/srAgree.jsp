<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="/WEB-INF/jsp/front/frontContent/reservPricePopup.jsp"/>

				<c:if test="${(atLoginVO ne null or snsLoginVO ne null)}">
					<!-- 전체 동의(S) -->
					<div class="mt40 pd_lr_15">
						<table style="width: 100%; border-collapse: collapse; font-size: 14.5px; line-height: 18px;">
			                <colgroup >
			                    <col width="25%">
			                    <col width="25%">
			                    <col width="25%">
			                    <col width="25%">
			                </colgroup>
			                <tr>
			                    <th style="border: 1px solid #ccc; padding: 10px; color: #000000; background-color: #EEEEEE;">전체약관 동의 합니다.</th>
			                    <td style="border: 1px solid #ccc; padding: 10px; color: #303030; border-right: hidden;" colspan="2";>
				                	<label for="agreeY_all" style="display: inline-block; font-size: 14.5px; color: #303030;">동의함</label>
				                	<input type="radio" style="margin-right: 10px; margin-left: 4px; display: inline; position: relative; accent-color: #267FED;" onclick="onclickAgreeAll(this);" name="agree_all" id="agreeY_all" value="Y">
				                	<label for="agreeN_all" style="display: inline-block; font-size: 14.5px; color: #303030;">동의하지 않음</label>
				                	<input type="radio" style="margin-right: 10px; margin-left: 4px; display: inline; position: relative; accent-color: #267FED;" onclick="onclickAgreeAll(this);" name="agree_all" id="agreeN_all" value="N">
			                    </td>
			                    <td style="border: 1px solid #ccc; padding: 10px; color: #303030;text-align:right;">※ 예약신청을 위해 약관에 동의 해주세요</td>
			                </tr>
			            </table>
					</div>
					<!-- 전체 동의(E) -->
					
					
					
					<div class="mt10 pd_lr_15" style="width: 100%; vertical-align: middle; border-collapse: collapse;">
					
						<!-- 전시실 사용 및 운영 지침(S) -->
						<div  class="gree_l" style="display: inline-block;  vertical-align: middle; border-collapse: collapse;">
							<form action="" method="post" style="margin: 5px; margin-left: 0; padding-bottom: 10px; border: 1px solid #ccc;">
								<div style="height: 320px; padding: 20px 10px 0 10px; overflow: auto;">
									<h4>전시실 사용 및 운영 지침</h4>
									<br>
									<div style="font-size: 14.5px; line-height: 18px;">
										<p style="color: #7D7D7D; font-weight: bold;"></p>
										<br>
										<p style="color: #7D7D7D; font-weight: bold;">제1조 (목적)</p>
										<p style="color: #303030;">◦ 이 지침은 화훼산업발전과 화훼사업센터 활성화 등을 위하여 운영하는 전시실로 한국농수산식품유통공사 화훼사업센터가 관리하는 전시실 및 설비의 사용과 운영에 관한 사항을 규정함으로써 전시실의 투명하고 효율적으로 운영하는 것을 목적으로 한다.<br></p>
										<br>
										<p style="color: #7D7D7D; font-weight: bold;">제2조 (용어의 정의)</p>
										<p style="color: #303030;">
											◦ 본 지침에 사용되는 용어의 정의는 다음과 같다.<br>
											① “전시실”이란 사용자가 개최하는 각종 회의, 세미나, 심포지엄, 이벤트 및 공연, 연회, 시험 등 (이하 “행사”라 칭한다)으로 사용하기 위해 구획된 다음의 시설을 말한다.(개정 `24.07.15.)<br>
 1. 2층 전시실(A: 무궁화홀(B+C), B: 국화홀, C: 장미홀)<br>
 2. 2층 전시실 복도
<br>
② “사용자”란 행사를 조직하고 운영하는 전시실 임차인을 말한다.<br>
③ “사용료”란 전시실 임차인이 납부하는 임대료를 말한다.<br>
④ “관리비”란 행사를 조직, 운영하는 임차인이 냉˙난방비, 전기료, 청소비, 상하수도료 등 실제 행사운영을    위하여 사용하는 모든 경비를 말한다. 다만, 냉·난방 미가동 시에는 관리비에서 냉·난방비를 공제한다.
<br>


										</p>
										
										<br>
										<p style="color: #7D7D7D; font-weight: bold;">제3조 (사용조건 및 책임한계)</p>
										<p style="color: #303030;">
											① 전시실을 사용하여 행하는 행사의 운영 및 관리는 모두 사용자의 책임으로 한다.<br>
 ② 사용자는 본 지침서 및「화훼사업센터시설운영관리요령 등의 모든 규정을 준수하여야 한다.<br>
 ③ 사용자가 고의 또는 과실로 화재, 도난, 파손, 그 밖의 사고를 발생케 하여 화훼사업센터(이하“센터”라 한다)   또는 타인에게 손해를 입혔을 때에는 그 사용자가 모든 배상책임을 진다.<br>
										</p>
										<br>
										<p style="color: #7D7D7D; font-weight: bold;">제4조 (사용시간)</p>
										<p style="color: #303030;">① 전시실의 사용시간은 09:00∼17:00 로 한다.<br>
1. 오전: 09:00∼12:00(3시간 이하는 반일)<br>
2. 오후: 13:00∼17:00(4시간 이하는 반일)<br>
3. 전일: 09:00∼17:00(4시간 초과∼8시간 이하는 전일)<br></p>
<p style="color: #303030;">② 각 호의 초과 사용시간이나, 행사준비를 위한 09:00시 이전이나 17:00시 이후에 사용할 때에는 시간당 계   산하여 임대료 및 관리비를 납부하여야 한다.</p>

										<%-- <table style="width: 100%; border-collapse: collapse; font-size: 14.5px; line-height: 18px;">
											<caption>회의실 안내표</caption>
							                <colgroup >
							                    <col width="20%">
							                    <col width="20%">
							                    <col width="20%">
							                    <col width="20%">
							                    <col width="20%">
							                </colgroup>
							                <tr>
							                    <th style="border: 1px solid #ccc; padding: 10px; color: #000000; background-color: #EEEEEE;">구분</th>
							                    <td style="border: 1px solid #ccc; padding: 10px; color: #303030;">오전</td>
							                    <td style="border: 1px solid #ccc; padding: 10px; color: #303030;">오후</td>
							                    <td style="border: 1px solid #ccc; padding: 10px; color: #303030;">1일</td>
							                    <td style="border: 1px solid #ccc; padding: 10px; color: #303030;">야간</td>
							                </tr>
							                <tr>
							                    <th style="border: 1px solid #ccc; padding: 10px; color: #000000; background-color: #EEEEEE;">사용시간</th>
							                    <td style="border: 1px solid #ccc; padding: 10px; color: #303030;">09:00~12:00</td>
							                    <td style="border: 1px solid #ccc; padding: 10px; color: #303030;">13:00~17:00</td>
							                    <td style="border: 1px solid #ccc; padding: 10px; color: #303030;">09:00~17:00</td>
							                    <td style="border: 1px solid #ccc; padding: 10px; color: #303030;">18:30~21:00</td>
							                </tr>
							            </table> --%>
										
										<br>
										<p style="color: #7D7D7D; font-weight: bold;">제5조 (사용신청)</p>
										<p style="color: #303030;"> ① 센터 전시실을 사용코자 하는 자는 센터 홈페이지 전시실 예약시스템에 접속하여 신청한다. (개정 `21.07.29, `24.07.15.)
 <br>② 전시회 개최를 위해 전시실을 임대할 때에는 사전에 시설물 중 중량물이나 현장설치 시설물 설치도면 및   개요를 제출하여야 한다.
 <br>③ 실제 회의, 행사 시간 외 별도 사전 준비시간이 필요한 경우 신청서에 이를 포함하여 신청하고 이에 대한   비용을 부담하여야 한다. 
 <br>④ 농림축산식품부 등 정부기관, 유통공사, 화훼산업 관련 단체 등에 우선적으로 신청을 받을 수 있다.</p>
										<br>
										<p style="color: #7D7D7D; font-weight: bold;">제6조 (사용신청 제한)</p>
										<p style="color: #303030;">
											센터장은 다음 각 호의 어느 하나에 해당하는 행사에 대해서 전시실 사용 신청을 제한 또는 거부할 수 있다.

<br>1. 법령 또는 사회적 통념을 위반하는 내용의 행사
<br>2. 호화, 사치, 퇴폐풍조를 유발하여 사회적 문제발생의 우려가 있는 행사
<br>3. 센터 시설 및 설비를 훼손할 우려가 있는 행사(무거운 물건 및 시설물 설치 등)
<br>4. 물품을 판매하는 목적으로 하거나 그 내용이 판매행위로 판단되는 행사 단, 센터장이 사전 승인한 품목은   제외한다.
<br>5. 노동, 사회, 종교, 정치 관련 회의 및 행사 등 그 밖에 센터 전시실 운영 목적에 부적합하다고 판단되는    행사

										</p>
										<br>
										<p style="color: #7D7D7D; font-weight: bold;">제7조 (사용승인 및 예약금 납입)</p>
										<p style="color: #303030;">
① 센터장은 행사의 성격 및 신청내용을 심사하여 전시실 사용 가능여부를 결정한 후 그 결과를 신청인에게 문자 또는 메일을 통해 통보한다.(개정 `21.07.29, `24.07.15.)
<br>② 센터장은 필요하다고 판단되는 경우 사용자에게 전시실 사용승인일 3일 이내에 전시실 임대료(부가가치세   포함)의 20%에 해당하는 예약금을 징수할 수 있다.
<br>③ 예약금을 징수하는 경우, 사용자가 제2항의 기한 내에 예약금을 납입하지 않을 경우에는 전시실 사용승인은   자동적으로 취소된다.
<br>④ 전시실 사용 신청 후 임대료 및 관리비는 사용일 7일 이전에 전액 납부하여야 한다. 단, 사용일 이전 7일 이내에 신청한 경우는 별도로 정한다.(개정 `21.07.29, `24.07.15.)

										</p>
										<br>
										<p style="color: #7D7D7D; font-weight: bold;">제8조 (사용승인 취소)</p>
										<p style="color: #303030;">
① 센터장은 사용승인 후 사용일 전일까지 다음 각 호의 어느 하나에 해당하는 사항이 발생하였을 때 전시실 사용승인을 취소할 수 있다.(개정 `24.07.15.)
<br>1. 사용신청서에 기재된 내용이 허위로 판명된 경우
<br>2. 제6조의 각 호에 해당함이 판명된 경우
<br>3. 전시행사로 시설물 반입 또는 설치로 인하여 센터 시설물이 현저히 훼손될 수 있다고 판단되는 경우
<br>4. 이 지침에 따른 기일 내에 임대료 및 관리비를 납부하지 않은 경우
<br>5. 사용자가 일방적으로 사용승인을 취소하는 경우
<br>6. 공사 정책목적으로 긴급히 전시실 사용이 필요한 경우(10일 이전 통보)
<br>② 제1항제6호를 제외한 각 호의 사유로 사용승인이 취소된 경우 사용자의 납입예약금은 센터에 귀속되며    사용자는 귀책사유가 없다는 사유로 반환청구를 할 수 없다. 다만, 관리비는 반환하며, 제1항제6호의 사유로   승인 취소된 경우 납입한 금액은 반환한다.(개정 `24.07.15.)

										</p>
										<br>
										<p style="color: #7D7D7D; font-weight: bold;">제9조 (계약)</p>
										<p style="color: #303030;">사용자는 전시실을 사용하기 7일 전까지 전시실 임대계약을 체결하여야 한다.
 다만, 전시실 사용계약서는 사용자가 센터홈페이지 전시실 예약시스템을 통해 예약한 전시실 사용신청에 근거하여 센터에서 사용자에게 사용승인 통보를 한 후 사용자가 사용일 7일 전까지 임대료 및 관리비를 완납 시 「화훼사업센터 전시실운영관리지침」을 준수하는 조건으로 계약이 체결된 것으로 본다.(개정 `24.07.15.)
<br></p>
										<br>
										<p style="color: #7D7D7D; font-weight: bold;">제10조 (임대료 및 관리비)</p>
										<p style="color: #303030;">
											① 임대료 및 관리비는 센터 전시실 임대료 <a  href="#" target="_self" onclick="event.preventDefault();layer_popup('#priceLayer');">별표1(클릭)</a>과 같다.
 <br>② 사용자는 계약체결 시 임대료 및 관리비(부가가치세 포함) 전액을 센터에서 지정한 예금계좌로 송금하여   야 한다. 다만, 센터장은 사용자가 후불납입을 요청하고 임대료 및 관리비 징수에 이상이 없다고 판단되는    경우 후불로 할 수 있다.
 <br>③ 제7조제2항의 예약금은 계약체결 시 임대료의 일부로 대체된다.
 <br>④ 화훼산업발전과 화훼소비촉진, 화훼사업센터 활성화를 위하여 센터장은 다음 전시실 사용자에게 전시실    임대료 또는 관리비(냉난방비, 집기사용료 등)를 면제할 수 있다.(개정 `24.07.15.)
 <br>1. 임대료 면제기준
 <br>- 화훼사업센터 내의 화훼유통 관련 단체(연합회ㆍ협회 등)
 <br>- 그 밖에 비영리목적으로 사용하는 조건으로 센터장이 필요하다고 인정한 경우 
 <br>다만, 위탁사업비의 보조사업(정산사업)으로 운영하는 행사는 제외
 <br>2. 임․관리비 면제기준
 <br>- 화훼사업센터 부류별 중도매인 단체 총회 시
 <br>- 화훼사업센터 입주사 4개 단체 총회 시
 <br>- 그 밖에 비영리목적으로 사용하는 조건으로 센터장이 필요하다고 인정한 경우 
 <br>다만, 위탁사업비의 보조사업(정산사업)으로 운영하는 행사는 제외

										</p>
										<br>
										<p style="color: #7D7D7D; font-weight: bold;">제11조 (사용일정 및 장소 변경)</p>
										<p style="color: #303030;">
											① 계약체결 후 사용자의 사정에 따라 사용일정 또는 장소를 변경하고자 할 경우 사전에 센터장의 사전 승인을 받아야 한다.
<br> ② 사용자는 사용일정 및 장소를 변경할 경우 센터와 재계약 관련사항을 협의하여야 한다.
										</p>
										<br>
										<p style="color: #7D7D7D; font-weight: bold;">제12조 (행사내용 임의변경 금지)</p>
										<p style="color: #303030;">① 사용자는 계약체결 후 임의로 행사내용을 변경하여 진행할 수 없다.
 다만, 사용자의 특별한 사정에 따른 내용변경이 불가피한 경우에는 사전에 센터장의 승인을 얻어야 한다.
 <br>② 센터의 승인 없이 임의로 행사내용을 변경하여 진행하는 경우 전시실 임대계약은 즉시 해지되며 사용자는   회의, 행사 등의 전시실 사용을 중지하고 퇴거하여야 한다.
 <br>③ 사용자가 제2항의 조치에 불응하여 퇴거하지 않을 경우 센터는 전력공급차단 및 전시실 폐쇄조치를 취할    수 있다.
<br></p>
										<br>
										<p style="color: #7D7D7D; font-weight: bold;">제13조(양도 및 전대금지)</p>
										<p style="color: #303030;">① 사용자는 전시실 사용권의 일부 또는 전부를 다른 사람에게 양도하거나 전대(轉貸) 할 수 없다.(개정 `24.07.15.)
<br> ② 전시실 사용권의 일부 또는 전부를 양도하거나 전대한 사실이 있는 업체에게는 차후 전시실 사용신청을 거부할 수 있다.(개정 `24.07.15.)
<br></p>
										<br>
										<p style="color: #7D7D7D; font-weight: bold;">제14조 (계약해지)</p>
										<p style="color: #303030;">
											① 센터는 계약체결 후 다음 각 호의 어느 하나에 해당하는 사유가 발생할 경우 계약해지 의사를 통보하고 통보 후 상당기간 동안 동사유가 제거되지 않을 경우에는 계약을 해지할 수 있다. 다만, 제2호, 제5호의 경우에는 즉시 해지할 수 있다.
 <br>1. 사용자가 사용일정과 장소 또는 행사내용을 임의로 변경하는 경우
 <br>2. 사용자가 제6조의 신청제한을 위반한 계약, 법령위반, 반사회질서 적 행사, 시설파손 우려가 있는 행사 등   전시실 운영목적에 부합하지 않는 행사를 진행하는 경우
 <br>3. 사용자가 이 지침을 중대하게 위반하는 경우
 <br>4. 사용자가 제13조의 양도 및 전대 금지를 위반한 경우
 <br>5. 사용자가 전시실 사용을 취소 또는 취소할 의사를 표시한 경우
 <br>② 제1항 각 호의 어느 하나에 의해 계약이 해지되는 경우 화훼사업센터는 사용자로부터 납입 받은 예약금 및 임대료 전액을 위약금으로 받는다.(개정 `24.07.15.)
 <br>③ 사용자가 전시실 사용 중 임대계약이 해지된 경우, 사용자는 즉시 전시실 사용을 중지하여야하고 모든 시   설에 대한 원상복구의 의무를 다하여야 한다.(개정 `24.07.15.)
 <br>④ 제2항의 위약금 징구는 사용자에 의한 시설파손 등의 손해배상 청구에는 영향을 미치지 않는다.
 <br>⑤ 제8조 제1항 각호에 해당되는 경우(개정 `24.07.15.)<br>
										</p>
										<%-- <p class="mt20" style="color: #303030;">&lt;계약 해지 &gt;</p> --%>
										<%-- <table style="width: 100%; border-collapse: collapse; font-size: 14.5px; line-height: 18px;">
											<caption>회의실 계약 해지 관련 안내표</caption>
							                <colgroup >
							                    <col width="60%">
							                    <col width="40%">
							                </colgroup>
							                <tr style="background-color: #EEEEEE;">
							                    <th style="border: 1px solid #ccc; padding: 10px; color: #000000;">계약해지 시기</th>
							                    <th style="border: 1px solid #ccc; padding: 10px; color: #000000;">위약금 비율</th>
							                </tr>
							                <tr>
							                    <td style="border: 1px solid #ccc; padding: 10px; color: #303030;">계약일 ~ 임대 개시일 91일 전까지</td>
							                    <td style="border: 1px solid #ccc; padding: 10px; color: #303030;">임대료의 20%</td>
							                </tr>
							                <tr>
							                    <td style="border: 1px solid #ccc; padding: 10px; color: #303030;">임대 개시일 8일 전 ~ 90일 전까지</td>
							                    <td style="border: 1px solid #ccc; padding: 10px; color: #303030;">임대료의 50%</td>
							                </tr>
							                <tr>
							                    <td style="border: 1px solid #ccc; padding: 10px; color: #303030;">임대 개시일 7일 전 ~ 임대 개시일 전일까지</td>
							                    <td style="border: 1px solid #ccc; padding: 10px; color: #303030;">임대료의 100%</td>
							                </tr>
							            </table> --%>
										<%-- <p class="mt20" style="color: #303030;">&lt;일부 취소(축소)&gt;</p> --%>
										<%-- <table style="width: 100%; border-collapse: collapse; font-size: 14.5px; line-height: 18px;">
											<caption>일부 취소(축소) 관련 안내표</caption>
							                <colgroup >
							                    <col width="45%">
							                    <col width="55%">
							                </colgroup>
							                <tr style="background-color: #EEEEEE;">
							                    <th style="border: 1px solid #ccc; padding: 10px; color: #000000;">일부취소 시기</th>
							                    <th style="border: 1px solid #ccc; padding: 10px; color: #000000;">위약금</th>
							                </tr>
							                <tr>
							                    <td style="border: 1px solid #ccc; padding: 10px; color: #303030;">계약일 ~ 임대 개시일 91일 전까지</td>
							                    <td style="border: 1px solid #ccc; padding: 10px; color: #303030;">(임대료 총액 – 일부 취소 후 임대료 총액) × 20%</td>
							                </tr>
							                <tr>
							                    <td style="border: 1px solid #ccc; padding: 10px; color: #303030;">임대 개시일 8일 전 ~ 90일 전까지</td>
							                    <td style="border: 1px solid #ccc; padding: 10px; color: #303030;">(임대료 총액 – 일부 취소 후 임대료 총액) × 50%</td>
							                </tr>
							                <tr>
							                    <td style="border: 1px solid #ccc; padding: 10px; color: #303030;">임대 개시일 7일 전 ~ 임대 개시일 전일까지</td>
							                    <td style="border: 1px solid #ccc; padding: 10px; color: #303030;">(임대료 총액 – 일부 취소 후 임대료 총액) × 100%</td>
							                </tr>
							            </table> --%>
										<%-- <p style="color: #303030;">
											(개정 2020.8.05., 2021.8.20., 2023.01.15)<br>
											③ 제2항의 위약금 징구는 사용자에 의한 시설파손 등의 손해배상 청구에는 영향을 미치지 않는다.<br>
											④ 사용자가 회의실 사용 중 계약이 해지된 경우, 사용자는 즉각 회의실 사용을 중지하여야 하고 제20조에 의거하여 원상복구 의무를 다하여야 한다.<br>
											⑤ 본 조에도 불구하고 센터의 일방적 사정에 따라 계약이 해지되는 경우 납입한 예약금 및 임대료 전액을 사용자에게 환불한다.<br>
										</p> --%>
										<br>
										<p style="color: #7D7D7D; font-weight: bold;">제15조 (불가항력)</p>
										<p style="color: #303030;">천재지변, 재앙, 전쟁, 국가시책변경 등 불가항력적 원인에 의해 센터 또는 사용자에게 손해가 발생하였을 때에는 센터 또는 사용자는 그 손해에 대하여 책임지지 아니한다.<br></p>
										
										<%-- <p style="color: #7D7D7D; font-weight: bold;">제2장 회의실 운영</p> --%>
										<br>
										<p style="color: #7D7D7D; font-weight: bold;">제16조 (홍보 및 안내 시설물의 설치)
										<p style="color: #303030;">
											 ① 행사 홍보 및 안내를 위한 현수막, 포스터, 안내입간판 등을 부착 또는 설치하고자 할 경우 사용자는 사전 협의 후 센터에서 지정하는 장소에 부착 또는 설치하여야 한다.
 <br>② 제1항에 명시된 장소 이외에 부착 또는 설치된 구조물, 포스터 및 안내입간판 등은 센터에서 지적하는    즉시 사용자는 철거하여야 하며 이에 불응 시 센터에서 강제철거 할 수 있으며, 이로 인해 발생되는 철거비   용 및 시설물 손상 손해에 대해서는 사용자가 전적으로 책임진다.
 <br>③ 행사관련 모든 홍보 및 안내물은 전시실 사용 종료 시까지 철거되어야 한다.
<br>
										</p>
										<br>
										<p style="color: #7D7D7D; font-weight: bold;">제17조 (전화 및 LAN시설)</p>
										<p style="color: #303030;">전시실 내 행사 진행용 전화 또는 LAN시설 등은 센터에서 지정하는 통신업체에서 설치, 관리하고 그 비용은 사용자 부담으로 한다.
<br></p>
										<br>
										<p style="color: #7D7D7D; font-weight: bold;">제18조 (장비대여 및 사용료 등)</p>
										<p style="color: #303030;">
											① 센터의 시청각장비를 사용하고자 하는 경우, 사용자는 전시실 사용 신청 시 함께 신청하고 장비사용료는 전시실 임대료와 함께 납입하여야한다.(개정 `24.07.15.)
 <br>② 외부의 시청각장비를 사용하고자 하는 경우, 사용자는 센터의 사전 승인을 받은 후 센터의 지시에 따     라 설치 및 철거하여야 한다.
 <br>③ 시청각 장비, 탁자, 의자 등 비품 사용료 및 대여기준은 <a  href="#" target="_self" onclick="event.preventDefault();layer_popup('#priceLayer');">별표1(클릭)</a>과 같다.
 <br>④ 대여한 장비, 비품 등의 사용 중 정상적인 마모 이외의 파손, 변형, 망실에 대해서는 동등 이상의 제품으   로 변상하여야 한다.
<br>
										</p>
										<br>
										<p style="color: #7D7D7D; font-weight: bold;">제19조 (금지사항)</p>
										<p style="color: #303030;">① 사용자는 다음 각 호의 행위를 할 수 없다.
 <br>1. 각종 화기사용(가스, 전열 등), 흡연행위
 <br>2. 센터의 사전 승인 없는 영업, 판매행위
 <br>3. 센터의 사전 승인 없이 음식물을 반입하거나 조리하는 행위
 <br>4. 승인 이외의 시설물 사용행위
 <br>② 사용자가 제1항 각 호의 어느 하나의 행위를 하는 경우 센터는 전력공급 차단 및 전시실 폐쇄조치를    취할 수 있다.
 <br>③ 제2항에 따른 조치를 취한 경우, 사용자는 그로 인한 어떠한 손해배상이나 손실보상을 청구할 수 없으며,   센터에서 입은 손해에 대해서는 별도로 보상하여야 한다.
<br></p>
										<br>
										<p style="color: #7D7D7D; font-weight: bold;">제20조 (원상복구)</p>
										<p style="color: #303030;">
											① 사용자는 전시실 사용시간이 종료되는 즉시 사용한 전시실의 각종 시설과 유·무상으로 제공된 장비, 비품 등을 센터의 상태점검을 받은 후 반납하여야 하며, 사용 중 발생한 정상적인 마모 이외의 파손, 변형, 망실 등은 원래의 상태 또는 동등 이상의 상태로 즉시 복구하여야 한다.
<br> ② 사용자가 센터장이 정하는 기한 내에 원상 복구치 아니하는 경우, 다음 사용자의 정상적인 사용을 위하여   센터에서 원상복구를 대행하기로 한다. 이 경우 사용자는 센터가 원상복구비용 청구 시 즉시 청구금액을 납   부하여야 한다.
<br>
										</p>
										<br>
										<p style="color: #7D7D7D; font-weight: bold;">제21조 (이 지침에 정하지 않은 사항)</p>
										<p style="color: #303030;">
											이 지침에 정하지 않은 사항에 관하여는 「화훼사업센터 시설운영관리요령」이 보충적으로 사용자에게 적용된다.
<br>
										</p>
										<br>
										<p style="color: #7D7D7D; font-weight: bold;">제22조 (부칙)</p>
										<p style="color: #303030;">
										이 지침은 2009년 2월 1일부터 최초 적용한다.
 <br>이 지침은 2018년 2월 20일부터 적용한다.
 <br>이 지침은 2021년 8월 1일부터 적용한다. 
 <br>이 지침은 2024년 9월 1일부터 적용한다. 
										</p>
									</div>
					            </div>
					            <div class="mt20" style="text-align: right;">
				                	<label for="agreeY_1" style="display: inline-block; font-size: 14.5px; color: #303030;">동의함</label>
				                	<input type="radio" style="margin-right: 10px; margin-left: 4px; display: inline; position: relative; accent-color: #267FED;" onclick="onclickAgree();" name="agree_1" id="agreeY_1" value="Y">
				                	<label for="agreeN_1" style="display: inline-block; font-size: 14.5px; color: #303030;">동의하지 않음</label>
				                	<input type="radio" style="margin-right: 10px; margin-left: 4px; display: inline; position: relative; accent-color: #267FED;" onclick="onclickAgree();" name="agree_1" id="agreeN_1" value="N">
					            </div>
							</form>
						</div>
						<!-- 전시실 사용 및 운영 지침(E) -->
						
						<!-- 개인정보 제3자 제공에 대한 안내(S) -->
						<div class="gree_r" style="display: inline-block;  vertical-align: middle; border-collapse: collapse;">
							<form action="" method="post" style="margin: 5px; margin-right: 0; padding-bottom: 10px; border: 1px solid #ccc;">
								<div style="height: 320px; padding: 20px 10px 0 10px; overflow: auto;">
						            <h4>개인정보 제3자 제공에 대한 안내</h4>
						            <table class="mt20" style="width: 100%; border-collapse: collapse; font-size: 14.5px; line-height: 18px;">
						                <colgroup >
						                    <col width="33.3%">
						                    <col width="33.3%">
						                    <col width="33.3%">
						                </colgroup>
						                <tr style="background-color: #EEEEEE;">
						                    <th style="border: 1px solid #ccc; padding: 10px; color: #000000;">수집·이용하려는 개인정보 항목</th>
						                    <th style="border: 1px solid #ccc; padding: 10px; color: #000000;">개인정보의 수집·이용 목적</th>
						                    <th style="border: 1px solid #ccc; padding: 10px; color: #000000;">개인정보 보유·이용기간</th>
						                </tr>
						                <tr>
						                    <td style="border: 1px solid #ccc; padding: 10px; color: #303030;">성명, 휴대폰 번호, 사업자 주소, 회사 전화번호, 이메일주소, 사업자등록번호</td>
						                    <td style="border: 1px solid #ccc; padding: 10px; color: #303030;">전시실 임대관리 및 전자세금계산서 발급</td>
						                    <td style="border: 1px solid #ccc; padding: 10px; color: #303030;">5년</td>
						                </tr>
						                <tr>
						                    <td style="border: 1px solid #ccc; padding: 10px; color: #303030;">동의를 거부할 권리 및 동의를 거부할 경우의 불이익</td>
						                    <td style="border: 1px solid #ccc; padding: 10px; color: #303030;" colspan="2">귀하는 개인정보 수집 동의를 거부하실 수 있으나 필수 정보의 수집·이용 동의거부 시, 해당 사업의 서비스 이용이 제한되는 불이익을 받을 수 있습니다</td>
						                </tr>
						            </table>
						            <p style="font-size: 13px; margin-top: 5px; line-height: 16px; color: #303030;">본인은 한국농수산식품유통공사 화훼사업센터의 개인정보 수집ㆍ이용에 대한 내용을 숙지하였으며, 이에 동의합니다.
					            </div>
					            <div class="mt20" style="text-align: right;">
				                	<label for="agreeY_2" style="display: inline-block; font-size: 14.5px; color: #303030;">동의함</label>
				                	<input type="radio" style="margin-right: 10px; margin-left: 4px; display: inline; position: relative; accent-color: #267FED;" onclick="onclickAgree();" name="agree_2" id="agreeY_2" value="Y">
				                	<label for="agreeN_2" style="display: inline-block; font-size: 14.5px; color: #303030;">동의하지 않음</label>
				                	<input type="radio" style="margin-right: 10px; margin-left: 4px; display: inline; position: relative; accent-color: #267FED;" onclick="onclickAgree();" name="agree_2" id="agreeN_2" value="N">
					            </div>
					        </form>
				        </div>
						<!-- 개인정보 제3자 제공에 대한 안내(E) -->
				        
					</div>
				</c:if>



<!-- 스크립트(S) -->
<script type="text/javascript">

	function onclickAgreeAll(target) {
		if (target.value == 'Y') {
			document.querySelector("#agreeN_1").checked = false;
			document.querySelector("#agreeN_2").checked = false;
			document.querySelector("#agreeY_1").checked = true;
			document.querySelector("#agreeY_2").checked = true;
		} else {
			document.querySelector("#agreeY_1").checked = false;
			document.querySelector("#agreeY_2").checked = false;
			document.querySelector("#agreeN_1").checked = true;
			document.querySelector("#agreeN_2").checked = true;
		}
	}
	
	function onclickAgree() {
		if (document.querySelector("#agreeY_1").checked == false || document.querySelector("#agreeY_2").checked == false) {
			document.querySelector("#agreeY_all").checked = false;
			document.querySelector("#agreeN_all").checked = false;
			
			if (document.querySelector("#agreeN_1").checked == true && document.querySelector("#agreeN_2").checked == true) {
				document.querySelector("#agreeN_all").checked = true;
			}
			
			return;
		} else if (document.querySelector("#agreeY_1").checked == true && document.querySelector("#agreeY_2").checked == true) {
			document.querySelector("#agreeN_all").checked = false;
			document.querySelector("#agreeY_all").checked = true;
		}
	}

	function layer_popup(el) {
		
		var $el = $(el);        //레이어의 id를 $el 변수에 저장
		var isDim = $el.prev().hasClass('dimBg');   //dimmed 레이어를 감지하기 위한 boolean 변수
		
		document.querySelector('body').style.overflow = 'hidden';
		
		isDim ? $('.dim-layer').fadeIn() : $el.fadeIn();

		var $elWidth = $(window).width(),
			$elHeight = $(window).height(),
			docWidth = $(window).width(),
			docHeight = $(window).height();

		var popboxW = $el.find(".pop_info").width()/2;
		var popboxH = $el.find(".pop_info").height()/2;
		
		$el.find(".popbox").css({
			top: "50%",
			left: "50%",
			transform: "translate(-" + popboxW.toString() + "px, -" + popboxH.toString() + "px)"
		});
		
		$(".dim-layer").attr("tabindex", 0).focus();
		
		// 화면의 중앙에 레이어를 띄운다.
		/*if ($elHeight < docHeight || $elWidth < docWidth) {
			$el.css({
			marginTop: -$elHeight /2,
			marginLeft: -$elWidth/2
			})
		} else {
			$el.css({top: 0, left: 0});
		}*/
		$el.find('a.btn-layerClose').click(function(){
			isDim ? $('.dim-layer').fadeOut() : $el.fadeOut(); // 닫기 버튼을 클릭하면 레이어가 닫힌다.
			$('body').css('overflow','auto');
			$('body').css('position','relative');
			return false;
		});

		$('.layer .dimBg').click(function(){
			$('.dim-layer').fadeOut();
			return false;
		});
		
	}


</script>
<!-- 스크립트(E) -->