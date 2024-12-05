<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui"%>
<!-- page-wrapper Start -->
<div id="page-wrapper" class="gray-bg">

    <jsp:include page="/WEB-INF/jsp/cms/inc/top.jsp"/>
    <!-- Content Start -->
    <style>
        .clickRow {
            background-color: #D4D4D4;
        }
    </style>
    <div class="row wrapper border-bottom white-bg page-heading">
    
        <div class="col-lg-10">
            <h2>전시실예약관리시스템</h2>
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <a href="index.html">관리자홈</a>
                </li>
                <li class="breadcrumb-item">
                    <a>센터소개</a>
                </li>
                <li class="breadcrumb-item active">
                    <strong>전시실예약관리시스템</strong>
                </li>
            </ol>
        </div>
    </div>
    <div class="wrapper wrapper-content animated fadeInRight ecommerce">
        <div class="ibox-content m-b-sm border-bottom">
            <div class="row">
                <div class="col-sm-2">
                    <div class="form-group">
                        <label class="col-form-label" for="inDt1">신청일자</label>
                        <div class="input-group date">
                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input id="inDt1" type="text" class="form-control" value="">
                        </div>
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="form-group">
                        <label class="col-form-label" for="inDt2">&nbsp;</label>
                        <div class="input-group date">
                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input id="inDt2" type="text" class="form-control" value="">
                        </div>
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="form-group">
                        <label class="col-form-label" for="rsrvDt1">예약일자</label>
                        <div class="input-group date">
                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input id="rsrvDt1" type="text" class="form-control" value="">
                        </div>
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="form-group">
                        <label class="col-form-label" for="rsrvDt2">&nbsp;</label>
                        <div class="input-group date">
                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input id="rsrvDt2" type="text" class="form-control" value="">
                        </div>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="col-form-label" for="srchEvntNm">행사명</label>
                        <input type="text" id="srchEvntNm" name="srchEvntNm" value="" placeholder="행사명 검색" class="form-control">
                    </div>
                </div>
            </div>
            <div class="row">

                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="col-form-label" for="srchStatus">상태구분</label>
                        <select  id="srchStatus" class="form-control">
                            <option value selected>전체</option>
                        </select>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="col-form-label" for="srchHallType">전시실</label>
                        <select  id="srchHallType" class="form-control">
                            <option value selected>전체</option>
                        </select>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="col-form-label" for="srchRprsntrNm">대표자명</label>
                        <input type="text" id="srchRprsntrNm" name="srchRprsntrNm" value="" placeholder="대표자명 검색" class="form-control">
                    </div>
                </div>

            </div>
            <div class="row">
                <div class="col-sm-4"></div>
                <div class="col-sm-4"></div>
                <div class="col-sm-4 btn-group">
                    <div class="col-sm-4 btn-group">
                    </div>
                    <div class="col-sm-8 btn-group">
                        <button type="button" style="background-color: #515ef5;border-color: #515ef5;" id="clckAccpt" class="btn  btn-success disabledByAdmin" data-style="" onclick="fn_clckStatus('A');">승인</button>
                        <button type="button" style="background-color: #4755f5;border-color: #4755f5;" id="clckRjct" class="btn  btn-danger disabledByAdmin" data-style="" onclick="fn_clckStatus('R')">반려</button>
                        <button type="button" style="background-color: #4755f5;border-color: #4755f5;" id="clckHold" class="btn  btn-info disabledByAdmin" data-style="" onclick="fn_clckStatus('H')">대기</button>
                        <button onclick="srchList()"  type="button" style="background-color: #1022e0;border-color: #1022e0;" id="clckSrch" class="btn  btn-info" data-style="">조회</button>
                        <button onclick="downloadExcel()"  type="button" style="background-color: #1022e0;border-color: #1022e0;" id="downloadExcel" class="btn  btn-info disabledByAdmin" data-style="">엑셀</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="ibox">
                    <div class="ibox-content">
                        <table class="footable table table-stripped toggle-arrow-tiny" data-page-size="15" data-paging="true">
                            <thead>
                            <tr>
                                <th data-hide="phone">선택</th>
                                <th data-hide="phone,tablet" >예약일자</th>
                                <th data-hide="phone,tablet" >상호/단체</th>
                                <th data-hide="phone" class="hiddenByAdmin">신청일자</th>
                                <th data-hide="phone">상태</th>
                                <th data-hide="phone">전시실</th>
                                <th data-hide="phone">행사명</th>
                                <th data-hide="phone">시간대</th>
                                <th data-hide="phone">대표자명</th>
                                <th data-hide="phone">결재금액</th>
                                <th data-hide="phone">배치형태</th>
                                <%-- <th data-hide="phone">사업자</th> --%>
                                <th data-hide="phone">배치도</th>
                                <th data-hide="phone">상세내역</th>
                            </tr>
                            </thead>
                            <tbody id="admListBind">
                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="7">
                                    <ul class="pagination float-right"></ul>
                                </td>
                            </tr>
                            </tfoot>
                        </table>
     
                    </div>

                </div>
            </div>
        </div>
        <div class="ibox-content m-b-sm border-bottom">

<div class="modal inmodal" id="myModal2" tabindex="-1" role="dialog" style="display: none;" aria-modal="true">
	<div class="modal-dialog" style="max-width:80%">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
				<h4 class="modal-title">Modal title</h4>
				<small class="font-bold">Lorem Ipsum is simply dummy text of the printing and typesetting industry.</small>
			</div>
			<div class="modal-body">
				<p><strong>Lorem Ipsum is simply dummy</strong> text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown
					printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting,
					remaining essentially unchanged.</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
				<%-- <button type="button" class="btn btn-primary">Save changes</button> --%>
			</div>
		</div>
	</div>
