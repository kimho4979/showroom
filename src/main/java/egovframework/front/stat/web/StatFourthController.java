package egovframework.front.stat.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.front.login.service.FrontLoginService;
import egovframework.front.stat.service.StatFourthService;
import egovframework.rte.psl.dataaccess.util.EgovMap;


@Controller
public class StatFourthController {

	/** EgovPropertyService */
	@Resource(name = "frontLoginService")
	protected FrontLoginService frontLoginService;

	@Resource(name = "statFourthService")
	private StatFourthService StatFourthService;
	
	
	//상장물량조회(절화)
	@RequestMapping(value = "/front/stat/sangJangCntList.do")
	public String sangJangCntList(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap, ModelMap model)
			throws Exception {
	
		
		try {
			
		Boolean aucType01 = (Boolean) request.getSession().getAttribute("aucType01");
		Boolean aucType02 = (Boolean) request.getSession().getAttribute("aucType02");
		Boolean aucType03 = (Boolean) request.getSession().getAttribute("aucType03");
		Boolean aucType04 = (Boolean) request.getSession().getAttribute("aucType04");
		Boolean aucType05 = (Boolean) request.getSession().getAttribute("aucType05");
		// 01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체 / 05 하역노조(항운노조) / 07 경매사
		if ((aucType01 == null || !aucType01)&&(aucType02 == null || !aucType02)&&(aucType03 == null || !aucType03)&&(aucType04 == null || !aucType04)&&(aucType05 == null || !aucType05)) {
			return "redirect:/front/login.do";
		}	
			
		model.addAttribute("paramMap",paramMap);
	
		model.addAttribute("contentPath", "stat/sangJangCnt.jsp");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "/front/layout/loginLayout";
	}
	
	
	//상장물량조회(절화) 조회
	@RequestMapping(value = "/front/stat/sangJangCntList.json")
	public String sangJangCntListJson(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap, ModelMap model)
			throws Exception {
		
		Boolean aucType01 = (Boolean) request.getSession().getAttribute("aucType01");
		Boolean aucType02 = (Boolean) request.getSession().getAttribute("aucType02");
		Boolean aucType03 = (Boolean) request.getSession().getAttribute("aucType03");
		Boolean aucType04 = (Boolean) request.getSession().getAttribute("aucType04");
		Boolean aucType05 = (Boolean) request.getSession().getAttribute("aucType05");
		// 01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체 / 05 하역노조(항운노조) / 07 경매사
		if ((aucType01 == null || !aucType01)&&(aucType02 == null || !aucType02)&&(aucType03 == null || !aucType03)&&(aucType04 == null || !aucType04)&&(aucType05 == null || !aucType05)) {
			return null;
		}
	
		List<EgovMap> resultList = StatFourthService.sangJangCntList(paramMap);
		
		model.addAttribute("resultList",resultList);
		model.addAttribute("paramMap", paramMap);
		
		return "jsonView";
	}
	
	
	
}
