package egovframework.front.stat.web;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import egovframework.front.login.service.FrontLoginService;
import egovframework.front.stat.service.StatSecondService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Controller
public class StatSecondController {

	/** EgovPropertyService */
	@Resource(name = "frontLoginService")
	protected FrontLoginService frontLoginService;
	
	@Resource(name = "statSecondService")
	private StatSecondService statSecondService;
	
	
	// 출하단체 - 출하단체 등록정보 aucType02 true
	@RequestMapping(value="/front/stat/marketOrgInfo.do")
	public String marketOrgInfo(HttpServletRequest request
			, HttpServletResponse response
			, @RequestParam Map<String, Object> paramMap
			, ModelMap model, HttpSession session) throws Exception {
		
		
			Boolean aucType = (Boolean)request.getSession().getAttribute("aucType02");
			//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
			if(aucType == null || !aucType  ){
				return "redirect:/front/login.do";
			}
			
			/*DB쿼리 가져오기*/
				
			model.addAttribute("paramMap", paramMap);
			model.addAttribute("contentPath", "stat/marketOrgInfo.jsp");
			
			
		return "/front/layout/loginLayout";
	}
	
	@RequestMapping(value="/front/stat/marketOrgInfo.json")
	public String marketOrgInfoJson(HttpServletRequest request
			, HttpServletResponse response
			, @RequestParam Map<String, Object> paramMap
			, ModelMap model, HttpSession session) throws Exception {
		
		
			Boolean aucType = (Boolean)request.getSession().getAttribute("aucType02");
			//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
			if(aucType == null || !aucType  ){
				return null;
			}
			
			EgovMap atLoginVO = (EgovMap) request.getSession().getAttribute("atLoginVO");
			
			paramMap.put("id", atLoginVO.get("loginId"));
			paramMap.put("memberId", atLoginVO.get("memberId"));
			paramMap.put("code", paramMap.get("gCode"));
			
			EgovMap checkVO = statSecondService.getFloMemberCheck(paramMap);
			if(checkVO == null){
				return null;
			}
			
			/*DB쿼리 가져오기*/
			EgovMap result = statSecondService.marktorgInfoSelect(paramMap);
			
				
			model.addAttribute("result", result);	
			model.addAttribute("paramMap", paramMap);
			
			
		return "jsonView";
	}
	
	// 출하단체 - 소속농가현황
	@RequestMapping(value = "/front/stat/marketOrgBelongShapr.do")
	public String marketOrgBelongShapr(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap, ModelMap model)
			throws Exception {

		Boolean aucType = (Boolean) request.getSession().getAttribute("aucType02");
				
		// 01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체 / 05 하역노조(항운노조) / 07 경매사
		if (aucType == null || !aucType) {
			return "redirect:/front/login.do";
		}

		model.addAttribute("paramMap", paramMap);
		model.addAttribute("contentPath", "stat/marketOrgBelongShapr.jsp");

		return "/front/layout/loginLayout";
	}
	
	@RequestMapping(value="/front/stat/marketOrgBelongShapr.json")
	public String marketOrgBelongShaprJson(HttpServletRequest request
			, HttpServletResponse response
			, @RequestParam Map<String, Object> paramMap
			, ModelMap model, HttpSession session) throws Exception {
		
		
			Boolean aucType = (Boolean)request.getSession().getAttribute("aucType02");
			//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
			if(aucType == null || !aucType  ){
				return null;
			}
			
			EgovMap atLoginVO = (EgovMap) request.getSession().getAttribute("atLoginVO");
			
			paramMap.put("id", atLoginVO.get("loginId"));
			paramMap.put("memberId", atLoginVO.get("memberId"));
			paramMap.put("code", paramMap.get("domeCode"));
			
			EgovMap checkVO = statSecondService.getFloMemberCheck(paramMap);
			if(checkVO == null){
				return null;
			}
			
			List<EgovMap> resultList = statSecondService.marktorgBelongShprSelectList(paramMap);

			EgovMap groupVO = statSecondService.marktorgBelongShprSelectInfoList(paramMap);

			EgovMap sumVO = statSecondService.marktorgBelongShprSelectListCount(paramMap);

			model.addAttribute("resultList", resultList);
			model.addAttribute("groupVO", groupVO);	
			model.addAttribute("sumVO", sumVO);	
			model.addAttribute("paramMap", paramMap);
			
			
		return "jsonView";
	}
	
	// 출하단체 - 판매정산내역(집계)
	@RequestMapping(value="/front/stat/marketSaleCalcTot.do")
	public String marketSaleCalcTot(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		Boolean aucType = (Boolean)request.getSession().getAttribute("aucType02");
		//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
		if(aucType == null || !aucType  ){
			return "redirect:/front/login.do";
		}
		
		
		/*DB쿼리 가져오기*/
		/*
		List<EgovMap> resultList = statSecondService.markSaleCalcTotSelectList(paramMap);
		EgovMap sumVO = statSecondService.markSaleCalcTotSumList(paramMap);
		model.addAttribute("resultList",resultList);
		model.addAttribute("sumVO",sumVO);
		*/
		model.addAttribute("contentPath", "stat/marketSaleCalcTot.jsp");
		
		return "/front/layout/loginLayout";
	}
	
