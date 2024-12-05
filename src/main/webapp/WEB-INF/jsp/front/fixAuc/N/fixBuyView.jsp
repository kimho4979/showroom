<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt" %>

<script src="${pageContext.request.contextPath}/assets/js/plugins/slick/slick.min.js"></script>
<script src="${pageContext.request.contextPath}/js/fix.js"></script>


<!-- sub내용(S) -->
	<input type="hidden" id="fixDealSeq"  value="${result.fixDealSeq}"/>
	<input type="hidden" id="dealType"  value="${result.dealType}"/>
	<input type="hidden" id="unitPrice"  value="${result.unitPrice}"/>
	<input type="hidden" id="fixState"  value="${result.fixState}"/>
	
			<div class="sub_conts_in">
				<div class="info_box">

					<!-- 슬라이드 이미지(S) /uploads/fixAuc/N/-->
					
					<div class="slide_box web">
						<c:forEach items="${fixFileList}" var="file" varStatus="status">
							<span class="img_size">
								<img src="${pageContext.request.contextPath}/uploads/fixAuc/N/${file.thumbStreFileNm}" alt="사진_${result.pumName}">
							</span>
						</c:forEach>
					</div>
					
					<div class="slide_box mobile" style="text-align: center;" >
						<c:forEach items="${fixFileList}" var="file" varStatus="status">
							<span class="img_size">
								<img src="${pageContext.request.contextPath}/uploads/fixAuc/N/${file.thumbStreFileNm}" alt="사진_${result.pumName}">
							</span>
						</c:forEach>
					</div>
					<!-- 슬라이드 이미지(E) -->

					<!-- 테이블03(S) -->
					<div class="table_type_03">
						<table>
							<caption>info</caption>
							<colgroup>
								<col style="width:17%">
								<col style="width:33%">
								<col style="width:17%">
								<col style="width:33%">
							</colgroup>
							<tbody>
								<tr>
									<th class="tc">품목명</th>
									<td class="tc"><p class="txt_01">${result.mokName}</p></td>
									<th class="tc">품종명</th>
									<td class="tc"><p class="txt_01">${result.pumName}<c:if test="${result.twinCnt ne '0'}">/${result.twinCnt}</c:if></p></td>
								</tr>
								<tr>
									<th class="tc">출하자명<br/>(코드)</th>
									<td class="tc"><p class="txt_01 dib">${result.chulName}(${fn:substring(result.chulCode,0,4)} - ${fn:substring(result.chulCode,4,8)})</p></td>
									<th class="tc">판매단가</th>
									<td class="tc"><p class="txt_01">
													<c:if test="${result.dealType eq 'L'}">
														최저가
													</c:if>
													<c:if test="${result.dealType eq 'W'}">
														희망가
													</c:if>
													<c:if test="${result.dealType eq 'F'}">
														정가
									</c:if>/<fmt:formatNumber value="${result.unitPrice}" pattern="#,###" /></p></td>
								</tr>
								<tr>
									<th class="tc">등급</th>
									<td class="tc"><p class="txt_01">${result.chulLevelName}</p></td>
									<th class="tc">상자수</th>
									<td class="tc"><p class="txt_01"><fmt:formatNumber value="${result.boxCnt}" pattern="#,###" /></p></td>
								</tr>
								<tr>
									<th class="tc">총 속/분 수량</th>
									<td class="tc"><p class="txt_01"><fmt:formatNumber value="${result.sokCnt}" pattern="#,###" /></p></td>
									<th class="tc">입찰된 수량</th>
									<td class="tc"><p class="txt_01"><fmt:formatNumber value="${result.bidSokCnt}" pattern="#,###" /></p></td>
								</tr>
								<tr>
									<th class="tc">경매일자</th>
									<td class="tc">
										<p class="txt_01">
											${result.aucDate}
											<c:if test="${result.aucDate eq null}">
												-
											</c:if>
										</p>
									</td>
									<th class="tc">출하예정일</th>
									<td class="tc"><p class="txt_01">${result.chulDate}</p></td>
								</tr>
								<tr>
									<th class="tc">상태</th>
									<td class="tc" <c:if test="${result.mokCode ne '1005'}">colspan="3"</c:if>><p class="txt_01">
									<c:if test="${result.fixState eq '1'}">
										신청
								    </c:if>
								    <c:if test="${result.fixState eq '2'}">
										반려
								   </c:if>
								   <c:if test="${result.fixState eq '3'}">
										준비
								   </c:if>
								   <c:if test="${result.fixState eq '4'}">
										완료
								   </c:if>
								   <c:if test="${result.fixState eq '5'}">
										유찰
								   </c:if>
								   <c:if test="${result.fixState eq '6'}">
										부분유찰
								   </c:if>
								   <c:if test="${result.fixState eq '7'}">
										<c:choose>
											<c:when test="${result.fixStateTwo eq '9'}">
												입찰대기 
										    </c:when>
									   		<c:otherwise>
												입찰
								            </c:otherwise>
						    	   		</c:choose>
								   </c:if>
								   <c:if test="${result.fixState eq '8'}">
										마감
								   </c:if>
									</p></td>
									<c:if test="${result.mokCode eq '1005'}">
										<th class="tc">쌍대/송이</th>
										<td class="tc"><p class="txt_01">${result.twinCnt}</p></td>
									</c:if>
								</tr>
								<tr>
									<th class="tc">지역</th>
									<td class="tc" colspan="3"><p class="txt_01">${result.chulArea}</p></td>
								</tr>
								<tr>
									<th class="tc">상품설명</th>
									<td class="tc" colspan="3"><p class="txt_01"><pre>${result.itemText}</pre></p></td>
								</tr>
								<tr id="bid_cancel_div" style="display:none;">
									<th class="tc">취소사유</th>
									<td class="tc" colspan="3"><p class="txt_01" id="bid_cancel_text"></p></td>
								</tr>
								<tr id="bid_comp_div" style="display:none;">
									<th class="tc">낙찰상태</th>
									<td class="tc" colspan="3"><p class="txt_01" id="bid_comp_text"></p></td>
								</tr>
							</tbody>
						</table>
					</div>
					
					
					<!-- 테이블03(E) -->
					<div class="title_box mt30" id="bid_box"  <c:if test="${result.fixState ne '7'}">style="display:none;"</c:if>>
							
							<div class="fr"> 
								<p class="txt_unit" id="limitAmt">거래잔액 : 0원 </p>
							</div>
							<div class="fr"> 
								<p class="txt_unit" id="reqAmt"> 입찰금액 : 0원 / </p>
							</div>
							<div class="fr"> 
								<p class="txt_unit" id="fixAmt"> 요청금액 : 0원 / </p>
							</div>		
					</div>
					<!-- 버튼박스(S) -->
					
					<div class="table_type_06 devide_4" id="bid_table" <c:if test="${result.fixState ne '7'}">style="display:none;"</c:if>>
						<ul>
							<li>
								<div class="t_th">
									<span>중도매인</span>
								</div>
								<div class="t_td">
									<div class="sel_type_03 w100p">
										<select id="jCode" name="jCode" onchange="getTradeAmt('${result.fixDealSeq}');">
											<c:forEach items="${floList}" var="floVO" varStatus="status">
												<c:if test="${floVO.bunChk eq 'N'}">
													<c:if test="${code eq null}">
														<option value="${floVO.code}" <c:if test="${nFloLoginVO.chulCd eq  floVO.code}">selected="selected"</c:if>>${floVO.name} ( ${floVO.code} )</option>
													</c:if>
													<c:if test="${code ne null}">
														<option value="${floVO.code}" <c:if test="${code eq  floVO.code}">selected="selected"</c:if>>${floVO.name} ( ${floVO.code} )</option>
													</c:if>
												</c:if>
											</c:forEach>	
										</select>
									</div>
								</div>
							</li>
							<li>
								<div class="t_th">
									<span>입찰상자</span>
								</div>
								<div class="t_td">
									<input type="number" id="bidBoxCnt" class="w100p" placeholder="-" maxlength="2" oninput="maxLengthCheck(this);fn_boxCntKeyUp('',${result.boxCnt},${result.unitSok},'w');"><label for="bidBoxCnt"></label>
								</div>
							</li>
							<li>
								<div class="t_th">
									<span>입찰수량</span> 
								</div>
								<div class="t_td">
									<input type="number" id="bidSokCnt" readonly="readonly" class="w100p" placeholder="-" maxlength="4" oninput="maxLengthCheck(this)"><label for="bidSokCnt"></label>
								</div>
							</li>
							<li>
								<div class="t_th">
									<span>입찰단가</span>
								</div>
								<div class="t_td">
									<c:if test="${result.dealType ne 'F'}">
									<input type="number" id="bidUnitPrice" class="w100p" placeholder="-" maxlength="10" oninput="maxLengthCheck(this)"><label for="bidUnitPrice"></label>
									</c:if>
									<c:if test="${result.dealType eq 'F'}">
									<input type="number" id="bidUnitPrice" class="w100p" placeholder="-" maxlength="10" oninput="maxLengthCheck(this)" value="${result.unitPrice}"><label for="bidUnitPrice"></label>
									</c:if>
								</div>
							</li>
						</ul>
					</div>
					<div class="btn_box mt30">
						
						<a href="javascript:fn_bid();" class="btn_type_01 fix" id="bid_btn" <c:if test="${result.fixState ne '7'}">style="display:none;"</c:if>>입찰</a>
						
						<a href="javascript:fn_list();" class="btn_type_01 list" id="listBtn">목록</a>
					</div>
					<!-- 버튼박스(E) -->

				</div>

			</div>
			<!-- sub내용(E) -->
			
	

