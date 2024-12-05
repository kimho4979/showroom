<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Expires" content="-1">
    <meta charset="UTF-8">
    <style type="text/css">
.container{
   /* background-color: #fff;
   border: 1px solid black;
   margin: 1em auto;
   width: 210mm; */
   font-size: 9pt;
}
.header{
  /* position: relative;
  margin-top: 20mm;
  margin-right: 20mm;
  margin-bottom: 15mm;
  margin-left: 20mm; */
}
.page{
    /* padding-top: 15mm;
    padding-bottom: 15mm; */
}
.title1{
    margin: 0pt 0pt 0pt 0pt;
  text-align: center;
  text-indent: 0pt;
  min-height: 1.6em;
}
.normal1{
  margin: 0pt 0pt 0pt 0pt;
  text-align: justify;
  text-indent: 0pt;
  min-height: 1.6em;
}
.table1{
  /* width: 167.98mm; */
  width: 80%;
  margin-left:auto;
  margin-right:auto;
  border-collapse: collapse;
  border-top: 1px solid #000000;
  border-right: 1px solid #000000;
  border-bottom: 1px solid #000000;
  border-left: 1px solid #000000;
  font-size: 9pt;
}
td{
  padding: 5px;
  border-top: 1px solid #000000;
  border-right: 1px solid #000000;
  border-bottom: 1px solid #000000;
  border-left: 1px solid #000000;}
p.normal{
  margin: 0pt 0pt 0pt 0pt;
  text-align: center;
  text-indent: 0pt;
  min-height: 1.6em;
    
}

</style>


<title>전시실 임대차 계약서</title>
</head>
<body>
<!-- Start your code here -->
<% // 포맷팅을 위한 자바 코드 

List<Map<String,Object>> result  = (List<Map<String,Object>>)request.getAttribute("resultList");
SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
SimpleDateFormat _formatter = new SimpleDateFormat("yyyy년 MM월 dd일");
Date dateS = formatter.parse(result.get(0).get("rsrvDtStart").toString());
Calendar cal = Calendar.getInstance();
cal.setTime(dateS);
cal.add(Calendar.DATE, -7);
String dDay = _formatter.format(cal.getTime());            

%>
<div class="container">
    <div class="header">
        <div class="page">
            <p class="title1" style="font-size:15pt;font-weight:bold;">전시실 임대차 계약서</p>
            
            <table class="table1" style="margin-top: 20px;">
                <colgroup>
                    <col width="20%"> 
                    <col width="20%"> 
                    <col width="20%"> 
                    <col width="20%"> 
                    <col width="20%"> 
                </colgroup>
                <tbody>
                    <tr>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">행사명</td>
                        <td colspan="5" style="text-align: left;" id="evntNmInpt"><c:out value="${resultList[0].evntNm}"></c:out></td>
                    </tr>
                    
                    <tr>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">임대일</td>
                        <td colspan="1" style="text-align: center;background-color: #e9cdf7;color:black;">임대시간</td>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">임대장소</td>
                        <td colspan="1" style="text-align: center;background-color: #e9cdf7;color:black;">세팅종류</td>
                        <td colspan="1" style="text-align: center;background-color: #e9cdf7;color:black;">할인</td>
                    </tr>
                    <c:forEach var="item" items="${resultList}">
                    <tr> <!-- 배열처리  -->
                        <td colspan="1" style="text-align:center" id="rsrvDtInpt">
                            <fmt:parseDate value="${item.rsrvDtStart}" var="rsrvDtStart" pattern="yyyyMMdd" />
                            <fmt:formatDate value="${rsrvDtStart}" pattern="yyyy년" /><br>
                            <fmt:formatDate value="${rsrvDtStart}" pattern="MM월 dd일" />

                        </td>
                        <td colspan="1" style="text-align: center;" id="evntDtInpt">${item.evntDt}</td>
                        <td colspan="1" style="text-align:center" id="hallTypeInpt">${item.hallType}</td>
                        <td colspan="1" style="text-align: center;" id="btchStylInpt">${item.btchStyl}</td>
                        <td colspan="1" style="text-align: center;" id="etcInpt"><fmt:formatNumber type="number" maxFractionDigits="3" value="${item.discount}" ></fmt:formatNumber>원</td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
            <p class="normal1" style="font-size:12pt;font-weight:bold;margin-top:20px"></p>
			<%
                String telNo = "";
                String faxNo = "";
                String comRgstNo = "";
                
				System.out.println("result : " + result.get(0));
                 		
                for(Map<String,Object> map : result){
                	
                    Object telNoObj = map.get("telNo");
                    if(telNoObj instanceof String && String.valueOf(telNoObj) != "null") {
                  		telNo = String.valueOf(telNoObj).replaceAll("(^02.{0}|^01.{1}|^0.{2})([0-9]{3,4})([0-9]{4})", "$1-$2-$3");
                    }
                    Object faxNoObj = map.get("faxNo");
                    if(faxNoObj instanceof String && String.valueOf(faxNoObj) != "null") {
                    	faxNo = String.valueOf(faxNoObj).replaceAll("(^02.{0}|^01.{1}|^0.{2})([0-9]{3,4})([0-9]{4})", "$1-$2-$3");
                    }
                    Object comRgstNoObj = map.get("comRgstNo");
                    if(comRgstNoObj instanceof String && String.valueOf(comRgstNoObj) != "null") {
                    	comRgstNo = String.valueOf(comRgstNoObj).replaceAll("(^[0-9]{3})([0-9]{2})([0-9]{5})", "$1-$2-$3");
                    }
                    
                }
            %>
            <table class="table1">
                <colgroup>
                    <col width="25%"> 
                    <col width="25%"> 
                    <col width="25%"> 
                    <col width="25%"> 
                </colgroup>
                <tbody>
                    <tr>
                        <td colspan="1" style="text-align:left;background-color: #e9cdf7;color:black;">임차인</td>
                        <td colspan="1" style="text-align:left" id="rprsntrNmInpt"><c:out value="${resultList[0].rprsntrNm}"></c:out></td>
                        <td colspan="1" style="text-align:left;background-color: #e9cdf7;color:black;">사업자등록번호</td>
                        <td colspan="1" style="text-align:left" id="comRgstNoInpt"><%=comRgstNo%></td>
                    </tr>
                    <tr>
                        <td colspan="1" style="text-align:left;background-color: #e9cdf7;color:black;">대표자</td>
                        <td colspan="1" style="text-align:left" id="rprsntrNm2Inpt"><c:out value="${resultList[0].rprsntrNm}"></c:out></td>
                        <td colspan="1" style="text-align:left;background-color: #e9cdf7;color:black;">담당자</td>
                        <td colspan="1" style="text-align:left" id="mngrNmInpt"><c:out value="${resultList[0].mngrNm}"></c:out></td>
                    </tr>
                    <tr>
                        <td colspan="1" style="text-align:left;background-color: #e9cdf7;color:black;">업태</td>
                        <td colspan="1" style="text-align:left" id="bizTypeInpt"><c:out value="${resultList[0].bizType}"></c:out></td>
                        <td colspan="1" style="text-align:left;background-color: #e9cdf7;color:black;">종목</td>
                        <td colspan="1" style="text-align:left" id="bizType2Inpt"><c:out value="${resultList[0].bizType2}"></c:out></td>
                    </tr>
                    <tr>
                        <%-- <td colspan="1" style="text-align:left;background-color: #e9cdf7;color:black;">전화번호</td>
                        <td colspan="1" style="text-align:left" id="telNoInpt"><%=telNo%></td> --%>
                        <td colspan="1" style="text-align:left;background-color: #e9cdf7;color:black;">유선전화번호</td>
                        <td colspan="1" style="text-align:left" id="faxNoInpt"><%=faxNo%></td>
                        <td colspan="1" style="text-align:left;background-color: #e9cdf7;color:black;">이메일</td>
                        <td colspan="1" style="text-align:left" id="emailInpt"><c:out value="${resultList[0].email}"></c:out></td>
                    </tr>
                    <tr>  
                        <td colspan="1" style="text-align:left;background-color: #e9cdf7;color:black;">휴대전화번호</td>
                        <td colspan="3" style="text-align:left" id="telNo2Inpt"><%=telNo%></td>
                        
                    </tr>
                    <tr>
                        <td colspan="1" style="text-align:left;background-color: #e9cdf7;color:black;">주소</td>
                        <td colspan="3" style="text-align:left" id="addressInpt">
                            <c:out value="${resultList[0].address}" />
                            <br>
                            <c:out value="${resultList[0].addressDtl}" />
                        </td>
                    </tr>
                </tbody>
            </table>
            <%  // 임대료,관리비,장비사용료 계산 로직 by java code 
                int rentPay = 0;
                int eqpmntPay = 0;
                int totalPay = 0;
                int mngPay = 0;
                int rentNmngPay = 0;
                int discount = 0;
                for(Map<String,Object> map : result){
                    System.out.println("map : " + map.get("discount"));
                    int _eqpmntPay=0;
                    Object rentPayObj = map.get("rentPay");
                    if(rentPayObj instanceof Number) {
                        rentPay = ((Number)rentPayObj).intValue();
                    }
                    
                    Object discountObj = map.get("discount");
                    if(discountObj instanceof Number) {
                        discount = ((Number)discountObj).intValue();
                    }

                    Object eqpmntPayObj = map.get("eqpmntPay");
                    if(eqpmntPayObj instanceof Number) {
                        _eqpmntPay = ((Number)eqpmntPayObj).intValue();
                    }
                    
                    Object mngPayObj = map.get("mngPay");
                    if(mngPayObj instanceof Number) {
                        mngPay = ((Number)mngPayObj).intValue();
                    }
                    
                    rentNmngPay += (rentPay + mngPay) - discount;
                    eqpmntPay += _eqpmntPay;
                    System.out.println("eqpmntPay : " + eqpmntPay);
                    
                }
                totalPay = rentNmngPay + eqpmntPay;
                NumberFormat numberFormat = NumberFormat.getNumberInstance();
                String formattedRentNmngPay = numberFormat.format(rentNmngPay);
                String formattedEqpmntPay = numberFormat.format(eqpmntPay);
                /////////totalPay = (int)((totalPay) + (totalPay * 0.1));
                System.out.println("totalPay : " + totalPay);
                System.out.println("discount : " + discount);
                String formattedTotalPay = numberFormat.format(totalPay);
                
                

            %>
            
            <p class="normal1" style="font-size:12pt;font-weight:bold;margin-top:20px"></p>
              <table class="table1">
                <colgroup>
                    <col width="16.6%"> 
                    <col width="16.6%"> 
                    <col width="16.6%"> 
                    <col width="16.6%"> 
                    <col width="16.6%"> 
                    <col width="16.6%"> 
                </colgroup>
                <tbody>
                    <tr>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">임대료<br>(관리비 포함)</td>
                        <td colspan="1" style="text-align:left" id="rentNmngPayInpt"><%=formattedRentNmngPay%>원</td>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">장비사용료</td>
                        <td colspan="1" style="text-align:left" id="eqpmntPayInpt"><%=formattedEqpmntPay%>원</td>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">합계<br>(VAT 포함)</td>
                        <td colspan="1" style="text-align:left" id="totalPayInpt"><%=formattedTotalPay%>원</td>
                    </tr>
                    <tr>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">납입일정</td>
                        <td colspan="5" style="text-align:left" id="dDayInpt"><%=dDay%>까지</td>
                    </tr>
                    <tr>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">납입방법</td>
                        <td colspan="5" style="text-align:left"><p> ◉ 계좌입금 : 농협은행 1087-17-000048(예금주 : 한국농수산식품유통공사 화훼사업센터)</p><p>※ 예약자명으로 입금 시 별도의 서류를 제출하여야 하므로, <strong style="font-size:medium;color:magenta">반드시 "회사명"</strong>으로 입금해주시기 바랍니다.</p></td>
                    </tr>
                </tbody>
            </table>


            <dl style="margin-left: auto;margin-right: auto;width: 80%;margin-top: 20px;">
                <dt>【기본약정사항 및 이용안내】</dt>
                <dt style="margin-top: 20px;">◉ 임대인의 "화훼사업센터 전시실 운영관리지침"은 이 계약의 약관으로 봅니다. 임차인은 동 지침을 충분히 숙지 및 준수하여야 합니다.</dt>
            </dl>

            <dl style="margin-left: auto;margin-right: auto;width: 80%;">
                <dt>◉ 계약체결</dt>
                <dd>- 임차인은 회의실 행사예정일로부터 7일전까지 또는 임대차 계약서의 임대료 납부일까지 임대료전액을(VAT포함) 납입하여야 합니다. 임대료 미납부 시 예약내역은 자동 취소됩니다.</dd>
            </dl>

            <dl style="margin-left: auto;margin-right: auto;width: 80%;">
                <dt>◉ 이용 시간</dt>
                <dd>- 개방 시간 : 임대 시작 ~ 임대 종료 시 까지</dd>
                <dd>- 임차인은 준비(반입) 및 정리(철수)시간을 고려하여 임대하여야 하며, 임대시간을 초과하여 사용 시 추가사용료가 부과 될 수 있습니다.</dd>
            </dl>    
            
            <dl style="margin-left: auto;margin-right: auto;width: 80%;">
                <dt>◉ 회의실 운영</dt>
                <dd>- 기본세팅(강의식 세미나테이블) 이외 좌석배치 필요 시 화훼사업센터와 사전 협의한 사항에 한하여 사전세팅 서비스를 제공하며, 행사 중 좌석배치 변경 필요시에는 임차인이 직접 하여야 합니다.</dd>
                <dd>- 화훼사업센터는 임차인에게 기본 장비를 무료로 제공(컴퓨터)하며, 기타 추가 장비 사용 시 별도의 장비임대료가 부과됩니다.</dd>
                <dd>- 임차인은 참석자 간 감염병 확산 예방을 위하여 화훼사업센터가 요청하는 방역 관련 사항을 반드시 숙지하고 협조하여야 합니다.</dd>
            </dl>

            <dl style="margin-left: auto;margin-right: auto;width: 80%;">
                <dt>◉ 주차</dt>
                <dd>- 화훼사업센터는 임차인에게 종일주차권(2매)을 무료로 제공하며, 추가로 필요 시 별도 구매하여야 합니다.</dd>
                <dd>- 주차권 구매장소 : 주차관리실(평일)</dd>
                <dd>- 주차관리실 전화번호 : 02-576-1686</dd>
            </dl>

            <dl style="margin-left: auto;margin-right: auto;width: 80%;">
                <dt>◉ 식음료 반입</dt>
                <dd>- 전시실 내 음식물 반입 금지가 원칙이나, 행사 성격 상 필요에 의한 반입시 화훼사업센터의 사전 승인을 받아야합니다.</dd>
            </dl>

            <dl style="margin-left: auto;margin-right: auto;width: 80%;">
                <dt>◉ 사인물</dt>
                <dd>- 현수막, 배너, 포스터 등 인쇄물은 지정된 위치에만 설치 가능하며, 접착제(테이프, 본드 등)의 사용은 금지합니다.</dd>
            </dl>

            <dl style="margin-left: auto;margin-right: auto;width: 80%;">
                <dt>◉ 폐기물 처리</dt>
                <dd>- 행사 중 발생한 폐기물은 종료 즉시 쓰레기봉투에 담아 실외로 반출하여야 합니다.<br>※ 쓰레기봉투는 전시실 예약 시 구매신청 하셔야 합니다.</dd>
            </dl>

            <dl style="margin-left: auto;margin-right: auto;width: 80%;">
                <dt>◉ 원상복구 의무</dt>
                <dd>- 회의실 사용 종료 즉시 시설, 장비, 비품 등은 사용 이전의 상태로 반납하여야 하며 파손, 변형 등 발생 시 원래의 상태 또는 동등 이상의 상태로 즉시 복구하거나 변상하여야 합니다.</dd>
                <dd>- 임차인이 전 항의 지침을 위반하시는 경우, 임대인은 동 지침에 의해 조치하며 임차인은 이에 대해 어떠한 이의도 제기하실 수 없습니다.</dd>
            </dl>

            <table class="table1" style="margin-top:40px;margin-left:auto; margin-right:auto;border-style: hidden;">
                <colgroup>
                    <col width="100%">
                </colgroup>
                <tbody>
                    <tr>
                        <td colspan="1" style="text-align:left;border-style: hidden;">【별첨】</td>
                    </tr>
                    <tr>
                        <td colspan="1" style="text-align:left;border-style: hidden;">임대차 계약 상세 내역서 1부</td>
                    </tr>
                </tbody>
              </table>
            <table class="table1" style="margin-top:40px;margin-left:auto; margin-right:auto;">
                
                <colgroup>
                    <col width="50%">
                    <col width="50%">
                </colgroup>
                <tbody>
                    <tr>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">임대인</td>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">임차인</td>
                    </tr>
                    <tr>
                        <td colspan="1" style="text-align:left;padding:10px 10px 10px 10px;">서울특별시 서초구 강남대로 27 <br>한국농수산식품유통공사 화훼사업센터<br>대표 : 김춘진</td>
                        <td colspan="1" style="text-align:left;" id="lastInpt">
                            <c:out value="${resultList[0].address}" />
                            <br>
                            <c:out value="${resultList[0].addressDtl}" />
                            <br>
                            <c:out value="대표 : ${resultList[0].rprsntrNm}" />
                        </td>
                    </tr>
                </tbody>
              </table>

        </div> 
    </div>
</div>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        var msg = '${result}';
        if(msg == 'noData')
            alert('모두 반려하여 데이터가 없습니다.');
    window.print();
});

</script>
</body></html>

