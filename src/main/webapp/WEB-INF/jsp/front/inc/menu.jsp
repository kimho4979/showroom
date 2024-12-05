<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="header">
	<div class="header_in">
		<a href="${pageContext.request.contextPath}" class="h_logo"><img src="${pageContext.request.contextPath}/img/h_logo.png" alt="헤더로고"></a>
		<div class="menu_box">
			<ul>
				<c:forEach items="${menuList}" var="menuVO2" varStatus="status">
	            <c:if test="${menuVO2.lv eq 2}">
	            <c:choose>
	            	<c:when test="${menuVO2.menuNm eq '점포소개' }">
	            		<c:set var="icon" value="${pageContext.request.contextPath}/img/ico_menu_01.png"/>
	            	</c:when>
	            	<c:when test="${menuVO2.menuNm eq '알림마당' }">
	            		<c:set var="icon" value="${pageContext.request.contextPath}/img/ico_menu_02.png"/>
	            	</c:when>
	            	<c:when test="${menuVO2.menuNm eq '화훼유통정보' }">
	            		<c:set var="icon" value="${pageContext.request.contextPath}/img/ico_menu_03.png"/>
	            	</c:when>
	            	<c:when test="${menuVO2.menuNm eq '서식자료실' }">
	            		<c:set var="icon" value="${pageContext.request.contextPath}/img/ico_menu_04.png"/>
	            	</c:when>
	            	<c:when test="${menuVO2.menuNm eq '센터소개' }">
	            		<c:set var="icon" value="${pageContext.request.contextPath}/img/ico_menu_05.png"/>
	            	</c:when>
	            	<c:when test="${menuVO2.menuNm eq '꽃문화플랫폼' }">
	            		<c:set var="icon" value="${pageContext.request.contextPath}/img/ico_menu_05-flower.png"/>
	            	</c:when>
	            	<c:otherwise>
	            		<c:set var="icon" value="${pageContext.request.contextPath}/img/ico_menu_02.png"/>
	            	</c:otherwise>
	            </c:choose>
				<li>
					<c:if test="${menuVO2.menuGb ne 'MENU_LINK'}">
                   	 	<c:set var="menuUrl" value="${pageContext.request.contextPath}${menuVO2.linkUrl}?${menuVO2.linkParam}&menuId=${menuVO2.menuId}"/>
                  	</c:if>
                  	<c:if test="${menuVO2.menuGb eq 'MENU_LINK'}">
                   	 	<c:set var="menuUrl" value="${pageContext.request.contextPath}${menuVO2.linkedUrl}&menuId=${menuVO2.linkMenuId}"/>
                  	</c:if>
                  	
					<a href="${menuUrl}" class="btn_m m1">${menuVO2.menuNm}</a>
					<!-- 점포소개(S)-->
					<div class="gnb_box gb_1">
						<div class="border_gradient"></div>
						<div class="gb_in">
							<div class="ico_box">
								<span class="gb_img">
									<img src="${icon}" alt="${menuVO2.menuNm}이미지">
								</span>
								<p class="menu_title">${menuVO2.menuNm}</p>
							</div>
							<div class="menu_list">
								<ul>
									<li>
										<c:forEach items="${menuList}" var="menuVO3" >
                                    		<c:if test="${menuVO3.lv eq 3 and menuVO2.menuId eq menuVO3.parntsMenuId}">
		                                        <c:if test="${menuVO3.menuGb ne 'MENU_LINK'}">
		                                        	<c:if test="${!fn:contains(menuVO3.linkUrl,'http')}">
			                                        	<a href="${pageContext.request.contextPath}${menuVO3.linkUrl}?${menuVO3.linkParam}&menuId=${menuVO3.menuId}" >
			                                        		${menuVO3.menuNm}
			                                        	</a>
		                                        	</c:if>
		                                        	<c:if test="${fn:contains(menuVO3.linkUrl,'http')}">
		                                        		<a href="${menuVO3.linkUrl}?${menuVO3.linkParam}" target="${menuVO3.linkTarget}" >
			                                        		${menuVO3.menuNm}
			                                        	</a>
		                                        	</c:if>
	                                        	</c:if>
	                                        	<c:if test="${menuVO3.menuGb eq 'MENU_LINK'}">
		                                        	<a href="${pageContext.request.contextPath}${menuVO3.linkedUrl}&menuId=${menuVO3.menuId}" >
		                                        		${menuVO3.menuNm}
		                                        	</a>
	                                        	</c:if>
                                        	</c:if>
                                        </c:forEach>
									</li>
								</ul>
							</div>
						</div>
					</div>
					<!-- 점포소개(E)-->
				</li>
				</c:if>
	            </c:forEach>
	            <!-- 
				<li>
					<a href="${pageContext.request.contextPath}/front/content/view.do?contentId=2" class="btn_m m2">알림마당</a>
					
					<div class="gnb_box gb_2">
						<div class="border_gradient"></div>
						<div class="gb_in">
							<div class="ico_box">
								<span class="gb_img">
									<img src="${pageContext.request.contextPath}/img/ico_menu_02.png" alt="알림마당이미지">
								</span>
								<p class="menu_title">알림마당</p>
							</div>
							<div class="menu_list">
								<ul>
									<li>
										<a href="${pageContext.request.contextPath}/front/content/view.do?contentId=2">공지사항</a>
										<a href="${pageContext.request.contextPath}/front/content/view.do?contentId=2">공판장소식</a>
									</li>
									<li>
										<a href="${pageContext.request.contextPath}/front/content/view.do?contentId=2">ARS 이용안내</a>
										<a href="${pageContext.request.contextPath}/front/content/view.do?contentId=2">FAX 이용안내</a>
										<a href="${pageContext.request.contextPath}/front/content/view.do?contentId=2">홈페이지 이용안내</a>
									</li>
								</ul>
							</div>
						</div>
					</div>
					
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/front/content/view.do?contentId=2" class="btn_m m3">화훼유통정보</a>
					
					<div class="gnb_box gb_3">
						<div class="border_gradient"></div>
						<div class="gb_in">
							<div class="ico_box">
								<span class="gb_img">
									<img src="${pageContext.request.contextPath}/img/ico_menu_03.png" alt="화훼유통정보이미지">
								</span>
								<p class="menu_title">화훼유통정보</p>
							</div>
							<div class="menu_list">
								<ul>
									<li>
										<a href="${pageContext.request.contextPath}/front/content/view.do?contentId=2">경매시세</a>
									</li>
								</ul>
							</div>
						</div>
					</div>
					
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/front/content/view.do?contentId=2" class="btn_m m4">서식자료실</a>
					
					<div class="gnb_box gb_4">
						<div class="border_gradient"></div>
						<div class="gb_in">
							<div class="ico_box">
								<span class="gb_img">
									<img src="${pageContext.request.contextPath}/img/ico_menu_04.png" alt="서식자료실이미지">
								</span>
								<p class="menu_title">서식자료실</p>
							</div>
							<div class="menu_list">
								<ul>
									<li>
										<a href="${pageContext.request.contextPath}/front/content/view.do?contentId=2">경매</a>
										<a href="${pageContext.request.contextPath}/front/content/view.do?contentId=2">임대</a>
										<a href="${pageContext.request.contextPath}/front/content/view.do?contentId=2">전시실</a>
									</li>
									<li>
										<a href="${pageContext.request.contextPath}/front/content/view.do?contentId=2">기타</a>
									</li>
								</ul>
							</div>
						</div>
					</div>
					
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/front/content/view.do?contentId=1" class="btn_m m5">센터소개</a>
					
					<div class="gnb_box gb_5">
						<div class="border_gradient"></div>
						<div class="gb_in">
							<div class="ico_box">
								<span class="gb_img">
									<img src="${pageContext.request.contextPath}/img/ico_menu_05.png" alt="센터소개이미지">
								</span>
								<p class="menu_title">센터소개</p>
							</div>
							<div class="menu_list">
								<ul>
									<li>
										<a href="${pageContext.request.contextPath}/front/content/view.do?contentId=1">센터장 인사말</a>
										<a href="${pageContext.request.contextPath}/front/content/view.do?contentId=3">공판장 소개</a>
										<a href="${pageContext.request.contextPath}/front/content/view.do?contentId=2">주요사업</a>
									</li>
									<li>
										<a href="${pageContext.request.contextPath}/front/content/view.do?contentId=2">조직 및 직원</a>
										<a href="${pageContext.request.contextPath}/front/content/view.do?contentId=2">경매일 안내</a>
										<a href="${pageContext.request.contextPath}/front/content/view.do?contentId=2">오시는 길</a>
									</li>
								</ul>
							</div>
						</div>
					</div>
					
				</li> -->
				<c:if test="${aucType07 ne null or aucType03 ne null or aucType01 ne null}">
					<li>
						<c:if test="${aucType07 ne true}">
							<c:if test="${aucType03 ne true}">
								<c:set var="onUrl" value="${pageContext.request.contextPath}/front/fixAuc/fixSellList.do"/>
							</c:if>
							<c:if test="${aucType01 ne true}">
								<c:set var="onUrl" value="${pageContext.request.contextPath}/front/fixAuc/fixBuyList.do"/>
							</c:if>
						</c:if>
						<c:if test="${aucType07 eq true}">
							<c:set var="onUrl" value="${pageContext.request.contextPath}/front/fixAuc/fixAdmList.do"/>
						</c:if>
						<a href="${onUrl}" class="btn_m m6 on">온라인매매</a>
						<!-- 온라인매매(S)-->
						<div class="gnb_box gb_6">
							<div class="border_gradient"></div>
							<div class="gb_in">
								<div class="ico_box">
									<span class="gb_img">
										<img src="${pageContext.request.contextPath}/img/ico_menu_06.png" alt="온라인매매이미지">
									</span>
									<p class="menu_title">온라인매매</p>
								</div>
								<div class="menu_list">
									<ul>
										<c:if test="${aucType07 ne true}">
										<li>
											<c:if test="${aucType03 ne true}">
											<a href="${pageContext.request.contextPath}/front/fixAuc/fixSellList.do">판매</a>
											</c:if>
											<c:if test="${aucType01 ne true}">
											<a href="${pageContext.request.contextPath}/front/fixAuc/fixBuyList.do">구매</a>
											<c:if test="${not empty nFloLoginVO }">
													<a href="${pageContext.request.contextPath}/front/fixNAuc/fixBuySmallList.do">구매 ( 입찰 )</a>
											</c:if>
												<c:if test="${not empty cFloLoginVO}">
													<a href="${pageContext.request.contextPath}/front/fixCAuc/fixBuySmallList.do">구매 ( 입찰 - 관엽 )</a>
											</c:if>
												<c:if test="${not empty yFloLoginVO}">
													<a href="${pageContext.request.contextPath}/front/fixYAuc/fixBuySmallList.do">구매 ( 입찰 - 난 )</a>
											</c:if>
											</c:if>
											<a href="${pageContext.request.contextPath}/front/fixAuc/reqList.do">요청</a>
										</li>
										</c:if>
										<c:if test="${aucType07 eq true}">
										<li>
											<a href="${pageContext.request.contextPath}/front/fixAuc/fixAdmList.do">판매관리</a>
											<a href="${pageContext.request.contextPath}/front/fixAuc/reqAdmList.do">요청관리</a>
											<a href="${pageContext.request.contextPath}/front/fixAucTime/time.do">시간관리</a>
											<a href="${pageContext.request.contextPath}/front/fixAuc/tranAdmList.do">정산관리</a>
											<c:if test="${not empty nFloLoginVO or not empty cFloLoginVO or not empty yFloLoginVO}">
												<a href="${pageContext.request.contextPath}/front/fixAuc/fixSmallAdmList.do">입찰관리</a>
											</c:if>
										</li>
										</c:if>
									</ul>
								</div>
							</div>
						</div>
						<!-- 온라인매매(E)-->
					</li>
				</c:if>
			</ul>
		</div>
		<div class="mypage">
			<c:if test="${atLoginVO eq null and snsLoginVO eq null}">
				<a href="${pageContext.request.contextPath}/front/login.do" class="btn_p login"><span><img src="${pageContext.request.contextPath}/img/icon_login.png" alt="로그인아이콘"></span>로그인</a>
				<a href="https://member.at.or.kr/customer/m100002/memberForm.action" class="btn_p sign" target="_blank"><span><img src="${pageContext.request.contextPath}/img/icon_sign.png" alt="회원가입아이콘"></span>회원가입</a>
			</c:if>
			<c:if test="${atLoginVO ne null or snsLoginVO ne null}">
				<a href="${pageContext.request.contextPath}/front/logout.do" class="btn_p login"><span><img src="${pageContext.request.contextPath}/img/icon_login.png" alt="로그아웃아이콘"></span>로그아웃</a>
				<a href="${pageContext.request.contextPath}/front/mypage.do" class="btn_p sign"><span><img src="${pageContext.request.contextPath}/img/icon_sign.png" alt="마이페이지"></span>마이페이지</a>
			</c:if>
			
		</div>
		<a href="#!" class="btn_lnb"><img src="${pageContext.request.contextPath}/img/btn_lnb_mobile.png" alt="모바일lnb버튼"></a>
	</div>
</div>
<div class="border_gradient"></div>

