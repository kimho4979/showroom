<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

<div class="row">
	<div class="col-lg-12">
		<div class="block">
			<div class="block-head">
				<h1>직원정보 <c:choose><c:when test="${command == 'insert'}"><spring:message code="common.create" /></c:when><c:otherwise><spring:message code="common.update" /></c:otherwise></c:choose></h1>
			</div>
			<div class="block-content">
				<form:form commandName="writeOrganizationStaff" action="?act=write" enctype="multipart/form-data" class="form-horizontal" role="form">
					<input type="hidden" name="command" value="<c:out value='${command}'/>" />
					<input type="hidden" name="returnQueryString" value="<c:out value='${searchOrganizationStaffVO.queryString}' escapeXml='false' />" />

					<c:if test="${command == 'update'}">
					<input type="hidden" name="staffId" value="<c:out value='${writeOrganizationStaff.staffId}' />" />
					<input type="hidden" name="loginId" value="${writeOrganizationStaff.loginId}"/>
					<input type="hidden" name="userSe" value="STF"/>
					</c:if>

					<fieldset>
						<legend>등록정보</legend>
						<div class="form-group">
							<span class="col-sm-2 text-right"><label for="deptId" class="control-label">* 부서</label></span>
							<div class="col-sm-10">
								<form:select path="deptId" class="form-control required">
								<c:forEach var="result" items="${treeDeptList}" varStatus="status">
								<form:option value="${result.deptId}"><c:out value="${result.deptNm}" /></form:option>
								</c:forEach>
								</form:select>
							</div>
						</div>
						<div class="form-group">
							<span class="col-sm-2 text-right"><label for="name" class="control-label">* 이름</label></span>
							<div class="col-sm-10">
								<form:input path="name" maxlength="30" class="form-control required" />
							</div>
						</div>
						<div class="form-group">
							<span class="col-sm-2 text-right"><label for="duty" class="control-label">직급</label></span>
							<div class="col-sm-10">
								<form:input path="duty" maxlength="60" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<span class="col-sm-2 text-right"><label for="rank" class="control-label">직위</label></span>
							<div class="col-sm-10">
								<form:input path="rank" maxlength="60" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<span class="col-sm-2 text-right"><label for="telNo" class="control-label">전화번호</label></span>
							<div class="col-sm-10">
								<form:input path="telNo" maxlength="30" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<span class="col-sm-2 text-right"><label for="faxNo" class="control-label">팩스</label></span>
							<div class="col-sm-10">
								<form:input path="faxNo" maxlength="14" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<span class="col-sm-2 text-right"><label for="hpNo" class="control-label">핸드폰번호</label></span>
							<div class="col-sm-10">
								<form:input path="hpNo" maxlength="30" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<span class="col-sm-2 text-right"><label for="email" class="control-label">이메일</label></span>
							<div class="col-sm-10">
								<form:input path="email" maxlength="60" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<span class="col-sm-2 text-right"><label for="mainBsnss" class="control-label">전문분야</label></span>
							<div class="col-sm-10">
								<form:textarea path="mainBsnss" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<span class="col-sm-2 text-right"><label for="asgnedArea" class="control-label">업무소개</label></span>
							<div class="col-sm-10">
								<form:textarea path="asgnedArea" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<span class="col-sm-2 text-right"><label for="photoFile" class="control-label">사진</label></span>
							<div class="col-sm-10">
								<input type="file" name="atchPhotoFile" id="photoFile" class="form-control" value="" />
								<c:if test="${not empty writeOrganizationStaff.photoFile}">
									<input type="checkbox" name="deletePhotoFile" value="Y" />삭제(<c:out value='${writeOrganizationStaff.photoFile}'/>)
									<img src="${CONTEXT_PATH}/uploads/organization/staff/<c:out value='${writeOrganizationStaff.photoFile}'/>" width="50" alt="" />
								</c:if>
							</div>
						</div>
						<div class="form-group">
							<span class="col-sm-2 text-right"><label for="position" class="control-label">* 순서</label></span>
							<div class="col-sm-10">
								<form:input path="position" maxlength="20" class="form-control number" />
							</div>
						</div>
						<div class="bd-t mg-t-md pd-t-md"></div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-primary pd-l-lg pd-r-lg"><spring:message code="common.save" /></button>
								<a href="?<c:out value='${searchOrganizationStaffVO.queryString}' />" class="btn btn-default pd-l-lg pd-r-lg"><spring:message code="common.reset" /></a>
							</div>
						</div>
					</fieldset>
					<fieldset>
						<legend>직원 로그인정보</legend>
						<c:if test="${not empty writeOrganizationStaff.loginId}">
						<div class="form-group">
							<span class="col-sm-2 text-right"><label class="control-label">직원 로그인 아이디</label></span>
							<div class="col-sm-10">
								<p class="form-control-static"><c:out value="${writeOrganizationStaff.loginId}" /></p>
							</div>
						</div>
						<div class="form-group">
							<span class="col-sm-2 text-right"><label for="loginPassword" class="control-label">직원 로그인 비밀번호</label></span>
							<div class="col-sm-10">
								<form:password path="loginPassword" class="form-control" />
								<div class="help-block">
								비밀번호를 변경하시는 경우에만 입력해 주세요.
								</div>
							</div>
						</div>
							<c:if test="${command == 'update'}">
								<div class="form-group">
									<span class="col-sm-2  text-right"><label for="passwordCnfirm" class="control-label">비밀번호 입력오류</label></span>
									<div class="col-sm-10">
										<input type="button" id="loginFailReset" class="btn btn-primary pd-l-lg pd-r-lg" value="초기화" />
									</div>
								</div>
							</c:if>
						</c:if>
						<c:if test="${empty writeOrganizationStaff.loginId}">
						<div class="form-group">
							<span class="col-sm-2 text-right"><label for="loginId" class="control-label">직원 로그인 아이디</label></span>
							<div class="col-sm-10">
								<form:input path="loginId" maxlength="20" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<span class="col-sm-2 text-right"><label for="loginPassword" class="control-label">직원 로그인 비밀번호</label></span>
							<div class="col-sm-10">
								<form:password path="loginPassword" class="form-control" />
							</div>
						</div>
						</c:if>
						<div class="form-group">
							<span class="col-sm-2  text-right"><label class="control-label">직원 그룹</label></span>
							<div class="col-sm-10">
								<c:forEach items="${groupList}" var="result" varStatus="status">
								<label class="checkbox-inline"><form:checkbox path="loginGroupIdArr" value="${result.groupId}" /> <c:out value="${result.groupNm}"/></label>
								</c:forEach>
							</div>
						</div>
						<div class="form-group">
							<span class="col-sm-2  text-right"><label for="loginLevelId" class="control-label">직원 등급</label></span>
							<div class="col-sm-10">
								<form:select path="loginLevelId" class="form-control">
								<form:option value="0">+ 선택</form:option>
								<c:forEach var="result" items="${levelList}" varStatus="status">
								<form:option value="${result.levelId}"><c:out value="${result.levelNm}" /></form:option>
								</c:forEach>
								</form:select>
							</div>
						</div>
						<div class="form-group">
							<span class="col-sm-2 text-right"><label for="loginStatus" class="control-label">상태</label></span>
							<div class="col-sm-10">
								<form:select path="loginStatus" class="form-control">
								<form:option value="">+ 선택</form:option>
								<form:option value="P">승인</form:option>
								<form:option value="A">승인대기</form:option>
								</form:select>
							</div>
						</div>
						<div class="bd-t mg-t-md pd-t-md"></div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-primary pd-l-lg pd-r-lg"><spring:message code="common.save" /></button>
								<a href="?<c:out value='${searchOrganizationStaffVO.queryString}' />" class="btn btn-default pd-l-lg pd-r-lg"><spring:message code="common.reset" /></a>
							</div>
						</div>
					</fieldset>
				</form:form>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
$(function()
{
	$("#writeOrganizationStaff").validate();
});

$(document).ready(function(){
	$("#loginFailReset").on("click", function(){
		writeOrganizationStaff.action = "/member/general.do?act=loginFailReset";
		writeOrganizationStaff.submit();
    });
});
</script>