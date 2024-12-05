package egovframework.front.fixCAuc.web;

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

































import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import egovframework.front.board.vo.FrontBoardFileVO;
import egovframework.front.content.service.FrontContentService;
import egovframework.front.content.service.FrontContentVO;
import egovframework.front.fixAuc.service.FrontFixAucRecordService;
import egovframework.front.fixCAuc.service.FrontFixCAucService;
import egovframework.front.login.service.FrontLoginService;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;

@Controller
public class FrontFixCAucController {
	private static final Logger logger = LoggerFactory.getLogger(FrontFixCAucController.class);

	@Resource(name = "frontFixCAucService")
	private FrontFixCAucService frontFixCAucService;
	
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
	
	@Resource(name="egovFixSubIdGnrService")
	private EgovIdGnrService egovFixSubIdGnrService;
	
	//상태 수정 이력
	@Resource(name = "FrontFixAucRecordService")
	private FrontFixAucRecordService frontFixAucRecordService;
	
	
	@RequestMapping(value="/front/fixCAuc/fixSellList.do")
	public String fixSellList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"01".equals(aucType) && !"02".equals(aucType) ){
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
				 * 관엽 => 화(3), 금(6)
				 */
				Calendar cal = Calendar.getInstance();
		        cal.setTime(date);
		        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		        if(dayOfWeek == 2 || dayOfWeek == 5){
		        	cal.add(Calendar.DATE, 1);
		        }else if(dayOfWeek == 1 || dayOfWeek == 4){
		        	cal.add(Calendar.DATE, 2);
		        }else if(dayOfWeek == 3 || dayOfWeek == 7){
		        	cal.add(Calendar.DATE, 3);
		        }else {
		        	cal.add(Calendar.DATE, 4);
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
			
			List<EgovMap> fixList = frontFixCAucService.getFixList(paramMap);
			
			for(EgovMap fixVO : fixList){
				String chulCode = String.valueOf(fixVO.get("chulCode"));
				EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
				if(chulInfo != null){
					fixVO.put("chulName", chulInfo.get("name"));
					fixVO.put("chulArea", chulInfo.get("chulArea"));
				}else{
					fixVO.put("chulName", "정보없음");
					fixVO.put("chulArea", "정보없음");
				}
			}
			
			int totCnt = frontFixCAucService.getFixListCnt(paramMap);
			
			paginationInfo.setTotalRecordCount(totCnt);
			
			
			
			//입찰 가능 시간
			paramMap.put("timeType", "3");
			EgovMap timeVO = frontFixCAucService.getTime(paramMap);
			
			//판매접수시간
			paramMap.put("timeType", "1");
			EgovMap regTimeVO = frontFixCAucService.getTime(paramMap);
			
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
		model.addAttribute("contentPath", "fixAuc/C/fixSellList.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	
	@RequestMapping(value="/front/fixCAuc/fixSellList.json")
	public String ajaxFixSellList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"01".equals(aucType) && !"02".equals(aucType) ){
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
			
			List<EgovMap> fixList = frontFixCAucService.getFixList(paramMap);
			
			for(EgovMap fixVO : fixList){
				String chulCode = String.valueOf(fixVO.get("chulCode"));
				EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
				if(chulInfo != null){
					fixVO.put("chulName", chulInfo.get("name"));
					fixVO.put("chulArea", chulInfo.get("chulArea"));
				}else{
					fixVO.put("chulName", "정보없음");
					fixVO.put("chulArea", "정보없음");
				}
			}
			
			int totCnt = frontFixCAucService.getFixListCnt(paramMap);
			
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
	
	@RequestMapping(value="/front/fixCAuc/fixSellView.do")
	public String fixSellView(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"01".equals(aucType) && !"02".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		paramMap.put("id", loginVO.get("loginId"));
		paramMap.put("memberId", loginVO.get("memberId"));
		
		
		EgovMap fixVO = frontFixCAucService.getFixArticle(paramMap);
		if(fixVO != null){
			
			String chulCode = String.valueOf(fixVO.get("chulCode"));
			EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				fixVO.put("chulName", chulInfo.get("name"));
				fixVO.put("chulArea", chulInfo.get("chulArea"));
			}else{
				fixVO.put("chulName", "정보없음");
				fixVO.put("chulArea", "정보없음");
			}
			List<EgovMap> fixFileList = frontFixCAucService.getFixFileList(paramMap);
			model.addAttribute("fixFileList", fixFileList);
			
			List<EgovMap> bidList = frontFixCAucService.getBidList(paramMap);
			model.addAttribute("bidList", bidList);
		}
		
		model.addAttribute("result", fixVO);
		
		model.addAttribute("title", "판매");
		model.addAttribute("contentPath", "fixAuc/C/fixSellView.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	@RequestMapping(value="/front/fixCAuc/fixSellReg.do")
	public String fixSellReg(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"01".equals(aucType) && !"02".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		paramMap.put("id", loginVO.get("loginId"));
		paramMap.put("memberId", loginVO.get("memberId"));
		
		
		//판매 접수 시간
		paramMap.put("timeType", "1");
		EgovMap timeVO = frontFixCAucService.getTime(paramMap);
		if(timeVO != null){
			if("N".equals(timeVO.get("timeCheck")) ){
				model.addAttribute("message", "판매접수시간이 아닙니다.");
				model.addAttribute("returnUrl", "/front/fixCAuc/fixSellList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/C/result";
			}
		}
		
		//출하자 코드리스트
		List<EgovMap> floMemberList = frontLoginService.getFloMember(paramMap);
		
		List<EgovMap> floList = new ArrayList<EgovMap>();
		
		for(EgovMap chulVO : floMemberList){
			String chulCode = String.valueOf(chulVO.get("chulCd"));
			EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				floList.add(chulInfo);
			}
			
		}
		
		model.addAttribute("floList", floList);
		
		//등급 코드 리스트
		paramMap.put("bunChk", "C");
		List<EgovMap> levelList = frontFixCAucService.getLevelList(paramMap);
		model.addAttribute("levelList", levelList);
		
		model.addAttribute("title", "판매");
		model.addAttribute("contentPath", "fixAuc/C/fixSellReg.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	@RequestMapping(value="/front/fixCAuc/fixSellRegProc.do")
	public String fixSellRegProc(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, MultipartHttpServletRequest multiRequest
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"01".equals(aucType) && !"02".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		//판매 접수 시간
		paramMap.put("timeType", "1");
		EgovMap timeVO = frontFixCAucService.getTime(paramMap);
		if(timeVO != null){
			if("N".equals(timeVO.get("timeCheck")) ){
				model.addAttribute("message", "판매접수시간이 아닙니다.");
				model.addAttribute("returnUrl", "/front/fixCAuc/fixSellList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/C/result";
			}
		}
		
		int result = 0;
		try {
			
			paramMap.put("fixDealSeq", egovFixIdGnrService.getNextIntegerId()); 
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			result = frontFixCAucService.fixSellRegProc(paramMap);
			
			//TB_RECORD 상태 변경 이력 Map
			Map<String, Object> recordMap = new HashMap<String, Object>();
			
			//TB_RECORD 상태 변경 이력 데이터 put
			recordMap.put("dealSeq", paramMap.get("fixDealSeq"));
			recordMap.put("fixType", "1");
			recordMap.put("bunChk", "C");
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
		    	  frontFixCAucService.fixSellFileProc(fileMap);
		    	  
		    	  
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
			model.addAttribute("returnUrl", "/front/fixCAuc/fixSellList.do");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = 0;
		}
		model.addAttribute("result", result);
		return "/front/fixAuc/C/result";
	}
	
	
	@RequestMapping(value="/front/fixCAuc/fixSellRegProc.json")
	public String fixSellRegProcAjax(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, MultipartHttpServletRequest multiRequest
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"01".equals(aucType) && !"02".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		//판매 접수 시간
		paramMap.put("timeType", "1");
		EgovMap timeVO = frontFixCAucService.getTime(paramMap);
		if(timeVO != null){
			if("N".equals(timeVO.get("timeCheck")) ){
				model.addAttribute("message", "판매접수시간이 아닙니다.");
				model.addAttribute("returnUrl", "/front/fixCAuc/fixSellList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/C/result";
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
			
			result = frontFixCAucService.fixSellRegProc(paramMap);
			
			//TB_RECORD 상태 변경 이력 Map
			Map<String, Object> recordMap = new HashMap<String, Object>();
			
			//TB_RECORD 상태 변경 이력 데이터 put
			recordMap.put("dealSeq", paramMap.get("fixDealSeq"));
			recordMap.put("fixType", "1");
			recordMap.put("bunChk", "C");
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
		    	  frontFixCAucService.fixSellFileProc(fileMap);
		    	  
		    	  
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
			model.addAttribute("returnUrl", "/front/fixCAuc/fixSellList.do");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = 0;
		}
		model.addAttribute("result", result);
		return "jsonView";
	}
	
	
	@RequestMapping(value="/front/fixCAuc/fixSellUpt.do")
	public String fixSellUpt(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"01".equals(aucType) && !"02".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		//판매 접수 시간
		paramMap.put("timeType", "1");
		EgovMap timeVO = frontFixCAucService.getTime(paramMap);
		if(timeVO != null){
			if("N".equals(timeVO.get("timeCheck")) ){
				model.addAttribute("message", "판매접수시간이 아닙니다.");
				model.addAttribute("returnUrl", "/front/fixCAuc/fixSellList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/C/result";
			}
		}
		
		paramMap.put("id", loginVO.get("loginId"));
		paramMap.put("memberId", loginVO.get("memberId"));
		
		EgovMap fixVO = frontFixCAucService.getFixArticle(paramMap);
		if(fixVO != null){
			
			if(!"1".equals(String.valueOf(fixVO.get("fixState"))) && !"2".equals(String.valueOf(fixVO.get("fixState"))) ){
				model.addAttribute("message", "비정상적인 접근입니다.");
				model.addAttribute("returnUrl", "/front/fixCAuc/fixSellList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/C/result";
			}
			
			String chulCode = String.valueOf(fixVO.get("chulCode"));
			EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				fixVO.put("chulName", chulInfo.get("name"));
				fixVO.put("chulArea", chulInfo.get("chulArea"));
			}else{
				fixVO.put("chulName", "정보없음");
				fixVO.put("chulArea", "정보없음");
			}
			List<EgovMap> fixFileList = frontFixCAucService.getFixFileList(paramMap);
			model.addAttribute("fixFileList", fixFileList);
			for(EgovMap file : fixFileList ){
				String fileSeq = String.valueOf(file.get("fileSeq"));
				model.addAttribute("file"+fileSeq, file);
			}
		}else{
			model.addAttribute("message", "비정상적인 접근입니다.");
			model.addAttribute("returnUrl", "/front/fixCAuc/fixSellList.do");
			model.addAttribute("result", 1);
			return "/front/fixAuc/C/result";
		}
		
		model.addAttribute("result", fixVO);
		
		//최근판매정보
		/*
		List<EgovMap> recentList = frontFixCAucService.getRecentList(paramMap);
		model.addAttribute("recentList", recentList);
		*/
		//출하자 코드리스트
		List<EgovMap> floMemberList = frontLoginService.getFloMember(paramMap);
		
		List<EgovMap> floList = new ArrayList<EgovMap>();
		
		for(EgovMap chulVO : floMemberList){
			String chulCode = String.valueOf(chulVO.get("chulCd"));
			EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				floList.add(chulInfo);
			}
		}
		
		model.addAttribute("floList", floList);
		
		//등급 코드 리스트
		paramMap.put("bunChk", "C");
		List<EgovMap> levelList = frontFixCAucService.getLevelList(paramMap);
		model.addAttribute("levelList", levelList);
		
		
		model.addAttribute("title", "판매");
		model.addAttribute("contentPath", "fixAuc/C/fixSellUpt.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	@RequestMapping(value="/front/fixCAuc/fixSellUptProc.do")
	public String fixSellUptProc(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, MultipartHttpServletRequest multiRequest
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"01".equals(aucType) && !"02".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		//판매 접수 시간
		paramMap.put("timeType", "1");
		EgovMap timeVO = frontFixCAucService.getTime(paramMap);
		if(timeVO != null){
			if("N".equals(timeVO.get("timeCheck")) ){
				model.addAttribute("message", "판매접수시간이 아닙니다.");
				model.addAttribute("returnUrl", "/front/fixCAuc/fixSellList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/C/result";
			}
		}
		
		
		int result = 0;
		
		
		try {
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			EgovMap fixVO = frontFixCAucService.getFixArticle(paramMap);
			if(fixVO != null){
				
				if(!"1".equals(String.valueOf(fixVO.get("fixState"))) && !"2".equals(String.valueOf(fixVO.get("fixState"))) ){
					model.addAttribute("message", "비정상적인 접근입니다.");
					model.addAttribute("returnUrl", "/front/fixCAuc/fixSellList.do");
					model.addAttribute("result", 1);
					return "/front/fixAuc/C/result";
				}
			}else{
				model.addAttribute("message", "비정상적인 접근입니다.");
				model.addAttribute("returnUrl", "/front/fixCAuc/fixSellList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/C/result";
			}	 
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));

			//TB_RECORD 상태 변경 이력 이전 상태 값
			String preFixState = frontFixAucRecordService.getPreFixState(String.valueOf(paramMap.get("fixDealSeq")));
			
			result = frontFixCAucService.fixSellUptProc(paramMap);
			
			//TB_RECORD 상태 변경 이력 map
			Map<String, Object> recordMap = new HashMap<String, Object>();
			
			//TB_RECORD 상태 변경 이력 데이터 put
			recordMap.put("dealSeq", paramMap.get("fixDealSeq"));
			recordMap.put("fixType", "1");
			recordMap.put("bunChk", "C");
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
			        frontFixCAucService.deleteFileOne(paramMap);
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
		    	  
		    	  EgovMap fileCheckVO = frontFixCAucService.getFileOne(fileMap);
		    	  if(fileCheckVO != null){
		    		  frontFixCAucService.deleteFileOne(fileMap);
		    	  }
		    	  
		    	  frontFixCAucService.fixSellFileProc(fileMap);
		    	  
		    	  
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
			model.addAttribute("returnUrl", "/front/fixCAuc/fixSellList.do");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = 0;
		}
		model.addAttribute("result", result);
		return "/front/fixAuc/C/result";
	}
	
	
	@RequestMapping(value="/front/fixCAuc/fixSellDelProc.do")
	public String fixSellDelProc(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"01".equals(aucType) && !"02".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		int result = 0;
		
		try {
		paramMap.put("id", loginVO.get("loginId"));
		paramMap.put("memberId", loginVO.get("memberId"));
		
		EgovMap fixVO = frontFixCAucService.getFixArticle(paramMap);
		if(fixVO != null){
			
			if(!"1".equals(String.valueOf(fixVO.get("fixState"))) && !"2".equals(String.valueOf(fixVO.get("fixState"))) ){
				model.addAttribute("message", "비정상적인 접근입니다.");
				model.addAttribute("returnUrl", "/front/fixCAuc/fixSellList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/C/result";
			}
		}else{
			model.addAttribute("message", "비정상적인 접근입니다.");
			model.addAttribute("returnUrl", "/front/fixCAuc/fixSellList.do");
			model.addAttribute("result", 1);
			return "/front/fixAuc/C/result";
		}
		
			//삭제 fixDealSeq
			String fixDealSeq = String.valueOf(paramMap.get("fixDealSeq"));
		
			//이전 상태 값
			String preFixState = frontFixAucRecordService.getPreFixState(fixDealSeq);		
		
			
			result = frontFixCAucService.fixSellDelProc(paramMap);
			
			if(result == 1){
				frontFixCAucService.fixSellFileDelProc(paramMap);
			}
			
			//상태 변경 이력 
			Map<String, Object> recordMap = new HashMap<String, Object>();
			
			recordMap.put("dealSeq", fixDealSeq);
			recordMap.put("fixType", "1");
			recordMap.put("bunChk", "C");
			recordMap.put("preFixState", preFixState);
			recordMap.put("id", paramMap.get("id"));
			
			//TB_RECORD 출하자 삭제 delete
			frontFixAucRecordService.fixAucRegRecord(recordMap);
						
			model.addAttribute("message", "정상적으로 삭제되었습니다.");
			model.addAttribute("returnUrl", "/front/fixCAuc/fixSellList.do");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = 0;
		}
		model.addAttribute("result", result);
		return "/front/fixAuc/C/result";
	}
	
	
	
	@RequestMapping(value="/front/fixCAuc/fixBuyList.do")
	public String fixBuyList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
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
			SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd");
			
			if(paramMap.get("searchStartDt") == null){
				// paramMap.put("searchStartDt",format.format(date));
				
				/*
				 * 채성주 [ 2022.01.10 ]
				 * 경매일자
				 * 관엽 => 화(3), 금(6)
				 * */
				Calendar cal = Calendar.getInstance();
		        cal.setTime(date);
		        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		        if(dayOfWeek == 2 || dayOfWeek == 5){
		        	cal.add(Calendar.DATE, 1);
		        }else if(dayOfWeek == 1 || dayOfWeek == 4){
		        	cal.add(Calendar.DATE, 2);
		        }else if(dayOfWeek == 3 || dayOfWeek == 7){
		        	cal.add(Calendar.DATE, 3);
		        }else {
		        	cal.add(Calendar.DATE, 4);
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
			List<EgovMap> fixList = frontFixCAucService.getFixBuyList(paramMap);
			
			for(EgovMap fixVO : fixList){
				String chulCode = String.valueOf(fixVO.get("chulCode"));
				EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
				if(chulInfo != null){
					fixVO.put("chulName", chulInfo.get("name"));
					fixVO.put("chulArea", chulInfo.get("chulArea"));
				}else{
					fixVO.put("chulName", "정보없음");
					fixVO.put("chulArea", "정보없음");
				}
			}
			
			int totCnt = frontFixCAucService.getFixBuyListCnt(paramMap);
			
			paginationInfo.setTotalRecordCount(totCnt);
			*/
			//중도매인 정보
			List<EgovMap> floMemberList = frontLoginService.getFloMember(paramMap);
			
			List<EgovMap> floList = new ArrayList<EgovMap>();
			
			for(EgovMap chulVO : floMemberList){
				String jCode = String.valueOf(chulVO.get("chulCd"));
				EgovMap jInfo = frontFixCAucService.getJCodeToJInfo(jCode);
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
			EgovMap timeVO = frontFixCAucService.getTime(paramMap);
			
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
		model.addAttribute("contentPath", "fixAuc/C/fixBuyList.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	
	@RequestMapping(value="/front/fixCAuc/fixBuyList.json")
	public String ajaxFixBuyList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
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
			
			List<EgovMap> fixList = frontFixCAucService.getFixBuyList(paramMap);
			
			for(EgovMap fixVO : fixList){
				String chulCode = String.valueOf(fixVO.get("chulCode"));
				EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
				if(chulInfo != null){
					fixVO.put("chulName", chulInfo.get("name"));
					fixVO.put("chulArea", chulInfo.get("chulArea"));
				}else{
					fixVO.put("chulName", "정보없음");
					fixVO.put("chulArea", "정보없음");
				}
				
				paramMap.put("fixDealSeq", fixVO.get("fixDealSeq"));
				EgovMap bidInfo = frontFixCAucService.getBidBuyInfo(paramMap);
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
			
			int totCnt = frontFixCAucService.getFixBuyListCnt(paramMap);
			
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
	
	
	@RequestMapping(value="/front/fixCAuc/fixBuyView.do")
	public String fixBuyView(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"03".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		
		paramMap.put("id", loginVO.get("loginId"));
		paramMap.put("memberId", loginVO.get("memberId"));
		
		
		EgovMap fixVO = frontFixCAucService.getFixBuyArticle(paramMap);
		if(fixVO != null){
			
			String chulCode = String.valueOf(fixVO.get("chulCode"));
			EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				fixVO.put("chulName", chulInfo.get("name"));
				fixVO.put("chulArea", chulInfo.get("chulArea"));
			}else{
				fixVO.put("chulName", "정보없음");
				fixVO.put("chulArea", "정보없음");
			}
			List<EgovMap> fixFileList = frontFixCAucService.getFixFileList(paramMap);
			model.addAttribute("fixFileList", fixFileList);
			
			//중도매인정보
			List<EgovMap> floMemberList = frontLoginService.getFloMember(paramMap);
			
			List<EgovMap> floList = new ArrayList<EgovMap>();
			
			for(EgovMap chulVO : floMemberList){
				String jCode = String.valueOf(chulVO.get("chulCd"));
				EgovMap jInfo = frontFixCAucService.getJCodeToJInfo(jCode);
				floList.add(jInfo);
				
				String code = String.valueOf(paramMap.get("jCode"));
				if(jCode.equals(code)){
					paramMap.put("code", code);
					model.addAttribute("code", code);
				}
			}
			
			model.addAttribute("floList", floList);
			
			/*
			EgovMap bidInfo = frontFixCAucService.getBidBuyInfo(paramMap);
			model.addAttribute("bidInfo", bidInfo);
			*/
			//입찰 가능 시간
			paramMap.put("timeType", "3");
			EgovMap timeVO = frontFixCAucService.getTime(paramMap);
			model.addAttribute("timeVO", timeVO);
			
		}
		
		model.addAttribute("result", fixVO);
		
		
		model.addAttribute("title", "구매");
		model.addAttribute("contentPath", "fixAuc/C/fixBuyView.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	
	@RequestMapping(value="/front/fixCAuc/reqSellList.do")
	public String reqSellList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"01".equals(aucType) && !"02".equals(aucType) ){
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
			 * 관엽 => 화(3), 금(6)
			 */
			Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
	        if(dayOfWeek == 2 || dayOfWeek == 5){
	        	cal.add(Calendar.DATE, 1);
	        }else if(dayOfWeek == 1 || dayOfWeek == 4){
	        	cal.add(Calendar.DATE, 2);
	        }else if(dayOfWeek == 3 || dayOfWeek == 7){
	        	cal.add(Calendar.DATE, 3);
	        }else {
	        	cal.add(Calendar.DATE, 4);
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
			EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				floList.add(chulInfo);
				chulCodeArr.add(String.valueOf(chulInfo.get("code")));
			}
		}
		
		model.addAttribute("floList", floList);
		
		paramMap.put("chulCodeArr", chulCodeArr);
		
		List<EgovMap> fixList = frontFixCAucService.getReqChulList(paramMap);
		
		for(EgovMap fixVO : fixList){
			String chulCode = String.valueOf(fixVO.get("chulCode"));
			EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				fixVO.put("chulName", chulInfo.get("name"));
				fixVO.put("chulArea", chulInfo.get("chulArea"));
			}else{
				fixVO.put("chulName", "정보없음");
				fixVO.put("chulArea", "정보없음");
			}
		}
		
		int totCnt = frontFixCAucService.getReqChulListCnt(paramMap);
		
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("paginationInfo", paginationInfo);
	    model.addAttribute("paginationQueryString", paginationQueryString);
		
		model.addAttribute("resultList", fixList);
		model.addAttribute("resultCnt", totCnt);
		model.addAttribute("paramMap", paramMap);
		
		model.addAttribute("title", "요청");
		model.addAttribute("contentPath", "fixAuc/C/reqSellList.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	
	@RequestMapping(value="/front/fixCAuc/reqSellList.json")
	public String ajaxReqSellList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"01".equals(aucType) && !"02".equals(aucType) ){
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
			EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				floList.add(chulInfo);
				chulCodeArr.add(String.valueOf(chulInfo.get("code")));
			}
		}
		
		model.addAttribute("floList", floList);
		
		paramMap.put("chulCodeArr", chulCodeArr);
		
		List<EgovMap> fixList = frontFixCAucService.getReqChulList(paramMap);
		
		for(EgovMap fixVO : fixList){
			String chulCode = String.valueOf(fixVO.get("chulCode"));
			EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				fixVO.put("chulName", chulInfo.get("name"));
				fixVO.put("chulArea", chulInfo.get("chulArea"));
			}else{
				fixVO.put("chulName", "정보없음");
				fixVO.put("chulArea", "정보없음");
			}
		}
		
		int totCnt = frontFixCAucService.getReqChulListCnt(paramMap);
		
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("paginationInfo", paginationInfo);
	    model.addAttribute("paginationQueryString", paginationQueryString);
		
		model.addAttribute("resultList", fixList);
		model.addAttribute("resultCnt", totCnt);
		model.addAttribute("paramMap", paramMap);
		
		return "jsonView";
	}
	
	
	@RequestMapping(value="/front/fixCAuc/reqSellView.do")
	public String reqSellView(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"01".equals(aucType) && !"02".equals(aucType) ){
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
			EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				floList.add(chulInfo);
				chulCodeArr.add(String.valueOf(chulInfo.get("code")));
			}
		}
		
		model.addAttribute("floList", floList);
		
		paramMap.put("chulCodeArr", chulCodeArr);
		
		
		EgovMap fixVO = frontFixCAucService.getReqChulArticle(paramMap);
		if(fixVO != null){
			
			String chulCode = String.valueOf(fixVO.get("chulCode"));
			EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
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
		model.addAttribute("contentPath", "fixAuc/C/reqSellView.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	@RequestMapping(value="/front/fixCAuc/reqBuyList.do")
	public String reqBuyList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
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
			 * 관엽 => 화(3), 금(6)
			 */
			Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
	        
	        if(dayOfWeek == 1) {
	        	cal.add(Calendar.DATE, 2);
	        }else if(dayOfWeek == 2) {
	        	cal.add(Calendar.DATE, 1);
	        }else if(dayOfWeek == 3) {
	        	cal.add(Calendar.DATE, 3);
	        }else if(dayOfWeek == 4) {
	        	cal.add(Calendar.DATE, 2);
	        }else if(dayOfWeek == 5) {
	        	cal.add(Calendar.DATE, 1);
	        }else if(dayOfWeek == 6) {
	        	cal.add(Calendar.DATE, 4);
	        }else if(dayOfWeek == 7) {
	        	cal.add(Calendar.DATE, 3);
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
			EgovMap jInfo = frontFixCAucService.getJCodeToJInfo(jCode);
			floList.add(jInfo);
			
			if(jInfo.get("bunChk").equals("C")) {
				jCodeList.add(jInfo.get("code").toString());
			}
		}
		model.addAttribute("floList", floList);
		paramMap.put("jCodeList", jCodeList);
		
		List<EgovMap> fixList = frontFixCAucService.getReqList(paramMap);
		
		for(EgovMap fixVO : fixList){
			String chulCode = String.valueOf(fixVO.get("chulCode"));
			EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				fixVO.put("chulName", chulInfo.get("name"));
				fixVO.put("chulArea", chulInfo.get("chulArea"));
			}else{
				fixVO.put("chulName", "정보없음");
				fixVO.put("chulArea", "정보없음");
			}
		}
		
		int totCnt = frontFixCAucService.getReqListCnt(paramMap);
		
		paginationInfo.setTotalRecordCount(totCnt);
		
		//요청접수시간
		paramMap.put("timeType", "2");
		EgovMap regTimeVO = frontFixCAucService.getTime(paramMap);
		
		model.addAttribute("paginationInfo", paginationInfo);
	    model.addAttribute("paginationQueryString", paginationQueryString);
		
		model.addAttribute("resultList", fixList);
		model.addAttribute("resultCnt", totCnt);
		model.addAttribute("regTimeVO", regTimeVO);
		model.addAttribute("paramMap", paramMap);
		
		
		
		model.addAttribute("title", "요청");
		model.addAttribute("contentPath", "fixAuc/C/reqBuyList.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	
	@RequestMapping(value="/front/fixCAuc/reqBuyList.json")
	public String ajaxReqBuyList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
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
			EgovMap jInfo = frontFixCAucService.getJCodeToJInfo(jCode);
			if(jInfo.get("bunChk").equals("C")) {
				jCodeList.add(jInfo.get("code").toString());
			}
		}
		
		paramMap.put("jCodeList", jCodeList);
		
		List<EgovMap> fixList = frontFixCAucService.getReqList(paramMap);
		
		for(EgovMap fixVO : fixList){
			String chulCode = String.valueOf(fixVO.get("chulCode"));
			EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				fixVO.put("chulName", chulInfo.get("name"));
				fixVO.put("chulArea", chulInfo.get("chulArea"));
			}else{
				fixVO.put("chulName", "정보없음");
				fixVO.put("chulArea", "정보없음");
			}
		}
		
		int totCnt = frontFixCAucService.getReqListCnt(paramMap);
		
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("paginationInfo", paginationInfo);
	    model.addAttribute("paginationQueryString", paginationQueryString);
		
		model.addAttribute("resultList", fixList);
		model.addAttribute("resultCnt", totCnt);
		model.addAttribute("paramMap", paramMap);
		
		
		return "jsonView";
	}
	
	@RequestMapping(value="/front/fixCAuc/reqBuyView.do")
	public String reqBuyView(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
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
		EgovMap fixVO = frontFixCAucService.getReqArticle(paramMap);
		if(fixVO != null){
			
			String chulCode = String.valueOf(fixVO.get("chulCode"));
			EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
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
		model.addAttribute("contentPath", "fixAuc/C/reqBuyView.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	@RequestMapping(value="/front/fixCAuc/reqBuyReg.do")
	public String reqBuyReg(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"03".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		//요청 접수 시간
		paramMap.put("timeType", "2");
		EgovMap timeVO = frontFixCAucService.getTime(paramMap);
		if(timeVO != null){
			if("N".equals(timeVO.get("timeCheck")) ){
				model.addAttribute("message", "요청접수시간이 아닙니다.");
				model.addAttribute("returnUrl", "/front/fixCAuc/reqBuyList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/C/result";
			}
		}
		
		paramMap.put("id", loginVO.get("loginId"));
		paramMap.put("memberId", loginVO.get("memberId"));
		
		//중도매인 정보
		List<EgovMap> floMemberList = frontLoginService.getFloMember(paramMap);
		
		List<EgovMap> floList = new ArrayList<EgovMap>();
		
		for(EgovMap chulVO : floMemberList){
			String jCode = String.valueOf(chulVO.get("chulCd"));
			EgovMap jInfo = frontFixCAucService.getJCodeToJInfo(jCode);
			floList.add(jInfo);
		}
		
		model.addAttribute("floList", floList);
		
		//등급 코드 리스트
		paramMap.put("bunChk", "C");
		List<EgovMap> levelList = frontFixCAucService.getLevelList(paramMap);
		model.addAttribute("levelList", levelList);
		
		if(paramMap.get("chulCode") != null){
			String chulCode = String.valueOf(paramMap.get("chulCode"));
			EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
			model.addAttribute("chulInfo", chulInfo);
			model.addAttribute("paramMap", paramMap);
		}
		
		model.addAttribute("title", "요청");
		model.addAttribute("contentPath", "fixAuc/C/reqBuyReg.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	
	
	@RequestMapping(value="/front/fixCAuc/reqBuyRegProc.do")
	public String reqBuyRegProc(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, MultipartHttpServletRequest multiRequest
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"03".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		//요청 접수 시간
		paramMap.put("timeType", "2");
		EgovMap timeVO = frontFixCAucService.getTime(paramMap);
		if(timeVO != null){
			if("N".equals(timeVO.get("timeCheck")) ){
				model.addAttribute("message", "요청접수시간이 아닙니다.");
				model.addAttribute("returnUrl", "/front/fixCAuc/reqBuyList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/C/result";
			}
		}
		
		int result = 0;
		try {
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			paramMap.put("code", paramMap.get("jCode"));
			EgovMap checkVO = frontFixCAucService.getFloMemberCheck(paramMap);
			if(checkVO != null){
				EgovMap limitVO = frontFixCAucService.getLimitAmt(paramMap);
				if(limitVO != null){
					int limitAmt = Integer.parseInt(String.valueOf(limitVO.get("limitAmt")));
					int sokCnt = Integer.parseInt(String.valueOf(paramMap.get("sokCnt")));
					int boxCnt = Integer.parseInt(String.valueOf(paramMap.get("boxCnt")));
					int unitPrice = Integer.parseInt(String.valueOf(paramMap.get("unitPrice")));
					System.out.println("limitAmt::::"+limitAmt);
					System.out.println("sokCnt*::::"+(sokCnt*unitPrice));
					
					if(paramMap.get("pUnit2").equals("상")) {
						limitAmt = limitAmt - (boxCnt*unitPrice);
					} else {
						limitAmt = limitAmt - (sokCnt*unitPrice);
					}
					
					if(limitAmt >= 0){
						paramMap.put("reqDealSeq", egovReqIdGnrService.getNextIntegerId()); 
						paramMap.put("id", loginVO.get("loginId"));
						paramMap.put("memberId", loginVO.get("memberId"));
						
						result = frontFixCAucService.reqBuyRegProc(paramMap);
						
						//TB_RECORD 상태 변경 이력 Map
						Map<String, Object> recordMap = new HashMap<String, Object>();
						
						//TB_RECORD 상태 변경 이력 데이터 put
						recordMap.put("dealSeq", paramMap.get("reqDealSeq"));
						recordMap.put("fixType", "2");
						recordMap.put("bunChk", "C");
						recordMap.put("fixState", "1");
						recordMap.put("id", paramMap.get("id"));
						
						//TB_RECORD 경매사 최초 등록 insert 진행(3)
						frontFixAucRecordService.fixAucRegRecord(recordMap);
						
						model.addAttribute("message", "정상적으로 저장되었습니다.");
						String returnType = String.valueOf(paramMap.get("returnType"));
						if(returnType != null){
							if("2".equals(returnType)){
								model.addAttribute("returnUrl", "/front/fixCAuc/reqBuyReg.do");
							}else{
								model.addAttribute("returnUrl", "/front/fixCAuc/reqBuyList.do");
							}
						}else{
							model.addAttribute("returnUrl", "/front/fixCAuc/reqBuyList.do");
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
		return "/front/fixAuc/C/result";
	}
	
	@RequestMapping(value="/front/fixCAuc/reqBuyUpt.do")
	public String reqBuyUpt(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"03".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		
		//요청 접수 시간
		paramMap.put("timeType", "2");
		EgovMap timeVO = frontFixCAucService.getTime(paramMap);
		if(timeVO != null){
			if("N".equals(timeVO.get("timeCheck")) ){
				model.addAttribute("message", "요청접수시간이 아닙니다.");
				model.addAttribute("returnUrl", "/front/fixCAuc/reqBuyList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/C/result";
			}
		}
		
		paramMap.put("id", loginVO.get("loginId"));
		paramMap.put("memberId", loginVO.get("memberId"));
		
		// param
		setJCodeList(paramMap);
		EgovMap fixVO = frontFixCAucService.getReqArticle(paramMap);
		if(fixVO != null){
			
			if(!"1".equals(String.valueOf(fixVO.get("fixState"))) && !"2".equals(String.valueOf(fixVO.get("fixState"))) ){
				model.addAttribute("message", "비정상적인 접근입니다.");
				model.addAttribute("returnUrl", "/front/fixCAuc/reqBuyList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/C/result";
			}
			
			String chulCode = String.valueOf(fixVO.get("chulCode"));
			EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				fixVO.put("chulName", chulInfo.get("name"));
				fixVO.put("chulArea", chulInfo.get("chulArea"));
			}else{
				fixVO.put("chulName", "정보없음");
				fixVO.put("chulArea", "정보없음");
			}
		}else{
			model.addAttribute("message", "비정상적인 접근입니다.");
			model.addAttribute("returnUrl", "/front/fixCAuc/reqBuyList.do");
			model.addAttribute("result", 1);
			return "/front/fixAuc/C/result";
		}
		
		model.addAttribute("result", fixVO);
		
		//중도매인 정보
		List<EgovMap> floMemberList = frontLoginService.getFloMember(paramMap);
		
		List<EgovMap> floList = new ArrayList<EgovMap>();
		
		for(EgovMap chulVO : floMemberList){
			String jCode = String.valueOf(chulVO.get("chulCd"));
			EgovMap jInfo = frontFixCAucService.getJCodeToJInfo(jCode);
			floList.add(jInfo);
		}
		
		model.addAttribute("floList", floList);
		
		//등급 코드 리스트
		paramMap.put("bunChk", "C");
		List<EgovMap> levelList = frontFixCAucService.getLevelList(paramMap);
		model.addAttribute("levelList", levelList);
		
		
		model.addAttribute("title", "요청");
		model.addAttribute("contentPath", "fixAuc/C/reqBuyUpt.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	@RequestMapping(value="/front/fixCAuc/reqBuyUptProc.do")
	public String reqBuyUptProc(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, MultipartHttpServletRequest multiRequest
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"03".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		//요청 접수 시간
		paramMap.put("timeType", "2");
		EgovMap timeVO = frontFixCAucService.getTime(paramMap);
		if(timeVO != null){
			if("N".equals(timeVO.get("timeCheck")) ){
				model.addAttribute("message", "요청접수시간이 아닙니다.");
				model.addAttribute("returnUrl", "/front/fixCAuc/reqBuyList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/C/result";
			}
		}
		
		
		int result = 0;
		
		
		try {
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			// param
			setJCodeList(paramMap);
			EgovMap fixVO = frontFixCAucService.getReqArticle(paramMap);
			if(fixVO != null){
				
				if(!"1".equals(String.valueOf(fixVO.get("fixState"))) && !"2".equals(String.valueOf(fixVO.get("fixState"))) ){
					model.addAttribute("message", "비정상적인 접근입니다.");
					model.addAttribute("returnUrl", "/front/fixCAuc/reqBuyList.do");
					model.addAttribute("result", 1);
					return "/front/fixAuc/C/result";
				}
			}else{
				model.addAttribute("message", "비정상적인 접근입니다.");
				model.addAttribute("returnUrl", "/front/fixCAuc/reqBuyList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/C/result";
			}
			
			
			paramMap.put("code", paramMap.get("jCode"));
			EgovMap checkVO = frontFixCAucService.getFloMemberCheck(paramMap);
			if(checkVO != null){
				EgovMap limitVO = frontFixCAucService.getLimitAmt(paramMap);
				if(limitVO != null){
					int limitAmt = Integer.parseInt(String.valueOf(limitVO.get("limitAmt")));
					int sokCnt = Integer.parseInt(String.valueOf(paramMap.get("sokCnt")));
					int boxCnt = Integer.parseInt(String.valueOf(paramMap.get("boxCnt")));
					int unitPrice = Integer.parseInt(String.valueOf(paramMap.get("unitPrice")));
					int reqSokCnt = Integer.parseInt(String.valueOf(fixVO.get("sokCnt")));
					int reqBoxCnt = Integer.parseInt(String.valueOf(fixVO.get("boxCnt")));
					int reqUnitPrice = Integer.parseInt(String.valueOf(fixVO.get("unitPrice")));
					
					System.out.println("limitAmt::::"+limitAmt);
					System.out.println("sokCnt*::::"+(sokCnt*unitPrice));
					System.out.println("sokCnt*::::"+(reqSokCnt*reqUnitPrice));
					System.out.println("sokCnt*::::"+((sokCnt*unitPrice)-(reqSokCnt*reqUnitPrice)));
					
					if(paramMap.get("pUnit2").equals("상")) {
						if(paramMap.get("originPUnit2").equals("상")) {
							limitAmt = limitAmt - ((boxCnt*unitPrice)-(reqBoxCnt*reqUnitPrice));
						} else {
							limitAmt = limitAmt - ((boxCnt*unitPrice)-(reqSokCnt*reqUnitPrice));
						}
					} else {
						if(paramMap.get("originPUnit2").equals("상")) {
							limitAmt = limitAmt - ((sokCnt*unitPrice)-(reqBoxCnt*reqUnitPrice));
						} else {
							limitAmt = limitAmt - ((sokCnt*unitPrice)-(reqSokCnt*reqUnitPrice));
						}
					}
					
					if(limitAmt >= 0){
						
						result = frontFixCAucService.reqBuyUptProc(paramMap);
						
						model.addAttribute("message", "정상적으로 저장되었습니다.");
						model.addAttribute("returnUrl", "/front/fixCAuc/reqBuyList.do");
						
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
		return "/front/fixAuc/C/result";
	}
	
	
	@RequestMapping(value="/front/fixCAuc/reqBuyDelProc.do")
	public String reqBuyDelProc(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"03".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		int result = 0;
		
		//상태 변경 이력 
		Map<String, Object> recordMap = new HashMap<String, Object>();
		String preFixState = "";
		
		try {
		paramMap.put("id", loginVO.get("loginId"));
		paramMap.put("memberId", loginVO.get("memberId"));
		
		// param
		setJCodeList(paramMap);
		EgovMap fixVO = frontFixCAucService.getReqArticle(paramMap);
		if(fixVO != null){
			
			if(!"1".equals(String.valueOf(fixVO.get("fixState"))) && !"2".equals(String.valueOf(fixVO.get("fixState"))) ){
				model.addAttribute("message", "비정상적인 접근입니다.");
				model.addAttribute("returnUrl", "/front/fixCAuc/reqBuyList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/C/result";
			}
		}else{
			model.addAttribute("message", "비정상적인 접근입니다.");
			model.addAttribute("returnUrl", "/front/fixCAuc/reqBuyList.do");
			model.addAttribute("result", 1);
			return "/front/fixAuc/C/result";
		}
		
		
			//이전 상태 값
			preFixState = frontFixAucRecordService.getPreReqState(String.valueOf(paramMap.get("reqDealSeq")));
			
			result = frontFixCAucService.reqBuyDelProc(paramMap);
			
			//TB_RECORD 상태 변경 이력 데이터 put
			recordMap.put("dealSeq", paramMap.get("reqDealSeq"));
			recordMap.put("fixType", "2");
			recordMap.put("bunChk", "C");
			recordMap.put("preFixState", String.valueOf(preFixState));
			recordMap.put("id", paramMap.get("id"));
			
			//TB_RECORD 경매사 삭제 delete
			frontFixAucRecordService.fixAucRegRecord(recordMap);
			
			
			model.addAttribute("message", "정상적으로 삭제되었습니다.");
			model.addAttribute("returnUrl", "/front/fixCAuc/reqBuyList.do");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = 0;
		}
		model.addAttribute("result", result);
		return "/front/fixAuc/C/result";
	}
	
	
	@RequestMapping(value="/front/fixCAuc/fixAdmList.do")
	public String fixAdmList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
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
				 * 관엽 => 화(3), 금(6)
				 */
				Calendar cal = Calendar.getInstance();
		        cal.setTime(date);
		        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		        if(dayOfWeek == 2 || dayOfWeek == 5){
		        	cal.add(Calendar.DATE, 1);
		        }else if(dayOfWeek == 1 || dayOfWeek == 4){
		        	cal.add(Calendar.DATE, 2);
		        }else if(dayOfWeek == 3 || dayOfWeek == 7){
		        	cal.add(Calendar.DATE, 3);
		        }else {
		        	cal.add(Calendar.DATE, 4);
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
					/* 정렬 유지 추가 1 (S) - 채성주 [ 2021.11.17 ]*/
					paramMap.put("tableOrder",seachVO.get("tableOrder"));
					/* 정렬 유지 추가 1 (E) */
				}
				/* 정렬 유지 수정 1 (S) - 채성주 [ 2021.11.26 ] */
				else if(referer.contains("fixCAuc/fixAdmList.do")) {
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
			
			List<EgovMap> fixList = frontFixCAucService.getFixAdmList(paramMap);
			/*
			for(EgovMap fixVO : fixList){
				String chulCode = String.valueOf(fixVO.get("chulCode"));
				EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
				if(chulInfo != null){
					fixVO.put("chulName", chulInfo.get("name"));
					fixVO.put("chulArea", chulInfo.get("chulArea"));
				}else{
					fixVO.put("chulName", "정보없음");
					fixVO.put("chulArea", "정보없음");
				}
			}*/
			
			int totCnt = frontFixCAucService.getFixAdmListCnt(paramMap);
			
			paginationInfo.setTotalRecordCount(totCnt);
			
			
			//입찰 가능 시간
			paramMap.put("timeType", "3");
			EgovMap timeVO = frontFixCAucService.getTime(paramMap);
			
			//판매접수시간
			paramMap.put("timeType", "1");
			EgovMap regTimeVO = frontFixCAucService.getTime(paramMap);
			
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
		model.addAttribute("contentPath", "fixAuc/C/fixAdmList.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	
	/* 정렬 유지 추가 2 (S) - 채성주 [ 2021.11.17 ] */
	@RequestMapping(value="/front/fixCAuc/tableOrder.json")
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
	
	
	@RequestMapping(value="/front/fixCAuc/fixAdmList.json")
	public String ajaxFixAdmList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
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
			
			List<EgovMap> fixList = frontFixCAucService.getFixAdmList(paramMap);
			
			for(EgovMap fixVO : fixList){
				String chulCode = String.valueOf(fixVO.get("chulCode"));
				EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
				if(chulInfo != null){
					fixVO.put("chulName", chulInfo.get("name"));
					fixVO.put("chulArea", chulInfo.get("chulArea"));
				}else{
					fixVO.put("chulName", "정보없음");
					fixVO.put("chulArea", "정보없음");
				}
			}
			
			int totCnt = frontFixCAucService.getFixAdmListCnt(paramMap);
			
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
	
	@RequestMapping(value="/front/fixCAuc/fixAdmView.do")
	public String fixAdmView(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"07".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		
		EgovMap fixVO = frontFixCAucService.getFixAdmArticle(paramMap);
		if(fixVO != null){
			
			String chulCode = String.valueOf(fixVO.get("chulCode"));
			EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				fixVO.put("chulName", chulInfo.get("name"));
				fixVO.put("chulArea", chulInfo.get("chulArea"));
				fixVO.put("phoneNo", chulInfo.get("phoneNo"));
			}else{
				fixVO.put("chulName", "정보없음");
				fixVO.put("chulArea", "정보없음");
				fixVO.put("phoneNo", "정보없음");
			}
			
			List<EgovMap> fixFileList = frontFixCAucService.getFixFileList(paramMap);
			model.addAttribute("fixFileList", fixFileList);
			
			String dealType = String.valueOf(fixVO.get("dealType"));
			int remainSokCnt = Integer.parseInt(String.valueOf(fixVO.get("sokCnt")));
			
			List<EgovMap> bidList = new ArrayList<EgovMap>();
			
			// 정가 : 등록시간순 / 최저가 : 금액순, 등록시간순 / 희망가: 금액순, 등록시간순
			if("F".equals(dealType)){
				//정가
				//입찰정보 가져오기
				paramMap.put("bidOrder", "F");
				bidList = frontFixCAucService.getBidList(paramMap);
				
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
				
				bidList = frontFixCAucService.getBidList(paramMap);
				
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
			
			List<EgovMap> bidCancelList = frontFixCAucService.getBidCancelList(paramMap);
			
			for(EgovMap bidVO : bidCancelList){
				bidVO.put("nakSokCnt",0);
				bidVO.put("nakState", "5");
				bidList.add(bidVO);
			}
			
			model.addAttribute("bidList", bidList);
			
			//TB_RECORD 상태 변경 이력 리스트
			paramMap.put("fixType", "1");
			paramMap.put("bunChk", "C");
			paramMap.put("dealSeq", paramMap.get("fixDealSeq"));
			List<EgovMap> recordList = frontFixAucRecordService.getFixRecordList(paramMap);
			model.addAttribute("recordList", recordList);
				
		}
		
		model.addAttribute("result", fixVO);
		
		model.addAttribute("title", "판매관리");
		model.addAttribute("contentPath", "fixAuc/C/fixAdmView.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	@RequestMapping(value="/front/fixCAuc/fixAdmReg.do")
	public String fixAdmReg(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
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
		paramMap.put("bunChk", "C");
		List<EgovMap> levelList = frontFixCAucService.getLevelList(paramMap);
		model.addAttribute("levelList", levelList);
		
		
		model.addAttribute("title", "판매관리");
		model.addAttribute("contentPath", "fixAuc/C/fixAdmReg.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	
	@RequestMapping(value="/front/fixCAuc/fixAdmRegProc.do")
	public String fixAdmRegProc(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, MultipartHttpServletRequest multiRequest
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
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
			
			EgovMap chulVO = frontFixCAucService.getCodeToLoginId(paramMap);
			
			if(chulVO != null){
				paramMap.put("chulId", chulVO.get("loginId"));
			}
			
			
			result = frontFixCAucService.fixAdmRegProc(paramMap);
			
			//TB_RECORD 상태 변경 이력 Map
			Map<String, Object> recordMap = new HashMap<String, Object>();
			
			//TB_RECORD 상태 변경 이력 데이터 put
			recordMap.put("dealSeq", paramMap.get("fixDealSeq"));
			recordMap.put("fixType", "1");
			recordMap.put("bunChk", "C");
			recordMap.put("fixState", "3");
			recordMap.put("id", paramMap.get("id"));
			
			//TB_RECORD 상태 변경 이력 - 경매사 최초 등록 (3)
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
		    	  frontFixCAucService.fixSellFileProc(fileMap);
		    	  
		    	  
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
			model.addAttribute("returnUrl", "/front/fixCAuc/fixAdmList.do");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = 0;
		}
		model.addAttribute("result", result);
		return "/front/fixAuc/C/result";
	}
	
	@RequestMapping(value="/front/fixCAuc/fixAdmUpt.do")
	public String fixAdmUpt(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"07".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		paramMap.put("id", loginVO.get("loginId"));
		paramMap.put("memberId", loginVO.get("memberId"));
		
		
		EgovMap fixVO = frontFixCAucService.getFixAdmArticle(paramMap);
		if(fixVO != null){
			
			if(!"1".equals(String.valueOf(fixVO.get("fixState"))) && !"2".equals(String.valueOf(fixVO.get("fixState"))) ){
				model.addAttribute("message", "비정상적인 접근입니다.");
				model.addAttribute("returnUrl", "/front/fixCAuc/fixAdmList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/C/result";
			}
			
			String chulCode = String.valueOf(fixVO.get("chulCode"));
			EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				fixVO.put("chulName", chulInfo.get("name"));
				fixVO.put("chulArea", chulInfo.get("chulArea"));
			}else{
				fixVO.put("chulName", "정보없음");
				fixVO.put("chulArea", "정보없음");
			}
			List<EgovMap> fixFileList = frontFixCAucService.getFixFileList(paramMap);
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
			EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				floList.add(chulInfo);
			}
		}
		
		model.addAttribute("floList", floList);
		
		//등급 코드 리스트
		paramMap.put("bunChk", "C");
		List<EgovMap> levelList = frontFixCAucService.getLevelList(paramMap);
		model.addAttribute("levelList", levelList);
		
		
		model.addAttribute("title", "판매");
		model.addAttribute("contentPath", "fixAuc/C/fixAdmUpt.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	@RequestMapping(value="/front/fixCAuc/fixAdmUptProc.do")
	public String fixAdmUptProc(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, MultipartHttpServletRequest multiRequest
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
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
			
			EgovMap chulVO = frontFixCAucService.getCodeToLoginId(paramMap);
			
			if(chulVO != null){
				paramMap.put("chulId", chulVO.get("loginId"));
			}
			
			EgovMap fixVO = frontFixCAucService.getFixAdmArticle(paramMap);
			if(fixVO != null){
				
				if(!"1".equals(String.valueOf(fixVO.get("fixState"))) && !"2".equals(String.valueOf(fixVO.get("fixState"))) ){
					model.addAttribute("message", "비정상적인 접근입니다.");
					model.addAttribute("returnUrl", "/front/fixCAuc/fixAdmList.do");
					model.addAttribute("result", 1);
					return "/front/fixAuc/C/result";
				}
			}	 
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			//TB_RECORD 상태 변경 이력 map 
			Map<String, Object> recordMap = new HashMap<String, Object>();
			String preFixState = frontFixAucRecordService.getPreFixState(String.valueOf(paramMap.get("fixDealSeq")));
			
			result = frontFixCAucService.fixAdmUptProc(paramMap);
			
			//TB_RECORD 상태 변경 이력 데이터 put
			recordMap.put("dealSeq", paramMap.get("fixDealSeq"));
			recordMap.put("fixType", "1");
			recordMap.put("bunChk", "C");
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
			        frontFixCAucService.deleteFileOne(paramMap);
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
		    	  
		    	  EgovMap fileCheckVO = frontFixCAucService.getFileOne(fileMap);
		    	  if(fileCheckVO != null){
		    		  frontFixCAucService.deleteFileOne(fileMap);
		    	  }
		    	  
		    	  frontFixCAucService.fixSellFileProc(fileMap);
		    	  
		    	  
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
			model.addAttribute("returnUrl", "/front/fixCAuc/fixAdmList.do");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = 0;
		}
		model.addAttribute("result", result);
		return "/front/fixAuc/C/result";
	}
	
	
	@RequestMapping(value="/front/fixCAuc/fixAdmDelProc.do")
	public String fixAdmDelProc(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
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
		
		EgovMap fixVO = frontFixCAucService.getFixAdmArticle(paramMap);
		if(fixVO != null){
			
			if(!"1".equals(String.valueOf(fixVO.get("fixState"))) && !"2".equals(String.valueOf(fixVO.get("fixState"))) ){
				model.addAttribute("message", "비정상적인 접근입니다.");
				model.addAttribute("returnUrl", "/front/fixCAuc/fixAdmList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/C/result";
			}
		}
		
			//삭제 fixDealSeq
			String fixDealSeq = String.valueOf(paramMap.get("fixDealSeq"));
			
			//TB_RECORD 상태 변경 이력 이전 상태 값
			String preFixState = frontFixAucRecordService.getPreFixState(fixDealSeq);
				
			result = frontFixCAucService.fixAdmDelProc(paramMap);
			
			if(result == 1){
				frontFixCAucService.fixSellFileDelProc(paramMap);
			}
			
			//TB_RECORD 상태 변경 이력 map
			Map<String, Object> recordMap = new HashMap<String, Object>();
			
			//TB_RECORD 상태 변경 이력 데이터 put
			recordMap.put("dealSeq", fixDealSeq);
			recordMap.put("fixType", "1");
			recordMap.put("bunChk", "C");
			recordMap.put("preFixState", preFixState);
			recordMap.put("id", paramMap.get("id"));
			
			//TB_RECORD 경매사 삭제 delete
			frontFixAucRecordService.fixAucRegRecord(recordMap);
			
			
			model.addAttribute("message", "정상적으로 삭제되었습니다.");
			model.addAttribute("returnUrl", "/front/fixCAuc/fixAdmList.do");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = 0;
		}
		model.addAttribute("result", result);
		return "/front/fixAuc/C/result";
	}
	
	
	@RequestMapping(value="/front/fixCAuc/reqAdmList.do")
	public String reqAdmList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
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
				paramMap.put("searchStartDt",format.format(date));
				/*
				Calendar cal = Calendar.getInstance();
		        cal.setTime(date);
		        cal.add(Calendar.DATE, -6);
				paramMap.put("searchStartDt",format.format(cal.getTime()));*/
			}
			/*
			if(paramMap.get("searchEndDt") == null){
				paramMap.put("searchEndDt",format.format(date));
			}*/
			/*신청일자 -> 다음 경매일자로 변경 20210127*/
			if(paramMap.get("searchEndDt") == null){
				Calendar cal = Calendar.getInstance();
		        cal.setTime(date);
		        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		        if(dayOfWeek == 2 || dayOfWeek == 5){
		        	cal.add(Calendar.DATE, 1);
		        }else if(dayOfWeek == 1 || dayOfWeek == 4){
		        	cal.add(Calendar.DATE, 2);
		        }else if(dayOfWeek == 3 || dayOfWeek == 7){
		        	cal.add(Calendar.DATE, 3);
		        }else {
		        	cal.add(Calendar.DATE, 4);
		        }
		        
				paramMap.put("searchEndDt",format.format(cal.getTime()));
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
					/* 정렬 유지 추가 3 (S) - 채성주 [ 2021.11.17 ] */
					paramMap.put("tableOrder",seachVO.get("tableOrder"));
					/* 정렬 유지 추가 3 (E) */
				}
				/* 정렬 유지 수정 2 (S) - 채성주 [ 2021.11.26 ] */
				else if(referer.contains("fixCAuc/reqAdmList.do")) {
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
			
			List<EgovMap> fixList = frontFixCAucService.getReqAdmList(paramMap);
			/*
			for(EgovMap fixVO : fixList){
				String chulCode = String.valueOf(fixVO.get("chulCode"));
				EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
				if(chulInfo != null){
					fixVO.put("chulName", chulInfo.get("name"));
					fixVO.put("chulArea", chulInfo.get("chulArea"));
				}else{
					fixVO.put("chulName", "정보없음");
					fixVO.put("chulArea", "정보없음");
				}
			}*/
			
			int totCnt = frontFixCAucService.getReqAdmListCnt(paramMap);
			
			paginationInfo.setTotalRecordCount(totCnt);
			
			
			//입찰 가능 시간
			paramMap.put("timeType", "3");
			EgovMap timeVO = frontFixCAucService.getTime(paramMap);
			
			//요청접수시간
			paramMap.put("timeType", "2");
			EgovMap regTimeVO = frontFixCAucService.getTime(paramMap);
			
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
		model.addAttribute("contentPath", "fixAuc/C/reqAdmList.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	
	@RequestMapping(value="/front/fixCAuc/reqAdmList.json")
	public String ajaxReqAdmList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
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
			
			List<EgovMap> fixList = frontFixCAucService.getReqAdmList(paramMap);
			
			for(EgovMap fixVO : fixList){
				String chulCode = String.valueOf(fixVO.get("chulCode"));
				EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
				if(chulInfo != null){
					fixVO.put("chulName", chulInfo.get("name"));
					fixVO.put("chulArea", chulInfo.get("chulArea"));
				}else{
					fixVO.put("chulName", "정보없음");
					fixVO.put("chulArea", "정보없음");
				}
			}
			
			int totCnt = frontFixCAucService.getReqAdmListCnt(paramMap);
			
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
	
	
	@RequestMapping(value="/front/fixCAuc/reqAdmView.do")
	public String reqAdmView(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"07".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		EgovMap fixVO = frontFixCAucService.getReqAdmArticle(paramMap);
		if(fixVO != null){
			
			String chulCode = String.valueOf(fixVO.get("chulCode"));
			EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
			
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
			paramMap.put("bunChk", "C");
			paramMap.put("dealSeq", paramMap.get("reqDealSeq"));
			List<EgovMap> recordList = frontFixAucRecordService.getFixRecordList(paramMap);
			model.addAttribute("recordList", recordList);
		}
		
		model.addAttribute("result", fixVO);
		
		
		model.addAttribute("title", "요청관리");
		model.addAttribute("contentPath", "fixAuc/C/reqAdmView.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	@RequestMapping(value="/front/fixCAuc/reqAdmReg.do")
	public String reqAdmReg(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
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
		paramMap.put("bunChk", "C");
		List<EgovMap> levelList = frontFixCAucService.getLevelList(paramMap);
		model.addAttribute("levelList", levelList);
		
		
		model.addAttribute("title", "요청관리");
		model.addAttribute("contentPath", "fixAuc/C/reqAdmReg.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	
	@RequestMapping(value="/front/fixCAuc/reqAdmRegProc.do")
	public String reqAdmRegProc(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, MultipartHttpServletRequest multiRequest
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
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
			
			EgovMap limitVO = frontFixCAucService.getLimitAmt(paramMap);
			if(limitVO != null){
				int limitAmt = Integer.parseInt(String.valueOf(limitVO.get("limitAmt")));
				int sokCnt = Integer.parseInt(String.valueOf(paramMap.get("sokCnt")));
				int unitPrice = Integer.parseInt(String.valueOf(paramMap.get("unitPrice")));
				if(limitAmt >= (sokCnt*unitPrice)){
					paramMap.put("reqDealSeq", egovReqIdGnrService.getNextIntegerId()); 
					paramMap.put("id", loginVO.get("loginId"));
					paramMap.put("memberId", loginVO.get("memberId"));
					
					EgovMap chulCheck = frontFixCAucService.getCodeToLoginId(paramMap);
					
					if(chulCheck != null){
						paramMap.put("jId", chulCheck.get("loginId"));
					}
					
					result = frontFixCAucService.reqAdmRegProc(paramMap);
					
					//TB_RECORD 상태 변경 이력 Map
					Map<String, Object> recordMap = new HashMap<String, Object>();
					
					//TB_RECORD 상태 변경 이력 데이터 put
					recordMap.put("dealSeq", paramMap.get("reqDealSeq"));
					recordMap.put("fixType", "2");
					recordMap.put("bunChk", "C");
					recordMap.put("fixState", "3");
					recordMap.put("id", paramMap.get("id"));
					
					//TB_RECORD 경매사 최초 등록 (3)
					frontFixAucRecordService.fixAucRegRecord(recordMap);
					
					
					model.addAttribute("message", "정상적으로 저장되었습니다.");
					model.addAttribute("returnUrl", "/front/fixCAuc/reqAdmList.do");
					
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
		return "/front/fixAuc/C/result";
	}
	
	@RequestMapping(value="/front/fixCAuc/reqAdmRegProc.json")
	public String reqAdmRegProcAjax(HttpServletRequest request
			, HttpServletResponse response
			, @RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
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
			
			EgovMap limitVO = frontFixCAucService.getLimitAmt(paramMap);
			if(limitVO != null){
				int limitAmt = Integer.parseInt(String.valueOf(limitVO.get("limitAmt")));
				int sokCnt = Integer.parseInt(String.valueOf(paramMap.get("sokCnt")));
				int unitPrice = Integer.parseInt(String.valueOf(paramMap.get("unitPrice")));
				if(limitAmt >= (sokCnt*unitPrice)){
					paramMap.put("reqDealSeq", egovReqIdGnrService.getNextIntegerId()); 
					paramMap.put("id", loginVO.get("loginId"));
					paramMap.put("memberId", loginVO.get("memberId"));
					
					EgovMap chulCheck = frontFixCAucService.getCodeToLoginId(paramMap);
					
					if(chulCheck != null){
						paramMap.put("jId", chulCheck.get("loginId"));
					}
					
					result = frontFixCAucService.reqAdmRegProc(paramMap);
					
					//TB_RECORD 상태 변경 이력 Map
					Map<String, Object> recordMap = new HashMap<String, Object>();
					
					//TB_RECORD 상태 변경 이력 데이터 put
					recordMap.put("dealSeq", paramMap.get("reqDealSeq"));
					recordMap.put("fixType", "2");
					recordMap.put("bunChk", "C");
					recordMap.put("fixState", "3");
					recordMap.put("id", paramMap.get("id"));
					
					//TB_RECORD 경매사 최초 등록 insert 진행(3)
					frontFixAucRecordService.fixAucRegRecord(recordMap);
					
					model.addAttribute("message", "정상적으로 저장되었습니다.");
					model.addAttribute("returnUrl", "/front/fixCAuc/reqAdmList.do");
					
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
	
	@RequestMapping(value="/front/fixCAuc/reqAdmUpt.do")
	public String reqAdmUpt(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"07".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		
		paramMap.put("id", loginVO.get("loginId"));
		paramMap.put("memberId", loginVO.get("memberId"));
		
		EgovMap fixVO = frontFixCAucService.getReqAdmArticle(paramMap);
		if(fixVO != null){
			/*
			if(!"1".equals(String.valueOf(fixVO.get("fixState"))) && !"2".equals(String.valueOf(fixVO.get("fixState"))) ){
				model.addAttribute("message", "비정상적인 접근입니다.");
				model.addAttribute("returnUrl", "/front/fixCAuc/reqAdmList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/C/result";
			}*/
			
			String chulCode = String.valueOf(fixVO.get("chulCode"));
			EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				fixVO.put("chulName", chulInfo.get("name"));
				fixVO.put("chulArea", chulInfo.get("chulArea"));
			}else{
				fixVO.put("chulName", "정보없음");
				fixVO.put("chulArea", "정보없음");
			}
		}else{
			model.addAttribute("message", "비정상적인 접근입니다.");
			model.addAttribute("returnUrl", "/front/fixCAuc/reqAdmList.do");
			model.addAttribute("result", 1);
			return "/front/fixAuc/C/result";
		}
		
		model.addAttribute("result", fixVO);
		
		//등급 코드 리스트
		paramMap.put("bunChk", "C");
		List<EgovMap> levelList = frontFixCAucService.getLevelList(paramMap);
		model.addAttribute("levelList", levelList);
		
		
		model.addAttribute("title", "요청관리");
		model.addAttribute("contentPath", "fixAuc/C/reqAdmUpt.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	@RequestMapping(value="/front/fixCAuc/reqAdmUptProc.do")
	public String reqAdmUptProc(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, MultipartHttpServletRequest multiRequest
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
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
			
			EgovMap fixVO = frontFixCAucService.getReqAdmArticle(paramMap);
			if(fixVO != null){
				/*
				if(!"1".equals(String.valueOf(fixVO.get("fixState"))) && !"2".equals(String.valueOf(fixVO.get("fixState"))) ){
					model.addAttribute("message", "비정상적인 접근입니다.");
					model.addAttribute("returnUrl", "/front/fixCAuc/reqAdmList.do");
					model.addAttribute("result", 1);
					return "/front/fixAuc/C/result";
				}*/
			}else{
				model.addAttribute("message", "비정상적인 접근입니다.");
				model.addAttribute("returnUrl", "/front/fixCAuc/reqAdmList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/C/result";
			}
			
			
			paramMap.put("code", paramMap.get("jCode"));
			
			EgovMap chulCheck = frontFixCAucService.getCodeToLoginId(paramMap);
			
			if(chulCheck != null){
				paramMap.put("jId", chulCheck.get("loginId"));
			}
			
			
			EgovMap limitVO = frontFixCAucService.getLimitAmt(paramMap);
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
					
					result = frontFixCAucService.reqAdmUptProc(paramMap);
					
					model.addAttribute("message", "정상적으로 저장되었습니다.");
					model.addAttribute("returnUrl", "/front/fixCAuc/reqAdmList.do");
					
				}else {
					
					if(limitAmt >= ((sokCnt*unitPrice)-(reqSokCnt*reqUnitPrice))){
						
						result = frontFixCAucService.reqAdmUptProc(paramMap);
						
						model.addAttribute("message", "정상적으로 저장되었습니다.");
						model.addAttribute("returnUrl", "/front/fixCAuc/reqAdmList.do");
						
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
		return "/front/fixAuc/C/result";
	}
	
	
	@RequestMapping(value="/front/fixCAuc/reqAdmDelProc.do")
	public String reqAdmDelProc(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
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
		
		
		try {
		paramMap.put("id", loginVO.get("loginId"));
		paramMap.put("memberId", loginVO.get("memberId"));
		
		EgovMap fixVO = frontFixCAucService.getReqAdmArticle(paramMap);
		if(fixVO != null){
			
			if(!"1".equals(String.valueOf(fixVO.get("fixState"))) && !"2".equals(String.valueOf(fixVO.get("fixState"))) ){
				model.addAttribute("message", "비정상적인 접근입니다.");
				model.addAttribute("returnUrl", "/front/fixCAuc/reqAdmList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/C/result";
			}
		}
		
			//TB_RECORD 상태 변경 이력 이전 상태 값
			preFixState = frontFixAucRecordService.getPreReqState(String.valueOf(paramMap.get("reqDealSeq")));
		
		
			result = frontFixCAucService.reqAdmDelProc(paramMap);
			
			//TB_RECORD 상태 변경 이력 데이터 put
			recordMap.put("dealSeq", paramMap.get("reqDealSeq"));
			recordMap.put("fixType", "2");
			recordMap.put("bunChk", "C");
			recordMap.put("preFixState", String.valueOf(preFixState));
			recordMap.put("id", paramMap.get("id"));
			
			//TB_RECORD 경매사 삭제 delete
			frontFixAucRecordService.fixAucRegRecord(recordMap);
			
			
			model.addAttribute("message", "정상적으로 삭제되었습니다.");
			model.addAttribute("returnUrl", "/front/fixCAuc/reqAdmList.do");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = 0;
		}
		model.addAttribute("result", result);
		return "/front/fixAuc/C/result";
	}
	
	
	
	@RequestMapping(value="/front/fixCAuc/time.do")
	public String time(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		try {
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");

			String aucType = String.valueOf(loginVO.get("aucType"));
			if(!"07".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
				throw new ModelAndViewDefiningException(modelAndView);
			}
			List<EgovMap> fixTimeList = frontFixCAucService.getFixAucTime(paramMap);
			model.addAttribute("fixTimeList",fixTimeList);
			model.addAttribute("title", "시간관리");
			model.addAttribute("contentPath", "fixAuc/C/time.jsp");
		}catch (Exception e) {
			e.printStackTrace();
		}

		return "/front/layout/fixLayout";
	}
	
	@RequestMapping(value = "/front/fixCAuc/updateTime.json")
	  public String updateTime(HttpServletRequest request, @RequestParam Map<String,Object> paramMap
			  		    , ModelMap model) throws Exception{
	    
		
		try {
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
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
				frontFixCAucService.updatetime(setParam);
				model.addAttribute("status","sucess");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("status","error");
			e.printStackTrace();
		}
	   
		
		return "jsonView";
	  }
	
	
	
	@RequestMapping(value = "/front/fixCAuc/moklist.json")
	  public String moklist(@RequestParam Map<String, Object> paramMap
			  		    , ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());  
			List<EgovMap> mokList = frontFixCAucService.getMokList(paramMap);
			model.addAttribute("results", mokList);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		
		return "jsonView";
	  }
	
	
	@RequestMapping(value = "/front/fixCAuc/pumlist.json")
	  public String pumlist(@RequestParam Map<String, Object> paramMap
			  		    , ModelMap model) throws Exception{
	    
		try {
			List<EgovMap> pumList = frontFixCAucService.getPumList(paramMap);
			model.addAttribute("results", pumList);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		
		return "jsonView";
	  }
	
	@RequestMapping(value = "/front/fixCAuc/getPrePrice.json")
	  public String getPrePrice(@RequestParam Map<String, Object> paramMap
			  		    , ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			List<EgovMap> preList = frontFixCAucService.getPrePrice(paramMap);
			model.addAttribute("result", preList);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		
		return "jsonView";
	  }
	
	
	@RequestMapping(value = "/front/fixCAuc/getRecentList.json")
	  public String getRecentList(HttpServletRequest request, HttpServletResponse response,@RequestParam Map<String, Object> paramMap
			  		    , ModelMap model ) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			//최근판매정보
			/*
			List<EgovMap> recentList = frontFixCAucService.getRecentList(paramMap);
			model.addAttribute("recentList", recentList);
			*/
			//출하자 코드리스트
			String aucType = String.valueOf(loginVO.get("aucType"));
			if("07".equals(aucType) ){
				List<EgovMap> recentList = frontFixCAucService.getRecentList(paramMap);
				model.addAttribute("recentList", recentList);
			}else{
				List<EgovMap> floMemberList = frontLoginService.getFloMember(paramMap);
				
				for(EgovMap chulVO : floMemberList){
					String chulCode = String.valueOf(chulVO.get("chulCd"));
					String paramChulCode = String.valueOf(paramMap.get("chulCode"));
					if(chulCode.equals(paramChulCode)){
						List<EgovMap> recentList = frontFixCAucService.getRecentList(paramMap);
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
	
	
	@RequestMapping(value = "/front/fixCAuc/getJRecentList.json")
	  public String getJRecentList(HttpServletRequest request, HttpServletResponse response,@RequestParam Map<String, Object> paramMap
			  		    , ModelMap model ) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			if("07".equals(aucType) ){
				List<EgovMap> recentList = frontFixCAucService.getAdmJRecentList(paramMap);
				model.addAttribute("recentList", recentList);
			}else{
				//중도매인 코드리스트
				List<EgovMap> floMemberList = frontLoginService.getFloMember(paramMap);
				
				for(EgovMap chulVO : floMemberList){
					String chulCode = String.valueOf(chulVO.get("chulCd"));
					String paramJCode = String.valueOf(paramMap.get("jCode"));
					if(chulCode.equals(paramJCode)){
						List<EgovMap> recentList = frontFixCAucService.getJRecentList(paramMap);
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
	
	@RequestMapping(value = "/front/fixCAuc/getCRecentList.json")
	  public String getCRecentList(HttpServletRequest request, HttpServletResponse response,@RequestParam Map<String, Object> paramMap
			  		    , ModelMap model ) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			if("07".equals(aucType) || "03".equals(aucType)){
				List<EgovMap> recentList = frontFixCAucService.getCRecentList(paramMap);
				model.addAttribute("recentList", recentList);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		
		return "jsonView";
	  }
	
	
	@RequestMapping(value = "/front/fixCAuc/getLimitAmt.json")
	  public String getLimitAmt(HttpServletRequest request, HttpServletResponse response,
			  	@RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			if("07".equals(aucType) ){
				EgovMap limitVO = frontFixCAucService.getLimitAmt(paramMap);
				model.addAttribute("result", limitVO);
			}else{
			EgovMap checkVO = frontFixCAucService.getFloMemberCheck(paramMap);
				if(checkVO != null){
					EgovMap limitVO = frontFixCAucService.getLimitAmt(paramMap);
					model.addAttribute("result", limitVO);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		
		return "jsonView";
	  }
	
	
	@RequestMapping(value = "/front/fixCAuc/getBidBuyInfo.json")
	  public String getBidBuyInfo(HttpServletRequest request, HttpServletResponse response,
			  	@RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			EgovMap checkVO = frontFixCAucService.getFloMemberCheck(paramMap);
			if(checkVO != null){
				EgovMap bidCheckVO = frontFixCAucService.getBidBuyInfo(paramMap);
				model.addAttribute("result", bidCheckVO);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		
		return "jsonView";
	  }
	
	
	
	
	@RequestMapping(value = "/front/fixCAuc/fixBuyBid.json")
	  public String fixBuyBid(HttpServletRequest request, HttpServletResponse response,
			  	@RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
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
			
			EgovMap bidCheckVO =  frontFixCAucService.getBidBuyInfo(paramMap);
			
			if(bidCheckVO != null){
				if("Y".equals(String.valueOf(bidCheckVO.get("bidAdminYn")) ) ){
					
				}else{
					result = 5;
					model.addAttribute("result",result);
					return "jsonView";
					//입찰 가능 시간
					/*
					paramMap.put("timeType", "3");
					EgovMap timeVO = frontFixCAucService.getTime(paramMap);
					if(timeVO != null){
						if("N".equals(timeVO.get("timeCheck")) ){
							result = 4;
							model.addAttribute("result",result);
							return "jsonView";
						}
					}*/
				}
			}else{
				//입찰 가능 시간
				/*
				paramMap.put("timeType", "3");
				EgovMap timeVO = frontFixCAucService.getTime(paramMap);
				if(timeVO != null){
					if("N".equals(timeVO.get("timeCheck")) ){
						result = 4;
						model.addAttribute("result",result);
						return "jsonView";
					}
				}*/
				EgovMap fixVO = frontFixCAucService.getFixBuyArticle(paramMap);
				
				if(fixVO != null){
					if(!"7".equals(fixVO.get("fixState")) ){
						result = 4;
						model.addAttribute("result",result);
						return "jsonView";
					}
				}
			}
			
			
			
			EgovMap checkVO = frontFixCAucService.getFloMemberCheck(paramMap);
			if(checkVO != null){
				EgovMap limitVO = frontFixCAucService.getLimitAmt(paramMap);
				if(limitVO != null){
					int limitAmt = Integer.parseInt(String.valueOf(limitVO.get("limitAmt")));
					int bidSokCnt = Integer.parseInt(String.valueOf(paramMap.get("bidSokCnt")));
					int bidBoxCnt = Integer.parseInt(String.valueOf(paramMap.get("bidBoxCnt")));
					int bidUnitPrice = Integer.parseInt(String.valueOf(paramMap.get("bidUnitPrice")));
					//EgovMap bidCheckVO =  frontFixCAucService.getBidBuyInfo(paramMap);
					int bidedSokCnt = 0;
					int bidedBoxCnt = 0;
					int bidedUnitPrice = 0; 
					if(bidCheckVO != null){
						bidedSokCnt = Integer.parseInt(String.valueOf(bidCheckVO.get("bidSokCnt")));
						bidedBoxCnt = Integer.parseInt(String.valueOf(bidCheckVO.get("bidBoxCnt")));
						bidedUnitPrice = Integer.parseInt(String.valueOf(bidCheckVO.get("bidUnitPrice")));
						
					}
					
					if(paramMap.get("resultPUnit").equals("상")) {
						limitAmt = limitAmt - ((bidBoxCnt*bidUnitPrice)-(bidedBoxCnt*bidedUnitPrice));
					} else {
						limitAmt = limitAmt - ((bidSokCnt*bidUnitPrice)-(bidedSokCnt*bidedUnitPrice));
					}
					
					if(limitAmt >= 0){
						
						EgovMap fixVO = frontFixCAucService.getFixBuyArticle(paramMap);
						
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
								frontFixCAucService.updateBid(paramMap);
								result = 2;
							}else{
								// 응찰 입력
								paramMap.put("bidSeq", egovBidIdGnrService.getNextIntegerId());
								frontFixCAucService.insertBid(paramMap);
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
	
	
	
	  @RequestMapping(value = "/front/fixCAuc/fixAdmApply.json")
	  public String fixAdmApply(HttpServletRequest request
			      , HttpServletResponse response
			      , @RequestParam(value="checkedArray[]") List<String> checkedArray
			      , @RequestParam(value="aucDateArray[]") List<String> aucDateArray
			  	  , @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
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
				int cnt = frontFixCAucService.fixAdmApply(paramMap);
				
				//TB_RECORD 상태 변경 이력 데이터 put
				recordMap.put("dealSeq", fixDealSeq);
				recordMap.put("fixType", "1");
				recordMap.put("bunChk", "C");
				recordMap.put("preFixState", preFixState);
				recordMap.put("fixState", "3");
				recordMap.put("id", paramMap.get("id"));
				
				//TB_RECORD 경매사 승인 (1->3)
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
	  
	  
	  @RequestMapping(value = "/front/fixCAuc/fixAdmAppCancel.json")
	  public String fixAdmAppCancel(HttpServletRequest request
			      , HttpServletResponse response
			      , @RequestParam(value="checkedArray[]") List<String> checkedArray
			      , @RequestParam(value="aucDateArray[]") List<String> aucDateArray
			  	  , @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
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
				int cnt = frontFixCAucService.fixAdmAppCancel(paramMap);
				
				//TB_RECORD 상태 변경 이력 데이터 put
				recordMap.put("dealSeq", fixDealSeq);
				recordMap.put("fixType", "1");
				recordMap.put("bunChk", "C");
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
	  
	  @RequestMapping(value = "/front/fixCAuc/fixAdmComp.json")
	  public String fixAdmComp(HttpServletRequest request
			      , HttpServletResponse response
			      , @RequestParam(value="checkedArray[]") List<String> checkedArray
			      , @RequestParam(value="aucDateArray[]") List<String> aucDateArray
			  	  , @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
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
				EgovMap fixVO = frontFixCAucService.getFixAdmArticle(paramMap);
				int sokCnt = Integer.parseInt(String.valueOf(fixVO.get("sokCnt")));
				int unitPrice = Integer.parseInt(String.valueOf(fixVO.get("unitPrice")));
				String dealType = String.valueOf(fixVO.get("dealType"));
				int remainSokCnt = sokCnt;
				//입찰 된 속/분 수량
				int bidTotSokCnt = frontFixCAucService.getBidTotSokCnt(paramMap);
				
				if(bidTotSokCnt == 0 ){
					//유찰
					int cnt = frontFixCAucService.fixAdmUChal(paramMap);
					
					//유찰 상태
					fixState = "5";
					
					if(cnt > 0){
						result++;
					}
					
				}else if(bidTotSokCnt > 0 && sokCnt > bidTotSokCnt){
					//부분유찰
					int cnt = frontFixCAucService.fixAdmPartUChal(paramMap);
					
					//부분 유찰 상태
					fixState = "6";
					
					if(cnt > 0){
						result++;
					}
					frontFixCAucService.fixAdmBidTotSuc(paramMap);
				}else if(bidTotSokCnt > 0 && sokCnt == bidTotSokCnt){
					//판매완료
					int cnt = frontFixCAucService.fixAdmComp(paramMap);
					
					//완료 상태
					fixState = "4";
					
					if(cnt > 0){
						result++;
					}
					frontFixCAucService.fixAdmBidTotSuc(paramMap);
				}else{
					//패찰자 확인
					// 정가 : 등록시간순 / 최저가 : 금액순, 등록시간순 / 희망가: 금액순, 등록시간순
					if("F".equals(dealType)){
						//정가
						//입찰정보 가져오기
						paramMap.put("bidOrder", "F");
						List<EgovMap> bidList = frontFixCAucService.getBidList(paramMap);
						
						for(int j=0; j<bidList.size(); j++ ){
							EgovMap bidVO = bidList.get(j);
							paramMap.put("bidSeq",bidVO.get("bidSeq"));
							int bidSokCnt = Integer.parseInt(String.valueOf(bidVO.get("bidSokCnt")));
							int preRemainSokCnt = remainSokCnt;
							
							remainSokCnt = (remainSokCnt - bidSokCnt);
							
							if(remainSokCnt>=0){
								//낙찰 : 2
								frontFixCAucService.fixAdmBidSuc(paramMap);
							}else if(remainSokCnt<0 && preRemainSokCnt>0){
								//부분 낙찰 : 4
								paramMap.put("nakSokCnt",preRemainSokCnt);
								frontFixCAucService.fixAdmBidPartSuc(paramMap);
							}else{
								//패찰 : 3
								frontFixCAucService.fixAdmBidFail(paramMap);
							}
							
						}
						
					}else{
						//최저가, 희망가
						paramMap.put("bidOrder", "L");
						
						List<EgovMap> bidList = frontFixCAucService.getBidList(paramMap);
						
						for(int j=0; j<bidList.size(); j++ ){
							EgovMap bidVO = bidList.get(j);
							paramMap.put("bidSeq",bidVO.get("bidSeq"));
							int bidSokCnt = Integer.parseInt(String.valueOf(bidVO.get("bidSokCnt")));
							int preRemainSokCnt = remainSokCnt;
							
							remainSokCnt = (remainSokCnt - bidSokCnt);
							
							if(remainSokCnt>=0){
								//낙찰 : 2
								frontFixCAucService.fixAdmBidSuc(paramMap);
							}else if(remainSokCnt<0 && preRemainSokCnt>0){
								//부분 낙찰 : 4
								paramMap.put("nakSokCnt",preRemainSokCnt);
								frontFixCAucService.fixAdmBidPartSuc(paramMap);
							}else{
								//패찰 : 3
								frontFixCAucService.fixAdmBidFail(paramMap);
							}
							
						}
						
					}
					
					
					int cnt = frontFixCAucService.fixAdmComp(paramMap);
					
					//완료 상태
					fixState = "4";
					
					if(cnt > 0){
						result++;
					}
					
					
				}
				
				//TB_RECORD 상태 변경 이력 데이터 put
				recordMap.put("dealSeq", fixDealSeq);
				recordMap.put("fixType", "1");
				recordMap.put("bunChk", "C");
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
	  
	  
	  @RequestMapping(value = "/front/fixCAuc/fixAdmBan.json")
	  public String fixAdmBan(HttpServletRequest request
			      , HttpServletResponse response
			      , @RequestParam(value="checkedArray[]") List<String> checkedArray
			      , @RequestParam(value="banTextArray[]") List<String> banTextArray
			  	  , @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
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
				int cnt = frontFixCAucService.fixAdmBan(paramMap);
				
				//TB_RECORD 상태 변경 이력 데이터 put
				recordMap.put("dealSeq", fixDealSeq);
				recordMap.put("fixType", "1");
				recordMap.put("bunChk", "C");
				recordMap.put("preFixState", preFixState);
				recordMap.put("fixState", "2");
				recordMap.put("id", paramMap.get("id"));
				
				//TB_RECORD 경매사 반려 (1,3->2)
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
	  
	  
	  @RequestMapping(value = "/front/fixCAuc/reqAdmApply.json")
	  public String reqAdmApply(HttpServletRequest request
			      , HttpServletResponse response
			      , @RequestParam(value="checkedArray[]") List<String> checkedArray
			      , @RequestParam(value="aucPriceArray[]") List<String> aucPriceArray
			  	  , @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
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
				int cnt = frontFixCAucService.reqAdmApply(paramMap);
				
				//TB_RECORD 상태 변경 이력 데이터 put
				recordMap.put("dealSeq", reqDealSeq);
				recordMap.put("fixType", "2");
				recordMap.put("bunChk", "C");
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
	  
	  
	  @RequestMapping(value = "/front/fixCAuc/reqAdmAppCancel.json")
	  public String reqAdmAppCancel(HttpServletRequest request
			      , HttpServletResponse response
			      , @RequestParam(value="checkedArray[]") List<String> checkedArray
			      , @RequestParam(value="aucPriceArray[]") List<String> aucPriceArray
			  	  , @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
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
				int cnt = frontFixCAucService.reqAdmAppCancel(paramMap);
				
				//TB_RECORD 상태 변경 이력 데이터 put
				recordMap.put("dealSeq", reqDealSeq);
				recordMap.put("fixType", "2");
				recordMap.put("bunChk", "C");
				recordMap.put("preFixState", preFixState);
				recordMap.put("fixState", "1");
				recordMap.put("id", paramMap.get("id"));
				
				//TB_RECORD 경매사 진행취소 insert (3->1)
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
	  
	  @RequestMapping(value = "/front/fixCAuc/reqAdmComp.json")
	  public String reqAdmComp(HttpServletRequest request
			      , HttpServletResponse response
			      , @RequestParam(value="checkedArray[]") List<String> checkedArray
			      , @RequestParam(value="aucPriceArray[]") List<String> aucPriceArray
			  	  , @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
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
				int cnt = frontFixCAucService.reqAdmComp(paramMap);
				
				//TB_RECORD 상태 변경 이력 데이터 put
				recordMap.put("dealSeq", reqDealSeq);
				recordMap.put("fixType", "2");
				recordMap.put("bunChk", "C");
				recordMap.put("preFixState", preFixState);
				recordMap.put("fixState", "4");
				recordMap.put("id", paramMap.get("id"));
				
				//TB_RECORD 경매사 체결 insert (3->4)
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
	  
	  
	  @RequestMapping(value = "/front/fixCAuc/reqAdmBan.json")
	  public String reqAdmBan(HttpServletRequest request
			      , HttpServletResponse response
			      , @RequestParam(value="checkedArray[]") List<String> checkedArray
			      , @RequestParam(value="banTextArray[]") List<String> banTextArray
			  	  , @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
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
				int cnt = frontFixCAucService.reqAdmBan(paramMap);
				
				//TB_RECORD 상태 변경 이력 데이터 put
				recordMap.put("dealSeq", reqDealSeq);
				recordMap.put("fixType", "2");
				recordMap.put("bunChk", "C");
				recordMap.put("preFixState", preFixState);
				recordMap.put("fixState", "2");
				recordMap.put("id", paramMap.get("id"));
				
				//TB_RECORD 경매사 미체결  (1,3->2)
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
	  
	  
	  @RequestMapping(value = "/front/fixCAuc/fixAdmBidAdmin.json")
	  public String fixAdmBidAdmin(HttpServletRequest request
			      , HttpServletResponse response
			      , @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"07".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			int result = frontFixCAucService.fixAdmBidAdmin(paramMap);
			
			model.addAttribute("result",result);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		return "jsonView";
	  }
	  
	  
	  @RequestMapping(value = "/front/fixCAuc/fixAdmBidCancel.json")
	  public String fixAdmBidCancel(HttpServletRequest request
			      , HttpServletResponse response
			      , @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"07".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			int result = frontFixCAucService.fixAdmBidCancel(paramMap);
			
			model.addAttribute("result",result);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		return "jsonView";
	  }
	  
	  
	  @RequestMapping(value = "/front/fixCAuc/fixAdmBidUpdate.json")
	  public String fixAdmBidUpdate(HttpServletRequest request
			      , HttpServletResponse response
			      , @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"07".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			int result = frontFixCAucService.fixAdmBidUpdate(paramMap);
			
			model.addAttribute("result",result);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		return "jsonView";
	  }
	  
	  @RequestMapping(value = "/front/fixCAuc/reqSellComp.json")
	  public String reqChulComp(HttpServletRequest request
			      , HttpServletResponse response
			      , @RequestParam(value="checkedArray[]") List<String> checkedArray
			  	  , @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"01".equals(aucType) && !"02".equals(aucType) ){
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
				EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
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
								
				int cnt = frontFixCAucService.reqChulComp(paramMap);
				
				//TB_RECORD 상태 변경 이력 데이터 put
				recordMap.put("dealSeq", reqDealSeq);
				recordMap.put("fixType", "2");
				recordMap.put("bunChk", "C");
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
	  
	  
	  @RequestMapping(value = "/front/fixCAuc/reqSellBan.json")
	  public String reqChulBan(HttpServletRequest request
			      , HttpServletResponse response
			      , @RequestParam(value="checkedArray[]") List<String> checkedArray
			      , @RequestParam(value="banTextArray[]") List<String> banTextArray
			  	  , @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"01".equals(aucType) && !"02".equals(aucType) ){
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
				EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
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
				int cnt = frontFixCAucService.reqChulBan(paramMap);
				
				//TB_RECORD 상태 변경 이력 데이터 put
				recordMap.put("dealSeq", reqDealSeq);
				recordMap.put("fixType", "2");
				recordMap.put("bunChk", "C");
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
	  
	  
	
	  
	  @RequestMapping(value = "/front/fixCAuc/getChulList.json")
	  public String getChulList(HttpServletRequest request, HttpServletResponse response,@RequestParam Map<String, Object> paramMap
			  		    , ModelMap model ) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
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
			List<EgovMap> chulList = frontFixCAucService.getChulList(paramMap);
			model.addAttribute("chulList", chulList);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		
		return "jsonView";
	  }
	  
	  
	  @RequestMapping(value = "/front/fixCAuc/fixSmsProc.json")
	  public String fixSmsProc(HttpServletRequest request
			       , HttpServletResponse response
			       , @RequestParam Map<String, Object> paramMap
			       , @RequestParam(value="banArray[]", required=false) String banArray
			       , @RequestParam(value="applyArray[]", required=false) String applyArray
			       , @RequestParam(value="bidStrArray[]", required=false) String bidStrArray
			       , @RequestParam(value="dealCompArray[]", required=false) String dealCompArray
			       , ModelMap model ) throws Exception{
	    
		try {
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"07".equals(aucType) && !"03".equals(aucType) ){
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
			System.out.println(paramMap.toString());
			//출하자 코드리스트
			String vMsg = frontFixCAucService.fixSmsProc(paramMap);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return "jsonView";
	  }
	  
	  
	  @RequestMapping(value = "/front/fixCAuc/reqSmsProc.json")
	  public String reqSmsProc(HttpServletRequest request
			       , HttpServletResponse response
			       , @RequestParam Map<String, Object> paramMap
			       , @RequestParam(value="banArray[]", required=false) String banArray
			       , @RequestParam(value="applyArray[]", required=false) String applyArray
			       , @RequestParam(value="dealDisCompArray[]", required=false) String dealDisCompArray
			       , @RequestParam(value="dealCompArray[]", required=false) String dealCompArray
			       , ModelMap model ) throws Exception{
	    
		try {
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
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
			String vMsg = frontFixCAucService.reqSmsProc(paramMap);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return "jsonView";
	  }
	  
	  @RequestMapping(value = "/front/fixCAuc/sendSmsMms.json")
	  public String sendSmsMms(HttpServletRequest request
			       , HttpServletResponse response
			       , @RequestParam Map<String, Object> paramMap
			       , ModelMap model ) throws Exception{
	    
		try {
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
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
				recInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
			}else{
				//중도매인
				String jCode = String.valueOf(paramMap.get("code"));
				recInfo = frontFixCAucService.getJCodeToJInfo(jCode);
			}
			
			
			if(recInfo != null){
				int sendCount = 0;
				int msgByte = frontFixCAucService.messageBiteLength(paramMap);
				paramMap.put("phoneNo", recInfo.get("phoneNo"));
				if(msgByte<90){
					//sms 보내기
					sendCount = frontFixCAucService.sendSms(paramMap);
				}else if(msgByte >= 90 && msgByte < 4000){
					//mms 보내기
					sendCount = frontFixCAucService.sendMms(paramMap);
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
	  
	  
	  
	  
	  
	  
	  @RequestMapping(value = "/front/fixCAuc/getJList.json")
	  public String getJList(HttpServletRequest request, HttpServletResponse response,@RequestParam Map<String, Object> paramMap
			  		    , ModelMap model ) throws Exception{
	    
		try {
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			if(!"07".equals(aucType)){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			//출하자 코드리스트
			List<EgovMap> jList = frontFixCAucService.getJList(paramMap);
			model.addAttribute("jList", jList);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		
		return "jsonView";
	  }
	
	
	
	private List<FileVO> uploadThumbFile(ModelMap model, MultipartHttpServletRequest multiRequest) throws Exception{
		
		String mode = EgovProperties.getProperty("Globals.Mode");
		String savePath = EgovProperties.getProperty("Globals.DstbtPath"+mode);
		
	    String uploadPath = savePath + "/uploads/fixAuc/C/";
		
	    String atchFileId = "-1";
	    List atchFile = multiRequest.getFiles("attachFile");

	    List atchFileList = null;
	    try {
	      atchFileList = egovFileMngUtil.parseFileInf(atchFile, "", 0, atchFileId, uploadPath, "gif,jpg,jpeg,png,bmp", 20);
	    } catch (Exception e) {
	      model.addAttribute("message", e.getMessage());
	      e.printStackTrace();
	    }

	    return atchFileList;
	  }
	
	
	// 관엽 중도매인 요청표 
	
	@RequestMapping(value="/front/fixCAuc/reqAdmTable.do")
	public String reqAdmTable(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"07".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		
		try {
			
			
			
			Map<String, Object> param = new HashMap<String, Object>();
		    param.putAll(paramMap);
		    param.put("pageIndex", null);
		    
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			List<EgovMap> fixList = frontFixCAucService.getReqAdmTable(paramMap);
			
			/*
			for(EgovMap fixVO : fixList){
				String chulCode = String.valueOf(fixVO.get("chulCode"));
				EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
				if(chulInfo != null){
					fixVO.put("chulName", chulInfo.get("name"));
					fixVO.put("chulArea", chulInfo.get("chulArea"));
				}else{
					fixVO.put("chulName", "정보없음");
					fixVO.put("chulArea", "정보없음");
				}
			}*/
			
			int totCnt = frontFixCAucService.getReqAdmTableCnt(paramMap);
			
			
			//입찰 가능 시간
			paramMap.put("timeType", "3");
			EgovMap timeVO = frontFixCAucService.getTime(paramMap);
			
			//요청접수시간
			paramMap.put("timeType", "2");
			EgovMap regTimeVO = frontFixCAucService.getTime(paramMap);
		
			
			model.addAttribute("resultList", fixList);
			model.addAttribute("resultCnt", totCnt);
			model.addAttribute("timeVO", timeVO);
			model.addAttribute("regTimeVO", regTimeVO);
			model.addAttribute("paramMap", paramMap);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		model.addAttribute("title", "중도매인 요청표");
		model.addAttribute("contentPath", "fixAuc/C/reqAdmTable.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	 @RequestMapping(value = "/front/fixCAuc/fixAdmTableUptProc.json")
	  public String fixAdmTableUptProc(HttpServletRequest request
			      , HttpServletResponse response
			      , @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"07".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			int result = frontFixCAucService.updateReaDealTable(paramMap);
			
			model.addAttribute("result",result);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		return "jsonView";
	  }
	 
	 
	 @RequestMapping(value = "/front/fixCAuc/reqAdmTableComp.json")
	  public String reqAdmTableComp(HttpServletRequest request
			      , HttpServletResponse response
			      , @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"07".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			int compresult = frontFixCAucService.updateReaDealTableComp(paramMap);
			int disresult = frontFixCAucService.updateReaDealTableDisComp(paramMap);
			
			model.addAttribute("compresult",compresult);
			model.addAttribute("disresult",disresult);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		return "jsonView";
	  }
	
	 
	@RequestMapping(value="/front/fixCAuc/reqAdmTableExcell.do")
		public void reqAdmTableExcell(
				  @RequestParam Map<String, Object> paramMap
				, HttpServletResponse response
				, HttpServletRequest request
				, ModelMap model) throws Exception{
		 
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"07".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		 List<EgovMap> list = frontFixCAucService.getReqAdmTable(paramMap);
		 	
		 SXSSFWorkbook workbook = new SXSSFWorkbook();
		 int tableInitRow = 5;
		// 시트 생성
	        SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("중도매인요청표");
	        
	        //시트 열 너비 설정
	        sheet.setColumnWidth(0, 3000);
	        sheet.setColumnWidth(1, 3000);
	        sheet.setColumnWidth(2, 3000);
	        sheet.setColumnWidth(3, 3000);
	        sheet.setColumnWidth(4, 3000);
	        sheet.setColumnWidth(5, 3000);
	        sheet.setColumnWidth(6, 3000);
	        sheet.setColumnWidth(7, 6000);
	        sheet.setColumnWidth(8, 1500);
	        sheet.setColumnWidth(9, 1500);
	        sheet.setColumnWidth(10, 2000);
	        sheet.setColumnWidth(11, 2000);
	        sheet.setColumnWidth(12, 2000);
	        sheet.setColumnWidth(13, 2500);
	        sheet.setColumnWidth(14, 5000);
	        
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
	        titleCell.setCellValue("중도매인 요청표");
	        
	        
	        titleCell.setCellStyle(titleCellStyle);
	        
	        
	        sheet.addMergedRegion(new CellRangeAddress(1, 3, 0, 14));

	        
	        // 헤더 행 생
	        Row headerRow = sheet.createRow(tableInitRow);
	        
	        
	       
	        Cell headerCell = headerRow.createCell(0);
	        
	        headerCell.setCellValue("번호");
	        headerCell.setCellStyle(headerCellStyle);
	        
	        headerCell = headerRow.createCell(1);
	        headerCell.setCellValue("출하자명");
	        headerCell.setCellStyle(headerCellStyle);

	        headerCell = headerRow.createCell(2);
	        headerCell.setCellValue("출하자코드");
	        headerCell.setCellStyle(headerCellStyle);
	        
	        headerCell = headerRow.createCell(3);
	        headerCell.setCellValue("출하자지역");
	        headerCell.setCellStyle(headerCellStyle);
	        
	        headerCell = headerRow.createCell(4);
	        headerCell.setCellValue("중도매인명");
	        headerCell.setCellStyle(headerCellStyle);
	        
	        headerCell = headerRow.createCell(5);
	        headerCell.setCellValue("중도매인코드");
	        headerCell.setCellStyle(headerCellStyle);
	        
	        headerCell = headerRow.createCell(6);
	        headerCell.setCellValue("품목명");
	        headerCell.setCellStyle(headerCellStyle);
	        
	        headerCell = headerRow.createCell(7);
	        headerCell.setCellValue("품종명");
	        headerCell.setCellStyle(headerCellStyle);
	        
	        headerCell = headerRow.createCell(8);
	        headerCell.setCellValue("등급");
	        headerCell.setCellStyle(headerCellStyle);
	        
	        headerCell = headerRow.createCell(9);
	        headerCell.setCellValue("상자수");
	        headerCell.setCellStyle(headerCellStyle);
	        
	        headerCell = headerRow.createCell(10);
	        headerCell.setCellValue("총 분수량");
	        headerCell.setCellStyle(headerCellStyle);
	        
	        headerCell = headerRow.createCell(11);
	        headerCell.setCellValue("요청단가");
	        headerCell.setCellStyle(headerCellStyle);
	        
	        headerCell = headerRow.createCell(12);
	        headerCell.setCellValue("조정단가");
	        headerCell.setCellStyle(headerCellStyle);
	        
	        headerCell = headerRow.createCell(13);
	        headerCell.setCellValue("상태");
	        headerCell.setCellStyle(headerCellStyle);
	        
	        headerCell = headerRow.createCell(14);
	        headerCell.setCellValue("미체결사유");
	        headerCell.setCellStyle(headerCellStyle);
	        
	        Row bodyRow = null;
	        Cell bodyCell = null;
	        for(int i=0; i<list.size(); i++) {
	            EgovMap fixVO = list.get(i);
	            
	            // 행 생성
	            bodyRow = sheet.createRow(tableInitRow+(i+1));

	            bodyCell = bodyRow.createCell(0);
	            bodyCell.setCellValue(i + 1);
	            bodyCell.setCellStyle(bodyCellStyle);

	            bodyCell = bodyRow.createCell(1);
	            bodyCell.setCellValue(String.valueOf(fixVO.get("chulName")));
	            bodyCell.setCellStyle(bodyCellStyle);

	            bodyCell = bodyRow.createCell(2);
	            bodyCell.setCellValue(String.valueOf(fixVO.get("chulCode")));
	            bodyCell.setCellStyle(bodyCellStyle);

	            bodyCell = bodyRow.createCell(3);
	            bodyCell.setCellValue(String.valueOf(fixVO.get("chulArea")));
	            bodyCell.setCellStyle(bodyLeftCellStyle);
	            
	            bodyCell = bodyRow.createCell(4);
	            bodyCell.setCellValue(String.valueOf(fixVO.get("jName")));
	            bodyCell.setCellStyle(bodyCellStyle);
	            
	            bodyCell = bodyRow.createCell(5);
	            bodyCell.setCellValue(String.valueOf(fixVO.get("jCode")));
	            bodyCell.setCellStyle(bodyCellStyle);
	            
	            bodyCell = bodyRow.createCell(6);
	            bodyCell.setCellValue(String.valueOf(fixVO.get("mokName")));
	            bodyCell.setCellStyle(bodyLeftCellStyle);
	            
	            bodyCell = bodyRow.createCell(7);
	            bodyCell.setCellValue(String.valueOf(fixVO.get("pumName")));
	            bodyCell.setCellStyle(bodyLeftCellStyle);
	            
	            
	            bodyCell = bodyRow.createCell(8);
	            bodyCell.setCellValue(String.valueOf(fixVO.get("chulLevelName")));
	            bodyCell.setCellStyle(bodyCellStyle);
	            
	            bodyCell = bodyRow.createCell(9);
	            double boxCnt = Double.parseDouble(String.valueOf(fixVO.get("boxCnt")));
	            bodyCell.setCellType(bodyCell.CELL_TYPE_NUMERIC);
	            bodyCell.setCellValue(boxCnt);
	            bodyCell.setCellStyle(bodyNumCellStyle);
	            
	            bodyCell = bodyRow.createCell(10);
	            double sokCnt = Double.parseDouble(String.valueOf(fixVO.get("sokCnt")));
	            bodyCell.setCellType(bodyCell.CELL_TYPE_NUMERIC);
	            bodyCell.setCellValue(sokCnt);
	            bodyCell.setCellStyle(bodyNumCellStyle);
	            
	            bodyCell = bodyRow.createCell(11);
	            double unitPrice = Double.parseDouble(String.valueOf(fixVO.get("unitPrice")));
	            bodyCell.setCellType(bodyCell.CELL_TYPE_NUMERIC);
	            bodyCell.setCellValue(unitPrice);
	            bodyCell.setCellStyle(bodyNumCellStyle);
	            
	            bodyCell = bodyRow.createCell(12);
	            double aucPrice = Double.parseDouble(String.valueOf(fixVO.get("aucPrice")));
	            bodyCell.setCellType(bodyCell.CELL_TYPE_NUMERIC);
	            bodyCell.setCellValue(aucPrice);
	            bodyCell.setCellStyle(bodyNumCellStyle);
	            
	            bodyCell = bodyRow.createCell(13);
	            bodyCell.setCellValue(String.valueOf(fixVO.get("fixStateName")));
	            bodyCell.setCellStyle(bodyCellStyle);
	            
	            if(fixVO.get("gtext") == null){
	            	fixVO.put("gtext","");
	            }
	            bodyCell = bodyRow.createCell(14);
	            bodyCell.setCellValue(String.valueOf(fixVO.get("gtext")));
	            bodyCell.setCellStyle(bodyCellStyle);
	            
	            
	            
	            
	        }
		 
			
	        
	        
	        model.addAttribute("locale", Locale.KOREA);
	        model.addAttribute("workbook", workbook);
	        model.addAttribute("workbookName", "중도매인요청표"+String.valueOf(paramMap.get("chulDate")));
	        
	        renderMergedOutputModel(model, request, response);
	       // return "excelDownloadView";
		 
		}
	
	
	
	@RequestMapping(value="/front/fixCAuc/getReqAdmTableExcellComp.do")
	public void getReqAdmTableExcellComp(
			  @RequestParam Map<String, Object> paramMap
			, HttpServletResponse response
			, HttpServletRequest request
			, ModelMap model) throws Exception{
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"07".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
	 
	 List<EgovMap> list = frontFixCAucService.getReqAdmTableExcellComp(paramMap);
	 	
	 SXSSFWorkbook workbook = new SXSSFWorkbook();
	 int tableInitRow = 6;
	// 시트 생성
        SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("정가수의매매 기록부내역");
        
        //시트 열 너비 설정
        sheet.setColumnWidth(0, 3000);
        sheet.setColumnWidth(1, 2500);
        sheet.setColumnWidth(2, 4500);
        sheet.setColumnWidth(3, 6000);
        sheet.setColumnWidth(4, 2000);
        sheet.setColumnWidth(5, 2000);
        sheet.setColumnWidth(6, 2000);
        sheet.setColumnWidth(7, 3000);
        sheet.setColumnWidth(8, 3000);
        sheet.setColumnWidth(9, 6000);
        
        
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
        
        
        
      //제목 병합
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 9));
        //결재란
        sheet.addMergedRegion(new CellRangeAddress(3, 3, 4, 6));
        sheet.addMergedRegion(new CellRangeAddress(3, 3, 7, 8));
        sheet.addMergedRegion(new CellRangeAddress(4, 4, 4, 6));
        sheet.addMergedRegion(new CellRangeAddress(4, 4, 7, 8));

        
        //일자 병합
        sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 9));
       
        
        Row titleRow = sheet.createRow(1);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("정가수의매매 기록부내역(중도매인)");
        titleCell.setCellStyle(titleCellStyle);
        
        Row appRow = sheet.createRow(3);
        Cell appCell = appRow.createCell(4);
        appCell.setCellValue("담당");
        appCell.setCellStyle(headerCellStyle);
        appCell = appRow.createCell(5);
        appCell.setCellStyle(headerCellStyle);
        appCell = appRow.createCell(6);
        appCell.setCellStyle(headerCellStyle);
        
        appCell = appRow.createCell(7);
        appCell.setCellValue("차장");
        appCell.setCellStyle(headerCellStyle);
        appCell = appRow.createCell(8);
        appCell.setCellStyle(headerCellStyle);
        
        appCell = appRow.createCell(9);
        appCell.setCellValue("부장");
        appCell.setCellStyle(headerCellStyle);
        
        Row signRow = sheet.createRow(4);
        signRow.setHeight((short)1000);
        
        Cell signCell = signRow.createCell(4);
        signCell.setCellStyle(headerCellStyle);
        signCell = signRow.createCell(5);
        signCell.setCellStyle(headerCellStyle);
        signCell = signRow.createCell(6);
        signCell.setCellStyle(headerCellStyle);
        signCell = signRow.createCell(7);
        signCell.setCellStyle(headerCellStyle);
        signCell = signRow.createCell(8);
        signCell.setCellStyle(headerCellStyle);
        signCell = signRow.createCell(9);
        signCell.setCellStyle(headerCellStyle);
        
        
        //일자 행
        Row dayRow = sheet.createRow(tableInitRow-1);
        
        Cell dayCell = dayRow.createCell(0);
        
        String chulDate = String.valueOf(paramMap.get("chulDate"));
        String dayKo = "";
        if(list.size()>0){
        	dayKo = "("+String.valueOf(list.get(0).get("dayKo"))+")"; 
        }
        
        dayCell.setCellValue("일자:"+chulDate+dayKo);
        
        
        // 헤더 행
        Row headerRow = sheet.createRow(tableInitRow);
        
        Cell headerCell = headerRow.createCell(0);
        
        headerCell.setCellValue("요청일자");
        headerCell.setCellStyle(headerCellStyle);
        
        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("중도매인");
        headerCell.setCellStyle(headerCellStyle);

        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("출하자");
        headerCell.setCellStyle(headerCellStyle);
        
        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("품종명");
        headerCell.setCellStyle(headerCellStyle);
        
        headerCell = headerRow.createCell(4);
        headerCell.setCellValue("상자");
        headerCell.setCellStyle(headerCellStyle);
        
        headerCell = headerRow.createCell(5);
        headerCell.setCellValue("분수");
        headerCell.setCellStyle(headerCellStyle);
        
        headerCell = headerRow.createCell(6);
        headerCell.setCellValue("가격");
        headerCell.setCellStyle(headerCellStyle);
        
        headerCell = headerRow.createCell(7);
        headerCell.setCellValue("합계");
        headerCell.setCellStyle(headerCellStyle);
        
        headerCell = headerRow.createCell(8);
        headerCell.setCellValue("체결여부");
        headerCell.setCellStyle(headerCellStyle);
        
        headerCell = headerRow.createCell(9);
        headerCell.setCellValue("비고");
        headerCell.setCellStyle(headerCellStyle);
        
        Row bodyRow = null;
        Cell bodyCell = null;
        for(int i=0; i<list.size(); i++) {
            EgovMap fixVO = list.get(i);
            
            // 행 생성
            bodyRow = sheet.createRow(tableInitRow+(i+1));

            bodyCell = bodyRow.createCell(0);
            bodyCell.setCellValue(String.valueOf(fixVO.get("insertDate")));
            bodyCell.setCellStyle(bodyCellStyle);

            bodyCell = bodyRow.createCell(1);
            bodyCell.setCellValue(String.valueOf(fixVO.get("jCode")));
            bodyCell.setCellStyle(bodyCellStyle);

            bodyCell = bodyRow.createCell(2);
            bodyCell.setCellValue(String.valueOf(fixVO.get("chulName"))+"("+String.valueOf(fixVO.get("chulCode"))+")");
            bodyCell.setCellStyle(bodyCellStyle);
            
            bodyCell = bodyRow.createCell(3);
            bodyCell.setCellValue(String.valueOf(fixVO.get("pumName")));
            bodyCell.setCellStyle(bodyLeftCellStyle);
            
            bodyCell = bodyRow.createCell(4);
            double boxCnt = Double.parseDouble(String.valueOf(fixVO.get("boxCnt")));
            bodyCell.setCellType(bodyCell.CELL_TYPE_NUMERIC);
            bodyCell.setCellStyle(bodyNumCellStyle);
            bodyCell.setCellValue(boxCnt);
            
            bodyCell = bodyRow.createCell(5);
            double sokCnt = Double.parseDouble(String.valueOf(fixVO.get("sokCnt")));
            bodyCell.setCellType(bodyCell.CELL_TYPE_NUMERIC);
            bodyCell.setCellStyle(bodyNumCellStyle);
            bodyCell.setCellValue(sokCnt);
            
            
            
            bodyCell = bodyRow.createCell(6);
            double aucPrice = Double.parseDouble(String.valueOf(fixVO.get("aucPrice")));
            bodyCell.setCellType(bodyCell.CELL_TYPE_NUMERIC);
            bodyCell.setCellStyle(bodyNumCellStyle);
            bodyCell.setCellValue(aucPrice);
            
            
            bodyCell = bodyRow.createCell(7);
            double totAmt = Double.parseDouble(String.valueOf(fixVO.get("totAmt")));
            bodyCell.setCellType(bodyCell.CELL_TYPE_NUMERIC);
            bodyCell.setCellStyle(bodyNumCellStyle);
            bodyCell.setCellValue(totAmt);
            
            
            bodyCell = bodyRow.createCell(8);
            bodyCell.setCellType(bodyCell.CELL_TYPE_STRING);
            bodyCell.setCellValue(String.valueOf(fixVO.get("fixStateName")));
            bodyCell.setCellStyle(bodyCellStyle);
            
            if(fixVO.get("gtext") == null){
            	fixVO.put("gtext","");
            }
            bodyCell = bodyRow.createCell(9);
            bodyCell.setCellValue(String.valueOf(fixVO.get("gtext")));
            bodyCell.setCellStyle(bodyCellStyle);
            
            
            
            
        }
	 
		
        
        
        model.addAttribute("locale", Locale.KOREA);
        model.addAttribute("workbook", workbook);
        model.addAttribute("workbookName", "정가수의매매 기록부내역"+String.valueOf(paramMap.get("chulDate")));
        
        renderMergedOutputModel(model, request, response);
       // return "excelDownloadView";
	 
	}
	
	
	@RequestMapping(value="/front/fixCAuc/reqExcellUpload.json")
	public String reqExcellUpload(
			  @RequestParam Map<String, Object> paramMap
			, HttpServletResponse response
			, MultipartHttpServletRequest request
			, ModelMap model) throws Exception{
		
		EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		
		String aucType = String.valueOf(loginVO.get("aucType"));
		//01 출하농가 / 03 중도매인 / 07 경매사
		if(!"07".equals(aucType) ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
		
		try {
			
		
		MultipartFile file = request.getFile("excellFile");
		 
		if(file == null){
			//파일없음
			model.addAttribute("result","4");
			return "jsonView";
		}
		 OPCPackage opcPackage = OPCPackage.open(file.getInputStream());
         XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);
         
         // 첫번째 시트 불러오기
         XSSFSheet sheet = workbook.getSheetAt(0);
         
         List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
         
         for(int i=1; i<sheet.getLastRowNum() + 1; i++) {
        	 
        	 Map<String, Object> reqParam = new HashMap<String, Object>();
             XSSFRow row = sheet.getRow(i);
             
             // 행이 존재하기 않으면 패스
             if(null == row) {
                 continue;
             }
             
             boolean rowCheck = true;
             
             // 출하자코드
             XSSFCell cell = row.getCell(0);
             if(null != cell){
            	 EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(cell.getStringCellValue());
            	 if(chulInfo != null){
            		 reqParam.put("chulCode",cell.getStringCellValue());
            	 }else{
            		 rowCheck = false;
                	 model.addAttribute("chulCodeErr","출하자코드가 일치하지 않습니다.");
            	 }
            	 
             }else{
            	 rowCheck = false;
            	 model.addAttribute("chulCodeErr","출하자코드가 입력되지않았습니다.");
             }
             // 중도매인코드
             cell = row.getCell(1);
             if(null != cell){
            	 EgovMap jInfo = frontFixCAucService.getJCodeToJInfo(cell.getStringCellValue());
            	 if(jInfo != null){
            		 reqParam.put("jCode",cell.getStringCellValue());
            		 paramMap.put("code", cell.getStringCellValue());
            		 EgovMap jCheck = frontFixCAucService.getCodeToLoginId(paramMap);
            		 if(jCheck != null){
            			 reqParam.put("jId",jCheck.get("loginId"));
            		 }
            		 
            	 }else{
            		 rowCheck = false;
                	 model.addAttribute("jCodeErr","중도매인코드가 일치하지 않습니다.");
            	 }
            	 
             }else{
            	 rowCheck = false;
            	 model.addAttribute("jCodeErr","중도매인코드가 입력되지않았습니다.");
             }
             
             // 품목코드
             cell = row.getCell(2);
             if(null != cell){
            	 reqParam.put("mokCode",cell.getStringCellValue());
            	 
            	 EgovMap mokInfo = frontFixCAucService.getMokInfo(reqParam);
            	 if(mokInfo != null){
            		 //성공
            		 reqParam.put("mokName",mokInfo.get("pMokName"));
            	 }else{
            		 rowCheck = false;
                	 model.addAttribute("mokCodeErr","품목코드가 일치하지 않습니다.");
            	 }
            	 
             }else{
            	 rowCheck = false;
            	 model.addAttribute("mokCodeErr","품목코드가 입력되지않았습니다.");
             }
             
             // 품종코드
             cell = row.getCell(3);
             if(null != cell){
            	 reqParam.put("pumCode",cell.getStringCellValue());
            	 
            	 EgovMap pumInfo = frontFixCAucService.getPumInfo(reqParam);
            	 if(pumInfo != null){
            		 //성공
            		 reqParam.put("pumName",pumInfo.get("pJongName"));
            	 }else{
            		 rowCheck = false;
                	 model.addAttribute("pumCodeErr","품종코드가 일치하지 않습니다.");
            	 }
            	 
             }else{
            	 rowCheck = false;
            	 model.addAttribute("pumCodeErr","품종코드가 입력되지않았습니다.");
             }
             
             // 등급코드
             cell = row.getCell(4);
             if(null != cell){
            	 reqParam.put("chulLevel",cell.getStringCellValue());
            	 
            	 EgovMap levelInfo = frontFixCAucService.getLevelInfo(reqParam);
            	 if(levelInfo != null){
            		 //성공
            		 reqParam.put("chulLevelName",levelInfo.get("chulLevelName"));
            	 }else{
            		 rowCheck = false;
                	 model.addAttribute("chulLevelErr","등급코드가 일치하지 않습니다.");
            	 }
            	 
             }else{
            	 rowCheck = false;
            	 model.addAttribute("chulLevelErr","등급코드가 입력되지않았습니다.");
             }
             
             // 상자수
             cell = row.getCell(5);
             if(null != cell){
            	 int boxCnt = (int)cell.getNumericCellValue();
            	 
            	 if(boxCnt > 0){
            		 //성공
            		 reqParam.put("boxCnt",boxCnt);
            	 }else{
            		 rowCheck = false;
                	 model.addAttribute("boxCntErr","상자수가 0입니다.");
            	 }
            	 
             }else{
            	 rowCheck = false;
            	 model.addAttribute("boxCntErr","상자수가 입력되지않았습니다.");
             }
             
             // 총분수량
             cell = row.getCell(6);
             if(null != cell){
            	 int sokCnt = (int)cell.getNumericCellValue();
            	 
            	 if(sokCnt > 0){
            		 //성공
            		 reqParam.put("sokCnt",sokCnt);
            	 }else{
            		 rowCheck = false;
                	 model.addAttribute("sokCntErr","총 분수량이 0입니다.");
            	 }
            	 
             }else{
            	 rowCheck = false;
            	 model.addAttribute("sokCntErr","총 분수량이 입력되지않았습니다.");
             }
             
             // 요청단가
             cell = row.getCell(7);
             if(null != cell){
            	 int unitPrice = (int)cell.getNumericCellValue();
            	 
            	 if(unitPrice > 0){
            		 //성공
            		 reqParam.put("unitPrice",unitPrice);
            	 }else{
            		 rowCheck = false;
                	 model.addAttribute("unitPriceErr","요청단가가 0입니다.");
            	 }
            	 
             }else{
            	 rowCheck = false;
            	 model.addAttribute("unitPriceErr","요청단가가 입력되지않았습니다.");
             }
             
             
             if(!rowCheck){
            	 //행 오류
            	 model.addAttribute("result","2");
            	 model.addAttribute("rowIndex",i);
            	 return "jsonView";
             }
             list.add(reqParam);
         }
		
         int uploadCnt = 0;
         for(Map<String, Object> reqParam : list){
        	reqParam.put("reqDealSeq", egovReqIdGnrService.getNextIntegerId());
            reqParam.put("chulDate",paramMap.get("excellChulDate"));
            reqParam.put("id", loginVO.get("loginId"));
    		reqParam.put("memberId", loginVO.get("memberId"));
    		
        	int result = frontFixCAucService.reqAdmRegProc(reqParam);
        	uploadCnt = uploadCnt+result;
         }
         
         //엑셀업로드 성공
          model.addAttribute("uploadCnt",uploadCnt);
          model.addAttribute("result","1");
		} catch (Exception e) {
			// 오류발생
		  model.addAttribute("result","3");
		  model.addAttribute("message",e.getMessage());
		  return "jsonView";
		}
		
        return "jsonView";
	 
	}
	 
	
	@RequestMapping(value="/front/fixCAuc/excellTempDown.do")
	public void excellTempDown(
			  @RequestParam Map<String, Object> paramMap
			, HttpServletResponse response
			, HttpServletRequest request
			, ModelMap model
			, String streFileNm
			, String orignFileNm) throws Exception{
		
		String mode = EgovProperties.getProperty("Globals.Mode");
		String savePath = EgovProperties.getProperty("Globals.DstbtPath"+mode);
		
	    String uploadfile = savePath + "/excell/requpload.xlsx";
		
		try {
			egovFileMngUtil.downFile(request, response, uploadfile, "중도매인요청 엑셀템플릿.xlsx");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
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
	    
	    //분갈이
	    @RequestMapping(value="/front/fixCAuc/fixSellSubList.do")
		public String fixSellSubList(HttpServletRequest request, HttpServletResponse response,
				@RequestParam Map<String, Object> paramMap
				, ModelMap model) throws Exception {
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"01".equals(aucType) && !"02".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}else{
				List<EgovMap> floMemberList = frontLoginService.getFloMember(paramMap);
				paramMap.put("floMemberList", floMemberList);
				
				int subCheckCnt = frontFixCAucService.getfixSubChulInfoCnt(paramMap);
				if(subCheckCnt == 0){
					ModelAndView modelAndView = new ModelAndView("forward:/front/mypage.do");
					modelAndView.addObject("message", "등록된 분갈이 취급 출하자가 아닙니다. 경매사에게 문의하세요.");
		            throw new ModelAndViewDefiningException(modelAndView);
				}
			}
			
			
			try {
				
				List<EgovMap> fixList = frontFixCAucService.fixSellSubList(paramMap);
				
				int totCnt = frontFixCAucService.fixSellSubListCnt(paramMap);
				
				model.addAttribute("resultList", fixList);
				model.addAttribute("resultCnt", totCnt);
				model.addAttribute("paramMap", paramMap);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			model.addAttribute("title", "판매 - 분갈이");
			model.addAttribute("contentPath", "fixAuc/C/fixSellSubList.jsp");
			
			return "/front/layout/fixLayout";
		}
	    
	    
	    @RequestMapping(value="/front/fixCAuc/fixSellSubView.do")
		public String fixSellSubView(HttpServletRequest request, HttpServletResponse response,
				@RequestParam Map<String, Object> paramMap
				, ModelMap model) throws Exception {
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"01".equals(aucType) && !"02".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}else{
				List<EgovMap> floMemberList = frontLoginService.getFloMember(paramMap);
				paramMap.put("floMemberList", floMemberList);
				
				int subCheckCnt = frontFixCAucService.getfixSubChulInfoCnt(paramMap);
				if(subCheckCnt == 0){
					ModelAndView modelAndView = new ModelAndView("forward:/front/mypage.do");
					modelAndView.addObject("message", "등록된 분갈이 취급 출하자가 아닙니다. 경매사에게 문의하세요.");
		            throw new ModelAndViewDefiningException(modelAndView);
				}
			}
			
			EgovMap fixVO = frontFixCAucService.getFixSubArticle(paramMap);
			
			if(fixVO != null){
				
				String chulCode = String.valueOf(fixVO.get("chulCode"));
				EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
				if(chulInfo != null){
					fixVO.put("chulName", chulInfo.get("name"));
					fixVO.put("chulArea", chulInfo.get("chulArea"));
				}else{
					fixVO.put("chulName", "정보없음");
					fixVO.put("chulArea", "정보없음");
				}
				List<EgovMap> fixFileList = frontFixCAucService.getFixSubFileList(paramMap);
				model.addAttribute("fixFileList", fixFileList);
			}
			
			model.addAttribute("result", fixVO);
			
			model.addAttribute("title", "판매 - 분갈이");
			model.addAttribute("contentPath", "fixAuc/C/fixSellSubView.jsp");
			
			return "/front/layout/fixLayout";
		}
		
		@RequestMapping(value="/front/fixCAuc/fixSellSubReg.do")
		public String fixSellSubReg(HttpServletRequest request, HttpServletResponse response,
				@RequestParam Map<String, Object> paramMap
				, ModelMap model) throws Exception {
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"01".equals(aucType) && !"02".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}else{
				List<EgovMap> floMemberList = frontLoginService.getFloMember(paramMap);
				paramMap.put("floMemberList", floMemberList);
				
				int subCheckCnt = frontFixCAucService.getfixSubChulInfoCnt(paramMap);
				if(subCheckCnt == 0){
					ModelAndView modelAndView = new ModelAndView("forward:/front/mypage.do");
					modelAndView.addObject("message", "등록된 분갈이 취급 출하자가 아닙니다. 경매사에게 문의하세요.");
		            throw new ModelAndViewDefiningException(modelAndView);
				}else{
					
					List<EgovMap> floList = new ArrayList<EgovMap>();
					
					for(EgovMap chulVO : floMemberList){
						String chulCode = String.valueOf(chulVO.get("chulCd"));
						EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
						EgovMap chulSubInfo = frontFixCAucService.getfixSubChulInfo(chulCode);
						if(chulInfo != null && chulSubInfo != null){
							floList.add(chulInfo);
						}
					}
					
					model.addAttribute("floList", floList);
					
				}
			}
			
			
			//등급 코드 리스트
			paramMap.put("bunChk", "C");
			List<EgovMap> levelList = frontFixCAucService.getLevelList(paramMap);
			model.addAttribute("levelList", levelList);
			
			model.addAttribute("title", "판매 - 분갈이");
			model.addAttribute("contentPath", "fixAuc/C/fixSellSubReg.jsp");
			
			return "/front/layout/fixLayout";
		}
		
		@RequestMapping(value="/front/fixCAuc/fixSellSubRegProc.do")
		public String fixSellSubRegProc(HttpServletRequest request, HttpServletResponse response,
				@RequestParam Map<String, Object> paramMap
				, MultipartHttpServletRequest multiRequest
				, ModelMap model) throws Exception {
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"01".equals(aucType) && !"02".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}else{
				List<EgovMap> floMemberList = frontLoginService.getFloMember(paramMap);
				paramMap.put("floMemberList", floMemberList);
				
				int subCheckCnt = frontFixCAucService.getfixSubChulInfoCnt(paramMap);
				if(subCheckCnt == 0){
					ModelAndView modelAndView = new ModelAndView("forward:/front/mypage.do");
					modelAndView.addObject("message", "등록된 분갈이 취급 출하자가 아닙니다. 경매사에게 문의하세요.");
		            throw new ModelAndViewDefiningException(modelAndView);
				}
			}
			
			
			int result = 0;
			try {
				
				paramMap.put("fixSubDealSeq", egovFixSubIdGnrService.getNextIntegerId()); 
				
				result = frontFixCAucService.fixSellSubRegProc(paramMap);
				//파일
				List<FileVO> uploadFileList = uploadThumbFile(model, multiRequest);
				
				if ((uploadFileList != null) && (uploadFileList.size() > 0)){
					Map<String, Object> fileMap = new HashMap<String, Object>();
			      
					for (int i = 0; i < uploadFileList.size(); i++){
			    	  
			    	  FileVO fileVO = uploadFileList.get(i);
			    	  fileMap.put("fileSeq",((FileVO)uploadFileList.get(i)).getFileSn());
			    	  fileMap.put("fixSubDealSeq", paramMap.get("fixSubDealSeq"));
			    	  fileMap.put("streFilePath", fileVO.getFileStreCours());
			    	  fileMap.put("streFileNm", fileVO.getOrignlFileNm()+"."+fileVO.getStreFileNm());
			    	  fileMap.put("orignlFileNm", fileVO.getOrignlFileNm());
			    	  fileMap.put("fileExtsn", fileVO.getFileExtsn());
			    	  fileMap.put("fileCn", fileVO.getFileCn());
			    	  fileMap.put("fileSize", fileVO.getFileMg());
			    	  fileMap.put("id", loginVO.get("loginId"));
			    	  
			    	  //썸네일 이후 수정
			    	  fileMap.put("thumbStreFileNm", fileVO.getStreFileNm());
			    	  
			    	  frontFixCAucService.fixSellSubFileProc(fileMap);
			    	  
			    	  
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
				model.addAttribute("returnUrl", "/front/fixCAuc/fixSellSubList.do");
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				result = 0;
			}
			model.addAttribute("result", result);
			return "/front/fixAuc/C/result";
		}
		
		@RequestMapping(value="/front/fixCAuc/fixSellSubUpt.do")
		public String fixSellSubUpt(HttpServletRequest request, HttpServletResponse response,
				@RequestParam Map<String, Object> paramMap
				, ModelMap model) throws Exception {
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"01".equals(aucType) && !"02".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}else{
				List<EgovMap> floMemberList = frontLoginService.getFloMember(paramMap);
				paramMap.put("floMemberList", floMemberList);
				
				int subCheckCnt = frontFixCAucService.getfixSubChulInfoCnt(paramMap);
				if(subCheckCnt == 0){
					ModelAndView modelAndView = new ModelAndView("forward:/front/mypage.do");
					modelAndView.addObject("message", "등록된 분갈이 취급 출하자가 아닙니다. 경매사에게 문의하세요.");
		            throw new ModelAndViewDefiningException(modelAndView);
				}else{
					
					List<EgovMap> floList = new ArrayList<EgovMap>();
					
					for(EgovMap chulVO : floMemberList){
						String chulCode = String.valueOf(chulVO.get("chulCd"));
						EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
						EgovMap chulSubInfo = frontFixCAucService.getfixSubChulInfo(chulCode);
						if(chulInfo != null && chulSubInfo != null){
							floList.add(chulInfo);
						}
					}
					
					model.addAttribute("floList", floList);
					
				}
			}
			
			
			
			EgovMap fixVO = frontFixCAucService.getFixSubArticle(paramMap);
			if(fixVO != null){
				
				String chulCode = String.valueOf(fixVO.get("chulCode"));
				EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
				if(chulInfo != null){
					fixVO.put("chulName", chulInfo.get("name"));
					fixVO.put("chulArea", chulInfo.get("chulArea"));
				}else{
					fixVO.put("chulName", "정보없음");
					fixVO.put("chulArea", "정보없음");
				}
				List<EgovMap> fixFileList = frontFixCAucService.getFixSubFileList(paramMap);
				model.addAttribute("fixFileList", fixFileList);
				for(EgovMap file : fixFileList ){
					String fileSeq = String.valueOf(file.get("fileSeq"));
					model.addAttribute("file"+fileSeq, file);
				}
			}else{
				model.addAttribute("message", "비정상적인 접근입니다.");
				model.addAttribute("returnUrl", "/front/fixCAuc/fixSellSubList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/C/result";
			}
			
			model.addAttribute("result", fixVO);
			
			
			//등급 코드 리스트
			paramMap.put("bunChk", "C");
			List<EgovMap> levelList = frontFixCAucService.getLevelList(paramMap);
			model.addAttribute("levelList", levelList);
			
			
			model.addAttribute("title", "판매 - 분갈이");
			model.addAttribute("contentPath", "fixAuc/C/fixSellSubUpt.jsp");
			
			return "/front/layout/fixLayout";
		}
		
		@RequestMapping(value="/front/fixCAuc/fixSellSubUptProc.do")
		public String fixSellSubUptProc(HttpServletRequest request, HttpServletResponse response,
				@RequestParam Map<String, Object> paramMap
				, MultipartHttpServletRequest multiRequest
				, ModelMap model) throws Exception {
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"01".equals(aucType) && !"02".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}else{
				List<EgovMap> floMemberList = frontLoginService.getFloMember(paramMap);
				paramMap.put("floMemberList", floMemberList);
				
				int subCheckCnt = frontFixCAucService.getfixSubChulInfoCnt(paramMap);
				if(subCheckCnt == 0){
					ModelAndView modelAndView = new ModelAndView("forward:/front/mypage.do");
					modelAndView.addObject("message", "등록된 분갈이 취급 출하자가 아닙니다. 경매사에게 문의하세요.");
		            throw new ModelAndViewDefiningException(modelAndView);
				}
			}
			
			int result = 0;
			
			try {
				
				paramMap.put("id", loginVO.get("loginId"));
				paramMap.put("memberId", loginVO.get("memberId"));
				
				EgovMap fixVO = frontFixCAucService.getFixSubArticle(paramMap);
				if(fixVO != null){
					
					
				}else{
					model.addAttribute("message", "비정상적인 접근입니다.");
					model.addAttribute("returnUrl", "/front/fixCAuc/fixSellSubList.do");
					model.addAttribute("result", 1);
					return "/front/fixAuc/C/result";
				}	 
				
				
				result = frontFixCAucService.fixSellSubUptProc(paramMap);
				//파일
				
				String[] deleteAtchFile = request.getParameterValues("deleteAtchFile");
				if(deleteAtchFile != null && deleteAtchFile.length > 0){
					for (int i = 0; i < deleteAtchFile.length; i++){
						paramMap.put("fileSeq",deleteAtchFile[i]);
				        frontFixCAucService.deleteSubFileOne(paramMap);
				      }
				}
				
				List<FileVO> uploadFileList = uploadThumbFile(model, multiRequest);
				
				if ((uploadFileList != null) && (uploadFileList.size() > 0)){
					Map<String, Object> fileMap = new HashMap<String, Object>();
			      
					for (int i = 0; i < uploadFileList.size(); i++){
						
			    	  FileVO fileVO = uploadFileList.get(i);
			    	  fileMap.put("fileSeq", ((FileVO)uploadFileList.get(i)).getFileSn());
			    	  fileMap.put("fixSubDealSeq", paramMap.get("fixSubDealSeq"));
			    	  fileMap.put("streFilePath", fileVO.getFileStreCours());
			    	  fileMap.put("streFileNm", fileVO.getOrignlFileNm()+"."+fileVO.getStreFileNm());
			    	  fileMap.put("orignlFileNm", fileVO.getOrignlFileNm());
			    	  
			    	  fileMap.put("fileExtsn", fileVO.getFileExtsn());
			    	  fileMap.put("fileCn", fileVO.getFileCn());
			    	  fileMap.put("fileSize", fileVO.getFileMg());
			    	  fileMap.put("id", loginVO.get("loginId"));
			    	  
			    	  //썸네일 이후 수정
			    	  fileMap.put("thumbStreFileNm", fileVO.getStreFileNm());
			    	  
			    	  EgovMap fileCheckVO = frontFixCAucService.getSubFileOne(fileMap);
			    	  if(fileCheckVO != null){
			    		  frontFixCAucService.deleteSubFileOne(fileMap);
			    	  }
			    	  
			    	  frontFixCAucService.fixSellSubFileProc(fileMap);
			    	  
			    	  
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
				model.addAttribute("returnUrl", "/front/fixCAuc/fixSellSubList.do");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				result = 0;
			}
			model.addAttribute("result", result);
			return "/front/fixAuc/C/result";
		}
		
		
		@RequestMapping(value="/front/fixCAuc/fixSellSubDelProc.do")
		public String fixSellSubDelProc(HttpServletRequest request, HttpServletResponse response,
				@RequestParam Map<String, Object> paramMap
				, ModelMap model) throws Exception {
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"01".equals(aucType) && !"02".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			int result = 0;
			
			try {
			
			
			EgovMap fixVO = frontFixCAucService.getFixSubArticle(paramMap);
			if(fixVO != null){
				
			}else{
				model.addAttribute("message", "비정상적인 접근입니다.");
				model.addAttribute("returnUrl", "/front/fixCAuc/fixSellSubList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/C/result";
			}
				
				result = frontFixCAucService.fixSellSubDelProc(paramMap);
				
				if(result == 1){
					frontFixCAucService.fixSellSubFileDelProc(paramMap);
				}
				
				
				model.addAttribute("message", "정상적으로 삭제되었습니다.");
				model.addAttribute("returnUrl", "/front/fixCAuc/fixSellSubList.do");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				result = 0;
			}
			model.addAttribute("result", result);
			return "/front/fixAuc/C/result";
		}
		
		
		//분갈이 - 중도매인
	    @RequestMapping(value="/front/fixCAuc/fixBuySubList.do")
		public String fixBuySubList(HttpServletRequest request, HttpServletResponse response,
				@RequestParam Map<String, Object> paramMap
				, ModelMap model) throws Exception {
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"03".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			try {
				
				List<EgovMap> fixList = frontFixCAucService.fixBuySubList(paramMap);
				
				/*
				for(EgovMap fixVO : fixList){
					String chulCode = String.valueOf(fixVO.get("chulCode"));
					EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
					if(chulInfo != null){
						fixVO.put("chulName", chulInfo.get("name"));
						fixVO.put("chulArea", chulInfo.get("chulArea"));
					}else{
						fixVO.put("chulName", "정보없음");
						fixVO.put("chulArea", "정보없음");
					}
				}*/
				
				int totCnt = frontFixCAucService.fixBuySubListCnt(paramMap);
				
				
				List<EgovMap> chulList = frontFixCAucService.getfixSubChulList(paramMap);
				
				model.addAttribute("resultList", fixList);
				model.addAttribute("chulList", chulList);
				model.addAttribute("resultCnt", totCnt);
				model.addAttribute("paramMap", paramMap);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			model.addAttribute("title", "구매 - 분갈이");
			model.addAttribute("contentPath", "fixAuc/C/fixBuySubList.jsp");
			
			return "/front/layout/fixLayout";
		}
	    
	    
	    @RequestMapping(value="/front/fixCAuc/fixBuySubView.do")
		public String fixBuySubView(HttpServletRequest request, HttpServletResponse response,
				@RequestParam Map<String, Object> paramMap
				, ModelMap model) throws Exception {
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"03".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			EgovMap fixVO = frontFixCAucService.getFixBuySubArticle(paramMap);
			
			if(fixVO != null){
				
				String chulCode = String.valueOf(fixVO.get("chulCode"));
				EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
				if(chulInfo != null){
					fixVO.put("chulName", chulInfo.get("name"));
					fixVO.put("chulArea", chulInfo.get("chulArea"));
				}else{
					fixVO.put("chulName", "정보없음");
					fixVO.put("chulArea", "정보없음");
				}
				List<EgovMap> fixFileList = frontFixCAucService.getFixSubFileList(paramMap);
				model.addAttribute("fixFileList", fixFileList);
			}
			
			model.addAttribute("result", fixVO);
			
			model.addAttribute("title", "구매 - 분갈이");
			model.addAttribute("contentPath", "fixAuc/C/fixBuySubView.jsp");
			
			return "/front/layout/fixLayout";
		}
	    
	    
	    //분갈이 - 경매사
	    @RequestMapping(value="/front/fixCAuc/fixAdmSubList.do")
		public String fixAdmSubList(HttpServletRequest request, HttpServletResponse response,
				@RequestParam Map<String, Object> paramMap
				, ModelMap model) throws Exception {
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"07".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			try {
				
				List<EgovMap> fixList = frontFixCAucService.fixBuySubList(paramMap);
				
				/*
				for(EgovMap fixVO : fixList){
					String chulCode = String.valueOf(fixVO.get("chulCode"));
					EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
					if(chulInfo != null){
						fixVO.put("chulName", chulInfo.get("name"));
						fixVO.put("chulArea", chulInfo.get("chulArea"));
					}else{
						fixVO.put("chulName", "정보없음");
						fixVO.put("chulArea", "정보없음");
					}
				}*/
				
				int totCnt = frontFixCAucService.fixBuySubListCnt(paramMap);
				
				
				List<EgovMap> chulList = frontFixCAucService.getfixSubChulList(paramMap);
				
				model.addAttribute("resultList", fixList);
				model.addAttribute("chulList", chulList);
				model.addAttribute("resultCnt", totCnt);
				model.addAttribute("paramMap", paramMap);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			model.addAttribute("title", "판매관리 - 분갈이");
			model.addAttribute("contentPath", "fixAuc/C/fixAdmSubList.jsp");
			
			return "/front/layout/fixLayout";
		}
	    
	    
	    @RequestMapping(value="/front/fixCAuc/fixAdmSubView.do")
		public String fixAdmSubView(HttpServletRequest request, HttpServletResponse response,
				@RequestParam Map<String, Object> paramMap
				, ModelMap model) throws Exception {
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"07".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			EgovMap fixVO = frontFixCAucService.getFixBuySubArticle(paramMap);
			
			if(fixVO != null){
				
				String chulCode = String.valueOf(fixVO.get("chulCode"));
				EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
				if(chulInfo != null){
					fixVO.put("chulName", chulInfo.get("name"));
					fixVO.put("chulArea", chulInfo.get("chulArea"));
				}else{
					fixVO.put("chulName", "정보없음");
					fixVO.put("chulArea", "정보없음");
				}
				List<EgovMap> fixFileList = frontFixCAucService.getFixSubFileList(paramMap);
				model.addAttribute("fixFileList", fixFileList);
			}
			
			model.addAttribute("result", fixVO);
			
			model.addAttribute("title", "판매관리 - 분갈이");
			model.addAttribute("contentPath", "fixAuc/C/fixAdmSubView.jsp");
			
			return "/front/layout/fixLayout";
		}
	    
	    
	    @RequestMapping(value="/front/fixCAuc/fixAdmSubUpt.do")
		public String fixAdmSubUpt(HttpServletRequest request, HttpServletResponse response,
				@RequestParam Map<String, Object> paramMap
				, ModelMap model) throws Exception {
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"07".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			EgovMap fixVO = frontFixCAucService.getFixAdmSubArticle(paramMap);
			if(fixVO != null){
				
				String chulCode = String.valueOf(fixVO.get("chulCode"));
				EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
				if(chulInfo != null){
					fixVO.put("chulName", chulInfo.get("name"));
					fixVO.put("chulArea", chulInfo.get("chulArea"));
				}else{
					fixVO.put("chulName", "정보없음");
					fixVO.put("chulArea", "정보없음");
				}
				List<EgovMap> fixFileList = frontFixCAucService.getFixSubFileList(paramMap);
				model.addAttribute("fixFileList", fixFileList);
				for(EgovMap file : fixFileList ){
					String fileSeq = String.valueOf(file.get("fileSeq"));
					model.addAttribute("file"+fileSeq, file);
				}
			}else{
				model.addAttribute("message", "비정상적인 접근입니다.");
				model.addAttribute("returnUrl", "/front/fixCAuc/fixSellSubList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/C/result";
			}
			
			model.addAttribute("result", fixVO);
			
			
			//등급 코드 리스트
			paramMap.put("bunChk", "C");
			List<EgovMap> levelList = frontFixCAucService.getLevelList(paramMap);
			model.addAttribute("levelList", levelList);
			
			
			model.addAttribute("title", "판매 - 분갈이");
			model.addAttribute("contentPath", "fixAuc/C/fixAdmSubUpt.jsp");
			
			return "/front/layout/fixLayout";
		}
		
		@RequestMapping(value="/front/fixCAuc/fixAdmSubUptProc.do")
		public String fixAdmSubUptProc(HttpServletRequest request, HttpServletResponse response,
				@RequestParam Map<String, Object> paramMap
				, MultipartHttpServletRequest multiRequest
				, ModelMap model) throws Exception {
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
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
				
				EgovMap fixVO = frontFixCAucService.getFixAdmSubArticle(paramMap);
				if(fixVO != null){
					
					
				}else{
					model.addAttribute("message", "비정상적인 접근입니다.");
					model.addAttribute("returnUrl", "/front/fixCAuc/fixSellSubList.do");
					model.addAttribute("result", 1);
					return "/front/fixAuc/C/result";
				}	 
				
				
				result = frontFixCAucService.fixAdmSubUptProc(paramMap);
				//파일
				
				String[] deleteAtchFile = request.getParameterValues("deleteAtchFile");
				if(deleteAtchFile != null && deleteAtchFile.length > 0){
					for (int i = 0; i < deleteAtchFile.length; i++){
						paramMap.put("fileSeq",deleteAtchFile[i]);
				        frontFixCAucService.deleteSubFileOne(paramMap);
				      }
				}
				
				List<FileVO> uploadFileList = uploadThumbFile(model, multiRequest);
				
				if ((uploadFileList != null) && (uploadFileList.size() > 0)){
					Map<String, Object> fileMap = new HashMap<String, Object>();
			      
					for (int i = 0; i < uploadFileList.size(); i++){
						
			    	  FileVO fileVO = uploadFileList.get(i);
			    	  fileMap.put("fileSeq", ((FileVO)uploadFileList.get(i)).getFileSn());
			    	  fileMap.put("fixSubDealSeq", paramMap.get("fixSubDealSeq"));
			    	  fileMap.put("streFilePath", fileVO.getFileStreCours());
			    	  fileMap.put("streFileNm", fileVO.getOrignlFileNm()+"."+fileVO.getStreFileNm());
			    	  fileMap.put("orignlFileNm", fileVO.getOrignlFileNm());
			    	  
			    	  fileMap.put("fileExtsn", fileVO.getFileExtsn());
			    	  fileMap.put("fileCn", fileVO.getFileCn());
			    	  fileMap.put("fileSize", fileVO.getFileMg());
			    	  fileMap.put("id", loginVO.get("loginId"));
			    	  
			    	  //썸네일 이후 수정
			    	  fileMap.put("thumbStreFileNm", fileVO.getStreFileNm());
			    	  
			    	  EgovMap fileCheckVO = frontFixCAucService.getSubFileOne(fileMap);
			    	  if(fileCheckVO != null){
			    		  frontFixCAucService.deleteSubFileOne(fileMap);
			    	  }
			    	  
			    	  frontFixCAucService.fixSellSubFileProc(fileMap);
			    	  
			    	  
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
				model.addAttribute("returnUrl", "/front/fixCAuc/fixAdmSubList.do");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				result = 0;
			}
			model.addAttribute("result", result);
			return "/front/fixAuc/C/result";
		}
		
		
		@RequestMapping(value="/front/fixCAuc/fixAdmSubDelProc.do")
		public String fixAdmSubDelProc(HttpServletRequest request, HttpServletResponse response,
				@RequestParam Map<String, Object> paramMap
				, ModelMap model) throws Exception {
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"07".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			int result = 0;
			
			try {
			
			
			EgovMap fixVO = frontFixCAucService.getFixAdmSubArticle(paramMap);
			if(fixVO != null){
				
			}else{
				model.addAttribute("message", "비정상적인 접근입니다.");
				model.addAttribute("returnUrl", "/front/fixCAuc/fixAdmSubList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/C/result";
			}
				
				result = frontFixCAucService.fixAdmSubDelProc(paramMap);
				
				if(result == 1){
					frontFixCAucService.fixSellSubFileDelProc(paramMap);
				}
				
				
				model.addAttribute("message", "정상적으로 삭제되었습니다.");
				model.addAttribute("returnUrl", "/front/fixCAuc/fixAdmSubList.do");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				result = 0;
			}
			model.addAttribute("result", result);
			return "/front/fixAuc/C/result";
		}
	    
	  
	    
	    @RequestMapping(value = "/front/fixCAuc/fixAdmSubChulRegProc.json")
		public String fixAdmSubChulRegProc(HttpServletRequest request, HttpServletResponse response
				  ,@RequestParam Map<String, Object> paramMap
				  		    , ModelMap model) throws Exception{
		    
			try {
				
				EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
				
				paramMap.put("id", loginVO.get("loginId"));
				paramMap.put("memberId", loginVO.get("memberId"));
				String aucType = String.valueOf(loginVO.get("aucType"));
				//01 출하농가 / 03 중도매인 / 07 경매사
				if(!"07".equals(aucType) ){
					ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
					modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
		            throw new ModelAndViewDefiningException(modelAndView);
				}
				
				int result = 0;
				String chulCode = String.valueOf(paramMap.get("chulCode"));
				EgovMap chulSubInfo = frontFixCAucService.getfixSubChulInfo(chulCode);
				if(chulSubInfo != null){
					result = 2;
					model.addAttribute("result",result);
					return "jsonView";
				}
				
				
				result = frontFixCAucService.fixAdmSubChulRegProc(paramMap);
				model.addAttribute("result", result);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		   
			
			return "jsonView";
		  }
	    
	    
	    
	    @RequestMapping(value = "/front/fixCAuc/fixAdmSubChulDelProc.json")
		public String fixAdmSubChulDelProc(HttpServletRequest request, HttpServletResponse response
				  ,@RequestParam Map<String, Object> paramMap
				  		    , ModelMap model) throws Exception{
		    
			try {
				
				EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
				
				paramMap.put("id", loginVO.get("loginId"));
				paramMap.put("memberId", loginVO.get("memberId"));
				String aucType = String.valueOf(loginVO.get("aucType"));
				//01 출하농가 / 03 중도매인 / 07 경매사
				if(!"07".equals(aucType) ){
					ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
					modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
		            throw new ModelAndViewDefiningException(modelAndView);
				}
				
				
				int result = frontFixCAucService.fixAdmSubChulDelProc(paramMap);
				model.addAttribute("result", result);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		   
			
			return "jsonView";
		  }
		
		
	    @RequestMapping(value="/front/fixCAuc/reqBuySubReg.do")
		public String reqBuySubReg(HttpServletRequest request, HttpServletResponse response,
				@RequestParam Map<String, Object> paramMap
				, ModelMap model) throws Exception {
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"03".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			//요청 접수 시간
			/*
			paramMap.put("timeType", "2");
			EgovMap timeVO = frontFixCAucService.getTime(paramMap);
			if(timeVO != null){
				if("N".equals(timeVO.get("timeCheck")) ){
					model.addAttribute("message", "요청접수시간이 아닙니다.");
					model.addAttribute("returnUrl", "/front/fixCAuc/reqBuyList.do");
					model.addAttribute("result", 1);
					return "/front/fixAuc/C/result";
				}
			}*/
			
			EgovMap fixVO = frontFixCAucService.getFixBuySubArticle(paramMap);
			model.addAttribute("result", fixVO);
			
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			//중도매인 정보
			List<EgovMap> floMemberList = frontLoginService.getFloMember(paramMap);
			
			List<EgovMap> floList = new ArrayList<EgovMap>();
			
			for(EgovMap chulVO : floMemberList){
				String jCode = String.valueOf(chulVO.get("chulCd"));
				EgovMap jInfo = frontFixCAucService.getJCodeToJInfo(jCode);
				floList.add(jInfo);
			}
			
			model.addAttribute("floList", floList);
			
			//등급 코드 리스트
			paramMap.put("bunChk", "C");
			List<EgovMap> levelList = frontFixCAucService.getLevelList(paramMap);
			model.addAttribute("levelList", levelList);
			
			if(fixVO != null){
				String chulCode = String.valueOf(fixVO.get("chulCode"));
				EgovMap chulInfo = frontFixCAucService.getChulCodeToChulInfo(chulCode);
				model.addAttribute("chulInfo", chulInfo);
				model.addAttribute("paramMap", paramMap);
			}
			
			model.addAttribute("title", "요청 - 분갈이");
			model.addAttribute("contentPath", "fixAuc/C/reqBuySubReg.jsp");
			
			return "/front/layout/fixLayout";
		}
		
		
		
		@RequestMapping(value="/front/fixCAuc/reqBuySubRegProc.do")
		public String reqBuySubRegProc(HttpServletRequest request, HttpServletResponse response,
				@RequestParam Map<String, Object> paramMap
				, ModelMap model) throws Exception {
			
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"03".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			/*
			String mokCode = String.valueOf(paramMap.get("mokCode"));
			if(!"029".equals(mokCode)){
				model.addAttribute("message", "잘못된 요청입니다.");
				model.addAttribute("returnUrl", "/front/fixCAuc/reqBuyList.do");
				model.addAttribute("result", 1);
				return "/front/fixAuc/C/result";
			}*/
			//요청 접수 시간
			/*
			paramMap.put("timeType", "2");
			EgovMap timeVO = frontFixCAucService.getTime(paramMap);
			if(timeVO != null){
				if("N".equals(timeVO.get("timeCheck")) ){
					model.addAttribute("message", "요청접수시간이 아닙니다.");
					model.addAttribute("returnUrl", "/front/fixCAuc/reqBuyList.do");
					model.addAttribute("result", 1);
					return "/front/fixAuc/C/result";
				}
			}*/
			
			int result = 0;
			try {
				paramMap.put("id", loginVO.get("loginId"));
				paramMap.put("memberId", loginVO.get("memberId"));
				String jCode = String.valueOf(paramMap.get("jCode"));
				if("".equals(jCode) || paramMap.get("jCode") == null){
					paramMap.put("code", loginVO.get("chulCd"));
					paramMap.put("jCode", loginVO.get("chulCd"));
				}else{
					paramMap.put("code", paramMap.get("jCode"));
				}
				
				EgovMap checkVO = frontFixCAucService.getFloMemberCheck(paramMap);
				if(checkVO != null){
					EgovMap limitVO = frontFixCAucService.getLimitAmt(paramMap);
					if(limitVO != null){
						EgovMap fixVO = frontFixCAucService.getFixBuySubArticle(paramMap);
						if(fixVO != null){
							paramMap.put("chulCode", fixVO.get("chulCode"));
							paramMap.put("pumCode", fixVO.get("pumCode"));
							paramMap.put("pumName", fixVO.get("pumName"));
							paramMap.put("mokCode", fixVO.get("mokCode"));
							paramMap.put("mokName", fixVO.get("mokName"));
							paramMap.put("chulLevel", "21");
							paramMap.put("chulLevelName", "상");
							paramMap.put("unitPrice", fixVO.get("unitPrice"));
						}
						
						int limitAmt = Integer.parseInt(String.valueOf(limitVO.get("limitAmt")));
						int sokCnt = Integer.parseInt(String.valueOf(paramMap.get("sokCnt")));
						int unitPrice = Integer.parseInt(String.valueOf(paramMap.get("unitPrice")));
						
						if(limitAmt >= (sokCnt*unitPrice)){
							paramMap.put("reqDealSeq", egovReqIdGnrService.getNextIntegerId()); 
							paramMap.put("id", loginVO.get("loginId"));
							paramMap.put("memberId", loginVO.get("memberId"));
							
							
							
							result = frontFixCAucService.reqBuySubRegProc(paramMap);
							
							model.addAttribute("message", "정상적으로 저장되었습니다.");
							model.addAttribute("returnUrl", "/front/fixCAuc/reqBuyList.do");
							
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
			return "/front/fixAuc/C/result";
		}
	    
		 /* 정산관리 */
		  
		  @RequestMapping(value="/front/fixCAuc/tranAdmList.do")
		  public String tranAdmList(HttpServletRequest request, HttpServletResponse response,
					@RequestParam Map<String, Object> paramMap
					, ModelMap model) throws Exception {
				
				EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
				
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
						paramMap.put("searchChulDate",frontFixCAucService.selectMaxChulDate(paramMap));
					}
					
					
					Map<String, Object> param = new HashMap<String, Object>();
				    param.putAll(paramMap);
				    param.put("pageIndex", null);
				    
					String paginationQueryString = MapQuery.urlEncodeUTF8(param);
					
					paramMap.put("id", loginVO.get("loginId"));
					paramMap.put("memberId", loginVO.get("memberId"));
					
					EgovMap fixInfo = frontFixCAucService.getTranAdmInfo(paramMap);
					
					List<EgovMap> fixList = frontFixCAucService.getTranAdmList(paramMap);
					
					int totCnt = frontFixCAucService.getTranAdmListCnt(paramMap);
					
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
				model.addAttribute("contentPath", "fixAuc/C/tranAdmList.jsp");
				
				return "/front/layout/fixLayout";
		}
		  
		  
		  @RequestMapping(value = "/front/fixCAuc/tranMagamInsProc.json")
		  public String tranMagamInsProc(HttpServletRequest request
				  , HttpServletResponse response
				  , @RequestParam Map<String, Object> paramMap
				  , ModelMap model ) throws Exception{
		    
			try {
				
				EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
				
				String aucType = String.valueOf(loginVO.get("aucType"));
				if(!"07".equals(aucType)){
					ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
					modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
		            throw new ModelAndViewDefiningException(modelAndView);
				}
				
				
				int tranCnt = frontFixCAucService.tranMagamTranCnt(paramMap);
				
				if(tranCnt > 0){
					//전송데이터 존재
					model.addAttribute("errorCode","1");
					model.addAttribute("tranCnt",tranCnt);
					return "jsonView";
				}
				
				paramMap.put("id", loginVO.get("loginId"));
				paramMap.put("memberId", loginVO.get("memberId"));
				
				//마감처리
				
				frontFixCAucService.tranMagamDelProc(paramMap);
				
				int result = frontFixCAucService.tranMagamInsProc(paramMap);
				model.addAttribute("result", result);
				
			} catch (Exception e) {
				
				// TODO: handle exception
				e.printStackTrace();
			}
		   
			
			return "jsonView";
		  } 
		  
		  
		  @RequestMapping(value = "/front/fixCAuc/tranMagamDelProc.json")
		  public String tranMagamDelProc(HttpServletRequest request
				  , HttpServletResponse response
				  , @RequestParam Map<String, Object> paramMap
				  , ModelMap model ) throws Exception{
		    
			try {
				
				EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
				
				String aucType = String.valueOf(loginVO.get("aucType"));
				if(!"07".equals(aucType)){
					ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
					modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
		            throw new ModelAndViewDefiningException(modelAndView);
				}
				
				int tranCnt = frontFixCAucService.tranMagamTranCnt(paramMap);
				
				if(tranCnt > 0){
					//전송데이터 존재
					model.addAttribute("errorCode","1");
					model.addAttribute("tranCnt",tranCnt);
					return "jsonView";
				}
				
				paramMap.put("id", loginVO.get("loginId"));
				paramMap.put("memberId", loginVO.get("memberId"));
				
				//마감 취소처리
				frontFixCAucService.tranMagamDelProc(paramMap);
				
			} catch (Exception e) {
				
				// TODO: handle exception
				e.printStackTrace();
			}
		   
			
			return "jsonView";
		  }  
		  
		  
		  @RequestMapping(value = "/front/fixCAuc/tranMagamComp.json")
		  public String tranMagamComp(HttpServletRequest request
				      , HttpServletResponse response
				      , @RequestParam(value="checkedArray[]") List<String> checkedArray
				      , @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
		    
			try {
				System.out.println(paramMap.toString());
				
				EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
				
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
					EgovMap checkVO = frontFixCAucService.tranMagamCompCheck(paramMap);
					
					if(checkVO != null){
						//전송데이터 존재
						model.addAttribute("errorCode","1");
						model.addAttribute("errorVO",checkVO);
						return "jsonView";
					}*/
					
					int cnt = frontFixCAucService.tranMagamComp(paramMap);
					
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
		  
		  
		  
		  @RequestMapping(value="/front/fixCAuc/tranCompListExcell.do")
			public void tranCompListExcell(
					  @RequestParam Map<String, Object> paramMap
					, HttpServletResponse response
					, HttpServletRequest request
					, ModelMap model) throws Exception{
			 
			  try {
			  
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
			String aucType = String.valueOf(loginVO.get("aucType"));
			//01 출하농가 / 03 중도매인 / 07 경매사
			if(!"07".equals(aucType) ){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	          throw new ModelAndViewDefiningException(modelAndView);
			}
			
			 List<Map<String,Object>> list = frontFixCAucService.getTranListMagam(paramMap);
			 
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
		  
		  
		  @RequestMapping(value="/front/fixCAuc/transAdmCompList.do")
		  public String transAdmCompList(HttpServletRequest request, HttpServletResponse response,
					@RequestParam Map<String, Object> paramMap
					, ModelMap model) throws Exception {
				
				EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
				
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
					
					List<EgovMap> fixList = frontFixCAucService.getFixAucCompChulList(paramMap);
					
					//List<EgovMap> fixList2 = frontFixCAucService.getFixAucCompDomeList(paramMap);
					//int result = frontFixCAucService.updateFixAucCompChulBigo(paramMap);
				    
					model.addAttribute("resultList", fixList);
					
					model.addAttribute("paramMap", paramMap);
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
				return "/front/fixAuc/C/transAdmCompList";
		  }
		  
		  
		  @RequestMapping(value="/front/fixCAuc/transAdmCompListUpdate.json")
		  public String transAdmCompListUpdate(HttpServletRequest request
				    , HttpServletResponse response
				    , @RequestParam Map<String, Object> paramMap
				    , @RequestParam(value="tranSeqArray[]") List<String> tranSeqArray
				    , @RequestParam(value="bigoArray[]") List<String> bigoArray
					, ModelMap model) throws Exception {
				
				EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
				
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
						int cnt = frontFixCAucService.updateFixAucCompChulBigo(paramMap);
						
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
		  
		  
		  @RequestMapping(value="/front/fixCAuc/transAdmCompChulListPrint.do")
		  public String transAdmCompChulListPrint(HttpServletRequest request, HttpServletResponse response,
					@RequestParam Map<String, Object> paramMap
					, ModelMap model) throws Exception {
				
				EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
				
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
					
					List<EgovMap> fixList = frontFixCAucService.getFixAucCompChulList(paramMap);
					
					model.addAttribute("resultList", fixList);
					model.addAttribute("paramMap", paramMap);
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
				return "/front/fixAuc/C/transAdmCompChulListPrint";
		  }
		  
		  @RequestMapping(value="/front/fixCAuc/transAdmCompDomeListPrint.do")
		  public String transAdmCompDomeListPrint(HttpServletRequest request, HttpServletResponse response,
					@RequestParam Map<String, Object> paramMap
					, ModelMap model) throws Exception {
				
				EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
				
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
					
					List<EgovMap> fixList = frontFixCAucService.getFixAucCompDomeList(paramMap);
					
					model.addAttribute("resultList", fixList);
					model.addAttribute("paramMap", paramMap);
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
				return "/front/fixAuc/C/transAdmCompDomeListPrint";
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
					EgovMap jInfo = frontFixCAucService.getJCodeToJInfo(jCode);
					
					if(jInfo.get("bunChk").equals("C")) {
						jCodeList.add(jInfo.get("code").toString());
					}
				}
				paramMap.put("jCodeList", jCodeList);
		  }
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
	  @RequestMapping(value = "/front/fixCAuc/tranMagamUpdateTrans.json")
	  public String tranMagamUpdateTrans(HttpServletRequest request
			      , HttpServletResponse response
			      , @RequestParam(value="checkedArray[]") List<String> checkedArray
			      , @RequestParam(value="pumKg") Integer pumKg
			      , @RequestParam(value="trans") String trans
			      , @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
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

				int cnt = frontFixCAucService.updateMagamTrans(paramMap);
				
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
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  @RequestMapping(value = "/front/fixCAuc/tranUpdateTransReq.json")
	  public String tranUpdateTransReq(HttpServletRequest request
			      , HttpServletResponse response
			      , @RequestParam(value="checkedArray[]") List<String> checkedArray
			      , @RequestParam(value="pumKg") Integer pumKg
			      , @RequestParam(value="trans") String trans
			      , @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
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

				int cnt = frontFixCAucService.updateTransReq(paramMap);
				
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
	  
	  
	  @RequestMapping(value = "/front/fixCAuc/tranUpdateTransFix.json")
	  public String tranUpdateTransFix(HttpServletRequest request
			      , HttpServletResponse response
			      , @RequestParam(value="checkedArray[]") List<String> checkedArray
			      , @RequestParam(value="pumKg") Integer pumKg
			      , @RequestParam(value="trans") String trans
			      , @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
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

				int cnt = frontFixCAucService.updateTransFix(paramMap);
				
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
}
