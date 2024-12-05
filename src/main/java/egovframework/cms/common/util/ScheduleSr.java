package egovframework.cms.common.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.srrsrvtn.service.impl.SrrsrvtnMapper;
import egovframework.srrsrvtn.util.MailUtil;
import egovframework.srrsrvtn.vo.SrrsrvtnVO;
/****
 * 2. 발송시간 : 매일 오전 09:00
3. 발송대상 : 금일 ~ 금일 +7일 / 승인건 + 대기건 (메일 내용에 상태가 포함되어야 함)
4. 
 - 예약일자, 홀, 예약시간, 행사명, 단체명
 - 복도선택여부, 냉난방여부, 장비사용료기준표 정보, 배치형태(배치형태에 파일이 있으면, 매핑처리)
 * 
 * 
 */

public class ScheduleSr {
    private SrrsrvtnMapper srrsrvtnMapper;
    public static void main(String[] args) throws Exception { 
        XmlWebApplicationContext wcontext = new XmlWebApplicationContext();
        wcontext.setConfigLocation("/WEB-INF/config/springmvc/dispatcher-servlet.xml");
        ApplicationContext context = new ClassPathXmlApplicationContext("egovframework/spring/context-datasource.xml","egovframework/spring/context-mapper.xml");
        
        ScheduleSr sr = new ScheduleSr();
        sr.srrsrvtnMapper = context.getBean(SrrsrvtnMapper.class);
        sr.sendToApl();
    }


