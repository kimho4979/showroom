/*     */ package egovframework.cms.cmm;
/*     */ 
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ public class EgovWebUtil
/*     */ {
/*     */   public static String clearXSSMinimum(String value)
/*     */   {
/*  20 */     if ((value == null) || (value.trim().equals(""))) {
/*  21 */       return "";
/*     */     }
/*     */ 
/*  24 */     String returnValue = value;
/*     */ 
/*  26 */     returnValue = returnValue.replaceAll("&", "&amp;");
/*  27 */     returnValue = returnValue.replaceAll("<", "&lt;");
/*  28 */     returnValue = returnValue.replaceAll(">", "&gt;");
/*  29 */     returnValue = returnValue.replaceAll("\"", "&#34;");
/*  30 */     returnValue = returnValue.replaceAll("'", "&#39;");
/*  31 */     returnValue = returnValue.replaceAll(".", "&#46;");
/*  32 */     returnValue = returnValue.replaceAll("%2E", "&#46;");
/*  33 */     returnValue = returnValue.replaceAll("%2F", "&#47;");
/*  34 */     return returnValue;
/*     */   }
/*     */ 
/*     */   public static String clearXSSMaximum(String value) {
/*  38 */     String returnValue = value;
/*  39 */     returnValue = clearXSSMinimum(returnValue);
/*     */ 
/*  41 */     returnValue = returnValue.replaceAll("%00", null);
/*     */ 
/*  43 */     returnValue = returnValue.replaceAll("%", "&#37;");
/*     */ 
/*  47 */     returnValue = returnValue.replaceAll("\\.\\./", "");
/*  48 */     returnValue = returnValue.replaceAll("\\.\\.\\\\", "");
/*  49 */     returnValue = returnValue.replaceAll("\\./", "");
/*  50 */     returnValue = returnValue.replaceAll("%2F", "");
/*     */ 
/*  52 */     return returnValue;
/*     */   }
/*     */ 
/*     */   public static String filePathBlackList(String value) {
/*  56 */     String returnValue = value;
/*  57 */     if ((returnValue == null) || (returnValue.trim().equals(""))) {
/*  58 */       return "";
/*     */     }
/*     */ 
/*  61 */     returnValue = returnValue.replaceAll("\\.\\./", "");
/*  62 */     returnValue = returnValue.replaceAll("\\.\\.\\\\", "");
/*     */ 
/*  64 */     return returnValue;
/*     */   }
/*     */ 
/*     */   public static String filePathReplaceAll(String value)
/*     */   {
/*  74 */     String returnValue = value;
/*  75 */     if ((returnValue == null) || (returnValue.trim().equals(""))) {
/*  76 */       return "";
/*     */     }
/*     */ 
/*  79 */     returnValue = returnValue.replaceAll("/", "");
/*  80 */     returnValue = returnValue.replaceAll("\\", "");
/*  81 */     returnValue = returnValue.replaceAll("\\.\\.", "");
/*  82 */     returnValue = returnValue.replaceAll("&", "");
/*     */ 
/*  84 */     return returnValue;
/*     */   }
/*     */ 
/*     */   public static String filePathWhiteList(String value) {
/*  88 */     return value;
/*     */   }
/*     */ 
/*     */   public static boolean isIPAddress(String str) {
/*  92 */     Pattern ipPattern = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
/*     */ 
/*  94 */     return ipPattern.matcher(str).matches();
/*     */   }
/*     */ 
/*     */   public static String removeCRLF(String parameter) {
/*  98 */     return parameter.replaceAll("\r", "").replaceAll("\n", "");
/*     */   }
/*     */ 
/*     */   public static String removeSQLInjectionRisk(String parameter) {
/* 102 */     return parameter.replaceAll("\\p{Space}", "").replaceAll("\\*", "").replaceAll("%", "").replaceAll(";", "").replaceAll("-", "").replaceAll("\\+", "").replaceAll(",", "");
/*     */   }
/*     */ 
/*     */   public static String removeOSCmdRisk(String parameter) {
/* 106 */     return parameter.replaceAll("\\p{Space}", "").replaceAll("\\*", "").replaceAll("|", "").replaceAll(";", "");
/*     */   }
/*     */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.cmm.EgovWebUtil
 * JD-Core Version:    0.6.2
 */