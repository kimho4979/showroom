<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<title>
 
<c:if test="${paramMap.tyGroupCode eq '0'}">
	본관입주점포(2층)
</c:if>
<c:if test="${paramMap.tyGroupCode eq '1'}">
	분화온실점포(가동)
</c:if>
<c:if test="${paramMap.tyGroupCode eq '2'}">
	분화온실점포(나동)
</c:if>
<c:if test="${paramMap.tyGroupCode eq '3'}">
	생화꽃도매시장점포(1층)
</c:if>
<c:if test="${paramMap.tyGroupCode eq '4'}">
	생화꽃도매시장점포(2층)
</c:if>
<c:if test="${paramMap.tyGroupCode eq '5'}">
	기타점포
</c:if>
<c:if test="${paramMap.tyGroupCode eq '6'}">
	화환점포
</c:if>
<c:if test="${paramMap.tyGroupCode eq '7'}">
	본관입주점포(지하1층)
</c:if>
<c:if test="${paramMap.tyGroupCode eq '8'}">
	전시실소개
</c:if>
 - 양재꽃시장
</title>

<input type="hidden" value="<c:out value="${paramMap.tyGroupCode}"/>" id="tyGroupCode"/>

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
							<a href="#!" class="btn_choice">생화꽃 도매시장점포(1층)</a>
							<ul>
								<li><a href="#!">전체점포</a></li>
								<li class="active"><a href="#!">생화꽃 도매시장점포(1층)</a></li>
								<li><a href="#!">생화꽃 도매시장점포(2층)</a></li>
								<li><a href="#!">분화온실점포(가동)</a></li>
								<li><a href="#!">분화온실점포(나동)</a></li>
								<li><a href="#!">화환점포</a></li>
								<li><a href="#!">본관입주점포(지하1층)</a></li>
								<li><a href="#!">본관입주점포(2층)</a></li>
								
							</ul>
						</li>
					</ul>-->
				</div>
				<!-- 점포선택(E) 모바일 -->

				<!-- 타이틀(S) -->
				<div class="title_box mt30">
					<div class="fl">
						<h4 class="sub_tit_04">점포검색</h4>
					</div>
				</div>
				<!-- 타이틀(E) -->

				<!-- 검색창(S) -->
				<form id="frm" name="searchFrm" method="post" action="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=<c:out value="${paramMap.tyGroupCode}"/>&menuId=<c:out value="${paramMap.menuId}"/>" >
				<div class="search_box_02 mt10">
					<div class="sel_type_02">
						<select id="searchCondition" name="searchCondition" title="검색조건">
							<option value="compName" <c:if test="${paramMap.searchCondition eq 'compName'}">selected="selected"</c:if>>점포명</option>
							<option value="ownerName" <c:if test="${paramMap.searchCondition eq 'ownerName'}">selected="selected"</c:if>>대표자명</option>
						</select>
						<label for="searchCondition"></label>
					</div>
					<div class="ip_type_02">
						<input type="text" id="searchKeyword" name="searchKeyword" placeholder="검색어를 입력하세요" title="검색어 입력" value="<c:out value="${paramMap.searchKeyword}"/>">
						<label for="searchKeyword"></label>
					</div>
					<a href="javascript:document.searchFrm.submit();" class="btn_search" title="검색">검색</a>
				</div>
				</form>
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
					<jsp:include page="/WEB-INF/jsp/front/market/areaMap.jsp"/>
				</div>
				<!-- 배치도(E) -->
				
				<!-- 타이틀(S) -->
				<div class="title_box mt30">
					<div class="fl">
						<h4 class="sub_tit_04">입주 점포</h4>
					</div>
					<!-- 
					<div class="fr">
						<h4 class="txt_unit">정보</h4>
					</div> -->
				</div>
				<!-- 타이틀(E) -->

				<!-- 입주점포(S) -->
				<div class="in_market mt10">
				<!-- 이미지있는것 상단 -->
					<c:forEach items="${resultList}" var="result" varStatus="status">
						<c:if test="${result.img1 ne null and fn:trim(result.img1) ne ''}">
							<c:set var="marketViewUrl" value="${pageContext.request.contextPath}/front/market/view.do?tyCode=${result.tyCode}&roCode=${result.roCode}&menuId=${paramMap.menuId}&tyGroupCode=${paramMap.tyGroupCode}"/>
							<a href="<c:out value="${marketViewUrl}"/>">
								<div class="in_market_info">
									<div class="in_m_info">
										<p class="txt_hash">
											<c:if test="${result.rcHashTag eq null}">
											#해시태그 미등록
											</c:if>
											<c:if test="${result.rcHashTag ne null}">
											${result.rcHashTag}
											</c:if>
										</p>
										<p class="txt_m_name">${result.rcCompName}</p>
										<p class="txt_place">
											<c:if test="${paramMap.tyGroupCode eq '0'}">
											본관입주점포(2층)
											</c:if>
											<c:if test="${paramMap.tyGroupCode eq '1'}">
											분화온실점포(가동)
											</c:if>
											<c:if test="${paramMap.tyGroupCode eq '2'}">
											분화온실점포(나동)
											</c:if>
											<c:if test="${paramMap.tyGroupCode eq '3'}">
											생화꽃도매시장점포(1층)
											</c:if>
											<c:if test="${paramMap.tyGroupCode eq '4'}">
											생화꽃도매시장점포(2층)
											</c:if>
											<c:if test="${paramMap.tyGroupCode eq '5'}">
											기타점포
											</c:if>
											<c:if test="${paramMap.tyGroupCode eq '6'}">
											화환점포
											</c:if>
											<c:if test="${paramMap.tyGroupCode eq '7'}">
											본관입주점포(지하1층)
											</c:if>
											<span class="p_num">${result.tyCode}-${result.roCode}호</span>
										</p>
										<p class="txt_call"><span class="ico_face">${result.rcName}</span><span class="ico_phone">${result.rcTel}</span></p>
									</div>
									<span class="in_m_img">
										<c:if test="${result.img1 ne null}">
										<!-- <img src="${pageContext.request.contextPath}/uploads/market/${result.thumbStreFileNm}" alt="${result.rcCompName}">-->
										<img src="${result.img1}" alt="${result.rcCompName}">
										</c:if>
									</span>
								</div>
							</a>
						</c:if>
					</c:forEach>
					<!-- 이미지 없는것 하단 -->
					<c:forEach items="${resultList}" var="result" varStatus="status">
						<c:if test="${result.img1 eq null or fn:trim(result.img1) eq ''}">
							<c:set var="marketViewUrl" value="${pageContext.request.contextPath}/front/market/view.do?tyCode=${result.tyCode}&roCode=${result.roCode}&menuId=${paramMap.menuId}&tyGroupCode=${paramMap.tyGroupCode}"/>
							<a href="<c:out value="${marketViewUrl}"/>">
								<div class="in_market_info">
									<div class="in_m_info">
										<p class="txt_hash">
											<c:if test="${result.rcHashTag eq null}">
											#해시태그 미등록
											</c:if>
											<c:if test="${result.rcHashTag ne null}">
											${result.rcHashTag}
											</c:if>
										</p>
										<p class="txt_m_name">${result.rcCompName}</p>
										<p class="txt_place">
											<c:if test="${paramMap.tyGroupCode eq '0'}">
											본관입주점포(2층)
											</c:if>
											<c:if test="${paramMap.tyGroupCode eq '1'}">
											분화온실점포(가동)
											</c:if>
											<c:if test="${paramMap.tyGroupCode eq '2'}">
											분화온실점포(나동)
											</c:if>
											<c:if test="${paramMap.tyGroupCode eq '3'}">
											생화꽃도매시장점포(1층)
											</c:if>
											<c:if test="${paramMap.tyGroupCode eq '4'}">
											생화꽃도매시장점포(2층)
											</c:if>
											<c:if test="${paramMap.tyGroupCode eq '5'}">
											기타점포
											</c:if>
											<c:if test="${paramMap.tyGroupCode eq '6'}">
											화환점포
											</c:if>
											<c:if test="${paramMap.tyGroupCode eq '7'}">
											본관입주점포(지하1층)
											</c:if>
											<span class="p_num">${result.tyCode}-${result.roCode}호</span>
										</p>
										<p class="txt_call"><span class="ico_face">${result.rcName}</span><span class="ico_phone">${result.rcTel}</span></p>
									</div>
								</div>
							</a>
						</c:if>
					</c:forEach>
					<c:if test="${fn:length(resultList) eq 0 }">
					검색된 점포가 없습니다.
					</c:if>
				</div>
				<!-- 입주점포(E) -->

				<!-- 담당정보(S) 
				<div class="table_flex mt100">
					<ul>
						<li>
							<span class="t_th">업무담당부서</span>
							<span class="t_td">시장지원부</span>
						</li>
						<li>
							<span class="t_th">업무담당자</span>
							<span class="t_td">오동환</span>
						</li>
						<li>
							<span class="t_th">문의전화</span>
							<span class="t_td">02-570-1812</span>
						</li>
					</ul>
				</div>-->
	<!-- 담당정보(E) -->

