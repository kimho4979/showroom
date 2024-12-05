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


<title>전시실 임대차 상세 계약서</title>
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

                // 임대료,관리비,장비사용료 계산 로직 by java code 
                int rentPay = 0;
                int eqpmntPay = 0;
                int totalPay = 0;
                int mngPay = 0;
                int rentNmngPay = 0;
                int discount = 0;
                for(Map<String,Object> map : result){
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
                    
                    rentNmngPay += ((rentPay + mngPay) - discount);
                    eqpmntPay += _eqpmntPay;
                    System.out.println("eqpmntPay : " + eqpmntPay);
                    System.out.println("rentPay : " + rentPay);
                    System.out.println("mngPay : " + mngPay);
                }
                totalPay = rentNmngPay + eqpmntPay;
                NumberFormat numberFormat = NumberFormat.getNumberInstance();
                String formattedRentNmngPay = numberFormat.format(rentNmngPay);
                String formattedEqpmntPay = numberFormat.format(eqpmntPay);
                //////////////////totalPay = (int)((totalPay) + (totalPay * 0.1));
                String formattedTotalPay = numberFormat.format(totalPay);
                int formattedTotalPay2 = totalPay;
                System.out.println("formattedRentNmngPay : " + formattedRentNmngPay);
                System.out.println("formattedTotalPay : " + formattedTotalPay);
                request.setAttribute("formattedTotalPay2", formattedTotalPay2);
%>

