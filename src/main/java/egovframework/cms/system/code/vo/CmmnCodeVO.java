/*     */ package egovframework.cms.system.code.vo;
/*     */ 
/*     */ import org.apache.commons.lang3.builder.ToStringBuilder;
/*     */ import org.apache.commons.lang3.builder.ToStringStyle;
/*     */ 
/*     */ public class CmmnCodeVO extends CmmnCode
/*     */ {
/*   9 */   private String searchCondition = "";
/*     */ 
/*  12 */   private String searchKeyword = "";
/*     */ 
/*  15 */   private int pageIndex = 1;
/*     */ 
/*  18 */   private int pageUnit = 10;
/*     */ 
/*  21 */   private int pageSize = 10;
/*     */ 
/*  24 */   private int firstIndex = 1;
/*     */ 
/*  27 */   private int lastIndex = 1;
/*     */ 
/*  30 */   private int recordCountPerPage = 10;
/*     */ 
/*  32 */   private String queryString = "";
/*     */ 
/*     */   public String getSearchCondition() {
/*  35 */     return this.searchCondition;
/*     */   }
/*     */ 
/*     */   public void setSearchCondition(String searchCondition) {
/*  39 */     this.searchCondition = searchCondition;
/*     */   }
/*     */ 
/*     */   public String getSearchKeyword() {
/*  43 */     return this.searchKeyword;
/*     */   }
/*     */ 
/*     */   public void setSearchKeyword(String searchKeyword) {
/*  47 */     this.searchKeyword = searchKeyword;
/*     */   }
/*     */ 
/*     */   public int getPageIndex() {
/*  51 */     return this.pageIndex;
/*     */   }
/*     */ 
/*     */   public void setPageIndex(int pageIndex) {
/*  55 */     this.pageIndex = pageIndex;
/*     */   }
/*     */ 
/*     */   public int getPageUnit() {
/*  59 */     return this.pageUnit;
/*     */   }
/*     */ 
/*     */   public void setPageUnit(int pageUnit) {
/*  63 */     this.pageUnit = pageUnit;
/*     */   }
/*     */ 
/*     */   public int getPageSize() {
/*  67 */     return this.pageSize;
/*     */   }
/*     */ 
/*     */   public void setPageSize(int pageSize) {
/*  71 */     this.pageSize = pageSize;
/*     */   }
/*     */ 
/*     */   public int getFirstIndex() {
/*  75 */     return this.firstIndex;
/*     */   }
/*     */ 
/*     */   public void setFirstIndex(int firstIndex) {
/*  79 */     this.firstIndex = firstIndex;
/*     */   }
/*     */ 
/*     */   public int getLastIndex() {
/*  83 */     return this.lastIndex;
/*     */   }
/*     */ 
/*     */   public void setLastIndex(int lastIndex) {
/*  87 */     this.lastIndex = lastIndex;
/*     */   }
/*     */ 
/*     */   public int getRecordCountPerPage() {
/*  91 */     return this.recordCountPerPage;
/*     */   }
/*     */ 
/*     */   public void setRecordCountPerPage(int recordCountPerPage) {
/*  95 */     this.recordCountPerPage = recordCountPerPage;
/*     */   }
/*     */ 
/*     */   public String getQueryString() {
/*  99 */     return this.queryString;
/*     */   }
/*     */ 
/*     */   public void setQueryString(String queryString) {
/* 103 */     this.queryString = queryString;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 108 */     return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
/*     */   }
/*     */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.system.code.vo.CmmnCodeVO
 * JD-Core Version:    0.6.2
 */