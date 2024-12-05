/*     */ package egovframework.cms.cmm;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.lang3.builder.ToStringBuilder;
/*     */ 
/*     */ public class ComDefaultCodeVO
/*     */   implements Serializable
/*     */ {
/*  26 */   private String codeId = "";
/*     */ 
/*  29 */   private String code = "";
/*     */ 
/*  32 */   private String codeNm = "";
/*     */ 
/*  35 */   private String codeDc = "";
/*     */ 
/*  38 */   private String tableNm = "";
/*     */ 
/*  41 */   private String haveDetailCondition = "N";
/*     */ 
/*  44 */   private String detailCondition = "";
/*     */ 
/*     */   public String getCodeId()
/*     */   {
/*  52 */     return this.codeId;
/*     */   }
/*     */ 
/*     */   public void setCodeId(String codeId)
/*     */   {
/*  62 */     this.codeId = codeId;
/*     */   }
/*     */ 
/*     */   public String getCode()
/*     */   {
/*  71 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/*  81 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public String getCodeNm()
/*     */   {
/*  90 */     return this.codeNm;
/*     */   }
/*     */ 
/*     */   public void setCodeNm(String codeNm)
/*     */   {
/* 100 */     this.codeNm = codeNm;
/*     */   }
/*     */ 
/*     */   public String getCodeDc()
/*     */   {
/* 109 */     return this.codeDc;
/*     */   }
/*     */ 
/*     */   public void setCodeDc(String codeDc)
/*     */   {
/* 119 */     this.codeDc = codeDc;
/*     */   }
/*     */ 
/*     */   public String getTableNm()
/*     */   {
/* 128 */     return this.tableNm;
/*     */   }
/*     */ 
/*     */   public void setTableNm(String tableNm)
/*     */   {
/* 138 */     this.tableNm = tableNm;
/*     */   }
/*     */ 
/*     */   public String getHaveDetailCondition()
/*     */   {
/* 147 */     return this.haveDetailCondition;
/*     */   }
/*     */ 
/*     */   public void setHaveDetailCondition(String haveDetailCondition)
/*     */   {
/* 157 */     this.haveDetailCondition = haveDetailCondition;
/*     */   }
/*     */ 
/*     */   public String getDetailCondition()
/*     */   {
/* 166 */     return this.detailCondition;
/*     */   }
/*     */ 
/*     */   public void setDetailCondition(String detailCondition)
/*     */   {
/* 176 */     this.detailCondition = detailCondition;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 183 */     return ToStringBuilder.reflectionToString(this);
/*     */   }
/*     */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.cmm.ComDefaultCodeVO
 * JD-Core Version:    0.6.2
 */