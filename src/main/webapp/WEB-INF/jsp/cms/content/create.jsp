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
	<!-- 네비게이션 변경 -->
<script type="text/javascript">
	$(document).ready(function() {
		$('#summernote').summernote({
			height: 300,                 // set editor height
			minHeight: 200, 	         // set minimum height of editor
			maxHeight: null,            // set maximum height of editor
			focus: false,		         // set focus to editable area after initializing summernote
			toolbar: [
	          ['style', ['style']],
	          ['font', ['bold', 'underline', 'clear']],
	          ['fontname', ['fontname']],
	          ['color', ['color']],
	          ['para', ['ul', 'ol', 'paragraph']],
	          ['table', ['table']],
	          ['insert', ['link', 'picture']],
	          ['view', ['fullscreen', 'codeview', 'help']]
	        ],
	        callbacks: {
			    onImageUpload: function(files) {
				    // 이미지 업로드 ajax 로 변경
			    	editorFileUploadCheck(files[0].name);	// 확장자 체크
			    	// 파일 업로드
				    data = new FormData();
			   	    data.append("uploadFile", files[0]);
			   	    $.ajax({
			   	        data : data,
			   	        type : "POST",
			   	        url : "/yfmc/admin/editor/imageFileUpload.json",
			   	        cache : false,
			   	        contentType : false,
			   	        processData : false,
			   	        success : function(data){
			   	            if(data.result == "1"){
			   	             	// 이미지 에디터에 추가
					   	        $('#summernote').summernote("insertImage", data.url, function ($image) {
							    	 $image.css('width', $image.width());
							    	 $image.css('max-width', "100%");
							    });
			   	            }else if(data.result == "2"){
			   	            	alert("이미지 파일만 등록해주시기 바랍니다.");
			   	            	return false;
			   	            }else if(data.result == "3"){
			   	            	alert("20MB가 넘는 이미지는 등록하실수 없습니다.");
			   	            	return false;
			   	            }
			   	        }
			   	    });
			    }
		 	},
		    lang : 'ko-KR'
		});
		
		// ajax 때문에 초기화 한번 해줘야함(값이 남아 있기 때문)
		/*
		resetFileEl();
		initFileEl("fileData", 1, 5, "html", 0);
		*/
	}); 
	
	// 목록페이지 이동
	function cancel(){
		window.location.href = "${pageContext.request.contextPath}/admin/content/list.do";
		/*
		var frm = $('#searchFrm');
		ajaxSubPageChange('/content/manage/list.do', '_self', '콘텐츠관리', '${searchFrm.mn}', frm, '${sessionScope.hpId}');
		*/
	}
	
	// 미리보기 기능
	function freeView(){
		if($("input[name=conType]:checked").val() == 1){
			// 에디터
			// HTML 로 미리보기 이후 에디터로 변경했을경우 대비 이벤트 un bind 
			$("#contentCreateFrm").ajaxFormUnbind();
			
			var cont = $('#summernote').summernote('code');
			$("#contentCreateFrm input[name=conHtml]").val(cont);  
			//console.log("serse", cont);
			
			
			$("#contentCreateFrm").attr("action", "/content/manage/freeView.do");
			$("#contentCreateFrm").attr("target", "_blank");
			$("#contentCreateFrm").submit();
		}else{
			// HTML 파일
			// 파일체크 완료 후 fileChkAfter() 호출
			fileSizeChk("contentCreateFrm", "/content/manage/fileSizeChk.do", "fileChkAfter" );
		}
		
	}

	// 파일 체크 이후 돌아갈 로직
	function fileChkAfter(res){
		// HTML 미리보기
		// 팝업으로 호출하기 위해 jquery form 해제
		$("#contentCreateFrm").ajaxFormUnbind();
		
		$("#contentCreateFrm").attr("action", "/content/manage/freeView.do");
		$("#contentCreateFrm").attr("target", "_blank");
		$("#contentCreateFrm").submit();
	}
	
	// 콘텐츠 등록 처리
	function createContent(){
		if($("input[name=title]").val() ==""){
			alert("콘텐츠 명을 입력해주시기 바랍니다.");
			$("input[name=title]").focus();
			return false;
		}
		
		if (!confirm('저장하시겠습니까?')) return false;
		
		var contentId = $("#contentId").val();
		if(contentId == ""){
			contentId = null;
		}
		var cont = $('#summernote').summernote('code');
		$("#contentCreateFrm input[name=content]").val(cont);
		var title = $("input[name=title]").val();
		
		$.ajax({
   			url: "${pageContext.request.contextPath}/admin/content/insertProc.json",
  	        type: 'POST',
  	        data:{
  	        	"title": title,
  	        	"content":cont,
  	        	"contentId":contentId
  	        },
  	        success: function (res) {
  	           if(res.result == 1){
  	        	   alert("정상적으로 저장되었습니다.");
  	        	   location.href = "${pageContext.request.contextPath}/admin/content/list.do";
  	           }else{
  	        	   alert("오류가발생했습니다. 관리자에게 문의하세요.");
  	           }
  	        }
 	   });
		
		return false;
	}
	
	// 콘텐츠 등록처리 이후 처리
	function contentCreateProc(res){
		alert(res.message);
		if(res.result == "1"){
			ajaxPageChange('/content/manage/list.do', '_self', '콘텐츠관리', '${searchFrm.mn}', '${sessionScope.hpId}');
		}
	}
	
	function deleteContent(){
		if (!confirm('삭제된 데이터는 복구 할 수없습니다. 정말 삭제하시겠습니까?')) return false;
		
		var contentId = $("#contentId").val();
		
		$.ajax({
   			url: "${pageContext.request.contextPath}/admin/content/deleteProc.json",
  	        type: 'POST',
  	        data:{
  	        	"contentId":contentId
  	        },
  	        success: function (res){
  	           if(res.result == 1){
  	        	   alert("정상적으로 삭제되었습니다.");
  	        	   location.href = "${pageContext.request.contextPath}/admin/content/list.do";
  	           }else{
  	        	   alert("오류가발생했습니다. 관리자에게 문의하세요.");
  	           }
  	        }
 	   });
	}
	
</script>
	

<div class="row">
	<div class="col-lg-12">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<h5 class="text-navy">
					콘텐츠 등록
					<small>아래의 내용을 입력해 주시기 바랍니다.</small>
				</h5>
			</div>
			<div class="ibox-content">
				<form id="contentCreateFrm" name="contentCreateFrm" method="post" class="form-horizontal" enctype="multipart/form-data" >
					<input type="hidden" name="contentId" id="contentId" value="${result.contentId}" />
					<div class="form-group"> <!-- has-success -->
						<label class="col-sm-2 control-label">콘텐츠 명</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="title" required="" maxlength="100" value="${result.title}">
						</div>
					</div>
					<div class="form-group" id="editorEl">
						<div class="col-sm-12">
							<input type="hidden" name="content" value="" />
							<div class="summernote" id="summernote">${result.content}</div>
						</div>
					</div>
					<!--// 기타항목 종료 -->
					<div class="hr-line-dashed"></div>
					
					<div class="form-group">
						<div class="col-sm-6 ">
							<button class="btn btn-primary" type="button" onclick="createContent();" >저장</button>
							<c:if test="${result.contentId ne null}">
							<button class="btn btn-warning" type="button" onclick="deleteContent();" >삭제</button>
							</c:if>
							<button class="btn btn-white m-l-xs" type="button" onclick="cancel();">취소</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

 </div>
	<!--// Content End -->
    	</div>
	<!--// page-wrapper End -->
	


