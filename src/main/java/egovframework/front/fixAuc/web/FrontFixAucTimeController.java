package egovframework.front.fixAuc.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import egovframework.front.fixAuc.service.FrontFixAucTimeService;
import egovframework.rte.psl.dataaccess.util.EgovMap;


@Controller
public class FrontFixAucTimeController {
	private static final Logger logger = LoggerFactory.getLogger(FrontFixAucTimeController.class);

	@Resource(name = "frontFixAucTimeService")
	private FrontFixAucTimeService frontFixAucTimeService;
	

	
	@RequestMapping(value = "/front/fixAucTime/time.do", method= RequestMethod.GET)
	public String time(HttpServletRequest request, HttpServletResponse response
			, @RequestParam(value="bunChk", required=false) String bunChk
			, ModelMap model) throws Exception {
		
		try {
			
			if(!Boolean.valueOf(String.valueOf(request.getSession().getAttribute("aucType07")))){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 매뉴 권한이 없습니다. 통합로그인 후 사용하세요.");
	            throw new ModelAndViewDefiningException(modelAndView);			}

			
			EgovMap nFloLoginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			EgovMap yFloLoginVO = (EgovMap) request.getSession().getAttribute("yFloLoginVO");
			EgovMap cFloLoginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
			String aucType = "";
			
			
			if(nFloLoginVO != null){
				aucType = String.valueOf(nFloLoginVO.get("aucType"));
			}else if(nFloLoginVO == null && yFloLoginVO != null){
				aucType = String.valueOf(yFloLoginVO.get("aucType"));
			}else if(nFloLoginVO == null && yFloLoginVO == null && cFloLoginVO != null){
				aucType = String.valueOf(cFloLoginVO.get("aucType"));
			}
			
			if(nFloLoginVO != null && (bunChk == null || bunChk.equals(""))){
				bunChk = "N";
			}else if(nFloLoginVO == null && yFloLoginVO != null && (bunChk == null || bunChk.equals(""))){
				bunChk = "Y";
			}else if(nFloLoginVO == null && yFloLoginVO == null && cFloLoginVO != null && (bunChk == null || bunChk.equals(""))){
				bunChk = "C";
			}
			
			
			if(!"07".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
				throw new ModelAndViewDefiningException(modelAndView);
			}
			
			
			List<EgovMap> fixTimeList = frontFixAucTimeService.getFixAucTime(bunChk);
			model.addAttribute("fixTimeList",fixTimeList);
			
			List<EgovMap> fixTimeBidList = frontFixAucTimeService.getFixAucTimeBid(bunChk);
			model.addAttribute("fixTimeBidList",fixTimeBidList);
			
			//if(bunChk.equals("N")) {
				//서면입찰
				List<EgovMap> fixAucTimeWriteBid = frontFixAucTimeService.getFixAucTimeWriteBid(bunChk);
				model.addAttribute("fixAucTimeWriteBid",fixAucTimeWriteBid);
			//}
			
			model.addAttribute("title", "시간관리");
			model.addAttribute("bunChk", bunChk);
			model.addAttribute("contentPath", "fixAuc/time.jsp");
			
		}catch (Exception e) {
			
			e.printStackTrace();
			
		}

		return "/front/layout/fixLayout";
		
	}
	
	@RequestMapping(value = "/front/fixAucTime/updateTime.json")
	  public String updateTime(HttpServletRequest request, @RequestParam Map<String,Object> paramMap
			  			, @RequestParam(value="bunChk", required=false, defaultValue="N") String bunChk
			  		    , ModelMap model) throws Exception{
	    
		
		try {
			
			EgovMap loginVO = null;
			String aucType = "";
			
			if(bunChk.equals("N")){
				loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
				aucType = String.valueOf(loginVO.get("aucType"));
			}else if(bunChk.equals("Y")){
				loginVO = (EgovMap) request.getSession().getAttribute("yFloLoginVO");
				aucType = String.valueOf(loginVO.get("aucType"));
			}else if(bunChk.equals("C")){
				loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
				aucType = String.valueOf(loginVO.get("aucType"));
			}

			if(!"07".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
				throw new ModelAndViewDefiningException(modelAndView);
			}
			
			List<Map<String, Object>> paramList = new ObjectMapper().readValue(paramMap.get("paramList").toString(), new TypeReference<List<Map<String, Object>>>(){});
			
			for(Map<String,Object> parameter : paramList) {
				Map<String,Object> setParam = new HashMap<String,Object>();
				setParam.put("bunChk", parameter.get("bunChk"));
				setParam.put("strTime", parameter.get("strTime"));
				setParam.put("endTime", parameter.get("endTime"));
				setParam.put("strFewDay", parameter.get("strFewDay"));
				setParam.put("endFewDay", parameter.get("endFewDay"));
				setParam.put("dayType", parameter.get("dayType"));
				setParam.put("timeType", parameter.get("timeType"));
				setParam.put("updateId", loginVO.get("loginId") );
				frontFixAucTimeService.updatetime(setParam);
				model.addAttribute("status","sucess");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("status","error");
			e.printStackTrace();
		}
	   
		
		return "jsonView";
	  }
	
	@RequestMapping(value = "/front/fixAucTime/currentTime.json")
	  public String getCurrentTime(HttpServletRequest request, @RequestParam Map<String,Object> paramMap
			  			, @RequestParam(value="bunChk", required=false, defaultValue="N") String bunChk
			  		    , ModelMap model) throws Exception{
	    
		
		try {
			
			EgovMap loginVO = null;
			String aucType = "";
			
			if(bunChk.equals("N")){
				loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
				aucType = String.valueOf(loginVO.get("aucType"));
			}else if(bunChk.equals("Y")){
				loginVO = (EgovMap) request.getSession().getAttribute("yFloLoginVO");
				aucType = String.valueOf(loginVO.get("aucType"));
			}else if(bunChk.equals("C")){
				loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
				aucType = String.valueOf(loginVO.get("aucType"));
			}

			if(!"07".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
				throw new ModelAndViewDefiningException(modelAndView);
			}
			
			
			List<EgovMap> currentTime = frontFixAucTimeService.getCurrentTime();
			
			model.addAttribute("currentTime", currentTime);
			
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("status","error");
			e.printStackTrace();
		}
	   
		
		return "jsonView";
	  }

	  
}