</div>
            <!-- <div class="col-lg-12"> -->
            <!-- <div class="ibox"> -->
            <div class="row">
                <div class="col-sm-4"></div>
                <div class="col-sm-4"></div>
                <div class="col-sm-4 btn-group">
                    <div class="col-sm-2 btn-group">
                        <input type="hidden" id="seq">
                        <input type="hidden" id="userId">
                    </div>
                    <div class="col-sm-10 btn-group">
                        <button type="button" style="background-color: #424cc7;border-color: #424cc7;" id="clckReset" class="btn  btn-success disabledByAdmin" data-style="" onclick="fn_reset();">초기화</button>
                        <button type="button" style="background-color: #4050f7;border-color: #4050f7;" id="clckNew" class="btn  btn-success disabledByAdmin" data-style="" onclick="fn_save();">신규</button>
                        <button type="button" style="background-color: #3344f5;border-color: #3344f5;" id="clckModi" class="btn  btn-primary disabledByAdmin" data-style="" onclick="fn_clckModi();">수정</button>
                        <button type="button" style="background-color: #1e30e8;border-color: #1e30e8;" id="clckDel" class="btn  btn-danger disabledByAdmin" data-style="" onclick="fn_clckDelete();" >삭제</button>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="col-form-label" for="clickDataInDt">신청일자</label>
                        <input type="text" id="clickDataInDt" name="inDt" value="" placeholder="YYYY-MM-DD" class="form-control hiddenByAdmin">
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="form-group">
                        <label class="col-form-label" for="clickDataRsrvDtStart">예약일자</label>
                        <input type="text" id="clickDataRsrvDtStart" name="rsrvDt" value="" placeholder="YYYY-MM-DD" class="form-control">
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="form-group">
                        <label class="col-form-label" for="clickDataRsrvDtEnd">&nbsp;</label>
                        <input type="text" id="clickDataRsrvDtEnd" name="rsrvDt" value="" placeholder="YYYY-MM-DD" class="form-control">
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="col-form-label" for="hallType">전시실</label>
                        <select class="form-control" id="hallType">
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="col-form-label" for="evntDesc">행사내용</label>
                        <input type="text" id="evntDesc" name="evntDesc" value="" placeholder="행사내용 입력" class="form-control">
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group" onchange="chgTime(event)">
                        <label class="col-form-label" for="evntDt1" style="display: block;">예약시간</label>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="evntDt0" id="evntDt1" value="AM">
                            <label class="form-check-label" for="evntDt1">오전</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="evntDt0" id="evntDt2" value="PM">
                            <label class="form-check-label" for="evntDt2">오후</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="evntDt0" id="evntDt3" value="ALL" checked>
                            <label class="form-check-label" for="evntDt3">전일</label>
                        </div>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="col-form-label" for="faxNo">유선전화번호</label>
                        <input type="text" id="faxNo"  placeholder="(-)를(을)빼고 입력" class="form-control" value="">
                    </div>
                </div>
              
            </div>
            <div class="row">
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="col-form-label" for="evntNm">행사명</label>
                        <input type="text" id="evntNm" value="" placeholder="행사명 입력" class="form-control">
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="col-form-label" for="orgNm">상호/단체명</label>
                        <input type="text" id="orgNm" value="" placeholder="상호/단체명 입력" class="form-control">
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="col-form-label" for="rprsntrNm" style="display: block;">대표자명</label>
                        <input class="form-control" type="text" id="rprsntrNm"  placeholder="대표자명 입력" value="" >
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="col-form-label" for="mngrNm">담당자명</label>
                        <input type="text" id="mngrNm"  readonly  value="" placeholder="담당자명 입력" class="form-control hiddenByAdmin">
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="col-form-label" for="comRgstNo">사업자번호</label>
                        <input type="text" id="comRgstNo" value="" placeholder="사업자번호 입력" class="form-control hiddenByAdmin">
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="col-form-label" for="bizType" style="display: block;">업종</label>
                        <input class="form-control hiddenByAdmin" type="text" id="bizType" value="" placeholder="업종 입력">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="col-form-label" for="bizType2">종목</label>
                        <input type="text" id="bizType2" value="" placeholder="종목 입력" class="form-control">
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="col-form-label" for="telNo">휴대전화번호(SMS수신용)</label>
                        <input type="text" id="telNo"  readonly  placeholder="(-)를(을)빼고 입력" class="form-control hiddenByAdmin" value="">
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="col-form-label" for="email" style="display: block;">이메일</label>
                        <input class="form-control"  readonly  type="text" id="email" value="" placeholder="이메일 입력">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6">
                    <div class="form-group">
                        <label class="col-form-label" for="address" >주소</label>
                        <input class="form-control hiddenByAdmin" type="text" id="address" value="" placeholder="클릭" onclick="openAddress();" >
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="form-group">
                        <label class="col-form-label" for="addressDtl" >상세주소</label>
                        <input class="form-control hiddenByAdmin" type="text" id="addressDtl" value="" placeholder="상세주소">
                    </div>
                </div>
            </div>
            <div class="row">
                
                <div class="col-sm-4">
                    <label class="col-form-label" for="iscorrdr" style="display: block;">복도선택여부</label>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="checkbox" name="iscorrdr" id="iscorrdr" value="">
                    </div>
                </div>
                <div class="col-sm-4">
                    <label class="col-form-label" for="isTemp" style="display: block;">냉난방여부</label>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="checkbox" name="isTemp" id="isTemp" value="">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="col-form-label" for="wlsMic" style="display: block;">무선마이크</label>
                        <input class="form-control" type="number" id="wlsMic" min="0" max="2" >
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="col-form-label" for="wMic" style="display: block;">유선마이크</label>
                        <input class="form-control" type="number" id="wMic" min="0" max="2">
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="col-form-label" for="bAmp" style="display: block;">방송앰프</label>
                        <input class="form-control" type="number" id="bAmp" min="0" max="1">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-4">
                    <label class="col-form-label" for="status1" style="display: block;">상태</label>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="status0" id="status1" value="A" readonly disabled>
                        <label class="form-check-label" for="status1">승인</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="status0" id="status2" value="R" readonly disabled>
                        <label class="form-check-label" for="status2">반려</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="status0" id="status3" value="H" checked readonly disabled>
                        <label class="form-check-label" for="status3">대기</label>
                    </div>
                    <span style="color:red;display:flex" >※ 상태변경은 상단 리스트에서 체크를 한뒤 승인,반려,대기 버튼을 누르세요.</span>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="col-form-label" for="projector" style="display: block;">프로젝터</label>
                        <input class="form-control" type="number" id="projector" min="0" max="1">
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="col-form-label" for="table" style="display: block;">탁자</label>
                        <input class="form-control" type="number" id="table" min="0" max="60">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="col-form-label" for="chair" style="display: block;">의자</label>
                        <input class="form-control" type="number" id="chair" min="0" max="120">
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="col-form-label" for="garbage" style="display: block;">쓰레기봉투</label>
                        <input class="form-control" type="number" id="garbage" min="0" max="50">
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="col-form-label" for="style" style="display: block;">배치형태</label>
                        <select class="form-control"  id="style" >
                            <option value="A">교실형태</option>
                            <option value="B">사각회의형</option>
                            <option value="C">기타(첨부파일)</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="col-form-label" for="rentPay" style="display: block;">임대료</label>
                        <input class="form-control" type="number" id="rentPay"  disabled  onchange="changePayInput();">
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="col-form-label" for="mngPay" style="display: block;">관리비</label>
                        <input class="form-control" type="number" id="mngPay"  disabled  onchange="changePayInput();">
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="col-form-label" for="eqpmntPay" >장비사용료</label>
                        <input class="form-control"  type="number"  id="eqpmntPay"  disabled   onchange="changePayInput();">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="col-form-label" for="totalPay" >합계금액</label>
                        <input type="number" class="form-control"  disabled  id="totalPay">
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="col-form-label" for="discount" >할인금액</label>
                        <input type="number" class="form-control" id="discount" placeholder=""  default="0"  >
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="col-form-label" for="e" ></label>
                        <%-- <input type="text" class="form-control" id="e" placeholder="고객요청사항 입력"> --%>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-form-label" for="etc" >고객요청사항</label>
                        <textarea   class="form-control"  id="etc" rows="10" ></textarea>
                    </div>
                </div>

            </div>
     
            <!-- </div> -->
        </div>
    </div>






    <!-- Page-Level Scripts -->
    <script>
        // 주소API로 주소검색 지번,도로명
        function openAddress(){
            new daum.Postcode({
                oncomplete: function(data) {
                    // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
                    // 예제를 참고하여 다양한 활용법을 확인해 보세요.
                    let address = "("+data.zonecode+") ";
                    if(data.userSelectedType == 'R'){
                        address += data.roadAddress;
                    } else {
                        address += data.jibunAddress;
                    }
                    if(data.apartment == 'Y')
                        address += ' ' + data.buildingName + ' ';
                    $('#address').val(address);
                }
            }).open();
        }

        // 검색 파라미터 생성
        function mkSrchPrm(){
            let srchPrm = {};
            srchPrm.inDt1 = $('#inDt1').val()
            srchPrm.inDt2 = $('#inDt2').val()
            srchPrm.rsrvDt1 = $('#rsrvDt1').val()
            srchPrm.rsrvDt2 = $('#rsrvDt2').val()
            srchPrm.evntNm = $.trim($('#srchEvntNm').val())
            srchPrm.status = $.trim($('#srchStatus').val())
            srchPrm.hallType = $.trim($('#srchHallType').val())
            srchPrm.rprsntrNm = $.trim($('#srchRprsntrNm').val())
            return srchPrm;
        }
        // 검색버튼 조회 
        function srchList() {
            getList(mkSrchPrm());
        }
        //엑셀 다운로드 
        function downloadExcel(){
            excel(mkSrchPrm());
        }
        // 그리드 특성상 그리드 전체를 다시 렌더링해야되서 필요한 코드 
        function staticCode() {
            var staticcode = '';
            staticcode += '<table class="footable table table-stripped toggle-arrow-tiny" data-page-size="15" data-paging="true">';
            staticcode += '<thead>';
            staticcode += '<tr>';
            staticcode += '<th data-hide="phone">선택</th>';
            staticcode += '<th data-hide="phone,tablet" style="width:150px;">예약일자</th>';
            staticcode += '<th data-hide="phone,tablet" style="width:200px;">상호/단체</th>';
            if('${atLoginVO.loginId}' == '에이플' && '${atLoginVO.loginId}' != ''){
                staticcode += '<th data-hide="phone" class="hiddenByAdmin">신청일자</th>';
            }
            staticcode += '<th data-hide="phone">상태</th>';
            staticcode += '<th data-hide="phone">전시실</th>';
            staticcode += '<th data-hide="phone" style="width: 300px;">행사명</th>';
            staticcode += '<th data-hide="phone">시간대</th>';
            staticcode += '<th data-hide="phone">대표자명</th>';
            staticcode += '<th data-hide="phone">결재금액</th>';
            staticcode += '<th data-hide="phone">배치형태</th>';
            //staticcode += '<th data-hide="phone">사업자</th>';
            staticcode += '<th data-hide="phone">배치도</th>';
            staticcode += '<th data-hide="phone">상세내역</th>';
            staticcode += '</tr>';
            staticcode += '</thead>';
            staticcode += '<tbody id="admListBind">';
            staticcode += '</tbody>';
            staticcode += '<tfoot>';
            staticcode += '<tr>';
            staticcode += '<td colspan="7">';
            staticcode += '<ul class="pagination"></ul>';
            staticcode += '</td>';
            staticcode += '</tr>';
            staticcode += '</tfoot>';
            staticcode += '</table>';
            return staticcode;
        }
        // 엑셀 다운로드 
        // 검색 파라미터와 바인딩 
        function excel(param){
            let obj = "?evntNm="+encodeURI(encodeURIComponent(param.evntNm))+"&status="+encodeURI(encodeURIComponent(param.status))+"&hallType="+encodeURI(encodeURIComponent(param.hallType))+"&rprsntrNm="+encodeURI(encodeURIComponent(param.rprsntrNm)); 
            obj += '&inDt1=' + encodeURI(encodeURIComponent(param.inDt1)) + '&inDt2=' + encodeURI(encodeURIComponent(param.inDt2)) + '&rsrvDt1=' + encodeURI(encodeURIComponent(param.rsrvDt1)) + '&rsrvDt2=' + encodeURI(encodeURIComponent(param.rsrvDt2));
            location.href = "${pageContext.request.contextPath}/admin/srrsrvtnExcel.excel" + obj;
            return false;
        }


        // 계약서 출력
        function printRsrvCntrct(e,detail){
        
            var v = (JSON.parse(e.target.getAttribute('data-json')))

            var url = '${pageContext.request.contextPath}/front/print/srContract.do';
                
            //var windowName = "_blank";
            //var windowFeatures = "width=800,height=600,left=200,top=200,resizable=yes,scrollbars=yes";
            
            const formData = new FormData();
            const items = [
                { grpId: v.grpId,userId: v.userId,type: detail == 'detail' ? 'detail' : 'default'}
            ];					

            formData.append('params', JSON.stringify(items));
            for (let pair of formData.entries()) {
                console.log(pair[0] + ': ' + pair[1]);
            }
            const form = document.createElement('form');
            form.method = 'POST';
            form.action = url;
            form.target = '_blank';
        
            // 기존 폼에 FormData 객체의 데이터를 추가
            for (let [name, value] of formData.entries()) {
                let input = document.createElement('input');
                input.type = 'hidden';
                input.name = name;
                input.value = value;
                form.appendChild(input);
            }
            // 폼을 문서에 추가 (필수: form이 submit되기 위해)
            document.body.appendChild(form);

            // 폼 제출
            form.submit();

            // 제출 후 폼 제거 (선택 사항)
            document.body.removeChild(form);

            return false;		
        }

        // 리스트 조회
        function getList(param){
            $('#seq').val('');
            $('#userId').val('');
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath}/admin/getadminlist.json",
                data : JSON.stringify(param),
                dataType : 'json',
                contentType : 'application/json;charset=UTF-8',
                beforeSend:function(){
                    $('div.overlay').fadeIn(300)
                },
                complete:function(){
                    $('div.overlay').fadeOut(300)
                },
                success:function(data){
                    if(Array.isArray(data.result)){
                        let htmlcode = '';
                        $.each(data.result,function(i,v){
                            /* 날짜 포맷 -(하이픈)추가 */
                            let inDt = formatDate(v.inDt);
                            let rsrvDtStart = formatDate(v.rsrvDtStart);
                            let rsrvDtEnd = formatDate(v.rsrvDtEnd);
                            
                            htmlcode += '<tr onclick="fn_tableClick(this);" id="tr'+ v.seq +'">';
                            htmlcode += '<td id="srchDataCheckBox" style="width: 80px;">'+'<input type="checkbox" class="grdChckBox" value="'+ v.seq +'"><input type="hidden" id="srchDataUserId" value="' + v.userId + '" ></td>';
                            htmlcode += '<td id="srchDataRsrvDt" >'+rsrvDtStart+'</td>';
                            if('에이플' == '${atLoginVO.loginId}' && '${atLoginVO.loginId}' != '') {
                                htmlcode += '<td id="inDt" class="hiddenByAdmin">' + inDt + '</td>';
                            }
                            htmlcode += '<td id="srchDataOrgNm">'+v.orgNm+'</td>';
                            if(v.status == '대기'){
                                htmlcode += '<td><span class="label label-warning">'+v.status+'</span></td>';
                            }else if(v.status == '승인'){
                                htmlcode += '<td><span class="label label-success">'+v.status+'</span></td>';
                            }else{
                                htmlcode += '<td><span class="label label-danger">'+v.status+'</span></td>';
                            }
                            htmlcode += '<td id="srchDataHallType">'+v.hallType+'</td>';
                            htmlcode += '<td id="srchDataEvntNm">'+v.evntNm+'</td>';

                            htmlcode += '<td id="srchDataEvntDt">'+v.evntDt+'</td>';
                            htmlcode += '<td id="srchDataRprsntrNm">'+v.rprsntrNm+'</td>';
                            htmlcode += '<td id="srchDataTotalPay">'+v.totalPay.toLocaleString()+'</td>';
                            htmlcode += '<td id="srchDataBtchStyl">'+v.btchStylNm+'</td>';
                            htmlcode += '<td hidden="hidden" class="srchSeq" data-seq="'+v.seq+'" value="'+v.seq+'">'+v.seq+'</td>';
                            htmlcode += '<td hidden="hidden" class="srchUserId" data-userId='+v.userId+'>'+v.userId+'</td>';
                            htmlcode += '<td hidden="hidden" class="srchRsrvDtStart" data-target-rsrvDtStart="'+v.rsrvDtStart+'" data-target-rsrvDtEnd="'+v.rsrvDtEnd+'"></td>';

                            /*if (v.comRgstNoFile != null) {
                            	htmlcode += '<td id="srchDataComRgstNoFile"><button data-target-fileNm="' + v.fileNmCmpny +'" data-target-filePath="' + v.filePathCmpny + '" onclick="fn_fileDown(this)" class="fileDown btn  btn-info" value="'+ v.comRgstNoFile +'" style="line-height: 0px; background-color: #1022e0;border-color: #1022e0; height: 25px; border: 0px; color: #fff1f5;">다운로드</button></td>';
                            } else {
                            	htmlcode += '<td></td>';
                            }*/
                            
                            if (v.btchStyl == "C") {
                            	htmlcode += '<td id="srchDataBtchStylFile'+ v.seq +'"><button class="fileDown btn  btn-info" value="'+ v.btchStylFile +'" data-target-fileNm="' + v.fileNm +'" data-target-filePath="' + v.filePath + '" onclick="fn_fileDown(this)" style="line-height: 0px; background-color: #1022e0;border-color: #1022e0; height: 25px; border: 0px; color: #fff1f5;">다운로드</button></td>';
                            } else {
                            	htmlcode += '<td></td>';
                            }

                            /*if(v.grpId == v.seq){
                                htmlcode += '<td><button class="fileDown btn btn-info"  data-json=\''+JSON.stringify(v)+'\'  onclick="printRsrvCntrct(event,\'detail\');"   value="계약서상세출력" style="line-height: 0px; height: 25px; border: 0px; color: #fff1f5;">상세출력</button><br><button class="fileDown btn btn-info"  onclick="printRsrvCntrct(event,\'default\');"  data-json=\''+JSON.stringify(v)+'\'   value="계약서출력" style="line-height: 0px; height: 25px; border: 0px; color: #fff1f5;margin-top:2px;">출력</button></td>';
                            } else {
                                htmlcode += '<td></td>';
                            }*/
                            
                            htmlcode += '<td><button class="btn btn-info" onclick="fn_more('+v.grpId+');">더보기</button></td>'

                            htmlcode += '</tr>';
                        })
                        $('.ibox .ibox-content').empty();
                        $('.ibox .ibox-content').html(staticCode());
                        $('#admListBind').empty();
                        $('#admListBind').html(htmlcode);
                        $('.footable').footable();
                    } else {
                        alert("리스트 불러오기 실패");
                    }
                    if(data.result.length == 0) {
                        // 데이터가 없을때
                        //$('#admListBind').html(footable.options.grid.emptyInfo);
                        $('#admListBind').html('<tr><td colspan="9" class="text-center text-danger">데이터가 없습니다.</td></tr>');
                    }
                },
                error:function(a,b,c){
                    console.log(a,b,c)
                }
            });
        }

        function fn_more(grpId){
            let modalBody = $('#myModal2').children().children().children().eq(1);
            $('#myModal2').children().children().children().eq(0).find('h4').text('신청일자 그룹별 상세내역')
            $('#myModal2').children().children().children().eq(0).find('small').text('')
            
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath}/admin/getadminlist.json",
                data : JSON.stringify({grpId:grpId}),
                dataType : 'json',
                contentType : 'application/json;charset=UTF-8',
                beforeSend:function(){
                    $('div.overlay').fadeIn(300)
                },
                complete:function(){
                    $('div.overlay').fadeOut(300)
                },
                success:function(data){
                    /* 날짜 포맷 -(하이픈)추가 */
                    //let inDt = formatDate(v.inDt);
                    let htmlcode = '<table class="table table-bordered">';
                    htmlcode += '<thead>';
                    htmlcode += '<tr>';
                    htmlcode += '<th>신청일자</th>';
                    htmlcode += '<th>예약일자</th>';
                    htmlcode += '<th>상태</th>';
                    htmlcode += '<th>전시실</th>';
                    htmlcode += '<th>행사명</th>';
                    htmlcode += '<th>시간대</th>';
                    htmlcode += '<th>대표자명</th>';
                    htmlcode += '<th>결재금액</th>';
                    htmlcode += '<th>배치형태</th>';
                    htmlcode += '<th>사업자등록증</th>';
                    htmlcode += '<th>계약서</th>';
                    htmlcode += '</tr>';
                    htmlcode += '</thead>';
                    htmlcode += '<tbody>';
                    $.each(data.result,function(i,v){
                        let fileSeq = typeof v.fileSeqCmpny !='undefined' ? v.fileSeqCmpny : 0;
                        let rsrvDtStart = formatDate(v.rsrvDtStart);
                        let rsrvDtEnd = formatDate(v.rsrvDtEnd);
                        htmlcode += '<tr>';
                        if(i == 0)
                            htmlcode += '<td  style="align-content:center;"  rowspan="'+data.result.length+'">'+formatDate(v.inDt)+'</td>';
                        htmlcode += '<td>'+rsrvDtStart+'</td>';
                        if(v.status == '대기'){
                            htmlcode += '<td><span class="label label-warning">'+v.status+'</span></td>';
                        }else if(v.status == '승인'){
                            htmlcode += '<td><span class="label label-success">'+v.status+'</span></td>';
                        }else{
                            htmlcode += '<td><span class="label label-danger">'+v.status+'</span></td>';
                        }
                        htmlcode += '<td>'+v.hallType+'</td>';
                        htmlcode += '<td>'+v.evntNm+'</td>';
                        htmlcode += '<td>'+v.evntDt+'</td>';
                        htmlcode += '<td>'+v.rprsntrNm+'</td>';
                        htmlcode += '<td>'+v.totalPay.toLocaleString()+'</td>';
                        htmlcode += '<td>'+v.btchStylNm+'</td>';
                        if(i == 0)
                            htmlcode += '<td style="align-content:center;"  rowspan="'+data.result.length+'" ><button data-target-fileNm="' + v.fileNmCmpny +'" data-target-filePath="' + v.filePathCmpny + '" onclick="fn_fileDown(this)" class="fileDown btn  btn-info" value="'+ v.comRgstNoFile +'" style="line-height: 0px; background-color: #1022e0;border-color: #1022e0; height: 25px; border: 0px; color: #fff1f5;">다운로드('+fileSeq+')</button></td>';
                        if(i == 0)
                            htmlcode += '<td style="align-content:center;"  rowspan="'+data.result.length+'" ><button class="fileDown btn btn-info"  data-json=\''+JSON.stringify(v)+'\'  onclick="printRsrvCntrct(event,\'detail\');"   value="계약서상세출력" style="line-height: 0px; height: 25px; border: 0px; color: #fff1f5;">상세출력</button><br><button class="fileDown btn btn-info"  onclick="printRsrvCntrct(event,\'default\');"  data-json=\''+JSON.stringify(v)+'\'   value="계약서출력" style="line-height: 0px; height: 25px; border: 0px; color: #fff1f5;margin-top:2px;">출력</button></td>';
                        htmlcode += '</tr>';                                    
                    })
                    htmlcode += '</tbody>';
                    htmlcode += '</table>';
                    modalBody.html(htmlcode);
                    $('#myModal2').modal('show');
                }
            })
            
            
        }

        // 전역변수 
        var gprcRfrnc = {}
        var geqpmntRfrnc = {}
        // 공통코드 렌더링 (콤보박스,라디오 등등 )
        function getCmmnCode(){
            $.ajax({
                type:"get",
                url:"${pageContext.request.contextPath}/front/getcmmnrfrnc.json",
                data : {},
                success:function(data){
                    gprcRfrnc = data.prcRfrnc;
                    geqpmntRfrnc = data.eqpmntRfrnc;
                    let statuscode = '<option value="" selected>전체</option>';
                    let halltypecode = '<option value="" selected>전체</option>';
                    let btchcode = '';
                    $.each(data.cmmnCode,function(i,v){
                        if(v.gpId == 'SR_STS_GP')
                            statuscode += '<option value="'+v.cdId+'" >'+v.cdNm+'</option>';
                        if(v.gpId == 'SR_GP')
                            halltypecode += '<option value="'+v.cdId+'" >'+v.cdNm+'</option>';
                        if(v.gpId == 'SR_BTCH_GP')
                            btchcode += '<option value="'+v.cdId+'" >'+v.cdNm+'</option>';

                    })

                    $('#srchStatus').empty();
                    $('#srchStatus').html(statuscode);
                    $('#srchHallType').empty();
                    $('#srchHallType').html(halltypecode);
                    $('#hallType').empty();
                    $('#hallType').html(halltypecode);
                    $('#style').empty();
                    $('#style').html(btchcode);
                    // 전체 옵션 삭제
                    $('#hallType option:eq(0)').remove()
                },error:function(a,b,c){
                    console.log(a,b,c)
                }
            });
        }

        // 문서가 렌더링되면 제일먼저 실행  
        $(document).ready(function() {
            let loginId = '${atLoginVO.loginId}';
            
            if(loginId != '' && loginId == '에이플'){
                $('.hiddenByAdmin').css('display','none');
                $('.disabledByAdmin').attr('disabled',true);
            }else{
                $('.hiddenByAdmin').css('display','block');
                $('.disabledByAdmin').attr('disabled',false);
            }

            chgLabelColor();

            let currentDate = new Date();
            let searchDate = currentDate.toISOString().split('T')[0];
            $('#rsrvDt1').val(searchDate);
            currentDate.setDate(currentDate.getDate() + 14);
            searchDate = currentDate.toISOString().split('T')[0];
            $('#rsrvDt2').val(searchDate);

            getList(mkSrchPrm());
            
            getCmmnCode();

            jquryDatePicker();
            
        });
        
        function fn_fileDown(data) {
            let fileNm = data.getAttribute('data-target-fileNm');
            let fileType = data.getAttribute('data-target-fileType');
            let filePath = data.getAttribute('data-target-filePath');

            var url = '${pageContext.request.contextPath}/admin/fileDownLoad.do';

            var formData = new FormData();
            formData.append('fileNm', fileNm);
            formData.append('fileType', fileType);
            formData.append('filePath', filePath);

        	// 파일 내용 준비
        	var xhr = new XMLHttpRequest();
            xhr.open('POST', url, true);
            xhr.responseType = 'blob';

            xhr.onload = function() {
                var blob = xhr.response;
                var downloadLink = document.createElement('a');
                downloadLink.href = window.URL.createObjectURL(blob);
                downloadLink.download = fileNm;

                document.body.appendChild(downloadLink);
                downloadLink.click();
                document.body.removeChild(downloadLink);
            };

            xhr.send(formData);

        }
        
        
        
        //그리드 로우 선택시 이벤트 
        function fn_tableClick(data){
            let seq = $(data).find('.srchSeq').data('seq');
            //let userId = $(data).find('.srchUserId').data('userId');
            let userId = $(data).find('.srchUserId')[0].textContent;
            $('#seq').val(seq);
            $('#userId').val(userId);
            $('tr').removeClass('clickRow');
            $('#tr'+seq).addClass('clickRow');
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath}/admin/getadminlist.json",
                data : JSON.stringify({"seq":seq,"userId":userId}),
                dataType : 'json',
                contentType : 'application/json;charset=UTF-8',
                beforeSend: function() {
                    // Show loading image or spinner
                    // You can add your own code here to display a loading image or spinner
                    $('div.overlay').fadeIn(300)
                },
                complete: function() {
                    // Hide loading image or spinner
                    // You can add your own code here to hide the loading image or spinner
                    $('div.overlay').fadeOut(300)
                },
                success:function(data){
                    if(Array.isArray(data.result)){
                        /* 날짜 포맷 -(하이픈)추가 */
                        let inDt = formatDate(data.result[0].inDt);
                        let rsrvDtStart = formatDate(data.result[0].rsrvDtStart);
                        let rsrvDtEnd = formatDate(data.result[0].rsrvDtEnd);

                        /* 신청일자 */
                        $('#clickDataInDt').val(inDt);
                        /* 예약일자 */
                        $('#clickDataRsrvDtStart').val(rsrvDtStart);
                        $('#clickDataRsrvDtEnd').val(rsrvDtEnd);
                        /* 전시실 */
                        $('#hallType').val(data.result[0].hallTypeCd);
                        /* 행사내용 */
                        $('#evntDesc').val(data.result[0].evntDesc);
                        /* 예약시간 */
                        $('input[name="evntDt0"][value="' + data.result[0].evntDtCd + '"]').prop('checked', true);
                        /* 냉난방여부 */
                        if(data.result[0].isTemp === 'Y'){
                            $('#isTemp').prop('checked', true);
                        }else{
                            $('#isTemp').prop('checked', false);
                        }
                        /* 행사명 */
                        $('#evntNm').val(data.result[0].evntNm);
                        /* 상호/단체명 */
                        $('#orgNm').val(data.result[0].orgNm);
                        /* 대표자명 */
                        $('#rprsntrNm').val(data.result[0].rprsntrNm);
                        /* 담당자명 */
                        $('#mngrNm').val(data.result[0].mngrNm);
                        /* 사업자번호 */
                        $('#comRgstNo').val(data.result[0].comRgstNo);
                        /* 업테 */
                        $('#bizType').val(data.result[0].bizType);
                        /* 종목 */
                        $('#bizType2').val(data.result[0].bizType2);
                        /* 전화번호 */
                        $('#telNo').val(data.result[0].telNo);
                        /* 이메일 */
                        $('#email').val(data.result[0].email);
                        /* 주소 */
                        $('#address').val(data.result[0].address);
                        /* 주소 */
                        $('#addressDtl').val(data.result[0].addressDtl);
                        /* 무선마이크 */
                        $('#wlsMic').val(data.result[0].wlsMic);
                        /* 유선마이크 */
                        $('#wMic').val(data.result[0].wMic);
                        /* 방송엠프 */
                        $('#bAmp').val(data.result[0].bAmp);
                        /* 고객요청사항 */
                        $('#etc').val(data.result[0].etc);
                        /* 프로젝터 */
                        $('#projector').val(data.result[0].prjctr);
                        /* 탁자 */
                        $('#table').val(data.result[0].tbl);
                        /* 의자 */
                        $('#chair').val(data.result[0].chr);
                        /* 쓰래기봉투 */
                        $('#garbage').val(data.result[0].grbgPck);
                        /* 배치형태 */
                        $('#style').val(data.result[0].btchStyl);
                        /* 임대료 */
                        $('#rentPay').val(data.result[0].rentPay);
                        /* 관리비 */
                        $('#mngPay').val(data.result[0].mngPay);
                        /* 장비사용료 */
                        $('#eqpmntPay').val(data.result[0].eqpmntPay);
                        /* 합계금액 */
                        $('#totalPay').val(data.result[0].totalPay);
                        /* 합계금액 */
                        $('#totalPay').val(data.result[0].totalPay);
                        /* 상태 */
                        $('input[name="status0"][value="' + data.result[0].statusCd + '"]').prop('checked', true);
                        /* 복도선택여부 */
                        if(data.result[0].iscorrdr === 'Y'){
                            $('#iscorrdr').prop('checked', true);
                        }else{
                            $('#iscorrdr').prop('checked', false);
                        }
                        /* 팩스번호 */
                        $('#faxNo').val(data.result[0].faxNo);
                        /* 사용자명 */
                        $('#userId').val(data.result[0].userId);

                        $('#discount').val(Math.abs(data.result[0].discount));
                    } else {
                        alert("리스트 불러오기 실패");
                    }
                },
                error:function(a,b,c){
                    console.log(a,b,c)
                }
            });
        }

        function formatDate(inputDate) {
            var year = inputDate.substring(0, 4);
            var month = inputDate.substring(4, 6);
            var day = inputDate.substring(6, 8);

            var date = new Date(year, month-1, day);

            return date.getFullYear() + '-' + ('0' + (date.getMonth() + 1)).slice(-2) + '-' + ('0' + date.getDate()).slice(-2);
        }
        const deleteFormatDate = function(inputDate){
            let year, month, day;
            if (inputDate.includes('-')) {
                const newInputDate = inputDate.split('-');
                year = newInputDate[0];
                month = newInputDate[1];
                day = newInputDate[2];
            } else {
                year = inputDate.substring(0, 4);
                month = inputDate.substring(4, 6);
                day = inputDate.substring(6, 8);
            }
            var nextMonthFirstDay = new Date(year, month, 0);
            var lastDay = nextMonthFirstDay.getDate();

            if (month < 1 || month > 12) {
                alert('ㅇㅇㅇ날짜 오류ㅇㅇㅇ');
                return false;
            }
            if(day>lastDay) {
                alert('날짜 오류');
                return false;
            }

            return year +''+ month +''+ day;
        }

        const jquryDatePicker = async function(){
            // $.datepicker.setDefaults($.datepicker.regional['ko']);
            $.fn.datepicker.dates['kr'] = {
                days: ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일"],
                daysShort: ["일", "월", "화", "수", "목", "금", "토", "일"],
                daysMin: ["일", "월", "화", "수", "목", "금", "토", "일"],
                months: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
                monthsShort: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"]
            };
            $('#inDt1, #inDt2, #rsrvDt1, #rsrvDt2').datepicker({
                changeMonth: true,
                changeYear: true,
                format: 'yyyy-mm-dd',
                showButtonPanel: true,
                language: "kr"
            });
        }


        const fn_clckModi = async function(){
            let seq = $('#seq').val();
            let inDt = $('#clickDataInDt').val();
            let rsrvDtStart = $('#clickDataRsrvDtStart').val();
            let rsrvDtEnd = $('#clickDataRsrvDtEnd').val();
            let hallType = $('#hallType').val();
            let evntDesc = $('#evntDesc').val();
            let evntDt = $('input[name="evntDt0"]:checked').val();
            let isTemp = $('#isTemp').is(':checked') ? 'Y' : 'N';
            let evntNm = $('#evntNm').val();
            let orgNm = $('#orgNm').val();
            let rprsntrNm = $('#rprsntrNm').val();
            let mngrNm = $('#mngrNm').val();
            let comRgstNo = $('#comRgstNo').val();
            let bizType = $('#bizType').val();
            let bizType2 = $('#bizType2').val();
            let telNo = $('#telNo').val();
            let email = $('#email').val();
            let address = $('#address').val();
            let addressDtl = $('#addressDtl').val();
            let wlsMic = $('#wlsMic').val();
            let wMic = $('#wMic').val();
            let bAmp = $('#bAmp').val();
            let etc = $('#etc').val();
            let projector = $('#projector').val();
            let table = $('#table').val();
            let chair = $('#chair').val();
            let garbage = $('#garbage').val();
            let style = $('#style').val();
            let rentPay = $('#rentPay').val();
            let mngPay = $('#mngPay').val();
            let eqpmntPay = $('#eqpmntPay').val();
            let totalPay = $('#totalPay').val();
            let status = $('input[name="status0"]:checked').val();
            let iscorrdr = $('#iscorrdr').prop('checked') ? 'Y' : 'N';
            let faxNo = $('#faxNo').val();
            let userId = $('#userId').val();
            let discount = Math.abs($('#discount').val());

            if (deleteFormatDate(inDt) === false){ alert('입력 날짜가 형식에 맞지 않습니다.');return;}else{inDt = deleteFormatDate(inDt)}
            if (deleteFormatDate(rsrvDtStart) === false){ alert('입력 날짜가 형식에 맞지 않습니다.');return;}else{rsrvDtStart = deleteFormatDate(rsrvDtStart)}
            if (deleteFormatDate(rsrvDtEnd) === false){ alert('입력 날짜가 형식에 맞지 않습니다.');return;}else{rsrvDtEnd = deleteFormatDate(rsrvDtEnd)}
            if(!confirm('수정 하시겠습니까 ?')){
                return;
            }

            let price = autoCalc(hallType,evntDt,wlsMic,wMic,bAmp,projector,table,chair,garbage);

            var rsrvtnData = {
                inDt: inDt,
                rsrvDtStart: rsrvDtStart,
                rsrvDtEnd: rsrvDtEnd,
                hallType: hallType,
                evntDesc: evntDesc,
                evntDt: evntDt,
                isTemp: isTemp,
                evntNm: evntNm,
                orgNm: orgNm,
                rprsntrNm: rprsntrNm,
                mngrNm: mngrNm,
                comRgstNo: comRgstNo,
                bizType: bizType,
                bizType2: bizType2,
                telNo: telNo,
                email: email,
                address: address,
                addressDtl: addressDtl,
                wlsMic: wlsMic,
                wMic: wMic,
                bAmp: bAmp,
                etc: etc,
                prjctr: projector,
                tbl: table,
                chr: chair,
                grbgPck: garbage,
                btchStyl: style,
                rentPay: price.rentPay,
                mngPay: price.mngPay,
                eqpmntPay: price.eqpmntPay,
                totalPay: price.totalPay,
                status: status,
                seq:seq,
                iscorrdr:iscorrdr,
                faxNo:faxNo,
                stts:"U",
                userId:userId,
                discount:discount
            }

            var formData = new FormData();

            formData.append("rsrvtnData", new Blob([JSON.stringify(rsrvtnData)], { type: "application/json" }));
            formData.append("act", "move");
            $.ajax({
                url: '${pageContext.request.contextPath}/admin/multiRsrvtn.json',
                processData: false,
                contentType: false,
                data: formData,
                type: 'POST',
                success:function(data){
                    if(data.result.code === 'E'){
                        alert(data.result.msg);
                    }
                    fn_reset();
                    getList(mkSrchPrm());
                },
                error:function(a,b,c){
                    console.log(a,b,c)
                }
            });
        }

        const fn_reset = async function(){
            $('#seq').val('');
            $('#userId').val('');
            $('#clickDataInDt').val('');
            $('#clickDataRsrvDtStart').val('');
            $('#clickDataRsrvDtEnd').val('');
            $('#hallType').val('1');
            $('#evntDesc').val('');
            $('#evntNm').val('');
            $('#orgNm').val('');
            $('#rprsntrNm').val('');
            //$('#mngrNm').val('');
            $('#comRgstNo').val('');
            $('#bizType').val('');
            $('#bizType2').val('');
            //$('#telNo').val('');
            //$('#email').val('');
            $('#address').val('');
            $('#addressDtl').val('');
            $('#wlsMic').val('');
            $('#wMic').val('');
            $('#bAmp').val('');
            $('#etc').val('');
            $('#projector').val('');
            $('#table').val('');
            $('#chair').val('');
            $('#garbage').val('');
            $('#style').val('');
            $('#rentPay').val('');
            $('#mngPay').val('');
            $('#eqpmntPay').val('');
            $('#totalPay').val('');
            $('#faxNo').val('');
            $('#discount').val('');

            $('#evntDt3').prop('checked', true);
            $('#isTemp').prop('checked', false);
            $('#status3').prop('checked', true);
            $('#iscorrdr').prop('checked', false);
        }

        const fn_save = function (){
            if(!confirm('저장하시겠습니까 ?')){
                return;
            }
            let inDt = $('#clickDataInDt').val();
            let rsrvDtStart = $('#clickDataRsrvDtStart').val();
            let rsrvDtEnd = $('#clickDataRsrvDtEnd').val();
            let hallType = $('#hallType').val();
            let evntDesc = $('#evntDesc').val();
            let evntDt = $('input[name="evntDt0"]:checked').val();
            let isTemp = $('#isTemp').is(':checked') ? 'Y' : 'N';
            let evntNm = $('#evntNm').val();
            let orgNm = $('#orgNm').val();
            let rprsntrNm = $('#rprsntrNm').val();
            let mngrNm = $('#mngrNm').val();
            let comRgstNo = $('#comRgstNo').val();
            let bizType = $('#bizType').val();
            let bizType2 = $('#bizType2').val();
            let telNo = $('#telNo').val();
            let email = $('#email').val();
            let address = $('#address').val();
            let addressDtl = $('#addressDtl').val();
            let wlsMic = $('#wlsMic').val();
            let wMic = $('#wMic').val();
            let bAmp = $('#bAmp').val();
            let etc = $('#etc').val();
            let projector = $('#projector').val();
            let table = $('#table').val();
            let chair = $('#chair').val();
            let garbage = $('#garbage').val();
            let style = $('#style').val();
            let rentPay = $('#rentPay').val();
            let mngPay = $('#mngPay').val();
            let eqpmntPay = $('#eqpmntPay').val();
            let totalPay = $('#totalPay').val();
            let status = $('input[name="status0"]:checked').val();
            let iscorrdr = $('#iscorrdr').prop('checked') ? 'Y' : 'N';
            let faxNo = $('#faxNo').val();
            let userId = $('#userId').val();
            let discount = Math.abs($('#discount').val());

            let price = autoCalc(hallType,evntDt,wlsMic,wMic,bAmp,projector,table,chair,garbage);
            
            var rsrvtnData = {
            		inDt: deleteFormatDate(inDt),
                    rsrvDtStart: deleteFormatDate(rsrvDtStart),
                    rsrvDtEnd: deleteFormatDate(rsrvDtEnd),
                    hallType: hallType,
                    evntDesc: evntDesc,
                    evntDt: evntDt,
                    isTemp: isTemp,
                    evntNm: evntNm,
                    orgNm: orgNm,
                    rprsntrNm: rprsntrNm,
                    mngrNm: mngrNm,
                    comRgstNo: comRgstNo,
                    bizType: bizType,
                    bizType2: bizType2,
                    telNo: telNo,
                    email: email,
                    address: address,
                    addressDtl:addressDtl,
                    wlsMic: wlsMic,
                    wMic: wMic,
                    bAmp: bAmp,
                    etc: etc,
                    prjctr: projector,
                    tbl: table,
                    chr: chair,
                    grbgPck: garbage,
                    btchStyl: style,
                    rentPay: price.rentPay,
                    mngPay: price.mngPay,
                    eqpmntPay: price.eqpmntPay,
                    totalPay: price.totalPay,
                    status: status,
                    iscorrdr:iscorrdr,
                    faxNo:faxNo,
                    stts:"I",
                    userId:userId,
                    discount:discount
            }
            
            var formData = new FormData();
            
            formData.append("rsrvtnData", new Blob([JSON.stringify(rsrvtnData)], { type: "application/json" }));
            formData.append("act", "move");
            $.ajax({
            	url: '${pageContext.request.contextPath}/admin/multiRsrvtn.json',
    			processData: false,
    			contentType: false,
    			data: formData,
    			type: 'POST',
                success:function(data){
                    if(data.result.code === 'E'){
                        alert(data.result.msg);
                    }
                    fn_reset();
                    getList(mkSrchPrm());
                },
                error:function(a,b,c){
                    console.log(a,b,c)
                }
            });
        }

        const fn_clckStatus = function(status) {
            var checkedValues = {};
            $('.grdChckBox:checked').each(function () {
                var seq = $(this).val();
                var userId = $(this).closest('tr').find('.srchUserId').attr('data-userid');
                var inptYmdFrom = $(this).closest('tr').find('.srchUserId').attr('data-userid');

                checkedValues[seq] = {"seq":seq ,"userId":userId, "status":status};
            });

            $.ajax({
                url: '${pageContext.request.contextPath}/admin/updateStatusList.json',
                data: JSON.stringify(checkedValues),
                contentType: 'application/json',
                async: false,
                type: 'POST',
                success:function(data){
                    fn_reset();
                    getList(mkSrchPrm());
                    if (data.result !== null && "E" === data.result.code) {
                        alert(data.result.msg);
                    }
                },
                error:function(a,b,c){
                    console.log(a,b,c);
                }
            });
        }

        const fn_clckDelete = function() {
            var deleteUserInfo = {};

            let seq = $('#seq').val();
            let userId = $('#userId').val();

            if(seq==='' || userId===''){
                alert('선택 없음');
                return;
            }
            if(!confirm('삭제하시겠습니까?')){
                return;
            }
            deleteUserInfo ={
                seq:seq,
                userId:userId
            }

            $.ajax({
                url: '${pageContext.request.contextPath}/admin/deleteUserInfo.json',
                data: JSON.stringify(deleteUserInfo),
                contentType: 'application/json',
                async: false,
                type: 'POST',
                success:function(data){
                    fn_reset();
                    getList(mkSrchPrm());
                },
                error:function(a,b,c){
                    console.log(a,b,c);
                }
            });
        }

        const changePayInput = async function(){
            return false;
            let rentPay = parseInt($('#rentPay').val());
            let mngPay = parseInt($('#mngPay').val());
            let eqpmntPay = parseInt($('#eqpmntPay').val());

            if (isNaN(rentPay)) { rentPay = 0; }
            if (isNaN(mngPay)) { mngPay = 0; }
            if (isNaN(eqpmntPay)) { eqpmntPay = 0; }

            $('#totalPay').val(rentPay + mngPay + eqpmntPay);
        }

        const autoCalc = function(hallType,evntDt,wlsMic,wMic,bAmp,projector,table,chair,garbage){
            let hall = $('#hallType option:selected').text()
            let iscorrdr = $('#iscorrdr').is(':checked')
            let isTemp = $('#isTemp').is(':checked')
            let corrdrRentAM = 0;
            let corrdrRentPM = 0;
            let corrdrRentALL = 0;
            let corrdrMngAM = 0;
            let corrdrMngPM = 0;
            let corrdrMngALL = 0;
            for (let i = 0; i < gprcRfrnc.length; i++) {
                if(gprcRfrnc[i]['SHW_RM'] == "전시실복도" && gprcRfrnc[i]['GBN'] == '임대료'){
                    corrdrRentAM = gprcRfrnc[i]['AM'];
                    corrdrRentPM = gprcRfrnc[i]['PM'];
                    corrdrRentALL = gprcRfrnc[i]['ALL_DAY'];
                }
                if(gprcRfrnc[i]['SHW_RM'] == "전시실복도" && gprcRfrnc[i]['GBN'] == '관리비(냉난방제외)'){
                    corrdrMngAM = gprcRfrnc[i]['AM'];
                    corrdrMngPM = gprcRfrnc[i]['PM'];
                    corrdrMngALL = gprcRfrnc[i]['ALL_DAY'];
                }
                
            }

            for (let i = 0; i < gprcRfrnc.length; i++) {
                
                //임대료
                if(gprcRfrnc[i]['GBN'] == '임대료' && gprcRfrnc[i]['SHW_RM'] == hall && evntDt == "AM"){
                    if(iscorrdr){
                        $('#rentPay').val(gprcRfrnc[i]['AM'] + corrdrRentAM )
                        console.log(corrdrRentAM + "값이 추가 ")
                    } else {
                        $('#rentPay').val(gprcRfrnc[i]['AM'] )
                        console.log("변함없음")
                    }
                    
                }
                if(gprcRfrnc[i]['GBN'] == '임대료' && gprcRfrnc[i]['SHW_RM'] == hall && evntDt == "PM"){
                    if(iscorrdr){
                        $('#rentPay').val(gprcRfrnc[i]['PM'] + corrdrRentPM )
                        console.log(corrdrRentPM + "값이 추가 ")
                    } else {
                        $('#rentPay').val(gprcRfrnc[i]['PM'] )
                        console.log("변함없음")
                    }
                }
                if(gprcRfrnc[i]['GBN'] == '임대료' && gprcRfrnc[i]['SHW_RM'] == hall && evntDt == "ALL"){
                    if(iscorrdr){
                        $('#rentPay').val(gprcRfrnc[i]['ALL_DAY'] + corrdrRentALL )
                        console.log(corrdrRentALL + "값이 추가 ")
                    } else {
                        $('#rentPay').val(gprcRfrnc[i]['ALL_DAY'] )
                        console.log("변함없음")
                    }
                }
                //관리비
                if(gprcRfrnc[i]['GBN'] == '관리비(냉난방제외)' && gprcRfrnc[i]['SHW_RM'] == hall && !isTemp && evntDt == "AM"){
                    if(iscorrdr){
                        $('#mngPay').val(gprcRfrnc[i]['AM'] + corrdrMngAM )
                        console.log(corrdrMngAM + "값이 추가 ")
                    } else {
                        $('#mngPay').val(gprcRfrnc[i]['AM'] )
                        console.log("변함없음")
                    }
                }
                if(gprcRfrnc[i]['GBN'] == '관리비(냉난방제외)' && gprcRfrnc[i]['SHW_RM'] == hall && !isTemp && evntDt == "PM"){
                    if(iscorrdr){
                        $('#mngPay').val(gprcRfrnc[i]['PM'] + corrdrMngPM )
                        console.log(corrdrMngPM + "값이 추가 ")
                    } else {
                        $('#mngPay').val(gprcRfrnc[i]['PM'] )
                        console.log("변함없음")
                    }
                }
                if(gprcRfrnc[i]['GBN'] == '관리비(냉난방제외)' && gprcRfrnc[i]['SHW_RM'] == hall && !isTemp && evntDt == "ALL"){
                    if(iscorrdr){
                        $('#mngPay').val(gprcRfrnc[i]['ALL_DAY'] + corrdrMngALL )
                        console.log(corrdrMngALL + "값이 추가 ")
                    } else {
                        $('#mngPay').val(gprcRfrnc[i]['ALL_DAY'] )
                        console.log("변함없음")
                    }
                }
                if(gprcRfrnc[i]['GBN'] == '관리비(냉난방포함)' && gprcRfrnc[i]['SHW_RM'] == hall && isTemp && evntDt == "AM"){
                    if(iscorrdr){
                        $('#mngPay').val(gprcRfrnc[i]['AM'] + corrdrMngAM )
                        console.log(corrdrMngAM + "값이 추가 ")
                    } else {
                        $('#mngPay').val(gprcRfrnc[i]['AM'] )
                        console.log("변함없음")
                    }
                }
                if(gprcRfrnc[i]['GBN'] == '관리비(냉난방포함)' && gprcRfrnc[i]['SHW_RM'] == hall && isTemp && evntDt == "PM"){
                    if(iscorrdr){
                        $('#mngPay').val(gprcRfrnc[i]['PM'] + corrdrMngPM )
                        console.log(corrdrMngPM + "값이 추가 ")
                    } else {
                        $('#mngPay').val(gprcRfrnc[i]['PM'] )
                        console.log("변함없음")
                    }
                }
                if(gprcRfrnc[i]['GBN'] == '관리비(냉난방포함)' && gprcRfrnc[i]['SHW_RM'] == hall && isTemp && evntDt == "ALL"){
                    if(iscorrdr){
                        $('#mngPay').val(gprcRfrnc[i]['ALL_DAY'] + corrdrMngALL )
                        console.log(corrdrMngALL + "값이 추가 ")
                    } else {
                        $('#mngPay').val(gprcRfrnc[i]['ALL_DAY'] )
                        console.log("변함없음")
                    }
                }
            }

            // 장비 사용료
            
            var eqpmntPay = 0;
            for (let i = 0; i < geqpmntRfrnc.length; i++){
                if(geqpmntRfrnc[i]['HALL_TYPE'] == hallType && geqpmntRfrnc[i]['EQPMNT_NM'] == '무선마이크' && (evntDt == "AM" || evntDt == "PM")){
                    eqpmntPay = eqpmntPay + ((geqpmntRfrnc[i]['HALF_PAY']) * (wlsMic == '' ? 0 : wlsMic))
                    console.log(eqpmntPay)
                }
                if(geqpmntRfrnc[i]['HALL_TYPE'] == hallType && geqpmntRfrnc[i]['EQPMNT_NM'] == '무선마이크' && evntDt == "ALL"){
                    eqpmntPay = eqpmntPay + ((geqpmntRfrnc[i]['DAY_PAY']) * (wlsMic == '' ? 0 : wlsMic))
                    console.log(eqpmntPay)
                }
                if(geqpmntRfrnc[i]['HALL_TYPE'] == hallType && geqpmntRfrnc[i]['EQPMNT_NM'] == '유선마이크' && (evntDt == "AM" || evntDt == "PM")){
                    eqpmntPay = eqpmntPay + ((geqpmntRfrnc[i]['HALF_PAY']) * (wMic == '' ? 0 : wMic))
                    console.log(eqpmntPay)
                }
                if(geqpmntRfrnc[i]['HALL_TYPE'] == hallType && geqpmntRfrnc[i]['EQPMNT_NM'] == '유선마이크' && evntDt == "ALL"){
                    eqpmntPay = eqpmntPay + ((geqpmntRfrnc[i]['DAY_PAY']) * (wMic == '' ? 0 : wMic))
                    console.log(eqpmntPay)
                }
                if(geqpmntRfrnc[i]['HALL_TYPE'] == hallType && geqpmntRfrnc[i]['EQPMNT_NM'] == '방송엠프' && (evntDt == "AM" || evntDt == "PM")){
                    eqpmntPay = eqpmntPay + ((geqpmntRfrnc[i]['HALF_PAY']) * (bAmp == '' ? 0 : bAmp))
                    console.log(eqpmntPay)
                }
                if(geqpmntRfrnc[i]['HALL_TYPE'] == hallType && geqpmntRfrnc[i]['EQPMNT_NM'] == '방송엠프' && evntDt == "ALL"){
                    eqpmntPay = eqpmntPay + ((geqpmntRfrnc[i]['DAY_PAY']) * (bAmp == '' ? 0 : bAmp))
                    console.log(eqpmntPay)
                }
                if(geqpmntRfrnc[i]['HALL_TYPE'] == hallType && geqpmntRfrnc[i]['EQPMNT_NM'] == '프로젝터' && (evntDt == "AM" || evntDt == "PM")){
                    eqpmntPay = eqpmntPay + ((geqpmntRfrnc[i]['HALF_PAY']) * (projector == '' ? 0 : projector))
                    console.log(eqpmntPay)
                }
                if(geqpmntRfrnc[i]['HALL_TYPE'] == hallType && geqpmntRfrnc[i]['EQPMNT_NM'] == '프로젝터' && evntDt == "ALL"){
                    eqpmntPay = eqpmntPay + ((geqpmntRfrnc[i]['DAY_PAY']) * (projector == '' ? 0 : projector))
                    console.log(eqpmntPay)
                }
                if(geqpmntRfrnc[i]['HALL_TYPE'] == hallType && geqpmntRfrnc[i]['EQPMNT_NM'] == '탁자' && (evntDt == "AM" || evntDt == "PM")){
                    eqpmntPay = eqpmntPay + ((geqpmntRfrnc[i]['HALF_PAY']) * (table == '' ? 0 : table))
                    console.log(eqpmntPay)
                }
                if(geqpmntRfrnc[i]['HALL_TYPE'] == hallType && geqpmntRfrnc[i]['EQPMNT_NM'] == '탁자' && evntDt == "ALL"){
                    eqpmntPay = eqpmntPay + ((geqpmntRfrnc[i]['DAY_PAY']) * (table == '' ? 0 : table))
                    console.log(eqpmntPay)
                }
                if(geqpmntRfrnc[i]['HALL_TYPE'] == hallType && geqpmntRfrnc[i]['EQPMNT_NM'] == '의자' && (evntDt == "AM" || evntDt == "PM")){
                    eqpmntPay = eqpmntPay + ((geqpmntRfrnc[i]['HALF_PAY']) * (chair == '' ? 0 : chair))
                    console.log(eqpmntPay)
                }
                if(geqpmntRfrnc[i]['HALL_TYPE'] == hallType && geqpmntRfrnc[i]['EQPMNT_NM'] == '의자' && evntDt == "ALL"){
                    eqpmntPay = eqpmntPay + ((geqpmntRfrnc[i]['DAY_PAY']) * (chair == '' ? 0 : chair))
                    console.log(eqpmntPay)
                }
                if(geqpmntRfrnc[i]['HALL_TYPE'] == hallType && geqpmntRfrnc[i]['EQPMNT_NM'] == '쓰레기봉투' && (evntDt == "AM" || evntDt == "PM")){
                    eqpmntPay = eqpmntPay + ((geqpmntRfrnc[i]['HALF_PAY']) * (garbage == '' ? 0 : garbage))
                    console.log(eqpmntPay)
                }
                if(geqpmntRfrnc[i]['HALL_TYPE'] == hallType && geqpmntRfrnc[i]['EQPMNT_NM'] == '쓰레기봉투' && evntDt == "ALL"){
                    eqpmntPay = eqpmntPay + ((geqpmntRfrnc[i]['DAY_PAY']) * (garbage == '' ? 0 : garbage))
                    console.log(eqpmntPay)
                }
            }
            $('#eqpmntPay').val(eqpmntPay);
            let rentPay = parseInt($('#rentPay').val());
            let mngPay = parseInt($('#mngPay').val());
            /////let eqpmntPay = parseInt($('#eqpmntPay').val());

            if (isNaN(rentPay)) { rentPay = 0; }
            if (isNaN(mngPay)) { mngPay = 0; }
            if (isNaN(eqpmntPay)) { eqpmntPay = 0; }

            $('#totalPay').val(rentPay + mngPay + eqpmntPay);
            return {totalPay : (rentPay + mngPay + eqpmntPay) - Math.abs($('#discount').val()), rentPay : rentPay, mngPay : mngPay, eqpmntPay : eqpmntPay};

        }

        const chgTime = async function(e){
            return false;
            let hall = $('#hallType option:selected').text()
            let iscorrdr = $('#iscorrdr').is(':checked')
            let isTemp = $('#isTemp').is(':checked')
                        
            for (let i = 0; i < gprcRfrnc.length; i++) {
                for(let obj in gprcRfrnc[i]){
                    if(obj.indexOf(e.target.value) != -1){
                        if(gprcRfrnc[i]['SHW_RM'] == hall && gprcRfrnc[i]['GBN'] == '임대료'){
                            $('#rentPay').val(gprcRfrnc[i][obj])
                        }

                        if(gprcRfrnc[i]['SHW_RM'] == hall && gprcRfrnc[i]['GBN'] == '관리비(냉난방제외)' && !isTemp){
                            $('#mngPay').val(gprcRfrnc[i][obj])
                        }

                        if(gprcRfrnc[i]['SHW_RM'] == hall && gprcRfrnc[i]['GBN'] == '관리비(냉난방포함)' && isTemp){
                            $('#mngPay').val(gprcRfrnc[i][obj])
                        }
                        
                        
                    }
                }
            }
            //console.log(e.target.value)
        }

        const chgLabelColor = async function(e){
            $.each($('.col-form-label'),function(i,v){
                if(i == 2 || i == 3){
                    $(v).parent().parent().css('background-color','#FCF6F5')    
                    $(v).parent().css('background-color','#FCF6F5')    
                    $(v).css('background-color','#FCF6F5')
                    $(v).css('color','#7b9acc')        
                    $(v).parent().css('color','#7b9acc')        
                    $(v).parent().parent().css('color','#7b9acc')
                }
                if(i > 7 && i < 26){
                    $(v).parent().parent().css('background-color','#FCF6F5')    
                    $(v).parent().css('background-color','#FCF6F5')    
                    $(v).css('background-color','#FCF6F5')
                    $(v).css('color','#7b9acc')        
                    $(v).parent().css('color','#7b9acc')        
                    $(v).parent().parent().css('color','#7b9acc')
                } else if(i > 26){
                    $(v).parent().parent().css('background-color','#7b9acc')    
                    $(v).parent().css('background-color','#7b9acc')    
                    $(v).css('background-color','#7b9acc')
                    //$(v).css('color','#FCF6F5')        
                    //$(v).parent().css('color','#FCF6F5')        
                    $(v).parent().parent().css('color','#FCF6F5')       
                     
                }
                $('.form-control').css('color','#121212')
            })
            
        }

    </script>