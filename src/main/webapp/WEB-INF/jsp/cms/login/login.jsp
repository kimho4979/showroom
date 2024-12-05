<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : egovSampleList.jsp
  * @Description : Sample List 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2009.02.01            최초 생성
  *
  * author 실행환경 개발팀
  * since 2009.02.01
  *
  * Copyright (C) 2009 by MOPAS  All right reserved.
  */
  
  String userIp = request.getRemoteHost();
  System.out.println(userIp);
  
  if (userIp.equals("0:0:0:0:0:0:0:1")) {
		userIp = "127.0.0.1";
  }
  
  if(userIp!=null&&(userIp.equals("115.89.203.83")||userIp.equals("103.108.108.254")||userIp.equals("127.0.0.1"))){
		// 내부망 or 우림ip
		// 제거 아이피 : 218.239.230.30, 106.241.33.158
		// 신규 추가 아이피 : 115.89.203.83 
		
  }else{
	response.sendRedirect("/yfmc/");
  }
  
%>
<!DOCTYPE html>
<html>

<head>

    <jsp:include page="/WEB-INF/jsp/cms/inc/header.jsp"/>

</head>

<body class="gray-bg">

    <div class="middle-box text-center loginscreen animated fadeInDown">
        <div>
            <div>

                <h1 class="logo-name">aT</h1>

            </div>
            <h3>화훼온라인매매시스템</h3>
            
            <form class="m-t" role="form" action="${pageContext.request.contextPath}/admin/loginProc.do" method="POST">
                <div class="form-group">
                    <input type="text" id="userId" name="userId" class="form-control" placeholder="userId" required="required">
                </div>
                <div class="form-group">
                    <input type="password" id="password" name="password" class="form-control" placeholder="Password" required="required">
                </div>
                <div class="form-group">
			        <input type="text" id="otp" name="otp" class="form-control" placeholder="otp" required="required" maxlength="6" minlength="6">
			    </div>
                <button type="submit" class="btn btn-primary block full-width m-b">Login</button>
            </form>
            <p class="m-t"> <small>화훼온라인매매시스템 관리자페이지입니다.<br/> 접속아이피 : <%=userIp %></small> </p>
        </div>
    </div>

    <!-- Mainly scripts
    <script src="${pageContext.request.contextPath}/assets/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/bootstrap.js"></script> -->

</body>

</html>

