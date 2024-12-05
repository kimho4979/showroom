/*     */ package egovframework.cms.cmm.service;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class EgovFormBasedFileVo
/*     */   implements Serializable
/*     */ {
/*  24 */   private String fileName = "";
/*     */ 
/*  26 */   private String contentType = "";
/*     */ 
/*  28 */   private String serverSubPath = "";
/*     */ 
/*  30 */   private String physicalName = "";
/*     */ 
/*  32 */   private long size = 0L;
/*     */ 
/*     */   public String getFileName()
/*     */   {
/*  39 */     return this.fileName;
/*     */   }
/*     */ 
/*     */   public void setFileName(String fileName)
/*     */   {
/*  46 */     this.fileName = fileName;
/*     */   }
/*     */ 
/*     */   public String getContentType()
/*     */   {
/*  53 */     return this.contentType;
/*     */   }
/*     */ 
/*     */   public void setContentType(String contentType)
/*     */   {
/*  60 */     this.contentType = contentType;
/*     */   }
/*     */ 
/*     */   public String getServerSubPath()
/*     */   {
/*  67 */     return this.serverSubPath;
/*     */   }
/*     */ 
/*     */   public void setServerSubPath(String serverSubPath)
/*     */   {
/*  74 */     this.serverSubPath = serverSubPath;
/*     */   }
/*     */ 
/*     */   public String getPhysicalName()
/*     */   {
/*  81 */     return this.physicalName;
/*     */   }
/*     */ 
/*     */   public void setPhysicalName(String physicalName)
/*     */   {
/*  88 */     this.physicalName = physicalName;
/*     */   }
/*     */ 
/*     */   public long getSize()
/*     */   {
/*  95 */     return this.size;
/*     */   }
/*     */ 
/*     */   public void setSize(long size)
/*     */   {
/* 102 */     this.size = size;
/*     */   }
/*     */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.cmm.service.EgovFormBasedFileVo
 * JD-Core Version:    0.6.2
 */