<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


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
			    	console.log("1");
				    data = new FormData();
			   	    data.append("uploadFile", files[0]);
			   	 	console.log("2");
			   	    $.ajax({
			   	        data : data,
			   	        type : "POST",
			   	        url : "/yfmc/admin/editor/imageFileUpload.json",
			   	        cache : false,
			   	        contentType : false,
			   	        processData : false,
			   	        success : function(data){
			   	        	console.log(data);
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
			   	        },
			   	     	error : function(data) {
							console.log(data);
			   	    	}
			   	    });
			    }
		 	},
		    lang : 'ko-KR'
		});
		
		
	}); 

	
	
	// 게시판 등록처리
	function updateBoard(){
		/*
		var $fileUseSize = $("input[name=fileUseSize]");
		if($('#boardUpdateFrm input[name=fileUseYn]').val() == 'Y' && $fileUseSize.val() == '' ){
			alert('첨부파일 사용 시 제한 용량을 입력해주시기 바랍니다.');
			$fileUseSize.focus();
			return false;
		}
		
		var $fileUseExt = $("input[name=fileUseExt]");
		if($('#boardUpdateFrm input[name=fileUseYn]').val() == 'Y' && $fileUseExt.val() == '' ){
			alert('첨부파일 사용 시 제한 확장자를 입력해주시기 바랍니다.');
			$fileUseExt.focus();
			return false;
		}*/
		
		if($('#boardTy').val() == 'image' && $('#attachThumbFile').val() == '') {
			alert('리스트 썸네일 이미지를 등록해주시기 바랍니다.');
			return false;
		}
		
		
		if (!confirm('등록하시겠습니까?')) return false;
		
		var cont = $('#summernote').summernote('code');
		$("#boardUpdateFrm input[name=content]").val(cont);
		
		document.boardUpdateFrm.submit();
		
		/*
		var frm = $("#boardUpdateFrm")[0];
		var data = new FormData(frm);
		var boardId = $("#boardId").val();
   		console.log($("#boardUpdateFrm"));
   		console.log($("#boardUpdateFrm")[0]);
   		console.log(data);
   		
		$.ajax({
			type: "POST",
			url: "${pageContext.request.contextPath}/admin/board/insertProc.json",
			enctype: 'multipart/form-data',
			processData: false,
            contentType: false,
            cache: false,
			data: data,
			success: function(res) {
				if(res.result == 1){
  	        	   alert("정상적으로 저장되었습니다.");
  	        	   location.href = "${pageContext.request.contextPath}/admin/board/list.do?boardId="+boardId;
  	           }else{
  	        	   console.log(res);
  	        	   alert("오류가발생했습니다. 관리자에게 문의하세요.");
  	           }
			}
		});*/
		
		
		
		return false;
	}
	
	
	// 목록페이지 이동
	function cancel(){
		var boardId = $("#boardId").val();
		window.location.href = "${pageContext.request.contextPath}/admin/board/list.do?boardId="+boardId;
	}
	
	
	
</script>

	<!-- page-wrapper Start -->
<div id="page-wrapper" class="gray-bg">
	<jsp:include page="/WEB-INF/jsp/cms/inc/top.jsp"/>
	<!-- Content Start -->
