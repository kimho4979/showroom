<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script type="text/javascript">
	<c:choose>
		<c:when test="${result == '1'}">
			alert("${message}");
			document.location.href = "${pageContext.request.contextPath}${returnUrl}";
		</c:when>
		<c:when test="${result == '2'}">
			document.location.href = "${pageContext.request.contextPath}${returnUrl}";
		</c:when>
		<c:when test="${result == '9'}">
			alert("${message}");
			history.back(-1);
		</c:when>
		<c:otherwise>
			alert("오류가발생했습니다.");
			history.back(-1);
		</c:otherwise>
	</c:choose>
</script>