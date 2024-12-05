<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- 가격표 보기 팝업 (S) -->
<div class="dim-layer pop_type_01" id="priceLayer">
	<div class="dimBg"></div>
	<div class="pop-layer popbox" tabindex="1">
		<div class="pop-container">
			<div class="pop_info" style="background:#ffffff;">
				<a href="#!" class="btn-layerClose pop_btn_close close_B" title="가격표 보기 팝업 닫기 버튼"></a>
				<div style="padding: 75px 0px 20px 0px;">
					<div style="padding: 0 25px; max-height: 800px; overflow: auto;">
					
						<div class="title_box">
							<div class="fl" style="display: inline-block;">
								<h4 class="sub_tit_04">전시실 임대료</h4>
							</div>
							<div class="r_story" style="display: inline-block; float: right;">
								<p class="s_txt">(단위 : 원, VAT 포함)</p>
							</div>
							<div style="clear: both;"></div>
						</div>
						<div class="table_type_01 mt10 overflow_a">
							<table>
								<caption>전시실 사용면적, 사용시간 당 임대료를 알려주는 전시실 임대료 정보 테이블입니다.</caption>
								<colgroup>
									<col style="width: 20%;">
									<col style="width: 20%;">
									<col style="width: 20%;">
									<col style="width: 20%;">
									<col style="width: 20%;">
								</colgroup>
								<thead>
									<tr>
										<th>구 분</th>
										<th>사용면적(㎡)</th>
										<th>09시 ~ 12시</th>
										<th>13시 ~ 17시</th>
										<th>09시 ~ 17시</th>
									</tr>
								</thead>
								<tbody id="prcRfrncBind">
									<tr>
										<td class="tc"><p class="txt_01">무궁화홀(A)</p></td>
										<td class="tc"><p class="txt_01">394</p></td>
										<td class="tc"><p class="txt_01">158,000</p></td>
										<td class="tc"><p class="txt_01">201,000</p></td>
										<td class="tc"><p class="txt_01">307,000</p></td>
									</tr>
									<tr>
										<td class="tc"><p class="txt_01">국화홀(B)</p></td>
										<td class="tc"><p class="txt_01">225</p></td>
										<td class="tc"><p class="txt_01">105,000</p></td>
										<td class="tc"><p class="txt_01">123,000</p></td>
										<td class="tc"><p class="txt_01">198,000</p></td>
									</tr>
									<tr>
										<td class="tc"><p class="txt_01">장미홀(C)</p></td>
										<td class="tc"><p class="txt_01">169</p></td>
										<td class="tc"><p class="txt_01">83,000</p></td>
										<td class="tc"><p class="txt_01">90,000</p></td>
										<td class="tc"><p class="txt_01">129,000</p></td>
									</tr>
									<tr>
										<td class="tc"><p class="txt_01">전시실 복도(D)</p></td>
										<td class="tc"><p class="txt_01">53</p></td>
										<td class="tc"><p class="txt_01">11,534</p></td>
										<td class="tc"><p class="txt_01">15,653</p></td>
										<td class="tc"><p class="txt_01">21,420</p></td>
									</tr>
									<tr>
										<td class="tc"><p class="txt_01">경매장(100㎡ 기준)</p></td>
										<td class="tc"><p class="txt_01">100</p></td>
										<td class="tc"><p class="txt_01">21,999</p></td>
										<td class="tc"><p class="txt_01">29,855</p></td>
										<td class="tc"><p class="txt_01">40,855</p></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="r_story">
							<p class="s_txt">※ 상기 사용시간을 초과하여 회의실을 사용하는 경우 및  09:00 이전, 17:00 이후 행사 등의 사전준비를 위한 사용 시에도 시간당  사</p>
							<p class="s_txt">&emsp;&nbsp;용료(임대료, 관리비)를 별도 납부하여야 함.</p>
							<p class="s_txt">※ 임대료 외 관리비(집기류, 프로젝터, 방송앰프 등) 비품 사용료는 별도임.</p>
						</div>
						<div style="clear: both;"></div>
						
						<div class="title_box mt30">
							<div class="fl" style="display: inline-block;">
								<h4 class="sub_tit_04">전시실 관리비</h4>
							</div>
							<div class="r_story" style="display: inline-block; float: right;">
								<p class="s_txt">(단위 : 원, VAT 포함)</p>
							</div>
							<div style="clear: both;"></div>
						</div>
						<div class="table_type_01 mt10 overflow_a">
							<table>
								<caption>전시실 사용면적, 관리비를 알려주는 전시실 관리비 정보 테이블입니다.</caption>
								<colgroup>
									<col style="width: 18%;">
									<col style="width: 10%;">
									<col style="width: 12%;">
									<col style="width: 12%;">
									<col style="width: 12%;">
									<col style="width: 12%;">
									<col style="width: 12%;">
									<col style="width: 12%;">
								</colgroup>
								<thead>
									<tr>
										<th rowspan="2">구 분</th>
										<th rowspan="2">사용면적<br>(㎡)</th>
										<th colspan="3">냉난방 제외 관리비</th>
										<th colspan="3">냉난방 포함 관리비</th>
									</tr>
									<tr>
										<th>09시 ~ 12시</th>
										<th>13시 ~ 17시</th>
										<th>09시 ~ 17시</th>
										<th>09시 ~ 12시</th>
										<th>13시 ~ 17시</th>
										<th>09시 ~ 17시</th>
									</tr>
								</thead>
								<tbody id="mngRfrncBind">
									<tr>
										<td class="tc"><p class="txt_01">① 무궁화홀</p></td>
										<td class="tc"><p class="txt_01">394</p></td>
										<td class="tc"><p class="txt_01">63,000</p></td>
										<td class="tc"><p class="txt_01">65,000</p></td>
										<td class="tc"><p class="txt_01">128,000</p></td>
										<td class="tc"><p class="txt_01">90,000</p></td>
										<td class="tc"><p class="txt_01">98,000</p></td>
										<td class="tc"><p class="txt_01">188,000</p></td>
									</tr>
									<tr>
										<td class="tc"><p class="txt_01">② 국화홀</p></td>
										<td class="tc"><p class="txt_01">225</p></td>
										<td class="tc"><p class="txt_01">32,000</p></td>
										<td class="tc"><p class="txt_01">33,000</p></td>
										<td class="tc"><p class="txt_01">65,000</p></td>
										<td class="tc"><p class="txt_01">46,000</p></td>
										<td class="tc"><p class="txt_01">51,000</p></td>
										<td class="tc"><p class="txt_01">97,000</p></td>
									</tr>
									<tr>
										<td class="tc"><p class="txt_01">③ 장미홀</p></td>
										<td class="tc"><p class="txt_01">169</p></td>
										<td class="tc"><p class="txt_01">31,000</p></td>
										<td class="tc"><p class="txt_01">32,000</p></td>
										<td class="tc"><p class="txt_01">63,000</p></td>
										<td class="tc"><p class="txt_01">44,000</p></td>
										<td class="tc"><p class="txt_01">48,000</p></td>
										<td class="tc"><p class="txt_01">92,000</p></td>
									</tr>
									<tr>
										<td class="tc"><p class="txt_01">④ 전시실 복도</p></td>
										<td class="tc"><p class="txt_01">53</p></td>
										<td class="tc"><p class="txt_01">14,830</p></td>
										<td class="tc"><p class="txt_01">15,653</p></td>
										<td class="tc"><p class="txt_01">30,483</p></td>
										<td class="tc"><p class="txt_01"></p></td>
										<td class="tc"><p class="txt_01"></p></td>
										<td class="tc"><p class="txt_01"></p></td>
									</tr>
									<tr>
										<td class="tc"><p class="txt_01">경매장(100㎡ 기준)</p></td>
										<td class="tc"><p class="txt_01">100</p></td>
										<td class="tc"><p class="txt_01">28,285</p></td>
										<td class="tc"><p class="txt_01">29,855</p></td>
										<td class="tc"><p class="txt_01">58,140</p></td>
										<td class="tc"><p class="txt_01"></p></td>
										<td class="tc"><p class="txt_01"></p></td>
										<td class="tc"><p class="txt_01"></p></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="r_story">
							<p class="s_txt">※ 냉, 난방 제외 관리비 : 조명 + 기타 전기 + 청소 + 상하수도 + 기타 유지관리비</p>
						</div>
						<div style="clear: both;"></div>
						
						<div class="title_box mt30">
							<div class="fl" style="display: inline-block;">
								<h4 class="sub_tit_04">회의장비 및 집기류 임대비</h4>
							</div>
							<div class="r_story" style="display: inline-block; float: right;">
								<p class="s_txt">(단위 : 원, VAT 포함)</p>
							</div>
							<div style="clear: both;"></div>
						</div>
						<div class="table_type_01 mt10 overflow_a">
							<table>
								<caption>전시실 면적, 사용시간 당 임대료를 알려주는 전시실 임대료 정보 테이블입니다.</caption>
								<colgroup>
									<col style="width: 20%;">
									<col style="width: 20%;">
									<col style="width: 10%;">
									<col style="width: 20%;">
									<col style="width: 30%;">
								</colgroup>
								<thead>
									<tr>
										<th>장 비 명</th>
										<th>규 격</th>
										<th>수 량</th>
										<th>임대단가</th>
										<th>비 고</th>
									</tr>
								</thead>
								<tbody id="eqpmntRfrncBind">
									<tr>
										<td class="tc"><p class="txt_01">무선마이크</p></td>
										<td class="tc"><p class="txt_01"></p></td>
										<td class="tc"><p class="txt_01">2</p></td>
										<td class="tc"><p class="txt_01">20,000</p></td>
										<td class="tc"><p class="txt_01"></p></td>
									</tr>
									<tr>
										<td class="tc"><p class="txt_01">유선마이크</p></td>
										<td class="tc"><p class="txt_01"></p></td>
										<td class="tc"><p class="txt_01">3</p></td>
										<td class="tc"><p class="txt_01">5,000</p></td>
										<td class="tc"><p class="txt_01"></p></td>
									</tr>
									<tr>
										<td class="tc"><p class="txt_01">방송앰프</p></td>
										<td class="tc"><p class="txt_01"></p></td>
										<td class="tc"><p class="txt_01">1</p></td>
										<td class="tc"><p class="txt_01">30,000</p></td>
										<td class="tc"><p class="txt_01">단순 방송 시 임대 필요<br>(프로젝터 임대 시 불필요)</p></td>
									</tr>
									<tr>
										<td class="tc"><p class="txt_01">노트북</p></td>
										<td class="tc"><p class="txt_01"></p></td>
										<td class="tc"><p class="txt_01">1</p></td>
										<td class="tc"><p class="txt_01">30,000</p></td>
										<td class="tc"><p class="txt_01"></p></td>
									</tr>
									<tr>
										<td class="tc"><p class="txt_01">프로젝터(무궁화홀)</p></td>
										<td class="tc"><p class="txt_01">4500Ansi/(반일)</p></td>
										<td class="tc"><p class="txt_01">1</p></td>
										<td class="tc"><p class="txt_01">200,000<br>(120,000)</p></td>
										<td class="tc"><p class="txt_01">방송앰프, PC포함</p></td>
									</tr>
									<tr>
										<td class="tc"><p class="txt_01">프로젝터(국화홀)</p></td>
										<td class="tc"><p class="txt_01">3500Ansi/(반일)</p></td>
										<td class="tc"><p class="txt_01">1</p></td>
										<td class="tc"><p class="txt_01">175,000<br>(105,000)</p></td>
										<td class="tc"><p class="txt_01">방송앰프, PC포함</p></td>
									</tr>
									<tr>
										<td class="tc"><p class="txt_01">프로젝터(장미홀)</p></td>
										<td class="tc"><p class="txt_01">3000Ansi/(반일)</p></td>
										<td class="tc"><p class="txt_01">1</p></td>
										<td class="tc"><p class="txt_01">150,000<br>(90,000)</p></td>
										<td class="tc"><p class="txt_01">방송앰프, PC포함</p></td>
									</tr>
									<tr>
										<td class="tc"><p class="txt_01">탁자</p></td>
										<td class="tc"><p class="txt_01"></p></td>
										<td class="tc"><p class="txt_01"></p></td>
										<td class="tc"><p class="txt_01">700/개</p></td>
										<td class="tc"><p class="txt_01">회의장 배치 제외</p></td>
									</tr>
									<tr>
										<td class="tc"><p class="txt_01">의자</p></td>
										<td class="tc"><p class="txt_01"></p></td>
										<td class="tc"><p class="txt_01"></p></td>
										<td class="tc"><p class="txt_01">350/개</p></td>
										<td class="tc"><p class="txt_01">회의장 배치 제외</p></td>
									</tr>
									<tr>
										<td class="tc"><p class="txt_01">쓰레기봉투</p></td>
										<td class="tc"><p class="txt_01">리터</p></td>
										<td class="tc"><p class="txt_01"></p></td>
										<td class="tc"><p class="txt_01">4,000/매</p></td>
										<td class="tc"><p class="txt_01">봉투 필요 시</p></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="r_story">
							<p class="s_txt">※ 탁자, 의자 대여기준(훼손, 낙서, 오염방지)</p>
							<!-- <p class="s_txt">&emsp;- 전시실 회의, 단순 강의, 교육 : 신품 대여(‘08.01 이후 반입품)</p>
							<p class="s_txt">&emsp;- 전시회, 작업형 강의, 각종시험 : 기존제품 대여(‘07.01 이전 반입품)</p> -->
							<p class="s_txt">※ 주차사항 : 주차비 무료 2대(승용차 기준, 임대시간 기준)</p>
							<p class="s_txt">※ 기타 부분 사용료(임대료, 관리비)는 화훼사업센터장이 별도로 정한다.</p>
						</div>
						<div style="clear: both;"></div>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 가격표 보기 팝업 (E) -->

