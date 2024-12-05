/*     */ package egovframework.cms.cmm.service;
/*     */ 
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class EgovClntInfo
/*     */ {
/*     */   public static String getClntIP(HttpServletRequest request)
/*     */     throws Exception
/*     */   {
/*  16 */     String ipAddr = request.getRemoteAddr();
/*  17 */     return ipAddr;
/*     */   }
/*     */ 
/*     */   public static String getClntOsInfo(HttpServletRequest request)
/*     */     throws Exception
/*     */   {
/*  28 */     String browserDetails = request.getHeader("User-Agent");
/*  29 */     String userAgent = browserDetails;
/*     */ 
/*  32 */     String os = "";
/*  33 */     if (userAgent.toLowerCase().indexOf("windows") >= 0)
/*     */     {
/*  35 */       os = "Windows";
/*  36 */     } else if (userAgent.toLowerCase().indexOf("mac") >= 0)
/*     */     {
/*  38 */       os = "Mac";
/*  39 */     } else if (userAgent.toLowerCase().indexOf("x11") >= 0)
/*     */     {
/*  41 */       os = "Unix";
/*  42 */     } else if (userAgent.toLowerCase().indexOf("android") >= 0)
/*     */     {
/*  44 */       os = "Android";
/*  45 */     } else if (userAgent.toLowerCase().indexOf("iphone") >= 0)
/*     */     {
/*  47 */       os = "IPhone";
/*     */     }
/*  49 */     else os = "UnKnown";
/*     */ 
/*  52 */     return os;
/*     */   }
/*     */ 
/*     */   public static String getClntWebKind(HttpServletRequest request)
/*     */     throws Exception
/*     */   {
/*  64 */     String browserDetails = request.getHeader("User-Agent");
/*  65 */     String userAgent = browserDetails;
/*  66 */     String user = userAgent.toLowerCase();
/*     */ 
/*  68 */     String browser = "";
/*  69 */     if (user.contains("msie"))
/*     */     {
/*  71 */       String substring = userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
/*  72 */       browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
/*  73 */     } else if ((user.contains("safari")) && (user.contains("version")))
/*     */     {
/*  75 */       browser = userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0].split("/")[0] + "-" + userAgent.substring(userAgent.indexOf("Version")).split(" ")[0].split("/")[1];
/*  76 */     } else if ((user.contains("opr")) || (user.contains("opera")))
/*     */     {
/*  78 */       if (user.contains("opera"))
/*  79 */         browser = userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0].split("/")[0] + "-" + userAgent.substring(userAgent.indexOf("Version")).split(" ")[0].split("/")[1];
/*  80 */       else if (user.contains("opr"))
/*  81 */         browser = userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0].replace("/", "-").replace("OPR", "Opera");
/*  82 */     } else if (user.contains("chrome"))
/*     */     {
/*  84 */       browser = userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0].replace("/", "-");
/*  85 */     } else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1) || (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1) || (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1))
/*     */     {
/*  88 */       browser = "Netscape-?";
/*     */     }
/*  90 */     else if (user.contains("firefox"))
/*     */     {
/*  92 */       browser = userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0].replace("/", "-");
/*     */     }
/*  94 */     else if (user.contains("rv:11.0"))
/*     */     {
/*  96 */       String substring = userAgent.substring(userAgent.indexOf("rv")).split("\\)")[0];
/*  97 */       browser = substring.split(":")[0].replace("rv", "IE") + "-" + substring.split(":")[1];
/*     */     }
/* 100 */     else if (user.contains("rv"))
/*     */     {
/* 102 */       browser = "IE";
/*     */     }
/*     */     else {
/* 105 */       browser = "UnKnown, More-Info: " + userAgent;
/*     */     }
/*     */ 
/* 108 */     return browser;
/*     */   }
/*     */ 
/*     */   public static String getClntWebVer(HttpServletRequest request)
/*     */     throws Exception
/*     */   {
/* 119 */     String user_agent = request.getHeader("user-agent");
/*     */ 
/* 122 */     String webVer = "";
/* 123 */     String[] arr = { "MSIE", "OPERA", "NETSCAPE", "FIREFOX", "SAFARI" };
/* 124 */     for (int i = 0; i < arr.length; i++) {
/* 125 */       int s_loc = user_agent.toUpperCase().indexOf(arr[i]);
/* 126 */       if (s_loc != -1) {
/* 127 */         int f_loc = s_loc + arr[i].length();
/* 128 */         webVer = user_agent.toUpperCase().substring(f_loc, f_loc + 5);
/* 129 */         webVer = webVer.replaceAll("/", "").replaceAll(";", "").replaceAll("^", "").replaceAll(",", "").replaceAll("//.", "");
/*     */       }
/*     */     }
/* 132 */     return webVer;
/*     */   }
/*     */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.cmm.service.EgovClntInfo
 * JD-Core Version:    0.6.2
 */