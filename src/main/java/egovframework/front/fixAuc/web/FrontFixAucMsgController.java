package egovframework.front.fixAuc.web;

import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import egovframework.front.fixAuc.service.FrontFixAucMsgService;
import egovframework.rte.psl.dataaccess.util.EgovMap;


@Controller
public class FrontFixAucMsgController {
	private static final Logger logger = LoggerFactory.getLogger(FrontFixAucMsgController.class);

	@Resource(name = "frontFixAucMsgService")
	private FrontFixAucMsgService frontFixAucMsgService;
	
	
	
	  @RequestMapping(value = "/front/fixAucMsg/fixSmsProc.json")
	  public String fixSmsProc(HttpServletRequest request
			       , HttpServletResponse response
			       , @RequestParam Map<String, Object> paramMap
			       , @RequestParam(value="banArray[]", required=false) String banArray
			       , @RequestParam(value="applyArray[]", required=false) String applyArray
			       , @RequestParam(value="bidStrArray[]", required=false) String bidStrArray
			       , @RequestParam(value="dealCompArray[]", required=false) String dealCompArray
			       , @RequestParam(value="bunChk", required=false) String bunChk
			       , @RequestParam(value="codeType", required=false) String codeType
			       , ModelMap model ) throws Exception{
	    
		try {
			
			EgovMap loginVO = null;
			
			if(bunChk.equals("N")) {
				loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			}else if(bunChk.equals("Y")) {
				loginVO = (EgovMap) request.getSession().getAttribute("yFloLoginVO");
			}else if(bunChk.equals("C")) {
				loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			}
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"07".equals(aucType)){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			paramMap.put("banArray", banArray);
			paramMap.put("applyArray", applyArray);
			paramMap.put("bidStrArray", bidStrArray);
			paramMap.put("dealCompArray", dealCompArray);
			paramMap.put("bunChk", bunChk);
			
			
			
			if(codeType.equals("1")) {
				
				//출하자
				String vMsg = frontFixAucMsgService.fixSmsProcChul(paramMap);
				
			}else {
				
				//중도매인
				String vMsg = frontFixAucMsgService.fixSmsProcJ(paramMap);
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return "jsonView";
	  }
	
	  
	  @RequestMapping(value = "/front/fixAucMsg/reqSmsProc.json")
	  public String reqSmsProc(HttpServletRequest request
			       , HttpServletResponse response
			       , @RequestParam Map<String, Object> paramMap
			       , @RequestParam(value="banArray[]", required=false) String banArray
			       , @RequestParam(value="applyArray[]", required=false) String applyArray
			       , @RequestParam(value="dealCompArray[]", required=false) String dealCompArray
			       , @RequestParam(value="bunChk", required=false) String bunChk
			       , @RequestParam(value="codeType", required=false) String codeType
			       , ModelMap model ) throws Exception{
	    
		try {
			
			EgovMap loginVO = null;
			
			if(bunChk.equals("N")) {
				loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			}else if(bunChk.equals("Y")) {
				loginVO = (EgovMap) request.getSession().getAttribute("yFloLoginVO");
			}else if(bunChk.equals("C")) {
				loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			}
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"07".equals(aucType)){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			paramMap.put("banArray", banArray);
			paramMap.put("applyArray", applyArray);
			paramMap.put("dealCompArray", dealCompArray);
			paramMap.put("bunChk", bunChk);
			
			
			
			if(codeType.equals("1")) {
				
				//출하자
				String vMsg = frontFixAucMsgService.reqSmsProcChul(paramMap);
				
			}else {
				
				//중도매인
				String vMsg = frontFixAucMsgService.reqSmsProcJ(paramMap);
				
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return "jsonView";
	  }
	  
	  
	  
	  @RequestMapping(value = "/front/fixAucMsg/fixSmallSmsProc.json")
	  public String fixSmallSmsProc(HttpServletRequest request
			       , HttpServletResponse response
			       , @RequestParam Map<String, Object> paramMap
			       , @RequestParam(value="bidStrArray[]", required=false) String bidStrArray
			       , @RequestParam(value="bunChk", required=false) String bunChk
			       , @RequestParam(value="codeType", required=false) String codeType
			       , ModelMap model ) throws Exception{
	    
		try {
			
			EgovMap loginVO = null;
			
			if(bunChk.equals("N")) {
				loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			}else if(bunChk.equals("Y")) {
				loginVO = (EgovMap) request.getSession().getAttribute("yFloLoginVO");
			}else if(bunChk.equals("C")) {
				loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			}
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"07".equals(aucType)){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			paramMap.put("bidStrArray", bidStrArray);
			paramMap.put("bunChk", bunChk);
			
			
			
			if(codeType.equals("2")) {
				
				//중도매인
				String vMsg = frontFixAucMsgService.fixSmallSmsProcJ(paramMap);
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return "jsonView";
	  }
	  
}
