<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!-- page-wrapper Start -->
<div id="page-wrapper" class="gray-bg">
	<jsp:include page="/WEB-INF/jsp/cms/inc/top.jsp"/>
	<!-- Content Start -->
	<div class="wrapper wrapper-content" id="content">
	
<div class="row">
	<div class="col-lg-12">
	<div class="ibox float-e-margins">
			<div class="ibox-title">
				<h5 class="text-navy">
					메뉴 등록
					<small>아래의 내용을 입력해 주시기 바랍니다.</small>
				</h5>
			</div>
		</div>
	</div>
		
	
	<div class="col-lg-2">
		
		<div class="ibox-content">
			<div class="block-content">
				<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/js/plugins/dtree/dtree.css">
				<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/plugins/dtree/dtree.js"></script>
				<script type="text/javascript">
				var d = new dTree('d');
				d.add(<c:out value="${rootMenuId}"/>,-1,"홈", "?siteId=<c:out value='${searchSiteMenuVO.siteId}' />");
				<c:forEach var="result" items="${allSiteMenuList}" varStatus="status">
				<c:if test="${result.parntsMenuId > 0}">
				d.add(<c:out value='${result.menuId}' />, <c:out value='${result.parntsMenuId}' />, "<c:out value='${result.menuNm}' />", "?siteId=<c:out value='${result.siteId}' />&menuId=<c:out value='${result.menuId}' />", "", "", "");
				</c:if>
				</c:forEach>
				document.write(d);
				</script>
			</div>
			<div class="block-footer">
				<a href="${pageContext.request.contextPath}/admin/site/menuMove.do" class="btn btn-default"><i class="fa fa-arrows"></i> 메뉴이동</a>
			</div>
		</div>
	</div>
	<div class="col-lg-10">
		<div class="ibox-content">
			<!-- 
			<c:if test="${command == 'update'}">
			<ul class="nav nav-tabs">
				<li class="active"><a href="#">메뉴변경</a></li>
			    <li><a href="${CONTEXT_PATH}/site/content.do?siteId=<c:out value='${searchSiteMenuVO.siteId}'/>&amp;menuId=<c:out value='${searchSiteMenuVO.menuId}'/>">콘텐츠변경</a></li>
			</ul>
			</c:if>
			<c:if test="${searchSiteMenuVO.parntsMenuId < 1 && searchSiteMenuVO.menuId < 1}">
			<ul class="nav nav-tabs">
				<li class="active"><a href="#">메뉴변경</a></li>
			    <li><a href="${CONTEXT_PATH}/site/content.do?siteId=<c:out value='${searchSiteMenuVO.siteId}'/>">콘텐츠변경</a></li>
			</ul>
			</c:if>
			 -->
			<div class="block-head">
				<c:choose>
					<c:when test="${searchSiteMenuVO.parntsMenuId > 0}"><h1>서브메뉴 만들기</h1></c:when>
					<c:when test="${searchSiteMenuVO.menuId > 0}"><h1>메뉴 등록정보</h1></c:when>
					<c:otherwise><h1>최상위메뉴 만들기</h1></c:otherwise>
				</c:choose>
			</div>
			<div class="block-content">
				<form:form commandName="writeSiteMenu" action="${pageContext.request.contextPath}/admin/site/menuProc.do" class="form-horizontal" role="form" enctype="multipart/form-data">
					<input type="hidden" name="command" value="<c:out value='${command}'/>" />
					<input type="hidden" name="siteId" value="<c:out value='${searchSiteMenuVO.siteId}'/>" />
					<input type="hidden" name="menuId" value="<c:out value='${searchSiteMenuVO.menuId}'/>"/>
					<input type="hidden" name="parntsMenuId" value="<c:out value='${searchSiteMenuVO.parntsMenuId}'/>"/>
					<fieldset>
						<legend class="sr-only">메뉴관리</legend>
						<c:if test="${searchSiteMenuVO.menuId > 0 || searchSiteMenuVO.parntsMenuId > 0}">
						<div class="form-group">
							<span class="col-sm-2 text-right"><label class="control-label">상위메뉴</label></span>
							<div class="col-sm-10">
								<p class="form-control-static">
									홈
									<c:forEach var="result" items="${parntsSiteMenuList}" varStatus="status">
									&gt; <c:out value="${result.menuNm}" />
									</c:forEach>
								</p>
							</div>
						</div>
						</c:if>
						<div class="form-group">
							<span class="col-sm-2 text-right"><label for="menuNm" class="control-label">* 메뉴명</label></span>
							<div class="col-sm-10">
								<c:choose>
									<c:when test="${command == 'insert'}">
										<form:input path="menuNm" class="form-control required" />
										<div class="help-block">
											복수의 메뉴를 한번에 등록하시려면 메뉴명을 콤마(,)로 구분해 주세요. 보기)회사소개,커뮤니티,고객센터
										</div>
									</c:when>
									<c:otherwise>
										<div class="input-group">
											<form:input path="menuNm" class="form-control" />
											<c:if test="${writeSiteMenu.menuId > 0}">
											<span class="input-group-btn"><a href="?siteId=<c:out value='${writeSiteMenu.siteId}' />&amp;parntsMenuId=<c:out value='${writeSiteMenu.menuId}' />" class="btn btn-default btn-sm"><i class="fa fa-plus"></i> 하위메뉴등록</a></span>
											</c:if>
										</div>
									</c:otherwise>
								</c:choose>
								<c:if test="${writeSiteMenu.parntsMenuId == 1 || (command == 'insert' && searchSiteMenuVO.parntsMenuId == 0)}">
								<div>
									<label class="checkbox-inline"><form:checkbox path="mainMenuAt" value="Y"/> 메인메뉴인 경우 체크</label>
								</div>
								</c:if>
							</div>
						</div>
						<input type="hidden" name="template" value="default">
						<input type="hidden" name="layout" value="sub_layout.jsp">
						<!-- 
						<div class="form-group">
							
							<span class="col-sm-2 text-right"><label for="template" class="control-label">* 템플릿/레이아웃</label></span>
							<div class="col-sm-10">
								<form:select path="template" class="form-control required">
								<form:option value="">+ 템플릿 선택</form:option>
								<c:forEach var="result" items="${templateInfoList}" varStatus="status">
								<form:option value="${result.tplId}"><c:out value="${result.tplNm}" /></form:option>
								</c:forEach>
								</form:select>
								<form:select path="layout" class="mg-t-xs form-control required">
								<form:option value="">+ 레이아웃 선택</form:option>
								<c:forEach var="result" items="${templateFileList}" varStatus="status">
								<option value="<c:out value='${result.fileNm}' />" class="<c:out value='${result.tplId}'/>" <c:if test="${result.fileNm eq writeSiteMenu.layout && result.tplId eq writeSiteMenu.template}">selected="selected"</c:if>><c:out value="${result.fileDc}"/></option>
								</c:forEach>
								</form:select>
								<label class="checkbox-inline"><input type="checkbox" name="updateChldrnsLayout" value="Y" />하위메뉴 일괄 적용</label>
							</div>
							 
						</div>
						-->
						<div class="form-group">
							<span class="col-sm-2 text-right"><label class="control-label">* 메뉴구분</label></span>
							<div class="col-sm-10">
								<label class="radio-inline"><form:radiobutton path="menuGb" value="HTML" />HTML</label>
								<!-- <label class="radio-inline"><form:radiobutton path="menuGb" value="JSP" />JSP</label> -->
								<label class="radio-inline"><form:radiobutton path="menuGb" value="MENU_LINK" />메뉴연결</label>
								<label class="radio-inline"><form:radiobutton path="menuGb" value="BOARD" />게시판연결</label>
								<label class="radio-inline"><form:radiobutton path="menuGb" value="PROGRAM" />프로그램연결</label>
								<!--<label class="radio-inline"><form:radiobutton path="menuGb" value="LINK_IN" />내부링크</label>-->
								<!--<label class="radio-inline"><form:radiobutton path="menuGb" value="LINK_OUT" />외부링크</label>-->

								<div id="linkMenuLayer" class="menuGbLayer">
									<div class="input-group mg-t-sm">
										<span class="input-group-addon">메뉴선택</span>
										<form:select path="linkMenuId" class="form-control required">
										<c:forEach var="result" items="${treeSiteMenuList}" varStatus="status">
										<form:option value="${result.menuId}"><c:out value="${result.menuNm}" /></form:option>
										</c:forEach>
										</form:select>
									</div>
									<div class="input-group mg-t-xs">
										<span class="input-group-addon">추가파라미터</span>
										<form:input path="linkParam" id="linkMenuParm" class="form-control" />
									</div>
								</div>

								<div id="linkBoardLayer" class="menuGbLayer">
									<div class="input-group mg-t-sm">
										<span class="input-group-addon">게시판선택</span>
										<form:select path="linkBoardId" class="form-control required">
										<form:option value="">+ 선택</form:option>
										<c:forEach var="result" items="${boardList}" varStatus="status">
										<form:option value="${result.boardId}">[<c:out value='${result.siteId}'/>]<c:out value="${result.boardNm}" /></form:option>
										</c:forEach>
										</form:select>
									</div>
									<div class="input-group mg-t-xs">
										<span class="input-group-addon">게시판URL</span>
										<form:input path="linkUrl" id="linkBoardUrl" class="form-control mg-t-xs" />
									</div>
									<div class="input-group mg-t-xs">
										<span class="input-group-addon">추가파라미터</span>
										<form:input path="linkParam" id="linkBoardParm" class="form-control" />
									</div>
								</div>

								<div id="programUrlLayer" class="menuGbLayer">
									<div class="input-group mg-t-sm">
										<span class="input-group-addon">프로그램선택</span>
										<form:select path="userLinkUrl" id="userLinkUrl" class="form-control required">
										<form:option value="-1">직접입력</form:option>
										<c:forEach var="result" items="${programList}" varStatus="status">
										<form:option value="${result.programUrl}"><c:out value="${result.programNm}" /></form:option>
										</c:forEach>
										</form:select>
									</div>
									<div class="input-group mg-t-xs">
										<span class="input-group-addon">프로그램URL</span>
										<form:input path="linkUrl" id="linkProgramUrl" class="form-control" />
									</div>
									<div class="input-group mg-t-xs">
										<span class="input-group-addon">추가파라미터</span>
										<form:input path="linkParam" id="linkProgramParm" class="form-control" />
									</div>
								</div>

								<div id="linkUrlLayer" class="menuGbLayer">
									<div class="input-group mg-t-sm">
										<span class="input-group-addon">메뉴URL</span>
										<form:input path="linkUrl" class="form-control" />
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<span class="col-sm-2 text-right"><label class="control-label">링크타겟</label></span>
							<div class="col-sm-10">
								<label class="radio-inline">
									<form:radiobutton path="linkTarget" value="_SELF" />현재창
								</label>
								<label class="radio-inline">
									<form:radiobutton path="linkTarget" value="_BLANK" />새창
								</label>
								<!-- 
								<label class="radio-inline">
									<form:radiobutton path="linkTarget" value="_POPUP" />팝업
								</label> -->
								<div class="input-group mg-t-sm" id="popupParamLayer" style="display:none;">
									<span class="input-group-addon"><label for="popupParam">팝업옵션</label></span>
									<form:input path="popupParam" class="form-control" />
								</div>
							</div>
						</div>
						<!-- 
						<div class="form-group">
							<span class="col-sm-2 text-right"><label for="showCpyrgtAt" class="control-label">저작권표시여부</label></span>
							<div class="col-sm-10">
								<form:select path="showCpyrgtAt" class="form-control">
								<form:option value="N">표시안함</form:option>
								<form:option value="A">출처표시</form:option>
								<form:option value="B">출처표시+상업적이용금지</form:option>
								<form:option value="C">출처표시+변경금지</form:option>
								<form:option value="D">출처표시+상업적이용금지+변경금지</form:option>
								</form:select>
								<label class="checkbox-inline"><input type="checkbox" name="updateChldrnsCpyrgtAt" value="Y" />하위메뉴 일괄 적용</label>
							</div>
						</div> -->
						<!-- 
						<div class="form-group">
							<span class="col-sm-2 text-right"><label for="showSnsAt" class="control-label">SNS표시여부</label></span>
							<div class="col-sm-10">
								<form:select path="showSnsAt" class="form-control">
								<form:option value="Y">표시함</form:option>
								<form:option value="N">표시안함</form:option>
								</form:select>
								<label class="checkbox-inline"><input type="checkbox" name="updateChldrnsSnsAt" value="Y" />하위메뉴 일괄 적용</label>
							</div>
						</div>
						
						
						<div class="form-group">
							<span class="col-sm-2 text-right"><label for="appointStaffAt" class="control-label">콘텐츠 담당자 지정여부</label></span>
							<div class="col-sm-10">
								<form:select path="appointStaffAt" class="form-control">
								<form:option value="Y">지정함</form:option>
								<form:option value="N">지정안함</form:option>
								</form:select>
								<label class="checkbox-inline"><input type="checkbox" name="updateChldrnsMngStaff" value="Y" />하위메뉴 일괄 적용</label>
							</div>
						</div> 
						<div class="form-group" id="mngDeptLayer">
							<span class="col-sm-2 text-right"><label for="mngDeptId" class="control-label">콘텐츠 담당자</label></span>
							<div class="col-sm-10">
								<div class="input-group mg-b-xs">
									<span class="input-group-addon">부서</span>
									<form:select path="mngDeptId" class="form-control">
									<form:option value="">+ 부서를 선택해 주세요</form:option>
									<c:forEach var="result" items="${treeDeptList}" varStatus="status">
									<form:option value="${result.deptId}"><c:out value="${result.deptNm}" /></form:option>
									</c:forEach>
									</form:select>
								</div>
								<div class="input-group mg-t-sm mg-b-sm">
									<span class="input-group-addon">담당자</span>
									<form:select path="mngStaffId" class="form-control">
									<form:option value="">+ 담당자를 선택해 주세요</form:option>
									<c:forEach var="result" items="${staffList}" varStatus="status">
									<form:option value="${result.staffId}">[<c:out value="${result.deptNm}" />] <c:out value="${result.name}" /></form:option>
									</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
						<div class="form-group">
							<span class="col-sm-2 text-right"><label for="mngUserIds" class="control-label">컨텐츠 관리자 지정</label></span>
							<div class="col-sm-10">
								<form:input path="mngUserIds" class="form-control"/>
								<div class="help-block">
									이 컨텐츠에 대해서 수정권한을 별도로 부여할 회원이 있을경우 회원아이디를 콤마(,)로 구분해서 등록해 주세요.
								</div>
								<div class="clearfix">
								<label class="checkbox-inline"><input type="checkbox" name="updateChldrnsMngUserIds" value="Y" />하위메뉴 일괄 적용</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<span class="col-sm-2 text-right"><label for="showSatisAt" class="control-label">만족도조사 사용여부</label></span>
							<div class="col-sm-10">
								<form:select path="showSatisAt" class="form-control">
								<form:option value="N">사용안함</form:option>
								<form:option value="Y">사용함</form:option>
								</form:select>
								<label class="checkbox-inline"><input type="checkbox" name="updateChldrnsSatis" value="Y" />하위메뉴 일괄 적용</label>
							</div>
						</div>
						<div class="form-group">
							<span class="col-sm-2  text-right"><label class="control-label">접근제한 그룹</label></span>
							<div class="col-sm-10">
								<c:forEach items="${groupList}" var="result" varStatus="status">
								<label class="checkbox-inline"><form:checkbox path="limitGroupIdArr" value="${result.groupId}" /> <c:out value="${result.groupNm}"/></label>
								</c:forEach>
							</div>
						</div>
						<div class="form-group">
							<span class="col-sm-2  text-right"><label for="permitLevelId" class="control-label">접근허용 등급</label></span>
							<div class="col-sm-10">
								<form:select path="permitLevelId" class="form-control">
								<form:option value="0">+ 선택</form:option>
								<c:forEach var="result" items="${levelList}" varStatus="status">
								<form:option value="${result.levelId}"><c:out value="${result.levelNm}" /></form:option>
								</c:forEach>
								<form:option value="98">사이트관리자</form:option>
								<form:option value="99">최고관리자</form:option>
								</form:select>
							</div>
						</div>-->
						<div class="form-group">
							<span class="col-sm-2 text-right"><label for="showAt" class="control-label">출력여부</label></span>
							<div class="col-sm-10">
								<form:select path="showAt" class="form-control">
								<form:option value="Y">출력함</form:option>
								<form:option value="N">출력안함</form:option>
								</form:select>
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
						<div class="form-group">
							<span class="col-sm-2 text-right">
								<label for="imgText" class="control-label">서머리 택스트</label>
							</span>
							<div class="col-sm-10">
								<textarea name="summary" id="summary" class="form-control"><c:out value='${writeSiteMenu.summary}'/></textarea>
							</div>
						</div>
						<div class="form-group">
							<span class="col-sm-2 text-right">
								<label for="imageFile" class="control-label">서머리 이미지</label>
							</span>
							<div class="col-sm-8">
								<input type="file" name="imageFile" id="imageFile" class="form-control" accept="image/*"/>
								<form:input type="hidden" path="imgUrl"/>
							</div>
						</div>
						<c:if test="${writeSiteMenu.imgUrl ne null}">	
						<div class="form-group">
							<span class="col-sm-2 text-right">
								<label for="imageFile" class="control-label">저장된 서머리 이미지 </label>
							</span>
							<div class="col-sm-2 imgSaved" >
								<img alt="<c:out value='${writeSiteMenu.imgText}'/>" src="${writeSiteMenu.imgUrl}" style="width:100%;"/>
								<input type="checkbox" value="Y" name="fileDeleteYn"> 삭제
							</div>
						</div>
						</c:if>
						
						<div class="form-group">
							<span class="col-sm-2 text-right">
								<label for="imgText" class="control-label">서머리 이미지 대체택스트</label>
							</span>
							<div class="col-sm-10">
								<textarea name="imgText" id="imgText" class="form-control"><c:out value='${writeSiteMenu.imgText}'/></textarea>
							</div>
						</div>
						<div class="bd-t md-t-md pd-t-md"></div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-primary pd-l-lg pd-r-lg">저장</button>
								<c:if test="${command == 'update'}">
								<button type="button" class="btn btn-default pd-l-lg pd-r-lg" onclick="del('<c:out value='${writeSiteMenu.menuId}' />'); return false;">삭제</button>
								</c:if>
							</div>
						</div>
					</fieldset>
				</form:form>
			</div>
		</div>
	</div>

