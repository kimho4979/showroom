/*    */ package egovframework.cms.cmm.util;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ public class MaskingUtil
/*    */ {
/*    */   public static String maskedEmail(String email)
/*    */   {
/* 11 */     String regex = "\\b(\\S+)+@(\\S+.\\S+)";
/* 12 */     Matcher matcher = Pattern.compile(regex).matcher(email);
/* 13 */     if (matcher.find())
/*    */     {
/* 15 */       String id = matcher.group(1);
/*    */ 
/* 21 */       int length = id.length();
/* 22 */       if (length < 3)
/*    */       {
/* 24 */         char[] c = new char[length];
/* 25 */         Arrays.fill(c, '*');
/* 26 */         return email.replace(id, String.valueOf(c));
/*    */       }
/* 28 */       if (length == 3) {
/* 29 */         return email.replaceAll("\\b(\\S+)[^@][^@]+@(\\S+)", "$1**@$2");
/*    */       }
/* 31 */       return email.replaceAll("\\b(\\S+)[^@][^@][^@]+@(\\S+)", "$1***@$2");
/*    */     }
/* 33 */     return email;
/*    */   }
/*    */ 
/*    */   public static String maskedPhoneNum(String phoneNum)
/*    */   {
/* 38 */     String regex = "(01[016789])(\\d{3,4})\\d{4}$";
/* 39 */     Matcher matcher = Pattern.compile(regex).matcher(phoneNum);
/* 40 */     if (matcher.find())
/*    */     {
/* 42 */       String replaceTarget = matcher.group(2);
/* 43 */       char[] c = new char[replaceTarget.length()];
/* 44 */       Arrays.fill(c, '*');
/* 45 */       return phoneNum.replace(replaceTarget, String.valueOf(c));
/*    */     }
/* 47 */     return phoneNum;
/*    */   }
/*    */ 
/*    */   public static String maskedName(String name)
/*    */   {
/* 52 */     String regex = "\\b(\\S+)+@(\\S+.\\S+)";
/* 53 */     Matcher matcher = Pattern.compile(regex).matcher(name);
/* 54 */     if (matcher.find()) {
/* 55 */       name = matcher.group(1);
/*    */     }
/* 57 */     int length = name.length();
/* 58 */     int length2 = 0;
/* 59 */     if (length == 2)
/* 60 */       length2 = length - 1;
/*    */     else {
/* 62 */       length2 = length - 2;
/*    */     }
/* 64 */     String maskId = name.substring(0, length2);
/* 65 */     if (length == 2)
/* 66 */       maskId = maskId + "*";
/*    */     else {
/* 68 */       maskId = maskId + "**";
/*    */     }
/* 70 */     return maskId;
/*    */   }
/*    */ 
/*    */   public static String maskedUserId(String id)
/*    */   {
/* 75 */     int length = id.length();
/* 76 */     length -= 2;
/*    */ 
/* 78 */     String maskId = id.substring(0, length);
/* 79 */     maskId = maskId + "**";
/*    */ 
/* 81 */     return maskId;
/*    */   }
/*    */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.cmm.util.MaskingUtil
 * JD-Core Version:    0.6.2
 */