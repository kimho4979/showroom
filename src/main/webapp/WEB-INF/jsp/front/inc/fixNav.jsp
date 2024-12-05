<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<title>${title} - 양재꽃시장</title>
				
				<!-- 20200720 네비게이션 수정(S) -->
				<div class="nav_box">
					<div class="nav_in">
						<a href="${pageContext.request.contextPath}/" class="btn_home"><img src="${pageContext.request.contextPath}/img/btn_home.png" alt="홈"></a>
						<ul class="nav_depth_box">
							<li class="btn_nav_depth_01">
								<a href="#!">온라인매매<span class="nav_arrow"><img src="${pageContext.request.contextPath}/img/nav_arrow.png" alt=""></span></a>
								<ul class="nav_depth_box_02">
									<c:forEach items="${menuList}" var="menuVO2" varStatus="status">
										<c:if test="${menuVO2.lv eq 2}">
											<c:if test="${menuVO2.menuGb ne 'MENU_LINK'}">
						                   	 	<c:set var="menuUrl" value="${pageContext.request.contextPath}${menuVO2.linkUrl}?${menuVO2.linkParam}&menuId=${menuVO2.menuId}"/>
						                  	</c:if>
						                  	<c:if test="${menuVO2.menuGb eq 'MENU_LINK'}">
						                   	 	<c:set var="menuUrl" value="${pageContext.request.contextPath}${menuVO2.linkedUrl}&menuId=${menuVO2.linkMenuId}"/>
						                  	</c:if>
											<li class="btn_nav_depth_02"><a href="${menuUrl}">${menuVO2.menuNm}</a></li>
										</c:if>
									</c:forEach>
									<c:if test="${aucType07 ne true}">
										<c:if test="${aucType03 eq true}">
											<li class="btn_nav_depth_02"><a href="${pageContext.request.contextPath}/front/fixAuc/fixBuyList.do">온라인매매</a></li>
										</c:if>
										<c:if test="${aucType01 eq true}">
											<li class="btn_nav_depth_02"><a href="${pageContext.request.contextPath}/front/fixAuc/fixSellList.do">온라인매매</a></li>
										</c:if>
									</c:if>
									<c:if test="${aucType07 eq true}">
										<li class="btn_nav_depth_02"><a href="${pageContext.request.contextPath}/front/fixAuc/fixAdmList.do">온라인매매</a></li>
									</c:if>
								</ul>
							</li>
							<li class="btn_nav_depth_01">
								<a href="#!">${title}<span class="nav_arrow"><img src="${pageContext.request.contextPath}/img/nav_arrow.png" alt=""></span></a>
								<ul class="nav_depth_box_02">
									<c:if test="${aucType07 ne true}">
										<c:if test="${aucType01 eq true}">
											<li class="btn_nav_depth_02">
												<a href="${pageContext.request.contextPath}/front/fixAuc/fixSellList.do">판매</a>
											</li>
										</c:if>
										<c:if test="${aucType03 eq true}">
											<li class="btn_nav_depth_02"><a href="${pageContext.request.contextPath}/front/fixAuc/fixBuyList.do">구매</a></li>
										</c:if>
										<li class="btn_nav_depth_02"><a href="${pageContext.request.contextPath}/front/fixAuc/reqList.do">요청</a></li>
										
									</c:if>
									<c:if test="${aucType07 eq true}">
										<li class="btn_nav_depth_02"><a href="${pageContext.request.contextPath}/front/fixAuc/fixAdmList.do">판매관리</a></li>
										<li class="btn_nav_depth_02"><a href="${pageContext.request.contextPath}/front/fixAuc/reqAdmList.do">요청관리</a></li>
										<li class="btn_nav_depth_02"><a href="${pageContext.request.contextPath}/front/fixAucTime/time.do">시간관리</a></li>
										<li class="btn_nav_depth_02"><a href="${pageContext.request.contextPath}/front/fixAuc/tranAdmList.do">정산관리</a></li>
									</c:if>
								</ul>
							</li>
							
							<c:set var="requestURI" value="${requestScope['javax.servlet.forward.request_uri']}" />
							<c:if test="${aucType07 ne true}">
								<c:if test="${aucType01 eq true and fn:contains(requestURI, 'fixSell')}">
									<li class="btn_nav_depth_01">
										<a href="#!">
											<c:if test="${fn:contains(requestURI, 'List.do')}">
												조회
											</c:if>
											<c:if test="${fn:contains(requestURI, 'Reg.do')}">
												등록
											</c:if>
										<span class="nav_arrow"><img src="${pageContext.request.contextPath}/img/nav_arrow.png" alt=""></span></a>
										<ul class="nav_depth_box_02">
											<c:if test="${fn:contains(requestURI, 'fixNAuc')}">
												<c:set var="listURL" value="${pageContext.request.contextPath}/front/fixNAuc/fixSellList.do"/>
												<c:set var="regURL" value="${pageContext.request.contextPath}/front/fixNAuc/fixSellReg.do"/>
											</c:if>
											<c:if test="${fn:contains(requestURI, 'fixYAuc')}">
												<c:set var="listURL" value="${pageContext.request.contextPath}/front/fixYAuc/fixSellList.do"/>
												<c:set var="regURL" value="${pageContext.request.contextPath}/front/fixYAuc/fixSellReg.do"/>
											</c:if>
											<c:if test="${fn:contains(requestURI, 'fixCAuc')}">
												<c:set var="listURL" value="${pageContext.request.contextPath}/front/fixCAuc/fixSellList.do"/>
												<c:set var="regURL" value="${pageContext.request.contextPath}/front/fixCAuc/fixSellReg.do"/>
											</c:if>
											<li class="btn_nav_depth_02"><a href="${listURL}">조회</a></li>
											<li class="btn_nav_depth_02"><a href="${regURL}">등록</a></li>
										</ul>
									</li>
								</c:if>
								<c:if test="${aucType03 eq true and fn:contains(requestURI, 'reqBuy')}">
									<li class="btn_nav_depth_01">
										<a href="#!">
											<c:if test="${fn:contains(requestURI, 'List.do')}">
												조회
											</c:if>
											<c:if test="${fn:contains(requestURI, 'Reg.do')}">
												등록
											</c:if>
										<span class="nav_arrow"><img src="${pageContext.request.contextPath}/img/nav_arrow.png" alt=""></span></a>
										<ul class="nav_depth_box_02">
											<c:if test="${fn:contains(requestURI, 'fixNAuc')}">
												<c:set var="listURL" value="${pageContext.request.contextPath}/front/fixNAuc/reqBuyList.do"/>
												<c:set var="regURL" value="${pageContext.request.contextPath}/front/fixNAuc/reqBuyReg.do"/>
											</c:if>
											<c:if test="${fn:contains(requestURI, 'fixYAuc')}">
												<c:set var="listURL" value="${pageContext.request.contextPath}/front/fixYAuc/reqBuyList.do"/>
												<c:set var="regURL" value="${pageContext.request.contextPath}/front/fixYAuc/reqBuyReg.do"/>
											</c:if>
											<c:if test="${fn:contains(requestURI, 'fixCAuc')}">
												<c:set var="listURL" value="${pageContext.request.contextPath}/front/fixCAuc/reqBuyList.do"/>
												<c:set var="regURL" value="${pageContext.request.contextPath}/front/fixCAuc/reqBuyReg.do"/>
											</c:if>
											<li class="btn_nav_depth_02"><a href="${listURL}">조회</a></li>
											<li class="btn_nav_depth_02"><a href="${regURL}">등록</a></li>
										</ul>
									</li>
								</c:if>
							</c:if>
						</ul>
					</div>
				</div>
				<!-- 20200720 네비게이션 수정(E) -->

	
    
    

