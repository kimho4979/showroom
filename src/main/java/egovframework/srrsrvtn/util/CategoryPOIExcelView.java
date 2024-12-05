package egovframework.srrsrvtn.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import egovframework.srrsrvtn.vo.SrrsrvtnVO;

public class CategoryPOIExcelView extends AbstractExcelView {
    

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				
				HSSFSheet sheet = (HSSFSheet) workbook.createSheet("예약현황");
				//시트 넓이 설정
				sheet.setColumnWidth(0, 3000);
				sheet.setColumnWidth(1, 3000);
				sheet.setColumnWidth(2, 3000);
				sheet.setColumnWidth(3, 3000);
				sheet.setColumnWidth(4, 3000);
				sheet.setColumnWidth(5, 3000);
				sheet.setColumnWidth(6, 3000);
				sheet.setColumnWidth(7, 3000);
				sheet.setColumnWidth(8, 3000);
				sheet.setColumnWidth(9, 3000);
				sheet.setColumnWidth(10, 3000);
				sheet.setColumnWidth(11, 3000);
				sheet.setColumnWidth(12, 3000);
				sheet.setColumnWidth(13, 3000);
				sheet.setColumnWidth(14, 3000);
				sheet.setColumnWidth(15, 3000);
				sheet.setColumnWidth(16, 3000);
				sheet.setColumnWidth(17, 3000);
				sheet.setColumnWidth(18, 3000);
				sheet.setColumnWidth(19, 3000);
				sheet.setColumnWidth(20, 3000);
				sheet.setColumnWidth(21, 3000);
				sheet.setColumnWidth(22, 3000);
				sheet.setColumnWidth(23, 3000);
				sheet.setColumnWidth(24, 3000);
				sheet.setColumnWidth(25, 3000);
				sheet.setColumnWidth(26, 3000);
				sheet.setColumnWidth(27, 3000);
				sheet.setColumnWidth(28, 3000);
				sheet.setColumnWidth(29, 3000);
				sheet.setColumnWidth(30, 3000);
				sheet.setColumnWidth(31, 3000);
				sheet.setColumnWidth(32, 3000);
				sheet.setColumnWidth(33, 3000);
				
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
	
				Row headerRow = sheet.createRow(0);
				
				Cell headerCell = headerRow.createCell(0);
				headerCell.setCellValue("예약일자");
				headerCell.setCellStyle(headerCellStyle);
	
				headerCell = headerRow.createCell(1);
				headerCell.setCellValue("신청일자");
				headerCell.setCellStyle(headerCellStyle);
	
				headerCell = headerRow.createCell(2);
				headerCell.setCellValue("상태");
				headerCell.setCellStyle(headerCellStyle);
	
				headerCell = headerRow.createCell(3);
				headerCell.setCellValue("전시실");
				headerCell.setCellStyle(headerCellStyle);
	
				headerCell = headerRow.createCell(4);
				headerCell.setCellValue("행사명");
				headerCell.setCellStyle(headerCellStyle);
	
				headerCell = headerRow.createCell(5);
				headerCell.setCellValue("시간대");
				headerCell.setCellStyle(headerCellStyle);
	
				headerCell = headerRow.createCell(6);
				headerCell.setCellValue("대표자명");
				headerCell.setCellStyle(headerCellStyle);
	
				headerCell = headerRow.createCell(7);
				headerCell.setCellValue("결재금액");
				headerCell.setCellStyle(headerCellStyle);

				headerCell = headerRow.createCell(8);
				headerCell.setCellValue("냉난방여부");
				headerCell.setCellStyle(headerCellStyle);

				headerCell = headerRow.createCell(9);
				headerCell.setCellValue("전시실복도여부");
				headerCell.setCellStyle(headerCellStyle);

				headerCell = headerRow.createCell(10);
				headerCell.setCellValue("행사내용");
				headerCell.setCellStyle(headerCellStyle);

