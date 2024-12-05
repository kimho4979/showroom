<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

<div class="row">
	<div class="col-lg-2">
		<div class="block">
			<div class="block-content">
				<link rel="stylesheet" href="<c:out value='${pageContext.request.contextPath}'/>/assets/js/plugins/dtree/dtree.css">
				<script type="text/javascript" src="<c:out value='${pageContext.request.contextPath}'/>/assets/js/plugins/dtree/dtree.js"></script>
				<script type="text/javascript">
				var d = new dTree('d');
				d.add("<c:out value='${rootDeptId}'/>",-1,"부서", "<c:url value='/organization/staff.do' />");
				<c:forEach var="result" items="${allDeptList}" varStatus="status">
				<c:if test="${not empty result.parntsDeptId}">
				d.add("<c:out value='${result.deptId}' />", "<c:out value='${result.parntsDeptId}' />", "<c:out value='${result.deptNm}' />", "?searchDeptId=<c:out value='${result.deptId}' />", "", "", "");
				</c:if>
				</c:forEach>
				document.write(d);
				</script>
			</div>
		</div>
	</div>
	<div class="col-lg-10">
		<div class="block">
			<ul class="nav nav-tabs">
			    <li><a href="<c:url value='/organization/dept.do' />?deptId=<c:out value='${searchOrganizationStaffVO.searchDeptId}' />">부서관리</a></li>
			    <li class="active"><a href="#">직원관리</a></li>
			</ul>
			<div class="block-head">
				<h2><c:out value="${deptVO.deptNm}" /> 직원 리스트</h2>
			</div>
			<div class="block-content">
				<form action="<c:url value='/organization/staff.do'/>" method="post" class="form-inline" role="form">
					<input type="hidden" name="searchDeptId" value="<c:out value='${searchOrganizationStaffVO.searchDeptId}'/>" />
					<fieldset>
						<legend class="sr-only">검색 폼</legend>
						<div class="form-group">
							<select name="searchCondition" id="searchCondition" class="form-control">
							<option value="deptNm" <c:if test="${searchOrganizationStaffVO.searchCondition == 'deptNm'}">selected="selected"</c:if>>부서명</option>
							<option value="name" <c:if test="${searchOrganizationStaffVO.searchCondition == 'name'}">selected="selected"</c:if>>성명</option>
							</select>
						</div>
						<div class="input-group">
							<input type="text" name="searchKeyword" id="searchKeyword" class="form-control" value="<c:out value='${searchOrganizationStaffVO.searchKeyword}'/>"/>
							<span class="input-group-btn">
								<button type="submit" class="btn btn-default"><i class="fa fa-search"></i> <spring:message code="common.search"/></button>
								<a href="<c:url value='/organization/staff.do?searchDeptId=${searchOrganizationStaffVO.searchDeptId}'/>" class="btn btn-default"><i class="fa fa-refresh"></i> <spring:message code="common.reset2"/></a>
							</span>
						</div>
					</fieldset>
				</form>
			</div>
			<form name="staffListForm" action="<c:url value='/organization/staff.do'/>" method="post" class="form-inline">
				<input type="hidden" name="act" value="" />
				<input type="hidden" name="change" value="" />
				<input type="hidden" name="returnQueryString" value="<c:out value='${searchOrganizationStaffVO.queryString}' escapeXml='false' />" />
				<div class="block-content np">
					<table class="table">
						<thead>
							<tr>
								<th scope="col" class="text-center"><input type="checkbox" onclick="set_checkbox(this.form, 'chks', this.checked)" /></th>
								<th scope="col" class="text-center">번호</th>
								<th scope="col" class="text-center">부서명</th>
								<th scope="col" class="text-center">직급</th>
								<th scope="col" class="text-center">성명</th>
								<th scope="col" class="text-center">전화번호</th>
								<th scope="col" class="text-center">로그인ID</th>
								<th scope="col" class="text-center">그룹</th>
								<th scope="col" class="text-center">등급</th>
								<th scope="col" class="text-center">상태</th>
								<th scope="col" class="text-center">관리</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="result" items="${resultList}" varStatus="status">
							<tr>
								<td class="text-center">
									<c:choose>
										<c:when test="${empty result.loginId}"><input type="checkbox" name="chks" value="<c:out value='${result.staffId}'/>" disabled="disabled"/></c:when>
										<c:otherwise><input type="checkbox" name="chks" value="<c:out value='${result.staffId}'/>" /></c:otherwise>
									</c:choose>
								</td>
								<td class="text-center"><c:out value="${(paginationInfo.totalRecordCount+1) - (searchOrganizationStaffVO.pageIndex-1) * searchOrganizationStaffVO.pageUnit - status.count}"/></td>
								<td class="text-center"><c:out value="${result.deptPath}" /></td>
								<td class="text-center"><c:out value="${result.rank}" /></td>
								<td class="text-center"><c:out value="${result.name}" /></td>
								<td class="text-center"><c:out value="${result.telNo}" /></td>
								<td class="text-center"><c:out value="${result.loginId}" /></td>
								<td class="text-center"><c:out value="${result.loginGroupIds}" /></td>
								<td class="text-center"><c:out value="${result.loginLevelId}" /></td>
								<td class="text-center">
									<c:choose>
										<c:when test="${result.loginStatus == 'P'}">승인</c:when>
										<c:when test="${result.loginStatus == 'A'}">승인대기</c:when>
										<c:otherwise></c:otherwise>
									</c:choose>
								</td>
								<td class="text-center">
									<a href="?act=write&amp;staffId=<c:out value='${result.staffId}'/>&amp;<c:out value='${searchOrganizationStaffVO.queryString}'/>" class="btn btn-default btn-xs">수정</a>
									<a href="javascript:post_delete('', 'act=delete&amp;staffId=<c:out value="${result.staffId}" />');" class="btn btn-default btn-xs">삭제</a>
								</td>
							</tr>
						</c:forEach>
						<c:if test="${resultList.size() == 0}">
							<tr>
								<td colspan="11" class="text-center"><spring:message code="info.nodata.msg"/></td>
							</tr>
						</c:if>
						</tbody>
					</table>
				</div>
				<div class="block-footer">
					<div class="clearfix">
						<div class="pull-left">
							<ul class="pagination pagination-sm">
							<ui:pagination paginationInfo="${paginationInfo}" type="default" jsFunction="${paginationQueryString}"/>
							</ul>
						</div>
						<div class="pull-right">
							<a href="?act=write&amp;<c:out value='${searchOrganizationStaffVO.queryString}'/>" class="btn btn-primary btn-sm pd-l-md pd-r-md"><spring:message code="common.create"/></a>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>