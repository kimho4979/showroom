package egovframework.front.fixNAuc.web;

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
















import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.cms.board.vo.BoardArticleVO;
import egovframework.cms.board.vo.BoardFileVO;
import egovframework.cms.board.vo.BoardInfoVO;
import egovframework.cms.cmm.service.EgovFileMngUtil;
import egovframework.cms.cmm.service.EgovProperties;
import egovframework.cms.cmm.service.FileVO;
import egovframework.cms.cmm.util.MapQuery;
import egovframework.front.content.service.FrontContentService;
import egovframework.front.content.service.FrontContentVO;
import egovframework.front.fixNAuc.service.FrontFixNAucService;
import egovframework.front.fixAuc.service.FrontFixAucRecordService;
import egovframework.front.login.service.FrontLoginService;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;

@Controller
public class FrontFixNAucController {
	private static final Logger logger = LoggerFactory.getLogger(FrontFixNAucController.class);

	@Resource(name = "frontFixNAucService")
	private FrontFixNAucService frontFixNAucService;
	
	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
	
	
	@Resource(name = "frontLoginService")
	protected FrontLoginService frontLoginService;
	
	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil egovFileMngUtil;
	
	@Resource(name="egovFixIdGnrService")
	private EgovIdGnrService egovFixIdGnrService;
	
	@Resource(name="egovBidIdGnrService")
	private EgovIdGnrService egovBidIdGnrService;
	
	@Resource(name="egovFixFileIdGnrService")
	private EgovIdGnrService egovFixFileIdGnrService;
	
	@Resource(name="egovReqIdGnrService")
	private EgovIdGnrService egovReqIdGnrService;
	