<div class="wrapper wrapper-content" id="content">
<div class="row">
	<div class="col-lg-12">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<h5 class="text-navy">
					${boardInfoVO.boardNm} 등록
					<small>아래의 내용을 입력해 주시기 바랍니다.</small>
				</h5>
			</div>
			<div class="ibox-content">
				<form id="boardUpdateFrm" name="boardUpdateFrm" method="post" enctype="multipart/form-data" class="form-horizontal" action="${pageContext.request.contextPath}/admin/board/insertProc.do" onsubmit="return updateBoard();" >
					<input type="hidden" name="boardId" id="boardId" value="${boardInfoVO.boardId }" />
					<input type="hidden" id="boardTy" value="${boardInfoVO.boardTy }" />
					<div class="form-group">
						<label class="col-sm-2 control-label">제목</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="subject" value="<c:out value="${result.subject }"/>" required="">
						</div>
					</div>
					
					<c:if test="${boardInfoVO.boardTy eq 'image'}">
						<div class="form-group">
							<label class="col-sm-2 control-label">링크URL</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="data01" value="<c:out value="${result.data01 }"/>">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">등록일자</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="data02" value="<c:out value="${result.data02 }"/>">
							</div>
						</div>
						<c:if test="${boardInfoVO.boardId eq '28' || boardInfoVO.boardId eq '30'}">
							<div class="form-group">
								<label class="col-sm-2 control-label">카테고리</label>
								<div class="col-sm-10">
									<select name="data03">
										<c:choose>
											<c:when test="${boardInfoVO.boardId eq '28'}">
												<option value="01">계절 꽃·식물</option>
												<option value="02">신화환</option>
												<option value="03">꽃과 식물 트렌드</option>
												<option value="04">꽃문화즐기기</option>
											</c:when>
											<c:when test="${boardInfoVO.boardId eq '30'}">
												<option value="05">원예따라하기</option>
												<option value="06">플렌테리어 팁</option>
											</c:when>
										</c:choose>
									</select>
								</div>
							</div>
						</c:if>
					</c:if>
					
					<div class="form-group" id="editorEl">
						<label class="col-sm-2 control-label">내용</label>
						<div class="col-sm-12">
							<input type="hidden" name="content" value="" />
							<div class="summernote" id="summernote">${result.content}</div>
						</div>
					</div>
					
					<c:if test="${boardInfoVO.useThumbAt eq 'Y'}">
						<div class="form-group">
							<span class="col-sm-2 text-right"><label for="attachThumbFile" class="control-label">* 리스트 썸네일 이미지</label></span>
							<div class="col-sm-10">
							<input type="file" name="attachThumbFile" id="attachThumbFile" value="" />
							<c:if test="${!empty result.listThumbFile}">
								<img id="thumbImg" alt="" src="${pageContext.request.contextPath}/uploads/board/${boardInfoVO.boardId}/${writeBoardArticle.listThumbFile}">
								<input type="checkbox" name="deleteListThumbFile" value="Y" />삭제
							</c:if>
							</div>
						</div>
					</c:if>	
				
				
					<c:if test="${boardInfoVO.uploadCount>0}">
						<div class="form-group">
							<span class="col-sm-2 text-right"><label class="control-label">* 첨부파일</label></span>
							<div class="col-sm-10">
								<span>업로드 가능 파일 : <c:out value="${boardInfoVO.uploadExt}"/></span><span class="m_none"> / </span><span>업로드 제한 용량 : <c:out value="${boardInfoVO.uploadSize}"/>MB</span></p>
							
							</div>
							<c:forEach var="result" begin="0" end="${boardInfoVO.uploadCount-1}" varStatus="status">
								<c:set var="fileVO" value="${boardFileList[status.index]}"/>
								<span class="col-sm-2 text-right"></span>
								<div class="col-sm-10">
									<input type="file" class="file" name="attachFile" id="attachFile[]" title="파일 첨부하기" />
									<c:if test="${!empty fileVO}">
										<label>
											<input type="checkbox" name="deleteAtchFile" value="<c:out value='${fileVO.fileSn}'/>" />
											<c:out value='${fileVO.orignlFileNm}'/> 삭제
										</label>
									</c:if>
								</div>
							</c:forEach>
						</div>
					</c:if>
					
				
					<!--// 기타항목 종료 -->
					<div class="hr-line-dashed"></div>
					
					<div class="form-group">
						<div class="col-sm-6 col-sm-offset-8">
							<button class="btn btn-primary" type="submit" >게시판 등록</button>
							<button class="btn btn-white col-sm-offset-1" type="button" onclick="cancel();">취소</button>
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
 
