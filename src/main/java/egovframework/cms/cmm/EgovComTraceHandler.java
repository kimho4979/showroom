/*    */ package egovframework.cms.cmm;
/*    */ 
/*    */ import egovframework.rte.fdl.cmmn.trace.handler.TraceHandler;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class EgovComTraceHandler
/*    */   implements TraceHandler
/*    */ {
/* 23 */   private static final Logger LOGGER = LoggerFactory.getLogger(EgovComTraceHandler.class);
/*    */ 
/*    */   public void todo(Class<?> clazz, String message)
/*    */   {
/* 30 */     LOGGER.debug("[TRACE]CLASS::: {}", clazz.getName());
/* 31 */     LOGGER.debug("[TRACE]MESSAGE::: {}", message);
/*    */   }
/*    */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.cmm.EgovComTraceHandler
 * JD-Core Version:    0.6.2
 */