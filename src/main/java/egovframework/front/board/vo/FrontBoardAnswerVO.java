/*    */ package egovframework.front.board.vo;
/*    */ 
/*    */ import org.apache.commons.lang3.builder.ToStringBuilder;
/*    */ import org.apache.commons.lang3.builder.ToStringStyle;
/*    */ 
/*    */ public class FrontBoardAnswerVO extends FrontBoardAnswer
/*    */ {
/*    */   private String deptNm;
/*    */ 
/*    */   public String getDeptNm()
/*    */   {
/* 11 */     return this.deptNm;
/*    */   }
/*    */ 
/*    */   public void setDeptNm(String deptNm) {
/* 15 */     this.deptNm = deptNm;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 20 */     return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
/*    */   }
/*    */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.board.vo.BoardAnswerVO
 * JD-Core Version:    0.6.2
 */