</div>
</div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.chained.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.chained.remote.min.js"></script>
<script type="text/javascript">


$(function(){

	$('#layout').chained('#template');

	$("#mngStaffId").remoteChained({
		parents : "#mngDeptId",
		url : "?act=getOrganizationStaffList"
	});

	function menuGbSelect(value) {

		$(".menuGbLayer").hide();
		$(".menuGbLayer").find("[name='linkUrl']").attr("disabled", true);
		$(".menuGbLayer").find("[name='linkParam']").attr("disabled", true);

		if (value != "<c:out value='${writeSiteMenu.menuGb}' />") {

			$(".menuGbLayer").find("[name='linkParam']").val('');

		} else {

			$(".menuGbLayer").find("[name='linkParam']").val("<c:out value='${writeSiteMenu.linkParam}' />");

		}

		switch(value) {

			case "MENU_LINK":
				$("#linkMenuLayer").show();
				$("#linkMenuLayer").find("[name='linkParam']").attr("disabled", false);
				break;

			case "BOARD":
				$("#linkBoardLayer").show();
				$("#linkBoardLayer").find("[name='linkUrl']").attr("disabled", false);
				$("#linkBoardLayer").find("[name='linkParam']").attr("disabled", false);
				break;

			case "PROGRAM":
				$("#programUrlLayer").show();
				$("#programUrlLayer").find("[name='linkUrl']").attr("disabled", false);
				$("#programUrlLayer").find("[name='linkParam']").attr("disabled", false);
				break;

			case "LINK_IN":
			case "LINK_OUT":
				$("#linkUrlLabel").text("링크URL");
				$("#linkUrlLayer").show();
				$("#linkUrlLayer").find("[name='linkUrl']").attr("disabled", false);
				break;
		}
	}
	
	$("input[name='menuGb']").change(function() {

		menuGbSelect($(this).val());
	});
	
	menuGbSelect("<c:out value='${writeSiteMenu.menuGb}' />");

	function showAtSelect(value) {
		$('#showAtLayer').hide();
		if (value == 'Y') {

			$('#showAtLayer').show();
		}
	}
	$("input[name='showAt']").change(function() {

		showAtSelect($(this).val());
	});
	showAtSelect("<c:out value='${writeSiteMenu.showAt}' />");

	function linkTargetSelect(value) {

		$("#popupParamLayer").hide();
		switch(value) {

			case "_POPUP":
				$("#popupParamLayer").show();
				$("#popupParam").val("<c:out value='${writeSiteMenu.popupParam}' />");
				break;

			default:
				$("#popupParam").val("");
				break;
		}
	}
	
	$("input[name='linkTarget']").change(function() {

		linkTargetSelect($(this).val());
	});
	linkTargetSelect("<c:out value='${writeSiteMenu.linkTarget}' />");

	function appointStaffAtSelect(value) {

		$('#mngDeptLayer').hide();
		if (value == 'Y') {

			$('#mngDeptLayer').show();
		}
	}
	$("select[name='appointStaffAt']").change(function() {

		appointStaffAtSelect($(this).val());
	});
	
	appointStaffAtSelect("<c:out value='${writeSiteMenu.appointStaffAt}' />");

	$("select[name='linkBoardId']").change(function() {

		$("#linkBoardUrl").val('/front/board/bbsList.do');
		$("#linkBoardParm").val('boardId='+$(this).val());
		
	});

	$("select[name='userLinkUrl']").change(function() {

		$("#linkProgramUrl").val($(this).val());
	});
	
	
});


function del(menuId){
	window.location.href = "${pageContext.request.contextPath}/admin/site/menuDel.do?siteId=flower&menuId="+menuId;
}
	window.onload = function() {
	    // Session에 저장된 lvl 값 가져오기
	    var userLevel = ${sessionScope.lvl};
	    
	    // lvl 값이 2일 때만 페이지 이동
	    if (userLevel === 2) {
	        window.location.href = "${pageContext.request.contextPath}/admin/sr/list.do";
	    } 
	}

</script>


	
