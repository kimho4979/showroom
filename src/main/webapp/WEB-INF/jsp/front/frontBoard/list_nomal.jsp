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
 		$("#tableCaption").html($("#menuNm").val() + " 게시판으로 번호, 제목, 작성자, 파일, 조회수, 작성일 정보를 제공합니다.");
 	})
</script>

	<!-- sub내용(S) -->
			<div class="sub_conts_in">

				<!-- 검색창(S) -->
			<form id ="searchForm" method="get" action="${pageContext.request.contextPath}/front/board/bbsList.do">
				<div class="search_box_02">
					<div class="sel_type_02">
						<select id="searchSel" name="searchCondition" title="검색조건">
							<option value="subject" <c:if test="${paramMap.searchCondition eq 'subject'}">selected="selected"</c:if>>제목</option>
							<option value="content" <c:if test="${paramMap.searchCondition eq 'content'}">selected="selected"</c:if>>내용</option>
						</select>
						<label for="searchSel"></label>
					</div>
					<div class="ip_type_02">
						<input type="text" id="searchIp" placeholder="검색어를 입력하세요" name="searchKeyword" value="<c:out value='${pageNav.searchKeyword}'/>" title="검색어 입력">
						<label for="searchIp"></label>
						<input type="hidden" name="boardId" value="${boardId}">
						<input type="hidden" name="menuId" value="${menuId}">
						<input type="hidden" name="pageIndex" value="${pageIndex}">
						<input type="hidden" name="pageSize" value="${pageSize}">
						<input type="hidden" name="pageUnit" value="${pageUnit}">
					</div>
					
					<button id ="serchBtn" class="btn_search" type ="submit">검색</button>
					<!--  <a href="#!" class="btn_search">검색</a>-->
				</div>
			</form>
			

				<!-- 검색창(E) -->

				<!-- 타이틀(S) -->
				<div class="title_box mt40">
					<div class="fl">
						<h4 class="sub_tit_06">전체 <span class="total_num">${recordsTotal}</span> 
						<span class="bar">|</span>Page <span class="bold">${pageIndex}</span>/
						<fmt:parseNumber var="pages" value="${(recordsTotal/pageUnit)}" integerOnly="true" />
						${pages+1} 
						</h4>
						<%-- <fmt:formatNumber var = "pages" IntegerOnly="true" value="${pages+(1-(pages%1))%1}"/> --%>    
					</div>
				</div>
				<!-- 타이틀(E) -->

				<!-- 공지사항테이블(S) -->
				<div class="table_type_05 board mt10">
					<table>
						<caption id="tableCaption">번호, 제목, 작성자, 파일, 조회수, 작성일 정보를 제공하는 게시판입니다.</caption>
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>파일</th>
								<th>조회수</th>
								<th>작성일</th>
							</tr>
						</thead>
						<tbody>
						<c:choose>
							<c:when test="${not empty resultList}">
								<c:forEach items="${resultList}" var="result" varStatus="status">
						
								<tr onclick="fn_view('${paramMap.menuId}','${paramMap.boardId}','${result.articleId}');">
									<td class="tc"><p class="txt_03 color_b_02">${recordsTotal-((pageIndex-1) * pageUnit + status.index)}</p></td>
									<c:set var="num" value="${num-1}"></c:set> 
									<td class="tl"><a href="#!" class="txt_03">${result.subject}</a></td>
									<td class="tc"><p class="txt_03">${result.writerNm}</p></td>
									<td class="tc"> 							
	 									<c:choose>
											<c:when test="${result.uploadCnt ne '0'}">
												<img src="${pageContext.request.contextPath}/img/ico_file_02.png" alt="첨부파일"/>
											</c:when>
											<c:otherwise>						
											</c:otherwise>
										</c:choose> 
									</td>
									<td class="tc"><p class="txt_03">${result.hits}</p></td>
									<td class="tc"><p class="txt_03">${result.registDttm}</p></td>
								</tr>
								</c:forEach>
								</c:when>
								<c:otherwise>
									<tr>
										<td style="text-align:center "colspan="6" >데이터가 없습니다.<td>
									</tr>
								</c:otherwise>
							</c:choose>
							</tbody>
					</table>
				</div>
				<!-- 공지사항테이블(E) -->

				<!-- 페이지박스(S) -->
	
			<div class="paging_box mt30">
				<ui:pagination paginationInfo="${paginationInfo}" type="default" jsFunction="${paginationQueryString}"/>
			</div> 
			<!--  
		 		<div class="paging_box mt30">
					<a href="#!" class="btn_page_prev">이전</a>
					<a href="#!" class="btn_page on">1</a>
					<a href="#!" class="btn_page">2</a>
					<a href="#!" class="btn_page">3</a>
					<a href="#!" class="btn_page">4</a>
					<a href="#!" class="btn_page">5</a>
					<a href="#!" class="btn_page_next">다음</a>
				</div> -->
				<!-- 페이지박스(E) -->

			</div>
			<!-- sub내용(E) -->





           