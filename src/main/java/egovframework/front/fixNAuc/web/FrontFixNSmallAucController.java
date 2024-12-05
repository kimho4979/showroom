package egovframework.front.fixNAuc.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import egovframework.cms.cmm.service.EgovFileMngUtil;
import egovframework.cms.cmm.service.EgovProperties;
import egovframework.cms.cmm.service.FileVO;
import egovframework.cms.cmm.util.MapQuery;
import egovframework.front.fixAuc.service.FrontFixAucRecordService;
import egovframework.front.fixNAuc.service.FrontFixNAucService;
import egovframework.front.fixNAuc.service.FrontFixNAucSmallService;
import egovframework.front.login.service.FrontLoginService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import net.coobird.thumbnailator.Thumbnails;

@Controller
public class FrontFixNSmallAucController {
	private static final Logger logger = LoggerFactory.getLogger(FrontFixNSmallAucController.class);

	@Resource(name = "frontLoginService")
	protected FrontLoginService frontLoginService;
	
	@Resource(name = "frontFixNAucSmallService")
	private FrontFixNAucSmallService frontFixNAucSmallService;
	
	@Resource(name = "frontFixNAucService")
	private FrontFixNAucService frontFixNAucService;
	
	//TB_RECORD 상태 변경 이력 
	@Resource(name = "FrontFixAucRecordService")
	private FrontFixAucRecordService frontFixAucRecordService;
	
	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil egovFileMngUtil;
	
	@RequestMapping(value="/front/fixAuc/fixSmallAdmList.do")
	public String fixSmallAdmList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		if(!Boolean.valueOf(String.valueOf(request.getSession().getAttribute("aucType07")))){
		
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 매뉴 권한이 없습니다. 통합로그인 후 사용하세요.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
	
		EgovMap nFloLoginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
		EgovMap cFloLoginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
		EgovMap yFloLoginVO = (EgovMap) request.getSession().getAttribute("yFloLoginVO");
		
				
		if(nFloLoginVO != null){
			return "redirect:/front/fixNAuc/fixSmallAdmList.do";
		} else if (cFloLoginVO != null){
			return "redirect:/front/fixCAuc/fixSmallAdmList.do";
		} else if (yFloLoginVO != null){
			return "redirect:/front/fixYAuc/fixSmallAdmList.do";
		}
		
		return "redirect:/front/fixNAuc/fixSmallAdmList.do";
	}
	
