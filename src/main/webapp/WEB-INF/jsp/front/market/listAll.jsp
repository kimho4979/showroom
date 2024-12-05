<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<!-- sub내용(S) -->
			<div class="sub_conts_in">

				<!-- 전체점포 선택 서브탭(S) 웹 -->
				<div class="tab_url devide_5 web">
					<ul>
						<li <c:if test="${paramMap.tyGroupCode eq null }">class="active"</c:if>>
							<a href="${pageContext.request.contextPath}/front/market/listAll.do?&menuId=10" title="전체 배치도<c:if test="${paramMap.tyGroupCode eq null }"> (선택됨)</c:if>">전체 배치도</a></li>
						<li <c:if test="${paramMap.tyGroupCode eq '3' }">class="active"</c:if>>
							<a href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=3&menuId=13" title="생화꽃 도매시장점포(1층)<c:if test="${paramMap.tyGroupCode eq '3' }"> (선택됨)</c:if>">생화꽃 도매시장점포(1층)</a></li>
						<li <c:if test="${paramMap.tyGroupCode eq '4' }">class="active"</c:if>>
							<a href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=4&menuId=14" title="생화꽃 도매시장점포(2층)<c:if test="${paramMap.tyGroupCode eq '4' }"> (선택됨)</c:if>">생화꽃 도매시장점포(2층)</a></li>
						<li <c:if test="${paramMap.tyGroupCode eq '6' }">class="active"</c:if>>
							<a href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=6&menuId=15" title="화환점포<c:if test="${paramMap.tyGroupCode eq '6' }"> (선택됨)</c:if>">화환점포</a></li>
						<li <c:if test="${paramMap.tyGroupCode eq '7' }">class="active"</c:if>>
							<a href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=7&menuId=16" title="본관입주점포(지하1층)<c:if test="${paramMap.tyGroupCode eq '7' }"> (선택됨)</c:if>">본관입주점포(지하1층)</a></li>
					</ul>
					<ul>
						<li <c:if test="${paramMap.tyGroupCode eq '0' }">class="active"</c:if>>
							<a href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=0&menuId=17" title="본관입주점포(2층)<c:if test="${paramMap.tyGroupCode eq '0' }"> (선택됨)</c:if>">본관입주점포(2층)</a></li>
						<li <c:if test="${paramMap.tyGroupCode eq '1' }">class="active"</c:if>>
							<a href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=1&menuId=11" title="분화온실점포(가동)<c:if test="${paramMap.tyGroupCode eq '1' }"> (선택됨)</c:if>">분화온실점포(가동)</a></li>
						<li <c:if test="${paramMap.tyGroupCode eq '2' }">class="active"</c:if>>
							<a href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=2&menuId=12" title="분화온실점포(나동)<c:if test="${paramMap.tyGroupCode eq '2' }"> (선택됨)</c:if>">분화온실점포(나동)</a></li>
						<li <c:if test="${paramMap.tyGroupCode eq '5' }">class="active"</c:if>>
							<a href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=5&menuId=18" title="기타점포<c:if test="${paramMap.tyGroupCode eq '5' }"> (선택됨)</c:if>">기타점포</a></li>
					</ul>
				</div>
				<!-- 전체점포 선택 서브탭(E) 웹 -->

				<!-- 점포선택(S) 모바일 -->
				<div class="tab_select mobile">
					<!-- 
					<ul>
						<li>
							<a href="#!" class="btn_choice">전체점포</a>
							<ul>
								<li class="active"><a href="#!">전체점포</a></li>
								<li><a href="#!">생화꽃 도매시장점포(1층)</a></li>
								<li><a href="#!">생화꽃 도매시장점포(2층)</a></li>
								<li><a href="#!">분화온실점포(가동)</a></li>
								<li><a href="#!">분화온실점포(나동)</a></li>
								<li><a href="#!">화환점포</a></li>
								<li><a href="#!">본관입주점포(지하1층)</a></li>
								<li><a href="#!">본관입주점포(2층)</a></li>
								
							</ul>
						</li>
					</ul> -->
				</div>
				<!-- 점포선택(E) 모바일 -->

				<!-- 타이틀(S) -->
				<!-- 
				<div class="title_box mt30">
					<div class="fl">
						<h4 class="sub_tit_04">점포검색</h4>
					</div>
				</div> -->
				<!-- 타이틀(E) -->

				<!-- 검색창(S) -->
				<!-- 
				<div class="search_box_02 mt10">
					<div class="sel_type_02">
						<select id="searchSel">
							<option>내용</option>
						</select>
						<label for="searchSel"></label>
					</div>
					<div class="ip_type_02">
						<input type="text" id="searchIp" placeholder="검색어를 입력하세요">
						<label for="searchIp"></label>
					</div>
					<a href="#!" class="btn_search">검색</a>
				</div> -->
				<!-- 검색창(E) -->

				<!-- 타이틀(S) -->
				<div class="title_box mt30">
					<div class="fl">
						<h4 class="sub_tit_04">배치도</h4>
					</div>
				</div>
				<!-- 타이틀(E) -->

				<!-- 배치도(S) -->
				<div class="map_box mt10">
					<!-- 1-전체점포(S) -->
					<img src="${pageContext.request.contextPath}/img/map/market_map_01.jpg" alt="양재 꽃시장 점포 배치도입니다. 양재대로쪽엔 남측출입구가 있고, 강남대로쪽엔 주 출입구가 있습니다. 주 출입구 기준 시계방향으로 분화매장 가동, 나무시장, 초화매장, 자재매장, 분화매장 나동, 생화도매시장(절화,소재), 지하꽃시장, 나무시장, 자재매장, 입주업체 사무동, 경매장 및 체험관이 위치해 있습니다." usemap="#map_mobile_02" class="mobile_map" width="1000" height="500">
					<map id="map_mobile_02" name="map_mobile_02">
						<area shape="rect" alt="생화도매시장(절화소재)" title="" coords="334,50,509,133" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=3&menuId=13" target="_self" />
						<area shape="rect" alt="나무시장" title="" coords="566,67,695,113" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=5&menuId=18" target="_self" />
						<area shape="rect" alt="자재매장" title="" coords="737,79,855,142" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=5&menuId=18" target="_self" />
						<area shape="rect" alt="자재매장" title="" coords="125,144,247,203" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=5&menuId=18" target="_self" />
						<area shape="rect" alt="분화매장나동" title="" coords="270,152,550,264" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=2&menuId=12" target="_self" />
						<area shape="rect" alt="지하꽃시장" title="" coords="571,147,677,288" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=6&menuId=15" target="_self" />
						<area shape="rect" alt="입주업체사무동" title="" coords="714,163,879,254" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=0&menuId=17" target="_self" />
						<area shape="rect" alt="초화매장" title="" coords="131,227,241,315" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=5&menuId=18" target="_self" />
						<area shape="rect" alt="나무시장" title="" coords="162,352,286,440" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=5&menuId=18" target="_self" />
						<area shape="rect" alt="분화매장가동" title="" coords="361,302,640,417" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=1&menuId=11" target="_self" />
						<area shape="rect" alt="경매장체험관" title="" coords="712,260,881,423" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=7&menuId=16" target="_self" />
					</map>
					<!-- 1-전체점포(E) -->
				</div>
				<!-- 배치도(E) -->

			</div>
			<!-- sub내용(E) -->