				headerCell = headerRow.createCell(11);
				headerCell.setCellValue("상호/단체명");
				headerCell.setCellStyle(headerCellStyle);

				headerCell = headerRow.createCell(12);
				headerCell.setCellValue("담당자명");
				headerCell.setCellStyle(headerCellStyle);

				headerCell = headerRow.createCell(13);
				headerCell.setCellValue("사업자등록번호");
				headerCell.setCellStyle(headerCellStyle);

				headerCell = headerRow.createCell(14);
				headerCell.setCellValue("업종");
				headerCell.setCellStyle(headerCellStyle);

				headerCell = headerRow.createCell(15);
				headerCell.setCellValue("종목");
				headerCell.setCellStyle(headerCellStyle);

				headerCell = headerRow.createCell(16);
				headerCell.setCellValue("휴대전화번호");
				headerCell.setCellStyle(headerCellStyle);

				headerCell = headerRow.createCell(17);
				headerCell.setCellValue("유선전화번호");
				headerCell.setCellStyle(headerCellStyle);

				headerCell = headerRow.createCell(18);
				headerCell.setCellValue("이메일");
				headerCell.setCellStyle(headerCellStyle);
	
				headerCell = headerRow.createCell(19);
				headerCell.setCellValue("주소");
				headerCell.setCellStyle(headerCellStyle);

				headerCell = headerRow.createCell(20);
				headerCell.setCellValue("무선마이크");
				headerCell.setCellStyle(headerCellStyle);

				headerCell = headerRow.createCell(21);
				headerCell.setCellValue("유선마이크");
				headerCell.setCellStyle(headerCellStyle);

				headerCell = headerRow.createCell(22);
				headerCell.setCellValue("방송엠프");
				headerCell.setCellStyle(headerCellStyle);

				headerCell = headerRow.createCell(23);
				headerCell.setCellValue("프로젝터");
				headerCell.setCellStyle(headerCellStyle);

				headerCell = headerRow.createCell(24);
				headerCell.setCellValue("탁자");
				headerCell.setCellStyle(headerCellStyle);

				headerCell = headerRow.createCell(25);
				headerCell.setCellValue("의자");
				headerCell.setCellStyle(headerCellStyle);

				headerCell = headerRow.createCell(26);
				headerCell.setCellValue("쓰레기봉투");
				headerCell.setCellStyle(headerCellStyle);

				headerCell = headerRow.createCell(27);
				headerCell.setCellValue("배치형태");
				headerCell.setCellStyle(headerCellStyle);

				headerCell = headerRow.createCell(28);
				headerCell.setCellValue("임대료");
				headerCell.setCellStyle(headerCellStyle);

				headerCell = headerRow.createCell(29);
				headerCell.setCellValue("관리비");
				headerCell.setCellStyle(headerCellStyle);

				headerCell = headerRow.createCell(30);
				headerCell.setCellValue("장비사용료");
				headerCell.setCellStyle(headerCellStyle);

				headerCell = headerRow.createCell(31);
				headerCell.setCellValue("합계");
				headerCell.setCellStyle(headerCellStyle);

				headerCell = headerRow.createCell(32);
				headerCell.setCellValue("할인금액");
				headerCell.setCellStyle(headerCellStyle);

				headerCell = headerRow.createCell(33);
				headerCell.setCellValue("회원아이디");
				headerCell.setCellStyle(headerCellStyle);

				headerCell = headerRow.createCell(34);
				headerCell.setCellValue("고객요청사항");
				headerCell.setCellStyle(headerCellStyle);

				Row bodyRow = null;
				Cell bodyCell = null;

				
				Map<String, Object> map= (Map<String, Object>) model.get("categoryMap");
				List<SrrsrvtnVO> list = (List<SrrsrvtnVO>) map.get("category");

