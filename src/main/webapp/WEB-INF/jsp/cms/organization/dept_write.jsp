<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

<div id="page-wrapper" class="gray-bg">
<jsp:include page="/WEB-INF/jsp/cms/inc/top.jsp"/>
	<!-- Content Start -->
	<div class="wrapper wrapper-content" id="content">
		<div class="row">
			<div class="col-lg-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5 class="text-navy">
							부서 등록
							<small>아래의 내용을 입력해 주시기 바랍니다.</small>
						</h5>
					</div>
				</div>
			</div>
			<div class="col-lg-2">	
				<div class="ibox-content">
					<div class="block-content">
						<link rel="stylesheet" href="<c:out value='${pageContext.request.contextPath}'/>/assets/js/plugins/dtree/dtree.css">
						<script type="text/javascript" src="<c:out value='${pageContext.request.contextPath}'/>/assets/js/plugins/dtree/dtree.js"></script>
						<script type="text/javascript">
						var d = new dTree('d');
						d.add("<c:out value='${rootDeptId}'/>",-1,"부서", "<c:url value='/organization/dept.do' />");
						<c:forEach var="result" items="${allOrganizationDeptList}" varStatus="status">
						<c:if test="${not empty result.parntsDeptId}">
						d.add("<c:out value='${result.deptId}' />", "<c:out value='${result.parntsDeptId}' />", "<c:out value='${result.deptNm}' />", "?deptId=<c:out value='${result.deptId}' />", "", "", "");
						</c:if>
						</c:forEach>
						document.write(d);
						</script>
					</div>
				</div>
			</div>
			<div class="col-lg-10">
				<div class="ibox-content">
					<ul class="nav nav-tabs">
					    <li class="active"><a href="#">부서관리</a></li>
					    <li><a href="<c:url value='/organization/staff.do' />?searchDeptId=<c:out value='${searchOrganizationDeptVO.deptId}' />">직원관리</a></li>
					</ul>
					<div class="block-head">
						<c:choose>
							<c:when test="${not empty searchOrganizationDeptVO.parntsDeptId}"><h1>하위부서 만들기</h1></c:when>
							<c:when test="${not empty searchOrganizationDeptVO.deptId}"><h1>부서 등록정보</h1></c:when>
							<c:otherwise><h1>최상위부서 만들기</h1></c:otherwise>
						</c:choose>
					</div>
					<div class="block-content">
						<form:form commandName="writeOrganizationDept" action="${pageContext.request.contextPath}/admin/organization/write.do" class="form-horizontal" role="form">
							<input type="hidden" name="command" value="<c:out value='${command}'/>" />
							<input type="hidden" name="deptId" value="<c:out value='${searchOrganizationDeptVO.deptId}'/>"/>
							<input type="hidden" name="parntsDeptId" value="<c:out value='${searchOrganizationDeptVO.parntsDeptId}'/>"/>
							<c:if test="${command == 'update'}">
								<input type="hidden" name="loginId" value="${writeOrganizationDept.loginId}"/>
								<input type="hidden" name="userSe" value="ORG"/>
							</c:if>
							
							<fieldset>
								<legend>등록정보</legend>
								<div class="form-group">
									<span class="col-sm-2 text-right"><label class="control-label">상위부서</label></span>
									<div class="col-sm-10">
										<p class="form-control-static">
											홈
											<c:forEach var="result" items="${parntsOrganizationDeptList}" varStatus="status">
											&gt; <c:out value="${result.deptNm}" />
											</c:forEach>
										</p>
									</div>
								</div>
								<div class="form-group">
									<span class="col-sm-2 text-right"><label for="deptNm" class="control-label">* 부서명</label></span>
									<div class="col-sm-10">
										<c:choose>
											<c:when test="${command == 'insert'}">
												<form:input path="deptNm" class="form-control required" maxlength="20" />
											</c:when>
											<c:otherwise>
												<div class="input-group">
													<form:input path="deptNm" class="form-control" />
													<c:if test="${not empty writeOrganizationDept.deptId}">
													<span class="input-group-btn"><a href="?parntsDeptId=<c:out value='${writeOrganizationDept.deptId}' />" class="btn btn-default btn-sm"><i class="fa fa-plus"></i> 하위조직등록</a></span>
													</c:if>
												</div>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
								<!--
								<div class="form-group">
									<span class="col-sm-2 text-right"><label for="deptDc" class="control-label">부서설명</label></span>
									<div class="col-sm-10">
										<form:textarea path="deptDc" class="form-control" />
										<script type="text/javascript" src="<c:url value='/cms/ckeditor.do'/>"></script>
										<c:import url="/cms/ckeditor/config.do?id=deptDc" />
									</div>
								</div>
								 -->
								<div class="form-group">
									<span class="col-sm-2 text-right"><label for="telNo" class="control-label">전화번호</label></span>
									<div class="col-sm-10">
										<form:input path="telNo" class="form-control" maxlength="20" />
									</div>
								</div>
								<div class="form-group">
									<span class="col-sm-2 text-right"><label for="faxNo" class="control-label">팩스번호</label></span>
									<div class="col-sm-10">
										<form:input path="faxNo" class="form-control" maxlength="20" />
									</div>
								</div>
								<div class="form-group">
									<span class="col-sm-2 text-right"><label for="useAt" class="control-label">사용여부</label></span>
									<div class="col-sm-10">
										<form:select path="useAt" class="form-control">
										<form:option value="Y">사용함</form:option>
										<form:option value="N">사용안함</form:option>
										</form:select>
									</div>
								</div>
								<div class="bd-t mg-t-md pd-t-md"></div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="submit" class="btn btn-primary pd-l-lg pd-r-lg">저장</button>
										<c:if test="${command == 'update'}">
										<button type="button" class="btn btn-default pd-l-lg pd-r-lg" onclick="post_delete('', 'act=delete&deptId=<c:out value='${writeOrganizationDept.deptId}'/>'); return false;">삭제</button>
										</c:if>
									</div>
								</div>
							</fieldest>
							<!-- 
							<fieldset>
								<legend>부서 로그인정보</legend>
								<c:if test="${not empty writeOrganizationDept.loginId}">
								<div class="form-group">
									<span class="col-sm-2 text-right"><label class="control-label">부서 로그인 아이디</label></span>
									<div class="col-sm-10">
										<p class="form-control-static"><c:out value="${writeOrganizationDept.loginId}" /></p>
									</div>
								</div>
								<div class="form-group">
									<span class="col-sm-2 text-right"><label for="loginPassword" class="control-label">부서 로그인 비밀번호</label></span>
									<div class="col-sm-10">
										<form:password path="loginPassword" class="form-control" />
										<div class="help-block">
										비밀번호를 변경하시는 경우에만 입력해 주세요.
										</div>
									</div>
								</div>
								<div class="form-group">
									<span class="col-sm-2  text-right"><label for="passwordCnfirm" class="control-label">비밀번호 입력오류</label></span>
									<div class="col-sm-10">
										<input type="button" id="loginFailReset" class="btn btn-primary pd-l-lg pd-r-lg" value="초기화" />
									</div>
								</div>
								</c:if>
								<c:if test="${empty writeOrganizationDept.loginId}">
								<div class="form-group">
									<span class="col-sm-2 text-right"><label for="loginId" class="control-label">부서 로그인 아이디</label></span>
									<div class="col-sm-10">
										<form:input path="loginId" class="form-control" maxlength="20" />
									</div>
								</div>
								<div class="form-group">
									<span class="col-sm-2 text-right"><label for="loginPassword" class="control-label">부서 로그인 비밀번호</label></span>
									<div class="col-sm-10">
										<form:password path="loginPassword" class="form-control" />
									</div>
								</div>
								</c:if>
								<div class="form-group">
									<span class="col-sm-2  text-right"><label for="loginGroupIds" class="control-label">부서 그룹</label></span>
									<div class="col-sm-10">
										<c:forEach items="${groupList}" var="result" varStatus="status">
										<label class="checkbox-inline"><form:checkbox path="loginGroupIdArr" value="${result.groupId}" /> <c:out value="${result.groupNm}"/></label>
										</c:forEach>
									</div>
								</div>
								<div class="form-group">
									<span class="col-sm-2  text-right"><label for="loginLevelId" class="control-label">부서 등급</label></span>
									<div class="col-sm-10">
										<form:select path="loginLevelId" class="form-control">
										<form:option value="0">+ 선택</form:option>
										<c:forEach var="result" items="${levelList}" varStatus="status">
										<form:option value="${result.levelId}"><c:out value="${result.levelNm}" /></form:option>
										</c:forEach>
										</form:select>
									</div>
								</div>
								<div class="bd-t mg-t-md pd-t-md"></div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="submit" class="btn btn-primary pd-l-lg pd-r-lg">저장</button>
									</div>
								</div>
							</fieldset>
							 -->
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$("#loginFailReset").on("click", function(){
			writeOrganizationDept.action = "/member/general.do?act=loginFailReset";
			writeOrganizationDept.submit();
	    });
	});
</script>