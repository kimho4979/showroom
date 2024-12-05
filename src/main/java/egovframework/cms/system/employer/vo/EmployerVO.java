/*     */ package egovframework.cms.system.employer.vo;
/*     */ 
/*     */ import org.apache.commons.lang3.builder.ToStringBuilder;
/*     */ import org.apache.commons.lang3.builder.ToStringStyle;
/*     */ 
/*     */ public class EmployerVO extends Employer
/*     */ {
/*     */   private String[] groupIdArr;
/*     */   private String[] adminSiteIdArr;
/*     */   private String[] adminMenuIdArr;
/*  14 */   private String searchCondition = "";
/*     */ 
/*  17 */   private String searchKeyword = "";
/*     */ 
/*  20 */   private int pageIndex = 1;
/*     */ 
/*  23 */   private int pageUnit = 10;
/*     */ 
/*  26 */   private int pageSize = 10;
/*     */ 
/*  29 */   private int firstIndex = 1;
/*     */ 
/*  32 */   private int lastIndex = 1;
/*     */ 
/*  35 */   private int recordCountPerPage = 10;
/*     */ 
/*  37 */   private String queryString = "";
/*     */ 
/*     */   public String[] getGroupIdArr() {
/*  40 */     return this.groupIdArr;
/*     */   }
/*     */ 
/*     */   public String[] getAdminSiteIdArr() {
/*  44 */     return this.adminSiteIdArr;
/*     */   }
/*     */ 
/*     */   public void setAdminSiteIdArr(String[] adminSiteIdArr) {
/*  48 */     this.adminSiteIdArr = adminSiteIdArr;
/*     */   }
/*     */ 
/*     */   public String[] getAdminMenuIdArr() {
/*  52 */     return this.adminMenuIdArr;
/*     */   }
/*     */ 
/*     */   public void setAdminMenuIdArr(String[] adminMenuIdArr) {
/*  56 */     this.adminMenuIdArr = adminMenuIdArr;
/*     */   }
/*     */ 
/*     */   public void setGroupIdArr(String[] groupIdArr) {
/*  60 */     this.groupIdArr = groupIdArr;
/*     */   }
/*     */ 
/*     */   public String getSearchCondition() {
/*  64 */     return this.searchCondition;
/*     */   }
/*     */ 
/*     */   public void setSearchCondition(String searchCondition) {
/*  68 */     this.searchCondition = searchCondition;
/*     */   }
/*     */ 
/*     */   public String getSearchKeyword() {
/*  72 */     return this.searchKeyword;
/*     */   }
/*     */ 
/*     */   public void setSearchKeyword(String searchKeyword) {
/*  76 */     this.searchKeyword = searchKeyword;
/*     */   }
/*     */ 
/*     */   public int getPageIndex() {
/*  80 */     return this.pageIndex;
/*     */   }
/*     */ 
/*     */   public void setPageIndex(int pageIndex) {
/*  84 */     this.pageIndex = pageIndex;
/*     */   }
/*     */ 
/*     */   public int getPageUnit() {
/*  88 */     return this.pageUnit;
/*     */   }
/*     */ 
/*     */   public void setPageUnit(int pageUnit) {
/*  92 */     this.pageUnit = pageUnit;
/*     */   }
/*     */ 
/*     */   public int getPageSize() {
/*  96 */     return this.pageSize;
/*     */   }
/*     */ 
/*     */   public void setPageSize(int pageSize) {
/* 100 */     this.pageSize = pageSize;
/*     */   }
/*     */ 
/*     */   public int getFirstIndex() {
/* 104 */     return this.firstIndex;
/*     */   }
/*     */ 
/*     */   public void setFirstIndex(int firstIndex) {
/* 108 */     this.firstIndex = firstIndex;
/*     */   }
/*     */ 
/*     */   public int getLastIndex() {
/* 112 */     return this.lastIndex;
/*     */   }
/*     */ 
/*     */   public void setLastIndex(int lastIndex) {
/* 116 */     this.lastIndex = lastIndex;
/*     */   }
/*     */ 
/*     */   public int getRecordCountPerPage() {
/* 120 */     return this.recordCountPerPage;
/*     */   }
/*     */ 
/*     */   public void setRecordCountPerPage(int recordCountPerPage) {
/* 124 */     this.recordCountPerPage = recordCountPerPage;
/*     */   }
/*     */ 
/*     */   public String getQueryString() {
/* 128 */     return this.queryString;
/*     */   }
/*     */ 
/*     */   public void setQueryString(String queryString) {
/* 132 */     this.queryString = queryString;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 137 */     return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
/*     */   }
/*     */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.system.employer.vo.EmployerVO
 * JD-Core Version:    0.6.2
 */