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

	// 목록페이지 이동
	function cancel(){
		window.location.href = "${pageContext.request.contextPath}/admin/auctioneer/list.do";
	}

	
	// 경매사 수정 처리
	function updateAuctioneer(){
		var memberId = $("input[name=memberId]").val();
		var password = $("input[name=password]").val();
		var name = $("input[name=name]").val();
		var memberType = $("input[name=memberType]").val();
		var aucType = $("input[name=aucType]").val();
		var floUnitCd = $("input[name=floUnitCd]").val();
		var floMokCd = $("#floMokCd option:selected").val();
		var chulCd = $("input[name=chulCd]").val();
		var aucCd = $("input[name=aucCd]").val();
		var aucPw = $("input[name=aucPw]").val();
		var aucRegNm = $("input[name=aucRegNm]").val();
		
		if(!name || !memberType || !aucType || !floUnitCd || !chulCd || !aucCd || !aucPw || !aucRegNm) {
			alert("모든 항목을 입력해주세요.");
			return false;
		}
		
		if (!confirm('수정하시겠습니까?')) return false;
		
		$.ajax({
   			url: "${pageContext.request.contextPath}/admin/auctioneer/updateProc.json",
  	        type: 'POST',
  	        data:{
  	        	"memberId":memberId,
  	        	"password":password,
  	        	"name":name,
  	        	"memberType":memberType,
  	        	"aucType":aucType,
  	        	"floUnitCd":floUnitCd,
  	        	"floMokCd":floMokCd,
  	        	"chulCd":chulCd,
  	        	"aucCd":aucCd,
  	        	"aucPw":aucPw,
  	        	"aucRegNm":aucRegNm
  	        },
  	        success: function (data) {
  	           if(data.result == 1){
  	        	   alert("정상적으로 수정되었습니다.");
  	        	   location.href = "${pageContext.request.contextPath}/admin/auctioneer/list.do";
  	           }else{
  	        	   alert("오류가발생했습니다. 관리자에게 문의하세요.");
  	           }
  	        }
 	   });
		
		return false;
	}
	
	
	// 경매사 삭제 처리
	function deleteAuctioneer(){
		if (!confirm('삭제된 데이터는 복구 할 수없습니다. 정말 삭제하시겠습니까?')) return false;
		
		var memberId = $("#memberId").val();
		
		$.ajax({
   			url: "${pageContext.request.contextPath}/admin/auctioneer/deleteProc.json",
  	        type: 'POST',
  	        data:{"memberId":memberId},
  	        success: function (data){
  	           if(data.result == 1){
  	        	   alert("정상적으로 삭제되었습니다.");
  	        	   location.href = "${pageContext.request.contextPath}/admin/auctioneer/list.do";
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
					경매사 수정
					<small>아래의 내용을 입력해 주시기 바랍니다.</small>
				</h5>
			</div>
			<div class="ibox-content">
				<form id="contentUpdateFrm" name="contentUpdateFrm" method="post" class="form-horizontal" enctype="multipart/form-data">
					<input type="hidden" name="memberId" id="memberId" value="${result.memberId}"/>
					<div class="form-group"> <!-- has-success -->
						<label class="col-sm-2 control-label">아이디</label>
						<div class="col-sm-10">
							<%-- <input type="text" class="form-control-auc" name="loginId" value="${result.loginId}" readonly> --%>
							<p>${result.loginId}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">비밀번호</label>
						<div class="col-sm-10">
							<input type="text" class="form-control-auc" name="password" maxlength="100" value="">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">이름</label>
						<div class="col-sm-10">
							<input type="text" class="form-control-auc" name="name" maxlength="100" value="${result.name}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">고객분류기준</label>
						<div class="col-sm-10">
							<input type="text" class="form-control-auc" name="memberType" maxlength="100" value="${result.memberType}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">경매관리구분코드</label>
						<div class="col-sm-10">
							<input type="text" class="form-control-auc" name="aucType" maxlength="100" value="${result.aucType}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">회계단위코드</label>
						<div class="col-sm-10">
							<input type="text" class="form-control-auc" name="floUnitCd" maxlength="100" value="${result.floUnitCd}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">화훼부류코드</label>
						<div class="col-sm-10">
							<select class="form-control-auc m-b" name="floMokCd" id="floMokCd">
								<option value="N" <c:if test="${result.floMokCd eq 'N'}">selected="selected"</c:if>>절화</option>
								<option value="Y" <c:if test="${result.floMokCd eq 'Y'}">selected="selected"</c:if>>난</option>
								<option value="C" <c:if test="${result.floMokCd eq 'C'}">selected="selected"</c:if>>관엽</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">경매관리농가코드</label>
						<div class="col-sm-10">
							<input type="text" class="form-control-auc" name="chulCd" maxlength="100" value="${result.chulCd}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">경매관리코드번호</label>
						<div class="col-sm-10">
							<input type="text" class="form-control-auc" name="aucCd" maxlength="100" value="${result.aucCd}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">경매관리비밀번호</label>
						<div class="col-sm-10">
							<input type="text" class="form-control-auc" name="aucPw" maxlength="100" value="${result.aucPw}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">경매관리등록명</label>
						<div class="col-sm-10">
							<input type="text" class="form-control-auc" name="aucRegNm" maxlength="100" value="${result.aucRegNm}">
						</div>
					</div>
					
					<!--// 기타항목 종료 -->
					<div class="hr-line-dashed"></div>
					
					<div class="form-group">
						<div class="col-sm-6">
							<button class="btn btn-primary" type="button" onclick="updateAuctioneer();">수정</button>
							<button class="btn btn-warning" type="button" onclick="deleteAuctioneer();">삭제</button>
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
	


