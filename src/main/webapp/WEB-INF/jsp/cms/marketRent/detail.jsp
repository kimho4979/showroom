<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${menuName }</title>
    <%@include file="/WEB-INF/jsp/common/commonCss.jsp" %>
	<%@include file="/WEB-INF/jsp/common/commonJs.jsp" %>
	
	<!-- 홈페이지 css, js -->
	<link href="${pageContext.request.contextPath}/css/supplydemand/common.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/supplydemand/date-picker.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/supplydemand/media.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/supplydemand/sub.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/js/supplydemand/common.js"></script>
	<script src="${pageContext.request.contextPath}/js/supplydemand/display.js"></script>
	
</head>

<body class="fixed-nav">

<%-- 마이페이지 등 메뉴코드 조회용 따로 메뉴코드 필요할 경우 여기서 조회 --%>
<c:forEach var="menu" items="${menuList}" varStatus="num">
	<c:if test="${menu.progPath eq '/myPage/user/detail.do' }">
		<c:set var="myUserMnCd" value="${menu.menuCd }"/>
		<c:set var="myUserMn" value="${menu.menuNm }"/>
	</c:if>
</c:forEach>

<!-- wrapper Start -->
    <div id="wrapper">
 	<!-- Left Start -->
		<jsp:include page="/layout/common/left.do" flush="true" >
			<jsp:param name="myUserMnCd" value="${myUserMnCd }"/>
			<jsp:param name="myUserMn" value="${myUserMn }"/>
		</jsp:include>
		
	<!--// Left End -->
	<!-- page-wrapper Start -->
		<div id="page-wrapper" class="gray-bg">
		<jsp:include page="/WEB-INF/jsp/cms/inc/top.jsp"/>
	<!-- Header Start -->
		<jsp:include page="/layout/common/header.do" flush="true" >
			<jsp:param name="myUserMnCd" value="${myUserMnCd }"/>
			<jsp:param name="myUserMn" value="${myUserMn }"/>
		</jsp:include>
	<!--// Header End -->
	<!-- Content Start -->
<div class="wrapper wrapper-content" id="content">

	<!-- 네비게이션 변경 -->
	<script type="text/javascript">
		var menuNavi = ${requestScope['menuNavi']};
		headTitle(menuNavi);
	</script>

<style type="text/css">
.CodeMirror {
	line-height: 1em;
	font-family: monospace;
	font-size: 10pt;
	background: White;
	border-top: 1px solid #CCCCCC;
	border-bottom: 1px solid #CCCCCC;
	border-right: 1px solid #CCCCCC;
}

.CodeMirror-scroll {
	overflow: auto;
	height: 300px;
	position: relative;
	outline: none;
}
</style>


<script type="text/javascript">
	$(document).ready(function() {
		var editor_one = CodeMirror.fromTextArea(document.getElementById("code1"), {
			lineNumbers: true,
			matchBrackets: true,
			readOnly:true,
		}); 
	}); 
	
	// 수정페이지 이동
	function contentUpdate(){
		var frm = $('#searchFrm');
		ajaxSubPageChange('/content/manage/update.do', '_self', '콘텐츠관리 수정', '${searchFrm.mn}', frm, '${sessionScope.hpId}');
	}
	
	// 목록페이지 이동
	function cancel(){
		var frm = $('#searchFrm');
		ajaxSubPageChange('/content/manage/list.do', '_self', '콘텐츠관리', '${searchFrm.mn}', frm, '${sessionScope.hpId}');
	}
	
	// 콘텐츠삭제 
	function contentDelete(){
		var frm = $('#searchFrm');
		
		if (!confirm('<spring:message code="common.delete.msg" />')) return false;
		
		// 중복확인
		$.ajax({
			type: "POST",
			url: "/content/manage/delete.do",
			data: frm.serialize(),
			success: function(res) {
				alert(res.message);
				if(res.result == "2"){
					ajaxPageChange('/content/manage/list.do', '_self', '콘텐츠관리', '${searchFrm.mn}', '${sessionScope.hpId}');
				}
			}
		});
		return false;
	}
	
</script>

