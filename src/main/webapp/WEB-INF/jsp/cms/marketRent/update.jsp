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
			   	        url : "/comm/editor/imageFileUpload.do",
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
		window.location.href = "${pageContext.request.contextPath}/admin/marketRent/list.do";
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
	function update(marketSeq){
		/*if($("input[name=title]").val() ==""){
			alert("콘텐츠 명을 입력해주시기 바랍니다.");
			$("input[name=title]").focus();
			return false;
		}*/
		
		if (!confirm('저장하시겠습니까?')) return false;
		/*
		var contentId = $("#contentId").val();
		if(contentId == ""){
			contentId = null;
		}*/
		var cont = $('#summernote').summernote('code');
		$("#updateFrm input[name=content]").val(cont);
		//var title = $("input[name=title]").val();
		
		document.updateFrm.submit();
		/*
		$.ajax({
   			url: "${pageContext.request.contextPath}/admin/content/updateProc.json",
  	        type: 'POST',
  	        data:{
  	        	"marketType":marketType,
  	        	"marketNo":marketNo,
  	        	"tyGroupCode":tyGroupCode,
  	        	"marketName":marketName,
  	        	"owner":owner,
  	        	"tel":tel,
  	        	"hashText":hashText,
  	        	"saleTime":saleTime,
  	        	"cloDay":cloDay,
  	        	"deliText":deliText,
  	        	"content":cont
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
		*/
		return false;
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
					점포 추가정보 수정
					<small>아래의 내용을 입력해 주시기 바랍니다.</small>
				</h5>
			</div>
			<div class="ibox-content">
				<form id="updateFrm" name="updateFrm" method="post" class="form-horizontal" enctype="multipart/form-data" action="${pageContext.request.contextPath}/admin/marketRent/updateProc.do" onsubmit="return update();">
					<input type="hidden" name="marketType" id="marketType" value="${result.tyCode}" />
					<input type="hidden" name="marketNo" id="marketNo" value="${result.roCode}" />
					<input type="hidden" name="tyGroupCode" id="tyGroupCode" value="${eResult.tyGroupCode}" />
					<input type="hidden" name="marketSeq" id="marketSeq" value="${eResult.marketSeq}" />
					
					<div class="form-group">
						<label class="col-sm-2 control-label">점포명</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="marketName" id="marketName" required="" maxlength="100" value="${result.rcCompName}" readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">대표자</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="owner" id="owner" required="" maxlength="100" value="${result.rcName}" readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">전화번호</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="tel" id="tel" required="" maxlength="100" value="${result.rcTel}" readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">해쉬태그</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="hashText" id="hashText" required="" maxlength="100" value="${eResult.hashText}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">영업시간</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="saleTime" id="saleTime" required="" maxlength="100" value="${eResult.saleTime}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">휴무</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="cloDay" id="cloDay" required="" maxlength="100" value="${eResult.cloDay}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">전국택배</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="deliText" id="deliText" required="" maxlength="100" value="${eResult.deliText}">
						</div>
					</div>
					<div class="form-group" id="editorEl">
						<label class="col-sm-2 control-label">점포설명</label>
						<div class="col-sm-12">
							<input type="hidden" name="content"  value="" />
							<div class="summernote" id="summernote">${eResult.content}</div>
						</div>
					</div>
					<div class="form-group">
						<span class="col-sm-2 text-right"><label class="control-label">* 이미지</label></span>
						<c:forEach var="result" begin="0" end="9" varStatus="status">
							<c:set var="fileVO" value="${marketFileList[status.index]}"/>
							<span class="col-sm-2 text-right"></span>
							<div class="col-sm-10">
								<input type="file" class="file" name="attachFile" id="attachFile[]" title="파일 첨부하기" />
								
								<c:if test="${!empty fileVO}">
									<label>
										<input type="checkbox" name="deleteAtchFile" value="<c:out value='${fileVO.fileSeq}'/>" />
										<c:out value='${fileVO.orignlFileNm}'/> 삭제
									</label>
								</c:if>
							</div>
						</c:forEach>
					</div>
					<!--// 기타항목 종료 -->
					<div class="hr-line-dashed"></div>
					
					<div class="form-group">
						<div class="col-sm-6 ">
							<button class="btn btn-primary" type="submit" >저장</button>
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
	


