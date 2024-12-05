package egovframework.srrsrvtn.util;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
/**
    *  전시실 예약 시스템 - 메일 발송 유틸리티
    *
    *  <p>
    *      메일 발송 유틸리티
    *  </p>
    *
    * @version : 0.0.1
    * @author : 채동호
    * @param : String content
    * @return : Map<String,Object> result (msg)
    * @throws : Exception
    * @see : 
    */
// 메일발송 
// 화훼서버가 아니면 현재 테스트불가
// 신중히 작성
// Map<String,Object> resultMap = new MailUtil().mailSend("본문내용 HTML형식으로 ");
public class MailUtil {
    private List<String> filenames;
    private String flag;
    public MailUtil(List<String> filenames, String flag){ 
        this.filenames = filenames;
        this.flag = flag;
    }

    public Map<String,Object> mailSend(String content){
        Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", "211.251.247.124");
		props.put("mail.smtp.port", "25");
        props.put("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.quitwait", "false");

        Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
            //	return new PasswordAuthentication("goeyu0213@gmail.com", "xsflmlvhuuxcnspk");
				return new javax.mail.PasswordAuthentication("atflower@at.or.kr", "atmail23$%");
			}
		});
        session.setDebug(true);
		MimeMessage mimeMessage = new MimeMessage(session);
        Map<String,Object> result = new HashMap<>();
        try {
            
            System.out.println(" ======메일 시작====== ");
            mimeMessage.setSentDate(new Date());// 보내는 날짜 지정
			mimeMessage.setFrom(new InternetAddress("atflower@at.or.kr", "관리자", "utf-8"));
            if(this.flag.equals("setting")){
                InternetAddress[] receiverAddresses = {
                    new InternetAddress("lhw@at.or.kr"),
                    new InternetAddress("orbi@at.or.kr"),
                    //new InternetAddress("jhlee5332@at.or.kr"),
                    //new InternetAddress("192538@aplegw.kr")
                    new InternetAddress("aml@aplegw.kr")
                };
                mimeMessage.setRecipients(Message.RecipientType.TO, receiverAddresses);
            } else {
                InternetAddress[] receiverAddresses = {
                    new InternetAddress("lhw@at.or.kr"),
                    new InternetAddress("orbi@at.or.kr"),
                    new InternetAddress("jhlee5332@at.or.kr")
                    
                };
                mimeMessage.setRecipients(Message.RecipientType.TO, receiverAddresses);
            }
            
            Multipart mp = new MimeMultipart(); //멀티파트 객체 생성
            // 메일 제목
            if(this.flag.equals("setting")){
                mimeMessage.setSubject("[화훼사업센터 전시실 예약 내역 안내]", "UTF-8");
            } else {
                mimeMessage.setSubject("[화훼사업센터 전시실 예약 신청 알림]", "UTF-8");
            }
            
            MimeBodyPart mbp1 = new MimeBodyPart();
            mbp1.setContent(content, "text/html; charset=utf-8"); //메일 내용 셋팅
			mp.addBodyPart(mbp1); // 파일송신시 보이는 텍스트 파일 멀티파트 담는다.
            
            if(this.filenames != null && this.filenames.size() > 0){
             
                for(String filename : this.filenames){
                    MimeBodyPart mbp2 = new MimeBodyPart();
                    System.out.println("첨부파일 : " + filename);
                    FileDataSource fds = new FileDataSource(filename.split("::")[0]);
                    
                    mbp2.setDataHandler(new DataHandler(fds));
                    mbp2.attachFile(filename.split("::")[0]);
                    mbp2.setFileName(MimeUtility.encodeText(filename.split("::")[1],"euc-kr","B"));
                    //mimeMessage.setFileName(filename.split("::")[1]);
                    mp.addBodyPart(mbp2);
                }
            }
            
			mimeMessage.setContent(mp);
			System.out.println(" ======메일 끝====== ");
			//메일 발송
            Transport.send(mimeMessage);
            
            result.put("msg", "success");
            return result;
        } catch (Exception e) {
            result.put("msg", e.getMessage());
            result.put("msg1", e.getLocalizedMessage());
            return result;
        }
    }
} 