<!-- 가격표 보기 팝업 스크립트(S) -->
<script type="text/javascript">
	
	$(document).ready(function(){
	
		// 디비 가격기준표의 값으로 바인딩 
		$.ajax({
			type:"get",
			url:"${pageContext.request.contextPath}/front/getcmmnrfrnc.json",
			data: {},
			success: function(data){
				var htmlcode = '';
				var htmlcode2 = '';
				var htmlcode3 = '';
				var area = { A : 394, B : 225, C : 169, D : 53 }; // 사용면적  
				var mngPayLst = [];
				var mngPayLst2 = [];
				var unit = '';
				$.each(data.eqpmntRfrnc,function(i,v){
					if(v.EQPMNT_NM != '프로젝터' && v.HALL_TYPE != '1') return;
					if(v.EQPMNT_NM == '프로젝터' && v.HALL_TYPE == '1') { v.EQPMNT_NM = '프로젝트(무궁화홀)' }
					if(v.EQPMNT_NM == '프로젝터' && v.HALL_TYPE == '2') { v.EQPMNT_NM = '프로젝트(국화홀)' }
					if(v.EQPMNT_NM == '프로젝터' && v.HALL_TYPE == '3') { v.EQPMNT_NM = '프로젝트(장미홀)' }

					if(v.EQPMNT_NM == '쓰레기봉투') { unit = '/매'; v.AMOUNT = ''; }
					if(v.EQPMNT_NM == '탁자') { unit = '/개'; v.AMOUNT = ''; }
					if(v.EQPMNT_NM == '의자') { unit = '/개'; v.AMOUNT = ''; }
					htmlcode3 += '<tr>';
					htmlcode3 += '<td class="tc"><p class="txt_01">'+v.EQPMNT_NM+'</p></td>';
					htmlcode3 += '<td class="tc"><p class="txt_01">'+empty(v.STNDRD)+'</p></td>';
					htmlcode3 += '<td class="tc"><p class="txt_01">'+empty(v.AMOUNT)+'</p></td>';
					if(v.DAY_PAY != v.HALF_PAY)
					htmlcode3 += '<td class="tc"><p class="txt_01">'+empty(v.DAY_PAY.toLocaleString('ko-KR', 'currency'))+'<br>('+empty(v.HALF_PAY.toLocaleString('ko-KR', 'currency'))+')</p></td>';
					else htmlcode3 += '<td class="tc"><p class="txt_01">'+empty(v.DAY_PAY.toLocaleString('ko-KR', 'currency')) + unit +'</p></td>';
					htmlcode3 += '<td class="tc"><p class="txt_01">'+empty(v.ETC)+'</p></td>';
					htmlcode3 += '</tr>';
				});
		
				$.each(data.prcRfrnc,function(i,v){
					
					if(v.GBN == '임대료'){
						htmlcode += '<tr>';
						htmlcode += '<td class="tc"><p class="txt_01">'+v.SHW_RM+'</p></td>';
						if (v.SHW_RM == '무궁화홀')htmlcode += '<td class="tc"><p class="txt_01">'+area.A+'</p></td>';
						if (v.SHW_RM == '국화홀')htmlcode += '<td class="tc"><p class="txt_01">'+area.B+'</p></td>';
						if (v.SHW_RM == '장미홀')htmlcode += '<td class="tc"><p class="txt_01">'+area.C+'</p></td>';
						if (v.SHW_RM == '전시실복도')htmlcode += '<td class="tc"><p class="txt_01">'+area.D+'</p></td>';
						htmlcode += '<td class="tc"><p class="txt_01">'+v.AM.toLocaleString('ko-KR', 'currency')+'</p></td>';
						htmlcode += '<td class="tc"><p class="txt_01">'+v.PM.toLocaleString('ko-KR', 'currency')+'</p></td>';
						htmlcode += '<td class="tc"><p class="txt_01">'+v.ALL_DAY.toLocaleString('ko-KR', 'currency')+'</p></td>';
						htmlcode += '</tr>';
					}  if(v.GBN == '관리비(냉난방제외)'){
						mngObj = {};
						mngObj.name = '<td class="tc"><p class="txt_01">'+v.SHW_RM+'</p></td>';
						if (v.SHW_RM == '무궁화홀')mngObj.area = '<td class="tc"><p class="txt_01">'+area.A+'</p></td>';
						if (v.SHW_RM == '국화홀')mngObj.area = '<td class="tc"><p class="txt_01">'+area.B+'</p></td>';
						if (v.SHW_RM == '장미홀')mngObj.area = '<td class="tc"><p class="txt_01">'+area.C+'</p></td>';
						if (v.SHW_RM == '전시실복도')mngObj.area = '<td class="tc"><p class="txt_01">'+area.D+'</p></td>';
						mngObj.am = '<td class="tc"><p class="txt_01">'+v.AM.toLocaleString('ko-KR', 'currency')+'</p></td>';
						mngObj.pm = '<td class="tc"><p class="txt_01">'+v.PM.toLocaleString('ko-KR', 'currency')+'</p></td>';
						mngObj.all = '<td class="tc"><p class="txt_01">'+v.ALL_DAY.toLocaleString('ko-KR', 'currency')+'</p></td>';
						mngPayLst.push(mngObj);
						 
					}  if(v.GBN == '관리비(냉난방포함)'){
						mngPayObj = {};
						mngPayObj.am = '<td class="tc"><p class="txt_01">'+v.AM.toLocaleString('ko-KR', 'currency')+'</p></td>';
						mngPayObj.pm = '<td class="tc"><p class="txt_01">'+v.PM.toLocaleString('ko-KR', 'currency')+'</p></td>';
						mngPayObj.all = '<td class="tc"><p class="txt_01">'+v.ALL_DAY.toLocaleString('ko-KR', 'currency')+'</p></td>';
						mngPayLst2.push(mngPayObj);	
						
					} 
					
				})
		
		
				for(let i=0;i<mngPayLst.length;i++){
					if(typeof mngPayLst2[i] == 'undefined') { 
							let obj = {}
							obj.am = '<td class="tc"><p class="txt_01"></p></td>';
							obj.pm = '<td class="tc"><p class="txt_01"></p></td>';
							obj.all = '<td class="tc"><p class="txt_01"></p></td>';
							mngPayLst2.push(obj)
					}
					htmlcode2 += '<tr>' + mngPayLst[i].name + mngPayLst[i].area + mngPayLst[i].am + mngPayLst[i].pm + mngPayLst[i].all + mngPayLst2[i].am + mngPayLst2[i].pm + mngPayLst2[i].all + '</tr>';
				}
		
				$('#prcRfrncBind').empty();
				$('#prcRfrncBind').html(htmlcode);
				$('#mngRfrncBind').empty();
				$('#mngRfrncBind').html(htmlcode2);
				$('#eqpmntRfrncBind').empty();
				$('#eqpmntRfrncBind').html(htmlcode3);
		
			},
			error: function(a,b,c){
				console.log(a,b,c)
			}
		});
		
	})
	
	function empty(data){
		return typeof data == 'undefined' ? '' : data === null ? '' : data;
	}

</script>
<!-- 가격표 보기 팝업 스크립트(E) -->