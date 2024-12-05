<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script type="text/javascript">
	<c:choose>
		<c:when test="${message == 'success.common.insert'}">
			alert("성공적으로 저장되었습니다.");
			document.location.href = "${pageContext.request.contextPath}/admin/organization/list.do?parntsDeptId=<c:out value='${searchOrganizationDeptVO.parntsDeptId}' />";
		</c:when>
		<c:when test="${message == 'success.common.update'}">
			alert("성공적으로 수정되었습니다.");
			document.location.href = "${pageContext.request.contextPath}/admin/organization/list.do?deptId=<c:out value='${searchOrganizationDeptVO.deptId}' />";
		</c:when>
		<c:when test="${message == 'success.common.move'}">
			alert("성공적으로 이동되었습니다.");
			document.location.href = "?";
		</c:when>
		<c:when test="${message == 'success.common.delete'}">
			alert("성공적으로 삭제되었습니다.");
			document.location.href = "${pageContext.request.contextPath}/admin/organization/list.do";
		</c:when>
		<c:otherwise>
			alert("오류가발생했습니다. 관리자에게 문의하세요.");
			history.back(-1);
		</c:otherwise>
	</c:choose>
</script>