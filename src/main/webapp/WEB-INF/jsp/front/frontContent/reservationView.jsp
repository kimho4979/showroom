<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

			<!-- sub내용(S) -->
			<div class="sub_conts_in">
<!--${pageContext.request.contextPath}/front/content/showroomview.do?id=1&menuId=33-->
				<!-- 전체전시장 선택 서브탭(S) 웹 -->
				<div class="tab devide_3 web web2" id="reservViewTab">
					<ul>
						<li class="active">
							<a onclick="hallTabLink(event,'1');" title="무궁화홀">무궁화홀</a></li>
						<li>
							<a onclick="hallTabLink(event,'2');" title="국화홀">국화홀</a></li>
						<li>
							<a onclick="hallTabLink(event,'3');" title="장미홀">장미홀</a></li>
					</ul>
				</div>
				<!-- 전체전시장 선택 서브탭(E) 웹 -->

				<!-- 전시장선택(S) 모바일 -->
				<div class="tab_select mobile">
					<!--
					<ul>
						<li>
							<a href="#!" class="btn_choice">생화꽃 도매시장점포(1층)</a>
							<ul>
								<li><a href="#!">전체점포</a></li>
								<li class="active"><a href="#!">생화꽃 도매시장점포(1층)</a></li>
								<li><a href="#!">생화꽃 도매시장점포(2층)</a></li>
								<li><a href="#!">분화온실점포(가동)</a></li>
								<li><a href="#!">분화온실점포(나동)</a></li>
								<li><a href="#!">화환점포</a></li>
								<li><a href="#!">본관입주점포(지하1층)</a></li>
								<li><a href="#!">본관입주점포(2층)</a></li>
								
							</ul>
						</li>
					</ul>-->
				</div>
				<!-- 전시장선택(E) 모바일 -->

				<!-- 타이틀(S) -->
				<div class="title_box mt30">
					<div class="fl">
						<h4 class="sub_tit_02">배치도</h4>
					</div>
				</div>
				<!-- 타이틀(E) -->

				<!-- 전시장 소개(S) -->
				<div class="about_market0">
				<div class="about_market" style="height: 360px;">
                    <div class="am_left" style="height: 100%;">
						<div class="l_img web">
							<div class="swiper-container swiper" id="swiper-action">
								<div class="swiper-wrapper">
									<div class="swiper-slide">
										<img src="${pageContext.request.contextPath}/img/showroom/mugungwha1.jpg">
									</div>
									<div class="swiper-slide">
										<img src="${pageContext.request.contextPath}/img/showroom/mugungwha2.jpg">
									</div>
									<div class="swiper-slide">
										<img src="${pageContext.request.contextPath}/img/showroom/mugungwha3.jpg">
									</div>
								</div>
								<!-- Add Pagination -->
								<div class="swiper-pagination"></div>
								<!-- Add Arrows -->
								<div class="swiper-button-next"></div>
								<div class="swiper-button-prev"></div>
							</div>	
						</div>	
						
							<script>
								var swiper = new Swiper('.swiper', {
									speed: 1000,	
									slidesPerView: 1,
									spaceBetween: 30,
									//spaceBetween: 30,
									loop: true,
									autoplay: {
										delay: 2500,
										disableOnInteraction: false,
									},	
									pagination: {
										el: '.swiper-pagination',
										clickable: true,
									},
									navigation: {
										nextEl: '.swiper-button-next',
										prevEl: '.swiper-button-prev',
									},
								});

							</script>
						<!-- 전시장소개모바일(E) -->
<!-- 						<div class="swiper-container mobile"> -->
<!-- 							<div class="swiper-wrapper"> -->
<%-- 								<c:if test="${mResult.img1 ne null and fn:trim(mResult.img1) ne ''}"> --%>
<!-- 									<div class="swiper-slide"> -->
<!-- 										<div class="market_info"> -->
<!-- 											<div class="m_img"> -->
<%-- 												<img src="${mResult.img1}" alt="${mResult.rcCompName}"> --%>
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 									</div> -->
<%-- 								</c:if> --%>
<%-- 								<c:if test="${mResult.img2 ne null and fn:trim(mResult.img2) ne ''}"> --%>
<!-- 									<div class="swiper-slide"> -->
<!-- 										<div class="market_info"> -->
<!-- 											<div class="m_img"> -->
<%-- 												<img src="${mResult.img2}" alt="${mResult.rcCompName}"> --%>
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 									</div> -->
<%-- 								</c:if> --%>
<%-- 								<c:if test="${mResult.img3 ne null and fn:trim(mResult.img3) ne ''}"> --%>
<!-- 									<div class="swiper-slide"> -->
<!-- 										<div class="market_info"> -->
<!-- 											<div class="m_img"> -->
<%-- 												<img src="${mResult.img3}" alt="${mResult.rcCompName}"> --%>
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 									</div> -->
<%-- 								</c:if> --%>
<%-- 								<c:if test="${mResult.img1 eq null and mResult.img2 eq null and mResult.img3 eq null}"> --%>
<!-- 									<div class="swiper-slide"> -->
<!-- 										<div class="market_info"> -->
<!-- 											<div class="m_img"> -->
<%-- 												<img src="${pageContext.request.contextPath}/img/noimage2.png" alt="이미지없음"> --%>
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 									</div> -->
<%-- 								</c:if> --%>
<%-- 								<c:if test="${fn:trim(mResult.img1) eq '' and fn:trim(mResult.img2) eq '' and fn:trim(mResult.img3) eq ''}"> --%>
<!-- 									<div class="swiper-slide"> -->
<!-- 										<div class="market_info"> -->
<!-- 											<div class="m_img"> -->
<%-- 												<img src="${pageContext.request.contextPath}/img/noimage2.png" alt="이미지없음"> --%>
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 									</div> -->
<%-- 								</c:if> --%>
<!-- 							</div> -->
							<!-- Add Arrows -->
							<!-- <div class="swiper-button-next"></div>
							<div class="swiper-button-prev"></div> -->
