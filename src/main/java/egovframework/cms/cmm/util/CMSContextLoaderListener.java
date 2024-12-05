/*    */ package egovframework.cms.cmm.util;
/*    */ 
/*    */ import javax.servlet.ServletContextEvent;
/*    */ import javax.servlet.ServletContextListener;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.web.context.ContextLoader;
/*    */ 
/*    */ public class CMSContextLoaderListener
/*    */   implements ServletContextListener
/*    */ {
/*    */   private ContextLoader contextLoader;
/* 13 */   private Logger LOGGER = LoggerFactory.getLogger(CMSContextLoaderListener.class);
/*    */ 
/*    */   public void contextInitialized(ServletContextEvent event)
/*    */   {
/*    */     try {
/* 18 */       this.contextLoader = new ContextLoader();
/*    */     } catch (Exception e) {
/* 20 */       this.LOGGER.debug("CMS License Error", e);
/* 21 */       e.printStackTrace();
/* 22 */       System.exit(0);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void contextDestroyed(ServletContextEvent event)
/*    */   {
/* 29 */     if (this.contextLoader != null)
/* 30 */       this.contextLoader.closeWebApplicationContext(event.getServletContext());
/*    */   }
/*    */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.cmm.util.CMSContextLoaderListener
 * JD-Core Version:    0.6.2
 */