	//TB_RECORD 상태 변경 이력 
	@Resource(name = "FrontFixAucRecordService")
	private FrontFixAucRecordService frontFixAucRecordService;
	
	
	@RequestMapping(value="/front/fixNAuc/fixSellList.do")
	public String fixSellList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"01".equals(aucType) &&  !"02".equals(aucType) ){
			
			
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		try {
			
			PaginationInfo paginationInfo = new PaginationInfo();
			
			if(paramMap.get("pageIndex") == null){
				paramMap.put("pageIndex",1);
			}
			
			if(paramMap.get("pageUnit") == null){
				paramMap.put("pageUnit",10);
			}
			
			if(paramMap.get("pageSize") == null){
				paramMap.put("pageSize",5);
			}
			
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd");
			
			if(paramMap.get("searchStartDt") == null){
				// paramMap.put("searchStartDt",format.format(date));
				
				/*
				 * 채성주 [ 2022.01.10 ]
				 * 출하예정일자 
				 * 절화 => 월(2), 수(4), 금(6)
				 */
				Calendar cal = Calendar.getInstance();
		        cal.setTime(date);
		        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		        
		        if(dayOfWeek == 1) {
		        	cal.add(Calendar.DATE, 1);
		        }else if(dayOfWeek == 2) {
		        	cal.add(Calendar.DATE, 2);
		        }else if(dayOfWeek == 3) {
		        	cal.add(Calendar.DATE, 1);
		        }else if(dayOfWeek == 4) {
		        	cal.add(Calendar.DATE, 2);
		        }else if(dayOfWeek == 5) {
		        	cal.add(Calendar.DATE, 1);
		        }else if(dayOfWeek == 6) {
		        	cal.add(Calendar.DATE, 3);
		        }else if(dayOfWeek == 7) {
		        	cal.add(Calendar.DATE, 2);
		        }
		        
				paramMap.put("searchStartDt",format.format(cal.getTime()));
			}
			
			if(paramMap.get("searchEndDt") == null){
				paramMap.put("searchEndDt",format.format(date));
			}
			
			Map<String, Object> param = new HashMap<String, Object>();
		    param.putAll(paramMap);
		    param.put("pageIndex", null);
		    
			String paginationQueryString = MapQuery.urlEncodeUTF8(param);
			
		    paginationInfo.setCurrentPageNo(Integer.parseInt(String.valueOf(paramMap.get("pageIndex"))));
		    paginationInfo.setRecordCountPerPage(Integer.parseInt(String.valueOf(paramMap.get("pageUnit"))));
		    paginationInfo.setPageSize(Integer.parseInt(String.valueOf(paramMap.get("pageSize"))));
		    
		    
		    paramMap.put("firstIndex", paginationInfo.getFirstRecordIndex());
		    paramMap.put("lastIndex", paginationInfo.getLastRecordIndex());
		    paramMap.put("recordCountPerPage", paginationInfo.getRecordCountPerPage());
			
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			List<EgovMap> fixList = frontFixNAucService.getFixList(paramMap);
			
			for(EgovMap fixVO : fixList){
				String chulCode = String.valueOf(fixVO.get("chulCode"));
				EgovMap chulInfo = frontFixNAucService.getChulCodeToChulInfo(chulCode);
				if(chulInfo != null){
					fixVO.put("chulName", chulInfo.get("name"));
					fixVO.put("chulArea", chulInfo.get("chulArea"));
				}else{
					fixVO.put("chulName", "정보없음");
					fixVO.put("chulArea", "정보없음");
				}
			}
			
			int totCnt = frontFixNAucService.getFixListCnt(paramMap);
			
			paginationInfo.setTotalRecordCount(totCnt);
			
			
			
			//입찰 가능 시간
			paramMap.put("timeType", "3");
			EgovMap timeVO = frontFixNAucService.getTime(paramMap);
			
			//판매접수시간
			paramMap.put("timeType", "1");
			EgovMap regTimeVO = frontFixNAucService.getTime(paramMap);
			
			model.addAttribute("paginationInfo", paginationInfo);
		    model.addAttribute("paginationQueryString", paginationQueryString);
			
			model.addAttribute("resultList", fixList);
			model.addAttribute("resultCnt", totCnt);
			model.addAttribute("timeVO", timeVO);
			model.addAttribute("regTimeVO", regTimeVO);
			model.addAttribute("paramMap", paramMap);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		model.addAttribute("title", "판매");
		model.addAttribute("contentPath", "fixAuc/N/fixSellList.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	
	@RequestMapping(value="/front/fixNAuc/fixSellList.json")
	public String ajaxFixSellList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"01".equals(aucType) &&  !"02".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		try {
			
			PaginationInfo paginationInfo = new PaginationInfo();
			
			if(paramMap.get("pageIndex") == null){
				paramMap.put("pageIndex",1);
			}
			
			if(paramMap.get("pageUnit") == null){
				paramMap.put("pageUnit",10);
			}
			
			if(paramMap.get("pageSize") == null){
				paramMap.put("pageSize",5);
			}
			
			Map<String, Object> param = new HashMap<String, Object>();
		    param.putAll(paramMap);
		    param.put("pageIndex", null);
		    
			String paginationQueryString = MapQuery.urlEncodeUTF8(param);
			
		    paginationInfo.setCurrentPageNo(Integer.parseInt(String.valueOf(paramMap.get("pageIndex"))));
		    paginationInfo.setRecordCountPerPage(Integer.parseInt(String.valueOf(paramMap.get("pageUnit"))));
		    paginationInfo.setPageSize(Integer.parseInt(String.valueOf(paramMap.get("pageSize"))));
		    
		    
		    paramMap.put("firstIndex", paginationInfo.getFirstRecordIndex());
		    paramMap.put("lastIndex", paginationInfo.getLastRecordIndex());
		    paramMap.put("recordCountPerPage", paginationInfo.getRecordCountPerPage());
			
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			List<EgovMap> fixList = frontFixNAucService.getFixList(paramMap);
			
			for(EgovMap fixVO : fixList){
				String chulCode = String.valueOf(fixVO.get("chulCode"));
				EgovMap chulInfo = frontFixNAucService.getChulCodeToChulInfo(chulCode);
				if(chulInfo != null){
					fixVO.put("chulName", chulInfo.get("name"));
					fixVO.put("chulArea", chulInfo.get("chulArea"));
				}else{
					fixVO.put("chulName", "정보없음");
					fixVO.put("chulArea", "정보없음");
				}
			}
			
			int totCnt = frontFixNAucService.getFixListCnt(paramMap);
			
			paginationInfo.setTotalRecordCount(totCnt);
			
			model.addAttribute("paginationInfo", paginationInfo);
		    model.addAttribute("paginationQueryString", paginationQueryString);
			
			model.addAttribute("resultList", fixList);
			model.addAttribute("resultCnt", totCnt);
			model.addAttribute("paramMap", paramMap);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return "jsonView";
	}
	
	@RequestMapping(value="/front/fixNAuc/fixSellView.do")
	public String fixSellView(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"01".equals(aucType) &&  !"02".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		paramMap.put("id", loginVO.get("loginId"));
		paramMap.put("memberId", loginVO.get("memberId"));
		
		
		EgovMap fixVO = frontFixNAucService.getFixArticle(paramMap);
		if(fixVO != null){
			
			String chulCode = String.valueOf(fixVO.get("chulCode"));
			EgovMap chulInfo = frontFixNAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				fixVO.put("chulName", chulInfo.get("name"));
				fixVO.put("chulArea", chulInfo.get("chulArea"));
			}else{
				fixVO.put("chulName", "정보없음");
				fixVO.put("chulArea", "정보없음");
			}
			List<EgovMap> fixFileList = frontFixNAucService.getFixFileList(paramMap);
			model.addAttribute("fixFileList", fixFileList);
			
			List<EgovMap> bidList = frontFixNAucService.getBidList(paramMap);
			model.addAttribute("bidList", bidList);
		}
		
		model.addAttribute("result", fixVO);
		
		model.addAttribute("title", "판매");
		model.addAttribute("contentPath", "fixAuc/N/fixSellView.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	@RequestMapping(value="/front/fixNAuc/fixSellReg.do")
	public String fixSellReg(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"01".equals(aucType) &&  !"02".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		paramMap.put("id", loginVO.get("loginId"));
		paramMap.put("memberId", loginVO.get("memberId"));
		
		
		//판매 접수 시간
		paramMap.put("timeType", "1");
		EgovMap timeVO = frontFixNAucService.getTime(paramMap);
		if(timeVO != null){
			if("N".equals(String.valueOf(timeVO.get("timeCheck"))) ){
				model.addAttribute("message", "판매접수시간이 아닙니다.");
				model.addAttribute("returnUrl", "/front/fixNAuc/fixSellList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/N/result";
			}
		}
		
		//출하자 코드리스트
		List<EgovMap> floMemberList = frontLoginService.getFloMember(paramMap);
		
		List<EgovMap> floList = new ArrayList<EgovMap>();
		
		for(EgovMap chulVO : floMemberList){
			String chulCode = String.valueOf(chulVO.get("chulCd"));
			EgovMap chulInfo = frontFixNAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				floList.add(chulInfo);
			}
			
		}
		
		model.addAttribute("floList", floList);
		
		//등급 코드 리스트
		paramMap.put("bunChk", "N");
		List<EgovMap> levelList = frontFixNAucService.getLevelList(paramMap);
		model.addAttribute("levelList", levelList);
		
		model.addAttribute("title", "판매");
		model.addAttribute("contentPath", "fixAuc/N/fixSellReg.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	@RequestMapping(value="/front/fixNAuc/fixSellRegProc.do")
	public String fixSellRegProc(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, MultipartHttpServletRequest multiRequest
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"01".equals(aucType) &&  !"02".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		//판매 접수 시간
		paramMap.put("timeType", "1");
		EgovMap timeVO = frontFixNAucService.getTime(paramMap);
		if(timeVO != null){
			if("N".equals(String.valueOf(timeVO.get("timeCheck"))) ){
				model.addAttribute("message", "판매접수시간이 아닙니다.");
				model.addAttribute("returnUrl", "/front/fixNAuc/fixSellList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/N/result";
			}
		}
		
		int result = 0;
		try {
			
			paramMap.put("fixDealSeq", egovFixIdGnrService.getNextIntegerId()); 
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			result = frontFixNAucService.fixSellRegProc(paramMap);
			
			//TB_RECORD 상태 변경 이력 Map
			Map<String, Object> recordMap = new HashMap<String, Object>();
			
			recordMap.put("dealSeq", paramMap.get("fixDealSeq"));
			recordMap.put("fixType", "1");
			recordMap.put("bunChk", "N");
			recordMap.put("fixState", "1");
			recordMap.put("id", paramMap.get("id"));
			
			//TB_RECORD 출하자 최초 등록 insert (1)
			frontFixAucRecordService.fixAucRegRecord(recordMap);
			
			//파일
			List<FileVO> uploadFileList = uploadThumbFile(model, multiRequest);
			
			if ((uploadFileList != null) && (uploadFileList.size() > 0)){
				Map<String, Object> fileMap = new HashMap<String, Object>();
		      
				for (int i = 0; i < uploadFileList.size(); i++){
		    	  
		    	  FileVO fileVO = uploadFileList.get(i);
		    	  fileMap.put("fileSeq",((FileVO)uploadFileList.get(i)).getFileSn());
		    	  fileMap.put("fixDealSeq", paramMap.get("fixDealSeq"));
		    	  fileMap.put("streFilePath", fileVO.getFileStreCours());
		    	  fileMap.put("streFileNm", fileVO.getOrignlFileNm()+"."+fileVO.getStreFileNm());
		    	  fileMap.put("orignlFileNm", fileVO.getOrignlFileNm());
		    	  
		    	  fileMap.put("fileExtsn", fileVO.getFileExtsn());
		    	  fileMap.put("fileCn", fileVO.getFileCn());
		    	  fileMap.put("fileSize", fileVO.getFileMg());
		    	  fileMap.put("id", loginVO.get("loginId"));
		    	  
		    	  //썸네일 이후 수정
		    	  fileMap.put("thumbStreFileNm", fileVO.getStreFileNm());
		    	  System.out.println("등록파일 SN : "+ String.valueOf(fileMap.get("fileSeq")));
		    	  frontFixNAucService.fixSellFileProc(fileMap);
		    	  
		    	  
		    	  if (fileVO != null) {
				      File image = new File(fileVO.getFileStreCours() + fileVO.getStreFileNm());
				      if (image.exists()) {
				        try {
				          Thumbnails.of(new File[] { image }).size(600, 600).outputQuality(1.0F).toFile(image);
				        } catch (Exception ex) {
				          ex.fillInStackTrace();
				        }
				      }
				    }
		    	  
		       }
		    }
			
			model.addAttribute("message", "정상적으로 저장되었습니다.");
			model.addAttribute("returnUrl", "/front/fixNAuc/fixSellList.do");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = 0;
		}
		model.addAttribute("result", result);
		return "/front/fixAuc/N/result";
	}
	
	@RequestMapping(value="/front/fixNAuc/fixSellRegProc.json")
	public String fixSellRegProcAjax(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, MultipartHttpServletRequest multiRequest
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"01".equals(aucType) &&  !"02".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		//판매 접수 시간
		paramMap.put("timeType", "1");
		EgovMap timeVO = frontFixNAucService.getTime(paramMap);
		if(timeVO != null){
			if("N".equals(String.valueOf(timeVO.get("timeCheck"))) ){
				model.addAttribute("message", "판매접수시간이 아닙니다.");
				model.addAttribute("returnUrl", "/front/fixNAuc/fixSellList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/N/result";
			}
		}
		
		int result = 0;
		try {
			
			paramMap.put("fixDealSeq", egovFixIdGnrService.getNextIntegerId()); 
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			String mokName = String.valueOf(paramMap.get("mokName"));
			String pumName = String.valueOf(paramMap.get("pumName"));
			String chulLevelCustom = String.valueOf(paramMap.get("chulLevelCustom"));
			String chulLevelName = String.valueOf(paramMap.get("chulLevelName"));
			String itemText = String.valueOf(paramMap.get("itemText"));
			paramMap.put("mokName", new String(mokName.getBytes("iso-8859-1"),"utf-8"));
			paramMap.put("pumName", new String(pumName.getBytes("iso-8859-1"),"utf-8"));
			paramMap.put("chulLevelCustom", new String(chulLevelCustom.getBytes("iso-8859-1"),"utf-8"));
			paramMap.put("itemText", new String(itemText.getBytes("iso-8859-1"),"utf-8"));
			paramMap.put("chulLevelName", new String(chulLevelName.getBytes("iso-8859-1"),"utf-8"));
			
			result = frontFixNAucService.fixSellRegProc(paramMap);
			
			//TB_RECORD 상태 변경 이력 map 
			Map<String, Object> recordMap = new HashMap<String, Object>();
			
			//TB_RECORD 상태 변경 이력 데이터 put
			recordMap.put("dealSeq", paramMap.get("fixDealSeq"));
			recordMap.put("fixType", "1");
			recordMap.put("bunChk", "N");
			recordMap.put("fixState", "1");
			recordMap.put("id", paramMap.get("id"));
			
			//TB_RECORD 출하자 최초 등록 insert (1)
			frontFixAucRecordService.fixAucRegRecord(recordMap);
			
			//파일
			List<FileVO> uploadFileList = uploadThumbFile(model, multiRequest);
			
			if ((uploadFileList != null) && (uploadFileList.size() > 0)){
				Map<String, Object> fileMap = new HashMap<String, Object>();
		      
				for (int i = 0; i < uploadFileList.size(); i++){
		    	  
		    	  FileVO fileVO = uploadFileList.get(i);
		    	  fileMap.put("fileSeq",((FileVO)uploadFileList.get(i)).getFileSn());
		    	  fileMap.put("fixDealSeq", paramMap.get("fixDealSeq"));
		    	  fileMap.put("streFilePath", fileVO.getFileStreCours());
		    	  fileMap.put("streFileNm", fileVO.getOrignlFileNm()+"."+fileVO.getStreFileNm());
		    	  fileMap.put("orignlFileNm", fileVO.getOrignlFileNm());
		    	  
		    	  fileMap.put("fileExtsn", fileVO.getFileExtsn());
		    	  fileMap.put("fileCn", fileVO.getFileCn());
		    	  fileMap.put("fileSize", fileVO.getFileMg());
		    	  fileMap.put("id", loginVO.get("loginId"));
		    	  
		    	  //썸네일 이후 수정
		    	  fileMap.put("thumbStreFileNm", fileVO.getStreFileNm());
		    	  System.out.println("등록파일 SN : "+ String.valueOf(fileMap.get("fileSeq")));
		    	  frontFixNAucService.fixSellFileProc(fileMap);
		    	  
		    	  
		    	  if (fileVO != null) {
				      File image = new File(fileVO.getFileStreCours() + fileVO.getStreFileNm());
				      if (image.exists()) {
				        try {
				          Thumbnails.of(new File[] { image }).size(600, 600).outputQuality(1.0F).toFile(image);
				        } catch (Exception ex) {
				          ex.fillInStackTrace();
				        }
				      }
				    }
		    	  
		       }
		    }
			
			model.addAttribute("message", "정상적으로 저장되었습니다.");
			model.addAttribute("returnUrl", "/front/fixNAuc/fixSellList.do");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = 0;
		}
		model.addAttribute("result", result);
		return "jsonView";
	}
	
	@RequestMapping(value="/front/fixNAuc/fixSellUpt.do")
	public String fixSellUpt(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"01".equals(aucType) &&  !"02".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		//판매 접수 시간
		paramMap.put("timeType", "1");
		EgovMap timeVO = frontFixNAucService.getTime(paramMap);
		if(timeVO != null){
			if("N".equals(String.valueOf(timeVO.get("timeCheck"))) ){
				model.addAttribute("message", "판매접수시간이 아닙니다.");
				model.addAttribute("returnUrl", "/front/fixNAuc/fixSellList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/N/result";
			}
		}
		
		paramMap.put("id", loginVO.get("loginId"));
		paramMap.put("memberId", loginVO.get("memberId"));
		
		EgovMap fixVO = frontFixNAucService.getFixArticle(paramMap);
		if(fixVO != null){
			
			if(!"1".equals(String.valueOf(fixVO.get("fixState"))) && !"2".equals(String.valueOf(fixVO.get("fixState"))) ){
				model.addAttribute("message", "비정상적인 접근입니다.");
				model.addAttribute("returnUrl", "/front/fixNAuc/fixSellList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/N/result";
			}
			
			String chulCode = String.valueOf(fixVO.get("chulCode"));
			EgovMap chulInfo = frontFixNAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				fixVO.put("chulName", chulInfo.get("name"));
				fixVO.put("chulArea", chulInfo.get("chulArea"));
			}else{
				fixVO.put("chulName", "정보없음");
				fixVO.put("chulArea", "정보없음");
			}
			List<EgovMap> fixFileList = frontFixNAucService.getFixFileList(paramMap);
			model.addAttribute("fixFileList", fixFileList);
			for(EgovMap file : fixFileList ){
				String fileSeq = String.valueOf(file.get("fileSeq"));
				model.addAttribute("file"+fileSeq, file);
			}
		}else{
			model.addAttribute("message", "비정상적인 접근입니다.");
			model.addAttribute("returnUrl", "/front/fixNAuc/fixSellList.do");
			model.addAttribute("result", 1);
			return "/front/fixAuc/N/result";
		}
		
		model.addAttribute("result", fixVO);
		
		//최근판매정보
		/*
		List<EgovMap> recentList = frontFixNAucService.getRecentList(paramMap);
		model.addAttribute("recentList", recentList);
		*/
		//출하자 코드리스트
		List<EgovMap> floMemberList = frontLoginService.getFloMember(paramMap);
		
		List<EgovMap> floList = new ArrayList<EgovMap>();
		
		for(EgovMap chulVO : floMemberList){
			String chulCode = String.valueOf(chulVO.get("chulCd"));
			EgovMap chulInfo = frontFixNAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				floList.add(chulInfo);
			}
		}
		
		model.addAttribute("floList", floList);
		
		//등급 코드 리스트
		paramMap.put("bunChk", "N");
		List<EgovMap> levelList = frontFixNAucService.getLevelList(paramMap);
		model.addAttribute("levelList", levelList);
		
		
		model.addAttribute("title", "판매");
		model.addAttribute("contentPath", "fixAuc/N/fixSellUpt.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	@RequestMapping(value="/front/fixNAuc/fixSellUptProc.do")
	public String fixSellUptProc(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, MultipartHttpServletRequest multiRequest
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"01".equals(aucType) &&  !"02".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		//판매 접수 시간
		paramMap.put("timeType", "1");
		EgovMap timeVO = frontFixNAucService.getTime(paramMap);
		if(timeVO != null){
			if("N".equals(String.valueOf(timeVO.get("timeCheck"))) ){
				model.addAttribute("message", "판매접수시간이 아닙니다.");
				model.addAttribute("returnUrl", "/front/fixNAuc/fixSellList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/N/result";
			}
		}
		
		
		int result = 0;
		
		
		try {
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			EgovMap fixVO = frontFixNAucService.getFixArticle(paramMap);
			if(fixVO != null){
				
				if(!"1".equals(String.valueOf(fixVO.get("fixState"))) && !"2".equals(String.valueOf(fixVO.get("fixState"))) ){
					model.addAttribute("message", "비정상적인 접근입니다.");
					model.addAttribute("returnUrl", "/front/fixNAuc/fixSellList.do");
					model.addAttribute("result", 1);
					return "/front/fixAuc/N/result";
				}
			}else{
				model.addAttribute("message", "비정상적인 접근입니다.");
				model.addAttribute("returnUrl", "/front/fixNAuc/fixSellList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/N/result";
			}	 
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			
			//TB_RECORD 상태 변경 이력  이전 상태 값
			String preFixState = frontFixAucRecordService.getPreFixState(String.valueOf(paramMap.get("fixDealSeq")));
			
			result = frontFixNAucService.fixSellUptProc(paramMap);
			
			//TB_RECORD 상태 변경 이력 map  
			Map<String, Object> recordMap = new HashMap<String, Object>();
			
			//TB_RECORD 상태 변경 이력 데이터 put
			recordMap.put("dealSeq", paramMap.get("fixDealSeq"));
			recordMap.put("fixType", "1");
			recordMap.put("bunChk", "N");
			recordMap.put("preFixState", preFixState);
			recordMap.put("fixState", "1");
			recordMap.put("id", paramMap.get("id"));
			
			//TB_RECORD 출하자 판매 재신청 (2->1)
			frontFixAucRecordService.fixAucRegRecord(recordMap);
			
			//파일
			
			String[] deleteAtchFile = request.getParameterValues("deleteAtchFile");
			if(deleteAtchFile != null && deleteAtchFile.length > 0){
				for (int i = 0; i < deleteAtchFile.length; i++){
					paramMap.put("fileSeq",deleteAtchFile[i]);
			        frontFixNAucService.deleteFileOne(paramMap);
			      }
			}
			
			List<FileVO> uploadFileList = uploadThumbFile(model, multiRequest);
			
			if ((uploadFileList != null) && (uploadFileList.size() > 0)){
				Map<String, Object> fileMap = new HashMap<String, Object>();
		      
				for (int i = 0; i < uploadFileList.size(); i++){
					
		    	  FileVO fileVO = uploadFileList.get(i);
		    	  fileMap.put("fileSeq", ((FileVO)uploadFileList.get(i)).getFileSn());
		    	  fileMap.put("fixDealSeq", paramMap.get("fixDealSeq"));
		    	  fileMap.put("streFilePath", fileVO.getFileStreCours());
		    	  fileMap.put("streFileNm", fileVO.getOrignlFileNm()+"."+fileVO.getStreFileNm());
		    	  fileMap.put("orignlFileNm", fileVO.getOrignlFileNm());
		    	  
		    	  fileMap.put("fileExtsn", fileVO.getFileExtsn());
		    	  fileMap.put("fileCn", fileVO.getFileCn());
		    	  fileMap.put("fileSize", fileVO.getFileMg());
		    	  fileMap.put("id", loginVO.get("loginId"));
		    	  
		    	  //썸네일 이후 수정
		    	  fileMap.put("thumbStreFileNm", fileVO.getStreFileNm());
		    	  
		    	  
		    	  System.out.println("수정파일 SN : "+ String.valueOf(fileMap.get("fileSeq")));
		    	  
		    	  EgovMap fileCheckVO = frontFixNAucService.getFileOne(fileMap);
		    	  if(fileCheckVO != null){
		    		  frontFixNAucService.deleteFileOne(fileMap);
		    	  }
		    	  
		    	  frontFixNAucService.fixSellFileProc(fileMap);
		    	  
		    	  
		    	  if (fileVO != null) {
				      File image = new File(fileVO.getFileStreCours() + fileVO.getStreFileNm());
				      if (image.exists()) {
				        try {
				          Thumbnails.of(new File[] { image }).size(600, 600).outputQuality(1.0F).toFile(image);
				        } catch (Exception ex) {
				          ex.fillInStackTrace();
				        }
				      }
				    }
		    	  
		       }
		    }
			
			model.addAttribute("message", "정상적으로 저장되었습니다.");
			model.addAttribute("returnUrl", "/front/fixNAuc/fixSellList.do");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = 0;
		}
		model.addAttribute("result", result);
		return "/front/fixAuc/N/result";
	}
	
	
	@RequestMapping(value="/front/fixNAuc/fixSellDelProc.do")
	public String fixSellDelProc(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"01".equals(aucType) &&  !"02".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		int result = 0;
		
		try {
		paramMap.put("id", loginVO.get("loginId"));
		paramMap.put("memberId", loginVO.get("memberId"));
		
		EgovMap fixVO = frontFixNAucService.getFixArticle(paramMap);
		if(fixVO != null){
			
			if(!"1".equals(String.valueOf(fixVO.get("fixState"))) && !"2".equals(String.valueOf(fixVO.get("fixState"))) ){
				model.addAttribute("message", "비정상적인 접근입니다.");
				model.addAttribute("returnUrl", "/front/fixNAuc/fixSellList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/N/result";
			}
		}else{
			model.addAttribute("message", "비정상적인 접근입니다.");
			model.addAttribute("returnUrl", "/front/fixNAuc/fixSellList.do");
			model.addAttribute("result", 1);
			return "/front/fixAuc/N/result";
		}
		
			//삭제 fixDealSeq
			String fixDealSeq = String.valueOf(paramMap.get("fixDealSeq"));
		
			//TB_RECORD 상태 변경 이력 이전 상태 값
			String preFixState = frontFixAucRecordService.getPreFixState(fixDealSeq);
			
			result = frontFixNAucService.fixSellDelProc(paramMap);
			
			if(result == 1){
				frontFixNAucService.fixSellFileDelProc(paramMap);
			}
			
			//TB_RECORD 상태 변경 이력 map  
			Map<String, Object> recordMap = new HashMap<String, Object>();
			
			//TB_RECORD 상태 변경 이력 데이터 put
			recordMap.put("dealSeq", fixDealSeq);
			recordMap.put("fixType", "1");
			recordMap.put("bunChk", "N");
			recordMap.put("preFixState", preFixState);
			recordMap.put("id", paramMap.get("id"));
			
			//TB_RECORD 출하자 삭제 delete
			frontFixAucRecordService.fixAucRegRecord(recordMap);
			
			model.addAttribute("message", "정상적으로 삭제되었습니다.");
			model.addAttribute("returnUrl", "/front/fixNAuc/fixSellList.do");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = 0;
		}
		model.addAttribute("result", result);
		return "/front/fixAuc/N/result";
	}
	
	
	
	@RequestMapping(value="/front/fixNAuc/fixBuyList.do")
	public String fixBuyList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"03".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		try {
			
			PaginationInfo paginationInfo = new PaginationInfo();
			
			if(paramMap.get("pageIndex") == null){
				paramMap.put("pageIndex",1);
			}
			
			if(paramMap.get("pageUnit") == null){
				paramMap.put("pageUnit",5);
			}
			
			if(paramMap.get("pageSize") == null){
				paramMap.put("pageSize",5);
			}
			
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			if(paramMap.get("searchStartDt") == null){
				// paramMap.put("searchStartDt",format.format(date));
				
				/*
				 * 채성주 [ 2022.01.10 ]
				 * 경매일자 
				 * 절화 => 월(2), 수(4), 금(6)
				 */
				Calendar cal = Calendar.getInstance();
		        cal.setTime(date);
		        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		        
		        if(dayOfWeek == 1) {
		        	cal.add(Calendar.DATE, 1);
		        }else if(dayOfWeek == 2) {
		        	cal.add(Calendar.DATE, 2);
		        }else if(dayOfWeek == 3) {
		        	cal.add(Calendar.DATE, 1);
		        }else if(dayOfWeek == 4) {
		        	cal.add(Calendar.DATE, 2);
		        }else if(dayOfWeek == 5) {
		        	cal.add(Calendar.DATE, 1);
		        }else if(dayOfWeek == 6) {
		        	cal.add(Calendar.DATE, 3);
		        }else if(dayOfWeek == 7) {
		        	cal.add(Calendar.DATE, 2);
		        }
		        
				paramMap.put("searchStartDt",format.format(cal.getTime()));
			}
			
			if(paramMap.get("searchEndDt") == null){
				//paramMap.put("searchEndDt",format.format(date));
			}

			
			Map<String, Object> param = new HashMap<String, Object>();
		    param.putAll(paramMap);
		    param.put("pageIndex", null);
		    
			String paginationQueryString = MapQuery.urlEncodeUTF8(param);
			
		    paginationInfo.setCurrentPageNo(Integer.parseInt(String.valueOf(paramMap.get("pageIndex"))));
		    paginationInfo.setRecordCountPerPage(Integer.parseInt(String.valueOf(paramMap.get("pageUnit"))));
		    paginationInfo.setPageSize(Integer.parseInt(String.valueOf(paramMap.get("pageSize"))));
		    
		    
		    paramMap.put("firstIndex", paginationInfo.getFirstRecordIndex());
		    paramMap.put("lastIndex", paginationInfo.getLastRecordIndex());
		    paramMap.put("recordCountPerPage", paginationInfo.getRecordCountPerPage());
			
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			/*
			List<EgovMap> fixList = frontFixNAucService.getFixBuyList(paramMap);
			
			for(EgovMap fixVO : fixList){
				String chulCode = String.valueOf(fixVO.get("chulCode"));
				EgovMap chulInfo = frontFixNAucService.getChulCodeToChulInfo(chulCode);
				if(chulInfo != null){
					fixVO.put("chulName", chulInfo.get("name"));
					fixVO.put("chulArea", chulInfo.get("chulArea"));
				}else{
					fixVO.put("chulName", "정보없음");
					fixVO.put("chulArea", "정보없음");
				}
			}
			
			int totCnt = frontFixNAucService.getFixBuyListCnt(paramMap);
			
			paginationInfo.setTotalRecordCount(totCnt);
			*/
			//중도매인 정보
			List<EgovMap> floMemberList = frontLoginService.getFloMember(paramMap);
			
			List<EgovMap> floList = new ArrayList<EgovMap>();
			
			for(EgovMap chulVO : floMemberList){
				String jCode = String.valueOf(chulVO.get("chulCd"));
				EgovMap jInfo = frontFixNAucService.getJCodeToJInfo(jCode);
				floList.add(jInfo);
				
				String code = String.valueOf(paramMap.get("jCode"));
				if(jCode.equals(code)){
					paramMap.put("code", code);
					model.addAttribute("code", code);
				}
			}
			
			model.addAttribute("floList", floList);
			
			
			//입찰 가능 시간
			paramMap.put("timeType", "3");
			EgovMap timeVO = frontFixNAucService.getTime(paramMap);
			
			model.addAttribute("paginationInfo", paginationInfo);
		    model.addAttribute("paginationQueryString", paginationQueryString);
			/*
			model.addAttribute("resultList", fixList);
			model.addAttribute("resultCnt", totCnt);*/
			model.addAttribute("timeVO", timeVO);
			
			model.addAttribute("paramMap", paramMap);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		model.addAttribute("title", "구매");
		model.addAttribute("contentPath", "fixAuc/N/fixBuyList.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	
	@RequestMapping(value="/front/fixNAuc/fixBuyList.json")
	public String ajaxFixBuyList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"03".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		try {
			
			PaginationInfo paginationInfo = new PaginationInfo();
			
			if(paramMap.get("pageIndex") == null){
				paramMap.put("pageIndex",1);
			}
			
			if(paramMap.get("pageUnit") == null){
				paramMap.put("pageUnit",5);
			}
			
			if(paramMap.get("pageSize") == null){
				paramMap.put("pageSize",5);
			}
			
			Map<String, Object> param = new HashMap<String, Object>();
		    param.putAll(paramMap);
		    param.put("pageIndex", null);
		    
			String paginationQueryString = MapQuery.urlEncodeUTF8(param);
			
		    paginationInfo.setCurrentPageNo(Integer.parseInt(String.valueOf(paramMap.get("pageIndex"))));
		    paginationInfo.setRecordCountPerPage(Integer.parseInt(String.valueOf(paramMap.get("pageUnit"))));
		    paginationInfo.setPageSize(Integer.parseInt(String.valueOf(paramMap.get("pageSize"))));
		    
		    
		    paramMap.put("firstIndex", paginationInfo.getFirstRecordIndex());
		    paramMap.put("lastIndex", paginationInfo.getLastRecordIndex());
		    paramMap.put("recordCountPerPage", paginationInfo.getRecordCountPerPage());
			
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			List<EgovMap> fixList = frontFixNAucService.getFixBuyList(paramMap);
			
			for(EgovMap fixVO : fixList){
				String chulCode = String.valueOf(fixVO.get("chulCode"));
				EgovMap chulInfo = frontFixNAucService.getChulCodeToChulInfo(chulCode);
				if(chulInfo != null){
					fixVO.put("chulName", chulInfo.get("name"));
					fixVO.put("chulArea", chulInfo.get("chulArea"));
				}else{
					fixVO.put("chulName", "정보없음");
					fixVO.put("chulArea", "정보없음");
				}
				
				paramMap.put("fixDealSeq", fixVO.get("fixDealSeq"));
				EgovMap bidInfo = frontFixNAucService.getBidBuyInfo(paramMap);
				if(bidInfo != null){
					fixVO.put("bidBoxCnt", bidInfo.get("bidBoxCnt"));
					fixVO.put("bidSokCnt", bidInfo.get("bidSokCnt"));
					fixVO.put("bidUnitPrice", bidInfo.get("bidUnitPrice"));
					fixVO.put("bidAdminYn", bidInfo.get("bidAdminYn"));
					fixVO.put("bidState", bidInfo.get("bidState"));
					fixVO.put("bidCheck", "Y");
				}else{
					fixVO.put("bidBoxCnt", "");
					fixVO.put("bidSokCnt", "");
					fixVO.put("bidUnitPrice", "");
					fixVO.put("bidAdminYn", "");
					fixVO.put("bidState", "");
					fixVO.put("bidCheck", "");
				}
				
			}
			
			int totCnt = frontFixNAucService.getFixBuyListCnt(paramMap);
			
			paginationInfo.setTotalRecordCount(totCnt);
			
			model.addAttribute("paginationInfo", paginationInfo);
		    model.addAttribute("paginationQueryString", paginationQueryString);
			
			model.addAttribute("resultList", fixList);
			model.addAttribute("resultCnt", totCnt);
			model.addAttribute("paramMap", paramMap);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return "jsonView";
	}
	
	
	@RequestMapping(value="/front/fixNAuc/fixBuyView.do")
	public String fixBuyView(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"03".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		
		paramMap.put("id", loginVO.get("loginId"));
		paramMap.put("memberId", loginVO.get("memberId"));
		
		
		EgovMap fixVO = frontFixNAucService.getFixBuyArticle(paramMap);
		if(fixVO != null){
			
			String chulCode = String.valueOf(fixVO.get("chulCode"));
			EgovMap chulInfo = frontFixNAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				fixVO.put("chulName", chulInfo.get("name"));
				fixVO.put("chulArea", chulInfo.get("chulArea"));
			}else{
				fixVO.put("chulName", "정보없음");
				fixVO.put("chulArea", "정보없음");
			}
			List<EgovMap> fixFileList = frontFixNAucService.getFixFileList(paramMap);
			model.addAttribute("fixFileList", fixFileList);
			
			//중도매인정보
			List<EgovMap> floMemberList = frontLoginService.getFloMember(paramMap);
			
			List<EgovMap> floList = new ArrayList<EgovMap>();
			
			for(EgovMap chulVO : floMemberList){
				String jCode = String.valueOf(chulVO.get("chulCd"));
				EgovMap jInfo = frontFixNAucService.getJCodeToJInfo(jCode);
				floList.add(jInfo);
				
				String code = String.valueOf(paramMap.get("jCode"));
				if(jCode.equals(code)){
					paramMap.put("code", code);
					model.addAttribute("code", code);
				}
			}
			
			model.addAttribute("floList", floList);
			
			/*
			EgovMap bidInfo = frontFixNAucService.getBidBuyInfo(paramMap);
			model.addAttribute("bidInfo", bidInfo);
			*/
			//입찰 가능 시간
			paramMap.put("timeType", "3");
			EgovMap timeVO = frontFixNAucService.getTime(paramMap);
			model.addAttribute("timeVO", timeVO);
			
		}
		
		model.addAttribute("result", fixVO);
		
		
		model.addAttribute("title", "구매");
		model.addAttribute("contentPath", "fixAuc/N/fixBuyView.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	
	@RequestMapping(value="/front/fixNAuc/reqSellList.do")
	public String reqSellList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"01".equals(aucType) &&  !"02".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		
		PaginationInfo paginationInfo = new PaginationInfo();
		
		if(paramMap.get("pageIndex") == null){
			paramMap.put("pageIndex",1);
		}
		
		if(paramMap.get("pageUnit") == null){
			paramMap.put("pageUnit",10);
		}
		
		if(paramMap.get("pageSize") == null){
			paramMap.put("pageSize",5);
		}
		
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd");
		
		if(paramMap.get("searchStartDt") == null){
			// paramMap.put("searchStartDt",format.format(date));
			
			/*
			 * 채성주 [ 2022.01.10 ]
			 * 입고희망일자 
			 * 절화 => 월(2), 수(4), 금(6)
			 */
			Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
	        
	        if(dayOfWeek == 1) {
	        	cal.add(Calendar.DATE, 1);
	        }else if(dayOfWeek == 2) {
	        	cal.add(Calendar.DATE, 2);
	        }else if(dayOfWeek == 3) {
	        	cal.add(Calendar.DATE, 1);
	        }else if(dayOfWeek == 4) {
	        	cal.add(Calendar.DATE, 2);
	        }else if(dayOfWeek == 5) {
	        	cal.add(Calendar.DATE, 1);
	        }else if(dayOfWeek == 6) {
	        	cal.add(Calendar.DATE, 3);
	        }else if(dayOfWeek == 7) {
	        	cal.add(Calendar.DATE, 2);
	        }
	        
			paramMap.put("searchStartDt",format.format(cal.getTime()));
		}
		
		if(paramMap.get("searchEndDt") == null){
			paramMap.put("searchEndDt",format.format(date));
		}
		
		Map<String, Object> param = new HashMap<String, Object>();
	    param.putAll(paramMap);
	    param.put("pageIndex", null);
	    
		String paginationQueryString = MapQuery.urlEncodeUTF8(param);
		
	    paginationInfo.setCurrentPageNo(Integer.parseInt(String.valueOf(paramMap.get("pageIndex"))));
	    paginationInfo.setRecordCountPerPage(Integer.parseInt(String.valueOf(paramMap.get("pageUnit"))));
	    paginationInfo.setPageSize(Integer.parseInt(String.valueOf(paramMap.get("pageSize"))));
	    
	    
	    paramMap.put("firstIndex", paginationInfo.getFirstRecordIndex());
	    paramMap.put("lastIndex", paginationInfo.getLastRecordIndex());
	    paramMap.put("recordCountPerPage", paginationInfo.getRecordCountPerPage());
		
		
		paramMap.put("id", loginVO.get("loginId"));
		paramMap.put("memberId", loginVO.get("memberId"));
		
		//출하자 코드리스트
		List<EgovMap> floMemberList = frontLoginService.getFloMember(paramMap);
		
		List<EgovMap> floList = new ArrayList<EgovMap>();
		List<String> chulCodeArr = new ArrayList<String>();
		
		for(EgovMap chulVO : floMemberList){
			String chulCode = String.valueOf(chulVO.get("chulCd"));
			EgovMap chulInfo = frontFixNAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				floList.add(chulInfo);
				chulCodeArr.add(String.valueOf(chulInfo.get("code")));
			}
		}
		
		model.addAttribute("floList", floList);
		
		paramMap.put("chulCodeArr", chulCodeArr);
		
		List<EgovMap> fixList = frontFixNAucService.getReqChulList(paramMap);
		
		for(EgovMap fixVO : fixList){
			String chulCode = String.valueOf(fixVO.get("chulCode"));
			EgovMap chulInfo = frontFixNAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				fixVO.put("chulName", chulInfo.get("name"));
				fixVO.put("chulArea", chulInfo.get("chulArea"));
			}else{
				fixVO.put("chulName", "정보없음");
				fixVO.put("chulArea", "정보없음");
			}
		}
		
		int totCnt = frontFixNAucService.getReqChulListCnt(paramMap);
		
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("paginationInfo", paginationInfo);
	    model.addAttribute("paginationQueryString", paginationQueryString);
		
		model.addAttribute("resultList", fixList);
		model.addAttribute("resultCnt", totCnt);
		model.addAttribute("paramMap", paramMap);
		
		model.addAttribute("title", "요청");
		model.addAttribute("contentPath", "fixAuc/N/reqSellList.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	
	@RequestMapping(value="/front/fixNAuc/reqSellList.json")
	public String ajaxReqSellList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"01".equals(aucType) &&  !"02".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		
		PaginationInfo paginationInfo = new PaginationInfo();
		
		if(paramMap.get("pageIndex") == null){
			paramMap.put("pageIndex",1);
		}
		
		if(paramMap.get("pageUnit") == null){
			paramMap.put("pageUnit",10);
		}
		
		if(paramMap.get("pageSize") == null){
			paramMap.put("pageSize",5);
		}
		
		Map<String, Object> param = new HashMap<String, Object>();
	    param.putAll(paramMap);
	    param.put("pageIndex", null);
	    
		String paginationQueryString = MapQuery.urlEncodeUTF8(param);
		
	    paginationInfo.setCurrentPageNo(Integer.parseInt(String.valueOf(paramMap.get("pageIndex"))));
	    paginationInfo.setRecordCountPerPage(Integer.parseInt(String.valueOf(paramMap.get("pageUnit"))));
	    paginationInfo.setPageSize(Integer.parseInt(String.valueOf(paramMap.get("pageSize"))));
	    
	    
	    paramMap.put("firstIndex", paginationInfo.getFirstRecordIndex());
	    paramMap.put("lastIndex", paginationInfo.getLastRecordIndex());
	    paramMap.put("recordCountPerPage", paginationInfo.getRecordCountPerPage());
		
		
		paramMap.put("id", loginVO.get("loginId"));
		paramMap.put("memberId", loginVO.get("memberId"));
		
		//출하자 코드리스트
		List<EgovMap> floMemberList = frontLoginService.getFloMember(paramMap);
		
		List<EgovMap> floList = new ArrayList<EgovMap>();
		List<String> chulCodeArr = new ArrayList<String>();
		
		for(EgovMap chulVO : floMemberList){
			String chulCode = String.valueOf(chulVO.get("chulCd"));
			EgovMap chulInfo = frontFixNAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				floList.add(chulInfo);
				chulCodeArr.add(String.valueOf(chulInfo.get("code")));
			}
		}
		
		model.addAttribute("floList", floList);
		
		paramMap.put("chulCodeArr", chulCodeArr);
		
		List<EgovMap> fixList = frontFixNAucService.getReqChulList(paramMap);
		
		for(EgovMap fixVO : fixList){
			String chulCode = String.valueOf(fixVO.get("chulCode"));
			EgovMap chulInfo = frontFixNAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				fixVO.put("chulName", chulInfo.get("name"));
				fixVO.put("chulArea", chulInfo.get("chulArea"));
			}else{
				fixVO.put("chulName", "정보없음");
				fixVO.put("chulArea", "정보없음");
			}
		}
		
		int totCnt = frontFixNAucService.getReqChulListCnt(paramMap);
		
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("paginationInfo", paginationInfo);
	    model.addAttribute("paginationQueryString", paginationQueryString);
		
		model.addAttribute("resultList", fixList);
		model.addAttribute("resultCnt", totCnt);
		model.addAttribute("paramMap", paramMap);
		
		return "jsonView";
	}
	
	
	@RequestMapping(value="/front/fixNAuc/reqSellView.do")
	public String reqSellView(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"01".equals(aucType) &&  !"02".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		paramMap.put("id", loginVO.get("loginId"));
		paramMap.put("memberId", loginVO.get("memberId"));
		
		//출하자 코드리스트
		List<EgovMap> floMemberList = frontLoginService.getFloMember(paramMap);
		
		List<EgovMap> floList = new ArrayList<EgovMap>();
		List<String> chulCodeArr = new ArrayList<String>();
		
		for(EgovMap chulVO : floMemberList){
			String chulCode = String.valueOf(chulVO.get("chulCd"));
			EgovMap chulInfo = frontFixNAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				floList.add(chulInfo);
				chulCodeArr.add(String.valueOf(chulInfo.get("code")));
			}
		}
		
		model.addAttribute("floList", floList);
		
		paramMap.put("chulCodeArr", chulCodeArr);
		
		
		EgovMap fixVO = frontFixNAucService.getReqChulArticle(paramMap);
		if(fixVO != null){
			
			String chulCode = String.valueOf(fixVO.get("chulCode"));
			EgovMap chulInfo = frontFixNAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				fixVO.put("chulName", chulInfo.get("name"));
				fixVO.put("chulArea", chulInfo.get("chulArea"));
			}else{
				fixVO.put("chulName", "정보없음");
				fixVO.put("chulArea", "정보없음");
			}
			
		}
		
		model.addAttribute("result", fixVO);
		
		model.addAttribute("title", "요청");
		model.addAttribute("contentPath", "fixAuc/N/reqSellView.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	@RequestMapping(value="/front/fixNAuc/reqBuyList.do")
	public String reqBuyList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"03".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		
		PaginationInfo paginationInfo = new PaginationInfo();
		
		if(paramMap.get("pageIndex") == null){
			paramMap.put("pageIndex",1);
		}
		
		if(paramMap.get("pageUnit") == null){
			paramMap.put("pageUnit",10);
		}
		
		if(paramMap.get("pageSize") == null){
			paramMap.put("pageSize",5);
		}
		
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd");
		
		
		
		if(paramMap.get("searchStartDt") == null){
			
			/*
			paramMap.put("searchStartDt",format.format(date));
			*/
			
			/*
			 * 고정대 [ 2021.10.18 ]
			 * 입고희망일자 
			 * 절화 => 월(2), 수(4), 금(6)
			 */
			Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
	        
	        if(dayOfWeek == 1) {
	        	cal.add(Calendar.DATE, 1);
	        }else if(dayOfWeek == 2) {
	        	cal.add(Calendar.DATE, 2);
	        }else if(dayOfWeek == 3) {
	        	cal.add(Calendar.DATE, 1);
	        }else if(dayOfWeek == 4) {
	        	cal.add(Calendar.DATE, 2);
	        }else if(dayOfWeek == 5) {
	        	cal.add(Calendar.DATE, 1);
	        }else if(dayOfWeek == 6) {
	        	cal.add(Calendar.DATE, 3);
	        }else if(dayOfWeek == 7) {
	        	cal.add(Calendar.DATE, 2);
	        }
	        
			paramMap.put("searchStartDt",format.format(cal.getTime()));
		}
		
		if(paramMap.get("searchEndDt") == null){
			paramMap.put("searchEndDt",format.format(date));
		}
		
		Map<String, Object> param = new HashMap<String, Object>();
	    param.putAll(paramMap);
	    param.put("pageIndex", null);
	    
		String paginationQueryString = MapQuery.urlEncodeUTF8(param);
		
	    paginationInfo.setCurrentPageNo(Integer.parseInt(String.valueOf(paramMap.get("pageIndex"))));
	    paginationInfo.setRecordCountPerPage(Integer.parseInt(String.valueOf(paramMap.get("pageUnit"))));
	    paginationInfo.setPageSize(Integer.parseInt(String.valueOf(paramMap.get("pageSize"))));
	    
	    
	    paramMap.put("firstIndex", paginationInfo.getFirstRecordIndex());
	    paramMap.put("lastIndex", paginationInfo.getLastRecordIndex());
	    paramMap.put("recordCountPerPage", paginationInfo.getRecordCountPerPage());
		
		
		paramMap.put("id", loginVO.get("loginId"));
		paramMap.put("memberId", loginVO.get("memberId"));
		
		//중도매인 정보
		List<EgovMap> floMemberList = frontLoginService.getFloMember(paramMap);
		
		List<EgovMap> floList = new ArrayList<EgovMap>();
		List<String> jCodeList = new ArrayList<String>();
		
		for(EgovMap chulVO : floMemberList){
			String jCode = String.valueOf(chulVO.get("chulCd"));
			EgovMap jInfo = frontFixNAucService.getJCodeToJInfo(jCode);
			floList.add(jInfo);
			
			if(jInfo.get("bunChk").equals("N")) {
				jCodeList.add(jInfo.get("code").toString());
			}
		}
		model.addAttribute("floList", floList);
		paramMap.put("jCodeList", jCodeList);
		
		List<EgovMap> fixList = frontFixNAucService.getReqList(paramMap);
		
		for(EgovMap fixVO : fixList){
			String chulCode = String.valueOf(fixVO.get("chulCode"));
			EgovMap chulInfo = frontFixNAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				fixVO.put("chulName", chulInfo.get("name"));
				fixVO.put("chulArea", chulInfo.get("chulArea"));
			}else{
				fixVO.put("chulName", "정보없음");
				fixVO.put("chulArea", "정보없음");
			}
		}
		
		int totCnt = frontFixNAucService.getReqListCnt(paramMap);
		
		paginationInfo.setTotalRecordCount(totCnt);
		
		//요청접수시간
		paramMap.put("timeType", "2");
		EgovMap regTimeVO = frontFixNAucService.getTime(paramMap);
		
		model.addAttribute("paginationInfo", paginationInfo);
	    model.addAttribute("paginationQueryString", paginationQueryString);
		
		model.addAttribute("resultList", fixList);
		model.addAttribute("resultCnt", totCnt);
		model.addAttribute("regTimeVO", regTimeVO);
		model.addAttribute("paramMap", paramMap);
		
		
		
		model.addAttribute("title", "요청");
		model.addAttribute("contentPath", "fixAuc/N/reqBuyList.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	
	@RequestMapping(value="/front/fixNAuc/reqBuyList.json")
	public String ajaxReqBuyList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"03".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		
		PaginationInfo paginationInfo = new PaginationInfo();
		
		if(paramMap.get("pageIndex") == null){
			paramMap.put("pageIndex",1);
		}
		
		if(paramMap.get("pageUnit") == null){
			paramMap.put("pageUnit",10);
		}
		
		if(paramMap.get("pageSize") == null){
			paramMap.put("pageSize",5);
		}
		
		Map<String, Object> param = new HashMap<String, Object>();
	    param.putAll(paramMap);
	    param.put("pageIndex", null);
	    
		String paginationQueryString = MapQuery.urlEncodeUTF8(param);
		
	    paginationInfo.setCurrentPageNo(Integer.parseInt(String.valueOf(paramMap.get("pageIndex"))));
	    paginationInfo.setRecordCountPerPage(Integer.parseInt(String.valueOf(paramMap.get("pageUnit"))));
	    paginationInfo.setPageSize(Integer.parseInt(String.valueOf(paramMap.get("pageSize"))));
	    
	    
	    paramMap.put("firstIndex", paginationInfo.getFirstRecordIndex());
	    paramMap.put("lastIndex", paginationInfo.getLastRecordIndex());
	    paramMap.put("recordCountPerPage", paginationInfo.getRecordCountPerPage());
		
		
		paramMap.put("id", loginVO.get("loginId"));
		paramMap.put("memberId", loginVO.get("memberId"));

		
		//중도매인 정보
		List<EgovMap> floMemberList = frontLoginService.getFloMember(paramMap);
		
		List<String> jCodeList = new ArrayList<String>();
		
		for(EgovMap chulVO : floMemberList){
			String jCode = String.valueOf(chulVO.get("chulCd"));
			EgovMap jInfo = frontFixNAucService.getJCodeToJInfo(jCode);
			
			if(jInfo.get("bunChk").equals("N")) {
				jCodeList.add(jInfo.get("code").toString());
			}
		}
		paramMap.put("jCodeList", jCodeList);
		
		
		List<EgovMap> fixList = frontFixNAucService.getReqList(paramMap);
		
		for(EgovMap fixVO : fixList){
			String chulCode = String.valueOf(fixVO.get("chulCode"));
			EgovMap chulInfo = frontFixNAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				fixVO.put("chulName", chulInfo.get("name"));
				fixVO.put("chulArea", chulInfo.get("chulArea"));
			}else{
				fixVO.put("chulName", "정보없음");
				fixVO.put("chulArea", "정보없음");
			}
		}
		
		int totCnt = frontFixNAucService.getReqListCnt(paramMap);
		
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("paginationInfo", paginationInfo);
	    model.addAttribute("paginationQueryString", paginationQueryString);
		
		model.addAttribute("resultList", fixList);
		model.addAttribute("resultCnt", totCnt);
		model.addAttribute("paramMap", paramMap);
		
		
		return "jsonView";
	}
	
	@RequestMapping(value="/front/fixNAuc/reqBuyView.do")
	public String reqBuyView(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"03".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		paramMap.put("id", loginVO.get("loginId"));
		paramMap.put("memberId", loginVO.get("memberId"));
		
		
		// param
		setJCodeList(paramMap);
		EgovMap fixVO = frontFixNAucService.getReqArticle(paramMap);
		if(fixVO != null){
			
			String chulCode = String.valueOf(fixVO.get("chulCode"));
			EgovMap chulInfo = frontFixNAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				fixVO.put("chulName", chulInfo.get("name"));
				fixVO.put("chulArea", chulInfo.get("chulArea"));
			}else{
				fixVO.put("chulName", "정보없음");
				fixVO.put("chulArea", "정보없음");
			}
			
		}
		
		model.addAttribute("result", fixVO);
		
		model.addAttribute("title", "요청");
		model.addAttribute("contentPath", "fixAuc/N/reqBuyView.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	@RequestMapping(value="/front/fixNAuc/reqBuyReg.do")
	public String reqBuyReg(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"03".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		//요청 접수 시간
		paramMap.put("timeType", "2");
		EgovMap timeVO = frontFixNAucService.getTime(paramMap);
		if(timeVO != null){
			if("N".equals(String.valueOf(timeVO.get("timeCheck"))) ){
				model.addAttribute("message", "요청접수시간이 아닙니다.");
				model.addAttribute("returnUrl", "/front/fixNAuc/reqBuyList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/N/result";
			}
		}
		
		paramMap.put("id", loginVO.get("loginId"));
		paramMap.put("memberId", loginVO.get("memberId"));
		
		//중도매인 정보
		List<EgovMap> floMemberList = frontLoginService.getFloMember(paramMap);
		
		List<EgovMap> floList = new ArrayList<EgovMap>();
		
		for(EgovMap chulVO : floMemberList){
			String jCode = String.valueOf(chulVO.get("chulCd"));
			EgovMap jInfo = frontFixNAucService.getJCodeToJInfo(jCode);
			floList.add(jInfo);
		}
		
		model.addAttribute("floList", floList);
		
		//등급 코드 리스트
		paramMap.put("bunChk", "N");
		List<EgovMap> levelList = frontFixNAucService.getLevelList(paramMap);
		model.addAttribute("levelList", levelList);
		
		model.addAttribute("title", "요청");
		model.addAttribute("contentPath", "fixAuc/N/reqBuyReg.jsp");
		
		paramMap.put("searchDefaultBoxCode", "200");
		
		List<EgovMap> boxList = frontFixNAucService.getBoxList(paramMap);
		model.addAttribute("boxList", boxList);
		
		return "/front/layout/fixLayout";
	}
	
	
	
	@RequestMapping(value="/front/fixNAuc/reqBuyRegProc.do")
	public String reqBuyRegProc(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, MultipartHttpServletRequest multiRequest
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"03".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		//요청 접수 시간
		paramMap.put("timeType", "2");
		EgovMap timeVO = frontFixNAucService.getTime(paramMap);
		if(timeVO != null){
			if("N".equals(String.valueOf(timeVO.get("timeCheck"))) ){
				model.addAttribute("message", "요청접수시간이 아닙니다.");
				model.addAttribute("returnUrl", "/front/fixNAuc/reqBuyList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/N/result";
			}
		}
		
		int result = 0;
		try {
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			paramMap.put("code", paramMap.get("jCode"));
			EgovMap checkVO = frontFixNAucService.getFloMemberCheck(paramMap);
			if(checkVO != null){
				EgovMap limitVO = frontFixNAucService.getLimitAmt(paramMap);
				if(limitVO != null){
					int limitAmt = Integer.parseInt(String.valueOf(limitVO.get("limitAmt")));
					int sokCnt = Integer.parseInt(String.valueOf(paramMap.get("sokCnt")));
					int unitPrice = Integer.parseInt(String.valueOf(paramMap.get("unitPrice")));
					System.out.println("limitAmt::::"+limitAmt);
					System.out.println("sokCnt*::::"+(sokCnt*unitPrice));
					if(limitAmt >= (sokCnt*unitPrice)){
						paramMap.put("reqDealSeq", egovReqIdGnrService.getNextIntegerId()); 
						paramMap.put("id", loginVO.get("loginId"));
						paramMap.put("memberId", loginVO.get("memberId"));
						
						result = frontFixNAucService.reqBuyRegProc(paramMap);
						
						//TB_RECORD 상태 변경 이력 map
						Map<String, Object> recordMap = new HashMap<String, Object>();
						
						//TB_RECORD 상태 변경 이력 데이터 put
						recordMap.put("dealSeq", paramMap.get("reqDealSeq"));
						recordMap.put("fixType", "2");
						recordMap.put("bunChk", "N");
						recordMap.put("fixState", "1");
						recordMap.put("id", paramMap.get("id"));
						
						//TB_RECORD 중도매인 최초 등록 insert 진행(1)
						frontFixAucRecordService.fixAucRegRecord(recordMap);
						
						model.addAttribute("message", "정상적으로 저장되었습니다.");
						String returnType = String.valueOf(paramMap.get("returnType"));
						if(returnType != null){
							if("2".equals(returnType)){
								model.addAttribute("returnUrl", "/front/fixNAuc/reqBuyReg.do");
							}else{
								model.addAttribute("returnUrl", "/front/fixNAuc/reqBuyList.do");
							}
						}else{
							model.addAttribute("returnUrl", "/front/fixNAuc/reqBuyList.do");
						}
						
					}else{
						//거래잔액부족
						result = 9;
						model.addAttribute("message", "거래잔액이 부족합니다.");
					}
				}else{
					result = 9;
					model.addAttribute("message", "거래잔액이 부족합니다.");
				}
			}else{
				//파라메터 jCode 변조
				result = 0;
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = 0;
		}
		model.addAttribute("result", result);
		return "/front/fixAuc/N/result";
	}
	
	@RequestMapping(value="/front/fixNAuc/reqBuyUpt.do")
	public String reqBuyUpt(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"03".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		
		//요청 접수 시간
		paramMap.put("timeType", "2");
		EgovMap timeVO = frontFixNAucService.getTime(paramMap);
		if(timeVO != null){
			if("N".equals(String.valueOf(timeVO.get("timeCheck"))) ){
				model.addAttribute("message", "요청접수시간이 아닙니다.");
				model.addAttribute("returnUrl", "/front/fixNAuc/reqBuyList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/N/result";
			}
		}
		
		paramMap.put("id", loginVO.get("loginId"));
		paramMap.put("memberId", loginVO.get("memberId"));
		
		// param
		setJCodeList(paramMap);
		EgovMap fixVO = frontFixNAucService.getReqArticle(paramMap);
		if(fixVO != null){
			
			if(!"1".equals(String.valueOf(fixVO.get("fixState"))) && !"2".equals(String.valueOf(fixVO.get("fixState"))) ){
				model.addAttribute("message", "비정상적인 접근입니다.");
				model.addAttribute("returnUrl", "/front/fixNAuc/reqBuyList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/N/result";
			}
			
			String chulCode = String.valueOf(fixVO.get("chulCode"));
			EgovMap chulInfo = frontFixNAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				fixVO.put("chulName", chulInfo.get("name"));
				fixVO.put("chulArea", chulInfo.get("chulArea"));
			}else{
				fixVO.put("chulName", "정보없음");
				fixVO.put("chulArea", "정보없음");
			}
		}else{
			model.addAttribute("message", "비정상적인 접근입니다.");
			model.addAttribute("returnUrl", "/front/fixNAuc/reqBuyList.do");
			model.addAttribute("result", 1);
			return "/front/fixAuc/N/result";
		}
		
		model.addAttribute("result", fixVO);
		
		//중도매인 정보
		List<EgovMap> floMemberList = frontLoginService.getFloMember(paramMap);
		
		List<EgovMap> floList = new ArrayList<EgovMap>();
		
		for(EgovMap chulVO : floMemberList){
			String jCode = String.valueOf(chulVO.get("chulCd"));
			EgovMap jInfo = frontFixNAucService.getJCodeToJInfo(jCode);
			floList.add(jInfo);
		}
		
		model.addAttribute("floList", floList);
		
		//등급 코드 리스트
		paramMap.put("bunChk", "N");
		List<EgovMap> levelList = frontFixNAucService.getLevelList(paramMap);
		model.addAttribute("levelList", levelList);
		
		
		model.addAttribute("title", "요청");
		model.addAttribute("contentPath", "fixAuc/N/reqBuyUpt.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	@RequestMapping(value="/front/fixNAuc/reqBuyUptProc.do")
	public String reqBuyUptProc(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, MultipartHttpServletRequest multiRequest
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"03".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		//요청 접수 시간
		paramMap.put("timeType", "2");
		EgovMap timeVO = frontFixNAucService.getTime(paramMap);
		if(timeVO != null){
			if("N".equals(String.valueOf(timeVO.get("timeCheck"))) ){
				model.addAttribute("message", "요청접수시간이 아닙니다.");
				model.addAttribute("returnUrl", "/front/fixNAuc/reqBuyList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/N/result";
			}
		}
		
		
		int result = 0;
		
		
		try {
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			// param
			setJCodeList(paramMap);
			EgovMap fixVO = frontFixNAucService.getReqArticle(paramMap);
			if(fixVO != null){
				
				if(!"1".equals(String.valueOf(fixVO.get("fixState"))) && !"2".equals(String.valueOf(fixVO.get("fixState"))) ){
					model.addAttribute("message", "비정상적인 접근입니다.");
					model.addAttribute("returnUrl", "/front/fixNAuc/reqBuyList.do");
					model.addAttribute("result", 1);
					return "/front/fixAuc/N/result";
				}
			}else{
				model.addAttribute("message", "비정상적인 접근입니다.");
				model.addAttribute("returnUrl", "/front/fixNAuc/reqBuyList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/N/result";
			}
			
			
			paramMap.put("code", paramMap.get("jCode"));
			EgovMap checkVO = frontFixNAucService.getFloMemberCheck(paramMap);
			if(checkVO != null){
				EgovMap limitVO = frontFixNAucService.getLimitAmt(paramMap);
				if(limitVO != null){
					int limitAmt = Integer.parseInt(String.valueOf(limitVO.get("limitAmt")));
					int sokCnt = Integer.parseInt(String.valueOf(paramMap.get("sokCnt")));
					int unitPrice = Integer.parseInt(String.valueOf(paramMap.get("unitPrice")));
					int reqSokCnt = Integer.parseInt(String.valueOf(fixVO.get("sokCnt")));
					int reqUnitPrice = Integer.parseInt(String.valueOf(fixVO.get("unitPrice")));
					
					System.out.println("limitAmt::::"+limitAmt);
					System.out.println("sokCnt*::::"+(sokCnt*unitPrice));
					System.out.println("sokCnt*::::"+(reqSokCnt*reqUnitPrice));
					System.out.println("sokCnt*::::"+((sokCnt*unitPrice)-(reqSokCnt*reqUnitPrice)));
					
					if(limitAmt >= ((sokCnt*unitPrice)-(reqSokCnt*reqUnitPrice))){
						
						result = frontFixNAucService.reqBuyUptProc(paramMap);
						
						model.addAttribute("message", "정상적으로 저장되었습니다.");
						model.addAttribute("returnUrl", "/front/fixNAuc/reqBuyList.do");
						
					}else{
						//거래잔액부족
						result = 9;
						model.addAttribute("message", "거래잔액이 부족합니다.");
					}
				}else{
					result = 9;
					model.addAttribute("message", "거래잔액이 부족합니다.");
				}
			}else{
				//파라메터 jCode 변조
				result = 0;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = 0;
		}
		model.addAttribute("result", result);
		return "/front/fixAuc/N/result";
	}
	
	
	@RequestMapping(value="/front/fixNAuc/reqBuyDelProc.do")
	public String reqBuyDelProc(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"03".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		int result = 0;
		
		//TB_RECORD 상태 변경 이력 map
		Map<String, Object> recordMap = new HashMap<String, Object>();
		//TB_RECORD 상태 변경 이력 이전 상태 값
		String preFixState = "";
		
		try {
		paramMap.put("id", loginVO.get("loginId"));
		paramMap.put("memberId", loginVO.get("memberId"));
		
		// param
		setJCodeList(paramMap);
		EgovMap fixVO = frontFixNAucService.getReqArticle(paramMap);
		if(fixVO != null){
			
			if(!"1".equals(String.valueOf(fixVO.get("fixState"))) && !"2".equals(String.valueOf(fixVO.get("fixState"))) ){
				model.addAttribute("message", "비정상적인 접근입니다.");
				model.addAttribute("returnUrl", "/front/fixNAuc/reqBuyList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/N/result";
			}
		}else{
			model.addAttribute("message", "비정상적인 접근입니다.");
			model.addAttribute("returnUrl", "/front/fixNAuc/reqBuyList.do");
			model.addAttribute("result", 1);
			return "/front/fixAuc/N/result";
		}
		
		
			//TB_RECORD 상태 변경 이력 이전 상태 값
			preFixState = frontFixAucRecordService.getPreReqState(String.valueOf(paramMap.get("reqDealSeq")));
		
			result = frontFixNAucService.reqBuyDelProc(paramMap);
			
			//TB_RECORD 상태 변경 이력 데이터 put
			recordMap.put("dealSeq", paramMap.get("reqDealSeq"));
			recordMap.put("fixType", "2");
			recordMap.put("bunChk", "N");
			recordMap.put("preFixState", String.valueOf(preFixState));
			recordMap.put("id", paramMap.get("id"));
			
			//TB_RECORD 경매사 삭제 delete
			frontFixAucRecordService.fixAucRegRecord(recordMap);
			
			model.addAttribute("message", "정상적으로 삭제되었습니다.");
			model.addAttribute("returnUrl", "/front/fixNAuc/reqBuyList.do");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = 0;
		}
		model.addAttribute("result", result);
		return "/front/fixAuc/N/result";
	}
	
	
	@RequestMapping(value="/front/fixNAuc/fixAdmList.do")
	public String fixAdmList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"07".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		try {
			
			PaginationInfo paginationInfo = new PaginationInfo();
			
			if(paramMap.get("pageIndex") == null){
				paramMap.put("pageIndex",1);
			}
			
			if(paramMap.get("pageUnit") == null){
				paramMap.put("pageUnit",10);
			}
			
			if(paramMap.get("pageSize") == null){
				paramMap.put("pageSize",5);
			}
			
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd");
			
			if(paramMap.get("searchStartDt") == null){
				// paramMap.put("searchStartDt",format.format(date));
				
				/*
				 * 채성주 [ 2022.01.10 ]
				 * 출하예정일자 
				 * 절화 => 월(2), 수(4), 금(6)
				 */
				Calendar cal = Calendar.getInstance();
		        cal.setTime(date);
		        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		        
		        if(dayOfWeek == 1) {
		        	cal.add(Calendar.DATE, 1);
		        }else if(dayOfWeek == 2) {
		        	cal.add(Calendar.DATE, 2);
		        }else if(dayOfWeek == 3) {
		        	cal.add(Calendar.DATE, 1);
		        }else if(dayOfWeek == 4) {
		        	cal.add(Calendar.DATE, 2);
		        }else if(dayOfWeek == 5) {
		        	cal.add(Calendar.DATE, 1);
		        }else if(dayOfWeek == 6) {
		        	cal.add(Calendar.DATE, 3);
		        }else if(dayOfWeek == 7) {
		        	cal.add(Calendar.DATE, 2);
		        }
		        
				paramMap.put("searchStartDt",format.format(cal.getTime()));
			}
			
			if(paramMap.get("searchEndDt") == null){
				paramMap.put("searchEndDt",format.format(date));
			}
			
			
			Map<String, Object> seachVO = (Map<String, Object>)request.getSession().getAttribute("fixAdmListSearch");
			if(seachVO != null){
				String referer = request.getHeader("referer");
				if(!referer.contains("List.do")){
					paramMap.put("pageIndex",seachVO.get("pageIndex"));
					paramMap.put("pageUnit",seachVO.get("pageUnit"));
					paramMap.put("pageSize",seachVO.get("pageSize"));
					paramMap.put("searchStartDt",seachVO.get("searchStartDt"));
					paramMap.put("searchEndDt",seachVO.get("searchEndDt"));
					paramMap.put("searchFixState",seachVO.get("searchFixState"));
					paramMap.put("searchChulCode",seachVO.get("searchChulCode"));
					paramMap.put("searchMokCode",seachVO.get("searchMokCode"));
					paramMap.put("searchPumCode",seachVO.get("searchPumCode"));
					paramMap.put("dateRadioChange",seachVO.get("dateRadioChange"));
					/* 정렬 유지 추가 1 (S) - 채성주 [ 2021.11.17 ] */
					paramMap.put("tableOrder",seachVO.get("tableOrder"));
					/* 정렬 유지 추가 1 (E) */
				}
				/* 정렬 유지 수정 1 (S) - 채성주 [ 2021.11.26 ] */
				else if(referer.contains("fixNAuc/fixAdmList.do")) {
					paramMap.put("tableOrder",seachVO.get("tableOrder"));
					if(paramMap.get("history") != null) {
						if(paramMap.get("history").equals("true")) {
							paramMap.put("pageIndex",seachVO.get("pageIndex"));
							paramMap.put("pageUnit",seachVO.get("pageUnit"));
							paramMap.put("pageSize",seachVO.get("pageSize"));
							paramMap.put("searchStartDt",seachVO.get("searchStartDt"));
							paramMap.put("searchEndDt",seachVO.get("searchEndDt"));
							paramMap.put("searchFixState",seachVO.get("searchFixState"));
							paramMap.put("searchChulCode",seachVO.get("searchChulCode"));
							paramMap.put("searchMokCode",seachVO.get("searchMokCode"));
							paramMap.put("searchPumCode",seachVO.get("searchPumCode"));
							paramMap.put("dateRadioChange",seachVO.get("dateRadioChange"));
						}
					}
				}
				/* 정렬 유지 수정 1 (E) */
				
			}
				
			
			Map<String, Object> param = new HashMap<String, Object>();
		    param.putAll(paramMap);
		    param.put("pageIndex", null);
		    
			String paginationQueryString = MapQuery.urlEncodeUTF8(param);
			
		    paginationInfo.setCurrentPageNo(Integer.parseInt(String.valueOf(paramMap.get("pageIndex"))));
		    paginationInfo.setRecordCountPerPage(Integer.parseInt(String.valueOf(paramMap.get("pageUnit"))));
		    paginationInfo.setPageSize(Integer.parseInt(String.valueOf(paramMap.get("pageSize"))));
		    
		    
		    paramMap.put("firstIndex", paginationInfo.getFirstRecordIndex());
		    paramMap.put("lastIndex", paginationInfo.getLastRecordIndex());
		    paramMap.put("recordCountPerPage", paginationInfo.getRecordCountPerPage());
			
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			List<EgovMap> fixList = frontFixNAucService.getFixAdmList(paramMap);
			
			/*
			for(EgovMap fixVO : fixList){
				String chulCode = String.valueOf(fixVO.get("chulCode"));
				EgovMap chulInfo = frontFixNAucService.getChulCodeToChulInfo(chulCode);
				if(chulInfo != null){
					fixVO.put("chulName", chulInfo.get("name"));
					fixVO.put("chulArea", chulInfo.get("chulArea"));
				}else{
					fixVO.put("chulName", "정보없음");
					fixVO.put("chulArea", "정보없음");
				}
			}*/
			
			int totCnt = frontFixNAucService.getFixAdmListCnt(paramMap);
			
			paginationInfo.setTotalRecordCount(totCnt);
			
			
			//입찰 가능 시간
			paramMap.put("timeType", "3");
			EgovMap timeVO = frontFixNAucService.getTime(paramMap);
			
			//판매접수시간
			paramMap.put("timeType", "1");
			EgovMap regTimeVO = frontFixNAucService.getTime(paramMap);
			
			model.addAttribute("paginationInfo", paginationInfo);
		    model.addAttribute("paginationQueryString", paginationQueryString);
			
			model.addAttribute("resultList", fixList);
			model.addAttribute("resultCnt", totCnt);
			model.addAttribute("timeVO", timeVO);
			model.addAttribute("regTimeVO", regTimeVO);
			model.addAttribute("paramMap", paramMap);
			
			searchSessoin(request, "fixAdmListSearch", paramMap);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		model.addAttribute("title", "판매관리");
		model.addAttribute("contentPath", "fixAuc/N/fixAdmList.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	
	/* 정렬 유지 추가 2 (S) - 채성주 [ 2021.11.17 ] */
	@RequestMapping(value="/front/fixNAuc/tableOrder.json")
	public void tableOrder(HttpServletRequest request, @RequestParam Map<String, Object> paramMap) throws Exception {
		String searchName = (String)paramMap.get("searchName");
		
		Map<String, Object> seachVO = (Map<String, Object>)request.getSession().getAttribute(searchName);
		
		if(seachVO != null){
			paramMap.put("pageIndex",seachVO.get("pageIndex"));
			paramMap.put("pageUnit",seachVO.get("pageUnit"));
			paramMap.put("pageSize",seachVO.get("pageSize"));
			paramMap.put("searchStartDt",seachVO.get("searchStartDt"));
			paramMap.put("searchEndDt",seachVO.get("searchEndDt"));
			paramMap.put("searchFixState",seachVO.get("searchFixState"));
			paramMap.put("searchChulCode",seachVO.get("searchChulCode"));
			paramMap.put("searchMokCode",seachVO.get("searchMokCode"));
			paramMap.put("searchPumCode",seachVO.get("searchPumCode"));
			paramMap.put("dateRadioChange",seachVO.get("dateRadioChange"));
			paramMap.put("tableOrder",paramMap.get("tableOrder"));
		}	
		
		searchSessoin(request, searchName, paramMap);
	}
	/* 정렬 유지 추가 2 (E) */
	
	
	@RequestMapping(value="/front/fixNAuc/fixAdmList.json")
	public String ajaxFixAdmList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"07".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		try {
			
			PaginationInfo paginationInfo = new PaginationInfo();
			
			if(paramMap.get("pageIndex") == null){
				paramMap.put("pageIndex",1);
			}
			
			if(paramMap.get("pageUnit") == null){
				paramMap.put("pageUnit",10);
			}
			
			if(paramMap.get("pageSize") == null){
				paramMap.put("pageSize",5);
			}
			
			Map<String, Object> param = new HashMap<String, Object>();
		    param.putAll(paramMap);
		    param.put("pageIndex", null);
		    
			String paginationQueryString = MapQuery.urlEncodeUTF8(param);
			
		    paginationInfo.setCurrentPageNo(Integer.parseInt(String.valueOf(paramMap.get("pageIndex"))));
		    paginationInfo.setRecordCountPerPage(Integer.parseInt(String.valueOf(paramMap.get("pageUnit"))));
		    paginationInfo.setPageSize(Integer.parseInt(String.valueOf(paramMap.get("pageSize"))));
		    
		    
		    paramMap.put("firstIndex", paginationInfo.getFirstRecordIndex());
		    paramMap.put("lastIndex", paginationInfo.getLastRecordIndex());
		    paramMap.put("recordCountPerPage", paginationInfo.getRecordCountPerPage());
			
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			List<EgovMap> fixList = frontFixNAucService.getFixAdmList(paramMap);
			
			for(EgovMap fixVO : fixList){
				String chulCode = String.valueOf(fixVO.get("chulCode"));
				EgovMap chulInfo = frontFixNAucService.getChulCodeToChulInfo(chulCode);
				if(chulInfo != null){
					fixVO.put("chulName", chulInfo.get("name"));
					fixVO.put("chulArea", chulInfo.get("chulArea"));
				}else{
					fixVO.put("chulName", "정보없음");
					fixVO.put("chulArea", "정보없음");
				}
			}
			
			int totCnt = frontFixNAucService.getFixAdmListCnt(paramMap);
			
			paginationInfo.setTotalRecordCount(totCnt);
			
			model.addAttribute("paginationInfo", paginationInfo);
		    model.addAttribute("paginationQueryString", paginationQueryString);
			
			model.addAttribute("resultList", fixList);
			model.addAttribute("resultCnt", totCnt);
			model.addAttribute("paramMap", paramMap);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return "jsonView";
	}
	
	@RequestMapping(value="/front/fixNAuc/fixAdmView.do")
	public String fixAdmView(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"07".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		
		EgovMap fixVO = frontFixNAucService.getFixAdmArticle(paramMap);
		if(fixVO != null){
			
			String chulCode = String.valueOf(fixVO.get("chulCode"));
			EgovMap chulInfo = frontFixNAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				fixVO.put("chulName", chulInfo.get("name"));
				fixVO.put("chulArea", chulInfo.get("chulArea"));
				fixVO.put("phoneNo", chulInfo.get("phoneNo"));
			}else{
				fixVO.put("chulName", "정보없음");
				fixVO.put("chulArea", "정보없음");
				fixVO.put("phoneNo", "정보없음");
			}
			
			List<EgovMap> fixFileList = frontFixNAucService.getFixFileList(paramMap);
			model.addAttribute("fixFileList", fixFileList);
			
			String dealType = String.valueOf(fixVO.get("dealType"));
			int remainSokCnt = Integer.parseInt(String.valueOf(fixVO.get("sokCnt")));
			
			List<EgovMap> bidList = new ArrayList<EgovMap>();
			
			// 정가 : 등록시간순 / 최저가 : 금액순, 등록시간순 / 희망가: 금액순, 등록시간순
			if("F".equals(dealType)){
				//정가
				//입찰정보 가져오기
				paramMap.put("bidOrder", "F");
				bidList = frontFixNAucService.getBidList(paramMap);
				
				for(EgovMap bidVO : bidList){
					paramMap.put("bidSeq",bidVO.get("bidSeq"));
					int bidSokCnt = Integer.parseInt(String.valueOf(bidVO.get("bidSokCnt")));
					int preRemainSokCnt = remainSokCnt;
					
					remainSokCnt = (remainSokCnt - bidSokCnt);
					
					if(remainSokCnt>=0){
						//낙찰 : 2
						bidVO.put("nakSokCnt",bidSokCnt);
						bidVO.put("nakState", "2");
						
					}else if(remainSokCnt<0 && preRemainSokCnt>0){
						//부분 낙찰 : 4
						bidVO.put("nakSokCnt",preRemainSokCnt);
						bidVO.put("nakState", "4");
					}else{
						//패찰 : 3
						bidVO.put("nakSokCnt",0);
						bidVO.put("nakState", "3");
						
					}
					
				}
				
			}else{
				//최저가, 희망가
				paramMap.put("bidOrder", "L");
				
				bidList = frontFixNAucService.getBidList(paramMap);
				
				for(EgovMap bidVO : bidList){
					paramMap.put("bidSeq",bidVO.get("bidSeq"));
					int bidSokCnt = Integer.parseInt(String.valueOf(bidVO.get("bidSokCnt")));
					int preRemainSokCnt = remainSokCnt;
					
					remainSokCnt = (remainSokCnt - bidSokCnt);
					
					if(remainSokCnt>=0){
						//낙찰 : 2
						bidVO.put("nakSokCnt",bidSokCnt);
						bidVO.put("nakState", "2");
						
					}else if(remainSokCnt<0 && preRemainSokCnt>0){
						//부분 낙찰 : 4
						bidVO.put("nakSokCnt",preRemainSokCnt);
						bidVO.put("nakState", "4");
					}else{
						//패찰 : 3
						bidVO.put("nakSokCnt",0);
						bidVO.put("nakState", "3");
						
					}
					
				}
				
			}
			
			List<EgovMap> bidCancelList = frontFixNAucService.getBidCancelList(paramMap);
			
			for(EgovMap bidVO : bidCancelList){
				bidVO.put("nakSokCnt",0);
				bidVO.put("nakState", "5");
				bidList.add(bidVO);
			}
			
			model.addAttribute("bidList", bidList);
			
			
			//TB_RECORD 상태 변경 이력  이력 리스트
			paramMap.put("fixType", "1");
			paramMap.put("bunChk", "N");
			paramMap.put("dealSeq", paramMap.get("fixDealSeq"));
			List<EgovMap> recordList = frontFixAucRecordService.getFixRecordList(paramMap);
			model.addAttribute("recordList", recordList);
	
		}
		
		model.addAttribute("result", fixVO);
		
		model.addAttribute("title", "판매관리");
		model.addAttribute("contentPath", "fixAuc/N/fixAdmView.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	@RequestMapping(value="/front/fixNAuc/fixAdmReg.do")
	public String fixAdmReg(HttpServletRequest request
			, HttpServletResponse response
			, @RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"07".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		paramMap.put("id", loginVO.get("loginId"));
		paramMap.put("memberId", loginVO.get("memberId"));
		
		//등급 코드 리스트
		paramMap.put("bunChk", "N");
		List<EgovMap> levelList = frontFixNAucService.getLevelList(paramMap);
		model.addAttribute("levelList", levelList);
		
		
		model.addAttribute("title", "판매관리");
		model.addAttribute("contentPath", "fixAuc/N/fixAdmReg.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	
	@RequestMapping(value="/front/fixNAuc/fixAdmRegProc.do")
	public String fixAdmRegProc(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, MultipartHttpServletRequest multiRequest
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"07".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		int result = 0;
		try {
			
			paramMap.put("fixDealSeq", egovFixIdGnrService.getNextIntegerId()); 
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			paramMap.put("code",paramMap.get("chulCode"));
			
			EgovMap chulVO = frontFixNAucService.getCodeToLoginId(paramMap);
			
			if(chulVO != null){
				paramMap.put("chulId", chulVO.get("loginId"));
			}
			
			
			result = frontFixNAucService.fixAdmRegProc(paramMap);
			
			
			//TB_RECORD 상태 변경 이력  Map
			Map<String, Object> recordMap = new HashMap<String, Object>();
			
			//TB_RECORD 상태 변경 이력 데이터 put
			recordMap.put("dealSeq", paramMap.get("fixDealSeq"));
			recordMap.put("fixType", "1");
			recordMap.put("bunChk", "N");
			recordMap.put("fixState", "3");
			recordMap.put("id", paramMap.get("id"));
			
			//TB_RECORD 상태 변경 이력  - 경매사 최초 등록 (3)
			frontFixAucRecordService.fixAucRegRecord(recordMap);
			
			//파일
			List<FileVO> uploadFileList = uploadThumbFile(model, multiRequest);
			
			if ((uploadFileList != null) && (uploadFileList.size() > 0)){
				Map<String, Object> fileMap = new HashMap<String, Object>();
		      
				for (int i = 0; i < uploadFileList.size(); i++){
		    	  
		    	  FileVO fileVO = uploadFileList.get(i);
		    	  fileMap.put("fileSeq",((FileVO)uploadFileList.get(i)).getFileSn());
		    	  fileMap.put("fixDealSeq", paramMap.get("fixDealSeq"));
		    	  fileMap.put("streFilePath", fileVO.getFileStreCours());
		    	  fileMap.put("streFileNm", fileVO.getOrignlFileNm()+"."+fileVO.getStreFileNm());
		    	  fileMap.put("orignlFileNm", fileVO.getOrignlFileNm());
		    	  
		    	  fileMap.put("fileExtsn", fileVO.getFileExtsn());
		    	  fileMap.put("fileCn", fileVO.getFileCn());
		    	  fileMap.put("fileSize", fileVO.getFileMg());
		    	  fileMap.put("id", loginVO.get("loginId"));
		    	  
		    	  //썸네일 이후 수정
		    	  fileMap.put("thumbStreFileNm", fileVO.getStreFileNm());
		    	  System.out.println("등록파일 SN : "+ String.valueOf(fileMap.get("fileSeq")));
		    	  frontFixNAucService.fixSellFileProc(fileMap);
		    	  
		    	  
		    	  if (fileVO != null) {
				      File image = new File(fileVO.getFileStreCours() + fileVO.getStreFileNm());
				      if (image.exists()) {
				        try {
				          Thumbnails.of(new File[] { image }).size(600, 600).outputQuality(1.0F).toFile(image);
				        } catch (Exception ex) {
				          ex.fillInStackTrace();
				        }
				      }
				    }
		    	  
		       }
		    }
			
			model.addAttribute("message", "정상적으로 저장되었습니다.");
			model.addAttribute("returnUrl", "/front/fixNAuc/fixAdmList.do");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = 0;
		}
		model.addAttribute("result", result);
		return "/front/fixAuc/N/result";
	}
	
	@RequestMapping(value="/front/fixNAuc/fixAdmUpt.do")
	public String fixAdmUpt(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"07".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		paramMap.put("id", loginVO.get("loginId"));
		paramMap.put("memberId", loginVO.get("memberId"));
		
		
		EgovMap fixVO = frontFixNAucService.getFixAdmArticle(paramMap);
		if(fixVO != null){
			
			if(!"1".equals(String.valueOf(fixVO.get("fixState"))) && !"2".equals(String.valueOf(fixVO.get("fixState"))) ){
				model.addAttribute("message", "비정상적인 접근입니다.");
				model.addAttribute("returnUrl", "/front/fixNAuc/fixAdmList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/N/result";
			}
			
			String chulCode = String.valueOf(fixVO.get("chulCode"));
			EgovMap chulInfo = frontFixNAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				fixVO.put("chulName", chulInfo.get("name"));
				fixVO.put("chulArea", chulInfo.get("chulArea"));
			}else{
				fixVO.put("chulName", "정보없음");
				fixVO.put("chulArea", "정보없음");
			}
			List<EgovMap> fixFileList = frontFixNAucService.getFixFileList(paramMap);
			model.addAttribute("fixFileList", fixFileList);
			for(EgovMap file : fixFileList ){
				String fileSeq = String.valueOf(file.get("fileSeq"));
				model.addAttribute("file"+fileSeq, file);
			}
		}
		
		model.addAttribute("result", fixVO);
		
		
		//출하자 코드리스트
		List<EgovMap> floMemberList = frontLoginService.getFloMember(paramMap);
		
		List<EgovMap> floList = new ArrayList<EgovMap>();
		
		for(EgovMap chulVO : floMemberList){
			String chulCode = String.valueOf(chulVO.get("chulCd"));
			EgovMap chulInfo = frontFixNAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				floList.add(chulInfo);
			}
		}
		
		model.addAttribute("floList", floList);
		
		//등급 코드 리스트
		paramMap.put("bunChk", "N");
		List<EgovMap> levelList = frontFixNAucService.getLevelList(paramMap);
		model.addAttribute("levelList", levelList);
		
		
		model.addAttribute("title", "판매");
		model.addAttribute("contentPath", "fixAuc/N/fixAdmUpt.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	@RequestMapping(value="/front/fixNAuc/fixAdmUptProc.do")
	public String fixAdmUptProc(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, MultipartHttpServletRequest multiRequest
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"07".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		int result = 0;
		
		
		try {
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			paramMap.put("code",paramMap.get("chulCode"));
			
			EgovMap chulVO = frontFixNAucService.getCodeToLoginId(paramMap);
			
			if(chulVO != null){
				paramMap.put("chulId", chulVO.get("loginId"));
			}
			
			EgovMap fixVO = frontFixNAucService.getFixAdmArticle(paramMap);
			if(fixVO != null){
				
				if(!"1".equals(String.valueOf(fixVO.get("fixState"))) && !"2".equals(String.valueOf(fixVO.get("fixState"))) ){
					model.addAttribute("message", "비정상적인 접근입니다.");
					model.addAttribute("returnUrl", "/front/fixNAuc/fixAdmList.do");
					model.addAttribute("result", 1);
					return "/front/fixAuc/N/result";
				}
			}	 
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			//TB_RECORD 상태 변경 이력 map 
			Map<String, Object> recordMap = new HashMap<String, Object>();
			String preFixState = frontFixAucRecordService.getPreFixState(String.valueOf(paramMap.get("fixDealSeq")));
						
			result = frontFixNAucService.fixAdmUptProc(paramMap);
			
			
			//TB_RECORD 상태 변경 이력 데이터 put
			recordMap.put("dealSeq", paramMap.get("fixDealSeq"));
			recordMap.put("fixType", "1");
			recordMap.put("bunChk", "N");
			recordMap.put("preFixState", preFixState);
			recordMap.put("fixState", "3");
			recordMap.put("id", paramMap.get("id"));
			
			//TB_RECORD 경매사 수정 (1,3->3)
			frontFixAucRecordService.fixAucRegRecord(recordMap);
			
			
			//파일
			
			String[] deleteAtchFile = request.getParameterValues("deleteAtchFile");
			if(deleteAtchFile != null && deleteAtchFile.length > 0){
				for (int i = 0; i < deleteAtchFile.length; i++){
					paramMap.put("fileSeq",deleteAtchFile[i]);
			        frontFixNAucService.deleteFileOne(paramMap);
			      }
			}
			
			List<FileVO> uploadFileList = uploadThumbFile(model, multiRequest);
			
			if ((uploadFileList != null) && (uploadFileList.size() > 0)){
				Map<String, Object> fileMap = new HashMap<String, Object>();
		      
				for (int i = 0; i < uploadFileList.size(); i++){
					
		    	  FileVO fileVO = uploadFileList.get(i);
		    	  fileMap.put("fileSeq", ((FileVO)uploadFileList.get(i)).getFileSn());
		    	  fileMap.put("fixDealSeq", paramMap.get("fixDealSeq"));
		    	  fileMap.put("streFilePath", fileVO.getFileStreCours());
		    	  fileMap.put("streFileNm", fileVO.getOrignlFileNm()+"."+fileVO.getStreFileNm());
		    	  fileMap.put("orignlFileNm", fileVO.getOrignlFileNm());
		    	  
		    	  fileMap.put("fileExtsn", fileVO.getFileExtsn());
		    	  fileMap.put("fileCn", fileVO.getFileCn());
		    	  fileMap.put("fileSize", fileVO.getFileMg());
		    	  fileMap.put("id", loginVO.get("loginId"));
		    	  
		    	  //썸네일 이후 수정
		    	  fileMap.put("thumbStreFileNm", fileVO.getStreFileNm());
		    	  
		    	  
		    	  System.out.println("수정파일 SN : "+ String.valueOf(fileMap.get("fileSeq")));
		    	  
		    	  EgovMap fileCheckVO = frontFixNAucService.getFileOne(fileMap);
		    	  if(fileCheckVO != null){
		    		  frontFixNAucService.deleteFileOne(fileMap);
		    	  }
		    	  
		    	  frontFixNAucService.fixSellFileProc(fileMap);
		    	  
		    	  
		    	  if (fileVO != null) {
				      File image = new File(fileVO.getFileStreCours() + fileVO.getStreFileNm());
				      if (image.exists()) {
				        try {
				          Thumbnails.of(new File[] { image }).size(600, 600).outputQuality(1.0F).toFile(image);
				        } catch (Exception ex) {
				          ex.fillInStackTrace();
				        }
				      }
				    }
		    	  
		       }
		    }
			
			model.addAttribute("message", "정상적으로 저장되었습니다.");
			model.addAttribute("returnUrl", "/front/fixNAuc/fixAdmList.do");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = 0;
		}
		model.addAttribute("result", result);
		return "/front/fixAuc/N/result";
	}
	
	
	@RequestMapping(value="/front/fixNAuc/fixAdmDelProc.do")
	public String fixAdmDelProc(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"07".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		int result = 0;
		
		try {
		paramMap.put("id", loginVO.get("loginId"));
		paramMap.put("memberId", loginVO.get("memberId"));
		
		EgovMap fixVO = frontFixNAucService.getFixAdmArticle(paramMap);
		if(fixVO != null){
			
			if(!"1".equals(String.valueOf(fixVO.get("fixState"))) && !"2".equals(String.valueOf(fixVO.get("fixState"))) ){
				model.addAttribute("message", "비정상적인 접근입니다.");
				model.addAttribute("returnUrl", "/front/fixNAuc/fixAdmList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/N/result";
			}
		}
		
			//삭제 fixDealSeq
			String fixDealSeq = String.valueOf(paramMap.get("fixDealSeq"));
		
			//TB_RECORD 상태 변경 이력 이전 상태 값
			String preFixState = frontFixAucRecordService.getPreFixState(fixDealSeq);
			
			result = frontFixNAucService.fixAdmDelProc(paramMap);
			
			if(result == 1){
				frontFixNAucService.fixSellFileDelProc(paramMap);
			}
			
			//TB_RECORD 상태 변경 이력 map 
			Map<String, Object> recordMap = new HashMap<String, Object>();
			
			//TB_RECORD 상태 변경 이력 데이터 put
			recordMap.put("dealSeq", fixDealSeq);
			recordMap.put("fixType", "1");
			recordMap.put("bunChk", "N");
			recordMap.put("preFixState", preFixState);
			recordMap.put("id", paramMap.get("id"));
			
			//TB_RECORD 경매사 삭제 delete
			frontFixAucRecordService.fixAucRegRecord(recordMap);
			
			
			model.addAttribute("message", "정상적으로 삭제되었습니다.");
			model.addAttribute("returnUrl", "/front/fixNAuc/fixAdmList.do");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = 0;
		}
		model.addAttribute("result", result);
		return "/front/fixAuc/N/result";
	}
	
	
	@RequestMapping(value="/front/fixNAuc/reqAdmList.do")
	public String reqAdmList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"07".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		
		try {
			
			PaginationInfo paginationInfo = new PaginationInfo();
			
			if(paramMap.get("pageIndex") == null){
				paramMap.put("pageIndex",1);
			}
			
			if(paramMap.get("pageUnit") == null){
				paramMap.put("pageUnit",10);
			}
			
			if(paramMap.get("pageSize") == null){
				paramMap.put("pageSize",5);
			}
			
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd");
			
			if(paramMap.get("searchStartDt") == null){
				
				/*
				paramMap.put("searchStartDt",format.format(date));
				*/
				
				/*
				 * 고정대 [ 2021.10.18 ]
				 * 입고희망일자 
				 * 절화 => 월(2), 수(4), 금(6)
				 */
				Calendar cal = Calendar.getInstance();
		        cal.setTime(date);
		        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		        
		        if(dayOfWeek == 1) {
		        	cal.add(Calendar.DATE, 1);
		        }else if(dayOfWeek == 2) {
		        	cal.add(Calendar.DATE, 2);
		        }else if(dayOfWeek == 3) {
		        	cal.add(Calendar.DATE, 1);
		        }else if(dayOfWeek == 4) {
		        	cal.add(Calendar.DATE, 2);
		        }else if(dayOfWeek == 5) {
		        	cal.add(Calendar.DATE, 1);
		        }else if(dayOfWeek == 6) {
		        	cal.add(Calendar.DATE, 3);
		        }else if(dayOfWeek == 7) {
		        	cal.add(Calendar.DATE, 2);
		        }
		        
				paramMap.put("searchStartDt",format.format(cal.getTime()));
			}
			
			if(paramMap.get("searchEndDt") == null){
				paramMap.put("searchEndDt",format.format(date));
			}
			
			Map<String, Object> seachVO = (Map<String, Object>)request.getSession().getAttribute("reqAdmListSearch");
			if(seachVO != null){
				String referer = request.getHeader("referer");
				if(!referer.contains("List.do")){
					paramMap.put("pageIndex",seachVO.get("pageIndex"));
					paramMap.put("pageUnit",seachVO.get("pageUnit"));
					paramMap.put("pageSize",seachVO.get("pageSize"));
					paramMap.put("searchStartDt",seachVO.get("searchStartDt"));
					paramMap.put("searchEndDt",seachVO.get("searchEndDt"));
					paramMap.put("searchFixState",seachVO.get("searchFixState"));
					paramMap.put("searchChulCode",seachVO.get("searchChulCode"));
					paramMap.put("searchMokCode",seachVO.get("searchMokCode"));
					paramMap.put("searchPumCode",seachVO.get("searchPumCode"));
					paramMap.put("dateRadioChange",seachVO.get("dateRadioChange"));
					/* 정렬 유지 추가 3 (S) - 채성주 [ 2021.11.17 ]*/
					paramMap.put("tableOrder",seachVO.get("tableOrder"));
					/* 정렬 유지 추가 3 (E) */
				}
				/* 정렬 유지 수정 2 (S) - 채성주 [ 2021.11.26 ] */
				else if(referer.contains("fixNAuc/reqAdmList.do")) {
					paramMap.put("tableOrder",seachVO.get("tableOrder"));
					if(paramMap.get("history") != null) {
						if(paramMap.get("history").equals("true")) {
							paramMap.put("pageIndex",seachVO.get("pageIndex"));
							paramMap.put("pageUnit",seachVO.get("pageUnit"));
							paramMap.put("pageSize",seachVO.get("pageSize"));
							paramMap.put("searchStartDt",seachVO.get("searchStartDt"));
							paramMap.put("searchEndDt",seachVO.get("searchEndDt"));
							paramMap.put("searchFixState",seachVO.get("searchFixState"));
							paramMap.put("searchChulCode",seachVO.get("searchChulCode"));
							paramMap.put("searchMokCode",seachVO.get("searchMokCode"));
							paramMap.put("searchPumCode",seachVO.get("searchPumCode"));
							paramMap.put("dateRadioChange",seachVO.get("dateRadioChange"));
						}
					}
				}
				/* 정렬 유지 수정 2 (E) */
			}
			
			Map<String, Object> param = new HashMap<String, Object>();
		    param.putAll(paramMap);
		    param.put("pageIndex", null);
		    
			String paginationQueryString = MapQuery.urlEncodeUTF8(param);
			
		    paginationInfo.setCurrentPageNo(Integer.parseInt(String.valueOf(paramMap.get("pageIndex"))));
		    paginationInfo.setRecordCountPerPage(Integer.parseInt(String.valueOf(paramMap.get("pageUnit"))));
		    paginationInfo.setPageSize(Integer.parseInt(String.valueOf(paramMap.get("pageSize"))));
		    
		    
		    paramMap.put("firstIndex", paginationInfo.getFirstRecordIndex());
		    paramMap.put("lastIndex", paginationInfo.getLastRecordIndex());
		    paramMap.put("recordCountPerPage", paginationInfo.getRecordCountPerPage());
			
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			List<EgovMap> fixList = frontFixNAucService.getReqAdmList(paramMap);
			
			/*
			for(EgovMap fixVO : fixList){
				String chulCode = String.valueOf(fixVO.get("chulCode"));
				EgovMap chulInfo = frontFixNAucService.getChulCodeToChulInfo(chulCode);
				if(chulInfo != null){
					fixVO.put("chulName", chulInfo.get("name"));
					fixVO.put("chulArea", chulInfo.get("chulArea"));
				}else{
					fixVO.put("chulName", "정보없음");
					fixVO.put("chulArea", "정보없음");
				}
			}*/
			
			int totCnt = frontFixNAucService.getReqAdmListCnt(paramMap);
			
			paginationInfo.setTotalRecordCount(totCnt);
			
			
			//입찰 가능 시간
			paramMap.put("timeType", "3");
			EgovMap timeVO = frontFixNAucService.getTime(paramMap);
			
			//요청접수시간
			paramMap.put("timeType", "2");
			EgovMap regTimeVO = frontFixNAucService.getTime(paramMap);
			
			model.addAttribute("paginationInfo", paginationInfo);
		    model.addAttribute("paginationQueryString", paginationQueryString);
			
			model.addAttribute("resultList", fixList);
			model.addAttribute("resultCnt", totCnt);
			model.addAttribute("timeVO", timeVO);
			model.addAttribute("regTimeVO", regTimeVO);
			model.addAttribute("paramMap", paramMap);
			
			searchSessoin(request, "reqAdmListSearch", paramMap);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		model.addAttribute("title", "요청관리");
		model.addAttribute("contentPath", "fixAuc/N/reqAdmList.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	
	@RequestMapping(value="/front/fixNAuc/reqAdmList.json")
	public String ajaxReqAdmList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"07".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		
		try {
			
			PaginationInfo paginationInfo = new PaginationInfo();
			
			if(paramMap.get("pageIndex") == null){
				paramMap.put("pageIndex",1);
			}
			
			if(paramMap.get("pageUnit") == null){
				paramMap.put("pageUnit",10);
			}
			
			if(paramMap.get("pageSize") == null){
				paramMap.put("pageSize",5);
			}
			
			Map<String, Object> param = new HashMap<String, Object>();
		    param.putAll(paramMap);
		    param.put("pageIndex", null);
		    
			String paginationQueryString = MapQuery.urlEncodeUTF8(param);
			
		    paginationInfo.setCurrentPageNo(Integer.parseInt(String.valueOf(paramMap.get("pageIndex"))));
		    paginationInfo.setRecordCountPerPage(Integer.parseInt(String.valueOf(paramMap.get("pageUnit"))));
		    paginationInfo.setPageSize(Integer.parseInt(String.valueOf(paramMap.get("pageSize"))));
		    
		    
		    paramMap.put("firstIndex", paginationInfo.getFirstRecordIndex());
		    paramMap.put("lastIndex", paginationInfo.getLastRecordIndex());
		    paramMap.put("recordCountPerPage", paginationInfo.getRecordCountPerPage());
			
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			List<EgovMap> fixList = frontFixNAucService.getReqAdmList(paramMap);
			
			for(EgovMap fixVO : fixList){
				String chulCode = String.valueOf(fixVO.get("chulCode"));
				EgovMap chulInfo = frontFixNAucService.getChulCodeToChulInfo(chulCode);
				if(chulInfo != null){
					fixVO.put("chulName", chulInfo.get("name"));
					fixVO.put("chulArea", chulInfo.get("chulArea"));
				}else{
					fixVO.put("chulName", "정보없음");
					fixVO.put("chulArea", "정보없음");
				}
			}
			
			int totCnt = frontFixNAucService.getReqAdmListCnt(paramMap);
			
			paginationInfo.setTotalRecordCount(totCnt);
			
			model.addAttribute("paginationInfo", paginationInfo);
		    model.addAttribute("paginationQueryString", paginationQueryString);
			
			model.addAttribute("resultList", fixList);
			model.addAttribute("resultCnt", totCnt);
			model.addAttribute("paramMap", paramMap);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return "jsonView";
	}
	
	
	@RequestMapping(value="/front/fixNAuc/reqAdmView.do")
	public String reqAdmView(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"07".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		EgovMap fixVO = frontFixNAucService.getReqAdmArticle(paramMap);
		if(fixVO != null){
			
			String chulCode = String.valueOf(fixVO.get("chulCode"));
			EgovMap chulInfo = frontFixNAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				fixVO.put("chulName", chulInfo.get("name"));
				fixVO.put("chulArea", chulInfo.get("chulArea"));
				fixVO.put("phoneNo", chulInfo.get("phoneNo"));
			}else{
				fixVO.put("chulName", "정보없음");
				fixVO.put("chulArea", "정보없음");
				fixVO.put("phoneNo", "정보없음");
			}
			
			//TB_RECORD 상태 변경 이력 리스트
			paramMap.put("fixType", "2");
			paramMap.put("bunChk", "N");
			paramMap.put("dealSeq", paramMap.get("reqDealSeq"));
			List<EgovMap> recordList = frontFixAucRecordService.getFixRecordList(paramMap);
			model.addAttribute("recordList", recordList);
			
		}
		
		model.addAttribute("result", fixVO);
		
		
		model.addAttribute("title", "요청관리");
		model.addAttribute("contentPath", "fixAuc/N/reqAdmView.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	@RequestMapping(value="/front/fixNAuc/reqAdmReg.do")
	public String reqAdmReg(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"07".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		paramMap.put("id", loginVO.get("loginId"));
		paramMap.put("memberId", loginVO.get("memberId"));
		
		
		//등급 코드 리스트
		paramMap.put("bunChk", "N");
		List<EgovMap> levelList = frontFixNAucService.getLevelList(paramMap);
		model.addAttribute("levelList", levelList);

		// box
		// paramMap.put("searchDefaultBoxCode", 200);
		List<EgovMap> boxList = frontFixNAucService.getBoxList(paramMap);
		model.addAttribute("boxList", boxList);
		
		model.addAttribute("title", "요청관리");
		model.addAttribute("contentPath", "fixAuc/N/reqAdmReg.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	
	@RequestMapping(value="/front/fixNAuc/reqAdmRegProc.do")
	public String reqAdmRegProc(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, MultipartHttpServletRequest multiRequest
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"07".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		int result = 0;
		try {
			
			paramMap.put("code", paramMap.get("jCode"));
			
			EgovMap limitVO = frontFixNAucService.getLimitAmt(paramMap);
			if(limitVO != null){
				int limitAmt = Integer.parseInt(String.valueOf(limitVO.get("limitAmt")));
				int sokCnt = Integer.parseInt(String.valueOf(paramMap.get("sokCnt")));
				int unitPrice = Integer.parseInt(String.valueOf(paramMap.get("unitPrice")));
				if(limitAmt >= (sokCnt*unitPrice)){
					paramMap.put("reqDealSeq", egovReqIdGnrService.getNextIntegerId()); 
					paramMap.put("id", loginVO.get("loginId"));
					paramMap.put("memberId", loginVO.get("memberId"));
					
					EgovMap chulCheck = frontFixNAucService.getCodeToLoginId(paramMap);
					
					if(chulCheck != null){
						paramMap.put("jId", chulCheck.get("loginId"));
					}
					
					result = frontFixNAucService.reqAdmRegProc(paramMap);
					
					//TB_RECORD 상태 변경 이력 Map
					Map<String, Object> recordMap = new HashMap<String, Object>();
					
					//TB_RECORD 상태 변경 이력 데이터 put
					recordMap.put("dealSeq", paramMap.get("reqDealSeq"));
					recordMap.put("fixType", "2");
					recordMap.put("bunChk", "N");
					recordMap.put("fixState", "3");
					recordMap.put("id", paramMap.get("id"));
					
					//TB_RECORD 경매사 최초 등록 (3)
					frontFixAucRecordService.fixAucRegRecord(recordMap);
					
					model.addAttribute("message", "정상적으로 저장되었습니다.");
					model.addAttribute("returnUrl", "/front/fixNAuc/reqAdmList.do");
					
				}else{
					//거래잔액부족
					result = 9;
					model.addAttribute("message", "거래잔액이 부족합니다.");
				}
			}else{
				result = 9;
				model.addAttribute("message", "거래잔액이 부족합니다.");
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = 0;
		}
		model.addAttribute("result", result);
		return "/front/fixAuc/N/result";
	}
	
	
	@RequestMapping(value="/front/fixNAuc/reqAdmRegProc.json")
	public String reqAdmRegProcAjax(HttpServletRequest request
			, HttpServletResponse response
			, @RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"07".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		int result = 0;
		try {
			
			paramMap.put("code", paramMap.get("jCode"));
			
			EgovMap limitVO = frontFixNAucService.getLimitAmt(paramMap);
			if(limitVO != null){
				int limitAmt = Integer.parseInt(String.valueOf(limitVO.get("limitAmt")));
				int sokCnt = Integer.parseInt(String.valueOf(paramMap.get("sokCnt")));
				int unitPrice = Integer.parseInt(String.valueOf(paramMap.get("unitPrice")));
				if(limitAmt >= (sokCnt*unitPrice)){
					paramMap.put("reqDealSeq", egovReqIdGnrService.getNextIntegerId()); 
					paramMap.put("id", loginVO.get("loginId"));
					paramMap.put("memberId", loginVO.get("memberId"));
					
					EgovMap chulCheck = frontFixNAucService.getCodeToLoginId(paramMap);
					
					if(chulCheck != null){
						paramMap.put("jId", chulCheck.get("loginId"));
					}
					
					result = frontFixNAucService.reqAdmRegProc(paramMap);
					
					//TB_RECORD 상태 변경 이력 Map
					Map<String, Object> recordMap = new HashMap<String, Object>();
					
					//TB_RECORD 상태 변경 이력 데이터 put
					recordMap.put("dealSeq", paramMap.get("reqDealSeq"));
					recordMap.put("fixType", "2");
					recordMap.put("bunChk", "N");
					recordMap.put("fixState", "3");
					recordMap.put("id", paramMap.get("id"));
					
					//TB_RECORD 경매사 최초 등록 insert 진행(3)
					frontFixAucRecordService.fixAucRegRecord(recordMap);
					
					
					model.addAttribute("message", "정상적으로 저장되었습니다.");
					model.addAttribute("returnUrl", "/front/fixNAuc/reqAdmList.do");
					
				}else{
					//거래잔액부족
					result = 9;
					model.addAttribute("message", "거래잔액이 부족합니다.");
				}
			}else{
				result = 9;
				model.addAttribute("message", "거래잔액이 부족합니다.");
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = 0;
		}
		model.addAttribute("result", result);
		return "jsonView";
	}
	
	
	@RequestMapping(value="/front/fixNAuc/reqAdmUpt.do")
	public String reqAdmUpt(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"07".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		
		paramMap.put("id", loginVO.get("loginId"));
		paramMap.put("memberId", loginVO.get("memberId"));
		
		EgovMap fixVO = frontFixNAucService.getReqAdmArticle(paramMap);
		if(fixVO != null){
			/*
			if(!"1".equals(String.valueOf(fixVO.get("fixState"))) && !"2".equals(String.valueOf(fixVO.get("fixState"))) ){
				model.addAttribute("message", "비정상적인 접근입니다.");
				model.addAttribute("returnUrl", "/front/fixNAuc/reqAdmList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/N/result";
			}*/
			
			String chulCode = String.valueOf(fixVO.get("chulCode"));
			EgovMap chulInfo = frontFixNAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				fixVO.put("chulName", chulInfo.get("name"));
				fixVO.put("chulArea", chulInfo.get("chulArea"));
			}else{
				fixVO.put("chulName", "정보없음");
				fixVO.put("chulArea", "정보없음");
			}
		}else{
			model.addAttribute("message", "비정상적인 접근입니다.");
			model.addAttribute("returnUrl", "/front/fixNAuc/reqAdmList.do");
			model.addAttribute("result", 1);
			return "/front/fixAuc/N/result";
		}
		
		model.addAttribute("result", fixVO);
		
		//등급 코드 리스트
		paramMap.put("bunChk", "N");
		List<EgovMap> levelList = frontFixNAucService.getLevelList(paramMap);
		model.addAttribute("levelList", levelList);
		
		// box
		// paramMap.put("searchDefaultBoxCode", 200);
		List<EgovMap> boxList = frontFixNAucService.getBoxList(paramMap);
		model.addAttribute("boxList", boxList);
				
		model.addAttribute("title", "요청관리");
		model.addAttribute("contentPath", "fixAuc/N/reqAdmUpt.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	@RequestMapping(value="/front/fixNAuc/reqAdmUptProc.do")
	public String reqAdmUptProc(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, MultipartHttpServletRequest multiRequest
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"07".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		int result = 0;
		
		try {
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			EgovMap fixVO = frontFixNAucService.getReqAdmArticle(paramMap);
			if(fixVO != null){
				/*
				if(!"1".equals(String.valueOf(fixVO.get("fixState"))) && !"2".equals(String.valueOf(fixVO.get("fixState"))) ){
					model.addAttribute("message", "비정상적인 접근입니다.");
					model.addAttribute("returnUrl", "/front/fixNAuc/reqAdmList.do");
					model.addAttribute("result", 1);
					return "/front/fixAuc/N/result";
				}*/
			}else{
				model.addAttribute("message", "비정상적인 접근입니다.");
				model.addAttribute("returnUrl", "/front/fixNAuc/reqAdmList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/N/result";
			}
			
			
			paramMap.put("code", paramMap.get("jCode"));
			
			EgovMap chulCheck = frontFixNAucService.getCodeToLoginId(paramMap);
			
			if(chulCheck != null){
				paramMap.put("jId", chulCheck.get("loginId"));
			}
			
			
			EgovMap limitVO = frontFixNAucService.getLimitAmt(paramMap);
			if(limitVO != null){
				int limitAmt = Integer.parseInt(String.valueOf(limitVO.get("limitAmt")));
				int sokCnt = Integer.parseInt(String.valueOf(paramMap.get("sokCnt")));
				int unitPrice = Integer.parseInt(String.valueOf(paramMap.get("unitPrice")));
				int reqSokCnt = Integer.parseInt(String.valueOf(fixVO.get("sokCnt")));
				int reqUnitPrice = Integer.parseInt(String.valueOf(fixVO.get("unitPrice")));
				
				System.out.println("#####################");
				System.out.println("    limitAmt: " + limitAmt);
				System.out.println("      sokCnt: " + sokCnt);
				System.out.println("   unitPrice: " + unitPrice);
				System.out.println("   reqSokCnt: " + reqSokCnt);
				System.out.println("reqUnitPrice: " + reqUnitPrice);
				System.out.println("#####################");
				
				if(sokCnt == reqSokCnt && unitPrice == reqUnitPrice) {
					
					System.out.println("총 속/분 수량, 구매단가 가 변하지 않았습니다. 그대로 수정 진행합니다.");
					
					result = frontFixNAucService.reqAdmUptProc(paramMap);
					
					model.addAttribute("message", "정상적으로 저장되었습니다.");
					model.addAttribute("returnUrl", "/front/fixNAuc/reqAdmList.do");
					
				}else {
					
					if(limitAmt >= ((sokCnt*unitPrice)-(reqSokCnt*reqUnitPrice))){
						
						result = frontFixNAucService.reqAdmUptProc(paramMap);
						
						model.addAttribute("message", "정상적으로 저장되었습니다.");
						model.addAttribute("returnUrl", "/front/fixNAuc/reqAdmList.do");
						
					}else{
						//거래잔액부족
						result = 9;
						model.addAttribute("message", "거래잔액이 부족합니다.");
					}
				}
				
			}else{
				result = 9;
				model.addAttribute("message", "거래잔액이 부족합니다.");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = 0;
		}
		model.addAttribute("result", result);
		return "/front/fixAuc/N/result";
	}
	
	
	@RequestMapping(value="/front/fixNAuc/reqAdmDelProc.do")
	public String reqAdmDelProc(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"07".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		int result = 0;
		
		//TB_RECORD 상태 변경 이력 map 
		Map<String, Object> recordMap = new HashMap<String, Object>();
		//TB_RECORD 상태 변경 이력 이전 상태 값
		String preFixState = "";
		
		try {
		paramMap.put("id", loginVO.get("loginId"));
		paramMap.put("memberId", loginVO.get("memberId"));
		
		EgovMap fixVO = frontFixNAucService.getReqAdmArticle(paramMap);
		if(fixVO != null){
			
			if(!"1".equals(String.valueOf(fixVO.get("fixState"))) && !"2".equals(String.valueOf(fixVO.get("fixState"))) ){
				model.addAttribute("message", "비정상적인 접근입니다.");
				model.addAttribute("returnUrl", "/front/fixNAuc/reqAdmList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/N/result";
			}
		}
		
		
			//TB_RECORD 상태 변경 이력 이전 상태 값
			preFixState = frontFixAucRecordService.getPreReqState(String.valueOf(paramMap.get("reqDealSeq")));
		
			result = frontFixNAucService.reqAdmDelProc(paramMap);
			
			//TB_RECORD 상태 변경 이력 데이터 put
			recordMap.put("dealSeq", paramMap.get("reqDealSeq"));
			recordMap.put("fixType", "2");
			recordMap.put("bunChk", "N");
			recordMap.put("preFixState", String.valueOf(preFixState));
			recordMap.put("id", paramMap.get("id"));
			
			//TB_RECORD 경매사 삭제 delete
			frontFixAucRecordService.fixAucRegRecord(recordMap);
			
			
			model.addAttribute("message", "정상적으로 삭제되었습니다.");
			model.addAttribute("returnUrl", "/front/fixNAuc/reqAdmList.do");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = 0;
		}
		model.addAttribute("result", result);
		return "/front/fixAuc/N/result";
	}
	
	
	
	@RequestMapping(value="/front/fixNAuc/time.do")
	public String time(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		try {
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");

			String aucType = String.valueOf(loginVO.get("aucType"));
			if(!"07".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
				throw new ModelAndViewDefiningException(modelAndView);
			}
			List<EgovMap> fixTimeList = frontFixNAucService.getFixAucTime(paramMap);
			model.addAttribute("fixTimeList",fixTimeList);
			model.addAttribute("title", "시간관리");
			model.addAttribute("contentPath", "fixAuc/N/time.jsp");
		}catch (Exception e) {
			e.printStackTrace();
		}

		return "/front/layout/fixLayout";
	}
	
	@RequestMapping(value = "/front/fixNAuc/updateTime.json")
	  public String updateTime(HttpServletRequest request, @RequestParam Map<String,Object> paramMap
			  		    , ModelMap model) throws Exception{
	    
		
		try {
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
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
				setParam.put("timeType", parameter.get("timeType"));
				setParam.put("updateId", loginVO.get("loginId") );
				frontFixNAucService.updatetime(setParam);
				model.addAttribute("status","sucess");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("status","error");
			e.printStackTrace();
		}
	   
		
		return "jsonView";
	  }
	
	
	
	@RequestMapping(value = "/front/fixNAuc/moklist.json")
	  public String moklist(@RequestParam Map<String, Object> paramMap
			  		    , ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());  
			List<EgovMap> mokList = frontFixNAucService.getMokList(paramMap);
			model.addAttribute("results", mokList);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		
		return "jsonView";
	  }
	
	
	@RequestMapping(value = "/front/fixNAuc/pumlist.json")
	  public String pumlist(@RequestParam Map<String, Object> paramMap
			  		    , ModelMap model) throws Exception{
	    
		try {
			List<EgovMap> pumList = frontFixNAucService.getPumList(paramMap);
			model.addAttribute("results", pumList);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		
		return "jsonView";
	  }
	
	@RequestMapping(value = "/front/fixNAuc/getPrePrice.json")
	  public String getPrePrice(@RequestParam Map<String, Object> paramMap
			  		    , ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			List<EgovMap> preList = frontFixNAucService.getPrePrice(paramMap);
			model.addAttribute("result", preList);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		
		return "jsonView";
	  }
	
	
	@RequestMapping(value = "/front/fixNAuc/getRecentList.json")
	  public String getRecentList(HttpServletRequest request, HttpServletResponse response,@RequestParam Map<String, Object> paramMap
			  		    , ModelMap model ) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			//최근판매정보
			/*
			List<EgovMap> recentList = frontFixNAucService.getRecentList(paramMap);
			model.addAttribute("recentList", recentList);
			*/
			//출하자 코드리스트
			String aucType = String.valueOf(loginVO.get("aucType"));
			if("07".equals(aucType) ){
				List<EgovMap> recentList = frontFixNAucService.getRecentList(paramMap);
				model.addAttribute("recentList", recentList);
			}else{
				List<EgovMap> floMemberList = frontLoginService.getFloMember(paramMap);
				
				for(EgovMap chulVO : floMemberList){
					String chulCode = String.valueOf(chulVO.get("chulCd"));
					String paramChulCode = String.valueOf(paramMap.get("chulCode"));
					if(chulCode.equals(paramChulCode)){
						List<EgovMap> recentList = frontFixNAucService.getRecentList(paramMap);
						model.addAttribute("recentList", recentList);
					}
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		
		return "jsonView";
	  }
	
	
	@RequestMapping(value = "/front/fixNAuc/getJRecentList.json")
	  public String getJRecentList(HttpServletRequest request, HttpServletResponse response,@RequestParam Map<String, Object> paramMap
			  		    , ModelMap model ) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			if("07".equals(aucType) ){
				List<EgovMap> recentList = frontFixNAucService.getAdmJRecentList(paramMap);
				model.addAttribute("recentList", recentList);
			}else{
				//중도매인 코드리스트
				List<EgovMap> floMemberList = frontLoginService.getFloMember(paramMap);
				
				for(EgovMap chulVO : floMemberList){
					String chulCode = String.valueOf(chulVO.get("chulCd"));
					String paramJCode = String.valueOf(paramMap.get("jCode"));
					if(chulCode.equals(paramJCode)){
						List<EgovMap> recentList = frontFixNAucService.getJRecentList(paramMap);
						model.addAttribute("recentList", recentList);
					}
				}
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		
		return "jsonView";
	  }
	
	
	@RequestMapping(value = "/front/fixNAuc/getCRecentList.json")
	  public String getCRecentList(HttpServletRequest request, HttpServletResponse response,@RequestParam Map<String, Object> paramMap
			  		    , ModelMap model ) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			if("07".equals(aucType) || "03".equals(aucType)){
				List<EgovMap> recentList = frontFixNAucService.getCRecentList(paramMap);
				model.addAttribute("recentList", recentList);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		
		return "jsonView";
	  }
	
	
	@RequestMapping(value = "/front/fixNAuc/getLimitAmt.json")
	  public String getLimitAmt(HttpServletRequest request, HttpServletResponse response,
			  	@RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			if("07".equals(aucType) ){
				EgovMap limitVO = frontFixNAucService.getLimitAmt(paramMap);
				model.addAttribute("result", limitVO);
			}else{
			EgovMap checkVO = frontFixNAucService.getFloMemberCheck(paramMap);
				if(checkVO != null){
					EgovMap limitVO = frontFixNAucService.getLimitAmt(paramMap);
					model.addAttribute("result", limitVO);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		
		return "jsonView";
	  }
	
	
	@RequestMapping(value = "/front/fixNAuc/getBidBuyInfo.json")
	  public String getBidBuyInfo(HttpServletRequest request, HttpServletResponse response,
			  	@RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			EgovMap checkVO = frontFixNAucService.getFloMemberCheck(paramMap);
			if(checkVO != null){
				EgovMap bidCheckVO = frontFixNAucService.getBidBuyInfo(paramMap);
				model.addAttribute("result", bidCheckVO);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		
		return "jsonView";
	  }
	
	
	
	
	@RequestMapping(value = "/front/fixNAuc/fixBuyBid.json")
	  public String fixBuyBid(HttpServletRequest request, HttpServletResponse response,
			  	@RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"03".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			int result = 0;
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			EgovMap bidCheckVO =  frontFixNAucService.getBidBuyInfo(paramMap);
			
			if(bidCheckVO != null){
				if("Y".equals(String.valueOf(bidCheckVO.get("bidAdminYn")) ) ){
					
				}else{
					result = 5;
					model.addAttribute("result",result);
					return "jsonView";
					//입찰 가능 시간
					/*
					paramMap.put("timeType", "3");
					EgovMap timeVO = frontFixNAucService.getTime(paramMap);
					if(timeVO != null){
						if("N".equals(String.valueOf(timeVO.get("timeCheck"))) ){
							result = 4;
							model.addAttribute("result",result);
							return "jsonView";
						}
					}*/
				}
			}else{
				//입찰 가능 시간
				EgovMap fixVO = frontFixNAucService.getFixBuyArticle(paramMap);
				
				if(fixVO != null){
					if(!"7".equals(fixVO.get("fixState")) ){
						result = 4;
						model.addAttribute("result",result);
						return "jsonView";
					}
				}
			}
			
			
			
			EgovMap checkVO = frontFixNAucService.getFloMemberCheck(paramMap);
			if(checkVO != null){
				EgovMap limitVO = frontFixNAucService.getLimitAmt(paramMap);
				if(limitVO != null){
					int limitAmt = Integer.parseInt(String.valueOf(limitVO.get("limitAmt")));
					int bidSokCnt = Integer.parseInt(String.valueOf(paramMap.get("bidSokCnt")));
					int bidUnitPrice = Integer.parseInt(String.valueOf(paramMap.get("bidUnitPrice")));
					//EgovMap bidCheckVO =  frontFixNAucService.getBidBuyInfo(paramMap);
					int bidedSokCnt = 0;
					int bidedUnitPrice = 0; 
					if(bidCheckVO != null){
						bidedSokCnt = Integer.parseInt(String.valueOf(bidCheckVO.get("bidSokCnt")));
						bidedUnitPrice = Integer.parseInt(String.valueOf(bidCheckVO.get("bidUnitPrice")));
						
					}
					
					
					if(limitAmt >= (bidSokCnt*bidUnitPrice)-(bidedSokCnt*bidedUnitPrice)){
						
						EgovMap fixVO = frontFixNAucService.getFixBuyArticle(paramMap);
						
						if(fixVO != null){
							String dealType = String.valueOf(fixVO.get("dealType"));
							int unitPrice = Integer.parseInt(String.valueOf(fixVO.get("unitPrice")));
							if("L".equals(dealType)){
								//최저가 체크
								if(bidUnitPrice < unitPrice){
									model.addAttribute("message","최저가거래입니다. 최저가보다 높은 입찰단가를 입력해주세요.");
									return "jsonView";
								}
							}else if("F".equals(dealType)){
								//정가 체크
								if(bidUnitPrice != unitPrice){
									model.addAttribute("message","정가거래입니다. 고정된 가격을 입력해 주세요.");
									return "jsonView";
								}
							}
							
							
							if(bidCheckVO != null){
								// 응찰 수정
								paramMap.put("bidSeq", bidCheckVO.get("bidSeq"));
								frontFixNAucService.updateBid(paramMap);
								result = 2;
							}else{
								// 응찰 입력
								paramMap.put("bidSeq", egovBidIdGnrService.getNextIntegerId());
								frontFixNAucService.insertBid(paramMap);
								result = 1;
							}
							
						}else{
							
						}
						
					}else{
						//거래잔액부족
						result = 3;
					}
				}else{
					result = 3;
				}
				
			}
			
			model.addAttribute("result",result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		
		return "jsonView";
	  }
	
	
	
	  @RequestMapping(value = "/front/fixNAuc/fixAdmApply.json")
	  public String fixAdmApply(HttpServletRequest request
			      , HttpServletResponse response
			      , @RequestParam(value="checkedArray[]") List<String> checkedArray
			      , @RequestParam(value="aucDateArray[]") List<String> aucDateArray
			  	  , @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"07".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			int result = 0;
			
			//상태 변경 이력 
			Map<String, Object> recordMap = new HashMap<String, Object>();
			String preFixState = "";
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			for(int i=0; i<checkedArray.size(); i++ ){
				String fixDealSeq = checkedArray.get(i);
				String aucDate = aucDateArray.get(i);
				
				//TB_RECORD 상태 변경 이력 이전 상태 값
				preFixState = frontFixAucRecordService.getPreFixState(fixDealSeq);
				
				paramMap.put("fixDealSeq", fixDealSeq);
				paramMap.put("aucDate", aucDate);
				int cnt = frontFixNAucService.fixAdmApply(paramMap);
				
				//TB_RECORD 상태 변경 이력 데이터 put
				recordMap.put("dealSeq", fixDealSeq);
				recordMap.put("fixType", "1");
				recordMap.put("bunChk", "N");
				recordMap.put("preFixState", preFixState);
				recordMap.put("fixState", "3");
				recordMap.put("id", paramMap.get("id"));
				
				//TB_RECORD 경매사 승인 insert (1->3)
				frontFixAucRecordService.fixAucRegRecord(recordMap);
				
				if(cnt > 0){
					result++;
				}
			}
			
			model.addAttribute("result",result);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		return "jsonView";
	  }
	  
	  
	  @RequestMapping(value = "/front/fixNAuc/fixAdmAppCancel.json")
	  public String fixAdmAppCancel(HttpServletRequest request
			      , HttpServletResponse response
			      , @RequestParam(value="checkedArray[]") List<String> checkedArray
			      , @RequestParam(value="aucDateArray[]") List<String> aucDateArray
			  	  , @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"07".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			int result = 0;
			
			//TB_RECORD 상태 변경 이력 map 
			Map<String, Object> recordMap = new HashMap<String, Object>();
			String preFixState = "";
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			for(int i=0; i<checkedArray.size(); i++ ){
				String fixDealSeq = checkedArray.get(i);
				String aucDate = aucDateArray.get(i);
				
				//TB_RECORD 상태 변경 이력 이전 상태 값
				preFixState = frontFixAucRecordService.getPreFixState(fixDealSeq);
				
				paramMap.put("fixDealSeq", fixDealSeq);
				paramMap.put("aucDate", aucDate);
				int cnt = frontFixNAucService.fixAdmAppCancel(paramMap);
				
				//TB_RECORD 상태 변경 이력 데이터 put
				recordMap.put("dealSeq", fixDealSeq);
				recordMap.put("fixType", "1");
				recordMap.put("bunChk", "N");
				recordMap.put("preFixState", preFixState);
				recordMap.put("fixState", "1");
				recordMap.put("id", paramMap.get("id"));
				
				//TB_RECORD 경매사 승인취소 (3->1)
				frontFixAucRecordService.fixAucRegRecord(recordMap);
				
				if(cnt > 0){
					result++;
				}
			}
			
			model.addAttribute("result",result);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		return "jsonView";
	  }
	  
	  @RequestMapping(value = "/front/fixNAuc/fixAdmComp.json")
	  public String fixAdmComp(HttpServletRequest request
			      , HttpServletResponse response
			      , @RequestParam(value="checkedArray[]") List<String> checkedArray
			      , @RequestParam(value="aucDateArray[]") List<String> aucDateArray
			  	  , @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"07".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			int result = 0;
			
			//TB_RECORD 상태 변경 이력 map 
			Map<String, Object> recordMap = new HashMap<String, Object>();
			String preFixState = "";
			String fixState = "";
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			for(int i=0; i<checkedArray.size(); i++ ){
				String fixDealSeq = checkedArray.get(i);
				paramMap.put("fixDealSeq", fixDealSeq);
				
				//TB_RECORD 상태 변경 이력 이전 상태 값
				preFixState = frontFixAucRecordService.getPreFixState(fixDealSeq);
				
				//상장정보 
				EgovMap fixVO = frontFixNAucService.getFixAdmArticle(paramMap);
				int sokCnt = Integer.parseInt(String.valueOf(fixVO.get("sokCnt")));
				int unitPrice = Integer.parseInt(String.valueOf(fixVO.get("unitPrice")));
				String dealType = String.valueOf(fixVO.get("dealType"));
				int remainSokCnt = sokCnt;
				//입찰 된 속/분 수량
				int bidTotSokCnt = frontFixNAucService.getBidTotSokCnt(paramMap);
				
				if(bidTotSokCnt == 0 ){
					//유찰
					int cnt = frontFixNAucService.fixAdmUChal(paramMap);
					
					//유찰 상태
					fixState = "5";
					
					if(cnt > 0){
						result++;
					}
					
				}else if(bidTotSokCnt > 0 && sokCnt > bidTotSokCnt){
					//부분유찰
					int cnt = frontFixNAucService.fixAdmPartUChal(paramMap);
					
					//부분 유찰 상태
					fixState = "6";
					
					if(cnt > 0){
						result++;
					}
					frontFixNAucService.fixAdmBidTotSuc(paramMap);
				}else if(bidTotSokCnt > 0 && sokCnt == bidTotSokCnt){
					//판매완료
					int cnt = frontFixNAucService.fixAdmComp(paramMap);
					
					//완료 상태
					fixState = "4";
					
					if(cnt > 0){
						result++;
					}
					frontFixNAucService.fixAdmBidTotSuc(paramMap);
				}else{
					//패찰자 확인
					// 정가 : 등록시간순 / 최저가 : 금액순, 등록시간순 / 희망가: 금액순, 등록시간순
					if("F".equals(dealType)){
						//정가
						//입찰정보 가져오기
						paramMap.put("bidOrder", "F");
						List<EgovMap> bidList = frontFixNAucService.getBidList(paramMap);
						
						for(int j=0; j<bidList.size(); j++ ){
							EgovMap bidVO = bidList.get(j);
							paramMap.put("bidSeq",bidVO.get("bidSeq"));
							int bidSokCnt = Integer.parseInt(String.valueOf(bidVO.get("bidSokCnt")));
							int preRemainSokCnt = remainSokCnt;
							
							remainSokCnt = (remainSokCnt - bidSokCnt);
							
							if(remainSokCnt>=0){
								//낙찰 : 2
								frontFixNAucService.fixAdmBidSuc(paramMap);
							}else if(remainSokCnt<0 && preRemainSokCnt>0){
								//부분 낙찰 : 4
								paramMap.put("nakSokCnt",preRemainSokCnt);
								frontFixNAucService.fixAdmBidPartSuc(paramMap);
							}else{
								//패찰 : 3
								frontFixNAucService.fixAdmBidFail(paramMap);
							}
							
						}
						
					}else{
						//최저가, 희망가
						paramMap.put("bidOrder", "L");
						
						List<EgovMap> bidList = frontFixNAucService.getBidList(paramMap);
						
						for(int j=0; j<bidList.size(); j++ ){
							EgovMap bidVO = bidList.get(j);
							paramMap.put("bidSeq",bidVO.get("bidSeq"));
							int bidSokCnt = Integer.parseInt(String.valueOf(bidVO.get("bidSokCnt")));
							int preRemainSokCnt = remainSokCnt;
							
							remainSokCnt = (remainSokCnt - bidSokCnt);
							
							if(remainSokCnt>=0){
								//낙찰 : 2
								frontFixNAucService.fixAdmBidSuc(paramMap);
							}else if(remainSokCnt<0 && preRemainSokCnt>0){
								//부분 낙찰 : 4
								paramMap.put("nakSokCnt",preRemainSokCnt);
								frontFixNAucService.fixAdmBidPartSuc(paramMap);
							}else{
								//패찰 : 3
								frontFixNAucService.fixAdmBidFail(paramMap);
							}
							
						}
						
					}
					
					
					int cnt = frontFixNAucService.fixAdmComp(paramMap);
					
					//완료 상태
					fixState = "4";
					
					if(cnt > 0){
						result++;
					}
					
					
				}
				
				//TB_RECORD 상태 변경 이력 데이터 put
				recordMap.put("dealSeq", fixDealSeq);
				recordMap.put("fixType", "1");
				recordMap.put("bunChk", "N");
				recordMap.put("preFixState", preFixState);
				recordMap.put("fixState", fixState);
				recordMap.put("id", paramMap.get("id"));
				
				//TB_RECORD 경매사 완료  (3->4,5,6)
				frontFixAucRecordService.fixAucRegRecord(recordMap);
				
			}
			
			model.addAttribute("result",result);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		return "jsonView";
	  }
	  
	  
	  @RequestMapping(value = "/front/fixNAuc/fixAdmBan.json")
	  public String fixAdmBan(HttpServletRequest request
			      , HttpServletResponse response
			      , @RequestParam(value="checkedArray[]") List<String> checkedArray
			      , @RequestParam(value="banTextArray[]") List<String> banTextArray
			  	  , @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"07".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			int result = 0;
			
			//TB_RECORD 상태 변경 이력 map 
			Map<String, Object> recordMap = new HashMap<String, Object>();
			String preFixState = "";
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			for(int i=0; i<checkedArray.size(); i++ ){
				String fixDealSeq = checkedArray.get(i);
				String banText = banTextArray.get(i);
				
				//TB_RECORD 상태 변경 이력 이전 상태 값
				preFixState = frontFixAucRecordService.getPreFixState(fixDealSeq);
				
				paramMap.put("fixDealSeq", fixDealSeq);
				paramMap.put("banText", banText);
				int cnt = frontFixNAucService.fixAdmBan(paramMap);
				
				//TB_RECORD 상태 변경 이력 데이터 put
				recordMap.put("dealSeq", fixDealSeq);
				recordMap.put("fixType", "1");
				recordMap.put("bunChk", "N");
				recordMap.put("preFixState", preFixState);
				recordMap.put("fixState", "2");
				recordMap.put("id", paramMap.get("id"));
				
				//TB_RECORD 경매사 반려  (1,3->2)
				frontFixAucRecordService.fixAucRegRecord(recordMap);
				
				if(cnt > 0){
					result++;
				}
			}
			
			model.addAttribute("result",result);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		return "jsonView";
	  }
	  
	  
	  @RequestMapping(value = "/front/fixNAuc/reqAdmApply.json")
	  public String reqAdmApply(HttpServletRequest request
			      , HttpServletResponse response
			      , @RequestParam(value="checkedArray[]") List<String> checkedArray
			      , @RequestParam(value="aucPriceArray[]") List<String> aucPriceArray
			  	  , @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"07".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			int result = 0;
			
			//TB_RECORD 상태 변경 이력 map 
			Map<String, Object> recordMap = new HashMap<String, Object>();
			String preFixState = "";
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			for(int i=0; i<checkedArray.size(); i++ ){
				String reqDealSeq = checkedArray.get(i);
				String aucPrice = aucPriceArray.get(i);
				
				//TB_RECORD 상태 변경 이력 이전 상태 값
				preFixState = frontFixAucRecordService.getPreReqState(reqDealSeq);
				
				paramMap.put("reqDealSeq", reqDealSeq);
				paramMap.put("aucPrice", aucPrice);
				int cnt = frontFixNAucService.reqAdmApply(paramMap);
				
				//TB_RECORD 상태 변경 이력 데이터 put
				recordMap.put("dealSeq", reqDealSeq);
				recordMap.put("fixType", "2");
				recordMap.put("bunChk", "N");
				recordMap.put("preFixState", preFixState);
				recordMap.put("fixState", "3");
				recordMap.put("id", paramMap.get("id"));
				
				//TB_RECORD 경매사 진행 (1->3)
				frontFixAucRecordService.fixAucRegRecord(recordMap);
				
				
				if(cnt > 0){
					result++;
				}
			}
			
			model.addAttribute("result",result);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		return "jsonView";
	  }
	  
	  
	  @RequestMapping(value = "/front/fixNAuc/reqAdmAppCancel.json")
	  public String reqAdmAppCancel(HttpServletRequest request
			      , HttpServletResponse response
			      , @RequestParam(value="checkedArray[]") List<String> checkedArray
			      , @RequestParam(value="aucPriceArray[]") List<String> aucPriceArray
			  	  , @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"07".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			int result = 0;
			
			//TB_RECORD 상태 변경 이력 map 
			Map<String, Object> recordMap = new HashMap<String, Object>();
			String preFixState = "";
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			for(int i=0; i<checkedArray.size(); i++ ){
				String reqDealSeq = checkedArray.get(i);
				String aucPrice = aucPriceArray.get(i);
				
				//TB_RECORD 상태 변경 이력 이전 상태 값
				preFixState = frontFixAucRecordService.getPreReqState(reqDealSeq);
				
				paramMap.put("reqDealSeq", reqDealSeq);
				paramMap.put("aucPrice", aucPrice);
				int cnt = frontFixNAucService.reqAdmAppCancel(paramMap);
				
				//TB_RECORD 상태 변경 이력 map
				recordMap.put("dealSeq", reqDealSeq);
				recordMap.put("fixType", "2");
				recordMap.put("bunChk", "N");
				recordMap.put("preFixState", preFixState);
				recordMap.put("fixState", "1");
				recordMap.put("id", paramMap.get("id"));
				
				//TB_RECORD 경매사 진행취소 (3->1)
				frontFixAucRecordService.fixAucRegRecord(recordMap);
				
				if(cnt > 0){
					result++;
				}
			}
			
			model.addAttribute("result",result);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		return "jsonView";
	  }
	  
	  @RequestMapping(value = "/front/fixNAuc/reqAdmComp.json")
	  public String reqAdmComp(HttpServletRequest request
			      , HttpServletResponse response
			      , @RequestParam(value="checkedArray[]") List<String> checkedArray
			      , @RequestParam(value="aucPriceArray[]") List<String> aucPriceArray
			  	  , @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"07".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			int result = 0;
			
			//TB_RECORD 상태 변경 이력 map 
			Map<String, Object> recordMap = new HashMap<String, Object>();
			String preFixState = "";
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			for(int i=0; i<checkedArray.size(); i++ ){
				String reqDealSeq = checkedArray.get(i);
				String aucPrice = aucPriceArray.get(i);
				
				//TB_RECORD 상태 변경 이력 이전 상태 값
				preFixState = frontFixAucRecordService.getPreReqState(reqDealSeq);
				
				paramMap.put("reqDealSeq", reqDealSeq);
				paramMap.put("aucPrice", aucPrice);
				int cnt = frontFixNAucService.reqAdmComp(paramMap);
				
				//TB_RECORD 상태 변경 이력 데이터 put
				recordMap.put("dealSeq", reqDealSeq);
				recordMap.put("fixType", "2");
				recordMap.put("bunChk", "N");
				recordMap.put("preFixState", preFixState);
				recordMap.put("fixState", "4");
				recordMap.put("id", paramMap.get("id"));
				
				//TB_RECORD 경매사 체결 (3->4)
				frontFixAucRecordService.fixAucRegRecord(recordMap);
				
				if(cnt > 0){
					result++;
				}
			}
			
			model.addAttribute("result",result);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		return "jsonView";
	  }
	  
	  
	  @RequestMapping(value = "/front/fixNAuc/reqAdmBan.json")
	  public String reqAdmBan(HttpServletRequest request
			      , HttpServletResponse response
			      , @RequestParam(value="checkedArray[]") List<String> checkedArray
			      , @RequestParam(value="banTextArray[]") List<String> banTextArray
			  	  , @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"07".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			int result = 0;
			
			//TB_RECORD 상태 변경 이력 map 
			Map<String, Object> recordMap = new HashMap<String, Object>();
			String preFixState = "";
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			for(int i=0; i<checkedArray.size(); i++ ){
				String reqDealSeq = checkedArray.get(i);
				String banText = banTextArray.get(i);
				
				//TB_RECORD 상태 변경 이력 이전 상태 값
				preFixState = frontFixAucRecordService.getPreReqState(reqDealSeq);
				
				paramMap.put("reqDealSeq", reqDealSeq);
				paramMap.put("banText", banText);
				int cnt = frontFixNAucService.reqAdmBan(paramMap);
				
				//TB_RECORD 상태 변경 이력 데이터 put
				recordMap.put("dealSeq", reqDealSeq);
				recordMap.put("fixType", "2");
				recordMap.put("bunChk", "N");
				recordMap.put("preFixState", preFixState);
				recordMap.put("fixState", "2");
				recordMap.put("id", paramMap.get("id"));
				
				//TB_RECORD 경매사 미체결 (1,3->2)
				frontFixAucRecordService.fixAucRegRecord(recordMap);
				
				
				if(cnt > 0){
					result++;
				}
			}
			
			model.addAttribute("result",result);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		return "jsonView";
	  }
	  
	  
	  @RequestMapping(value = "/front/fixNAuc/fixAdmBidAdmin.json")
	  public String fixAdmBidAdmin(HttpServletRequest request
			      , HttpServletResponse response
			      , @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"07".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			int result = frontFixNAucService.fixAdmBidAdmin(paramMap);
			
			model.addAttribute("result",result);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		return "jsonView";
	  }
	  
	  
	  @RequestMapping(value = "/front/fixNAuc/fixAdmBidCancel.json")
	  public String fixAdmBidCancel(HttpServletRequest request
			      , HttpServletResponse response
			      , @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"07".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			int result = frontFixNAucService.fixAdmBidCancel(paramMap);
			
			model.addAttribute("result",result);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		return "jsonView";
	  }
	  
	  
	  @RequestMapping(value = "/front/fixNAuc/fixAdmBidUpdate.json")
	  public String fixAdmBidUpdate(HttpServletRequest request
			      , HttpServletResponse response
			      , @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"07".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			int result = frontFixNAucService.fixAdmBidUpdate(paramMap);
			
			model.addAttribute("result",result);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		return "jsonView";
	  }
	  
	  
	  @RequestMapping(value = "/front/fixNAuc/reqSellComp.json")
	  public String reqChulComp(HttpServletRequest request
			      , HttpServletResponse response
			      , @RequestParam(value="checkedArray[]") List<String> checkedArray
			  	  , @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"01".equals(aucType) &&  !"02".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			int result = 0;
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			
			//출하자 코드리스트
			List<EgovMap> floMemberList = frontLoginService.getFloMember(paramMap);
			
			List<EgovMap> floList = new ArrayList<EgovMap>();
			List<String> chulCodeArr = new ArrayList<String>();
			
			for(EgovMap chulVO : floMemberList){
				String chulCode = String.valueOf(chulVO.get("chulCd"));
				EgovMap chulInfo = frontFixNAucService.getChulCodeToChulInfo(chulCode);
				if(chulInfo != null){
					floList.add(chulInfo);
					chulCodeArr.add(String.valueOf(chulInfo.get("code")));
				}
			}
			
			model.addAttribute("floList", floList);
			
			paramMap.put("chulCodeArr", chulCodeArr);
			
			//TB_RECORD 상태 변경 이력 map 
			Map<String, Object> recordMap = new HashMap<String, Object>();
			String preFixState = "";
			
			
			for(int i=0; i<checkedArray.size(); i++ ){
				String reqDealSeq = checkedArray.get(i);
				paramMap.put("reqDealSeq", reqDealSeq);
				
				//TB_RECORD 상태 변경 이력 이전 상태 값
				preFixState = frontFixAucRecordService.getPreReqState(reqDealSeq);
				
				int cnt = frontFixNAucService.reqChulComp(paramMap);
				
				//TB_RECORD 상태 변경 이력 데이터 put
				recordMap.put("dealSeq", reqDealSeq);
				recordMap.put("fixType", "2");
				recordMap.put("bunChk", "N");
				recordMap.put("preFixState", preFixState);
				recordMap.put("fixState", "4");
				recordMap.put("id", paramMap.get("id"));
				
				//TB_RECORD 출하자 체결  (3->4)
				frontFixAucRecordService.fixAucRegRecord(recordMap);
				
				if(cnt > 0){
					result++;
				}
			}
			
			model.addAttribute("result",result);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		return "jsonView";
	  }
	  
	  
	  @RequestMapping(value = "/front/fixNAuc/reqSellBan.json")
	  public String reqChulBan(HttpServletRequest request
			      , HttpServletResponse response
			      , @RequestParam(value="checkedArray[]") List<String> checkedArray
			      , @RequestParam(value="banTextArray[]") List<String> banTextArray
			  	  , @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"01".equals(aucType) &&  !"02".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			int result = 0;
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			//출하자 코드리스트
			List<EgovMap> floMemberList = frontLoginService.getFloMember(paramMap);
			
			List<EgovMap> floList = new ArrayList<EgovMap>();
			List<String> chulCodeArr = new ArrayList<String>();
			
			for(EgovMap chulVO : floMemberList){
				String chulCode = String.valueOf(chulVO.get("chulCd"));
				EgovMap chulInfo = frontFixNAucService.getChulCodeToChulInfo(chulCode);
				if(chulInfo != null){
					floList.add(chulInfo);
					chulCodeArr.add(String.valueOf(chulInfo.get("code")));
				}
			}
			
			model.addAttribute("floList", floList);
			
			paramMap.put("chulCodeArr", chulCodeArr);

			//TB_RECORD 상태 변경 이력 map 
			Map<String, Object> recordMap = new HashMap<String, Object>();
			String preFixState = "";
			
			for(int i=0; i<checkedArray.size(); i++ ){
				String reqDealSeq = checkedArray.get(i);
				String banText = banTextArray.get(i);
				
				//TB_RECORD 상태 변경 이력 이전 상태 값
				preFixState = frontFixAucRecordService.getPreReqState(reqDealSeq);
				
				paramMap.put("reqDealSeq", reqDealSeq);
				paramMap.put("banText", banText);
				int cnt = frontFixNAucService.reqChulBan(paramMap);
				
				//TB_RECORD 상태 변경 이력 데이터 put
				recordMap.put("dealSeq", reqDealSeq);
				recordMap.put("fixType", "2");
				recordMap.put("bunChk", "N");
				recordMap.put("preFixState", preFixState);
				recordMap.put("fixState", "2");
				recordMap.put("id", paramMap.get("id"));
				
				//TB_RECORD 출하자 미체결 (4,3->2)
				frontFixAucRecordService.fixAucRegRecord(recordMap);
				
				
				if(cnt > 0){
					result++;
				}
			}
			
			model.addAttribute("result",result);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		return "jsonView";
	  }
	  
	  
	
	  
	  @RequestMapping(value = "/front/fixNAuc/getChulList.json")
	  public String getChulList(HttpServletRequest request, HttpServletResponse response,@RequestParam Map<String, Object> paramMap
			  		    , ModelMap model ) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"07".equals(aucType) && !"03".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			//출하자 코드리스트
			List<EgovMap> chulList = frontFixNAucService.getChulList(paramMap);
			model.addAttribute("chulList", chulList);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		
		return "jsonView";
	  }
	  
	  
	  @RequestMapping(value = "/front/fixNAuc/fixSmsProc.json")
	  public String fixSmsProc(HttpServletRequest request
			       , HttpServletResponse response
			       , @RequestParam Map<String, Object> paramMap
			       , @RequestParam(value="banArray[]", required=false) String banArray
			       , @RequestParam(value="applyArray[]", required=false) String applyArray
			       , @RequestParam(value="bidStrArray[]", required=false) String bidStrArray
			       , @RequestParam(value="dealCompArray[]", required=false) String dealCompArray
			       , ModelMap model ) throws Exception{
	    
		try {
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			
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
			
			String vMsg = frontFixNAucService.fixSmsProc(paramMap);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return "jsonView";
	  }
	  
	  
	  @RequestMapping(value = "/front/fixNAuc/reqSmsProc.json")
	  public String reqSmsProc(HttpServletRequest request
			       , HttpServletResponse response
			       , @RequestParam Map<String, Object> paramMap
			       , @RequestParam(value="banArray[]", required=false) String banArray
			       , @RequestParam(value="applyArray[]", required=false) String applyArray
			       , @RequestParam(value="dealDisCompArray[]", required=false) String dealDisCompArray
			       , @RequestParam(value="dealCompArray[]", required=false) String dealCompArray
			       , ModelMap model ) throws Exception{
	    
		try {
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			
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
			paramMap.put("dealDisCompArray", dealDisCompArray);
			
			
			//출하자 코드리스트
			String vMsg = frontFixNAucService.reqSmsProc(paramMap);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return "jsonView";
	  }
	  
	  
	  @RequestMapping(value = "/front/fixNAuc/sendSmsMms.json")
	  public String sendSmsMms(HttpServletRequest request
			       , HttpServletResponse response
			       , @RequestParam Map<String, Object> paramMap
			       , ModelMap model ) throws Exception{
	    
		try {
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"07".equals(aucType)){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			String codeType =  String.valueOf(paramMap.get("codeType"));
			
			EgovMap recInfo = null;
			if("1".equals(codeType)){
				//출하자
				
				String chulCode = String.valueOf(paramMap.get("code"));
				recInfo = frontFixNAucService.getChulCodeToChulInfo(chulCode);
			}else{
				//중도매인
				String jCode = String.valueOf(paramMap.get("code"));
				recInfo = frontFixNAucService.getJCodeToJInfo(jCode);
			}
			
			
			if(recInfo != null){
				int sendCount = 0;
				int msgByte = frontFixNAucService.messageBiteLength(paramMap);
				paramMap.put("phoneNo", recInfo.get("phoneNo"));
				if(msgByte<90){
					//sms 보내기
					sendCount = frontFixNAucService.sendSms(paramMap);
				}else if(msgByte >= 90 && msgByte < 4000){
					//mms 보내기
					sendCount = frontFixNAucService.sendMms(paramMap);
				}else{
					// 4000바이트 초과
					model.addAttribute("result", 2);
					model.addAttribute("msgByte", msgByte);
					return "jsonView";
				}
				
				//발송성공
				model.addAttribute("result", 1);
				model.addAttribute("sendCount", sendCount);
				
			}else{
				// 정보없음
				model.addAttribute("result", 0);
				return "jsonView";
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return "jsonView";
	  }
	  
	  
	  
	  
	  
	  
	  @RequestMapping(value = "/front/fixNAuc/getJList.json")
	  public String getJList(HttpServletRequest request, HttpServletResponse response,@RequestParam Map<String, Object> paramMap
			  		    , ModelMap model ) throws Exception{
	    
		try {
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			if(!"07".equals(aucType)){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			//출하자 코드리스트
			List<EgovMap> jList = frontFixNAucService.getJList(paramMap);
			model.addAttribute("jList", jList);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		
		return "jsonView";
	  }
	
	  
	  /* 정산관리 */
	  
	  @RequestMapping(value="/front/fixNAuc/tranAdmList.do")
	  public String tranAdmList(HttpServletRequest request, HttpServletResponse response,
				@RequestParam Map<String, Object> paramMap
				, ModelMap model) throws Exception {
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"07".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			
			try {
				
				Date date = new Date();
				SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd");
				
				if(paramMap.get("searchChulDate") == null){
					paramMap.put("searchChulDate",frontFixNAucService.selectMaxChulDate(paramMap));
				}
				
				
				Map<String, Object> param = new HashMap<String, Object>();
			    param.putAll(paramMap);
			    param.put("pageIndex", null);
			    
				String paginationQueryString = MapQuery.urlEncodeUTF8(param);
				
				paramMap.put("id", loginVO.get("loginId"));
				paramMap.put("memberId", loginVO.get("memberId"));
				
				EgovMap fixInfo = frontFixNAucService.getTranAdmInfo(paramMap);
				
				List<EgovMap> fixList = frontFixNAucService.getTranAdmList(paramMap);
				
				int totCnt = frontFixNAucService.getTranAdmListCnt(paramMap);
				
			    model.addAttribute("paginationQueryString", paginationQueryString);
				
			    model.addAttribute("fixInfo", fixInfo);
				model.addAttribute("resultList", fixList);
				model.addAttribute("resultCnt", totCnt);
				model.addAttribute("paramMap", paramMap);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			model.addAttribute("title", "정산관리");
			model.addAttribute("contentPath", "fixAuc/N/tranAdmList.jsp");
			
			return "/front/layout/fixLayout";
	}
	  
	  
	  @RequestMapping(value = "/front/fixNAuc/tranMagamInsProc.json")
	  public String tranMagamInsProc(HttpServletRequest request
			  , HttpServletResponse response
			  , @RequestParam Map<String, Object> paramMap
			  , ModelMap model ) throws Exception{
	    
		try {
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			if(!"07".equals(aucType)){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			
			int tranCnt = frontFixNAucService.tranMagamTranCnt(paramMap);
			
			if(tranCnt > 0){
				//전송데이터 존재
				model.addAttribute("errorCode","1");
				model.addAttribute("tranCnt",tranCnt);
				return "jsonView";
			}
			
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			//마감처리
			
			frontFixNAucService.tranMagamDelProc(paramMap);
			
			int result = frontFixNAucService.tranMagamInsProc(paramMap);
			model.addAttribute("result", result);
			
		} catch (Exception e) {
			
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		
		return "jsonView";
	  } 
	  
	  
	  @RequestMapping(value = "/front/fixNAuc/tranMagamDelProc.json")
	  public String tranMagamDelProc(HttpServletRequest request
			  , HttpServletResponse response
			  , @RequestParam Map<String, Object> paramMap
			  , ModelMap model ) throws Exception{
	    
		try {
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			if(!"07".equals(aucType)){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			int tranCnt = frontFixNAucService.tranMagamTranCnt(paramMap);
			
			if(tranCnt > 0){
				//전송데이터 존재
				model.addAttribute("errorCode","1");
				model.addAttribute("tranCnt",tranCnt);
				return "jsonView";
			}
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			//마감 취소처리
			frontFixNAucService.tranMagamDelProc(paramMap);
			
		} catch (Exception e) {
			
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		
		return "jsonView";
	  }  
	  
	  
	  @RequestMapping(value = "/front/fixNAuc/tranMagamComp.json")
	  public String tranMagamComp(HttpServletRequest request
			      , HttpServletResponse response
			      , @RequestParam(value="checkedArray[]") List<String> checkedArray
			      , @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"07".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			int result = 0;
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			paramMap.put("aucCode", loginVO.get("chulCd"));
			
			for(int i=0; i<checkedArray.size(); i++ ){
				String tranSeq = checkedArray.get(i);
				paramMap.put("tranSeq", tranSeq);
				
				/*
				EgovMap checkVO = frontFixNAucService.tranMagamCompCheck(paramMap);
				
				if(checkVO != null){
					//전송데이터 존재
					model.addAttribute("errorCode","1");
					model.addAttribute("errorVO",checkVO);
					return "jsonView";
				}*/
				
				int cnt = frontFixNAucService.tranMagamComp(paramMap);
				
				if(cnt > 0){
					result++;
				}
			}
			
			model.addAttribute("result",result);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		return "jsonView";
	  }
	  
	  
	  
	  @RequestMapping(value="/front/fixNAuc/tranCompListExcell.do")
		public void tranCompListExcell(
				  @RequestParam Map<String, Object> paramMap
				, HttpServletResponse response
				, HttpServletRequest request
				, ModelMap model) throws Exception{
		 
		  try {
		  
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"07".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
          throw new ModelAndViewDefiningException(modelAndView);
		}
		
		 List<Map<String,Object>> list = frontFixNAucService.getTranListMagam(paramMap);
		 
		 SXSSFWorkbook workbook = new SXSSFWorkbook();
		 int tableInitRow = 5;
		 	// 상장 데이터 시트 생성
	        SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("마감데이터");
	        
	        //시트 열 너비 설정
	        for(int i=0; i<20; i++){
	        	sheet.setColumnWidth(i, 4000);
	        }
	        
	        
	        //스타일
	        CellStyle titleCellStyle = workbook.createCellStyle();
	        titleCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
	        titleCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	        
	        CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
	        headerCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	        headerCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
	        headerCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
	        headerCellStyle.setBorderRight(CellStyle.BORDER_THIN);
	        headerCellStyle.setBorderTop(CellStyle.BORDER_THIN);
	        //headerCellStyle.setFillBackgroundColor(CellStyle.);
	        
	        CellStyle bodyCellStyle = workbook.createCellStyle();
	        bodyCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
	        bodyCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	        bodyCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
	        bodyCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
	        bodyCellStyle.setBorderRight(CellStyle.BORDER_THIN);
	        bodyCellStyle.setBorderTop(CellStyle.BORDER_THIN);
	        
	        CellStyle bodyLeftCellStyle = workbook.createCellStyle();
	        bodyLeftCellStyle.setAlignment(CellStyle.ALIGN_LEFT);
	        bodyLeftCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	        bodyLeftCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
	        bodyLeftCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
	        bodyLeftCellStyle.setBorderRight(CellStyle.BORDER_THIN);
	        bodyLeftCellStyle.setBorderTop(CellStyle.BORDER_THIN);
	        
	        CellStyle bodyRightCellStyle = workbook.createCellStyle();
	        bodyRightCellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
	        bodyRightCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	        bodyRightCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
	        bodyRightCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
	        bodyRightCellStyle.setBorderRight(CellStyle.BORDER_THIN);
	        bodyRightCellStyle.setBorderTop(CellStyle.BORDER_THIN);
	        
	        CellStyle bodyNumCellStyle = workbook.createCellStyle();
	        DataFormat format = workbook.createDataFormat();
	        bodyNumCellStyle.setDataFormat(format.getFormat("#,##0"));
	        bodyNumCellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
	        bodyNumCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	        bodyNumCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
	        bodyNumCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
	        bodyNumCellStyle.setBorderRight(CellStyle.BORDER_THIN);
	        bodyNumCellStyle.setBorderTop(CellStyle.BORDER_THIN);
	        
	        Row titleRow = sheet.createRow(1);
	        Cell titleCell = titleRow.createCell(0);
	        titleCell.setCellValue("마감 테이블 데이터");
	        titleCell.setCellStyle(titleCellStyle);

	        sheet.addMergedRegion(new CellRangeAddress(1, 3, 0, 14));
	        
	        String[] headerArrayMagam = {"BUN_CHK","UP_DAY","UP_NO","CHUL_CODE","J_CODE","MOK_CODE","PUM_CODE","CHUL_LEVEL","BOX_CNT","SOK_CNT","F_SONGE_CNT","KM_P_ORG","TRAN_YN","FIX_TYPE","FIX_SEQ","TRANS","BIGO","PAN_TYPE","PUM_KG","BOX_CODE"};
	        
	        // 헤더 행 생
	        Row headerRow = sheet.createRow(tableInitRow);
	       
	        Cell headerCell = null;
	        for(int i = 0; i<headerArrayMagam.length; i++){
	        	headerCell = headerRow.createCell(i);
	 	        headerCell.setCellValue(headerArrayMagam[i]);
	 	        headerCell.setCellStyle(headerCellStyle);
	        	
	        }
	        
	        Row bodyRow = null;
	        Cell bodyCell = null;
	        for(int i=0; i<list.size(); i++) {
	            Map<String, Object> fixVO = list.get(i);
	            
	            // 행 생성
	            bodyRow = sheet.createRow(tableInitRow+(i+1));
	            for(int j = 0; j<headerArrayMagam.length; j++){
	            	if(fixVO.get(headerArrayMagam[j]) == null){
		            	fixVO.put(headerArrayMagam[j],"");
		            }
		            bodyCell = bodyRow.createCell(j);
		            bodyCell.setCellValue(String.valueOf(fixVO.get(headerArrayMagam[j])));
		            bodyCell.setCellStyle(bodyCellStyle);
	            }
 
	        }
	        
	        model.addAttribute("locale", Locale.KOREA);
	        model.addAttribute("workbook", workbook);
	        model.addAttribute("workbookName", "마감데이터"+String.valueOf(paramMap.get("chulDate")));
	        
	        renderMergedOutputModel(model, request, response);
	       
		  } catch (Exception e) {
				// TODO: handle exception
			  e.printStackTrace();
			  System.out.println("tempDir:::::::::::"+System.getProperty("java.io.tmpdir"));
			  
			}
		 
		}
	  
	  
	  @RequestMapping(value="/front/fixNAuc/transAdmCompList.do")
	  public String transAdmCompList(HttpServletRequest request, HttpServletResponse response,
				@RequestParam Map<String, Object> paramMap
				, ModelMap model) throws Exception {
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"07".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			
			try {
				
				paramMap.put("id", loginVO.get("loginId"));
				paramMap.put("memberId", loginVO.get("memberId"));
				
				List<EgovMap> fixList = frontFixNAucService.getFixAucCompChulList(paramMap);
				
				//List<EgovMap> fixList2 = frontFixNAucService.getFixAucCompDomeList(paramMap);
				//int result = frontFixNAucService.updateFixAucCompChulBigo(paramMap);
			    
				model.addAttribute("resultList", fixList);
				
				model.addAttribute("paramMap", paramMap);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			return "/front/fixAuc/N/transAdmCompList";
	  }
	  
	  
	  @RequestMapping(value="/front/fixNAuc/transAdmCompListUpdate.json")
	  public String transAdmCompListUpdate(HttpServletRequest request
			    , HttpServletResponse response
			    , @RequestParam Map<String, Object> paramMap
			    , @RequestParam(value="tranSeqArray[]") List<String> tranSeqArray
			    , @RequestParam(value="bigoArray[]") List<String> bigoArray
				, ModelMap model) throws Exception {
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"07".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			try {
				
				int result = 0;
				
				for(int i=0; i<tranSeqArray.size(); i++ ){
					String tranSeq = tranSeqArray.get(i);
					String bigo = bigoArray.get(i);
					paramMap.put("tranSeq", tranSeq);
					paramMap.put("bigo", bigo);
					int cnt = frontFixNAucService.updateFixAucCompChulBigo(paramMap);
					
					if(cnt > 0){
						result++;
					}
				}
				
				model.addAttribute("result", result);
				model.addAttribute("paramMap", paramMap);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			return "jsonView";
		}
	  
	  
	  @RequestMapping(value="/front/fixNAuc/transAdmCompChulListPrint.do")
	  public String transAdmCompChulListPrint(HttpServletRequest request, HttpServletResponse response,
				@RequestParam Map<String, Object> paramMap
				, ModelMap model) throws Exception {
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"07".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			
			try {
				
				paramMap.put("id", loginVO.get("loginId"));
				paramMap.put("memberId", loginVO.get("memberId"));
				
				List<EgovMap> fixList = frontFixNAucService.getFixAucCompChulList(paramMap);
				
				model.addAttribute("resultList", fixList);
				model.addAttribute("paramMap", paramMap);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			return "/front/fixAuc/N/transAdmCompChulListPrint";
	  }
	  
	  @RequestMapping(value="/front/fixNAuc/transAdmCompDomeListPrint.do")
	  public String transAdmCompDomeListPrint(HttpServletRequest request, HttpServletResponse response,
				@RequestParam Map<String, Object> paramMap
				, ModelMap model) throws Exception {
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"07".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			
			try {
				
				paramMap.put("id", loginVO.get("loginId"));
				paramMap.put("memberId", loginVO.get("memberId"));
				
				List<EgovMap> fixList = frontFixNAucService.getFixAucCompDomeList(paramMap);
				
				model.addAttribute("resultList", fixList);
				model.addAttribute("paramMap", paramMap);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			return "/front/fixAuc/N/transAdmCompDomeListPrint";
	  }
	  
	  
	  
	  
	  protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
	            throws Exception {
	        
	        Locale locale = (Locale) model.get("locale");
	        String workbookName = (String) model.get("workbookName");
	        
	        // 겹치는 파일 이름 중복을 피하기 위해 시간을 이용해서 파일 이름에 추
	        Date date = new Date();
	        SimpleDateFormat dayformat = new SimpleDateFormat("yyyyMMdd", locale);
	        SimpleDateFormat hourformat = new SimpleDateFormat("hhmmss", locale);
	        String day = dayformat.format(date);
	        String hour = hourformat.format(date);
	        //String fileName = workbookName + "_" + day + "_" + hour + ".xlsx";         
	        String fileName = workbookName+ ".xlsx";
	        
	        // 여기서부터는 각 브라우저에 따른 파일이름 인코딩작업
	        String browser = request.getHeader("User-Agent");
	        if (browser.indexOf("MSIE") > -1) {
	            fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
	        } else if (browser.indexOf("Trident") > -1) {       // IE11
	            fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
	        } else if (browser.indexOf("Firefox") > -1) {
	            fileName = "\"" + new String(fileName.getBytes("UTF-8"), "8859_1") + "\"";
	        } else if (browser.indexOf("Opera") > -1) {
	            fileName = "\"" + new String(fileName.getBytes("UTF-8"), "8859_1") + "\"";
	        } else if (browser.indexOf("Chrome") > -1) {
	            StringBuffer sb = new StringBuffer();
	            for (int i = 0; i < fileName.length(); i++) {
	               char c = fileName.charAt(i);
	               if (c > '~') {
	                     sb.append(URLEncoder.encode("" + c, "UTF-8"));
	                       } else {
	                             sb.append(c);
	                       }
	                }
	                fileName = sb.toString();
	        } else if (browser.indexOf("Safari") > -1){
	            fileName = "\"" + new String(fileName.getBytes("UTF-8"), "8859_1")+ "\"";
	        } else {
	             fileName = "\"" + new String(fileName.getBytes("UTF-8"), "8859_1")+ "\"";
	        }
	        
	        response.setContentType("application/download;charset=utf-8");
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
	        response.setHeader("Content-Transfer-Encoding", "binary");
	        
	       OutputStream os = null;
	       SXSSFWorkbook workbook = null;
	       
	       try {
	           workbook = (SXSSFWorkbook) model.get("workbook");
	           os = response.getOutputStream();
	           
	           // 파일생성
	           workbook.write(os);
	       }catch (Exception e) {
	           e.printStackTrace();
	       } finally {
	           
	    	   if(workbook != null) {
	        	   
	               try {
	            	   workbook.close();
	               } catch (Exception e) {
	                   e.printStackTrace();
	               }
	           }
	           
	           if(os != null) {
	               try {
	                   os.close();
	               } catch (Exception e) {
	                   e.printStackTrace();
	               }
	           }
	           
	           
	       }
	    }
	  
	  
	  
	
	
	private List<FileVO> uploadThumbFile(ModelMap model, MultipartHttpServletRequest multiRequest) throws Exception{
		
		String mode = EgovProperties.getProperty("Globals.Mode");
		String savePath = EgovProperties.getProperty("Globals.DstbtPath"+mode);
		
	    String uploadPath = savePath + "/uploads/fixAuc/N/";
		
	    String atchFileId = "-1";
	    List<MultipartFile> atchFile = multiRequest.getFiles("attachFile");

	    List<FileVO> atchFileList = null;
	    try {
	      atchFileList = egovFileMngUtil.parseFileInf(atchFile, "", 0, atchFileId, uploadPath, "gif,jpg,jpeg,png,bmp", 20);
	    } catch (Exception e) {
	      model.addAttribute("message", e.getMessage());
	      e.printStackTrace();
	    }

	    return atchFileList;
	  }
	
	  public void searchSessoin(HttpServletRequest request, String searchName, Map<String, Object> paramMap) throws Exception{
		
	  	String[] searchNameArr = {"fixAdmListSearch","fixSellListSearch","fixBuyListSearch","reqAdmListSearch","reqSellListSearch","reqBuyListSearch"};
	          	  
		for(int i=0; i<searchNameArr.length; i++){
			if(searchNameArr[i].equals(searchName)){
				request.getSession().setAttribute(searchNameArr[i], paramMap);
			}else{
				request.getSession().setAttribute(searchNameArr[i], null);
			}
		}
		
	  }	
      
	  
	  
	  private void setJCodeList(Map<String, Object> paramMap) throws Exception {
		  
		  List<EgovMap> floMemberList = frontLoginService.getFloMember(paramMap);
			
		  List<String> jCodeList = new ArrayList<String>();
			
		  for(EgovMap chulVO : floMemberList){
		
			  String jCode = String.valueOf(chulVO.get("chulCd"));
				EgovMap jInfo = frontFixNAucService.getJCodeToJInfo(jCode);
				
				if(jInfo.get("bunChk").equals("N")) {
					jCodeList.add(jInfo.get("code").toString());
				}
			}
			paramMap.put("jCodeList", jCodeList);
	  }
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  @RequestMapping(value = "/front/fixNAuc/getBoxList.json")
	  public String getBoxList(HttpServletRequest request, HttpServletResponse response,@RequestParam Map<String, Object> paramMap
			  		    , ModelMap model ) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"07".equals(aucType) && !"03".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			//출하자 코드리스트
			List<EgovMap> boxList = frontFixNAucService.getBoxList(paramMap);
			model.addAttribute("boxList", boxList);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		
		return "jsonView";
	  }
}
