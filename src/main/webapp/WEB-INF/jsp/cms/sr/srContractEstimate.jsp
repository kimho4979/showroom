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


<title>화훼사업센터 전시실 임대료 예상 견적서</title>
</head>
<body>
<!-- Start your code here -->
<% // 포맷팅을 위한 자바 코드 
                /*List<Map<String,Object>> result  = (List<Map<String,Object>>)request.getAttribute("resultList");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat _formatter = new SimpleDateFormat("yyyy년 MM월 dd일");
                Date dateS = formatter.parse(result.get(0).get("rsrvDtStart").toString());
                Calendar cal = Calendar.getInstance();
                cal.setTime(dateS);
                cal.add(Calendar.DATE, -7);
                String dDay = _formatter.format(cal.getTime());

                // 임대료,관리비,장비사용료 계산 로직 by java code 
                int rentPay = 0;
                int eqpmntPay = 0;
                int totalPay = 0;
                int mngPay = 0;
                int rentNmngPay = 0;
                for(Map<String,Object> map : result){
                    int _eqpmntPay=0;
                    Object rentPayObj = map.get("rentPay");
                    if(rentPayObj instanceof Number) {
                        rentPay = ((Number)rentPayObj).intValue();
                    }
                    
                    Object eqpmntPayObj = map.get("eqpmntPay");
                    if(eqpmntPayObj instanceof Number) {
                        _eqpmntPay = ((Number)eqpmntPayObj).intValue();
                    }
                    
                    Object mngPayObj = map.get("mngPay");
                    if(mngPayObj instanceof Number) {
                        mngPay = ((Number)mngPayObj).intValue();
                    }
                    
                    rentNmngPay += rentPay + mngPay;
                    eqpmntPay += _eqpmntPay;
                    System.out.println("eqpmntPay : " + eqpmntPay);
                    System.out.println("rentPay : " + rentPay);
                    System.out.println("mngPay : " + mngPay);
                }
                totalPay = rentNmngPay + eqpmntPay;
                NumberFormat numberFormat = NumberFormat.getNumberInstance();
                String formattedRentNmngPay = numberFormat.format(rentNmngPay);
                String formattedEqpmntPay = numberFormat.format(eqpmntPay);
                ///////////////totalPay = (int)((totalPay) + (totalPay * 0.1));
                String formattedTotalPay = numberFormat.format(totalPay);
                System.out.println("formattedRentNmngPay : " + formattedRentNmngPay);*/

request.setCharacterEncoding("UTF-8");
List<Map<String,Object>> result  = (List<Map<String,Object>>)request.getAttribute("resultList");

for(Map<String,Object> map : result){
    //out.println("map : " + map.get("mngPay"));
}

