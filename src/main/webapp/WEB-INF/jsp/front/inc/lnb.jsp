<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>

		<div id="lnb">
			<div class="dim" id="mobileDim"></div>
			<a href="#!" class="m_btn_close" id="mLnbBtnClose"><img src="${pageContext.request.contextPath}/img/m_lnb_close.png" alt="모바일lnb닫기버튼"></a>
			<div class="m_mypage">
				<c:if test="${atLoginVO eq null and snsLoginVO eq null}">
					<a href="${pageContext.request.contextPath}/front/login.do" class="m_btn login"><span><img src="${pageContext.request.contextPath}/img/m_icon_login.png" alt="모바일로그인아이콘"></span>로그인</a>
					<a href="https://member.at.or.kr/customer/m100002/memberForm.action" class="m_btn sign"><span><img src="${pageContext.request.contextPath}/img/m_icon_sign.png" alt="모바일회원가입아이콘"></span>회원가입</a>
				</c:if>
				<c:if test="${atLoginVO ne null or snsLoginVO ne null}">
					<a href="${pageContext.request.contextPath}/front/logout.do" class="m_btn login"><span><img src="${pageContext.request.contextPath}/img/m_icon_login.png" alt="모바일로그아웃아이콘"></span>로그아웃</a>
					<a href="${pageContext.request.contextPath}/front/mypage.do" class="m_btn sign"><span><img src="${pageContext.request.contextPath}/img/m_icon_sign.png" alt="모바일마이페이지아이콘"></span>마이페이지</a>
				</c:if>
			</div>
			<ul class="lnb_box">
			  <c:forEach items="${menuList}" var="menuVO2" varStatus="status">
			  <c:if test="${menuVO2.lv eq 2}">
			  
				  <li><a href="#none">${menuVO2.menuNm}</a>
					<ul>
						<c:forEach items="${menuList}" var="menuVO3" >
	               		<c:if test="${menuVO3.lv eq 3 and menuVO2.menuId eq menuVO3.parntsMenuId}">
		               		<li>
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
		                    </li>
	                   	</c:if>
	                   </c:forEach>
					</ul>
				  </li>
			  </c:if>
			  </c:forEach>
			  <c:if test="${aucType07 ne null or aucType03 ne null or aucType01 ne null}">
			  <li><a href="#none">온라인매매</a>
				<ul>
				  <c:if test="${aucType07 ne true}">
				  	  <c:if test="${aucType03 ne true}">
					  <li><a href="${pageContext.request.contextPath}/front/fixAuc/fixSellList.do">판매</a></li>
					  </c:if>
					  <c:if test="${aucType01 ne true}">
					  <li><a href="${pageContext.request.contextPath}/front/fixAuc/fixBuyList.do">구매</a></li>
					  <c:if test="${not empty nFloLoginVO }">
						<li><a href="${pageContext.request.contextPath}/front/fixNAuc/fixBuySmallList.do">구매 ( 입찰 )</a></li>
					  </c:if>
					  <c:if test="${not empty cFloLoginVO}">
						<li><a href="${pageContext.request.contextPath}/front/fixCAuc/fixBuySmallList.do">구매 ( 입찰 - 관엽 )</a></li>
					  </c:if>
					  <c:if test="${not empty yFloLoginVO}">
						<li><a href="${pageContext.request.contextPath}/front/fixYAuc/fixBuySmallList.do">구매 ( 입찰 - 난 )</a></li>
					  </c:if>
					  </c:if>
					  <li><a href="${pageContext.request.contextPath}/front/fixAuc/reqList.do">요청</a></li>
				  </c:if>
				  <c:if test="${aucType07 eq true}">
				  		<li><a href="${pageContext.request.contextPath}/front/fixAuc/fixAdmList.do">판매관리</a></li>
				  		<li><a href="${pageContext.request.contextPath}/front/fixAuc/reqAdmList.do">요청관리</a></li>
				  		<li><a href="${pageContext.request.contextPath}/front/fixAucTime/time.do">시간관리</a></li>
				  		<li><a href="${pageContext.request.contextPath}/front/fixAuc/tranAdmList.do">정산관리</a></li>
						<c:if test="${not empty nFloLoginVO }">
						<li><a href="${pageContext.request.contextPath}/front/fixNAuc/fixSmallAdmList.do">입찰관리</a></li>
					  </c:if>
					  <c:if test="${not empty cFloLoginVO}">
						<li><a href="${pageContext.request.contextPath}/front/fixCAuc/fixSmallAdmList.do">입찰관리(관엽)</a></li>
					  </c:if>
					  <c:if test="${not empty yFloLoginVO}">
						<li><a href="${pageContext.request.contextPath}/front/fixYAuc/fixSmallAdmList.do">입찰관리(난)</a></li>
					  </c:if>
				  </c:if>
				</ul>
			  </li>
			  </c:if>
			</ul>
		  </div>
		  
<script type="text/javascript">
 $("#mobileDim").click(function(e){
   $('#lnb').attr('style','display: none');
 });


</script>

