/*     */ package egovframework.cms.cmm.service;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.lang3.builder.ToStringBuilder;
/*     */ 
/*     */ public class FileVO
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -287950405903719128L;
/*  31 */   public String atchFileId = "";
/*     */ 
/*  35 */   public String creatDt = "";
/*     */ 
/*  39 */   public String fileCn = "";
/*     */ 
/*  43 */   public String fileExtsn = "";
/*     */ 
/*  47 */   public String fileMg = "";
/*     */ 
/*  51 */   public String fileSn = "";
/*     */ 
/*  55 */   public String fileStreCours = "";
/*     */ 
/*  59 */   public String orignlFileNm = "";
/*     */ 
/*  63 */   public String streFileNm = "";
/*     */ 
/*  65 */   public String sync = "";
/*     */ 
/*     */   public String getAtchFileId()
/*     */   {
/*  73 */     return this.atchFileId;
/*     */   }
/*     */ 
/*     */   public void setAtchFileId(String atchFileId)
/*     */   {
/*  83 */     this.atchFileId = atchFileId;
/*     */   }
/*     */ 
/*     */   public String getCreatDt()
/*     */   {
/*  92 */     return this.creatDt;
/*     */   }
/*     */ 
/*     */   public void setCreatDt(String creatDt)
/*     */   {
/* 102 */     this.creatDt = creatDt;
/*     */   }
/*     */ 
/*     */   public String getFileCn()
/*     */   {
/* 111 */     return this.fileCn;
/*     */   }
/*     */ 
/*     */   public void setFileCn(String fileCn)
/*     */   {
/* 121 */     this.fileCn = fileCn;
/*     */   }
/*     */ 
/*     */   public String getFileExtsn()
/*     */   {
/* 130 */     return this.fileExtsn;
/*     */   }
/*     */ 
/*     */   public void setFileExtsn(String fileExtsn)
/*     */   {
/* 140 */     this.fileExtsn = fileExtsn;
/*     */   }
/*     */ 
/*     */   public String getFileMg()
/*     */   {
/* 149 */     return this.fileMg;
/*     */   }
/*     */ 
/*     */   public void setFileMg(String fileMg)
/*     */   {
/* 159 */     this.fileMg = fileMg;
/*     */   }
/*     */ 
/*     */   public String getFileSn()
/*     */   {
/* 168 */     return this.fileSn;
/*     */   }
/*     */ 
/*     */   public void setFileSn(String fileSn)
/*     */   {
/* 178 */     this.fileSn = fileSn;
/*     */   }
/*     */ 
/*     */   public String getFileStreCours()
/*     */   {
/* 187 */     return this.fileStreCours;
/*     */   }
/*     */ 
/*     */   public void setFileStreCours(String fileStreCours)
/*     */   {
/* 197 */     this.fileStreCours = fileStreCours;
/*     */   }
/*     */ 
/*     */   public String getOrignlFileNm()
/*     */   {
/* 206 */     return this.orignlFileNm;
/*     */   }
/*     */ 
/*     */   public void setOrignlFileNm(String orignlFileNm)
/*     */   {
/* 216 */     this.orignlFileNm = orignlFileNm;
/*     */   }
/*     */ 
/*     */   public String getStreFileNm()
/*     */   {
/* 225 */     return this.streFileNm;
/*     */   }
/*     */ 
/*     */   public void setStreFileNm(String streFileNm)
/*     */   {
/* 235 */     this.streFileNm = streFileNm;
/*     */   }
/*     */ 
/*     */   public String getSync() {
/* 239 */     return this.sync;
/*     */   }
/*     */ 
/*     */   public void setSync(String sync) {
/* 243 */     this.sync = sync;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 250 */     return ToStringBuilder.reflectionToString(this);
/*     */   }
/*     */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.cmm.service.FileVO
 * JD-Core Version:    0.6.2
 */