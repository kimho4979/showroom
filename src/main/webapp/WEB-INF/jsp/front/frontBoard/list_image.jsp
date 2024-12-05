<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script type="text/javascript">

function fn_view(menuId, boardId, resultList){
	var articleId = $("#articleId").val();
	window.location.href = "${pageContext.request.contextPath}/front/board/bbsView.do?boardId="+boardId+"&menuId="+menuId+"&articleId="+resultList;
	//window.location.href = "${pageContext.request.contextPath}/front/board/bbsView.do?boardId="+boardId+"&menuId="+menuId"&articleId="+articleId;
}

var searchForm = $("#searchForm");
$("#searchBtn").on("click",function(e){
	if(!searchForm.find("option:selected").val()){
		alert('검색조건을 선택하세요');
		return false;
	}
	if(!serarchForm.find("input[name='searchKeyword']").val()){
		alert('검색어를 입력하세요');
		return false;
	}
	searchForm.find("input[name='pageIndex']".val(1));
	e.preventDefault();
	
	searchForm.submit();
});

$(function(){
	
	var curPageNum = $(".paging_box .btn_page.on strong").html();
	
	$(".paging_box .btn_page.on").prop("title", "현재 페이지는 " + curPageNum + "페이지 입니다.");
})
</script>

<!-- sub내용(S) -->
<c:choose>
<%-- 양재 플라워 페스타 게시판 --%>
<c:when test="${paramMap.boardId >= 32 && paramMap.boardId <= 56}">
	<div class="sub_conts_in_03 top<c:if test="${paramMap.boardId <= 36}"> f-2020</c:if>">
		<div class="sub-03-in">
			<img class="bg-web" src="${pageContext.request.contextPath}/img/s-03-banner<c:if test="${paramMap.boardId <= 36}">-2020</c:if>.png" alt="양재 플라워 페스타 이미지">
			<img class="bg-mobile" src="${pageContext.request.contextPath}/img/s-03-banner-mobile<c:if test="${paramMap.boardId <= 36}">-2020</c:if>.png" alt="양재 플라워 페스타 이미지">
			<select class="sel-festa" id="selFesta" onchange="if(this.value) location.href=(this.value);" title="양재 플라워 페스타 보러가기">
				<option value="${pageContext.request.contextPath}/front/content/view.do?contentId=23&menuId=40">2020년 페스타 보러가기</option>
				<option value="${pageContext.request.contextPath}/front/content/view.do?contentId=24&menuId=40"<c:if test="${paramMap.boardId >= 38}"> selected</c:if>>2021년 페스타 보러가기</option>
				<option value="${pageContext.request.contextPath}/front/content/view.do?contentId=33&menuId=40" >2022년 페스타 보러가기</option>
			</select>
			<label for="selFesta"></label>
		</div>
	</div>
	<div class="sub_conts_in_03 mid<c:if test="${paramMap.boardId <= 36}"> f-2020</c:if>">
		<div class="sub-03-in">
			<ul>
				<c:choose>
					<c:when test="${paramMap.boardId <= 36}">
						<li><a title="행사 안내" href="${pageContext.request.contextPath}/front/content/view.do?contentId=23&menuId=40">행사 안내</a></li>
						<li<c:if test="${paramMap.boardId eq '32'}"> class="on"</c:if>><a title="온라인 플라워 클래스<c:if test="${paramMap.boardId eq '32'}"> (선택됨)</c:if>" href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=32&menuId=40">온라인 플라워 클래스</a></li>
						<li<c:if test="${paramMap.boardId eq '34'}"> class="on"</c:if>><a title="꽃과 함께하는 문화생활<c:if test="${paramMap.boardId eq '34'}"> (선택됨)</c:if>" href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=34&menuId=40">꽃과 함께하는 문화생활</a></li>
						<li<c:if test="${paramMap.boardId eq '36'}"> class="on"</c:if>><a title="공모전 투표하기<c:if test="${paramMap.boardId eq '36'}"> (선택됨)</c:if>" href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=36&menuId=40">공모전 투표하기</a></li>
					</c:when>
					<c:otherwise>
						<li><a title="행사 안내" href="${pageContext.request.contextPath}/front/content/view.do?contentId=24&menuId=40">행사 안내</a></li>
						<li<c:if test="${paramMap.boardId >= 38 && paramMap.boardId <= 44}"> class="on"</c:if>><a title="나愛 꽃 전시<c:if test="${paramMap.boardId >= 38 && paramMap.boardId <= 44}"> (선택됨)</c:if>" href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=38&menuId=40">나愛 꽃 전시</a></li>
						<li<c:if test="${paramMap.boardId >= 46 && paramMap.boardId <= 52}"> class="on"</c:if>><a title="나愛 꽃 놀이<c:if test="${paramMap.boardId >= 46 && paramMap.boardId <= 52}"> (선택됨)</c:if>" href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=46&menuId=40">나愛 꽃 놀이</a></li>
						<li<c:if test="${paramMap.boardId >= 54 && paramMap.boardId <= 56}"> class="on"</c:if>><a title="나愛 꽃 창업<c:if test="${paramMap.boardId >= 54 && paramMap.boardId <= 56}"> (선택됨)</c:if>" href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=54&menuId=40">나愛 꽃 창업</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
	<div class="sub_conts_in_03 bottom<c:if test="${paramMap.boardId <= 36}"> f-2020</c:if>">
		<div class="sub-03-in">
			<div class="title_box">
				<div class="fl">
					<h4 class="sub03_tit_02<c:if test="${paramMap.boardId >= 38}"> dib</c:if>">
						<c:if test="${paramMap.boardId eq '32'}">온라인 플라워 클래스</c:if>
						<c:if test="${paramMap.boardId eq '34'}">꽃과 함께하는 문화생활</c:if>
						<c:if test="${paramMap.boardId eq '36'}">공모전 투표하기</c:if>
						<c:if test="${paramMap.boardId >= 38 && paramMap.boardId <= 44}">나愛 꽃 전시</c:if>
						<c:if test="${paramMap.boardId >= 46 && paramMap.boardId <= 52}">나愛 꽃 놀이</c:if>
						<c:if test="${paramMap.boardId >= 54}">나愛 꽃 창업</c:if>
					</h4>
					<c:if test="${paramMap.boardId >= 38}">
						<div class="sub03_tit_box">
							<c:choose>
								<c:when test="${paramMap.boardId >= 38 && paramMap.boardId <= 44}">
									<a href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=38&menuId=40" class="sub03_tit_03
										<c:if test="${paramMap.boardId eq '38'}"> on" title="한국 전통 꽃꽃이 (선택됨)</c:if>
										<c:if test="${paramMap.boardId ne '38'}">" title="한국 전통 꽃꽃이</c:if>
										">한국 전통 꽃꽂이
									</a>
									<a href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=40&menuId=40" class="sub03_tit_03
										<c:if test="${paramMap.boardId eq '40'}"> on" title="꽃청춘 서포터즈 (선택됨)</c:if>
										<c:if test="${paramMap.boardId ne '40'}">" title="꽃청춘 서포터즈</c:if>
										">꽃청춘 서포터즈
									</a>
									<a href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=42&menuId=40" class="sub03_tit_03
										<c:if test="${paramMap.boardId eq '42'}"> on" title="문화 속 꽃 이야기 (선택됨)</c:if>
										<c:if test="${paramMap.boardId ne '42'}">" title="문화 속 꽃 이야기</c:if>
										">문화 속 꽃 이야기
									</a>
									<a href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=44&menuId=40" class="sub03_tit_03
										<c:if test="${paramMap.boardId eq '44'}"> on" title="화훼 정책 정보 (선택됨)</c:if>
										<c:if test="${paramMap.boardId ne '44'}">" title="화훼 정책 정보</c:if>
										">화훼 정책 정보
									</a>
								</c:when>
								<c:when test="${paramMap.boardId >= 46 && paramMap.boardId <= 52}">
									<a href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=46&menuId=40" class="sub03_tit_03
										<c:if test="${paramMap.boardId eq '46'}"> on" title="양재 꽃시장 투어 (선택됨)</c:if>
										<c:if test="${paramMap.boardId ne '46'}">" title="양재 꽃시장 투어</c:if>
										">양재 꽃시장 투어
									</a>
									<a href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=48&menuId=40" class="sub03_tit_03
										<c:if test="${paramMap.boardId eq '48'}"> on" title="나愛 꽃밭 만들기 (선택됨)</c:if>
										<c:if test="${paramMap.boardId ne '48'}">" title="나愛 꽃밭 만들기</c:if>
										">나愛 꽃밭 만들기
									</a>
									<a href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=50&menuId=40" class="sub03_tit_03
										<c:if test="${paramMap.boardId eq '50'}"> on" title="꽃을 든 사람들 (선택됨)</c:if>
										<c:if test="${paramMap.boardId ne '50'}">" title="꽃을 든 사람들</c:if>
										">꽃을 든 사람들
									</a>
									<a href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=52&menuId=40" class="sub03_tit_03
										<c:if test="${paramMap.boardId eq '52'}"> on" title="참여 이벤트 (선택됨)</c:if>
										<c:if test="${paramMap.boardId ne '52'}">" title="참여 이벤트</c:if>
										">참여 이벤트
									</a>
								</c:when>
								<c:when test="${paramMap.boardId >= 54}">
									<a href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=54&menuId=40" class="sub03_tit_03
										<c:if test="${paramMap.boardId eq '54'}"> on" title="꽃집 토크 콘서트 (선택됨)</c:if>
										<c:if test="${paramMap.boardId ne '54'}">" title="꽃집 토크 콘서트</c:if>
										">꽃집 토크 콘서트
									</a>
									<a href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=56&menuId=40" class="sub03_tit_03
										<c:if test="${paramMap.boardId eq '56'}"> on" title="실전 창업 특강 (선택됨)</c:if>
										<c:if test="${paramMap.boardId ne '56'}">" title="실전 창업 특강</c:if>
										">실전 창업 특강
									</a>
								</c:when>
							</c:choose>
						</div>
					</c:if>
				</div>
			</div>
			
			<div class="sub03-cont">
				<div class="s03-card-box">
					<ul>
						<c:choose>
							<c:when test="${not empty resultList}">
								<c:forEach items="${resultList}" var="result" varStatus="status">
								<li>
									<div class="s03-card">
										<c:choose>
											<c:when test="${not empty result.data01}">
												<a href="${result.data01}" target="_BLANK" title="${result.subject} (새 창으로 이동)">
											</c:when>
											<c:otherwise>
												<a href="${pageContext.request.contextPath}/front/board/bbsView.do?boardId=${paramMap.boardId}&menuId=${paramMap.menuId}&articleId=${result.articleId}">
											</c:otherwise>
										</c:choose>
											<span class="card-img">
												<c:choose>
													<c:when test="${not empty result.listThumbFile}">
														<img src="${pageContext.request.contextPath}/uploads/board/${paramMap.boardId}/${result.listThumbFile}" alt="${result.subject} 썸네일" onerror="this.src='${pageContext.request.contextPath}/img/noimage236x240.png'">
													</c:when>
													<c:otherwise>
														<!-- 기본 이미지 -->
														<img src="${pageContext.request.contextPath}/img/noimage236x240.png" alt="">
													</c:otherwise>
												</c:choose>
											</span>
											<div class="card-info">
												<div class="ci-text">
													<p>
														${result.subject}
													</p>
												</div>
												<span class="cia-txt left">${result.writerNm}</span>
												<span class="cia-txt right">
													<c:if test="${not empty result.data02}">
														${result.data02}
													</c:if>
												</span>
											</div>
										</a>
									</div>
								</li>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<div class="title_box tc">
									<h4 class="no-title blue">게시글이 존재하지 않습니다.</h4>
								</div>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>
	
			<div class="paging_box mt40 include-btn">
				<ui:pagination paginationInfo="${paginationInfo}" type="default" jsFunction="${paginationQueryString}"/>
			</div>
		</div>
	</div>