<div class="row hide" >
	<div class="col-lg-12">
		<div class="ibox float-e-margins">
			<div class="ibox-content">
				<form id="searchFrm" name="searchFrm" role="form" class="form-inline" onsubmit="return goSearch();" >
					<!-- mn 필수 -->
					<c:if test="${empty mn}"><c:set var="mn" value="${searchFrm.mn }" /></c:if>
					<input type="hidden" name="mn" value="${mn }">
					<input type="hidden" name="conSeq" value="${searchFrm.conSeq }">
					<input type="hidden" name="pageIndex" value="${searchFrm.pageIndex }">
					<input type="hidden" name="pageSize" value="${searchFrm.pageSize }">
					
					<div class="form-group">
						<label for="searchType" class="m-r-xs">콘텐츠구분 : </label>
						<select name="searchType" id="searchType" class="form-control m-r-xs">
							<option value="">전체</option>
							<option value="1" <c:if test="${searchFrm.searchType eq '1' }">selected="selected"</c:if>>에디터</option>
							<option value="2" <c:if test="${searchFrm.searchType eq '2' }">selected="selected"</c:if>>HTML</option>
						</select>
					</div>
					<div class="form-group">
						<label for="searchWord" class="m-r-xs">콘텐츠명 : </label>
						<input type="text" name="searchWord" id="searchWord" placeholder="검색내용" class="form-control" value="${searchFrm.searchWord }">
					</div>
					<button class="btn btn-white" type="button" onclick="goSearch();">검색</button>
				</form>
			</div>
		</div>
	</div>
</div>


<div class="row">
	<div class="col-lg-12">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<h5 class="text-navy">
					콘텐츠관리 상세
					<small>특정콘텐츠의 상세 정보입니다.</small>
				</h5>
			</div>
			<div class="ibox-content">
				<table class="table table-bordered">
					<colgroup>
						<col width="20%">
						<col width="80%">
					</colgroup>
					<tbody>
						<tr>
							<th class="bg-muted text-center">콘텐츠 명</th> 
							<td><c:out value="${detail.conTitle }"/></td>
						</tr>
						<tr>
							<th class="bg-muted text-center">콘텐츠 구분</th>
							<td>
								<c:if test="${detail.conType eq '1'}">
									에디터
								</c:if>
								<c:if test="${detail.conType eq '2'}">
									HTML 파일
								</c:if>
							</td>
						</tr>
						<tr>
							<th class="bg-muted text-center" style="vertical-align: middle;">콘텐츠 내용</th>
							<td style="width: 80%;">
								<textarea id="code1" class="editor" spellcheck="false" style="width: 100%;">
									<c:out value="${detail.conHtml }"/>
								</textarea>
							</td>						
						</tr>
						<c:if test="${detail.conType eq '2' }">
							<tr>
								<th class="bg-muted text-center">첨부파일</th>
								<td>
									<a href="/content/manage/contentHtmlDown.do?conSeq=${detail.conSeq }" >
										${detail.conHtmlOri } <i class="fa fa-download"></i>
									</a>
								</td>
							</tr>
						</c:if>
						
						<tr>
							<th class="bg-muted text-center">사용여부</th>
							<td>
								<c:if test="${detail.useYn eq 'Y' }">사용</c:if>
								<c:if test="${detail.useYn eq 'N' }">미사용</c:if>
							</td>
						</tr>
						<tr>
							<th class="bg-muted text-center">생성일</th>
							<td>
								<c:out value="${detail.insertDate }" />
							</td>
						</tr>
						<tr>
							<th class="bg-muted text-center">생성자ID</th>
							<td>
								<c:out value="${detail.insertId }" />
							</td>
						</tr>
					</tbody>
				</table>
				<div>
					<button class="btn btn-primary" type="button" onclick="contentUpdate();">수정</button>
					<button class="btn btn-danger" type="button" onclick="contentDelete();">삭제</button>
					<button class="btn btn-white m-l-xs" type="button" onclick="cancel();">취소</button>
				</div>
				
			</div>
		</div>
	</div>
</div>



 </div>
	<!--// Content End -->
        
	<!-- Footer Start -->
        <jsp:include page="/layout/common/footer.do" flush="true" />
	<!--// Footer End -->
		</div>
	<!--// page-wrapper End -->
	</div>
<!-- wrapper End -->
</body>
</html>


