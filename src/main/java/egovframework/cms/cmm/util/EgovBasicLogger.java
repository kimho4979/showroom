/*    */ package egovframework.cms.cmm.util;
/*    */ 
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ 
/*    */ public class EgovBasicLogger
/*    */ {
/* 23 */   private static final Level IGNORE_INFO_LEVEL = Level.OFF;
/* 24 */   private static final Level DEBUG_INFO_LEVEL = Level.FINEST;
/* 25 */   private static final Level INFO_INFO_LEVEL = Level.INFO;
/*    */ 
/* 27 */   private static final Logger ignoreLogger = Logger.getLogger("ignore");
/* 28 */   private static final Logger debugLogger = Logger.getLogger("debug");
/* 29 */   private static final Logger infoLogger = Logger.getLogger("info");
/*    */ 
/*    */   public static void ignore(String message, Exception exception)
/*    */   {
/* 37 */     if (exception == null)
/* 38 */       ignoreLogger.log(IGNORE_INFO_LEVEL, message);
/*    */     else
/* 40 */       ignoreLogger.log(IGNORE_INFO_LEVEL, message, exception);
/*    */   }
/*    */ 
/*    */   public static void ignore(String message)
/*    */   {
/* 50 */     ignore(message, null);
/*    */   }
/*    */ 
/*    */   public static void debug(String message, Exception exception)
/*    */   {
/* 59 */     if (exception == null)
/* 60 */       debugLogger.log(DEBUG_INFO_LEVEL, message);
/*    */     else
/* 62 */       debugLogger.log(DEBUG_INFO_LEVEL, message, exception);
/*    */   }
/*    */ 
/*    */   public static void debug(String message)
/*    */   {
/* 72 */     debug(message, null);
/*    */   }
/*    */ 
/*    */   public static void info(String message)
/*    */   {
/* 81 */     infoLogger.log(INFO_INFO_LEVEL, message);
/*    */   }
/*    */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.cmm.util.EgovBasicLogger
 * JD-Core Version:    0.6.2
 */