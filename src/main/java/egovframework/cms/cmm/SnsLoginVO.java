/*    */ package egovframework.cms.cmm;
/*    */ 
/*    */ import org.apache.commons.lang3.builder.ToStringBuilder;
/*    */ import org.apache.commons.lang3.builder.ToStringStyle;
/*    */ 
/*    */ public class SnsLoginVO
/*    */ {
/*    */   private String id;
/*    */   private String name;
/*    */   private String email;
/*    */   private String birthday;
/*    */   private String gender;
/*    */   private String gubun;
/*    */ 
/*    */   public String getId()
/*    */   {
/* 21 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(String id) {
/* 25 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String getName() {
/* 29 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 33 */     this.name = name;
/*    */   }
/*    */ 
/*    */   public String getEmail() {
/* 37 */     return this.email;
/*    */   }
/*    */ 
/*    */   public void setEmail(String email) {
/* 41 */     this.email = email;
/*    */   }
/*    */ 
/*    */   public String getBirthday() {
/* 45 */     return this.birthday;
/*    */   }
/*    */ 
/*    */   public void setBirthday(String birthday) {
/* 49 */     this.birthday = birthday;
/*    */   }
/*    */ 
/*    */   public String getGender() {
/* 53 */     return this.gender;
/*    */   }
/*    */ 
/*    */   public void setGender(String gender) {
/* 57 */     this.gender = gender;
/*    */   }
/*    */ 
/*    */   public String getGubun() {
/* 61 */     return this.gubun;
/*    */   }
/*    */ 
/*    */   public void setGubun(String gubun) {
/* 65 */     this.gubun = gubun;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 70 */     return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
/*    */   }
/*    */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.cmm.SnsLoginVO
 * JD-Core Version:    0.6.2
 */