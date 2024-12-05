/*     */ package egovframework.front.board.vo;
/*     */ 
/*     */ import org.apache.commons.lang3.builder.ToStringBuilder;
/*     */ import org.apache.commons.lang3.builder.ToStringStyle;
/*     */ 
/*     */ public class FrontBoardInfoVO extends FrontBoardInfo
/*     */ {
/*     */   private String[] siteIdArr;
/*     */   private String[] adminSiteIdArr;
/*     */   private String[] limitGrpListArr;
/*     */   private String[] limitGrpReadArr;
/*     */   private String[] limitGrpWriteArr;
/*     */   private String[] limitGrpReplyArr;
/*     */   private String[] limitGrpAnswerArr;
/*     */   private String[] limitGrpEditorArr;
/*     */   private String[] limitGrpCommentArr;
/*  27 */   private String searchCondition = "";
/*     */ 
/*  30 */   private String searchKeyword = "";
			private String searchWord = "";
/*     */ 
/*  33 */   private int pageIndex = 1;
/*     */ 
/*  36 */   private int pageUnit = 10;
/*     */ 
/*  39 */   private int pageSize = 10;
/*     */ 
/*  42 */   private int firstIndex = 1;
/*     */ 
/*  45 */   private int lastIndex = 1;
/*     */ 
/*  48 */   private int recordCountPerPage = 10;
/*     */ 
/*  50 */   private String queryString = "";
/*     */ 
/*     */   public String[] getSiteIdArr() {
/*  53 */     return this.siteIdArr;
/*     */   }

			

/*
public String[] getSearchConArray(){
	
	String[] selectedCon = searchCondition == null ? new String[] {} : searchCondition.split("");
	return  selectedCon;
}*/
 


public String getSearchWord() {
	return searchWord;
}



public void setSearchWord(String searchWord) {
	this.searchWord = searchWord;
}



/*     */ 
/*     */   public void setSiteIdArr(String[] siteIdArr) {
/*  57 */     this.siteIdArr = siteIdArr;
/*     */   }
/*     */ 
/*     */   public String[] getAdminSiteIdArr() {
/*  61 */     return this.adminSiteIdArr;
/*     */   }
/*     */ 
/*     */   public void setAdminSiteIdArr(String[] adminSiteIdArr) {
/*  65 */     this.adminSiteIdArr = adminSiteIdArr;
/*     */   }
/*     */ 
/*     */   public String[] getLimitGrpListArr() {
/*  69 */     return this.limitGrpListArr;
/*     */   }
/*     */ 
/*     */   public void setLimitGrpListArr(String[] limitGrpListArr) {
/*  73 */     this.limitGrpListArr = limitGrpListArr;
/*     */   }
/*     */ 
/*     */   public String[] getLimitGrpReadArr() {
/*  77 */     return this.limitGrpReadArr;
/*     */   }
/*     */ 
/*     */   public void setLimitGrpReadArr(String[] limitGrpReadArr) {
/*  81 */     this.limitGrpReadArr = limitGrpReadArr;
/*     */   }
/*     */ 
/*     */   public String[] getLimitGrpWriteArr() {
/*  85 */     return this.limitGrpWriteArr;
/*     */   }
/*     */ 
/*     */   public void setLimitGrpWriteArr(String[] limitGrpWriteArr) {
/*  89 */     this.limitGrpWriteArr = limitGrpWriteArr;
/*     */   }
/*     */ 
/*     */   public String[] getLimitGrpReplyArr() {
/*  93 */     return this.limitGrpReplyArr;
/*     */   }
/*     */ 
/*     */   public void setLimitGrpReplyArr(String[] limitGrpReplyArr) {
/*  97 */     this.limitGrpReplyArr = limitGrpReplyArr;
/*     */   }
/*     */ 
/*     */   public String[] getLimitGrpAnswerArr() {
/* 101 */     return this.limitGrpAnswerArr;
/*     */   }
/*     */ 
/*     */   public void setLimitGrpAnswerArr(String[] limitGrpAnswerArr) {
/* 105 */     this.limitGrpAnswerArr = limitGrpAnswerArr;
/*     */   }
/*     */ 
/*     */   public String[] getLimitGrpEditorArr() {
/* 109 */     return this.limitGrpEditorArr;
/*     */   }
/*     */ 
/*     */   public void setLimitGrpEditorArr(String[] limitGrpEditorArr) {
/* 113 */     this.limitGrpEditorArr = limitGrpEditorArr;
/*     */   }
/*     */ 
/*     */   public String[] getLimitGrpCommentArr() {
/* 117 */     return this.limitGrpCommentArr;
/*     */   }
/*     */ 
/*     */   public void setLimitGrpCommentArr(String[] limitGrpCommentArr) {
/* 121 */     this.limitGrpCommentArr = limitGrpCommentArr;
/*     */   }
/*     */ 
/*     */   public String getSearchCondition() {
/* 125 */     return this.searchCondition;
/*     */   }
/*     */ 
/*     */   public void setSearchCondition(String searchCondition) {
/* 129 */     this.searchCondition = searchCondition;
/*     */   }
/*     */ 
/*     */   public String getSearchKeyword() {
/* 133 */     return this.searchKeyword;
/*     */   }
/*     */ 
/*     */   public void setSearchKeyword(String searchKeyword) {
/* 137 */     this.searchKeyword = searchKeyword;
/*     */   }
/*     */ 
/*     */   public int getPageIndex() {
/* 141 */     return this.pageIndex;
/*     */   }
/*     */ 
/*     */   public void setPageIndex(int pageIndex) {
/* 145 */     this.pageIndex = pageIndex;
/*     */   }
/*     */ 
/*     */   public int getPageUnit() {
/* 149 */     return this.pageUnit;
/*     */   }
/*     */ 
/*     */   public void setPageUnit(int pageUnit) {
/* 153 */     this.pageUnit = pageUnit;
/*     */   }
/*     */ 
/*     */   public int getPageSize() {
/* 157 */     return this.pageSize;
/*     */   }
/*     */ 
/*     */   public void setPageSize(int pageSize) {
/* 161 */     this.pageSize = pageSize;
/*     */   }
/*     */ 
/*     */   public int getFirstIndex() {
/* 165 */     return this.firstIndex;
/*     */   }
/*     */ 
/*     */   public void setFirstIndex(int firstIndex) {
/* 169 */     this.firstIndex = firstIndex;
/*     */   }
/*     */ 
/*     */   public int getLastIndex() {
/* 173 */     return this.lastIndex;
/*     */   }
/*     */ 
/*     */   public void setLastIndex(int lastIndex) {
/* 177 */     this.lastIndex = lastIndex;
/*     */   }
/*     */ 
/*     */   public int getRecordCountPerPage() {
/* 181 */     return this.recordCountPerPage;
/*     */   }
/*     */ 
/*     */   public void setRecordCountPerPage(int recordCountPerPage) {
/* 185 */     this.recordCountPerPage = recordCountPerPage;
/*     */   }
/*     */ 
/*     */   public String getQueryString() {
/* 189 */     return this.queryString;
/*     */   }
/*     */ 
/*     */   public void setQueryString(String queryString) {
/* 193 */     this.queryString = queryString;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 198 */     return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
/*     */   }
/*     */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.board.vo.BoardInfoVO
 * JD-Core Version:    0.6.2
 */