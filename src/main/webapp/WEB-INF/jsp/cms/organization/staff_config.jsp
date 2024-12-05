<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="row">
	<div class="col-lg-12">	
		<div class="block">
			<ul class="nav nav-tabs">
			    <li><a href="<c:url value='/member/general.do?mberSttus=P' />">회원목록(승인)</a></li>
			    <li><a href="<c:url value='/member/general.do?mberSttus=A' />">회원목록(가입대기)</a></li>
			    <li><a href="<c:url value='/member/general.do?mberSttus=D' />">회원목록(탈퇴)</a></li>			    
			    <li class="active"><a href="#">환경설정</a></li>
			</ul>
			<div class="block-content">
				<form:form commandName="writeMemberConfig" action="?act=write" class="form-horizontal" role="form">
					<input type="hidden" name="command" value="<c:out value='${command}' />" />
					<fieldset>
						<legend class="sr-only">환경설정 관리</legend>
						<div class="form-group">
							<span class="col-sm-2  text-right"><label for="joinState" class="control-label">회원가입 작동상태</label></span>
							<div class="col-sm-10">
								<form:select path="joinState" class="form-control">
								<form:option value="RUN">ㆍ회원가입 작동</form:option>
								<form:option value="STOP">ㆍ회원가입 중단</form:option>
								</form:select>
							</div>
						</div>
						<div class="form-group">
							<span class="col-sm-2  text-right"><label for="joinGroupId" class="control-label">가입시 소속그룹</label></span>
							<div class="col-sm-10">
								<form:select path="joinGroupId" class="form-control required">
								<c:forEach var="result" items="${groupList}" varStatus="status">
								<form:option value="${result.groupId}"><c:out value="${result.groupNm}" /></form:option>
								</c:forEach>
								</form:select>								
							</div>
						</div>
						<div class="form-group">
							<span class="col-sm-2  text-right"><label for="joinLevelId" class="control-label">가입시 회원등급</label></span>
							<div class="col-sm-10">
								<form:select path="joinLevelId" class="form-control required">
								<c:forEach var="result" items="${levelList}" varStatus="status">
								<form:option value="${result.levelId}"><c:out value="${result.levelNm}" /></form:option>
								</c:forEach>
								<form:option value="99">최고관리자</form:option>
								</form:select>								
							</div>
						</div>
						<div class="form-group">
							<span class="col-sm-2  text-right"><label for="joinConfirm" class="control-label">가입시 승인처리</label></span>
							<div class="col-sm-10">
								<form:select path="joinConfirm" class="form-control">
								<form:option value="DIRECT_CONFIRM">ㆍ즉시승인</form:option>
								<form:option value="ADMIN_CONFIRM">ㆍ관리자확인 후 승인</form:option>
								</form:select>
							</div>
						</div>
						<div class="form-group">
							<span class="col-sm-2  text-right"><label for="leaveDeleteAt" class="control-label">탈퇴시 승인처리</label></span>
							<div class="col-sm-10">
								<form:select path="leaveDeleteAt" class="form-control">
								<form:option value="DIRECT_CONFIRM">ㆍ즉시승인</form:option>
								<form:option value="ADMIN_CONFIRM">ㆍ관리자확인 후 승인</form:option>
								</form:select>
							</div>
						</div>						
						<div class="form-group">
							<span class="col-sm-2  text-right"><label for="useLimitedId" class="control-label">사용제한 아이디</label></span>
							<div class="col-sm-10">
								<form:input path="useLimitedId" class="form-control" />
								<div class="help-block">
								사용을 제한하려는 아이디를 콤마(,)로 구분해서 입력해 주세요.
								</div>
							</div>
						</div>
						<div class="form-group">
							<span class="col-sm-2  text-right"><label for="joinAgreeAt" class="control-label">이용약관/개인정보</label></span>
							<div class="col-sm-10">
								<form:select path="joinAgreeAt" class="form-control">
								<form:option value="Y">ㆍ동의얻음</form:option>								
								<form:option value="N">ㆍ생략</form:option>
								</form:select>
							</div>
						</div>
						<div class="form-group">
							<span class="col-sm-2  text-right"><label for="joinRealnameAt" class="control-label">실명인증</label></span>
							<div class="col-sm-10">
								<form:select path="joinRealnameAt" class="form-control">
								<form:option value="Y">ㆍ인증함</form:option>								
								<form:option value="N">ㆍ생략</form:option>
								</form:select>
							</div>
						</div>
						<div class="form-group">
							<span class="col-sm-2  text-right"><label for="findRealnameAt" class="control-label">비밀번호찾기시 실명인증</label></span>
							<div class="col-sm-10">
								<form:select path="findRealnameAt" class="form-control">
								<form:option value="Y">ㆍ실명인증</form:option>								
								<form:option value="N">ㆍ이메일인증</form:option>
								</form:select>
							</div>
						</div>						
						<div class="form-group">
							<span class="col-sm-2  text-right"><label for="serviceCn" class="control-label">이용약관</label></span>
							<div class="col-sm-10">
								<form:textarea path="serviceCn" class="form-control"/>
							</div>
						</div>
						<div class="form-group">
							<span class="col-sm-2  text-right"><label for="privacyCn" class="control-label">개인정보취급방침</label></span>
							<div class="col-sm-10">
								<form:textarea path="privacyCn" class="form-control"/>
							</div>
						</div>
						<div class="form-group">
							<span class="col-sm-2  text-right"><label for="joinGubunAt" class="control-label">회원구분</label></span>
							<div class="col-sm-10">
								<form:select path="joinGubunAt" class="form-control">
								<form:option value="Y">ㆍ구분함</form:option>								
								<form:option value="N">ㆍ구분안함</form:option>
								</form:select>
							</div>
						</div>
						<div class="bd-t mg-t-md pd-t-md"></div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-primary pd-l-lg pd-r-lg"><spring:message code="common.save" /></button>
							</div>
						</div>								
					</fieldset>
				</form:form>			
			</div>
		</div>					
	</div>
</div>

<script type="text/javascript" src="/core/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="/core/js/ckeditor/adapters/jquery.js"></script>
<script type="text/javascript"> 
$(function()
{	
	$("#writeMemberConfig").validate();	
	$("#serviceCn").ckeditor();
	$("#privacyCn").ckeditor();
});
</script>