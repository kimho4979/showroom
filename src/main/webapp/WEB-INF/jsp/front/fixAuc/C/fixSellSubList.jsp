<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
.td_add{
	background: #EAEAEA !important;
}


</style>

<script src="${pageContext.request.contextPath}/js/fix.js"></script>

<script type="text/javascript">

function fn_view(fixSubDealSeq){
	window.location.href = "${pageContext.request.contextPath}/front/fixCAuc/fixSellSubView.do?fixSubDealSeq="+fixSubDealSeq;
}

function fn_reg(){
	window.location.href = "${pageContext.request.contextPath}/front/fixCAuc/fixSellSubReg.do";
}


function fn_search(orderString){
	
	$("#frm").submit();
}


</script>




<!-- sub내용(S) -->
			<div class="sub_conts_in">

				<div class="tab_content pdt0">
					<!-- tab 내용01(S) --><!-- 판매(S)-->
					<div class="ti_01">


						<div class="info_box">

							<!-- 서브탭(S) -->
							<div class="sub_tab">
								<ul class="devide_3">
									<c:if test="${nFloLoginVO ne null }">
									<li><a href="${pageContext.request.contextPath}/front/fixNAuc/fixSellList.do" >절화</a></li>
									</c:if>
									<c:if test="${yFloLoginVO ne null }">
									<li><a href="${pageContext.request.contextPath}/front/fixYAuc/fixSellList.do">&nbsp;&nbsp;&nbsp;난&nbsp;&nbsp;&nbsp;</a></li>
									</c:if>
									<c:if test="${cFloLoginVO ne null }">
									<li><a href="${pageContext.request.contextPath}/front/fixCAuc/fixSellList.do">관엽</a></li>
									<c:if test="${fixCSubChulCheck eq true }">
									<li><a href="${pageContext.request.contextPath}/front/fixCAuc/fixSellSubList.do" class="active">관엽-분갈이</a></li>
									</c:if>
									</c:if>
								</ul>
							</div>
							<!-- 서브탭(E) -->

							<!-- 검색조건창(S) -->
							<form id="frm" name="searchFrm" method="post" action="${pageContext.request.contextPath}/front/fixCAuc/fixSellSubList.do" >
							
							<div class="condition_box">
								<div class="search_box">
									<ul class="sb_line">
										<li>
											<p class="sb_title">품종명</p>
											<div class="sb_data">
												<div class="ip_type_01 w100p">
													<input type="text" id="searchPumCode" name="searchPumCode" value="<c:out value="${paramMap.searchPumCode}"/>" placeholder="검색어를 입력하세요"><label for="searchPumCode"></label>
												</div>
											</div>
										</li>
										<li>
											<p class="sb_title">사용여부</p>
											<div class="sb_data">
												<div class="sel_type_01 w150">
													<select id="searchUseYn" name="searchUseYn">
														<option value="" <c:if test="${paramMap.searchUseYn eq null}">selected="selected"</c:if>>전체</option>
														<option value="Y" <c:if test="${paramMap.searchUseYn eq 'Y'}">selected="selected"</c:if>>사용</option>
														<option value="N" <c:if test="${paramMap.searchUseYn eq 'N'}">selected="selected"</c:if>>미사용</option>
													</select>
													<label for="searchUseYn"></label>
												</div>
											</div>
										</li>
									</ul>
									<a href="javascript:fn_search();" class="btn_search">검색</a>
								</div>
							</div>
							</form>
							<!-- 검색조건창(E) -->

							<!-- 검색조건버튼(S) -->
							<div class="btn_box tc">
								<a href="#!" class="btn_condition close">검색조건 닫기<span class="img_close"><img src="${pageContext.request.contextPath}/img/ico_close.png" alt="닫기아이콘"></span></a>
								<a href="#!" class="btn_condition open">검색조건 열기<span class="img_close"><img src="${pageContext.request.contextPath}/img/ico_close.png" alt="닫기아이콘"></span></a>
							</div>
							<!-- 검색조건버튼(E) -->

						</div>
						
						<!-- 버튼박스(S) -->
						<div class="btn_box mt30">
							<a href="javascript:fn_reg();" class="btn_type_01 gray">등록</a>
						</div>
						<!-- 버튼박스(S) -->

						<div class="card_box mt30">
							<ul>
								<c:forEach items="${resultList}" var="result" varStatus="status">
								<li onclick="fn_view('${result.fixSubDealSeq}');" style="cursor: pointer;">
									<div class="card">
										<span class="c_img">
											<img src="${pageContext.request.contextPath}${result.thumbPath}" alt="이미지_${result.pumName}">
										</span>
										<div class="c_info">
											<h4 class="c_tit">${result.pumName}</h4>
											<p class="c_cost">판매단가 : <fmt:formatNumber value="${result.unitPrice}" pattern="#,###" /></p>
										</div>
										<c:if test="${result.useYn eq 'Y'}">
										<p class="c_use">사용</p>
										</c:if>
										<c:if test="${result.useYn ne 'Y'}">
										<p class="c_use no">미사용</p>
										</c:if>
									</div>
								</li>
								</c:forEach>
							</ul>
						</div>
						<c:if test="${fn:length(resultList) eq 0 }">
								<p class="tc">등록된 데이터가 없습니다.</p>
						</c:if>

					</div>
					<!-- tab 내용01(E) --><!-- 판매(E)-->
				</div>
				<!-- sub탭(E) -->

			</div>
			<!-- sub내용(E) -->
