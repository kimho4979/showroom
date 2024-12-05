<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="requestURI" value="${requestScope['javax.servlet.forward.request_uri']}" />
<%-- 2021-11-17 이현승 --%>
<c:set var="floMokCdCheck" value="${false}" />
<c:forEach items="${floLoginList}" var="floLoginVO" varStatus="status" >
	<c:if test="${floLoginVO.aucType eq '03' and floLoginVO.floMokCd eq 'N'}">
		<c:if test="${floMokCdCheck eq false }">
		  <c:set var="floMokCdCheck" value="${true}" />
		</c:if>		
	</c:if>
</c:forEach>
<c:choose>
	<c:when test="${fn:contains(requestURI, 'pumAucPriceList.do')}">
		<c:set var="dept01Nm" value="공통"/>
		<c:set var="dept02Nm" value="품목/품종별 경매시세"/>
	</c:when>
	
	<c:when test="${fn:contains(requestURI, 'marketOrgInfo.do') or fn:contains(requestURI, 'marketOrgBelongShapr.do') or fn:contains(requestURI, 'marketSaleCalc') or fn:contains(requestURI, 'marketActualResult.do') or fn:contains(requestURI, 'markShprBounty.do')}">
		<c:set var="dept01Nm" value="출하단체"/>
		<c:choose>
			<c:when test="${fn:contains(requestURI, 'marketOrgInfo.do')}">
				<c:set var="dept02Nm" value="등록정보"/>
			</c:when>
			<c:when test="${fn:contains(requestURI, 'marketOrgBelongShapr.do')}">
				<c:set var="dept02Nm" value="소속농가현황"/>
			</c:when>
			<c:when test="${fn:contains(requestURI, 'marketSaleCalc')}">
				<c:set var="dept02Nm" value="판매정산내역"/>
			</c:when>
			<c:when test="${fn:contains(requestURI, 'marketActualResult.do')}">
				<c:set var="dept02Nm" value="소속농가실적확인서"/>
			</c:when>
			<c:otherwise>
				<c:set var="dept02Nm" value="공동출하장려금"/>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:when test="${fn:contains(requestURI, 'marketShprInfo.do') or fn:contains(requestURI, 'shprSaleCalc') or fn:contains(requestURI, 'shprActualResult.do')}">
		<c:set var="dept01Nm" value="출하농가"/>
		<c:choose>
			<c:when test="${fn:contains(requestURI, 'marketShprInfo.do')}">
				<c:set var="dept02Nm" value="등록정보"/>
			</c:when>
			<c:when test="${fn:contains(requestURI, 'shprSaleCalc')}">
				<c:set var="dept02Nm" value="판매정산내역"/>
			</c:when>
			<c:otherwise>
				<c:set var="dept02Nm" value="실적확인서"/>
			</c:otherwise>
		</c:choose>
	</c:when>
	<%-- 2021-11-17 이현승 --%>
	<c:when test="${fn:contains(requestURI, 'whslDealCalc.do') or fn:contains(requestURI, 'sangJangCntList.do') or  fn:contains(requestURI, 'whslDealBlotter.do')}">
		<c:set var="dept01Nm" value="중도매인"/>
		<c:choose>
			<c:when test="${fn:contains(requestURI, 'whslDealCalc.do')}">
				<c:set var="dept02Nm" value="거래내역(낙찰서)"/>
			</c:when>
			<%-- 2021-11-17 이현승 --%>
			<c:when test="${fn:contains(requestURI, 'sangJangCntList.do')}">
				<c:if test="${floMokCdCheck eq true}">
					<c:set var="dept02Nm" value="상장물량조회(절화)"/>
				</c:if>
			</c:when>
			<c:otherwise>
				<c:set var="dept02Nm" value="거래원장"/>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:when test="${fn:contains(requestURI, 'transDateParticulars.do') or fn:contains(requestURI, 'transShprPartiTot.do') or fn:contains(requestURI, 'transShprPartiDetail.do')}">
		<c:set var="dept01Nm" value="운송업체"/>
		<c:choose>
			<c:when test="${fn:contains(requestURI, 'transDateParticulars.do')}">
				<c:set var="dept02Nm" value="일자별운송내역"/>
			</c:when>
			<c:otherwise>
				<c:set var="dept02Nm" value="출하자운송내역"/>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:when test="${fn:contains(requestURI, 'wholeSalerShipping.do') or fn:contains(requestURI, 'boxLoading.do')}">
		<c:set var="dept01Nm" value="항운노조"/>
		<c:choose>
			<c:when test="${fn:contains(requestURI, 'wholeSalerShipping.do')}">
				<c:set var="dept02Nm" value="중도매인 배송비내역"/>
			</c:when>
			<c:otherwise>
				<c:set var="dept02Nm" value="상자형별 하역비내역"/>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<c:set var="dept01Nm" value="마이페이지"/>
	</c:otherwise>
</c:choose>

<title>
<c:choose>
	<c:when test="${dept01Nm ne '마이페이지'}">
		${dept02Nm} - 
	</c:when>
	<c:otherwise>
		마이페이지 - 
	</c:otherwise>
