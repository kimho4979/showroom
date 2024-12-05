/*     */ package egovframework.cms.site.vo;
/*     */ 
/*     */ import org.apache.commons.lang3.builder.ToStringBuilder;
/*     */ import org.apache.commons.lang3.builder.ToStringStyle;
/*     */ 
/*     */ public class SiteMenuVO extends SiteMenu
/*     */ {
/*     */   private String menuPath;
/*     */   private String[] limitGroupIdArr;
/*     */   private String userLinkUrl;
/*     */   private String updateChldrnsLayout;
/*     */   private String updateChldrnsMngStaff;
/*     */   private String updateChldrnsCpyrgtAt;
/*     */   private String updateChldrnsSnsAt;
/*     */   private String updateChldrnsMngUserIds;
/*     */   private String updateChldrnsSatis;
/*     */ 
/*     */   public String getMenuPath()
/*     */   {
/*  27 */     return this.menuPath;
/*     */   }
/*     */ 
/*     */   public void setMenuPath(String menuPath) {
/*  31 */     this.menuPath = menuPath;
/*     */   }
/*     */ 
/*     */   public String[] getLimitGroupIdArr() {
/*  35 */     return this.limitGroupIdArr;
/*     */   }
/*     */ 
/*     */   public void setLimitGroupIdArr(String[] limitGroupIdArr) {
/*  39 */     this.limitGroupIdArr = limitGroupIdArr;
/*     */   }
/*     */ 
/*     */   public String getUserLinkUrl() {
/*  43 */     return this.userLinkUrl;
/*     */   }
/*     */ 
/*     */   public void setUserLinkUrl(String userLinkUrl) {
/*  47 */     this.userLinkUrl = userLinkUrl;
/*     */   }
/*     */ 
/*     */   public String getUpdateChldrnsLayout() {
/*  51 */     return this.updateChldrnsLayout;
/*     */   }
/*     */ 
/*     */   public void setUpdateChldrnsLayout(String updateChldrnsLayout) {
/*  55 */     this.updateChldrnsLayout = updateChldrnsLayout;
/*     */   }
/*     */ 
/*     */   public String getUpdateChldrnsMngStaff() {
/*  59 */     return this.updateChldrnsMngStaff;
/*     */   }
/*     */ 
/*     */   public void setUpdateChldrnsMngStaff(String updateChldrnsMngStaff) {
/*  63 */     this.updateChldrnsMngStaff = updateChldrnsMngStaff;
/*     */   }
/*     */ 
/*     */   public String getUpdateChldrnsCpyrgtAt() {
/*  67 */     return this.updateChldrnsCpyrgtAt;
/*     */   }
/*     */ 
/*     */   public void setUpdateChldrnsCpyrgtAt(String updateChldrnsCpyrgtAt) {
/*  71 */     this.updateChldrnsCpyrgtAt = updateChldrnsCpyrgtAt;
/*     */   }
/*     */ 
/*     */   public String getUpdateChldrnsSnsAt() {
/*  75 */     return this.updateChldrnsSnsAt;
/*     */   }
/*     */ 
/*     */   public void setUpdateChldrnsSnsAt(String updateChldrnsSnsAt) {
/*  79 */     this.updateChldrnsSnsAt = updateChldrnsSnsAt;
/*     */   }
/*     */ 
/*     */   public String getUpdateChldrnsMngUserIds() {
/*  83 */     return this.updateChldrnsMngUserIds;
/*     */   }
/*     */ 
/*     */   public void setUpdateChldrnsMngUserIds(String updateChldrnsMngUserIds) {
/*  87 */     this.updateChldrnsMngUserIds = updateChldrnsMngUserIds;
/*     */   }
/*     */ 
/*     */   public String getUpdateChldrnsSatis() {
/*  91 */     return this.updateChldrnsSatis;
/*     */   }
/*     */ 
/*     */   public void setUpdateChldrnsSatis(String updateChldrnsSatis) {
/*  95 */     this.updateChldrnsSatis = updateChldrnsSatis;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 100 */     return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
/*     */   }
/*     */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.site.vo.SiteMenuVO
 * JD-Core Version:    0.6.2
 */