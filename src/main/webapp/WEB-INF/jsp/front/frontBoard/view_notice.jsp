<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- sub내용(S) -->
			<div class="sub_conts_in">

				<!-- 게시판(S) -->
				<div class="table_type_05">
					<table>
						<caption>${result.subject} 관련 정보를 제공하는 게시글입니다.</caption>
						<colgroup>
							<col width="20%">
							<col width="80%">
						</colgroup>
						<thead>
							<tr>
								<th class="tl" colspan="2" id="noticeSubject">${result.subject}</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="tl pd0" colspan="2">
									<ul class="notice_info">
										<li>
											<div>
												<p class="tit_notice">작성자</p>
												<span class="n_data">${result.writerNm}</span>
											</div>

										</li>
										<li>
											<div>
												<p class="tit_notice">작성일</p>
												<span class="n_data">${result.registDttm}</span>
											</div>
											<div>
												<p class="tit_notice">조회수</p>
												<span class="n_data">${result.hits}</span>
											</div>
										</li>
									</ul>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div class="notice_story" id="noticeContent">
										<p class="txt_01">${result.content}</p>
									</div>
								</td>
							</tr>
							<tr>
								<th>첨부파일</th>
								<td>
									<c:if test="${result.uploadCnt eq '0'}">
										<a href="#!" class="txt_notice">첨부파일이 없습니다.</a>
									</c:if>
									<c:if test="${result.uploadCnt ne '0'}">
									<c:forEach items="${fileList}" var="file">
									<c:set var="fileExtsn" value="${fn:toLowerCase(file.fileExtsn)}"/>
										<c:choose>
											<c:when test ="${fileExtsn eq 'docx' || fileExtsn eq 'doc'}">
												<c:set var="ext" value="doc"/>
											</c:when>
											<c:when test ="${fileExtsn eq 'xlsx' || fileExtsn eq 'xls'}">
												<c:set var="ext" value="xls"/>
											</c:when>
											<c:when test ="${fileExtsn eq 'hwp'}">
												<c:set var="ext" value="hwp"/>
											</c:when>
											<c:when test ="${fileExtsn eq 'pdf'}">
												<c:set var="ext" value="pdf"/>
											</c:when>
											<c:when test ="${fileExtsn eq 'pptx' || fileExtsn eq 'ppt'}">
												<c:set var="ext" value="ppt"/>
											</c:when>
											<c:when test ="${fileExtsn eq 'jpg' || fileExtsn eq 'jpeg'}">
												<c:set var="ext" value="jpg"/>
											</c:when>
											<c:when test ="${fileExtsn eq 'png'}">
												<c:set var="ext" value="png"/>
											</c:when>
											<c:when test ="${fileExtsn eq 'gif'}">
												<c:set var="ext" value="gif"/>
											</c:when>		
											<c:otherwise>
												<c:set var="ext" value="etc"/>
											</c:otherwise>	
										</c:choose>
										<a href="${pageContext.request.contextPath}/front/board/fileDown.do?boardId=${file.boardId}&articleId=${file.articleId}&fileSn=${file.fileSn}" class="ico_file ${ext}" alt="첨부파일">
												<c:out value="${file.orignlFileNm}"/><br>
										</a>
									</c:forEach>
									
									</c:if>
									
								</td>
							</tr>
							<tr>
								<th>다음글<span class="n_ico next"></span></th>
								<td>
									<c:if test="${result.nextArticleId eq null}">
										<a href="#!" class="txt_notice">다음글 정보가 없습니다.</a>
									</c:if>
									<c:if test="${result.nextArticleId ne null}">
										<a href="${pageContext.request.contextPath}/front/board/bbsView.do?boardId=${paramMap.boardId}&menuId=${paramMap.menuId}&articleId=${result.nextArticleId}" class="txt_notice">${result.nextSubject}</a>
									</c:if>
								</td>
							</tr>
							<tr>
								<th>이전글<span class="n_ico prev"></span></th>
								<td>
									<c:if test="${result.prevArticleId eq null}">
										<a href="#!" class="txt_notice">이전글 정보가 없습니다.</a>
									</c:if>
									<c:if test="${result.prevArticleId ne null}">
										<a href="${pageContext.request.contextPath}/front/board/bbsView.do?boardId=${paramMap.boardId}&menuId=${paramMap.menuId}&articleId=${result.prevArticleId}" class="txt_notice">${result.prevSubject}</a>
									</c:if>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- 게시판(E) -->

				<!-- 버튼박스(S) -->
				<div class="btn_box mt30">
					<a href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=${paramMap.boardId}&menuId=${paramMap.menuId}" class="btn_type_01 list">목록</a>
				</div>
				<!-- 버튼박스(E) -->

			</div>
			<!-- sub내용(E) -->
			
			
<script type="text/javascript">
function fn_fileDown(boardId, articleId, fileSn){
	
	$.ajax({
		data:{
				boardId: boardId,
				articleId: articleId,
				fileSn: fileSn
	        },
	    type : "POST",
	    url : "${pageContext.request.contextPath}/front/board/fileDown.json",
	    success : function(data){
	        console.log(data);
	    }
	});
	
}

$(function(){
	
	$("#noticeContent img").prop('alt', $("#noticeSubject").html() + " 이미지");
	$("#noticeContent iframe").prop('alt', $("#noticeSubject").html());
})
	
</script>