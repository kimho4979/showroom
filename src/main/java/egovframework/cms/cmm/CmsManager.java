/*     */ package egovframework.cms.cmm;
/*     */ 
/*     */ import egovframework.cms.cmm.service.EgovStringUtil;
/*     */ import egovframework.cms.cmm.util.EgovUserDetailsHelper;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ 
/*     */ public class CmsManager
/*     */ {
/*     */   public static String getModulePath()
/*     */   {
/*  12 */     return "/WEB-INF/jsp";
/*     */   }
/*     */ 
/*     */   public static String alert(String contentFile, ModelMap model)
/*     */   {
/*  17 */     model.addAttribute("contentFile", contentFile);
/*     */ 
/*  19 */     return "egovframework/wms/common/alert_view";
/*     */   }
/*     */ 
/*     */   public static ModelAndView alertMV(String contentFile, ModelMap model)
/*     */   {
/*  24 */     ModelAndView mv = new ModelAndView("egovframework/wms/common/alert_view");
/*  25 */     mv.addObject("contentFile", contentFile);
/*  26 */     mv.addAllObjects(model);
/*     */ 
/*  28 */     return mv;
/*     */   }
/*     */ 
/*     */   public static boolean ipCheck(String remoteAddr, String ips)
/*     */   {
/*  34 */     if ("".equals(EgovStringUtil.nullConvert(ips))) {
/*  35 */       return true;
/*     */     }
/*     */ 
/*  38 */     boolean isAccessIp = false;
/*     */ 
/*  40 */     String[] ipsArr = ips.split("\\s*\\r?\\n\\s*");
/*  41 */     if ((ipsArr != null) && (ipsArr.length > 0)) {
/*  42 */       for (int i = 0; i < ipsArr.length; i++) {
/*  43 */         if (remoteAddr.equals(ipsArr[i])) {
/*  44 */           isAccessIp = true;
/*  45 */           break;
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*  50 */     return isAccessIp;
/*     */   }
/*     */ 
/*     */   public static String checkAuthority(int levelId, String groupIds) throws Exception
/*     */   {
/*  55 */     int auth = getAuthority(levelId, groupIds);
/*     */ 
/*  57 */     if (auth < 0)
/*     */     {
/*  59 */       if (auth == -1)
/*  60 */         return "common.only.guest";
/*  61 */       if (auth == -99)
/*  62 */         return "common.notuse";
/*  63 */       if (auth == -2)
/*  64 */         return "common.only.user";
/*  65 */       if (auth == -3)
/*  66 */         return "common.level.permission";
/*  67 */       if (auth == -4) {
/*  68 */         return "common.group.permission";
/*     */       }
/*     */     }
/*     */ 
/*  72 */     return "";
/*     */   }
/*     */ 
/*     */   public static int getAuthority(int levelId, String groupIds) throws Exception
/*     */   {
/*  77 */     LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
/*     */ 
/*  79 */     if ((EgovUserDetailsHelper.isAuthenticated().booleanValue()) && (EgovUserDetailsHelper.isMasterAdmin().booleanValue())) {
/*  80 */       return 0;
/*     */     }
/*     */ 
/*  83 */     if (levelId == -1)
/*     */     {
/*  85 */       return -1;
/*     */     }
/*  87 */     if (levelId == -99)
/*     */     {
/*  89 */       return -99;
/*     */     }
/*  91 */     if (levelId > 0)
/*     */     {
/*  93 */       if (!EgovUserDetailsHelper.isAuthenticated().booleanValue())
/*     */       {
/*  95 */         return -2;
/*     */       }
/*  97 */       if (loginVO.getLevelId() < levelId)
/*     */       {
/*  99 */         return -3;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 104 */     groupIds = EgovStringUtil.nullConvert(groupIds);
/* 105 */     if (!"".equals(groupIds))
/*     */     {
/* 107 */       String[] groupIdArr = EgovStringUtil.nullConvert(loginVO.getGroupIds()).split(",");
/* 108 */       for (String groupId : groupIdArr)
/*     */       {
/* 110 */         if (groupIds.indexOf(groupId) > -1)
/*     */         {
/* 112 */           return -4;
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 117 */     return 0;
/*     */   }
/*     */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.cmm.CmsManager
 * JD-Core Version:    0.6.2
 */