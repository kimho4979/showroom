<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>



<!doctype html>
<html lang="ko">
<head>
<jsp:include page="/WEB-INF/jsp/front/inc/mainHeader.jsp"/>
</head>
<style>
a, a:visited, a:hover, a:active {
  color: inherit;
}
</style>
<body>

	<!-- 본문바로가기(S) -->
	<div class="skip">
        <a href="#contents">본문 바로가기</a>
	</div>
	<!-- 본문바로가기(E) -->
	
	
	
	<!-- warp(S) -->
	<div class="wrap">
		<!-- header(S) -->
		<jsp:include page="/front/inc/menuList.do"/>
		<!-- header(E) -->

		<!-- main-conts(S) -->
		<div class="main_conts" id="contents" tabindex="0">
			<div class="mc_in">

				<div class="m_col_wrap">
					<div class="m_col_left">
						<div class="ml-top">
							<ul>
								<li>
									<a href="${pageContext.request.contextPath}/front/stat/aucPrice.do?&menuId=23">
										<img src="${pageContext.request.contextPath}/img/ml-top-01.png" alt="">
										<div class="ml-top-text-box">
											<h4>경매시세</h4>
											<p>새롭게바뀐 경매시세<em></em>화훼공판장에서 확인하세요!</p>
										</div>
									</a>
								</li>
								<li>
									<a href="https://flower.at.or.kr/" target="_BLANK" title="화훼유통정보 홈페이지 (새 창으로 이동)">
										<img src="${pageContext.request.contextPath}/img/ml-top-02.png" alt="">
										<div class="ml-top-text-box">
											<h4>화훼유통정보</h4>
											<p>전국 화훼공판장<em></em>경매정보를 실시간으로!</p>
										</div>
									</a>
								</li>
								<li>
									<a href="${pageContext.request.contextPath}/front/content/view.do?contentId=22&menuId=35">
										<img src="${pageContext.request.contextPath}/img/ml-top-03.png" alt="">
										<div class="ml-top-text-box">
											<h4>F스퀘어</h4>
											<p>다채로운 꽃문화 플랫폼,<em></em>F스퀘어</p>
										</div>
									</a>
								</li>
							</ul>
						</div>
					
						<div class="ml-bottom">
					<!-- 슬라이드(S) -->
					<div class="main-banner">
						<div class="main_slide" tabindex="0">
							<!-- Swiper -->
							<div class="swiper-container swiper1">
								<div class="swiper-wrapper">
									<c:forEach items="${bannerList}" var="result" varStatus="status">
									
										<div class="swiper-slide">
											<c:choose>
												<c:when test="${empty result.subject}">
													<c:set var="linkUrl" value="javascript:void(0);"/>
													<c:set var="target" value=""/>
												</c:when>
												<c:when test="${not empty result.subject}">
													<c:set var="linkUrl" value="${result.subject}"/>
													<c:set var="target" value="_BLANK"/>
												</c:when>											
											</c:choose>
											
											<c:set var="bannerId" value="${result.articleId}"/>
											<c:set var="alt" value=""/>
											
											<c:choose>
												<c:when test="${bannerId eq '338'}">
													<c:set var="alt" value="함께 만들어 가는 우리 농수산물의 미래 한국농수산식품유통궁사 at는 우리농식품의 경쟁력을 확보하고, 유통 효율화를 통해 생산자와 소비자의 이익을 극대화 합니다. (새 창으로 이동)"/>
												</c:when>
												<c:when test="${bannerId eq '339'}">
													<c:set var="alt" value="화훼공판장 신종코로나 바이러스 확산 예방 조치사항 1.화훼공판장 전체 매일 방역 2.열화상카메라 운영 3.전매장 손소독제, 체온측정제, 출입명부비치 4.긴급마스크 배부처 운영 5.예방행동수칙 포스터부착, 안내방송 실시"/>
												</c:when>
												<c:when test="${bannerId eq '342'}">
													<c:set var="alt" value="지구를 지키는 저탄소 식생활 코리아 그린푸드 데이 저탄소, 친환경 인증 농산물과 로컬푸드를 이용한 식단을 남김없이 먹어요! (새 창으로 이동)"/>
												</c:when>
												<c:when test="${bannerId eq '720'}">
													<c:set var="alt" value="2022년 상반기 농식품마케팅대학 교육생 모집안내 농림축산식품부와 aT(한국농수산식품유통공사)는 농산물 유통혁신을 이끌 핵심인력 양성을 위해 아래와 같이 aT 농식품 마케팅대학 상반기 교육생을 모집하오니 관심 있는 분들의 많은 참여 바랍니다. 3.2(수) 18:00까지 신청 후 서류전형 선발(합격자에 한하여 3.4(금) 개별통보) 농식품유통교육원 홈페이지에서 신청 가능 (새 창으로 이동)"/>
												</c:when>
												<c:when test="${bannerId eq '736'}">
													<c:set var="alt" value="2022 농식품 빅데이터 플랫폼(KADX) 데이터 바우처 수요기업 모집 농식품 빅데잉터 플랫폼 공급기업 선정 KADX 유료 데이터 상품 119종에 대해 데이터 바우처 사업 연계 데이터 구매시 최대 1600만원 지원, 데이터 가공시 일반 가공 최대 4500만원, ALL 가공 최대 7000만원 지원 (새 창으로 이동)"/>
												</c:when>
												<c:when test="${bannerId eq '745'}">
													<c:set var="alt" value="3월 11일은 흙의 날 생명의 원천, 흙을 지켜요! (새 창으로 이동)"/>
												</c:when>
												<c:when test="${bannerId eq '752'}">
													<c:set var="alt" value="화훼사업센터 주차요금체계 변경 최초1시간: 3000원, 초과 10분당 500원 생화매장 구매고객(최초 1000원), 3만원 미만 구매시(최초 1500원), 3만원 이상 구매시(1시간 무료), 10만원 이상 구매시(2시간 무료)"/>
												</c:when>
											</c:choose>
											
											<a href="${linkUrl}" target="${target}">
												<img src="${pageContext.request.contextPath}/front/board/fileDown2.do?boardId=${result.boardId}&articleId=${result.articleId}&fileSn=0" alt="${alt}" onerror="this.src='${pageContext.request.contextPath}/img/noimage260x225.png'">
											</a>
										</div>
										
									</c:forEach>
								</div>
								<!-- Add Pagination -->
								<div class="swiper-pagination"></div>
								<!-- Add Arrows -->
								<div class="swiper-button-next"></div>
								<div class="swiper-button-prev"></div>
							</div>		
						</div>
					</div>
					<script>
						var swiper = new Swiper('.swiper1', {
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
					</script>
					<!-- 슬라이드(E) -->
				</div>
					</div>
					<div class="m_col_center">
						<div class="mc-top">
							<div class="title_box">
								<div class="fl">
									<h3 class="tit_01">양재 꽃시장 점포소개</h3>
								</div>
							</div>
							<div class="info">
								<img src="${pageContext.request.contextPath}/img/map.png" alt="양재 꽃시장 점포소개 지도로 양재대로쪽엔 남측출입구가 있고, 강남대로쪽엔 주 출입구가 있습니다. 주 출입구 기준 시계방향으로 분화매장 가동, 나무시장, 초화매장, 자재매장, 분화매장 나동, 생화도매시장(절화,소재), 지하꽃시장, 나무시장, 자재매장, 입주업체 사무동, 경매장 및 체험관이 위치해 있습니다." usemap="#map" class="pc_map" width="604" height="313">								
								<map name="map" class="pc_map_point">
									<area shape="rect" coords="196,17,316,74" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=3&menuId=13" target="_self" outline="none" alt="생화도매시장(절화,소재)">
									<area shape="rect" coords="355,27,444,64" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=5&menuId=18" target="_self" outline="none" alt="나무시장">
									<area shape="rect" coords="471,38,536,79" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=5&menuId=18" target="_self" outline="none" alt="자재매장">
									<area shape="rect" coords="71,83,139,120" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=5&menuId=18" target="_self" outline="none" alt="자재매장">
									<area shape="rect" coords="153,86,346,167" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=2&menuId=12" target="_self" outline="none" alt="분화매장 나동">
									<area shape="rect" coords="359,84,427,179" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=6&menuId=15" target="_self" outline="none" alt="지하꽃시장">
									<area shape="rect" coords="456,94,566,155" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=0&menuId=17" target="_self" outline="none" alt="입주업체 사무동">
									<area shape="rect" coords="60,137,144,196" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=5&menuId=18" target="_self" outline="none" alt="초화매장">
									<area shape="rect" coords="82,224,164,283" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=5&menuId=18" target="_self" outline="none" alt="나무시장">
									<area shape="rect" coords="216,189,406,268" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=1&menuId=11" target="_self" outline="none" alt="분화매장가동">
									<area shape="rect" coords="454,160,566,269" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=7&menuId=16" target="_self" outline="none" alt="경매장/체험관">
								</map>
								<img src="${pageContext.request.contextPath}/img/map_mobile.png" alt="양재 꽃시장 점포소개 지도로 양재대로쪽엔 남측출입구가 있고, 강남대로쪽엔 주 출입구가 있습니다. 주 출입구 기준 시계방향으로 분화매장 가동, 나무시장, 초화매장, 자재매장, 분화매장 나동, 생화도매시장(절화,소재), 지하꽃시장, 나무시장, 자재매장, 입주업체 사무동, 경매장 및 체험관이 위치해 있습니다."  usemap="#map_mobile" class="mobile_map" width="584" height="362" style="height: 100%;">
								<map id="map_mobile" name="map_mobile"  class="mobile_map_point">
									<area shape="rect" coords="154,0,313,92" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=3&menuId=13" target="_self" outline="none" alt="생화도매시장(절화,소재)">
									<area shape="rect" coords="321,16,431,92" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=5&menuId=18" target="_self" outline="none" alt="나무시장">
									<area shape="rect" coords="439,17,548,91" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=5&menuId=18" target="_self" outline="none" alt="자재매장">
									<area shape="rect" coords="21,99,130,175" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=5&menuId=18" target="_self" outline="none" alt="자재매장">
									<area shape="rect" coords="134,100,315,214" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=2&menuId=12" target="_self" outline="none" alt="분화매장 나동">
									<area shape="rect" coords="322,100,433,216" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=6&menuId=15" target="_self" outline="none" alt="지하꽃시장">
									<area shape="rect" coords="439,101,580,196" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=0&menuId=17" target="_self" outline="none" alt="입주업체 사무동">
									<area shape="rect" coords="21,180,131,255" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=5&menuId=18" target="_self" outline="none" alt="초화매장">
									<area shape="rect" coords="21,261,130,336" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=5&menuId=18" target="_self" outline="none" alt="나무시장">
									<area shape="rect" coords="137,220,314,334" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=1&menuId=11" target="_self" outline="none" alt="분화매장가동">
									<area shape="rect" coords="439,199,580,338" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=7&menuId=16" target="_self" outline="none" alt="경매장/체험관">
								</map>
								<a href="${pageContext.request.contextPath}/front/market/listAll.do?&menuId=10" class="btn_more" title="양재 꽃시장 점포소개 더보기">더보기+</a>
							</div>
							
						</div>
						<div class="mc-bottom">
							<div class="title_box"> 
								<div class="fl">
									<h3 class="tit_01">알림마당</h3>
								</div>
								<div class="fr">
									<a href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=6&menuId=9" class="btn_more" title="공지사항 더보기">더보기+</a>
								</div>
							</div>
							<div class="info">
								<img class="ico-notice" src="${pageContext.request.contextPath}/img/ico-notice.png" alt="">
								<div class="main_table">
									<table>
										<caption>최신 알림마당 게시글 정보를 제공합니다.</caption>
										<tbody>
											<c:forEach items="${noiceList.resultList}" var="result" varStatus="status">
												<c:if test="${status.index ge 0 and status.index le 2}">
												<tr>
													<th class="tl"><a href="${pageContext.request.contextPath}/front/board/bbsView.do?boardId=${result.boardId}&menuId=9&articleId=${result.articleId}" class="m_th">${result.subject}</a></th>
													<td class="tr"><a href="${pageContext.request.contextPath}/front/board/bbsView.do?boardId=${result.boardId}&menuId=9&articleId=${result.articleId}" class="m_td">${result.registDttm}</a></td>
												</tr>
												</c:if>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
					<div class="m_col_right">
						<div class="mr-top" >
							<ul>
								<li class="event-wrap">
									<div class="event-01">
										<div class="title_box">
											<div class="fl">
												<span class="ico-event blue">2023년 페스타</span>
											</div>
											<div class="fr">
												<a href="${pageContext.request.contextPath}/front/content/view.do?contentId=34&menuId=72" class="btn_more" title="2023년 제5회 양재 플라워 페스타 바로가기">바로가기+</a>
											</div>
										</div>
									</div>
									<span class="event-img">
										<a href="${pageContext.request.contextPath}/front/content/view.do?contentId=34&menuId=40" class="btn_more">
											<img src="${pageContext.request.contextPath}/img/2023_festa/banner.jpg" alt="2023년 제5회 양재 플라워 페스타 이미지" title="2023년 제5회 양재 플라워 페스타 바로가기">
										</a>
									</span>
								</li>
								<li class="event-wrap"  style="line-height:0">
									<span class="event-img" style="display:contents;line-height:0">	</span>
								<div class="ement_img">
									<div class="event-img_lb">
										<a href="${pageContext.request.contextPath}/front/content/showroomreserv.do?type=1" style="height: 100%; width: 100%; text-align: center; line-height: 2.2; font-size: 15.8px;font-family: 'Noto Sans KR', 고딕, san-serif, IcoMoon,dashicons, FontAwesome !important">전시실 예약하기</a>
									</div>
									
									<div class="event-img_rb">
										<a href="${pageContext.request.contextPath}/front/content/showroomreserv.do?type=2" style="height: 100%; width: 100%; text-align: center; line-height: 2.2; font-size: 15.8px;font-family: 'Noto Sans KR', 고딕, san-serif, IcoMoon,dashicons, FontAwesome !important">온라인 견적산출</a>
									</div>
								</div>
								
								
									<%-- <div class="mr-top-text-box"  style="">
										<h4><a href="${pageContext.request.contextPath}/front/content/showroomreserv.do?type=1">전시실 예약</a></h4>
									</div> --%>
										
									<%-- <div class="mr-top-text-box"  style="">
										<h4><a href="${pageContext.request.contextPath}/front/content/showroomreserv.do?type=2">온라인 견적산출</a></h4>
									</div> --%>
								</li>
								<li class="event-wrap" style="display:none;">
									<div class="event-02">
										<div class="title_box">
											<div class="fl">
												<span class="ico-event green">플라워 클래스</span>
											</div>
											<div class="fr">
												<a href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=26&menuId=36" class="btn_more" title="플라워 클래스 더보기">더보기+</a>
											</div>
										</div>
										<div class="mr-top-text-box">
											<c:forEach items="${fClsList.resultList}" var="result" varStatus="status">
												<c:if test="${status.index ge 0 and status.index le 1}">
													<c:choose>
														<c:when test="${not empty result.data01}">
															<a href="${result.data01}" target="_BLANK" title="${result.subject} (새 창으로 이동)">
																<h4>·${result.subject}</h4>
															</a>
														</c:when>
														<c:otherwise>
															<a href="${pageContext.request.contextPath}/front/board/bbsView.do?boardId=${result.boardId}&menuId=36&articleId=${result.articleId}">
																<h4>·${result.subject}</h4>
															</a>
														</c:otherwise>
													</c:choose>
												</c:if>
											</c:forEach>
										</div>
									</div>
								</li>
								<li class="event-wrap" style="display:none;">
									<div class="event-03">
										<div class="title_box">
											<div class="fl">
												<span class="ico-event purple">꽃과 식물 정보</span>
											</div>
											<div class="fr">
												<a href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=28&menuId=37" class="btn_more" title="꽃과 식물 정보 더보기">더보기+</a>
											</div>
										</div>
										<div class="mr-top-text-box">
											<c:forEach items="${fInfoList.resultList}" var="result" varStatus="status">
												<c:if test="${status.index ge 0 and status.index le 2}">
													<c:choose>
														<c:when test="${not empty result.data01}">
															<a href="${result.data01}" target="_BLANK" title="${result.subject} (새 창으로 이동)">
																<h4>·${result.subject}</h4>
															</a>
														</c:when>
														<c:otherwise>
															<a href="${pageContext.request.contextPath}/front/board/bbsView.do?boardId=${result.boardId}&menuId=37&articleId=${result.articleId}">
																<h4>·${result.subject}</h4>
															</a>
														</c:otherwise>
													</c:choose>
												</c:if>
											</c:forEach>
										</div>
									</div>
								</li>
							</ul>
						</div>
					</div>
				</div>	


				
			</div>
		</div>
		<!-- main-conts(E) -->

		<!-- footer(S) -->
		<jsp:include page="/WEB-INF/jsp/front/inc/footer.jsp"/>
		<!-- footer(E) -->

		
		<!-- LNB(S) -->
		<jsp:include page="/front/inc/lnbMenuList.do"/>
		  <!-- LNB(E) -->

	</div>
	<!-- warp(E) -->		
</body>
<script>


	// 전시실 예약 등록 예제 
	let param = {};
	param.evntNm = '행사명입력1';
	param.evntDesc = '행사내용입력1';
	param.orgNm = '상호/단체명입력1';
	param.mngrNm = '담당자입력1';
	param.rprsntrNm = '대표자입력1';
	param.bizType = '업태입력1';
	param.bizType2 = '종목입력1';
	param.telNo = '010-0000-0000';
	param.faxNo = '000-000-000';
	param.address = '주소입력1';
	param.email = 'test@test.com';
	param.rsrvDt = '2024-05-11';
	param.evntDt = 'AM';
	param.userId = 'chae';
	param.hallType = '1';
	param.isTemp = 'Y'
	param.iscorrdr = 'N'
	param.status = 'H'
	listChildParam = [];

	var sreqpmntrsrvtnVO = {
		seq : '',
		eqpmntNm : '장비명1',
		etc:'비고1',
		hallType : '1',
		rentAmt : '1',
		style : 'A'
	};
	listChildParam.push(sreqpmntrsrvtnVO);
	param.sreqpmntrsrvtns = JSON.stringify(listChildParam);

	/*$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/front/sr/rsrvtn.json",
		
		data : param,
		success:function(data){
			if(!isNaN(data.result)){
				alert(data.result)
			}
		},
		error:function(a,b,c){
			console.log(a,b,c)
		}
	});
	*/

</script>
</html>