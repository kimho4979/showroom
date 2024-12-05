/*    */ package egovframework.cms.cmm;
/*    */ 
/*    */ import java.util.Locale;
/*    */ import org.springframework.context.MessageSource;
/*    */ import org.springframework.context.support.ReloadableResourceBundleMessageSource;
/*    */ 
/*    */ public class EgovMessageSource extends ReloadableResourceBundleMessageSource
/*    */   implements MessageSource
/*    */ {
/*    */   private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
/*    */ 
/*    */   public void setReloadableResourceBundleMessageSource(ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource)
/*    */   {
/* 35 */     this.reloadableResourceBundleMessageSource = reloadableResourceBundleMessageSource;
/*    */   }
/*    */ 
/*    */   public ReloadableResourceBundleMessageSource getReloadableResourceBundleMessageSource()
/*    */   {
/* 43 */     return this.reloadableResourceBundleMessageSource;
/*    */   }
/*    */ 
/*    */   public String getMessage(String code)
/*    */   {
/* 52 */     return getReloadableResourceBundleMessageSource().getMessage(code, null, Locale.getDefault());
/*    */   }
/*    */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.cmm.EgovMessageSource
 * JD-Core Version:    0.6.2
 */