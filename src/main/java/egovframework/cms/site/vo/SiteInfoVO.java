/*     */ package egovframework.cms.site.vo;
/*     */ 
/*     */ import org.apache.commons.lang3.builder.ToStringBuilder;
/*     */ import org.apache.commons.lang3.builder.ToStringStyle;
/*     */ 
/*     */ public class SiteInfoVO extends SiteInfo
/*     */ {
/*     */   private String[] siteIdArr;
/*     */   private String[] adminSiteIdArr;
/*     */   private String[] limitGroupIdArr;
/*  15 */   private String searchCondition = "";
/*     */ 
/*  18 */   private String searchKeyword = "";
/*     */ 
/*  21 */   private int pageIndex = 1;
/*     */ 
/*  24 */   private int pageUnit = 10;
/*     */ 
/*  27 */   private int pageSize = 10;
/*     */ 
/*  30 */   private int firstIndex = 1;
/*     */ 
/*  33 */   private int lastIndex = 1;
/*     */ 
/*  36 */   private int recordCountPerPage = 10;
/*     */ 
/*  38 */   private String queryString = "";
/*     */ 
/*     */   public String[] getSiteIdArr() {
/*  41 */     return this.siteIdArr;
/*     */   }
/*     */ 
/*     */   public void setSiteIdArr(String[] siteIdArr) {
/*  45 */     this.siteIdArr = siteIdArr;
/*     */   }
/*     */ 
/*     */   public String[] getAdminSiteIdArr() {
/*  49 */     return this.adminSiteIdArr;
/*     */   }
/*     */ 
/*     */   public void setAdminSiteIdArr(String[] adminSiteIdArr) {
/*  53 */     this.adminSiteIdArr = adminSiteIdArr;
/*     */   }
/*     */ 
/*     */   public String[] getLimitGroupIdArr() {
/*  57 */     return this.limitGroupIdArr;
/*     */   }
/*     */ 
/*     */   public void setLimitGroupIdArr(String[] limitGroupIdArr) {
/*  61 */     this.limitGroupIdArr = limitGroupIdArr;
/*     */   }
/*     */ 
/*     */   public String getSearchCondition() {
/*  65 */     return this.searchCondition;
/*     */   }
/*     */ 
/*     */   public void setSearchCondition(String searchCondition) {
/*  69 */     this.searchCondition = searchCondition;
/*     */   }
/*     */ 
/*     */   public String getSearchKeyword() {
/*  73 */     return this.searchKeyword;
/*     */   }
/*     */ 
/*     */   public void setSearchKeyword(String searchKeyword) {
/*  77 */     this.searchKeyword = searchKeyword;
/*     */   }
/*     */ 
/*     */   public int getPageIndex() {
/*  81 */     return this.pageIndex;
/*     */   }
/*     */ 
/*     */   public void setPageIndex(int pageIndex) {
/*  85 */     this.pageIndex = pageIndex;
/*     */   }
/*     */ 
/*     */   public int getPageUnit() {
/*  89 */     return this.pageUnit;
/*     */   }
/*     */ 
/*     */   public void setPageUnit(int pageUnit) {
/*  93 */     this.pageUnit = pageUnit;
/*     */   }
/*     */ 
/*     */   public int getPageSize() {
/*  97 */     return this.pageSize;
/*     */   }
/*     */ 
/*     */   public void setPageSize(int pageSize) {
/* 101 */     this.pageSize = pageSize;
/*     */   }
/*     */ 
/*     */   public int getFirstIndex() {
/* 105 */     return this.firstIndex;
/*     */   }
/*     */ 
/*     */   public void setFirstIndex(int firstIndex) {
/* 109 */     this.firstIndex = firstIndex;
/*     */   }
/*     */ 
/*     */   public int getLastIndex() {
/* 113 */     return this.lastIndex;
/*     */   }
/*     */ 
/*     */   public void setLastIndex(int lastIndex) {
/* 117 */     this.lastIndex = lastIndex;
/*     */   }
/*     */ 
/*     */   public int getRecordCountPerPage() {
/* 121 */     return this.recordCountPerPage;
/*     */   }
/*     */ 
/*     */   public void setRecordCountPerPage(int recordCountPerPage) {
/* 125 */     this.recordCountPerPage = recordCountPerPage;
/*     */   }
/*     */ 
/*     */   public String getQueryString() {
/* 129 */     return this.queryString;
/*     */   }
/*     */ 
/*     */   public void setQueryString(String queryString) {
/* 133 */     this.queryString = queryString;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 138 */     return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
/*     */   }
/*     */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.site.vo.SiteInfoVO
 * JD-Core Version:    0.6.2
 */