/*     */ package egovframework.cms.system.code.vo;
/*     */ 
/*     */ import egovframework.cms.cmm.service.CmmnDetailCode;
/*     */ import org.apache.commons.lang3.builder.ToStringBuilder;
/*     */ import org.apache.commons.lang3.builder.ToStringStyle;
/*     */ 
/*     */ public class CmmnDetailCodeVO extends CmmnDetailCode
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String clCode;
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
/*     */   public String getClCode() {
/*  41 */     return this.clCode;
/*     */   }
/*     */ 
/*     */   public void setClCode(String clCode) {
/*  45 */     this.clCode = clCode;
/*     */   }
/*     */ 
/*     */   public String getSearchCondition() {
/*  49 */     return this.searchCondition;
/*     */   }
/*     */ 
/*     */   public void setSearchCondition(String searchCondition) {
/*  53 */     this.searchCondition = searchCondition;
/*     */   }
/*     */ 
/*     */   public String getSearchKeyword() {
/*  57 */     return this.searchKeyword;
/*     */   }
/*     */ 
/*     */   public void setSearchKeyword(String searchKeyword) {
/*  61 */     this.searchKeyword = searchKeyword;
/*     */   }
/*     */ 
/*     */   public int getPageIndex() {
/*  65 */     return this.pageIndex;
/*     */   }
/*     */ 
/*     */   public void setPageIndex(int pageIndex) {
/*  69 */     this.pageIndex = pageIndex;
/*     */   }
/*     */ 
/*     */   public int getPageUnit() {
/*  73 */     return this.pageUnit;
/*     */   }
/*     */ 
/*     */   public void setPageUnit(int pageUnit) {
/*  77 */     this.pageUnit = pageUnit;
/*     */   }
/*     */ 
/*     */   public int getPageSize() {
/*  81 */     return this.pageSize;
/*     */   }
/*     */ 
/*     */   public void setPageSize(int pageSize) {
/*  85 */     this.pageSize = pageSize;
/*     */   }
/*     */ 
/*     */   public int getFirstIndex() {
/*  89 */     return this.firstIndex;
/*     */   }
/*     */ 
/*     */   public void setFirstIndex(int firstIndex) {
/*  93 */     this.firstIndex = firstIndex;
/*     */   }
/*     */ 
/*     */   public int getLastIndex() {
/*  97 */     return this.lastIndex;
/*     */   }
/*     */ 
/*     */   public void setLastIndex(int lastIndex) {
/* 101 */     this.lastIndex = lastIndex;
/*     */   }
/*     */ 
/*     */   public int getRecordCountPerPage() {
/* 105 */     return this.recordCountPerPage;
/*     */   }
/*     */ 
/*     */   public void setRecordCountPerPage(int recordCountPerPage) {
/* 109 */     this.recordCountPerPage = recordCountPerPage;
/*     */   }
/*     */ 
/*     */   public String getQueryString() {
/* 113 */     return this.queryString;
/*     */   }
/*     */ 
/*     */   public void setQueryString(String queryString) {
/* 117 */     this.queryString = queryString;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 122 */     return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
/*     */   }
/*     */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.system.code.vo.CmmnDetailCodeVO
 * JD-Core Version:    0.6.2
 */