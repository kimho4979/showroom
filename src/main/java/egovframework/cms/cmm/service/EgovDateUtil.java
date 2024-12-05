/*     */ package egovframework.cms.cmm.service;
/*     */ 

/*     */ import java.security.SecureRandom;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.HashMap;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.TimeZone;
/*     */ 
/*     */ public class EgovDateUtil
/*     */ {
/*     */   public static String addYearMonthDay(String sDate, int year, int month, int day)
/*     */   {
/*  62 */     String dateStr = validChkDate(sDate);
/*     */ 
/*  64 */     Calendar cal = Calendar.getInstance();
/*  65 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
/*     */     try {
/*  67 */       cal.setTime(sdf.parse(dateStr));
/*     */     } catch (ParseException e) {
/*  69 */       throw new IllegalArgumentException("Invalid date format: " + dateStr);
/*     */     }
/*     */ 
/*  72 */     if (year != 0) {
/*  73 */       cal.add(1, year);
/*     */     }
/*  75 */     if (month != 0) {
/*  76 */       cal.add(2, month);
/*     */     }
/*  78 */     if (day != 0) {
/*  79 */       cal.add(5, day);
/*     */     }
/*     */ 
/*  82 */     return sdf.format(cal.getTime());
/*     */   }
/*     */ 
/*     */   public static String addYear(String dateStr, int year)
/*     */   {
/* 104 */     return addYearMonthDay(dateStr, year, 0, 0);
/*     */   }
/*     */ 
/*     */   public static String addMonth(String dateStr, int month)
/*     */   {
/* 127 */     return addYearMonthDay(dateStr, 0, month, 0);
/*     */   }
/*     */ 
/*     */   public static String addDay(String dateStr, int day)
/*     */   {
/* 153 */     return addYearMonthDay(dateStr, 0, 0, day);
/*     */   }
/*     */ 
/*     */   public static int getDaysDiff(String sDate1, String sDate2)
/*     */   {
/* 177 */     String dateStr1 = validChkDate(sDate1);
/* 178 */     String dateStr2 = validChkDate(sDate2);
/*     */ 
/* 180 */     if ((!checkDate(sDate1)) || (!checkDate(sDate2))) {
/* 181 */       throw new IllegalArgumentException("Invalid date format: args[0]=" + sDate1 + " args[1]=" + sDate2);
/*     */     }
/* 183 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
/*     */ 
/* 185 */     Date date1 = null;
/* 186 */     Date date2 = null;
/*     */     try {
/* 188 */       date1 = sdf.parse(dateStr1);
/* 189 */       date2 = sdf.parse(dateStr2);
/*     */     } catch (ParseException e) {
/* 191 */       throw new IllegalArgumentException("Invalid date format: args[0]=" + dateStr1 + " args[1]=" + dateStr2);
/*     */     }
/*     */ 
/* 194 */     if ((date1 != null) && (date2 != null)) {
/* 195 */       int days1 = (int)(date1.getTime() / 3600000L / 24L);
/* 196 */       int days2 = (int)(date2.getTime() / 3600000L / 24L);
/* 197 */       return days2 - days1;
/*     */     }
/* 199 */     return 0;
/*     */   }
/*     */ 
/*     */   public static boolean checkDate(String sDate)
/*     */   {
/* 221 */     String dateStr = validChkDate(sDate);
/*     */ 
/* 223 */     String year = dateStr.substring(0, 4);
/* 224 */     String month = dateStr.substring(4, 6);
/* 225 */     String day = dateStr.substring(6);
/*     */ 
/* 227 */     return checkDate(year, month, day);
/*     */   }
/*     */ 
/*     */   public static boolean checkDate(String year, String month, String day)
/*     */   {
/*     */     try
/*     */     {
/* 240 */       SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
/*     */ 
/* 242 */       Date result = formatter.parse(year + "." + month + "." + day);
/* 243 */       String resultStr = formatter.format(result);
/* 244 */       if (resultStr.equalsIgnoreCase(year + "." + month + "." + day)) {
/* 245 */         return true;
/*     */       }
/* 247 */       return false; } catch (ParseException e) {
/*     */     }
/* 249 */     return false;
/*     */   }
/*     */ 
/*     */   public static String convertDate(String strSource, String fromDateFormat, String toDateFormat, String strTimeZone)
/*     */   {
/* 263 */     SimpleDateFormat simpledateformat = null;
/* 264 */     Date date = null;
/* 265 */     String fromFormat = "";
/* 266 */     String toFormat = "";
/*     */ 
/* 268 */     if (EgovStringUtil.isNullToString(strSource).trim().equals("")) {
/* 269 */       return "";
/*     */     }
/* 271 */     if (EgovStringUtil.isNullToString(fromDateFormat).trim().equals(""))
/* 272 */       fromFormat = "yyyyMMddHHmmss";
/* 273 */     if (EgovStringUtil.isNullToString(toDateFormat).trim().equals(""))
/* 274 */       toFormat = "yyyy-MM-dd HH:mm:ss";
/*     */     try
/*     */     {
/* 277 */       simpledateformat = new SimpleDateFormat(fromFormat, Locale.getDefault());
/* 278 */       date = simpledateformat.parse(strSource);
/* 279 */       if (!EgovStringUtil.isNullToString(strTimeZone).trim().equals("")) {
/* 280 */         simpledateformat.setTimeZone(TimeZone.getTimeZone(strTimeZone));
/*     */       }
/* 282 */       simpledateformat = new SimpleDateFormat(toFormat, Locale.getDefault());
/*     */     } catch (ParseException exception) {
/* 284 */       throw new RuntimeException(exception);
/*     */     }
/*     */ 
/* 287 */     return simpledateformat.format(date);
/*     */   }
/*     */ 
/*     */   public static String formatDate(String sDate, String ch)
/*     */   {
/* 304 */     String dateStr = validChkDate(sDate);
/*     */ 
/* 306 */     String str = dateStr.trim();
/* 307 */     String yyyy = "";
/* 308 */     String mm = "";
/* 309 */     String dd = "";
/*     */ 
/* 311 */     if (str.length() == 8) {
/* 312 */       yyyy = str.substring(0, 4);
/* 313 */       if (yyyy.equals("0000")) {
/* 314 */         return "";
/*     */       }
/*     */ 
/* 317 */       mm = str.substring(4, 6);
/* 318 */       if (mm.equals("00")) {
/* 319 */         return yyyy;
/*     */       }
/*     */ 
/* 322 */       dd = str.substring(6, 8);
/* 323 */       if (dd.equals("00")) {
/* 324 */         return yyyy + ch + mm;
/*     */       }
/*     */ 
/* 327 */       return yyyy + ch + mm + ch + dd;
/*     */     }
/* 329 */     if (str.length() == 6) {
/* 330 */       yyyy = str.substring(0, 4);
/* 331 */       if (yyyy.equals("0000")) {
/* 332 */         return "";
/*     */       }
/*     */ 
/* 335 */       mm = str.substring(4, 6);
/* 336 */       if (mm.equals("00")) {
/* 337 */         return yyyy;
/*     */       }
/*     */ 
/* 340 */       return yyyy + ch + mm;
/*     */     }
/* 342 */     if (str.length() == 4) {
/* 343 */       yyyy = str.substring(0, 4);
/* 344 */       if (yyyy.equals("0000")) {
/* 345 */         return "";
/*     */       }
/* 347 */       return yyyy;
/*     */     }
/*     */ 
/* 350 */     return "";
/*     */   }
/*     */ 
/*     */   public static String formatTime(String sTime, String ch)
/*     */   {
/* 365 */     String timeStr = validChkTime(sTime);
/* 366 */     return timeStr.substring(0, 2) + ch + timeStr.substring(2, 4) + ch + timeStr.substring(4, 6);
/*     */   }
/*     */ 
/*     */   public String leapYear(int year)
/*     */   {
/* 376 */     if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
/* 377 */       return "29";
/*     */     }
/*     */ 
/* 380 */     return "28";
/*     */   }
/*     */ 
/*     */   public static boolean isLeapYear(int year)
/*     */   {
/* 396 */     if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
/* 397 */       return false;
/*     */     }
/* 399 */     return true;
/*     */   }
/*     */ 
/*     */   public static String getToday()
/*     */   {
/* 408 */     return getCurrentDate("");
/*     */   }
/*     */ 
/*     */   public static String getCurrentDate(String dateType)
/*     */   {
/* 417 */     Calendar aCalendar = Calendar.getInstance();
/*     */ 
/* 419 */     int year = aCalendar.get(1);
/* 420 */     int month = aCalendar.get(2) + 1;
/* 421 */     int date = aCalendar.get(5);
/* 422 */     String strDate = Integer.toString(year) + (
/* 423 */       month < 10 ? "0" + Integer.toString(month) : Integer.toString(month)) + (
/* 424 */       date < 10 ? "0" + Integer.toString(date) : Integer.toString(date));
/*     */ 
/* 426 */     if (!"".equals(dateType)) {
/* 427 */       strDate = convertDate(strDate, "yyyyMMdd", dateType);
/*     */     }
/*     */ 
/* 430 */     return strDate;
/*     */   }
/*     */ 
/*     */   public static String getCurrentDateFull() {
/* 434 */     return getCurrentDateFull("");
/*     */   }
/*     */ 
/*     */   public static String getCurrentDateFull(String dateType) {
/* 438 */     Calendar cal = Calendar.getInstance();
/* 439 */     if ("".equals(dateType)) {
/* 440 */       dateType = "yyyy:MM:dd hh:mm:ss";
/*     */     }
/* 442 */     SimpleDateFormat sdf1 = new SimpleDateFormat(dateType);
/* 443 */     return sdf1.format(cal.getTime());
/*     */   }
/*     */ 
/*     */   public static String convertDate(String sDate, String sTime, String sFormatStr)
/*     */   {
/* 485 */     String dateStr = validChkDate(sDate);
/* 486 */     String timeStr = validChkTime(sTime);
/*     */ 
/* 488 */     Calendar cal = null;
/* 489 */     cal = Calendar.getInstance();
/*     */ 
/* 491 */     cal.set(1, Integer.parseInt(dateStr.substring(0, 4)));
/* 492 */     cal.set(2, Integer.parseInt(dateStr.substring(4, 6)) - 1);
/* 493 */     cal.set(5, Integer.parseInt(dateStr.substring(6, 8)));
/* 494 */     cal.set(11, Integer.parseInt(timeStr.substring(0, 2)));
/* 495 */     cal.set(12, Integer.parseInt(timeStr.substring(2, 4)));
/*     */ 
/* 497 */     SimpleDateFormat sdf = new SimpleDateFormat(sFormatStr, Locale.ENGLISH);
/*     */ 
/* 499 */     return sdf.format(cal.getTime());
/*     */   }
/*     */ 
/*     */   public static String getRandomDate(String sDate1, String sDate2)
/*     */   {
/* 509 */     String dateStr1 = validChkDate(sDate1);
/* 510 */     String dateStr2 = validChkDate(sDate2);
/*     */ 
/* 512 */     String randomDate = null;
/*     */ 
/* 517 */     int sYear = Integer.parseInt(dateStr1.substring(0, 4));
/* 518 */     int sMonth = Integer.parseInt(dateStr1.substring(4, 6));
/* 519 */     int sDay = Integer.parseInt(dateStr1.substring(6, 8));
/*     */ 
/* 521 */     int eYear = Integer.parseInt(dateStr2.substring(0, 4));
/* 522 */     int eMonth = Integer.parseInt(dateStr2.substring(4, 6));
/* 523 */     int eDay = Integer.parseInt(dateStr2.substring(6, 8));
/*     */ 
/* 525 */     GregorianCalendar beginDate = new GregorianCalendar(sYear, sMonth - 1, sDay, 0, 0);
/* 526 */     GregorianCalendar endDate = new GregorianCalendar(eYear, eMonth - 1, eDay, 23, 59);
/*     */ 
/* 528 */     if (endDate.getTimeInMillis() < beginDate.getTimeInMillis()) {
/* 529 */       throw new IllegalArgumentException("Invalid input date : " + sDate1 + "~" + sDate2);
/*     */     }
/*     */ 
/* 532 */     SecureRandom r = new SecureRandom();
/*     */ 
/* 534 */     r.setSeed(new Date().getTime());
/*     */ 
/* 536 */     long rand = (r.nextLong() >>> 1) % (endDate.getTimeInMillis() - beginDate.getTimeInMillis() + 1L) + beginDate.getTimeInMillis();
/*     */ 
/* 538 */     GregorianCalendar cal = new GregorianCalendar();
/*     */ 
/* 540 */     SimpleDateFormat calformat = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
/* 541 */     cal.setTimeInMillis(rand);
/* 542 */     randomDate = calformat.format(cal.getTime());
/*     */ 
/* 545 */     return randomDate;
/*     */   }
/*     */ 
/*     */   public static Map<String, String> toLunar(String sDate)
/*     */   {
/* 554 */     String dateStr = validChkDate(sDate);
/*     */ 
/* 556 */     Map hm = new HashMap();
/* 557 */     hm.put("day", "");
/* 558 */     hm.put("leap", "0");
/*     */ 
/* 560 */     if (dateStr.length() != 8) {
/* 561 */       return hm;
/*     */     }
/*     */ 
/* 567 */     Calendar cal = Calendar.getInstance();

/*     */ 
/* 570 */     cal.set(1, Integer.parseInt(dateStr.substring(0, 4)));
/* 571 */     cal.set(2, Integer.parseInt(dateStr.substring(4, 6)) - 1);
/* 572 */     cal.set(5, Integer.parseInt(dateStr.substring(6, 8)));
/*     */ 

/*     */ 
/* 593 */     return hm;
/*     */   }
/*     */ 
/*     */   public static String toSolar(String sDate, int iLeapMonth)
/*     */   {
/* 603 */     String dateStr = validChkDate(sDate);
/*     */ 
/* 608 */     Calendar cal = Calendar.getInstance();

/* 629 */     return null;
/*     */   }
/*     */ 
/*     */   public static String convertWeek(String sWeek)
/*     */   {
/* 638 */     String retStr = null;
/*     */ 
/* 640 */     if (sWeek.equals("SUN"))
/* 641 */       retStr = "일요일";
/* 642 */     else if (sWeek.equals("MON"))
/* 643 */       retStr = "월요일";
/* 644 */     else if (sWeek.equals("TUE"))
/* 645 */       retStr = "화요일";
/* 646 */     else if (sWeek.equals("WED"))
/* 647 */       retStr = "수요일";
/* 648 */     else if (sWeek.equals("THR"))
/* 649 */       retStr = "목요일";
/* 650 */     else if (sWeek.equals("FRI"))
/* 651 */       retStr = "금요일";
/* 652 */     else if (sWeek.equals("SAT")) {
/* 653 */       retStr = "토요일";
/*     */     }
/*     */ 
/* 656 */     return retStr;
/*     */   }
/*     */ 
/*     */   public static boolean validDate(String sDate)
/*     */   {
/* 665 */     String dateStr = validChkDate(sDate);
/*     */ 
/* 668 */     boolean ret = false;
/*     */ 
/* 670 */     Calendar cal = Calendar.getInstance();
/*     */ 
/* 672 */     cal.set(1, Integer.parseInt(dateStr.substring(0, 4)));
/* 673 */     cal.set(2, Integer.parseInt(dateStr.substring(4, 6)) - 1);
/* 674 */     cal.set(5, Integer.parseInt(dateStr.substring(6, 8)));
/*     */ 
/* 676 */     String year = String.valueOf(cal.get(1));
/* 677 */     String month = String.valueOf(cal.get(2) + 1);
/* 678 */     String day = String.valueOf(cal.get(5));
/*     */ 
/* 680 */     String pad4Str = "0000";
/* 681 */     String pad2Str = "00";
/*     */ 
/* 683 */     String retYear = (pad4Str + year).substring(year.length());
/* 684 */     String retMonth = (pad2Str + month).substring(month.length());
/* 685 */     String retDay = (pad2Str + day).substring(day.length());
/*     */ 
/* 687 */     String retYMD = retYear + retMonth + retDay;
/*     */ 
/* 689 */     if (sDate.equals(retYMD)) {
/* 690 */       ret = true;
/*     */     }
/*     */ 
/* 693 */     return ret;
/*     */   }
/*     */ 
/*     */   public static boolean validDate(String sDate, int sWeek)
/*     */   {
/* 703 */     String dateStr = validChkDate(sDate);
/*     */ 
/* 706 */     boolean ret = false;
/*     */ 
/* 708 */     Calendar cal = Calendar.getInstance();
/*     */ 
/* 710 */     cal.set(1, Integer.parseInt(dateStr.substring(0, 4)));
/* 711 */     cal.set(2, Integer.parseInt(dateStr.substring(4, 6)) - 1);
/* 712 */     cal.set(5, Integer.parseInt(dateStr.substring(6, 8)));
/*     */ 
/* 714 */     int Week = cal.get(7);
/*     */ 
/* 716 */     if ((validDate(sDate)) && 
/* 717 */       (sWeek == Week)) {
/* 718 */       ret = true;
/*     */     }
/*     */ 
/* 722 */     return ret;
/*     */   }
/*     */ 
/*     */   public static boolean validTime(String sTime)
/*     */   {
/* 731 */     String timeStr = validChkTime(sTime);
/*     */ 
/* 734 */     boolean ret = false;
/*     */ 
/* 736 */     Calendar cal = Calendar.getInstance();
/*     */ 
/* 738 */     cal.set(11, Integer.parseInt(timeStr.substring(0, 2)));
/* 739 */     cal.set(12, Integer.parseInt(timeStr.substring(2, 4)));
/*     */ 
/* 741 */     String HH = String.valueOf(cal.get(11));
/* 742 */     String MM = String.valueOf(cal.get(12));
/*     */ 
/* 744 */     String pad2Str = "00";
/*     */ 
/* 746 */     String retHH = (pad2Str + HH).substring(HH.length());
/* 747 */     String retMM = (pad2Str + MM).substring(MM.length());
/*     */ 
/* 749 */     String retTime = retHH + retMM;
/*     */ 
/* 751 */     if (sTime.equals(retTime)) {
/* 752 */       ret = true;
/*     */     }
/*     */ 
/* 755 */     return ret;
/*     */   }
/*     */ 
/*     */   public static String addYMDtoWeek(String sDate, int year, int month, int day)
/*     */   {
/* 767 */     String dateStr = validChkDate(sDate);
/*     */ 
/* 769 */     dateStr = addYearMonthDay(dateStr, year, month, day);
/*     */ 
/* 771 */     Calendar cal = Calendar.getInstance();
/* 772 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
/*     */     try {
/* 774 */       cal.setTime(sdf.parse(dateStr));
/*     */     } catch (ParseException e) {
/* 776 */       throw new IllegalArgumentException("Invalid date format: " + dateStr);
/*     */     }
/*     */ 
/* 779 */     SimpleDateFormat rsdf = new SimpleDateFormat("E", Locale.ENGLISH);
/*     */ 
/* 781 */     return rsdf.format(cal.getTime());
/*     */   }
/*     */ 
/*     */   public static String addYMDtoDayTime(String sDate, String sTime, int year, int month, int day, int hour, int minute, String formatStr)
/*     */   {
/* 797 */     String dateStr = validChkDate(sDate);
/* 798 */     String timeStr = validChkTime(sTime);
/*     */ 
/* 800 */     dateStr = addYearMonthDay(dateStr, year, month, day);
/*     */ 
/* 802 */     dateStr = convertDate(dateStr, timeStr, "yyyyMMddHHmm");
/*     */ 
/* 804 */     Calendar cal = Calendar.getInstance();
/* 805 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm", Locale.ENGLISH);
/*     */     try
/*     */     {
/* 808 */       cal.setTime(sdf.parse(dateStr));
/*     */     } catch (ParseException e) {
/* 810 */       throw new IllegalArgumentException("Invalid date format: " + dateStr);
/*     */     }
/*     */ 
/* 813 */     if (hour != 0) {
/* 814 */       cal.add(10, hour);
/*     */     }
/*     */ 
/* 817 */     if (minute != 0) {
/* 818 */       cal.add(12, minute);
/*     */     }
/*     */ 
/* 821 */     SimpleDateFormat rsdf = new SimpleDateFormat(formatStr, Locale.ENGLISH);
/*     */ 
/* 823 */     return rsdf.format(cal.getTime());
/*     */   }
/*     */ 
/*     */   public static int datetoInt(String sDate)
/*     */   {
/* 832 */     return Integer.parseInt(convertDate(sDate, "0000", "yyyyMMdd"));
/*     */   }
/*     */ 
/*     */   public static int timetoInt(String sTime)
/*     */   {
/* 841 */     return Integer.parseInt(convertDate("00000101", sTime, "HHmm"));
/*     */   }
/*     */ 
/*     */   public static String validChkDate(String dateStr)
/*     */   {
/* 850 */     if ((dateStr == null) || ((dateStr.trim().length() != 8) && (dateStr.trim().length() != 10))) {
/* 851 */       throw new IllegalArgumentException("Invalid date format: " + dateStr);
/*     */     }
/*     */ 
/* 854 */     if (dateStr.length() == 10) {
/* 855 */       return EgovStringUtil.removeMinusChar(dateStr);
/*     */     }
/*     */ 
/* 858 */     return dateStr;
/*     */   }
/*     */ 
/*     */   public static String validChkTime(String timeStr)
/*     */   {
/* 867 */     if ((timeStr == null) || (timeStr.trim().length() != 4)) {
/* 868 */       throw new IllegalArgumentException("Invalid time format: " + timeStr);
/*     */     }
/*     */ 
/* 871 */     if (timeStr.length() == 5) {
/* 872 */       timeStr = EgovStringUtil.remove(timeStr, ':');
/*     */     }
/*     */ 
/* 875 */     return timeStr;
/*     */   }
/*     */ 
/*     */   public static int getNumberByPattern(String pattern)
/*     */   {
/* 880 */     SimpleDateFormat formatter = 
/* 881 */       new SimpleDateFormat(pattern, Locale.KOREA);
/* 882 */     String dateString = formatter.format(new Date());
/* 883 */     return Integer.parseInt(dateString);
/*     */   }
/*     */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.cmm.service.EgovDateUtil
 * JD-Core Version:    0.6.2
 */