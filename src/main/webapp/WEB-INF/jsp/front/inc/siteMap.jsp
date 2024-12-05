<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
  <!-- sub-conts(S) -->
		<div class="sub_conts bgw">
			<!-- sub상단(S) -->
			<div class="sub_top">
				<h2 class="sub_title">·사이트맵·</h2>
				
				<!-- 네비게이션(S) -->
				<div class="nav_box">
					<div class="nav_in">
						<a href="${pageContext.request.contextPath}/" class="btn_home"><img src="${pageContext.request.contextPath}/img/btn_home.png" alt="홈"></a>
						
						<ul class="nav_depth_box">
							<li class="btn_nav_depth_01">
								<a href="#!">사이트맵<span class="nav_arrow"></a>
							</li>
						</ul>
					</div> 
				</div>
				<!-- 네비게이션(E) -->
			</div>
			<!-- sub상단(E) -->
			
			<!-- sub내용(S) -->
			<div class="sub_conts_in_02">
				
				<!-- 사이트맵(S) -->
				<div class="site_map">
					<ul>
						<!-- 점포소개(S)-->
						<c:forEach items="${menuList}" var="menuVO2" varStatus="status">
	            		<c:if test="${menuVO2.lv eq 2}">
							<li>
								<h4 class="tit_site">${menuVO2.menuNm}</h4>
								<c:forEach items="${menuList}" var="menuVO3" >
									<c:if test="${menuVO3.lv eq 3 and menuVO2.menuId eq menuVO3.parntsMenuId}">
										<c:if test="${menuVO3.menuGb ne 'MENU_LINK'}">
											<c:if test="${!fn:contains(menuVO3.linkUrl,'http')}">
	                                        	<a class="sub_tit_site" href="${pageContext.request.contextPath}${menuVO3.linkUrl}?${menuVO3.linkParam}&menuId=${menuVO3.menuId}" >
	                                        		${menuVO3.menuNm}
	                                        	</a>
                                        	</c:if>
                                        	<c:if test="${fn:contains(menuVO3.linkUrl,'http')}">
                                        		<a class="sub_tit_site" href="${menuVO3.linkUrl}?${menuVO3.linkParam}&menuId=${menuVO3.menuId}" target="${menuVO3.linkTarget}" >
	                                        		${menuVO3.menuNm}
	                                        	</a>
                                        	</c:if>
                                        	
                                       	</c:if>
                                       	<c:if test="${menuVO3.menuGb eq 'MENU_LINK'}">
                                        	<a class="sub_tit_site" href="${pageContext.request.contextPath}${menuVO3.linkedUrl}&menuId=${menuVO3.menuId}" >
                                        		${menuVO3.menuNm}
                                        	</a>
                                       	</c:if>	
									</c:if>
								</c:forEach>
							</li>
						</c:if>
	            		</c:forEach>
						<!-- 점포소개(E)-->
						<c:if test="${aucType07 ne null or aucType03 ne null or aucType01 ne null}">
							<li>
								<h4 class="tit_site">온라인매매</h4>
								<c:if test="${aucType07 ne true}">
									<c:if test="${aucType03 ne true}">
									<a class="sub_tit_site" href="${pageContext.request.contextPath}/front/fixAuc/fixSellList.do">판매</a>
									</c:if>
									<c:if test="${aucType01 ne true}">
									<a class="sub_tit_site" href="${pageContext.request.contextPath}/front/fixAuc/fixBuyList.do">구매</a>
									</c:if>
									<a class="sub_tit_site" href="${pageContext.request.contextPath}/front/fixAuc/reqList.do">요청</a>
								</c:if>
								<c:if test="${aucType07 eq true}">
									<a class="sub_tit_site" href="${pageContext.request.contextPath}/front/fixAuc/fixAdmList.do">판매관리</a>
									<a class="sub_tit_site" href="${pageContext.request.contextPath}/front/fixAuc/reqAdmList.do">요청관리</a>
									<a class="sub_tit_site" href="${pageContext.request.contextPath}/front/fixAucTime/time.do">시간관리</a>
									<a class="sub_tit_site" href="${pageContext.request.contextPath}/front/fixAuc/tranAdmList.do">정산관리</a>
								</c:if>
							</li>
						</c:if>
					</ul>
				</div>
				<!-- 사이트맵(E) -->

			</div>
			<!-- sub내용(E) -->
		</div>
		<!-- sub-conts(E) -->