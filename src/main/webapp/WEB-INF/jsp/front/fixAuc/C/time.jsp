<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript">
function fn_timeSave(){
	console.log(1);
	var paramList = new Array();
	paramList.push({bunChk:"C",timeType:"1",strTime:$(".strTime1").val(),endTime:$(".endTime1").val()});
	paramList.push({bunChk:"C",timeType:"2",strTime:$(".strTime2").val(),endTime:$(".endTime2").val()});
	paramList.push({bunChk:"C",timeType:"3",strTime:$(".strTime3").val(),endTime:$(".endTime3").val()});
	console.log(paramList);
	$.ajax({
		data: {paramList : JSON.stringify(paramList)
		},
        type : "POST",
        url : "${pageContext.request.contextPath}/front/fixCAuc/updateTime.json",
        success : function(data){
            if(data.status == "sucess"){
            	alert("저장되었습니다.");
            	location.href = "${pageContext.request.contextPath}/front/fixCAuc/time.do";
            }else{
            	alert("시스템 오류로 인하여 실패하였습니다.");
            }
        }
    });
	
}
</script>

<!-- sub내용(S) -->
			<div class="sub_conts_in">
				<div class="tab_content pdt0">
					<!-- tab 내용01(S) --><!-- 판매관리(S)-->
					<div class="ti_01">
						<div class="info_box">
							<!-- 서브탭02(S) -->
							<div class="sub_tab">
								<ul class="devide_3">
									<c:if test="${nFloLoginVO ne null }">
									<li><a href="${pageContext.request.contextPath}/front/fixNAuc/time.do">절화</a></li>
									</c:if>
									<c:if test="${yFloLoginVO ne null }">
									<li><a href="${pageContext.request.contextPath}/front/fixYAuc/time.do">&nbsp;&nbsp;&nbsp;난&nbsp;&nbsp;&nbsp;</a></li>
									</c:if>
									<c:if test="${cFloLoginVO ne null }">
									<li><a href="${pageContext.request.contextPath}/front/fixCAuc/time.do" class="active">관엽</a></li>
									</c:if>
								</ul>
							</div>
							<!-- 서브탭02(E) -->

							<!-- 테이블03(S) -->
					<div class="table_type_03 bdtn">
						<table>
							<caption>info</caption>
							<colgroup>
								<col style="width:17%">
								<col style="width:33%">
							</colgroup>
							<tbody>
								<tr>
									<th class="tc">출하자 판매 접수시간</th>
									<td class="tc">
										<div class="time_box">
											<input type="text" placeholder="시간선택"  id="timeFrom" class="strTime1" value="${fixTimeList[0].strTime}">
											<label for="timeFrom"></label>
										</div>
										<span class="hyphen">~</span>
										<div class="time_box">
											<input type="text" placeholder="시간선택"  id="timeTo" class="endTime1" value="${fixTimeList[0].endTime}">
											<label for="timeTo"></label>
										</div>
									</td>
								</tr>
								<tr>
									<th class="tc">중도매인 요청 접수시간</th>
									<td class="tc">
										<div class="time_box">
											<input type="text" placeholder="시간선택"  id="timeFrom02" class="strTime2" value="${fixTimeList[1].strTime}">
											<label for="timeFrom02"></label>
										</div>
										<span class="hyphen">~</span>
										<div class="time_box">
											<input type="text" placeholder="시간선택"  id="timeTo02" class="endTime2" value="${fixTimeList[1].endTime}">
											<label for="timeTo02"></label>
										</div>
									</td>
								</tr>
								<tr>
									<th class="tc">중도매인 입찰 가능시간</th>
									<td class="tc">
										<div class="time_box">
											<input type="text" placeholder="시간선택"  id="timeFrom03" class="strTime3" value="${fixTimeList[2].strTime}">
											<label for="timeFrom03"></label>
										</div>
										<span class="hyphen">~</span>
										<div class="time_box">
											<input type="text" placeholder="시간선택"  id="timeTo03" class="endTime3" value="${fixTimeList[2].endTime}">
											<label for="timeTo03"></label>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<!-- 테이블03(E) -->

							<!-- 버튼박스(S) -->
							<div class="btn_box mt30">
								<a href="#!" class="btn_type_01 save" onclick="fn_timeSave();">저장</a>
							</div>
							<!-- 버튼박스(S) -->
						</div>
						
					</div>
					<!-- tab 내용01(E) --><!-- 판매관리(E)-->
				</div>
				<!-- sub탭(E) -->

			</div>
			<!-- sub내용(E) -->