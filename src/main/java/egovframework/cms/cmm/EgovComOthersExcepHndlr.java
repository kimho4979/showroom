/*    */ package egovframework.cms.cmm;
/*    */ 
/*    */ import egovframework.rte.fdl.cmmn.exception.handler.ExceptionHandler;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class EgovComOthersExcepHndlr
/*    */   implements ExceptionHandler
/*    */ {
/* 11 */   private static final Logger LOGGER = LoggerFactory.getLogger(EgovComOthersExcepHndlr.class);
/*    */ 
/*    */   public void occur(Exception exception, String packageName)
/*    */   {
/* 15 */     LOGGER.error(packageName, exception);
/*    */   }
/*    */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.cmm.EgovComOthersExcepHndlr
 * JD-Core Version:    0.6.2
 */