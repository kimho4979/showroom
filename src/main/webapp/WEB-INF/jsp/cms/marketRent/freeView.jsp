<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width, user-scalable=no">
<meta name="apple-mobile-web-app-title" content="수급정보종합시스템">
<meta name="robots" content="no-index,follow">
<meta name="og:title" content="수급정보종합시스템">
<meta property="og:type" content="website">
<meta name="og:description" content="수급정보종합시스템">
<meta name="content" content="수급정보종합시스템 입니다.">
<meta name="keywords" content="수급정보종합시스템">
<meta name="Author"
	content="수급정보종합시스템2차 & www.woorim.co.kr : Kim Juyeon">
<title>수급정보종합시스템</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sypplydemand/common.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sypplydemand/date-picker.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sypplydemand/sub.css" />
<link rel="stylesheet" type="text/css" media="screen and (max-width:1200px)" href="${pageContext.request.contextPath}/css/sypplydemand/media.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/plugine/jQueryUI/jquery-ui.css">
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/sypplydemand/display.js"></script>
</head>
<body>
	<div id="wrap">
		<ul class="skip_nav">
			<li><a href="#header">상단바로가기</a></li>
			<li><a href="#contentWrap">본문바로가기</a></li>
		</ul>
		<!-- skip_nav end -->
		<ul class="skip_nav">
			<li><a href="#header">상단바로가기</a></li>
			<li><a href="#contentWrap">본문바로가기</a></li>
		</ul>
		<!-- skip_nav end -->
		<!-- footer begin -->

		<!-- 페이지 이동(새창 열기) -->
		<form name="blankFrm" id="blankFrm" method="post"	style="display: none;">
			<input type="hidden" name="menuName" value="">
			<input type="hidden" name="url" value="">
			<input type="hidden" name="menuCd" value="">
		</form>

		<header id="header">
			<h1 class="blind">at농수산물유통정보시스템</h1>
			<div class="header01">
				<div class="container">
					<p class="logo">
						<a href="javascript:void(0);"><img src="/images/common/logo.png" alt="농산물유통정보시스템 로고" /></a>
					</p>
					<!-- logo end -->
					<ul class="user">
						<li class="li01"><a href="javascript:void(0);">회원가입</a></li>
						<!-- li01 end -->
						<li class="li02"><a href="">로그인</a></li>
						<!-- li02 end -->
						<li class="li03"><a href="">회원관리</a></li>
						<!-- li03 end -->
						<li class="search">
							<button class="searchBrn">검색</button>
							<!-- searchBrn end -->
						</li>
						<!-- searchBrn -->
					</ul>
					<!-- user end -->
				</div>
				<!-- container end -->
			</div>
			<!-- header01 end -->

			<div class="header02">
				<nav class="mainNav container">
					<div class="">
						<ul class="navList">
							<!-- 1Lv -->
							<li class="depth1" id="MN00000052"><a
								href="javascript:void(0);">
									수급분석 </a> <!-- 2Lv -->
								<ul class="depth2">
									<li id="MN00000060"><a
										href="javascript:void(0);">
											일별 수급상황 </a></li>
									<li id="MN00000061"><a
										href="javascript:void(0);">
											품목별 수급상황 </a></li>
									<!--// 1Lv -->
								</ul> <!--// 2Lv --> <!-- 1Lv -->
							<li class="depth1" id="MN00000053"><a
								href="javascript:void(0);">
									생산관측 </a> <!-- 2Lv -->
								<ul class="depth2">
									<li id="MN00000062"><a
										href="javascript:void(0);">
											생산통계 </a></li>
									<li id="MN00000063"><a
										href="javascript:void(0);">
											소득분석 </a></li>
									<li id="MN00000064"><a
										href="javascript:void(0);">
											산지작황정보 </a></li>
									<!--// 1Lv -->
								</ul> <!--// 2Lv --> <!-- 1Lv -->
							<li class="depth1" id="MN00000054"><a
								href="javascript:void(0);">
									농업기상 </a> <!-- 2Lv -->
								<ul class="depth2">
									<li id="MN00000065"><a
										href="javascript:void(0);">
											읍면별기상정보 </a></li>
									<li id="MN00000066"><a
										href="javascript:void(0);">
											주산지기상정보 </a></li>
									<li id="MN00000067"><a
										href="javascript:void(0);">
											기상영향분석 </a></li>
									<!--// 1Lv -->
								</ul> <!--// 2Lv --> <!-- 1Lv -->
							<li class="depth1" id="MN00000055"><a
								href="javascript:void(0);">
									유통분석 </a> <!-- 2Lv -->
								<ul class="depth2">
									<li id="MN00000068"><a
										href="javascript:void(0);">
											인공신경망 가격예측 </a></li>
									<li id="MN00000069"><a
										href="javascript:void(0);">
											유통가격분석 </a></li>
									<li id="MN00000070"><a
										href="javascript:void(0);">
											농협수매가격 </a></li>
									<li id="MN00000071"><a
										href="javascript:void(0);">
											수입동향 </a></li>
									<li id="MN00000072"><a
										href="javascript:void(0);">
											도매시장별 반입량 </a></li>
									<li id="MN00000073"><a
										href="javascript:void(0);">
											추정재고량 </a></li>
									<!--// 1Lv -->
								</ul> <!--// 2Lv --> <!-- 1Lv -->
							<li class="depth1" id="MN00000056"><a
								href="javascript:void(0);">
									수급정책 </a> <!-- 2Lv -->
								<ul class="depth2">
									<li id="MN00000075"><a
										href="javascript:void(0);">
											수급조절위원회 소개 </a></li>
									<li id="MN00000077"><a
										href="javascript:void(0);">
											TRQ 운영내역 </a></li>
									<li id="MN00000078"><a
										href="javascript:void(0);">
											정부수매내역 </a></li>
									<li id="MN00000079"><a
										href="javascript:void(0);">
											농산물수급조절매뉴얼 </a></li>
									<li id="MN00000080"><a
										href="javascript:void(0);">
											정책성과분석 </a></li>
									<!--// 1Lv -->
								</ul> <!--// 2Lv --> <!-- 1Lv -->
							<li class="depth1" id="MN00000057"><a
								href="javascript:void(0);">
									참여마당 </a> <!-- 2Lv -->
								<ul class="depth2">
									<li id="MN00000081"><a
										href="javascript:void(0);">
											개선의견 </a></li>
								</ul> <!--// 2Lv --></li>
							<!--// 1Lv -->
							<!--// 메뉴출력 -->
						</ul>
						<div class="bg_depth2">&nbsp;</div>
						<!--bg_depth2 end  -->
					</div>
					<!-- container end -->
				</nav>
				<!-- mainNav end -->
			</div>
			<!-- header02 end -->
			<div class="mb_header">
				<button class="mb_navBtn">모바일네비열기</button>
				<div class="mb_navWrap">
					<ul class="mb_navList">
						<li class="tit">전체메뉴
							<button class="login">로그인</button>
							<button class="navCloseBtn">닫기</button>
						</li>
						<li class="depth1">
							<ul class="depth2">
							</ul>
							<!-- depth2 end -->
						</li>
						<!-- depth1 end -->
						<li class="depth1"><a
							href="javascript:void(0);">수급분석</a>
							<ul class="depth2">
								<li><a
									href="javascript:void(0);">일별
										수급상황</a></li>
								<li><a
									href="javascript:void(0);">품목별
										수급상황</a></li>
							</ul>
							<!-- depth2 end --></li>
						<li class="depth1"><a
							href="javascript:void(0);">생산관측</a>
							<ul class="depth2">
								<li><a
									href="javascript:void(0);">생산통계</a></li>
								<li><a
									href="javascript:void(0);">소득분석</a></li>
								<li><a
									href="javascript:void(0);">산지작황정보</a></li>
							</ul>
							<!-- depth2 end --></li>
						<li class="depth1"><a
							href="javascript:void(0);">농업기상</a>
							<ul class="depth2">
								<li><a
									href="javascript:void(0);">읍면별기상정보</a></li>
								<li><a
									href="javascript:void(0);">주산지기상정보</a></li>
								<li><a
									href="javascript:void(0);">기상영향분석</a></li>
							</ul>
							<!-- depth2 end --></li>
						<!-- depth1 end -->

						<li class="depth1"><a href="javascript:void(0);">유통분석</a>
							<ul class="depth2">
								<li><a
									href="javascript:void(0);">인공신경망
										가격예측</a></li>
								<li><a
									href="javascript:void(0);">유통가격분석</a></li>
								<li><a
									href="javascript:void(0);">농협수매가격</a></li>
								<li><a
									href="javascript:void(0);">수입동향</a></li>
								<li><a
									href="javascript:void(0);">도매시장별
										반입량</a></li>
								<li><a
									href="javascript:void(0);">추정재고량</a></li>
							</ul>
							<!-- depth2 end --></li>
						<li class="depth1"><a
							href="javascript:void(0);">수급정책</a>
							<ul class="depth2">
								<li><a
									href="javascript:void(0);">수급조절위원회
										소개</a></li>
								<li><a
									href="javascript:void(0);">TRQ
										운영내역</a></li>
								<li><a
									href="javascript:void(0);">정부수매내역</a></li>
								<li><a
									href="javascript:void(0);">농산물수급조절매뉴얼</a></li>
								<li><a
									href="javascript:void(0);">정책성과분석</a></li>
							</ul>
							<!-- depth2 end --></li>
						<li class="depth1"><a
							href="javascript:void(0);">참여마당</a>
							<ul class="depth2">
								<li><a
									href="javascript:void(0);">개선의견</a></li>
							</ul>
							<!-- depth2 end --></li>
					</ul>
					<!-- mb_navList end -->
				</div>
				<!-- mb_navWrap end -->
			</div>
			<!-- mb_header end -->
		</header>
		<!-- header end-->
		<!-- // footer end -->
		<div id="contentWrap">
			<div class="sub_header01">
				<div class="container">
					<h3 class="subTit">수급정책</h3>
				</div>
				<!-- container end -->
				<ul class="customDl">
					<li class="customDt"><a href="#">맞춤메뉴보기</a></li>
					<!-- customDt end -->
					<li class="customDd">
						<ul class="customList">
							<li class="li01"><span class="tit">일반사용자</span>
								<ul class="customMenu">
									<li><a href="javascript:void(0);">품목별매뉴얼</a></li>
									<li><a href="javascript:void(0);">추정재고량</a></li>
									<li><a href="javascript:void(0);">기관별 조치사항 입력</a></li>
									<li><a href="javascript:void(0);">추정재고량</a></li>
									<li><a href="javascript:void(0);">기관별 조치사항 입력</a></li>
								</ul>
								<!-- customMenu end -->
							<li>
								<!-- li01 end -->
							<li class="li02"><span class="tit">농민</span>
								<ul class="customMenu">
									<li><a href="javascript:void(0);">품목별매뉴얼</a></li>
									<li><a href="javascript:void(0);">추정재고량</a></li>
									<li><a href="javascript:void(0);">기관별 조치사항 입력</a></li>
									<li><a href="javascript:void(0);">추정재고량</a></li>
									<li><a href="javascript:void(0);">기관별 조치사항 입력</a></li>
								</ul>
								<!-- customMenu end -->
							<li>
								<!-- li01 end -->
							<li class="li03"><span class="tit">정책담당자</span>
								<ul class="customMenu">
									<li><a href="javascript:void(0);">품목별매뉴얼</a></li>
									<li><a href="javascript:void(0);">추정재고량</a></li>
									<li><a href="javascript:void(0);">기관별 조치사항 입력</a></li>
									<li><a href="javascript:void(0);">추정재고량</a></li>
									<li><a href="javascript:void(0);">기관별 조치사항 입력</a></li>
								</ul>
								<!-- customMenu end -->
							<li>
								<!-- li01 end -->
						</ul>
						<!-- customList end -->
						<button class="customClose">close</button>
					</li>
					<!-- customDd end -->
				</ul>
				<!-- customDl end -->
			</div>
			<!-- sub_header01 end -->
			<div class="sub_header03">
				<div class="container">
					<!-- <ul class="layerTabList">
						<li class="layerTab"><a href="#">프로그램관리</a>
							<button class="btn_tabClose">close</button></li>
						layerTab end
						<li class="layerTab"><a href="#">로그정보확인</a>
							<button class="btn_tabClose">close</button></li>
						layerTab end
						<li class="layerTab"><a href="#">배치작업현황</a>
							<button class="btn_tabClose">close</button></li>
						layerTab end
					</ul> -->
					<!-- layerTabList end -->
					<div class="radioWrap width30p flR mbNone">
						<p class="tit">관심품목</p>
						<!-- input과 label의 id, for 값이 동일해야함 -->
						<label for="cab" class="selected"><input type="radio"
							class="radio" value="cab" id="cab" />배추</label> <label for="rad"><input
							type="radio" class="radio" value="rad" id="rad" />무</label> <label
							for="pap"><input type="radio" class="radio" value="pap"
							id="pap" />고추</label> <label for="oni"><input type="radio"
							class="radio" value="oni" id="oni" />양파</label> <label for="gar"><input
							type="radio" class="radio" value="gar" id="gar" />마늘</label>
					</div>
					<!-- radioWrap end -->
				</div>
				<!-- container end -->
			</div>
			<!-- sub_header03 end -->
			<div class="sub_header02">
				<div class="container">
					<ul class="subGnbList">
						<li class="home"><a href="#"></a></li>
						<!-- home end -->
						<li class="depth01">
							<dl class="tit_selct">
								<dt>
									<a href="#">수급정책</a>
								</dt>
								<dd>
									<ul class="tit_option">
										<li><a href="#">수급분석</a></li>
										<li><a href="#">생산관측</a></li>
										<li><a href="#">농업기상</a></li>
										<li><a href="#">유통분석</a></li>
										<li class="selected"><a href="#">수급정책</a></li>
										<li><a href="#">참여마당</a></li>
									</ul>
								</dd>
							</dl>
						</li>
						<!-- depth01 end -->
						<li class="depth02">
							<dl class="tit_selct">
								<dt>
									<a href="#">수급조절위원회 소개</a>
								</dt>
								<dd>
									<ul class="tit_option">
										<li><a href="#">title1</a></li>
										<li><a href="#">title2</a></li>
										<li><a href="#">title3</a></li>
									</ul>
								</dd>
							</dl>
						</li>
						<!-- depth02 end -->
					</ul>
					<!-- subGnbList end-->
				</div>
				<!-- container end -->
			</div>
			<!-- sub_header02 end -->
			<section class="ct_section sec01">
				<c:set  var="cont" value="${fn:replace(htmlText , LF, '<br/>')}" />
				${cont }
			</section>
			<!-- ct_section sec01 end -->
		</div>
		<!-- contentWrap end -->

		<!-- footer begin -->


		<footer id="footer" class="scrollbox">
			<div class="container">
				<div class="footer02">
					<ul class="snsList">
						<li class="li01"><a href="javascript:void(0);">페이스북</a></li>
						<!-- li01 end -->
						<li class="li02"><a href="javascript:void(0);">트위터</a></li>
						<!-- li02 end -->
						<li class="li03"><a href="javascript:void(0);">네이버밴드</a></li>
						<!-- li03 end -->
					</ul>
					<!-- snsList end -->
				</div>
				<!-- footer02 end -->

				<div class="footer01">
					<ul class="ft_nav">
						<li><a href="#">개인정보처리방침</a></li>
						<li><a href="#">이메일 주소 무단수집 거부</a></li>
						<li><a href="#">사이트맵</a></li>
					</ul>
					<!-- ft_nav end -->
					<address class="address">
						[58326] 전라남도 나주시 문화로 227 <span class="mbBr">Tel :
							061-931-1114 Fax : 062-931-1299</span> <span class="enter">Copyright
							ⓒ 2017 by Korea agro-Fisheries & Food Trade <span class="mbBr">Corporation
								All Rights Reserved. </span>
						</span>
					</address>
					<!-- address end -->
				</div>
				<!-- footer01 end -->

				<div class="mb_footer03">
					<button class="btn01">HOME</button>
					<!-- btn01 end -->
					<button class="btn01">PC Ver</button>
					<!-- btn01 end -->
					<button class="btn01 topBtn">TOP</button>
					<!-- btn01 end -->
				</div>
				<!-- mb_footer03 end -->
			</div>
			<!-- container end -->
		</footer>
		<!-- footer end -->
		<!-- // footer end -->
	</div>
	<!-- wrap end -->
</body>

</html>