<!-- 							<script> -->
<!--  								//스와이프 -->
<!--  								var swiper = new Swiper('.swiper-container', { -->
<!--  									navigation: { -->
<!--  										nextEl: '.swiper-button-next', -->
<!--  										prevEl: '.swiper-button-prev',	 -->
<!--  									}, -->
<!--  									loop: true, -->
<!--  									speed:1000, -->
<!--  									autoplay:true -->
<!--  								});	 -->
<!-- 							</script> -->
<!-- 						</div> -->
						<!-- 전시장소개모바일(E) -->
					</div>
					
				</div>
				<div class="am_right" style="padding: 0px 0px 0px 0px; border-left: 0px; ${noimgestyle}">
						<div class="table_type_01 overflow_a">
							<table>
								<caption>전시실 정보를 알려주는 테이블입니다.</caption>
								<colgroup>
									<col style="width: 33.3%;">
									<col style="width: 33.3%;">
									<col style="width: 33.3%;">
								</colgroup>
								<tbody id="srintro">
									<tr>
										<td class="tc"><p class="txt_01">전용면적</p></td>
<!-- 										<td class="tc"> -->
<!-- 											<p class="txt_01"> -->
<%-- 												${mResult.tyCode}-${mResult.roCode}㎡ --%>
<!-- 											</p> -->
<!-- 										</td> -->
<!-- 										<td class="tc"> -->
<!-- 											<p class="txt_01"> -->
<%-- 												${mResult.tyCode}-${mResult.roCode}평 --%>
<!-- 											</p> -->
<!-- 										</td> -->

										<td class="tc">
											<p class="txt_01">
												394㎡
											</p>
										</td>
										<td class="tc">
											<p class="txt_01">
												119평
											</p>
										</td>
									</tr>
									<tr>
										<td class="tc"><p class="txt_01">수용인원</p></td>
										<td class="tc" colspan="2">
											<p class="txt_01">120명</p>
										</td>
									</tr>
									<tr>
										<td class="tc"><p class="txt_01">사용시간</p></td>
										<td class="tc" colspan="2">
											<p class="txt_01">09:00 ~ 17:00 (오전/오후/전일 선택)</p>
										</td>
									</tr>
									
									<tr>
										<td class="tc" rowspan="5"><p class="txt_01">사용시설</p></td>
										<td class="tc" colspan="2">
											<p class="txt_01">냉/난방</p>
										</td>
									</tr>
									<tr>
										<td class="tc" colspan="2">
											<p class="txt_01">방송앰프</p>
										</td>
									</tr>
									<tr>
										<td class="tc" colspan="2">
											<p class="txt_01">유/무선마이크</p>
										</td>
									</tr>
									<tr>
										<td class="tc" colspan="2">
											<p class="txt_01">빔프로젝트</p>
										</td>
									</tr>
									<tr>
										<td class="tc" colspan="2">
											<p class="txt_01">탁자/의자</p>
										</td>
									</tr>
									
									<tr>
										<td class="tc" rowspan="2"><p class="txt_01">주차이용</p></td>
										<td class="tc" colspan="2">
											<p class="txt_01">2대 무료, 초과분은 할인권 구매 가능<br>(구매처 : 정문 주차관리실 / 주말, 공휴일 미운영)</p>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
					<div class="info_btn_box mt20">
					<button onclick="moveToCal(2);" class="link default" style="">온라인 견적산출</button>
					<button onclick="layer_popup('#priceLayer');" class="link default" style="position: relative; center: 33.3%;">가격표 보기</button>
					<button onclick="moveToCal(1);" class="link reservation" style="position: relative; center: 33.3%;">전시실 예약</button>
				</div>
				<!-- 전시장 소개(E) -->
				
			</div>
			<!-- sub내용(E) -->



<!-- 팝업 (S) -->
<jsp:include page="/WEB-INF/jsp/front/frontContent/reservPricePopup.jsp"/>
<!-- 팝업 (E) -->



