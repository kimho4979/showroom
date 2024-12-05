<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- sub내용(S) -->
<c:choose>
<%-- 양재 플라워 페스타 게시판 --%>
<c:when test="${paramMap.boardId >= 32 && paramMap.boardId <= 56}">
	<div class="sub_conts_in_03 top<c:if test="${paramMap.boardId <= 36}"> f-2020</c:if>">
		<div class="sub-03-in">	
			<img class="bg-web" src="${pageContext.request.contextPath}/img/s-03-banner<c:if test="${paramMap.boardId <= 36}">-2020</c:if>.png" alt="양재플라워페스타 이미지">
			<img class="bg-mobile" src="${pageContext.request.contextPath}/img/s-03-banner-mobile<c:if test="${paramMap.boardId <= 36}">-2020</c:if>.png" alt="양재플라워페스타 이미지">
			<select class="sel-festa" id="selFesta" onchange="if(this.value) location.href=(this.value);" title="양재 플라워 페스타 보러가기 콤보박스">
				<option value="${pageContext.request.contextPath}/front/content/view.do?contentId=23&menuId=40">2020년 페스타 보러가기</option>
				<option value="${pageContext.request.contextPath}/front/content/view.do?contentId=24&menuId=40"<c:if test="${paramMap.boardId >= 38}"> selected</c:if>>2021년 페스타 보러가기</option>
			</select>	
			<label for="selFesta"></label>
		</div>
	</div>
	<div class="sub_conts_in_03 mid<c:if test="${paramMap.boardId <= 36}"> f-2020</c:if>">
		<div class="sub-03-in">
			<ul>
				<c:choose>
					<c:when test="${paramMap.boardId <= 36}">
						<li><a href="${pageContext.request.contextPath}/front/content/view.do?contentId=23&menuId=40">행사 안내</a></li>
						<li<c:if test="${paramMap.boardId eq '32'}"> class="on"</c:if>><a href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=32&menuId=40">온라인 플라워 클래스</a></li>
						<li<c:if test="${paramMap.boardId eq '34'}"> class="on"</c:if>><a href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=34&menuId=40">꽃과 함께하는 문화생활</a></li>
						<li<c:if test="${paramMap.boardId eq '36'}"> class="on"</c:if>><a href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=36&menuId=40">공모전 투표하기</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${pageContext.request.contextPath}/front/content/view.do?contentId=24&menuId=40">행사 안내</a></li>
						<li<c:if test="${paramMap.boardId >= 38 && paramMap.boardId <= 44}"> class="on"</c:if>><a href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=38&menuId=40">나愛 꽃 전시</a></li>
						<li<c:if test="${paramMap.boardId >= 46 && paramMap.boardId <= 52}"> class="on"</c:if>><a href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=46&menuId=40">나愛 꽃 놀이</a></li>
						<li<c:if test="${paramMap.boardId >= 54 && paramMap.boardId <= 56}"> class="on"</c:if>><a href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=54&menuId=40">나愛 꽃 창업</a></li>
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
									<a href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=38&menuId=40" class="sub03_tit_03<c:if test="${paramMap.boardId eq '38'}"> on</c:if>">한국 전통 꽃꽂이</a>
									<a href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=40&menuId=40" class="sub03_tit_03<c:if test="${paramMap.boardId eq '40'}"> on</c:if>">꽃청춘 서포터즈</a>
									<a href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=42&menuId=40" class="sub03_tit_03<c:if test="${paramMap.boardId eq '42'}"> on</c:if>">문화 속 꽃 이야기</a>
									<a href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=44&menuId=40" class="sub03_tit_03<c:if test="${paramMap.boardId eq '44'}"> on</c:if>">화훼 정책 정보</a>
								</c:when>
								<c:when test="${paramMap.boardId >= 46 && paramMap.boardId <= 52}">
									<a href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=46&menuId=40" class="sub03_tit_03<c:if test="${paramMap.boardId eq '46'}"> on</c:if>">양재 꽃시장 투어</a>
									<a href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=48&menuId=40" class="sub03_tit_03<c:if test="${paramMap.boardId eq '48'}"> on</c:if>">나愛 꽃밭 만들기</a>
									<a href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=50&menuId=40" class="sub03_tit_03<c:if test="${paramMap.boardId eq '50'}"> on</c:if>">꽃을 든 사람들</a>
									<a href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=52&menuId=40" class="sub03_tit_03<c:if test="${paramMap.boardId eq '52'}"> on</c:if>">참여 이벤트</a>
								</c:when>
								<c:when test="${paramMap.boardId >= 54}">
									<a href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=54&menuId=40" class="sub03_tit_03<c:if test="${paramMap.boardId eq '54'}"> on</c:if>">꽃집 토크 콘서트</a>
									<a href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=56&menuId=40" class="sub03_tit_03<c:if test="${paramMap.boardId eq '56'}"> on</c:if>">실전 창업 특강</a>
								</c:when>
							</c:choose>
						</div>
					</c:if>
				</div>
			</div>
			<div class="sub03-cont">
				<div class="detail-page<c:if test="${paramMap.boardId <= 36}"> blue</c:if>">
					<div class="dp-title">
						<h4 id="imgSubject">${result.subject}</h4>
					</div>
					<div class="dp-top">
						<p>작성자<span>${result.writerNm}</span></p>
						<c:if test="${not empty result.data02}">
								<p>작성일<span>${result.data02}</span></p>
						</c:if>
						<p>조회수<span>${result.hits}</span></p>
					</div>
					<div class="dp-mid" id="imgContent">
						<p>${result.content}</p>
					</div>
					<c:if test="${result.uploadCnt ne '0'}">
						<div class="dp-bottom">
							<h4 class="dpb-title">첨부파일</h4>
							<div class="dpb-file-box">
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
								<a href="#!" class="ico-file">심비디운.jpg(16.5KB)</a>
							</div>
						</div>
					</c:if>
					<div class="dp-bottom prev">
						<h4 class="dpb-title">이전글</h4>
						<div class="dpb-file-box">
							<c:if test="${result.prevArticleId eq null}">
								<a href="#!" class="btn-page">이전글 정보가 없습니다.</a>
							</c:if>
							<c:if test="${result.prevArticleId ne null}">
								<c:choose>
									<c:when test="${not empty result.prevData01}">
										<a href="${result.prevData01}" class="btn-page" target="_BLANK" title="${result.prevSubject} (새 창으로 이동)">${result.prevSubject}</a>
									</c:when>
									<c:otherwise>
										<a href="${pageContext.request.contextPath}/front/board/bbsView.do?boardId=${paramMap.boardId}&menuId=${paramMap.menuId}&articleId=${result.prevArticleId}" class="btn-page">${result.prevSubject}</a>
									</c:otherwise>
								</c:choose>
							</c:if>
						</div>
					</div>
					<div class="dp-bottom next">
						<h4 class="dpb-title">다음글</h4>
						<div class="dpb-file-box">
							<c:if test="${result.nextArticleId eq null}">
								<a href="#!" class="btn-page">다음글 정보가 없습니다.</a>
							</c:if>
							<c:if test="${result.nextArticleId ne null}">
								<c:choose>
									<c:when test="${not empty result.nextData01}">
										<a href="${result.nextData01}" class="btn-page" target="_BLANK" title="${result.nextSubject} (새 창으로 이동)">${result.nextSubject}</a>
									</c:when>
									<c:otherwise>
										<a href="${pageContext.request.contextPath}/front/board/bbsView.do?boardId=${paramMap.boardId}&menuId=${paramMap.menuId}&articleId=${result.nextArticleId}" class="btn-page">${result.nextSubject}</a>
									</c:otherwise>
								</c:choose>
							</c:if>
						</div>
					</div>
				</div>
				
				<div class="btn_box mt40">
					<a href="${pageContext.request.contextPath}/front/board/bbsList.do?boardId=${paramMap.boardId}&menuId=${paramMap.menuId}" class="btn_type_04">목록</a>
				</div>
			</div>
		</div>
	</div>
