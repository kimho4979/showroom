package egovframework.front.board.web;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.cms.cmm.service.EgovFileMngUtil;
import egovframework.cms.cmm.service.EgovProperties;
import egovframework.cms.cmm.service.EgovStringUtil;
import egovframework.cms.cmm.util.MapQuery;
import egovframework.front.board.service.FrontBoardInfoService;
import egovframework.front.board.service.FrontBoardService;
import egovframework.front.board.vo.FrontBoardArticleVO;
import egovframework.front.board.vo.FrontBoardFileVO;
import egovframework.front.board.vo.FrontBoardInfoVO;
import egovframework.front.menu.service.FrontMenuService;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class FrontBoardController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FrontBoardController.class);
	
	@Resource(name="frontMenuService")
	private FrontMenuService frontMenuService;
	
	@Resource(name="frontBoardInfoService")
	private FrontBoardInfoService frontBoardInfoService;

	@Resource(name="frontBoardService")
	private FrontBoardService frontBoardService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
	
	@Resource(name="egovContentIdGnrService")
	private EgovIdGnrService egovContentIdGnrService;
	
	@Autowired
	//@Qualifier("EgovFileMngUtil")
	private EgovFileMngUtil egovFileMngUtil;
	
	
	
	
	/* 리스트 */
	@RequestMapping(value = "/front/board/bbsList.do")
	public String frontBoardList(HttpServletRequest pRequest, 
			HttpServletResponse pResponse
			, @RequestParam Map<String, Object> paramMap
			, @ModelAttribute FrontBoardInfoVO infoVO
			, @ModelAttribute FrontBoardArticleVO searchVO
			, ModelMap model) throws Exception{
		try {
			
		FrontBoardInfoVO frontBoardInfoVO = frontBoardInfoService.getFrontBoardInfo(infoVO);
		String boardTy = frontBoardInfoVO.getBoardTy();	
		
		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		searchVO.setPageSize(propertiesService.getInt("pageSize"));
		if(boardTy.equals("image")) searchVO.setPageUnit(8);
		
		Map<String, Object> param = new HashMap<String, Object>();
		
	    param.putAll(paramMap);
	    param.put("pageIndex", null);
	    param.put("pageUnit", searchVO.getPageUnit());
	    param.put("pageSize", searchVO.getPageSize());
	    
		String paginationQueryString = MapQuery.urlEncodeUTF8(param);
		model.addAttribute("paginationQueryString", paginationQueryString);
		
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
	
		
		EgovMap menuInfo = frontMenuService.getMenuInfo(paramMap);
		String boardId = String.valueOf(paramMap.get("boardId"));
		String menuId = String.valueOf(paramMap.get("menuId"));		
		
		
		
		int totCnt = frontBoardService.getFrontBoardArticleListCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		Map map =  frontBoardService.getFrontBoardArticleList(searchVO);  
		 
		
		
		model.addAttribute("resultList", map.get("resultList")); 
		model.addAttribute("recordsTotal", totCnt);
		model.addAttribute("pageNav", searchVO);
		model.addAttribute("boardId", paramMap.get("boardId"));
		model.addAttribute("menuId", paramMap.get("menuId"));
		
		if(paramMap.get("pageIndex") == null){
			model.addAttribute("pageIndex", 1);
		}else{
			model.addAttribute("pageIndex", paramMap.get("pageIndex"));
		}
		
		if(paramMap.get("pageUnit") == null){
			model.addAttribute("pageUnit", 10);
			if(boardTy.equals("image")) model.addAttribute("pageUnit", 8);
		}else{
			model.addAttribute("pageUnit", paramMap.get("pageUnit"));
		}
		
		if(paramMap.get("pageSize") == null){
			model.addAttribute("pageSize", 5);
		}else{
			model.addAttribute("pageSize", paramMap.get("pageSize"));
		}
		
		//model.addAttribute("boardFileList", getBoardFileList(result));
		
		paginationInfo.setTotalRecordCount(totCnt);
		
		if(null != paramMap.get("year") && paramMap.get("year").toString().equals("2022")){
			model.addAttribute("contentPath", "/frontBoard/list_image_2022.jsp");
		} else if(null != paramMap.get("year") && paramMap.get("year").toString().equals("2023")){ 
			model.addAttribute("contentPath", "/frontBoard/list_image_2023.jsp");	
		} else {
			model.addAttribute("contentPath", "/frontBoard/list_"+boardTy+".jsp");
		}
		
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("paramMap", paramMap);

/*		double endPage = (int) Math.ceil(totCnt/10); 
		model.addAttribute("endPage", endPage);*/
		
		LOGGER.info("pageIndex = {}", paramMap.get("pageIndex"));
		LOGGER.info("pageSize = {}", paramMap.get("pageSize"));
		LOGGER.info("pageUnit = {}",paramMap.get("pageUnit"));
		LOGGER.info("boardId = {}", boardId);
		LOGGER.info("totCnt = {}", totCnt);
		LOGGER.info("pageNav.searchKeyword = {}", searchVO.getSearchKeyword());
		LOGGER.info("pageNav.searchCondition = {}", searchVO.getSearchCondition());
	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "/front/layout/subLayout";
		  	
	}
	
/*	private List<FrontBoardFileVO> getBoardFileList(FrontBoardArticleVO frontBoardArticleVO) throws Exception
	{
		if (frontBoardArticleVO == null){
			return null;
		}
		
		FrontBoardFileVO frontBoardFileVO = new FrontBoardFileVO();
		frontBoardFileVO.setBoardId(frontBoardArticleVO.getBoardId());
		frontBoardFileVO.setArticleId(frontBoardArticleVO.getArticleId());
		return frontBoardService.getFrontBoardFileList(paramMap); 
	}*/
	
	@RequestMapping(value="/front/board/bbsView.do")
	public String boardArticleView(
			@RequestParam Map<String, Object> paramMap
			,@RequestParam Map<String, String[]> map
			, @ModelAttribute FrontBoardInfoVO infoVO
			, @ModelAttribute FrontBoardArticleVO searchVO
			, @ModelAttribute FrontBoardFileVO fileVO
			, ModelMap model) throws Exception {
		try {
			
		
		paramMap.put("articleId", searchVO.getArticleId());
		
		List<FrontBoardFileVO> list = frontBoardService.getFrontBoardFileList(paramMap);
		
	
		FrontBoardInfoVO frontBoardInfoVO = frontBoardInfoService.getFrontBoardInfo(infoVO);
		
		EgovMap menuInfo = frontMenuService.getMenuInfo(paramMap);
		String boardId = String.valueOf(paramMap.get("boardId"));
		String menuId = String.valueOf(paramMap.get("menuId"));
		String articleId = String.valueOf(paramMap.get("articleId"));
		FrontBoardArticleVO result = frontBoardService.getFrontBoardArticle(searchVO);
		
		
	
		String orignlFileNm = String.valueOf(paramMap.get("orignlFileNm"));
	
		
		/*if(!searchVO.getWriterId().equals("userId")){
			frontBoardService.updateFrontBoardArticleHits(searchVO);
		}*/
		frontBoardService.updateFrontBoardArticleHits(searchVO);
		
		String boardTy = frontBoardInfoVO.getBoardTy();
		
		model.addAttribute("result", result);
		model.addAttribute("fileList", list);
		model.addAttribute("orignlFileNm", orignlFileNm);
		model.addAttribute("contentPath", "/frontBoard/view_"+boardTy+".jsp");
		model.addAttribute("paramMap", paramMap);
		
		

		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return "/front/layout/subLayout";
		
	}
	
	@RequestMapping(value="/front/board/fileDown.do")
	public void downFile(
			  @RequestParam Map<String, Object> paramMap
			, @ModelAttribute FrontBoardFileVO fileVO
			, HttpServletResponse response
			, HttpServletRequest request
			, ModelMap model
			, String streFileNm
			, String orignFileNm) throws Exception{
		
		String mode = EgovProperties.getProperty("Globals.Mode");
		String savePath = EgovProperties.getProperty("Globals.DstbtPath"+mode);
		System.out.println("savePath::::::"+savePath);
		
	    String uploadPath = savePath + "/uploads/board/" + fileVO.getBoardId() + "/";
		
		FrontBoardFileVO fileInfo = frontBoardService.getFrontBoardFile(fileVO);
		try {
			egovFileMngUtil.downFile(request, response, uploadPath+fileInfo.getStreFileNm(), fileInfo.getOrignlFileNm());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		streFileNm = String.valueOf(paramMap.get("streFileNm"));
	 	orignFileNm = String.valueOf(paramMap.get("orignlFileNm"));
		String fileSn = String.valueOf(paramMap.get("fileSn"));

		model.addAttribute("paramMap", paramMap);
		//return "jsonView";
	}
	
	
	@RequestMapping(value="/front/board/fileDown2.do")
	public void downFile2(
			  @RequestParam Map<String, Object> paramMap
			, @ModelAttribute FrontBoardFileVO fileVO
			, HttpServletResponse response
			, HttpServletRequest request
			, ModelMap model
			, String streFileNm
			, String orignFileNm) throws Exception{
		
		String mode = EgovProperties.getProperty("Globals.Mode");
		String savePath = EgovProperties.getProperty("Globals.DstbtPath"+mode);
		System.out.println("savePath::::::"+savePath);
		
	    String uploadPath = savePath + "/uploads/board/" + fileVO.getBoardId() + "/";
		
		FrontBoardFileVO fileInfo = frontBoardService.getFrontBoardFile(fileVO);
		try {
			egovFileMngUtil.downFile2(request, response, uploadPath+fileInfo.getStreFileNm(), fileInfo.getOrignlFileNm());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		streFileNm = String.valueOf(paramMap.get("streFileNm"));
	 	orignFileNm = String.valueOf(paramMap.get("orignlFileNm"));
		String fileSn = String.valueOf(paramMap.get("fileSn"));

		model.addAttribute("paramMap", paramMap);
		//return "jsonView";
	}
}
