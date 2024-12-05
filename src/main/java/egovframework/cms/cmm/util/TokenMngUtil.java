/*    */ package egovframework.cms.cmm.util;
/*    */ 
/*    */ import egovframework.rte.fdl.idgnr.impl.Base64;
/*    */ import java.security.MessageDigest;
/*    */ import java.security.NoSuchAlgorithmException;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpSession;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class TokenMngUtil
/*    */ {
/*    */   private static final String TOKEN_KEY = "TOKEN_KEY";
/* 34 */   private static final Logger logger = Logger.getLogger(TokenMngUtil.class.getName());
/*    */ 
/*    */   public static void saveToken(HttpServletRequest request)
/*    */   {
/* 42 */     HttpSession session = request.getSession(true);
/* 43 */     long systemTime = System.currentTimeMillis();
/* 44 */     byte[] time = new Long(systemTime).toString().getBytes();
/* 45 */     byte[] id = session.getId().getBytes();
/*    */     try
/*    */     {
/* 48 */       MessageDigest SHA = MessageDigest.getInstance("SHA-256");
/* 49 */       SHA.update(id);
/* 50 */       SHA.update(time);
/*    */ 
/* 52 */       String token = Base64.encode(SHA.digest());
/* 53 */       request.setAttribute("TOKEN_KEY", token);
/* 54 */       session.setAttribute("TOKEN_KEY", token);
/*    */     }
/*    */     catch (NoSuchAlgorithmException e) {
/* 57 */       logger.error("ERROR:키 생성 에러");
/*    */     }
/*    */   }
/*    */ 
/*    */   public static void resetToken(HttpServletRequest request)
/*    */   {
/* 67 */     HttpSession session = request.getSession(true);
/*    */ 
/* 69 */     session.removeAttribute("TOKEN_KEY");
/*    */   }
/*    */ 
/*    */   public static boolean isTokenValid(HttpServletRequest request)
/*    */   {
/* 80 */     HttpSession session = request.getSession(true);
/* 81 */     String requestToken = request.getParameter("TOKEN_KEY");
/* 82 */     String sessionToken = (String)session.getAttribute("TOKEN_KEY");
/*    */ 
/* 84 */     if ((requestToken == null) || (sessionToken == null)) {
/* 85 */       return false;
/*    */     }
/* 87 */     return requestToken.equals(sessionToken);
/*    */   }
/*    */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.cmm.util.TokenMngUtil
 * JD-Core Version:    0.6.2
 */