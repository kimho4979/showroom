/*     */ package egovframework.cms.cmm.service;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.math.BigDecimal;
/*     */ import java.security.SecureRandom;
/*     */ import java.sql.Timestamp;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Locale;
/*     */ 
/*     */ public class EgovStringUtil
/*     */ {
/*     */   public static final String EMPTY = "";
/*     */ 
/*     */   public static String transcriptionInfo(String html)
/*     */   {
/*  67 */     if ((html == null) || ("".equals(html.trim()))) {
/*  68 */       return "";
/*     */     }
/*  70 */     return html.replace("&br&", "<br />").trim();
/*     */   }
/*     */ 
/*     */   public static String stripTag(String html)
/*     */   {
/*  79 */     if ((html == null) || ("".equals(html.trim()))) {
/*  80 */       return "";
/*     */     }
/*     */ 
/*  84 */     return html.replaceAll("<style[\\d\\D]*?>[\\d\\D]*?</style>", "").replaceAll("<script[\\d\\D]*?>[\\d\\D]*?</script>", "").replaceAll("\\<br \\/\\>", "&br&").replaceAll("\\<.*?\\>", "").replaceAll("(\r\n|\n|\r|\n\r|\\t)", " ").replaceAll(" +", " ").trim();
/*     */   }
/*     */ 
/*     */   public static String cutString(String source, String output, int slength)
/*     */   {
/*  95 */     String returnVal = null;
/*  96 */     if (source != null) {
/*  97 */       if (source.length() > slength)
/*  98 */         returnVal = source.substring(0, slength) + output;
/*     */       else
/* 100 */         returnVal = source;
/*     */     }
/* 102 */     return returnVal;
/*     */   }
/*     */ 
/*     */   public static String cutString(String source, int slength)
/*     */   {
/* 112 */     String result = null;
/* 113 */     if (source != null) {
/* 114 */       if (source.length() > slength)
/* 115 */         result = source.substring(0, slength);
/*     */       else
/* 117 */         result = source;
/*     */     }
/* 119 */     return result;
/*     */   }
/*     */ 
/*     */   public static boolean isEmpty(String str)
/*     */   {
/* 139 */     return (str == null) || (str.length() == 0);
/*     */   }
/*     */ 
/*     */   public static String remove(String str, char remove)
/*     */   {
/* 157 */     if ((isEmpty(str)) || (str.indexOf(remove) == -1)) {
/* 158 */       return str;
/*     */     }
/* 160 */     char[] chars = str.toCharArray();
/* 161 */     int pos = 0;
/* 162 */     for (int i = 0; i < chars.length; i++) {
/* 163 */       if (chars[i] != remove) {
/* 164 */         chars[(pos++)] = chars[i];
/*     */       }
/*     */     }
/* 167 */     return new String(chars, 0, pos);
/*     */   }
/*     */ 
/*     */   public static String removeCommaChar(String str)
/*     */   {
/* 184 */     return remove(str, ',');
/*     */   }
/*     */ 
/*     */   public static String removeMinusChar(String str)
/*     */   {
/* 201 */     return remove(str, '-');
/*     */   }
/*     */ 
/*     */   public static String replace(String source, String subject, String object)
/*     */   {
/* 212 */     StringBuffer rtnStr = new StringBuffer();
/* 213 */     String preStr = "";
/* 214 */     String nextStr = source;
/* 215 */     String srcStr = source;
/*     */ 
/* 217 */     while (srcStr.indexOf(subject) >= 0) {
/* 218 */       preStr = srcStr.substring(0, srcStr.indexOf(subject));
/* 219 */       nextStr = srcStr.substring(srcStr.indexOf(subject) + subject.length(), srcStr.length());
/* 220 */       srcStr = nextStr;
/* 221 */       rtnStr.append(preStr).append(object);
/*     */     }
/* 223 */     rtnStr.append(nextStr);
/*     */ 
/* 225 */     return rtnStr.toString();
/*     */   }
/*     */ 
/*     */   public static String replaceOnce(String source, String subject, String object)
/*     */   {
/* 236 */     StringBuffer rtnStr = new StringBuffer();
/* 237 */     String preStr = "";
/* 238 */     String nextStr = source;
/* 239 */     if (source.indexOf(subject) >= 0) {
/* 240 */       preStr = source.substring(0, source.indexOf(subject));
/* 241 */       nextStr = source.substring(source.indexOf(subject) + subject.length(), source.length());
/* 242 */       rtnStr.append(preStr).append(object).append(nextStr);
/*     */ 
/* 244 */       return rtnStr.toString();
/*     */     }
/* 246 */     return source;
/*     */   }
/*     */ 
/*     */   public static String replaceChar(String source, String subject, String object)
/*     */   {
/* 259 */     StringBuffer rtnStr = new StringBuffer();
/* 260 */     String preStr = "";
/* 261 */     String nextStr = source;
/* 262 */     String srcStr = source;
/*     */ 
/* 266 */     for (int i = 0; i < subject.length(); i++) {
/* 267 */       char chA = subject.charAt(i);
/*     */ 
/* 269 */       if (srcStr.indexOf(chA) >= 0) {
/* 270 */         preStr = srcStr.substring(0, srcStr.indexOf(chA));
/* 271 */         nextStr = srcStr.substring(srcStr.indexOf(chA) + 1, srcStr.length());
/* 272 */         srcStr = preStr + object + nextStr;
/*     */       }
/*     */     }
/*     */ 
/* 276 */     return srcStr;
/*     */   }
/*     */ 
/*     */   public static int indexOf(String str, String searchStr)
/*     */   {
/* 299 */     if ((str == null) || (searchStr == null)) {
/* 300 */       return -1;
/*     */     }
/*     */ 
/* 303 */     return str.indexOf(searchStr);
/*     */   }
/*     */ 
/*     */   public static String decode(String sourceStr, String compareStr, String returnStr, String defaultStr)
/*     */   {
/* 330 */     if ((sourceStr == null) && (compareStr == null)) {
/* 331 */       return returnStr;
/*     */     }
/*     */ 
/* 334 */     if ((sourceStr == null) && (compareStr != null)) {
/* 335 */       return defaultStr;
/*     */     }
/*     */ 
/* 338 */     if (sourceStr.trim().equals(compareStr)) {
/* 339 */       return returnStr;
/*     */     }
/*     */ 
/* 342 */     return defaultStr;
/*     */   }
/*     */ 
/*     */   public static String decode(String sourceStr, String compareStr, String returnStr)
/*     */   {
/* 367 */     return decode(sourceStr, compareStr, returnStr, sourceStr);
/*     */   }
/*     */ 
/*     */   public static String isNullToString(Object object)
/*     */   {
/* 376 */     String string = "";
/*     */ 
/* 378 */     if (object != null) {
/* 379 */       string = object.toString().trim();
/*     */     }
/*     */ 
/* 382 */     return string;
/*     */   }
/*     */ 
/*     */   public static String nullConvert(Object src)
/*     */   {
/* 394 */     if ((src != null) && ((src instanceof BigDecimal))) {
/* 395 */       return ((BigDecimal)src).toString();
/*     */     }
/*     */ 
/* 398 */     if ((src == null) || (src.equals("null"))) {
/* 399 */       return "";
/*     */     }
/* 401 */     return ((String)src).trim();
/*     */   }
/*     */ 
/*     */   public static String nullConvert(String src)
/*     */   {
/* 414 */     if ((src == null) || (src.equals("null")) || ("".equals(src)) || (" ".equals(src))) {
/* 415 */       return "";
/*     */     }
/* 417 */     return src.trim();
/*     */   }
/*     */ 
/*     */   public static String nullConvert(String src, String replaceSrc)
/*     */   {
/* 423 */     if ((src == null) || (src.equals("null")) || ("".equals(src)) || (" ".equals(src))) {
/* 424 */       return replaceSrc;
/*     */     }
/* 426 */     return src.trim();
/*     */   }
/*     */ 
/*     */   public static int zeroConvert(Object src)
/*     */   {
/* 439 */     if ((src == null) || (src.equals("null"))) {
/* 440 */       return 0;
/*     */     }
/* 442 */     return Integer.parseInt(((String)src).trim());
/*     */   }
/*     */ 
/*     */   public static int zeroConvert(String src)
/*     */   {
/* 455 */     if ((src == null) || (src.equals("null")) || ("".equals(src)) || (" ".equals(src))) {
/* 456 */       return 0;
/*     */     }
/* 458 */     return Integer.parseInt(src.trim());
/*     */   }
/*     */ 
/*     */   public static String removeWhitespace(String str)
/*     */   {
/* 477 */     if (isEmpty(str)) {
/* 478 */       return str;
/*     */     }
/* 480 */     int sz = str.length();
/* 481 */     char[] chs = new char[sz];
/* 482 */     int count = 0;
/* 483 */     for (int i = 0; i < sz; i++) {
/* 484 */       if (!Character.isWhitespace(str.charAt(i))) {
/* 485 */         chs[(count++)] = str.charAt(i);
/*     */       }
/*     */     }
/* 488 */     if (count == sz) {
/* 489 */       return str;
/*     */     }
/*     */ 
/* 492 */     return new String(chs, 0, count);
/*     */   }
/*     */ 
/*     */   public static String checkHtmlView(String strString)
/*     */   {
/* 502 */     String strNew = "";
/*     */ 
/* 504 */     StringBuffer strTxt = new StringBuffer("");
/*     */ 
/* 507 */     int len = strString.length();
/*     */ 
/* 509 */     for (int i = 0; i < len; i++) {
/* 510 */       char chrBuff = strString.charAt(i);
/*     */ 
/* 512 */       switch (chrBuff) {
/*     */       case '<':
/* 514 */         strTxt.append("&lt;");
/* 515 */         break;
/*     */       case '>':
/* 517 */         strTxt.append("&gt;");
/* 518 */         break;
/*     */       case '"':
/* 520 */         strTxt.append("&quot;");
/* 521 */         break;
/*     */       case '\n':
/* 523 */         strTxt.append("<br>");
/* 524 */         break;
/*     */       case ' ':
/* 526 */         strTxt.append("&nbsp;");
/* 527 */         break;
/*     */       default:
/* 532 */         strTxt.append(chrBuff);
/*     */       }
/*     */     }
/*     */ 
/* 536 */     strNew = strTxt.toString();
/*     */ 
/* 538 */     return strNew;
/*     */   }
/*     */ 
/*     */   public static String[] split(String source, String separator)
/*     */     throws NullPointerException
/*     */   {
/* 548 */     String[] returnVal = null;
/* 549 */     int cnt = 1;
/*     */ 
/* 551 */     int index = source.indexOf(separator);
/* 552 */     int index0 = 0;
/* 553 */     while (index >= 0) {
/* 554 */       cnt++;
/* 555 */       index = source.indexOf(separator, index + 1);
/*     */     }
/* 557 */     returnVal = new String[cnt];
/* 558 */     cnt = 0;
/* 559 */     index = source.indexOf(separator);
/* 560 */     while (index >= 0) {
/* 561 */       returnVal[cnt] = source.substring(index0, index);
/* 562 */       index0 = index + 1;
/* 563 */       index = source.indexOf(separator, index + 1);
/* 564 */       cnt++;
/*     */     }
/* 566 */     returnVal[cnt] = source.substring(index0);
/*     */ 
/* 568 */     return returnVal;
/*     */   }
/*     */ 
/*     */   public static String lowerCase(String str)
/*     */   {
/* 584 */     if (str == null) {
/* 585 */       return null;
/*     */     }
/*     */ 
/* 588 */     return str.toLowerCase();
/*     */   }
/*     */ 
/*     */   public static String upperCase(String str)
/*     */   {
/* 604 */     if (str == null) {
/* 605 */       return null;
/*     */     }
/*     */ 
/* 608 */     return str.toUpperCase();
/*     */   }
/*     */ 
/*     */   public static String stripStart(String str, String stripChars)
/*     */   {
/*     */     int strLen;
/* 631 */     if ((str == null) || ((strLen = str.length()) == 0))
/* 632 */       return str;

/* 634 */     int start = 0;
/* 635 */     if (stripChars == null) {
/*     */       do {
/* 637 */         start++;
/*     */ 
/* 636 */         if (start == strLen) break;  } while (Character.isWhitespace(str.charAt(start)));
/*     */     }
/*     */     else {
/* 639 */       if (stripChars.length() == 0) {
/* 640 */         return str;
/*     */       }
/* 642 */       while ((start != strLen) && (stripChars.indexOf(str.charAt(start)) != -1)) {
/* 643 */         start++;
/*     */       }
/*     */     }
/*     */ 
/* 647 */     return str.substring(start);
/*     */   }
/*     */ 
/*     */   public static String stripEnd(String str, String stripChars)
/*     */   {
/*     */     int end;
/* 670 */     if ((str == null) || ((end = str.length()) == 0))
/* 671 */       return str;

/* 674 */     if (stripChars == null) {
/*     */       do {
/* 676 */         end--;
/*     */ 
/* 675 */         if (end == 0) break;  } while (Character.isWhitespace(str.charAt(end - 1)));
/*     */     }
/*     */     else {
/* 678 */       if (stripChars.length() == 0) {
/* 679 */         return str;
/*     */       }
/* 681 */       while ((end != 0) && (stripChars.indexOf(str.charAt(end - 1)) != -1)) {
/* 682 */         end--;
/*     */       }
/*     */     }
/*     */ 
/* 686 */     return str.substring(0, end);
/*     */   }
/*     */ 
/*     */   public static String strip(String str, String stripChars)
/*     */   {
/* 707 */     if (isEmpty(str)) {
/* 708 */       return str;
/*     */     }
/*     */ 
/* 711 */     String srcStr = str;
/* 712 */     srcStr = stripStart(srcStr, stripChars);
/*     */ 
/* 714 */     return stripEnd(srcStr, stripChars);
/*     */   }
/*     */ 
/*     */   public static String[] split(String source, String separator, int arraylength)
/*     */     throws NullPointerException
/*     */   {
/* 725 */     String[] returnVal = new String[arraylength];
/* 726 */     int cnt = 0;
/* 727 */     int index0 = 0;
/* 728 */     int index = source.indexOf(separator);
/* 729 */     while ((index >= 0) && (cnt < arraylength - 1)) {
/* 730 */       returnVal[cnt] = source.substring(index0, index);
/* 731 */       index0 = index + 1;
/* 732 */       index = source.indexOf(separator, index + 1);
/* 733 */       cnt++;
/*     */     }
/* 735 */     returnVal[cnt] = source.substring(index0);
/* 736 */     if (cnt < arraylength - 1) {
/* 737 */       for (int i = cnt + 1; i < arraylength; i++) {
/* 738 */         returnVal[i] = "";
/*     */       }
/*     */     }
/*     */ 
/* 742 */     return returnVal;
/*     */   }
/*     */ 
/*     */   public static String getRandomStr(char startChr, char endChr)
/*     */   {
/* 757 */     String randomStr = null;
/*     */ 
/* 760 */     int startInt = Integer.valueOf(startChr).intValue();
/* 761 */     int endInt = Integer.valueOf(endChr).intValue();
/*     */ 
/* 764 */     if (startInt > endInt) {
/* 765 */       throw new IllegalArgumentException("Start String: " + startChr + " End String: " + endChr);
/*     */     }
/*     */ 
/* 769 */     SecureRandom rnd = new SecureRandom();
/*     */     int randomInt;
/*     */     do
/*     */     {
/* 773 */       randomInt = rnd.nextInt(endInt + 1);
/*     */     }
/* 771 */     while (
/* 774 */       randomInt < startInt);
/*     */ 

/*     */ 
/* 780 */     return randomStr;
/*     */   }
/*     */ 
/*     */   public static String getEncdDcd(String srcString, String srcCharsetNm, String cnvrCharsetNm)
/*     */   {
/* 798 */     String rtnStr = null;
/*     */ 
/* 800 */     if (srcString == null)
/* 801 */       return null;
/*     */     try
/*     */     {
/* 804 */       rtnStr = new String(srcString.getBytes(srcCharsetNm), cnvrCharsetNm);
/*     */     } catch (UnsupportedEncodingException e) {
/* 806 */       rtnStr = null;
/*     */     }
/*     */ 
/* 809 */     return rtnStr;
/*     */   }
/*     */ 
/*     */   public static String getSpclStrCnvr(String srcString)
/*     */   {
/* 821 */     String rtnStr = null;
/*     */ 
/* 823 */     StringBuffer strTxt = new StringBuffer("");
/*     */ 
/* 826 */     int len = srcString.length();
/*     */ 
/* 828 */     for (int i = 0; i < len; i++) {
/* 829 */       char chrBuff = srcString.charAt(i);
/*     */ 
/* 831 */       switch (chrBuff) {
/*     */       case '<':
/* 833 */         strTxt.append("&lt;");
/* 834 */         break;
/*     */       case '>':
/* 836 */         strTxt.append("&gt;");
/* 837 */         break;
/*     */       case '&':
/* 839 */         strTxt.append("&amp;");
/* 840 */         break;
/*     */       default:
/* 842 */         strTxt.append(chrBuff);
/*     */       }
/*     */     }
/*     */ 
/* 846 */     rtnStr = strTxt.toString();
/*     */ 
/* 848 */     return rtnStr;
/*     */   }
/*     */ 
/*     */   public static String getTimeStamp()
/*     */   {
/* 861 */     String rtnStr = null;
/*     */ 
/* 864 */     String pattern = "yyyyMMddhhmmssSSS";
/*     */ 
/* 866 */     SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
/* 867 */     Timestamp ts = new Timestamp(System.currentTimeMillis());
/*     */ 
/* 869 */     rtnStr = sdfCurrent.format(Long.valueOf(ts.getTime()));
/*     */ 
/* 871 */     return rtnStr;
/*     */   }
/*     */ 
/*     */   public static String getHtmlStrCnvr(String srcString)
/*     */   {
/* 884 */     String tmpString = srcString;
/*     */ 
/* 886 */     tmpString = tmpString.replaceAll("&lt;", "<");
/* 887 */     tmpString = tmpString.replaceAll("&gt;", ">");
/* 888 */     tmpString = tmpString.replaceAll("&amp;", "&");
/* 889 */     tmpString = tmpString.replaceAll("&nbsp;", " ");
/* 890 */     tmpString = tmpString.replaceAll("&apos;", "'");
/* 891 */     tmpString = tmpString.replaceAll("&quot;", "\"");
/*     */ 
/* 893 */     return tmpString;
/*     */   }
/*     */ 
/*     */   public static String addMinusChar(String date)
/*     */   {
/* 908 */     if (date.length() == 8) {
/* 909 */       return date.substring(0, 4).concat("-").concat(date.substring(4, 6)).concat("-").concat(date.substring(6, 8));
/*     */     }
/* 911 */     return "";
/*     */   }
/*     */ 
/*     */   public static String join(Object[] aobj, String s)
/*     */   {
/* 917 */     if (aobj == null) {
/* 918 */       return "";
/*     */     }
/* 920 */     StringBuffer stringbuffer = new StringBuffer();
/* 921 */     int i = aobj.length;
/* 922 */     if (i > 0) {
/* 923 */       stringbuffer.append(aobj[0].toString());
/*     */     }
/* 925 */     for (int j = 1; j < i; j++)
/*     */     {
/* 927 */       stringbuffer.append(s);
/* 928 */       stringbuffer.append(aobj[j].toString());
/*     */     }
/* 930 */     return stringbuffer.toString();
/*     */   }
/*     */ 
/*     */   public static String nl2br(String str)
/*     */   {
/* 935 */     if (str == null) {
/* 936 */       return "";
/*     */     }
/* 938 */     int length = str.length();
/* 939 */     StringBuffer buffer = new StringBuffer();
/* 940 */     for (int i = 0; i < length; i++)
/*     */     {
/* 942 */       String tmp = str.substring(i, i + 1);
/* 943 */       if ("\r".compareTo(tmp) == 0)
/*     */       {
/* 945 */         tmp = str.substring(++i, i + 1);
/* 946 */         if ("\n".compareTo(tmp) == 0)
/* 947 */           buffer.append("<br />\r");
/*     */         else {
/* 949 */           buffer.append("\r");
/*     */         }
/*     */       }
/* 952 */       buffer.append(tmp);
/*     */     }
/* 954 */     return buffer.toString();
/*     */   }
/*     */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.cmm.service.EgovStringUtil
 * JD-Core Version:    0.6.2
 */