</c:choose>
양재꽃시장
</title>



			<!-- 네비게이션(S) -->
			<div class="nav_box">
				<div class="nav_in">
					<a href="${pageContext.request.contextPath}/" class="btn_home"><img src="${pageContext.request.contextPath}/img/btn_home.png" alt="홈"></a>
					<ul class="nav_depth_box">
						<li class="btn_nav_depth_01">
							<a href="#!">${dept01Nm}<span class="nav_arrow"><img src="${pageContext.request.contextPath}/img/nav_arrow.png" alt=""></span></a>
							<ul class="nav_depth_box_02">
								<li class="btn_nav_depth_02"><a href="${pageContext.request.contextPath}/front/mypage.do">마이페이지</a></li>
								<c:if test="${aucType02 eq true}">
								<li class="btn_nav_depth_02"><a href="${pageContext.request.contextPath}/front/stat/marketOrgInfo.do">출하단체</a></li>
								</c:if>
								<c:if test="${aucType01 eq true}">
								<li class="btn_nav_depth_02"><a href="${pageContext.request.contextPath}/front/stat/marketShprInfo.do">출하농가</a></li>
								</c:if>
								<c:if test="${aucType03 eq true}">
								<li class="btn_nav_depth_02"><a href="${pageContext.request.contextPath}/front/stat/whslDealCalc.do">중도매인</a></li>
								</c:if>
								<c:if test="${aucType04 eq true}">
								<li class="btn_nav_depth_02"><a href="${pageContext.request.contextPath}/front/stat/transDateParticulars.do">운송업체</a></li>
								</c:if>
								<c:if test="${aucType05 eq true}">
								<li class="btn_nav_depth_02"><a href="${pageContext.request.contextPath}/front/stat/wholeSalerShipping.do">항운노조</a></li>
								</c:if>
								<c:if test="${aucType01 eq true or aucType02 eq true or aucType03 eq true or aucType04 eq true or aucType05 eq true}">
								<li class="btn_nav_depth_02"><a href="${pageContext.request.contextPath}/front/stat/pumAucPriceList.do">공통</a></li>
								</c:if>
							</ul>
						</li>
						<c:if test="${dept01Nm ne '마이페이지'}">
							<li class="btn_nav_depth_01">
								<a href="#!">${dept02Nm}<span class="nav_arrow"><img src="${pageContext.request.contextPath}/img/nav_arrow.png" alt=""></span></a>
								<ul class="nav_depth_box_02">
									<c:if test="${dept01Nm eq '출하단체'}">
										<li class="btn_nav_depth_02"><a href="${pageContext.request.contextPath}/front/stat/marketOrgInfo.do">등록정보</a></li>
										<li class="btn_nav_depth_02"><a href="${pageContext.request.contextPath}/front/stat/marketOrgBelongShapr.do">소속농가현황</a></li>
										<li class="btn_nav_depth_02"><a href="${pageContext.request.contextPath}/front/stat/marketSaleCalcTot.do">판매정산내역</a></li>
										<li class="btn_nav_depth_02"><a href="${pageContext.request.contextPath}/front/stat/marketActualResult.do">소속농가실적확인서</a></li>
										<li class="btn_nav_depth_02"><a href="${pageContext.request.contextPath}/front/stat/markShprBounty.do">공동출하장려금</a></li>
									</c:if>
									<c:if test="${dept01Nm eq '출하농가'}">
										<li class="btn_nav_depth_02"><a href="${pageContext.request.contextPath}/front/stat/marketShprInfo.do">등록정보</a></li>
										<li class="btn_nav_depth_02"><a href="${pageContext.request.contextPath}/front/stat/shprSaleCalcTot.do">판매정산내역</a></li>
										<li class="btn_nav_depth_02"><a href="${pageContext.request.contextPath}/front/stat/shprActualResult.do">실적확인서</a></li>
									</c:if>
									<c:if test="${dept01Nm eq '중도매인'}">
										<li class="btn_nav_depth_02"><a href="${pageContext.request.contextPath}/front/stat/whslDealCalc.do">거래내역(낙찰서)</a></li>
										<li class="btn_nav_depth_02"><a href="${pageContext.request.contextPath}/front/stat/whslDealBlotter.do">거래원장</a></li>
										<%-- 2021-11-17 이현승 --%>
										<c:if test="${floMokCdCheck eq true}">
											<li class="btn_nav_depth_02"><a href="${pageContext.request.contextPath}/front/stat/sangJangCntList.do">상장물량조회(절화)</a></li>
										</c:if>
									</c:if>
									<c:if test="${dept01Nm eq '운송업체'}">
										<li class="btn_nav_depth_02"><a href="${pageContext.request.contextPath}/front/stat/transDateParticulars.do">일자별운송내역</a></li>
										<li class="btn_nav_depth_02"><a href="${pageContext.request.contextPath}/front/stat/transShprPartiTot.do">출하자별운송내역</a></li>
									</c:if>
									<c:if test="${dept01Nm eq '항운노조'}">
										<li class="btn_nav_depth_02"><a href="${pageContext.request.contextPath}/front/stat/wholeSalerShipping.do">중도매인 배송비내역</a></li>
										<li class="btn_nav_depth_02"><a href="${pageContext.request.contextPath}/front/stat/boxLoading.do">상자형별 하역비내역</a></li>
									</c:if>
									<c:if test="${dept01Nm eq '공통'}">
										<li class="btn_nav_depth_02"><a href="${pageContext.request.contextPath}/front/stat/pumAucPriceList.do">품목/품종별 경매시세</a></li>
									</c:if>
								</ul>
							</li>
						</c:if>
					</ul>
				</div> 
			</div>
			<!-- 네비게이션(E) -->
				

	
    
    

