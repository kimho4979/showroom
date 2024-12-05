<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui"%>


<!-- page-wrapper Start -->
<div id="page-wrapper" class="gray-bg">
	<jsp:include page="/WEB-INF/jsp/cms/inc/top.jsp"/>
	<!-- Content Start -->
<div class="wrapper wrapper-content" id="content">

<script type="text/javascript">
	var tableEl = null;
	$(document).ready(function() {
		var pLen = "${searchFrm.pageSize}";
		if(pLen == "") pLen = 10;
		
		var boardId = $("#boardId").val();
		
		// 목록 출력 시작
		tableEl =
		$('#contentList').DataTable({
			pageLength: pLen,					// 기본 데이터 개수
			<c:if test="${not empty searchFrm.pageIndex}">
				displayStart : ("${searchFrm.pageIndex}"-1) * 10,
			</c:if>
			lengthMenu: [[10, 25, 50, 100, 60000], [10, 25, 50, 100, 60000]],
			pagingType : "full_numbers",	// 페이징 타입
			bPaginate: true,				// 페이징사용
			responsive: true,
			dom: '<"html5buttons"B>lTgtp',
			processing: "true",		// 개발 디버깅용 "processing"인디케이터를 보여준다.
			serverSide: true,		// 그리드 내에서 이루어지는 모든 상황들(검색, 페이징, 정렬 등)에 대한 처리를 서버측에서 수행할지
			//order: [[ 5, 'desc' ]],	// 기본 정렬
			ordering: false,		// 정렬 사용하지않음
			language: {
				"url": "${pageContext.request.contextPath}/assets/js/plugins/dataTables/korean.lang"
			},
			ajax: {
				type : "POST",
				url : "${pageContext.request.contextPath}/admin/banner/bannerList.json?boardId=" + boardId,
				dataType: "JSON",
				data: function(d){
					$("#searchFrm input[name=pageIndex]").val(d.start/d.length+1);
					$("#searchFrm input[name=pageSize]").val(d.length);
					
					//d.searchWord = $("#searchWord").val();
					console.log(d);
					//d = dataTableFromFaram(d, $("#searchFrm"));
					
					// 사용하지 않는 보기 싫은 파라미터 정리
					/*
					delete d['columns'];
					delete d['order'];
					delete d['search'];*/
					//console.log("param", d);
			    }
			},
			columns : [
				{data: "articleId"		,className: "cursor-link"	,render: $.fn.dataTable.render.text()},  
			    {data: "orderNo"		,className: "cursor-link"	,render: $.fn.dataTable.render.text(), defaultContent : ""},  
			    {data: "boardId"		,className: "cursor-link"	,defaultContent : "",
					render: function(data, type, row) {
						data = "<img src=${pageContext.request.contextPath}/front/board/fileDown2.do?boardId=" + data + "&articleId=" + row["articleId"] + "&fileSn=0 style='max-width: 260px; max-height: 120px;' />";
						return data;
					}
				},
				{data: "subject"		,className: "cursor-link"	,render: $.fn.dataTable.render.text(), defaultContent : ""},
				{data: "writerNm"		,className: "cursor-link"	,render: $.fn.dataTable.render.text(), defaultContent : ""},
				{data: "registDttm"		,className: "cursor-link"	,render: $.fn.dataTable.render.text(), defaultContent : ""}
			],
			columnDefs: [
				{ "visible":false, "targets":[0] } // 숨기고 싶을때
			],
			buttons: []
			
        });
		
		// 데이터 onClick 이벤트 적용
		$('#contentList tbody').on( 'click', 'tr', function () {
			var data = $(this).children();
			if(data.hasClass("dataTables_empty"))return false;
			bannerUpdate(tableEl.cell( this, 0 ).data());
	    });
	});
	
	// 등록 페이지 이동
	function bannerCreate(){
		var boardId = $("#boardId").val();
		window.location.href = "${pageContext.request.contextPath}/admin/banner/create.do?boardId=" + boardId;
	}
	
	// 수정 페이지 이동
	function bannerUpdate(data){
		var boardId = $("#boardId").val();
		window.location.href = "${pageContext.request.contextPath}/admin/banner/update.do?boardId=" + boardId + "&articleId=" + data;
	}
	
</script>

<!-- 데이터 -->
<input type="hidden" name="boardId" id="boardId" value="${boardInfoVO.boardId}">
<div class="row">
	<div class="col-lg-12">
		<div class="ibox float-e-margins">
			<div class="ibox-content">
				<div class="table-responsive">
					<table id="contentList" class="table table-striped table-bordered table-hover" style="width: 100%;">
						<thead>
							<tr>
								<th>게시물ID</th>
								<th>순번</th>
								<th>이미지</th>
								<th>링크URL</th>
								<th>작성자</th>
								<th>생성일</th>
							</tr>
						</thead>
					</table>
					<button class="btn btn-sm btn-primary pull-right m-t-n-xs" type="button" onclick="bannerCreate();">
						<strong>배너 등록</strong>
					</button>
				</div>
			</div>
		</div>
	</div>
</div>
<!--// 데이터 -->

 </div>
	<!--// Content End -->
 </div>
 
