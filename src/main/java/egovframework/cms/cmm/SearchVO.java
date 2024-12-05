/*     */ package egovframework.cms.cmm;
/*     */ 
/*     */ import org.apache.commons.lang3.builder.ToStringBuilder;
/*     */ import org.apache.commons.lang3.builder.ToStringStyle;
/*     */ 
/*     */ public class SearchVO
/*     */ {
/*     */   private String searchStartDt;
/*     */   private String searchEndDt;
/*  12 */   private String searchCondition = "";
/*     */ 
/*  15 */   private String searchKeyword = "";
/*     */ 
/*  18 */   private int pageIndex = 1;
/*     */ 
/*  21 */   private int pageUnit = 10;
/*     */ 
/*  24 */   private int pageSize = 10;
/*     */ 
/*  27 */   private int firstIndex = 1;
/*     */ 
/*  30 */   private int lastIndex = 1;
/*     */ 
/*  33 */   private int recordCountPerPage = 10;
/*     */ 
/*  35 */   private String queryString = "";
/*     */ 
/*     */   public String getSearchStartDt() {
/*  38 */     return this.searchStartDt;
/*     */   }
/*     */ 
/*     */   public void setSearchStartDt(String searchStartDt) {
/*  42 */     this.searchStartDt = searchStartDt;
/*     */   }
/*     */ 
/*     */   public String getSearchEndDt() {
/*  46 */     return this.searchEndDt;
/*     */   }
/*     */ 
/*     */   public void setSearchEndDt(String searchEndDt) {
/*  50 */     this.searchEndDt = searchEndDt;
/*     */   }
/*     */ 
/*     */   public String getSearchCondition() {
/*  54 */     return this.searchCondition;
/*     */   }
/*     */ 
/*     */   public void setSearchCondition(String searchCondition) {
/*  58 */     this.searchCondition = searchCondition;
/*     */   }
/*     */ 
/*     */   public String getSearchKeyword() {
/*  62 */     return this.searchKeyword;
/*     */   }
/*     */ 
/*     */   public void setSearchKeyword(String searchKeyword) {
/*  66 */     this.searchKeyword = searchKeyword;
/*     */   }
/*     */ 
/*     */   public int getPageIndex() {
/*  70 */     return this.pageIndex;
/*     */   }
/*     */ 
/*     */   public void setPageIndex(int pageIndex) {
/*  74 */     this.pageIndex = pageIndex;
/*     */   }
/*     */ 
/*     */   public int getPageUnit() {
/*  78 */     return this.pageUnit;
/*     */   }
/*     */ 
/*     */   public void setPageUnit(int pageUnit) {
/*  82 */     this.pageUnit = pageUnit;
/*     */   }
/*     */ 
/*     */   public int getPageSize() {
/*  86 */     return this.pageSize;
/*     */   }
/*     */ 
/*     */   public void setPageSize(int pageSize) {
/*  90 */     this.pageSize = pageSize;
/*     */   }
/*     */ 
/*     */   public int getFirstIndex() {
/*  94 */     return this.firstIndex;
/*     */   }
/*     */ 
/*     */   public void setFirstIndex(int firstIndex) {
/*  98 */     this.firstIndex = firstIndex;
/*     */   }
/*     */ 
/*     */   public int getLastIndex() {
/* 102 */     return this.lastIndex;
/*     */   }
/*     */ 
/*     */   public void setLastIndex(int lastIndex) {
/* 106 */     this.lastIndex = lastIndex;
/*     */   }
/*     */ 
/*     */   public int getRecordCountPerPage() {
/* 110 */     return this.recordCountPerPage;
/*     */   }
/*     */ 
/*     */   public void setRecordCountPerPage(int recordCountPerPage) {
/* 114 */     this.recordCountPerPage = recordCountPerPage;
/*     */   }
/*     */ 
/*     */   public String getQueryString() {
/* 118 */     return this.queryString;
/*     */   }
/*     */ 
/*     */   public void setQueryString(String queryString) {
/* 122 */     this.queryString = queryString;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 127 */     return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
/*     */   }
/*     */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.cmm.SearchVO
 * JD-Core Version:    0.6.2
 */