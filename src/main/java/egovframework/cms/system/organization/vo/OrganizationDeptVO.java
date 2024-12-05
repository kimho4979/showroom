/*    */ package egovframework.cms.system.organization.vo;
/*    */ 
/*    */ import org.apache.commons.lang3.builder.ToStringBuilder;
/*    */ import org.apache.commons.lang3.builder.ToStringStyle;
/*    */ 
/*    */ public class OrganizationDeptVO extends OrganizationDept
/*    */ {
/*    */   private String[] LoginGroupIdArr;
/*    */   private String[] searchDeptIds;
/*    */   private String chldrnDept;
/*    */ 
/*    */   public String[] getLoginGroupIdArr()
/*    */   {
/* 15 */     return this.LoginGroupIdArr;
/*    */   }
/*    */ 
/*    */   public void setLoginGroupIdArr(String[] loginGroupIdArr) {
/* 19 */     this.LoginGroupIdArr = loginGroupIdArr;
/*    */   }
/*    */ 
/*    */   public String[] getSearchDeptIds() {
/* 23 */     return this.searchDeptIds;
/*    */   }
/*    */ 
/*    */   public void setSearchDeptIds(String[] searchDeptIds) {
/* 27 */     this.searchDeptIds = searchDeptIds;
/*    */   }
/*    */ 
/*    */   public String getChldrnDept() {
/* 31 */     return this.chldrnDept;
/*    */   }
/*    */ 
/*    */   public void setChldrnDept(String chldrnDept) {
/* 35 */     this.chldrnDept = chldrnDept;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 40 */     return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
/*    */   }
/*    */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.system.organization.vo.OrganizationDeptVO
 * JD-Core Version:    0.6.2
 */