</c:when>
<%-- 기본 이미지 게시판 --%>
<c:otherwise>
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
						<th class="tl" colspan="2" id="imgSubject">${result.subject}</th>
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
										<c:choose>
											<c:when test="${not empty result.data02}">
												<p class="tit_notice">작성일</p>
												<span class="n_data">${result.data02}</span>
											</c:when>
											<c:otherwise>
												<c:if test="${paramMap.boardId ne '26' && paramMap.boardId ne '28' && paramMap.boardId ne '30'}">
													<p class="tit_notice">작성일</p>
													<span class="n_data">${result.registDttm}</span>
												</c:if>
											</c:otherwise>
										</c:choose>
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
							<div class="notice_story" id="imgContent">
								<p class="txt_01">${result.content}</p>
							</div>
						</td>
					</tr>
					<c:if test="${result.uploadCnt ne '0'}">
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
					</c:if>
					<tr>
						<th>다음글<span class="n_ico next"></span></th>
						<td>
							<c:if test="${result.nextArticleId eq null}">
								<a href="#!" class="txt_notice">다음글 정보가 없습니다.</a>
							</c:if>
							<c:if test="${result.nextArticleId ne null}">
								<c:choose>
									<c:when test="${not empty result.nextData01}">
										<a href="${result.nextData01}" class="txt_notice" target="_BLANK" title="${result.nextSubject} (새 창으로 이동)">${result.nextSubject}</a>
									</c:when>
									<c:otherwise>
										<a href="${pageContext.request.contextPath}/front/board/bbsView.do?boardId=${paramMap.boardId}&menuId=${paramMap.menuId}&articleId=${result.nextArticleId}" class="txt_notice">${result.nextSubject}</a>
									</c:otherwise>
								</c:choose>
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
								<c:choose>
									<c:when test="${not empty result.prevData01}">
										<a href="${result.prevData01}" class="txt_notice" target="_BLANK" title="${result.prevSubject} (새 창으로 이동)">${result.prevSubject}</a>
									</c:when>
									<c:otherwise>
										<a href="${pageContext.request.contextPath}/front/board/bbsView.do?boardId=${paramMap.boardId}&menuId=${paramMap.menuId}&articleId=${result.prevArticleId}" class="txt_notice">${result.prevSubject}</a>
									</c:otherwise>
								</c:choose>
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
</c:otherwise>
</c:choose>
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
	
	$("#imgContent img").prop('alt', $("#imgSubject").html() + " 이미지");
	$("#imgContent iframe").prop('title', $("#imgSubject").html());
})
	
</script>