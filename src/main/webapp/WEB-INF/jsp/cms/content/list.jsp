<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


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
				url : "${pageContext.request.contextPath}/admin/content/contentList.json",
				dataType: "JSON",
				data: function(d){
					$("#searchFrm input[name=pageIndex]").val(d.start/d.length+1);
					$("#searchFrm input[name=pageSize]").val(d.length);
					
					d.searchWord = $("#searchWord").val();
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
			    {data: "contentId"			,className: "cursor-link"	,render: $.fn.dataTable.render.text()},        
				{data: "title"		,className: "cursor-link"	,render: $.fn.dataTable.render.text()},    
				{data: "registId"		,className: "cursor-link"	,render: $.fn.dataTable.render.text()},
				{data: "registDttm"		,className: "cursor-link"	,render: $.fn.dataTable.render.text()}
			],
			columnDefs: [
				{ "visible" : false, "targets": [0] } // 숨기고 싶을때
			],
			buttons: [
			    {extend: 'copy'},
			    {extend: 'excel'},
			    {extend: 'pdf'},
			    {extend: 'print',	autoPrint : 'true',
			         customize: function (win){
			                $(win.document.body).addClass('white-bg');
			                $(win.document.body).css('font-size', '10px');
			                $(win.document.body).find('table').css('font-size', 'inherit').addClass('compact');
			        }
			    }
			]
			
        });
		
		// 데이터 onClick 이벤트 적용
		$('#contentList tbody').on( 'click', 'tr', function () {
			var data = $(this).children();
			//console.log("test", tableEl.cell( this, 0 ).data()); 
			if(data.hasClass("dataTables_empty"))return false;
			contentDetail(tableEl.cell( this, 0 ).data());
	    });
	});
	
	// 사용자 생성 페이지 이동
	function contentManageCreate(){
		/*
		var frm = $("#searchFrm");
		// searchFrm 맵 형태 전송을 위하여 객체로 변경 및 할당
		frm.attr("action", "${pageContext.request.contextPath}/admin/content/create.do");
		frm.submit();*/
		
		window.location.href = "${pageContext.request.contextPath}/admin/content/create.do";
	}
	
	// 상세 페이지 이동
	function contentDetail(data){
		//console.log("onclick Data",data);
		
		window.location.href = "${pageContext.request.contextPath}/admin/content/update.do?contentId="+data;
		//ajaxSubPageChange('/content/manage/detail.do', '_self', '콘텐츠상세', frm.find("input[name=mn]").val(), frm, '${sessionScope.hpId}');
		//ajaxSubPageChange('/content/manage/update.do', '_self', '콘텐츠관리 수정', '${searchFrm.mn}', frm, '${sessionScope.hpId}');
	}
	
	// 검색
	function goSearch(){
		tableEl.draw();
		return false;
	}
	
</script>

<!-- 검색조건 -->
<div class="row">
	<div class="col-lg-12">
		<div class="ibox float-e-margins">
			<div class="ibox-content">
				<form id="searchFrm" name="searchFrm" role="form" class="form-inline" onsubmit="return goSearch();">
					
					<input type="hidden" name="pageIndex" value="${searchFrm.pageIndex }">
					<input type="hidden" name="pageSize" value="${searchFrm.pageSize }">
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
<!--// 검색조건 -->

<!-- 데이터 -->
<div class="row">
	<div class="col-lg-12">
		<div class="ibox float-e-margins">
			<div class="ibox-content">
				<div class="table-responsive">
					<table id="contentList" class="table table-striped table-bordered table-hover" style="width: 100%;">
						<thead>
							<tr>
								<th>콘텐츠순번</th>
								<th>콘텐츠명</th>
								<th>등록자아이디</th>
								<th>생성일</th>
							</tr>
						</thead>
					</table>
					<button class="btn btn-sm btn-primary pull-right m-t-n-xs" type="button" onclick="contentManageCreate();">
						<strong>콘텐츠 등록</strong>
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
 
