<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<style>
.statul li{border-top: 2px solid #ae1ee8;font-size:12px !important;}
</style>
<c:set var="requestURI" value="${requestScope['javax.servlet.forward.request_uri']}" />
<c:if test="${aucType02 eq true}">	
	<div class="title_box">
			<h4 class="sub_tit_04">출하단체 통계</h4>
	</div>	
	<div class="tab_url devide_4">
		<ul class="statul">
			<li <c:if test="${fn:contains(requestURI, 'marketOrgInfo.do')}">class="active"</c:if>><a href="${pageContext.request.contextPath}/front/stat/marketOrgInfo.do">등록정보</a></li>
			<li <c:if test="${fn:contains(requestURI, 'marketOrgBelongShapr.do')}">class="active"</c:if>><a href="${pageContext.request.contextPath}/front/stat/marketOrgBelongShapr.do">소속농가현황</a></li>
			<li <c:if test="${fn:contains(requestURI, 'marketSaleCalc')}">class="active"</c:if>><a href="${pageContext.request.contextPath}/front/stat/marketSaleCalcTot.do">판매정산내역</a></li>
		</ul>
		<ul class="statul mt10">
			<li <c:if test="${fn:contains(requestURI, 'marketActualResult.do')}">class="active"</c:if>><a href="${pageContext.request.contextPath}/front/stat/marketActualResult.do">소속농가실적확인서</a></li>
			<li <c:if test="${fn:contains(requestURI, 'markShprBounty.do')}">class="active"</c:if>><a href="${pageContext.request.contextPath}/front/stat/markShprBounty.do">공동출하장려금</a></li>
			<li <c:if test="${fn:contains(requestURI, 'pumAucPriceList.do')}">class="active"</c:if>><a href="${pageContext.request.contextPath}/front/stat/pumAucPriceList.do">품목품종별 경매시세</a></li>
		</ul>
	</div>
</c:if>
<c:if test="${aucType01 eq true}">	
	<div class="title_box mt30">
			<h4 class="sub_tit_04">출하농가 통계</h4>
	</div>	
	<div class="tab_url devide_4">
		<ul class="statul">
			<li <c:if test="${fn:contains(requestURI, 'marketShprInfo.do')}">class="active"</c:if>><a href="${pageContext.request.contextPath}/front/stat/marketShprInfo.do">등록정보</a></li>
			<li <c:if test="${fn:contains(requestURI, 'shprSaleCalc')}">class="active"</c:if>><a href="${pageContext.request.contextPath}/front/stat/shprSaleCalcTot.do">판매정산내역</a></li>
			<li <c:if test="${fn:contains(requestURI, 'shprActualResult.do')}">class="active"</c:if>><a href="${pageContext.request.contextPath}/front/stat/shprActualResult.do">실적확인서</a></li>
			<li <c:if test="${fn:contains(requestURI, 'pumAucPriceList.do')}">class="active"</c:if>><a href="${pageContext.request.contextPath}/front/stat/pumAucPriceList.do">품목품종별 경매시세</a></li>
		</ul>
	</div>
</c:if>
<c:if test="${aucType03 eq true}">	
	<div class="title_box mt30">
			<h4 class="sub_tit_04">중도매인 통계</h4>
	</div>	
	<div class="tab_url devide_4">
		<ul class="statul">
			<li <c:if test="${fn:contains(requestURI, 'whslDealCalc.do')}">class="active"</c:if>><a href="${pageContext.request.contextPath}/front/stat/whslDealCalc.do">거래내역(낙찰서)</a></li>
			<li <c:if test="${fn:contains(requestURI, 'whslDealBlotter.do')}">class="active"</c:if>><a href="${pageContext.request.contextPath}/front/stat/whslDealBlotter.do">거래원장</a></li>
			<li <c:if test="${fn:contains(requestURI, 'pumAucPriceList.do')}">class="active"</c:if>><a href="${pageContext.request.contextPath}/front/stat/pumAucPriceList.do">품목품종별 경매시세</a></li>
			<%-- 2021-11-17 이현승 --%>
			<c:set var="floMokCdCheck" value="${false}" />
			<c:forEach items="${floLoginList}" var="floLoginVO" varStatus="status" >
				<c:if test="${floLoginVO.aucType eq '03' and floLoginVO.floMokCd eq 'N'}">
					<c:if test="${floMokCdCheck eq false }">
					  <c:set var="floMokCdCheck" value="${true}" />
					</c:if>		
				</c:if>
			</c:forEach>
			<%-- 2021-11-17 이현승 --%>
			<c:if test="${floMokCdCheck eq true}">
				<li <c:if test="${fn:contains(requestURI, 'sangJangCntList.do')}">class="active"</c:if>><a href="${pageContext.request.contextPath}/front/stat/sangJangCntList.do">상장물량조회(절화)</a></li>
			</c:if>
		</ul>
	</div>
</c:if>
<c:if test="${aucType04 eq true}">	
	<div class="title_box mt30">
			<h4 class="sub_tit_04">운송업체 통계</h4>
	</div>	
	<div class="tab_url devide_4">
		<ul class="statul">
			<li <c:if test="${fn:contains(requestURI, 'transDateParticulars.do')}">class="active"</c:if>><a href="${pageContext.request.contextPath}/front/stat/transDateParticulars.do">일자별운송내역</a></li>
			<li <c:if test="${fn:contains(requestURI, 'transShprPartiTot.do') or fn:contains(requestURI, 'transShprPartiDetail.do')}">class="active"</c:if>><a href="${pageContext.request.contextPath}/front/stat/transShprPartiTot.do">출하자별운송내역[집계]</a></li>
			<li <c:if test="${fn:contains(requestURI, 'pumAucPriceList.do')}">class="active"</c:if>><a href="${pageContext.request.contextPath}/front/stat/pumAucPriceList.do">품목품종별 경매시세</a></li>
		</ul>
	</div>
</c:if>
<c:if test="${aucType05 eq true}">	
	<div class="title_box mt30">
			<h4 class="sub_tit_04">항운노조 통계</h4>
	</div>	
	<div class="tab_url devide_4">
		<ul class="statul">
			<li <c:if test="${fn:contains(requestURI, 'wholeSalerShipping.do')}">class="active"</c:if>><a href="${pageContext.request.contextPath}/front/stat/wholeSalerShipping.do">중도매인 배송비내역</a></li>
			<li <c:if test="${fn:contains(requestURI, 'boxLoading.do')}">class="active"</c:if>><a href="${pageContext.request.contextPath}/front/stat/boxLoading.do">상자형별 하역비내역</a></li>
			<li <c:if test="${fn:contains(requestURI, 'pumAucPriceList.do')}">class="active"</c:if>><a href="${pageContext.request.contextPath}/front/stat/pumAucPriceList.do">품목품종별 경매시세</a></li>
		</ul>
	</div>
</c:if>

<!-- MAFRA -->
<c:forEach var="floLoginMafra" items="${floLoginList}"> 
	<c:if test="${floLoginMafra.aucType eq '99'}">
		<c:set var="aucType99" value="true" />
	</c:if> 
</c:forEach>

<c:if test="${aucType07 eq true}">	
	<div class="title_box mt30">
		<h4 class="sub_tit_04">경매사 통계</h4>
	</div>	
	<div class="tab_url devide_4">
		<ul class="statul">
			<li <c:if test="${fn:contains(requestURI, 'shprSaleAllCalc')}">class="active"</c:if>><a href="${pageContext.request.contextPath}/front/stat/shprSaleAllCalcDetail.do">판매정산내역[전체]</a></li>
		</ul>
	</div>
</c:if>

<c:if test="${aucType99 eq true}">
	<div class="title_box mt30">
		<h4 class="sub_tit_04">통계</h4>
	</div>	
	<div class="tab_url devide_4">
		<ul class="statul">
			<li <c:if test="${fn:contains(requestURI, 'shprSaleAllCalc')}">class="active"</c:if>><a href="${pageContext.request.contextPath}/front/stat/mafraMagamMasterList.do">월별 온라인매매 현황</a></li>
		</ul>
	</div>
</c:if>



				