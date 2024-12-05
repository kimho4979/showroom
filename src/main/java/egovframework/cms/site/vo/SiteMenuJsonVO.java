/*     */ package egovframework.cms.site.vo;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class SiteMenuJsonVO
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String siteId;
/*     */   private int menuId;
/*     */   private int parntsMenuId;
/*     */   private String menuNm;
/*     */   private String menuGb;
/*     */   private String linkTarget;
/*     */   private String linkUrl;
/*     */   private int linkMenuId;
/*     */   private String linkBoardId;
/*     */   private String linkParam;
/*     */   private String popupParam;
/*     */   private String mainMenuAt;
/*     */   private String limitGroupIds;
/*     */   private int permitLevelId;
/*     */   private String template;
/*     */   private String layout;
/*     */   private int lvl;
/*     */ 
/*     */   public String getSiteId()
/*     */   {
/*  61 */     return this.siteId;
/*     */   }
/*     */ 
/*     */   public void setSiteId(String siteId) {
/*  65 */     this.siteId = siteId;
/*     */   }
/*     */ 
/*     */   public int getMenuId() {
/*  69 */     return this.menuId;
/*     */   }
/*     */ 
/*     */   public void setMenuId(int menuId) {
/*  73 */     this.menuId = menuId;
/*     */   }
/*     */ 
/*     */   public int getParntsMenuId() {
/*  77 */     return this.parntsMenuId;
/*     */   }
/*     */ 
/*     */   public void setParntsMenuId(int parntsMenuId) {
/*  81 */     this.parntsMenuId = parntsMenuId;
/*     */   }
/*     */ 
/*     */   public String getMenuNm() {
/*  85 */     return this.menuNm;
/*     */   }
/*     */ 
/*     */   public void setMenuNm(String menuNm) {
/*  89 */     this.menuNm = menuNm;
/*     */   }
/*     */ 
/*     */   public String getMenuGb() {
/*  93 */     return this.menuGb;
/*     */   }
/*     */ 
/*     */   public void setMenuGb(String menuGb) {
/*  97 */     this.menuGb = menuGb;
/*     */   }
/*     */ 
/*     */   public String getLinkTarget() {
/* 101 */     return this.linkTarget;
/*     */   }
/*     */ 
/*     */   public void setLinkTarget(String linkTarget) {
/* 105 */     this.linkTarget = linkTarget;
/*     */   }
/*     */ 
/*     */   public String getLinkUrl() {
/* 109 */     return this.linkUrl;
/*     */   }
/*     */ 
/*     */   public void setLinkUrl(String linkUrl) {
/* 113 */     this.linkUrl = linkUrl;
/*     */   }
/*     */ 
/*     */   public int getLinkMenuId() {
/* 117 */     return this.linkMenuId;
/*     */   }
/*     */ 
/*     */   public void setLinkMenuId(int linkMenuId) {
/* 121 */     this.linkMenuId = linkMenuId;
/*     */   }
/*     */ 
/*     */   public String getLinkBoardId() {
/* 125 */     return this.linkBoardId;
/*     */   }
/*     */ 
/*     */   public void setLinkBoardId(String linkBoardId) {
/* 129 */     this.linkBoardId = linkBoardId;
/*     */   }
/*     */ 
/*     */   public String getLinkParam() {
/* 133 */     return this.linkParam;
/*     */   }
/*     */ 
/*     */   public void setLinkParam(String linkParam) {
/* 137 */     this.linkParam = linkParam;
/*     */   }
/*     */ 
/*     */   public String getPopupParam() {
/* 141 */     return this.popupParam;
/*     */   }
/*     */ 
/*     */   public void setPopupParam(String popupParam) {
/* 145 */     this.popupParam = popupParam;
/*     */   }
/*     */ 
/*     */   public String getMainMenuAt() {
/* 149 */     return this.mainMenuAt;
/*     */   }
/*     */ 
/*     */   public void setMainMenuAt(String mainMenuAt) {
/* 153 */     this.mainMenuAt = mainMenuAt;
/*     */   }
/*     */ 
/*     */   public String getLimitGroupIds() {
/* 157 */     return this.limitGroupIds;
/*     */   }
/*     */ 
/*     */   public void setLimitGroupIds(String limitGroupIds) {
/* 161 */     this.limitGroupIds = limitGroupIds;
/*     */   }
/*     */ 
/*     */   public int getPermitLevelId() {
/* 165 */     return this.permitLevelId;
/*     */   }
/*     */ 
/*     */   public void setPermitLevelId(int permitLevelId) {
/* 169 */     this.permitLevelId = permitLevelId;
/*     */   }
/*     */ 
/*     */   public String getTemplate() {
/* 173 */     return this.template;
/*     */   }
/*     */ 
/*     */   public void setTemplate(String template) {
/* 177 */     this.template = template;
/*     */   }
/*     */ 
/*     */   public String getLayout() {
/* 181 */     return this.layout;
/*     */   }
/*     */ 
/*     */   public void setLayout(String layout) {
/* 185 */     this.layout = layout;
/*     */   }
/*     */ 
/*     */   public int getLvl() {
/* 189 */     return this.lvl;
/*     */   }
/*     */ 
/*     */   public void setLvl(int lvl) {
/* 193 */     this.lvl = lvl;
/*     */   }
/*     */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.site.vo.SiteMenuJsonVO
 * JD-Core Version:    0.6.2
 */