	@RequestMapping(value="/front/stat/marketSaleCalcTot.json")
	public String marketSaleCalcTotJson(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		Boolean aucType = (Boolean)request.getSession().getAttribute("aucType02");
		//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
		if(aucType == null || !aucType  ){
			return null;
		}
		
		EgovMap atLoginVO = (EgovMap) request.getSession().getAttribute("atLoginVO");
		
		paramMap.put("id", atLoginVO.get("loginId"));
		paramMap.put("memberId", atLoginVO.get("memberId"));
		paramMap.put("code", paramMap.get("domeCode"));
		
		EgovMap checkVO = statSecondService.getFloMemberCheck(paramMap);
		if(checkVO == null){
			return null;
		}
		/*DB쿼리 가져오기*/
		
		EgovMap groupVO = statSecondService.markSaleCalcTotSelectGroupInfo(paramMap);
		List<EgovMap> resultList = statSecondService.markSaleCalcTotSelectList(paramMap);
		EgovMap sumVO = statSecondService.markSaleCalcTotSumList(paramMap);
		
		model.addAttribute("resultList",resultList);
		model.addAttribute("sumVO",sumVO);
		model.addAttribute("groupVO",groupVO);
		
		return "jsonView";
	}
	
	
	@RequestMapping(value="/front/stat/marketSaleCalcTotExcell.do")
	public void marketSaleCalcTotExcell(
			  @RequestParam Map<String, Object> paramMap
			, HttpServletResponse response
			, HttpServletRequest request
			, ModelMap model) throws Exception{
	 
		Boolean aucType = (Boolean)request.getSession().getAttribute("aucType02");
		//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
		if(aucType == null || !aucType  ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	        throw new ModelAndViewDefiningException(modelAndView);
		}
		
		EgovMap atLoginVO = (EgovMap) request.getSession().getAttribute("atLoginVO");
		
		paramMap.put("id", atLoginVO.get("loginId"));
		paramMap.put("memberId", atLoginVO.get("memberId"));
		paramMap.put("code", paramMap.get("domeCode"));
		
		EgovMap checkVO = statSecondService.getFloMemberCheck(paramMap);
		if(checkVO == null){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
			throw new ModelAndViewDefiningException(modelAndView);
		}
		
		EgovMap groupVO = statSecondService.markSaleCalcTotSelectGroupInfo(paramMap);
		List<EgovMap> list = statSecondService.markSaleCalcTotSelectList(paramMap);
		EgovMap sumVO = statSecondService.markSaleCalcTotSumList(paramMap);
		
		try {
			
		
		 SXSSFWorkbook workbook = new SXSSFWorkbook();
		 int tableInitRow = 10;
	 	// 상장 데이터 시트 생성
	    SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("판매정산내역집계");
	    
	    //시트 열 너비 설정
	    for(int i=0; i<5; i++){
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
	    titleCell.setCellValue("판매정산내역[집계]");
	    titleCell.setCellStyle(titleCellStyle);
	    
	    sheet.addMergedRegion(new CellRangeAddress(1, 3, 0, 4));
	    
	    Row groupRow = sheet.createRow(5);
	    Cell groupCell = groupRow.createCell(0);
	    groupCell.setCellValue("출하단체");
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupCell = groupRow.createCell(1);
	    groupCell.setCellValue(String.valueOf(groupVO.get("gName")));
	    sheet.addMergedRegion(new CellRangeAddress(5, 5, 1, 4));
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(2);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(3);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(4);
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupRow = sheet.createRow(6);
	    groupCell = groupRow.createCell(0);
	    groupCell.setCellValue("화훼부류");
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupCell = groupRow.createCell(1);
	    String bunName = "";
	    String bunChk = String.valueOf(paramMap.get("bunChk")); 
	    if("N".equals(bunChk)){
	    	bunName = "절화";
	    }else if("Y".equals(bunChk)){
	    	bunName = "난";
	    }else if("C".equals(bunChk)){
	    	bunName = "관엽";
	    }
	    groupCell.setCellValue(bunName);
	    sheet.addMergedRegion(new CellRangeAddress(6, 6, 1, 4));
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(2);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(3);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(4);
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupRow = sheet.createRow(7);
	    groupCell = groupRow.createCell(0);
	    groupCell.setCellValue("경매기간");
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupCell = groupRow.createCell(1);
	    String startDate = String.valueOf(paramMap.get("startDate"));
	    String endDate = String.valueOf(paramMap.get("endDate")); 
	    groupCell.setCellValue(startDate + "~" + endDate);
	    sheet.addMergedRegion(new CellRangeAddress(7, 7, 1, 4));
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(2);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(3);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(4);
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupRow = sheet.createRow(8);
	    groupCell = groupRow.createCell(0);
	    groupCell.setCellValue("조회일자");
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupCell = groupRow.createCell(1);
	    Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat( "yyyy-MM-dd");
	    groupCell.setCellValue(dateformat.format(date));
	    sheet.addMergedRegion(new CellRangeAddress(8, 8, 1, 4));
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(2);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(3);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(4);
	    groupCell.setCellStyle(headerCellStyle);
	    
	    
	    String[] headerArray = {"경매일자","판매금액","공제금액","지급금액","비고"};
	    String[] dataArray = {"fpanday","panmoney","pandeduction","pandifference","bigo"};
	    
	    // 헤더 행 생
	    Row headerRow = sheet.createRow(tableInitRow);
	   
	    Cell headerCell = null;
	    for(int i = 0; i<headerArray.length; i++){
	    	headerCell = headerRow.createCell(i);
	        headerCell.setCellValue(headerArray[i]);
	        headerCell.setCellStyle(headerCellStyle);
	    	
	    }
	    
	    Row bodyRow = null;
	    Cell bodyCell = null;
	    for(int i=0; i<list.size(); i++) {
	    	EgovMap resultVO = list.get(i);
	        
	        // 행 생성
	        bodyRow = sheet.createRow(tableInitRow+(i+1));
	        for(int j = 0; j<dataArray.length; j++){
	        	if(resultVO.get(dataArray[j]) == null){
	        		resultVO.put(dataArray[j],"");
	            }
	            bodyCell = bodyRow.createCell(j);
	            
	            if(j>0 && j<4){
	            	bodyCell.setCellValue(Integer.parseInt(String.valueOf(resultVO.get(dataArray[j]))));
	            	bodyCell.setCellStyle(bodyNumCellStyle);
	            }else{
	            	bodyCell.setCellValue(String.valueOf(resultVO.get(dataArray[j])));
	            	bodyCell.setCellStyle(bodyCellStyle);
	            }
	            
	        }
	
	    }
	    if(list.size()>0){
	    	int sumIndex = tableInitRow+(list.size()+1);
		    bodyRow = sheet.createRow(sumIndex);
		    bodyCell = bodyRow.createCell(0);
	        bodyCell.setCellValue("합계");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(1);
	        bodyCell.setCellValue(Integer.parseInt(String.valueOf(sumVO.get("sumpanmoney"))) );
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(2);
	        bodyCell.setCellValue(Integer.parseInt(String.valueOf(sumVO.get("sumpandeduction"))));
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(3);
	        bodyCell.setCellValue(Integer.parseInt(String.valueOf(sumVO.get("sumpandifference"))));
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(4);
	        bodyCell.setCellValue("");
	        bodyCell.setCellStyle(bodyCellStyle);
	    }
	    
        
        model.addAttribute("locale", Locale.KOREA);
        model.addAttribute("workbook", workbook);
        model.addAttribute("workbookName", "판매정산내역[집계]"+String.valueOf(paramMap.get("startDate"))+"_"+String.valueOf(paramMap.get("endDate")));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 	
        renderMergedOutputModel(model, request, response);
       // return "excelDownloadView";
	 
	}
	
	
	// 출하단체 - 판매정산내역(상세)
	@RequestMapping(value="/front/stat/marketSaleCalcDetail.do")
	public String marketSaleCalcDetail(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		Boolean aucType = (Boolean)request.getSession().getAttribute("aucType02");
		//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
		if(aucType == null || !aucType  ){
			return "redirect:/front/login.do";
		}
		
		model.addAttribute("paramMap",paramMap);
		
		model.addAttribute("contentPath", "stat/marketSaleCalcDetail.jsp");
		
		return "/front/layout/loginLayout";
	}
	
	@RequestMapping(value="/front/stat/marketSaleCalcDetail.json")
	public String marketSaleCalcDetailJson(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		Boolean aucType = (Boolean)request.getSession().getAttribute("aucType02");
		//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
		if(aucType == null || !aucType  ){
			return null;
		}
		
		EgovMap atLoginVO = (EgovMap) request.getSession().getAttribute("atLoginVO");
		
		paramMap.put("id", atLoginVO.get("loginId"));
		paramMap.put("memberId", atLoginVO.get("memberId"));
		paramMap.put("code", paramMap.get("domeCode"));
		
		EgovMap checkVO = statSecondService.getFloMemberCheck(paramMap);
		if(checkVO == null){
			return null;
		}
		
		/*DB쿼리 가져오기*/
		List<EgovMap> resultList = statSecondService.markSaleCalcTotSelect(paramMap);
		model.addAttribute("resultList",resultList);
		EgovMap groupVO = statSecondService.markSaleCalcTotSelectInfoList(paramMap);
		model.addAttribute("groupVO",groupVO);
		
		
		
		
		return "jsonView";
	}
	
	@RequestMapping(value="/front/stat/marketSaleCalcDetailExcell.do")
	public void marketSaleCalcDetailExcell(
			  @RequestParam Map<String, Object> paramMap
			, HttpServletResponse response
			, HttpServletRequest request
			, ModelMap model) throws Exception{
	 
		Boolean aucType = (Boolean)request.getSession().getAttribute("aucType02");
		//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
		if(aucType == null || !aucType  ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	        throw new ModelAndViewDefiningException(modelAndView);
		}
		
		EgovMap atLoginVO = (EgovMap) request.getSession().getAttribute("atLoginVO");
		
		paramMap.put("id", atLoginVO.get("loginId"));
		paramMap.put("memberId", atLoginVO.get("memberId"));
		paramMap.put("code", paramMap.get("domeCode"));
		
		EgovMap checkVO = statSecondService.getFloMemberCheck(paramMap);
		if(checkVO == null){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
			throw new ModelAndViewDefiningException(modelAndView);
		}
		
		EgovMap groupVO = statSecondService.markSaleCalcTotSelectInfoList(paramMap);
		List<EgovMap> list = statSecondService.markSaleCalcTotSelect(paramMap);
		
		try {
			
		
		 SXSSFWorkbook workbook = new SXSSFWorkbook();
		 int tableInitRow = 10;
	 	// 상장 데이터 시트 생성
	    SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("판매정산내역상세");
	    
	    String[] headerArray = {"출하자","출하일자","품목","품종","등급","상자","물량","단가","경매대금","결과","수수료","하역비","재포장비","운송비","습식상자대여료","선도금상환액","백합자조금","미수금","자조금","회비","공제액계","지급금액"};
	    String[] dataArray = {"chulname","panday","pmokname","pjongname","levelname","boxcnt","sokcnt","kmpnew","panprice","kmlist","susup","downrep","reboxp","transprice","rentalamt","sanghwan","baekamt","misunuge","selfamt","feeamt","gongjeamt","chainamt"};
	    
	    //시트 열 너비 설정
	    for(int i=0; i<headerArray.length; i++){
	    	if(i == 0 || i == 3){
	    		sheet.setColumnWidth(i, 5000);
	    	}else{
	    		sheet.setColumnWidth(i, 4000);
	    	}
	    	
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
	    titleCell.setCellValue("판매정산내역[상세]");
	    titleCell.setCellStyle(titleCellStyle);
	    
	    sheet.addMergedRegion(new CellRangeAddress(1, 3, 0, 4));
	    
	    Row groupRow = sheet.createRow(5);
	    Cell groupCell = groupRow.createCell(0);
	    groupCell.setCellValue("출하농가");
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupCell = groupRow.createCell(1);
	    groupCell.setCellValue(String.valueOf(groupVO.get("gname")));
	    sheet.addMergedRegion(new CellRangeAddress(5, 5, 1, 4));
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(2);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(3);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(4);
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupRow = sheet.createRow(6);
	    groupCell = groupRow.createCell(0);
	    groupCell.setCellValue("화훼부류");
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupCell = groupRow.createCell(1);
	    
	    String bunChk = String.valueOf(groupVO.get("bunchk")); 
	    
	    groupCell.setCellValue(bunChk);
	    sheet.addMergedRegion(new CellRangeAddress(6, 6, 1, 4));
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(2);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(3);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(4);
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupRow = sheet.createRow(7);
	    groupCell = groupRow.createCell(0);
	    groupCell.setCellValue("경매기간");
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupCell = groupRow.createCell(1);
	    String panDate = String.valueOf(paramMap.get("panDate")); 
	    groupCell.setCellValue(panDate);
	    sheet.addMergedRegion(new CellRangeAddress(7, 7, 1, 4));
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(2);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(3);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(4);
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupRow = sheet.createRow(8);
	    groupCell = groupRow.createCell(0);
	    groupCell.setCellValue("조회일자");
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupCell = groupRow.createCell(1);
	    Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat( "yyyy-MM-dd");
	    groupCell.setCellValue(dateformat.format(date));
	    sheet.addMergedRegion(new CellRangeAddress(8, 8, 1, 4));
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(2);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(3);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(4);
	    groupCell.setCellStyle(headerCellStyle);
	    
	    
	    
	    
	    // 헤더 행 생
	    Row headerRow = sheet.createRow(tableInitRow);
	   
	    Cell headerCell = null;
	    for(int i = 0; i<headerArray.length; i++){
	    	headerCell = headerRow.createCell(i);
	        headerCell.setCellValue(headerArray[i]);
	        headerCell.setCellStyle(headerCellStyle);
	    	
	    }
	    
	    Row bodyRow = null;
	    Cell bodyCell = null;
	    for(int i=0; i<list.size(); i++) {
	    	EgovMap resultVO = list.get(i);
	    	String panDay = String.valueOf(resultVO.get("panday"));
            
	        // 행 생성
	        bodyRow = sheet.createRow(tableInitRow+(i+1));
	        for(int j = 0; j<dataArray.length; j++){
	        	if(resultVO.get(dataArray[j]) == null){
	        		resultVO.put(dataArray[j],"");
	            }
	            bodyCell = bodyRow.createCell(j);
	            
	            String chulCode = String.valueOf(resultVO.get("chulcode"));
	            
	            if(j == 0){
	            	String chulText = "";
	            	if(resultVO.get("chulcode") != null){
	            		chulText = "("+chulCode.substring(0, 4)+"-"+chulCode.substring(4, 8)+")";
	            	}
	            	bodyCell.setCellValue(String.valueOf(resultVO.get(dataArray[j]))+chulText);
	            	bodyCell.setCellStyle(bodyCellStyle);
	            }else if(j>4 && j<9){
	            	if(j==7 &&("null".equals(panDay)||panDay == null)){
	            		bodyCell.setCellValue("");
	            	}else{
	            		bodyCell.setCellValue(Integer.parseInt(String.valueOf(resultVO.get(dataArray[j]))));
			            bodyCell.setCellStyle(bodyNumCellStyle);
	            	}
	            }else if(j>9){
	            	bodyCell.setCellValue(Integer.parseInt(String.valueOf(resultVO.get(dataArray[j]))));
	            	bodyCell.setCellStyle(bodyNumCellStyle);
	            }else if(j==9){
	            	String kmListText = "";
	            	String kmList = String.valueOf(resultVO.get(dataArray[j]));
	            	
	            	if(chulCode != null){
	            		if(kmList != null){
	            			if(!"null".equals(panDay)){
	            				if(kmList.equals("1")){
			            			kmListText = "낙찰";
				            	}else if(kmList.equals("2")){
				            		kmListText = "유찰";
				            	}else if(kmList.equals("3")){
				            		kmListText = "기록";
				            	}else if(kmList.equals("4")){
				            		kmListText = "기타";
				            	}else if(kmList.equals("8")){
				            		kmListText = "폐기대상";
				            	}else if(kmList.equals("9")){
				            		kmListText = "폐기확정";
				            	}
	            			}
		            	}
	            	}
	            	
	            	bodyCell.setCellValue(kmListText);
	            	bodyCell.setCellStyle(bodyCellStyle);
	            }else{
	            	bodyCell.setCellValue(String.valueOf(resultVO.get(dataArray[j])));
	            	bodyCell.setCellStyle(bodyCellStyle);
	            }
	            
	        }
	
	    }
	    
        
        model.addAttribute("locale", Locale.KOREA);
        model.addAttribute("workbook", workbook);
        model.addAttribute("workbookName", "판매정산내역[상세]"+String.valueOf(paramMap.get("panDate")));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 	
        renderMergedOutputModel(model, request, response);
       // return "excelDownloadView";
	 
	}
	
	
	

	// 출하단체 - 소속농가 실적 확인서
	@RequestMapping(value="/front/stat/marketActualResult.do")
	public String marketActualResult(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		Boolean aucType = (Boolean)request.getSession().getAttribute("aucType02");
		//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
		if(aucType == null || !aucType  ){
			return "redirect:/front/login.do";
		}
		
		
		model.addAttribute("contentPath", "stat/marketActualResult.jsp");
		
		return "/front/layout/loginLayout";
	}
	
	@RequestMapping(value="/front/stat/markActualResultChulhajaList.json")
	public String markActualResultChulhajaList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		Boolean aucType = (Boolean)request.getSession().getAttribute("aucType02");
		//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
		if(aucType == null || !aucType  ){
			return null;
		}
		
		EgovMap atLoginVO = (EgovMap) request.getSession().getAttribute("atLoginVO");
		
		paramMap.put("id", atLoginVO.get("loginId"));
		paramMap.put("memberId", atLoginVO.get("memberId"));
		paramMap.put("code", paramMap.get("domeCode"));
		
		EgovMap checkVO = statSecondService.getFloMemberCheck(paramMap);
		if(checkVO == null){
			return null;
		}
		
		List<EgovMap> chulList = statSecondService.markActualResultChulhajaList(paramMap);
		model.addAttribute("chulList",chulList);
		
		return "jsonView";
	}
	
	@RequestMapping(value="/front/stat/marketActualResult.json")
	public String marketActualResultJson(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		Boolean aucType = (Boolean)request.getSession().getAttribute("aucType02");
		//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
		if(aucType == null || !aucType  ){
			return null;
		}
		
		EgovMap atLoginVO = (EgovMap) request.getSession().getAttribute("atLoginVO");
		
		paramMap.put("id", atLoginVO.get("loginId"));
		paramMap.put("memberId", atLoginVO.get("memberId"));
		paramMap.put("code", paramMap.get("domeCode"));
		
		EgovMap checkVO = statSecondService.getFloMemberCheck(paramMap);
		if(checkVO == null){
			return null;
		}
		
		EgovMap chulCheckVO = statSecondService.getDomeCodeToChulCodeCheck(paramMap);
		
		if(chulCheckVO == null){
			return null;
		}
		
		List<EgovMap> resultList = statSecondService.markActualResultSelectList(paramMap);
		EgovMap sumVO = statSecondService.markActualResultSumList(paramMap);
		model.addAttribute("resultList",resultList);
		model.addAttribute("sumVO",sumVO);
		
		return "jsonView";
	}
	
	// 출하단체 - 공동출하장려금
	@RequestMapping(value="/front/stat/markShprBounty.do")
	public String markShprBounty(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		Boolean aucType = (Boolean)request.getSession().getAttribute("aucType02");
		//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
		if(aucType == null || !aucType  ){
			return "redirect:/front/login.do";
		}
		
		model.addAttribute("paramMap",paramMap);
		
		model.addAttribute("contentPath", "stat/markShprBounty.jsp");
		
		return "/front/layout/loginLayout";
	}
	
	@RequestMapping(value="/front/stat/markShprBounty.json")
	public String markShprBountyJson(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		Boolean aucType = (Boolean)request.getSession().getAttribute("aucType02");
		//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
		if(aucType == null || !aucType  ){
			return null;
		}
		
		EgovMap atLoginVO = (EgovMap) request.getSession().getAttribute("atLoginVO");
		
		paramMap.put("id", atLoginVO.get("loginId"));
		paramMap.put("memberId", atLoginVO.get("memberId"));
		paramMap.put("code", paramMap.get("domeCode"));
		
		EgovMap checkVO = statSecondService.getFloMemberCheck(paramMap);
		if(checkVO == null){
			return null;
		}
		
		/*DB쿼리 가져오기*/
		List<EgovMap> termList = statSecondService.markShprBountySelectTermList(paramMap);
		model.addAttribute("termList",termList);
		List<EgovMap> monthList = statSecondService.markShprBountySelectList(paramMap);
		model.addAttribute("monthList",monthList);
		EgovMap groupVO = statSecondService.markShprBountySelectInfoList(paramMap);
		model.addAttribute("groupVO",groupVO);
		
		model.addAttribute("paramMap",paramMap);
		
		return "jsonView";
	}
	
	
	// 출하농가 - 출하농가 등록정보 aucType02 true
	@RequestMapping(value="/front/stat/marketShprInfo.do")
	public String marketShprInfo(HttpServletRequest request
			, HttpServletResponse response
			, @RequestParam Map<String, Object> paramMap
			, ModelMap model, HttpSession session) throws Exception {
		
		
			Boolean aucType = (Boolean)request.getSession().getAttribute("aucType01");
			//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
			if(aucType == null || !aucType  ){
				return "redirect:/front/login.do";
			}
			
			/*DB쿼리 가져오기*/
				
			model.addAttribute("paramMap", paramMap);
			model.addAttribute("contentPath", "stat/marketShprInfo.jsp");
			
			
		return "/front/layout/loginLayout";
	}
	
	@RequestMapping(value="/front/stat/marketShprInfo.json")
	public String marketShprInfoJson(HttpServletRequest request
			, HttpServletResponse response
			, @RequestParam Map<String, Object> paramMap
			, ModelMap model, HttpSession session) throws Exception {
		
		
			Boolean aucType = (Boolean)request.getSession().getAttribute("aucType01");
			//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
			if(aucType == null || !aucType  ){
				return null;
			}
			
			EgovMap atLoginVO = (EgovMap) request.getSession().getAttribute("atLoginVO");
			
			paramMap.put("id", atLoginVO.get("loginId"));
			paramMap.put("memberId", atLoginVO.get("memberId"));
			
			EgovMap checkVO = statSecondService.getFloMemberCheck(paramMap);
			if(checkVO == null){
				return null;
			}
			
			/*DB쿼리 가져오기*/
			EgovMap result = statSecondService.shprInfoSelect(paramMap);
			
				
			model.addAttribute("result", result);	
			model.addAttribute("paramMap", paramMap);
			
			
		return "jsonView";
	}
	


	
	// 출하농가 - 판매정산내역(집계)
	@RequestMapping(value="/front/stat/shprSaleCalcTot.do")
	public String shprSaleCalcTot(HttpServletRequest request
			, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		Boolean aucType = (Boolean)request.getSession().getAttribute("aucType01");
		//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
		if(aucType == null || !aucType  ){
			return "redirect:/front/login.do";
		}
		
		/*DB쿼리 가져오기*/
		
		model.addAttribute("contentPath", "stat/shprSaleCalcTot.jsp");
		
		return "/front/layout/loginLayout";
	}
	
	@RequestMapping(value="/front/stat/shprSaleCalcTot.json")
	public String shprSaleCalcTotJson(HttpServletRequest request
			, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		Boolean aucType = (Boolean)request.getSession().getAttribute("aucType01");
		//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
		if(aucType == null || !aucType  ){
			return null;
		}
		
		EgovMap atLoginVO = (EgovMap) request.getSession().getAttribute("atLoginVO");
		
		paramMap.put("id", atLoginVO.get("loginId"));
		paramMap.put("memberId", atLoginVO.get("memberId"));
		paramMap.put("code", paramMap.get("chulCode"));
		
		EgovMap checkVO = statSecondService.getFloMemberCheck(paramMap);
		if(checkVO == null){
			return null;
		}
		
		/*DB쿼리 가져오기*/
		EgovMap chulVO = statSecondService.getChulInfo(paramMap);
		List<EgovMap> resultList = statSecondService.shprSaleCalcTotSelectList(paramMap);
		EgovMap sumVO = statSecondService.shprSaleCalcTotSumList(paramMap);
		
		model.addAttribute("resultList",resultList);
		model.addAttribute("sumVO",sumVO);
		model.addAttribute("chulVO",chulVO);
		
		
		return "jsonView";
	}
	
	
	@RequestMapping(value="/front/stat/shprSaleCalcTotExcell.do")
	public void shprSaleCalcTotExcell(
			  @RequestParam Map<String, Object> paramMap
			, HttpServletResponse response
			, HttpServletRequest request
			, ModelMap model) throws Exception{
	 
		Boolean aucType = (Boolean)request.getSession().getAttribute("aucType01");
		//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
		if(aucType == null || !aucType  ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	        throw new ModelAndViewDefiningException(modelAndView);
		}
		
		EgovMap atLoginVO = (EgovMap) request.getSession().getAttribute("atLoginVO");
		
		paramMap.put("id", atLoginVO.get("loginId"));
		paramMap.put("memberId", atLoginVO.get("memberId"));
		paramMap.put("code", paramMap.get("chulCode"));
		
		EgovMap checkVO = statSecondService.getFloMemberCheck(paramMap);
		if(checkVO == null){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
			throw new ModelAndViewDefiningException(modelAndView);
		}
		
		EgovMap chulVO = statSecondService.getChulInfo(paramMap);
		List<EgovMap> list = statSecondService.shprSaleCalcTotSelectList(paramMap);
		EgovMap sumVO = statSecondService.shprSaleCalcTotSumList(paramMap);
		
		try {
			
		
		 SXSSFWorkbook workbook = new SXSSFWorkbook();
		 int tableInitRow = 11;
	 	// 상장 데이터 시트 생성
	    SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("판매정산내역집계");
	    
	    String[] headerMergeArray = {"경매일자","총경매량","","판매량","","유찰량","","판매금액","공제금액","지급금액"};
	    String[] headerArray = {"경매일자","상자수","속수","상자수","속수","상자수","속수","판매금액","공제금액","지급금액"};
	    String[] dataArray = {"fupday","totbox","totsok","panbox","pansok","uchalbox","uchalsok","panprice","gongje","chain"};
	    
	    //시트 열 너비 설정
	    for(int i=0; i<headerArray.length; i++){
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
	    titleCell.setCellValue("판매정산내역[집계]");
	    titleCell.setCellStyle(titleCellStyle);
	    
	    sheet.addMergedRegion(new CellRangeAddress(1, 3, 0, 4));
	    
	    Row groupRow = sheet.createRow(5);
	    Cell groupCell = groupRow.createCell(0);
	    groupCell.setCellValue("출하농가");
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupCell = groupRow.createCell(1);
	    groupCell.setCellValue(String.valueOf(chulVO.get("name")));
	    sheet.addMergedRegion(new CellRangeAddress(5, 5, 1, 4));
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(2);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(3);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(4);
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupRow = sheet.createRow(6);
	    groupCell = groupRow.createCell(0);
	    groupCell.setCellValue("화훼부류");
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupCell = groupRow.createCell(1);
	    String bunName = "";
	    String bunChk = String.valueOf(paramMap.get("bunChk")); 
	    if("N".equals(bunChk)){
	    	bunName = "절화";
	    }else if("Y".equals(bunChk)){
	    	bunName = "난";
	    }else if("C".equals(bunChk)){
	    	bunName = "관엽";
	    }
	    groupCell.setCellValue(bunName);
	    sheet.addMergedRegion(new CellRangeAddress(6, 6, 1, 4));
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(2);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(3);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(4);
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupRow = sheet.createRow(7);
	    groupCell = groupRow.createCell(0);
	    groupCell.setCellValue("경매기간");
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupCell = groupRow.createCell(1);
	    String startDate = String.valueOf(paramMap.get("startDate"));
	    String endDate = String.valueOf(paramMap.get("endDate")); 
	    groupCell.setCellValue(startDate + "~" + endDate);
	    sheet.addMergedRegion(new CellRangeAddress(7, 7, 1, 4));
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(2);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(3);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(4);
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupRow = sheet.createRow(8);
	    groupCell = groupRow.createCell(0);
	    groupCell.setCellValue("조회일자");
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupCell = groupRow.createCell(1);
	    Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat( "yyyy-MM-dd");
	    groupCell.setCellValue(dateformat.format(date));
	    sheet.addMergedRegion(new CellRangeAddress(8, 8, 1, 4));
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(2);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(3);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(4);
	    groupCell.setCellStyle(headerCellStyle);
	    
	    // 헤더 행 생

	    Row headerRow = sheet.createRow(tableInitRow-1);
	   
	    Cell headerCell = null;
	    for(int i = 0; i<headerMergeArray.length; i++){
	    	headerCell = headerRow.createCell(i);
	        headerCell.setCellValue(headerMergeArray[i]);
	        headerCell.setCellStyle(headerCellStyle);
	    	
	    }
        
        headerRow = sheet.createRow(tableInitRow);
	    for(int i = 0; i<headerArray.length; i++){
	    	headerCell = headerRow.createCell(i);
	        headerCell.setCellValue(headerArray[i]);
	        headerCell.setCellStyle(headerCellStyle);
	    	
	    }
	    
	    sheet.addMergedRegion(new CellRangeAddress(10, 11, 0, 0));
	    sheet.addMergedRegion(new CellRangeAddress(10, 10, 1, 2));
	    sheet.addMergedRegion(new CellRangeAddress(10, 10, 3, 4));
	    sheet.addMergedRegion(new CellRangeAddress(10, 10, 5, 6));
	    sheet.addMergedRegion(new CellRangeAddress(10, 11, 7, 7));
	    sheet.addMergedRegion(new CellRangeAddress(10, 11, 8, 8));
	    sheet.addMergedRegion(new CellRangeAddress(10, 11, 9, 9));
	    
	    
	    Row bodyRow = null;
	    Cell bodyCell = null;
	    for(int i=0; i<list.size(); i++) {
	    	EgovMap resultVO = list.get(i);
	        
	        // 행 생성
	        bodyRow = sheet.createRow(tableInitRow+(i+1));
	        for(int j = 0; j<dataArray.length; j++){
	        	if(resultVO.get(dataArray[j]) == null){
	        		resultVO.put(dataArray[j],"");
	            }
	            bodyCell = bodyRow.createCell(j);
	            
	            if(j>0){
	            	bodyCell.setCellValue(Integer.parseInt(String.valueOf(resultVO.get(dataArray[j]))));
	            	bodyCell.setCellStyle(bodyNumCellStyle);
	            }else{
	            	bodyCell.setCellValue(String.valueOf(resultVO.get(dataArray[j])));
	            	bodyCell.setCellStyle(bodyCellStyle);
	            }
	            
	        }
	
	    }
	    if(list.size()>0){
	    	int sumIndex = tableInitRow+(list.size()+1);
		    bodyRow = sheet.createRow(sumIndex);
		    bodyCell = bodyRow.createCell(0);
	        bodyCell.setCellValue("합계");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(1);
	        bodyCell.setCellValue(Integer.parseInt(String.valueOf(sumVO.get("sumtotbox"))) );
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(2);
	        bodyCell.setCellValue(Integer.parseInt(String.valueOf(sumVO.get("sumtotsok"))));
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(3);
	        bodyCell.setCellValue(Integer.parseInt(String.valueOf(sumVO.get("sumpanbox"))));
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(4);
	        bodyCell.setCellValue(Integer.parseInt(String.valueOf(sumVO.get("sumpansok"))));
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(5);
	        bodyCell.setCellValue(Integer.parseInt(String.valueOf(sumVO.get("sumuchalbox"))));
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(6);
	        bodyCell.setCellValue(Integer.parseInt(String.valueOf(sumVO.get("sumuchalsok"))));
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(7);
	        bodyCell.setCellValue(Integer.parseInt(String.valueOf(sumVO.get("sumpanprice"))));
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(8);
	        bodyCell.setCellValue(Integer.parseInt(String.valueOf(sumVO.get("sumgongje"))));
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(9);
	        bodyCell.setCellValue(Integer.parseInt(String.valueOf(sumVO.get("sumchain"))));
	        bodyCell.setCellStyle(bodyNumCellStyle);
	    }
	    
        
        model.addAttribute("locale", Locale.KOREA);
        model.addAttribute("workbook", workbook);
        model.addAttribute("workbookName", "판매정산내역[집계]"+String.valueOf(paramMap.get("startDate"))+"_"+String.valueOf(paramMap.get("endDate")));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 	
        renderMergedOutputModel(model, request, response);
       
	}
	
	
	// 출하농가 - 판매정산내역(상세)
	@RequestMapping(value="/front/stat/shprSaleCalcDetail.do")
	public String shprSaleCalcDetail(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		Boolean aucType = (Boolean)request.getSession().getAttribute("aucType01");
		//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
		if(aucType == null || !aucType  ){
			return "redirect:/front/login.do";
		}
		
		
		/*DB쿼리 가져오기*/
		EgovMap lastVO = statSecondService.shprSaleCalcDetailSelectLatestDate(paramMap);
		
		model.addAttribute("paramMap",paramMap);
		model.addAttribute("lastVO",lastVO);
		model.addAttribute("contentPath", "stat/shprSaleCalcDetail.jsp");
		
		return "/front/layout/loginLayout";
	}
	
	@RequestMapping(value="/front/stat/shprSaleCalcDetail.json")
	public String shprSaleCalcDetailJson(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		Boolean aucType = (Boolean)request.getSession().getAttribute("aucType01");
		//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
		if(aucType == null || !aucType  ){
			return null;
		}
		
		EgovMap atLoginVO = (EgovMap) request.getSession().getAttribute("atLoginVO");
		
		paramMap.put("id", atLoginVO.get("loginId"));
		paramMap.put("memberId", atLoginVO.get("memberId"));
		paramMap.put("code", paramMap.get("chulCode"));
		
		EgovMap checkVO = statSecondService.getFloMemberCheck(paramMap);
		if(checkVO == null){
			return null;
		}
		
		/*DB쿼리 가져오기*/
		EgovMap chulVO = statSecondService.getChulInfo(paramMap);
		
		/*DB쿼리 가져오기*/
		List<EgovMap> resultList = statSecondService.shprSaleCalcDetailSelect(paramMap);
		
		model.addAttribute("resultList",resultList);
		model.addAttribute("chulVO",chulVO);
		
		return "jsonView";
	}
	
	
	@RequestMapping(value="/front/stat/shprSaleCalcDetailExcell.do")
	public void shprSaleCalcDetailExcell(
			  @RequestParam Map<String, Object> paramMap
			, HttpServletResponse response
			, HttpServletRequest request
			, ModelMap model) throws Exception{
	 
		Boolean aucType = (Boolean)request.getSession().getAttribute("aucType01");
		//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
		if(aucType == null || !aucType  ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	        throw new ModelAndViewDefiningException(modelAndView);
		}
		
		EgovMap atLoginVO = (EgovMap) request.getSession().getAttribute("atLoginVO");
		
		paramMap.put("id", atLoginVO.get("loginId"));
		paramMap.put("memberId", atLoginVO.get("memberId"));
		paramMap.put("code", paramMap.get("chulCode"));
		
		EgovMap checkVO = statSecondService.getFloMemberCheck(paramMap);
		if(checkVO == null){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
			throw new ModelAndViewDefiningException(modelAndView);
		}
		
		
		EgovMap chulVO = statSecondService.getChulInfo(paramMap);
		List<EgovMap> list = statSecondService.shprSaleCalcDetailSelect(paramMap);
		
		
		try {
			
		
		 SXSSFWorkbook workbook = new SXSSFWorkbook();
		 int tableInitRow = 10;
	 	// 상장 데이터 시트 생성
	    SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("판매정산내역상세");
	    
	    String[] headerArray = {"출하일자","품목","품종","품종코드","상자","속","등급","낙찰단가","판매금액","결과"};
	    String[] dataArray = {"panday","pmokname","pjongname","pumcode","boxcnt","sokcnt","levelname","kmpnew","panprice","kmlist"};
	    
	    //시트 열 너비 설정
	    for(int i=0; i<headerArray.length; i++){
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
	    titleCell.setCellValue("판매정산내역[상세]");
	    titleCell.setCellStyle(titleCellStyle);
	    
	    sheet.addMergedRegion(new CellRangeAddress(1, 3, 0, 4));
	    
	    Row groupRow = sheet.createRow(5);
	    Cell groupCell = groupRow.createCell(0);
	    groupCell.setCellValue("출하농가");
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupCell = groupRow.createCell(1);
	    groupCell.setCellValue(String.valueOf(chulVO.get("name")));
	    sheet.addMergedRegion(new CellRangeAddress(5, 5, 1, 4));
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(2);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(3);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(4);
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupRow = sheet.createRow(6);
	    groupCell = groupRow.createCell(0);
	    groupCell.setCellValue("화훼부류");
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupCell = groupRow.createCell(1);
	    
	    String bunChk = String.valueOf(chulVO.get("bunchknm")); 
	    
	    groupCell.setCellValue(bunChk);
	    sheet.addMergedRegion(new CellRangeAddress(6, 6, 1, 4));
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(2);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(3);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(4);
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupRow = sheet.createRow(7);
	    groupCell = groupRow.createCell(0);
	    groupCell.setCellValue("경매기간");
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupCell = groupRow.createCell(1);
	    String panDate = String.valueOf(paramMap.get("panDate")); 
	    groupCell.setCellValue(panDate);
	    sheet.addMergedRegion(new CellRangeAddress(7, 7, 1, 4));
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(2);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(3);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(4);
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupRow = sheet.createRow(8);
	    groupCell = groupRow.createCell(0);
	    groupCell.setCellValue("조회일자");
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupCell = groupRow.createCell(1);
	    Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat( "yyyy-MM-dd");
	    groupCell.setCellValue(dateformat.format(date));
	    sheet.addMergedRegion(new CellRangeAddress(8, 8, 1, 4));
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(2);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(3);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(4);
	    groupCell.setCellStyle(headerCellStyle);
	    
	    // 헤더 행 생
	    Row headerRow = sheet.createRow(tableInitRow);
	   
	    Cell headerCell = null;
	    for(int i = 0; i<headerArray.length; i++){
	    	headerCell = headerRow.createCell(i);
	        headerCell.setCellValue(headerArray[i]);
	        headerCell.setCellStyle(headerCellStyle);
	    	
	    }
	    
	    Row bodyRow = null;
	    Cell bodyCell = null;
	    
	    int sumboxcnt = 0;
        int sumsokcnt = 0;
        int sumpanprice = 0;
        int sumchain = 0;
        int sumgongje = 0;
        int sumsusup = 0;
        int sumdownrep = 0;
        int sumreboxp = 0;
        int sumtransprice = 0;
        int sumdaegiprice = 0;
        int sumsanghwan = 0;
        int sumbaekamt = 0;
        int sumtmisunuge = 0;
        int sumselfamt = 0;
        int sumfeeamt = 0;
        int sumrentalamt = 0;
	    
	    for(int i=0; i<list.size(); i++) {
	    	EgovMap resultVO = list.get(i);
	        
	    	if(resultVO.get("boxcnt") != null){
	    		sumboxcnt = sumboxcnt + Integer.parseInt(String.valueOf(resultVO.get("boxcnt")));
	    	}
	    	if(resultVO.get("sokcnt") != null){
	    		sumsokcnt = sumsokcnt + Integer.parseInt(String.valueOf(resultVO.get("sokcnt")));
	    	}
	    	if(resultVO.get("panprice") != null){
	    		sumpanprice = sumpanprice + Integer.parseInt(String.valueOf(resultVO.get("panprice")));
	    	}
	    	if(resultVO.get("chain") != null){
	    		sumchain = sumchain + Integer.parseInt(String.valueOf(resultVO.get("chain")));
	    	}
	    	if(resultVO.get("susup") != null){
	    		sumsusup = sumsusup + Integer.parseInt(String.valueOf(resultVO.get("susup")));
	    	}
	    	if(resultVO.get("downrep") != null){
	    		sumdownrep = sumdownrep + Integer.parseInt(String.valueOf(resultVO.get("downrep")));
	    	}
	    	if(resultVO.get("reboxp") != null){
	    		sumreboxp = sumreboxp + Integer.parseInt(String.valueOf(resultVO.get("reboxp")));
	    	}
	    	if(resultVO.get("transprice") != null){
	    		sumtransprice = sumtransprice + Integer.parseInt(String.valueOf(resultVO.get("transprice")));
	    	}
	    	if(resultVO.get("transpriceadd") != null){
	    		sumtransprice = sumtransprice + Integer.parseInt(String.valueOf(resultVO.get("transpriceadd")));
	    	}
	    	if(resultVO.get("daegiprice") != null){
	    		sumdaegiprice = sumdaegiprice + Integer.parseInt(String.valueOf(resultVO.get("daegiprice")));
	    	}
	    	if(resultVO.get("sanghwan") != null){
	    		sumsanghwan = sumsanghwan + Integer.parseInt(String.valueOf(resultVO.get("sanghwan")));
	    	}
	    	if(resultVO.get("baekamt") != null){
	    		sumbaekamt = sumbaekamt + Integer.parseInt(String.valueOf(resultVO.get("baekamt")));
	    	}
	    	if(resultVO.get("misunuge") != null){
	    		sumtmisunuge = sumtmisunuge + Integer.parseInt(String.valueOf(resultVO.get("misunuge")));
	    	}
	    	if(resultVO.get("selfamt") != null){
	    		sumselfamt = sumselfamt + Integer.parseInt(String.valueOf(resultVO.get("selfamt")));
	    	}
	    	if(resultVO.get("feeamt") != null){
	    		sumfeeamt = sumfeeamt + Integer.parseInt(String.valueOf(resultVO.get("feeamt")));
	    	}
	    	if(resultVO.get("rentalamt") != null){
	    		sumrentalamt = sumrentalamt + Integer.parseInt(String.valueOf(resultVO.get("rentalamt")));
	    	}
	    	
	    	sumgongje = sumsusup+sumdownrep+sumreboxp+sumtransprice+sumdaegiprice+sumsanghwan+sumbaekamt+sumtmisunuge+sumselfamt+sumfeeamt+sumrentalamt;
	    	
	        // 행 생성
	        bodyRow = sheet.createRow(tableInitRow+(i+1));
	        for(int j = 0; j<dataArray.length; j++){
	        	if(resultVO.get(dataArray[j]) == null){
	        		resultVO.put(dataArray[j],"");
	            }
	            bodyCell = bodyRow.createCell(j);
	            
	            if(j>3 && j<6){
	            	bodyCell.setCellValue(Integer.parseInt(String.valueOf(resultVO.get(dataArray[j]))));
	            	bodyCell.setCellStyle(bodyNumCellStyle);
	            }else if(j>6 && j<9){
	            	bodyCell.setCellValue(Integer.parseInt(String.valueOf(resultVO.get(dataArray[j]))));
	            	bodyCell.setCellStyle(bodyNumCellStyle);
	            }else{
	            	bodyCell.setCellValue(String.valueOf(resultVO.get(dataArray[j])));
	            	bodyCell.setCellStyle(bodyCellStyle);
	            }
	            
	        }
	
	    }
	    
	    if(list.size()>0){
	    	int sumIndex = tableInitRow+(list.size()+1);
		    bodyRow = sheet.createRow(sumIndex);
		    bodyCell = bodyRow.createCell(0);
	        bodyCell.setCellValue("속계(A)");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(1);
	        bodyCell.setCellValue("");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(2);
	        bodyCell.setCellValue("");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(3);
	        bodyCell.setCellValue("");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(4);
	        bodyCell.setCellValue(sumboxcnt);
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(5);
	        bodyCell.setCellValue(sumsokcnt);
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(6);
	        bodyCell.setCellValue("");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(7);
	        bodyCell.setCellValue("");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(8);
	        bodyCell.setCellValue(sumpanprice);
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(9);
	        bodyCell.setCellValue("");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        
	        bodyRow = sheet.createRow(sumIndex+1);
		    bodyCell = bodyRow.createCell(0);
	        bodyCell.setCellValue("공제금액(B)");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(1);
	        bodyCell.setCellValue("");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(2);
	        bodyCell.setCellValue("");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(3);
	        bodyCell.setCellValue("");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(4);
	        bodyCell.setCellValue("");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(5);
	        bodyCell.setCellValue("");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(6);
	        bodyCell.setCellValue("");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(7);
	        bodyCell.setCellValue("");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(8);
	        bodyCell.setCellValue(sumgongje);
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(9);
	        bodyCell.setCellValue("");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyRow = sheet.createRow(sumIndex+2);
		    bodyCell = bodyRow.createCell(0);
	        bodyCell.setCellValue("지급금액(A-B)");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(1);
	        bodyCell.setCellValue("");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(2);
	        bodyCell.setCellValue("");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(3);
	        bodyCell.setCellValue("");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(4);
	        bodyCell.setCellValue("");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(5);
	        bodyCell.setCellValue("");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(6);
	        bodyCell.setCellValue("");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(7);
	        bodyCell.setCellValue("");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(8);
	        bodyCell.setCellValue(sumchain);
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(9);
	        bodyCell.setCellValue("");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        sheet.addMergedRegion(new CellRangeAddress(sumIndex, sumIndex, 0, 3));
	        sheet.addMergedRegion(new CellRangeAddress(sumIndex+1, sumIndex+1, 0, 3));
	        sheet.addMergedRegion(new CellRangeAddress(sumIndex+2, sumIndex+2, 0, 3));
	        
	        bodyRow = sheet.createRow(sumIndex+4);
		    bodyCell = bodyRow.createCell(0);
	        bodyCell.setCellValue("[공제내역]");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyRow = sheet.createRow(sumIndex+5);
		    bodyCell = bodyRow.createCell(0);
	        bodyCell.setCellValue("상장수수료");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(1);
	        bodyCell.setCellValue(sumsusup);
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(2);
	        bodyCell.setCellValue("습식용기렌탈비");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(3);
	        bodyCell.setCellValue(sumrentalamt);
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(4);
	        bodyCell.setCellValue("자조금");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(5);
	        bodyCell.setCellValue(sumselfamt);
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyRow = sheet.createRow(sumIndex+6);
		    bodyCell = bodyRow.createCell(0);
	        bodyCell.setCellValue("하역비");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(1);
	        bodyCell.setCellValue(sumdownrep);
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(2);
	        bodyCell.setCellValue("선도금상환");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(3);
	        bodyCell.setCellValue(sumsanghwan);
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(4);
	        bodyCell.setCellValue("회비");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(5);
	        bodyCell.setCellValue(sumfeeamt);
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyRow = sheet.createRow(sumIndex+7);
		    bodyCell = bodyRow.createCell(0);
	        bodyCell.setCellValue("재포장비");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(1);
	        bodyCell.setCellValue(sumreboxp);
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(2);
	        bodyCell.setCellValue("백합자조금");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(3);
	        bodyCell.setCellValue(sumbaekamt);
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(4);
	        bodyCell.setCellValue("공제액계");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(5);
	        bodyCell.setCellValue(sumgongje);
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        sheet.addMergedRegion(new CellRangeAddress(sumIndex+7, sumIndex+8, 4, 4));
	        sheet.addMergedRegion(new CellRangeAddress(sumIndex+7, sumIndex+8, 5, 5));
	        
	        bodyRow = sheet.createRow(sumIndex+8);
		    bodyCell = bodyRow.createCell(0);
	        bodyCell.setCellValue("운송비");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(1);
	        bodyCell.setCellValue(sumtransprice);
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(2);
	        bodyCell.setCellValue("미수금상환");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(3);
	        bodyCell.setCellValue(sumtmisunuge);
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(4);
	        bodyCell.setCellValue("");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(5);
	        bodyCell.setCellValue("");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        
	        
	        
	    }
	    
        
        model.addAttribute("locale", Locale.KOREA);
        model.addAttribute("workbook", workbook);
        model.addAttribute("workbookName", "판매정산내역[상세]"+String.valueOf(paramMap.get("panDate")));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 	
        renderMergedOutputModel(model, request, response);
       // return "excelDownloadView";
	 
	}
	
	// 출하농가 - 실적확인서
	@RequestMapping(value="/front/stat/shprActualResult.do")
	public String shprActualResult(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		Boolean aucType = (Boolean)request.getSession().getAttribute("aucType01");
		//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
		if(aucType == null || !aucType  ){
			return "redirect:/front/login.do";
		}
		
		model.addAttribute("contentPath", "stat/shprActualResult.jsp");
		
		return "/front/layout/loginLayout";
	}
	
	@RequestMapping(value="/front/stat/shprActualResult.json")
	public String shprActualResultJson(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		Boolean aucType = (Boolean)request.getSession().getAttribute("aucType01");
		//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
		if(aucType == null || !aucType  ){
			return null;
		}
		
		/*DB쿼리 가져오기*/
		EgovMap atLoginVO = (EgovMap) request.getSession().getAttribute("atLoginVO");
		
		paramMap.put("id", atLoginVO.get("loginId"));
		paramMap.put("memberId", atLoginVO.get("memberId"));
		paramMap.put("code", paramMap.get("chulCode"));
		
		EgovMap checkVO = statSecondService.getFloMemberCheck(paramMap);
		if(checkVO == null){
			return null;
		}
		
		
		/*DB쿼리 가져오기*/
		List<EgovMap> resultList = statSecondService.shprActualResultSelectList(paramMap);
		EgovMap sumVO = statSecondService.shprActualResultSumList(paramMap);
		
		model.addAttribute("resultList",resultList);
		model.addAttribute("sumVO",sumVO);
		
		
		return "jsonView";
	}
	
	// 운송업체 - 일자 별 운송내역
	@RequestMapping(value = "/front/stat/transDateParticulars.do")
	public String transDateParticulars(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap, ModelMap model)
			throws Exception {

		Boolean aucType = (Boolean) request.getSession().getAttribute("aucType04");
		// 01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체 / 05 하역노조(항운노조) / 07 경매사
		if (aucType == null || !aucType) {
			return "redirect:/front/login.do";
		}

		/* DB쿼리 가져오기 */

		model.addAttribute("contentPath", "stat/transDateParticulars.jsp");

		return "/front/layout/loginLayout";
	}
	
	@RequestMapping(value="/front/stat/transDateParticulars.json")
	public String transDateParticularsJson(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		Boolean aucType = (Boolean)request.getSession().getAttribute("aucType04");
		//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
		if(aucType == null || !aucType  ){
			return null;
		}
		
		EgovMap atLoginVO = (EgovMap) request.getSession().getAttribute("atLoginVO");
		
		paramMap.put("id", atLoginVO.get("loginId"));
		paramMap.put("memberId", atLoginVO.get("memberId"));
		paramMap.put("code", paramMap.get("transCode"));
		
		EgovMap checkVO = statSecondService.getFloMemberCheck(paramMap);
		if(checkVO == null){
			return null;
		}
		/*DB쿼리 가져오기*/
		
		List<EgovMap> resultList = statSecondService.transDateParticularsSelectList(paramMap);
		EgovMap sumVO = statSecondService.transDateParticularsSumList(paramMap);
		
		model.addAttribute("resultList",resultList);
		model.addAttribute("sumVO",sumVO);
		model.addAttribute("paramMap", paramMap);
		
		return "jsonView";
	}
	
	@RequestMapping(value="/front/stat/transDateParticularsExcell.do")
	public void transDateParticularsExcell(
			  @RequestParam Map<String, Object> paramMap
			, HttpServletResponse response
			, HttpServletRequest request
			, ModelMap model) throws Exception{
	 
		Boolean aucType = (Boolean)request.getSession().getAttribute("aucType04");
		//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
		if(aucType == null || !aucType  ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	        throw new ModelAndViewDefiningException(modelAndView);
		}
		
		EgovMap atLoginVO = (EgovMap) request.getSession().getAttribute("atLoginVO");
		
		paramMap.put("id", atLoginVO.get("loginId"));
		paramMap.put("memberId", atLoginVO.get("memberId"));
		paramMap.put("code", paramMap.get("transCode"));
		
		EgovMap checkVO = statSecondService.getFloMemberCheck(paramMap);
		if(checkVO == null){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
			throw new ModelAndViewDefiningException(modelAndView);
		}
		
		List<EgovMap> list = statSecondService.transDateParticularsSelectList(paramMap);
		EgovMap sumVO = statSecondService.transDateParticularsSumList(paramMap);
		
		try {
			
		String[] headerArray = {"경매일자","출하자수","규격상자","비규격상자","합계","운송비","KG"};
		String[] dataArray = {"fday","chulcount","sboxcnt","uboxcnt","tboxcnt","trpri","pumkg"};
		
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		 int tableInitRow = 10;
	 	// 상장 데이터 시트 생성
	    SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("일자별 운송내역");
	    
	    //시트 열 너비 설정
	    for(int i=0; i<headerArray.length; i++){
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
	    titleCell.setCellValue("일자별 운송내역");
	    titleCell.setCellStyle(titleCellStyle);
	    
	    sheet.addMergedRegion(new CellRangeAddress(1, 3, 0, 4));
	    
	    Row groupRow = sheet.createRow(5);
	    Cell groupCell = groupRow.createCell(0);
	    groupCell.setCellValue("운송업체");
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupCell = groupRow.createCell(1);
	    groupCell.setCellValue(String.valueOf(paramMap.get("transCode")));
	    sheet.addMergedRegion(new CellRangeAddress(5, 5, 1, 4));
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(2);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(3);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(4);
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupRow = sheet.createRow(6);
	    groupCell = groupRow.createCell(0);
	    groupCell.setCellValue("화훼부류");
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupCell = groupRow.createCell(1);
	    String bunName = "";
	    String bunChk = String.valueOf(paramMap.get("bunChk")); 
	    if("N".equals(bunChk)){
	    	bunName = "절화";
	    }else if("Y".equals(bunChk)){
	    	bunName = "난";
	    }else if("C".equals(bunChk)){
	    	bunName = "관엽";
	    }
	    groupCell.setCellValue(bunName);
	    sheet.addMergedRegion(new CellRangeAddress(6, 6, 1, 4));
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(2);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(3);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(4);
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupRow = sheet.createRow(7);
	    groupCell = groupRow.createCell(0);
	    groupCell.setCellValue("경매기간");
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupCell = groupRow.createCell(1);
	    String startDate = String.valueOf(paramMap.get("startDate"));
	    String endDate = String.valueOf(paramMap.get("endDate")); 
	    groupCell.setCellValue(startDate + "~" + endDate);
	    sheet.addMergedRegion(new CellRangeAddress(7, 7, 1, 4));
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(2);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(3);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(4);
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupRow = sheet.createRow(8);
	    groupCell = groupRow.createCell(0);
	    groupCell.setCellValue("조회일자");
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupCell = groupRow.createCell(1);
	    Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat( "yyyy-MM-dd");
	    groupCell.setCellValue(dateformat.format(date));
	    sheet.addMergedRegion(new CellRangeAddress(8, 8, 1, 4));
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(2);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(3);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(4);
	    groupCell.setCellStyle(headerCellStyle);
	    
	    
	   
	    
	    // 헤더 행 생
	    Row headerRow = sheet.createRow(tableInitRow);
	   
	    Cell headerCell = null;
	    for(int i = 0; i<headerArray.length; i++){
	    	headerCell = headerRow.createCell(i);
	        headerCell.setCellValue(headerArray[i]);
	        headerCell.setCellStyle(headerCellStyle);
	    	
	    }
	    
	    Row bodyRow = null;
	    Cell bodyCell = null;
	    for(int i=0; i<list.size(); i++) {
	    	EgovMap resultVO = list.get(i);
	        
	        // 행 생성
	        bodyRow = sheet.createRow(tableInitRow+(i+1));
	        for(int j = 0; j<dataArray.length; j++){
	        	if(resultVO.get(dataArray[j]) == null){
	        		resultVO.put(dataArray[j],"");
	            }
	            bodyCell = bodyRow.createCell(j);
	            
	            if(j>0){
	            	bodyCell.setCellValue(Integer.parseInt(String.valueOf(resultVO.get(dataArray[j]))));
	            	bodyCell.setCellStyle(bodyNumCellStyle);
	            }else{
	            	bodyCell.setCellValue(String.valueOf(resultVO.get(dataArray[j])));
	            	bodyCell.setCellStyle(bodyCellStyle);
	            }
	            
	        }
	
	    }
	    if(list.size()>0){
	    	int sumIndex = tableInitRow+(list.size()+1);
		    bodyRow = sheet.createRow(sumIndex);
		    bodyCell = bodyRow.createCell(0);
	        bodyCell.setCellValue("합계");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(1);
	        bodyCell.setCellValue(Integer.parseInt(String.valueOf(sumVO.get("chulcountsum"))) );
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(2);
	        bodyCell.setCellValue(Integer.parseInt(String.valueOf(sumVO.get("sboxcntsum"))));
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(3);
	        bodyCell.setCellValue(Integer.parseInt(String.valueOf(sumVO.get("uboxcntsum"))));
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(4);
	        bodyCell.setCellValue(Integer.parseInt(String.valueOf(sumVO.get("tboxcntsum"))));
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(5);
	        bodyCell.setCellValue(Integer.parseInt(String.valueOf(sumVO.get("trprisum"))));
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(6);
	        bodyCell.setCellValue(Integer.parseInt(String.valueOf(sumVO.get("pumkgsum"))));
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        
	    }
	    
        
        model.addAttribute("locale", Locale.KOREA);
        model.addAttribute("workbook", workbook);
        model.addAttribute("workbookName", "일자별운송내역"+String.valueOf(paramMap.get("startDate"))+"_"+String.valueOf(paramMap.get("endDate")));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 	
        renderMergedOutputModel(model, request, response);
       // return "excelDownloadView";
	 
	}
	

	// 운송업체 - 출하자 별 운송집계
	@RequestMapping(value = "/front/stat/transShprPartiTot.do")
	public String transShprPartiTot(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap, ModelMap model)
			throws Exception {

		Boolean aucType = (Boolean) request.getSession().getAttribute("aucType04");
		// 01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체 / 05 하역노조(항운노조) / 07 경매사
		if (aucType == null || !aucType) {
			return "redirect:/front/login.do";
		}

		/* DB쿼리 가져오기 */

		model.addAttribute("contentPath", "stat/transShprPartiTot.jsp");

		return "/front/layout/loginLayout";
	}
	
	@RequestMapping(value="/front/stat/transShprPartiTotSelectMaxPanDate.json")
	public String transShprPartiTotSelectMaxPanDateJson(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		Boolean aucType = (Boolean)request.getSession().getAttribute("aucType04");
		//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
		if(aucType == null || !aucType  ){
			return null;
		}
		
		EgovMap atLoginVO = (EgovMap) request.getSession().getAttribute("atLoginVO");
		
		paramMap.put("id", atLoginVO.get("loginId"));
		paramMap.put("memberId", atLoginVO.get("memberId"));
		paramMap.put("code", paramMap.get("transCode"));
		
		EgovMap checkVO = statSecondService.getFloMemberCheck(paramMap);
		if(checkVO == null){
			return null;
		}
		/*DB쿼리 가져오기*/
		
		EgovMap maxVO = statSecondService.transShprPartiTotSelectMaxPanDate(paramMap);
		
		model.addAttribute("maxVO",maxVO);
		
		return "jsonView";
	}
	
	
	
	@RequestMapping(value="/front/stat/transShprPartiTot.json")
	public String transShprPartiTotJson(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		Boolean aucType = (Boolean)request.getSession().getAttribute("aucType04");
		//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
		if(aucType == null || !aucType  ){
			return null;
		}
		
		EgovMap atLoginVO = (EgovMap) request.getSession().getAttribute("atLoginVO");
		
		paramMap.put("id", atLoginVO.get("loginId"));
		paramMap.put("memberId", atLoginVO.get("memberId"));
		paramMap.put("code", paramMap.get("transCode"));
		
		EgovMap checkVO = statSecondService.getFloMemberCheck(paramMap);
		if(checkVO == null){
			return null;
		}
		/*DB쿼리 가져오기*/
		
		List<EgovMap> resultList = statSecondService.transShprPartiTotSelectList(paramMap);
		EgovMap sumVO = statSecondService.transShprPartiTotSumList(paramMap);
		
		model.addAttribute("resultList",resultList);
		model.addAttribute("sumVO",sumVO);
		model.addAttribute("paramMap", paramMap);
		
		return "jsonView";
	}
	
	@RequestMapping(value="/front/stat/transShprPartiTotExcell.do")
	public void transShprPartiTotExcell(
			  @RequestParam Map<String, Object> paramMap
			, HttpServletResponse response
			, HttpServletRequest request
			, ModelMap model) throws Exception{
	 
		Boolean aucType = (Boolean)request.getSession().getAttribute("aucType04");
		//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
		if(aucType == null || !aucType  ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	        throw new ModelAndViewDefiningException(modelAndView);
		}
		
		EgovMap atLoginVO = (EgovMap) request.getSession().getAttribute("atLoginVO");
		
		paramMap.put("id", atLoginVO.get("loginId"));
		paramMap.put("memberId", atLoginVO.get("memberId"));
		paramMap.put("code", paramMap.get("transCode"));
		
		EgovMap checkVO = statSecondService.getFloMemberCheck(paramMap);
		if(checkVO == null){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
			throw new ModelAndViewDefiningException(modelAndView);
		}
		
		List<EgovMap> list = statSecondService.transShprPartiTotSelectList(paramMap);
		EgovMap sumVO = statSecondService.transShprPartiTotSumList(paramMap);
		
		try {
			
		String[] headerArray = {"경매일자","코드","성명","규격","비규격","합계","운송비","KG"};
		String[] dataArray = {"panday","chulcode","chulname","sboxcnt","uboxcnt","tboxcnt","trpri","pumkg"};
		
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		 int tableInitRow = 10;
	 	// 상장 데이터 시트 생성
	    SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("출하자별 운송내역집계");
	    
	    //시트 열 너비 설정
	    for(int i=0; i<headerArray.length; i++){
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
	    titleCell.setCellValue("출하자별 운송내역집계");
	    titleCell.setCellStyle(titleCellStyle);
	    
	    sheet.addMergedRegion(new CellRangeAddress(1, 3, 0, 4));
	    
	    Row groupRow = sheet.createRow(5);
	    Cell groupCell = groupRow.createCell(0);
	    groupCell.setCellValue("운송업체");
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupCell = groupRow.createCell(1);
	    groupCell.setCellValue(String.valueOf(paramMap.get("transCode")));
	    sheet.addMergedRegion(new CellRangeAddress(5, 5, 1, 4));
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(2);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(3);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(4);
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupRow = sheet.createRow(6);
	    groupCell = groupRow.createCell(0);
	    groupCell.setCellValue("화훼부류");
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupCell = groupRow.createCell(1);
	    String bunName = "";
	    String bunChk = String.valueOf(paramMap.get("bunChk")); 
	    if("N".equals(bunChk)){
	    	bunName = "절화";
	    }else if("Y".equals(bunChk)){
	    	bunName = "난";
	    }else if("C".equals(bunChk)){
	    	bunName = "관엽";
	    }
	    groupCell.setCellValue(bunName);
	    sheet.addMergedRegion(new CellRangeAddress(6, 6, 1, 4));
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(2);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(3);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(4);
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupRow = sheet.createRow(7);
	    groupCell = groupRow.createCell(0);
	    groupCell.setCellValue("경매기간");
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupCell = groupRow.createCell(1);
	    String panDate = String.valueOf(paramMap.get("panDate"));
	    groupCell.setCellValue(panDate);
	    sheet.addMergedRegion(new CellRangeAddress(7, 7, 1, 4));
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(2);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(3);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(4);
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupRow = sheet.createRow(8);
	    groupCell = groupRow.createCell(0);
	    groupCell.setCellValue("조회일자");
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupCell = groupRow.createCell(1);
	    Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat( "yyyy-MM-dd");
	    groupCell.setCellValue(dateformat.format(date));
	    sheet.addMergedRegion(new CellRangeAddress(8, 8, 1, 4));
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(2);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(3);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(4);
	    groupCell.setCellStyle(headerCellStyle);
	    
	    
	   
	    
	    // 헤더 행 생
	    Row headerRow = sheet.createRow(tableInitRow);
	   
	    Cell headerCell = null;
	    for(int i = 0; i<headerArray.length; i++){
	    	headerCell = headerRow.createCell(i);
	        headerCell.setCellValue(headerArray[i]);
	        headerCell.setCellStyle(headerCellStyle);
	    	
	    }
	    
	    Row bodyRow = null;
	    Cell bodyCell = null;
	    for(int i=0; i<list.size(); i++) {
	    	EgovMap resultVO = list.get(i);
	        
	        // 행 생성
	        bodyRow = sheet.createRow(tableInitRow+(i+1));
	        for(int j = 0; j<dataArray.length; j++){
	        	if(resultVO.get(dataArray[j]) == null){
	        		resultVO.put(dataArray[j],"");
	            }
	            bodyCell = bodyRow.createCell(j);
	            
	            if(j>2){
	            	bodyCell.setCellValue(Integer.parseInt(String.valueOf(resultVO.get(dataArray[j]))));
	            	bodyCell.setCellStyle(bodyNumCellStyle);
	            }else{
	            	bodyCell.setCellValue(String.valueOf(resultVO.get(dataArray[j])));
	            	bodyCell.setCellStyle(bodyCellStyle);
	            }
	            
	        }
	
	    }
	    if(list.size()>0){
	    	int sumIndex = tableInitRow+(list.size()+1);
		    bodyRow = sheet.createRow(sumIndex);
		    bodyCell = bodyRow.createCell(0);
	        bodyCell.setCellValue("합계");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(1);
	        bodyCell.setCellValue(String.valueOf(sumVO.get("chulcountsum")+"명"));
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(2);
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(3);
	        bodyCell.setCellValue(Integer.parseInt(String.valueOf(sumVO.get("sboxcntsum"))));
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(4);
	        bodyCell.setCellValue(Integer.parseInt(String.valueOf(sumVO.get("uboxcntsum"))));
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(5);
	        bodyCell.setCellValue(Integer.parseInt(String.valueOf(sumVO.get("tboxcntsum"))));
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(6);
	        bodyCell.setCellValue(Integer.parseInt(String.valueOf(sumVO.get("trprisum"))));
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(7);
	        bodyCell.setCellValue(Integer.parseInt(String.valueOf(sumVO.get("pumkgsum"))));
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        
	    }
	    
        
        model.addAttribute("locale", Locale.KOREA);
        model.addAttribute("workbook", workbook);
        model.addAttribute("workbookName", "출하자별운송내역[집계]"+String.valueOf(paramMap.get("panDate")));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 	
        renderMergedOutputModel(model, request, response);
       // return "excelDownloadView";
	 
	}
	
	
	
	

	// 운송업체 - 출하자 별 운송내역 상세
	@RequestMapping(value = "/front/stat/transShprPartiDetail.do")
	public String transShprPartiDetail(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap, ModelMap model)
			throws Exception {

		Boolean aucType = (Boolean) request.getSession().getAttribute("aucType04");
		// 01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체 / 05 하역노조(항운노조) / 07 경매사
		if (aucType == null || !aucType) {
			return "redirect:/front/login.do";
		}

		/* DB쿼리 가져오기 */
		model.addAttribute("paramMap", paramMap);
		model.addAttribute("contentPath", "stat/transShprPartiDetail.jsp");

		return "/front/layout/loginLayout";
	}
	
	
	@RequestMapping(value="/front/stat/transShprPartiDetail.json")
	public String transShprPartiDetailJson(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		Boolean aucType = (Boolean)request.getSession().getAttribute("aucType04");
		//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
		if(aucType == null || !aucType  ){
			return null;
		}
		
		EgovMap atLoginVO = (EgovMap) request.getSession().getAttribute("atLoginVO");
		
		paramMap.put("id", atLoginVO.get("loginId"));
		paramMap.put("memberId", atLoginVO.get("memberId"));
		paramMap.put("code", paramMap.get("transCode"));
		
		EgovMap checkVO = statSecondService.getFloMemberCheck(paramMap);
		if(checkVO == null){
			return null;
		}
		/*DB쿼리 가져오기*/
		
		List<EgovMap> resultList = statSecondService.transShprPartiDetailSelectList(paramMap);
		EgovMap sumVO = statSecondService.transShprPartiDetailSumList(paramMap);
		
		model.addAttribute("resultList",resultList);
		model.addAttribute("sumVO",sumVO);
		model.addAttribute("paramMap", paramMap);
		
		return "jsonView";
	}
	
	@RequestMapping(value="/front/stat/transShprPartiDetailExcell.do")
	public void transShprPartiDetailExcell(
			  @RequestParam Map<String, Object> paramMap
			, HttpServletResponse response
			, HttpServletRequest request
			, ModelMap model) throws Exception{
	 
		Boolean aucType = (Boolean)request.getSession().getAttribute("aucType04");
		//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
		if(aucType == null || !aucType  ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	        throw new ModelAndViewDefiningException(modelAndView);
		}
		
		EgovMap atLoginVO = (EgovMap) request.getSession().getAttribute("atLoginVO");
		
		paramMap.put("id", atLoginVO.get("loginId"));
		paramMap.put("memberId", atLoginVO.get("memberId"));
		paramMap.put("code", paramMap.get("transCode"));
		
		EgovMap checkVO = statSecondService.getFloMemberCheck(paramMap);
		if(checkVO == null){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
			throw new ModelAndViewDefiningException(modelAndView);
		}
		
		List<EgovMap> list = statSecondService.transShprPartiDetailSelectList(paramMap);
		EgovMap sumVO = statSecondService.transShprPartiDetailSumList(paramMap);
		
		try {
			
		String[] headerArray = {"경매일자","코드","성명","상장번호","규격","비규격","합계","운송비","KG","결과"};
		String[] dataArray = {"panday","chulcode","chulname","panno","sboxcnt","uboxcnt","tboxcnt","trpri","pumkg","kmlist"};
		
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		 int tableInitRow = 10;
	 	// 상장 데이터 시트 생성
	    SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("출하자별 운송내역상세");
	    
	    //시트 열 너비 설정
	    for(int i=0; i<headerArray.length; i++){
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
	    titleCell.setCellValue("출하자별 운송내역상세");
	    titleCell.setCellStyle(titleCellStyle);
	    
	    sheet.addMergedRegion(new CellRangeAddress(1, 3, 0, 4));
	    
	    Row groupRow = sheet.createRow(5);
	    Cell groupCell = groupRow.createCell(0);
	    groupCell.setCellValue("운송업체");
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupCell = groupRow.createCell(1);
	    groupCell.setCellValue(String.valueOf(paramMap.get("transCode")));
	    sheet.addMergedRegion(new CellRangeAddress(5, 5, 1, 4));
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(2);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(3);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(4);
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupRow = sheet.createRow(6);
	    groupCell = groupRow.createCell(0);
	    groupCell.setCellValue("화훼부류");
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupCell = groupRow.createCell(1);
	    String bunName = "";
	    String bunChk = String.valueOf(paramMap.get("bunChk")); 
	    if("N".equals(bunChk)){
	    	bunName = "절화";
	    }else if("Y".equals(bunChk)){
	    	bunName = "난";
	    }else if("C".equals(bunChk)){
	    	bunName = "관엽";
	    }
	    groupCell.setCellValue(bunName);
	    sheet.addMergedRegion(new CellRangeAddress(6, 6, 1, 4));
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(2);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(3);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(4);
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupRow = sheet.createRow(7);
	    groupCell = groupRow.createCell(0);
	    groupCell.setCellValue("경매기간");
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupCell = groupRow.createCell(1);
	    String panDate = String.valueOf(paramMap.get("panDate"));
	    groupCell.setCellValue(panDate);
	    sheet.addMergedRegion(new CellRangeAddress(7, 7, 1, 4));
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(2);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(3);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(4);
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupRow = sheet.createRow(8);
	    groupCell = groupRow.createCell(0);
	    groupCell.setCellValue("조회일자");
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupCell = groupRow.createCell(1);
	    Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat( "yyyy-MM-dd");
	    groupCell.setCellValue(dateformat.format(date));
	    sheet.addMergedRegion(new CellRangeAddress(8, 8, 1, 4));
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(2);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(3);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(4);
	    groupCell.setCellStyle(headerCellStyle);
	    
	    
	   
	    
	    // 헤더 행 생
	    Row headerRow = sheet.createRow(tableInitRow);
	   
	    Cell headerCell = null;
	    for(int i = 0; i<headerArray.length; i++){
	    	headerCell = headerRow.createCell(i);
	        headerCell.setCellValue(headerArray[i]);
	        headerCell.setCellStyle(headerCellStyle);
	    	
	    }
	    
	    Row bodyRow = null;
	    Cell bodyCell = null;
	    for(int i=0; i<list.size(); i++) {
	    	EgovMap resultVO = list.get(i);
	        
	        // 행 생성
	        bodyRow = sheet.createRow(tableInitRow+(i+1));
	        for(int j = 0; j<dataArray.length; j++){
	        	if(resultVO.get(dataArray[j]) == null){
	        		resultVO.put(dataArray[j],"");
	            }
	            bodyCell = bodyRow.createCell(j);
	            
	            if(j>3 && j<9){
	            	bodyCell.setCellValue(Integer.parseInt(String.valueOf(resultVO.get(dataArray[j]))));
	            	bodyCell.setCellStyle(bodyNumCellStyle);
	            }else if(j == 9){
	            	String kmListText = "";
	            	String kmList = String.valueOf(resultVO.get(dataArray[j]));
	            	
            		if(kmList != null){
	            		if(kmList.equals("1")){
	            			kmListText = "낙찰";
		            	}else if(kmList.equals("2")){
		            		kmListText = "유찰";
		            	}else if(kmList.equals("3")){
		            		kmListText = "기록";
		            	}else if(kmList.equals("4")){
		            		kmListText = "기타";
		            	}else if(kmList.equals("8")){
		            		kmListText = "폐기대상";
		            	}else if(kmList.equals("9")){
		            		kmListText = "폐기확정";
		            	}
	            	}
	            	
	            	
	            	bodyCell.setCellValue(kmListText);
	            	bodyCell.setCellStyle(bodyCellStyle);
	            }else{
	            	bodyCell.setCellValue(String.valueOf(resultVO.get(dataArray[j])));
	            	bodyCell.setCellStyle(bodyCellStyle);
	            }
	            
	        }
	
	    }
	    if(list.size()>0){
	    	int sumIndex = tableInitRow+(list.size()+1);
		    bodyRow = sheet.createRow(sumIndex);
		    bodyCell = bodyRow.createCell(0);
	        bodyCell.setCellValue("합계");
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(1);
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(2);
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(3);
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        bodyCell = bodyRow.createCell(4);
	        bodyCell.setCellValue(Integer.parseInt(String.valueOf(sumVO.get("sboxcntsum"))));
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(5);
	        bodyCell.setCellValue(Integer.parseInt(String.valueOf(sumVO.get("uboxcntsum"))));
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(6);
	        bodyCell.setCellValue(Integer.parseInt(String.valueOf(sumVO.get("tboxcntsum"))));
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(7);
	        bodyCell.setCellValue(Integer.parseInt(String.valueOf(sumVO.get("trprisum"))));
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(8);
	        bodyCell.setCellValue(Integer.parseInt(String.valueOf(sumVO.get("pumkgsum"))));
	        bodyCell.setCellStyle(bodyNumCellStyle);
	        
	        bodyCell = bodyRow.createCell(9);
	        bodyCell.setCellStyle(bodyCellStyle);
	        
	        
	    }
	    
        
        model.addAttribute("locale", Locale.KOREA);
        model.addAttribute("workbook", workbook);
        model.addAttribute("workbookName", "출하자별운송내역[상세]"+String.valueOf(paramMap.get("panDate")));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 	
        renderMergedOutputModel(model, request, response);
       // return "excelDownloadView";
	 
	}
	
	
	// 항운노조 - 중도매인 배송비 내역
	@RequestMapping(value = "/front/stat/wholeSalerShipping.do")
	public String wholeSalerShipping(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap, ModelMap model)
			throws Exception {

		Boolean aucType = (Boolean) request.getSession().getAttribute("aucType05");
		// 01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체 / 05 하역노조(항운노조) / 07 경매사
		if (aucType == null || !aucType) {
			return "redirect:/front/login.do";
		}

		/* DB쿼리 가져오기 */

		model.addAttribute("contentPath", "stat/wholeSalerShipping.jsp");

		return "/front/layout/loginLayout";
	}

	@RequestMapping(value="/front/stat/wholeSalerShipping.json")
	public String wholeSalerShippingJson(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		Boolean aucType = (Boolean)request.getSession().getAttribute("aucType05");
		//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
		if(aucType == null || !aucType  ){
			return null;
		}
		
		/*DB쿼리 가져오기*/
		
		List<EgovMap> resultList = statSecondService.wholeSalerShippingSelectList(paramMap);
		
		model.addAttribute("resultList",resultList);
		model.addAttribute("paramMap", paramMap);
		
		return "jsonView";
	}
	
	@RequestMapping(value="/front/stat/wholeSalerShippingExcell.do")
	public void wholeSalerShippingExcell(
			  @RequestParam Map<String, Object> paramMap
			, HttpServletResponse response
			, HttpServletRequest request
			, ModelMap model) throws Exception{
	 
		Boolean aucType = (Boolean)request.getSession().getAttribute("aucType05");
		//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
		if(aucType == null || !aucType  ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	        throw new ModelAndViewDefiningException(modelAndView);
		}
		
		
		List<EgovMap> list = statSecondService.wholeSalerShippingSelectList(paramMap);
		
		try {
			
		String[] headerArray = {"코드","30속까지\n노임비","50속까지\n노임비","51속까지\n노임비","0상자(11속이상)\n노임비","습식상자\n노임비","상자계\n노임비계"};
		String[] dataArray = {"jDomeCode","cnt30","cnt50","cnt50up","cnt0","cntw","totcnt"};
		String[] dataArray2 = {"name","amt30","amt50","amt50up","amt0","amtw","totamt"};
		
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		 int tableInitRow = 10;
	 	// 상장 데이터 시트 생성
	    SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("중도매인 배송비내역");
	    
	    //시트 열 너비 설정
	    for(int i=0; i<headerArray.length; i++){
	    	sheet.setColumnWidth(i, 5000);
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
	    titleCell.setCellValue("중도매인 배송비 내역");
	    titleCell.setCellStyle(titleCellStyle);
	    
	    sheet.addMergedRegion(new CellRangeAddress(1, 3, 0, 4));
	    
	    Row groupRow = sheet.createRow(6);
	    Cell groupCell = groupRow.createCell(0);
	    
	    groupCell.setCellValue("화훼부류");
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupCell = groupRow.createCell(1);
	    String bunName = "";
	    String bunChk = String.valueOf(paramMap.get("bunChk")); 
	    if("N".equals(bunChk)){
	    	bunName = "절화";
	    }else if("Y".equals(bunChk)){
	    	bunName = "난";
	    }else if("C".equals(bunChk)){
	    	bunName = "관엽";
	    }
	    groupCell.setCellValue(bunName);
	    sheet.addMergedRegion(new CellRangeAddress(6, 6, 1, 4));
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(2);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(3);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(4);
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupRow = sheet.createRow(7);
	    groupCell = groupRow.createCell(0);
	    groupCell.setCellValue("경매기간");
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupCell = groupRow.createCell(1);
	    String startDate = String.valueOf(paramMap.get("startDate"));
	    String endDate = String.valueOf(paramMap.get("endDate")); 
	    groupCell.setCellValue(startDate + "~" + endDate);
	    sheet.addMergedRegion(new CellRangeAddress(7, 7, 1, 4));
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(2);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(3);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(4);
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupRow = sheet.createRow(8);
	    groupCell = groupRow.createCell(0);
	    groupCell.setCellValue("조회일자");
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupCell = groupRow.createCell(1);
	    Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat( "yyyy-MM-dd");
	    groupCell.setCellValue(dateformat.format(date));
	    sheet.addMergedRegion(new CellRangeAddress(8, 8, 1, 4));
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(2);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(3);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(4);
	    groupCell.setCellStyle(headerCellStyle);
	    
	    
	   
	    
	    // 헤더 행 생
	    Row headerRow = sheet.createRow(tableInitRow);
	   
	    Cell headerCell = null;
	    for(int i = 0; i<headerArray.length; i++){
	    	headerCell = headerRow.createCell(i);
	        headerCell.setCellValue(headerArray[i]);
	        headerCell.setCellStyle(headerCellStyle);
	    	
	    }
	    
	    Row bodyRow = null;
	    Row bodyRow2 = null;
	    Cell bodyCell = null;
	    Cell bodyCell2 = null;
	    for(int i=0; i<list.size(); i++) {
	    	EgovMap resultVO = list.get(i);
	        
	        // 행 생성 2 0+2  4 1+3  6 2+4 8 3+5   
	        bodyRow = sheet.createRow(tableInitRow+(i+(i+1)));
	        bodyRow2 = sheet.createRow(tableInitRow+(i+(i+2)));
	        
	        for(int j = 0; j<dataArray.length; j++){
	        	if(resultVO.get(dataArray[j]) == null){
	        		resultVO.put(dataArray[j],"");
	            }
	        	if(resultVO.get(dataArray2[j]) == null){
	        		resultVO.put(dataArray2[j],"");
	            }
	        	
	            bodyCell = bodyRow.createCell(j);
	            
	            if(j>0){
	            	bodyCell.setCellValue(Integer.parseInt(String.valueOf(resultVO.get(dataArray[j]))));
	            	bodyCell.setCellStyle(bodyNumCellStyle);
	            }else{
	            	bodyCell.setCellValue(String.valueOf(resultVO.get(dataArray[j])));
	            	bodyCell.setCellStyle(bodyCellStyle);
	            }
	            
	            bodyCell2 = bodyRow2.createCell(j);
	            
	            if(j>0){
	            	bodyCell2.setCellValue(Integer.parseInt(String.valueOf(resultVO.get(dataArray2[j]))));
	            	bodyCell2.setCellStyle(bodyNumCellStyle);
	            }else{
	            	bodyCell2.setCellValue(String.valueOf(resultVO.get(dataArray2[j])));
	            	bodyCell2.setCellStyle(bodyCellStyle);
	            }
	            
	        }
	
	    }
	    
        model.addAttribute("locale", Locale.KOREA);
        model.addAttribute("workbook", workbook);
        model.addAttribute("workbookName", "중도매인 배송비내역"+String.valueOf(paramMap.get("startDate"))+"_"+String.valueOf(paramMap.get("endDate")));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 	
        renderMergedOutputModel(model, request, response);
       // return "excelDownloadView";
	 
	}
	
	
	// 항운노조 - 상자형 별 하역비 내역
	@RequestMapping(value = "/front/stat/boxLoading.do")
	public String boxLoading(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap, ModelMap model)
			throws Exception {

		Boolean aucType = (Boolean) request.getSession().getAttribute("aucType05");
		// 01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체 / 05 하역노조(항운노조) / 07 경매사
		if (aucType == null || !aucType) {
			return "redirect:/front/login.do";
		}

		/* DB쿼리 가져오기 */

		model.addAttribute("contentPath", "stat/boxLoading.jsp");

		return "/front/layout/loginLayout";
	}
	
	
	@RequestMapping(value="/front/stat/boxLoading.json")
	public String boxLoadingJson(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap
			, ModelMap model) throws Exception {
		
		Boolean aucType = (Boolean)request.getSession().getAttribute("aucType05");
		//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
		if(aucType == null || !aucType  ){
			return null;
		}
		
		/*DB쿼리 가져오기*/
		try {
			List<EgovMap> resultList = statSecondService.boxLoadingSelectList(paramMap);
			
			model.addAttribute("resultList",resultList);
			model.addAttribute("paramMap",paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "jsonView";
	}
	
	@RequestMapping(value="/front/stat/boxLoadingExcell.do")
	public void boxLoadingExcell(
			  @RequestParam Map<String, Object> paramMap
			, HttpServletResponse response
			, HttpServletRequest request
			, ModelMap model) throws Exception{
	 
		Boolean aucType = (Boolean)request.getSession().getAttribute("aucType05");
		//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
		if(aucType == null || !aucType  ){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	        throw new ModelAndViewDefiningException(modelAndView);
		}
		
		
		List<EgovMap> list = statSecondService.boxLoadingSelectList(paramMap);
		
		try {
			
		String[] headerArray = {"하역일자","상자코드","상자형","상자수량","하역단가","하역비(농가)","하역비(공사)","재포장비","합계금액"};
		String[] dataArray = {"fpanDay","boxCode","boxName","boxCnt","downP","downReP","downRePg","reBoxP","sumP"};
		
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		 int tableInitRow = 10;
	 	// 상장 데이터 시트 생성
	    SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("상자형별 하역비내역");
	    
	    //시트 열 너비 설정
	    for(int i=0; i<headerArray.length; i++){
	    	sheet.setColumnWidth(i, 5000);
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
	    titleCell.setCellValue("중도매인 배송비 내역");
	    titleCell.setCellStyle(titleCellStyle);
	    
	    sheet.addMergedRegion(new CellRangeAddress(1, 3, 0, 4));
	    
	    Row groupRow = sheet.createRow(6);
	    Cell groupCell = groupRow.createCell(0);
	    
	    groupCell.setCellValue("화훼부류");
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupCell = groupRow.createCell(1);
	    String bunName = "";
	    String bunChk = String.valueOf(paramMap.get("bunChk")); 
	    if("N".equals(bunChk)){
	    	bunName = "절화";
	    }else if("Y".equals(bunChk)){
	    	bunName = "난";
	    }else if("C".equals(bunChk)){
	    	bunName = "관엽";
	    }
	    groupCell.setCellValue(bunName);
	    sheet.addMergedRegion(new CellRangeAddress(6, 6, 1, 4));
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(2);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(3);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(4);
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupRow = sheet.createRow(7);
	    groupCell = groupRow.createCell(0);
	    groupCell.setCellValue("경매기간");
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupCell = groupRow.createCell(1);
	    String startDate = String.valueOf(paramMap.get("startDate"));
	    String endDate = String.valueOf(paramMap.get("endDate")); 
	    groupCell.setCellValue(startDate + "~" + endDate);
	    sheet.addMergedRegion(new CellRangeAddress(7, 7, 1, 4));
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(2);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(3);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(4);
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupRow = sheet.createRow(8);
	    groupCell = groupRow.createCell(0);
	    groupCell.setCellValue("조회일자");
	    groupCell.setCellStyle(headerCellStyle);
	    
	    groupCell = groupRow.createCell(1);
	    Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat( "yyyy-MM-dd");
	    groupCell.setCellValue(dateformat.format(date));
	    sheet.addMergedRegion(new CellRangeAddress(8, 8, 1, 4));
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(2);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(3);
	    groupCell.setCellStyle(headerCellStyle);
	    groupCell = groupRow.createCell(4);
	    groupCell.setCellStyle(headerCellStyle);
	    
	    
	   
	    
	    // 헤더 행 생
	    Row headerRow = sheet.createRow(tableInitRow);
	   
	    Cell headerCell = null;
	    for(int i = 0; i<headerArray.length; i++){
	    	headerCell = headerRow.createCell(i);
	        headerCell.setCellValue(headerArray[i]);
	        headerCell.setCellStyle(headerCellStyle);
	    	
	    }
	    
	    Row bodyRow = null;
	    
	    Cell bodyCell = null;
	    
	    for(int i=0; i<list.size(); i++) {
	    	EgovMap resultVO = list.get(i);
	        
	        // 행 생성 2 0+2  4 1+3  6 2+4 8 3+5   
	        bodyRow = sheet.createRow(tableInitRow+(i+1));
	        
	        for(int j = 0; j<dataArray.length; j++){
	        	
	        	System.out.println("jjjjjjjjjjjjj"+String.valueOf(resultVO.get(dataArray[j])));
	        	if(resultVO.get(dataArray[j]) == null){
	        		resultVO.put(dataArray[j],"");
	            }
	        	
	            bodyCell = bodyRow.createCell(j);
	            
	            if(j>2){
	            	System.out.println("jjjjjjjjjjjjj"+j);
	            	
	            	bodyCell.setCellValue(Integer.parseInt(String.valueOf(resultVO.get(dataArray[j]))));
	            	bodyCell.setCellStyle(bodyNumCellStyle);
	            }else{
	            	bodyCell.setCellValue(String.valueOf(resultVO.get(dataArray[j])));
	            	bodyCell.setCellStyle(bodyCellStyle);
	            }
	        }
	
	    }
	    
        model.addAttribute("locale", Locale.KOREA);
        model.addAttribute("workbook", workbook);
        model.addAttribute("workbookName", "상자형별 하역비내역"+String.valueOf(paramMap.get("startDate"))+"_"+String.valueOf(paramMap.get("endDate")));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 	
        renderMergedOutputModel(model, request, response);
       // return "excelDownloadView";
	 
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
	
}