<script type="text/javascript">

function slide(imgCnt){
	if(imgCnt > 1){
		$('.slide_box').slick({
			 slide: 'span',
			 infinite: true,
	         slidesToShow: 1,
	         slidesToScroll: 1,
	         centerMode: true,
	         arrows: false,
	         responsive: [
	             {
	                 breakpoint: 1024,
	                 settings: {
	                     slidesToShow: 1,
	                     slidesToScroll: 1,
	                     infinite: true
	                 }
	             },
	             {
	                 breakpoint: 600,
	                 settings: {
	                     slidesToShow: 1,
	                     slidesToScroll: 1,
	                     infinite: true
	                 }
	             },
	             {
	                 breakpoint: 480,
	                 settings: {
	                     slidesToShow: 1,
	                     slidesToScroll: 1,
	                     infinite: true
	                 }
	             }
	         ]
		});
	}else {
		$('.slide_box.mobile span').css("width","100%");
	}
}


function getTradeAmt(fixDealSeq){
	var code = $("#jCode").prop("value");
	
	$.ajax({
		data:{
			code: code
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixNAuc/getLimitAmt.json",
        success : function(data){
           
           var limitAmt = data.result.limitAmt;
           var reqAmt = data.result.reqAmt;
           var fixAmt = data.result.fixAmt;
           
           $("#limitAmt").html("거래잔액 : "+comma(limitAmt)+"원");
           $("#reqAmt").html("요청금액 : "+comma(reqAmt)+"원/");
           $("#fixAmt").html("입찰금액 : "+comma(fixAmt)+"원/");
           
           getBidBuyInfo(fixDealSeq);
        }
    });
}

function getBidBuyInfo(fixDealSeq){
	var code = $("#jCode").prop("value");
	var dealType = $("#dealType").val();
	var unitPrice = $("#unitPrice").val();
	var fixStateTwo =  ${result.fixStateTwo};
	
	$.ajax({
		data:{
			code: code,
			fixDealSeq: fixDealSeq
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixNAuc/getBidBuyInfo.json",
        success : function(data){
           console.log(data);
           var bidBoxCnt = "";
           var bidSokCnt = "";
           var bidUnitPrice = "";
           
           $("#bid_cancel_div").css("display","none");
           $("#bid_comp_div").css("display","none");
           
           if(data.result != null){
        	   bidBoxCnt = data.result.bidBoxCnt;
   			   bidSokCnt = data.result.bidSokCnt;
   			   bidUnitPrice = data.result.bidUnitPrice;   
   			   
				if(data.result.bidAdminYn == 'Y'){
					$("#bid_btn").text("입찰");
					$("#bid_btn").attr("href","javascript:fn_bid();");
				}else{
					if(data.result.bidState == '5'){
						$("#bid_btn").text("취소된 입찰");
						$("#bid_btn").attr("href","javascript:alert('경매사에 의해 취소된 입찰입니다.');");
						$("#bid_cancel_div").css("display","");
						$("#bid_cancel_text").html(data.result.bidCancelText);
					}else{
						if(data.result.bidState == '2'){
							//낙찰
							$("#bid_comp_div").css("display","");
							$("#bid_comp_text").html("낙찰("+data.result.nakSokCnt+")");
						}else if(data.result.bidState == '3'){
							//패찰
							$("#bid_comp_div").css("display","");
							$("#bid_comp_text").html("패찰");
						}else if(data.result.bidState == '4'){
							//부분낙찰
							$("#bid_comp_div").css("display","");
							$("#bid_comp_text").html("부분낙찰("+data.result.nakSokCnt+")");
						}
						$("#bid_btn").text("입찰완료");
						$("#bid_btn").attr("href","javascript:alert('완료된 입찰입니다.');");
						
					}
				}
				
				if(data.result.bidAdminYn == 'Y' && fixStateTwo == 9){
					$("#bid_btn").css("display","none");
				}else{
					$("#bid_btn").css("display","");
				}
				$("#bid_table").css("display","");
				$("#bid_box").css("display","");
				
   			   
           }else{
        	   if($("#fixState").val() == '7'){
        		   if(fixStateTwo == 9){						
						$("#bid_btn").css("display","none");
					}else{
        		    	$("#bid_btn").css("display","");
        		    }
        	   }else{
        		    $("#bid_btn").css("display","none");
        	   }
        	   $("#bid_btn").text("입찰");
        	   $("#bid_btn").attr("href","javascript:fn_bid();");
        	   
        	   if(dealType == "F"){
            	   bidUnitPrice = unitPrice;
               }
           }
           
          
           
			$("#bidBoxCnt").val(bidBoxCnt);
			$("#bidSokCnt").val(bidSokCnt);
			$("#bidUnitPrice").val(bidUnitPrice);
			
			
           
        }
    });
}


function fn_bid(){
	var bidBoxCnt = $("#bidBoxCnt").val();
	var bidSokCnt = $("#bidSokCnt").val();
	var bidUnitPrice = $("#bidUnitPrice").val();
	var fixDealSeq = $("#fixDealSeq").val();
	var code = $("#jCode").prop("value");
	
	if(bidBoxCnt == "" || bidBoxCnt == null){
		alert("입찰 상자수량을 입력해주세요");
		return false;
	}
	
	if(bidSokCnt == ""  || bidSokCnt == null){
		alert("입찰 속/분 수량을 입력해주세요");
		return false;
	}
	
	if(bidUnitPrice == ""  || bidUnitPrice == null){
		alert("입찰단가를 입력해주세요");
		return false;
	}
	
	if(code == ""  || code == null){
		alert("중도매인을 선택해주세요");
		return false;
	}
	
	var code = $("#jCode").prop("value");
	
	$.ajax({
		data:{
			fixDealSeq: fixDealSeq,
			bidBoxCnt: bidBoxCnt,
			bidSokCnt: bidSokCnt,
			bidUnitPrice: bidUnitPrice,
			code: code
	        },
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixNAuc/fixBuyBid.json",
        success : function(data){
           if(data.result == 1){
        	   alert("정상적으로 입찰되었습니다");
           }else if(data.result == 2){
        	   alert("정상적으로 수정되었습니다");
           }else if(data.result == 3){
        	   alert("거래잔액이 부족합니다.");
           }else if(data.result == 4){
        	   alert("입찰 마감된 거래입니다.");
           }else if(data.result == 5){
        	   alert("완료된 입찰입니다.");
           }else {
        	   alert(data.message);
           }
           
           getTradeAmt(fixDealSeq);
        }
    });
}




function fn_list(){
	var jCode = $("#jCode").prop("value");
	window.location.href = "${pageContext.request.contextPath}/front/fixNAuc/fixBuyList.do?jCode="+jCode;
}




$(document).ready(function(){
	var fixDealSeq = $("#fixDealSeq").val();
	getTradeAmt(fixDealSeq);
	
	slide($(".slide_box.mobile span").size());
});

</script>			
			
			