<!-- 스크립트(S) -->
<script type="text/javascript">
	
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


	function hallTabLink(e,id){
		e.preventDefault();

		var swipercode = '<div class="swiper-wrapper">';
		
		if(id == '1'){
			swipercode += '<div class="swiper-slide">';
			swipercode += '<img src="${pageContext.request.contextPath}/img/showroom/mugungwha1.jpg">';
			swipercode += '</div>';
			swipercode += '<div class="swiper-slide">';
			swipercode += '<img src="${pageContext.request.contextPath}/img/showroom/mugungwha2.jpg">';
			swipercode += '</div>';
			swipercode += '<div class="swiper-slide">';
			swipercode += '<img src="${pageContext.request.contextPath}/img/showroom/mugungwha3.jpg">';	
			swipercode += '</div>';
			
		} else if(id == '2'){
			swipercode += '<div class="swiper-slide">';
			swipercode += '<img src="${pageContext.request.contextPath}/img/showroom/kukwha1.jpg">';
			swipercode += '</div>';
			swipercode += '<div class="swiper-slide">';
			swipercode += '<img src="${pageContext.request.contextPath}/img/showroom/kukwha2.jpg">';
			swipercode += '</div>';
			swipercode += '<div class="swiper-slide">';
			swipercode += '<img src="${pageContext.request.contextPath}/img/showroom/kukwha3.jpg">';	
			swipercode += '</div>';
		} else if(id == '3'){
			swipercode += '<div class="swiper-slide">';
			swipercode += '<img src="${pageContext.request.contextPath}/img/showroom/jangmi1.jpg">';
			swipercode += '</div>';
			swipercode += '<div class="swiper-slide">';
			swipercode += '<img src="${pageContext.request.contextPath}/img/showroom/jangmi2.jpg">';
			swipercode += '</div>';
			swipercode += '<div class="swiper-slide">';
			swipercode += '<img src="${pageContext.request.contextPath}/img/showroom/jangmi3.jpg">';	
			swipercode += '</div>';
		}
						
		swipercode += '</div>';
		swipercode += '<div class="swiper-pagination"></div>';
		swipercode += '<div class="swiper-button-next"></div>';
		swipercode += '<div class="swiper-button-prev"></div>';

		$('#swiper-action').empty();
		$('#swiper-action').html(swipercode);
								
		var swiper = new Swiper('.swiper', {
			speed: 1000,	
			slidesPerView: 1,
			spaceBetween: 30,
			loop: true,
			autoplay: {
				delay: 2500,
				disableOnInteraction: false,
			},	
			pagination: {
				el: '.swiper-pagination',
				clickable: true,
			},
			navigation: {
				nextEl: '.swiper-button-next',
				prevEl: '.swiper-button-prev',
			},
		});

		var htmlcode = '';		
		let tabIndex = Number(id) - 1;
		
		switch (id) {
			case '1':
				htmlcode = '<td class="tc"><p class="txt_01">전용면적</p></td><td class="tc"><p class="txt_01">394㎡</p></td><td class="tc"><p class="txt_01">119평</p></td>';
				$('#srintro').find('tr').eq(0).empty();
				$('#srintro').find('tr').eq(0).html(htmlcode);
				htmlcode = '<td class="tc"><p class="txt_01">수용인원</p></td><td  colspan="2"  class="tc"><p class="txt_01">120명</p></td>';
				$('#srintro').find('tr').eq(1).empty();
				$('#srintro').find('tr').eq(1).html(htmlcode);
				break;
			case '2':
				htmlcode = '<td class="tc"><p class="txt_01">전용면적</p></td><td class="tc"><p class="txt_01">225㎡</p></td><td class="tc"><p class="txt_01">68평</p></td>'; 
				$('#srintro').find('tr').eq(0).empty();
				$('#srintro').find('tr').eq(0).html(htmlcode);
				htmlcode = '<td class="tc"><p class="txt_01">수용인원</p></td><td  colspan="2"  class="tc"><p class="txt_01">60명</p></td>';
				$('#srintro').find('tr').eq(1).empty();
				$('#srintro').find('tr').eq(1).html(htmlcode);
				break;
			case '3':
				htmlcode = '<td class="tc"><p class="txt_01">전용면적</p></td><td class="tc"><p class="txt_01">169㎡</p></td><td class="tc"><p class="txt_01">51평</p></td>'; 
				$('#srintro').find('tr').eq(0).empty();
				$('#srintro').find('tr').eq(0).html(htmlcode);
				htmlcode = '<td class="tc"><p class="txt_01">수용인원</p></td><td  colspan="2"  class="tc"><p class="txt_01">40명</p></td>';
				$('#srintro').find('tr').eq(1).empty();
				$('#srintro').find('tr').eq(1).html(htmlcode);
				break;
		}

	}
	
	function moveToCal(type) {
		location.href='${pageContext.request.contextPath}/front/content/showroomreserv.do?type='+type;
	}
	
</script>
<!-- 스크립트(E) -->