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
<%-- 양재 플라워 페스타 게시판 --%>
	<div class="sub_conts_in_03 top">
		<div class="sub-03-in">
			<img class="bg-web" src="${pageContext.request.contextPath}/img/2022/s-03-banner.png" alt="양재 플라워 페스타 이미지">
			<img class="bg-mobile" src="${pageContext.request.contextPath}/img/2022/s-03-banner-mobile.png" alt="양재 플라워 페스타 이미지">
			<select class="sel-festa" id="selFesta" onchange="if(this.value) location.href=(this.value);" title="양재 플라워 페스타 보러가기">
				<option value="${pageContext.request.contextPath}/front/content/view.do?contentId=23&menuId=40">2020년 페스타 보러가기</option>
				<option value="${pageContext.request.contextPath}/front/content/view.do?contentId=24&menuId=40">2021년 페스타 보러가기</option>
				<option value="${pageContext.request.contextPath}/front/content/view.do?contentId=33&menuId=40" selected>2022년 페스타 보러가기</option>
				
			</select>
			<label for="selFesta"></label>
		</div>
	</div>
	<div class="sub_conts_in_03 mid">
		<div class="sub-03-in">
			<ul class="tabN5">
				
						<li <c:if test="${paramMap.boardId eq '68'}"> class="on" </c:if>  ><a    title="플로럴 프로그램" href="${pageContext.request.contextPath}/front/content/view.do?contentId=33&menuId=40">플로럴 프로그램</a></li>
						<li><a title="" href="${pageContext.request.contextPath}/front/content/view.do?contentId=26&menuId=40">가을 국화 꽃 축제</a></li>
						<li><a title="" href="${pageContext.request.contextPath}/front/content/view.do?contentId=27&menuId=40">화훼산업발전 심포지엄</a></li>
						<li><a title="" href="${pageContext.request.contextPath}/front/content/view.do?contentId=28&menuId=40">공모/전시</a></li>
						<li><a title="" href="${pageContext.request.contextPath}/front/content/view.do?contentId=31&menuId=40">이벤트/부대행사</a></li>
						
					
						
			</ul>
		</div>
	</div>
	<div class="sub_conts_in_03 bottom">
		<div class="sub-03-in">
			<div class="title_box">
				<div class="fl">
					<h4 class="sub03_tit_02 dib">
						플로럴 프로그램
					</h4>
					
						<div class="sub03_tit_box">
								<a href="${pageContext.request.contextPath}/front/content/view.do?contentId=33&menuId=40" class="sub03_tit_03">행사안내
								</a>
								<a href="${pageContext.request.contextPath}/front/content/view.do?contentId=32&menuId=40" class="sub03_tit_03">원데이 클래스
								</a>
								<a href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=68&menuId=40&year=2022" class="sub03_tit_03 on">토크 콘서트
								</a>
									
					
						</div>
					
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

<!-- sub내용(E) -->