	@RequestMapping(value="/front/fixNAuc/fixSmallAdmList.do")
	public String fixSmallAdmListN(HttpServletRequest request, HttpServletResponse response,
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
				paramMap.put("searchStartDt",format.format(date));
				/*
				Calendar cal = Calendar.getInstance();
		        cal.setTime(date);
		        cal.add(Calendar.DATE, -6);
				paramMap.put("searchStartDt",format.format(cal.getTime()));*/
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
				else if(referer.contains("fixNAuc/fixSmallAdmList.do")) {
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
			
			List<EgovMap> fixList = frontFixNAucSmallService.getFixAdmList(paramMap);
			
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
			
			int totCnt = frontFixNAucSmallService.getFixAdmListCnt(paramMap);
			
			paginationInfo.setTotalRecordCount(totCnt);
			
			
			//입찰 가능 시간
			paramMap.put("timeType", "3");
			EgovMap timeVO = frontFixNAucSmallService.getTime(paramMap);
			
			//판매접수시간
			paramMap.put("timeType", "1");
			EgovMap regTimeVO = frontFixNAucSmallService.getTime(paramMap);
			
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
		
		
		model.addAttribute("title", "입찰관리");
		model.addAttribute("contentPath", "fixAuc/N/fixSmallAdmList.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	
	@RequestMapping(value="/front/fixNAuc/fixSmallAdmView.do")
	public String fixSmallAdmView(HttpServletRequest request, HttpServletResponse response,
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
		
		
		EgovMap fixVO = frontFixNAucSmallService.getFixAdmArticle(paramMap);
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
			
			List<EgovMap> fixFileList = frontFixNAucSmallService.getFixFileList(paramMap);
			model.addAttribute("fixFileList", fixFileList);
			
			String dealType = String.valueOf(fixVO.get("dealType"));
			int remainSokCnt = Integer.parseInt(String.valueOf(fixVO.get("sokCnt")));
			
			List<EgovMap> bidList = new ArrayList<EgovMap>();
			
			// 정가 : 등록시간순 / 최저가 : 금액순, 등록시간순 / 희망가: 금액순, 등록시간순
			if("F".equals(dealType)){
				//정가
				//입찰정보 가져오기
				paramMap.put("bidOrder", "F");
				bidList = frontFixNAucSmallService.getBidList(paramMap);
				
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
				
				bidList = frontFixNAucSmallService.getBidList(paramMap);
				
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
			
			List<EgovMap> bidCancelList = frontFixNAucSmallService.getBidCancelList(paramMap);
			
			for(EgovMap bidVO : bidCancelList){
				bidVO.put("nakSokCnt",0);
				bidVO.put("nakState", "5");
				bidList.add(bidVO);
			}
			
			model.addAttribute("bidList", bidList);
			
			
			//TB_RECORD 상태 변경 이력  이력 리스트
			paramMap.put("fixType", "3");
			paramMap.put("bunChk", "N");
			paramMap.put("dealSeq", paramMap.get("fixDealSeq"));
			List<EgovMap> recordList = frontFixAucRecordService.getFixRecordList(paramMap);
			model.addAttribute("recordList", recordList);
	
		}
		
		model.addAttribute("result", fixVO);
		
		model.addAttribute("title", "입찰관리");
		model.addAttribute("contentPath", "fixAuc/N/fixSmallAdmView.jsp");
		
		return "/front/layout/fixLayout";
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
	
	
	
	
	
	@RequestMapping(value = "/front/fixNAuc/fixSmallAdmSangJang.json")
	public String fixSmallAdmSangJang(
			HttpServletRequest request, 
			HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap, 
			ModelMap model) throws Exception{
		
		try {
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			// [ Vaild ] 경매사 권한
			String aucType = String.valueOf(loginVO.get("aucType"));
			if(!"07".equals(aucType)){
				
				model.addAttribute("errorCode", "9999");
				model.addAttribute("errorMessage", "[재상장 내역 불러오기] 기능에 대한 권한이 없습니다.");
				return "jsonView";
			}
			
			// [ Vaild ] 화, 목, 토 
			Object upDay = paramMap.get("upDay");
			if(upDay == null) {
				
				model.addAttribute("errorCode", "9998");
				model.addAttribute("errorMessage", "[재상장 내역 불러오기] 재상장 일자를 입력해주세요.");
				return "jsonView";
				
			}else {
				
				String upDayStr = upDay.toString();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
			    Calendar cal = Calendar.getInstance();
			    cal.setTime(dateFormat.parse(upDayStr));
			    
			    int dayNum = cal.get(Calendar.DAY_OF_WEEK);
			    
			    if(dayNum == 1 || dayNum == 2 || dayNum == 4 || dayNum == 6) {
			    	model.addAttribute("errorCode", "9997");
			    	model.addAttribute("errorMessage", "[재상장 내역 불러오기]는 화요일, 목요일, 토요일만 가능합니다.");
					return "jsonView";
			    }
			}
			
			// [ Vaild ] 기존에 등록되어 있는지
			int resultCnt = frontFixNAucSmallService.getFixSmallDealCount(paramMap);
			if(resultCnt > 0) {
				model.addAttribute("errorCode", "9996");
		    	model.addAttribute("errorMessage", "[재상장 내역 불러오기] 이미 불러온 재상장 내역입니다.");
				return "jsonView";
			}
			
			// Action
			int result = frontFixNAucSmallService.fixSmallDealRegProc(paramMap);
			model.addAttribute("result", result);
			
			
/*			if (result > 0) {
			//TB_RECORD 상태 변경 이력  Map
			Map<String, Object> recordMap = new HashMap<String, Object>();
			String fixDealSeq = "";
			
			List<EgovMap> dealSeq = frontFixNAucSmallService.getFixSmallDealSeq(paramMap);
			recordMap.put("dealSeq", "");
			recordMap.put("fixType", "");
			recordMap.put("bunChk", "");
			recordMap.put("fixState", "");
			recordMap.put("id", "");
			
			for(EgovMap seq : dealSeq){
				fixDealSeq = String.valueOf(seq.get("fixDealSeq"));
				
				//TB_RECORD 상태 변경 이력 데이터 put
				recordMap.put("dealSeq", fixDealSeq);
				recordMap.put("fixType", "3");
				recordMap.put("bunChk", "N");
				recordMap.put("fixState", "3");
				recordMap.put("id", paramMap.get("id"));
				
				//TB_RECORD 상태 변경 이력  - 경매사 최초 등록 (3)
				frontFixAucRecordService.fixAucRegRecord(recordMap);
				
			}
		  }*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	   
		return "jsonView";
	  }
	
	
	
	@RequestMapping(value = "/front/fixNAuc/fixSmallAdmSangJangRecord.json")
	public String fixSmallAdmSangJangRecord(
			HttpServletRequest request, 
			HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap, 
			ModelMap model) throws Exception{
		
		try {
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			// [ Vaild ] 경매사 권한
			String aucType = String.valueOf(loginVO.get("aucType"));
			if(!"07".equals(aucType)){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
						
			//TB_RECORD 상태 변경 이력  Map
			Map<String, Object> recordMap = new HashMap<String, Object>();
			String fixDealSeq = "";
			
			List<EgovMap> dealSeq = frontFixNAucSmallService.getFixSmallDealSeq(paramMap);
			recordMap.put("dealSeq", "");
			recordMap.put("fixType", "");
			recordMap.put("bunChk", "");
			recordMap.put("fixState", "");
			recordMap.put("id", "");
			
			for(EgovMap seq : dealSeq){
				fixDealSeq = String.valueOf(seq.get("fixDealSeq"));
				
				//TB_RECORD 상태 변경 이력 데이터 put
				recordMap.put("dealSeq", fixDealSeq);
				recordMap.put("fixType", "3");
				recordMap.put("bunChk", "N");
				recordMap.put("fixState", "3");
				recordMap.put("id", paramMap.get("id"));
				
				//TB_RECORD 상태 변경 이력  - 경매사 최초 등록 (3)
				frontFixAucRecordService.fixAucRegRecord(recordMap);
				
			}
		  
		} catch (Exception e) {
			e.printStackTrace();
		}
	   
		return "jsonView";
	  }
	
	
	@RequestMapping(value = "/front/fixNAuc/fixSmallAdmSangJangDel.json")
	public String fixSmallAdmSangJangDel(
			HttpServletRequest request, 
			HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap, 
			ModelMap model) throws Exception{
		
		try {
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			// [ Vaild ] 경매사 권한
			String aucType = String.valueOf(loginVO.get("aucType"));
			if(!"07".equals(aucType)){
				
				model.addAttribute("errorCode", "9999");
				model.addAttribute("errorMessage", "[재상장 내역 삭제] 기능에 대한 권한이 없습니다.");
				return "jsonView";
			}
			
			// [ Vaild ] 화, 목, 토 
			Object upDay = paramMap.get("upDay");
			if(upDay == null) {
				
				model.addAttribute("errorCode", "9998");
				model.addAttribute("errorMessage", "[재상장 내역 삭제] 재상장 일자를 입력해주세요.");
				return "jsonView";
				
			}else {
				
				String upDayStr = upDay.toString();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
			    Calendar cal = Calendar.getInstance();
			    cal.setTime(dateFormat.parse(upDayStr));
			    
			    int dayNum = cal.get(Calendar.DAY_OF_WEEK);
			    
			    if(dayNum == 1 || dayNum == 2 || dayNum == 4 || dayNum == 6) {
			    	model.addAttribute("errorCode", "9997");
			    	model.addAttribute("errorMessage", "[재상장 내역 삭제]는 화요일, 목요일, 토요일만 가능합니다.");
					return "jsonView";
			    }
			}
			
			// [ Vaild ] 기존에 등록되어 있는지
			int resultCnt = frontFixNAucSmallService.getFixSmallDealCount(paramMap);
			if(resultCnt == 0) {
				model.addAttribute("errorCode", "9996");
		    	model.addAttribute("errorMessage", "[재상장 내역 삭제] 이미 삭제된 내역입니다.");
				return "jsonView";
			}
			
			//TB_RECORD 상태 변경 이력  Map
			Map<String, Object> recordMap = new HashMap<String, Object>();
			String fixDealSeq = "";
			String preFixState = "";
			
			List<EgovMap> dealSeq = frontFixNAucSmallService.getFixSmallDealSeq(paramMap);
			
			// Action
			int result = frontFixNAucSmallService.fixSmallDealRegProcDel(paramMap);
			model.addAttribute("result", result);
			
		
			recordMap.put("dealSeq", "");
			recordMap.put("fixType", "");
			recordMap.put("bunChk", "");
			recordMap.put("fixState", "");
			recordMap.put("id", "");
			
			for(EgovMap seq : dealSeq){
				fixDealSeq = String.valueOf(seq.get("fixDealSeq"));
				preFixState = String.valueOf(seq.get("fixState"));
				
				if(result > 0){
					paramMap.put("fixDealSeq", fixDealSeq);
					frontFixNAucSmallService.fixSellFileDelProc(paramMap);
				}
				
				//TB_RECORD 상태 변경 이력 데이터 put
				recordMap.put("dealSeq", fixDealSeq);
				recordMap.put("fixType", "3");
				recordMap.put("bunChk", "N");
				recordMap.put("preFixState", preFixState);
				recordMap.put("id", paramMap.get("id"));
				
				//TB_RECORD 상태 변경 이력  - 삭제
				frontFixAucRecordService.fixAucRegRecord(recordMap);
				
			}
	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	   
		return "jsonView";
	  }
	
	
	  @RequestMapping(value = "/front/fixNAuc/fixSmallAdmApply.json")
	  public String fixSmallAdmApply(HttpServletRequest request
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
				preFixState = frontFixAucRecordService.getPreFixSmallState(fixDealSeq);
				
				paramMap.put("fixDealSeq", fixDealSeq);
				paramMap.put("aucDate", aucDate);
				int cnt = frontFixNAucSmallService.fixAdmApply(paramMap);
				
				//TB_RECORD 상태 변경 이력 데이터 put
				recordMap.put("dealSeq", fixDealSeq);
				recordMap.put("fixType", "3");
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
	  
	
	  
	  @RequestMapping(value = "/front/fixNAuc/fixSmallAdmAppCancel.json")
	  public String fixSmallAdmAppCancel(HttpServletRequest request
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
				preFixState = frontFixAucRecordService.getPreFixSmallState(fixDealSeq);
				
				paramMap.put("fixDealSeq", fixDealSeq);
				paramMap.put("aucDate", aucDate);
				int cnt = frontFixNAucSmallService.fixAdmAppCancel(paramMap);
				
				//TB_RECORD 상태 변경 이력 데이터 put
				recordMap.put("dealSeq", fixDealSeq);
				recordMap.put("fixType", "3");
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
	  
	  
	  @RequestMapping(value = "/front/fixNAuc/fixSmallAdmComp.json")
	  public String fixSmallAdmComp(HttpServletRequest request
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
				preFixState = frontFixAucRecordService.getPreFixSmallState(fixDealSeq);
				
				//상장정보 
				EgovMap fixVO = frontFixNAucSmallService.getFixAdmArticle(paramMap);
				int sokCnt = Integer.parseInt(String.valueOf(fixVO.get("sokCnt")));
				int unitPrice = Integer.parseInt(String.valueOf(fixVO.get("unitPrice")));
				String dealType = String.valueOf(fixVO.get("dealType"));
				int remainSokCnt = sokCnt;
				//입찰 된 속/분 수량
				int bidTotSokCnt = frontFixNAucSmallService.getBidTotSokCnt(paramMap);
				
				if(bidTotSokCnt == 0 ){
					//유찰
					int cnt = frontFixNAucSmallService.fixAdmUChal(paramMap);
					
					//유찰 상태
					fixState = "5";
					
					if(cnt > 0){
						result++;
					}
					
				}else if(bidTotSokCnt > 0 && sokCnt > bidTotSokCnt){
					//부분유찰
					int cnt = frontFixNAucSmallService.fixAdmPartUChal(paramMap);
					
					//부분 유찰 상태
					fixState = "6";
					
					if(cnt > 0){
						result++;
					}
					frontFixNAucSmallService.fixAdmBidTotSuc(paramMap);
				}else if(bidTotSokCnt > 0 && sokCnt == bidTotSokCnt){
					//판매완료
					int cnt = frontFixNAucSmallService.fixAdmComp(paramMap);
					
					//완료 상태
					fixState = "4";
					
					if(cnt > 0){
						result++;
					}
					frontFixNAucSmallService.fixAdmBidTotSuc(paramMap);
				}else{
					//패찰자 확인
					// 정가 : 등록시간순 / 최저가 : 금액순, 등록시간순 / 희망가: 금액순, 등록시간순
					if("F".equals(dealType)){
						//정가
						//입찰정보 가져오기
						paramMap.put("bidOrder", "F");
						List<EgovMap> bidList = frontFixNAucSmallService.getBidList(paramMap);
						
						for(int j=0; j<bidList.size(); j++ ){
							EgovMap bidVO = bidList.get(j);
							paramMap.put("bidSeq",bidVO.get("bidSeq"));
							int bidSokCnt = Integer.parseInt(String.valueOf(bidVO.get("bidSokCnt")));
							int preRemainSokCnt = remainSokCnt;
							
							remainSokCnt = (remainSokCnt - bidSokCnt);
							
							if(remainSokCnt>=0){
								//낙찰 : 2
								frontFixNAucSmallService.fixAdmBidSuc(paramMap);
							}else if(remainSokCnt<0 && preRemainSokCnt>0){
								//부분 낙찰 : 4
								paramMap.put("nakSokCnt",preRemainSokCnt);
								frontFixNAucSmallService.fixAdmBidPartSuc(paramMap);
							}else{
								//패찰 : 3
								frontFixNAucSmallService.fixAdmBidFail(paramMap);
							}
							
						}
						
					}else{
						//최저가, 희망가
						paramMap.put("bidOrder", "L");
						
						List<EgovMap> bidList = frontFixNAucSmallService.getBidList(paramMap);
						
						for(int j=0; j<bidList.size(); j++ ){
							EgovMap bidVO = bidList.get(j);
							paramMap.put("bidSeq",bidVO.get("bidSeq"));
							int bidSokCnt = Integer.parseInt(String.valueOf(bidVO.get("bidSokCnt")));
							int preRemainSokCnt = remainSokCnt;
							
							remainSokCnt = (remainSokCnt - bidSokCnt);
							
							if(remainSokCnt>=0){
								//낙찰 : 2
								frontFixNAucSmallService.fixAdmBidSuc(paramMap);
							}else if(remainSokCnt<0 && preRemainSokCnt>0){
								//부분 낙찰 : 4
								paramMap.put("nakSokCnt",preRemainSokCnt);
								frontFixNAucSmallService.fixAdmBidPartSuc(paramMap);
							}else{
								//패찰 : 3
								frontFixNAucSmallService.fixAdmBidFail(paramMap);
							}
							
						}
						
					}
					
					
					int cnt = frontFixNAucSmallService.fixAdmComp(paramMap);
					
					//완료 상태
					fixState = "4";
					
					if(cnt > 0){
						result++;
					}
					
					
				}
				
				//TB_RECORD 상태 변경 이력 데이터 put
				recordMap.put("dealSeq", fixDealSeq);
				recordMap.put("fixType", "3");
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
	  
	  
	  @RequestMapping(value = "/front/fixNAuc/fixSmallAdmBan.json")
	  public String fixSmallAdmBan(HttpServletRequest request
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
				preFixState = frontFixAucRecordService.getPreFixSmallState(fixDealSeq);
				
				paramMap.put("fixDealSeq", fixDealSeq);
				paramMap.put("banText", banText);
				int cnt = frontFixNAucSmallService.fixAdmBan(paramMap);
				
				//TB_RECORD 상태 변경 이력 데이터 put
				recordMap.put("dealSeq", fixDealSeq);
				recordMap.put("fixType", "3");
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
	  	  
	 
	  
		private List<FileVO> uploadThumbFile(ModelMap model, MultipartHttpServletRequest multiRequest) throws Exception{
			
			String mode = EgovProperties.getProperty("Globals.Mode");
			String savePath = EgovProperties.getProperty("Globals.DstbtPath"+mode);
			
		    String uploadPath = savePath + "/uploads/fixSmallAuc/N/";
			
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
		
		
		
		
		
		
		@RequestMapping(value="/front/fixNAuc/fixSmallAdmUpt.do")
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
			
			
			EgovMap fixVO = frontFixNAucSmallService.getFixAdmArticle(paramMap);
			if(fixVO != null){
				
				if(!"1".equals(String.valueOf(fixVO.get("fixState"))) && !"2".equals(String.valueOf(fixVO.get("fixState"))) && !"3".equals(String.valueOf(fixVO.get("fixState")))  && !"7".equals(String.valueOf(fixVO.get("fixState")))){
					model.addAttribute("message", "비정상적인 접근입니다.");
					model.addAttribute("returnUrl", "/front/fixNAuc/fixSmallAdmList.do");
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
				List<EgovMap> fixFileList = frontFixNAucSmallService.getFixFileList(paramMap);
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
			model.addAttribute("contentPath", "fixAuc/N/fixSmallAdmUpt.jsp");
			
			return "/front/layout/fixLayout";
		}
		
		
		@RequestMapping(value="/front/fixNAuc/fixSmallAdmUptProc.do")
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
				
			    String type = (String)paramMap.get("type");
				if(type == null || type.isEmpty()) {
					type = "";
				}
			    
			    
				paramMap.put("id", loginVO.get("loginId"));
				paramMap.put("memberId", loginVO.get("memberId"));
				paramMap.put("code",paramMap.get("chulCode"));
				
				EgovMap chulVO = frontFixNAucService.getCodeToLoginId(paramMap);
				
				if(chulVO != null){
					paramMap.put("chulId", chulVO.get("loginId"));
				}
				
				EgovMap fixVO = frontFixNAucSmallService.getFixAdmArticle(paramMap);
				if(fixVO != null){
					
					if(!"1".equals(String.valueOf(fixVO.get("fixState"))) && !"2".equals(String.valueOf(fixVO.get("fixState")))  && !"3".equals(String.valueOf(fixVO.get("fixState")))  && !"7".equals(String.valueOf(fixVO.get("fixState")))){
						model.addAttribute("message", "비정상적인 접근입니다.");
						model.addAttribute("returnUrl", "/front/fixNAuc/fixAdmList.do");
						model.addAttribute("result", 1);
						return "/front/fixAuc/N/result";
					}
				}	 
				paramMap.put("id", loginVO.get("loginId"));
				paramMap.put("memberId", loginVO.get("memberId"));
				
				//TB_RECORD 상태 변경 이력 map 
				//Map<String, Object> recordMap = new HashMap<String, Object>();
				//String preFixState = frontFixAucRecordService.getPreFixState(String.valueOf(paramMap.get("fixDealSeq")));
							
				//result = frontFixNAucService.fixAdmUptProc(paramMap);
				
				
				//TB_RECORD 상태 변경 이력 데이터 put
				//recordMap.put("dealSeq", paramMap.get("fixDealSeq"));
				//recordMap.put("fixType", "1");
				//recordMap.put("bunChk", "N");
				//recordMap.put("preFixState", preFixState);
				//recordMap.put("fixState", "3");
				//recordMap.put("id", paramMap.get("id"));
				
				//TB_RECORD 경매사 수정 (1,3->3)
				//frontFixAucRecordService.fixAucRegRecord(recordMap);
				
				
				//파일
				
				String[] deleteAtchFile = request.getParameterValues("deleteAtchFile");
				if(deleteAtchFile != null && deleteAtchFile.length > 0){
					for (int i = 0; i < deleteAtchFile.length; i++){
						paramMap.put("fileSeq",deleteAtchFile[i]);
						frontFixNAucSmallService.deleteFileOne(paramMap);
				      }
				}
				
				List<FileVO> uploadFileList = uploadThumbFile(model, multiRequest);
				
				if ((uploadFileList != null) && (uploadFileList.size() > 0)){
					Map<String, Object> fileMap = new HashMap<String, Object>();
			      
					for (int i = 0; i < uploadFileList.size(); i++){
						
			    	  FileVO fileVO = uploadFileList.get(i);
			    	  fileMap.put("fixDealSeq", paramMap.get("fixDealSeq"));
			    	  
			    	  if(type.equals("list")) {
			    		  int num = 0;
			    		  while( num < 10 ){
			    			  fileMap.put("fileSeq", num);
			    			  EgovMap fileCheckVO = frontFixNAucSmallService.getFileOne(fileMap);
			    		        if( fileCheckVO == null ){
			    		            break;
			    		        }
			    		        num++;
			    		   }
			    	  }else {
			    		  fileMap.put("fileSeq", ((FileVO)uploadFileList.get(i)).getFileSn());  
			    	  }
			    	  
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
			    	  
			    	  EgovMap fileCheckVO = frontFixNAucSmallService.getFileOne(fileMap);
			    	  if(fileCheckVO != null){
			    		  frontFixNAucSmallService.deleteFileOne(fileMap);
			    	  }
			    	  
			    	  frontFixNAucSmallService.fixSellFileProc(fileMap);
			    	  
			    	  
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
				result = 1;
				model.addAttribute("message", "정상적으로 저장되었습니다.");
				
				if(type.equals("list")) {
					result = 2;
					model.addAttribute("returnUrl", "/front/fixNAuc/fixSmallAdmUpt.do?fixDealSeq="+paramMap.get("fixDealSeq"));
				}else {
					model.addAttribute("returnUrl", "/front/fixNAuc/fixSmallAdmList.do");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				result = 0;
			}
			model.addAttribute("result", result);
			return "/front/fixAuc/N/result";
		}
		
		
		
		
		  @RequestMapping(value = "/front/fixNAuc/fixSmallAdmBidUpdate.json")
		  public String fixSmallAdmBidUpdate(HttpServletRequest request
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
				
				int result = frontFixNAucSmallService.fixAdmBidUpdate(paramMap);
				
				model.addAttribute("result",result);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		   
			return "jsonView";
		  }
		  
		  
		  
		  
		  @RequestMapping(value = "/front/fixNAuc/fixSmallAdmBidCancel.json")
		  public String fixSmallAdmBidCancel(HttpServletRequest request
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
				
				int result = frontFixNAucSmallService.fixAdmBidCancel(paramMap);
				
				model.addAttribute("result",result);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		   
			return "jsonView";
		  }
		  
		  
		  
		  @RequestMapping(value = "/front/fixNAuc/fixSmallAdmBidAdmin.json")
		  public String fixSmallAdmBidAdmin(HttpServletRequest request
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
				
				int result = frontFixNAucSmallService.fixAdmBidAdmin(paramMap);
				
				model.addAttribute("result",result);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		   
			return "jsonView";
		  }
		
	
}
