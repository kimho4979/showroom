/*    */ package egovframework.cms.cmm;
/*    */ 
/*    */ import egovframework.rte.fdl.cmmn.exception.handler.ExceptionHandler;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class EgovComExcepHndlr
/*    */   implements ExceptionHandler
/*    */ {
/* 25 */   private static final Logger LOGGER = LoggerFactory.getLogger(EgovComExcepHndlr.class);
/*    */ 
/*    */   public void occur(Exception ex, String packageName)
/*    */   {
/* 32 */     LOGGER.debug("[HANDLER][PACKAGE]::: {}", packageName);
/* 33 */     LOGGER.debug("[HANDLER][Exception]:::{}", ex);
/*    */   }
/*    */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.cmm.EgovComExcepHndlr
 * JD-Core Version:    0.6.2
 */