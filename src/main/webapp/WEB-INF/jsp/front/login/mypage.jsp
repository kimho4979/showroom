<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	<input type="hidden" id="message" value="${message}">
	
	<!-- sub-conts(S) -->
		<div class="sub_conts bgw" id="contents" tabindex="0">
			
			<!-- sub상단(S) -->
			<div class="sub_top">
				<h2 class="sub_title">·마이페이지·</h2>

				<!-- 네비게이션(S) -->
				<jsp:include page="/WEB-INF/jsp/front/inc/statNav.jsp"/>
				<!-- 네비게이션(E) -->
			</div>
			<!-- sub상단(E) -->
			
			<!-- sub내용(S) -->
			<div class="sub_conts_in">
				<jsp:include page="/WEB-INF/jsp/front/login/statTab.jsp"/>
				<div class="info_btn_box mt20">
					<!-- <button onclick="layer_popup('#srStatusPopup');" class="link reservation">전시실 예약현황</button> -->
					<!-- <button onclick="moveToReservCal();" class="link reservation">예약하러 가기</button> -->
					<!-- <button onclick="agreePopup(event)" class="link reservation">예약하러 가기</button> -->
				</div>
				<!-- 로그인 정보(S) -->
				<div class="info_box mt30">
					<div class="column_flex col4">
						<ul>
							<c:if test="${atLoginVO ne null }">
							<li class="flex_01">
								<div class="title_box">
									<h4 class="sub_tit_04">aT로그인 정보</h4>
								</div>
								<div class="table_flex_02 col2 mt15">
									<ul>
										<li>
											<span class="t_th_02">아이디</span>
											<div class="ip_type_03">
												<input type="text" class="disabled tc" id="ip_01" value="${atLoginVO.loginId}" readonly="readonly" title="아이디"><label for="ip_01"></label>
											</div>
										</li>
										<li>
											<span class="t_th_02">성명</span>
											<div class="ip_type_03">
												<input type="text" class="disabled tc" id="ip_02" value="${atLoginVO.name}" readonly="readonly" title="성명"/><label for="ip_02"></label>
											</div>
										</li>
									</ul>
								</div>	
							</li>
							</c:if>
							<c:if test="${atLoginVO ne null and loginOracle ne 'Y'}">
							<li class="flex_02">
								<div class="title_box">
									<h4 class="sub_tit_04">SNS 연계상태</h4>
								</div>
								<div class="table_flex_02 col3 mt15">
									<ul>
										<li>
											<span class="t_th_02 naver">네이버</span>
											<c:if test="${naverVO ne null}">
											<a href="javascript:fn_snsDel('N');" class="btn_type_03 cancel" title="네이버 계정 연계 해제">해제</a>
											</c:if>
											<c:if test="${naverVO eq null}">
											<a href="#" id="naver_id_login" class="btn_type_03 link" title="네이버 계정 연계">연계</a>
											</c:if>
										</li>
										<li>
											<span class="t_th_02 kakao">카카오</span>
											<c:if test="${kakaoVO ne null}">
											<a href="javascript:fn_snsDel('K');" class="btn_type_03 cancel" title="카카오 계정 연계 해제">해제</a>
											</c:if>
											<c:if test="${kakaoVO eq null}">
											<a href="javascript:loginWithKakao();" class="btn_type_03 link" title="카카오 계정 연계">연계</a>
											</c:if>
										</li>
										<!-- 
										<li>
											<span class="t_th_02 fb">페이스북</span>
											<c:if test="${facebookVO ne null}">
											<a href="javascript:fn_snsDel('F');" class="btn_type_03 cancel">해제</a>
											</c:if>
											<c:if test="${facebookVO eq null}">
											<a href="javascript:faceLogin();" class="btn_type_03 link">연계</a>
											</c:if>
										</li> -->
									</ul>
								</div>	
							</li>
							</c:if>
							<c:if test="${snsLoginVO ne null}">
							<li class="flex_03">
								<div class="title_box">
									<h4 class="sub_tit_04">SNS 로그인 정보</h4>
								</div>
								<div class="table_flex_02 col2 mt15">
									<ul>
										<li>
											<span class="t_th_02">SNS 회원번호</span>
											<div class="ip_type_03">
												<input type="text" class="disabled tc" id="ip_03" value="${snsLoginVO.id}" readonly="readonly" title="SNS 회원번호"><label for="ip_03"></label>
											</div>
										</li>
										<li>
											<span class="t_th_02">구분</span>
											<div class="dis_box">
												<c:if test="${snsLoginVO.type eq 'N'}">
													<span class="log_info naver">네이버</span>
												</c:if>
												<c:if test="${snsLoginVO.type eq 'K'}">
													<span class="log_info kakao">카카오</span>
												</c:if>
												<c:if test="${snsLoginVO.type eq 'F'}">
													<span class="log_info fb">페이스북</span>
												</c:if>
											</div>
										</li>
									</ul>
								</div>	
							</li>							
							</c:if>
							<c:if test="${atLoginVO eq null }">
							<li class="flex_04">
								<div class="title_box">
									<h4 class="sub_tit_04">aT 연계</h4>
								</div>
								<div class="table_flex_02 mt15">
									<ul>
										<li>
											<div class="ip_type_01">
												<input type="text" class="h40" placeholder="아이디를 입력하세요" id="logId" title="aT 연계 아이디 입력"><label for="logId"></label>
											</div>
											<div class="ip_type_01">
												<input type="password" class="h40" placeholder="비밀번호를 입력하세요" id="logPw" title="aT 연계 비밀번호 입력"><label for="logPw"></label>
											</div>
											<a href="javascript:atLogin();" class="btn_search dib">로그인</a>
										</li>
									</ul>
								</div>	
							</li>
							</c:if>
						</ul>
					</div>
				</div>
				
								<div class="title_box" style="margin-top:30px">
									<h4 class="sub_tit_04">전시실예약</h4>
								</div>
								<div class="info_box mt10">
									<div class="info_btn_box mt10">
										<button onclick="layer_popup('#reservationStat');" class="link reservation">전시실 예약현황</button>
									</div>
								</div>


				<!-- 로그인 정보(E) -->
				<c:if test="${atLoginVO ne null}">
					<c:if test="${floList eq null or fn:length(floList) == 0}">
						<c:if test="${aucType99 ne true}">
							<div class="title_box mt30">
								<div class="fl">
									<h4 class="sub_tit_04">
										화훼부가정보 입력 방법
									</h4>
								</div>
							</div>
							<div class="warn_box mt10"><!-- https://member.at.or.kr/customer/m100002/writeMemberCategory.action -->
								<p class="txt_w"><span class="number">·</span>aT통합 홈페이지 로그인 : <a href="https://member.at.or.kr/customer/m100002/login.action?prePage=https://member.at.or.kr/customer/m100002/writeMemberCategory.action" target="_BLANK">https://member.at.or.kr/customer/m100002/login.action</a></p>
								<p class="txt_w mt20"><span class="number">·</span> 로그인 후 추가정보 입력 화면 </p>
								<p class="txt_w pdl20">(1) 분류추가 선택</p>
								<p class="txt_w pdl20">- 고객분류 : 화훼유통사업</p>
								<p class="txt_w pdl20">- 고객구분 : 중도매인, 출하농가, 공동출하단체, 운송업체 중 선택</p>
								<p class="txt_w pdl20">- 관리지사 : aT센터(양재동)</p>
								<p class="txt_w pdl20">- 화훼부류 : 절화, 난, 관엽 중 선택</p>
								<p class="txt_w pdl20">- 화훼공판장코드 : 본인 공판장 코드 입력</p>
								<p class="txt_w mt10 pdl20">(2) 회원가입완료 버튼 클릭</p>
								<p class="txt_w mt10 pdl20">(3) aT화훼공판장 로그아웃 후 재로그인</p>
								
								
							</div>
						</c:if>
					</c:if>
				</c:if>

				<!-- 타이틀(S) -->
				<c:if test="${floList ne null and fn:length(floList) > 0}">
				<div class="title_box mt30">
					<div class="fl">
						<h4 class="sub_tit_04">
							화훼부가정보 
							<c:if test="${aucType07 ne true}">
								<c:if test="${aucType01 eq true}">(출하자)</c:if>
								<c:if test="${aucType03 eq true}">(중도매인)</c:if>
							</c:if>
							<c:if test="${aucType07 eq true}">
								(경매사)
							</c:if>
						</h4>
					</div>
				</div>
				<!-- 타이틀(E) -->

				<!-- 테이블(S) -->
				<div class="table_type_06 type_02 devide_3 mt10">
					<ul>
						<li>
							<div class="t_th">
								<span>절화</span>
							</div>
							<div class="t_td">
								<div class="ip_type_01 w100p">
									<input type="text" class="tc" id="ip_04" value="${nFloLoginVO.chulCd}" readonly="readonly"><label for="ip_04"></label>
								</div>
							</div>
						</li>
						<li>
							<div class="t_th">
								<span>난</span> 
							</div>
							<div class="t_td">
								<div class="ip_type_01 w100p">
									<input type="text" class="tc" id="ip_05" value="${yFloLoginVO.chulCd}" readonly="readonly"><label for="ip_05"></label>
								</div>
							</div>
						</li>
						<li>
							<div class="t_th">
								<span>관엽</span>
							</div>
							<div class="t_td">
								<div class="ip_type_01 w100p">
									<input type="text" class="tc" id="ip_06" value="${cFloLoginVO.chulCd}" readonly="readonly"><label for="ip_06"></label>
								</div>
							</div>
						</li>
					</ul>
				</div>
				<!-- 테이블(E) -->

				<!-- 테이블03(S) -->
				<div class="table_type_01 mt30">
					<table>
						<caption>코드, 부류, 기본설정, 입찰시작 문자 수신동의 정보를 알려주는 화훼부가정보 테이블입니다.</caption>
						<colgroup>
							<col width="25%">
							<col width="25%">
							<col width="25%">
							<col width="25%">
						</colgroup>
						<thead class="bdn">
							<tr>
								<th>코드</th>
								<th>부류</th>
								<th>기본설정</th>
								<th>입찰시작 문자 수신동의</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${aucType07 ne true}">
							
								<c:if test="${aucType01 eq true}">
									<!-- 출하자 -->
									<c:set var="aucType" value="01"/>
								</c:if>
								<c:if test="${aucType03 eq true}">
									<!-- 중도매인 -->
									<c:set var="aucType" value="03"/>
									
								</c:if>
								
							</c:if>
							<c:if test="${aucType07 eq true}">
								<!-- 중도매인 -->
									<c:set var="aucType" value="07"/>
							</c:if>
							
							
							<c:forEach items="${floList}" var="floVO" varStatus="status">
							<c:if test="${aucType eq floVO.aucType}">
								<tr>
									<td class="tc" style="cursor:pointer"><p class="txt_01">${floVO.chulCd}</p></td>
									<td class="tc" style="cursor:pointer"><p class="txt_01">
										<c:if test="${floVO.floMokCd eq 'N'}">
										절화
										</c:if>
										<c:if test="${floVO.floMokCd eq 'Y'}">
										난
										</c:if>
										<c:if test="${floVO.floMokCd eq 'C'}">
										관엽
										</c:if>
										</p>
									</td>
									<td class="tc"><a href="javascript:setFloDef('${floVO.floMokCd}','${floVO.chulCd}');" class="btn_type_round set">기본설정</a></td>
									<td class="tc">
									<c:if test="${floVO.smsAppVO ne null }">
										<a href="javascript:setSmsCan('${floVO.floMokCd}','${floVO.chulCd}');" class="btn_type_round set">수신거부</a>
									</c:if>
									<c:if test="${floVO.smsAppVO eq null }">
										<a href="javascript:setSmsApp('${floVO.floMokCd}','${floVO.chulCd}');" class="btn_type_round set">수신동의</a>
									</c:if>
									</td>
								</tr>
							</c:if>
							</c:forEach>
						</tbody>
					</table>
					</div>
				<!-- 테이블03(E) -->
				
				
				<!-- 타이틀(S) -->
				<div class="title_box mt30">
					<div class="fl">
						<h4 class="sub_tit_04">
							sms 문자내역
						</h4>
					</div>
				</div>
				<!-- 타이틀(E) -->
				
				<!-- 테이블03(S) -->
				<div class="table_type_01 mt30 overflow_a" style="max-height: 300px;">
					<table>
						<caption>수신자번호, 수신일시, 수신내용 정보를 알려주는 SMS 문자내역 테이블입니다.</caption>
						<colgroup>
							<col width="20%">
							<col width="20%">
							<col width="60%">
						</colgroup>
						<thead class="bdn">
							<tr>
								<th>수신자번호</th>
								<th>수신일시</th>
								<th>수신내용</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${smsList}" var="smsVO" varStatus="status">
								<tr>
									<td class="tc" style="cursor:pointer"><p class="txt_01">${smsVO.recvNumber}</p></td>
									<td class="tc" style="cursor:pointer"><p class="txt_01">${smsVO.sendReqDate}</p></td>
									<td class="tl" style="cursor:pointer"><p class="txt_01"><pre>${smsVO.message}</pre></p></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- 테이블03(E) -->
				
				<!-- 타이틀(S) -->
				<div class="title_box mt30">
					<div class="fl">
						<h4 class="sub_tit_04">
							mms 문자내역
						</h4>
					</div>
				</div>
				<!-- 타이틀(E) -->
				
				<!-- 테이블03(S) -->
				<div class="table_type_01 mt30 overflow_a" style="max-height: 300px;">
					<table>
						<caption>수신자번호, 수신일시, 수신내용 정보를 알려주는 MMS 문자내역 테이블입니다.</caption>
						<colgroup>
							<col width="20%">
							<col width="20%">
							<col width="60%">
						</colgroup>
						<thead class="bdn">
							<tr>
								<th>수신자번호</th>
								<th>수신일시</th>
								<th>수신내용</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${mmsList}" var="mmsVO" varStatus="status">
								<tr>
									<td class="tc" style="cursor:pointer"><p class="txt_01">${mmsVO.recvNumber}</p></td>
									<td class="tc" style="cursor:pointer"><p class="txt_01">${mmsVO.sendReqDate}</p></td>
									<td class="tl" style="cursor:pointer"><p class="txt_01"><pre>${mmsVO.message}</pre></p></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- 테이블03(E) -->
				</c:if>
				

			</div>
			<!-- sub내용(E) -->
		</div>
		<!-- sub-conts(E) -->
		
		<jsp:include page="/WEB-INF/jsp/front/frontContent/reservationStat.jsp"/>
			
			<script type="text/javascript" src="${pageContext.request.contextPath}/js/sns.js"></script>
			<script type="text/javascript">
				 $( document ).ready(function() {
		        	   var message = $("#message").val();
		        	   if(message != null && message != ""){
		        		   alert(message);
		        	   }
		        	   
					   console.log('${atLoginVO}')
					   console.log('${snsLoginVO}')
		        	   naverLoginInitMypage();

					   getRsrvtnStat()
		        	   
		          });
				 
				 
				 function setFloDef(bunChk, code){
					
					 $.ajax({
							data:{
								code: code,
								bunChk: bunChk
						        },
					        type : "POST",
					        url : "${pageContext.request.contextPath}/front/setFloDef.json",
					        success : function(data){
					           console.log(data.result);
					           if(data.result == '1'){
					        	   alert("성공적으로 저장되었습니다.");
					        	   location.reload(true);
					           }
					           
					        }
					    });
					
				 }
				 
				 
				 function setSmsCan(bunChk, code){
						
					 $.ajax({
							data:{
								code: code,
								bunChk: bunChk
						        },
					        type : "POST",
					        url : "${pageContext.request.contextPath}/front/setSmsCan.json",
					        success : function(data){
					           console.log(data.result);
					           if(data.result == '1'){
					        	   alert("성공적으로 수신거부 되었습니다.");
					        	   location.reload(true);
					           }
					           
					        }
					    });
					
				 }
				 
				 function setSmsApp(bunChk, code){
					 $.ajax({
							data:{
								code: code,
								bunChk: bunChk
						        },
					        type : "POST",
					        url : "${pageContext.request.contextPath}/front/setSmsApp.json",
					        success : function(data){
					           console.log(data.result);
					           if(data.result == '1'){
					        	   alert("성공적으로 수신동의 되었습니다.");
					        	   location.reload(true);
					           }
					           
					        }
					    });
					
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
				 function getRsrvtnStat(){
					let loginId = '${atLoginVO.loginId}';
					$.ajax({
						type:"post",
						url:"${pageContext.request.contextPath}/front/getsrrsrvtnlist.json",
						data : ({rsrvDtFrom:null,rsrvDtTo:null,loginId:loginId}),
						//dataType : 'json',
						//contentType : 'application/json;charset=UTF-8',
						success:function(data){
							console.log(data);
							var eqpmntRfrnc = {};
							$.each(data.eqpmntRfrnc,function(i,v){
								if (v.HALL_TYPE == '1') {
									if (v.EQPMNT_NM == '무선마이크') eqpmntRfrnc.wlsMic = v.DAY_PAY;
									if (v.EQPMNT_NM == '유선마이크') eqpmntRfrnc.wMic = v.DAY_PAY;
									if (v.EQPMNT_NM == '방송엠프') eqpmntRfrnc.bAmp = v.DAY_PAY;
									if (v.EQPMNT_NM == '프로젝터') { eqpmntRfrnc.prjctrDayPay1 = v.DAY_PAY; eqpmntRfrnc.prjctrHalfPay1 = v.HALF_PAY; }
									if (v.EQPMNT_NM == '탁자') eqpmntRfrnc.tbl = v.DAY_PAY;
									if (v.EQPMNT_NM == '의자') eqpmntRfrnc.chr = v.DAY_PAY;
									if (v.EQPMNT_NM == '쓰레기봉투') eqpmntRfrnc.grbgPck = v.DAY_PAY;
								}
								if (v.HALL_TYPE == '2') {
									if (v.EQPMNT_NM == '프로젝터') { eqpmntRfrnc.prjctrDayPay2 = v.DAY_PAY; eqpmntRfrnc.prjctrHalfPay2 = v.HALF_PAY; }
								}
								if (v.HALL_TYPE == '3') {
									if (v.EQPMNT_NM == '프로젝터') { eqpmntRfrnc.prjctrDayPay3 = v.DAY_PAY; eqpmntRfrnc.prjctrHalfPay3 = v.HALF_PAY; }
								}
							})
							if(Array.isArray(data.srrsrvtnlistGrp)){
								let htmlcode = '';
								$.each(data.srrsrvtnlistGrp,function(i,v){
									v.eqpmntRfrnc = eqpmntRfrnc;
									let inDt = formatDate(v.inDt);
									let srrsrvtnInDtList = data.srrsrvtnlist.filter((element) => element.grpId == v.grpId);
									
									for (var i=0; i<srrsrvtnInDtList.length; i++) {
										if (i == 0) htmlcode += '<tr><td class="tc" rowspan="'+srrsrvtnInDtList.length+'">'+inDt+'</td>';
										else htmlcode += '<tr>';
										htmlcode += '<td class="tc" >'+srrsrvtnInDtList[i].status+'</td>';
										htmlcode += '<td class="tc" >'+srrsrvtnInDtList[i].hallType+'</td>';
										htmlcode += '<td class="tc" >'+formatDate(srrsrvtnInDtList[i].rsrvDtStart)+'</td>';
										htmlcode += '<td class="tc" >'+srrsrvtnInDtList[i].evntDt+'</td>';
										htmlcode += '<td class="tc" >'+ (srrsrvtnInDtList[i].totalPay == null || srrsrvtnInDtList[i].totalPay == '' ? '-' : srrsrvtnInDtList[i].totalPay.toLocaleString()) +'</td>';
										if (i == 0) htmlcode += '<td class="tc" rowspan="'+srrsrvtnInDtList.length+'"><div class="info_btn_box info_btn_box2"><button class="link default but_001" style="width:100%; height: 2em;"  data-json=\''+JSON.stringify(v)+'\' onclick="printRsrvCntrct(event);">출력</button>	<button class="link reservation but_002" style="width:100%; height: 2em;border-right:1px solid #dcdcdc;" data-json=\''+JSON.stringify(v)+'\'  onclick="printRsrvCntrct(event,\'detail\');">상세출력</button></div></td></tr>';
										else htmlcode += '</tr>';
									}
								})	
								
								$('#rsrvtnStatBindList').empty()
								$('#rsrvtnStatBindList').html(htmlcode)

							}
						}
					})
				 } 
				 
				 function formatDate(inputDate) {
					var year = inputDate.substring(0, 4);
					var month = inputDate.substring(4, 6);
					var day = inputDate.substring(6, 8);

					var date = new Date(year, month - 1, day);

					var formattedDate = date.getFullYear() + '-' + ('0' + (date.getMonth() + 1)).slice(-2) + '-' + ('0' + date.getDate()).slice(-2);

					return formattedDate;
        		}
				// 계약서 출력
				function printRsrvCntrct(e,detail){
					
					var v = (JSON.parse(e.target.getAttribute('data-json')))

					var url = '${pageContext.request.contextPath}/front/print/srContract.do';
						
					//var windowName = "_blank";
					//var windowFeatures = "width=800,height=600,left=200,top=200,resizable=yes,scrollbars=yes";
					
					const formData = new FormData();
					const items = [
						{ grpId: v.grpId,userId: v.userId,type: detail == 'detail' ? 'detail' : 'default'}
    				];					

					formData.append('params', JSON.stringify(items));
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


					var newWindow = window.open(url, windowName, windowFeatures);
					// Wait for the new window to fully load before sending the message
					newWindow.onload = function() {
						
						var params = {
							evntNm: v.evntNm,
							rprsntrNm: v.rprsntrNm,
							mngrNm: v.mngrNm,
							rsrvDtStart: v.rsrvDtStart,
							rsrvDtEnd: v.rsrvDtEnd,
							hallType: v.hallTypeCd,
							evntDt: v.evntDtCd,
							totalPay:  v.totalPay,
							eqpmntPay:  v.eqpmntPay,
							mngPay:  v.mngPay,
							rentPay:  v.rentPay,
							btchStyl : v.btchStyl,
							eqpmntRfrnc : v.eqpmntRfrnc,
							wlsMicInpt : v.wlsMic,
							wMicInpt : v.wMic,
							prjctrInpt : v.prjctr,
							tblInpt : v.tbl,
							bAmpInpt : v.bAmp,
							chrInpt : v.chr,
							grbgPckInpt : v.grbgPck,
							address : v.address + ' ' + v.addressDtl,
							comRgstNo : v.comRgstNo,	
							bizType : v.bizType,
							bizType2 : v.bizType2,
							telNo : v.telNo,
							faxNo : v.faxNo,
							email : v.email,
							act : 'move',
							act : 'write',
						};
						newWindow.postMessage(params, '*');
					};
				}
				 
		    </script> 
  