<div class="container">
    <div class="header">
        <div class="page">
            <p class="title1" style="font-size:15pt;font-weight:bold;">전시실 임대차 상세 내역서</p>
            <p class="normal1" style="font-size:12pt;font-weight:bold;margin-top:20px;margin-left:auto;margin-right:auto;width:80%">사용자정보</p>
            <table class="table1" style="margin-top: 0px;">
                <colgroup>
                    <col width="25%"> 
                    <col width="25%"> 
                    <col width="25%"> 
                    <col width="25%"> 
                </colgroup>
                <tbody>
                    <tr>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">행사명</td>
                        <td colspan="3" style="text-align: left;" id="evntNmInpt"><c:out value="${resultList[0].evntNm}"></c:out></td>
                    </tr>
                    
                    <tr>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">임차인</td>
                        <td colspan="1" style="text-align: center;"><c:out value="${resultList[0].rprsntrNm}"></c:out></td>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">담당자</td>
                        <td colspan="1" style="text-align: center;"><c:out value="${resultList[0].mngrNm}"></c:out></td>
                    </tr>
                    
                </tbody>
            </table>
            
            <p class="normal1" style="font-size:12pt;font-weight:bold;margin-top:20px;margin-left:auto;margin-right:auto;width:80%">전시실 임대료 (관리비 포함)</p>
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
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">예약일</td>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">임대장소</td>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">사용시간</td>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">금액</td>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">세팅</td>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">할인</td>
                    </tr>
                     <c:forEach var="item" items="${resultList}">
                    <tr>
                        <td colspan="1" style="text-align:center;" id="rsrvDtStartInpt">
                            <fmt:parseDate value="${item.rsrvDtStart}" var="rsrvDtStart" pattern="yyyyMMdd" />
                            <fmt:formatDate value="${rsrvDtStart}" pattern="yyyy년" /><br>
                            <fmt:formatDate value="${rsrvDtStart}" pattern="MM월 dd일" />
                        </td>
                        <td colspan="1" style="text-align:center;" id="hallTypeInpt"><c:out value="${item.hallType}"></c:out></td>
                        <td colspan="1" style="text-align:center;" id="evntDtInpt"><c:out value="${item.evntDt}"></c:out></td>
                        <td colspan="1" style="text-align:center;" id="totalPayInpt"><fmt:formatNumber  type="number" maxFractionDigits="3"   value="${(item.mngPay + item.rentPay) - item.discount }"></fmt:formatNumber>원</td>
                        <td colspan="1" style="text-align:center;" id="btchStylInpt"><c:out value="${item.btchStyl}"></c:out></td>
                        <td colspan="1" style="text-align:center;" id="etcInpt"><fmt:formatNumber  type="number" maxFractionDigits="3"   value="${item.discount}"></fmt:formatNumber>원</td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
            
              <p class="normal1" style="font-size:12pt;font-weight:bold;margin-top:20px;margin-left:auto;margin-right:auto;width:80%">장비사용료</p>
              <table class="table1">
                <colgroup>
                    <col width="20%"> 
                    <col width="20%"> 
                    <col width="20%"> 
                    <col width="20%"> 
                    <col width="20%"> 
                </colgroup>
                <tbody>
                    <tr>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">예약일</td>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">장비명</td>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">단가</td>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">수량</td>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">금액</td>
                    </tr>
                    <c:forEach var="item" items="${resultList}" varStatus="status">
                        <c:set var="formattedEqpmntPay" value="${formattedEqpmntPay + item.eqpmntPay}"/>
                        <c:set var="formattedRentPay" value="${formattedRentPay + item.rentPay}"/>
                        <c:set var="formattedMngPay" value="${formattedMngPay + item.mngPay}"/>
                        <c:set var="formattedTotalPay" value="${(formattedMngPay + formattedRentPay + formattedEqpmntPay) - item.discount}"/>
                        <td colspan="1" style="text-align:center;" rowspan="8">
                            <fmt:parseDate value="${item.rsrvDtStart}" var="rsrvDtStart" pattern="yyyyMMdd" />
                            <fmt:formatDate value="${rsrvDtStart}" pattern="yyyy년" /><br>
                            <fmt:formatDate value="${rsrvDtStart}" pattern="MM월 dd일" />
                        </td>
                        
                    <c:forEach var="item2" items="${eqpmntRfrnc}">
                    <c:if test="${item2.HALL_TYPE eq item.hallTypeCd && item2.EQPMNT_NM eq '프로젝터'}">
                    <tr>
                        <td style="text-align:center;" ><c:out value="${item2.EQPMNT_NM }"></c:out></td>
                        <td style="text-align:center;" ><fmt:formatNumber type="number" maxFractionDigits="3"   value="${item.evntDtCd ne 'ALL' ? item2.HALF_PAY : item2.DAY_PAY}"></fmt:formatNumber>원</td>
                        <td style="text-align:center;" ><c:out value="${item.prjctr == null ? 0 : item.prjctr }"></c:out>개</td>
                        <td style="text-align:center;" ><fmt:formatNumber type="number" maxFractionDigits="3"  value="${item.evntDtCd ne 'ALL' ? item2.HALF_PAY * (item.prjctr == null ? 0 : item.prjctr) : item2.DAY_PAY * (item.prjctr == null ? 0 : item.prjctr)}"></fmt:formatNumber>원</td>
                    </tr>
                    </c:if>
                    <c:if test="${item2.HALL_TYPE eq item.hallTypeCd && item2.EQPMNT_NM eq '방송엠프'}">
                    <tr>
                        <td style="text-align:center;" ><c:out value="${item2.EQPMNT_NM }"></c:out></td>
                        <td style="text-align:center;" ><fmt:formatNumber type="number" maxFractionDigits="3"  value="${item.evntDtCd ne 'ALL' ? item2.HALF_PAY : item2.DAY_PAY}"></fmt:formatNumber>원</td>
                        <td style="text-align:center;" ><c:out value="${item.bAmp == null ? 0 : item.bAmp }"></c:out>개</td>
                        <td style="text-align:center;" ><fmt:formatNumber type="number" maxFractionDigits="3"  value="${item.evntDtCd ne 'ALL' ? item2.HALF_PAY * (item.bAmp == null ? 0 : item.bAmp) : item2.DAY_PAY * (item.bAmp == null ? 0 : item.bAmp)}"></fmt:formatNumber>원</td>
                    </tr>
                    </c:if>
                    <c:if test="${item2.HALL_TYPE eq item.hallTypeCd && item2.EQPMNT_NM eq '무선마이크'}">
                    <tr>
                        <td style="text-align:center;" ><c:out value="${item2.EQPMNT_NM }"></c:out></td>
                        <td style="text-align:center;" ><fmt:formatNumber type="number" maxFractionDigits="3"  value="${item.evntDtCd ne 'ALL' ? item2.HALF_PAY : item2.DAY_PAY}"></fmt:formatNumber>원</td>
                        <td style="text-align:center;" ><c:out value="${item.wlsMic == null ? 0 : item.wlsMic }"></c:out>개</td>
                        <td style="text-align:center;" ><fmt:formatNumber type="number" maxFractionDigits="3"  value="${item.evntDtCd ne 'ALL' ? item2.HALF_PAY * (item.wlsMic == null ? 0 : item.wlsMic) : item2.DAY_PAY * (item.wlsMic == null ? 0 : item.wlsMic)}"></fmt:formatNumber>원</td>
                    </tr>
                    </c:if>
                    <c:if test="${item2.HALL_TYPE eq item.hallTypeCd && item2.EQPMNT_NM eq '유선마이크'}">
                    <tr>
                        <td style="text-align:center;" ><c:out value="${item2.EQPMNT_NM }"></c:out></td>
                        <td style="text-align:center;" ><fmt:formatNumber type="number" maxFractionDigits="3"  value="${item.evntDtCd ne 'ALL' ? item2.HALF_PAY : item2.DAY_PAY}"></fmt:formatNumber>원</td>
                        <td style="text-align:center;" ><c:out value="${item.wMic == null ? 0 : item.wMic }"></c:out>개</td>
                        <td style="text-align:center;" ><fmt:formatNumber type="number" maxFractionDigits="3"  value="${item.evntDtCd ne 'ALL' ? item2.HALF_PAY * (item.wMic == null ? 0 : item.wMic) : item2.DAY_PAY * (item.wMic == null ? 0 : item.wMic)}"></fmt:formatNumber>원</td>
                    </tr>
                    </c:if>
                    <c:if test="${item2.HALL_TYPE eq item.hallTypeCd && item2.EQPMNT_NM eq '탁자'}">
                    <tr>
                        <td style="text-align:center;" ><c:out value="${item2.EQPMNT_NM }"></c:out></td>
                        <td style="text-align:center;" ><fmt:formatNumber type="number" maxFractionDigits="3"  value="${item.evntDtCd ne 'ALL' ? item2.HALF_PAY : item2.DAY_PAY}"></fmt:formatNumber>원</td>
                        <td style="text-align:center;" ><c:out value="${item.tbl == null ? 0 : item.tbl }"></c:out>개</td>
                        <td style="text-align:center;" ><fmt:formatNumber type="number" maxFractionDigits="3"  value="${item.evntDtCd ne 'ALL' ? item2.HALF_PAY * (item.tbl == null ? 0 : item.tbl) : item2.DAY_PAY * (item.tbl == null ? 0 : item.tbl)}"></fmt:formatNumber>원</td>
                    </tr>
                    </c:if>
                    <c:if test="${item2.HALL_TYPE eq item.hallTypeCd && item2.EQPMNT_NM eq '의자'}">
                    <tr>
                        <td style="text-align:center;" ><c:out value="${item2.EQPMNT_NM }"></c:out></td>
                        <td style="text-align:center;" ><fmt:formatNumber type="number" maxFractionDigits="3"  value="${item.evntDtCd ne 'ALL' ? item2.HALF_PAY : item2.DAY_PAY}"></fmt:formatNumber>원</td>
                        <td style="text-align:center;" ><c:out value="${item.chr == null ? 0 : item.chr }"></c:out>개</td>
                        <td style="text-align:center;" ><fmt:formatNumber type="number" maxFractionDigits="3"  value="${item.evntDtCd ne 'ALL' ? item2.HALF_PAY * (item.chr == null ? 0 : item.chr) : item2.DAY_PAY * (item.chr == null ? 0 : item.chr)}"></fmt:formatNumber>원</td>
                    </tr>
                    </c:if>
                    <c:if test="${item2.HALL_TYPE eq item.hallTypeCd && item2.EQPMNT_NM eq '쓰레기봉투'}">
                    <tr>
                        <td style="text-align:center;" ><c:out value="${item2.EQPMNT_NM }"></c:out></td>
                        <td style="text-align:center;" ><fmt:formatNumber type="number" maxFractionDigits="3"  value="${item.evntDtCd ne 'ALL' ? item2.HALF_PAY : item2.DAY_PAY}"></fmt:formatNumber>원</td>
                        <td style="text-align:center;" ><c:out value="${item.grbgPck == null ? 0 : item.grbgPck }"></c:out>개</td>
                        <td style="text-align:center;" ><fmt:formatNumber type="number" maxFractionDigits="3"  value="${item.evntDtCd ne 'ALL' ? item2.HALF_PAY * (item.grbgPck == null ? 0 : item.grbgPck) : item2.DAY_PAY * (item.grbgPck == null ? 0 : item.grbgPck)}"></fmt:formatNumber>원</td>
                    </tr>
                    </c:if>
                    </c:forEach>
                    </c:forEach>
                    <tr>
                        <td colspan="4" style="text-align:center;background-color: #e9cdf7;color:black;">합계</td>
                        <td colspan="1" style="text-align:center;" id="eqpmntPayInpt"><fmt:formatNumber type="number" maxFractionDigits="3"  value="${formattedEqpmntPay}"></fmt:formatNumber>원</td>
                </tbody>
            </table>

            <p class="normal1" style="font-size:12pt;font-weight:bold;margin-top:20px;margin-left:auto;margin-right:auto;width:80%">총 전시실 임대료 (VAT 포함)</p>
            <table class="table1" style="margin-left:auto; margin-right:auto;">
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
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">합계 (VAT 포함)</td>
                    </tr>
                    <tr>
                        <td colspan="1" style="text-align:center;" ><fmt:formatNumber type="number" maxFractionDigits="3"  value="${formattedRentPay}"></fmt:formatNumber>원</td>
                        <td colspan="1" style="text-align:center;" ><fmt:formatNumber type="number" maxFractionDigits="3"  value="${formattedMngPay}"></fmt:formatNumber>원</td>
                        <td colspan="1" style="text-align:center;" ><fmt:formatNumber type="number" maxFractionDigits="3"  value="${formattedEqpmntPay}"></fmt:formatNumber>원</td>
                        <td colspan="1" style="text-align:center;" ><fmt:formatNumber type="number" maxFractionDigits="3"  value="${formattedTotalPay2}"></fmt:formatNumber>원</td>    
                    </tr>
                </tbody>
              </table>
            <p class="normal1" style="font-size:12pt;font-weight:bold;margin-top:20px;margin-left:auto;margin-right:auto;width:80%">주요 서비스 문의처</p>
            <table class="table1" style="margin-top:0px;margin-left:auto; margin-right:auto;">
                <colgroup>
                    <col width="10%"> 
                    <col width="20%"> 
                    <col width="40%"> 
                    <col width="20%"> 
                    <col width="10%"> 
                </colgroup>
                <tbody>
                    <tr>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">위치</td>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">서비스 내용</td>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">상호</td>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">전화번호</td>
                        <td colspan="1" style="text-align:center;background-color: #e9cdf7;color:black;">비고</td>
                    </tr>
                    <tr>
                        <td colspan="1" style="text-align:center">1F</td>
                        <td colspan="1" style="text-align:center">주차권</td>
                        <td colspan="1" style="text-align:center">화훼사업센터 주차관리실</td>
                        <td colspan="1" style="text-align:center">02-576-1686</td>
                        <td colspan="1" style="text-align:center"></td>
                    </tr>
                    <tr>
                        <td colspan="1" style="text-align:center">2F</td>
                        <td colspan="1" style="text-align:center">미화</td>
                        <td colspan="1" style="text-align:center">에이플주식회사</td>
                        <td colspan="1" style="text-align:center">02-576-5462</td>
                        <td colspan="1" style="text-align:center"></td>
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
    window.print();
});

</script>
</body></html>

