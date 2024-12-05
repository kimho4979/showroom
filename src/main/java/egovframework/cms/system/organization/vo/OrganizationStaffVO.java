/*     */ package egovframework.cms.system.organization.vo;
/*     */ 
/*     */ import org.apache.commons.lang3.builder.ToStringBuilder;
/*     */ import org.apache.commons.lang3.builder.ToStringStyle;
/*     */ 
/*     */ public class OrganizationStaffVO extends OrganizationStaff
/*     */ {
/*     */   private String newPassword;
/*     */   private String deptNm;
/*     */   private String deptPath;
/*     */   private String[] searchStaffIds;
/*     */   private String[] searchLoginGroupIds;
/*     */   private String[] loginGroupIdArr;
/*     */   private String searchDeptId;
/*     */   private int searchDeptLft;
/*     */   private int searchDeptRgt;
/*     */   private String deletePhotoFile;
/*  29 */   private String searchCondition = "";
/*     */ 
/*  32 */   private String searchKeyword = "";
/*     */ 
/*  35 */   private int pageIndex = 1;
/*     */ 
/*  38 */   private int pageUnit = 10;
/*     */ 
/*  41 */   private int pageSize = 10;
/*     */ 
/*  44 */   private int firstIndex = 1;
/*     */ 
/*  47 */   private int lastIndex = 1;
/*     */ 
/*  50 */   private int recordCountPerPage = 10;
/*     */ 
/*  52 */   private String queryString = "";
/*     */ 
/*     */   public String getDeletePhotoFile()
/*     */   {
/*  56 */     return this.deletePhotoFile;
/*     */   }
/*     */ 
/*     */   public void setDeletePhotoFile(String deletePhotoFile) {
/*  60 */     this.deletePhotoFile = deletePhotoFile;
/*     */   }
/*     */ 
/*     */   public String getNewPassword() {
/*  64 */     return this.newPassword;
/*     */   }
/*     */ 
/*     */   public void setNewPassword(String newPassword) {
/*  68 */     this.newPassword = newPassword;
/*     */   }
/*     */ 
/*     */   public String getDeptNm() {
/*  72 */     return this.deptNm;
/*     */   }
/*     */ 
/*     */   public void setDeptNm(String deptNm) {
/*  76 */     this.deptNm = deptNm;
/*     */   }
/*     */ 
/*     */   public String getDeptPath() {
/*  80 */     return this.deptPath;
/*     */   }
/*     */ 
/*     */   public void setDeptPath(String deptPath) {
/*  84 */     this.deptPath = deptPath;
/*     */   }
/*     */ 
/*     */   public String[] getSearchStaffIds() {
/*  88 */     return this.searchStaffIds;
/*     */   }
/*     */ 
/*     */   public void setSearchStaffIds(String[] searchStaffIds) {
/*  92 */     this.searchStaffIds = searchStaffIds;
/*     */   }
/*     */ 
/*     */   public String[] getSearchLoginGroupIds() {
/*  96 */     return this.searchLoginGroupIds;
/*     */   }
/*     */ 
/*     */   public void setSearchLoginGroupIds(String[] searchLoginGroupIds) {
/* 100 */     this.searchLoginGroupIds = searchLoginGroupIds;
/*     */   }
/*     */ 
/*     */   public String[] getLoginGroupIdArr() {
/* 104 */     return this.loginGroupIdArr;
/*     */   }
/*     */ 
/*     */   public void setLoginGroupIdArr(String[] loginGroupIdArr) {
/* 108 */     this.loginGroupIdArr = loginGroupIdArr;
/*     */   }
/*     */ 
/*     */   public String getSearchDeptId() {
/* 112 */     return this.searchDeptId;
/*     */   }
/*     */ 
/*     */   public void setSearchDeptId(String searchDeptId) {
/* 116 */     this.searchDeptId = searchDeptId;
/*     */   }
/*     */ 
/*     */   public int getSearchDeptLft() {
/* 120 */     return this.searchDeptLft;
/*     */   }
/*     */ 
/*     */   public void setSearchDeptLft(int searchDeptLft) {
/* 124 */     this.searchDeptLft = searchDeptLft;
/*     */   }
/*     */ 
/*     */   public int getSearchDeptRgt() {
/* 128 */     return this.searchDeptRgt;
/*     */   }
/*     */ 
/*     */   public void setSearchDeptRgt(int searchDeptRgt) {
/* 132 */     this.searchDeptRgt = searchDeptRgt;
/*     */   }
/*     */ 
/*     */   public String getSearchCondition() {
/* 136 */     return this.searchCondition;
/*     */   }
/*     */ 
/*     */   public void setSearchCondition(String searchCondition) {
/* 140 */     this.searchCondition = searchCondition;
/*     */   }
/*     */ 
/*     */   public String getSearchKeyword() {
/* 144 */     return this.searchKeyword;
/*     */   }
/*     */ 
/*     */   public void setSearchKeyword(String searchKeyword) {
/* 148 */     this.searchKeyword = searchKeyword;
/*     */   }
/*     */ 
/*     */   public int getPageIndex() {
/* 152 */     return this.pageIndex;
/*     */   }
/*     */ 
/*     */   public void setPageIndex(int pageIndex) {
/* 156 */     this.pageIndex = pageIndex;
/*     */   }
/*     */ 
/*     */   public int getPageUnit() {
/* 160 */     return this.pageUnit;
/*     */   }
/*     */ 
/*     */   public void setPageUnit(int pageUnit) {
/* 164 */     this.pageUnit = pageUnit;
/*     */   }
/*     */ 
/*     */   public int getPageSize() {
/* 168 */     return this.pageSize;
/*     */   }
/*     */ 
/*     */   public void setPageSize(int pageSize) {
/* 172 */     this.pageSize = pageSize;
/*     */   }
/*     */ 
/*     */   public int getFirstIndex() {
/* 176 */     return this.firstIndex;
/*     */   }
/*     */ 
/*     */   public void setFirstIndex(int firstIndex) {
/* 180 */     this.firstIndex = firstIndex;
/*     */   }
/*     */ 
/*     */   public int getLastIndex() {
/* 184 */     return this.lastIndex;
/*     */   }
/*     */ 
/*     */   public void setLastIndex(int lastIndex) {
/* 188 */     this.lastIndex = lastIndex;
/*     */   }
/*     */ 
/*     */   public int getRecordCountPerPage() {
/* 192 */     return this.recordCountPerPage;
/*     */   }
/*     */ 
/*     */   public void setRecordCountPerPage(int recordCountPerPage) {
/* 196 */     this.recordCountPerPage = recordCountPerPage;
/*     */   }
/*     */ 
/*     */   public String getQueryString() {
/* 200 */     return this.queryString;
/*     */   }
/*     */ 
/*     */   public void setQueryString(String queryString) {
/* 204 */     this.queryString = queryString;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 209 */     return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
/*     */   }
/*     */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.system.organization.vo.OrganizationStaffVO
 * JD-Core Version:    0.6.2
 */