    //@Scheduled(cron = "*/5 * * * * *") // 개발 테스트용
    //@Scheduled(cron = "0 0 15 * * *")
    public void sendToApl() throws Exception {
        System.out.println("스케쥴러 작동 in ScheduleSr.class");
        ////Thread.sleep(1000);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat _formatter = new SimpleDateFormat("yyyy-MM-dd");
		//Date dateS = formatter.parse(param.get("rsrvDtStart").toString());
		//Date dateE = formatter.parse(param.get("rsrvDtEnd").toString());
		//Calendar calendar = Calendar.getInstance();
        ObjectMapper mapper = new ObjectMapper();
        List<SrrsrvtnVO> resultList = srrsrvtnMapper.getAdminList(null);
        List<EgovMap> _resultList = (mapper.convertValue(resultList, List.class));
        StringBuilder sb = new StringBuilder();
        List<String> filenames = new ArrayList<>();
        int avacnt = 0;
        sb.append("전시실 예약 대기 및 승인건 목록입니다.<br><br>");
        sb.append("<table style=\"border:1px solid;border-collapse:collapse;\"><tbody style=\"border:1px solid;\">");
        sb.append("<tr>"); 
        sb.append("<th style=\"border:1px solid;padding:10px 10px 10px 10px;\">예약일자</th>");
        sb.append("<th style=\"border:1px solid;padding:10px 10px 10px 10px;\">홀</th>");
        sb.append("<th style=\"border:1px solid;padding:10px 10px 10px 10px;\">예약시간</th>");
        sb.append("<th style=\"border:1px solid;padding:10px 10px 10px 10px;\">행사명</th>");
        sb.append("<th style=\"border:1px solid;padding:10px 10px 10px 10px;\">상태</th>");
        sb.append("<th style=\"border:1px solid;padding:10px 10px 10px 10px;\">단체명</th>");
        sb.append("<th style=\"border:1px solid;padding:10px 10px 10px 10px;\">복도선택여부</th>");
        sb.append("<th style=\"border:1px solid;padding:10px 10px 10px 10px;\">냉난방여부</th>");
        sb.append("<th style=\"border:1px solid;padding:10px 10px 10px 10px;\">배치형태</th>");
        sb.append("<th style=\"border:1px solid;padding:10px 10px 10px 10px;\">무선마이크</th>");
        sb.append("<th style=\"border:1px solid;padding:10px 10px 10px 10px;\">유선마이크</th>");
        sb.append("<th style=\"border:1px solid;padding:10px 10px 10px 10px;\">방송앰프</th>");
        sb.append("<th style=\"border:1px solid;padding:10px 10px 10px 10px;\">프로젝터</th>");
        sb.append("<th style=\"border:1px solid;padding:10px 10px 10px 10px;\">탁자</th>");
        sb.append("<th style=\"border:1px solid;padding:10px 10px 10px 10px;\">의자</th>");
        sb.append("<th style=\"border:1px solid;padding:10px 10px 10px 10px;\">쓰레기봉투</th>");
        sb.append("</tr>");
        for (EgovMap map : _resultList) {
            /////System.out.println(map);
            for (Object key : map.keySet()) {
                if(key.toString().equals("rsrvDtStart")){
                    Date dateS = new Date();
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(dateS);
                    calendar.set(Calendar.HOUR_OF_DAY, 0);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);
                    calendar.set(Calendar.MILLISECOND, 0);
                    dateS = calendar.getTime();
                    Date rsrv = formatter.parse(map.get(key).toString());
                    calendar = Calendar.getInstance();
                    calendar.setTime(dateS);
                    calendar.set(Calendar.HOUR_OF_DAY, 0);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);
                    calendar.set(Calendar.MILLISECOND, 0);
                    calendar.add(Calendar.DATE, 7);
                    Date dateE = calendar.getTime();
                    System.out.println("당일날짜 : "+(dateS));
                    System.out.println("일주일후 : "+(dateE));
                    System.out.println("예약날짜 : "+(rsrv));
                    System.out.println("비교값(당일):"+dateS.compareTo(rsrv));
                    System.out.println("비교값(일주일후): "+dateE.compareTo(rsrv));
                    if (dateS.compareTo(rsrv) <= 0 && dateE.compareTo(rsrv) >= 0) {
                        System.out.println(map.get("statusCd"));
                        if((map.get("statusCd").toString().equals("H") || map.get("statusCd").toString().equals("A"))){
                            //BTCH_STYL_FILE
                            if(map.get("btchStylFile") != null){
                                filenames.add(map.get("btchStylFile").toString().concat("::" + _formatter.format(rsrv) +"_"+ map.get("hallType").toString() +"_"+ map.get("evntDt").toString() +"."+map.get("fileType").toString())) ;
                            }
                            avacnt++;
                            sb.append("<tr>");
                            sb.append("<td style=\"border:1px solid;padding:10px 10px 10px 10px;\">"+_formatter.format(rsrv)+"</td>");                            
                            sb.append("<td style=\"border:1px solid;padding:10px 10px 10px 10px;\">"+map.get("hallType")+"</td>");                            
                            sb.append("<td style=\"border:1px solid;padding:10px 10px 10px 10px;\">"+map.get("evntDt")+"</td>");                            
                            sb.append("<td style=\"border:1px solid;padding:10px 10px 10px 10px;\">"+map.get("evntNm")+"</td>");                            
                            sb.append("<td style=\"border:1px solid;padding:10px 10px 10px 10px;\">"+map.get("status")+"</td>");                            
                            sb.append("<td style=\"border:1px solid;padding:10px 10px 10px 10px;\">"+map.get("orgNm")+"</td>");
                            sb.append("<td style=\"border:1px solid;padding:10px 10px 10px 10px;\">"+(map.get("iscorrdr").toString().equals("Y") ? "포함" : "미포함")+"</td>");
                            sb.append("<td style=\"border:1px solid;padding:10px 10px 10px 10px;\">"+(map.get("isTemp").toString().equals("Y") ? "포함" : "미포함")+"</td>");
                            sb.append("<td style=\"border:1px solid;padding:10px 10px 10px 10px;\">"+(map.get("btchStylNm"))+"</td>");
                            sb.append("<td style=\"border:1px solid;padding:10px 10px 10px 10px;\">"+(map.get("wlsMic") == null ? "-" : map.get("wlsMic")+"개")+"</td>");
                            sb.append("<td style=\"border:1px solid;padding:10px 10px 10px 10px;\">"+(map.get("wMic") == null ? "-" : map.get("wMic")+"개")+"</td>");
                            sb.append("<td style=\"border:1px solid;padding:10px 10px 10px 10px;\">"+(map.get("bAmp") == null ? "-" : map.get("bAmp")+"개")+"</td>");
                            sb.append("<td style=\"border:1px solid;padding:10px 10px 10px 10px;\">"+(map.get("prjctr") == null ? "-" : map.get("prjctr")+"개")+"</td>");
                            sb.append("<td style=\"border:1px solid;padding:10px 10px 10px 10px;\">"+(map.get("tbl") == null ? "-" : map.get("tbl")+"개")+"</td>");
                            sb.append("<td style=\"border:1px solid;padding:10px 10px 10px 10px;\">"+(map.get("chr") == null ? "-" : map.get("chr")+"개")+"</td>");
                            sb.append("<td style=\"border:1px solid;padding:10px 10px 10px 10px;\">"+(map.get("grbgPck") == null ? "-" : map.get("grbgPck")+"개")+"</td>");
                            sb.append("</tr>");                            
                        }        
                    }
                
                }
                
            }
        }
        
        sb.append("</tbody></table>");
        System.out.println(sb.toString());
        //if(avacnt > 0)new MailUtil(filenames,"setting").mailSend(sb.toString());
    }
}
