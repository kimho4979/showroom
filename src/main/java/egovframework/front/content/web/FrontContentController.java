package egovframework.front.content.web;

import java.util.HashMap;
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
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.front.content.service.FrontContentService;
import egovframework.front.content.service.FrontContentVO;
import egovframework.rte.fdl.property.EgovPropertyService;

@Controller
public class FrontContentController {
	private static final Logger logger = LoggerFactory.getLogger(FrontContentController.class);

	@Resource(name = "frontContentService")
	private FrontContentService frontContentService;
	
	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	//2020.07.14 김재광, 김동국 차장님 수정(mapping url, 전달방식 수정)
	@RequestMapping(value="/front/content/view.do")
	public String contentView(@RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception {
		try {
			
		
		
		FrontContentVO result = frontContentService.getFrontContent(paramMap);
		
		model.addAttribute("result", result);
		
		model.addAttribute("contentPath", "frontContent/contentView.jsp");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		model.addAttribute("paramMap", paramMap);
		
		return "/front/layout/subLayout";
	}
	
	@RequestMapping(value="/front/content/showroomview.do")
	public String reservationView(
			HttpServletRequest request
		  , @RequestParam Map<String, Object> paramMap
		  , HttpServletResponse response
		  , ModelMap model) throws Exception {
		try{
			paramMap.put("menuId", "101"); //전시실 예약 메뉴아이디 필요
			model.addAttribute("paramMap", paramMap);
			model.addAttribute("contentPath", "/frontContent/reservationView.jsp");
			
		} catch (Exception e){
			e.printStackTrace();
		}
		return "/front/layout/subLayout";
	}
	
	@RequestMapping(value="/front/content/showroomreserv.do")
	public String reservationCalendar(
			@RequestParam Map<String, Object> paramMap
		  , HttpServletRequest request
		  , HttpServletResponse response
		  , ModelMap model) throws Exception {
		try{
			paramMap = new HashMap<>();
			paramMap.put("menuId", "100"); //전시실 예약 메뉴아이디 필요
			model.addAttribute("paramMap", paramMap);
			model.addAttribute("contentPath", "/frontContent/reservationCalendar.jsp");
			
		} catch (Exception e){
			e.printStackTrace();
		}
		return "/front/layout/subLayout";
	}
	
	@RequestMapping(value="/front/content/showroomestimate.do")
	public String reservation(
			HttpServletRequest request
		  , HttpServletResponse response
		  , ModelMap model) throws Exception {
		try{
			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("menuId", "100"); //전시실 예약 메뉴아이디 필요
			if(request.getQueryString() != null){
				String[] query = request.getQueryString().split("&");
				for(String q : query){
					String[] qArr = q.split("=");
					paramMap.put(qArr[0], qArr[1]);
				}
			}
			if(paramMap.get("type").toString().equals("1"))
				model.addAttribute("contentPath", "/frontContent/reservation.jsp");
			if(paramMap.get("type").toString().equals("2"))
				model.addAttribute("contentPath", "/frontContent/imttnEstimate.jsp");
			model.addAttribute("paramMap", paramMap);
		} catch (Exception e){
			e.printStackTrace();
		}
		return "/front/layout/subLayout";
	}
}
