<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


	<c:if test="${paramMap.tyGroupCode eq 0}">
	<!-- 본관입주점포(2층) -->
	<img src="${pageContext.request.contextPath}/img/map/market_map_08.jpg" alt="8-본관입주점포(2층)" usemap="#map_mobile_02" class="mobile_map" width="998" height="400">
	<map id="map_mobile_02" name="map_mobile_02">
		<area data-tycode="12" shape="rect" alt="001" title="" coords="189,41,330,73" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="12" shape="rect" alt="017" title="" coords="359,41,416,73" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="12" shape="rect" alt="016" title="" coords="420,41,486,73" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="12" shape="rect" alt="015" title="" coords="449,77,486,107" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="12" shape="rect" alt="022" title="" coords="188,103,221,161" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="12" shape="rect" alt="021" title="" coords="226,103,258,161" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="12" shape="rect" alt="020" title="" coords="261,103,293,159" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="12" shape="rect" alt="002" title="" coords="298,103,328,161" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="12" shape="rect" alt="023" title="" coords="190,167,232,218" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="12" shape="rect" alt="004" title="" coords="238,167,280,218" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="12" shape="rect" alt="005" title="" coords="287,167,328,218" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="12" shape="rect" alt="018" title="" coords="360,105,386,147" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="12" shape="rect" alt="039" title="" coords="391,105,416,146" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="12" shape="rect" alt="019" title="" coords="360,152,416,182" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="12" shape="rect" alt="003" title="" coords="361,188,417,219" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="12" shape="rect" alt="014" title="" coords="449,113,486,145" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="12" shape="rect" alt="013" title="" coords="448,150,486,180" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="12" shape="rect" alt="012" title="" coords="448,185,485,215" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="12" shape="rect" alt="006" title="" coords="308,251,354,328" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="12" shape="rect" alt="007" title="" coords="360,251,417,298" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="12" shape="rect" alt="008" title="" coords="359,302,416,359" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="12" shape="rect" alt="009" title="" coords="448,221,506,253" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="12" shape="rect" alt="010" title="" coords="448,257,507,290" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="12" shape="rect" alt="026" title="" coords="511,222,582,289" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="12" shape="rect" alt="028" title="" coords="586,223,618,288" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="12" shape="rect" alt="029" title="" coords="623,221,653,288" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="12" shape="rect" alt="038" title="" coords="658,148,734,180" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="12" shape="rect" alt="034" title="" coords="659,185,732,215" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="12" shape="rect" alt="033" title="" coords="661,221,731,252" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="12" shape="rect" alt="031" title="" coords="658,257,694,289" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="12" shape="rect" alt="032" title="" coords="698,257,733,291" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="12" shape="rect" alt="011" title="" coords="420,325,496,357" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="12" shape="rect" alt="025" title="" coords="500,325,573,357" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="12" shape="rect" alt="027" title="" coords="579,325,651,358" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="12" shape="rect" alt="030" title="" coords="656,324,730,358" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="12" shape="rect" alt="035" title="" coords="734,323,810,357" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="12" shape="rect" alt="036" title="" coords="762,265,810,319" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="12" shape="rect" alt="037" title="" coords="762,205,808,261" href="#!" onclick="ViewSearch(this);" target="_self" />
	</map>
	</c:if>
	<c:if test="${paramMap.tyGroupCode eq 1}">
	<!-- 분화온실점포가동 -->
	<img src="${pageContext.request.contextPath}/img/map/market_map_04.jpg" alt="3-생화꽃도매시장점포(2층)" usemap="#map_mobile_02" class="mobile_map" width="998" height="400">
	<map id="map_mobile_02" name="map_mobile_02">
		<area data-tycode="21" shape="rect" alt="060" title="" coords="191,98,224,128" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="059" title="" coords="228,99,259,129" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="058" title="" coords="263,98,295,129" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="057" title="" coords="300,98,331,128" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="056" title="" coords="335,98,367,128" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="055" title="" coords="412,98,443,128" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="054" title="" coords="448,96,478,128" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="053" title="" coords="485,98,514,129" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="052" title="" coords="521,99,550,128" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="051" title="" coords="556,98,587,130" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="050" title="" coords="631,98,663,129" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="049" title="" coords="667,98,698,129" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="048" title="" coords="705,97,735,130" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="047" title="" coords="739,98,770,128" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="046" title="" coords="776,98,805,128" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="045" title="" coords="191,166,222,196" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="044" title="" coords="228,166,258,196" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="043" title="" coords="263,166,294,196" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="042" title="" coords="299,166,331,196" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="041" title="" coords="335,166,366,196" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="040" title="" coords="412,166,443,196" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="039" title="" coords="448,165,479,196" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="038" title="" coords="484,166,514,196" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="037" title="" coords="521,166,551,196" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="036" title="" coords="555,166,587,197" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="035" title="" coords="633,166,663,196" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="034" title="" coords="668,166,698,196" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="033" title="" coords="704,166,734,196" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="032" title="" coords="740,166,770,196" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="031" title="" coords="775,166,807,196" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="030" title="" coords="192,202,222,233" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="029" title="" coords="229,203,259,233" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="028" title="" coords="264,202,295,232" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="027" title="" coords="299,202,331,232" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="026" title="" coords="336,204,366,234" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="025" title="" coords="412,202,443,233" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="024" title="" coords="448,202,479,233" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="023" title="" coords="484,202,513,232" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="022" title="" coords="519,203,550,233" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="021" title="" coords="556,203,585,231" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="020" title="" coords="631,202,662,234" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="019" title="" coords="667,201,699,233" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="018" title="" coords="704,202,733,232" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="017" title="" coords="740,202,771,233" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="016" title="" coords="775,202,806,232" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="015" title="" coords="192,270,222,302" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="014" title="" coords="227,269,257,301" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="013" title="" coords="264,270,294,301" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="012" title="" coords="299,270,330,301" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="011" title="" coords="335,269,366,301" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="010" title="" coords="413,271,443,300" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="009" title="" coords="448,271,478,301" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="008" title="" coords="483,270,515,301" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="007" title="" coords="520,271,550,301" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="006" title="" coords="557,271,586,302" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="005" title="" coords="633,271,661,301" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="004" title="" coords="667,271,699,302" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="003" title="" coords="703,269,735,301" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="002" title="" coords="740,270,771,301" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="21" shape="rect" alt="001" title="" coords="775,270,807,301" href="#!" onclick="ViewSearch(this);" target="_self" />
	</map>
	</c:if>
	<c:if test="${paramMap.tyGroupCode eq 2}">
	<!-- 분화온실점포나동 -->
	<img src="${pageContext.request.contextPath}/img/map/market_map_05.jpg" alt="5-분화온실점포(나동)" usemap="#map_mobile_02" class="mobile_map" width="998" height="400">
	<map id="map_mobile_02" name="map_mobile_02">
		<area data-tycode="22" shape="rect" alt="111" title="" coords="191,97,224,129" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="110" title="" coords="228,97,259,129" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="109" title="" coords="263,97,295,129" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="108" title="" coords="299,98,331,129" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="107" title="" coords="336,98,367,129" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="106" title="" coords="413,97,443,131" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="105" title="" coords="449,98,479,129" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="104" title="" coords="483,98,515,129" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="103" title="" coords="521,97,551,129" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="102" title="" coords="556,97,589,130" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="101" title="" coords="631,97,663,130" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="100" title="" coords="667,97,699,130" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="099" title="" coords="704,97,735,130" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="098" title="" coords="739,97,771,130" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="097" title="" coords="774,97,808,129" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="114" title="" coords="192,165,223,197" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="113" title="" coords="229,164,259,196" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="112" title="" coords="265,165,295,197" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="096" title="" coords="301,165,330,197" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="095" title="" coords="335,165,367,197" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="094" title="" coords="411,165,443,197" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="093" title="" coords="447,165,479,197" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="092" title="" coords="484,166,515,196" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="091" title="" coords="519,166,551,197" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="090" title="" coords="555,165,587,197" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="089" title="" coords="633,165,664,197" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="088" title="" coords="667,165,699,197" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="087" title="" coords="703,165,734,197" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="086" title="" coords="740,166,771,197" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="085" title="" coords="775,166,807,197" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="117" title="" coords="189,201,223,233" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="116" title="" coords="226,203,259,234" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="115" title="" coords="263,202,294,232" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="084" title="" coords="299,201,331,234" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="083" title="" coords="334,201,366,233" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="082" title="" coords="411,201,443,233" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="081" title="" coords="448,203,478,233" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="080" title="" coords="483,203,515,234" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="079" title="" coords="519,202,550,233" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="078" title="" coords="555,201,587,233" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="077" title="" coords="631,202,662,233" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="076" title="" coords="665,201,698,233" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="075" title="" coords="703,202,734,233" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="074" title="" coords="740,201,771,234" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="073" title="" coords="775,202,807,233" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="120" title="" coords="192,270,223,301" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="119" title="" coords="228,270,259,301" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="118" title="" coords="263,269,295,301" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="072" title="" coords="300,271,331,301" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="071" title="" coords="335,270,367,301" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="070" title="" coords="412,270,443,301" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="069" title="" coords="447,269,479,301" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="068" title="" coords="483,269,515,301" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="067" title="" coords="519,269,551,302" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="066" title="" coords="556,269,587,302" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="065" title="" coords="633,270,663,301" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="064" title="" coords="668,271,699,301" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="063" title="" coords="704,271,735,301" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="062" title="" coords="739,270,771,301" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="22" shape="rect" alt="061" title="" coords="775,270,807,302" href="#!" onclick="ViewSearch(this);" target="_self" />
	</map>
	</c:if>
	<c:if test="${paramMap.tyGroupCode eq 3}">	
	<!-- 2-생화꽃도매시장점포(1층)(S) -->
	<img src="${pageContext.request.contextPath}/img/map/market_map_02.jpg" alt="2-생화꽃도매시장점포(1층)" usemap="#map_mobile_02" class="mobile_map" width="998" height="400">
	<map id="map_mobile_02" name="map_mobile_02">
		<area data-tycode="45" shape="rect" alt="302" title="" coords="90,90,118,124" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="107" title="" coords="176,54,211,89" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="108" title="" coords="213,54,247,89" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="119" title="" coords="250,54,282,89" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="120" title="" coords="285,55,319,89" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="131" title="" coords="323,55,354,88" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="132" title="" coords="359,55,390,88" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="153" title="" coords="502,56,534,88" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="154" title="" coords="540,56,570,88" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="155" title="" coords="574,56,605,87" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="166" title="" coords="611,56,641,88" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="167" title="" coords="647,56,676,88" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="178" title="" coords="683,57,712,87" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="179" title="" coords="719,56,748,88" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="190" title="" coords="754,56,784,88" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="191" title="" coords="789,56,821,88" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="197" title="" coords="826,56,857,88" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="105" title="" coords="142,120,172,151" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="109" title="" coords="188,120,219,150" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="118" title="" coords="225,120,254,151" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="121" title="" coords="272,119,303,151" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="130" title="" coords="307,119,338,152" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="133" title="" coords="356,119,387,151" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="142" title="" coords="392,119,422,152" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="143" title="" coords="439,120,469,151" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="152" title="" coords="532,119,564,151" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="156" title="" coords="579,120,608,151" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="165" title="" coords="616,120,644,150" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="168" title="" coords="660,120,691,152" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="177" title="" coords="697,120,727,151" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="180" title="" coords="744,120,774,151" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="189" title="" coords="779,120,810,150" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="192" title="" coords="826,119,855,152" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="104" title="" coords="142,156,173,187" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="110" title="" coords="188,157,219,186" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="117" title="" coords="224,157,255,187" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="122" title="" coords="272,157,303,186" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="129" title="" coords="308,158,339,187" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="134" title="" coords="357,158,386,187" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="141" title="" coords="393,158,422,186" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="144" title="" coords="437,157,467,187" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="151" title="" coords="533,156,564,187" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="157" title="" coords="578,158,609,188" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="164" title="" coords="615,158,645,188" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="169" title="" coords="661,156,691,188" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="176" title="" coords="697,157,727,188" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="181" title="" coords="744,157,774,187" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="188" title="" coords="779,156,809,188" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="193" title="" coords="825,157,856,187" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="103" title="" coords="141,211,173,243" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="111" title="" coords="187,212,218,243" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="116" title="" coords="223,212,255,244" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="123" title="" coords="272,211,303,243" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="128" title="" coords="308,211,339,243" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="135" title="" coords="356,212,386,244" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="140" title="" coords="391,211,422,242" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="145" title="" coords="437,213,468,242" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="150" title="" coords="533,211,563,243" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="158" title="" coords="579,211,609,243" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="163" title="" coords="615,211,645,243" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="170" title="" coords="661,211,691,243" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="175" title="" coords="697,212,728,243" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="182" title="" coords="743,211,774,244" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="187" title="" coords="780,211,810,243" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="194" title="" coords="826,211,857,243" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="115" title="" coords="142,248,172,278" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="112" title="" coords="189,248,218,278" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="115" title="" coords="225,250,255,279" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="124" title="" coords="273,248,303,279" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="127" title="" coords="308,248,339,279" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="136" title="" coords="357,248,387,279" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="139" title="" coords="392,248,423,278" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="146" title="" coords="438,248,468,279" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="149" title="" coords="533,248,563,279" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="159" title="" coords="579,248,609,279" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="162" title="" coords="614,248,645,279" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="171" title="" coords="662,249,691,279" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="174" title="" coords="696,248,728,279" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="183" title="" coords="744,248,773,279" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="186" title="" coords="779,249,811,279" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="195" title="" coords="826,248,856,279" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="101" title="" coords="142,311,173,342" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="113" title="" coords="178,311,209,342" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="114" title="" coords="213,311,245,343" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="125" title="" coords="251,311,281,342" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="126" title="" coords="285,310,315,343" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="137" title="" coords="322,311,352,343" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="138" title="" coords="358,311,388,342" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="147" title="" coords="392,311,424,341" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="148" title="" coords="575,310,605,344" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="160" title="" coords="612,310,641,344" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="161" title="" coords="648,311,676,344" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="172" title="" coords="683,310,713,343" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="173" title="" coords="719,310,749,343" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="184" title="" coords="754,310,784,343" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="185" title="" coords="791,310,821,343" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="196" title="" coords="826,310,856,344" href="#!" onclick="ViewSearch(this);" target="_self" />
	</map>
	<!-- 2-생화꽃도매시장점포(1층)(E) -->
	</c:if>
	<c:if test="${paramMap.tyGroupCode eq 4}">
	<!-- 생화꽃도매시장점포 2층 -->
	<img src="${pageContext.request.contextPath}/img/map/market_map_03.jpg" alt="3-생화꽃도매시장점포(2층)" usemap="#map_mobile_02" class="mobile_map" width="998" height="400">
	<map id="map_mobile_02" name="map_mobile_02">
		<area data-tycode="43" shape="rect" alt="215" title="" coords="143,55,235,89" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="43" shape="rect" alt="214" title="" coords="237,56,330,88" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="42" shape="rect" alt="213" title="" coords="335,55,429,89" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="42" shape="rect" alt="212" title="" coords="501,55,617,89" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="43" shape="rect" alt="211" title="" coords="621,56,737,88" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="43" shape="rect" alt="210" title="" coords="741,55,859,89" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="43" shape="rect" alt="216" title="" coords="141,120,339,187" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="43" shape="rect" alt="231" title="" coords="353,121,394,186" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="236" title="" coords="401,121,445,186" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="244" title="" coords="541,121,587,186" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="247" title="" coords="599,120,643,186" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="252" title="" coords="647,120,693,187" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="43" shape="rect" alt="209" title="" coords="707,121,800,187" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="43" shape="rect" alt="209" title="" coords="813,121,856,187" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="43" shape="rect" alt="201" title="" coords="143,213,233,278" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="43" shape="rect" alt="204" title="" coords="246,213,288,277" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="43" shape="rect" alt="227" title="" coords="294,212,338,278" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="43" shape="rect" alt="305" title="" coords="353,213,395,279" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="235" title="" coords="401,212,443,279" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="240" title="" coords="459,213,502,279" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="243" title="" coords="543,212,587,278" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="41" shape="rect" alt="248" title="" coords="601,212,644,278" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="43" shape="rect" alt="302" title="" coords="649,210,693,279" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="43" shape="rect" alt="259" title="" coords="707,211,749,278" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="43" shape="rect" alt="208" title="" coords="755,212,800,279" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="43" shape="rect" alt="208" title="" coords="813,211,857,279" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="43" shape="rect" alt="201" title="" coords="141,311,227,342" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="43" shape="rect" alt="202" title="" coords="231,311,317,343" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="43" shape="rect" alt="203" title="" coords="321,311,407,342" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="42" shape="rect" alt="204" title="" coords="411,311,497,343" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="42" shape="rect" alt="205" title="" coords="502,311,587,343" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="43" shape="rect" alt="206" title="" coords="591,311,677,343" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="43" shape="rect" alt="207" title="" coords="683,311,767,343" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="43" shape="rect" alt="208" title="" coords="772,311,857,343" href="#!" onclick="ViewSearch(this);"  target="_self" />
		
		<area data-tycode="43" shape="rect" alt="303" title="" coords="770,342,812,374" href="#!" onclick="ViewSearch(this);"  target="_self" />
		<area data-tycode="43" shape="rect" alt="304" title="" coords="814,342,857,374" href="#!" onclick="ViewSearch(this);"  target="_self" />
		<area data-tycode="45" shape="rect" alt="300" title="" coords="862,311,907,374" href="#!" onclick="ViewSearch(this);"  target="_self" />
		
	</map>
	</c:if>
	<c:if test="${paramMap.tyGroupCode eq 5}">
	<!-- 기타점포-->
	<img src="${pageContext.request.contextPath}/img/map/market_map_01.jpg" alt="기타점포" usemap="#map_mobile_02" class="mobile_map" width="998" height="500">
	<map id="map_mobile_02" name="map_mobile_02">
		<area shape="rect" alt="생화도매시장(절화소재)" title="" coords="334,50,509,133" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=3&menuId=13" target="_self" />
		<area shape="rect" alt="나무시장" title="" coords="566,67,695,113" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=5&menuId=18" target="_self" />
		<area shape="rect" alt="자재매장" title="" coords="737,79,855,142" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=5&menuId=18" target="_self" />
		<area shape="rect" alt="자재매장" title="" coords="125,144,247,203" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=5&menuId=18" target="_self" />
		<area shape="rect" alt="분화매장나동" title="" coords="270,152,550,264" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=2&menuId=12" target="_self" />
		<area shape="rect" alt="지하꽃시장" title="" coords="571,147,677,288" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=6&menuId=15" target="_self" />
		<area shape="rect" alt="입주업체사무동" title="" coords="714,163,879,254" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=0&menuId=17" target="_self" />
		<area shape="rect" alt="초화매장" title="" coords="131,227,241,315" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=5&menuId=18" target="_self" />
		<area shape="rect" alt="나무시장" title="" coords="162,352,286,440" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=5&menuId=18" target="_self" />
		<area shape="rect" alt="분화매장가동" title="" coords="361,302,640,417" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=1&menuId=11" target="_self" />
		<area shape="rect" alt="경매장체험관" title="" coords="712,260,881,423" href="${pageContext.request.contextPath}/front/market/list.do?tyGroupCode=7&menuId=16" target="_self" />
	</map>
	</c:if>
	<c:if test="${paramMap.tyGroupCode eq 6}">
	<!-- 화환점포-->
	<img src="${pageContext.request.contextPath}/img/map/market_map_06.jpg" alt="6-화환점포" usemap="#map_mobile_02" class="mobile_map" width="998" height="400">
	<map id="map_mobile_02" name="map_mobile_02">
		<area data-tycode="61" shape="rect" alt="059" title="" coords="186,36,318,62" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="077" title="" coords="105,61,147,79" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="076" title="" coords="106,83,147,101" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="074" title="" coords="106,107,147,125" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="070" title="" coords="106,130,148,149" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="071" title="" coords="106,152,147,171" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="069" title="" coords="105,176,147,193" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="068" title="" coords="105,197,148,216" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="067" title="" coords="104,221,148,239" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="072" title="" coords="105,245,148,263" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="073" title="" coords="105,267,148,285" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="066" title="" coords="106,289,146,309" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="060" title="" coords="185,89,226,115" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="058" title="" coords="231,88,272,115" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="047" title="" coords="276,89,316,115" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="061" title="" coords="186,121,226,146" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="057" title="" coords="232,119,273,147" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="048" title="" coords="276,121,317,146" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="062" title="" coords="186,171,248,195" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="049" title="" coords="254,171,319,195" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="063" title="" coords="186,202,226,227" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="055" title="" coords="232,203,272,227" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="050" title="" coords="277,201,317,227" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="064" title="" coords="185,252,226,277" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="054" title="" coords="231,252,271,277" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="051" title="" coords="277,252,318,277" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="065" title="" coords="185,283,226,309" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="053" title="" coords="231,283,272,309" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="052" title="" coords="277,285,318,309" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="046" title="" coords="355,87,396,115" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="035" title="" coords="400,89,441,115" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="045" title="" coords="354,120,395,144" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="036" title="" coords="400,121,442,146" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="044" title="" coords="354,170,396,196" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="037" title="" coords="401,170,442,195" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="043" title="" coords="354,200,395,227" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="038" title="" coords="401,199,441,227" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="042" title="" coords="354,252,395,278" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="039" title="" coords="400,251,442,278" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="041" title="" coords="354,283,395,309" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="040" title="" coords="399,283,442,309" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="034" title="" coords="477,88,519,114" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="023" title="" coords="525,87,567,115" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="033" title="" coords="478,120,519,147" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="024" title="" coords="525,119,566,146" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="032" title="" coords="478,171,519,197" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="025" title="" coords="525,171,565,195" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="031" title="" coords="479,202,518,227" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="026" title="" coords="526,202,565,228" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="030" title="" coords="477,252,519,278" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="027" title="" coords="525,251,565,277" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="029" title="" coords="479,283,519,309" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="028" title="" coords="524,283,565,309" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="022" title="" coords="601,88,643,115" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="021" title="" coords="603,121,644,146" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="002" title="" coords="773,88,813,105" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="003" title="" coords="772,110,812,125" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="004" title="" coords="772,131,813,145" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="020" title="" coords="601,170,644,196" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="013" title="" coords="649,169,689,196" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="019" title="" coords="602,201,643,226" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="014" title="" coords="648,201,688,226" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="018" title="" coords="602,251,643,278" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="015" title="" coords="647,251,690,277" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="017" title="" coords="601,283,643,309" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="016" title="" coords="648,283,689,308" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="012" title="" coords="725,170,767,197" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="005" title="" coords="772,170,812,196" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="011" title="" coords="725,201,766,227" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="006" title="" coords="772,201,813,227" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="010" title="" coords="725,251,768,278" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="007" title="" coords="771,250,813,277" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="009" title="" coords="726,283,767,309" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="008" title="" coords="772,283,813,309" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="078" title="" coords="185,334,243,362" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="079" title="" coords="249,335,306,363" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="080" title="" coords="312,335,369,363" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="081" title="" coords="375,335,432,363" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="082" title="" coords="438,334,496,363" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="083" title="" coords="502,335,559,362" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="084" title="" coords="564,335,621,361" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="085" title="" coords="626,336,684,361" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="086" title="" coords="690,335,749,363" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="087" title="" coords="752,335,813,362" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="001" title="" coords="851,73,893,99" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="094" title="" coords="851,106,893,132" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="093" title="" coords="850,138,893,165" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="092" title="" coords="853,170,893,199" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="091" title="" coords="849,205,893,231" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="090" title="" coords="851,237,893,263" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="089" title="" coords="851,269,893,296" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="61" shape="rect" alt="088" title="" coords="851,301,893,329" href="#!" onclick="ViewSearch(this);" target="_self" />
	</map>
	</c:if>
	<c:if test="${paramMap.tyGroupCode eq 7}">
	<!-- 본관입주점포(지하1층)-->
	<img src="${pageContext.request.contextPath}/img/map/market_map_07.jpg" alt="7-본관입주점포(지하1층)" usemap="#map_mobile_02" class="mobile_map" width="998" height="400">
	<map id="map_mobile_02" name="map_mobile_02">
		<area data-tycode="B1" shape="rect" alt="021" title="" coords="264,41,327,68" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="B1" shape="rect" alt="020" title="" coords="264,73,328,98" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="B1" shape="rect" alt="019" title="" coords="297,103,327,129" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="B1" shape="rect" alt="018" title="" coords="358,41,398,67" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="B1" shape="rect" alt="017" title="" coords="357,74,399,99" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="B1" shape="rect" alt="016" title="" coords="357,103,398,129" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="B1" shape="rect" alt="015" title="" coords="403,81,444,129" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="B1" shape="rect" alt="014" title="" coords="449,82,490,127" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="B1" shape="rect" alt="013" title="" coords="495,43,541,128" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="B1" shape="rect" alt="012" title="" coords="546,43,592,129" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="B1" shape="rect" alt="023" title="" coords="596,42,644,76" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="B1" shape="rect" alt="011" title="" coords="596,82,643,129" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="B1" shape="rect" alt="010" title="" coords="648,42,694,75" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="B1" shape="rect" alt="024" title="" coords="658,83,694,129" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="B1" shape="rect" alt="026" title="" coords="698,81,736,129" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="B1" shape="rect" alt="009" title="" coords="750,42,806,129" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="B1" shape="rect" alt="001" title="" coords="357,176,438,219" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="B1" shape="rect" alt="025" title="" coords="398,256,439,282" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="B1" shape="rect" alt="002" title="" coords="496,175,555,263" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="B1" shape="rect" alt="004" title="" coords="570,175,622,217" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="B1" shape="rect" alt="005" title="" coords="627,174,709,217" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="B1" shape="rect" alt="008" title="" coords="714,175,765,216" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="B1" shape="rect" alt="003" title="" coords="560,221,635,262" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="B1" shape="rect" alt="006" title="" coords="640,221,698,262" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="B1" shape="rect" alt="007" title="" coords="702,231,766,263" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="B1" shape="rect" alt="022" title="" coords="286,329,338,357" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="B1" shape="rect" alt="023" title="" coords="342,329,394,357" href="#!" onclick="ViewSearch(this);" target="_self" />
		<area data-tycode="B1" shape="rect" alt="024" title="" coords="398,329,450,357" href="#!" onclick="ViewSearch(this);" target="_self" />
	</map>
	<!-- 7-본관입주점포(지하1층)(E) -->
	</c:if>
	
	
	<!-- 배치도 팝업 스크립트(S) -->
	<script type="text/javascript">
	
		var selRoCode = "";	
	
		function ViewSearch(obj){
			var tyCode = $(obj).attr("data-tycode");
			var roCode = $(obj).attr("alt");
			var tyGroupCode = $("#tyGroupCode").val();
			$.ajax({
				data:{
					tyCode : tyCode,
					roCode : roCode,
					marketType : tyCode,
					marketNo : roCode
			        },
		        type : "POST",
		        url : "${pageContext.request.contextPath}/front/market/areaMapInfo.json",
		        success : function(data){
		             console.log(data);
					 var position = ""; 
		             
		             if(tyGroupCode == 0){
		            	 position = "본관입주점포(2층)|"+roCode+"호"; 
		             }else if(tyGroupCode == 1){
		            	 position = "분화온실점포(가동)|"+roCode+"호"; 
		             }else if(tyGroupCode == 2){
		            	 position = "분화온실점포(나동)|"+roCode+"호"; 
		             }else if(tyGroupCode == 3){
		            	 position = "생화꽃도매시장점포(1층)|"+roCode+"호"; 
		             }else if(tyGroupCode == 4){
		            	 position = "생화꽃도매시장점포(2층)|"+roCode+"호"; 
		             }else if(tyGroupCode == 5){
		            	 position = "기타점포|"+roCode+"호"; 
		             }else if(tyGroupCode == 6){
		            	 position = "화환점포|"+roCode+"호"; 
		             }else if(tyGroupCode == 7){
		            	 position = "본관입주점포(지하1층)|"+roCode+"호"; 
		             }
		             // 09:00 ~ 18:00 공휴일 휴무 
		             if(data.atMarketInfo == null){
		            	 alert("공실입니다.");
		            	 return false;
		             }
		             
		             var marketName = data.atMarketInfo.rcCompName;
		             var owner = data.atMarketInfo.rcName;
		             var tel = data.atMarketInfo.rcTel;
		             
		             if(tel == null || tel == ""){
		            	 tel = "미등록";
		             }
		             
		             var hashText = "해시태그 미등록";
		             var content = "컨텐츠 미등록";
		             var saleTime = "미등록";
		             var cloDay = "미등록";
		             var deliText = "미등록";
		             var filePath = contextPath+"/img/noimage.png";
		             
		             if(data.atMarketInfo != null){
		            	 hashText = data.atMarketInfo.rcHashTag;
			             content = data.atMarketInfo.rcIntro;
			             //saleTime = data.oraMarketInfo.saleTime;
			             //cloDay = data.oraMarketInfo.cloDay;
			             //deliText = data.oraMarketInfo.deliText;
			             
			             if(hashText == null || hashText == ""){
			            	 hashText = "해시태그 미등록";
			             }
			             
			             if(content == null  || content == ""){
			            	 content = "컨텐츠 미등록";
			             }
			             
			             
			             if(data.atMarketInfo.rcImg2 == null || $.trim(data.atMarketInfo.rcImg2) == ''){
			            	 if(data.atMarketInfo.rcImg1 == null || $.trim(data.atMarketInfo.rcImg1) == ''){
			            		 filePath = contextPath+"/img/noimage2.png";	 
			            	 }else{
			            		 filePath = data.atMarketInfo.rcImg1;	 	 
			            	 }
			             }else{
			            	 filePath = data.atMarketInfo.rcImg2;	 
			             }
		             }
		             
		             
		             $("#marketImg").attr("src",filePath);
		             $("#marketImg").attr("alt",marketName);
		             $("#marketName").html(marketName);
		             $("#marketHashText").html(hashText);
		             $("#marketContent").html(content);
		             $("#marketOwner").html(owner);
		             $("#marketTel").html(tel);
		             $("#marketPosition").html(position);
		             //$("#marketDeliText").html(deliText);
		             
		             if(tyGroupCode == 3 || tyGroupCode == 4){
		            	 //생화도매시장
		            	 saleTime = "0시 ~ 13시(공휴일 낮 12시)";
		            	 cloDay = "일요일 휴무";
		             }else if(tyGroupCode == 1 || tyGroupCode == 2){
		            	 //분화온실
		            	 saleTime = "7시 ~ 19시";
		            	 cloDay = "가동 : 일요일( 1,3,5주차 및 7,8월)<br> 나동 : 일요일(2,4,5주차 및 7,8월)";
		             }else if(tyGroupCode == 6){
		            	 //화환점포
		            	 saleTime = "7시 ~ 21시";
		            	 cloDay = "일요일 격주 휴무 및 영업";
		             }else if(tyGroupCode == 5){
		            	 //기타점포
		            	 saleTime = "7시 ~ 21시";
		            	 cloDay = "연중무휴";
		             }else if(tyGroupCode == 0 || tyGroupCode == 7){
		            	 //본관입주점포
		            	 saleTime = "9시 ~ 18시";
		            	 cloDay = "공휴일 휴무";
		             }
		             
		             $("#marketSaleTime").html(saleTime);
		             $("#marketCloDay").html(cloDay);
		             
		             
		             $("#marketCall").attr("href","tel:"+tel);
		             $("#marketAdd").unbind('click');
		             $("#marketShare").unbind('click');
		             $("#marketAdd").click(function(e){
		            	 var trb = "https://flower.at.or.kr/yfmc/front/market/view.do?tyCode="+data.atMarketInfo.tyCode+"&roCode="+data.atMarketInfo.roCode+"&menuId=11&tyGroupCode="+tyGroupCode;
		     			var IE=(document.all)?true:false;
		     			if (IE) {
		     			if(confirm("이 글의 주소를 클립보드에 복사하시겠습니까?"))
		     				window.clipboardData.setData("Text", trb);
		     			} else {
		     				temp = prompt("이 글의 주소입니다. Ctrl+C를 눌러 클립보드로 복사하세요", trb);
		     			}
		    		});
		             
		             $("#marketShare").click(function(e){
		     		    if (navigator.share === undefined){
		    		        alert('지원하지 않는 기기입니다.');
		    		    }else{
		    		    	var marketSeq
		    		    	window.navigator.share({
		    					  title: '양재동 화훼공판장 점포소개', // 공유될 제목
		    					  text: $("#marketName").html(), // 공유될 설명
		    					  url: "https://flower.at.or.kr/yfmc/front/market/view.do?tyCode="+data.atMarketInfo.tyCode+"&roCode="+data.atMarketInfo.roCode+"&menuId=11&tyGroupCode="+tyGroupCode
		    				});
		    		    }
		    		});
		             
		            selRoCode = roCode;
		             
		            layer_popup("#layer01");
		 			$('body').css('overflow','hidden');
		 			$('.dim-layer').css('position','fixed');
		        }
		    });
		
			// "/front/marketrent/info.fo?marketNo="+marketNo;
			
			
			
			
		}
		
			function layer_popup(el){
  
				var $el = $(el);        //레이어의 id를 $el 변수에 저장
				var isDim = $el.prev().hasClass('dimBg');   //dimmed 레이어를 감지하기 위한 boolean 변수

				isDim ? $('.dim-layer').fadeIn() : $el.fadeIn();

				var $elWidth = $( window ).width(),
					$elHeight = $( window ).height(),
					docWidth = $(window).width(),
					docHeight = $(window).height();

				var popboxW = $el.find(".popbox").width()/2; 
				var popboxH = $el.find(".popbox").height()/2; 
				
				
				$el.find(".popbox").css({
					marginTop: $elHeight /2 - popboxH,
					marginLeft: $elWidth/2 - popboxW
				})
				
				console.log($elWidth/2 - popboxW);
				
				$(".dim-layer").attr("tabindex", 0).focus();
				
				// 화면의 중앙에 레이어를 띄운다.
				/*if ($elHeight < docHeight || $elWidth < docWidth) {
					$el.css({
					marginTop: -$elHeight /2,
					marginLeft: -$elWidth/2
					})
				} else {
					$el.css({top: 0, left: 0});
				}*/

				$el.find('a.btn-layerClose').click(function(){
					isDim ? $('.dim-layer').fadeOut() : $el.fadeOut(); // 닫기 버튼을 클릭하면 레이어가 닫힌다.
					$('body').css('overflow','auto');
					$('body').css('position','relative');
					$("#map_mobile_02 area[alt=" + selRoCode + "]").focus();
					return false;
				});

				$('.layer .dimBg').click(function(){
					$('.dim-layer').fadeOut();
					return false;
				});

			}

			
			
			    /*var $this;

			    $("#map_mobile_02 area").click(function(){
			     
			        $(".dim-layer").attr("tabindex", 0).show().focus();
			     
			        $this = $(this);
			     
			    $(".btn-layerClose").click(function(){
			     
			        $(".dim-layer").css("display", "none");
			     
			        // $(".blackBg").fadeOut(0);
			     
			        $this.find("a").focus();
			    });
			});*/
	</script>
	<!-- 배치도 팝업 스크립트(E) -->
	