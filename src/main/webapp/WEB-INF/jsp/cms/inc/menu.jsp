<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<nav class="navbar-default navbar-static-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav metismenu" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="block m-t-xs font-bold">
                                	
                                </span>
                            </a>
                        </div>
                        <div class="logo-element">
                            IN+
                        </div>
                    </li>
                    <c:choose>
                    	 <c:when test="${sessionScope.lvl == 1}">
		                    <li class="active">
		                        <a href="index.html"><i class="fa fa-th-large"></i> <span class="nav-label">사이트관리</span> <span class="fa arrow"></span></a>
		                        <ul class="nav nav-second-level">
		                            <li><a href="${pageContext.request.contextPath}/admin/site/menu.do">사용자메뉴관리</a></li>
		                            <li><a href="${pageContext.request.contextPath}/admin/content/list.do">콘텐츠관리</a></li>
		                            <li><a href="${pageContext.request.contextPath}/admin/boardInfo/list.do">게시판설정관리</a></li>
		                            <li><a href="${pageContext.request.contextPath}/admin/auctioneer/list.do">경매사관리</a></li>
		                            <li><a href="${pageContext.request.contextPath}/admin/banner/list.do?boardId=24">배너관리</a></li>
		                        </ul>
		                    </li>
		                    <!-- 
		                    <li class="active">
		                        <a href="index.html"><i class="fa fa-th-large"></i> <span class="nav-label">시스템관리</span> <span class="fa arrow"></span></a>
		                        <ul class="nav nav-second-level">
		                            <li><a href="${pageContext.request.contextPath}/admin/organization/list.do">부서관리</a></li>
		                            <li><a href="${pageContext.request.contextPath}/admin/staff/list.do">관리자관리</a></li>
		                            <li><a href="${pageContext.request.contextPath}/admin/staff/menu.do">관리자메뉴관리</a></li>
		                            <li><a href="${pageContext.request.contextPath}/admin/code/list.do">공통코드관리</a></li>
		                            <li><a href="${pageContext.request.contextPath}/admin/ip/list.do">아이피접속허용관리</a></li>
		                        </ul>
		                    </li> -->
		                    <li class="active">
		                        <a href="#"><i class="fa fa-bar-chart-o"></i> <span class="nav-label">알림마당</span><span class="fa arrow"></span></a>
		                        <ul class="nav nav-second-level collapse">
		                            <li><a href="${pageContext.request.contextPath}/admin/board/list.do?boardId=6">공지사항</a></li>
		                            <li><a href="${pageContext.request.contextPath}/admin/board/list.do?boardId=10">공판장소식</a></li>
		                        </ul>
		                    </li>
		                    <li class="active">
		                        <a href="#"><i class="fa fa-bar-chart-o"></i> <span class="nav-label">자료실</span><span class="fa arrow"></span></a>
		                        <ul class="nav nav-second-level collapse">
		                        	<li><a href="${pageContext.request.contextPath}/admin/board/list.do?boardId=18">경매</a></li>
		                        	<li><a href="${pageContext.request.contextPath}/admin/board/list.do?boardId=16">임대</a></li>
		                            <li><a href="${pageContext.request.contextPath}/admin/board/list.do?boardId=20">전시실</a></li>
		                            <li><a href="${pageContext.request.contextPath}/admin/board/list.do?boardId=8">기타</a></li>
		                        </ul>
		                    </li>
		                    <li class="active">
		                        <a href="#"><i class="fa fa-bar-chart-o"></i> <span class="nav-label">점포소개</span><span class="fa arrow"></span></a>
		                        <ul class="nav nav-second-level collapse">
		                        	<li><a href="${pageContext.request.contextPath}/admin/marketRent/list.do">점포관리</a></li>
		                        </ul>
		                    </li>
		
		
		                    <li class="active">
		                        <a href="#"><i class="fa fa-bar-chart-o"></i> <span class="nav-label">센터소개</span><span class="fa arrow"></span></a>
		                        <ul class="nav nav-second-level collapse">
		                        	<li><a href="${pageContext.request.contextPath}/admin/sr/list.do">전시실예약관리</a></li>
		                        </ul>
		                    </li>


		                    <li class="active">
		                        <a href="#"><i class="fa fa-leaf"></i> <span class="nav-label">꽃 문화 플랫폼</span><span class="fa arrow"></span></a>
		                        <ul class="nav nav-second-level collapse">
		                        	<li><a href="${pageContext.request.contextPath}/admin/board/list.do?boardId=26">플라워 클래스</a></li>
		                        	<li><a href="${pageContext.request.contextPath}/admin/board/list.do?boardId=28">꽃 정보</a></li>
		                        	<li><a href="${pageContext.request.contextPath}/admin/board/list.do?boardId=30">식물 정보</a></li>
		                        	<li class="">
			                        	<a href="#"><span class="nav-label">2020년 페스타</span><span class="fa arrow"></span></a>
			                        	<ul class="nav nav-third-level ">
			                        		<li><a href="${pageContext.request.contextPath}/admin/board/list.do?boardId=32">온라인 플라워 클래스</a></li>
				                        	<li><a href="${pageContext.request.contextPath}/admin/board/list.do?boardId=34">꽃과 함께하는 문화생활</a></li>
				                        	<li><a href="${pageContext.request.contextPath}/admin/board/list.do?boardId=36">공모전 투표하기</a></li>
			                        	</ul>
		                        	</li>
		                        	<li class="">
			                        	<a href="#"><span class="nav-label">2021년 페스타</span><span class="fa arrow"></span></a>
			                        	<ul class="nav nav-third-level ">
			                        		<li><a href="${pageContext.request.contextPath}/admin/board/list.do?boardId=38">한국 전통 꽃꽃이</a></li>
			                        		<li><a href="${pageContext.request.contextPath}/admin/board/list.do?boardId=40">꽃청춘 서포터즈</a></li>
				                        	<li><a href="${pageContext.request.contextPath}/admin/board/list.do?boardId=42">문화 속 꽃 이야기</a></li>
				                        	<li><a href="${pageContext.request.contextPath}/admin/board/list.do?boardId=44">화훼 정책 정보</a></li>
				                        	<li><a href="${pageContext.request.contextPath}/admin/board/list.do?boardId=46">양재 꽃시장 투어</a></li>
				                        	<li><a href="${pageContext.request.contextPath}/admin/board/list.do?boardId=48">나愛 꽃밭 만들기</a></li>
				                        	<li><a href="${pageContext.request.contextPath}/admin/board/list.do?boardId=50">꽃을 든 사람들</a></li>
				                        	<li><a href="${pageContext.request.contextPath}/admin/board/list.do?boardId=52">참여 이벤트</a></li>
				                        	<li><a href="${pageContext.request.contextPath}/admin/board/list.do?boardId=54">꽃집 토크 콘서트</a></li>
				                        	<li><a href="${pageContext.request.contextPath}/admin/board/list.do?boardId=56">실전 창업 특강</a></li>
			                        	</ul>
		                        	</li>
		                        	<li class="">
			                        	<a href="#"><span class="nav-label">2022년 페스타</span><span class="fa arrow"></span></a>
			                        	<ul class="nav nav-third-level collapse">
			                        		<li><a href="${pageContext.request.contextPath}/admin/content/update.do?contentId=33">플로럴프로그램<br/>(행사안내)</a></li>
			                        		<li><a href="${pageContext.request.contextPath}/admin/content/update.do?contentId=32">플로럴프로그램<br/>(원데이클래스)</a></li>
			                        		<li><a href="${pageContext.request.contextPath}/admin/board/list.do?boardId=68">플로럴프로그램<br/>(토크콘서트)</a></li>
			                        		<li><a href="${pageContext.request.contextPath}/admin/content/update.do?contentId=26">가을국화꽃축제</a></li>
			                        		<li><a href="${pageContext.request.contextPath}/admin/content/update.do?contentId=27">화훼산업발전 심포지엄</a></li>
			                        		<li><a href="${pageContext.request.contextPath}/admin/content/update.do?contentId=28">공모/전시<br/>(식집사 부심 뽐내기 대회)</a></li>
			                        		<li><a href="${pageContext.request.contextPath}/admin/content/update.do?contentId=29">공모/전시<br/>(어린이 꽃그림 그리기 대회)</a></li>
			                        		<li><a href="${pageContext.request.contextPath}/admin/content/update.do?contentId=30">공모/전시<br/>(희귀식물 전시 등)</a></li>
			                        		<li><a href="${pageContext.request.contextPath}/admin/content/update.do?contentId=31">이벤트/부대행사</a></li>
	                                        <li class="active">
		                                        <a href="#"><span class="nav-label">2023년 페스타</span><span class="fa arrow"></span></a>
		                                        <ul class="nav nav-third-level collapse">
													<li><a href="${pageContext.request.contextPath}/admin/content/update.do?contentId=34">행사안내 - 2023</a></li>
													<li><a href="${pageContext.request.contextPath}/admin/content/update.do?contentId=35">관람안내 - 2023</a></li>
													<li><a href="${pageContext.request.contextPath}/admin/content/update.do?contentId=36">플라워전시 - 2023</a></li>
													<li><a href="${pageContext.request.contextPath}/admin/content/update.do?contentId=37">플라워클래스 - 2023</a></li>
													<li><a href="${pageContext.request.contextPath}/admin/content/update.do?contentId=38">이벤트/부대행사 - 2023</a></li>
		                                        </ul>
		                                	</li>
			                        	</ul>
			                        </li>
		                        </ul>
		                    </li>
				        </c:when>
				        <c:when test="${sessionScope.lvl == 2}">
				        	<li class="active">
		                        <a href="#"><i class="fa fa-bar-chart-o"></i> <span class="nav-label">센터소개</span><span class="fa arrow"></span></a>
		                        <ul class="nav nav-second-level collapse">
		                        	<li><a href="${pageContext.request.contextPath}/admin/sr/list.do">전시실예약관리</a></li>
		                        </ul>
		                    </li>
				        </c:when>
				        <c:otherwise>
				        </c:otherwise>
                    </c:choose>
                </ul>

            </div>
        </nav>
