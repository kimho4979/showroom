/*     */ package egovframework.front.board.vo;
/*     */ 
/*     */ import org.apache.commons.lang3.builder.ToStringBuilder;
/*     */ import org.apache.commons.lang3.builder.ToStringStyle;
/*     */ 
/*     */ public class FrontBoardArticleVO extends FrontBoardArticle
/*     */ {
/*     */   private String categoryNm;
/*     */   private String progressNm;
/*     */   private String stateNm;
/*     */   private String mode;
/*     */   private String deleteListThumbFile;
/*     */   private String[] deleteAtchFile;
/*  20 */   private String searchMode = "";
/*     */   private String searchStartDt;
/*     */   private String searchEndDt;
/*     */   private String orderby;
/*  30 */   private String searchCondition = "";
/*     */ 
/*  33 */   private String searchKeyword = "";
/*     */ 
/*  36 */   private int pageIndex = 1;
/*     */ 
/*  39 */   private int pageUnit = 0;
/*     */ 
/*  42 */   private int pageSize = 10;
/*     */ 
/*  45 */   private int firstIndex = 1;
/*     */ 
/*  48 */   private int lastIndex = 1;
/*     */ 
/*  51 */   private int recordCountPerPage = 10;
/*     */ 
/*  53 */   private String queryString = "";
/*     */ 
/*     */   public String getCategoryNm() {
/*  56 */     return this.categoryNm;
/*     */   }
/*     */ 
/*     */   public void setCategoryNm(String categoryNm)
/*     */   {
/*  62 */     this.categoryNm = categoryNm;
/*     */   }
/*     */ 
/*     */   public String getProgressNm()
/*     */   {
/*  68 */     return this.progressNm;
/*     */   }
/*     */ 
/*     */   public void setProgressNm(String progressNm)
/*     */   {
/*  74 */     this.progressNm = progressNm;
/*     */   }
/*     */ 
/*     */   public String getOrderby()
/*     */   {
/*  79 */     return this.orderby;
/*     */   }
/*     */ 
/*     */   public void setOrderby(String orderby)
/*     */   {
/*  85 */     this.orderby = orderby;
/*     */   }
/*     */ 
/*     */   public String getStateNm()
/*     */   {
/*  91 */     return this.stateNm;
/*     */   }
/*     */ 
/*     */   public void setStateNm(String stateNm)
/*     */   {
/*  97 */     this.stateNm = stateNm;
/*     */   }
/*     */ 
/*     */   public String getMode()
/*     */   {
/* 103 */     return this.mode;
/*     */   }
/*     */ 
/*     */   public void setMode(String mode)
/*     */   {
/* 109 */     this.mode = mode;
/*     */   }
/*     */ 
/*     */   public String getDeleteListThumbFile()
/*     */   {
/* 115 */     return this.deleteListThumbFile;
/*     */   }
/*     */ 
/*     */   public void setDeleteListThumbFile(String deleteListThumbFile)
/*     */   {
/* 121 */     this.deleteListThumbFile = deleteListThumbFile;
/*     */   }
/*     */ 
/*     */   public String[] getDeleteAtchFile()
/*     */   {
/* 127 */     return this.deleteAtchFile;
/*     */   }
/*     */ 
/*     */   public void setDeleteAtchFile(String[] deleteAtchFile)
/*     */   {
/* 133 */     this.deleteAtchFile = deleteAtchFile;
/*     */   }
/*     */ 
/*     */   public String getSearchMode()
/*     */   {
/* 139 */     return this.searchMode;
/*     */   }
/*     */ 
/*     */   public void setSearchMode(String searchMode)
/*     */   {
/* 145 */     this.searchMode = searchMode;
/*     */   }
/*     */ 
/*     */   public String getSearchStartDt()
/*     */   {
/* 151 */     return this.searchStartDt;
/*     */   }
/*     */ 
/*     */   public void setSearchStartDt(String searchStartDt)
/*     */   {
/* 157 */     this.searchStartDt = searchStartDt;
/*     */   }
/*     */ 
/*     */   public String getSearchEndDt()
/*     */   {
/* 163 */     return this.searchEndDt;
/*     */   }
/*     */ 
/*     */   public void setSearchEndDt(String searchEndDt)
/*     */   {
/* 169 */     this.searchEndDt = searchEndDt;
/*     */   }
/*     */ 
/*     */   public String getSearchCondition()
/*     */   {
/* 175 */     return this.searchCondition;
/*     */   }
/*     */ 
/*     */   public void setSearchCondition(String searchCondition)
/*     */   {
/* 181 */     this.searchCondition = searchCondition;
/*     */   }
/*     */ 
/*     */   public String getSearchKeyword()
/*     */   {
/* 187 */     return this.searchKeyword;
/*     */   }
/*     */ 
/*     */   public void setSearchKeyword(String searchKeyword)
/*     */   {
/* 193 */     this.searchKeyword = searchKeyword;
/*     */   }
/*     */ 
/*     */   public int getPageIndex()
/*     */   {
/* 199 */     return this.pageIndex;
/*     */   }
/*     */ 
/*     */   public void setPageIndex(int pageIndex)
/*     */   {
/* 205 */     this.pageIndex = pageIndex;
/*     */   }
/*     */ 
/*     */   public int getPageUnit()
/*     */   {
/* 211 */     return this.pageUnit;
/*     */   }
/*     */ 
/*     */   public void setPageUnit(int pageUnit)
/*     */   {
/* 217 */     this.pageUnit = pageUnit;
/*     */   }
/*     */ 
/*     */   public int getPageSize()
/*     */   {
/* 223 */     return this.pageSize;
/*     */   }
/*     */ 
/*     */   public void setPageSize(int pageSize)
/*     */   {
/* 229 */     this.pageSize = pageSize;
/*     */   }
/*     */ 
/*     */   public int getFirstIndex()
/*     */   {
/* 235 */     return this.firstIndex;
/*     */   }
/*     */ 
/*     */   public void setFirstIndex(int firstIndex)
/*     */   {
/* 241 */     this.firstIndex = firstIndex;
/*     */   }
/*     */ 
/*     */   public int getLastIndex()
/*     */   {
/* 247 */     return this.lastIndex;
/*     */   }
/*     */ 
/*     */   public void setLastIndex(int lastIndex)
/*     */   {
/* 253 */     this.lastIndex = lastIndex;
/*     */   }
/*     */ 
/*     */   public int getRecordCountPerPage()
/*     */   {
/* 259 */     return this.recordCountPerPage;
/*     */   }
/*     */ 
/*     */   public void setRecordCountPerPage(int recordCountPerPage)
/*     */   {
/* 265 */     this.recordCountPerPage = recordCountPerPage;
/*     */   }
/*     */ 
/*     */   public String getQueryString()
/*     */   {
/* 271 */     return this.queryString;
/*     */   }
/*     */ 
/*     */   public void setQueryString(String queryString)
/*     */   {
/* 277 */     this.queryString = queryString;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 284 */     return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
/*     */   }
/*     */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.board.vo.BoardArticleVO
 * JD-Core Version:    0.6.2
 */