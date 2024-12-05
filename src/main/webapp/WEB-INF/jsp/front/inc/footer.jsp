<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<title>양재꽃시장 화훼공판장·F스퀘어</title>

<style>
#footerSelBtn {height: 30px; margin-top: 10px; background: linear-gradient( to right, #a353c3, #6b53c3 ); color: #ffffff; font-size: 14px;}
</style>


		<div class="footer">
			<div class="f_top">
				<div class="ft_in">
					<div class="fl">
						<a href="${pageContext.request.contextPath}/front/content/view.do?contentId=18&menuId=22" class="btn_f">홈페이지이용안내</a>
						<span class="f_bar">|</span>
						<a href="javascript:window.open('http://www.at.or.kr/html/member/at_acount_2.html','개인정보처리방침','width=865px,height=680px,scrollbars=yes');void(0);" class="btn_f" title="개인정보처리방침 (새 창으로 이동)"><b>개인정보처리방침</b></a>
						<span class="f_bar">|</span>
						<a href="http://www.at.or.kr/privacy/apko372000/intro.action" target="_BLANK" class="btn_f" title="개인정보침해신고 (새 창으로 이동)">개인정보침해신고</a>
						<span class="f_bar">|</span>
						<a href="javascript:window.open('http://www.at.or.kr/html/member/at_acount_5.html','전자우편주소수집거부','width=865px,height=680px,scrollbars=yes');void(0);" class="btn_f" title="전자우편주소수집거부 (새 창으로 이동)">전자우편주소수집거부</a>
						<span class="f_bar">|</span>
						<a href="javascript:window.open('http://www.at.or.kr/html/member/at_acount_6.html','뷰어다운로드','width=865px,height=680px,scrollbars=yes');void(0);" class="btn_f" title="뷰어다운로드 (새 창으로 이동)">뷰어다운로드</a>
						<span class="f_bar">|</span>
						<a href="${pageContext.request.contextPath}/front/content/view.do?contentId=21&menuId=32" class="btn_f">찾아오시는길</a>
						<span class="f_bar">|</span>
						<a href="${pageContext.request.contextPath}/front/siteMap.do" class="btn_f">사이트맵</a>
					</div>
					<div class="fr">
						<div class="foot_sel">
							<select id="footerSel" title="유관기관 사이트">
								<option value="">유관기관</option>
								<option value="http://www.mafra.go.kr/">농림축산식품부</option>
								<option value="http://www.naqs.go.kr/">국립농산물품질관리원</option>
								<option value="http://www.naas.go.kr/">농촌진흥청 국립농업과학원</option>
								<option value="http://www.qia.go.kr/">농림축산검역본부</option>
								<option value="http://www.seed.go.kr/">국립종자원</option>
								<option value="http://www.kma.go.kr/">기상청</option>
								<option value="http://www.ipet.re.kr/">농림수산식품기술기획평가원</option>
								<option value="http://www.epis.or.kr/">농림수산식품교육문화정보원</option>
								<option value="http://www.ati.go.kr/">농식품공무원교육원</option>
								<option value="http://www.rda.go.kr/">농촌진흥청</option>
								<option value="http://www.nonghyup.com/">농협</option>
								<option value="http://www.forest.go.kr">산림청</option>
								<option value="http://www.suhyup.co.kr/">수협</option>
								<option value="http://www.ekr.or.kr">한국농어촌공사</option>
								<option value="http://www.af.ac.kr/">한국농수산대학</option>
								<option value="http://www.krei.re.kr/">한국농촌경제연구원</option>
								<option value="http://www.kfri.re.kr/">한국식품연구원</option>
								<option value="http://www.sonosa.or.kr/">남북교류협력지원협회</option>
							</select>
							<button id="footerSelBtn" type="submit" title="새 창 열림" onclick="fn_goSite();">이동</button>
						</div>
					</div>
				</div>
			</div>

			<div class="f_bottom">
				<div class="fb_in">
					<div class="fl">
						<p class="f_tit_01"><span class="color_p">주소</span>[06774]서울특별시 서초구 강남대로 27 화훼공판장</p>
						<p class="f_tit_01"><span class="color_p">전화</span>02-579-8100</p>
						<p class="f_tit_01"><span class="color_p">팩스</span>02-578-8671  </p>
						<br/>
						<p class="f_tit_02"><span>Copyright(c) 2020 BY Korea Agro-Fisheries & Food Trade Coporation</span> <span>  ALL RIGHTS RESERVED.</span></p>
					</div>
					
					
					<div class="fr">
							<a title="새창" class="web-mark" href="http://www.wa.or.kr/board/list.asp?BoardID=0006" target="_blank"><img alt="(사)한국장애인단체총연합회 한국웹접근성인증평가원 웹 접근성 우수사이트 인증마크(WA인증마크)" src="${pageContext.request.contextPath}/img/web_20220422.png" width="118" height="83.78"/></a>
					</div>
				</div>
			</div>
		</div>
		
		<script type="text/javascript">
		function fn_goSite(){
			var siteUrl = $("#footerSel").prop("value");
			
			if(siteUrl != ""){
				//window.location.href=siteUrl;
				window.open(siteUrl);
			}
		};
		
		</script>

