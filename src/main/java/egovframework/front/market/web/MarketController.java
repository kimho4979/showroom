package egovframework.front.market.web;

import java.net.URLEncoder;
import java.util.ArrayList;
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
import egovframework.front.market.service.MarketService;
import egovframework.front.market.vo.MarketInfoVO;
import egovframework.front.market.vo.MarketReviewVO;
import egovframework.front.menu.service.FrontMenuService;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class MarketController {
	
	private static final Logger logger = LoggerFactory.getLogger(MarketController.class);
	
	@Resource(name="frontMenuService")
	private FrontMenuService frontMenuService;
	
	@Resource(name="marketService")
	private MarketService marketService;
	
	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** Validator */
	@Resource(name = "beanValidator") 
	protected DefaultBeanValidator beanValidator;
	
	@Resource(name="egovMarketReviewIdGnrService")
	private EgovIdGnrService egovMarketReviewIdGnrService;
	
	@Autowired
	//@Qualifier("EgovFileMngUtil")
	private EgovFileMngUtil egovFileMngUtil;
	
	@RequestMapping(value="/front/market/view.do")
	public String marketView(
			@RequestParam Map<String, Object> paramMap
			, @ModelAttribute MarketInfoVO infoVO
			, @ModelAttribute MarketReviewVO reviewVO
			, ModelMap model) throws Exception {
		try{
			
			//MarketInfoVO result = marketService.getMarketInfo(infoVO);	
			
			paramMap.put("tyCode", paramMap.get("tyCode"));
			paramMap.put("roCode", paramMap.get("roCode"));
			
			EgovMap mResult = marketService.selectMarketInfo(paramMap);
			
			//List<EgovMap> fileList = marketService.getMarketFileList(paramMap);
			reviewVO.setMarketSeq(Integer.parseInt(String.valueOf(mResult.get("marketSeq"))));
			EgovMap sumVO = marketService.getMarketReviewListCnt(reviewVO);
			String totCnt = String.valueOf(sumVO.get("totcnt"));
			String stAvg = String.valueOf(sumVO.get("stavg"));
			
			/*
			EgovMap menuInfo = frontMenuService.getMenuInfo(paramMap);
			String menuId = String.valueOf(paramMap.get("menuId"));
			String marketSeq = String.valueOf(paramMap.get("marketSeq"));
			*/
			
			
			if(mResult.get("rcImg1") != null){
				mResult.put("img1", mResult.get("rcImg1"));
			}
			
			if(mResult.get("rcImg2") != null){
				mResult.put("img2", mResult.get("rcImg2"));
			}
			
			if(mResult.get("rcImg3") != null){
				mResult.put("img3", mResult.get("rcImg3"));
			}
			
			model.addAttribute("stAvg", stAvg);
			model.addAttribute("totCnt", totCnt);
			
			//model.addAttribute("fileList", fileList);
			model.addAttribute("mResult", mResult);
			model.addAttribute("paramMap", paramMap);
			//model.addAttribute("result", result);
			
			model.addAttribute("contentPath", "/market/view.jsp");
			
		} catch (Exception e){
			e.printStackTrace();
		}
		return "/front/layout/subLayout";
	}
	
	
	@RequestMapping(value="/front/market/regReview.json")
	public String regReviewAjax(@RequestParam Map<String, Object> paramMap,
			ModelMap model,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		try {
  
				System.out.println("세션있음");
				System.out.println(request.getSession().getAttribute("atLoginVO"));
				EgovMap atLoginVO = (EgovMap)request.getSession().getAttribute("atLoginVO");
				Map<String, Object> snsLoginVO = (Map<String, Object>)request.getSession().getAttribute("snsLoginVO");
				
				if(atLoginVO != null || snsLoginVO != null){
					if(atLoginVO != null){
						paramMap.put("userId", atLoginVO.get("loginId"));
					}
					
					if(snsLoginVO != null){
						paramMap.put("userId", snsLoginVO.get("id"));
					}
					
					int reviewInfoCnt = marketService.getMarketReviewInfo(paramMap);
					if(reviewInfoCnt > 0){
						model.addAttribute("result","review");
					}else{
						paramMap.put("reviewSeq", egovMarketReviewIdGnrService.getNextIntegerId());
						marketService.regMarketReview(paramMap);
						model.addAttribute("result","success");
					}
					
				}else{
					model.addAttribute("result","login");
				}
				
			
		} catch (Exception e){
			e.printStackTrace();
			model.addAttribute("result","error");
		}
		
		return "jsonView";
	}
	
	
	@RequestMapping(value="/front/market/reviewList.json")
	public String marketReviewListAjax(
			@RequestParam Map<String, Object> paramMap
		   , @ModelAttribute MarketInfoVO infoVO
		   , @ModelAttribute MarketReviewVO reviewVO
		   , ModelMap model)
			throws Exception{
		
		List<MarketReviewVO> marketReviewVO = marketService.getMarketReviewList(reviewVO); 
		
		model.addAttribute("list",marketReviewVO);
		return "jsonView";
	}
	
	
	
	
	// 25.03. 2.3 프로세스 검증 누락 – 취약(1) 대응	
	// @RequestMapping(value="/front/market/saveImgFile.json")
	// public String ajaxFixSellList(HttpServletRequest request
	// 		, HttpServletResponse response
	// 		, @RequestParam Map<String, Object> paramMap
	// 		, MultipartHttpServletRequest multiRequest
	// 		, ModelMap model) throws Exception {
	// 	List<FileVO> uploadFileList = uploadThumbFile(model, multiRequest);
	// 	model.addAttribute("uploadFileList",uploadFileList);
	// 	return "jsonView";
	// }
	
     private List<FileVO> uploadThumbFile(ModelMap model, MultipartHttpServletRequest multiRequest) throws Exception{
		
		String mode = EgovProperties.getProperty("Globals.Mode");
		String savePath = EgovProperties.getProperty("Globals.DstbtPath"+mode);
		
	    String uploadPath = savePath + "/uploads/market/review/";
		
	    String atchFileId = "-1";
	    List<MultipartFile> atchFile = multiRequest.getFiles("imgFile");

	    List<FileVO> atchFileList = null;
	    try {
	      atchFileList = egovFileMngUtil.parseFileInf(atchFile, "", 0, atchFileId, uploadPath, "gif,jpg,jpeg,png,bmp", 20);
	    } catch (Exception e) {
	      model.addAttribute("message", e.getMessage());
	      e.printStackTrace();
	    }

	    return atchFileList;
	  }
	
	
}