				for(int i=0;i<list.size();i++){
					SrrsrvtnVO vo = list.get(i);
	
					System.out.println(vo);
	
					bodyRow = sheet.createRow(i+1);
	
					bodyCell = bodyRow.createCell(0);
					bodyCell.setCellValue(vo.getRsrvDtStart());
					bodyCell.setCellStyle(bodyCellStyle);
	
					bodyCell = bodyRow.createCell(1);
					bodyCell.setCellValue(vo.getInDt());
					bodyCell.setCellStyle(bodyCellStyle);
	
					bodyCell = bodyRow.createCell(2);
					bodyCell.setCellValue(vo.getStatus());
					bodyCell.setCellStyle(bodyCellStyle);
	
					bodyCell = bodyRow.createCell(3);
					bodyCell.setCellValue(vo.getHallType());
					bodyCell.setCellStyle(bodyCellStyle);
	
					bodyCell = bodyRow.createCell(4);
					bodyCell.setCellValue(vo.getEvntNm());
					bodyCell.setCellStyle(bodyCellStyle);
	
					bodyCell = bodyRow.createCell(5);
					bodyCell.setCellValue(vo.getEvntDt());
					bodyCell.setCellStyle(bodyCellStyle);
	
					bodyCell = bodyRow.createCell(6);
					bodyCell.setCellValue(vo.getRprsntrNm());
					bodyCell.setCellStyle(bodyCellStyle);
	
					bodyCell = bodyRow.createCell(7);
					bodyCell.setCellValue(vo.getTotalPay() == null ? 0 : vo.getTotalPay());
					//bodyCell.setCellStyle(bodyCellStyle);
					bodyCell.setCellType(bodyCell.CELL_TYPE_NUMERIC);
					bodyCell.setCellStyle(bodyNumCellStyle);

					bodyCell = bodyRow.createCell(8);
					bodyCell.setCellValue(vo.getIsTemp());
					bodyCell.setCellStyle(bodyCellStyle);
	
					bodyCell = bodyRow.createCell(9);
					bodyCell.setCellValue(vo.getIscorrdr());
					bodyCell.setCellStyle(bodyCellStyle);

					bodyCell = bodyRow.createCell(10);
					bodyCell.setCellValue(vo.getEvntDesc());
					bodyCell.setCellStyle(bodyCellStyle);

					bodyCell = bodyRow.createCell(11);
					bodyCell.setCellValue(vo.getOrgNm());
					bodyCell.setCellStyle(bodyCellStyle);

					bodyCell = bodyRow.createCell(12);
					bodyCell.setCellValue(vo.getMngrNm());
					bodyCell.setCellStyle(bodyCellStyle);

					bodyCell = bodyRow.createCell(13);
					bodyCell.setCellValue(vo.getComRgstNo());
					bodyCell.setCellStyle(bodyCellStyle);

					bodyCell = bodyRow.createCell(14);
					bodyCell.setCellValue(vo.getBizType());
					bodyCell.setCellStyle(bodyCellStyle);

					bodyCell = bodyRow.createCell(15);
					bodyCell.setCellValue(vo.getBizType2());
					bodyCell.setCellStyle(bodyCellStyle);

					bodyCell = bodyRow.createCell(16);
					//bodyCell.setCellValue(vo.getTelNo());
					bodyCell.setCellValue("");
					bodyCell.setCellStyle(bodyCellStyle);

					bodyCell = bodyRow.createCell(17);
					bodyCell.setCellValue(vo.getFaxNo());
					bodyCell.setCellStyle(bodyCellStyle);

					bodyCell = bodyRow.createCell(18);
					//bodyCell.setCellValue(vo.getEmail());
					bodyCell.setCellValue("");
					bodyCell.setCellStyle(bodyCellStyle);

					bodyCell = bodyRow.createCell(19);
					bodyCell.setCellValue(vo.getAddress() + " " + vo.getAddressDtl());
					bodyCell.setCellStyle(bodyCellStyle);

					bodyCell = bodyRow.createCell(20);
					bodyCell.setCellValue(vo.getWlsMic() == null ? 0 : vo.getWlsMic());
					bodyCell.setCellStyle(bodyCellStyle);

					bodyCell = bodyRow.createCell(21);
					bodyCell.setCellValue(vo.getwMic() == null ? 0 : vo.getwMic());
					bodyCell.setCellStyle(bodyCellStyle);

					bodyCell = bodyRow.createCell(22);
					bodyCell.setCellValue(vo.getBAmp() == null ? 0 : vo.getBAmp());
					bodyCell.setCellStyle(bodyCellStyle);

					bodyCell = bodyRow.createCell(23);
					bodyCell.setCellValue(vo.getPrjctr() == null ? 0 : vo.getPrjctr());
					bodyCell.setCellStyle(bodyCellStyle);

					bodyCell = bodyRow.createCell(24);
					bodyCell.setCellValue(vo.getTbl() == null ? 0 : vo.getTbl());
					bodyCell.setCellStyle(bodyCellStyle);

					bodyCell = bodyRow.createCell(25);
					bodyCell.setCellValue(vo.getChr() == null ? 0 : vo.getChr());
					bodyCell.setCellStyle(bodyCellStyle);

					bodyCell = bodyRow.createCell(26);
					bodyCell.setCellValue(vo.getGrbgPck() == null ? 0 : vo.getGrbgPck());
					bodyCell.setCellStyle(bodyCellStyle);

					bodyCell = bodyRow.createCell(27);
					bodyCell.setCellValue(vo.getBtchStyl());
					bodyCell.setCellStyle(bodyCellStyle);

					bodyCell = bodyRow.createCell(28);
					bodyCell.setCellValue(vo.getRentPay() == null ? 0 : vo.getRentPay());
					bodyCell.setCellType(bodyCell.CELL_TYPE_NUMERIC);
					bodyCell.setCellStyle(bodyNumCellStyle);

					bodyCell = bodyRow.createCell(29);
					bodyCell.setCellValue(vo.getMngPay() == null ? 0 : vo.getMngPay());
					bodyCell.setCellType(bodyCell.CELL_TYPE_NUMERIC);
					bodyCell.setCellStyle(bodyNumCellStyle);

					bodyCell = bodyRow.createCell(30);
					bodyCell.setCellValue(vo.getEqpmntPay() == null ? 0 : vo.getEqpmntPay());
					bodyCell.setCellType(bodyCell.CELL_TYPE_NUMERIC);
					bodyCell.setCellStyle(bodyNumCellStyle);

					bodyCell = bodyRow.createCell(31);
					bodyCell.setCellValue(vo.getTotalPay() == null ? 0 : vo.getTotalPay());
					bodyCell.setCellType(bodyCell.CELL_TYPE_NUMERIC);
					bodyCell.setCellStyle(bodyNumCellStyle);

					bodyCell = bodyRow.createCell(32);
					bodyCell.setCellValue(vo.getDiscount() == null ? 0 : vo.getDiscount());
					bodyCell.setCellType(bodyCell.CELL_TYPE_NUMERIC);
					bodyCell.setCellStyle(bodyNumCellStyle);

					bodyCell = bodyRow.createCell(33);
					bodyCell.setCellValue(vo.getUserId());
					bodyCell.setCellStyle(bodyCellStyle);

					bodyCell = bodyRow.createCell(34);
					bodyCell.setCellValue(vo.getEtc());
					bodyCell.setCellStyle(bodyCellStyle);




				}
				Date date = new Date();
				SimpleDateFormat dayformat = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
				SimpleDateFormat hourformat = new SimpleDateFormat("hhmmss", Locale.KOREA);
				String day = dayformat.format(date);
				String hour = hourformat.format(date);
				String fileName = "admin_rsrvtn" + "_" + day + "_" + hour + ".xls";         

				response.setHeader("Content-disposition", "attachment;filename="+ fileName);
				response.setContentType("application/vnd.ms-excel");
				response.setLocale(Locale.KOREAN);
				workbook.write(response.getOutputStream());
				workbook.close();
	}
	
}