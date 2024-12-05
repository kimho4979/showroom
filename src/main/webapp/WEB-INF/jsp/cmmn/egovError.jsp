<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Error</title>
</head>
<%
String msg = (String)request.getAttribute("msg");
if (!msg.equals("noDataFromSr")) {
    msg = "fail.common.msg";
} else {
    out.print("<script>alert('" + "모두 반려되어 계약서 데이터가 없습니다." + "');  location.href=\'/yfmc/front/main.do\';  </script>");
}
%>

<body>
    <spring:message code='fail.common.msg' />
</body>
</html>