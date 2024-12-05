<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<script type="text/javascript">
	var fileCnt = $('select[name=fileUseCnt]').val();
	var fileSize = $('input[name=fileUseSize]').val();
	var fileExt = $("#boardUpdateFrm input[name=fileUseExt]").val();

	$(document).ready(function() {
		
		// 첨부파일 가능불가능일때 UI 변경
		$('#boardUpdateFrm input[name=fileUseYn]').on('change', function() {
			if($(this).val() == 'Y' ){
				$('.fileDetail').removeClass('hide');
			}else{
				$('.fileDetail').addClass('hide');
				
				if(fileCnt != "" && fileCnt != 0){
					$('select[name=fileUseCnt]').val(fileCnt);
					$('input[name=fileUseSize]').val(fileSize);
					$("#boardUpdateFrm input[name=fileUseExt]").val(fileExt);
				}else{
					$('select[name=fileUseCnt]').val("2");
					$('input[name=fileUseSize]').val("20");
					if($('#boardUpdateFrm select[name=bbsType]').val() == 'photo'){
						$("#boardUpdateFrm input[name=fileUseExt]").val("png,gif,bmp,jpg,jpeg");
					}else{
						$("#boardUpdateFrm input[name=fileUseExt]").val("zip,png,gif,bmp,jpg,jpeg,7z,hwp,ppt,xls,doc,txt,text,pdf,xlsx,pptx,docx");
					}		
				}
					
			}
		});
		
		// 사진게시판일 경우 첨부파일은 이미지만 가능
		$('#boardUpdateFrm select[name=bbsType]').on('change',function(){
			if($(this).val() == 'photo'){
				$("#boardUpdateFrm input[name=fileUseExt]").val("png,gif,bmp,jpg,jpeg");
			}else{
				$("#boardUpdateFrm input[name=fileUseExt]").val("zip,png,gif,bmp,jpg,jpeg,7z,hwp,ppt,xls,doc,txt,text,pdf,xlsx,pptx,docx");
			}
		});
		
	}); 

	
	
	// 게시판 등록처리
	function updateBoard(){
		
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
		}
		
		
		if (!confirm('등록하시겠습니까?')) return false;
		var $frm = $("#boardUpdateFrm");
   		
		$.ajax({
			type: "POST",
			url: "${pageContext.request.contextPath}/admin/boardInfo/insertProc.json",
			data: $frm.serialize(),
			success: function(res) {
				if(res.result == 1){
  	        	   alert("정상적으로 저장되었습니다.");
  	        	   location.href = "${pageContext.request.contextPath}/admin/boardInfo/list.do";
  	           }else{
  	        	   alert("오류가발생했습니다. 관리자에게 문의하세요.");
  	           }
			}
		});
		return false;
	}
	
	
	// 목록페이지 이동
	function cancel(){
		window.location.href = "${pageContext.request.contextPath}/admin/boardInfo/list.do";
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
					게시판관리 등록
					<small>아래의 내용을 입력해 주시기 바랍니다.</small>
				</h5>
			</div>
			<div class="ibox-content">
				<form id="boardUpdateFrm" name="boardUpdateFrm" method="post" class="form-horizontal" onsubmit="return updateBoard();" >
					<input type="hidden" name="boardId" id="boardId" value="${result.boardId }" />
					<div class="form-group">
						<label class="col-sm-2 control-label">게시판명</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="boardNm" value="<c:out value="${result.boardNm }"/>" required="">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">게시판타입</label>
						<div class="col-sm-10">
							<div class="row">
								<div class="col-md-4">
									<select class="form-control m-b" name="boardTy" id="boardTy" required="">
										<option value="nomal" <c:if test="${result.boardTy eq 'nomal' }">selected="selected"</c:if>>일반</option>
										<option value="notice" <c:if test="${result.boardTy eq 'notice' }">selected="selected"</c:if>>공지사항</option>
										<option value="faq" <c:if test="${result.boardTy eq 'faq' }">selected="selected"</c:if>>FAQ</option>
										<option value="image" <c:if test="${result.boardTy eq 'image' }">selected="selected"</c:if>>이미지</option>
										<option value="comment" <c:if test="${result.boardTy eq 'comment' }">selected="selected"</c:if>>댓글</option>
									</select>
								</div>	
							</div>
						</div>
					</div>
					<div class="form-group">
						<span class="col-sm-2 text-right"><label class="control-label">기능</label></span>
						<div class="col-sm-10">
							<label class="checkbox-inline"><input type="checkbox" name="useThumbAt" id="useThumbAt" value="Y"/>리스트썸네일 사용</label>
						</div>
					</div>
					<div class="form-group">
						<span class="col-sm-2 text-right"><label for="uploadCount" class="control-label">업로드</label></span>
						<div class="col-sm-10">
							<div class="input-group">
								<span class="input-group-addon">갯수</span>
								<input type="text" id="uploadCount" name="uploadCount" class="form-control digits" />
							</div>
							<div class="input-group mg-t-xs">
								<span class="input-group-addon">사이즈</span>
								<input type="text" id="uploadSize" name="uploadSize" class="form-control digits" />
							</div>
							<div class="input-group mg-t-xs">
								<span class="input-group-addon">허용확장자</span>
								<input type="text" id="uploadExt" name="uploadExt" class="form-control digits" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">사용여부</label>
						<div class="col-sm-10">
							<div>
								<label><input type="radio" value="Y" name="useAt" <c:if test="${result.useAt eq 'Y' }">checked="checked"</c:if>> 사용 </label>
							</div>
							<div>
								<label><input type="radio" value="N" name="useAt" <c:if test="${result.useAt eq 'N' }">checked="checked"</c:if>> 미사용 </label>
							</div>
						</div>
					</div>
				
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
	


