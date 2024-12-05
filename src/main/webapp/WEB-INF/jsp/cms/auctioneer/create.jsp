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
	
	var idCheckFlag = false;
	// 아이디 중복체크
	function validation() {
		var id = $("input[name=loginId]").val()
		if(id == "") {
			alert("아이디를 입력해주세요.");
			return false;
		}

		$.ajax({
   			url: "${pageContext.request.contextPath}/admin/auctioneer/idCheck.json",
  	        type: 'POST',
  	        data: {"id":id},
  	        success: function(data) {
  	        	if(data.result == 1) {
  	        		alert("중복된 아이디입니다. 다른 아이디를 입력해주세요.");
  	        		$("input[name=loginId]").focus();
  	        		idCheckFlag = false;
  	        	}else {
  	        		alert("사용 가능한 아이디입니다.");
  	        		idCheckFlag = true;
  	        	}
  	        }
 	   });
	}

	// 목록페이지 이동
	function cancel(){
		window.location.href = "${pageContext.request.contextPath}/admin/auctioneer/list.do";
	}
	
	// 등록 처리
	function createAuctioneer(){
		var loginId = $("input[name=loginId]").val();
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
		
		if(!loginId || !password || !name || !memberType || !aucType || !floUnitCd
				|| !chulCd || !aucCd || !aucPw || !aucRegNm) {
			
			alert("모든 항목을 입력해주세요.");
			return false;
		}

		if(!idCheckFlag) {
			alert("아이디 중복확인을 해주세요.");
			return false;
		}
		
		if(!confirm("등록하시겠습니까?")) return false;
		
		$.ajax({
   			url: "${pageContext.request.contextPath}/admin/auctioneer/insertProc.json",
  	        type: 'POST',
  	        data:{
  	        	"loginId":loginId,
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
  	        	   alert("정상적으로 등록되었습니다.");
  	        	   location.href = "${pageContext.request.contextPath}/admin/auctioneer/list.do";
  	           }else{
  	        	   alert("오류가발생했습니다. 관리자에게 문의하세요.");
  	           }
  	        }
 	   });
		
		return false;
	}
	
</script>
	

<div class="row">
	<div class="col-lg-12">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<h5 class="text-navy">
					경매사 등록
					<small>아래의 내용을 입력해 주시기 바랍니다.</small>
				</h5>
			</div>
			<div class="ibox-content">
				<form id="contentCreateFrm" name="contentCreateFrm" method="post" class="form-horizontal" enctype="multipart/form-data" >
					<input type="hidden" name="contentId" id="contentId" value="${result.contentId}" />
					<div class="form-group"> <!-- has-success -->
						<label class="col-sm-2 control-label">아이디</label>
						<div class="col-sm-10">
							<!-- <input type="text" class="form-control" name="loginId" value=""> -->
							<input type="text" class="form-control-auc" name="loginId" value="">
							<input type="button" class="btn btn-primary m-l-xs" value="중복확인" onclick="return validation()">
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
							<input type="text" class="form-control-auc" name="name" maxlength="100" value="">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">고객분류기준</label>
						<div class="col-sm-10">
							<input type="text" class="form-control-auc" name="memberType" maxlength="100" value="">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">경매관리구분코드</label>
						<div class="col-sm-10">
							<input type="text" class="form-control-auc" name="aucType" maxlength="100" value="">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">회계단위코드</label>
						<div class="col-sm-10">
							<input type="text" class="form-control-auc" name="floUnitCd" maxlength="100" value="">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">화훼부류코드</label>
						<div class="col-sm-10">
							<select class="form-control-auc m-b" name="floMokCd" id="floMokCd">
								<option value="N" >절화</option>
								<option value="Y" >난</option>
								<option value="C" >관엽</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">경매관리농가코드</label>
						<div class="col-sm-10">
							<input type="text" class="form-control-auc" name="chulCd" maxlength="100" value="">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">경매관리코드번호</label>
						<div class="col-sm-10">
							<input type="text" class="form-control-auc" name="aucCd" maxlength="100" value="">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">경매관리비밀번호</label>
						<div class="col-sm-10">
							<input type="text" class="form-control-auc" name="aucPw" maxlength="100" value="">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">경매관리등록명</label>
						<div class="col-sm-10">
							<input type="text" class="form-control-auc" name="aucRegNm" maxlength="100" value="">
						</div>
					</div>

					<!--// 기타항목 종료 -->
					<div class="hr-line-dashed"></div>
					
					<div class="form-group">
						<div class="col-sm-6 ">
							<button class="btn btn-primary" type="button" onclick="createAuctioneer();">등록</button>
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
	


