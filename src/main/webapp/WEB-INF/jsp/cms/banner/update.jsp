<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

	
<script type="text/javascript">

	// 순번 입력 글자 수 제한
	function numberMaxLength(e) {
		if(e.value.length > e.maxLength) {
			e.value = e.value.slice(0, e.maxLength);
		}
	}

	
	// 목록페이지 이동
	function cancel(){
		var boardId = $("#boardId").val();
		window.location.href = "${pageContext.request.contextPath}/admin/banner/list.do?boardId=" + boardId;
	}
	
	
	// 배너 수정 처리
	function updateBanner(){
		
		var orderNo = $('input[name=orderNo]').val();
		
		if(!orderNo) {
			alert("순번을 입력해주세요.");
			return false;
		}
		
		if(!confirm("수정하시겠습니까?")) return false;
		
		document.bannerUpdateFrm.submit();
		
		return false;
	}
	
	
	// 배너 삭제
	function del(){
		
		if (!confirm('삭제된 데이터는 복구 할 수없습니다. 정말 삭제하시겠습니까?')) return false;
		
		var articleId = $("#articleId").val();
		var boardId = $("#boardId").val();
		
		$.ajax({
   			url: "${pageContext.request.contextPath}/admin/banner/deleteProc.json",
  	        type: 'POST',
  	        data:{
  	        	"articleId":articleId,
  	        	"boardId":boardId
  	        },
  	        success: function (res){
  	           if(res.result == 1){
  	        	   alert("정상적으로 삭제되었습니다.");
  	        	   location.href = "${pageContext.request.contextPath}/admin/banner/list.do?boardId=" + boardId;
  	           }else{
  	        	   alert("오류가발생했습니다. 관리자에게 문의하세요.");
  	           }
  	        }
 	   });
	}
	
	
	// 배너 이미지 미리보기 변경
	function chgImgPreview(e) {
		var files = $('input[name=attachFile]')[0].files;
		var filesArr = Array.prototype.slice.call(files);
		
		filesArr.forEach(function(f) {
			if(!f.type.match("image.*")) {
				alert("이미지 확장자만 등록이 가능합니다.");
				return false;
			}
			
			var reader = new FileReader();
			reader.onload = function(e) {
				$('#bannerImg').attr("src", e.target.result);
			}
			reader.readAsDataURL(f);
		});
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
					배너 수정
					<small>아래의 내용을 입력해 주시기 바랍니다.</small>
				</h5>
			</div>
			<div class="ibox-content">
				<form id="bannerUpdateFrm" name="bannerUpdateFrm" method="post" class="form-horizontal" enctype="multipart/form-data" action="${pageContext.request.contextPath}/admin/banner/updateProc.do" onsubmit="return updateBanner();" >
					<input type="hidden" name="boardId" id="boardId" value="${result.boardId }" />
					<input type="hidden" name="articleId" id="articleId" value="${result.articleId }" />
					<input type="hidden" name="content" value="" />
					<div class="form-group">
						<label class="col-sm-2 control-label">* 순번</label>
						<div class="col-sm-10">
							<input type="number" class="form-control-auc" name="orderNo" maxlength="10" min="1" max="9999999999" oninput="numberMaxLength(this);" value="${result.orderNo}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">링크URL</label>
						<div class="col-sm-10">
							<input type="text" class="form-control-auc" name="subject" maxlength="255" value="${result.subject}">
						</div>
					</div>
					
					<c:if test="${boardInfoVO.uploadCount>0}">
						<div class="form-group">
							<span class="col-sm-2 text-right"><label class="control-label">* 첨부파일</label></span>
							<div class="col-sm-10">
								<span>권장 이미지 사이즈 : 260 x 225px</span><span class="m_none"> / </span>
								<span>업로드 제한 용량 : <c:out value="${boardInfoVO.uploadSize}"/>MB</span><span class="m_none"> / </span>
								<span>업로드 가능 파일 : <c:out value="${boardInfoVO.uploadExt}"/></span>
							</div>
							<c:forEach begin="0" end="${boardInfoVO.uploadCount-1}" varStatus="status">
								<c:set var="fileVO" value="${boardFileList[status.index]}"/>
								<span class="col-sm-2 text-right"></span>
								<div class="col-sm-10">
									<input type="file" class="file" name="attachFile" id="attachFile[]" accept="image/*" onchange="chgImgPreview(this);" title="파일 첨부하기" />
									<div id="imgPreview">
										<img id="bannerImg" src="${pageContext.request.contextPath}/front/board/fileDown2.do?boardId=${result.boardId}&articleId=${result.articleId}&fileSn=${fileVO.fileSn}" alt="배너 이미지 미리보기" />
									</div>
								</div>
							</c:forEach>
						</div>
					</c:if>

					<!--// 기타항목 종료 -->
					<div class="hr-line-dashed"></div>
					
					<div class="form-group">
						<div class="col-sm-6 col-sm-offset-8">
							<button class="btn btn-primary" type="submit" >배너 수정</button>
							<button class="btn btn-warning" type="button" onclick="del();" >삭제</button>
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
	