</div>
<!-- sub내용(E) -->

<div class="dim-layer pop_type_01" id="layer01">
	<div class="dimBg"></div>
	<div class="pop-layer popbox" tabindex="1">
		<div class="pop-container">
			<a href="#!" class="btn-layerClose pop_btn_close close_B" title="점포 조회 팝업 닫기 버튼"></a>
			<div class="pop_info">
				<div class="pop_pic">
					<img id="marketImg"
						src="${pageContext.request.contextPath}/img/noimage.png"
						alt="팝업사진">
				</div>
				<div class="pop_intro">
					<h2 class="pop_tit_01" id="marketName">우성꽃식물원</h2>
					<p class="pop_txt_hash" id="marketHashText">#동양란 전문점(혜란,춘란)</p>
					<p class="pop_txt_01" id="marketContent">고객님과의 신뢰를 위해 약속을 항상
						우선으로 생각합니다. 합리적인 가격과 고객 맞춤서비스로 항상 최선을 다하겠습니다.</p>
					<div class="pop_call">
						<span class="pop_ico_face" id="marketOwner">이강옥</span> <span
							class="pop_ico_phone" id="marketTel">02-579-2027</span>
					</div>

							<div class="pop_info_table" style="padding: 0 25px;">
								<table>
									<caption>점포위치, 영업시간, 휴무 정보를 알려주는 점포정보 테이블입니다.</caption>
									<tbody>
										<tr>
											<th><p class="txt_j">위 치</p></th>
											<td><p class="txt_01" id="marketPosition">분화온실점포(가동) | 12호</p></td>
										</tr>
										<tr>
											<th><p class="txt_j">영 업 시 간</p></th>
											<td><p class="txt_01" id="marketSaleTime">07:00 ~ 19:00</p></td>
										</tr>
										<tr>
											<th><p class="txt_j">휴 무</p></th>
											<td><p class="txt_01" id="marketCloDay">연중무휴</p></td> 
										</tr>
										<!-- 
										<tr>
											<th><p class="txt_j">전 국 택 배</p></th>
											<td><p class="txt_01" id="marketDeliText">(전화문의)택배, 퀵</p></td>
										</tr> -->
									</tbody>
								</table>
							</div>

							<div class="pop_info_btn_box">
								<a href="#!" class="btn_link call" id="marketCall">전화걸기</a>
								<a href="#!" class="btn_link add" id="marketAdd">주소복사</a>
								<a href="#!" class="btn_link share" id="marketShare">공유하기</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		

		