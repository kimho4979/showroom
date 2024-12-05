package egovframework.front.fixAuc.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.cms.board.vo.BoardArticleVO;
import egovframework.cms.board.vo.BoardInfoVO;
import egovframework.front.content.service.FrontContentService;
import egovframework.front.content.service.FrontContentVO;
import egovframework.front.fixCAuc.service.FrontFixCAucService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Controller
public class FrontFixAucController {
	private static final Logger logger = LoggerFactory.getLogger(FrontFixAucController.class);

	@Resource(name = "frontFixCAucService")
	private FrontFixCAucService frontFixCAucService;
	
	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	
	@RequestMapping(value="/front/fixAuc/fixSellList.do")
	public String fixSellList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		if(!Boolean.valueOf(String.valueOf(request.getSession().getAttribute("aucType01")))){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 매뉴 권한이 없습니다. 통합로그인 후 사용하세요.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		EgovMap nFloLoginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		EgovMap yFloLoginVO = (EgovMap) request.getSession().getAttribute("yFloLoginVO");
		EgovMap cFloLoginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
				
		if(nFloLoginVO != null){
			return "redirect:/front/fixNAuc/fixSellList.do";
		}
		
		if(yFloLoginVO != null){
			return "redirect:/front/fixYAuc/fixSellList.do";
		}
		
		if(cFloLoginVO != null){
			return "redirect:/front/fixCAuc/fixSellList.do";
		}
		
		
		return "redirect:/front/fixNAuc/fixSellList.do";
	}
	
	
	@RequestMapping(value="/front/fixAuc/fixBuyList.do")
	public String fixBuyList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		
		if(!Boolean.valueOf(String.valueOf(request.getSession().getAttribute("aucType03")))){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 매뉴 권한이 없습니다. 통합로그인 후 사용하세요.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
	
		EgovMap nFloLoginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		EgovMap yFloLoginVO = (EgovMap) request.getSession().getAttribute("yFloLoginVO");
		EgovMap cFloLoginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
				
		if(nFloLoginVO != null){
			return "redirect:/front/fixNAuc/fixBuyList.do";
		}
		
		if(yFloLoginVO != null){
			return "redirect:/front/fixYAuc/fixBuyList.do";
		}
		
		if(cFloLoginVO != null){
			return "redirect:/front/fixCAuc/fixBuyList.do";
		}
		
		
		return "redirect:/front/fixNAuc/fixBuyList.do";
	}
	
	
	@RequestMapping(value="/front/fixAuc/reqList.do")
	public String reqlist(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		boolean aucType01 = Boolean.valueOf(String.valueOf(request.getSession().getAttribute("aucType01")));
		boolean aucType03 = Boolean.valueOf(String.valueOf(request.getSession().getAttribute("aucType03")));
		
		if(!aucType01 && !aucType03){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 매뉴 권한이 없습니다. 통합로그인 후 사용하세요.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
	
		EgovMap nFloLoginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		EgovMap yFloLoginVO = (EgovMap) request.getSession().getAttribute("yFloLoginVO");
		EgovMap cFloLoginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
		if(aucType01){
			if(nFloLoginVO != null){
				return "redirect:/front/fixNAuc/reqSellList.do";
			}
			
			if(yFloLoginVO != null){
				return "redirect:/front/fixYAuc/reqSellList.do";
			}
			
			if(cFloLoginVO != null){
				return "redirect:/front/fixCAuc/reqSellList.do";
			}
		}
		
		
		if(aucType03){
			if(nFloLoginVO != null){
				return "redirect:/front/fixNAuc/reqBuyList.do";
			}
			
			if(yFloLoginVO != null){
				return "redirect:/front/fixYAuc/reqBuyList.do";
			}
			
			if(cFloLoginVO != null){
				return "redirect:/front/fixCAuc/reqBuyList.do";
			}
		}
		
		
		
		
		return "redirect:/front/fixNAuc/reqList.do";
	}
	
	
	@RequestMapping(value="/front/fixAuc/fixAdmList.do")
	public String fixAdmList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		if(!Boolean.valueOf(String.valueOf(request.getSession().getAttribute("aucType07")))){
		
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 매뉴 권한이 없습니다. 통합로그인 후 사용하세요.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
	
		EgovMap nFloLoginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		EgovMap yFloLoginVO = (EgovMap) request.getSession().getAttribute("yFloLoginVO");
		EgovMap cFloLoginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
				
		if(nFloLoginVO != null){
			return "redirect:/front/fixNAuc/fixAdmList.do";
		}
		
		if(yFloLoginVO != null){
			return "redirect:/front/fixYAuc/fixAdmList.do";
		}
		
		if(cFloLoginVO != null){
			return "redirect:/front/fixCAuc/fixAdmList.do";
		}
		
		
		return "redirect:/front/fixNAuc/fixAdmList.do";
	}
	
	
	@RequestMapping(value="/front/fixAuc/reqAdmList.do")
	public String reqAdmList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		if(!Boolean.valueOf(String.valueOf(request.getSession().getAttribute("aucType07")))){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 매뉴 권한이 없습니다. 통합로그인 후 사용하세요.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
	
		EgovMap nFloLoginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		EgovMap yFloLoginVO = (EgovMap) request.getSession().getAttribute("yFloLoginVO");
		EgovMap cFloLoginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
				
		if(nFloLoginVO != null){
			return "redirect:/front/fixNAuc/reqAdmList.do";
		}
		
		if(yFloLoginVO != null){
			return "redirect:/front/fixYAuc/reqAdmList.do";
		}
		
		if(cFloLoginVO != null){
			return "redirect:/front/fixCAuc/reqAdmList.do";
		}
		
		
		return "redirect:/front/fixNAuc/reqAdmList.do";
	}
	
	
	
	@RequestMapping(value="/front/fixAuc/time.do")
	public String time(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		if(!Boolean.valueOf(String.valueOf(request.getSession().getAttribute("aucType07")))){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 매뉴 권한이 없습니다. 통합로그인 후 사용하세요.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
	
		EgovMap nFloLoginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		EgovMap yFloLoginVO = (EgovMap) request.getSession().getAttribute("yFloLoginVO");
		EgovMap cFloLoginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
				
		if(nFloLoginVO != null){
			return "redirect:/front/fixNAuc/time.do";
		}
		
		if(yFloLoginVO != null){
			return "redirect:/front/fixYAuc/time.do";
		}
		
		if(cFloLoginVO != null){
			return "redirect:/front/fixCAuc/time.do";
		}
		
		
		return "redirect:/front/fixNAuc/time.do";
	}
	
	
	@RequestMapping(value="/front/fixAuc/tranAdmList.do")
	public String tranAdmList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		if(!Boolean.valueOf(String.valueOf(request.getSession().getAttribute("aucType07")))){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 매뉴 권한이 없습니다. 통합로그인 후 사용하세요.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
	
		EgovMap nFloLoginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		EgovMap yFloLoginVO = (EgovMap) request.getSession().getAttribute("yFloLoginVO");
		EgovMap cFloLoginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
				
		if(nFloLoginVO != null){
			return "redirect:/front/fixNAuc/tranAdmList.do";
		}
		
		if(yFloLoginVO != null){
			return "redirect:/front/fixYAuc/tranAdmList.do";
		}
		
		if(cFloLoginVO != null){
			return "redirect:/front/fixCAuc/tranAdmList.do";
		}
		
		
		return "redirect:/front/fixNAuc/tranAdmList.do";
	}
	
	
	
	
}
