<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

	<div class="sub_conts_in exception">

		<div class="title_box mt30 web">
			<div class="fl">
				<h4 class="sub_tit_02">업체소개</h4>
			</div>
		</div>

		<!-- 점포소개(S) -->
		<div class="about_market" style="width:100%">
			<div class="am_left am_left45">
				<div class="l_img web">
					<div class="big_img" id="largeImg">
						<c:if test="${mResult.img1 ne null and fn:trim(mResult.img1) ne ''}">
						<img src="${mResult.img1}" alt="${mResult.rcCompName}">
						</c:if>
						<c:if test="${mResult.img2 ne null and fn:trim(mResult.img2) ne ''}">
						<img src="${mResult.img2}" alt="${mResult.rcCompName}">
						</c:if>
						<c:if test="${mResult.img3 ne null and fn:trim(mResult.img3) ne '' }">
						<img src="${mResult.img3}" alt="${mResult.rcCompName}">
						</c:if>
						<c:if test="${mResult.img1 eq null and mResult.img2 eq null and mResult.img3 eq null}">
						<c:set var="noimgestyle" value="width:100%;height:100%;"/>
						</c:if>
						<c:if test="${fn:trim(mResult.img1) eq '' and fn:trim(mResult.img2) eq '' and fn:trim(mResult.img3) eq ''}">
						<c:set var="noimgestyle" value="width:100%;height:100%;"/>
						</c:if>
						<div id="caption" style="top: -25px;"></div>
					</div>
					<div class="thumbs">
						<c:if test="${mResult.img1 ne null and fn:trim(mResult.img1) ne ''}">
							<a href="${mResult.img1}" title="${mResult.rcCompName}" alt="image1" class="b_selected"> 
								<img src="${mResult.img1}" alt="${mResult.rcCompName}" class="">
							</a>
						</c:if>
						<c:if test="${mResult.img2 ne null and fn:trim(mResult.img2) ne ''}">
							<a href="${mResult.img2}" title="${mResult.rcCompName}" alt="image2"> 
								<img src="${mResult.img2}" alt="${mResult.rcCompName}" class="">
							</a>
						</c:if>
						<c:if test="${mResult.img3 ne null and fn:trim(mResult.img3) ne ''}">
							<a href="${mResult.img3}" title="${mResult.rcCompName}" alt="image3"> 
								<img src="${mResult.img3}" alt="${mResult.rcCompName}" class="">
							</a>
						</c:if>
					</div>
				</div>
				<div class="swiper-container mobile">
					<div class="swiper-wrapper">
						<c:if test="${mResult.img1 ne null and fn:trim(mResult.img1) ne ''}">
							<div class="swiper-slide">
								<div class="market_info">
									<div class="m_img">
										<img src="${mResult.img1}" alt="${mResult.rcCompName}">
									</div>
								</div>
							</div>
						</c:if>
						<c:if test="${mResult.img2 ne null and fn:trim(mResult.img2) ne ''}">
							<div class="swiper-slide">
								<div class="market_info">
									<div class="m_img">
										<img src="${mResult.img2}" alt="${mResult.rcCompName}">
									</div>
								</div>
							</div>
						</c:if>
						<c:if test="${mResult.img3 ne null and fn:trim(mResult.img3) ne ''}">
							<div class="swiper-slide">
								<div class="market_info">
									<div class="m_img">
										<img src="${mResult.img3}" alt="${mResult.rcCompName}">
									</div>
								</div>
							</div>
						</c:if>
						<c:if test="${mResult.img1 eq null and mResult.img2 eq null and mResult.img3 eq null}">
							<div class="swiper-slide">
								<div class="market_info">
									<div class="m_img">
										<img src="${pageContext.request.contextPath}/img/noimage2.png" alt="이미지없음">
									</div>
								</div>
							</div>
						</c:if>
						<c:if test="${fn:trim(mResult.img1) eq '' and fn:trim(mResult.img2) eq '' and fn:trim(mResult.img3) eq ''}">
							<div class="swiper-slide">
								<div class="market_info">
									<div class="m_img">
										<img src="${pageContext.request.contextPath}/img/noimage2.png" alt="이미지없음">
									</div>
								</div>
							</div>
						</c:if>
					</div>
					<!-- Add Arrows -->
					<!-- <div class="swiper-button-next"></div>
					<div class="swiper-button-prev"></div> -->
					<script>
						//스와이프
						var swiper = new Swiper('.swiper-container', {
							navigation: {
								nextEl: '.swiper-button-next',
								prevEl: '.swiper-button-prev',	
							},
							loop: true,
							speed:1000,
							autoplay:true
						});	
					</script>
				</div>
				<!-- 추천점포모바일(E) -->
			</div>
			<div class="am_right" style="${noimgestyle}">
					<input type="hidden" name="menuId" value="<c:out value="${paramMap.menuId}"/>" />
					<input type="hidden" name="marketType" id="marketType" value="${mResult.tyCode}" />
					<input type="hidden" name="marketNo" id="marketNo" value="${mResult.roCode}" />
					<input type="hidden" name="tyGroupCode" id="tyGroupCode" value="<c:out value="${paramMap.tyGroupCode}"/>" />
			
				<p class="txt_hash_02">${mResult.rcHashTag}</p>
				<h4 class="sub_tit_08 mt20">${mResult.rcCompName}</h4>
				<p class="txt_01 mt20">${mResult.rcIntro}</p>
				<div class="call_info">
					<ul>
						<li>
							<p class="txt_01 bold">대표자</p>
						</li>
						<li><span class="call_ico_face">${mResult.rcName}</span></li>
						<li>
							<p class="txt_01 bold">연락처</p>
						</li>
						<li><span class="call_ico_phone">${mResult.rcTel}</span></li>
					</ul>
				</div>
				<div class="info_table mt25 pd0">
					<table>
						<caption>점포 위치, 영업시간, 휴무 정보를 알려주는 점포정보 테이블입니다.</caption>
						<colgroup>
							<col style="width: 25%;">
							<col style="width: 75%;">
						</colgroup>
						<tbody>
							<tr>
								<th><p class="txt_j">위 치</p></th>
								<td class="tl">
									<p class="txt_01">
										${mResult.tyCode}-${mResult.roCode}호
									</p>
								</td>
							</tr>
							<tr>
								<th><p class="txt_j">영 업 시 간</p></th>
								<td>
									<c:if test="${paramMap.tyGroupCode eq '3' or paramMap.tyGroupCode eq '4'}">
									<p class="txt_01">0시 ~ 13시(공휴일 낮 12시)</p>
									</c:if>
									<c:if test="${paramMap.tyGroupCode eq '1' or paramMap.tyGroupCode eq '2'}">
									<p class="txt_01">7시 ~ 19시</p>
									</c:if>
									<c:if test="${paramMap.tyGroupCode eq '6'}">
									<p class="txt_01">7시 ~ 21시</p>
									</c:if>
									<c:if test="${paramMap.tyGroupCode eq '5'}">
									<p class="txt_01">7시 ~ 21시</p>
									</c:if>
									<c:if test="${paramMap.tyGroupCode eq '0' or paramMap.tyGroupCode eq '7'}">
									<p class="txt_01">9시 ~ 18시</p>
									</c:if>
								</td>
							</tr>
							<tr>
								<th><p class="txt_j">휴 무</p></th>
								<td>
									<c:if test="${paramMap.tyGroupCode eq '3' or paramMap.tyGroupCode eq '4'}">
									<p class="txt_01">일요일 휴무</p>
									</c:if>
									<c:if test="${paramMap.tyGroupCode eq '1' or paramMap.tyGroupCode eq '2'}">
									<p class="txt_01">가동 : 일요일( 1,3,5주차 및 7,8월)<br> 나동 : 일요일(2,4,5주차 및 7,8월)</p>
									</c:if>
									<c:if test="${paramMap.tyGroupCode eq '6'}">
									<p class="txt_01">일요일 격주 휴무 및 영업</p>
									</c:if>
									<c:if test="${paramMap.tyGroupCode eq '5'}">
									<p class="txt_01">연중무휴</p>
									</c:if>
									<c:if test="${paramMap.tyGroupCode eq '0' or paramMap.tyGroupCode eq '7'}">
									<p class="txt_01">공휴일 휴무</p>
									</c:if>
								</td>
							</tr>
							<!-- <tr>
								<th><p class="txt_j">전 국 택 배</p></th>
								<td><p class="txt_01">${result.deliText }</p></td>
							</tr> -->
						</tbody>
					</table>
				</div>
				<div class="info_btn_box mt20">
					<a href="tel:${mResult.rcTel}" class="link call">전화걸기</a> 
					<a href="javascript:copy_trackback();" 
					 class="link add">주소복사</a> 
					<a href="#!" class="link share" id="printTest">공유하기</a>
				</div>
			</div>
		</div>
		<!-- 점포소개(E) -->

		<div class="title_box mt30 web">
			<div class="fl">
				<h4 class="sub_tit_02">이용별점</h4>
			</div>
		</div>

		<div class="title_box_mbile mobile">
			<h4>이용별점</h4>
		</div>

		<!-- 이용후기 글 남기기(S) -->
		<form name="reviewForm" id="reviewForm" role="form" method="post">
			<input type="hidden" name="menuId" value="<c:out value="${paramMap.menuId}"/>" />
			<input type="hidden" id="marketSeq" name="marketSeq" value="${mResult.marketSeq}" />
			<input type="hidden"  id="nick" name="nickName" value="" />
			<input type="hidden" id="stPoint" name="stPoint" value="5" />
			<input type="hidden" id="cont" name="content" value="" />
		</form>
		<div class="upload_rate">
			<div class="ur_top">
				<!-- 
				<span class="member_face">
					<img src="/yfmc/img/member_face.png" alt="회원얼굴사진">
				</span>
				<p class="member_id"><input type="text" name="nickName" id="nickName" placeholder="닉네임을 입력해 주세요"></p>
				 -->
				<div class="score_box" title="총 5점 중 5점">
					<span class="star on" onclick="starClick('1')" tabindex="0"></span>
					<span class="star on" onclick="starClick('2')" tabindex="0"></span>
					<span class="star on" onclick="starClick('3')" tabindex="0"></span>
					<span class="star on" onclick="starClick('4')" tabindex="0"></span>
					<span class="star on" onclick="starClick('5')" tabindex="0"></span>
					<p class="comment">별점을 선택하세요!</p>
				</div>
				<c:if test="${atLoginVO ne null or snsLoginVO ne null }">	
					<button type="button" onClick="fn_review('${mResult.marketSeq}')" class="btn_upload">별점 등록</button>
				</c:if>
				<c:if test="${atLoginVO eq null and snsLoginVO eq null }">
					<button type="button" onClick="fn_loginPage('${mResult.marketSeq}')" class="btn_upload">로그인</button>
				</c:if>
			</div>
			
			<!-- 
			<form id="fileForm" name="fileForm" method="post" enctype="multipart/form-data">
			<div class="ur_bottom">
				
				<div class="text_photo">
					<textarea id="content" name="content" placeholder="최소 10자 이상 등록해주세요."></textarea>
					<label for="review"></label> 
					<input id="inputFile" type="file" onchange="fn_fileSelect(this);" accept="image/*" style="display:none"/>
					<a href="javascript:fn_fileClick();" class="btn_photo">사진첨부</a>
				</div>
				<button type="button" onClick="fn_review('${result.marketSeq}')" class="btn_upload">등록</button>
			</div>
			</form> 
			<div class="ur_choose_img" id="reviewImgDiv">
				
			</div>-->
		</div>
		<!-- 이용후기 글 남기기(E) -->
		
		<!-- 사용자 총 평점(S) -->
		<div class="upload_rate_02 mt40">
			<div class="ur_02_left">
				<h4 class="tit_total">사용자 총 평점</h4>
				<div class="star_per_box">
					<fmt:parseNumber var="totAvg" value="${stAvg*10}" integerOnly="true" />
					<c:if test="${totAvg eq 0}">
						<c:set var="totAvg" value="00" />
					</c:if>
					<div class="average_score sc${totAvg}">
					
						<!-- 예시 sc50 = 5점 -->
						<img src="${pageContext.request.contextPath}/img/ico_yellow_stars.png" alt="평균별점 ${stAvg}점">
					</div>
				</div>
				<h4 class="tit_score">${stAvg}</h4>
			</div>
			<div class="ur_02_right">
				<h4 class="txt_reple">
					별점건수<span class="re_num">${totCnt}</span>건
				</h4>
			</div>
		</div>
				
				<!-- 리뷰(S) -->
				
			<div class="table_type_accordion">
				<div class="recruit" id="reviewList">
					
				</div>	
			</div>
				

		</div>


		<script type="text/javascript">
		
		function fn_fileSelect(fileObj){
			console.log(fileObj);
			
			var formData = new FormData(); 
			formData.append("imgFile", $("#inputFile")[0].files[0]); 
			$.ajax({ 
				url: '/yfmc/front/market/saveImgFile.json', 
				data: formData, 
				processData: false, 
				contentType: false, 
				type: 'POST', 
				success: function(data){ 
					// /uploads/market/review/
					console.log("/uploads/market/review/"+data.uploadFileList[0].streFileNm);
					var saveFilePath = contextPath+"/uploads/market/review/"+data.uploadFileList[0].streFileNm;
					var html = "";
					html+="<div class=\"answer_box\">                                ";
					html+="	<span class=\"big_img\">                                 ";
					html+="		<img src=\""+saveFilePath+"\" alt=\"\">      ";
					html+="	</span>                                                ";
					html+="</div>                                                  ";
					
					$("#reviewImgDiv").append(html);
					
				} 
			});

			
			
		}
		
		function starClick(i){
			
			$("#stPoint").val(i);
			$(".score_box").attr("title", "총 5점 중 " + i + "점");
		}
		

		
		function fn_review(marketSeq) {

				var nick =$("#nickName").val();
				var cont = $("#content").val();
				var imgDiv = $("#reviewImgDiv").html();
				var stPoint = $("#stPoint").val();
				var imgCnt = $("#reviewImgDiv .answer_box").size();
				
				$("#nick").val(nick);
				$("#cont").val(cont);
	
		
				$.ajax({
					type : 'POST',
					url : "/yfmc/front/market/regReview.json",
					data : {
						nickName : nick,
						content : cont,
						imgDiv : imgDiv,
						marketSeq : marketSeq,
						stPoint : stPoint,
						fileCnt : imgCnt
					},
					success : function(data) {
						if (data.result == "success") {
							$("#nickName").val("");
							$("#content").val("");
							starClick('5');
							$(".star").addClass('on').prevAll('.star').addClass('on');
							//getReviewList(marketSeq);
							window.location.reload(true);
						}else if(data.result == "login"){
							alert("로그인 후 사용해주세요");
						}else if(data.result == "review"){
							alert("이미 등록된 별점입니다.");
						}
					},
					error : function(request, status, error) {
						
	
	
					}
				
	
				});
			
		}
		
		function fn_loginPage(marketSeq) {
			window.location.href = contextPath+"/front/login.do";
		}
		
		
		
		
		$(document).ready(function() {
			var marketSeq = $("#marketSeq").val();
			//getReviewList(marketSeq);
		});

		

		function getReviewList(marketSeq) {
			
			$.ajax({
						type : 'GET',
						url : "<c:url value ='/front/market/reviewList.json'/>",
						dataType : "json",
						data : $("#reviewForm").serialize(),
						contentType : "application/x-www-from-urlencoded; charset=UTF-8",
						success : function(result) {
							var data = result.list;
							var html = "";
							var totCnt = data.length;
							console.log(data);
							
							if (totCnt > 0) {
								for (i = 0; i < totCnt; i++) {
									
									/*
									var str = data[i].imgDiv;
									
									if(str != null){
										var index = str.indexOf("/uploads");
										
										//console.log(index);
										
										var imgSrc= str.substring(133,178);
										console.log(imgSrc);
									}*/
									
									html += "<div id=\"div"+data[i].reviewSeq+"\">";
									if(data[i].fileCnt == 0 || data[i].fileCnt == null){
										html += "<ul class=\"item no_photo\">";	
									}else{
										html += "<ul class=\"item\">";
									}
									html += "<li>";
						  			html += "<span class=\"member_face_02\">"
									html +=	"<img src=\"${pageContext.request.contextPath}/img/member_face.png\" alt=\"회원얼굴사진\"></span><p class=\"member_id_02\">"
											+ data[i].nickName
											+ "</p>";
									html += "<span class=\"reple_date\">"
											+ data[i].insertDate
											+"</span>";
									html += "</li>";
									html += "<li>";
									html += "<p class=\"txt_01\">"
											+ data[i].content
											+ "</p>";
									html += "</li>";
									html += "<li>";	
									if(data[i].fileCnt == 0 || data[i].fileCnt == null){
																		
										//html += "<div class =\"upload_img\">";
										//html += "<p class=\"img_total\">"
										//html += data[i].fileCnt
										//html += "</p>";
										//html += "</div>";
									//	html += "<img src=>";
										
									} else{
										html += "<div class =\"upload_img\">";
										html += "<img id=\"img"+data[i].reviewSeq+"\" >";
										html += "<p class=\"img_total\">"
										html += "+"+data[i].fileCnt
										html += "</p>";
										html += "</div>";
									}
									html += "</li>";
									html += "<li>";
									if(data[i].stPoint==1){
										html += "<div class=\"score_box_02\">        ";
										html += "	<span class=\"star on\"></span>  ";
										html += "	<span class=\"star\"></span>     ";
										html += "	<span class=\"star\"></span>     ";
										html += "	<span class=\"star\"></span>     ";
										html += "	<span class=\"star\"></span>     ";
										html += "</div>                            ";
									} else if(data[i].stPoint==2){
										html += "<div class=\"score_box_02\">        ";
										html += "	<span class=\"star on\"></span>  ";
										html += "	<span class=\"star on\"></span>     ";
										html += "	<span class=\"star\"></span>     ";
										html += "	<span class=\"star\"></span>     ";
										html += "	<span class=\"star\"></span>     ";
										html += "</div>                            ";
									} else if(data[i].stPoint==3){
										html += "<div class=\"score_box_02\">        ";
										html += "	<span class=\"star on\"></span>  ";
										html += "	<span class=\"star on\"></span>     ";
										html += "	<span class=\"star on\"></span>     ";
										html += "	<span class=\"star\"></span>     ";
										html += "	<span class=\"star\"></span>     ";
										html += "</div>                            ";
									} else if(data[i].stPoint==4){
										html += "<div class=\"score_box_02\">        ";
										html += "	<span class=\"star on\"></span>  ";
										html += "	<span class=\"star on\"></span>     ";
										html += "	<span class=\"star on\"></span>     ";
										html += "	<span class=\"star on\"></span>     ";
										html += "	<span class=\"star\"></span>     ";
										html += "</div>                            ";
									} else {
										html += "<div class=\"score_box_02\">        ";
										html += "	<span class=\"star on\"></span>  ";
										html += "	<span class=\"star on\"></span>     ";
										html += "	<span class=\"star on\"></span>     ";
										html += "	<span class=\"star on\"></span>     ";
										html += "	<span class=\"star on\"></span>     ";
										html += "</div>                            ";
									}
									html += "</li>";
									html += "</ul>";
									
									
									html += "<ul class=\"hide details\">                                     ";
									if(data[i].fileCnt > 0){
										html += "	<li>                                                       ";
										html += data[i].imgDiv;
										html += "	</li>                                                      ";
										html += "</ul>                                                         ";	
									}
									html += "</div>";
									
								}
							} else {
								html += "<div>";
								html += "<div><table class='table'><h6><strong>등록된 댓글이 없습니다.</strong></h6>";
								html += "</table></div>";
								html += "</div>";
							}
							$("#totCnt").html(totCnt);
							$("#reviewList").html(html);
							
							if (totCnt > 0) {
								for (i = 0; i < totCnt; i++) {
									if($("#div"+data[i].reviewSeq+" .big_img").size() > 0){
										$("#img"+data[i].reviewSeq).attr("src",$("#div"+data[i].reviewSeq+" .big_img img").eq(0).attr("src"));
									}
								}
							}
							
							recruitClickInit();

						},
						error : function(request, status, error) {
							
							console.log(error);
							alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
						}
					});
		}

		
		
		function copy_trackback() {
			var trb = window.location.href;
			var IE=(document.all)?true:false;
			if (IE) {
			if(confirm("이 글의 주소를 클립보드에 복사하시겠습니까?"))
				window.clipboardData.setData("Text", trb);
			} else {
				temp = prompt("이 글의 주소입니다. Ctrl+C를 눌러 클립보드로 복사하세요", trb);
			}
		}

		function fn_fileClick(){
			console.log($("#inputFile"));
			$("#inputFile").click();
		}
		
		$("#printTest").click(function(e){
		    if (navigator.share === undefined){
		        alert('지원하지 않는 기기입니다.');
		    }else{
		    	window.navigator.share({
					  title: '양재동 화훼공판장 점포소개', // 공유될 제목
					  text: $(".sub_tit_08").html(), // 공유될 설명
					  url: ''
				});
		    }
		});
		
		
		
		$(".score_box .star").on("keydown", function(e) {
			
			var keyCode = e.keyCode;
			
			if(keyCode == 13) {
				starClick($(this).index()+1);
				$(this).parent().children('.star').removeClass('on');
				$(this).addClass('on').prevAll('.star').addClass('on');
			}
		})
		
		

</script>
