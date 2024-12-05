package egovframework.front.fixCAuc.web;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
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
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import egovframework.cms.cmm.service.EgovProperties;
import egovframework.cms.cmm.util.MapQuery;
import egovframework.front.fixCAuc.service.FrontFixCAucService;
import egovframework.front.fixCAuc.service.FrontFixCAucSmallJService;
import egovframework.front.login.service.FrontLoginService;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class FrontFixCSmallJAucController {
	private static final Logger logger = LoggerFactory.getLogger(FrontFixCSmallJAucController.class);

	@Resource(name = "frontLoginService")
	protected FrontLoginService frontLoginService;
	
	@Resource(name = "FrontFixCAucSmallJService")
	private FrontFixCAucSmallJService frontfixCAucSmallJService;
	
	@Resource(name = "frontFixCAucService")
	private FrontFixCAucService frontfixCAucService;
	
	@Resource
	private EgovIdGnrService egovBidIdGnrService;
	
	
	
	public void searchSessoin(HttpServletRequest request, String searchName, Map<String, Object> paramMap) throws Exception{
		
	  	String[] searchNameArr = {"fixBuySmallListSearch"};
	          	  
		for(int i=0; i<searchNameArr.length; i++){
			if(searchNameArr[i].equals(searchName)){
				request.getSession().setAttribute(searchNameArr[i], paramMap);
			}else{
				request.getSession().setAttribute(searchNameArr[i], null);
			}
		}
		
	}
	
	
	/*@RequestMapping(value="/front/fixAuc/fixBuySmallList.do")
	public String fixBuySmallList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		
		if(!Boolean.valueOf(String.valueOf(request.getSession().getAttribute("aucType03")))){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 매뉴 권한이 없습니다. 통합로그인 후 사용하세요.");
            throw new ModelAndViewDefiningException(modelAndView);
		}
	
		EgovMap cFloLoginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
				
		if(cFloLoginVO != null){
			return "redirect:/front/fixCAuc/fixBuySmallList.do";
		}
		
		return "redirect:/front/fixCAuc/fixBuySmallList.do";
	}*/
	
	
	@RequestMapping(value="/front/fixCAuc/fixBuySmallList.do")
	public String fixBuySmallList2(HttpServletRequest request, HttpServletResponse response,
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
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			if(paramMap.get("searchStartDt") == null){
				// paramMap.put("searchStartDt",format.format(date));
				
				Calendar cal = Calendar.getInstance();
		        cal.setTime(date);
		        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		        
		        if(dayOfWeek == 1) {
		        	cal.add(Calendar.DATE, 2);
		        }else if(dayOfWeek == 2) {
		        	cal.add(Calendar.DATE, 1);
		        }else if(dayOfWeek == 3) {
		        	cal.add(Calendar.DATE, 2);
		        }else if(dayOfWeek == 4) {
		        	cal.add(Calendar.DATE, 1);
		        }else if(dayOfWeek == 5) {
		        	cal.add(Calendar.DATE, 2);
		        }else if(dayOfWeek == 6) {
		        	cal.add(Calendar.DATE, 1);
		        }else if(dayOfWeek == 7) {
		        	cal.add(Calendar.DATE, 3);
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

			//중도매인 정보
			List<EgovMap> floMemberList = frontLoginService.getFloMember(paramMap);
			
			List<EgovMap> floList = new ArrayList<EgovMap>();
			
			for(EgovMap chulVO : floMemberList){
				String jCode = String.valueOf(chulVO.get("chulCd"));
				EgovMap jInfo = frontfixCAucService.getJCodeToJInfo(jCode);
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
			EgovMap timeVO = frontfixCAucSmallJService.getTime(paramMap);
			
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
		
		
		
		model.addAttribute("title", "구매 ( 입찰 )");
		model.addAttribute("contentPath", "fixAuc/C/fixBuySmallList.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	
	
	
	@RequestMapping(value="/front/fixCAuc/fixBuySmallList.json")
	public String ajaxFixBuySmallList(HttpServletRequest request, HttpServletResponse response,
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
			
			List<EgovMap> fixList = frontfixCAucSmallJService.getFixBuyList(paramMap);
			
			for(EgovMap fixVO : fixList){
				String chulCode = String.valueOf(fixVO.get("chulCode"));
				EgovMap chulInfo = frontfixCAucService.getChulCodeToChulInfo(chulCode);
				if(chulInfo != null){
					fixVO.put("chulName", chulInfo.get("name"));
					fixVO.put("chulArea", chulInfo.get("chulArea"));
				}else{
					fixVO.put("chulName", "정보없음");
					fixVO.put("chulArea", "정보없음");
				}
				
				paramMap.put("fixDealSeq", fixVO.get("fixDealSeq"));
				EgovMap bidInfo = frontfixCAucSmallJService.getBidBuyInfo(paramMap);
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
			
			int totCnt = frontfixCAucSmallJService.getFixBuyListCnt(paramMap);
			
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
	
	
	
	@RequestMapping(value="/front/fixCAuc/fixBuySmallView.do")
	public String fixBuySmallView(HttpServletRequest request, HttpServletResponse response,
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
		
		
		EgovMap fixVO = frontfixCAucSmallJService.getFixBuyArticle(paramMap);
		if(fixVO != null){
			
			String chulCode = String.valueOf(fixVO.get("chulCode"));
			EgovMap chulInfo = frontfixCAucService.getChulCodeToChulInfo(chulCode);
			if(chulInfo != null){
				fixVO.put("chulName", chulInfo.get("name"));
				fixVO.put("chulArea", chulInfo.get("chulArea"));
			}else{
				fixVO.put("chulName", "정보없음");
				fixVO.put("chulArea", "정보없음");
			}
			List<EgovMap> fixFileList = frontfixCAucSmallJService.getFixFileList(paramMap);
			model.addAttribute("fixFileList", fixFileList);
			
			//중도매인정보
			List<EgovMap> floMemberList = frontLoginService.getFloMember(paramMap);
			
			List<EgovMap> floList = new ArrayList<EgovMap>();
			
			for(EgovMap chulVO : floMemberList){
				String jCode = String.valueOf(chulVO.get("chulCd"));
				EgovMap jInfo = frontfixCAucService.getJCodeToJInfo(jCode);
				floList.add(jInfo);
				
				String code = String.valueOf(paramMap.get("jCode"));
				if(jCode.equals(code)){
					paramMap.put("code", code);
					model.addAttribute("code", code);
				}
			}
			
			model.addAttribute("floList", floList);
			
			/*
			EgovMap bidInfo = frontfixCAucService.getBidBuyInfo(paramMap);
			model.addAttribute("bidInfo", bidInfo);
			*/
			//입찰 가능 시간
			paramMap.put("timeType", "3");
			EgovMap timeVO = frontfixCAucSmallJService.getTime(paramMap);
			model.addAttribute("timeVO", timeVO);
			
		}
		
		model.addAttribute("result", fixVO);
		
		
		model.addAttribute("title", "구매 (서면입찰)");
		model.addAttribute("contentPath", "fixAuc/C/fixBuySmallView.jsp");
		
		return "/front/layout/fixLayout";
	}
	
	
	
	
	@RequestMapping(value = "/front/fixCAuc/fixBuySmallBid.json")
	  public String fixBuySmallBid(HttpServletRequest request, HttpServletResponse response,
			  	@RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		System.out.println("###########");
		System.out.println(paramMap);
		System.out.println("###########");
		
		try {
			
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
			
			EgovMap bidCheckVO =  frontfixCAucSmallJService.getBidBuyInfo(paramMap);
			
			System.out.println("### bidCheckVO ###");
			System.out.println(bidCheckVO);
			System.out.println("##################");
			
			if(bidCheckVO != null){
				if("Y".equals(String.valueOf(bidCheckVO.get("bidAdminYn")) ) ){
					
				}else{
					result = 5;
					model.addAttribute("result",result);
					return "jsonView";
				}
			}else{
				//입찰 가능 시간
				EgovMap fixVO = frontfixCAucSmallJService.getFixBuyArticle(paramMap);
				
				if(fixVO != null){
					if(!"7".equals(fixVO.get("fixState")) ){
						result = 4;
						model.addAttribute("result",result);
						return "jsonView";
					}
				}
			}
			
			EgovMap checkVO = frontfixCAucService.getFloMemberCheck(paramMap);
			
			
			if(checkVO != null){
				
				EgovMap limitVO = frontfixCAucService.getLimitAmt(paramMap);
				
				if(limitVO != null){
					
					int limitAmt = Integer.parseInt(String.valueOf(limitVO.get("limitAmt")));
					int bidSokCnt = Integer.parseInt(String.valueOf(paramMap.get("bidSokCnt")));
					int bidUnitPrice = Integer.parseInt(String.valueOf(paramMap.get("bidUnitPrice")));
					int bidedSokCnt = 0;
					int bidedUnitPrice = 0; 
					if(bidCheckVO != null){
						bidedSokCnt = Integer.parseInt(String.valueOf(bidCheckVO.get("bidSokCnt")));
						bidedUnitPrice = Integer.parseInt(String.valueOf(bidCheckVO.get("bidUnitPrice")));
						
					}
					
					if(limitAmt >= (bidSokCnt*bidUnitPrice)-(bidedSokCnt*bidedUnitPrice)){
						
						EgovMap fixVO = frontfixCAucSmallJService.getFixBuyArticle(paramMap);
						
						if(fixVO != null){String dealType = String.valueOf(fixVO.get("dealType"));
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
								frontfixCAucSmallJService.updateBid(paramMap);
								result = 2;
							}else{
								// 응찰 입력
								paramMap.put("bidSeq", egovBidIdGnrService.getNextIntegerId());
								frontfixCAucSmallJService.insertBid(paramMap);
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
	
	
	
	@RequestMapping(value = "/front/fixCAuc/getSmallBidBuyInfo.json")
	  public String getSmallBidBuyInfo(HttpServletRequest request, HttpServletResponse response,
			  	@RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
	    
		try {
			System.out.println(paramMap.toString());
			
			EgovMap loginVO = (EgovMap) request.getSession().getAttribute("cFloLoginVO");
			
			paramMap.put("id", loginVO.get("loginId"));
			paramMap.put("memberId", loginVO.get("memberId"));
			
			EgovMap checkVO = frontfixCAucService.getFloMemberCheck(paramMap);
			if(checkVO != null){
				EgovMap bidCheckVO = frontfixCAucSmallJService.getBidBuyInfo(paramMap);
				model.addAttribute("result", bidCheckVO);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	   
		
		return "jsonView";
	  }
	
	
	
	@RequestMapping(value="/front/fixCAuc/fixSmallListExcell.do")
	public void fixSmallListExcell(
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
		
		String mode = EgovProperties.getProperty("Globals.Mode");
		String savePath = EgovProperties.getProperty("Globals.DstbtPath"+mode);
		
		FileInputStream file = new FileInputStream(savePath + "/excell/서면입찰데이터.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		
		XSSFSheet sheetSangJang = workbook.getSheet("경매기록원표");
		List<Map<String,Object>> sangJangList = frontfixCAucSmallJService.getFixSmallSangJangList(paramMap);
	    String[] headerSangJangArray = {"UP_NO","CHUL_CODE","CHUL_NAME","MOK_NAME","PUM_NAME","CHUL_LEVEL","ACC_LEVEL","BOX_TYPE","TRANS","BOX_CNT","SOK_CNT","STD_PRICE"};

	    for(int i=0; i<sangJangList.size(); i++) {
	        Map<String, Object> sangJangVO = sangJangList.get(i);
	        
	        for(int j=0; j<headerSangJangArray.length; j++){
	        	Object objCellValue = sangJangVO.get(headerSangJangArray[j]);
	        	if(ObjectUtils.isEmpty(objCellValue)) {
	        		sheetSangJang.getRow(i+3).getCell(j).setCellValue("");
	        	} else if(objCellValue instanceof Integer || objCellValue instanceof Double || objCellValue instanceof BigDecimal) {
	        		sheetSangJang.getRow(i+3).getCell(j).setCellValue(Integer.parseInt(String.valueOf(objCellValue)));
	        	} else {
	        		sheetSangJang.getRow(i+3).getCell(j).setCellValue(String.valueOf(objCellValue));
	        	}
	        }
	
	    }
	    
	    XSSFSheet sheetBid = workbook.getSheet("서면응찰데이터");
	    List<Map<String,Object>> bidList = frontfixCAucSmallJService.getFixSmallBidList(paramMap);
	    
	    String[] headerBidArray = {"UP_NO","UNIT_PRICE","J_CODE","BIGO"};
	    
	    for(int i=0; i<bidList.size(); i++) {
	        Map<String, Object> bidVO = bidList.get(i);
	        
	        for(int j=0; j<headerBidArray.length; j++){
	        	Object objCellValue = bidVO.get(headerBidArray[j]);
	        	
	        	if(ObjectUtils.isEmpty(objCellValue)) {
	        		sheetBid.getRow(i+1).getCell(j).setCellValue("");
	        	} else if(objCellValue instanceof Integer || objCellValue instanceof Double || objCellValue instanceof BigDecimal) {
	        		sheetBid.getRow(i+1).getCell(j).setCellValue(Integer.parseInt(String.valueOf(objCellValue)));
	        	} else {
	        		sheetBid.getRow(i+1).getCell(j).setCellValue(String.valueOf(objCellValue));
	        	}
	        }
	
	    }
	    
	    workbook.setForceFormulaRecalculation(true);
        
        model.addAttribute("locale", Locale.KOREA);
        model.addAttribute("workbook", workbook);
        model.addAttribute("workbookName", "서면입찰데이터("+String.valueOf(paramMap.get("upDay"))+")");
        
        renderMergedOutputModel(model, request, response);
       
	  } catch (Exception e) {
			// TODO: handle exception
		  e.printStackTrace();
		  System.out.println("tempDir:::::::::::"+System.getProperty("java.io.tmpdir"));
		  
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
       XSSFWorkbook workbook = null;
       
       try {
           workbook = (XSSFWorkbook) model.get("workbook");
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
	
}
