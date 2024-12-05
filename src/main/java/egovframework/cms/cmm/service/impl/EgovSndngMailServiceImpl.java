/*    */ package egovframework.cms.cmm.service.impl;
/*    */ 
/*    */ import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
/*    */ import egovframework.cms.cmm.SndngMailVO;
/*    */ import egovframework.cms.cmm.service.EgovSndngMailService;

/*    */ import java.io.PrintStream;


/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.mail.MailAuthenticationException;
/*    */ import org.springframework.mail.MailParseException;
/*    */ import org.springframework.mail.MailSendException;
/*    */ import org.springframework.mail.javamail.JavaMailSender;
/*    */ import org.springframework.mail.javamail.MimeMessageHelper;
/*    */ import org.springframework.stereotype.Service;



/*    */ 

/*    */ public class EgovSndngMailServiceImpl extends EgovAbstractServiceImpl
/*    */   implements EgovSndngMailService
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private JavaMailSender mailSender;
/* 42 */   private static final Logger LOGGER = LoggerFactory.getLogger(EgovSndngMailServiceImpl.class);
/*    */ 
/*    */   public boolean sndngMail(SndngMailVO sndngMailVO)
/*    */     throws Exception
/*    */   {
/* 53 */     String recptnPerson = sndngMailVO.getRecptnPerson() == null ? "" : sndngMailVO.getRecptnPerson();
/* 54 */     String subject = sndngMailVO.getSj() == null ? "" : sndngMailVO.getSj();
/* 55 */     String emailCn = sndngMailVO.getEmailCn() == null ? "" : sndngMailVO.getEmailCn();
/*    */     try
/*    */     {
               

/* 59 */       MimeMessageHelper messageHelper = null;
/*    */ 
/* 61 */      
/*    */     } catch (MailParseException ex) {
/* 66 */       LOGGER.error("Sending Mail Exception : {} [failure when parsing the message]", ex.getCause());
/* 67 */       return false;
/*    */     } catch (MailAuthenticationException ex) {
/* 69 */       LOGGER.error("Sending Mail Exception : {} [authentication failure]", ex.getCause());
/* 70 */       return false;
/*    */     } catch (MailSendException ex) {
/* 72 */       ex.fillInStackTrace();
/* 73 */       System.out.println(ex.getMessage());
/* 74 */       LOGGER.error("Sending Mail Exception : {} [failure when sending the message]", ex.getCause());
/* 75 */       return false;
/*    */     } catch (Exception ex) {
/* 77 */       LOGGER.debug(ex.getMessage());
/* 78 */       return false;
/*    */     }
/*    */ 
/* 81 */     return true;
/*    */   }
/*    */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.cmm.service.impl.EgovSndngMailServiceImpl
 * JD-Core Version:    0.6.2
 */