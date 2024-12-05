package egovframework.front.menu.web;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.front.content.service.FrontContentService;
import egovframework.front.content.service.FrontContentVO;
import egovframework.front.menu.service.FrontMenuService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class FrontMenuController {
	

	@Resource(name = "frontMenuService")
	private FrontMenuService frontMenuService;
	
	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	@RequestMapping("/front/inc/menuList.do")
	  public String menu(@RequestParam Map<String, Object> paramMap
			    , HttpServletRequest request
			  	, ModelMap model)
	    throws Exception
	  {
		@SuppressWarnings("unchecked")
		List<EgovMap> menuList = (List<EgovMap>)request.getSession().getAttribute("menuList");
		if(menuList == null){
			menuList = frontMenuService.getMenuList();
			request.getSession().setAttribute("menuList", menuList);
		}
	    model.addAttribute("menuList", menuList);
	
	    return "/front/inc/menu";
	  }
	
	
	@RequestMapping("/front/inc/lnbMenuList.do")
	  public String lnb(@RequestParam Map<String, Object> paramMap, ModelMap model)
	    throws Exception
	  {
		
	    List<EgovMap> menuList = frontMenuService.getMenuList();
	
	    model.addAttribute("menuList", menuList);
		 
	    return "/front/inc/lnb";
	  }
	
	
	@RequestMapping({"/front/inc/menuInfo.do"})
	  public String sub(@RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception
	  {
		try {
		    EgovMap menuVO = frontMenuService.getMenuInfo(paramMap);

		    //String menuGb = String.valueOf(menuVO.get("menuGb"));

		    model.addAttribute("menuVO", menuVO);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	    return "/front/inc/subLocation";
	  }

	
	/*
	 @RequestMapping("/front/sub.do")
	  public String sub(@RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception
	  {
		 
		 try {
		
		 
	    EgovMap menuVO = frontMenuService.getMenuInfo(paramMap);

	    String menuGb = String.valueOf(menuVO.get("menuGb"));

	    model.addAttribute("menuVO", menuVO);

	    if ("BOARD".equals(menuGb))
	    {
	      String url = String.valueOf(menuVO.get("linkUrl")) + "?menuId=" + paramMap.get("menuId");
	      if ((menuVO.get("linkParam") != null) && (!"".equals(String.valueOf(menuVO.get("linkParam"))))) {
	        url = url + "&" + String.valueOf(menuVO.get("linkParam"));
	      }

	      return "redirect:" + String.valueOf(url);
	    }
	    if ("PROGRAM".equals(menuGb)) {
	      String url = String.valueOf(menuVO.get("linkUrl")) + "?menuId=" + paramMap.get("menuId");
	      if ((menuVO.get("linkParam") != null) && (!"".equals(String.valueOf(menuVO.get("linkParam"))))) {
	        url = url + "&" + String.valueOf(menuVO.get("linkParam"));
	      }

	      return "redirect:" + String.valueOf(url);
	    }
	    
	    
		 } catch (Exception e) {
				// TODO: handle exception
			 e.printStackTrace();
			}
	    
	    return "/front/layout/subLayout";
	  }
	*/
	
	
	
	
	
}
