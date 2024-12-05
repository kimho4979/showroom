/*    */ package egovframework.cms.cmm;
/*    */ 
/*    */ import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
/*    */ import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationRenderer;
/*    */ import java.text.MessageFormat;
/*    */ 
/*    */ public abstract class AbstractPaginationRenderer
/*    */   implements PaginationRenderer
/*    */ {
/*    */   public String firstPageLabel;
/*    */   public String previousPageLabel;
/*    */   public String currentPageLabel;
/*    */   public String otherPageLabel;
/*    */   public String nextPageLabel;
/*    */   public String lastPageLabel;
/* 17 */   public String pageStr = "pageIndex";
/*    */ 
/*    */   public String renderPagination(PaginationInfo paginationInfo, String jsFunction)
/*    */   {

/* 22 */     if (!"".equals(jsFunction)) {
/* 23 */       jsFunction = jsFunction + "&";
/*    */     }
/* 25 */     StringBuffer strBuff = new StringBuffer();
/*    */ 
/* 27 */     int firstPageNo = paginationInfo.getFirstPageNo();
/* 28 */     int firstPageNoOnPageList = paginationInfo.getFirstPageNoOnPageList();
/* 29 */     int totalPageCount = paginationInfo.getTotalPageCount();
/* 30 */     int pageSize = paginationInfo.getPageSize();
/* 31 */     int lastPageNoOnPageList = paginationInfo.getLastPageNoOnPageList();
/* 32 */     int currentPageNo = paginationInfo.getCurrentPageNo();
/* 33 */     int lastPageNo = paginationInfo.getLastPageNo();
/*    */ 
/* 35 */     if (totalPageCount > pageSize) {
/* 36 */       if (firstPageNoOnPageList > pageSize) {
/* 37 */         strBuff.append(MessageFormat.format(this.firstPageLabel, new Object[] { jsFunction, this.pageStr, Integer.toString(firstPageNo) }));
/* 38 */         strBuff.append(MessageFormat.format(this.previousPageLabel, new Object[] { jsFunction, this.pageStr, Integer.toString(firstPageNoOnPageList - 1) }));
/*    */       } else {
/* 40 */         strBuff.append(MessageFormat.format(this.firstPageLabel, new Object[] { jsFunction, this.pageStr, Integer.toString(firstPageNo) }));
/* 41 */         strBuff.append(MessageFormat.format(this.previousPageLabel, new Object[] { jsFunction, this.pageStr, Integer.toString(firstPageNo) }));
/*    */       }
/*    */     }
/*    */ 
/* 45 */     for (int i = firstPageNoOnPageList; i <= lastPageNoOnPageList; i++) {
/* 46 */       if (i == currentPageNo)
/* 47 */         strBuff.append(MessageFormat.format(this.currentPageLabel, new Object[] { Integer.toString(i) }));
/*    */       else {
/* 49 */         strBuff.append(MessageFormat.format(this.otherPageLabel, new Object[] { jsFunction, this.pageStr, Integer.toString(i), Integer.toString(i) }));
/*    */       }
/*    */     }
/*    */ 
/* 53 */     if (totalPageCount > pageSize) {
/* 54 */       if (lastPageNoOnPageList < totalPageCount) {
/* 55 */         strBuff.append(MessageFormat.format(this.nextPageLabel, new Object[] { jsFunction, this.pageStr, Integer.toString(firstPageNoOnPageList + pageSize) }));
/* 56 */         strBuff.append(MessageFormat.format(this.lastPageLabel, new Object[] { jsFunction, this.pageStr, Integer.toString(lastPageNo) }));
/*    */       } else {
/* 58 */         strBuff.append(MessageFormat.format(this.nextPageLabel, new Object[] { jsFunction, this.pageStr, Integer.toString(lastPageNo) }));
/* 59 */         strBuff.append(MessageFormat.format(this.lastPageLabel, new Object[] { jsFunction, this.pageStr, Integer.toString(lastPageNo) }));
/*    */       }
/*    */     }
/* 62 */     return strBuff.toString();
/*    */   }
/*    */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.cmm.AbstractPaginationRenderer
 * JD-Core Version:    0.6.2
 */