</c:when>
<%-- 기본 이미지 게시판 --%>
<c:otherwise>
	<div class="sub_conts_in_03 bgw">
	
		<div class="sub-03-in pdt70 pdb87">					
		
			<c:if test="${paramMap.boardId eq '28' || paramMap.boardId eq '30'}">
				<div class="title_box type_2">
					<div class="fl">
						<h4 class="sub_tit_02">
							<c:choose>
								<c:when test="${paramMap.boardId eq '28'}">
									꽃 정보
								</c:when>
								<c:when test="${paramMap.boardId eq '30'}">
									식물 정보
								</c:when>
							</c:choose>
						</h4>
					</div>
					<div class="fr"> 
						<form id ="searchForm" method="get" action="${pageContext.request.contextPath}/front/board/bbsList.do">
							<div class="tit-search-box">
								<input type="hidden" id="searchSel" name="searchCondition" value="subject">
								<input type="text" id="titSearch" name="searchKeyword" value="<c:out value='${pageNav.searchKeyword}'/>" placeholder="검색어를 입력하세요" title="검색어 입력">
								<label for="titSearch"></label>
								<input type="hidden" name="boardId" value="${boardId}">
								<input type="hidden" name="menuId" value="${menuId}">
								<input type="hidden" name="pageIndex" value="1">
								<input type="hidden" name="pageSize" value="${pageSize}">
								<input type="hidden" name="pageUnit" value="${pageUnit}">
								<button class="btn_search" id="serchBtn" type="submit">검색</button>
							</div>
						</form>
					</div>
				</div>
	
				<div class="img-banner mt10">
					<c:choose>
						<c:when test="${paramMap.boardId eq '28'}">
							<img src="${pageContext.request.contextPath}/img/banner-flower.png" alt="꽃 정보 이미지">
						</c:when>
						<c:when test="${paramMap.boardId eq '30'}">
							<img src="${pageContext.request.contextPath}/img/banner-plant.png" alt="식물 정보 이미지">
						</c:when>
					</c:choose>
				</div>
	
				<div class="sub_conts_in_03 mid menu2 mt20">
					<div class="sub-03-in">					
						<ul>
							<li<c:if test="${paramMap.boardId eq '28'}"> class="on"</c:if>><a<c:if test="${paramMap.boardId eq '28'}"> title="꽃 정보 (선택됨)"</c:if> href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=28&menuId=37">꽃 정보</a></li>
							<li<c:if test="${paramMap.boardId eq '30'}"> class="on"</c:if>><a<c:if test="${paramMap.boardId eq '30'}"> title="식물 정보 (선택됨)"</c:if> href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=30&menuId=37">식물 정보</a></li>
						</ul>
					</div>
				</div>
			</c:if>
			
			<div class="sub03-cont">
				<div class="s03-card-box">
					<ul>
						<c:choose>
							<c:when test="${not empty resultList}">
								<c:forEach items="${resultList}" var="result" varStatus="status">
								<li>
									<div class="s03-card">
										<c:choose>
											<c:when test="${not empty result.data01}">
												<a href="${result.data01}" target="_BLANK" title="${result.subject} (새 창으로 이동)">
											</c:when>
											<c:otherwise>
												<a href="${pageContext.request.contextPath}/front/board/bbsView.do?boardId=${paramMap.boardId}&menuId=${paramMap.menuId}&articleId=${result.articleId}">
											</c:otherwise>
										</c:choose>
											<span class="card-img">
												<c:choose>
													<c:when test="${not empty result.listThumbFile}">
														<img src="${pageContext.request.contextPath}/uploads/board/${paramMap.boardId}/${result.listThumbFile}" alt="${result.subject} 썸네일" onerror="this.src='${pageContext.request.contextPath}/img/noimage236x240.png'">
													</c:when>
													<c:otherwise>
														<!-- 기본 이미지 -->
														<img src="${pageContext.request.contextPath}/img/noimage236x240.png" alt="">
													</c:otherwise>
												</c:choose>
											</span>
											<div class="card-info">
												<div class="ci-text">
													<p>
														${result.subject}
													</p>
												</div>
												<span class="cia-txt left">${result.writerNm}</span>
												<span class="cia-txt right">
													<c:if test="${not empty result.data02}">
														${result.data02}
													</c:if>
												</span>
											</div>
										</a>
									</div>
								</li>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<div class="title_box tc">
									<h4 class="no-title blue">게시글이 존재하지 않습니다.</h4>
								</div>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>
	
			<div class="paging_box mt40 include-btn">
				<ui:pagination paginationInfo="${paginationInfo}" type="default" jsFunction="${paginationQueryString}"/>
			</div>
	
		</div>
	
	</div>
</c:otherwise>
</c:choose>
<!-- sub내용(E) -->