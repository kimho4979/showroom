/*    */ package egovframework.cms.cmm.util;
/*    */ 
/*    */ import java.io.UnsupportedEncodingException;
/*    */ import java.net.URLEncoder;
/*    */ import java.util.Map;
/*    */ import java.util.Map.Entry;
/*    */ 
/*    */ public class MapQuery
/*    */ {
/*    */   public static String urlEncodeUTF8(String s)
/*    */   {
/*    */     try
/*    */     {
/* 10 */       return URLEncoder.encode(s, "UTF-8");
/*    */     } catch (UnsupportedEncodingException e) {
/* 12 */       throw new UnsupportedOperationException(e);
/*    */     }
/*    */   }
/*    */ 
/* 16 */   public static String urlEncodeUTF8(Map<?, ?> map) { StringBuilder sb = new StringBuilder();
/* 17 */     for (Map.Entry entry : map.entrySet()) {
/* 18 */       if (entry.getValue() != null)
/*    */       {
/* 21 */         if (sb.length() > 0) {
/* 22 */           sb.append("&");
/*    */         }
/* 24 */         sb.append(String.format("%s=%s", new Object[] { 
/* 25 */           urlEncodeUTF8(entry.getKey().toString()), 
/* 26 */           urlEncodeUTF8(entry.getValue().toString()) }));
/*    */       }
/*    */     }
/* 29 */     return sb.toString();
/*    */   }
/*    */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.cmm.util.MapQuery
 * JD-Core Version:    0.6.2
 */