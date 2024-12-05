/*    */ package egovframework.cms.cmm.util;
/*    */ 

/*    */ import egovframework.cms.cmm.LoginVO;
/*    */ import egovframework.cms.cmm.RealnameVO;
/*    */ import egovframework.cms.cmm.SnsLoginVO;
/*    */ import org.springframework.web.context.request.RequestAttributes;
/*    */ import org.springframework.web.context.request.RequestContextHolder;
/*    */ 
/*    */ public class EgovUserDetailsHelper
/*    */ {
/*    */   public static Object getAuthenticatedUser()
/*    */   {
/* 37 */     return (LoginVO)RequestContextHolder.getRequestAttributes().getAttribute("loginVO", 1) == null ? 
/* 38 */       new LoginVO() : (LoginVO)RequestContextHolder.getRequestAttributes().getAttribute("loginVO", 1);
/*    */   }
/*    */ 
/*    */   public static Boolean isAuthenticated()
/*    */   {
/* 48 */     
/* 52 */     return Boolean.TRUE;
/*    */   }
/*    */ 
/*    */   public static Boolean isMasterAdmin()
/*    */   {
/* 57 */     if (!isAuthenticated().booleanValue()) {
/* 58 */       return Boolean.FALSE;
/*    */     }
/*    */ 
/* 61 */     LoginVO loginVO = (LoginVO)getAuthenticatedUser();
/* 62 */     if (loginVO.getLevelId() == 99) {
/* 63 */       return Boolean.TRUE;
/*    */     }
/*    */ 
/* 66 */     return Boolean.FALSE;
/*    */   }
/*    */ 
/*    */   public static Boolean isRealname()
/*    */   {
/* 71 */     
/* 75 */     return Boolean.TRUE;
/*    */   }
/*    */ 
/*    */   public static Boolean isSns()
/*    */   {
/* 80 */     
/* 84 */     return Boolean.TRUE;
/*    */   }
/*    */ 
/*    */   public static Object getRealnameUser() {
/* 88 */     return (RealnameVO)RequestContextHolder.getRequestAttributes().getAttribute("realnameVO", 1) == null ? 
/* 89 */       new RealnameVO() : (RealnameVO)RequestContextHolder.getRequestAttributes().getAttribute("realnameVO", 1);
/*    */   }
/*    */ 
/*    */   public static Object getSnsUser() {
/* 93 */     return (SnsLoginVO)RequestContextHolder.getRequestAttributes().getAttribute("snsLoginVO", 1) == null ? 
/* 94 */       new SnsLoginVO() : (SnsLoginVO)RequestContextHolder.getRequestAttributes().getAttribute("snsLoginVO", 1);
/*    */   }
/*    */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.cmm.util.EgovUserDetailsHelper
 * JD-Core Version:    0.6.2
 */