<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>

<title>${menuVO.menuNm} - 양재꽃시장</title>
				
				<!-- 20200720 네비게이션 수정(S) -->
				<div class="nav_box">
					<div class="nav_in">
						<a href="${pageContext.request.contextPath}/" class="btn_home"><img src="${pageContext.request.contextPath}/img/btn_home.png" alt="홈"></a>
						<ul class="nav_depth_box">
							<li class="btn_nav_depth_01">
								<a href="#!">${menuVO.parntsMenuNm}<span class="nav_arrow"><img src="${pageContext.request.contextPath}/img/nav_arrow.png" alt=""></span></a>
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
								<a href="#!">${menuVO.menuNm}<span class="nav_arrow"><img src="${pageContext.request.contextPath}/img/nav_arrow.png" alt=""></span></a>
								<ul class="nav_depth_box_02">
									<c:forEach items="${menuList}" var="menuVO3" >
										<c:if test="${menuVO3.lv eq 3 and menuVO.parntsMenuId eq menuVO3.parntsMenuId}">
	                                        <c:if test="${!fn:contains(menuVO3.linkUrl,'http') && (menuVO3.menuId ne '37' && menuVO3.menuId ne '40')}">
	                                        	<li class="btn_nav_depth_02"><a href="${pageContext.request.contextPath}${menuVO3.linkUrl}?${menuVO3.linkParam}&menuId=${menuVO3.menuId}" >
	                                        		${menuVO3.menuNm}
	                                        	</a></li>
                                        	</c:if>
                                        	<c:if test="${fn:contains(menuVO3.linkUrl,'http')}">
                                        		<li class="btn_nav_depth_02"><a href="${menuVO3.linkUrl}?${menuVO3.linkParam}" target="${menuVO3.linkTarget}" >
	                                        		${menuVO3.menuNm}
	                                        	</a></li>
                                        	</c:if>
                                        	<c:if test="${menuVO3.menuGb eq 'MENU_LINK'}">
	                                        	<li class="btn_nav_depth_02"><input type="hidden" value="${menuVO3}"><a href="${pageContext.request.contextPath}${menuVO3.linkedUrl}&menuId=${menuVO3.menuId}">${menuVO3.menuNm}</a></li>
                                        	</c:if>
                                       	</c:if>
									</c:forEach>
								</ul>
							</li>
						</ul>
					</div>
				</div>
				<!-- 20200720 네비게이션 수정(E) -->

	
    
    

