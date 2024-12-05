package egovframework.front.stat.web;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
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
import egovframework.front.stat.service.StatService;
import egovframework.rte.psl.dataaccess.util.EgovMap;


@Controller
public class StatController {

	/** EgovPropertyService */
	@Resource(name = "frontLoginService")
	protected FrontLoginService frontLoginService;

	@Resource(name = "statService")
	private StatService statService;

	@Resource(name = "statSecondService")
	private StatSecondService statSecondService;
	
	// 경매시세
	@RequestMapping(value = "/front/stat/aucPrice.do")
	public String dealPrice(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap, ModelMap model)
			throws Exception {

		/* DB쿼리 가져오기 */
		try {
			
		
		List<EgovMap> maxList = statService.maxBunChkPanDay(paramMap);
		model.addAttribute("maxList",maxList);
		model.addAttribute("paramMap",paramMap);

		model.addAttribute("contentPath", "stat/aucPrice.jsp");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "/front/layout/subLayout";
	}

	// 경매시세
	@RequestMapping(value = "/front/stat/aucPrice.json")
	public String aucPriceJson(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap, ModelMap model)
			throws Exception {
	
		List<EgovMap> resultList = statService.aucPriceList(paramMap);
		
		model.addAttribute("resultList",resultList);
		
		return "jsonView";
	}
	
	
	//공통 품목,품종별 거래시세
	@RequestMapping(value = "/front/stat/pumAucPriceList.do")
	public String pumAucPriceList(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap, ModelMap model)
			throws Exception {

		
		try {
			
		Boolean aucType01 = (Boolean) request.getSession().getAttribute("aucType01");
		Boolean aucType02 = (Boolean) request.getSession().getAttribute("aucType02");
		Boolean aucType03 = (Boolean) request.getSession().getAttribute("aucType03");
		Boolean aucType04 = (Boolean) request.getSession().getAttribute("aucType04");
		Boolean aucType05 = (Boolean) request.getSession().getAttribute("aucType05");
		// 01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체 / 05 하역노조(항운노조) / 07 경매사
		if ((aucType01 == null || !aucType01)&&(aucType02 == null || !aucType02)&&(aucType03 == null || !aucType03)&&(aucType04 == null || !aucType04)&&(aucType05 == null || !aucType05)) {
			return "redirect:/front/login.do";
		}	
			
		model.addAttribute("paramMap",paramMap);

		model.addAttribute("contentPath", "stat/pumAucPrice.jsp");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "/front/layout/loginLayout";
	}

	//공통 품목,품종별 거래시세
	@RequestMapping(value = "/front/stat/pumAucPriceList.json")
	public String pumAucPriceListJson(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap, ModelMap model)
			throws Exception {
		
		Boolean aucType01 = (Boolean) request.getSession().getAttribute("aucType01");
		Boolean aucType02 = (Boolean) request.getSession().getAttribute("aucType02");
		Boolean aucType03 = (Boolean) request.getSession().getAttribute("aucType03");
		Boolean aucType04 = (Boolean) request.getSession().getAttribute("aucType04");
		Boolean aucType05 = (Boolean) request.getSession().getAttribute("aucType05");
		// 01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체 / 05 하역노조(항운노조) / 07 경매사
		if ((aucType01 == null || !aucType01)&&(aucType02 == null || !aucType02)&&(aucType03 == null || !aucType03)&&(aucType04 == null || !aucType04)&&(aucType05 == null || !aucType05)) {
			return null;
		}
	
		List<EgovMap> resultList = statService.pumAucPriceList(paramMap);
		
		model.addAttribute("resultList",resultList);
		model.addAttribute("paramMap", paramMap);
		
		return "jsonView";
	}

	// 중도매인 - 거래내역(낙찰서)
	@RequestMapping(value = "/front/stat/whslDealCalc.do")
	public String whslDealCalc(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap, ModelMap model)
			throws Exception {

		Boolean aucType = (Boolean) request.getSession().getAttribute("aucType03");
		// 01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체 / 05 하역노조(항운노조) / 07 경매사
		if (aucType == null || !aucType) {
			return "redirect:/front/login.do";
		}

		/* DB쿼리 가져오기 */
		
		model.addAttribute("paramMap",paramMap);

		model.addAttribute("contentPath", "stat/whslDealCalc.jsp");

		return "/front/layout/loginLayout";
	}

	// 중도매인 - 거래내역(낙찰서)
	@RequestMapping(value = "/front/stat/whslDealCalc.json")
	public String whslDealCalcJson(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap, ModelMap model)
			throws Exception {

		Boolean aucType = (Boolean) request.getSession().getAttribute("aucType03");
		// 01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체 / 05 하역노조(항운노조) / 07 경매사
		if (aucType == null || !aucType) {
			return null;
		}
	
		EgovMap atLoginVO = (EgovMap) request.getSession().getAttribute("atLoginVO");
		
		paramMap.put("id", atLoginVO.get("loginId"));
		paramMap.put("memberId", atLoginVO.get("memberId"));
		paramMap.put("code", paramMap.get("domeCode"));
		
		EgovMap checkVO = statService.getFloMemberCheck(paramMap);
		if(checkVO == null){
			return null;
		}
		System.out.println("1");
		/* DB쿼리 가져오기 */
		String selectType = String.valueOf(paramMap.get("selectType")) ;
		if("date".equals(selectType)){
			EgovMap dateVO  = statService.whslDealCalcMdateSelect(paramMap);
			model.addAttribute("dateVO",dateVO);
		}else{
			List<EgovMap> resultList = statService.whslDealCalcSelectList(paramMap);
			EgovMap totVO = statService.whslDealCalcTotList(paramMap);
			model.addAttribute("resultList",resultList);
			model.addAttribute("totVO",totVO);
		}
		
		return "jsonView";
	}
	
	// 중도매인 - 거래내역(낙찰서)
	@RequestMapping(value = "/front/stat/whslDealCalcExcell.do")
	public void whslDealCalcExcell(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap, ModelMap model)
			throws Exception {

		Boolean aucType = (Boolean) request.getSession().getAttribute("aucType03");
		// 01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체 / 05 하역노조(항운노조) / 07 경매사
		if (aucType == null || !aucType) {
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	        throw new ModelAndViewDefiningException(modelAndView);
		}

		/* DB쿼리 가져오기 */
		List<EgovMap> resultList = statService.whslDealCalcSelectList(paramMap);
		EgovMap totVO = statService.whslDealCalcTotList(paramMap);
		EgovMap dateVO  = statService.whslDealCalcMdateSelect(paramMap);
		EgovMap nameVO = statService.whslDealBlotterNameSelect(paramMap);

		try {
			
			
			 SXSSFWorkbook workbook = new SXSSFWorkbook();
			 int tableInitRow = 11;
		 	// 상장 데이터 시트 생성
		    SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("거래내역(낙찰서)");
		    
		    //String[] headerMergeArray = {"경매일자","총경매량","","판매량","","유찰량","","판매금액","공제금액","지급금액"};
		    String[] headerArray = {"품목명","품종명","등급","상자수","속수량","단가","매입금액","상장번호","출하자"};
		    String[] dataArray = {"pmokname","pjongname","levelname","pboxcnt","psokcnt","kmpnew","panprice","panno","chulname"};
		    
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
		    titleCell.setCellValue("거래내역(낙찰서)");
		    titleCell.setCellStyle(titleCellStyle);
		    
		    sheet.addMergedRegion(new CellRangeAddress(1, 3, 0, 4));
		    
		    Row groupRow = sheet.createRow(5);
		    Cell groupCell = groupRow.createCell(0);
		    groupCell.setCellValue("중도매인");
		    groupCell.setCellStyle(headerCellStyle);
		    
		    groupCell = groupRow.createCell(1);
		    groupCell.setCellValue(String.valueOf(nameVO.get("name")));
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
		    groupCell.setCellValue("경매일자");
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
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
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
	        
	        //headerRow = sheet.createRow(tableInitRow);
		    for(int i = 0; i<headerArray.length; i++){
		    	headerCell = headerRow.createCell(i);
		        headerCell.setCellValue(headerArray[i]);
		        headerCell.setCellStyle(headerCellStyle);
		    	
		    }
		    
		    sheet.addMergedRegion(new CellRangeAddress(10, 10, 0, 0));
		    sheet.addMergedRegion(new CellRangeAddress(10, 10, 1, 1));
		    sheet.addMergedRegion(new CellRangeAddress(10, 10, 2, 2));
		    sheet.addMergedRegion(new CellRangeAddress(10, 10, 3, 3));
		    sheet.addMergedRegion(new CellRangeAddress(10, 10, 4, 4));
		    sheet.addMergedRegion(new CellRangeAddress(10, 10, 5, 5));
		    sheet.addMergedRegion(new CellRangeAddress(10, 10, 6, 6));
		    sheet.addMergedRegion(new CellRangeAddress(10, 10, 7, 7));
		    sheet.addMergedRegion(new CellRangeAddress(10, 10, 8, 8));
		  
		    
		    
		    Row bodyRow = null;
		    Cell bodyCell = null;
		    for(int i=0; i<resultList.size(); i++) {
		    	EgovMap resultVO = resultList.get(i);
		        
		        // 행 생성
		        bodyRow = sheet.createRow(tableInitRow+(i));
		        for(int j = 0; j<dataArray.length; j++){
		        	if(resultVO.get(dataArray[j]) == null){
		        		resultVO.put(dataArray[j],"");
		            }
		            bodyCell = bodyRow.createCell(j);
		            
		            if(j>2 && j<7){
		            	bodyCell.setCellValue(Integer.parseInt(String.valueOf(resultVO.get(dataArray[j]))));
		            	bodyCell.setCellStyle(bodyNumCellStyle);
		            }else{
		            	bodyCell.setCellValue(String.valueOf(resultVO.get(dataArray[j])));
		            	bodyCell.setCellStyle(bodyCellStyle);
		            }
		            
		        }
		
		    }
		    if(resultList.size()>0){
		    	int sumIndex = tableInitRow+(resultList.size());
			    bodyRow = sheet.createRow(sumIndex);
			    bodyCell = bodyRow.createCell(0);
		        bodyCell.setCellValue("합계");
		        bodyCell.setCellStyle(bodyCellStyle);
		        
		        bodyCell = bodyRow.createCell(1);
		        bodyCell.setCellValue("");
		        bodyCell.setCellStyle(bodyNumCellStyle);
		        
		        bodyCell = bodyRow.createCell(2);
		        bodyCell.setCellValue("");
		        bodyCell.setCellStyle(bodyNumCellStyle);
		        
		        bodyCell = bodyRow.createCell(3);
		        bodyCell.setCellValue(Integer.parseInt(String.valueOf(totVO.get("pboxcnttot"))));
		        bodyCell.setCellStyle(bodyNumCellStyle);
		        
		        bodyCell = bodyRow.createCell(4);
		        bodyCell.setCellValue(Integer.parseInt(String.valueOf(totVO.get("psokcnttot"))));
		        bodyCell.setCellStyle(bodyNumCellStyle);
		        
		        bodyCell = bodyRow.createCell(5);
		        bodyCell.setCellValue("");
		        bodyCell.setCellStyle(bodyNumCellStyle);
		        
		        bodyCell = bodyRow.createCell(6);
		        bodyCell.setCellValue(Integer.parseInt(String.valueOf(totVO.get("panpricetot"))));
		        bodyCell.setCellStyle(bodyNumCellStyle);
		        
		        bodyCell = bodyRow.createCell(7);
		        bodyCell.setCellValue("");
		        bodyCell.setCellStyle(bodyNumCellStyle);
		        
		        bodyCell = bodyRow.createCell(8);
		        bodyCell.setCellValue("");
		        bodyCell.setCellStyle(bodyNumCellStyle);

		    }
		    
	        
	        model.addAttribute("locale", Locale.KOREA);
	        model.addAttribute("workbook", workbook);
	        model.addAttribute("workbookName", "거래내역(낙찰서)"+String.valueOf(paramMap.get("panDate")));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} 	
	        renderMergedOutputModel(model, request, response);
	       
		}
		


	
	// 중도매인 - 거래원장
	@RequestMapping(value = "/front/stat/whslDealBlotter.do")
	public String whslDealBlotter(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap, ModelMap model)
			throws Exception {

		Boolean aucType = (Boolean) request.getSession().getAttribute("aucType03");
		// 01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체 / 05 하역노조(항운노조) / 07 경매사
		if (aucType == null || !aucType) {
			return "redirect:/front/login.do";
		}

		/* DB쿼리 가져오기 */

		model.addAttribute("paramMap",paramMap);
		model.addAttribute("contentPath", "stat/whslDealBlotter.jsp");

		return "/front/layout/loginLayout";
	}
	
	// 중도매인 - 거래원장
	@RequestMapping(value = "/front/stat/whslDealBlotter.json")
	public String whslDealBlotterJson(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap, ModelMap model)
			throws Exception {

		Boolean aucType = (Boolean) request.getSession().getAttribute("aucType03");
		// 01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체 / 05 하역노조(항운노조) / 07 경매사
		if (aucType == null || !aucType) {
			return null;
		}
	
		EgovMap atLoginVO = (EgovMap) request.getSession().getAttribute("atLoginVO");
		
		paramMap.put("id", atLoginVO.get("loginId"));
		paramMap.put("memberId", atLoginVO.get("memberId"));
		paramMap.put("code", paramMap.get("domeCode"));
		
		EgovMap checkVO = statService.getFloMemberCheck(paramMap);
		if(checkVO == null){
			return null;
		}

		/* DB쿼리 가져오기 */
		List<EgovMap> resultList = statService.whslDealBlotterSelectList(paramMap);
		EgovMap sumVO = statService.whslDealBlotterSumList(paramMap); 
		EgovMap nameVO = statService.whslDealBlotterNameSelect(paramMap);
		
		model.addAttribute("nameVO",nameVO);
		model.addAttribute("resultList",resultList);
		model.addAttribute("sumVO",sumVO);
		return "jsonView";
	}
	
	
	// 중도매인 - 거래원장
	@RequestMapping(value = "/front/stat/whslDealBlotterExcell.do")
	public void whslDealBlotterExcell(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap, ModelMap model)
			throws Exception {

		Boolean aucType = (Boolean) request.getSession().getAttribute("aucType03");
		// 01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체 / 05 하역노조(항운노조) / 07 경매사
		if (aucType == null || !aucType) {
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
			throw new ModelAndViewDefiningException(modelAndView);
		}
	
		EgovMap atLoginVO = (EgovMap) request.getSession().getAttribute("atLoginVO");
		
		paramMap.put("id", atLoginVO.get("loginId"));
		paramMap.put("memberId", atLoginVO.get("memberId"));
		paramMap.put("code", paramMap.get("domeCode"));
		
		EgovMap checkVO = statService.getFloMemberCheck(paramMap);
		if(checkVO == null){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
			throw new ModelAndViewDefiningException(modelAndView);
		}

		/* DB쿼리 가져오기 */
		List<EgovMap> resultList = statService.whslDealBlotterSelectList(paramMap);
		EgovMap sumVO = statService.whslDealBlotterSumList(paramMap); 
		EgovMap nameVO = statService.whslDealBlotterNameSelect(paramMap);
		
		try {
			
			
			 SXSSFWorkbook workbook = new SXSSFWorkbook();
			 int tableInitRow = 11;
		 	// 상장 데이터 시트 생성
		    SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("거래원장");
		    
		    String[] headerMergeArray = {"거래일자","적요","매입액","입금액","","","미수금","","","이자부과","","경매가능금액"};
		    String[] headerArray = {"거래일자","적요","매입액","입금총액","미수원금","이자","미수총액","미수원금","이자잔액","이자부과액","이자산출대상금액","경매가능금액"};
		    String[] dataArray = {"jday","summary","meip","ipgum","orgmeip","todayret","misutotal","unptot","dlytot","todaydly","dlyptot","todaybo"};
		    
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
		    titleCell.setCellValue("거래원장");
		    titleCell.setCellStyle(titleCellStyle);
		    
		    sheet.addMergedRegion(new CellRangeAddress(1, 3, 0, 4));
		    
		    Row groupRow = sheet.createRow(5);
		    Cell groupCell = groupRow.createCell(0);
		    groupCell.setCellValue("중도매인");
		    groupCell.setCellStyle(headerCellStyle);
		    
		    groupCell = groupRow.createCell(1);
		    groupCell.setCellValue(String.valueOf(nameVO.get("name")));
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
		    sheet.addMergedRegion(new CellRangeAddress(10, 11, 1, 1));
		    sheet.addMergedRegion(new CellRangeAddress(10, 11, 2, 2));
		    sheet.addMergedRegion(new CellRangeAddress(10, 10, 3, 5));
		    sheet.addMergedRegion(new CellRangeAddress(10, 10, 6, 8));
		    sheet.addMergedRegion(new CellRangeAddress(10, 10, 9, 10));
		    sheet.addMergedRegion(new CellRangeAddress(10, 11, 11, 11));
		    
		    
		    Row bodyRow = null;
		    Cell bodyCell = null;
		    for(int i=0; i<resultList.size(); i++) {
		    	EgovMap resultVO = resultList.get(i);
		        
		        // 행 생성
		        bodyRow = sheet.createRow(tableInitRow+(i+1));
		        for(int j = 0; j<dataArray.length; j++){
		        	if(resultVO.get(dataArray[j]) == null){
		        		resultVO.put(dataArray[j],"");
		            }
		            bodyCell = bodyRow.createCell(j);
		            
		            if(j>1){
		            	bodyCell.setCellValue(Integer.parseInt(String.valueOf(resultVO.get(dataArray[j]))));
		            	bodyCell.setCellStyle(bodyNumCellStyle);
		            }else{
		            	bodyCell.setCellValue(String.valueOf(resultVO.get(dataArray[j])));
		            	bodyCell.setCellStyle(bodyCellStyle);
		            }
		            
		        }
		
		    }
		    if(resultList.size()>0){
		    	int sumIndex = tableInitRow+(resultList.size()+1);
			    bodyRow = sheet.createRow(sumIndex);
			    bodyCell = bodyRow.createCell(0);
		        bodyCell.setCellValue("합계");
		        bodyCell.setCellStyle(bodyCellStyle);
		        
		        bodyCell = bodyRow.createCell(1);
		        bodyCell.setCellValue("");
		        bodyCell.setCellStyle(bodyNumCellStyle);
		        
		        bodyCell = bodyRow.createCell(2);
		        bodyCell.setCellValue(Integer.parseInt(String.valueOf(sumVO.get("meipsum"))));
		        bodyCell.setCellStyle(bodyNumCellStyle);
		        
		        bodyCell = bodyRow.createCell(3);
		        bodyCell.setCellValue(Integer.parseInt(String.valueOf(sumVO.get("ipgumsum"))));
		        bodyCell.setCellStyle(bodyNumCellStyle);
		        
		        bodyCell = bodyRow.createCell(4);
		        bodyCell.setCellValue(Integer.parseInt(String.valueOf(sumVO.get("orgmeipsum"))));
		        bodyCell.setCellStyle(bodyNumCellStyle);
		        
		        bodyCell = bodyRow.createCell(5);
		        bodyCell.setCellValue(Integer.parseInt(String.valueOf(sumVO.get("todayretsum"))));
		        bodyCell.setCellStyle(bodyNumCellStyle);
		        
		        bodyCell = bodyRow.createCell(6);
		        bodyCell.setCellValue(Integer.parseInt(String.valueOf(sumVO.get("misutotalsum"))));
		        bodyCell.setCellStyle(bodyNumCellStyle);
		        
		        bodyCell = bodyRow.createCell(7);
		        bodyCell.setCellValue(Integer.parseInt(String.valueOf(sumVO.get("unptotsum"))));
		        bodyCell.setCellStyle(bodyNumCellStyle);
		        
		        bodyCell = bodyRow.createCell(8);
		        bodyCell.setCellValue(Integer.parseInt(String.valueOf(sumVO.get("dlytotsum"))));
		        bodyCell.setCellStyle(bodyNumCellStyle);
		        
		        bodyCell = bodyRow.createCell(9);
		        bodyCell.setCellValue(Integer.parseInt(String.valueOf(sumVO.get("todaydlysum"))));
		        bodyCell.setCellStyle(bodyNumCellStyle);
		        
		        bodyCell = bodyRow.createCell(10);
		        bodyCell.setCellValue("");
		        bodyCell.setCellStyle(bodyNumCellStyle);
		        
		        bodyCell = bodyRow.createCell(11);
		        bodyCell.setCellValue("");
		        bodyCell.setCellStyle(bodyNumCellStyle);
		    }
		    
	        
	        model.addAttribute("locale", Locale.KOREA);
	        model.addAttribute("workbook", workbook);
	        model.addAttribute("workbookName", "거래원장"+String.valueOf(paramMap.get("startDate"))+"_"+String.valueOf(paramMap.get("endDate")));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} 	
	        renderMergedOutputModel(model, request, response);

		
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