%>
<div class="container">
    <div class="header">
        <div class="page">
            <p class="title1" style="font-size:15pt;font-weight:bold;">화훼사업센터 전시실 임대료 예상 견적서</p>
            <p class="normal1" style="font-size:12pt;font-weight:bold;margin-top:20px;margin-left:auto;margin-right:auto;width:80%">◉ 예상금액</p>
            <table class="table1" style="margin-top: 0px;">
                <colgroup>
                    <col width="25%"> 
                    <col width="25%"> 
                    <col width="25%"> 
                    <col width="25%"> 
                </colgroup>
                <tbody>
                    <tr>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">임대료</td>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">관리비</td>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">장비사용료</td>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">합계(VAT포함)</td>
                    </tr>
                    <tr>
                        <c:set var="rentTotal" value="${fn:replace(fn:replace(resultList[0].rentTotal, ' 원', ''),',','')}"></c:set>
                        <td colspan="1" style="text-align: center;" id="evntNmInpt"><fmt:formatNumber type="number" maxFractionDigits="3"  value="${rentTotal}"></fmt:formatNumber>원</td>   
                        <c:set var="mngTotal" value="${fn:replace(fn:replace(resultList[0].mngTotal, ' 원', ''),',','')}"></c:set>
                        <td colspan="1" style="text-align: center;"><fmt:formatNumber type="number" maxFractionDigits="3"  value="${mngTotal}"></fmt:formatNumber>원</td>
                        <c:set var="eqpmntTotal" value="${fn:replace(fn:replace(resultList[0].eqpmntTotal, ' 원', ''),',','')}"></c:set>
                        <td colspan="1" style="text-align: center;"><fmt:formatNumber type="number" maxFractionDigits="3"  value="${eqpmntTotal}"></fmt:formatNumber>원</td>
                        <c:set var="total" value="${fn:replace(fn:replace(resultList[0].total, ' 원', ''),',','')}"></c:set>
                        <td colspan="1" style="text-align: center;"><fmt:formatNumber type="number" maxFractionDigits="3" value="${total}" />원</td>
                    </tr>
                </tbody>
            </table>
            <p class="normal1" style="font-size:12pt;font-weight:bold;margin-top:20px;margin-left:auto;margin-right:auto;width:80%">◉ 전시실 임대료(관리비 포함)</p>
            <table class="table1" style="margin-top: 0px;">
                <colgroup>
                    <col width="25%"> 
                    <col width="25%"> 
                    <col width="25%"> 
                    <col width="25%">   
                </colgroup>
                <tbody>
                    <tr>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">예약일</td>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">임대장소</td>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">사용시간</td>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">금액</td>
                    </tr>
                    <c:forEach var="item" items="${resultList}">
                        <tr>
                            <td colspan="1" style="text-align: center;">
                                <fmt:parseDate value="${item.rsrvDtStart}" var="rsrvDtStart" pattern="yyyy-MM-dd" />
                                <fmt:formatDate value="${rsrvDtStart}" pattern="yyyy년 MM월 dd일" />
                            </td>
                            <c:if test="${item.hallType == '1'}">
                                <td colspan="1" style="text-align: center;">무궁화홀</td>
                            </c:if>
                            <c:if test="${item.hallType == '2'}">
                                <td colspan="1" style="text-align: center;">국화홀</td>
                            </c:if>   
                            <c:if test="${item.hallType == '3'}">
                                <td colspan="1" style="text-align: center;">장미홀</td>
                            </c:if>
                            <c:if test="${item.evntDt == 'AM'}">
                                <td colspan="1" style="text-align: center;">9시~12시</td>
                                <c:set var="evntDtNm" value="9시~12시"></c:set>
                            </c:if>
                            <c:if test="${item.evntDt == 'PM'}">
                                <td colspan="1" style="text-align: center;">13시~17시</td>
                                <c:set var="evntDtNm" value="13시~17시"></c:set>
                            </c:if>
                            <c:if test="${item.evntDt == 'ALL'}">
                                <td colspan="1" style="text-align: center;">9시~17시</td>
                                <c:set var="evntDtNm" value="9시~17시"></c:set>
                            </c:if>
                            <c:set var="rentPay" value="${fn:replace(fn:replace(item.rentPay, ' 원', ''),',','')}"></c:set>
                            <c:set var="mngPay" value="${fn:replace(fn:replace(item.mngPay, ' 원', ''),',','')}"></c:set>
                            <td colspan="1" style="text-align: center;"><fmt:formatNumber type="number" maxFractionDigits="3" value="${rentPay + mngPay}"></fmt:formatNumber>원</td>
                        </tr>
                    </c:forEach>
            </table>
            <p class="normal1" style="font-size:12pt;font-weight:bold;margin-top:20px;width:80%;margin-left:auto;margin-right:auto;">※ 전시실 이용은 임대시간에 한해 가능하며 준비/철거 및 리허설이 필요한 경우 소요시간을 감안하여 임대하시기 바랍니다.</p> 
            <p class="normal1" style="font-size:12pt;font-weight:bold;margin-top:0px;width:80%;margin-left:auto;margin-right:auto;">※ 무료주차권은 2매 무료 제공(추가구입 문의 : 02-576-1686, 주차권 구매장소 : 주차관리실(평일))</p>
            <p class="normal1" style="font-size:12pt;font-weight:bold;margin-top:20px;margin-left:auto;margin-right:auto;width:80%">◉ 장비사용료</p>
            <table class="table1" style="margin-top: 0px;">
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
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">예약일</td>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">사용시간</td>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">장비명</td>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">단가</td>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">수량</td>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">소계</td>
                    </tr>
                    <c:forEach var="item" items="${resultList}">
                        <tr>
                            <td colspan="1" style="text-align: center;" rowspan="8">
                                <fmt:parseDate value="${item.rsrvDtStart}" var="rsrvDtStart" pattern="yyyy-MM-dd" />
                                <fmt:formatDate value="${rsrvDtStart}" pattern="yyyy년" /><br>
                                <fmt:formatDate value="${rsrvDtStart}" pattern="MM월 dd일" />
                            </td>
                            <c:if test="${item.evntDt == 'AM'}">
                                <td colspan="1" style="text-align: center;" rowspan="8">9시~12시</td>
                            </c:if>
                            <c:if test="${item.evntDt == 'PM'}">
                                <td colspan="1" style="text-align: center;" rowspan="8">13시~17시</td>
                            </c:if>
                            <c:if test="${item.evntDt == 'ALL'}">
                                <td colspan="1" style="text-align: center;" rowspan="8">9시~17시</td>
                            </c:if>
                        </tr>
                        <c:forEach var="item2" items="${eqpmntRfrnc}">
                        <c:if test="${item2.HALL_TYPE eq item.hallType && item2.EQPMNT_NM eq '프로젝터'}">
                        <tr>
                            <td colspan="1" style="text-align: center;"><c:out value="${item2.EQPMNT_NM}"></c:out></td>
                            <td style="text-align:center;" ><fmt:formatNumber type="number" maxFractionDigits="3"   value="${item.evntDt ne 'ALL' ? item2.HALF_PAY : item2.DAY_PAY}"></fmt:formatNumber>원</td>
                            <td style="text-align:center;" ><c:out value="${item.prjctrInpt == '' ? 0 : item.prjctrInpt }"></c:out>개</td>
                            <td style="text-align:center;" ><fmt:formatNumber type="number" maxFractionDigits="3"  value="${item.evntDt ne 'ALL' ? item2.HALF_PAY * (item.prjctrInpt == '' ? 0 : item.prjctrInpt) : item2.DAY_PAY * (item.prjctrInpt == '' ? 0 : item.prjctrInpt)}"></fmt:formatNumber>원</td>
                        </tr>
                        </c:if>
                        <c:if test="${item2.HALL_TYPE eq item.hallType && item2.EQPMNT_NM eq '방송엠프'}">
                        <tr>
                            <td colspan="1" style="text-align: center;"><c:out value="${item2.EQPMNT_NM}"></c:out></td>
                            <td style="text-align:center;" ><fmt:formatNumber type="number" maxFractionDigits="3"   value="${item.evntDt ne 'ALL' ? item2.HALF_PAY : item2.DAY_PAY}"></fmt:formatNumber>원</td>
                            <td style="text-align:center;" ><c:out value="${item.bAmpInpt == '' ? 0 : item.bAmpInpt }"></c:out>개</td>
                            <td style="text-align:center;" ><fmt:formatNumber type="number" maxFractionDigits="3"  value="${item.evntDt ne 'ALL' ? item2.HALF_PAY * (item.bAmpInpt == '' ? 0 : item.bAmpInpt) : item2.DAY_PAY * (item.bAmpInpt == '' ? 0 : item.bAmpInpt)}"></fmt:formatNumber>원</td>
                        </tr>
                        </c:if>
                        <c:if test="${item2.HALL_TYPE eq item.hallType && item2.EQPMNT_NM eq '무선마이크'}">
                        <tr>
                            <td colspan="1" style="text-align: center;"><c:out value="${item2.EQPMNT_NM}"></c:out></td>
                            <td style="text-align:center;" ><fmt:formatNumber type="number" maxFractionDigits="3"   value="${item.evntDt ne 'ALL' ? item2.HALF_PAY : item2.DAY_PAY}"></fmt:formatNumber>원</td>
                            <td style="text-align:center;" ><c:out value="${item.wlsMicInpt == '' ? 0 : item.wlsMicInpt }"></c:out>개</td>
                            <td style="text-align:center;" ><fmt:formatNumber type="number" maxFractionDigits="3"  value="${item.evntDt ne 'ALL' ? item2.HALF_PAY * (item.wlsMicInpt == '' ? 0 : item.wlsMicInpt) : item2.DAY_PAY * (item.wlsMicInpt == '' ? 0 : item.wlsMicInpt)}"></fmt:formatNumber>원</td>
                        </tr>
                        </c:if>
                        <c:if test="${item2.HALL_TYPE eq item.hallType && item2.EQPMNT_NM eq '유선마이크'}">
                        <tr>
                            <td colspan="1" style="text-align: center;"><c:out value="${item2.EQPMNT_NM}"></c:out></td>
                            <td style="text-align:center;" ><fmt:formatNumber type="number" maxFractionDigits="3"   value="${item.evntDt ne 'ALL' ? item2.HALF_PAY : item2.DAY_PAY}"></fmt:formatNumber>원</td>
                            <td style="text-align:center;" ><c:out value="${item.wMicInpt == '' ? 0 : item.wMicInpt }"></c:out>개</td>
                            <td style="text-align:center;" ><fmt:formatNumber type="number" maxFractionDigits="3"  value="${item.evntDt ne 'ALL' ? item2.HALF_PAY * (item.wMicInpt == '' ? 0 : item.wMicInpt) : item2.DAY_PAY * (item.wMicInpt == '' ? 0 : item.wMicInpt)}"></fmt:formatNumber>원</td>
                        </tr>
                        </c:if>
                        <c:if test="${item2.HALL_TYPE eq item.hallType && item2.EQPMNT_NM eq '탁자'}">
                        <tr>
                            <td colspan="1" style="text-align: center;"><c:out value="${item2.EQPMNT_NM}"></c:out></td>
                            <td style="text-align:center;" ><fmt:formatNumber type="number" maxFractionDigits="3"   value="${item.evntDt ne 'ALL' ? item2.HALF_PAY : item2.DAY_PAY}"></fmt:formatNumber>원</td>
                            <td style="text-align:center;" ><c:out value="${item.tblInpt == '' ? 0 : item.tblInpt }"></c:out>개</td>
                            <td style="text-align:center;" ><fmt:formatNumber type="number" maxFractionDigits="3"  value="${item.evntDt ne 'ALL' ? item2.HALF_PAY * (item.tblInpt == '' ? 0 : item.tblInpt) : item2.DAY_PAY * (item.tblInpt == '' ? 0 : item.tblInpt)}"></fmt:formatNumber>원</td>
                        </tr>
                        </c:if>
                        <c:if test="${item2.HALL_TYPE eq item.hallType && item2.EQPMNT_NM eq '의자'}">
                        <tr>
                            <td colspan="1" style="text-align: center;"><c:out value="${item2.EQPMNT_NM}"></c:out></td>
                            <td style="text-align:center;" ><fmt:formatNumber type="number" maxFractionDigits="3"   value="${item.evntDt ne 'ALL' ? item2.HALF_PAY : item2.DAY_PAY}"></fmt:formatNumber>원</td>
                            <td style="text-align:center;" ><c:out value="${item.chrInpt == '' ? 0 : item.chrInpt }"></c:out>개</td>
                            <td style="text-align:center;" ><fmt:formatNumber type="number" maxFractionDigits="3"  value="${item.evntDt ne 'ALL' ? item2.HALF_PAY * (item.chrInpt == '' ? 0 : item.chrInpt) : item2.DAY_PAY * (item.chrInpt == '' ? 0 : item.chrInpt)}"></fmt:formatNumber>원</td>
                        </tr>
                        </c:if>
                        <c:if test="${item2.HALL_TYPE eq item.hallType && item2.EQPMNT_NM eq '쓰레기봉투'}">
                        <tr>
                            <td colspan="1" style="text-align: center;"><c:out value="${item2.EQPMNT_NM}"></c:out></td>
                            <td style="text-align:center;" ><fmt:formatNumber type="number" maxFractionDigits="3"   value="${item.evntDt ne 'ALL' ? item2.HALF_PAY : item2.DAY_PAY}"></fmt:formatNumber>원</td>
                            <td style="text-align:center;" ><c:out value="${item.grbgPckInpt == '' ? 0 : item.grbgPckInpt }"></c:out>개</td>
                            <td style="text-align:center;" ><fmt:formatNumber type="number" maxFractionDigits="3"  value="${item.evntDt ne 'ALL' ? item2.HALF_PAY * (item.grbgPckInpt == '' ? 0 : item.grbgPckInpt) : item2.DAY_PAY * (item.grbgPckInpt == '' ? 0 : item.grbgPckInpt)}"></fmt:formatNumber>원</td>
                        </tr>
                        </c:if>
                    </c:forEach>
                    </c:forEach>
                    <tr>
                        <td colspan="5" style="text-align:center;background-color: #e9cdf7;color:black;">합계</td>
                        <td style="text-align:center;" ><fmt:formatNumber type="number" maxFractionDigits="3"  value="${eqpmntTotal}"></fmt:formatNumber>원</td>
                    </tr>
                </tbody>
            </table>
            <%-- <c:out value="${eqpmntRfrnc}"></c:out> --%>
            <%-- <c:out value="${resultList}"></c:out> --%>
            <p class="normal1" style="font-size:12pt;font-weight:bold;margin-top:20px;width:80%;margin-left:auto;margin-right:auto;">※ 예상 견적서는 고객의 이해를 돕기 위한 참고자료입니다.</p> 
            <p class="normal1" style="font-size:12pt;font-weight:bold;margin-top:0px;width:80%;margin-left:auto;margin-right:auto;">위의 예상 견적서를 통해 전시실의 예약이 신청되거나 확정되지 않습니다.</p> 
            <p class="normal1" style="font-size:12pt;font-weight:bold;margin-top:50px;width:80%;margin-left:auto;margin-right:auto;">세부적인 사항은 화훼사업센터 화훼사업지원부(02-570-1815)으로 연락하여 주시기 바랍니다.</p> 
            <p class="title1" style="font-size:20pt;font-weight:bold;margin-top: 100px;width:80%;margin-left:auto;margin-right:auto;">화훼사업센터 화훼사업지원부</p>
        </div> 
    </div>
</div>
<script>
    document.addEventListener('DOMContentLoaded', function() {
    window.print();    
});

</script>
</body></html>

