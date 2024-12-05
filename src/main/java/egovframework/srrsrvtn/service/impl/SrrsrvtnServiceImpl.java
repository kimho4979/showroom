package egovframework.srrsrvtn.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import egovframework.cms.cmm.service.Globals;
import egovframework.front.login.service.FrontLoginService;
import egovframework.srrsrvtn.service.SrrsrvtnService;
import egovframework.srrsrvtn.util.MailUtil;
import egovframework.srrsrvtn.vo.SrrsrvtnAttchVO;
import egovframework.srrsrvtn.vo.SrrsrvtnVO;

import org.springframework.util.StringUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class SrrsrvtnServiceImpl implements SrrsrvtnService {
    
    @Autowired
    private SrrsrvtnMapper srrsrvtnMapper;

	
    @Resource(name = "frontLoginService")
	protected FrontLoginService frontLoginService;

    @Override
    public int selectRsrvSeq() throws Exception {
        int seq = srrsrvtnMapper.selectRsrvSeq();
        return seq;
    }
    
    @Override
    public List<Map<String,Object>> getEqpmntRfrnc() throws Exception {
        return srrsrvtnMapper.getEqpmntRfrnc();
    }

    @Override
    public List<Map<String,Object>> getPrcRfrnc() throws Exception {
        return srrsrvtnMapper.getPrcRfrnc();
    }

    @Override
    public List<SrrsrvtnVO> getAdminList(Map<String, Object> data) throws Exception {
        return srrsrvtnMapper.getAdminList(data);
    }

    @Override
    public List<Map<String, Object>> getCmmnCode() throws Exception {
        return srrsrvtnMapper.getCmmnCode();    
    }
    @Override
    public List<SrrsrvtnVO> getSrRsrvtnList(SrrsrvtnVO param) throws Exception {
        return srrsrvtnMapper.getSrRsrvtnList(param);
    }

	@Override
	public List<SrrsrvtnVO> getRsrvYn(SrrsrvtnVO data) throws Exception {
		List<SrrsrvtnVO> resultList = new ArrayList<>();
		List<SrrsrvtnVO> rsrvtnList = new ArrayList<>();
		
		resultList.add(srrsrvtnMapper.getRsrvYn(data));
		rsrvtnList = getSrRsrvtnList(data);
		
		for (SrrsrvtnVO srrsrvtnVO : rsrvtnList) {
			if (!srrsrvtnVO.getStatusCd().equals('R')) {
				resultList.add(srrsrvtnVO);
			}
		}
		
		return resultList;
	}

    @Override
    public SrrsrvtnVO getRsrvSessionInfo(SrrsrvtnVO param) throws Exception {
        return srrsrvtnMapper.getRsrvSessionInfo(param);
    }

	private void mailSend(List<HashMap<String,Object>> params) throws Exception {
		System.out.println("--------------메일발송----------------------");
		StringBuilder sbHallType = new StringBuilder();
		StringBuilder sbEvntDt = new StringBuilder();
		StringBuilder sbRsrvDtStart = new StringBuilder();
		StringBuilder sbEvntNm = new StringBuilder();
        String delimiter = ", ";
		int i = 0;
		for(HashMap<String,Object> param : params){
			String halltype = param.get("hallType").toString();
			String evntdt = param.get("evntDt").toString();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat _formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date dateS = formatter.parse(param.get("rsrvDtStart").toString());
			if(halltype.equals("1"))
				halltype = "무궁화홀";
			if(halltype.equals("2"))
				halltype = "국화홀";
			if(halltype.equals("3"))
				halltype = "장미홀";
			if(evntdt.equals("AM"))
				evntdt = "9시~12시";
			if(evntdt.equals("PM"))
				evntdt = "13시~17시";
			if(evntdt.equals("ALL"))
				evntdt = "9시~17시";
			sbHallType.append(halltype);
			sbEvntDt.append(evntdt);
			sbRsrvDtStart.append(_formatter.format(dateS));
			sbEvntNm.append(param.get("evntNm").toString());
			if(i < params.size()-1){
				sbHallType.append(delimiter);
				sbEvntDt.append(delimiter);
				sbRsrvDtStart.append(delimiter);
				sbEvntNm.append(delimiter);
			}
			i++;
		}
		String content = params.get(0).get("orgNm")+"에서 요청하신 예약신청이 있습니다. <br>" +sbRsrvDtStart.toString() + "<br> " + sbEvntNm.toString() + "<br>" + sbHallType.toString() + "<br> " + sbEvntDt.toString() + "<br>"; 
		content += "전시실 예약 관리 페이지에서 확인해주세요.<br> 감사합니다.";
		new MailUtil(null,"apply").mailSend(content);
	}

    @Override
	public void sendMMS(Map<String,Object> param) throws Exception {
		System.out.println("--------------문자발송----------------------");
		String halltype = param.get("hallType").toString();
		String evntdt = param.get("evntDt").toString();
		if(halltype.equals("1"))
			halltype = "무궁화홀";
		if(halltype.equals("2"))
			halltype = "국화홀";
		if(halltype.equals("3"))
			halltype = "장미홀";
		if(evntdt.equals("AM"))
			evntdt = "9시~12시";
		if(evntdt.equals("PM"))
			evntdt = "13시~17시";
		if(evntdt.equals("ALL"))
			evntdt = "9시~17시";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat _formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dateS = formatter.parse(param.get("rsrvDtStart").toString());
		Date dateE = formatter.parse(param.get("rsrvDtEnd").toString());
		Calendar calendar = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar.setTime(dateS);
		calendar2.setTime(dateS);
		// 7일 전으로 설정
		calendar.add(Calendar.DAY_OF_MONTH, -7);
		calendar2.add(Calendar.DAY_OF_MONTH, -7);
		// 결과 날짜 가져오기
		Date sevenDaysAgo = calendar.getTime();
		Date eightDaysAgo = calendar2.getTime();
		String sevenDaysAgos = _formatter.format(sevenDaysAgo);
		String eightDaysAgos = _formatter.format(eightDaysAgo);

		String fdateS = _formatter.format(dateS);
		String fdateE = _formatter.format(dateE);
		String orgNm = param.get("orgNm").toString();
		String mngrNm = param.get("mngrNm").toString();
		StringBuffer sb = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		sb2.append("화훼사업센터 전시실 임대료 납입 문자<br>");
		sb2.append(mngrNm+" 님 " + sevenDaysAgos + " 까지 납입하셔야됩니다.<br>");
		sb2.append("◎ 신청내역<br>");
		sb2.append(fdateS + " "  + halltype + " " + evntdt + " 현재승인대기 상태이며<br>");
		sb2.append("전시실 예약조회 페이지에서 임대차계약서 확인 후 임대료를 <br>");
		sb2.append(sevenDaysAgos + " 까지 납입하셔야 확정됩니다.<br>");
		sb2.append("<br>");
		sb2.append("<br>");
		sb2.append("◎ 입금처 : 농협은행 1087-17-000048<br>");
		sb2.append("◎ 예금주 : 한국농수산식품유통공사 화훼사업센터<br>");
		sb2.append("※ 예약자명으로 입금 시 별도의 서류를 제출해야하므로, 반드시 “회사명”으로 입금하여주시기 바랍니다.<br>");
		sb2.append("<br>");
		sb2.append("감사합니다.");
		sb.append("화훼사업센터 전시실 예약 신청 확인<br>");
		sb.append(orgNm + " 회사의 " + mngrNm + " 님께서 예약하신 " + halltype + " 룸이 신청되었습니다.<br>");
		sb.append("◎ 신청내역<br>");
		sb.append(fdateS + " " + halltype + " " + evntdt + " 현재승인대기 상태이며<br>");
		sb.append("전시실 예약조회 페이지에서 임대차계약서 확인 후 임대료를 <br>");
		sb.append(sevenDaysAgos + " 까지 납입하셔야 확정됩니다.<br>");
		sb.append("<br>");
		sb.append("<br>");
		sb.append("◎ 입금처 : 농협은행 1087-17-000048<br>");
		sb.append("◎ 예금주 : 한국농수산식품유통공사 화훼사업센터<br>");
		sb.append("※ 예약자명으로 입금 시 별도의 서류를 제출해야하므로, 반드시 “회사명”으로 입금하여주시기 바랍니다.<br>");
		sb.append("<br>");
		sb.append("감사합니다.");
		System.out.println(sb.toString());
		param.put("sendDt", "SYSDATE");
		param.put("body", sb.toString());
		param.put("telNo", param.get("telNo").toString());
		param.put("subject", "화훼사업센터 전시실 예약 신청 확인");
        srrsrvtnMapper.sendMMS(param);
		param.put("sendDt", eightDaysAgos.concat(" 09:00:00") );
		param.put("body", sb2.toString());
		param.put("telNo", param.get("telNo").toString());
		param.put("subject", "화훼사업센터 전시실 임대료 납입 문자");
        param.put("seq", param.get("seq"));
		srrsrvtnMapper.sendMMS(param);

    }

    @Override
    public List<SrrsrvtnVO> getAdminListExcel(Map<String, Object> data) throws Exception {
        return srrsrvtnMapper.getAdminListExcel(data);
    }

    @Override
    public int updateRsrvList(Map<String, Object> data) throws Exception {
        return srrsrvtnMapper.updateRsrvList(data);
    }
    @Override
    public int insertSrrsrvtn(Map<String, Object> data) throws Exception {
        return srrsrvtnMapper.insertSrrsrvtn(data);
    }

    @Override
    public int insertHistory(Map<String, Object> data) throws Exception {
        return  srrsrvtnMapper.insertHistory(data);
    }

    @Override
    public void saveAgree(Map<String, Object> param) throws Exception {
        srrsrvtnMapper.saveAgree(param);
    }

    @Override
    public int checkAgree(Map<String, Object> param) throws Exception {
        return srrsrvtnMapper.checkAgree(param);
    }

    @Override
    public SrrsrvtnAttchVO getLastAttchFile(String param) throws Exception {
        return srrsrvtnMapper.getLastAttchFile(param);
    }
    
    	
	
    // 파일 저장 및 파일 테이블 저장 정보 셋팅
 	public SrrsrvtnAttchVO saveFile(MultipartFile file) throws Exception {
         
 		// 서버 반영 전 주석 체인지 임시 주석
        String filePath = Globals.DISTRIBUTE_UPLOAD_PATH  + toDay(); // 저장할 파일의 경로
 		//String filePath = "C:/uploads/"+ toDay();	// 임시 경로
        double fileSize = file.getSize();
        String fileType = "";
         
        // 경로에 폴더 존재 하지 않는 경우 폴더 생성
        Path uploadPath = Paths.get(filePath);
        if (!Files.exists(uploadPath)) {
             Files.createDirectories(uploadPath);
        }
         
        // 파일 확장자 구하기
        String originalFileName = file.getOriginalFilename();
        if (originalFileName != null) {
        	fileType =  StringUtils.getFilenameExtension(originalFileName);
        }
         
        // 파일명 난수로 처리
        long t = System.currentTimeMillis();
 		int r = (int)(Math.random()*1000000);
        String orgnNm = new String(file.getOriginalFilename().getBytes("8859_1"),"utf-8");
        String fileName = ""+t+r + "."+fileType;
 		
         // 파일 객체 생성
 	    File uploadfile = new File(filePath, fileName); 
 	    
 	    byte[] bytes = file.getBytes();
 	    
 	    // 파일 업로드
 	    try (FileOutputStream fos = new FileOutputStream(uploadfile)) {
 	        fos.write(bytes); // 파일 쓰기 
 	    } catch (IOException e) {
 	        e.printStackTrace();
 	    }
 	    
 	    // 업로드 파일 DB생성
 	    SrrsrvtnAttchVO srrsrvtnAttchVO = new SrrsrvtnAttchVO();
 	    
 	    srrsrvtnAttchVO.setFileNm(fileName);
 	    srrsrvtnAttchVO.setOrgnNm(orgnNm);
 	    srrsrvtnAttchVO.setFileSize(fileSize);
 	    srrsrvtnAttchVO.setFilePath(filePath);
 	    srrsrvtnAttchVO.setFileType(fileType);
 	    
 	    return srrsrvtnAttchVO;
 	}
	
	public String toDay() {
		Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return formatter.format(currentDate);
	}

	@Override
	public HashMap<String, Object> updateStatusList(Map<String, Object> data) throws Exception {

		for (Map.Entry<String, Object> entry : data.entrySet()) {
			int resultCnt = 0;
			Map<String, Object> item = (Map<String, Object>) entry.getValue();

			String seq = (String) item.get("seq");
			String userId = (String) item.get("userId");
			String status = (String) item.get("status");

			Map<String, Object> searchMap = new HashMap<>();
			searchMap.put("seq",seq);
			searchMap.put("userId",userId);
			searchMap.put("status",status);

			SrrsrvtnVO resultVO = srrsrvtnMapper.getAdmin(searchMap);
			String hallType = resultVO.getHallType();
			if("R".equals(resultVO.getStatus())){
				SrrsrvtnVO srrsrvtnVO = srrsrvtnMapper.getRsrvYn(resultVO);

				if (!(("1".equals(hallType) &&  "Y".equals(srrsrvtnVO.getHallType1Yn()))
						|| ("2".equals(hallType) && "Y".equals(srrsrvtnVO.getHallType2Yn()))
						|| ("3".equals(hallType) && "Y".equals(srrsrvtnVO.getHallType3Yn())))) {
					HashMap<String, Object> resultMap = new HashMap<String, Object>();
					resultMap.put("code", "E");
					resultMap.put("msg", "예약이 이미 있습니다.");

					return resultMap;
				}
			}
			searchMap.put("id", userId);
			Map<String,Object> at = frontLoginService.getAtMember(searchMap);
			resultVO.setTelNo(null != at && null != at.get("phone") ? at.get("phone").toString() : "");
			resultVO.setMngrNm(null != at && null != at.get("name") ? at.get("name").toString() : "");
			resultVO.setModiDt(toDay());
			resultVO.setStatus(status);
			Map<String, Object> paramMap = BeanUtils.describe(resultVO);

			if (0 == srrsrvtnMapper.insertHistory(paramMap)) {
				HashMap<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("code", "E");
				resultMap.put("msg", "예약 중 오류가 발생 하였습니다.");
				return resultMap;
			}

			if (0 == srrsrvtnMapper.updateStatus(searchMap)) {
				HashMap<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("code", "E");
				resultMap.put("msg", "예약 중 오류가 발생 하였습니다.");
				return resultMap;
			} else {
				if("H".equals(status)){
					searchMap.put("status", "0");
				} else {
					searchMap.put("status", "1");
				}
				//임시 주석
				if(0 == srrsrvtnMapper.updateMMSStatus(searchMap)){
					HashMap<String, Object> resultMap = new HashMap<String, Object>();
					resultMap.put("code", "E");
					resultMap.put("msg", "예약 중 오류가 발생 하였습니다.");
					return resultMap;
				} else {
					if("A".equals(status)){
						this.sendAcceptMMS(resultVO);
					}
					if("R".equals(status)){
						this.sendRejectMMS(resultVO);
					}
				}
				
				
				
			}

		}
		return null;
	}

	private void sendAcceptMMS(SrrsrvtnVO vo) throws Exception {
		// todo: 승인문자처리
		 
		System.out.println("--------------승인문자발송----------------------");
		String halltype = vo.getHallType();
		String evntdt = vo.getEvntDt();
		if(halltype.equals("1"))
			halltype = "무궁화홀";
		if(halltype.equals("2"))
			halltype = "국화홀";
		if(halltype.equals("3"))
			halltype = "장미홀";
		if(evntdt.equals("AM"))
			evntdt = "9시~12시";
		if(evntdt.equals("PM"))
			evntdt = "13시~17시";
		if(evntdt.equals("ALL"))
			evntdt = "9시~17시";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat _formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dateS = formatter.parse(vo.getRsrvDtStart());
		//Date dateE = forvo.getRsrvDtEnd();
		Calendar calendar = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar.setTime(dateS);
		calendar2.setTime(dateS);
		// 7일 전으로 설정
		calendar.add(Calendar.DAY_OF_MONTH, -7);
		calendar2.add(Calendar.DAY_OF_MONTH, -7);
		// 결과 날짜 가져오기
		Date sevenDaysAgo = calendar.getTime();
		Date eightDaysAgo = calendar2.getTime();
		String sevenDaysAgos = _formatter.format(sevenDaysAgo);
		String eightDaysAgos = _formatter.format(eightDaysAgo);

		String fdateS = _formatter.format(dateS);
		//String fdateE = _formatter.format(dateE);
		String orgNm = vo.getOrgNm();
		String mngrNm = vo.getMngrNm();
		StringBuffer sb = new StringBuffer();
		


		sb.append("◎ 승인내역<br>");
		sb.append(orgNm+ " " + mngrNm + "님께서 예약하신<br> "+ fdateS + " " + halltype + " " + evntdt + " 예약이 승인되었습니다. <br><br>");
		sb.append("승인 내역은 전시실 예약조회 페이지에서 확인하실 수 있습니다.<br><br>감사합니다.");
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("sendDt", "SYSDATE" );
		param.put("body", sb.toString());
		param.put("telNo", vo.getTelNo());
		param.put("subject", "화훼사업센터 전시실 승인 안내");
        param.put("seq", vo.getSeq());
		srrsrvtnMapper.sendMMS(param);
	}

	private void sendRejectMMS(SrrsrvtnVO vo) throws Exception {
		// todo: 반려문자처리
		 
		System.out.println("--------------반려문자발송----------------------");
		String halltype = vo.getHallType();
		String evntdt = vo.getEvntDt();
		if(halltype.equals("1"))
			halltype = "무궁화홀";
		if(halltype.equals("2"))
			halltype = "국화홀";
		if(halltype.equals("3"))
			halltype = "장미홀";
		if(evntdt.equals("AM"))
			evntdt = "9시~12시";
		if(evntdt.equals("PM"))
			evntdt = "13시~17시";
		if(evntdt.equals("ALL"))
			evntdt = "9시~17시";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat _formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dateS = formatter.parse(vo.getRsrvDtStart());
		//Date dateE = forvo.getRsrvDtEnd();
		Calendar calendar = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar.setTime(dateS);
		calendar2.setTime(dateS);
		// 7일 전으로 설정
		calendar.add(Calendar.DAY_OF_MONTH, -7);
		calendar2.add(Calendar.DAY_OF_MONTH, -7);
		// 결과 날짜 가져오기
		Date sevenDaysAgo = calendar.getTime();
		Date eightDaysAgo = calendar2.getTime();
		String sevenDaysAgos = _formatter.format(sevenDaysAgo);
		String eightDaysAgos = _formatter.format(eightDaysAgo);

		String fdateS = _formatter.format(dateS);
		//String fdateE = _formatter.format(dateE);
		String orgNm = vo.getOrgNm();
		String mngrNm = vo.getMngrNm();
		StringBuffer sb = new StringBuffer();
		

//ㅇㅇㅇ(회사명) ㅇㅇㅇ(신청자)님의 2024년 7월 1일 무궁화홀 전일(오전,오후) 예약 신청이 반려되었습니다.
//		sb.append("◎ 반려안내<br>");
		sb.append(orgNm+ " " + mngrNm + "님께서 예약하신<br> "+ fdateS + " " + halltype + " " + evntdt + " 예약 신청이 반려되었습니다. <br><br>");
		sb.append("감사합니다.");
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("sendDt", "SYSDATE" );
		param.put("body", sb.toString());
		param.put("telNo", vo.getTelNo());
		param.put("subject", "화훼사업센터 전시실 예약 신청 반려 안내");
        param.put("seq", vo.getSeq());
		srrsrvtnMapper.sendMMS(param);
	}

	@Override
	public int deleteUserInfo(Map<String, Object> data) throws Exception {
		int resultCnt = 0;

		String seq = (String) data.get("seq");
		String userId = (String) data.get("userId");

		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("seq",seq);
		searchMap.put("userId",userId);

		SrrsrvtnVO resultVO = srrsrvtnMapper.getAdmin(searchMap);
		resultVO.setModiDt(toDay());
		Map<String, Object> paramMap = BeanUtils.describe(resultVO);
		resultCnt += srrsrvtnMapper.insertHistory(paramMap);
		resultCnt += srrsrvtnMapper.deleteUserInfo(paramMap);
		return resultCnt;
	}

	@Override
	public SrrsrvtnVO getAdmin(Map<String, Object> data) throws Exception {
		return srrsrvtnMapper.getAdmin(data);
	}


	@Override
    public HashMap<String, Object> multiRsrvtn(Map<String, Object> data) throws Exception {
        
        String stts = (String)data.get("stts");
        String hallType = (String)data.get("hallType");

        if ("I".equals(stts)) {
            SrrsrvtnVO srrsrvtnVO = new SrrsrvtnVO();
            
            srrsrvtnVO.setRsrvDtStart((String)data.get("rsrvDtStart"));
            srrsrvtnVO.setRsrvDtEnd((String)data.get("rsrvDtEnd"));
            
            srrsrvtnVO = srrsrvtnMapper.getRsrvYn(srrsrvtnVO);
            
            if (("1".equals(hallType) &&  "Y".equals(srrsrvtnVO.getHallType1Yn()))
                || ("2".equals(hallType) && "Y".equals(srrsrvtnVO.getHallType2Yn()))
                || ("3".equals(hallType) && "Y".equals(srrsrvtnVO.getHallType3Yn()))) {
                
                
                int seq = selectRsrvSeq();
                data.put("seq", seq);
                
                if (1 != insertSrrsrvtn(data)) {
                    HashMap<String, Object> resultMap = new HashMap<String, Object>();
                    resultMap.put("code", "E");
                    resultMap.put("msg", "예약 중 오류가 발생 하였습니다.");
                    return resultMap;
                }
                
            } else {
                HashMap<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("code", "E");
                resultMap.put("msg", "예약이 이미 있습니다.");
                
                return resultMap;
            }
        }
        
        if ("U".equals(stts)) {
            data.put("modiDt", toDay());
            
            if (1 != updateRsrvList(data)) {
                
                HashMap<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("code", "E");
                resultMap.put("msg", "예약 중 오류가 발생 하였습니다.");
                return resultMap;
            }
            
        }
        
        if (1 != insertHistory(data)) {
            HashMap<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("code", "E");
            resultMap.put("msg", "예약 중 오류가 발생 하였습니다.");
            return resultMap;
        }
        
        return null;
    }

	@Override
	public HashMap<String, Object> multiRsrvtn(List<HashMap<String, Object>> srrsrvtnList, MultipartFile file, List<MultipartFile> fileList) throws Exception {
		
		
		int sn = 0;
		int grpId = 0;
		int fileSn = 0;
		List<SrrsrvtnAttchVO> srrsrvtnAttchList = new ArrayList<>();
		
		for (HashMap<String, Object> data : srrsrvtnList) {
			
			SrrsrvtnVO resulttnVO = new SrrsrvtnVO();
	        
			resulttnVO.setRsrvDtStart((String)data.get("rsrvDtStart"));
			resulttnVO.setRsrvDtEnd((String)data.get("rsrvDtEnd"));
			resulttnVO.setUserId((String)data.get("userId"));
	        
			resulttnVO = srrsrvtnMapper.getRsrvYn(resulttnVO);
	        
	        String hallType = (String)data.get("hallType"); 
	        
	        if (("1".equals(hallType) &&  "Y".equals(resulttnVO.getHallType1Yn()))
		        	|| ("2".equals(hallType) && "Y".equals(resulttnVO.getHallType2Yn()))
		        	|| ("3".equals(hallType) && "Y".equals(resulttnVO.getHallType3Yn()))) {
		        	
	        	
	        	int seq = selectRsrvSeq();
	        	
	        	if (sn == 0) {
	        		grpId = seq;
					
		        }
	        	
	        	data.put("seq",  seq);
	        	data.put("grpId",  grpId);
	        	System.err.println("SMS 들어갈 데이터 : " + data);
				//임시 주석 - 여러번
				sendMMS(data);

	        	if (1 != insertSrrsrvtn(data)) {
	        		HashMap<String, Object> resultMap = new HashMap<String, Object>();
					resultMap.put("code", "E");
					resultMap.put("msg", "예약 중 오류가 발생 하였습니다.");
					return resultMap;
	        	}
	        	
				if (sn == 0 && file != null) {
					SrrsrvtnAttchVO srrsrvtnAttchVO = saveFile(file);
					srrsrvtnAttchVO.setGbn("CMPNY");
					srrsrvtnAttchVO.setSeq(seq);
					srrsrvtnAttchVO.setUserId((String)data.get("userId"));
					srrsrvtnAttchList.add(srrsrvtnAttchVO);
				}
				if (sn == 0 && file == null) {
					SrrsrvtnAttchVO srrsrvtnAttchVO = getLastAttchFile((String)data.get("userId"));
					srrsrvtnAttchVO.setSeq(seq);
					srrsrvtnAttchList.add(srrsrvtnAttchVO);
				}
				
				if ("C".equals((String)data.get("btchStyl"))) {
					SrrsrvtnAttchVO srrsrvtnAttchVO = saveFile(fileList.get(fileSn));
					srrsrvtnAttchVO.setGbn("BTCH");
					srrsrvtnAttchVO.setSeq(seq);
					srrsrvtnAttchVO.setUserId((String)data.get("userId"));
					srrsrvtnAttchList.add(srrsrvtnAttchVO);
					
					fileSn++;
				}
				
		        if (1 != insertHistory(data)) {
					HashMap<String, Object> resultMap = new HashMap<String, Object>();
					resultMap.put("code", "E");
					resultMap.put("msg", "예약 중 오류가 발생 하였습니다.");
					return resultMap;
				}
		        
	        	
	        } else {
	        	HashMap<String, Object> resultMap = new HashMap<String, Object>();
	        	resultMap.put("code", "E");
	        	resultMap.put("msg", "예약이 이미 있습니다.");
	        	
	        	return resultMap;
	        }
	        
			sn++;
		}
		
		
		for (SrrsrvtnAttchVO srrsrvtnAttchVO : srrsrvtnAttchList) {
    		
    		if (1 != srrsrvtnMapper.insertSrrsrvtnAttch(srrsrvtnAttchVO)) {
    			HashMap<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("code", "E");
				resultMap.put("msg", "예약 중 오류가 발생 하였습니다.");
				return resultMap;
    		}
    	}
		Map<String,Object> updateParam = new HashMap<String,Object>();
		updateParam.put("grpId", grpId);
		updateParam.put("userId", srrsrvtnList.get(0).get("userId"));
		updateParam.put("gbn","CMPNY");
		//srrsrvtnMapper.updateAttch(updateParam); 사용안함

		//임시 주석
		mailSend(srrsrvtnList);
		System.err.println("EMAIL 들어갈 데이터 : " + srrsrvtnList);
		return null;
	}

	@Override
	public List<SrrsrvtnVO> getSrRsrvtnListGrp(SrrsrvtnVO data) throws Exception {
		return srrsrvtnMapper.getSrRsrvtnListGrp(data);
	}
}
