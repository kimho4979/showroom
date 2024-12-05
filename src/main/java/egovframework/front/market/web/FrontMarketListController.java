package egovframework.front.market.web;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.cms.cmm.service.EgovFileMngUtil;
import egovframework.cms.cmm.service.EgovProperties;
import egovframework.cms.cmm.service.FileVO;
import egovframework.cms.cmm.util.MapQuery;
import egovframework.front.board.vo.FrontBoardInfoVO;
import egovframework.front.fixNAuc.web.FrontFixNAucController;
import egovframework.front.market.service.FrontMarketListService;
import egovframework.front.market.service.MarketService;
import egovframework.front.market.vo.MarketInfoVO;
import egovframework.front.market.vo.MarketReviewVO;
import egovframework.front.menu.service.FrontMenuService;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class FrontMarketListController {
	
	private static final Logger logger = LoggerFactory.getLogger(FrontMarketListController.class);
	
	@Resource(name="frontMenuService")
	private FrontMenuService frontMenuService;
	
	@Resource(name="frontMarketListService")
	private FrontMarketListService frontMarketListService;
	
	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** Validator */
	@Resource(name = "beanValidator") 
	protected DefaultBeanValidator beanValidator;
	
	
	@Autowired
	private EgovFileMngUtil egovFileMngUtil;
	
	@RequestMapping(value="/front/market/listAll.do")
	public String listAll(
			@RequestParam Map<String, Object> paramMap
		  , HttpServletRequest request
		  , HttpServletResponse response
		  , ModelMap model) throws Exception {
		try{
			model.addAttribute("paramMap", paramMap);
			model.addAttribute("contentPath", "/market/listAll.jsp");
			
		} catch (Exception e){
			e.printStackTrace();
		}
		return "/front/layout/subLayout";
	}
	
	@RequestMapping(value="/front/market/list.do")
	public String list(
			@RequestParam Map<String, Object> paramMap
		  , HttpServletRequest request
		  , HttpServletResponse response
		  , ModelMap model) throws Exception {
		try{
			
			/*
			EgovMap tyGroupVO = frontMarketListService.getMarketTypeGroupInfo(paramMap);
			
			if(tyGroupVO != null){
				paramMap.put("searchTypeArr", String.valueOf(tyGroupVO.get("tyCodeArr")).split(","));
			}
			
			List<EgovMap> resultList = frontMarketListService.getMarketAtList(paramMap);
			
			List<EgovMap> oraList = frontMarketListService.getOraMarketList(paramMap);
			
			for(EgovMap result : resultList ){
				paramMap.put("marketType", result.get("tyCode"));
				paramMap.put("marketNo", result.get("roCode"));
				String tyCode = String.valueOf(result.get("tyCode"));
				String roCode = String.valueOf(result.get("roCode"));
				for(EgovMap oraResult : oraList ){
					String marketType = String.valueOf(oraResult.get("marketType"));
					String marketNo = String.valueOf(oraResult.get("marketNo"));
					if(tyCode.equals(marketType) && roCode.equals(marketNo)){
						result.putAll(oraResult);
					}
				}
				if(result.get("rcImg1") != null){
					result.put("img1", result.get("rcImg1"));
				}
			}
			
			Collections.shuffle(resultList);*/
			
			model.addAttribute("resultList", new ArrayList<>());
			model.addAttribute("paramMap", paramMap);
			model.addAttribute("contentPath", "/market/list2.jsp");//전시실예약
			
		} catch (Exception e){
			e.printStackTrace();
		}
		return "/front/layout/subLayout";
	}
	
	
	@RequestMapping(value="/front/market/areaMapInfo.json")
	public String areaMapInfo(
			@RequestParam Map<String, Object> paramMap
		   , HttpServletRequest request
		   , HttpServletResponse response
		   , ModelMap model) throws Exception{
		
		EgovMap atMarketInfo = frontMarketListService.getAtMarketInfo(paramMap);
		//EgovMap oraMarketInfo = frontMarketListService.getOraMarketInfo(paramMap); 
		
		model.addAttribute("atMarketInfo",atMarketInfo);
		//model.addAttribute("oraMarketInfo",oraMarketInfo);
		
		return "jsonView";
	}
	
}
