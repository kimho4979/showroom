/*     */ package egovframework.cms.site.vo;
/*     */ 
/*     */ import org.apache.commons.lang3.builder.ToStringBuilder;
/*     */ import org.apache.commons.lang3.builder.ToStringStyle;
/*     */ 
/*     */ public class SiteContentCommentVO extends SiteContentComment
/*     */ {
/*     */   private String menuNm;
/*     */   private String menuPath;
/*     */   private int searchMenuLft;
/*     */   private int searchMenuRgt;
/*  17 */   private String searchCondition = "";
/*     */ 
/*  20 */   private String searchKeyword = "";
/*     */ 
/*  23 */   private int pageIndex = 1;
/*     */ 
/*  26 */   private int pageUnit = 10;
/*     */ 
/*  29 */   private int pageSize = 10;
/*     */ 
/*  32 */   private int firstIndex = 1;
/*     */ 
/*  35 */   private int lastIndex = 1;
/*     */ 
/*  38 */   private int recordCountPerPage = 10;
/*     */ 
/*  40 */   private String queryString = "";
/*     */ 
/*     */   public String getMenuNm()
/*     */   {
/*  44 */     return this.menuNm;
/*     */   }
/*     */ 
/*     */   public void setMenuNm(String menuNm)
/*     */   {
/*  50 */     this.menuNm = menuNm;
/*     */   }
/*     */ 
/*     */   public String getMenuPath()
/*     */   {
/*  56 */     return this.menuPath;
/*     */   }
/*     */ 
/*     */   public void setMenuPath(String menuPath)
/*     */   {
/*  62 */     this.menuPath = menuPath;
/*     */   }
/*     */ 
/*     */   public int getSearchMenuLft()
/*     */   {
/*  70 */     return this.searchMenuLft;
/*     */   }
/*     */ 
/*     */   public void setSearchMenuLft(int searchMenuLft)
/*     */   {
/*  76 */     this.searchMenuLft = searchMenuLft;
/*     */   }
/*     */ 
/*     */   public int getSearchMenuRgt()
/*     */   {
/*  82 */     return this.searchMenuRgt;
/*     */   }
/*     */ 
/*     */   public void setSearchMenuRgt(int searchMenuRgt)
/*     */   {
/*  88 */     this.searchMenuRgt = searchMenuRgt;
/*     */   }
/*     */ 
/*     */   public String getSearchCondition()
/*     */   {
/*  94 */     return this.searchCondition;
/*     */   }
/*     */ 
/*     */   public void setSearchCondition(String searchCondition)
/*     */   {
/* 100 */     this.searchCondition = searchCondition;
/*     */   }
/*     */ 
/*     */   public String getSearchKeyword()
/*     */   {
/* 106 */     return this.searchKeyword;
/*     */   }
/*     */ 
/*     */   public void setSearchKeyword(String searchKeyword)
/*     */   {
/* 112 */     this.searchKeyword = searchKeyword;
/*     */   }
/*     */ 
/*     */   public int getPageIndex()
/*     */   {
/* 118 */     return this.pageIndex;
/*     */   }
/*     */ 
/*     */   public void setPageIndex(int pageIndex)
/*     */   {
/* 124 */     this.pageIndex = pageIndex;
/*     */   }
/*     */ 
/*     */   public int getPageUnit()
/*     */   {
/* 130 */     return this.pageUnit;
/*     */   }
/*     */ 
/*     */   public void setPageUnit(int pageUnit)
/*     */   {
/* 136 */     this.pageUnit = pageUnit;
/*     */   }
/*     */ 
/*     */   public int getPageSize()
/*     */   {
/* 142 */     return this.pageSize;
/*     */   }
/*     */ 
/*     */   public void setPageSize(int pageSize)
/*     */   {
/* 148 */     this.pageSize = pageSize;
/*     */   }
/*     */ 
/*     */   public int getFirstIndex()
/*     */   {
/* 154 */     return this.firstIndex;
/*     */   }
/*     */ 
/*     */   public void setFirstIndex(int firstIndex)
/*     */   {
/* 160 */     this.firstIndex = firstIndex;
/*     */   }
/*     */ 
/*     */   public int getLastIndex()
/*     */   {
/* 166 */     return this.lastIndex;
/*     */   }
/*     */ 
/*     */   public void setLastIndex(int lastIndex)
/*     */   {
/* 172 */     this.lastIndex = lastIndex;
/*     */   }
/*     */ 
/*     */   public int getRecordCountPerPage()
/*     */   {
/* 178 */     return this.recordCountPerPage;
/*     */   }
/*     */ 
/*     */   public void setRecordCountPerPage(int recordCountPerPage)
/*     */   {
/* 184 */     this.recordCountPerPage = recordCountPerPage;
/*     */   }
/*     */ 
/*     */   public String getQueryString()
/*     */   {
/* 190 */     return this.queryString;
/*     */   }
/*     */ 
/*     */   public void setQueryString(String queryString)
/*     */   {
/* 196 */     this.queryString = queryString;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 203 */     return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
/*     */   }
/*     */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.site.vo.SiteContentCommentVO
 * JD-Core Version:    0.6.2
 */