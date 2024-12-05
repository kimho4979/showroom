<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script type="text/javascript">
	<c:choose>
		<c:when test="${result == '1'}">
			alert("정상적으로 저장되었습니다.");
			document.location.href = "${pageContext.request.contextPath}/admin/board/list.do?boardId=${boardId}";
		</c:when>
		<c:otherwise>
			alert("오류가발생했습니다.");
			history.back(-1);
		</c:otherwise>
	</c:choose>
</script>