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
import egovframework.front.stat.service.StatThirdService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Controller
public class StatThirdController {

	/** EgovPropertyService */
	@Resource(name = "frontLoginService")
	protected FrontLoginService frontLoginService;
	
	@Resource(name = "statSecondService")
	private StatSecondService statSecondService;
	
	@Resource(name = "statThirdService")
	private StatThirdService statThirdService;
	
	
	// 경매사 - 출하농가 판매정산내역[전체]
	@RequestMapping(value="/front/stat/shprSaleAllCalcDetail.do")
	public String shprSaleAllCalcDetail(HttpServletRequest request
								      , HttpServletResponse response
									  , @RequestParam Map<String, Object> paramMap
									  , ModelMap model) throws Exception {
		
		Boolean aucType = (Boolean)request.getSession().getAttribute("aucType07");
		//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
		if(aucType == null || !aucType) {
			return "redirect:/front/login.do";
		}
		
		model.addAttribute("contentPath", "stat/shprSaleAllCalcDetail.jsp");
		
		return "/front/layout/loginLayout";
	}
	
	@RequestMapping(value="/front/stat/shprSaleAllCalcDetail.json")
	public String shprSaleAllCalcDetailJson(HttpServletRequest request
										  , HttpServletResponse response
										  , @RequestParam Map<String, Object> paramMap
										  , ModelMap model) throws Exception {
		
		Boolean aucType = (Boolean)request.getSession().getAttribute("aucType07");
		//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
		if(aucType == null || !aucType){
			return null;
		}
		
		/*DB쿼리 가져오기*/
		EgovMap chulVO = statSecondService.getChulInfo(paramMap);
		List<EgovMap> resultList = statThirdService.shprSaleCalcDetailSelect(paramMap);
		
		model.addAttribute("chulVO", chulVO);
		model.addAttribute("resultList", resultList);
		
		return "jsonView";
	}
	
	@RequestMapping(value = "/front/stat/getChulList.json")
	public String getChulList(HttpServletRequest request
							, HttpServletResponse response
							, @RequestParam Map<String, Object> paramMap
							, ModelMap model ) throws Exception {
	    
		try {
			
			Boolean aucType = (Boolean)request.getSession().getAttribute("aucType07");
			//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
			if(aucType == null || !aucType){
				ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
				modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
			}
			
			//출하자 코드리스트
			List<EgovMap> chulList = statThirdService.getChulList(paramMap);
			model.addAttribute("chulList", chulList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "jsonView";
	  }
	
	
	@RequestMapping(value="/front/stat/shprSaleAllCalcDetailExcell.do")
	public void shprSaleAllCalcDetailExcell(@RequestParam Map<String, Object> paramMap
										  , HttpServletResponse response
										  , HttpServletRequest request
										  , ModelMap model) throws Exception{
	 
		Boolean aucType = (Boolean)request.getSession().getAttribute("aucType07");
		//01 출하농가/ 02 출하단체 / 03 중도매인 / 04 운송업체  / 05 하역노조(항운노조) / 07 경매사
		if(aucType == null || !aucType){
			ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
			modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
	        throw new ModelAndViewDefiningException(modelAndView);
		}
		
		
		EgovMap chulVO = statSecondService.getChulInfo(paramMap);
		List<EgovMap> list = statThirdService.shprSaleCalcDetailSelect(paramMap);
		
		
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
        model.addAttribute("workbookName", "판매정산내역[상세]"+String.valueOf(paramMap.get("startDate"))+"_"+String.valueOf(paramMap.get("endDate")));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 	
        renderMergedOutputModel(model, request, response);
       // return "excelDownloadView";
	 
	}
	
	protected void renderMergedOutputModel(Map<String, Object> model
										 , HttpServletRequest request
										 , HttpServletResponse response) throws Exception {
        
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
	
	
	
	
	
	
	
	
	
	
	
	// 농림수산식품부 - 월별 온라인매매 현황
	@RequestMapping(value="/front/stat/mafraMagamMasterList.do")
	public String mafraMagamMasterList(HttpServletRequest request
								      , HttpServletResponse response
									  , @RequestParam Map<String, Object> paramMap
									  , ModelMap model) throws Exception {
		
		Boolean aucType = (Boolean)request.getSession().getAttribute("aucType99");
		//99 농식품부
		if(aucType == null || !aucType) {
			return "redirect:/front/login.do";
		}
		
		model.addAttribute("contentPath", "stat/mafraMagamMaster.jsp");
		
		return "/front/layout/loginLayout";
	}
	
	@RequestMapping(value="/front/stat/mafraMagamMasterListJson.json")
	public String mafraMagamMasterListJson(HttpServletRequest request
										  , HttpServletResponse response
										  , @RequestParam Map<String, Object> paramMap
										  , ModelMap model) throws Exception {
		
		Boolean aucType = (Boolean)request.getSession().getAttribute("aucType99");
		//99 농식품부
		if(aucType == null || !aucType) {
			return null;
		}
		
		System.out.println("paramMap: " + paramMap);
		
		/*DB쿼리 가져오기*/
		List<EgovMap> resultList = statThirdService.mafraMagamMasterListJson(paramMap);
		
		model.addAttribute("paramMap", paramMap);
		model.addAttribute("resultList", resultList);
		
		return "jsonView";
	}
}
